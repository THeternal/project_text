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
import com.kemean.service.admin.AdminOrderService;
import com.kemean.service.admin.AdminShopService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.shop.AdminShopBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderListBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;
import com.kemean.vo.bo.admin.shop.AdminShopReturnBO;
import com.kemean.vo.bo.admin.shop.AdminShopSettledBO;
import com.kemean.vo.po.admin.AdminMonitorPO;
import com.kemean.vo.po.admin.shop.AdminShopAuditStatusPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderListPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderPO;
import com.kemean.vo.po.admin.shop.AdminShopPO;
import com.kemean.vo.po.admin.shop.AdminShopPromotionPO;
import com.kemean.vo.po.admin.shop.AdminShopSettledPO;
import com.kemean.vo.po.admin.shop.AdminShopUpdatePO;

/**
 * 店铺管理
 * 
 * @Date 2018年6月21日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/shop")
public class AdminShopController extends DaikenBaseController {

	@Autowired
	private AdminShopService adminShopService;

	@Autowired
	private AdminOrderService adminOrderService;

	/**
	 * 店铺入驻列表（商铺） page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@RequestMapping(value = "settled", method = RequestMethod.GET)
	public String shopSettledPage() {
		return KemeanConstant.ADMIN_FOLDER + "shopSettled";
	}

	/**
	 * 店铺入驻列表（个人） page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@RequestMapping(value = "settled_persona", method = RequestMethod.GET)
	public String shopSettledPersonaPage() {
		return KemeanConstant.ADMIN_FOLDER + "shopPersonalSettled";
	}

	/**
	 * 店铺入驻列表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@ResponseBody
	@RequestMapping(value = "settled_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopSettledBO>> shopSettledData(@Valid AdminShopSettledPO adminShopSettledPO) {
		return adminShopService.shopSettledData(adminShopSettledPO);
	}

	/**
	 * 店铺入驻详情page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@RequestMapping(value = "settled_info_page", method = RequestMethod.GET)
	public String shopSettledInfoData(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminShopService.shopSettledInfoData(objId));
		return KemeanConstant.ADMIN_FOLDER + "shopSettledInfo";
	}

	/**
	 * 店铺个人入驻详情page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@RequestMapping(value = "settled_personal_info_page", method = RequestMethod.GET)
	public String shopSettledPersonalInfoData(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminShopService.shopSettledInfoData(objId));
		return KemeanConstant.ADMIN_FOLDER + "shopSettledPersonalInfo";
	}

	/**
	 * 店铺入驻审核
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@ResponseBody
	@RequestMapping(value = "status_audit", method = RequestMethod.POST)
	public KemeanResult<String> shopStatusAudit(@Valid AdminShopAuditStatusPO shopAuditStatusPO) {
		return adminShopService.shopStatusAudit(shopAuditStatusPO);
	}

	/**
	 * 店铺信息列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String shopPage() {
		return KemeanConstant.ADMIN_FOLDER + "shop";
	}

	/**
	 * 店铺列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	@ResponseBody
	@RequestMapping(value = "page_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopBO>> shopData(@Valid AdminShopPO adminShopPO) {
		return adminShopService.shopData(adminShopPO, null);
	}

	/**
	 * 监听商铺状态/平台推荐
	 *
	 * @author huangyujian
	 * @date 2018年4月8日
	 */
	@ResponseBody
	@RequestMapping(value = "shop_status_operate", method = RequestMethod.GET)
	public KemeanResult<String> shopStatusOperate(@Valid AdminMonitorPO adminMonitorPO) {
		return adminShopService.shopStatusOperate(adminMonitorPO);
	}

	/**
	 * 商铺详情 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@RequestMapping(value = "shop_info_page", method = RequestMethod.GET)
	public String shopInfoPage(Integer objId) {
		request.setAttribute("pageData", adminShopService.shopInfoData(objId, null));
		return KemeanConstant.ADMIN_FOLDER + "shopInfo";
	}

	/**
	 * 修改商铺信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@ResponseBody
	@RequestMapping(value = "shop_info_update", method = RequestMethod.POST)
	public KemeanResult<String> shopInfoUpdate(@Valid AdminShopUpdatePO adminShopUpdatePO) {
		return adminShopService.shopInfoUpdate(adminShopUpdatePO, null);
	}

	/**
	 * 店铺订单信息查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "order", method = RequestMethod.GET)
	public String shopOrderPage() {
		return KemeanConstant.ADMIN_FOLDER + "shopOrder";
	}

	/**
	 * 店铺订单信息查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@ResponseBody
	@RequestMapping(value = "order_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopOrderListBO>> shopOrderData(
			@Valid AdminShopOrderListPO adminShopOrderListPO) {
		return adminOrderService.shopOrderData(adminShopOrderListPO, null);
	}

	/**
	 * 订单信息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "order_info_page", method = RequestMethod.GET)
	public String shopOrderInfo(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminOrderService.shopOrderDataInfo(objId));
		return KemeanConstant.ADMIN_FOLDER + "shopOrderInfo";
	}

	/**
	 * 删除订单
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月13日
	 */
	@ResponseBody
	@RequestMapping(value = "del", method = RequestMethod.GET)
	public KemeanResult<String> orderDel(@RequestParam Integer objId) {
		return adminOrderService.orderDel(objId, getLoginAdminShop());
	}

	/**
	 * 店铺退款信息page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "return_page", method = RequestMethod.GET)
	public String shopReturnPage() {
		return KemeanConstant.ADMIN_FOLDER + "shopReturn";
	}

	/**
	 * 店铺退款信息data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@ResponseBody
	@RequestMapping(value = "return_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopReturnBO>> shopReturnData(@Valid AdminShopOrderPO adminShopReturnPO) {
		return adminOrderService.shopReturnData(adminShopReturnPO, null);
	}

	/**
	 * 退款详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "return_info_page", method = RequestMethod.GET)
	public String shopReturnInfoPage(@RequestParam String orderNo) {
		request.setAttribute("pageData", adminOrderService.shopReturnInfoData(orderNo));
		return KemeanConstant.ADMIN_FOLDER + "shopReturnInfo";
	}

	/**
	 * 店铺促销活动报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */
	@RequestMapping(value = "promotion_page", method = RequestMethod.GET)
	public String shopDiscountPage() {
		return KemeanConstant.ADMIN_FOLDER + "shopPromotion";
	}

	/**
	 * 店铺促销活动报表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */
	@ResponseBody
	@RequestMapping(value = "promotion_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopPromotionBO>> shopDiscountData(
			@Valid AdminShopPromotionPO adminShopPromotionPO) {
		return adminShopService.shopPromotionData(adminShopPromotionPO);
	}

	/**
	 * 店铺投诉信息查询page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "complaint_page", method = RequestMethod.GET)
	public String shopComplaint() {
		return KemeanConstant.ADMIN_FOLDER + "shopComplaint";
	}

}
