package com.kemean.vo.bo.admin.goods;

import com.kemean.vo.bo.KemeanIdBO;

public class AdminConfigBO extends KemeanIdBO {

	private String record;

	private String remark;

	private String createTimeStr;

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

}
