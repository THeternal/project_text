package com.kemean.constant;

public enum DaikenOrderEnum {
	
	/** 待发货和已发货  **/
	ACCEPT_SHIP(1404),
	/** 售后退货未完成  **/
	AFTER_SALE_UNFINISH(1405),
	/** 售后退货已完成  **/
	AFTER_SALE_FINISH(1406);
	
	private Integer code;
	
	private DaikenOrderEnum(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

}
