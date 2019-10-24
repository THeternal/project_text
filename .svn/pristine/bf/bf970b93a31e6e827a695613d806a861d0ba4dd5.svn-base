package com.kemean.controller.consumer;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminPayService;
import com.kemean.service.consumer.COrderService;
import com.kemean.service.consumer.PayService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.po.CallBackALiPO;
import com.kemean.vo.po.c.oder.GetPaidOrderPO;

/**
 * 支付
 * 
 * @Date 2018年7月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/c/pay")
public class CPayController extends DaikenBaseController {

	@Autowired
	private PayService payService;

	@Autowired
	private COrderService corderService;
	
	@Autowired
	private AdminPayService adminPayService;

	/**
	 * 微信支付签名
	 * 
	 * @author huangyujian
	 * @date 2018年4月4日
	 */
	@RequestMapping(value = "pay_sign_wx", method = RequestMethod.POST)
	public KemeanResult<Map<String, String>> paySignWx(@Valid @RequestBody GetPaidOrderPO getPaidOrderPO) {
		return payService.paySignWx(getPaidOrderPO, request, getLoginConsumer());
	}

	/**
	 * 支付回调（微信）
	 * 
	 * @author huangyujian
	 * @throws IOException
	 * @date 2018年4月8日
	 */
	@RequestMapping(value = "pay_callback_wx")
	public void payCallbackWx() throws IOException {
		corderService.payCallbackWx(request);
	}
	
	
	
	/**
	 * 商家后台管理--充值
	 * 微信支付回调
	 * 
	 * @author linjinzhan
	 * @throws IOException 
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "pay_shop_recharge_callback_wx")
	public void payShopChargeCallbackWx() throws IOException {
		adminPayService.payCallbackWxpay(request);
	}
	
	/**
	 * 商家后台管理--充值
	 * 支付宝支付回调
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "pay_shop_recharge_callback_alipay", method = RequestMethod.POST)
	public void payShopChargeCallbackAlipay(CallBackALiPO callBackALiPO) {
		adminPayService.payCallbackALipay(callBackALiPO, request);
	}
	
}
