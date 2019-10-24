package com.kemean.controller.admin.shop;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.AdminGoodsService;
import com.kemean.service.business.BMineService;
import com.kemean.service.common.CommonService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewSkuBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsPrecisionPushBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsSaleTotalBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.po.admin.AdminMonitorPO;
import com.kemean.vo.po.admin.goods.AdminGoodsListPO;
import com.kemean.vo.po.admin.goods.AdminGoodsNewSkuUpdatePO;
import com.kemean.vo.po.admin.goods.AdminGoodsPrecisionPO;
import com.kemean.vo.po.admin.goods.AdminGoodsPrecisionPushPO;
import com.kemean.vo.po.admin.goods.AdminGoodsPrecisionUserPO;
import com.kemean.vo.po.admin.goods.AdminGoodsSkuPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderListPO;
import com.kemean.vo.po.admin.user.AdminUserListPO;
import com.kemean.vo.po.b.goods.GoodsPO;

/**
 * 商品管理
 * 
 * @Date 2018年6月25日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("shop/goods")
public class AdminShopGoodsController extends DaikenBaseController {

	@Autowired
	private AdminGoodsService adminGoodsService;

	@Autowired
	private AdminCommonService adminCommonService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private BMineService bMineService;

	/**
	 * 一手商品 列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String goodsNewPage() {
		return "shop/goodsNew";
	}

	/**
	 * 添加 一手商品 列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "new_add_page", method = RequestMethod.GET)
	public String goodsNewAddPage() {
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return "shop/goodsNewAdd";
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
		return adminGoodsService.goodsNewListData(adminGoodsListPO, getLoginAdminShop());

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
		return adminGoodsService.goodsNewStatusOperate(adminMonitorPO, null);
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
		return "shop/goodsNewInfo";
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
	 * 商品信息添加 -- 不需要审核
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@ResponseBody
	@RequestMapping(value = "new_add", method = RequestMethod.POST)
	public KemeanResult<String> goodsNewAdd(@Valid @RequestBody GoodsPO goodsPO) {
		return bMineService.addGoods(goodsPO, getLoginAdminShop(), false);
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
	 * 精准投放page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@RequestMapping(value = "precision_put_page", method = RequestMethod.GET)
	public String precisionPutPage(@RequestParam Integer goodsId) {
		request.setAttribute("goodsId", goodsId);
		request.setAttribute("provinceData", commonService.region(0));
		request.setAttribute("goodsCategoryData", adminCommonService.goodsBeseCategory(0));
		return "shop/goodsPrecision";
	}

	// /**
	// * 精准投放 条件匹配条件
	// *
	// * @author tanggengxiang
	// * @date 2018年7月12日
	// */
	// @ResponseBody
	// @RequestMapping(value = "precision_put_user_data", method =
	// RequestMethod.GET)
	// public KemeanPageAdminBO<List<AdminUserBO>> precisionPutUserData(
	// @Valid AdminGoodsConditionPO adminGoodsConditionPO) {
	// return adminGoodsService.precisionPutUserData(adminGoodsConditionPO);
	// }

	/**
	 * 商品精准投放
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "precision_operate", method = RequestMethod.POST)
	public KemeanResult<String> precisionOperate(
			@Valid @RequestBody AdminGoodsPrecisionUserPO adminGoodsPrecisionUserPO) {
		return adminGoodsService.precisionOperate(adminGoodsPrecisionUserPO, getLoginAdminShop());
	}

	/**
	 * 精准推送送达人列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "push_user_page", method = RequestMethod.GET)
	public String goodsPushUserPage() {
		return "shop/goodsPushUser";
	}

	/**
	 * 精准推送送达人列表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@ResponseBody
	@RequestMapping(value = "push_user_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserBO>> goodsPushUserDataData(
			@Valid AdminGoodsPrecisionPO adminGoodsPrecisionPO) {
		return adminGoodsService.goodsPushUserDataData(adminGoodsPrecisionPO, getLoginAdminShop());
	}

	/**
	 * 精准推送送达人活动轨迹page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */
	@RequestMapping(value = "push_user_record_page", method = RequestMethod.GET)
	public String goodsPushUserRecordPage(@RequestParam Integer userId) {
		request.setAttribute("pageData", adminCommonService.goodsPushUserRecord(userId));
		return "shop/goodsPushUserRecord";
	}

	/**
	 * 商品复制
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@RequestMapping(value = "news_copy_page", method = RequestMethod.GET)
	public String goodsNewCopyPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminGoodsService.goodsNewInfoData(objId));
		return "shop/goodsNewCopy";
	}

	/**
	 * 店铺的商品营销page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月23日
	 */
	@RequestMapping(value = "marketing_page", method = RequestMethod.GET)
	public String goodsMarketingPage() {
		return "shop/goodsMarketing";
	}

	/**
	 * 商品访问用户列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月24日
	 */
	@RequestMapping(value = "access_people_page", method = RequestMethod.GET)
	public String goodsAccessPeoplePage(@RequestParam Integer goodsId) {
		request.setAttribute("pageData", adminGoodsService.goodsNewInfoData(goodsId));
		request.setAttribute("marketingData", adminGoodsService.goodsMarketingData(goodsId, getLoginAdminShop()));
		return "shop/goodsAccessPeople";
	}

	/**
	 * 商品访问用户列表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月24日
	 */
	@ResponseBody
	@RequestMapping(value = "access_people_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserBO>> goodsAccessPeopleData(@Valid AdminUserListPO adminUserListPO) {
		return adminGoodsService.goodsAccessPeopleData(adminUserListPO);
	}

	/**
	 * 商品收入列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月28日
	 */
	@RequestMapping(value = "income_page", method = RequestMethod.GET)
	public String goodsIncomePage() {
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return "shop/goodsIncome";
	}

	/**
	 * 商品代卖列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月28日
	 */
	@RequestMapping(value = "purchasing_page", method = RequestMethod.GET)
	public String goodsPurchasingPage() {
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return "shop/goodsPurchasing";
	}

	/**
	 * 商品红包列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月28日
	 */
	@RequestMapping(value = "red_share_page", method = RequestMethod.GET)
	public String goodsRedSharePage() {
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return "shop/goodsRedShare";
	}

	/**
	 * 商品精准推送page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	@RequestMapping(value = "precision_push_page", method = RequestMethod.GET)
	public String goodsPrecisionPushPage() {
		return "shop/goodsPrecisionPush";
	}

	/**
	 * 商品精准推送data
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	@ResponseBody
	@RequestMapping(value = "precision_push_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminGoodsPrecisionPushBO>> goodsPrecisionPushData(
			@Valid AdminGoodsPrecisionPushPO adminGoodsPrecisionPushPO) {
		return adminGoodsService.goodsPrecisionPushData(adminGoodsPrecisionPushPO, getLoginAdminShop());
	}

	/**
	 * 促销商品报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	@RequestMapping(value = "promotion_page", method = RequestMethod.GET)
	public String goodsPromotionPage() {
		return "shop/goodsPromotion";
	}

	/**
	 * 商品总销售报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	@RequestMapping(value = "sale_total_page", method = RequestMethod.GET)
	public String goodsSaleTotalPage() {
		return "shop/goodsSaleTotal";
	}

	/**
	 * 商品精准推送data
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	@ResponseBody
	@RequestMapping(value = "sale_total_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminGoodsSaleTotalBO>> goodsSaleTotalData(
			@Valid AdminShopOrderListPO adminShopOrderListPO) {
		return adminGoodsService.goodsSaleTotalData(adminShopOrderListPO, getLoginAdminShop());
	}

}
