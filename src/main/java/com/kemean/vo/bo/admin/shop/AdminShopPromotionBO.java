package com.kemean.vo.bo.admin.shop;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminShopPromotionBO extends KemeanIdBO {
	private String shopName;
	private String goodTitle;
	private Integer salesType;
	private Integer goodId;
	private String salesTypeStr;
	private Date discountTimeBegin;
	private Date discountTimeEnd;

	private String discountTimeBeginStr;
	private String discountTimeEndStr;

	// 数量
	private Integer quantity;
	private Double salesPrice;
	// 门市价
	private Double priceStore;
	// 库存
	private Integer stock;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getGoodTitle() {
		return goodTitle;
	}

	public void setGoodTitle(String goodTitle) {
		this.goodTitle = goodTitle;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSalesTypeStr() {
		return salesTypeStr;
	}

	public void setSalesTypeStr(String salesTypeStr) {
		this.salesTypeStr = salesTypeStr;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Date getDiscountTimeBegin() {
		return discountTimeBegin;
	}

	public void setDiscountTimeBegin(Date discountTimeBegin) {
		this.discountTimeBegin = discountTimeBegin;
	}

	public Date getDiscountTimeEnd() {
		return discountTimeEnd;
	}

	public void setDiscountTimeEnd(Date discountTimeEnd) {
		this.discountTimeEnd = discountTimeEnd;
	}

	public String getDiscountTimeBeginStr() {
		return discountTimeBeginStr;
	}

	public void setDiscountTimeBeginStr(String discountTimeBeginStr) {
		this.discountTimeBeginStr = discountTimeBeginStr;
	}

	public String getDiscountTimeEndStr() {
		return discountTimeEndStr;
	}

	public void setDiscountTimeEndStr(String discountTimeEndStr) {
		this.discountTimeEndStr = discountTimeEndStr;
	}

}
