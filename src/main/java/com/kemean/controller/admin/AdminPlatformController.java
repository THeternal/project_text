package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.KemeanBaseController;
import com.kemean.service.admin.AdminPlatformService;
import com.kemean.service.admin.AdminShopService;
import com.kemean.service.admin.AdminSupportService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.platform.AdminIncomeBO;
import com.kemean.vo.bo.admin.platform.AdminPlatformWorkBO;
import com.kemean.vo.bo.admin.platform.AdminPromotionBO;
import com.kemean.vo.bo.admin.platform.AdminQuestionUserBO;
import com.kemean.vo.bo.admin.platform.AdminSalePerformanceBO;
import com.kemean.vo.bo.admin.shop.AdminShopFinanceClearBO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.platform.AdminInvestQuestionUserPO;
import com.kemean.vo.po.admin.platform.AdminPromotionPO;
import com.kemean.vo.po.admin.platform.AdminSalePerformancePO;
import com.kemean.vo.po.admin.platform.AdminSupportDealPO;
import com.kemean.vo.po.admin.shop.AdminShopFinanceClearPO;

/**
 * 平台管理
 * 
 * @Date 2018年7月9日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/platform")
public class AdminPlatformController extends KemeanBaseController {

	@Autowired
	private AdminPlatformService adminPlatformService;

	@Autowired
	private AdminShopService adminShopService;

	@Autowired
	private AdminSupportService adminSupportService;

	/**
	 * 平台总销售额 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "sale_chart", method = RequestMethod.GET)
	public String platformSaleChart(@Valid AdminDatePO adminChartPO) {
		request.setAttribute("pageData", adminPlatformService.platformSaleData(adminChartPO, 15));
		return KemeanConstant.ADMIN_FOLDER + "platformSaleChart";

	}

	/**
	 * 平台总销售额 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@ResponseBody
	@RequestMapping(value = "sale_data", method = RequestMethod.GET)
	public String platformSaleData(@Valid AdminDatePO adminChartPO) {
		return adminPlatformService.platformSaleData(adminChartPO, 0);
	}

	/**
	 * 平台收入(总利润率和收入) page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "income_chart", method = RequestMethod.GET)
	public String platformIncomeChart(@Valid AdminDatePO adminChartPO) {
		request.setAttribute("pageData", adminPlatformService.platformIncomeData(adminChartPO));
		return KemeanConstant.ADMIN_FOLDER + "platformIncomeChart";

	}

	/**
	 * 平台收入(总利润率和收入)总额 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@ResponseBody
	@RequestMapping(value = "income_data", method = RequestMethod.GET)
	public AdminIncomeBO platformIncomeData(@Valid AdminDatePO adminChartPO) {
		return adminPlatformService.platformIncomeData(adminChartPO);
	}

	/**
	 * 平台四个促销活动(限时折扣)
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "discount_page", method = RequestMethod.GET)
	public String platformDiscountPage() {
		return KemeanConstant.ADMIN_FOLDER + "platformDiscount";
	}

	/**
	 * 平台四个促销活动(亏本走量)
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "loss_money_page", method = RequestMethod.GET)
	public String platformLossMoneyPage() {
		return KemeanConstant.ADMIN_FOLDER + "platformLossMoney";
	}

	/**
	 * 平台四个促销活动(9.9包邮)
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "postage_page", method = RequestMethod.GET)
	public String platformPostagePage() {
		return KemeanConstant.ADMIN_FOLDER + "platformPostage";
	}

	/**
	 * 平台四个促销活动(名牌折扣)
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "famous_page", method = RequestMethod.GET)
	public String platformFamousPage() {
		return KemeanConstant.ADMIN_FOLDER + "platformFamous";
	}

	/**
	 * 平台四个促销活动 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	@ResponseBody
	@RequestMapping(value = "promotion_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminPromotionBO>> platformPromotionData(@Valid AdminPromotionPO adminPromotionPO) {
		return adminPlatformService.platformPromotionData(adminPromotionPO);
	}

	/**
	 * 平台精准推广（问卷调查用户）列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	@RequestMapping(value = "question_user_page", method = RequestMethod.GET)
	public String platformQuestionUserPage() {
		return KemeanConstant.ADMIN_FOLDER + "platformQuestionUser";
	}

	/**
	 * 
	 * 平台精准推广（问卷调查用户）列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "question_user_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminQuestionUserBO>> platformQuestionUserData(
			@Valid AdminInvestQuestionUserPO adminInvestQuestionUserPO) {
		return adminPlatformService.platformQuestionUserData(adminInvestQuestionUserPO);
	}

	/**
	 * 平台管理员工作报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */

	@RequestMapping(value = "work_page", method = RequestMethod.GET)
	public String platformWorkPage(@Valid AdminDatePO adminChartPO) {
		request.setAttribute("pageData", adminPlatformService.platformWorkCount(adminChartPO, getLoginAdminUser(), 15));
		return KemeanConstant.ADMIN_FOLDER + "platformWork";
	}

	/**
	 * 平台管理员工作报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "work_data", method = RequestMethod.GET)
	public AdminPlatformWorkBO platformQuestionUserData(@Valid AdminDatePO adminChartPO) {
		return adminPlatformService.platformWorkCount(adminChartPO, getLoginAdminUser(), 0);
	}

	/**
	 * 销售报表---- 销售客户报表（销售对应多少客户、店铺）
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	@RequestMapping(value = "sale_performance_page", method = RequestMethod.GET)
	public String platformSalesPerformancePage() {
		return KemeanConstant.ADMIN_FOLDER + "platformSalesPerformance";
	}

	/**
	 * 销售客户报表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "sale_performance_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminSalePerformanceBO>> platformSalesPerformanceData(
			@Valid AdminSalePerformancePO adminSalePerformancePO) {
		return adminPlatformService.platformSalesPerformanceData(adminSalePerformancePO);
	}

	/**
	 * 平台财务流水page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "finance_page", method = RequestMethod.GET)
	public String platformFinancePage() {
		return KemeanConstant.ADMIN_FOLDER + "platformFinance";
	}

	/**
	 * 平台财务流水data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "finance_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopFinanceClearBO>> platformFinanceData(
			@Valid AdminShopFinanceClearPO adminShopFinanceClearPO) {
		return adminShopService.shopFinanceClearData(adminShopFinanceClearPO, null, "");

	}

	/**
	 * 客服处理客户信息统计page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */

	@RequestMapping(value = "deal_count_page", method = RequestMethod.GET)
	public String complaintDealCountPage(@Valid AdminSupportDealPO adminSupportDealPO) {
		request.setAttribute("pageData", adminSupportService.complaintDealCount(adminSupportDealPO, null, 15));
		return KemeanConstant.ADMIN_FOLDER + "platformComplaintDealCount";
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
		return adminSupportService.complaintDealCount(adminSupportDealPO, null, 0);
	}

	/**
	 * 平台运营投诉信息查询page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "complaint_page", method = RequestMethod.GET)
	public String platformComplaint() {
		return KemeanConstant.ADMIN_FOLDER + "platformComplaint";
	}

	/**
	 * 平台现金报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	@RequestMapping(value = "cash_chart", method = RequestMethod.GET)
	public String platformCashCountPage(@Valid AdminDatePO adminChartPO) {
		request.setAttribute("pageData", adminPlatformService.platformIncomeData(adminChartPO));
		return KemeanConstant.ADMIN_FOLDER + "platformCashChart";
	}

}
