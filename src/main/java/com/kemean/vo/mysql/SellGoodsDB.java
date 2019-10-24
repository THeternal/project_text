package com.kemean.vo.mysql;

public class SellGoodsDB {

	/**
	 * 商品
	 */
	private Integer goodsId;

	/**
	 * 商品图片
	 */
	private String HeadImg;

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

	public String getHeadImg() {
		return HeadImg;
	}

	public void setHeadImg(String headImg) {
		HeadImg = headImg;
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
