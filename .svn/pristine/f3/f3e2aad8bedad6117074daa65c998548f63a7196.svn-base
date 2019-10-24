package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.KemeanMessage;
import com.kemean.bean.KemeanMessageUser;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenMessageType.MessageTypeEnum;
import com.kemean.constant.DaikenPushJumType;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanMessageDao;
import com.kemean.dao.KemeanMessageUserDao;
import com.kemean.service.PushJGService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.message.AdminMessageBO;
import com.kemean.vo.po.admin.message.AdminMessagePO;

@Service
@PropertySource(value = "classpath:kemean/kemean_third.properties", encoding = "UTF-8")
public class AdminMessageService {

	@Value("${jg.base64}")
	private String jgBase64;

	@Autowired
	private KemeanMessageDao kemeanMessageDao;

	@Autowired
	private KemeanMessageUserDao kemeanMessageUserDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private PushJGService pushJGService;

	/**
	 * 消息列表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	public KemeanPageAdminBO<List<AdminMessageBO>> messageListData(AdminMessagePO adminMessagePO) {

		List<Integer> type = new ArrayList<>();
		if (adminMessagePO.getType() == null) {
			type = Arrays.asList(MessageTypeEnum.CONSUMER.getType(), MessageTypeEnum.SHOP.getType(),
					MessageTypeEnum.SYSTEM.getType());
		} else {
			type.add(adminMessagePO.getType());
		}

		List<KemeanMessage> dbMessage = kemeanMessageDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false }, "type", type, "update_time",
				false, adminMessagePO.getPage(), adminMessagePO.getLimit());

		List<AdminMessageBO> result = Lists.transform(dbMessage, new Function<KemeanMessage, AdminMessageBO>() {
			@Override
			public AdminMessageBO apply(KemeanMessage msg) {
				AdminMessageBO bo = new AdminMessageBO();
				BeanUtils.copyProperties(msg, bo);
				bo.setTypeStr(DaikenMapData.messageType.get(msg.getType()));
				return bo;
			}
		});
		return new KemeanPageAdminBO<List<AdminMessageBO>>(new PageInfo<>(dbMessage).getTotal(), result);
	}

	/**
	 * 添加消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	public KemeanResult<String> messageAdd(AdminMessagePO adminMessagePO) {
		KemeanMessage newMessage = new KemeanMessage();
		Date now = new Date();
		newMessage.setRecord(adminMessagePO.getRecord());
		newMessage.setTitle(adminMessagePO.getTitle());
		newMessage.setType(adminMessagePO.getType());
		newMessage.setCreateTime(now);
		newMessage.setUpdateTime(now);
		kemeanMessageDao.saveSelective(newMessage);

		// 系统消息
		if (MessageTypeEnum.SYSTEM.getType().equals(adminMessagePO.getType())) {
			List<DaikenUser> dbUser = daikenUserDao.selectByProperty(KemeanConstant.DATA_DELETED, false);
			messageUserSave(dbUser, newMessage.getId(), adminMessagePO.getType(), adminMessagePO.getTitle(),
					adminMessagePO.getRecord());

			return new KemeanResult<>(true, KemeanTips.Operate.ADD_SUCCESS);
		}

		// 用户消息、messageType跟userType对应
		List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
				new String[] { "userType", KemeanConstant.DATA_DELETED },
				new Object[] { adminMessagePO.getType(), false });
		String[] sendUserUids = new String[dbUser.size()];
		for (int i = 0; i < dbUser.size(); i++) {
			sendUserUids[i] = String.valueOf(dbUser.get(i).getUid());
		}

		// 极光推送
		// 商家
		if (adminMessagePO.getType().equals(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType())) {
			pushJGService.notification(jgBase64, false, sendUserUids, "你有一份来自新的代研消息，请接收", "jump",
					DaikenPushJumType.ORDER.getType());
		}

		if (dbUser.isEmpty()) {
			dbUser.add(daikenUserDao.selectById(adminMessagePO.getUserId()));
		}
		messageUserSave(dbUser, newMessage.getId(), adminMessagePO.getType(), adminMessagePO.getTitle(),
				adminMessagePO.getRecord());

		// pushJGService.notification(jgBase64B, false, sendUserUids, "你有新的商品订单",
		// "jump",
		// DaikenPushJumType.ORDER.getType());

		return new KemeanResult<>(true, KemeanTips.Operate.ADD_SUCCESS);
	}

	/**
	 * 修改消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月9日
	 */
	@Transactional
	public KemeanResult<String> messageUpdateData(AdminMessagePO adminMessagePO) {
		KemeanMessage dbMessage = kemeanMessageDao.selectById(adminMessagePO.getObjId());
		Date now = new Date();
		dbMessage.setType(adminMessagePO.getType());
		dbMessage.setRecord(adminMessagePO.getRecord());
		dbMessage.setUpdateTime(now);
		kemeanMessageDao.updateByPrimaryKeySelective(dbMessage);
		// 遍历 消息对应的用户， 并删除
		List<KemeanMessageUser> dbMessageUser = kemeanMessageUserDao.selectByProperties(
				new String[] { "messageId", KemeanConstant.DATA_DELETED },
				new Object[] { adminMessagePO.getObjId(), false });
		for (KemeanMessageUser item : dbMessageUser) {
			item.setUpdateTime(now);
			item.setDataDeleted(true);
			kemeanMessageUserDao.updateByPrimaryKeySelective(item);
		}
		// 遍历 消息类型对应的用户
		List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
				new String[] { "userType", KemeanConstant.DATA_DELETED },
				new Object[] { adminMessagePO.getType(), false });
		for (DaikenUser item : dbUser) {
			KemeanMessageUser newMessageUser = new KemeanMessageUser();
			newMessageUser.setUpdateTime(now);
			newMessageUser.setCreateTime(now);
			newMessageUser.setUserId(item.getId());
			newMessageUser.setMessageId(dbMessage.getId());
			newMessageUser.setMessageType(adminMessagePO.getType());
			newMessageUser.setMessageTitle(adminMessagePO.getTitle());
			newMessageUser.setMessageRecord(adminMessagePO.getRecord());
			newMessageUser.setReaded(false);
			kemeanMessageUserDao.saveSelective(newMessageUser);
		}
		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 查询消息明细
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月9日
	 */

	public AdminMessageBO messageInfo(Integer objId) {
		KemeanMessage dbMessage = kemeanMessageDao.selectById(objId);
		AdminMessageBO bo = new AdminMessageBO();
		BeanUtils.copyProperties(dbMessage, bo);
		return bo;
	}

	/**
	 * 删除消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月9日
	 */
	@Transactional
	public KemeanResult<String> messageDel(Integer objId) {
		Date now = new Date();
		KemeanMessage dbMessage = kemeanMessageDao.selectById(objId);
		dbMessage.setUpdateTime(now);
		dbMessage.setDataDeleted(true);
		kemeanMessageDao.updateByPrimaryKeySelective(dbMessage);

		List<KemeanMessageUser> dbMessageUser = kemeanMessageUserDao.selectByProperties(
				new String[] { "messageId", KemeanConstant.DATA_DELETED }, new Object[] { objId, false });
		for (KemeanMessageUser user : dbMessageUser) {
			user.setUpdateTime(now);
			user.setDataDeleted(true);
			kemeanMessageUserDao.updateByPrimaryKeySelective(user);
		}
		return new KemeanResult<>(true, KemeanTips.Operate.DELETE_SUCCESS);
	}

	/**
	 * 构建消息接收人
	 * 
	 * @author huangyujian
	 * @date 2018年4月24日
	 */
	private void messageUserSave(List<DaikenUser> user, Integer messageId, Integer type, String title, String record) {
		Date now = new Date();
		for (DaikenUser item : user) {
			KemeanMessageUser newMessageUser = new KemeanMessageUser();
			newMessageUser.setCreateTime(now);
			newMessageUser.setUpdateTime(now);
			newMessageUser.setUserId(item.getId());
			newMessageUser.setMessageRecord(record);
			newMessageUser.setMessageTitle(title);
			newMessageUser.setMessageId(messageId);
			newMessageUser.setMessageType(type);
			newMessageUser.setReaded(false);
			kemeanMessageUserDao.saveSelective(newMessageUser);
		}
	}

}
