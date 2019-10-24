package com.kemean.vo.bo.admin.goods;

public class AdminGoodsNewMinPriceBO {

	/**
	 * 最低售价
	 */
	private Double minPriceSales;

	/**
	 * 最低售价的门市价
	 */
	private Double minPriceStore;

	/**
	 * 最低售价的折扣
	 */
	private Float minDiscount;

	public Double getMinPriceSales() {
		return minPriceSales;
	}

	public void setMinPriceSales(Double minPriceSales) {
		this.minPriceSales = minPriceSales;
	}

	public Double getMinPriceStore() {
		return minPriceStore;
	}

	public void setMinPriceStore(Double minPriceStore) {
		this.minPriceStore = minPriceStore;
	}

	public Float getMinDiscount() {
		return minDiscount;
	}

	public void setMinDiscount(Float minDiscount) {
		this.minDiscount = minDiscount;
	}

}
