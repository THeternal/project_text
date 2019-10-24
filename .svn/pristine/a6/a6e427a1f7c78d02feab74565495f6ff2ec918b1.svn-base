package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.bean.DaikenGoodsNew;
import com.kemean.constant.KemeanConstant;
import com.kemean.controller.KemeanBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.AdminGoodsService;
import com.kemean.service.business.BMineService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.goods.AdminGoodsCategoryBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewSkuBO;
import com.kemean.vo.po.admin.AdminMonitorPO;
import com.kemean.vo.po.admin.goods.AdminGoodsAuditStatusPO;
import com.kemean.vo.po.admin.goods.AdminGoodsCategoryAddPO;
import com.kemean.vo.po.admin.goods.AdminGoodsCategoryPO;
import com.kemean.vo.po.admin.goods.AdminGoodsListPO;
import com.kemean.vo.po.admin.goods.AdminGoodsNewSkuUpdatePO;
import com.kemean.vo.po.admin.goods.AdminGoodsSkuPO;
import com.kemean.vo.po.b.goods.GoodsPO;

/**
 * 商品管理
 * 
 * @Date 2018年6月25日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/goods")
public class AdminGoodsController extends KemeanBaseController {

	@Autowired
	private AdminGoodsService adminGoodsService;

	@Autowired
	private AdminCommonService adminCommonService;

	@Autowired
	private BMineService bMineService;

	/**
	 * 一手商品 列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String goodsNewPage(@RequestParam Integer objId) {
		request.setAttribute("shopData", adminGoodsService.shopName(objId));
		return KemeanConstant.ADMIN_FOLDER + "goodsNew";
	}

	/**
	 * 添加 一手商品 列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "new_add_page", method = RequestMethod.GET)
	public String goodsNewAddPage(@RequestParam Integer shopId) {
		request.setAttribute("shopId", shopId);
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return KemeanConstant.ADMIN_FOLDER + "goodsNewAdd";
	}

	/**
	 * 一手商品列表 Data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "news_page_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminGoodsNewBO>> goodsNewListData(@Valid AdminGoodsListPO adminGoodsListPO) {
		return adminGoodsService.goodsNewListData(adminGoodsListPO, null);

	}

	/**
	 * 操作商品上下架/推荐状态
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "status_operate", method = RequestMethod.GET)
	public KemeanResult<String> goodsNewStatusOperate(@Valid AdminMonitorPO adminMonitorPO) {
		return adminGoodsService.goodsNewStatusOperate(adminMonitorPO, getLoginAdminUser());
	}

	/**
	 * 商品详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "news_info_page", method = RequestMethod.GET)
	public String goodsNewInfoPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminGoodsService.goodsNewInfoData(objId));
		return KemeanConstant.ADMIN_FOLDER + "goodsNewInfo";
	}

	/**
	 * 商品SKU
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "new_sku_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminGoodsNewSkuBO>> goodsNewSkuData(@Valid AdminGoodsSkuPO adminGoodsSkuPO) {
		return adminGoodsService.goodsNewSkuData(adminGoodsSkuPO);

	}

	/**
	 * 商品信息删除
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "new_del", method = RequestMethod.GET)
	public KemeanResult<String> goodsNewDel(@RequestParam Integer objId) {
		return adminGoodsService.goodsNewDel(objId);

	}

	/**
	 * 商品信息添加/修改
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "new_add", method = RequestMethod.POST)
	public KemeanResult<String> goodsNewAdd(@Valid @RequestBody GoodsPO goodsPO) {
		return bMineService.addGoods(goodsPO, null, true);

	}

	/**
	 * 商品SKU信息更改
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	@ResponseBody
	@RequestMapping(value = "new_sku_update", method = RequestMethod.POST)
	public KemeanResult<String> goodsNewSkuUpdate(@Valid AdminGoodsNewSkuUpdatePO adminGoodsNewSkuUpdatePO) {
		return adminGoodsService.goodsNewSkuUpdate(adminGoodsNewSkuUpdatePO);
	}

	/**
	 * 商品一级分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */

	@RequestMapping(value = "category_page", method = RequestMethod.GET)
	public String goodsCategoryPage() {
		return KemeanConstant.ADMIN_FOLDER + "goodsCategory";

	}

	/**
	 * 商品二级分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */

	@RequestMapping(value = "category_two_page", method = RequestMethod.GET)
	public String goodsCategoryTwoPage(@RequestParam Integer pid) {
		request.setAttribute("pageData", adminGoodsService.getCategoryName(pid));
		return KemeanConstant.ADMIN_FOLDER + "goodsCategoryTwo";

	}

	/**
	 * 商品三级分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	@RequestMapping(value = "category_three_page", method = RequestMethod.GET)
	public String goodsCategoryThreePage(@RequestParam Integer pid) {
		request.setAttribute("pageData", adminGoodsService.getCategoryName(pid));
		return KemeanConstant.ADMIN_FOLDER + "goodsCategoryThree";
	}

	/**
	 * 添加商品三级分类page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */

	@RequestMapping(value = "category_three_add_page", method = RequestMethod.GET)
	public String goodsCategoryThreeAddPage(@RequestParam Integer pid) {
		request.setAttribute("pid", pid);
		request.setAttribute("baseTypeData", adminGoodsService.getGoodsType());
		return KemeanConstant.ADMIN_FOLDER + "goodsCategoryThreeAdd";
	}

	/**
	 * 添加商品三级分类详情page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */

	@RequestMapping(value = "category_three_info_page", method = RequestMethod.GET)
	public String goodsCategoryThreeInfoPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminGoodsService.goodsCategoryInfo(objId));
		request.setAttribute("baseTypeData", adminGoodsService.getGoodsType());
		return KemeanConstant.ADMIN_FOLDER + "goodsCategoryThreeInfo";
	}

	/**
	 * 商品分类data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	@ResponseBody
	@RequestMapping(value = "category_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminGoodsCategoryBO>> goodsCategoryData(
			@Valid AdminGoodsCategoryPO adminGoodsCategoryPO) {
		return adminGoodsService.goodsCategoryData(adminGoodsCategoryPO);
	}

	/**
	 * 添加/修改商品一二级分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */

	@ResponseBody
	@RequestMapping(value = "category_operate", method = RequestMethod.POST)
	public KemeanResult<String> goodsCategoryOperate(@Valid AdminGoodsCategoryAddPO adminGoodsCategoryAddPO) {
		return adminGoodsService.goodsCategoryOperate(adminGoodsCategoryAddPO);
	}

	/**
	 * 删除商品分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	@ResponseBody
	@RequestMapping(value = "category_del", method = RequestMethod.GET)
	public KemeanResult<String> goodsCategoryDel(@RequestParam Integer objId) {
		return adminGoodsService.goodsCategoryDel(objId);
	}

	/**
	 * 商品推荐页面
	 * 
	 * @author huwei
	 * @date 2018年7月18日
	 */
	@RequestMapping(value = "recommend_page", method = RequestMethod.GET)
	public String recommendPage() {
		request.setAttribute("shopInfos", adminGoodsService.getShopInfos());
		return KemeanConstant.ADMIN_FOLDER + "recommendPage";
	}

	/**
	 * 获取店铺的商品
	 * 
	 * @author huwei
	 * @date 2018年7月18日
	 */
	@ResponseBody
	@RequestMapping(value = "get_shop_goods_info", method = RequestMethod.GET)
	public KemeanResult<List<DaikenGoodsNew>> getShopGoodsInfo(@RequestParam Integer shopId) {
		return new KemeanResult<List<DaikenGoodsNew>>(adminGoodsService.getShopGoodsInfo(shopId));
	}

	/**
	 * 商品审核总page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "audit_page", method = RequestMethod.GET)
	public String goodsAuditPage() {
		return KemeanConstant.ADMIN_FOLDER + "goodsAudit";
	}

	/**
	 * 商品审核通过page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "audit_success_page", method = RequestMethod.GET)
	public String goodsAuditSuccessPage() {
		return KemeanConstant.ADMIN_FOLDER + "goodsAuditSuccess";
	}

	/**
	 * 商品待审核page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "audit_wait_page", method = RequestMethod.GET)
	public String goodsAuditWaitPage() {
		return KemeanConstant.ADMIN_FOLDER + "goodsAuditWait";
	}

	/**
	 * 商品审核paga
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "news_audit_info_page", method = RequestMethod.GET)
	public String goodsStatusAuditPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminGoodsService.goodsNewInfoData(objId));
		return KemeanConstant.ADMIN_FOLDER + "goodsAuditInfo";
	}

	/**
	 * 商品审核
	 * 
	 * @author huwei
	 * @date 2018年8月20日
	 */
	@ResponseBody
	@RequestMapping(value = "status_audit", method = RequestMethod.POST)
	public KemeanResult<String> goodsStatusAudit(@Valid AdminGoodsAuditStatusPO adminGoodsAuditStatusPO) {
		return adminGoodsService.goodsStatusAudit(adminGoodsAuditStatusPO);
	}

	/**
	 * 商品复制data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "news_copy_page", method = RequestMethod.GET)
	public String goodsNewCopyPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminGoodsService.goodsNewInfoData(objId));
		return KemeanConstant.ADMIN_FOLDER + "goodsNewCopy";
	}

	/**
	 * 商品推荐按钮
	 * 
	 * @author huwei
	 * @date 2018年9月6日
	 */
	@ResponseBody
	@RequestMapping(value = "recommend_change", method = RequestMethod.GET)
	public KemeanResult<String> recommendChange(@RequestParam Integer objId) {
		return adminGoodsService.recommendChange(objId);
	}

}
