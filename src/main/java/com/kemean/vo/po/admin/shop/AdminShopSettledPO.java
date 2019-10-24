package com.kemean.vo.po.admin.shop;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminShopSettledPO extends KemeanPageAdminPO {
	private String phone;
	private String shopName;
	private Integer auditStatus;
	private Boolean settledPersonal;

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

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Boolean getSettledPersonal() {
		return settledPersonal;
	}

	public void setSettledPersonal(Boolean settledPersonal) {
		this.settledPersonal = settledPersonal;
	}

}
