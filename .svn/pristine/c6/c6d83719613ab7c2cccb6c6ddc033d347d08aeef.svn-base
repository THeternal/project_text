package com.kemean.service.consumer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenImMessageTypeEnum;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.vo.mysql.ImRecordRedis;

/**
 * 【客户端】消息业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class CMessageService {

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	/**
	 * IM记录
	 * 
	 * @author huwei
	 * @date 2018年7月24日
	 */
	public void imRecord(ImRecordRedis imRecordPO) {
		DaikenUser formUser = daikenUserDao.selectUniqueEntityByProperty("uid", imRecordPO.getFromUserId());
		DaikenUser toUser = daikenUserDao.selectUniqueEntityByProperty("uid", imRecordPO.getToUserId());
		imRecordPO.setDate(new Date());
		imRecordPO.setName(toUser.getNickName());
		imRecordPO.setHeadImg(toUser.getHeadImg());
		if (toUser.getShopId() != null) {
			DaikenShop dbShop = daikenShopDao.selectById(toUser.getShopId());
			if (dbShop != null) {
				imRecordPO.setName(dbShop.getShopName());
				imRecordPO.setHeadImg(dbShop.getShopLogo());
			}
		}
		imRecordPO.setFromUserId(formUser.getUid() + "");
		if (DaikenImMessageTypeEnum.TEXT.getType().equals(imRecordPO.getMessageType())) {
			JSONObject json = JSONObject.parseObject(imRecordPO.getContent());
			String content = json.get("content").toString();
			imRecordPO.setContent(content);
		}
		if (DaikenImMessageTypeEnum.IMG.getType().equals(imRecordPO.getMessageType())) {
			imRecordPO.setContent("[图片]");
		}
		if (DaikenImMessageTypeEnum.POSITION.getType().equals(imRecordPO.getMessageType())) {
			imRecordPO.setContent("[位置]");
		}
		if (DaikenImMessageTypeEnum.VOICE.getType().equals(imRecordPO.getMessageType())) {
			imRecordPO.setContent("[语音]");
		}
		kemeanRedisService.hset(
				String.format(DaikenRedisKeyEnum.IM_RECORD_USER_COMVERSATION.getKey(), imRecordPO.getFromUserId()),
				toUser.getUid() + "", JSONObject.toJSONString(imRecordPO));
	}
}
