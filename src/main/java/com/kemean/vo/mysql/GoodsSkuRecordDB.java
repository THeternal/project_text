package com.kemean.vo.mysql;

import java.util.List;

public class GoodsSkuRecordDB {
	private Integer typeId;
	private String typeValue;
	private List<String> recordTypeZBO;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public List<String> getRecordTypeZBO() {
		return recordTypeZBO;
	}

	public void setRecordTypeZBO(List<String> recordTypeZBO) {
		this.recordTypeZBO = recordTypeZBO;
	}

}
