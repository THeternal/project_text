package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenOrderGoods;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsMarketingBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsSaleTotalBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;

public interface DaikenOrderGoodsDao extends Idao<DaikenOrderGoods> {

	/**
	 * 店铺促销活动活动周期对应的销售额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminShopPromotionBO> selectGoodsDiscountPriceSum(Integer shopId, String shopName, String goodsTitle,
			Integer salesType, String dateStart, String dateEnd, Integer pageNo, Integer limit);

	/**
	 * 商品的销售数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodInSaleNum(Integer shopId, Integer goodsId, String dateStart, String dateEnd,
			Boolean purchasing, Integer limit);

	/**
	 * 商品销售额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodInSalePrice(Integer shopId, Integer goodsId, String dateStart, String dateEnd,
			Boolean purchasing, Integer limit);

	/**
	 * 代卖商品的销售额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	Double goodsSalePrice(Integer goodsId);

	/**
	 * 商家订单管理列表商品信息
	 * 
	 * @author linjinzhan
	 * @date 2018年8月17日
	 */
	List<DaikenOrderGoods> shopOrderGoods(String orderNo, String goodsUid, String skuNo);

	/**
	 * 店铺参与营销活动的销量、销售额、访问量、成交量
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月24日
	 */
	AdminGoodsMarketingBO selectGoodsMarkting(List<String> orderNo);

	/**
	 * 【管理后台】总销售收入
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	List<AdminGoodsSaleTotalBO> selectGoodsSaleTotalData(Integer shopId, String dateStart, String dateEnd,
			Integer pageNo, Integer limit);
}