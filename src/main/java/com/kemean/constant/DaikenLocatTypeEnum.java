package com.kemean.constant;

public enum DaikenLocatTypeEnum {

	/** 首页 **/
	HOME_PAGE(1101),
	/** 调研 **/
	INVESTIGATE_PAGE(1201);
	private Integer type;

	private DaikenLocatTypeEnum(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
