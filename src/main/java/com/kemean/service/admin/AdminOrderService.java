package com.kemean.service.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenOrderAfterSale;
import com.kemean.bean.DaikenOrderGoods;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserShop;
import com.kemean.constant.DaikenAdminResultTips;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanMapData;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.constant.LogisticsCompanyEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenOrderAfterSaleDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.exception.KemeanException;
import com.kemean.service.LogisticsService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.util.KemeanUtilWeb;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.ThirdLogisticsInfoDataBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderInfoBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderListBO;
import com.kemean.vo.bo.admin.shop.AdminShopOrderReturnBO;
import com.kemean.vo.bo.admin.shop.AdminShopReturnBO;
import com.kemean.vo.bo.b.order.OrderListGoodsBO;
import com.kemean.vo.mysql.AdminRecordLogistics;
import com.kemean.vo.mysql.GoodsRecordDB;
import com.kemean.vo.mysql.LogisticsInfoDB;
import com.kemean.vo.mysql.UserAddressDB;
import com.kemean.vo.po.admin.shop.AdminOrderExportExcelPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderListPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderReturnPO;
import com.kemean.vo.po.admin.shop.AdminShopRefundLogisticsPO;

@Service
public class AdminOrderService {

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenOrderAfterSaleDao daikenOrderAfterSaleDao;

	@Autowired
	private LogisticsService logisticsService;

	@Autowired
	private DaikenUserShopDao daikenUserShopDao;

	/**
	 * 订单详情data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	public AdminShopOrderBO shopOrderDataInfo(Integer objId) {
		AdminShopOrderBO bo = new AdminShopOrderBO();
		DaikenOrder dbOrder = daikenOrderDao.selectById(objId);
		BeanUtils.copyProperties(dbOrder, bo);
		bo.setStatusShopStr(KemeanMapData.orderStatusBusiness.get(dbOrder.getStatusShop()));
		bo.setStatusUserStr(KemeanMapData.orderStatusUser.get(dbOrder.getStatusUser()));
		DaikenUser dbUser = daikenUserDao.selectById(dbOrder.getIdUser());
		bo.setUserUid(dbUser.getUid());

		List<DaikenOrderGoods> dbOrderGoodss = daikenOrderGoodsDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, dbOrder.getOrderNo() });

		List<OrderListGoodsBO> goods = new ArrayList<OrderListGoodsBO>();
		for (DaikenOrderGoods daikenOrderGoods : dbOrderGoodss) {
			OrderListGoodsBO goodsBO = new OrderListGoodsBO();
			GoodsRecordDB goodsRecordDB = JSONObject.parseObject(daikenOrderGoods.getGoodsRecord(),
					GoodsRecordDB.class);
			if (goodsRecordDB.getPriceStore() == null) {
				goodsBO.setPriceStore(0.0);
			}
			BeanUtils.copyProperties(goodsRecordDB, goodsBO);
			BeanUtils.copyProperties(daikenOrderGoods, goodsBO);
			if (StringUtils.isNoneBlank(goodsRecordDB.getRecordType())) {
				goodsBO.setRecordType(DaikenUtil.parseJsonArray(goodsRecordDB.getRecordType()));
			}
			goods.add(goodsBO);
		}
		bo.setGoods(goods);

		// 快递信息
		bo.setCompanyNo("");
		bo.setExpressWaybillNo("");
		if (StringUtils.isNotBlank(dbOrder.getRecordLogistics())) {
			LogisticsInfoDB logisticsInfoDB = JSONObject.parseObject(dbOrder.getRecordLogistics(),
					LogisticsInfoDB.class);
			bo.setExpressWaybillNo(logisticsInfoDB.getExpressWaybillNo());
			bo.setCompanyNo(logisticsInfoDB.getCompanyNo());
			bo.setCompanyName(logisticsInfoDB.getCompanyName());
		}

		if (StringUtils.isNoneBlank(dbOrder.getRecordReceiving()) && dbOrder.getIdShop() > 0) {
			UserAddressDB userAddressDB = JSONObject.parseObject(dbOrder.getRecordReceiving(), UserAddressDB.class);
			bo.setRecordReceiving(userAddressDB.getProvinceName() + userAddressDB.getCityName()
					+ userAddressDB.getAreaName() + userAddressDB.getAddress());
			bo.setUserPhone(userAddressDB.getPhone());
		}
		bo.setPayTypeStr(KemeanMapData.payType.get(dbOrder.getPayType()));
		// 售后信息
		if (KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus().equals(dbOrder.getStatusShop())
				|| KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus().equals(dbOrder.getStatusShop())
				|| KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus()
						.equals(dbOrder.getStatusShop())) {
			bo.setGoodsReturn(shopReturnInfoData(dbOrder.getOrderNo()));
		}
		return bo;

	}

	/**
	 * 店铺退货信息查询
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */

	public KemeanPageAdminBO<List<AdminShopReturnBO>> shopReturnData(AdminShopOrderPO adminShopReturnPO,
			DaikenUser loginer) {

		List<DaikenOrderAfterSale> dbOrderAfterSale = daikenOrderAfterSaleDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo", "refundStatus" },
				new Object[] { false, adminShopReturnPO.getOrderNo(), adminShopReturnPO.getOrderStatus() }, "id", false,
				adminShopReturnPO.getPage(), adminShopReturnPO.getLimit());

		List<AdminShopReturnBO> result = Lists.transform(dbOrderAfterSale,
				new Function<DaikenOrderAfterSale, AdminShopReturnBO>() {
					@Override
					public AdminShopReturnBO apply(DaikenOrderAfterSale input) {
						AdminShopReturnBO bo = new AdminShopReturnBO();
						BeanUtils.copyProperties(input, bo);
						bo.setGoodsStatusStr(BooleanUtils.isNotTrue(input.getGoodsStatus()) ? "已收到货物" : "没有收到");
						bo.setRefundStatus(KemeanMapData.orderStatusBusiness.get(input.getRefundStatus()));
						return bo;
					}
				});
		return new KemeanPageAdminBO<List<AdminShopReturnBO>>(new PageInfo<>(dbOrderAfterSale).getTotal(), result);
	}

	/**
	 * 退款详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	public AdminShopReturnBO shopReturnInfoData(String orderNo) {
		AdminShopReturnBO bo = new AdminShopReturnBO();
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
				new String[] { "orderNo", KemeanConstant.DATA_DELETED }, new Object[] { orderNo, false });

		DaikenOrderAfterSale dbOrderAdterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
				new String[] { "orderNo", KemeanConstant.DATA_DELETED }, new Object[] { orderNo, false });
		BeanUtils.copyProperties(dbOrderAdterSale, bo);

		if (StringUtils.isNoneBlank(dbOrderAdterSale.getRefundImg())) {
			bo.setRefundImg(JSONArray.parseArray(dbOrderAdterSale.getRefundImg(), String.class));
		}
		bo.setGoodsTitle(dbOrder.getGoodsTitles());
		bo.setRefundReason(dbOrderAdterSale.getRefundReason());
		bo.setShopName(dbOrder.getShopName());
		bo.setGoodsStatusStr(BooleanUtils.isNotTrue(dbOrderAdterSale.getGoodsStatus()) ? "已收到货物" : "没有收到");
		return bo;
	}

	/**
	 * 删除订单
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月13日
	 */
	public KemeanResult<String> orderDel(Integer objId, DaikenUser loginAdminShop) {
		DaikenOrder dbOrder = daikenOrderDao.selectById(objId);
		if (loginAdminShop != null) {
			dbOrder.setDeletedShop(true);
		} else {
			dbOrder.setDataDeleted(true);
		}
		dbOrder.setUpdateTime(new Date());
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		return new KemeanResult<>(true, KemeanTips.Operate.DELETE_SUCCESS);
	}

	/**
	 * 商家订单管理
	 * 
	 * @author linjinzhan
	 * @date 2018年8月16日
	 */
	public KemeanPageAdminBO<List<AdminShopOrderListBO>> shopOrderData(AdminShopOrderListPO adminShopOrderListPO,
			DaikenUser loginUser) {
		Integer shopId = loginUser != null ? loginUser.getShopId() : null;
		Integer orderStatus = adminShopOrderListPO.getOrderStatus();
		if (adminShopOrderListPO.getAfterSaleStatus() != null) {
			orderStatus = null;
		}
		List<DaikenOrder> dbOrders = daikenOrderDao.orderListBusiness(adminShopOrderListPO.getOrderNo(),
				adminShopOrderListPO.getShopName(), shopId, adminShopOrderListPO.getAfterSaleStatus(),
				adminShopOrderListPO.getPurchasing(), adminShopOrderListPO.getRecordReceiving(), orderStatus,
				adminShopOrderListPO.getStartDate(), adminShopOrderListPO.getEndDate(), adminShopOrderListPO.getPage(),
				adminShopOrderListPO.getLimit());

		List<AdminShopOrderListBO> result = new ArrayList<AdminShopOrderListBO>();
		for (DaikenOrder dbOrder : dbOrders) {
			AdminShopOrderListBO bo = new AdminShopOrderListBO();
			// 查询订单商品信息
			List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.shopOrderGoods(dbOrder.getOrderNo(),
					adminShopOrderListPO.getGoodsUid(), adminShopOrderListPO.getSkuNo());
			if (CollectionUtils.isNotEmpty(dbOrderGoods)) {
				if (dbOrderGoods.size() > 1) {
					throw new KemeanException("【AdminShopOrderService】根据订单编号查询出来多个订单商品信息");
				}

				DaikenOrderGoods dbOrderGood = dbOrderGoods.get(0);
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbOrderGood.getGoodsId());
				if (adminShopOrderListPO.getCategoryId() != null) {
					if (!dbGoodsNew.getCategoryId().equals(adminShopOrderListPO.getCategoryId())) {
						continue;
					}
				}
				// 是否促销
				if (adminShopOrderListPO.getPromotion() != null) {
					if (DaikenGoodsType.SalesType.NORMAL_SALES.getType().equals(dbGoodsNew.getSalesType())) {
						continue;
					}

					if (adminShopOrderListPO.getSaleType() != null) {
						if (!dbGoodsNew.getSalesType().equals(adminShopOrderListPO.getSaleType())) {
							continue;
						}
					}

					bo.setSaleTypeStr(DaikenMapData.salesType.get(dbGoodsNew.getSalesType()));
				}
				DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
						new Object[] { false, dbOrder.getOrderNo() });
				Double compensateMoney = 0.00;
				if (dbOrderAfterSale != null) {
					compensateMoney = dbOrderAfterSale.getCompensateMoney();
				}
				// 补偿金额
				bo.setCompensateMoney(compensateMoney);
				// 实际转账金额 = 用户支付价格- 代卖佣金- 售前-售后-补偿
				Double realMoney = dbOrder.getPricePay() - dbOrderGood.getPricePurchasing() - dbOrderGood.getRedAfter()
						- dbOrderGood.getRedBefore() - dbOrderGood.getCompensateMoney();
				bo.setRealMoney(realMoney);

				// 帮代卖
				if (adminShopOrderListPO.getPurchasing() != null) {
					DaikenUserShop dbUserShop = daikenUserShopDao.selectById(dbOrder.getIdPurchasing());
					bo.setPurchasingShopName(dbUserShop.getShopName());
				}

				BeanUtils.copyProperties(dbOrderGood, bo);
				BeanUtils.copyProperties(dbOrder, bo);
				// 红包成本
				bo.setRedBeforeAndAfter(dbOrderGood.getRedBefore() + dbOrderGood.getRedAfter());
				bo.setRedAfter(dbOrderGood.getRedAfter());
				bo.setRedBefore(dbOrderGood.getRedBefore());
				bo.setId(dbOrder.getId());
				bo.setStatusShopStr(KemeanMapData.orderStatusBusiness.get(dbOrder.getStatusShop()));
				bo.setCategoryName(dbGoodsNew.getCategoryName());
				// 收货地址
				UserAddressDB userAddressDB = JSONObject.parseObject(dbOrder.getRecordReceiving(), UserAddressDB.class);
				bo.setRecordReceiving(userAddressDB.getProvinceName() + userAddressDB.getCityName()
						+ userAddressDB.getAreaName() + userAddressDB.getAddress());
				bo.setUserPhone(userAddressDB.getPhone());
				// 用户信息(微信用户名、收货人、收货地址、电话）
				bo.setUserRecord(userAddressDB.getReceiveName());
				result.add(bo);
			}
		}
		return new KemeanPageAdminBO<List<AdminShopOrderListBO>>(new PageInfo<>(dbOrders).getTotal(), result);
	}

	/**
	 * 商家订单商品详情
	 * 
	 * @author linjinzhan
	 * @date 2018年8月17日
	 */
	public AdminShopOrderInfoBO shopOrderAndGoodsInfo(Integer objId) {
		AdminShopOrderInfoBO bo = new AdminShopOrderInfoBO();
		DaikenOrder dbOrder = daikenOrderDao.selectById(objId);
		BeanUtils.copyProperties(dbOrder, bo);
		bo.setStatusShopStr(KemeanMapData.orderStatusBusiness.get(dbOrder.getStatusShop()));
		bo.setStatusUserStr(KemeanMapData.orderStatusUser.get(dbOrder.getStatusUser()));

		DaikenUser dbUser = daikenUserDao.selectById(dbOrder.getIdUser());
		bo.setUserUid(dbUser.getUid());

		DaikenOrderGoods dbOrderGoods = daikenOrderGoodsDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, dbOrder.getOrderNo() });
		BeanUtils.copyProperties(dbOrderGoods, bo);

		UserAddressDB userAddressDB = JSONObject.parseObject(dbOrder.getRecordReceiving(), UserAddressDB.class);
		bo.setRecordReceiving(userAddressDB.getProvinceName() + userAddressDB.getCityName()
				+ userAddressDB.getAreaName() + userAddressDB.getAddress());
		bo.setPayTypeStr(KemeanMapData.payType.get(dbOrder.getPayType()));
		bo.setGoodsquantity(dbOrderGoods.getQuantity());
		GoodsRecordDB goodsRecordDB = JSONObject.parseObject(dbOrderGoods.getGoodsRecord(), GoodsRecordDB.class);
		bo.setCreateTime(KemeanUtilAid.formatDate(dbOrder.getCreateTime()));
		if (StringUtils.isNoneBlank(goodsRecordDB.getRecordType())) {
			bo.setRecordType(DaikenUtil.parseJsonArray(goodsRecordDB.getRecordType()));
		}
		if (!dbOrder.getIdPurchasing().equals(0)) {
			DaikenUser user = daikenUserDao.selectById(dbOrder.getIdPurchasingUser());
			bo.setUserPurchasing(user.getNickName());
		}
		return bo;
	}

	/**
	 * 根据订单id获取订单物流信息
	 * 
	 * @author linjinzhan
	 * @date 2018年8月19日
	 */
	public KemeanResult<List<ThirdLogisticsInfoDataBO>> orderRecordLogistics(Integer orderId) {
		DaikenOrder dbOrder = daikenOrderDao.selectById(orderId);
		// 判断是否查询到对应的订单信息
		if (null == dbOrder) {
			return new KemeanResult<List<ThirdLogisticsInfoDataBO>>(false, DaikenAdminResultTips.Order.ORDER_NOT_FOUND);
		}
		// 判断订单物流信息是否为空
		if (StringUtils.isEmpty(dbOrder.getRecordLogistics())) {
			return new KemeanResult<List<ThirdLogisticsInfoDataBO>>(false,
					DaikenAdminResultTips.Order.ORDER_LOGISTICS_NOT_FOUND);
		}
		AdminRecordLogistics dbRecordLogistics = JSONObject.parseObject(dbOrder.getRecordLogistics(),
				AdminRecordLogistics.class);
		List<ThirdLogisticsInfoDataBO> logisticsInfo = logisticsService
				.getThirdLogisticsInfo(dbRecordLogistics.getCompanyNo(), dbRecordLogistics.getExpressWaybillNo());
		return new KemeanResult<List<ThirdLogisticsInfoDataBO>>(logisticsInfo);
	}

	/**
	 * 售后管理退货待收订单列表，补偿订单列表
	 * 
	 * @author linjinzhan
	 * @date 2018年8月19日
	 */
	public KemeanPageAdminBO<List<AdminShopOrderReturnBO>> shopReturnListData(
			AdminShopOrderReturnPO adminShopOrderReturnPO, DaikenUser loginUser) {
		Integer shopId = loginUser.getShopId();
		List<DaikenOrder> dbOrders = daikenOrderDao.shopOrderList(adminShopOrderReturnPO.getOrderNo(), shopId,
				adminShopOrderReturnPO.getOrderStatus(), adminShopOrderReturnPO.getStartDate(),
				adminShopOrderReturnPO.getEndDate(), adminShopOrderReturnPO.getPage(),
				adminShopOrderReturnPO.getLimit());

		List<AdminShopOrderReturnBO> result = new ArrayList<AdminShopOrderReturnBO>();
		for (DaikenOrder dbOrder : dbOrders) {
			AdminShopOrderReturnBO bo = new AdminShopOrderReturnBO();
			// 查询订单商品信息
			List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.shopOrderGoods(dbOrder.getOrderNo(),
					adminShopOrderReturnPO.getGoodsUid(), null);
			if (CollectionUtils.isNotEmpty(dbOrderGoods)) {
				if (dbOrderGoods.size() > 1) {
					throw new KemeanException("【AdminShopOrderService】根据订单编号查询出来多个订单商品信息");
				}
				DaikenOrderGoods dbOrderGood = dbOrderGoods.get(0);
				BeanUtils.copyProperties(dbOrder, bo);
				BeanUtils.copyProperties(dbOrderGood, bo);
				DaikenUser dbUser = daikenUserDao.selectById(dbOrder.getIdUser());
				bo.setUserName(dbUser.getNickName());
				bo.setUserPhone(dbUser.getPhone());
				// 售后信息
				DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
						new Object[] { false, dbOrder.getOrderNo() });
				if (null != dbOrderAfterSale) {
					BeanUtils.copyProperties(dbOrderAfterSale, bo);
					// 快递单号
					if (StringUtils.isNotBlank(dbOrderAfterSale.getRefundRecordLogistics())) {
						AdminRecordLogistics dbRefundRecordLogistics = JSONObject
								.parseObject(dbOrderAfterSale.getRefundRecordLogistics(), AdminRecordLogistics.class);
						bo.setExpressWaybillNo(dbRefundRecordLogistics.getExpressWaybillNo());
					}
					bo.setRefundTime(dbOrderAfterSale.getCreateTime());
				}
				bo.setOrderTime(dbOrder.getCreateTime());
				bo.setId(dbOrder.getId());
				result.add(bo);
			}
		}
		return new KemeanPageAdminBO<List<AdminShopOrderReturnBO>>(new PageInfo<>(dbOrders).getTotal(), result);
	}

	/**
	 * 保存客户退货的物流信息
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	public KemeanResult<String> saveRefundLogistics(AdminShopRefundLogisticsPO adminShopRefundLogisticsPO) {
		LogisticsInfoDB newLogisticsInfoDB = new LogisticsInfoDB();
		Date now = new Date();
		BeanUtils.copyProperties(adminShopRefundLogisticsPO, newLogisticsInfoDB);
		String companyName = "";
		for (LogisticsCompanyEnum item : LogisticsCompanyEnum.values()) {
			if (item.getType().equals(adminShopRefundLogisticsPO.getCompanyNo())) {
				companyName = DaikenMapData.expressCompany.get(item.getType());
			}
		}
		newLogisticsInfoDB.setCompanyName(companyName);
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo",
				adminShopRefundLogisticsPO.getOrderNo());
		// 退货之后即退款成功
		dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus());
		dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus());
		dbOrder.setUpdateTime(now);
		// 更新订单信息
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		// 保存物流信息
		DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperty("orderNo",
				dbOrder.getOrderNo());
		dbOrderAfterSale.setRefundRecordLogistics(JSONObject.toJSONString(newLogisticsInfoDB));
		dbOrderAfterSale.setRefundStatus(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus());
		dbOrderAfterSale.setUpdateTime(now);
		// 更新售后订单信息
		daikenOrderAfterSaleDao.updateByPrimaryKeySelective(dbOrderAfterSale);
		return new KemeanResult<String>(true, DaikenAdminResultTips.Order.ORDER_AFTER_SALE_LOGISTICS_SUCCESS);
	}

	/**
	 * 保存补偿金额
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	@Transactional
	public KemeanResult<String> saveOrderCompensate(String orderNo, Double compensateMoney) {
		Date now = new Date();
		// 获取对应售后订单记录
		DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperty("orderNo",
				orderNo);
		if (null == dbOrderAfterSale) {
			return new KemeanResult<String>(false, DaikenAdminResultTips.Order.ORDER_AFTER_SALE_NOT_FOUND);
		}
		dbOrderAfterSale.setCompensateMoney(compensateMoney);
		dbOrderAfterSale.setCompensateTime(now);
		dbOrderAfterSale.setUpdateTime(now);
		daikenOrderAfterSaleDao.updateByPrimaryKeySelective(dbOrderAfterSale);

		daikenOrderGoodsDao.updateEntityByProperties("compensateMoney", compensateMoney,
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });

		return new KemeanResult<String>(true, DaikenAdminResultTips.Order.ORDER_COMPENSATE_SAVE_SUCCESS);
	}

	/**
	 * 统计订单补偿总金额
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	public Double orderCompensateCount(DaikenUser loginUser) {
		return daikenOrderAfterSaleDao.selectOrderCompensateTotal(loginUser.getShopId());
	}

	/**
	 * 发货管理订单列表导出
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	public HttpEntity<byte[]> exportOrderExcel(AdminOrderExportExcelPO adminOrderExportExcelPO, DaikenUser loginUser)
			throws IOException {
		String fileName = new String(adminOrderExportExcelPO.getFileName().getBytes("utf-8"), "iso8859-1");
		List<String> header = Arrays.asList("订单编号", "商品编号", "商品名称", "商品数量", "收货人", "电话", "收货地址", "订单状态", "订单时间", "备注");
		List<List<String>> data = new ArrayList<List<String>>();

		List<DaikenOrder> dbOrders = daikenOrderDao.exportOrderList(adminOrderExportExcelPO.getOrderNo(),
				loginUser.getShopId(), adminOrderExportExcelPO.getOrderStatus(), adminOrderExportExcelPO.getStartDate(),
				adminOrderExportExcelPO.getEndDate());
		List<AdminShopOrderListBO> result = new ArrayList<AdminShopOrderListBO>();
		for (DaikenOrder dbOrder : dbOrders) {
			AdminShopOrderListBO bo = new AdminShopOrderListBO();
			// 查询订单商品信息
			List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.shopOrderGoods(dbOrder.getOrderNo(),
					adminOrderExportExcelPO.getGoodsUid(), null);
			if (CollectionUtils.isNotEmpty(dbOrderGoods)) {
				if (dbOrderGoods.size() > 1) {
					throw new KemeanException("【AdminShopOrderService】根据订单编号查询出来多个订单商品信息");
				}
				DaikenOrderGoods dbOrderGood = dbOrderGoods.get(0);
				BeanUtils.copyProperties(dbOrderGood, bo);
				BeanUtils.copyProperties(dbOrder, bo);
				// 红包成本
				bo.setRedBeforeAndAfter(dbOrderGood.getRedBefore() + dbOrderGood.getRedAfter());
				bo.setId(dbOrder.getId());
				// 收货地址
				UserAddressDB userAddressDB = JSONObject.parseObject(dbOrder.getRecordReceiving(), UserAddressDB.class);
				bo.setRecordReceiving(userAddressDB.getProvinceName() + userAddressDB.getCityName()
						+ userAddressDB.getAreaName() + userAddressDB.getAddress());
				bo.setUserPhone(userAddressDB.getPhone());
				result.add(bo);
			}
		}
		// 遍历列表数据，插入到二维列表中
		for (AdminShopOrderListBO item : result) {
			List<String> row = new ArrayList<String>();
			row.add(item.getOrderNo());
			row.add(item.getGoodsUid());
			row.add(item.getGoodsTitles());
			row.add(item.getQuantity().toString());
			row.add(item.getUserName());
			row.add(item.getUserPhone());
			row.add(item.getRecordReceiving());
			if (KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus().equals(item.getStatusShop())) {
				row.add("待发货");
			} else if (KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus().equals(item.getStatusShop())) {
				row.add("已发货");
			} else {
				row.add("待确定");
			}
			row.add(DaikenUtil.formatDate(item.getCreateTime()));
			row.add(item.getRemark());
			data.add(row);
		}
		return KemeanUtilWeb.exportExcel(fileName, header, data);
	}

	/**
	 * 售后管理订单列表导出
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	public HttpEntity<byte[]> exportReturnOrderExcel(AdminOrderExportExcelPO adminOrderExportExcelPO,
			DaikenUser loginUser) throws IOException {
		String fileName = new String(adminOrderExportExcelPO.getFileName().getBytes("utf-8"), "iso8859-1");
		List<String> header = Arrays.asList("订单编号", "商品编号", "商品名称", "退货数量", "退货金额", "客户留言", "快递单号", "处理状态", "退货时间",
				"订单时间");
		List<List<String>> data = new ArrayList<List<String>>();

		List<DaikenOrder> dbOrders = daikenOrderDao.exportOrderList(adminOrderExportExcelPO.getOrderNo(),
				loginUser.getShopId(), adminOrderExportExcelPO.getOrderStatus(), adminOrderExportExcelPO.getStartDate(),
				adminOrderExportExcelPO.getEndDate());

		List<AdminShopOrderReturnBO> result = new ArrayList<AdminShopOrderReturnBO>();
		for (DaikenOrder dbOrder : dbOrders) {
			AdminShopOrderReturnBO bo = new AdminShopOrderReturnBO();
			// 查询订单商品信息
			List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.shopOrderGoods(dbOrder.getOrderNo(),
					adminOrderExportExcelPO.getGoodsUid(), null);
			if (CollectionUtils.isNotEmpty(dbOrderGoods)) {
				if (dbOrderGoods.size() > 1) {
					throw new KemeanException("【AdminShopOrderService】根据订单编号查询出来多个订单商品信息");
				}
				DaikenOrderGoods dbOrderGood = dbOrderGoods.get(0);
				BeanUtils.copyProperties(dbOrder, bo);
				BeanUtils.copyProperties(dbOrderGood, bo);
				DaikenUser dbUser = daikenUserDao.selectById(dbOrder.getIdUser());
				bo.setUserName(dbUser.getNickName());
				bo.setUserPhone(dbUser.getPhone());
				// 售后信息
				DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
						new Object[] { false, dbOrder.getOrderNo() });
				if (null != dbOrderAfterSale) {
					BeanUtils.copyProperties(dbOrderAfterSale, bo);
					// 快递单号
					if (StringUtils.isNotBlank(dbOrderAfterSale.getRefundRecordLogistics())) {
						AdminRecordLogistics dbRefundRecordLogistics = JSONObject
								.parseObject(dbOrderAfterSale.getRefundRecordLogistics(), AdminRecordLogistics.class);
						bo.setExpressWaybillNo(dbRefundRecordLogistics.getExpressWaybillNo());
					}
					bo.setRefundTime(dbOrderAfterSale.getCreateTime());
				}
				bo.setOrderTime(dbOrder.getCreateTime());
				bo.setId(dbOrder.getId());
				result.add(bo);
			}
		}
		// 遍历列表数据，插入到二维列表中
		for (AdminShopOrderReturnBO item : result) {
			List<String> row = new ArrayList<String>();
			row.add(item.getOrderNo());
			row.add(item.getGoodsUid());
			row.add(item.getGoodsTitles());
			row.add(item.getGoodsNum().toString());
			row.add(item.getRefundMoney().toString());
			row.add(item.getRefundReason());
			row.add(item.getExpressWaybillNo());
			if (KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus().equals(item.getRefundStatus())) {
				row.add("未完成");
			} else if (KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus()
					.equals(item.getRefundStatus())
					|| KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus().equals(item.getRefundStatus())) {
				row.add("已发货");
			} else {
				row.add("待确定");
			}
			row.add(DaikenUtil.formatDate(item.getRefundTime()));
			row.add(DaikenUtil.formatDate(item.getOrderTime()));
			data.add(row);
		}
		return KemeanUtilWeb.exportExcel(fileName, header, data);
	}

}
