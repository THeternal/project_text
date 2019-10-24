package com.kemean.controller.admin.sale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.sale.AdminSaleOpenService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.po.admin.AdminLoginCheckPO;

/**
 * 商家
 * 
 * @Date 2018年7月10日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("sale/open")
public class AdminSaleOpenController extends DaikenBaseController {

	@Autowired
	private AdminSaleOpenService adminSaleOpenService;

	/**
	 * 登录页
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "sale/login";
	}

	/**
	 * 登录校验
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@ResponseBody
	@RequestMapping(value = "login_check", method = RequestMethod.POST)
	public KemeanResult<String> loginCheck(@Valid AdminLoginCheckPO adminLoginCheckPO) {
		return adminSaleOpenService.loginCheck(adminLoginCheckPO, request);
	}
	
	

}
