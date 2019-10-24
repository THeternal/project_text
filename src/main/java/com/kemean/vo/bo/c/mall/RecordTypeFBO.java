package com.kemean.vo.bo.c.mall;

import java.util.List;

public class RecordTypeFBO {

	private Integer typeId;

	private String typeValue;

	private List<String> recordTypeZBO;

	public List<String> getRecordTypeZBO() {
		return recordTypeZBO;
	}

	public void setRecordTypeZBO(List<String> recordTypeZBO) {
		this.recordTypeZBO = recordTypeZBO;
	}

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

}
