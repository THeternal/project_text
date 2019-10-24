package com.kemean.controller.admin.shop;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.AdminOrderService;
import com.kemean.service.business.BOrderService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.ThirdLogisticsInfoDataBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderListBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderReturnBO;
import com.kemean.vo.po.admin.shop.AdminOrderExportExcelPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderListPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderReturnPO;
import com.kemean.vo.po.admin.shop.AdminShopRefundLogisticsPO;
import com.kemean.vo.po.b.order.ConfirmRefundPO;
import com.kemean.vo.po.b.order.SendGoodsPO;

/**
 * 商家订单管理
 * 
 * @Date 2018年8月13日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("shop/order")
public class AdminShopOrderController extends DaikenBaseController {

	@Autowired
	private AdminOrderService adminOrderService;

	@Autowired
	private BOrderService bOrderService;

	@Autowired
	private AdminCommonService adminCommonService;

	/**
	 * 店铺订单信息查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String shopOrderPage() {
		request.setAttribute("courierData", bOrderService.getCourierCompany());
		return "shop/order";
	}

	/**
	 * 店铺订单详细信息查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "detailed", method = RequestMethod.GET)
	public String shopOrderDetailedPage() {
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return "shop/orderDetailed";
	}

	/**
	 * 店铺退货详细信息查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "after_sale_page", method = RequestMethod.GET)
	public String shopOrderAfterSalePage() {
		request.setAttribute("category", adminCommonService.goodsBeseCategory(0));
		return "shop/orderAfterSale";
	}

	/**
	 * 待发货订单
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "accept_order_page", method = RequestMethod.GET)
	public String shopAcceptOrderPage() {
		request.setAttribute("courierData", bOrderService.getCourierCompany());
		return "shop/acceptOrder";
	}

	/**
	 * 待收货订单
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "ship_order_page", method = RequestMethod.GET)
	public String shopshipOrderPage() {
		return "shop/shipOrder";
	}

	/**
	 * 已完成订单
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "finish_order_page", method = RequestMethod.GET)
	public String shoFinishOrderPage() {
		return "shop/finishOrder";
	}

	/**
	 * 售后管理 退货待收订单列表页面
	 * 
	 * @author linjinzhan
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "after_sale_order_page", method = RequestMethod.GET)
	public String shopAfterSaleOrderPage() {
		request.setAttribute("courierData", bOrderService.getCourierCompany());
		return "shop/afterSaleOrder";
	}

	/**
	 * 售后管理 补偿订单列表页面
	 * 
	 * @author linjinzhan
	 * @date 2018年8月19日
	 */
	@RequestMapping(value = "after_sale_compensate_order_page", method = RequestMethod.GET)
	public String shopAfterSaleCompensateOrderPage() {
		request.setAttribute("compensateData", adminOrderService.orderCompensateCount(getLoginAdminShop()));
		return "shop/compensateOrder";
	}

	/**
	 * 售后管理 退货待收订单列表页面data
	 * 
	 * @author linjinzhan
	 * @date 2018年7月9日
	 */
	@ResponseBody
	@RequestMapping(value = "return_list_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopOrderReturnBO>> shopReturnListData(
			@Valid AdminShopOrderReturnPO adminShopOrderReturnPO) {
		return adminOrderService.shopReturnListData(adminShopOrderReturnPO, getLoginAdminShop());
	}

	/**
	 * 售后管理 补偿订单列表页面data
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	@ResponseBody
	@RequestMapping(value = "compensate_list_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopOrderReturnBO>> shopCompensateListData(
			@Valid AdminShopOrderReturnPO adminShopOrderReturnPO) {
		return adminOrderService.shopReturnListData(adminShopOrderReturnPO, getLoginAdminShop());
	}

	/**
	 * 订单信息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "info_page", method = RequestMethod.GET)
	public String shopOrderInfo(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminOrderService.shopOrderAndGoodsInfo(objId));
		return "shop/orderInfo";
	}

	/**
	 * 发货
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	@ResponseBody
	@RequestMapping(value = "send_goods", method = RequestMethod.POST)
	public KemeanResult<String> sendGoods(@Valid @RequestBody SendGoodsPO sendGoodsPO) {
		return bOrderService.sendGoods(sendGoodsPO);
	}

	/**
	 * 保存客户退货的物流信息
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	@ResponseBody
	@RequestMapping(value = "save_refund_logistics", method = RequestMethod.POST)
	public KemeanResult<String> saveRefundLogistics(
			@Valid @RequestBody AdminShopRefundLogisticsPO adminShopRefundLogisticsPO) {
		return adminOrderService.saveRefundLogistics(adminShopRefundLogisticsPO);
	}

	/**
	 * 店铺退款信息page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "return_page", method = RequestMethod.GET)
	public String shopReturnPage() {
		return "shop/shopReturn";
	}

	/**
	 * 确认退款/拒绝退款按钮
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	@ResponseBody
	@RequestMapping(value = "confirm_reject_refund", method = RequestMethod.POST)
	public KemeanResult<String> confirmRejectRefund(@Valid @RequestBody ConfirmRefundPO confirmRefundPO) {
		return bOrderService.confirmRejectRefund(confirmRefundPO);
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
	 * 店铺订单管理列表 -- 新
	 * 
	 * @author linjinzhan
	 * @date 2018年8月16日
	 */
	@ResponseBody
	@RequestMapping(value = "order_data_list", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopOrderListBO>> shopOrderData(
			@Valid AdminShopOrderListPO adminShopOrderListPO) {
		return adminOrderService.shopOrderData(adminShopOrderListPO, getLoginAdminShop());
	}

	/**
	 * 根据订单id查询对应的订单物流信息
	 * 
	 * @author linjinzhan
	 * @date 2018年8月19日
	 */
	@ResponseBody
	@RequestMapping(value = "order_record_logistics", method = RequestMethod.GET)
	public KemeanResult<List<ThirdLogisticsInfoDataBO>> orderRecordLogistics(@RequestParam Integer orderId) {
		return adminOrderService.orderRecordLogistics(orderId);
	}

	/**
	 * 保存补偿金额
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	@ResponseBody
	@RequestMapping(value = "save_order_compensate", method = RequestMethod.POST)
	public KemeanResult<String> saveOrderCompensate(@RequestParam String orderNo,
			@RequestParam Double compensateMoney) {
		return adminOrderService.saveOrderCompensate(orderNo, compensateMoney);
	}

	/**
	 * 发货管理订单列表导出
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	@RequestMapping(value = "export_order_excel", method = RequestMethod.GET)
	public HttpEntity<byte[]> exportOrderExcel(@Valid AdminOrderExportExcelPO adminOrderExportExcelPO)
			throws IOException {
		return adminOrderService.exportOrderExcel(adminOrderExportExcelPO, getLoginAdminShop());
	}

	/**
	 * 售后管理订单列表导出
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	@RequestMapping(value = "export_return_order_excel", method = RequestMethod.GET)
	public HttpEntity<byte[]> exportReturnOrderExcel(@Valid AdminOrderExportExcelPO adminOrderExportExcelPO)
			throws IOException {
		return adminOrderService.exportReturnOrderExcel(adminOrderExportExcelPO, getLoginAdminShop());
	}

}
