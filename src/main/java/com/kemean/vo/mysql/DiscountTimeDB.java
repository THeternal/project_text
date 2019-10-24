package com.kemean.vo.mysql;

public class DiscountTimeDB {
	/**
	 * 折扣开始时间
	 */
	private String discountTimeBegin;

	/**
	 * 折扣结束时间
	 */
	private String discountTimeEnd;

	public DiscountTimeDB() {
		super();
	}

	public DiscountTimeDB(String discountTimeBegin, String discountTimeEnd) {
		super();
		this.discountTimeBegin = discountTimeBegin;
		this.discountTimeEnd = discountTimeEnd;
	}

	public String getDiscountTimeBegin() {
		return discountTimeBegin;
	}

	public void setDiscountTimeBegin(String discountTimeBegin) {
		this.discountTimeBegin = discountTimeBegin;
	}

	public String getDiscountTimeEnd() {
		return discountTimeEnd;
	}

	public void setDiscountTimeEnd(String discountTimeEnd) {
		this.discountTimeEnd = discountTimeEnd;
	}

}
