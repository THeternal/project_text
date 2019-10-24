package com.kemean.vo.bo.c.goodscar;

import com.kemean.vo.bo.KemeanIdBO;

public class GoodscarInfoListBO extends KemeanIdBO {

	/**
	 * 区分一手、二手
	 */
	private Boolean isNewGoods;

	/**
	 * 商品id
	 */
	private Integer goodsId;

	/**
	 * SKU编号
	 */
	private String skuNo;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 封面轮播图-json
	 */
	private String headImg;

	/**
	 * 门市价
	 */
	private Double priceStore;

	/**
	 * 折扣
	 */
	private Float discount;

	/**
	 * 销售价
	 */
	private Double priceSales;

	/**
	 * 数量
	 */
	private Integer addQuantity;

	/**
	 * 商品规格
	 */
	private String recordType;

	/**
	 * 库存 = 库存 - 销售量
	 */
	private Integer stock;

	/**
	 * 商品运费
	 */
	private Double postage;

	/**
	 * 商品状态
	 */
	private Boolean goodsStatus;

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Boolean getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(Boolean isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public Integer getAddQuantity() {
		return addQuantity;
	}

	public void setAddQuantity(Integer addQuantity) {
		this.addQuantity = addQuantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

}
