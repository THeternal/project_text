package com.kemean.constant;

public enum DaikenLunboLocatTypeEnum {

	/** 首页展示 **/
	HOME_PAGE(1101),
	/** 调研展示 **/
	INVESTIGATE_PAGE(1201);

	private Integer locatType;

	private DaikenLunboLocatTypeEnum(Integer locatType) {
		this.locatType = locatType;
	}

	public Integer getLocatType() {
		return locatType;
	}

	public void setLocatType(Integer locatType) {
		this.locatType = locatType;
	}

}
