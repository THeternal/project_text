package com.kemean.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.DaikenOrder;
import com.kemean.constant.DaikenOrderEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenOrderMapper;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.db.DateAndDateBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenOrderDaoImpl extends DaoSupport<DaikenOrder> implements DaikenOrderDao {

	@Autowired
	private DaikenOrderMapper mapper;

	@Override
	protected Mapper<DaikenOrder> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenOrder> orderListBusiness(String keyWord, String shopName, Integer shopId, Integer afterSaleStatus,
			Boolean purchasing, String recordReceiving, Integer orderStatus, String startDate, String endDate,
			Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("deletedShop", false);
		criteria.andEqualTo("idShop", shopId);
		// 模糊查询
		if (StringUtils.isNotBlank(keyWord)) {
			// 判断是订单编号还是商品名称
			if (DaikenUtil.isOrderNo(keyWord)) {
				// 订单编号
				criteria.andLike("orderNo", "%" + keyWord + "%");
			} else {
				// 商品名称
				criteria.andLike("goodsTitles", "%" + keyWord + "%");
			}
		}

		if (purchasing != null) {
			criteria.andGreaterThan("idPurchasing", 0);
		}
		//收货人的姓名和电话
		if (StringUtils.isNoneBlank(recordReceiving)) {
			criteria.andLike("recordReceiving", "%" + recordReceiving + "%");
		}
		if (StringUtils.isNoneBlank(shopName)) {
			criteria.andLike("shopName", "%" + shopName + "%");
		}

		if (StringUtils.isNoneBlank(startDate)) {
			criteria.andGreaterThanOrEqualTo("createTime", startDate);
		}
		if (StringUtils.isNoneBlank(endDate)) {
			criteria.andLessThanOrEqualTo("createTime", endDate);
		}

		if (orderStatus == null || orderStatus == 0) {
			criteria.andNotIn("statusShop", Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus(),
					KemeanOrderEnum.OrderStatusBusiness.CANCLE_NOT_PAY.getStatus()));
		}

		// 用于查询退货状态
		if (afterSaleStatus != null) {
			criteria.andEqualTo("statusShop", afterSaleStatus);
		}

		// 代发货
		if (KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus().equals(orderStatus)) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.PAYED.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus());
		}
		// 待收货
		if (KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus().equals(orderStatus)) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.SHIP.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus());
		}
		// 已完成
		if (KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus().equals(orderStatus)) {
			criteria.andIn("statusUser", Arrays.asList(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus(),
					KemeanOrderEnum.OrderStatusUser.FINISH.getStatus()));
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		}
		// 售后
		if (KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus().equals(orderStatus)) {
			criteria.andIn("statusUser",
					Arrays.asList(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus()));
			criteria.andIn("statusShop",
					Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus()));

		}

		// 售后未完成
		if (DaikenOrderEnum.AFTER_SALE_UNFINISH.getCode().equals(orderStatus)) {

			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus());
		}
		// 售后已完成
		if (DaikenOrderEnum.AFTER_SALE_FINISH.getCode().equals(orderStatus)) {
			criteria.andIn("statusShop",
					Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus()));
		}
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenOrder> orderListConsumer(Integer userId, String keyWord, Integer orderStatus, Integer pageNo,
			Integer pageSize) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("deletedUser", false);
		criteria.andEqualTo("idUser", userId);
		// 只找订单（不包扣调研订单）
		criteria.andEqualTo("idInvestigate", 0);
		// 待支付
		if (orderStatus.equals(KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus())) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus());
		}
		// 代发货
		if (orderStatus.equals(KemeanOrderEnum.OrderStatusUser.PAYED.getStatus())) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.PAYED.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus());
		}

		// 待收货
		if (orderStatus.equals(KemeanOrderEnum.OrderStatusUser.SHIP.getStatus())) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.SHIP.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus());
		}

		// 待评价
		if (orderStatus.equals(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus())) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		}

		// 待售后
		if (orderStatus.equals(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus())) {
			criteria.andIn("statusUser",
					Arrays.asList(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus()));
			criteria.andIn("statusShop", Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus(),
					KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus()));
		}
		// 全部订单 则不做处理
		// 模糊查询
		if (StringUtils.isNotBlank(keyWord)) {
			// 判断是订单编号还是商品名称
			if (DaikenUtil.isOrderNo(keyWord)) {
				// 订单编号
				criteria.andLike("orderNo", "%" + keyWord + "%");
			} else {
				// 商品名称
				criteria.andLike("goodsTitles", "%" + keyWord + "%");
			}
		}
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectUserSaleMoney(List<Integer> statusUsers, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectUserSaleMoney(statusUsers, dateStart, dateEnd, limit);
	}

	@Override
	public List<DaikenOrder> orderListData(String orderNo, String shopName, Integer orderStatus, Integer pageNo,
			Integer pageSize) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("deletedShop", false);
		criteria.andGreaterThanOrEqualTo("idShop", 0);
		if (StringUtils.isNoneBlank(shopName)) {
			criteria.andEqualTo("shopName", shopName);
		}

		if (StringUtils.isNoneBlank(orderNo)) {
			criteria.andEqualTo("orderNo", orderNo);
		}
		criteria.andEqualTo("statusUser", orderStatus);
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DateAndDateBO> selectUserOrderNum(String dateStart, String dateEnd, Integer limit) {
		return mapper.selectUserOrderNum(dateStart, dateEnd, limit);
	}

	@Override
	public Integer countNoAfterSaleOrder(Integer userId) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("idUser", userId);
		criteria.andEqualTo("idInvestigate", 0);
		criteria.andIn("statusUser",
				Arrays.asList(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus(),
						KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus(),
						KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus()));
		return mapper.selectByExample(example).size();
	}

	@Override
	public List<DaikenOrder> orderPriceFlowList(Date plusDay, Integer shopId, Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("deletedShop", false);
		criteria.andEqualTo("idShop", shopId);
		criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		criteria.andIn("statusUser", Arrays.asList(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus(),
				KemeanOrderEnum.OrderStatusUser.FINISH.getStatus()));
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectShopSalesNum(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectShopSalesNum(shopId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectShopSalesMoney(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectShopSalesMoney(shopId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectGoodsFinishNum(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectGoodsFinishNum(shopId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectInvestUserPayMoney(List<Integer> userIds, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectInvestUserPayMoney(userIds, dateStart, dateEnd, limit);
	}

	@Override
	public List<DateAndDateBO> selectOrderChart(String dateStart, String dateEnd, Integer limit) {
		return mapper.selectOrderChart(dateStart, dateEnd, limit);
	}

	@Override
	public List<DateAndDateBO> selectPlatformSaleMoneyCount(List<Integer> statusUsers, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectPlatformSaleMoneyCount(statusUsers, dateStart, dateEnd, limit);
	}

	@Override
	public List<DaikenOrder> shopOrderList(String orderNo, Integer shopId, Integer orderStatus, String startDate,
			String endDate, Integer page, Integer limit) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("deletedShop", false);
		criteria.andEqualTo("idShop", shopId);

		if (StringUtils.isNotBlank(orderNo)) {
			criteria.andLike("orderNo", "%" + orderNo + "%");
		}
		if (StringUtils.isNoneBlank(startDate)) {
			criteria.andGreaterThanOrEqualTo("createTime", startDate);
		}
		if (StringUtils.isNoneBlank(endDate)) {
			criteria.andLessThanOrEqualTo("createTime", endDate);
		}
		if (orderStatus == null || orderStatus == 0) {
			criteria.andNotEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus());
			criteria.andNotEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.CANCLE_NOT_PAY.getStatus());
		}
		// 查询待发货和已发货
		if (DaikenOrderEnum.ACCEPT_SHIP.getCode().equals(orderStatus)) {
			criteria.andIn("statusUser", Arrays.asList(KemeanOrderEnum.OrderStatusUser.PAYED.getStatus(),
					KemeanOrderEnum.OrderStatusUser.SHIP.getStatus()));
			criteria.andIn("statusShop", Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus(),
					KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus()));
		}
		// 待发货
		if (KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus().equals(orderStatus)) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.PAYED.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus());
		}
		// 待收货
		if (KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus().equals(orderStatus)) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.SHIP.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus());
		}
		// 已完成
		if (KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus().equals(orderStatus)) {
			criteria.andIn("statusUser", Arrays.asList(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus(),
					KemeanOrderEnum.OrderStatusUser.FINISH.getStatus()));
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		}
		// 售后
		if (KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus().equals(orderStatus)) {
			criteria.andIn("statusUser",
					Arrays.asList(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus()));
			criteria.andIn("statusShop",
					Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus()));

		}

		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, limit);
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectShopOrderProfit(Integer shopId, Integer limit) {
		return mapper.selectShopOrderProfit(shopId, limit);
	}

	@Override
	public List<String> selectGoodsMarketingByOrderNo(Integer shopId, List<Integer> userId, Boolean finish) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("idShop", shopId);
		criteria.andNotIn("statusShop", Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus(),
				KemeanOrderEnum.OrderStatusBusiness.CANCLE_NOT_PAY.getStatus()));
		if (!userId.isEmpty()) {
			criteria.andIn("idUser", userId);
		}
		if (finish) {
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		}
		List<DaikenOrder> selectByExample = mapper.selectByExample(example);
		List<String> orderNo = new ArrayList<>(selectByExample.size());
		for (DaikenOrder daikenOrder : selectByExample) {
			orderNo.add(daikenOrder.getOrderNo());
		}
		return orderNo;
	}

	@Override
	public List<DaikenOrder> exportOrderList(String orderNo, Integer shopId, Integer orderStatus, String startDate,
			String endDate) {
		Example example = new Example(DaikenOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("deletedShop", false);
		criteria.andEqualTo("idShop", shopId);

		if (StringUtils.isNotBlank(orderNo)) {
			criteria.andLike("orderNo", "%" + orderNo + "%");
		}
		if (StringUtils.isNoneBlank(startDate)) {
			criteria.andGreaterThanOrEqualTo("createTime", startDate);
		}
		if (StringUtils.isNoneBlank(endDate)) {
			criteria.andLessThanOrEqualTo("createTime", endDate);
		}
		if (orderStatus == null || orderStatus == 0) {
			criteria.andNotEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus());
			criteria.andNotEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.CANCLE_NOT_PAY.getStatus());
		}
		// 查询待发货和已发货
		if (DaikenOrderEnum.ACCEPT_SHIP.getCode().equals(orderStatus)) {
			criteria.andIn("statusUser", Arrays.asList(KemeanOrderEnum.OrderStatusUser.PAYED.getStatus(),
					KemeanOrderEnum.OrderStatusUser.SHIP.getStatus()));
			criteria.andIn("statusShop", Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus(),
					KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus()));
		}
		// 待发货
		if (KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus().equals(orderStatus)) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.PAYED.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.ACCEPT.getStatus());
		}
		// 待收货
		if (KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus().equals(orderStatus)) {
			criteria.andEqualTo("statusUser", KemeanOrderEnum.OrderStatusUser.SHIP.getStatus());
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.SHIP.getStatus());
		}
		// 已完成
		if (KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus().equals(orderStatus)) {
			criteria.andIn("statusUser", Arrays.asList(KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus(),
					KemeanOrderEnum.OrderStatusUser.FINISH.getStatus()));
			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.FINISH.getStatus());
		}
		// 售后
		if (KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus().equals(orderStatus)) {
			criteria.andIn("statusUser",
					Arrays.asList(KemeanOrderEnum.OrderStatusUser.AFTER_SALES.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_WAIT.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusUser.REFUND_RE.getStatus()));
			criteria.andIn("statusShop",
					Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus()));

		}
		// 售后未完成
		if (DaikenOrderEnum.AFTER_SALE_UNFINISH.getCode().equals(orderStatus)) {

			criteria.andEqualTo("statusShop", KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE.getStatus());
		}
		// 售后已完成
		if (DaikenOrderEnum.AFTER_SALE_FINISH.getCode().equals(orderStatus)) {
			criteria.andIn("statusShop",
					Arrays.asList(KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_REFUND_ED.getStatus(),
							KemeanOrderEnum.OrderStatusBusiness.AFTER_SALE_.getStatus()));
		}
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}

}
