package com.kemean.vo.po.admin.sales;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminSalesUserPO extends KemeanPageAdminPO {
	private String uid;
	private String phone;
	private Integer level;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
