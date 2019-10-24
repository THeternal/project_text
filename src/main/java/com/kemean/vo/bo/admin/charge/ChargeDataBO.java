package com.kemean.vo.bo.admin.charge;

import com.kemean.vo.bo.KemeanIdBO;

public class ChargeDataBO extends KemeanIdBO {

	private String beginTimeStr;

	private String endTimeStr;

	private String showChargeStr;

	private String clickChargeStr;

	private String createTimeStr;

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getShowChargeStr() {
		return showChargeStr;
	}

	public void setShowChargeStr(String showChargeStr) {
		this.showChargeStr = showChargeStr;
	}

	public String getClickChargeStr() {
		return clickChargeStr;
	}

	public void setClickChargeStr(String clickChargeStr) {
		this.clickChargeStr = clickChargeStr;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

}
