package com.kemean.constant;

/**
 * 操作类型
 * 
 * @Date 2018年5月9日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class DaikenOperationTypeExplain {

	/** 删除收货地址 **/
	public static Integer c_delAddress = 1101;

	/** 设置默认地址 **/
	public static Integer c_setDefAddress = 1102;

	/** 点赞（赞） **/
	public static Integer c_good = 1101;

	/** 点赞（一般） **/
	public static Integer c_common = 1102;

	/** 点赞（踩） **/
	public static Integer c_bad = 1103;

	/** 店铺收藏 **/
	public static Integer c_shopCollect = 1101;
	
	/** 帮卖店铺收藏  **/
	public static Integer c_helpSellShopCollect = 1102;

	/** 一手商品收藏 **/
	public static Integer c_newGoodsCollect = 1201;

	/** 二手商品收藏 **/
	public static Integer c_secondHandGoodsCollect = 1301;

	/** 收藏列表取消收藏 **/
	public static Integer c_cancelCollect = 1401;

	/** 店铺取消收藏 **/
	public static Integer c_shopCancelCollect = 1501;

	/** 一手商品取消收藏 **/
	public static Integer c_newGoodsCancelCollect = 1601;

	/** 二手商品取消收藏 **/
	public static Integer c_oldGoodsCancelCollect = 1701;

	/** 添加帮卖商品 **/
	public static Integer c_addSellGoods = 1101;

	/** 结束帮卖商品 **/
	public static Integer c_overSellGoods = 1201;

	/** 商家删除商品 **/
	public static Integer b_delGoods = 1101;

	/** 商家上，下架商品 **/
	public static Integer b_soldOutGoods = 1201;

	/** 客户端删除调研 **/
	public static Integer c_delInvestigate = 1101;

	/** 客户端上下架调研 **/
	public static Integer c_soldOutInvestigate = 1201;

	/** 添加购物车一手商品 **/
	public static Integer c_goodsCatNewGoods = 1101;

	/** 添加购物车二手商品 **/
	public static Integer c_goodsCarSecondGoods = 1201;

	/** 上下二手商品 **/
	public static Integer c_soldOutldGoods = 1101;

	/** 删除二手商品 **/
	public static Integer c_delOldGoods = 1102;
}
