package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenOrderAfterSale;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenOrderAfterSaleMapper extends TkMapper<DaikenOrderAfterSale> {
	/**
	 * 商品退货数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectGoodsReturnNum(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);
	
	/**
	 * 获取售后订单补偿总金额
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	Double selectOrderCompensateTotal(@Param("shopId") Integer shopId);
}