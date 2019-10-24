package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenGoodsNew;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenGoodsNewDao extends Idao<DaikenGoodsNew> {

	/**
	 * 一手商品列表
	 * 
	 * @author huwei
	 * @date 2018年6月25日
	 */
	List<DaikenGoodsNew> newGoodsList(String descStr, Double minPriceSales, Double maxPriceSales,
			List<Integer> categoryIds, Boolean purchasing, String keyWord, Integer pageNo, Integer pageSize);

	/**
	 * 商品活动
	 * 
	 * @author huwei
	 * @date 2018年6月27日
	 */
	List<DaikenGoodsNew> goodsActivity(List<Integer> salesTypes, String descStr, Double minPriceSales,
			Double maxPriceSales, List<Integer> categoryIds, String keyWord, Integer pageNo, Integer pageSize);

	/**
	 * 推荐商品（个人中心、商品内页）
	 * 
	 * @author huwei
	 * @date 2018年6月29日
	 */
	List<DaikenGoodsNew> recommendGoods(Integer pageNo, Integer pageSize);

	/**
	 * 商家端商品列表
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	List<DaikenGoodsNew> goodsList(List<Integer> salesTypes, String title, Integer shopId, Integer pageNo,
			Integer pageSize);

	/**
	 * 获取商店全部商品信息
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	List<DaikenGoodsNew> goodsInfo(List<Integer> categoryIds, String title, Integer shopId, Integer pageNo,
			Integer pageSize);

	/**
	 * 搜索
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	List<DaikenGoodsNew> search(String searchInfo);

	/**
	 * 代卖商品
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<DaikenGoodsNew> selectGoodsPurchasing(Integer goodsId, String dateStart, String dateEnd, Integer pageNo,
			Integer pageSize);

	/**
	 * 商品数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsNum(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 商品下架数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsStatusNum(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 商品在售数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsSaleNum(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 商品成交数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsFinishNum(Integer shopId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 商品类型统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectGoodsCategoryCount(Integer shopId, String dateStart, String dateEnd);

	/**
	 * 帮卖商铺商品信息
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	List<DaikenGoodsNew> helpSellGoodsInfo(List<Integer> categoryIds, String title, Integer userShopId, Integer pageNo,
			Integer pageSize);

	/**
	 * 四个促销商品推广列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<DaikenGoodsNew> selectGoodsPromotionData(Integer type, Integer shopId, String goodsTitle, String dateStart,
			String dateEnd, Integer pageNo, Integer pageSize);

	/**
	 * 平台管理代客户上下架商品统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectAdminOperateGoodsCount(Integer userId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 统计商品的售后红包领取金额(订单完成，用户领取售后红包 )
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectRedAfterGetRecord(Integer statusUser, List<Integer> shopId, Integer goodsId,
			String dateStart, String dateEnd, Integer limit);

	/**
	 * 店铺代卖佣金统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectShopPurchasingPriceSum(List<Integer> goodsId, String dateStart, String dateEnd,
			Integer limit);

	/**
	 * 搜索 一手商品
	 * 
	 * @author huwei
	 * @date 2018年8月2日
	 */
	List<DaikenGoodsNew> search(String searchInfo, Integer pageNo, Integer pageSize, Boolean flag);

	/**
	 * 【管理后台】 商品审核列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月21日
	 */
	List<DaikenGoodsNew> goodsListData(Integer shopId, String title, Integer salesType, String goodsUid,
			Boolean marketing, List<Integer> auditStatus, Integer pageNo, Integer pageSize);

	/**
	 * 【管理后台】 查询精准推送的商品
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月21日
	 */
	List<DaikenGoodsNew> goodsPrecisionPushData(Integer shopId, String goodsUid, Integer pageNo, Integer pageSize);

}