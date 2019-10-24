package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenOrderClearMapper {

	/**
	 * 用户收入统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectUserOrderCount(@Param("financeTypes") List<Integer> financeTypes,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 店铺充值金额、提现金额、到账和未到账统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopFinanceSum(@Param("financeType") List<Integer> financeType,
			@Param("submitAimsId") List<Integer> submitAimsId, @Param("financeStatus") Integer financeStatus,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);
}
