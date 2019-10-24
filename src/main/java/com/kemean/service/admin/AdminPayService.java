package com.kemean.service.admin;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.bean.KemeanRichText;
import com.kemean.constant.DaikenAdminResultTips;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenPayMethodEnum;
import com.kemean.constant.DaikenPayStatus;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenRichTextTypeEnum;
import com.kemean.constant.DaikenSmsTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.constant.PayWxTradeTypeEnum;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.exception.KemeanException;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.PayAliService;
import com.kemean.service.PayWxService;
import com.kemean.service.QrCodeService;
import com.kemean.service.common.ThirdService;
import com.kemean.service.common.UserService;
import com.kemean.service.util.WxPayUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.util.KemeanUtilWeb;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.shop.AdminShopBalanceInfoBO;
import com.kemean.vo.po.CallBackALiPO;
import com.kemean.vo.po.PaySignALiWebPO;
import com.kemean.vo.po.PaySignWxPO;
import com.kemean.vo.po.PayTransferALiPO;
import com.kemean.vo.po.admin.shop.AdminWithdrawDepositPO;
import com.kemean.vo.po.com.SendCodePO;
import com.kemean.vo.po.com.TransferQueryALiPO;

@Service
@PropertySource(value = "classpath:daiken-pay.properties", encoding = "UTF-8")
public class AdminPayService {

	private static final String PAY_TITLE = "代研商家充值";
	private static final String SHOW_NAME = "代研商家管理平台";
	private static final String REMARK = "余额提现";
	// private static final String DESC = "代研商家余额提现";

	/** 应用ID **/
	@Value("${wx.open.appid}")
	private String APP_ID;

	/** 商户号 **/
	@Value("${wx.business.mchid}")
	private String MCH_ID;

	/** 回调地址 **/
	@Value("${wx.shop.notify.url}")
	private String NOTIFY_URL;

	/** 商户平台设置的密钥key **/
	@Value("${wx.business.key}")
	private String KEY;

	@Value("${alipay.appid}")
	private String alipayAppid;

	@Value("${alipay.app.private.key}")
	private String alipayAppPrivateKey;

	@Value("${alipay.public.key}")
	private String alipayPublicKey;

	@Value("${alipay.notify.url}")
	private String alipayNotifyUrl;

	@Value("${alipay.return.url}")
	private String alipayReturnUrl;

	@Autowired
	private PayWxService payWxService;

	@Autowired
	private PayAliService payAliService;

	@Autowired
	private QrCodeService qrCodeService;

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private AdminProtocolService adminProtocolService;

	@Autowired
	private ThirdService thirdService;

	@Autowired
	private UserService userService;

	@Autowired
	private KemeanRedisService redisService;

	/**
	 * 微信扫码支付(后台管理PC)
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	public void paySignWx(HttpServletRequest request, Double payPrice, HttpServletResponse response,
			DaikenUser loginUser) {
		Date now = new Date();
		// 生成订单编号
		String orderNo = KemeanUtilAid.getOrderNo(now);

		// 生成商家订单
		// 需要保存的订单流水记录
		KemeanFinanceClear newFinanceClear = new KemeanFinanceClear();
		newFinanceClear.setFinanceNo(orderNo);
		newFinanceClear.setFinanceType(DaikenFinanceTypeEnum.B_RECHARGE_MONEY.getType());
		newFinanceClear.setSubmitAimsId(loginUser.getId());
		newFinanceClear.setSubmitAimsName(loginUser.getNickName());
		newFinanceClear.setSubmitMoney(KemeanUtilAid.formatDouble(payPrice, 2));
		// rate 佣金率
		// balance 用户余额，先默认是0，待用户支付成功之后再从用户表同步过来
		newFinanceClear.setBalance(KemeanUtilAid.formatDouble(0.0, 2));
		// deal_money 成交金额，支付成功之后再改
		newFinanceClear.setDealMoney(KemeanUtilAid.formatDouble(payPrice, 2));
		newFinanceClear.setPayMethod(DaikenPayMethodEnum.WX_PAY.getCode());
		// pay_account 交易账号
		// third_trade_no 第三方交易号
		// third_trade_status 第三方交易状态
		newFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
		newFinanceClear.setFinanceData(now);
		newFinanceClear.setFinanceMonth(KemeanUtilAid.formatDate(now, KemeanDateFormatEnum.DATE));
		newFinanceClear.setCreateTime(now);
		newFinanceClear.setUpdateTime(now);
		kemeanFinanceClearDao.saveSelective(newFinanceClear);

		// 封装微信支付请求参数
		PaySignWxPO paySignWxPO = new PaySignWxPO();
		paySignWxPO.setAppId(APP_ID);
		paySignWxPO.setMchId(MCH_ID);
		paySignWxPO.setKey(KEY);
		paySignWxPO.setBody(PAY_TITLE);
		paySignWxPO.setTradeType(PayWxTradeTypeEnum.NATIVE);
		paySignWxPO.setNotifyUrl(KemeanUtilWeb.getServiceVisitPath(request) + NOTIFY_URL);
		paySignWxPO.setPayPrice(payPrice);
		paySignWxPO.setOrderNo(orderNo);
		// 请求微信支付接口，并返回结果数据
		Map<String, String> result = payWxService.unifiedorder(paySignWxPO, false);
		qrCodeService.qrCode2Response(result.get("codeUrl"), 350, response);
	}

	/**
	 * 支付宝扫码支付(后台管理PC)
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	@Transactional
	public void paySignAli(HttpServletRequest request, HttpServletResponse response, Double payPrice,
			DaikenUser loginUser) {
		Date now = new Date();
		// 生成订单编号
		String orderNo = KemeanUtilAid.getOrderNo(now);

		// 生成商家订单
		// 需要保存的订单流水记录
		KemeanFinanceClear newFinanceClear = new KemeanFinanceClear();
		newFinanceClear.setFinanceNo(orderNo);
		newFinanceClear.setFinanceType(DaikenFinanceTypeEnum.B_RECHARGE_MONEY.getType());
		newFinanceClear.setSubmitAimsId(loginUser.getId());
		newFinanceClear.setSubmitAimsName(loginUser.getNickName());
		newFinanceClear.setSubmitMoney(KemeanUtilAid.formatDouble(payPrice, 2));
		// rate 佣金率
		// balance 用户余额，先默认是0，待用户支付成功之后再从用户表同步过来
		newFinanceClear.setBalance(KemeanUtilAid.formatDouble(0.0, 2));
		// deal_money 成交金额，支付成功之后再改
		newFinanceClear.setDealMoney(KemeanUtilAid.formatDouble(payPrice, 2));
		newFinanceClear.setPayMethod(DaikenPayMethodEnum.ALI_PAY.getCode());
		// pay_account 交易账号
		// third_trade_no 第三方交易号
		// third_trade_status 第三方交易状态
		newFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
		newFinanceClear.setFinanceData(now);
		newFinanceClear.setFinanceMonth(KemeanUtilAid.formatDate(now, KemeanDateFormatEnum.DATE));
		newFinanceClear.setCreateTime(now);
		newFinanceClear.setUpdateTime(now);
		kemeanFinanceClearDao.saveSelective(newFinanceClear);

		// 封装微信支付请求参数
		PaySignALiWebPO paySignALiWebPO = new PaySignALiWebPO();
		paySignALiWebPO.setAppId(alipayAppid);
		paySignALiWebPO.setAppPrivateKey(alipayAppPrivateKey);
		paySignALiWebPO.setAlipayPublicKey(alipayPublicKey);
		paySignALiWebPO.setReturnUrl(KemeanUtilWeb.getServiceVisitPath(request) + alipayReturnUrl);
		paySignALiWebPO.setNotifyUrl(KemeanUtilWeb.getServiceVisitPath(request) + alipayNotifyUrl);
		paySignALiWebPO.setOrderNo(orderNo);
		paySignALiWebPO.setPayPrice(payPrice);
		paySignALiWebPO.setSubject(PAY_TITLE);
		paySignALiWebPO.setBody(PAY_TITLE);
		paySignALiWebPO.setRequest(request);
		paySignALiWebPO.setResponse(response);
		// 输出支付表单页面
		payAliService.pay_web(paySignALiWebPO);
	}

	/**
	 * 支付宝支付回调
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	public void payCallbackALipay(CallBackALiPO callBackALiPO, HttpServletRequest request) {
		KemeanUtilAid.appLog.info("支付宝支付回调，callBackALiPO参数：{}，request参数：{}", JSONObject.toJSONString(callBackALiPO),
				JSONObject.toJSONString(request.getParameterMap()));
		Boolean rsaCheck = payAliService.rsaCheck(request, alipayPublicKey);
		if (!rsaCheck) {
			throw new KemeanException("支付宝支付回调验签失败");
		}
		// 更新数据库中相关用户金额信息
		payCallbackOperate(DaikenPayMethodEnum.ALI_PAY.getCode(), callBackALiPO.getOut_trade_no(),
				callBackALiPO.getTrade_no(), callBackALiPO.getNotify_time(), callBackALiPO.getTrade_status());
	}

	/**
	 * 微信支付回调
	 * 
	 * @author linjinzhan
	 * @throws IOException
	 * @date 2018年8月21日
	 */
	public void payCallbackWxpay(HttpServletRequest request) throws IOException {
		Map<String, String> wxResultMap = WxPayUtil.inputStreamToMap(request.getInputStream());
		KemeanUtilAid.appLog.info("微信支付回调，参数：{}", JSONObject.toJSONString(wxResultMap));
		// 新数据库中相关用户金额信息
		payCallbackOperate(DaikenPayMethodEnum.WX_PAY.getCode(), wxResultMap.get("out_trade_no"),
				wxResultMap.get("transaction_id"), wxResultMap.get("time_end"), wxResultMap.get("result_code"));
	}

	/**
	 * 商家充值支付回调统一处理
	 * 
	 * @param orderNo
	 *            平台订单号
	 * @param thirdOrderNo
	 *            第三方订单号
	 * @param thirdNotifyTime
	 *            第三方通知时间
	 * @param thirdTradeStatus
	 *            第三方处理状态
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	@Transactional
	public synchronized void payCallbackOperate(Integer payType, String orderNo, String thirdOrderNo,
			String thirdNotifyTime, String thirdTradeStatus) {
		// 获取数据库中下单前生成的流水记录
		KemeanFinanceClear dbFinanceClear = kemeanFinanceClearDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "financeNo" }, new Object[] { false, orderNo });
		// 只针对流水状态是新订单进行操作，防止微信或支付宝多次调用回调通知导致数据不同步
		if (!KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus().equals(dbFinanceClear.getFinanceStatus())) {
			return;
		}
		Date now = new Date();
		dbFinanceClear.setUpdateTime(now);
		dbFinanceClear.setThirdTradeNo(thirdOrderNo);
		dbFinanceClear.setThirdTradeStatus(thirdTradeStatus);
		// 默认交易失败
		dbFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.FAILURE.getStatus());
		// 如果是微信支付或支付宝支付
		if (DaikenPayMethodEnum.WX_PAY.getCode().equals(payType)) {
			// 判断用户是否支付成功
			if (!DaikenPayStatus.WxPay.SUCCESS.equals(thirdTradeStatus)) {
				kemeanFinanceClearDao.updateByPrimaryKeySelective(dbFinanceClear);
				return;
			}
		}
		// 如果是支付宝支付
		if (DaikenPayMethodEnum.ALI_PAY.getCode().equals(payType)) {
			// 判断用户是否支付成功
			if (!DaikenPayStatus.AliPay.TRADE_SUCCESS.equals(thirdTradeStatus)) {
				kemeanFinanceClearDao.updateByPrimaryKeySelective(dbFinanceClear);
				return;
			}
		}
		// 支付成功则更新商家流水记录
		// 同时更新商家对应的用户余额
		dbFinanceClear.setDealMoney(dbFinanceClear.getSubmitMoney());
		dbFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus());
		// 获取商家信息
		DaikenUser dbUser = daikenUserDao.selectById(dbFinanceClear.getSubmitAimsId());
		// 计算累加后的余额=原来的余额+充值的金额
		dbUser.setBalancePrice(
				KemeanUtilAid.formatDouble(dbUser.getBalancePrice() + dbFinanceClear.getSubmitMoney(), 2));
		dbUser.setUpdateTime(now);
		// 同步用户表中的账户余额到流水记录中
		dbFinanceClear.setBalance(dbUser.getBalancePrice());
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		kemeanFinanceClearDao.updateByPrimaryKeySelective(dbFinanceClear);
		return;
	}

	/**
	 * 获取商家充值支付条款
	 * 
	 * @author linjinzhan
	 * @date 2018年8月22日
	 */
	public KemeanResult<KemeanRichText> payItem() {
		return new KemeanResult<KemeanRichText>(
				adminProtocolService.protocolInfoData(DaikenRichTextTypeEnum.PAY_ITEM.getType()));
	}

	/**
	 * 商家提现 -- 支付宝、微信
	 * 
	 * @author linjinzhan
	 * @date 2018年8月23日
	 */
	// TODO 支付宝的待完善，微信的提现还没测试，另外需要加上短信验证码
	@Transactional
	public synchronized KemeanResult<String> withdrawDeposit(AdminWithdrawDepositPO adminWithdrawDepositPO,
			DaikenUser loginUser) {
		Date now = new Date();
		// 生成流水记录订单号
		String orderNo = KemeanUtilAid.getOrderNo(now);
		// 获取数据库中的账户余额
		Double balance = daikenUserDao.selectById(loginUser.getId()).getBalancePrice();
		if (adminWithdrawDepositPO.getMoney() > balance) {
			return new KemeanResult<String>(false, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_MONEY_ERROR);
		}

		// 从redis中取出验证码
		String cacheKey = String.format(DaikenRedisKeyEnum.CODE.getKey(), DaikenSmsTypeEnum.B_WITHDRAW.getType(),
				loginUser.getPhone());
		String cacheCode = redisService.getString(cacheKey);
		if (!adminWithdrawDepositPO.getCode().equals(cacheCode)) {
			return new KemeanResult<String>(false, DaikenAdminResultTips.Sms.SEND_CODE_ERROR);
		}

		// 生成商家订单
		// 需要保存的订单流水记录
		KemeanFinanceClear newFinanceClear = new KemeanFinanceClear();
		newFinanceClear.setFinanceNo(orderNo);
		newFinanceClear.setFinanceType(DaikenFinanceTypeEnum.B_CASH_SHOP.getType());
		newFinanceClear.setSubmitAimsId(loginUser.getId());
		newFinanceClear.setSubmitAimsName(loginUser.getNickName());
		newFinanceClear.setSubmitMoney(KemeanUtilAid.formatDouble(adminWithdrawDepositPO.getMoney(), 2));
		// rate 佣金率
		// balance 用户余额，先默认是0，待用户提现成功之后再从用户表同步过来
		newFinanceClear.setBalance(KemeanUtilAid.formatDouble(0.0, 2));
		// deal_money 成交金额
		newFinanceClear.setDealMoney(KemeanUtilAid.formatDouble(adminWithdrawDepositPO.getMoney(), 2));
		// pay_account 交易账号
		// third_trade_no 第三方交易号
		// third_trade_status 第三方交易状态
		newFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
		newFinanceClear.setFinanceData(now);
		newFinanceClear.setFinanceMonth(KemeanUtilAid.formatDate(now, KemeanDateFormatEnum.DATE));
		newFinanceClear.setCreateTime(now);
		newFinanceClear.setUpdateTime(now);

		// 如果是支付宝提现
		if (DaikenPayMethodEnum.ALI_PAY.getCode().equals(adminWithdrawDepositPO.getType())) {
			// 如果是支付宝，则支付宝账号不能为空，实名名称可以为空
			if (StringUtils.isBlank(adminWithdrawDepositPO.getAccount())) {
				return new KemeanResult<String>(false, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_ACCOUNT_NULL);
			}
			newFinanceClear.setPayMethod(DaikenPayMethodEnum.ALI_PAY.getCode());
			newFinanceClear.setPayAccount(adminWithdrawDepositPO.getAccount());
			kemeanFinanceClearDao.saveSelective(newFinanceClear);
			// 请求支付宝进行提现操作
			AlipayFundTransToaccountTransferResponse result = payAliService.transfer(new PayTransferALiPO(alipayAppid,
					alipayAppPrivateKey, alipayPublicKey, orderNo, adminWithdrawDepositPO.getAccount(),
					adminWithdrawDepositPO.getMoney(), SHOW_NAME, REMARK, adminWithdrawDepositPO.getName()));
			// 提现结果
			KemeanUtilAid.appLog.info("【支付宝单笔转账】" + JSONObject.toJSONString(result));
			if (null == result) {
				return new KemeanResult<String>(false, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_ERROR);
			}
			// 如果转账成功
			if (result.isSuccess()) {
				// 查询转账结果
				if (!transferQueryALi(result.getOutBizNo(), result.getOrderId())) {
					return new KemeanResult<String>(false, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_ERROR);
				}
				DaikenUser dbUser = daikenUserDao.selectById(loginUser.getId());
				// 计算余额
				Double newBalance = dbUser.getBalancePrice() - adminWithdrawDepositPO.getMoney();
				dbUser.setBalancePrice(KemeanUtilAid.formatDouble(newBalance, 2));
				// 更新用户余额
				daikenUserDao.updateByPrimaryKeySelective(dbUser);
				// 查询转账结果
				KemeanFinanceClear dbFinanceClear = kemeanFinanceClearDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "financeNo" }, new Object[] { false, orderNo });
				dbFinanceClear.setBalance(KemeanUtilAid.formatDouble(newBalance, 2));
				dbFinanceClear.setThirdTradeNo(result.getOrderId());
				dbFinanceClear.setThirdTradeStatus(result.getMsg());
				dbFinanceClear.setUpdateTime(new Date());
				dbFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus());
				// 更新流水记录
				kemeanFinanceClearDao.updateByPrimaryKeySelective(dbFinanceClear);
				// 提现成功短信通知
				withdrawDepositSendCode(DaikenSmsTypeEnum.WITHDRAW_SUCCESS.getType(), loginUser,
						KemeanUtilAid.formatDouble(adminWithdrawDepositPO.getMoney(), 2));
				return new KemeanResult<String>(true, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_SUCCESS);
			}
		}

		// 如果是微信提现
		if (DaikenPayMethodEnum.WX_PAY.getCode().equals(adminWithdrawDepositPO.getType())) {
			String openid = adminWithdrawDepositPO.getOpenid();
			// 如果是微信，则用户的openid不能为空
			if (StringUtils.isBlank(openid)) {
				return new KemeanResult<String>(false, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_OPENID_NULL);
			}
			// TODO 企业打款待测试，还没有证书，客户还没配scope权限，域名
			/*
			 * WxTransfersBO result = payWxService.transfers(new PayTransfersWxPO(APP_ID,
			 * MCH_ID, orderNo, openid, DESC, adminWithdrawDepositPO.getMoney().intValue()),
			 * "", KEY); // 判断是否打款失败 if (null == result || !result.getSuccessReturn() ||
			 * !result.getSuccessResult()) { return new KemeanResult<String>(false,
			 * DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_ERROR); } // 查询打款状态 // 更新数据库用户余额
			 * DaikenUser dbUser = daikenUserDao.selectById(loginUser.getId()); // 计算余额
			 * Double newBalance = dbUser.getBalancePrice() -
			 * adminWithdrawDepositPO.getMoney();
			 * dbUser.setBalancePrice(KemeanUtilAid.formatDouble(newBalance, 2)); // 更新用户余额
			 * daikenUserDao.updateByPrimaryKeySelective(dbUser); // 查询转账结果
			 * KemeanFinanceClear dbFinanceClear =
			 * kemeanFinanceClearDao.selectUniqueEntityByProperties( new String[] {
			 * KemeanConstant.DATA_DELETED, "financeNo" }, new Object[] { false, orderNo });
			 * dbFinanceClear.setBalance(KemeanUtilAid.formatDouble(newBalance, 2));
			 * dbFinanceClear.setThirdTradeNo(result.getPaymentNo());
			 * dbFinanceClear.setThirdTradeStatus(result.getReturnCode());
			 * dbFinanceClear.setUpdateTime(new Date());
			 * dbFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.
			 * getStatus()); // 更新流水记录
			 * kemeanFinanceClearDao.updateByPrimaryKeySelective(dbFinanceClear); //
			 * 提现成功短信通知
			 * withdrawDepositSendCode(DaikenSmsTypeEnum.WITHDRAW_SUCCESS.getType(),
			 * loginUser, KemeanUtilAid.formatDouble(adminWithdrawDepositPO.getMoney(), 2));
			 */
			// 更新流水记录
			return new KemeanResult<String>(true, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_SUCCESS);
		}

		// 默认提现失败
		return new KemeanResult<String>(false, DaikenAdminResultTips.Shop.WITHDRAW_DEPOSIT_ERROR);
	}

	/**
	 * 查询支付宝单笔转账结果
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	private boolean transferQueryALi(String orderNo, String orderId) {
		// 查询转账结果
		AlipayFundTransOrderQueryResponse result = thirdService.transferResultQueryALi(
				new TransferQueryALiPO(alipayAppid, alipayAppPrivateKey, alipayPublicKey, orderNo, orderId));
		if (null != result && result.isSuccess()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取商户提现页面相关数据
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	public AdminShopBalanceInfoBO shopBalanceInfo(DaikenUser loginUser) {
		// 获取商户可提现余额
		Double balance = daikenUserDao.selectById(loginUser.getId()).getBalancePrice();
		return new AdminShopBalanceInfoBO(balance, APP_ID, UUID.randomUUID().toString(), loginUser.getPhone());
	}

	/**
	 * 商家提现，发送短信验证码
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	public KemeanResult<String> withdrawDepositSendCode(Integer type, DaikenUser loginUser, Double money) {
		SendCodePO sendCodePO = new SendCodePO();
		sendCodePO.setPhone(loginUser.getPhone());
		sendCodePO.setType(type);
		sendCodePO.setMoney(money);
		return userService.sendCode(sendCodePO);
	}

}
