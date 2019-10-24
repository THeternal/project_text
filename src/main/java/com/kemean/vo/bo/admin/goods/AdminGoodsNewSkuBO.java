package com.kemean.vo.bo.admin.goods;

import java.util.List;

import com.kemean.vo.bo.KemeanIdBO;

public class AdminGoodsNewSkuBO extends KemeanIdBO {
	/**
	 * SKU编号
	 */
	private String skuNo;
	/**
	 * skuID
	 */
	private Integer goodsNewSkuId;

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
	 * 规格类型记录-json
	 */
	private String recordType;

	/**
	 * 规格类型记录-list
	 */
	private List<AdminGoodsSkuRecordBO> goodsSkuRecord;
	/**
	 * 库存
	 */
	private Integer stock;

	/**
	 * 冻结库存
	 */
	private Integer stockFreeze;

	/**
	 * 销售数量
	 */
	private Integer salesNum;

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public List<AdminGoodsSkuRecordBO> getGoodsSkuRecord() {
		return goodsSkuRecord;
	}

	public void setGoodsSkuRecord(List<AdminGoodsSkuRecordBO> goodsSkuRecord) {
		this.goodsSkuRecord = goodsSkuRecord;
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

	public Integer getGoodsNewSkuId() {
		return goodsNewSkuId;
	}

	public void setGoodsNewSkuId(Integer goodsNewSkuId) {
		this.goodsNewSkuId = goodsNewSkuId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStockFreeze() {
		return stockFreeze;
	}

	public void setStockFreeze(Integer stockFreeze) {
		this.stockFreeze = stockFreeze;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

}
