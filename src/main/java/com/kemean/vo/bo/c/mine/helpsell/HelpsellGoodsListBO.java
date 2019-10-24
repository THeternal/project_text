package com.kemean.vo.bo.c.mine.helpsell;

import com.kemean.vo.bo.KemeanIdBO;

public class HelpsellGoodsListBO extends KemeanIdBO {

	/**
	 * 代卖商铺id
	 */
	private Integer userShopId;

	/**
	 * 商品id
	 */
	private Integer goodsId;

	/**
	 * 商品图片
	 */
	private String headImg;

	/**
	 * 商品标识，区分一手，二手 false-新品 true-二手
	 */
	private Boolean isOld;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 门市价
	 */
	private Double priceStore;

	/**
	 * 销售价
	 */
	private Double priceSales;

	/**
	 * 代卖提成
	 */
	private Double pricePurchasing;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

}
