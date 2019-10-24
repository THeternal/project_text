package com.kemean.vo.bo.admin.goods;

public class AdminGoodsSaleTotalBO {
	private String totalDate;

	/**
	 * 售前红包
	 */
	private Double redBefore;

	/**
	 * 售后红包
	 */
	private Double redAfter;
	/**
	 * 代卖成本：代卖提成
	 */
	private Double pricePurchasing;

	/**
	 * 补偿金额
	 */
	private Double compensateMoney;

	/**
	 * 实际收入
	 */
	private Double pricePay;

	/**
	 * 销售额
	 */
	private Double salesPrice;

	public String getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(String totalDate) {
		this.totalDate = totalDate;
	}

	public Double getRedBefore() {
		return redBefore;
	}

	public void setRedBefore(Double redBefore) {
		this.redBefore = redBefore;
	}

	public Double getRedAfter() {
		return redAfter;
	}

	public void setRedAfter(Double redAfter) {
		this.redAfter = redAfter;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public Double getCompensateMoney() {
		return compensateMoney;
	}

	public void setCompensateMoney(Double compensateMoney) {
		this.compensateMoney = compensateMoney;
	}

	public Double getPricePay() {
		return pricePay;
	}

	public void setPricePay(Double pricePay) {
		this.pricePay = pricePay;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

}
