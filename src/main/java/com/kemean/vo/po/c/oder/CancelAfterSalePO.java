package com.kemean.vo.po.c.oder;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CancelAfterSalePO {

	@NotBlank(message = "订单编号不能为空")
	private String orderNo;

	@NotNull(message = "商品id不能为空")
	private Integer goodsId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

}
