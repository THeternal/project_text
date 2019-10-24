package com.kemean.vo.bo.admin.support;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminComplaintBO extends KemeanIdBO {
	/**
	 * 投诉的类型
	 */
	private String complaintTypeStr;

	/**
	 * 投诉的名称 (店铺、销售、运营、调研)
	 */
	private String name;

	/**
	 * 发起投诉的用户类型
	 */
	private String userTypeStr;

	/**
	 * 发起投诉的用户姓名
	 */
	private String userName;

	/**
	 * 发起投诉的用户手机号
	 */
	private String userPhone;

	/**
	 * 投诉内容
	 */
	private String content;

	private String dealContent;

	/**
	 * 状态
	 */
	private String complaintStatusStr;

	private Integer complaintStatus;

	/**
	 * 记录的客服id
	 */
	private Integer adminSerivceId;

	/**
	 * 记录的客服name
	 */
	private String adminSerivceName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public String getComplaintTypeStr() {
		return complaintTypeStr;
	}

	public void setComplaintTypeStr(String complaintTypeStr) {
		this.complaintTypeStr = complaintTypeStr;
	}

	public Integer getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(Integer complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public Integer getAdminSerivceId() {
		return adminSerivceId;
	}

	public void setAdminSerivceId(Integer adminSerivceId) {
		this.adminSerivceId = adminSerivceId;
	}

	public String getAdminSerivceName() {
		return adminSerivceName;
	}

	public void setAdminSerivceName(String adminSerivceName) {
		this.adminSerivceName = adminSerivceName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}

	public String getUserTypeStr() {
		return userTypeStr;
	}

	public void setUserTypeStr(String userTypeStr) {
		this.userTypeStr = userTypeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComplaintStatusStr() {
		return complaintStatusStr;
	}

	public void setComplaintStatusStr(String complaintStatusStr) {
		this.complaintStatusStr = complaintStatusStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
