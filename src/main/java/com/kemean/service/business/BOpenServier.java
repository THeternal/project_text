package com.kemean.service.business;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenShopSettled;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenMessageType.MessageWebsocketCodeEnum;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenSmsTypeEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenShopSettledDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.ImRyService;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.aid.KemeanSocketService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.com.BBaseUserInfoBO;
import com.kemean.vo.po.com.PhoneLoginPO;
import com.kemean.vo.po.com.RegisterPO;

/**
 * 
 * 【商户端】用户业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class BOpenServier {

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private KemeanRedisService redisService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenShopSettledDao daikenShopSettledDao;

	@Autowired
	private ImRyService imRyService;

	@Autowired
	private KemeanSocketService kemeanSocketService;

	/**
	 * 手机号登陆
	 * 
	 * @author huwei
	 * @date 2018年6月21日
	 */
	public KemeanResult<BBaseUserInfoBO> phoneLogin(PhoneLoginPO phoneLoginPO) {
		BBaseUserInfoBO result = new BBaseUserInfoBO();
		boolean flag = false;
		DaikenUser dbUser = null;
		result.setCheck(DaikenApiResultTips.Login.LOGIN_CHECK_PASS);
		if (StringUtils.isNoneBlank(phoneLoginPO.getPhone()) && StringUtils.isNoneBlank(phoneLoginPO.getPassword())) {
			flag = true;
			dbUser = daikenUserDao.selectByPhone(phoneLoginPO.getPhone(),
					Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType(), DaikenUserTypeEnum.BUSINESS_SON.getType()));
			if (dbUser == null) {
				return new KemeanResult<>(false, DaikenApiResultTips.Login.NO_REGISTERED_PHONE);
			}
			if (DaikenUserEnum.DaikenUserStatusEnum.DISABLED.getStatus().equals(dbUser.getUserStatus())) {
				return new KemeanResult<>(false, DaikenApiResultTips.Login.ACCOUNT_DISABLED);
			}
			if (!dbUser.getPassword().equalsIgnoreCase(phoneLoginPO.getPassword())) {
				return new KemeanResult<>(false, DaikenApiResultTips.Login.PASSWORD_ERROR);
			}
		}
		if (!flag) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.LOGIN_TYPE_FAILURE);
		}
		redisService.del(String.format(DaikenRedisKeyEnum.USER_BUSINESS.getKey(), dbUser.getToken()));
		dbUser.setToken(DaikenUtil.getUUIDString());
		dbUser.setUpdateTime(new Date());
		if (StringUtils.isEmpty(dbUser.getTokenRy())) {
			String headImg = StringUtils.isBlank(dbUser.getHeadImg())
					? commonService.getConfig(DaikenConfigEnum.DEFAULT_IMG_USER).getRecord()
					: dbUser.getHeadImg();
			String nickName = StringUtils.isBlank(dbUser.getNickName()) ? dbUser.getUid() + "" : dbUser.getNickName();
			dbUser.setTokenRy(imRyService.userToken(dbUser.getUid() + "", nickName, headImg));
		}
		dbUser.setServiceMark(userService.getServiceUid(dbUser, true));
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		BeanUtils.copyProperties(dbUser, result);
		result.setUid(String.valueOf(dbUser.getUid()));
		result.setSex(dbUser.getSexMan() ? "男" : "女");
		DaikenShop dbShop = daikenShopDao.selectById(dbUser.getShopId());
		DaikenShopSettled dbShopSettled = daikenShopSettledDao.selectUniqueEntityByProperty("shopId", dbShop.getId());
		result.setAuditStatus(dbShopSettled.getAuditStatus());
		result.setServiceUserUid(dbUser.getServiceMark());
		return new KemeanResult<BBaseUserInfoBO>(result);
	}

	/**
	 * 忘记密码
	 * 
	 * @author huwei
	 * @date 2018年6月13日
	 */
	public KemeanResult<String> forgetPassword(RegisterPO registerPO) {
		if (!KemeanConstant.SUPER_CODE.equals(registerPO.getCode())) {
			String cachaKey = String.format(DaikenRedisKeyEnum.CODE.getKey(), DaikenSmsTypeEnum.FORGET_B.getType(),
					registerPO.getPhone());
			String cacheCode = redisService.getString(cachaKey);
			if (StringUtils.isBlank(cacheCode)) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_NOT_EXIT);
			}
			if (!cacheCode.equals(registerPO.getCode())) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_ERROR);
			}
			redisService.del(cachaKey);
		}

		DaikenUser dbUser = daikenUserDao.selectByPhone(registerPO.getPhone(),
				Arrays.asList(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType(),
						DaikenUserEnum.DaikenUserTypeEnum.BUSINESS_SON.getType()));
		if (dbUser == null) {
			return new KemeanResult<String>(false, DaikenApiResultTips.Login.NO_REGISTERED_PHONE);
		}
		dbUser.setPassword(registerPO.getPassword());
		dbUser.setUpdateTime(new Date());
		daikenUserDao.updateByPrimaryKeySelective(dbUser);

		return new KemeanResult<String>();
	}

	/**
	 * 手机号注册
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	@Transactional
	public KemeanResult<String> register(RegisterPO registerPO) {
		if (!KemeanConstant.SUPER_CODE.equals(registerPO.getCode())) {
			String cachaKey = String.format(DaikenRedisKeyEnum.CODE.getKey(), DaikenSmsTypeEnum.REGISTERED_B.getType(),
					registerPO.getPhone());
			String cacheCode = redisService.getString(cachaKey);
			if (StringUtils.isBlank(cacheCode)) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_NOT_EXIT);
			}
			if (!cacheCode.equals(registerPO.getCode())) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_ERROR);
			}
			redisService.del(cachaKey);
		}

		DaikenUser dbUser = daikenUserDao.selectByPhone(registerPO.getPhone(),
				Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType()));
		if (dbUser != null) {
			return new KemeanResult<>(false, DaikenApiResultTips.SendCode.PHONE_REGISTERED);
		}
		Date now = new Date();
		// 生成一个用户
		DaikenUser newUser = new DaikenUser();
		newUser.setFirstPhone(registerPO.getPhone());
		newUser.setUserType(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType());
		newUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.NORMAL.getStatus());
		newUser.setUid(commonService.getUid());
		newUser.setNickName(DaikenUtil.encryptPhone(registerPO.getPhone()));
		newUser.setPhone(registerPO.getPhone());
		newUser.setPassword(registerPO.getPassword());
		newUser.setToken(DaikenUtil.getUUIDString());
		newUser.setCreateTime(now);
		newUser.setUpdateTime(now);
		daikenUserDao.saveSelective(newUser);
		// 生成一个商户
		DaikenShop newShop = new DaikenShop();
		newShop.setShopUserUid(newUser.getUid());
		newShop.setCreateTime(now);
		newShop.setUpdateTime(now);
		daikenShopDao.saveSelective(newShop);
		// 关联
		newUser.setShopId(newShop.getId());
		newUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(newUser);
		// 生成一个入驻信息
		DaikenShopSettled newShopSettled = new DaikenShopSettled();
		newShopSettled.setShopId(newShop.getId());
		newShopSettled.setCreateTime(now);
		newShopSettled.setUpdateTime(now);
		newShopSettled.setSettledPersonal(true);
		daikenShopSettledDao.saveSelective(newShopSettled);

		String info = String.format("用户%s%s", newUser.getNickName(), ",入驻成功！");
		if (newShopSettled.getSettledPersonal()) {
			kemeanSocketService.notification("admin", new KemeanResult<String>(true,
					MessageWebsocketCodeEnum.SHOP_PERSONAL_SETTLED.getCode(), info, null));
		} else {
			kemeanSocketService.notification("admin",
					new KemeanResult<String>(true, MessageWebsocketCodeEnum.SHOP_SETTLED.getCode(), info, null));
		}

		return new KemeanResult<>(true, "注册成功");
	}

}
