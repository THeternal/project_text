package com.kemean.vo.mysql;

public class LogisticsInfoDB {

	/**
	 * 快递公司名称
	 */
	private String companyName;

	/**
	 * 快递公司编号
	 */
	private String companyNo;

	/**
	 * 快递单编号
	 */
	private String expressWaybillNo;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getExpressWaybillNo() {
		return expressWaybillNo;
	}

	public void setExpressWaybillNo(String expressWaybillNo) {
		this.expressWaybillNo = expressWaybillNo;
	}

}
