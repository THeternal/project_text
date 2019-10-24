package com.kemean.vo.bo.c.mall;

import com.alibaba.fastjson.JSONObject;

public class SkuNoBO {

	/**
	 * sku编号
	 */
	private String skuNo;

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

	/**
	 * 销售量
	 */
	private Integer salesNum;

	/**
	 * 规格类型记录
	 */
	private JSONObject recordType;

	public JSONObject getRecordType() {
		return recordType;
	}

	public void setRecordType(JSONObject recordType) {
		this.recordType = recordType;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
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

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

}
