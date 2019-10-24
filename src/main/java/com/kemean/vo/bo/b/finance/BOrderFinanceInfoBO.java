package com.kemean.vo.bo.b.finance;

public class BOrderFinanceInfoBO {

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 订单完成时间
	 */
	private String orderFinishTime;

	/**
	 * 订单价格
	 */
	private Double orderPrice;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderFinishTime() {
		return orderFinishTime;
	}

	public void setOrderFinishTime(String orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

}
