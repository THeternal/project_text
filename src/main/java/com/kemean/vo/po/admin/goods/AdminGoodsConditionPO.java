package com.kemean.vo.po.admin.goods;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminGoodsConditionPO extends KemeanPageAdminPO {
	private String userAge;
	private String cityId;
	private Boolean userSex;
	

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Boolean getUserSex() {
		return userSex;
	}

	public void setUserSex(Boolean userSex) {
		this.userSex = userSex;
	}

}
