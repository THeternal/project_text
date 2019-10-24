package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_goods_new_sku")
public class DaikenGoodsNewSku extends KemeanAbstractBaseBean {

	/**
	 * SKU编号
	 */
	@Column(name = "sku_no")
	private String skuNo;

	/**
	 * 商品关联id
	 */
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 * 门市价
	 */
	@Column(name = "price_store")
	private Double priceStore;

	/**
	 * 折扣
	 */
	@Column(name = "discount")
	private Float discount;

	/**
	 * 销售价
	 */
	@Column(name = "price_sales")
	private Double priceSales;

	/**
	 * 规格类型记录-json
	 */
	@Column(name = "record_type")
	private String recordType;

	/**
	 * 规格类型记录，用于后台复制操作-json
	 */
	@Column(name = "record_type_backstage_use")
	private String recordTypeBackstageUse;

	/**
	 * 库存
	 */
	@Column(name = "stock")
	private Integer stock;

	/**
	 * 冻结库存
	 */
	@Column(name = "stock_freeze")
	private Integer stockFreeze;

	/**
	 * 销售数量
	 */
	@Column(name = "sales_num")
	private Integer salesNum;

	/**
	 * 数据版本号
	 */
	@Column(name = "date_version")
	private Integer dateVersion;

	public String getRecordTypeBackstageUse() {
		return recordTypeBackstageUse;
	}

	public void setRecordTypeBackstageUse(String recordTypeBackstageUse) {
		this.recordTypeBackstageUse = recordTypeBackstageUse;
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

	public Integer getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(Integer dateVersion) {
		this.dateVersion = dateVersion;
	}

}