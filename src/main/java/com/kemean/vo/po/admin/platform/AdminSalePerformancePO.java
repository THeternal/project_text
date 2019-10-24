package com.kemean.vo.po.admin.platform;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminSalePerformancePO extends KemeanPageAdminPO {

	private String phone;

	private String dateStart;

	private String dateEnd;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

}
