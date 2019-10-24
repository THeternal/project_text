package com.kemean.controller.admin.sale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kemean.controller.DaikenBaseController;

/**
 * 销售公共
 * 
 * @Date 2018年7月13日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("sale/common")
public class AdminSaleCommonController extends DaikenBaseController {

	/**
	 * 主页
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		request.setAttribute("saleData", getAdminLoginSale());
		return "sale/home";
	}
	
	
}
