package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenGoodsNew;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenGoodsNewMapper extends TkMapper<DaikenGoodsNew> {

	/**
	 * 商品数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsNum(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 商品下架数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsStatusNum(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 商品在售数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsSaleNum(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 商品类型统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsCategoryCount(@Param("shopId") Integer shopId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd);

	/**
	 * 平台管理代客户上下架商品统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectAdminOperateGoodsCount(@Param("userId") Integer userId,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 统计商品的售后红包领取金额(订单完成，用户领取售后红包 )
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectRedAfterGetRecord(@Param("statusUser") Integer statusUser,
			@Param("shopId") List<Integer> shopId, @Param("goodsId") Integer goodsId,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 店铺代卖佣金统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectShopPurchasingPriceSum(@Param("goodsId") List<Integer> goodsId,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

}