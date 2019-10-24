package com.kemean.vo.bo.c.order;

public class GetPaidOrderGoodsBO {

	/**
	 * 商品图片
	 */
	private String headImg;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 销售价
	 */
	private Double priceSales;

	/**
	 * 门市价
	 */
	private Double priceStore;

	/**
	 * 数量
	 */
	private Integer quantity;

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

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
