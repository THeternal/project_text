package com.kemean.vo.po.s;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class LoginPO {

	@Pattern(regexp = "^1\\d{10}$", message = "手机格式不正确")
	private String phone;

	@Length(min = 32, message = "密码为32位MD5加密串")
	private String password;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
