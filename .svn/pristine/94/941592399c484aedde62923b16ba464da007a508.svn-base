package com.kemean.vo.po.c.oder;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class SubmitOrderPO {

	/**
	 * 如果是商铺自卖就传0
	 */
	@NotNull(message = "代卖商铺id不能为空")
	private Integer userShopId;

	/**
	 * 区分一手(true)、二手(false)商品
	 */
	@NotNull(message = "类型不能为空")
	private Boolean type;

	@NotBlank(message = "购买商品SkuNo不能为空")
	private String skuNo;

	@NotNull(message = "购买数量不能为空")
	private Integer quantity;

	@NotNull(message = "收货地址不能为空")
	private Integer addressId;

	private String remark;

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
