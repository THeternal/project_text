package com.kemean.service.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenGoodsOld;
import com.kemean.bean.DaikenInvestigate;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenOrderAfterSale;
import com.kemean.bean.DaikenOrderAppraisal;
import com.kemean.bean.DaikenOrderGoods;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserShop;
import com.kemean.bean.KemeanConfig;
import com.kemean.bean.KemeanFinanceRefund;
import com.kemean.bean.KemeanUserAddress;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenPushJumType;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenShareTypeEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanPayTypeEnum;
import com.kemean.constant.KemeanResultEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenGoodsOldDao;
import com.kemean.dao.DaikenInvestigateDao;
import com.kemean.dao.DaikenOrderAfterSaleDao;
import com.kemean.dao.DaikenOrderAppraisalDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.DaikenRedShareDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.dao.KemeanFinanceRefundDao;
import com.kemean.dao.KemeanUserAddressDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.PushJGService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.WxTemplateService;
import com.kemean.service.util.WxPayUtil;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.WxRefundBO;
import com.kemean.vo.bo.c.order.GetPaidOrderBO;
import com.kemean.vo.bo.c.order.GetPaidOrderGoodsBO;
import com.kemean.vo.bo.c.order.OrderInfoBO;
import com.kemean.vo.bo.c.order.OrderListBO;
import com.kemean.vo.bo.c.order.OrderListGoodsBO;
import com.kemean.vo.bo.c.order.RefundCauseBO;
import com.kemean.vo.bo.c.order.RefundCauseGoodsBO;
import com.kemean.vo.bo.c.order.SubmitOrderBO;
import com.kemean.vo.mysql.GoodsRecordDB;
import com.kemean.vo.mysql.LogisticsInfoDB;
import com.kemean.vo.mysql.UserAddressDB;
import com.kemean.vo.po.c.oder.CancelAfterSalePO;
import com.kemean.vo.po.c.oder.GetPaidOrderPO;
import com.kemean.vo.po.c.oder.OrderAppraisalGoodsPO;
import com.kemean.vo.po.c.oder.OrderAppraisalPO;
import com.kemean.vo.po.c.oder.OrderListPO;
import com.kemean.vo.po.c.oder.RefundAfterSalePO;
import com.kemean.vo.po.c.oder.SendTemplatePO;
import com.kemean.vo.po.c.oder.SubmitOrderPO;

/**
 * 【客户端】订单业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
@PropertySource(value = "classpath:kemean/kemean_third.properties", encoding = "UTF-8")
public class COrderService {

	@Value("${jg.base64}")
	private String jgBase64;

	@Value("${wx.app.template.order_goods}")
	public String templateId;

	@Autowired
	private KemeanUserAddressDao kemeanUserAddressDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenOrderAfterSaleDao daikenOrderAfterSaleDao;

	@Autowired
	private DaikenOrderAppraisalDao daikenOrderAppraisalDao;

	@Autowired
	private DaikenInvestigateDao daikenInvestigateDao;

	@Autowired
	private DaikenGoodsOldDao daikenGoodsOldDao;

	@Autowired
	private DaikenRedShareDao daikenRedShareDao;

	@Autowired
	private DaikenUserShopDao daikenUserShopDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private KemeanFinanceRefundDao kemeanFinanceRefundDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private PushJGService pushJGService;

	@Autowired
	private WxTemplateService wxTemplateService;

	@Autowired
	private PayService payService;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	/**
	 * 用户订单列表（待付款，代发货，待收货，待评价）
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public KemeanPageApiBO<List<OrderListBO>> orderList(OrderListPO orderListPO, DaikenUser loginConsumer) {
		Integer orderStatus = orderListPO.getOrderStatus() == null
				? KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus()
				: orderListPO.getOrderStatus();
		String keyWord = "";
		if (orderListPO.getKeyword() != null) {
			if (StringUtils.isNotBlank(orderListPO.getKeyword())) {
				keyWord = orderListPO.getKeyword();
			}
		}
		List<DaikenOrder> dbOrders = daikenOrderDao.orderListConsumer(loginConsumer.getId(), keyWord, orderStatus,
				orderListPO.getPageNo(), orderListPO.getPageSize());
		List<OrderListBO> result = new ArrayList<OrderListBO>(dbOrders.size());
		if (CollectionUtils.isEmpty(dbOrders)) {
			return new KemeanPageApiBO<List<OrderListBO>>(0l, 1, result);
		}
		for (DaikenOrder daikenOrder : dbOrders) {
			OrderListBO bo = new OrderListBO();
			BeanUtils.copyProperties(daikenOrder, bo);
			bo.setSumQuantity(daikenOrder.getQuantity());
			bo.setShopId(daikenOrder.getIdShop());
			bo.setUserShopId(0);
			bo.setShopLogo("http://p68iq7sn2.bkt.clouddn.com/upload/201809/26/0b9b3f4ae741427e93684c4f9d84b018");
			bo.setShopName("二手商品");
			if (daikenOrder.getIsNewGoods()) {
				DaikenShop dbShop = daikenShopDao.selectById(daikenOrder.getIdShop());
				bo.setShopLogo(dbShop.getShopLogo());
				bo.setShopName(dbShop.getShopName());
				bo.setCompanyNo("");
				bo.setExpressWaybillNo("");
				if (StringUtils.isNotBlank(daikenOrder.getRecordLogistics())) {
					LogisticsInfoDB logisticsInfoDB = JSONObject.parseObject(daikenOrder.getRecordLogistics(),
							LogisticsInfoDB.class);
					bo.setCompanyNo(logisticsInfoDB.getCompanyNo());
					bo.setExpressWaybillNo(logisticsInfoDB.getExpressWaybillNo());
				}
			}
			bo.setStatusUserStr(getUserStatusStr(daikenOrder.getStatusUser()));
			List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
					new Object[] { false, daikenOrder.getOrderNo() });
			List<OrderListGoodsBO> orderListGoods = new ArrayList<OrderListGoodsBO>(dbOrderGoods.size());
			for (DaikenOrderGoods daikenOrderGoods : dbOrderGoods) {
				OrderListGoodsBO goodsBO = new OrderListGoodsBO();
				GoodsRecordDB orderGoodsRecordDB = JSONObject.parseObject(daikenOrderGoods.getGoodsRecord(),
						GoodsRecordDB.class);
				BeanUtils.copyProperties(orderGoodsRecordDB, goodsBO);
				goodsBO.setRecordType("");
				goodsBO.setSkuNo("");
				if (daikenOrder.getIsNewGoods()) {
					goodsBO.setRecordType(DaikenUtil.parseJsonArray(orderGoodsRecordDB.getRecordType()));
					goodsBO.setSkuNo(orderGoodsRecordDB.getSkuNo());
				}
				goodsBO.setGoodsId(daikenOrderGoods.getGoodsId());
				goodsBO.setQuantity(daikenOrderGoods.getQuantity());
				orderListGoods.add(goodsBO);
			}
			bo.setOrderListGoods(orderListGoods);
			if (daikenOrder.getIdPurchasing() != 0) {
				DaikenUserShop dbUserShop = daikenUserShopDao.selectById(daikenOrder.getIdPurchasing());
				bo.setShopLogo(dbUserShop.getImg());
				bo.setShopName(dbUserShop.getShopName());
				bo.setShopId(0);
				bo.setUserShopId(daikenOrder.getIdPurchasing());
			}
			result.add(bo);
		}
		PageInfo<DaikenOrder> pageInfo = new PageInfo<DaikenOrder>(dbOrders);
		return new KemeanPageApiBO<List<OrderListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 订单删除、取消
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	@Transactional
	public KemeanResult<String> cancleOrder(String orderNo) {
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		Date now = new Date();
		// 用户未支付 订单直接删除
		if (dbOrder.getStatusUser().equals(KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus())) {
			dbOrder.setDeletedUser(true);
			dbOrder.setUpdateTime(now);
			daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			return new KemeanResult<>();
		}
		// 用户支付，商家未发货 TODO 未测试
		if (dbOrder.getStatusUser().equals(KemeanOrderEnum.OrderStatusUser.PAYED.getStatus())
				|| dbOrder.getStatusUser().equals(KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus())) {
			String orderNoRefund = KemeanUtilAid.getOrderNo(now);
			KemeanFinanceRefund newRefund = new KemeanFinanceRefund();
			newRefund.setCreateTime(now);
			newRefund.setUpdateTime(now);
			newRefund.setMoney(dbOrder.getPricePay());
			newRefund.setOrderNo(dbOrder.getOrderNo());
			newRefund.setOrderNoRefund(orderNoRefund);
			newRefund.setPayType(dbOrder.getPayType());
			if (KemeanPayTypeEnum.WX_PAY.getType().equals(dbOrder.getPayType())) {
				WxRefundBO wxResult = payService.refundWx(dbOrder, orderNoRefund);
				if (!wxResult.getSuccessReturn()) {
					return new KemeanResult<>(false, wxResult.getReturnMsg());
				}
				if (!wxResult.getSuccessResult()) {
					return new KemeanResult<>(false, wxResult.getErrCodeDes());
				}
				newRefund.setThirdTradeNo(wxResult.getTransactionId());
				newRefund.setThirdTradeStatus(wxResult.getReturnCode());
			}
			dbOrder.setUpdateTime(now);
			dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus());
			daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			kemeanFinanceRefundDao.saveSelective(newRefund);
			return new KemeanResult<>();
		}
		// 用户支付，商家发货，用户未收货 不允许退款
		return new KemeanResult<String>();
	}

	/**
	 * 订单详情
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public OrderInfoBO orderInfo(String orderNo) {
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		OrderInfoBO bo = new OrderInfoBO();
		BeanUtils.copyProperties(dbOrder, bo);
		UserAddressDB userAddressDB = JSONObject.parseObject(dbOrder.getRecordReceiving(), UserAddressDB.class);
		BeanUtils.copyProperties(userAddressDB, bo);
		bo.setAddress(userAddressDB.getProvinceName() + userAddressDB.getCityName() + userAddressDB.getAreaName()
				+ userAddressDB.getAddress());
		bo.setStatusUserStr(getUserStatusStr(dbOrder.getStatusUser()));
		DaikenShop dbShop = daikenShopDao.selectById(dbOrder.getIdShop());
		bo.setUserShopUid("0");
		if (dbShop == null) {
			bo.setShopId(0);
			bo.setShopLogo("http://p68iq7sn2.bkt.clouddn.com/upload/201806/23/83997ca282af4d27ace20a9226756647");
			bo.setShopName("二手商品");
		}

		if (dbShop != null) {
			bo.setShopId(dbShop.getId());
			bo.setShopLogo(dbShop.getShopLogo());
			bo.setShopName(dbShop.getShopName());
			DaikenUser dbUserShop = daikenUserDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" },
					new Object[] { false, dbShop.getId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
			bo.setUserShopUid(String.valueOf(dbUserShop.getUid()));

		}
		List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, dbOrder.getOrderNo() });
		if (CollectionUtils.isNotEmpty(dbOrderGoods)) {
			List<OrderListGoodsBO> orderListGoods = new ArrayList<OrderListGoodsBO>(dbOrderGoods.size());
			for (DaikenOrderGoods daikenOrderGoods : dbOrderGoods) {
				OrderListGoodsBO goodsBO = new OrderListGoodsBO();
				goodsBO.setGoodsId(daikenOrderGoods.getGoodsId());
				GoodsRecordDB goodsRecordDB = JSONObject.parseObject(daikenOrderGoods.getGoodsRecord(),
						GoodsRecordDB.class);
				BeanUtils.copyProperties(goodsRecordDB, goodsBO);
				goodsBO.setQuantity(daikenOrderGoods.getQuantity());
				goodsBO.setRecordType("");
				// 一手
				if (dbOrder.getIsNewGoods()) {
					goodsBO.setRecordType(DaikenUtil.parseJsonArray(goodsRecordDB.getRecordType()));
				}

				// 二手
				if (!dbOrder.getIsNewGoods()) {
					DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(daikenOrderGoods.getGoodsId());
					bo.setUserShopUid(String.valueOf(dbGoodsOld.getUserUid()));
					goodsBO.setSkuNo("");
				}

				orderListGoods.add(goodsBO);
			}
			bo.setOrderListGoods(orderListGoods);
		}

		if (dbOrder.getIdPurchasing() != 0) {
			DaikenUserShop dbUserShop = daikenUserShopDao.selectById(dbOrder.getIdPurchasing());
			bo.setShopId(0);
			bo.setUserShopId(dbUserShop.getId());
			bo.setShopLogo(dbUserShop.getImg());
			bo.setShopName(dbUserShop.getShopName());
		}
		return bo;
	}

	/**
	 * 退款（售后）
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	@Transactional
	public KemeanResult<String> refundAfterSale(RefundAfterSalePO refundAfterSalePO) {
		String orderNo = refundAfterSalePO.getOrderNo();
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus());
		dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus());
		dbOrder.setUpdateTime(new Date());
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		Date now = new Date();
		DaikenOrderAfterSale newAfterSale = new DaikenOrderAfterSale();
		BeanUtils.copyProperties(refundAfterSalePO, newAfterSale);
		newAfterSale
				.setRefundImg(DaikenUtil.parseJSONArrayByList(refundAfterSalePO.getUploadDocumentsImg()).toString());
		newAfterSale.setGoodsId(refundAfterSalePO.getGoodsId());
		newAfterSale.setRefundStatus(KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus());
		newAfterSale.setSkuNo(refundAfterSalePO.getSkuNo());
		newAfterSale.setCreateTime(now);
		newAfterSale.setUpdateTime(now);
		daikenOrderAfterSaleDao.saveSelective(newAfterSale);
		// TODO 取消售后红包流水

		return new KemeanResult<String>();
	}

	/**
	 * 订单评价
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	@Transactional
	public KemeanResult<String> orderAppraisal(OrderAppraisalPO orderAppraisalPO, DaikenUser daikenUser) {
		Date now = new Date();
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderAppraisalPO.getOrderNo());
		dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.FINISH.getStatus());
		dbOrder.setUpdateTime(new Date());
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		Integer shopId = dbOrder.getIdShop();
		List<OrderAppraisalGoodsPO> appraisalGoodsPOs = orderAppraisalPO.getAppraisalGoodsPO();
		for (OrderAppraisalGoodsPO orderAppraisalGoodsPO : appraisalGoodsPOs) {
			DaikenOrderAppraisal newAppraisal = new DaikenOrderAppraisal();
			newAppraisal.setOrderNo(dbOrder.getOrderNo());
			newAppraisal.setIdShop(shopId);
			newAppraisal.setIdUser(daikenUser.getId());
			newAppraisal.setIdGoods(orderAppraisalGoodsPO.getGoodsId());
			newAppraisal.setUserNickName(daikenUser.getNickName());
			newAppraisal.setUserHeadImg(daikenUser.getHeadImg());
			newAppraisal.setScore(orderAppraisalGoodsPO.getScore());
			newAppraisal.setContent(orderAppraisalGoodsPO.getContent());
			newAppraisal
					.setContentImg(DaikenUtil.parseJSONArrayByList(orderAppraisalGoodsPO.getContentImg()).toString());
			newAppraisal.setIsNewGoods(dbOrder.getIsNewGoods());
			newAppraisal.setCreateTime(now);
			newAppraisal.setUpdateTime(now);
			daikenOrderAppraisalDao.saveSelective(newAppraisal);
			// 一手商品
			if (dbOrder.getIsNewGoods()) {
				DaikenShop dbShop = daikenShopDao.selectById(shopId);
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(orderAppraisalGoodsPO.getGoodsId());
				// 踩
				if (orderAppraisalGoodsPO.getScore().equals(1)) {
					dbGoodsNew.setNumTrample(dbGoodsNew.getNumTrample() + 1);
					dbShop.setNumTrample(dbShop.getNumTrample() + 1);
				}
				// 一般
				if (orderAppraisalGoodsPO.getScore().equals(2)) {
					dbGoodsNew.setNumCommon(dbGoodsNew.getNumCommon() + 1);
					dbShop.setNumCommon(dbShop.getNumCommon() + 1);
				}
				// 赞
				if (orderAppraisalGoodsPO.getScore().equals(3)) {
					dbGoodsNew.setNumPraise(dbGoodsNew.getNumPraise() + 1);
					dbShop.setNumPraise(dbGoodsNew.getNumPraise() + 1);
				}
				dbGoodsNew.setUpdateTime(now);
				daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
				dbShop.setUpdateTime(now);
				daikenShopDao.updateByPrimaryKeySelective(dbShop);
			}
		}
		return new KemeanResult<String>();
	}

	/**
	 * 立即下单
	 * 
	 * @author huwei
	 * @throws InterruptedException
	 * @date 2018年6月27日
	 */
	@Transactional
	public KemeanResult<SubmitOrderBO> submitOrder(SubmitOrderPO submitOrderPO, DaikenUser daikenUser)
			throws InterruptedException {
		Date now = new Date();
		SubmitOrderBO bo = new SubmitOrderBO();
		KemeanResult<SubmitOrderBO> result = new KemeanResult<SubmitOrderBO>(bo);
		String orderNo = DaikenUtil.getOrderNo(now);
		DaikenOrder newOrder = new DaikenOrder();
		newOrder.setOrderNo(orderNo);
		newOrder.setIdInvestigate(0);
		newOrder.setIdUser(daikenUser.getId());
		// 商铺id
		Integer idShop = 0;
		// 商铺名称
		String shopName = "";
		// 购买数量
		Integer quantity = 0;
		// 支付价格
		Double pricePay = 0.0;
		// 邮费
		Double postage = 0.0;
		// 商品名称
		String goodsTitles = "";
		// 一手商品购买
		if (submitOrderPO.getType()) {
			newOrder.setIsNewGoods(true);
			// 【乐观锁】 获取当前版本
			boolean flag = false;
			String skuNo = submitOrderPO.getSkuNo();
			DaikenGoodsNewSku dbGoodsNewSku = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "skuNo" }, new Object[] { false, skuNo });
			for (int i = 0; i < 3; i++) {
				Integer happyLock = daikenGoodsNewSkuDao.updateByHappyLock(dbGoodsNewSku.getId(),
						dbGoodsNewSku.getDateVersion());
				if (happyLock > 0) {
					flag = true;
					break;
				}
				Thread.sleep(1000);
				// 重新获取版本，尝试再次操作
				dbGoodsNewSku = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "skuNo" }, new Object[] { false, skuNo });
			}
			// 数据版本已经增加，操作失败
			if (!flag) {
				result.setCode(KemeanResultEnum.FAILURE.getCode());
				result.setInfo(DaikenApiResultTips.Order.ORDER_FAILED);
				result.setSuccess(false);
				return result;
			}
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsNewSku.getGoodsId());
			DaikenShop dbShop = daikenShopDao.selectById(dbGoodsNew.getShopId());
			DaikenOrderGoods newOrderGoods = new DaikenOrderGoods();
			newOrderGoods.setOrderNo(orderNo);
			newOrderGoods.setGoodsId(dbGoodsNewSku.getGoodsId());
			newOrderGoods.setGoodsUid(dbGoodsNew.getGoodsUid());
			newOrderGoods.setRedBefore(dbGoodsNew.getRedBefore());
			newOrderGoods.setRedAfter(dbGoodsNew.getRedAfter());
			newOrderGoods.setPricePurchasing(dbGoodsNew.getPricePurchasing());
			newOrderGoods.setSkuNo(skuNo);
			newOrderGoods.setQuantity(submitOrderPO.getQuantity());
			GoodsRecordDB goodsRecordDB = new GoodsRecordDB();
			goodsRecordDB.setTitle(dbGoodsNew.getTitle());
			goodsRecordDB.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			goodsRecordDB.setPriceSales(dbGoodsNewSku.getPriceSales());
			goodsRecordDB.setRecordType(dbGoodsNewSku.getRecordType());
			goodsRecordDB.setPriceStore(dbGoodsNewSku.getPriceStore());
			goodsRecordDB.setQuantity(submitOrderPO.getQuantity());
			goodsRecordDB.setSkuNo(dbGoodsNewSku.getSkuNo());
			newOrderGoods.setSalesPrice(dbGoodsNewSku.getPriceSales());
			newOrderGoods.setPriceStore(dbGoodsNewSku.getPriceStore());
			newOrderGoods.setGoodsRecord(JSONObject.toJSONString(goodsRecordDB));
			newOrderGoods.setCreateTime(now);
			newOrderGoods.setUpdateTime(now);
			daikenOrderGoodsDao.saveSelective(newOrderGoods);
			idShop = dbGoodsNew.getShopId();
			shopName = dbShop.getShopName();
			quantity = quantity + submitOrderPO.getQuantity();
			pricePay = pricePay + (submitOrderPO.getQuantity() * dbGoodsNewSku.getPriceSales());
			postage = postage + dbGoodsNew.getPostage();
			goodsTitles += dbGoodsNew.getTitle() + ",";
		}
		// 二手商品购买
		if (!submitOrderPO.getType()) {
			newOrder.setIsNewGoods(false);
			// 【乐观锁】
			Integer goodsId = Integer.valueOf(submitOrderPO.getSkuNo());
			DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(goodsId);
			boolean flag = false;
			for (int i = 0; i < 3; i++) {
				Integer happyLock = daikenGoodsOldDao.updateByHappyLock(dbGoodsOld.getId(),
						dbGoodsOld.getDateVersion());

				if (happyLock > 0) {
					flag = true;
					break;
				}
				Thread.sleep(1000);
				// 重新获取版本，尝试再次操作
				dbGoodsOld = daikenGoodsOldDao.selectById(goodsId);
			}
			// 数据版本已经增加，操作失败
			if (!flag) {
				result.setCode(KemeanResultEnum.FAILURE.getCode());
				result.setInfo(DaikenApiResultTips.Order.ORDER_FAILED);
				result.setSuccess(false);
				return result;
			}
			DaikenOrderGoods newOrderGoods = new DaikenOrderGoods();
			newOrderGoods.setOrderNo(orderNo);
			newOrderGoods.setGoodsId(goodsId);
			newOrderGoods.setGoodsUid(dbGoodsOld.getGoodsUid());
			newOrderGoods.setRedBefore(dbGoodsOld.getRedBefore());
			newOrderGoods.setRedAfter(dbGoodsOld.getRedAfter());
			newOrderGoods.setPricePurchasing(dbGoodsOld.getPricePurchasing());
			newOrderGoods.setSkuNo("二手商品 【" + dbGoodsOld.getTitle() + "】");
			newOrderGoods.setQuantity(submitOrderPO.getQuantity());
			GoodsRecordDB orderGoodsRecordDB = new GoodsRecordDB();
			orderGoodsRecordDB.setTitle(dbGoodsOld.getTitle());
			orderGoodsRecordDB.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class).get(0));
			orderGoodsRecordDB.setPriceSales(dbGoodsOld.getPriceSales());
			newOrderGoods.setSalesPrice(dbGoodsOld.getPriceSales());
			newOrderGoods.setPriceStore(dbGoodsOld.getPriceOriginal());
			newOrderGoods.setGoodsRecord(JSONObject.toJSONString(orderGoodsRecordDB));
			newOrderGoods.setCreateTime(now);
			newOrderGoods.setUpdateTime(now);
			daikenOrderGoodsDao.saveSelective(newOrderGoods);
			goodsTitles += dbGoodsOld.getTitle() + ",";
			quantity = quantity + submitOrderPO.getQuantity();
			pricePay = pricePay + (submitOrderPO.getQuantity() * dbGoodsOld.getPriceSales());
		}
		newOrder.setUserName(daikenUser.getNickName());
		newOrder.setGoodsTitles(goodsTitles.substring(0, goodsTitles.length() - 1));
		newOrder.setIdShop(idShop);
		newOrder.setShopName(shopName);
		newOrder.setUidUser(daikenUser.getUid());
		Integer purchasing = 0;
		Integer purchasingUser = 0;
		if (submitOrderPO.getUserShopId() != 0) {
			purchasing = submitOrderPO.getUserShopId();
			DaikenUserShop dbUserShop = daikenUserShopDao.selectById(purchasing);
			purchasingUser = dbUserShop.getUserId();
		}
		newOrder.setIdPurchasing(purchasing);
		newOrder.setIdPurchasingUser(purchasingUser);

		newOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus());
		newOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus());
		newOrder.setUserPhone(daikenUser.getPhone());
		newOrder.setQuantity(quantity);
		// TODO 如果是测试版的话 支付0.01
		KemeanConfig config = commonService.getConfig(DaikenConfigEnum.PROJECT_VERSION);
		if (config.getRecord().equals("测试")) {
			pricePay = 0.01;
			postage = 0.0;
		}
		newOrder.setPricePay(pricePay + postage);
		newOrder.setPriceDiscount(0.0);
		newOrder.setPriceTotal(pricePay + postage);
		newOrder.setPostage(postage);
		newOrder.setPayType(KemeanPayTypeEnum.NO_SET.getType());
		// 收货地址
		KemeanUserAddress dbUserAddress = kemeanUserAddressDao.selectById(submitOrderPO.getAddressId());
		UserAddressDB userAddressDB = new UserAddressDB();
		BeanUtils.copyProperties(dbUserAddress, userAddressDB);
		newOrder.setRecordReceiving(JSONObject.toJSONString(userAddressDB));
		if (submitOrderPO.getRemark() != null) {
			if (StringUtils.isNotBlank(submitOrderPO.getRemark())) {
				newOrder.setRemark(submitOrderPO.getRemark());
			}
		}
		newOrder.setCreateYearMonth(DaikenUtil.formatDate(now, KemeanDateFormatEnum.YEAR_MONTH));
		newOrder.setCreateTime(now);
		newOrder.setUpdateTime(now);
		daikenOrderDao.saveSelective(newOrder);
		bo.setOrderNo(orderNo);
		bo.setPrice(newOrder.getPricePay());
		bo.setOrderInfo(DaikenApiResultTips.Order.SUBMIT_ORDER_SUCCESS);
		return result;
	}

	public String getUserStatusStr(Integer userStatus) {
		String userStatusStr = "";
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus())) {
			userStatusStr = "待支付";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.CANCLE_NOT_PAY.getStatus())) {
			userStatusStr = "未支付取消 ";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.PAYED.getStatus())) {
			userStatusStr = "已支付";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.SHIP.getStatus())) {
			userStatusStr = "已发货";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus())) {
			userStatusStr = "待评价";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.FINISH.getStatus())) {
			userStatusStr = "已完成";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus())) {
			userStatusStr = "售后中";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus())) {
			userStatusStr = "待退款";
		}
		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus())) {
			userStatusStr = "已退款";
		}
//		if (userStatus.equals(KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus())) {
//			userStatusStr = "拒绝退款";
//		}
		return userStatusStr;
	}

	/**
	 * 订单删除
	 * 
	 * @author huwei
	 * @date 2018年7月5日
	 */
	public KemeanResult<String> delOrder(String orderNo) {
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		dbOrder.setDeletedUser(true);
		dbOrder.setUpdateTime(new Date());
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		return new KemeanResult<String>();
	}

	/**
	 * 支付回调（微信）
	 * 
	 * @author huangyujian
	 * @throws IOException
	 * @date 2018年4月8日
	 */
	public void payCallbackWx(HttpServletRequest request) throws IOException {
		Map<String, String> wxResultMap = WxPayUtil.inputStreamToMap(request.getInputStream());
		KemeanUtilAid.appLog.info("微信支付回调，参数：{}", JSONObject.toJSONString(wxResultMap));
		payCallbackOperate(wxResultMap.get("out_trade_no"), wxResultMap.get("transaction_id"),
				wxResultMap.get("time_end"), wxResultMap.get("result_code"));
	}

	/**
	 * 支付回调统一处理
	 * 
	 * @param orderNo
	 *            平台订单号
	 * @param thirdOrderNo
	 *            第三方订单号
	 * @param thirdNotifyTime
	 *            第三方通知时间
	 * @param thirdTradeStatus
	 *            第三方处理状态
	 * @author huangyujian
	 * @date 2018年4月8日
	 */
	@Transactional
	private synchronized void payCallbackOperate(String orderNo, String thirdOrderNo, String thirdNotifyTime,
			String thirdTradeStatus) {
		Date now = new Date();
		Integer idUser = 0;
		// 售后红包奖励的钱数
		Double money = 0.0;
		// 区分多个订单
		List<String> orderNos = new ArrayList<String>();
		if (orderNo.startsWith("g")) {
			// 多个订单
			List<DaikenOrder> dbOrders = daikenOrderDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNoGroup" }, new Object[] { false, orderNo });
			for (DaikenOrder dbOrder : dbOrders) {
				idUser = dbOrder.getIdUser();
				orderNos.add(dbOrder.getOrderNo());
			}
		}
		if (!orderNo.startsWith("g")) {
			// 单个订单
			DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
			idUser = dbOrder.getIdUser();
			orderNos.add(dbOrder.getOrderNo());
		}
		// 记录商铺id
		List<Integer> shopIds = new ArrayList<Integer>();
		for (String orderNum : orderNos) {
			DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNum);
			dbOrder.setThirdTradeNo(thirdOrderNo);
			dbOrder.setThirdNotifyTime(thirdNotifyTime);
			dbOrder.setThirdTradeStatus(thirdTradeStatus);
			dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.PAYED.getStatus());
			dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus());
			dbOrder.setPaymentTime(now);
			dbOrder.setUpdateTime(now);
			daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			// 调研订单
			if (!dbOrder.getIdInvestigate().equals(0)) {
				DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(dbOrder.getIdInvestigate());
				dbInvestigate.setPayStatus(true);
				dbInvestigate.setPayTime(now);
				dbInvestigate.setInvestigateStatus(true);
				dbInvestigate.setUpdateTime(now);
				daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
				// 生成订单流水
				commonService.createFinanceOrder(dbOrder.getOrderNo(),
						DaikenFinanceTypeEnum.C_LINE_PAY_INVESTIGATE.getType(),
						dbInvestigate.getMaxPeopleNum() * dbInvestigate.getRewardPrice(), dbOrder.getIdUser(), 0,
						"【发布调研】", "");
				continue;
			}
			DaikenOrderGoods dbOrderGood = daikenOrderGoodsDao.selectUniqueEntityByProperty("orderNo", orderNum);
			// 一手商品订单
			if (dbOrder.getIsNewGoods()) {
				Integer addShopVolume = 0;
				// 自卖店铺销量加
				DaikenShop dbShop = daikenShopDao.selectById(dbOrder.getIdShop());
				shopIds.add(dbShop.getId());
				// 一手商品id
				// 帮卖店铺销量加 主商品销量增加
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbOrderGood.getGoodsId());
				dbGoodsNew.setDateVersion(dbGoodsNew.getDateVersion() + 1);
				dbGoodsNew.setSalesNum(dbGoodsNew.getSalesNum() + dbOrderGood.getQuantity());
				dbGoodsNew.setNumShare(0);
				dbGoodsNew.setUpdateTime(now);
				daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
				// 规格商品销量增加
				DaikenGoodsNewSku dbGoodsNewSku = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "skuNo" },
						new Object[] { false, dbOrderGood.getSkuNo() });
				dbGoodsNewSku.setSalesNum(dbGoodsNewSku.getSalesNum() + dbOrderGood.getQuantity());
				dbGoodsNewSku.setUpdateTime(now);
				daikenGoodsNewSkuDao.updateByPrimaryKeySelective(dbGoodsNewSku);
				addShopVolume += dbOrderGood.getQuantity();
				money += dbGoodsNew.getRedAfter();
				dbShop.setSalesVolume(addShopVolume);
				dbShop.setUpdateTime(now);
				daikenShopDao.updateByPrimaryKeySelective(dbShop);
				if (!dbOrder.getIdPurchasing().equals(0)) {
					DaikenUserShop dbUserShop = daikenUserShopDao.selectById(dbOrder.getIdPurchasing());
					dbUserShop.setSalesVolume(addShopVolume);
					dbUserShop.setUpdateTime(now);
					daikenUserShopDao.updateByPrimaryKeySelective(dbUserShop);
				}
				// 根据id去除一手商品撸羊毛标志 去除商品撸羊毛标识
				daikenRedShareDao.updateEntityByProperties("relieveWool", false, new String[] { "type", "typeId" },
						new Object[] { DaikenShareTypeEnum.NEW_GOODS.getType(), dbGoodsNew.getId() });
				commonService.createFinanceOrder(orderNum, DaikenFinanceTypeEnum.C_LINE_PAY_BUY_GOODS.getType(),
						dbGoodsNewSku.getPriceSales() * dbOrderGood.getQuantity(), dbOrder.getIdUser(),
						dbOrder.getIdShop(), dbShop.getShopName(), "一手商品订单");
			}
			// 二手商品订单
			if (!dbOrder.getIsNewGoods()) {
				// 二手商品id
				Set<Integer> goodsIds = new HashSet<Integer>();
				DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(dbOrderGood.getGoodsId());
				if (dbGoodsOld.getGoodsId() != null && dbGoodsOld.getGoodsId() != 0) {
					dbGoodsOld = daikenGoodsOldDao.selectById(dbGoodsOld.getGoodsId());
				}
				goodsIds.add(dbGoodsOld.getId());
				dbGoodsOld.setIsBuy(true);
				dbGoodsOld.setUpdateTime(now);
				daikenGoodsOldDao.updateByPrimaryKeySelective(dbGoodsOld);
				money += dbGoodsOld.getRedAfter();
				// 将代卖商铺也改为已经卖出
				List<DaikenGoodsOld> dbGoodsOlds = daikenGoodsOldDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
						new Object[] { false, dbGoodsOld.getId() });
				if (CollectionUtils.isNotEmpty(dbGoodsOlds)) {
					for (DaikenGoodsOld dbGoodsOldH : dbGoodsOlds) {
						dbGoodsOldH.setIsBuy(true);
						dbGoodsOldH.setUpdateTime(now);
						daikenGoodsOldDao.updateByPrimaryKeySelective(dbGoodsOldH);
					}
				}

				Double financeMoney = dbGoodsOld.getPriceSales() * dbOrderGood.getQuantity();
				commonService.createFinanceOrder(orderNum, DaikenFinanceTypeEnum.C_LINE_PAY_BUY_GOODS.getType(),
						financeMoney, dbOrder.getIdUser(), dbOrder.getIdShop(), "", "二手商品订单（支出）");
				// 帮卖
				if (!dbOrder.getIdPurchasing().equals(0)) {
					DaikenUserShop dbUserShop = daikenUserShopDao.selectById(dbOrder.getIdPurchasing());
					if (dbUserShop.getSalesVolume() != null && dbUserShop.getSalesVolume() != 0) {
						dbUserShop.setSalesVolume(dbUserShop.getSalesVolume() + dbOrderGood.getQuantity());
					} else {
						dbUserShop.setSalesVolume(dbOrderGood.getQuantity());
					}
					dbUserShop.setUpdateTime(now);
					daikenUserShopDao.updateByPrimaryKeySelective(dbUserShop);
					financeMoney = financeMoney - dbGoodsOld.getPricePurchasing() * dbOrderGood.getQuantity();
					commonService.createFinanceOrder(orderNo, DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType(),
							dbGoodsOld.getPricePurchasing() * dbOrderGood.getQuantity(), dbUserShop.getUserId(), 0,
							dbUserShop.getShopName(), "二手帮卖收入");
				}
				commonService.createFinanceOrder(orderNum, DaikenFinanceTypeEnum.C_SELL_GOODS.getType(), financeMoney,
						dbOrder.getIdUser(), dbOrder.getIdShop(), "", "二手商品订单（收入）");
			}
		}
		// 用户去除分享次数
		DaikenUser dbUser = daikenUserDao.selectById(idUser);
		// 售后红包
		dbUser.setBalancePrice(dbUser.getBalancePrice() + money);
		if (dbUser.getWoolLabelNum() >= 3) {
			dbUser.setWoolLabelNum(0);
		}
		dbUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), dbUser.getToken()));
		// 极光推送
		String[] sendUserUids = new String[shopIds.size()];
		for (int i = 0; i < shopIds.size(); i++) {
			DaikenUser dbBusUser = daikenUserDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" },
					new Object[] { false, shopIds.get(i), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
			sendUserUids[i] = String.valueOf(dbBusUser.getUid());
		}
		pushJGService.notification(jgBase64, false, sendUserUids, "你有一份来自代研新的商品订单，请注意查收", "jump",
				DaikenPushJumType.ORDER.getType());
	}

	/**
	 * 查看售后
	 * 
	 * @author huwei
	 * @date 2018年7月12日
	 */
	public RefundCauseBO refundCause(String orderNo) {
		RefundCauseBO bo = new RefundCauseBO();
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		DaikenShop dbShop = daikenShopDao.selectById(dbOrder.getIdShop());
		BeanUtils.copyProperties(dbOrder, bo);
		bo.setIsSevenDay(false);
		bo.setShopName(dbShop.getShopName());
		DaikenUser dbShopUser = daikenUserDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" },
				new Object[] { false, dbShop.getId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
		bo.setShopUid(dbShopUser.getUid());
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
				// 购买商品数量
				DaikenOrderGoods dbOrderGoods = daikenOrderGoodsDao.selectUniqueEntityByProperties(
						new String[] { "orderNo", "goodsId" }, new Object[] { orderNo, dbOrderAfterSale.getGoodsId() });
				goodsBo.setQuantity(dbOrderGoods.getQuantity());
				goodsBo.setGoodsId(dbOrderAfterSale.getGoodsId());
				goodsBo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
				goodsBo.setRecordType(DaikenUtil.parseJsonArray(dbGoodsNewSku.getRecordType()));
				goodsBo.setCreateTimeStr(DaikenUtil.formatDate(dbOrderAfterSale.getCreateTime()));
				goodsBo.setRefundReason("");
				if (StringUtils.isNotBlank(dbOrderAfterSale.getRefundReason())) {
					goodsBo.setRefundReason(dbOrderAfterSale.getRefundReason());
				}
				goodsBo.setRefundImg(Arrays.asList());
				if (StringUtils.isNotBlank(dbOrderAfterSale.getRefundImg())) {
					goodsBo.setRefundImg(JSONArray.parseArray(dbOrderAfterSale.getRefundImg(), String.class));
				}
				if (dbOrderAfterSale.getGoodsStatus()) {
					goodsStatusStr = "已收到货物";
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
	 * 根据订单信息查找物品
	 * 
	 * @author huwei
	 * @date 2018年7月12日
	 */
	public KemeanResult<GetPaidOrderBO> getPaidOrder(GetPaidOrderPO getPaidOrderPO, DaikenUser daikenUser) {
		GetPaidOrderBO bo = new GetPaidOrderBO();
		if (daikenUser != null) {
			bo.setHeadImg(daikenUser.getHeadImg());
			bo.setNickName(daikenUser.getNickName());
		}
		Integer idUser = 0;
		// 购买商品数量
		Integer quantity = 0;
		// 支付价格
		Double pricePay = 0.0;
		// 购买物品信息
		List<GetPaidOrderGoodsBO> getPaidOrderGoodsBO = new ArrayList<GetPaidOrderGoodsBO>();

		for (String orderNo : getPaidOrderPO.getOrderNos()) {
			DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
			idUser = dbOrder.getIdUser();
			quantity += dbOrder.getQuantity();
			pricePay += dbOrder.getPricePay();
			// 一手商品
			List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
			for (DaikenOrderGoods daikenOrderGoods : dbOrderGoods) {
				GetPaidOrderGoodsBO goodsBO = new GetPaidOrderGoodsBO();
				GoodsRecordDB goodsRecordDB = JSONObject.parseObject(daikenOrderGoods.getGoodsRecord(),
						GoodsRecordDB.class);
				BeanUtils.copyProperties(goodsRecordDB, goodsBO);
				goodsBO.setQuantity(daikenOrderGoods.getQuantity());
				getPaidOrderGoodsBO.add(goodsBO);
			}
		}
		DaikenUser dbUser = daikenUserDao.selectById(idUser);
		bo.setHeadImg(dbUser.getHeadImg());
		bo.setNickName(dbUser.getNickName());
		bo.setGetPaidOrderGoodsBO(getPaidOrderGoodsBO);
		bo.setQuantity(quantity);
		bo.setPricePay(pricePay);
		return new KemeanResult<GetPaidOrderBO>(bo);
	}

	/**
	 * 确认收货
	 * 
	 * @author huwei
	 * @date 2018年7月13日
	 */
	@Transactional
	public KemeanResult<String> affirmTakeGoods(String orderNo) {
		Date now = new Date();
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
		dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus());
		dbOrder.setReceiveGoodsTime(now);
		dbOrder.setUpdateTime(now);
		daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		Double money = 0.0;
		Double afterRedMoney = 0.0;
		Double pricePurchasing = 0.0;
		List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		for (DaikenOrderGoods dbOrderGood : dbOrderGoods) {
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbOrderGood.getGoodsId());
			DaikenGoodsNewSku dbGoodsNewSkus = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "skuNo" },
					new Object[] { false, dbOrderGood.getSkuNo() });
			money += dbGoodsNewSkus.getPriceSales();
			afterRedMoney += dbGoodsNew.getRedAfter();
			pricePurchasing += dbGoodsNew.getPricePurchasing();
		}

		if (!dbOrder.getIdPurchasing().equals(0)) {
			money = money - pricePurchasing;
			DaikenUserShop dbUserShop = daikenUserShopDao.selectById(dbOrder.getIdPurchasing());
			// 生成帮卖订单流水 -- 客户端
			commonService.createFinanceOrder(orderNo, DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType(),
					pricePurchasing, dbOrder.getIdUser(), 0, dbUserShop.getShopName(), "一手订单帮卖货（收入）");
		}

		DaikenShop dbShop = daikenShopDao.selectById(dbOrder.getIdShop());

		// 生成订单流水信息 -- 商家端
		commonService.createFinanceOrder(orderNo, DaikenFinanceTypeEnum.B_SELL_GOODS.getType(), money - afterRedMoney,
				dbOrder.getIdUser(), dbOrder.getIdShop(), dbShop.getShopName(), "一手订单卖货（收入）");
		// 生成订单流水信息 -- 客户端
		commonService.createFinanceOrder(orderNo, DaikenFinanceTypeEnum.C_AFTER_RED_INCOME.getType(),
				money - afterRedMoney, dbOrder.getIdUser(), dbOrder.getIdShop(), dbShop.getShopName(), "一手订单售后红包（收入）");

		return new KemeanResult<String>();
	}

	/**
	 * 下单回调
	 * 
	 * @author huwei
	 * @date 2018年7月13日
	 */
	public KemeanResult<String> orderCallback(GetPaidOrderPO getPaidOrderPO) {
		Date now = new Date();
		String orderNoGroup = "";
		List<String> orderNos = getPaidOrderPO.getOrderNos();
		if (orderNos.size() >= 2) {
			orderNoGroup = "g" + KemeanUtilAid.getOrderNo(now);
			for (String orderNo : orderNos) {
				DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
				dbOrder.setOrderNoGroup(orderNoGroup);
				dbOrder.setPayReqTime(now);
				dbOrder.setUpdateTime(now);
				daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			}
		}
		if (orderNos.size() == 1) {
			orderNoGroup = getPaidOrderPO.getOrderNos().get(0);
		}
		payCallbackOperate(orderNoGroup, "", "", "");
		return new KemeanResult<String>();
	}

	/**
	 * 取消退款（售后）
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	@Transactional
	public KemeanResult<String> cancelAfterSale(CancelAfterSalePO cancelAfterSalePO) {
		String orderNo = cancelAfterSalePO.getOrderNo();
		DaikenOrderAfterSale dbOrderAfterSale = daikenOrderAfterSaleDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo", "goodsId" },
				new Object[] { false, orderNo, cancelAfterSalePO.getGoodsId() });
		if (dbOrderAfterSale != null) {
			dbOrderAfterSale.setDataDeleted(true);
			dbOrderAfterSale.setUpdateTime(new Date());
			daikenOrderAfterSaleDao.updateByPrimaryKeySelective(dbOrderAfterSale);
		}
		// 删除完了之后 如果没有退款的 订单改为正常状态
		List<DaikenOrderAfterSale> dbAfterSales = daikenOrderAfterSaleDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		if (CollectionUtils.isEmpty(dbAfterSales)) {
			DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
			dbOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
			dbOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.FINISH.getStatus());
			dbOrder.setUpdateTime(new Date());
			daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
		}
		return new KemeanResult<String>();
	}

	/**
	 * 支付完成，发送模板消息
	 * 
	 * @author huwei
	 * @date 2018年7月26日
	 */
	public KemeanResult<String> sendTemplate(SendTemplatePO sendTemplatePO, DaikenUser loginConsumer) {
		String orderNo = sendTemplatePO.getOrderNo();
		Double price = 0.0;
		String payTime = "";
		if (orderNo.startsWith("g")) {
			List<DaikenOrder> dbOrders = daikenOrderDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNoGroup" },
					new Object[] { false, sendTemplatePO.getOrderNo() });
			for (DaikenOrder dbOrder : dbOrders) {
				price += dbOrder.getPricePay();
				payTime = DaikenUtil.formatDate(dbOrder.getPaymentTime());
			}
		}
		if (!orderNo.startsWith("g")) {
			DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperty("orderNo", orderNo);
			price += dbOrder.getPricePay();
			payTime = DaikenUtil.formatDate(dbOrder.getPaymentTime());
		}
		String[] fillDate = new String[] { orderNo, String.valueOf(price), payTime };
		wxTemplateService.sendTemplate(loginConsumer.getWxOpenId(), sendTemplatePO.getPrepayId(), templateId, "",
				fillDate);
		return new KemeanResult<String>();
	}
}
