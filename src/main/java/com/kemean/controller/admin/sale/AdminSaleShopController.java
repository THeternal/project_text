package com.kemean.controller.admin.sale;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminShopService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.shop.AdminShopBO;
import com.kemean.vo.bo.admin.shop.AdminShopChartBO;
import com.kemean.vo.bo.admin.shop.AdminShopFinanceClearBO;
import com.kemean.vo.po.admin.AdminMonitorPO;
import com.kemean.vo.po.admin.shop.AdminShopChartPO;
import com.kemean.vo.po.admin.shop.AdminShopFinanceClearPO;
import com.kemean.vo.po.admin.shop.AdminShopPO;
import com.kemean.vo.po.admin.shop.AdminShopUpdatePO;

/**
 * 销售负责店铺管理
 * 
 * @Date 2018年7月13日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("sale/shop")
public class AdminSaleShopController extends DaikenBaseController {

	@Autowired
	private AdminShopService adminShopService;

	/**
	 * 店铺列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String shopPage() {
		return "sale/shop";
	}

	/**
	 * 获取所负责的店铺列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */
	@ResponseBody
	@RequestMapping(value = "page_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopBO>> shopData(@Valid AdminShopPO adminShopPO) {
		return adminShopService.shopData(adminShopPO, getAdminLoginSale());
	}

	/**
	 * 监听商铺状态/平台推荐
	 *
	 * @author huangyujian
	 * @date 2018年4月8日
	 */
	@ResponseBody
	@RequestMapping(value = "status_operate", method = RequestMethod.GET)
	public KemeanResult<String> shopStatusOperate(@Valid AdminMonitorPO adminMonitorPO) {
		return adminShopService.shopStatusOperate(adminMonitorPO);
	}

	/**
	 * 商铺详情 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@RequestMapping(value = "info_page", method = RequestMethod.GET)
	public String shopInfoPage(Integer objId) {
		request.setAttribute("pageData", adminShopService.shopInfoData(objId, null));
		return "sale/shopInfo";
	}

	/**
	 * 修改商铺信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@ResponseBody
	@RequestMapping(value = "info_update", method = RequestMethod.POST)
	public KemeanResult<String> shopInfoUpdate(@Valid AdminShopUpdatePO adminShopUpdatePO) {
		return adminShopService.shopInfoUpdate(adminShopUpdatePO, null);
	}

	/**
	 * 充值、提现记录page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	@RequestMapping(value = "finance_page", method = RequestMethod.GET)
	public String financePage() {
		return "salse/finance";

	}

	/**
	 * 充值、提现记录data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	@ResponseBody
	@RequestMapping(value = "finance_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopFinanceClearBO>> shopFinanceClearData(
			@Valid AdminShopFinanceClearPO adminShopFinanceClearPO) {
		return adminShopService.shopFinanceClearData(adminShopFinanceClearPO, null,
				getAdminLoginSale().getReferralCode());
	}

	/**
	 * 店铺支出报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */

	@RequestMapping(value = "expend_page", method = RequestMethod.GET)
	public String shopExpendChartPage(@Valid AdminShopChartPO adminShopChartPO) {
		request.setAttribute("pageData",
				adminShopService.shopExpendData(adminShopChartPO, 15, getAdminLoginSale().getReferralCode()));
		return "sale/shopExpendChart";

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
		return adminShopService.shopExpendData(adminShopChartPO, 0, getAdminLoginSale().getReferralCode());

	}

}
