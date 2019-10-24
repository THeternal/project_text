package com.kemean.dao;

import java.util.Date;
import java.util.List;

import com.kemean.bean.KemeanFinanceClear;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface KemeanFinanceClearDao extends Idao<KemeanFinanceClear> {

	/**
	 * 结算流水
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	List<KemeanFinanceClear> closeAccountList(Date plusDay, Integer shopId, Integer pageNo, Integer pageSize);

	/**
	 * 【管理后台】 财务流水
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	List<KemeanFinanceClear> shopFinanceOrderList(String financeNo, Integer financeStatus, Integer type,
			String dateStart, String dateEnd, List<Integer> shopId, Integer pageNo, Integer pageSize);

	/**
	 * 店铺充值金额、提现金额、到账、未到账统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopFinanceSum(List<Integer> financeType, Integer financeStatus,
			Integer submitAimsId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 店铺到账和未到账金额统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月19日
	 */
	Double shopMoneyCount(List<Integer> financeType, Integer status, Integer submitAimsId);

	/**
	 * 平台入账金额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	Double selectPlatformInMoney(Integer financeType, Integer financeStatus, String dateStart, String dateEnd);

	/**
	 * 平台支出金额统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	Double selectPlatformOutMoney(List<Integer> financeType, Boolean rate, Integer financeStatus, String dateStart,
			String dateEnd);

}
