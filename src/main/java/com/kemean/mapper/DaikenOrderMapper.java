package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenOrder;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.db.DateAndDateBO;

public interface DaikenOrderMapper extends TkMapper<DaikenOrder> {

	/**
	 * 用户消费统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectUserSaleMoney(@Param("statusUsers") List<Integer> statusUsers,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 用户下单量分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<DateAndDateBO> selectUserOrderNum(@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd,
			@Param("limit") Integer limit);

	/**
	 * 店铺销售额统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopSalesMoney(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 平台总销售额统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<DateAndDateBO> selectPlatformSaleMoneyCount(@Param("statusUsers") List<Integer> statusUsers,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 店铺商品成交数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectGoodsFinishNum(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 店铺销售量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopSalesNum(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 调研客户消费统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectInvestUserPayMoney(@Param("userIds") List<Integer> userIds,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 用户日下单金额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	List<DateAndDateBO> selectOrderChart(@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd,
			@Param("limit") Integer limit);

	/**
	 * 店铺销售利润
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopOrderProfit(@Param("shopId") Integer shopId, @Param("limit") Integer limit);

}