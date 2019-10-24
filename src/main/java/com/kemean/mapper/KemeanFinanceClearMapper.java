package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.KemeanFinanceClear;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface KemeanFinanceClearMapper extends TkMapper<KemeanFinanceClear> {

	/**
	 * 店铺充值金额、提现金额、到账和未到账统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopFinanceSum(@Param("financeType") List<Integer> financeType,
			@Param("submitAimsId") Integer submitAimsId, @Param("financeStatus") Integer financeStatus,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

}