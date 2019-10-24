package com.kemean.vo.po.admin.investigate;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminInvestPO extends KemeanPageAdminPO {

	private Integer type;
	private String title;
	private Integer userId;
	private Integer investId;
	private String dateTimeS;
	private String dateTimeE;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDateTimeS() {
		return dateTimeS;
	}

	public void setDateTimeS(String dateTimeS) {
		this.dateTimeS = dateTimeS;
	}

	public String getDateTimeE() {
		return dateTimeE;
	}

	public void setDateTimeE(String dateTimeE) {
		this.dateTimeE = dateTimeE;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInvestId() {
		return investId;
	}

	public void setInvestId(Integer investId) {
		this.investId = investId;
	}

}
