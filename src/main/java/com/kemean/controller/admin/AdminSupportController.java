package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminSupportService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.po.admin.platform.AdminSupportDealPO;
import com.kemean.vo.po.admin.support.AdminSupportUserPO;

/**
 * 客服管理
 * 
 * @Date 2018年7月24日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/support")
public class AdminSupportController extends DaikenBaseController {
	@Autowired
	private AdminSupportService adminSupportService;

	/**
	 * 用户信息列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@RequestMapping(value = "user_page", method = RequestMethod.GET)
	public String userPage() {
		return KemeanConstant.ADMIN_FOLDER + "complaintUser";
	}

	/**
	 * 用户信息列表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@ResponseBody
	@RequestMapping(value = "user_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserBO>> userPageData(@Valid AdminSupportUserPO adminSupportUserPO) {
		return adminSupportService.userPageData(adminSupportUserPO);
	}

	/**
	 * 客服处理客户信息统计page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */

	@RequestMapping(value = "deal_count_page", method = RequestMethod.GET)
	public String complaintDealCountPage(@Valid AdminSupportDealPO adminSupportDealPO) {
		request.setAttribute("pageData",
				adminSupportService.complaintDealCount(adminSupportDealPO, getLoginAdminUser(), 15));
		return KemeanConstant.ADMIN_FOLDER + "complaintDealCount";
	}

	/**
	 * 客服处理客户信息统计data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */

	@ResponseBody
	@RequestMapping(value = "deal_count_data", method = RequestMethod.GET)
	public List<AdminChartBO> complaintDealCountData(@Valid AdminSupportDealPO adminSupportDealPO) {
		return adminSupportService.complaintDealCount(adminSupportDealPO, getLoginAdminUser(), 0);
	}

}
