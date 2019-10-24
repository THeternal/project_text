package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenShop;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenShopMapper extends TkMapper<DaikenShop> {

	/**
	 * 店铺数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopCount(@Param("settledType") Boolean settledType,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

}