package com.kemean.vo.po.b.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ConfirmRefundPO {

	@NotNull(message = "操作类型不能为空")
	private Boolean flag;

	@NotBlank(message = "订单编号不能为空")
	private String orderNo;

	@NotNull(message = "商品id不能为空")
	private Integer goodsId;

	@NotBlank(message = "商品规格号不能为空")
	private String skuNo;

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

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

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

}
