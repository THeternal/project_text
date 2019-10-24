package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminSalesService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.sales.AdminSalesUserBO;
import com.kemean.vo.po.admin.sales.AdminSalesUserOperatePO;
import com.kemean.vo.po.admin.sales.AdminSalesUserPO;
import com.kemean.vo.po.admin.sales.AdminSalesUserPasswordPO;
import com.kemean.vo.po.admin.user.AdminUserStatusPO;

/**
 * 平台销售管理
 * 
 * @Date 2018年7月13日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("admin/sales")
public class AdminSalesController extends DaikenBaseController {

	@Autowired
	private AdminSalesService adminSalesService;

	/**
	 * 销售人员信息管理page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@RequestMapping(value = "user_page", method = RequestMethod.GET)
	public String salesUserPage() {
		return KemeanConstant.ADMIN_FOLDER + "salesUser";
	}

	/**
	 * 平台销售人员信息管理data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@ResponseBody
	@RequestMapping(value = "user_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminSalesUserBO>> salesUserData(@Valid AdminSalesUserPO adminSalesUserPO) {
		return adminSalesService.salesUserData(adminSalesUserPO, null);
	}

	/**
	 * 添加销售人员信息page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@RequestMapping(value = "user_add_page", method = RequestMethod.GET)
	public String salesUserAddPage() {
		return KemeanConstant.ADMIN_FOLDER + "salesUserAdd";
	}

	/**
	 * 添加销售人员信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@ResponseBody
	@RequestMapping(value = "user_add", method = RequestMethod.POST)
	public KemeanResult<String> salesUserAdd(@Valid AdminSalesUserOperatePO adminSalesUserOperatePO) {
		return adminSalesService.salesUserAdd(adminSalesUserOperatePO);
	}

	/**
	 * 修改销售人员信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@ResponseBody
	@RequestMapping(value = "user_update", method = RequestMethod.POST)
	public KemeanResult<String> salesUserUpdate(@Valid AdminSalesUserOperatePO adminSalesUserOperatePO) {
		return adminSalesService.salesUserUpdate(adminSalesUserOperatePO);
	}

	/**
	 * 修改销售人员密码
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@ResponseBody
	@RequestMapping(value = "user_password_edit", method = RequestMethod.GET)
	public KemeanResult<String> salesUserPasswordEdit(@Valid AdminSalesUserPasswordPO adminSalesUserPasswordPO) {
		return adminSalesService.salesUserPasswordEdit(adminSalesUserPasswordPO);
	}

	/**
	 * 监听销售人员状态
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@ResponseBody
	@RequestMapping(value = "user_status_operate", method = RequestMethod.GET)
	public KemeanResult<String> userStatusOperate(@Valid AdminUserStatusPO adminUserStatusPO) {
		return adminSalesService.userStatusOperate(adminUserStatusPO);
	}

	/**
	 * 销售用户详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	@RequestMapping(value = "user_info_page", method = RequestMethod.GET)
	public String salesUserPasswordEdit(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminSalesService.salesUserInfo(objId));
		return KemeanConstant.ADMIN_FOLDER + "salesUserInfo";
	}
	
	/**
	 * 销售投诉信息查询page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "complaint_page", method = RequestMethod.GET)
	public String saleComplaint() {
		return KemeanConstant.ADMIN_FOLDER + "saleComplaint";
	}


}
