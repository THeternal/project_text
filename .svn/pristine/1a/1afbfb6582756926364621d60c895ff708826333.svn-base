package com.kemean.dao;

import java.util.Date;
import java.util.List;

import com.kemean.bean.DaikenOrder;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.db.DateAndDateBO;

public interface DaikenOrderDao extends Idao<DaikenOrder> {

	/**
	 * 根据状态找订单
	 * 
	 * @author huwei
	 * @date 2018年6月20日
	 */
	List<DaikenOrder> orderListBusiness(String keyWord, String shopName, Integer shopId, Integer afterSaleStatus,
			Boolean purchasing, String recordReceiving, Integer orderStatus, String startDate, String endDate,
			Integer pageNo, Integer pageSize);

	/**
	 * 用户订单列表（待付款，代发货，待收货，待评价）
	 * 
	 * @author huwei
	 * @date 2018年6月28日
	 */
	List<DaikenOrder> orderListConsumer(Integer userId, String keyword, Integer orderStatus, Integer pageNo,
			Integer pageSize);

	/**
	 * 订单列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	List<DaikenOrder> orderListData(String orderNo, String shopName, Integer orderStatus, Integer pageNo,
			Integer pageSize);

	/**
	 * 用户消费统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectUserSaleMoney(List<Integer> statusUsers, String dateStart, String dateEnd, Integer limit);

	/**
	 * 用户下单量分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<DateAndDateBO> selectUserOrderNum(String dateStart, String dateEnd, Integer limit);

	/**
	 * 待售后订单
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	Integer countNoAfterSaleOrder(Integer userId);

	/**
	 * 商家端 -- 订单流水
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	List<DaikenOrder> orderPriceFlowList(Date plusDay, Integer shopId, Integer pageNo, Integer pageSize);

	/**
	 * 店铺销售额统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopSalesMoney(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 店铺商品成交数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectGoodsFinishNum(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 店铺销售量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopSalesNum(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 调研客户消费统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectInvestUserPayMoney(List<Integer> userIds, String dateStart, String dateEnd, Integer limit);

	/**
	 * 用户日下单金额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	List<DateAndDateBO> selectOrderChart(String dateStart, String dateEnd, Integer limit);

	/**
	 * 平台总销售额统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<DateAndDateBO> selectPlatformSaleMoneyCount(List<Integer> statusUsers, String dateStart, String dateEnd,
			Integer limit);

	/**
	 * 商家订单管理列表
	 * 
	 * @author linjinzhan
	 * @date 2018年8月16日
	 */
	List<DaikenOrder> shopOrderList(String orderNo, Integer shopId, Integer orderStatus, String startDate,
			String endDate, Integer page, Integer limit);

	/**
	 * 店铺销售利润
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopOrderProfit(Integer shopId, Integer limit);

	/**
	 * 店铺参与营销活动的订单
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月24日
	 */
	List<String> selectGoodsMarketingByOrderNo(Integer shopId, List<Integer> userId, Boolean finish);

	/**
	 * 订单列表导出
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	List<DaikenOrder> exportOrderList(String orderNo, Integer shopId, Integer orderStatus, String startDate,
			String endDate);

}