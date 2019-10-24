package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_complaint")
public class DaikenComplaint extends KemeanAbstractBaseBean {

	/**
	 * 投诉的类型
	 */
	@Column(name = "complaint_type")
	private Integer complaintType;

	/**
	 * 投诉的名称 (店铺、销售、运营、调研)
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 发起投诉的用户类型
	 */
	@Column(name = "user_type")
	private Integer userType;

	/**
	 * 发起投诉的用户姓名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 发起投诉的用户手机号
	 */
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 * 投诉内容
	 */
	@Column(name = "content")
	private String content;

	/**
	 * 投诉内容
	 */
	@Column(name = "deal_content")
	private String dealContent;

	/**
	 * 状态
	 */
	@Column(name = "complaint_status")
	private Integer complaintStatus;

	/**
	 * 记录的客服id
	 */
	@Column(name = "admin_serivce_id")
	private Integer adminSerivceId;

	/**
	 * 记录的客服name
	 */
	@Column(name = "admin_serivce_name")
	private String adminSerivceName;

	public Integer getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(Integer complaintType) {
		this.complaintType = complaintType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
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

}