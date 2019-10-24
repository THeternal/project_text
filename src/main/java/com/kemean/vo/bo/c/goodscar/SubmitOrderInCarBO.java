package com.kemean.vo.bo.c.goodscar;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SubmitOrderInCarBO {

	private String orderNo;

	private Double price;

	private String orderInfo;

	@JsonFormat(pattern = "mm:ss", timezone = "GMT+8")
	private Date payReqTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Date getPayReqTime() {
		return payReqTime;
	}

	public void setPayReqTime(Date payReqTime) {
		this.payReqTime = payReqTime;
	}

}
