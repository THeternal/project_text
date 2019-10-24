package com.kemean.service.admin.shop;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenShopSettled;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenAdminResultTips;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenMessageType.MessageWebsocketCodeEnum;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenSmsTypeEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserStatusEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenShopSettledDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.aid.KemeanSocketService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.ThirdService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.common.AdminAccessTokenWxBO;
import com.kemean.vo.po.admin.shop.AdminShopLoginPO;
import com.kemean.vo.po.b.shop.SubmitSettledInfoPO;

@Service
public class AdminShopOpenService {

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private DaikenShopSettledDao daikenShopSettledDao;

	@Autowired
	private KemeanSocketService kemeanSocketService;

	@Autowired
	private KemeanRedisService redisService;

	@Autowired
	private ThirdService thirdService;

	/**
	 * 登录校验
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	public KemeanResult<String> loginCheck(AdminShopLoginPO adminShopLoginPO, HttpServletRequest request) {
		DaikenUser dbUser = null;
		// 手机号码
		if (Pattern.matches("1\\d{10}", adminShopLoginPO.getLoginNo())) {
			dbUser = daikenUserDao.selectUniqueEntityByProperties(
					new String[] { "phone", "userType", KemeanConstant.DATA_DELETED },
					new Object[] { adminShopLoginPO.getLoginNo(), DaikenUserTypeEnum.BUSINESS.getType(), false });
		}
		if (dbUser == null) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.NO_REGISTERED_PHONE);
		}
		if (DaikenUserStatusEnum.DISABLED.getStatus().equals(dbUser.getUserStatus())) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.ACCOUNT_DISABLED);
		}
		String md5Password = adminShopLoginPO.getLoginPwd().toLowerCase();
		if (!md5Password.equalsIgnoreCase(dbUser.getPassword())) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.PASSWORD_ERROR);
		}
		DaikenShop dbShop = daikenShopDao.selectById(dbUser.getShopId());
		if (dbShop == null) {
			return new KemeanResult<>(false, "非商家用户");
		}
		request.getSession().setAttribute("login_shop", dbUser);
		return new KemeanResult<>(true, KemeanTips.Account.LOGIN_SUCCESS);
	}

	/**
	 * 注册手机号码校验
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月2日
	 */
	public KemeanResult<String> phoneCheck(String phone) {
		DaikenUser dbUser = daikenUserDao.selectByPhone(phone, Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType()));
		if (dbUser != null) {
			return new KemeanResult<>(false, "该手机号已注册");
		}
		return new KemeanResult<>(true, "校验成功");
	}

	/**
	 * 商铺入驻
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月13日
	 */
	public KemeanResult<String> settledData(SubmitSettledInfoPO submitSettledInfoPO) {
		KemeanUtilAid.appLog.info("【管理后台--商家入驻】" + JSONObject.toJSONString(submitSettledInfoPO));
		if (!KemeanConstant.SUPER_CODE.equals(submitSettledInfoPO.getCode())) {
			String cachaKey = String.format(DaikenRedisKeyEnum.CODE.getKey(), DaikenSmsTypeEnum.REGISTERED_B.getType(),
					submitSettledInfoPO.getPhone());
			String cacheCode = redisService.getString(cachaKey);
			if (StringUtils.isBlank(cacheCode)) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_NOT_EXIT);
			}
			if (!cacheCode.equals(submitSettledInfoPO.getCode())) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_ERROR);
			}
			redisService.del(cachaKey);
		}

		DaikenUser dbUser = daikenUserDao.selectByPhone(submitSettledInfoPO.getPhone(),
				Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType()));
		if (dbUser != null) {
			return new KemeanResult<>(false, DaikenApiResultTips.SendCode.PHONE_REGISTERED);
		}
		Date now = new Date();
		// 生成一个用户
		DaikenUser newUser = new DaikenUser();
		newUser.setFirstPhone(submitSettledInfoPO.getPhone());
		newUser.setUserType(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType());
		newUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.NORMAL.getStatus());
		newUser.setUid(commonService.getUid());
		newUser.setNickName(DaikenUtil.encryptPhone(submitSettledInfoPO.getPhone()));
		newUser.setPhone(submitSettledInfoPO.getPhone());
		newUser.setPassword(submitSettledInfoPO.getPassword());
		newUser.setToken(DaikenUtil.getUUIDString());
		newUser.setCreateTime(now);
		newUser.setUpdateTime(now);
		daikenUserDao.saveSelective(newUser);
		// 生成一个商户
		DaikenShop newShop = new DaikenShop();
		newShop.setShopUserUid(newUser.getUid());
		BeanUtils.copyProperties(submitSettledInfoPO, newShop);
		newShop.setSettledType(submitSettledInfoPO.getSettledPersonal());
		newShop.setAuditStatus(KemeanSettledEnum.REVIEW_ING.getStatus());
		newShop.setShopPhone(submitSettledInfoPO.getPrincipalPhone());
		newShop.setBusinessLicenseLocation(submitSettledInfoPO.getBusinessLicenseLocation());
		newShop.setCreateTime(now);
		newShop.setUpdateTime(now);
		daikenShopDao.saveSelective(newShop);
		// 关联
		newUser.setShopId(newShop.getId());
		newUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(newUser);
		// 生成一个入驻信息
		DaikenShopSettled newShopSettled = new DaikenShopSettled();
		BeanUtils.copyProperties(submitSettledInfoPO, newShopSettled);
		newShopSettled.setSettledPersonal(submitSettledInfoPO.getSettledPersonal());
		newShopSettled.setShopId(newShop.getId());
		newShopSettled.setAuditStatus(KemeanSettledEnum.REVIEW_ING.getStatus());
		newShopSettled.setBusinessLicenseLocation(submitSettledInfoPO.getBusinessLicenseLocation());
		newShopSettled.setCreateTime(now);
		newShopSettled.setUpdateTime(now);
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

	/**
	 * 忘记密码 校验验证码
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月16日
	 */
	public KemeanResult<String> verifyCode(String phone, String code) {

		DaikenUser dbUser = daikenUserDao.selectByPhone(phone,
				Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType(), DaikenUserTypeEnum.BUSINESS_SON.getType()));
		if (dbUser == null) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.NO_REGISTERED_PHONE);
		}

		if (!KemeanConstant.SUPER_CODE.equals(code)) {
			String cachaKey = String.format(DaikenRedisKeyEnum.CODE.getKey(), DaikenSmsTypeEnum.FORGET_B.getType(),
					phone);
			String cacheCode = redisService.getString(cachaKey);
			if (StringUtils.isBlank(cacheCode)) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_NOT_EXIT);
			}
			if (!cacheCode.equals(code)) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_ERROR);
			}
			redisService.del(cachaKey);
		}
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 忘记密码第二步，修改用户密码
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月16日
	 */
	public KemeanResult<String> forgetPasswordOperate(String phone, String password) {
		DaikenUser dbUser = daikenUserDao.selectByPhone(phone,
				Arrays.asList(DaikenUserTypeEnum.BUSINESS.getType(), DaikenUserTypeEnum.BUSINESS_SON.getType()));
		dbUser.setPassword(password.toLowerCase());
		dbUser.setUpdateTime(new Date());
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		return new KemeanResult<>(true, DaikenAdminResultTips.forgetPassword.UPDATE_PASSWORD_SUCCESS);
	}

	/**
	 * 微信第三方授权登录
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	public KemeanResult<String> thirdLoginWx(String code) {
		AdminAccessTokenWxBO bo = thirdService.getWxAccessToken(code);
		if (null == bo) {
			return new KemeanResult<String>(false, DaikenAdminResultTips.Third.WX_LOGIN_FAIL);
		}
		// 如果登录成功，则返回openid
		return new KemeanResult<String>(bo.getOpenid());
	}

}
