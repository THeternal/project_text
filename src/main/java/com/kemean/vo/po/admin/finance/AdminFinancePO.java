package com.kemean.vo.po.admin.finance;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminFinancePO extends KemeanPageAdminPO {

	private String financeNo;
	private String shopName;
	private Integer financeStatus;

	public String getFinanceNo() {
		return financeNo;
	}

	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(Integer financeStatus) {
		this.financeStatus = financeStatus;
	}



}
