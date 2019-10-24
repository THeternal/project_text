package com.kemean.service.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenImMessageTypeEnum;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.dao.DaikenShopDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.vo.mysql.ImRecordRedis;

/**
 * 【商户端】消息业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class BMessageService {

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	/**
	 * IM记录
	 */
	public void imRecord(ImRecordRedis imRecordPO, DaikenUser loginBusiness) {
		imRecordPO.setDate(new Date());
		// 塞入店铺名称和logo
		DaikenShop dbShop = daikenShopDao.selectById(loginBusiness.getShopId());
		imRecordPO.setName(dbShop.getShopName());
		imRecordPO.setHeadImg(dbShop.getShopLogo());
		imRecordPO.setFromUserId(loginBusiness.getUid() + "");
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
				String.format(DaikenRedisKeyEnum.IM_RECORD_USER_COMVERSATION.getKey(), imRecordPO.getToUserId()),
				imRecordPO.getFromUserId() + "", JSONObject.toJSONString(imRecordPO));
		kemeanRedisService.incrBy(String.format(DaikenRedisKeyEnum.IM_RECORD_MESSAGE_NUMBER.getKey(),
				imRecordPO.getToUserId(), loginBusiness.getUid()), 1);
	}

}
