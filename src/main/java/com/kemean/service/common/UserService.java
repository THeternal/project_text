package com.kemean.service.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenInvestigate;
import com.kemean.bean.DaikenInvestigateQuestionUser;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserBase;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenMessageType;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenSmsTypeEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserStatusEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.dao.DaikenInvestigateDao;
import com.kemean.dao.DaikenInvestigateQuestionUserDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserBaseDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.dao.KemeanMessageUserDao;
import com.kemean.service.ImRyService;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.SmsService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.WxLoginAppletsBO;
import com.kemean.vo.bo.c.mine.finance.EarningsDetailBO;
import com.kemean.vo.bo.com.CBaseUserInfoBO;
import com.kemean.vo.bo.com.HobbiesInterestsBO;
import com.kemean.vo.db.SmsConfigBO;
import com.kemean.vo.mysql.ImRecordRedis;
import com.kemean.vo.po.api.WxAppletsLoginPO;
import com.kemean.vo.po.com.SendCodePO;

/**
 * 
 * 【公共】用户业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class UserService {

	/** 需要校验手机号已注册的验证码类型 **/
	private static final List<Integer> SMS_CHECK = Arrays.asList(DaikenSmsTypeEnum.LOGIN_B.getType());

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenUserBaseDao daikenUserBaseDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenInvestigateQuestionUserDao daikenInvestigateQuestionUserDao;

	@Autowired
	private KemeanFinanceOrderDaikenDao kemeanFinanceOrderDao;

	@Autowired
	private KemeanMessageUserDao kemeanMessageUserDao;

	@Autowired
	private DaikenInvestigateDao daikenInvestigateDao;

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private ImRyService imRyService;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ThirdService thirdService;

	@Autowired
	private KemeanRedisService redisService;

	/**
	 * 发送验证码
	 * 
	 * @author huwei
	 * @date 2018年6月13日
	 */
	public KemeanResult<String> sendCode(SendCodePO sendCodePO) {
		boolean flag = false;
		Integer type = sendCodePO.getType();
		for (DaikenSmsTypeEnum item : DaikenSmsTypeEnum.values()) {
			if (item.getType().equals(type)) {
				flag = true;
			}
		}
		if (!flag) {
			return new KemeanResult<>(false, DaikenApiResultTips.SendCode.TYPE_INVALID);
		}
		// 登陆检查
		if (SMS_CHECK.contains(type)) {
			DaikenUser dbUser = daikenUserDao.selectByPhone(sendCodePO.getPhone(),
					Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType()));
			if (dbUser == null) {
				return new KemeanResult<>(false, DaikenApiResultTips.SendCode.PHONE_NOT_REGISTERED);
			}
			if (DaikenUserStatusEnum.DISABLED.getStatus().equals(dbUser.getUserStatus())) {
				return new KemeanResult<>(false, DaikenApiResultTips.Login.ACCOUNT_DISABLED);
			}
		}
		String msg = "";
		String code = KemeanUtilAid.getRandomNumCode(6);
		// 注册
		if (DaikenSmsTypeEnum.REGISTERED_B.getType().equals(type)) {
			if (daikenUserDao.selectByPhone(sendCodePO.getPhone(),
					Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType())) != null) {
				return new KemeanResult<>(false, DaikenApiResultTips.SendCode.PHONE_REGISTERED);
			}
			msg = String.format(commonService.getConfig(DaikenConfigEnum.SMS_REGISTERED).getRecord(), code);
		}
		// 忘记密码
		if (DaikenSmsTypeEnum.FORGET_B.getType().equals(type)) {
			msg = String.format(commonService.getConfig(DaikenConfigEnum.SMS_RESET_PASSWORD).getRecord(), code);
		}
		// 修改手机号
		if (DaikenSmsTypeEnum.C_UPDATE_PHONE.getType().equals(type)) {
			msg = String.format(commonService.getConfig(DaikenConfigEnum.SMS_RESET_PHONE).getRecord(), code);
		}
		// 提现
		if (DaikenSmsTypeEnum.C_WITHDRAW.getType().equals(type)
				|| DaikenSmsTypeEnum.B_WITHDRAW.getType().equals(type)) {
			msg = String.format(commonService.getConfig(DaikenConfigEnum.WITH_DRAW).getRecord(), code);
		}
		// 提现成功
		if (DaikenSmsTypeEnum.WITHDRAW_SUCCESS.getType().equals(type)) {
			msg = String.format(commonService.getConfig(DaikenConfigEnum.WITH_DRAW_SUCCESS).getRecord(),
					sendCodePO.getMoney());
		}
		KemeanResult<String> sendResult = daikenSendCode(sendCodePO.getPhone(), msg);
		if (!sendResult.getSuccess()) {
			return new KemeanResult<>(false, DaikenApiResultTips.SendCode.GET_FAILURE);
		}
		if (DaikenSmsTypeEnum.WITHDRAW_SUCCESS.getType().equals(type)) {
			return new KemeanResult<String>();
		}
		String cachaKey = String.format(DaikenRedisKeyEnum.CODE.getKey(), type, sendCodePO.getPhone());
		redisService.setString(cachaKey, code);
		return new KemeanResult<String>();
	}

	/**
	 * 项目唯一短信入口
	 * 
	 * @author huangyujian
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> daikenSendCode(String phone, String msg) {
		String record = commonService.getConfig(DaikenConfigEnum.SMS_CONFIG).getRecord();
		return smsService.smsSend(phone, msg, JSONObject.parseObject(record, SmsConfigBO.class));
	}

	/**
	 * 小程序登录
	 * 
	 * @author huangyujian
	 * @date 2018年6月25日
	 */
	public KemeanResult<CBaseUserInfoBO> miniAppsLogin(WxAppletsLoginPO wxAppletsLoginPO) {
		Date now = new Date();
		WxLoginAppletsBO loginResult = thirdService.appletsLogin(wxAppletsLoginPO.getCode(),
				wxAppletsLoginPO.getEncryptedData(), wxAppletsLoginPO.getIv());
		DaikenUser dbUser = daikenUserDao.selectUniqueEntityByProperty("wxOpenId", loginResult.getOpenid());
		if (dbUser == null) {
			dbUser = new DaikenUser();
			dbUser.setUpdateTime(now);
			dbUser.setCreateTime(now);
			dbUser.setUid(commonService.getUid());
			dbUser.setUserType(DaikenUserEnum.DaikenUserTypeEnum.CONSUMER.getType());
			dbUser.setToken(KemeanUtilAid.getUUIDString());
			// 密码针对用户端无实际操作
			dbUser.setPassword(KemeanUtilAid.md5("123123"));
			// 头像
			if (StringUtils.isNoneBlank(loginResult.getAvatarUrl())) {
				dbUser.setHeadImg(loginResult.getAvatarUrl());
			}
			dbUser.setNickName(DaikenUtil.filterEmoji(loginResult.getNickName(), ""));
			dbUser.setSexMan(loginResult.getGender().equals("1"));
			dbUser.setWxOpenId(loginResult.getOpenid());
			dbUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.NORMAL.getStatus());
			dbUser.setTokenRy(imRyService.userToken(dbUser.getUid() + "", dbUser.getNickName(), dbUser.getHeadImg()));
			daikenUserDao.saveSelective(dbUser);
		}
		if (StringUtils.isBlank(dbUser.getTokenRy())) {
			dbUser.setTokenRy(imRyService.userToken(dbUser.getUid() + "", dbUser.getNickName(), dbUser.getHeadImg()));
		}
		redisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), dbUser.getToken()));
		dbUser.setServiceMark(getServiceUid(dbUser, true));
		dbUser.setToken(DaikenUtil.getUUIDString());
		dbUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		return getUserInfo(dbUser, true);
	}

	// 获取系统分配的客服UID
	public Integer getServiceUid(DaikenUser dbUser, Boolean login) {
		String cacheKey = DaikenRedisKeyEnum.SERVICE_LIST_H.getKey();
		String cacheHashKey = dbUser.getServiceMark() + "";
		Date now = new Date();
		if (!login) {
			String cacheService = kemeanRedisService.hget(cacheKey, cacheHashKey);
			if (StringUtils.isNoneBlank(cacheService)) {
				Date cacheDate = KemeanUtilAid.parseDate(cacheService, KemeanDateFormatEnum.DATE_NUM);
				int twoDayMinute = KemeanUtilAid.getTwoDayMinute(cacheDate, now);
				if (twoDayMinute < 60)
					return dbUser.getServiceMark();
			}
		}
		// 处理过期客服
		Map<String, String> hGetAll = kemeanRedisService.hGetAll(cacheKey);
		for (String hashKeyItem : hGetAll.keySet()) {
			Date cacheDate = KemeanUtilAid.parseDate(hGetAll.get(hashKeyItem), KemeanDateFormatEnum.DATE_NUM);
			int twoDayMinute = KemeanUtilAid.getTwoDayMinute(cacheDate, now);
			if (twoDayMinute < 60) {
				continue;
			}
			kemeanRedisService.hdel(cacheKey, hashKeyItem);
		}
		// 真实活动客服
		Map<String, String> activityService = kemeanRedisService.hGetAll(cacheKey);
		// 如果不存在活动客服，则返回默认客服ID
		if (activityService.isEmpty()) {
			return Integer.valueOf(commonService.getConfig(DaikenConfigEnum.IM_SERVICE_UID).getRecord());
		}
		int randomIndex = new Random().nextInt(activityService.size());
		int index = 0;
		for (String hashKeyItem : activityService.keySet()) {
			if (index == randomIndex) {
				return Integer.valueOf(hashKeyItem);
			}
			index += 1;
		}
		return Integer.valueOf(commonService.getConfig(DaikenConfigEnum.IM_SERVICE_UID).getRecord());
	}

	/**
	 * 构造用户的基本信息
	 */
	public KemeanResult<CBaseUserInfoBO> getUserInfo(DaikenUser dbUser, Boolean login) {
		CBaseUserInfoBO result = new CBaseUserInfoBO();
		BeanUtils.copyProperties(dbUser, result);
		String provinceName = "";
		String cityName = "";
		String areaName = "";
		if (StringUtils.isNoneBlank(dbUser.getProvinceName())) {
			provinceName = dbUser.getProvinceName();
		}
		if (StringUtils.isNoneBlank(dbUser.getCityName())) {
			cityName = dbUser.getCityName();
		}
		if (StringUtils.isNoneBlank(dbUser.getAreaName())) {
			areaName = dbUser.getAreaName();
		}
		result.setAreaName(areaName);
		result.setCityName(cityName);
		result.setProvinceName(provinceName);
		result.setUid(dbUser.getUid());
		if (dbUser.getSexMan() != null) {
			result.setSexMan(dbUser.getSexMan() ? "男" : "女");
		}
		// 随机分配的客服UID
		// 融云客服uid
		result.setServiceUserUid(dbUser.getServiceMark());
		// 职业
		if (dbUser.getProfession() != null && dbUser.getProfession() != 0) {
			DaikenUserBase dbUserBase = daikenUserBaseDao.selectById(dbUser.getProfession());
			result.setProfessionInt(dbUserBase.getId());
			result.setProfession(dbUserBase.getContent());
		}
		// 兴趣爱好
		if (StringUtils.isNotBlank(dbUser.getHobbiesInterests())) {
			List<Integer> hobbiesInterests = JSONArray.parseArray(dbUser.getHobbiesInterests(), Integer.class);
			List<HobbiesInterestsBO> hobbiesInterestsStr = new ArrayList<HobbiesInterestsBO>(hobbiesInterests.size());
			if (CollectionUtils.isNotEmpty(hobbiesInterests)) {
				for (Integer hobbiesInterest : hobbiesInterests) {
					DaikenUserBase dbUserBase = daikenUserBaseDao.selectById(hobbiesInterest);
					HobbiesInterestsBO interestsbo = new HobbiesInterestsBO();
					interestsbo.setHobbiesInterestsStr(dbUserBase.getContent());
					interestsbo.setId(dbUserBase.getId());
					hobbiesInterestsStr.add(interestsbo);
				}
			}
			result.setHobbiesInterests(hobbiesInterestsStr);
		}
		result.setInvestigatePlatformCharge(
				Double.valueOf(commonService.getConfig(DaikenConfigEnum.INVESTIGATE_CHARGE).getRecord()));
		// 资料是否填写完成
		result.setIsFillUserInfo(false);
		if (CommonService.isFillInfo(dbUser)) {
			// 资料填写完成
			result.setIsFillUserInfo(true);
		}
		// 未付款订单
		result.setNumNoPayOrder(daikenOrderDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "idUser", "statusUser", "deletedUser", "idInvestigate" },
				new Object[] { false, dbUser.getId(), KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus(), false,
						0 }));
		// 未发货订单
		result.setNumNoSendOrder(daikenOrderDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "idUser", "statusUser", "deletedUser", "idInvestigate" },
				new Object[] { false, dbUser.getId(), KemeanOrderEnum.OrderStatusUser.PAYED.getStatus(), false, 0 }));
		// 未收货订单
		result.setNumNoTakeGoodsOrder(daikenOrderDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "idUser", "statusUser", "deletedUser", "idInvestigate" },
				new Object[] { false, dbUser.getId(), KemeanOrderEnum.OrderStatusUser.SHIP.getStatus(), false, 0 }));
		// 未评价订单
		result.setNumNoAppraisalOrder(daikenOrderDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "idUser", "statusUser", "deletedUser", "idInvestigate" },
				new Object[] { false, dbUser.getId(), KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus(), false,
						0 }));
		// 待售后订单
		result.setNumNoAfterSaleOrder(daikenOrderDao.countNoAfterSaleOrder(dbUser.getId()));
		// 未调研数量
		List<DaikenInvestigateQuestionUser> dbQuestionUsers = daikenInvestigateQuestionUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "isFinished" },
				new Object[] { false, dbUser.getId(), false });
		Date now = new Date();
		Integer numNoInvestigate = 0;
		if (CollectionUtils.isNotEmpty(dbQuestionUsers)) {
			for (DaikenInvestigateQuestionUser dbQuestionUser : dbQuestionUsers) {
				DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(dbQuestionUser.getInvestigateId());
				// 如果结束时间大于当前时间 + 1
				Integer dateDifference = DaikenUtil.dateDifference(now, dbInvestigate.getEndTime());
				if (dateDifference >= 0) {
					numNoInvestigate += 1;
				}
			}
		}
		result.setNumNoInvestigate(numNoInvestigate);
		// 本月做任务收入 任务（调研，点赞，问卷）
		result.setCurrMonthTaskPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(),
						DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(),
						DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType()),
				true), 2));
		// 本月二手卖货收入
		result.setCurrMonthSellGoodsPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_SELL_GOODS.getType()), true), 2));
		// 本月买货收入（计算售后红包）
		result.setCurrMonthBuyGoodsPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_BEFOR_RED_INCOME.getType()), true), 2));
		// 本月帮卖收入
		result.setCurrMonthHellpGoodsPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType()), true), 2));
		// 帮卖总金额
		Double hellpGoodsSumMoneyPrice = DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType()), false), 2);
		Integer hellpGoodsSumMoneyPriceType = 1101;
		// 帮卖等级
		if (hellpGoodsSumMoneyPrice <= 10000 && hellpGoodsSumMoneyPrice > 5000) {
			hellpGoodsSumMoneyPriceType = 1201;
		}
		if (hellpGoodsSumMoneyPrice <= 30000 && hellpGoodsSumMoneyPrice > 10000) {
			hellpGoodsSumMoneyPriceType = 1301;
		}
		if (hellpGoodsSumMoneyPrice <= 50000 && hellpGoodsSumMoneyPrice > 30000) {
			hellpGoodsSumMoneyPriceType = 1401;
		}
		if (hellpGoodsSumMoneyPrice > 50000) {
			hellpGoodsSumMoneyPriceType = 1501;
		}
		result.setHellpGoodsSumMoneyPriceType(hellpGoodsSumMoneyPriceType);
		// 冻结资金
		Double freezeMoney = DaikenUtil.formatDouble(kemeanFinanceOrderDao.getConsumerMoney(dbUser.getId(), true), 2);
		result.setFreezeMoney(freezeMoney);
		// 已提现
		Double finishMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(dbUser.getId(),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus()), 2);
		result.setFinishMoney(finishMoney);
		// 总资产
		Double sumMoney = DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(), Arrays.asList(
				DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(), DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(),
				DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType(), DaikenFinanceTypeEnum.C_SELL_GOODS.getType(),
				DaikenFinanceTypeEnum.C_BEFOR_RED_INCOME.getType(), DaikenFinanceTypeEnum.C_AFTER_RED_INCOME.getType(),
				DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType()), false), 2);
		result.setSumMoney(sumMoney);
		// 余额 = 未冻结资金 - 提现成功 - 提现中
		Double blancePrice = getConsumerOrBusinessBlance(dbUser);
		result.setBalancePrice(blancePrice);
		// 已领取token总数
		result.setSumToken(daikenUserDao.countToken());
		// 总消息数量 = 平台消息数量 + 融云消息数量
		// 平台消息
		Integer platformMessageCount = kemeanMessageUserDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "messageType", "userId", "readed" },
				new Object[] { false, DaikenMessageType.MessageTypeEnum.SYSTEM.getType(), dbUser.getId(), false })
				+ kemeanMessageUserDao.countByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "messageType", "userId", "readed" }, new Object[] {
								false, DaikenMessageType.MessageTypeEnum.CONSUMER.getType(), dbUser.getId(), false });
		Integer imMessageCount = 0;
		// 融云消息
		Map<String, String> allConversation = kemeanRedisService
				.hGetAll(String.format(DaikenRedisKeyEnum.IM_RECORD_USER_COMVERSATION.getKey(), dbUser.getUid()));
		if (!allConversation.isEmpty()) {
			for (String item : allConversation.keySet()) {
				String conversation = allConversation.get(item);
				ImRecordRedis imRecordPO = JSONObject.parseObject(conversation, ImRecordRedis.class);
				String fromUserId = imRecordPO.getFromUserId();
				String messageNum = kemeanRedisService.getString(String
						.format(DaikenRedisKeyEnum.IM_RECORD_MESSAGE_NUMBER.getKey(), dbUser.getUid(), fromUserId));
				if (StringUtils.isNotBlank(messageNum)) {
					imMessageCount = +Integer.valueOf(messageNum);
				}
			}
		}
		result.setNumMessage(platformMessageCount + imMessageCount);
		result.setSearchBoxDefaultValue(commonService.getConfig(DaikenConfigEnum.SEARCH_BOX_DEFAULT_VALUE).getRecord());
		// IM未读消息数
		int imMessageNum = 0;
		Map<String, String> hGetAll = redisService
				.hGetAll(String.format(DaikenRedisKeyEnum.IM_RECORD_USER_COMVERSATION.getKey(), dbUser.getUid()));
		for (String item : hGetAll.keySet()) {
			String messageNum = redisService.getString(
					String.format(DaikenRedisKeyEnum.IM_RECORD_MESSAGE_NUMBER.getKey(), dbUser.getUid(), item));
			if (StringUtils.isNotBlank(messageNum)) {
				imMessageNum += Integer.valueOf(messageNum);

			}
		}
		result.setImMessageNum(imMessageNum);
		result.setOtherSharImg(
				"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=533818403,51389860&fm=111&gp=0.jpg");
		return new KemeanResult<CBaseUserInfoBO>(result);
	}

	/**
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	public KemeanResult<EarningsDetailBO> earningsDetail(String wxOpenId) {
		DaikenUser dbUser = daikenUserDao.selectUniqueEntityByProperty("wxOpenId", wxOpenId);
		EarningsDetailBO bo = new EarningsDetailBO();
		bo.setHeadImg(dbUser.getHeadImg());
		bo.setNickName(dbUser.getNickName());
		// 总收入
		Double sumMoney = DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(), Arrays.asList(
				DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(), DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(),
				DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType(), DaikenFinanceTypeEnum.C_SELL_GOODS.getType(),
				DaikenFinanceTypeEnum.C_BEFOR_RED_INCOME.getType(), DaikenFinanceTypeEnum.C_AFTER_RED_INCOME.getType(),
				DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType()), false), 2);
		bo.setSumMoney(sumMoney);
		// 已提现
		Double finishMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(dbUser.getId(),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus()), 2);
		// 提现中
		Double waitMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(dbUser.getId(),
				KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus()), 2);
		bo.setFinishMoney(finishMoney);
		// 余额（未冻结的资金 + 冻结资金） = 收入 - 提现 - 提现中
		Double blancePrice = sumMoney - finishMoney - waitMoney;
		bo.setBalancePrice(DaikenUtil.formatDouble(blancePrice, 2));
		// 现token余额
		bo.setBalanceToken(dbUser.getBalanceToken());
		// 任务收入
		bo.setMissionPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(),
						DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(),
						DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType()),
				false), 2));
		// TODO 无活动收入
		bo.setActivityPrice(0.0);
		// 买货收入
		bo.setBuyGoodsPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_BEFOR_RED_INCOME.getType()), false), 2));
		// 卖货收入
		bo.setSellGoodsPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_SELL_GOODS.getType()), false), 2));
		// 帮卖收入
		bo.setHelpSellGoodsPrice(DaikenUtil.formatDouble(kemeanFinanceOrderDao.currMonthPrice(dbUser.getId(),
				Arrays.asList(DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType()), false), 2));
		return new KemeanResult<EarningsDetailBO>(bo);
	}

	/**
	 * 获取商家 或 客户 余额
	 * 
	 * @author huwei
	 * @date 2018年9月26日
	 */
	public Double getConsumerOrBusinessBlance(DaikenUser daikenUser) {
		Double blance = 0.0;
		if (daikenUser != null) {
			// 客户，商户共同平台收费（发布调研，精准推送）
			Double charge = 0.0;
			List<KemeanFinanceOrder> dbFinanceOrders = kemeanFinanceOrderDao
					.getPlatformChargeByUser(daikenUser.getId());
			if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
				for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
					charge += dbFinanceOrder.getMoney();
				}
			}
			// 小程序余额标准
			if (daikenUser.getUserType().equals(DaikenUserTypeEnum.CONSUMER.getType())) {
				// 未冻结资金（充值金额不存在冻结资金）
				Double unFreezeMoney = DaikenUtil
						.formatDouble(kemeanFinanceOrderDao.getConsumerMoney(daikenUser.getId(), false), 2);
				// 已提现
				Double finishMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(
						daikenUser.getId(), KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus()), 2);
				// 提现中
				Double waitMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(daikenUser.getId(),
						KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus()), 2);
				// 余额 = 未冻结资金 - 已提现 - 提现中 - 平台收费
				blance = unFreezeMoney - finishMoney - waitMoney - charge;
				return DaikenUtil.formatDouble(blance, 2);
			}
			// 商户端余额标准
			if (daikenUser.getUserType().equals(DaikenUserTypeEnum.BUSINESS.getType())) {
				DaikenShop dbShop = daikenShopDao.selectById(daikenUser.getShopId());
				Date cycleDate = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY,
						-dbShop.getCycle());
				// 未冻结资金（充值金额不存在冻结资金）
				Double unFreezeMoney = kemeanFinanceOrderDao.getShopMoney(daikenUser.getShopId(), daikenUser.getId(),
						cycleDate);
				// 提现中
				Double finshMoneying = kemeanFinanceClearDao.shopClearMoney(daikenUser.getId(),
						KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
				// 已提现
				Double finshMoney = kemeanFinanceClearDao.shopClearMoney(daikenUser.getId(),
						KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus());
				// 广告收费（轮播图，精准投放，商铺商品首页推荐，推荐宝贝，售前红包）
				Double advertisingCosts = 0.0;
				List<KemeanFinanceOrder> dbAdvertisingCosts = kemeanFinanceOrderDao
						.getBusinessAdvertisingCostsByShop(dbShop.getId());
				if (CollectionUtils.isNotEmpty(dbAdvertisingCosts)) {
					for (KemeanFinanceOrder dbAdvertisingCost : dbAdvertisingCosts) {
						advertisingCosts += dbAdvertisingCost.getMoney();
					}
				}
				// 可提资金 = 未冻结资金 - 已提现 - 提现中 - 平台收费 - 广告收费
				blance = unFreezeMoney - finshMoneying - finshMoney - charge - advertisingCosts;
				return DaikenUtil.formatDouble(blance, 2);
			}
		}
		return blance;
	}

}
