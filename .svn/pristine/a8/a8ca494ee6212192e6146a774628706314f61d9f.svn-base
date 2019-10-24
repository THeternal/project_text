package com.kemean.dao;

import java.util.Date;
import java.util.List;

import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface KemeanFinanceOrderDaikenDao extends Idao<KemeanFinanceOrder> {

	/**
	 * 【客户端】钱包信息
	 * 
	 * @author huwei
	 * @date 2018年7月13日
	 */
	List<KemeanFinanceOrder> wallet(Integer userId, Date plusDay, Integer pageNo, Integer pageSize);

	/**
	 * 【商家端】 订单流水
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	List<KemeanFinanceOrder> financeOrderList(Date plusDay, Integer shopId, Integer pageNo, Integer pageSize);

	/**
	 * 【管理平台】用户流水账单
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	List<KemeanFinanceOrder> userFinanceOrderList(String orderNo, String userName, String dateStart, String dateEnd,
			Integer pageNo, Integer pageSize);

	/**
	 * 冻结资金
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	Double freezeMoney(Integer shopId, Date cycleDate);

	/**
	 * 可提现资金（未冻结资金）
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	Double getShopMoney(Integer shopId, Integer userId, Date cycleDate);

	/**
	 * 本月做任务收入
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	Double currMonthPrice(Integer userId, List<Integer> financeTypes, Boolean flag);

	/**
	 * 总资金
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	Double totalAssets(Integer shopId);

	/**
	 * 用户收入统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectUserOrderCount(List<Integer> financeTypes, String dateStart, String dateEnd,
			Integer limit);

	/**
	 * 获取冻结资金和余额（收入）
	 * 
	 * @author huwei
	 * @date 2018年9月17日
	 */
	Double getConsumerMoney(Integer userId, Boolean flag);

	/**
	 * 根据userId查找平台收费
	 * 
	 * @author huwei
	 * @date 2018年10月9日
	 */
	List<KemeanFinanceOrder> getPlatformChargeByUser(Integer userId);

	/**
	 * @author huwei
	 * @date 2018年10月9日
	 */
	List<KemeanFinanceOrder> getBusinessAdvertisingCostsByShop(Integer shopId);

}
