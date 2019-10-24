package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenRedShareGet;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenRedShareGetMapper extends TkMapper<DaikenRedShareGet> {

	/**
	 * 商品红包领取金额(适用于单个店铺和总店铺)
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectRedShareGetRecord(@Param("goodsId") Integer goodsId, @Param("shopId") List<Integer> shopId,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

}
