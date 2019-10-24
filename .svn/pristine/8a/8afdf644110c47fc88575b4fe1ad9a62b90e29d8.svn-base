package com.kemean.controller.admin.shop;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminInvestigateService;
import com.kemean.vo.bo.admin.investigate.AdminInvestChartBO;
import com.kemean.vo.po.admin.investigate.AdminInvestChartPO;

/**
 * 调研报表
 * 
 * @Date 2018年6月28日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("shop/invest/chart")
public class AdminShopInvestigateChartController extends DaikenBaseController {

	@Autowired
	private AdminInvestigateService adminInvestigateService;

	/**
	 * 结果查询统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public String investResultChartPage() {
		request.setAttribute("pageDate", adminInvestigateService.investResultChart(null, getLoginAdminShop()));
		return "shop/investResultChart";
	}

	/**
	 * 结果统计搜索
	 * 
	 * @author tanggengxiang
	 * @date 2018年5月12日
	 */
	@ResponseBody
	@RequestMapping(value = "result_search", method = RequestMethod.GET)
	public AdminInvestChartBO chartSearch(@RequestParam String date) {
		return adminInvestigateService.investResultChart(date, getLoginAdminShop());
	}

	/**
	 * 调研活动统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "invest_count_page", method = RequestMethod.GET)
	public String investDayLikeChartPage(@Valid AdminInvestChartPO adminChartsPO) {
		request.setAttribute("pageDate",
				adminInvestigateService.investDayChartData(adminChartsPO, 15, getLoginAdminShop()));
		return "shop/investCountChart";
	}

	/**
	 * 调研活动统计数据
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public AdminInvestChartBO chartSearch(@Valid AdminInvestChartPO adminChartsPO) {
		return adminInvestigateService.investDayChartData(adminChartsPO, 0, getLoginAdminShop());
	}

}
