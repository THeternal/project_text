package com.kemean.vo.bo.admin.platform;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminQuestionUserBO extends KemeanIdBO {

	/**
	 * 与调研表关联
	 */
	private Integer investigateId;

	/**
	 * 与用户表关联
	 */
	private Integer userId;

	/**
	 * 是否完成,false-未完成，true完成
	 */
	private String isFinishedStr;

	private String nickName;
	private String phone;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public Integer getInvestigateId() {
		return investigateId;
	}

	public void setInvestigateId(Integer investigateId) {
		this.investigateId = investigateId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIsFinishedStr() {
		return isFinishedStr;
	}

	public void setIsFinishedStr(String isFinishedStr) {
		this.isFinishedStr = isFinishedStr;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
