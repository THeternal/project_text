package com.kemean.vo.bo.b.goods;

import java.util.List;

import com.kemean.vo.bo.KemeanIdBO;

public class GoodsSkusBO extends KemeanIdBO {

	/**
	 * 规格类型记录-json
	 */
	private List<RecordTypeBO> recordTypeBO;

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
	 * 库存
	 */
	private Integer stock;

	public List<RecordTypeBO> getRecordTypeBO() {
		return recordTypeBO;
	}

	public void setRecordTypeBO(List<RecordTypeBO> recordTypeBO) {
		this.recordTypeBO = recordTypeBO;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
