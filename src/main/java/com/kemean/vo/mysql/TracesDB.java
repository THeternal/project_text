package com.kemean.vo.mysql;

public class TracesDB {

	/**
	 * 接收地点
	 */
	private String AcceptStation;

	/**
	 * 接收时间
	 */
	private String AcceptTime;

	/**
	 * 备注
	 */
	private String Remark;

	public String getAcceptStation() {
		return AcceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}

	public String getAcceptTime() {
		return AcceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
