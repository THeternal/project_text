package com.kemean.vo.po.admin.shop;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminShopPO extends KemeanPageAdminPO {
	private String phone;
	private String shopName;
	private Integer shopType;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

}
