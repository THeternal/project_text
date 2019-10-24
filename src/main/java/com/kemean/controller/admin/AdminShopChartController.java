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
import com.kemean.service.admin.AdminShopService;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.shop.AdminShopChartBO;
import com.kemean.vo.bo.admin.shop.AdminShopFinanceWaterBO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.shop.AdminShopChartPO;
import com.kemean.vo.po.admin.shop.AdminShopFinanceOrderPO;

/**
 * 店铺报表
 * 
 * @Date 2018年6月21日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/shop/chart")
public class AdminShopChartController extends DaikenBaseController {

	@Autowired
	private AdminShopService adminShopService;

	/**
	 * 店铺统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String shopCountChartPage(@Valid AdminDatePO adminChartPO) {
		request.setAttribute("pageData", adminShopService.shopChart(adminChartPO, 15));
		return KemeanConstant.ADMIN_FOLDER + "shopChart";
	}

	/**
	 * 店铺统计搜索
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public AdminShopChartBO shopCountChartData(@Valid AdminDatePO adminChartPO) {
		return adminShopService.shopChart(adminChartPO, 0);
	}

	/**
	 * 店铺支出报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */

	@RequestMapping(value = "expend_page", method = RequestMethod.GET)
	public String shopExpendChartPage(@Valid AdminShopChartPO adminShopChartPO) {
		request.setAttribute("pageData", adminShopService.shopExpendData(adminShopChartPO, 15, ""));
		return KemeanConstant.ADMIN_FOLDER + "shopExpendChart";

	}

	/**
	 * 店铺支出报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */
	@ResponseBody
	@RequestMapping(value = "expend_data", method = RequestMethod.GET)
	public AdminShopChartBO shopExpendChartData(@Valid AdminShopChartPO adminShopChartPO) {
		return adminShopService.shopExpendData(adminShopChartPO, 0, "");

	}

	/**
	 * 店铺收益报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */

	@RequestMapping(value = "income_page", method = RequestMethod.GET)
	public String shopIncomeChartPage(@Valid AdminShopChartPO adminShopChartPO) {
		request.setAttribute("pageData", adminShopService.shopIncomeData(adminShopChartPO, 15));
		return KemeanConstant.ADMIN_FOLDER + "shopIncomeChart";

	}

	/**
	 * 店铺收益报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */
	@ResponseBody
	@RequestMapping(value = "income_data", method = RequestMethod.GET)
	public List<AdminChartBO> shopIncomeChartData(@Valid AdminShopChartPO adminShopChartPO) {
		return adminShopService.shopIncomeData(adminShopChartPO, 0);
	}

	/**
	 * 店铺流水报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月20日
	 */
	@RequestMapping(value = "finance_order_page", method = RequestMethod.GET)
	public String shopOrderListPage() {
		request.setAttribute("pageData", adminShopService.shopFinanceWaterData(null, null, 15));
		return KemeanConstant.ADMIN_FOLDER + "shopFinanceOrder";
	}

	/**
	 * 店铺流水报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月20日
	 */
	@ResponseBody
	@RequestMapping(value = "finance_order_data", method = RequestMethod.GET)
	public AdminShopFinanceWaterBO shopOrderListDate(@Valid AdminShopFinanceOrderPO adminShopFinanceOrderPO) {
		return adminShopService.shopFinanceWaterData(adminShopFinanceOrderPO, null, 0);
	}
}
