package com.kemean.vo.bo.c.mall;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class NewGoodsListBO extends KemeanIdBO {

	/**
	 * 商品图片
	 */
	private String headImg;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 代卖
	 */
	private Boolean purchasing;

	/**
	 * 代卖提成
	 */
	private Double pricePurchasing;

	/**
	 * 门市价
	 */
	private Double priceStore;

	/**
	 * 支付价
	 */
	private Double priceSales;

	/**
	 * 商品售卖类型
	 */
	private String salesTypeStr;

	/**
	 * 帮代卖商铺id，如果是0表示不是帮代卖
	 */
	private Integer userShopId;

	/**
	 * 时间类型
	 */
	private Integer timeType;

	/**
	 * 活动开始时间
	 */
	@JsonFormat(pattern = "MM:dd HH:ss", timezone = "GMT+8")
	private Date discountTimeBegin;

	/**
	 * 活动结束时间
	 */
	@JsonFormat(pattern = "MM:dd HH:ss", timezone = "GMT+8")
	private Date discountTimeEnd;

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
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

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public String getSalesTypeStr() {
		return salesTypeStr;
	}

	public void setSalesTypeStr(String salesTypeStr) {
		this.salesTypeStr = salesTypeStr;
	}

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

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

}
