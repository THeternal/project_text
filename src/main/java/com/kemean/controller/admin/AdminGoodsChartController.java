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
import com.kemean.service.admin.AdminGoodsService;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsChartBO;
import com.kemean.vo.po.admin.goods.AdminGoodsChartPO;

/**
 * 商品报表
 * 
 * @Date 2018年6月25日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/goods/chart")
public class AdminGoodsChartController extends KemeanBaseController {

	@Autowired
	private AdminGoodsService adminGoodsService;

	/**
	 * 单个店铺及总店铺的商品统计page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "count_page", method = RequestMethod.GET)
	public String goodsCountChartPage(@Valid AdminGoodsChartPO adminChartsPO) {
		request.setAttribute("pageDate", adminGoodsService.goodsCount(adminChartsPO, 15));
		return KemeanConstant.ADMIN_FOLDER + "goodsCountChart";
	}

	/**
	 * 单个店铺及总店铺的商品统计查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@ResponseBody
	@RequestMapping(value = "count_search", method = RequestMethod.GET)
	public AdminGoodsChartBO goodsCountChartData(@Valid AdminGoodsChartPO adminChartsPO) {
		return adminGoodsService.goodsCount(adminChartsPO, 0);
	}

	/**
	 * 商品成交报表统计page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "complete_page", method = RequestMethod.GET)
	public String goodsCompleteChartPage(@Valid AdminGoodsChartPO adminChartsPO) {
		request.setAttribute("pageDate", adminGoodsService.goodsCompleteCount(adminChartsPO, 15));
		return KemeanConstant.ADMIN_FOLDER + "goodsCompleteChart";
	}

	/**
	 * 商品成交报表统计查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	@ResponseBody
	@RequestMapping(value = "complete_search", method = RequestMethod.GET)
	public AdminGoodsChartBO goodsCompleteChartData(@Valid AdminGoodsChartPO adminChartsPO) {
		return adminGoodsService.goodsCompleteCount(adminChartsPO, 0);
	}

	/**
	 * 商品类型统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	@RequestMapping(value = "category_page", method = RequestMethod.GET)
	public String goodsCategoryChartPage(@Valid AdminGoodsChartPO adminGoodsChartPO) {
		request.setAttribute("pageData", adminGoodsService.goodsCategoryCount(adminGoodsChartPO));
		return KemeanConstant.ADMIN_FOLDER + "goodsCategoryChart";
	}

	/**
	 * 商品类型统计查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	@ResponseBody
	@RequestMapping(value = "category_data", method = RequestMethod.GET)
	public List<AdminChartBO> goodsCategoryChartData(@Valid AdminGoodsChartPO adminGoodsChartPO) {
		return adminGoodsService.goodsCategoryCount(adminGoodsChartPO);
	}
}
