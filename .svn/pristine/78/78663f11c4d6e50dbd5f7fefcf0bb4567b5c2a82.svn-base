package com.kemean.vo.po.admin.support;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class AdminSupportComplaintAddPO {
	/**
	 * 投诉的类型
	 */
	@NotNull(message = "投诉的类型不能为空")
	private Integer complaintType;

	/**
	 * 投诉的名称 (店铺、销售、运营、调研)
	 */
	@NotBlank(message = "投诉的名称不能为空")
	private String name;

	/**
	 * 发起投诉的用户类型
	 */
	@NotNull(message = "发起投诉的用户类型不能为空")
	private Integer userType;

	/**
	 * 发起投诉的用户姓名
	 */
	@NotBlank(message = "发起投诉的用户姓名不能为空")
	private String userName;

	/**
	 * 发起投诉的用户手机号
	 */
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机格式不正确")
	private String userPhone;

	/**
	 * 投诉内容
	 */
	@NotBlank(message = "投诉的内容不能为空")
	private String content;

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

}
