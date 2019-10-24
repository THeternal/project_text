package com.kemean.vo.po.admin.platform;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminFinancePO extends KemeanPageAdminPO {
	private String financeNo;
	private String dateStart;
	private String dateEnd;
	private Integer financeStatus;

	public String getFinanceNo() {
		return financeNo;
	}

	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
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

	public Integer getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(Integer financeStatus) {
		this.financeStatus = financeStatus;
	}

}
