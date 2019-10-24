package com.kemean.vo.bo.b.order;

public class OrderListGoodsBO {

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
	 * 销售价
	 */
	private Double priceSales;

	/**
	 * 购买数量
	 */
	private Integer quantity;

	/**
	 * 规格
	 */
	private String recordType;

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
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

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
