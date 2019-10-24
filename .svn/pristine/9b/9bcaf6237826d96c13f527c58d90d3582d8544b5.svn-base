package com.kemean.vo.po.com;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class SendCodePO {

	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机格式不正确")
	private String phone;

	@NotNull(message = "类型不能为空")
	private Integer type;
	
	private Double money;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
