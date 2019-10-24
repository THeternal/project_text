package com.kemean.controller.admin.shop;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.shop.AdminShopOpenService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.po.admin.shop.AdminShopLoginPO;
import com.kemean.vo.po.b.shop.SubmitSettledInfoPO;

/**
 * 商家
 * 
 * @Date 2018年7月10日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("shop/open")
public class AdminShopOpenController extends DaikenBaseController {

	@Autowired
	private AdminShopOpenService adminShopOpenService;

	/**
	 * 登录页
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "shop/login";
	}

	/**
	 * 商家入驻提示页面
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月16日
	 */
	@RequestMapping(value = "prompt", method = RequestMethod.GET)
	public String promptPage() {
		return "shop/prompt";
	}
	
	/**
	 * 商家入驻
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月2日
	 */
	@RequestMapping(value = "settled", method = RequestMethod.GET)
	public String settledPage() {
		return "shop/settled";
	}

	/**
	 * 商家入驻信息
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@ResponseBody
	@RequestMapping(value = "settled_data", method = RequestMethod.POST)
	public KemeanResult<String> settledData(@Valid @RequestBody SubmitSettledInfoPO submitSettledInfoPO) {
		return adminShopOpenService.settledData(submitSettledInfoPO);
	}

	/**
	 * 登录校验
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@ResponseBody
	@RequestMapping(value = "login_check", method = RequestMethod.POST)
	public KemeanResult<String> loginCheck(@Valid AdminShopLoginPO adminShopLoginPO) {
		return adminShopOpenService.loginCheck(adminShopLoginPO, request);
	}

	/**
	 * 入驻时的手机号码校验
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@ResponseBody
	@RequestMapping(value = "phone_check", method = RequestMethod.GET)
	public KemeanResult<String> phoneCheck(@RequestParam String phone) {
		return adminShopOpenService.phoneCheck(phone);
	}

	/**
	 * 忘记密码第一步
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月2日
	 */
	@RequestMapping(value = "forget_password", method = RequestMethod.GET)
	public String forgetPasswordPage() {
		return "shop/forgetPassword";
	}

	/**
	 * 忘记密码校验验证码
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月16日
	 */
	@ResponseBody
	@RequestMapping(value = "verify_code", method = RequestMethod.GET)
	public KemeanResult<String> verifyCode(@RequestParam String phone, @RequestParam String sendCode) {
		return adminShopOpenService.verifyCode(phone, sendCode);
	}

	/**
	 * 忘记密码第二步
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月2日
	 */
	@RequestMapping(value = "forget_password_next", method = RequestMethod.GET)
	public String forgetPasswordNextPage(@RequestParam String phone) {
		request.setAttribute("phone", phone);
		return "shop/forgetPasswordNext";
	}

	/**
	 * 忘记密码,修改用户密码
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月16日
	 */
	@ResponseBody
	@RequestMapping(value = "forget_password_operate", method = RequestMethod.GET)
	public KemeanResult<String> forgetPasswordOperate(@RequestParam String phone, @RequestParam String password) {
		return adminShopOpenService.forgetPasswordOperate(phone, password);
	}
	
	/**
	 * 第三方登录微信
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	@ResponseBody
	@RequestMapping(value = "third_login_wx", method = RequestMethod.GET)
	// TODO 得部署上去才能测试，现在测不了，先写接口
	public KemeanResult<String> thirdLoginWx(@RequestParam String code) {
		return adminShopOpenService.thirdLoginWx(code);
	}
	
	/**
	 * 微信绑定登录成功回调页面
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	@RequestMapping(value = "wx_login_success", method = RequestMethod.GET)
	public String wxLoginSuccess() {
		return "shop/wxLoginSuccess";
	}
}
