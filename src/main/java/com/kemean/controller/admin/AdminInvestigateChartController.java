package com.kemean.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminInvestigateService;
import com.kemean.vo.bo.admin.investigate.AdminInvestChartBO;
import com.kemean.vo.bo.admin.investigate.AdminInvestExpendBO;
import com.kemean.vo.bo.admin.investigate.AdminInvestUserFinanceBO;
import com.kemean.vo.po.admin.investigate.AdminInvestChartPO;
import com.kemean.vo.po.admin.investigate.AdminInvestExpendPO;
import com.kemean.vo.po.admin.investigate.AdminInvestUserFinancePO;

/**
 * 调研报表
 * 
 * @Date 2018年6月28日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("admin/invest/chart")
public class AdminInvestigateChartController extends DaikenBaseController {

	@Autowired
	private AdminInvestigateService adminInvestigateService;

	/**
	 * 调研客户/类型 统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "user_page", method = RequestMethod.GET)
	public String investUserChartPage(@Valid AdminInvestChartPO adminChartsPO) {
		request.setAttribute("pageDate", adminInvestigateService.investUserCountData(adminChartsPO, null));
		return KemeanConstant.ADMIN_FOLDER + "investUserChart";
	}

	/**
	 * 调研客户统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@ResponseBody
	@RequestMapping(value = "user_search", method = RequestMethod.GET)
	public AdminInvestChartBO investUserChartData(@Valid AdminInvestChartPO adminChartsPO) {
		return adminInvestigateService.investUserCountData(adminChartsPO, null);
	}

	/**
	 * 调研活动统计(平台)--- 点赞
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "type_page", method = RequestMethod.GET)
	public String investPlatformLikeChartPage(@Valid AdminInvestChartPO adminChartsPO) {
		request.setAttribute("pageDate", adminInvestigateService.investDayChartData(adminChartsPO, 15, null));
		return KemeanConstant.ADMIN_FOLDER + "investTypeCountChart";
	}

	/**
	 * 调研类型报表统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public AdminInvestChartBO investSearchPage(@Valid AdminInvestChartPO adminChartsPO) {
		return adminInvestigateService.investDayChartData(adminChartsPO, 15, null);
	}

	/**
	 * 调研客户流水统计page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "user_finance_page", method = RequestMethod.GET)
	public String investUserFinancePage(@Valid AdminInvestUserFinancePO adminInvestUserFinancePO) {
		request.setAttribute("pageData", adminInvestigateService.investUserFinanceData(adminInvestUserFinancePO, 15));
		return KemeanConstant.ADMIN_FOLDER + "investUserFinance";
	}

	/**
	 * 调研客户流水统计data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "user_finance_data", method = RequestMethod.GET)
	public AdminInvestUserFinanceBO investUserFinanceData(@Valid AdminInvestUserFinancePO adminInvestUserFinancePO) {
		return adminInvestigateService.investUserFinanceData(adminInvestUserFinancePO, 0);

	}

	/**
	 * 调研客户支出page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "user_expend_page", method = RequestMethod.GET)
	public String investUserExpendPage(@Valid AdminInvestExpendPO adminInvestExpendPO) {
		request.setAttribute("pageData", adminInvestigateService.investExpendData(adminInvestExpendPO, 0, ""));
		return KemeanConstant.ADMIN_FOLDER + "investUserExpendChart";
	}

	/**
	 * 调研客户流水统计data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "user_expend_data", method = RequestMethod.GET)
	public AdminInvestExpendBO investUserExpendData(@Valid AdminInvestExpendPO adminInvestExpendPO) {
		return adminInvestigateService.investExpendData(adminInvestExpendPO, 0, "");

	}

}
