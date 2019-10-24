package com.kemean.vo.po.b.order;

import javax.validation.constraints.NotNull;

public class SendGoodsPO {

	@NotNull(message = "订单编号不能为空")
	private String orderNo;

	@NotNull(message = "快递公司编号不能为空")
	private String companyNo;

	@NotNull(message = "快递单编号不能为空")
	private String expressWaybillNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getExpressWaybillNo() {
		return expressWaybillNo;
	}

	public void setExpressWaybillNo(String expressWaybillNo) {
		this.expressWaybillNo = expressWaybillNo;
	}

}
