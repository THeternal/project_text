package com.kemean.vo.bo.b.order;

public class CourierCompanyBO {

	public CourierCompanyBO() {
		super();
	}

	public CourierCompanyBO(String companyName, String companyNo) {
		super();
		this.companyName = companyName;
		this.companyNo = companyNo;
	}

	private String companyName;

	private String companyNo;

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

}
