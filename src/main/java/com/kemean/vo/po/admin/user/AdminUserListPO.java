package com.kemean.vo.po.admin.user;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminUserListPO extends KemeanPageAdminPO {

	private String phone;
	private Integer userType;
	private String uid;
	private Integer goodsId;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

}
