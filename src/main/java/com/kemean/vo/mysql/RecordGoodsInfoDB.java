package com.kemean.vo.mysql;

public class RecordGoodsInfoDB {

	/**
	 * 规格记录
	 */
	private String recordType;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 图片
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
	 * 支付价
	 */
	private Double priceSales;

	/**
	 * 邮费
	 */
	private Double postage;

	/**
	 * 商铺id
	 */
	private Integer shopId;

	/**
	 * 是否帮代卖
	 */
	private Boolean isPurchasing;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Boolean getIsPurchasing() {
		return isPurchasing;
	}

	public void setIsPurchasing(Boolean isPurchasing) {
		this.isPurchasing = isPurchasing;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
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

}
