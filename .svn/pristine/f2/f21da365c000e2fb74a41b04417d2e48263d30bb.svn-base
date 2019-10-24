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
import com.kemean.service.admin.AdminComplaintService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.support.AdminComplaintBO;
import com.kemean.vo.po.admin.support.AdminSupportComplaintAddPO;
import com.kemean.vo.po.admin.support.AdminSupportComplaintPO;

/**
 * 投诉信息管理
 * 
 * @Date 2018年7月23日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("admin/complaint")
public class AdminComplaintController extends DaikenBaseController {

	@Autowired
	private AdminComplaintService adminComplaintService;

	/**
	 * 投诉信息列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String complaintPage() {
		return KemeanConstant.ADMIN_FOLDER + "complaint";
	}

	/**
	 * 投诉信息列表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "page_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminComplaintBO>> complaintListData(
			@Valid AdminSupportComplaintPO adminSupportComplaintPO) {
		return adminComplaintService.complaintListData(adminSupportComplaintPO, getLoginAdminUser());
	}

	/**
	 * 添加投诉信息page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "add_page", method = RequestMethod.GET)
	public String complaintAddPage() {
		return KemeanConstant.ADMIN_FOLDER + "complaintAdd";
	}

	/**
	 * 添加投诉信息data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "add_data", method = RequestMethod.POST)
	public KemeanResult<String> complaintAddData(@Valid AdminSupportComplaintAddPO adminSupportComplaintPO) {
		return adminComplaintService.addComplaintData(adminSupportComplaintPO, getLoginAdminUser());
	}

	/**
	 * 处理投诉内容
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "deal_data", method = RequestMethod.GET)
	public KemeanResult<String> complaintDeal(@RequestParam Integer objId, @RequestParam String dealContent) {
		return adminComplaintService.complaintDeal(objId, dealContent, getLoginAdminUser());
	}

}
