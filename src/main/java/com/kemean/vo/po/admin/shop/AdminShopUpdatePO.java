package com.kemean.vo.po.admin.shop;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class AdminShopUpdatePO {
	private Integer shopId;
	@NotBlank(message = "商铺logo不能为空")
	private String shopLogo;
	@NotBlank(message = "商铺名称不能为空")
	private String shopName;
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机格式不正确")
	private String shopPhone;
	private String password;

	private String presentation;

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

}
