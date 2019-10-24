package com.kemean.vo.po.c.mine;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateUserPhonePO {

	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机格式不正确")
	private String phone;

	@NotBlank(message = "验证码不能为空")
	private String code;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
