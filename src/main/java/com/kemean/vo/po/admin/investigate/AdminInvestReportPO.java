package com.kemean.vo.po.admin.investigate;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminInvestReportPO extends KemeanPageAdminPO {

	// 结果
	private Boolean result;

	private Integer type;

	private String title;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
