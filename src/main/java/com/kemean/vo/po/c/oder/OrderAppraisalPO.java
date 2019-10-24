package com.kemean.vo.po.c.oder;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class OrderAppraisalPO {

	@NotBlank(message = "订单编号不能为空")
	private String orderNo;

	private List<OrderAppraisalGoodsPO> appraisalGoodsPO;

	public List<OrderAppraisalGoodsPO> getAppraisalGoodsPO() {
		return appraisalGoodsPO;
	}

	public void setAppraisalGoodsPO(List<OrderAppraisalGoodsPO> appraisalGoodsPO) {
		this.appraisalGoodsPO = appraisalGoodsPO;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
