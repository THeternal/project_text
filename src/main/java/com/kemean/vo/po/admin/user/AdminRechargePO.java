package com.kemean.vo.po.admin.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AdminRechargePO {
	@NotNull(message = "ID不能为空")
	private Integer objId;
	@NotNull(message = "充值金额不能为空")
	private Double money;
	@NotBlank(message = "财务密码不能为空")
	private String password;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
