package com.kemean.service.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenOrderAfterSale;
import com.kemean.bean.DaikenOrderGoods;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.LogisticsCompanyEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenOrderAfterSaleDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.b.order.CourierCompanyBO;
import com.kemean.vo.bo.b.order.OrderListBO;
import com.kemean.vo.bo.b.order.OrderListGoodsBO;
import com.kemean.vo.bo.b.order.RefundCauseBO;
import com.kemean.vo.bo.b.order.RefundCauseGoodsBO;
import com.kemean.vo.mysql.GoodsRecordDB;
import com.kemean.vo.mysql.LogisticsInfoDB;
import com.kemean.vo.mysql.UserAddressDB;
import com.kemean.vo.po.b.order.ConfirmRefundPO;
import com.kemean.vo.po.b.order.OrderListPO;
import com.kemean.vo.po.b.order.SendGoodsPO;

/**
 * 【商户端】订单业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class BOrderService {

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenOrderAfterSaleDao daikenOrderAfterSaleDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private UserService userService;

	/**
	 * 订单列表
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanPageApiBO<List<OrderListBO>> orderList(OrderListPO orderListPO, DaikenUser loginBusiness) {
		Integer orderStatus = orderListPO.getOrderStatus() == null
				? KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus()
				: orderListPO.getOrderStatus();
		List<OrderListBO> result = new ArrayList<OrderListBO>();
		String keyWord = "";
		if (orderListPO.getKeyword() != null && StringUtils.isNotBlank(orderListPO.getKeyword())) {
			keyWord = orderListPO.getKeyword();
		}
		List<DaikenOrder> dbOrders = daikenOrderDao.orderListBusiness(keyWord, "", loginBusiness.getShopId(), null,
				null, "", orderStatus, "", "", orderListPO.getPageNo(), orderListPO.getPageSize());
		if (CollectionUtils.isEmpty(dbOrders)) {
			return new KemeanPageApiBO<List<OrderListBO>>(0l, 1, result);
		}
		for (DaikenOrder daikenOrder : dbOrders) {
			OrderListBO bo = new OrderListBO();
			BeanUtils.copyProperties(daikenOrder, bo);
			bo.setUserName(daikenOrder.getUserName());
			bo.setStatusShopStr(getBusinessStatusStr(daikenOrder.getStatusShop()));
			UserAddressDB userAddressDB = JSONObject.parseObject(daikenOrder.getRecordReceiving(), UserAddressDB.class);
			BeanUtils.copyProperties(userAddressDB, bo);
			bo.setReceiveAddress(userAddressDB.getProvinceName() + userAddressDB.getCityName()
					+ userAddressDB.getAreaName() + userAddressDB.getAddress());
			List<OrderListGoodsBO> goods = new ArrayList<OrderListGoodsBO>();
			List<DaikenOrderGoods> dbOrderGoodss = daikenOrderGoodsDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
					new Object[] { false, daikenOrder.getOrderNo() });
			for (DaikenOrderGoods daikenOrderGoods : dbOrderGoodss) {
				OrderListGoodsBO goodsBO = new OrderListGoodsBO();
				GoodsRecordDB goodsRecordDB = JSONObject.parseObject(daikenOrderGoods.getGoodsRecord(),
						GoodsRecordDB.class);
				if (goodsRecordDB.getPriceStore() == null) {
					goodsBO.setPriceStore(0.0);
				}
				BeanUtils.copyProperties(goodsRecordDB, goodsBO);
				BeanUtils.copyProperties(daikenOrderGoods, goodsBO);
				goodsBO.setRecordType(DaikenUtil.parseJsonArray(goodsRecordDB.getRecordType()));
				goods.add(goodsBO);
			}
			bo.setGoods(goods);
			// 快递信息
			bo.setCompanyNo("");
			bo.setExpressWaybillNo("");
			if (StringUtils.isNotBlank(daikenOrder.getRecordLogistics())) {
				LogisticsInfoDB logisticsInfoDB = JSONObject.parseObject(daikenOrder.getRecordLogistics(),
						LogisticsInfoDB.class);
				bo.setExpressWaybillNo(logisticsInfoDB.getExpressWaybillNo());
				bo.setCompanyNo(logisticsInfoDB.getCompanyNo());
			}

			result.add(bo);
		}
		PageInfo<DaikenOrder> pageInfo = new PageInfo<DaikenOrder>(dbOrders);
		return new KemeanPageApiBO<List<OrderListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 订单删除
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> delOrder(String orderNo, DaikenUser loginBusiness) {
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		dbOrder.setDeletedShop(true);
		dbOrder.setUpdateTime(new Date());
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		return new KemeanResult<String>();
	}

	/**
	 * 查看退款原因
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	public RefundCauseBO refundCause(String orderNo) {
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		RefundCauseBO bo = new RefundCauseBO();
		BeanUtils.copyProperties(dbOrder, bo);
		bo.setIsSevenDay(false);
		// 根据收货时间
		if (dbOrder.getReceiveGoodsTime() != null) {
			Integer dateDifference = DaikenUtil.dateDifference(dbOrder.getReceiveGoodsTime(), new Date());
			if (dateDifference > 7) {
				bo.setIsSevenDay(true);
			}
		}

		List<DaikenOrderAfterSale> dbOrderAfterSales = daikenOrderAfterSaleDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		if (CollectionUtils.isNotEmpty(dbOrderAfterSales)) {
			List<RefundCauseGoodsBO> refundCauseGoodsBO = new ArrayList<RefundCauseGoodsBO>(dbOrderAfterSales.size());
			for (DaikenOrderAfterSale dbOrderAfterSale : dbOrderAfterSales) {
				String goodsStatusStr = "未收到货物";
				RefundCauseGoodsBO goodsBo = new RefundCauseGoodsBO();
				DaikenGoodsNewSku dbGoodsNewSku = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "skuNo" },
						new Object[] { false, dbOrderAfterSale.getSkuNo() });
				if (dbGoodsNewSku != null) {
					BeanUtils.copyProperties(dbGoodsNewSku, goodsBo);
				}
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsNewSku.getGoodsId());
				if (dbGoodsNew != null) {
					BeanUtils.copyProperties(dbGoodsNew, goodsBo);
				}
				BeanUtils.copyProperties(dbOrderAfterSale, goodsBo);
				goodsBo.setGoodsId(dbOrderAfterSale.getGoodsId());
				goodsBo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
				goodsBo.setRecordType(DaikenUtil.parseJsonArray(dbGoodsNewSku.getRecordType()));
				goodsBo.setCreateTimeStr(DaikenUtil.formatDate(dbOrderAfterSale.getCreateTime()));
				goodsBo.setRefundImg(Arrays.asList());
				if (StringUtils.isNotBlank(dbOrderAfterSale.getRefundImg())) {
					goodsBo.setRefundImg(JSONArray.parseArray(dbOrderAfterSale.getRefundImg(), String.class));
				}
				if (dbOrderAfterSale.getGoodsStatus()) {
					goodsStatusStr = "已收到货物";
				}
				goodsBo.setRefundReason("");
				if (StringUtils.isNotBlank(dbOrderAfterSale.getRefundReason())) {
					goodsBo.setRefundReason(dbOrderAfterSale.getRefundReason());
				}
				goodsBo.setGoodsStatusStr(goodsStatusStr);
				goodsBo.setRefundStatusStr(getRefundStatusStr(dbOrderAfterSale.getRefundStatus()));
				goodsBo.setGoodsSumPrice(
						dbGoodsNewSku.getPriceSales() * dbOrderAfterSale.getGoodsNum() + dbOrder.getPostage());
				refundCauseGoodsBO.add(goodsBo);
			}
			bo.setRefundCauseGoodsBO(refundCauseGoodsBO);
		}
		return bo;
	}

	/**
	 * 获取快递公司
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	public List<CourierCompanyBO> getCourierCompany() {
		List<CourierCompanyBO> result = new ArrayList<>();
		for (LogisticsCompanyEnum item : LogisticsCompanyEnum.values()) {
			result.add(new CourierCompanyBO(DaikenMapData.expressCompany.get(item.getType()), item.getType()));
		}
		return result;
	}

	/**
	 * 发货
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	public KemeanResult<String> sendGoods(SendGoodsPO sendGoodsPO) {
		Date now = new Date();
		LogisticsInfoDB newLogisticsInfoDB = new LogisticsInfoDB();
		BeanUtils.copyProperties(sendGoodsPO, newLogisticsInfoDB);
		String companyName = "";
		for (LogisticsCompanyEnum item : LogisticsCompanyEnum.values()) {
			if (item.getType().equals(sendGoodsPO.getCompanyNo())) {
				companyName = DaikenMapData.expressCompany.get(item.getType());
			}
		}
		newLogisticsInfoDB.setCompanyName(companyName);
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", sendGoodsPO.getOrderNo());
		dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.SHIP.getStatus());
		dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus());
		dbOrder.setRecordLogistics(JSONObject.toJSONString(newLogisticsInfoDB));
		dbOrder.setSendTime(now);
		dbOrder.setUpdateTime(now);
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		userService.daikenSendCode(dbOrder.getUserPhone(),
				"您在 " + dbOrder.getShopName() + "订购的货物，在" + DaikenUtil.formatDate(now) + "已给你发货!!!");
		return new KemeanResult<String>();
	}

	public String getBusinessStatusStr(Integer shopStatus) {
		String userStatusStr = "";
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus())) {
			userStatusStr = "待用户支付";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.CANCLE_NOT_PAY.getStatus())) {
			userStatusStr = "未支付取消 ";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.REFUSE.getStatus())) {
			userStatusStr = "拒绝接单";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.WAIT_ACCEPT.getStatus())) {
			userStatusStr = "待接单";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus())) {
			userStatusStr = "待发货";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus())) {
			userStatusStr = "已发货";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus())) {
			userStatusStr = "已完成";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus())) {
			userStatusStr = "售后";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus())) {
			userStatusStr = "确认退款";
		}
		if (shopStatus.equals(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus())) {
			userStatusStr = "拒绝退款";
		}
		return userStatusStr;
	}

	public String getRefundStatusStr(Integer refundStatus) {
		String refundStatusStr = "";
		if (refundStatus.equals(KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus())) {
			refundStatusStr = "待退款";
		}
		if (refundStatus.equals(KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus())) {
			refundStatusStr = "已退款";
		}
		if (refundStatus.equals(KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus())) {
			refundStatusStr = "拒绝退款";
		}
		return refundStatusStr;
	}

	/**
	 * 确认退款按钮
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	@Transactional
	public KemeanResult<String> confirmRejectRefund(ConfirmRefundPO confirmRefundPO) {
		Date now = new Date();
		if (confirmRefundPO.getFlag()) {
			// 修改子订单状态
			DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo", "goodsId", "skuNo" }, new Object[] { false,
							confirmRefundPO.getOrderNo(), confirmRefundPO.getGoodsId(), confirmRefundPO.getSkuNo() });
			// TODO 需立即退款
			dbOrderAfterSale.setRefundStatus(KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus());
			dbOrderAfterSale.setUpdateTime(now);
			daikenOrderAfterSaleDao.updateByPrimaryKeySelective(dbOrderAfterSale);

			Boolean flag = true;
			List<DaikenOrderAfterSale> dbOrderAfterSales = daikenOrderAfterSaleDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
					new Object[] { false, confirmRefundPO.getOrderNo() });
			for (DaikenOrderAfterSale daikenOrderAfterSale : dbOrderAfterSales) {
				if (daikenOrderAfterSale.getRefundStatus()
						.equals(KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus())) {
					flag = false;
				}
			}

			if (flag) {
				// 修改主订单状态
				DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo",
						confirmRefundPO.getOrderNo());
				dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus());
				dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus());
				dbOrder.setUpdateTime(new Date());
				daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			}
		}

		if (!confirmRefundPO.getFlag()) {
			// 修改子订单状态
			DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo", "goodsId", "skuNo" }, new Object[] { false,
							confirmRefundPO.getOrderNo(), confirmRefundPO.getGoodsId(), confirmRefundPO.getSkuNo() });
			dbOrderAfterSale.setRefundStatus(KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus());
			dbOrderAfterSale.setUpdateTime(now);
			daikenOrderAfterSaleDao.updateByPrimaryKeySelective(dbOrderAfterSale);

			Boolean flag = true;
			List<DaikenOrderAfterSale> dbOrderAfterSales = daikenOrderAfterSaleDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
					new Object[] { false, confirmRefundPO.getOrderNo() });
			for (DaikenOrderAfterSale daikenOrderAfterSale : dbOrderAfterSales) {
				if (daikenOrderAfterSale.getRefundStatus()
						.equals(KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus())) {
					flag = false;
				}
			}

			if (flag) {
				// 修改主订单状态
				DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo",
						confirmRefundPO.getOrderNo());
				dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus());
				dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus());
				dbOrder.setIsDisputed(true);
				dbOrder.setUpdateTime(new Date());
				daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			}
		}
		return new KemeanResult<>();
	}

	/**
	 * 订单 取消
	 * 
	 * @author huwei
	 * @date 2018年7月5日
	 */
	public KemeanResult<String> cancleOrder(String orderNo) {
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus());
		dbOrder.setUpdateTime(new Date());
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		return new KemeanResult<String>();
	}
}
