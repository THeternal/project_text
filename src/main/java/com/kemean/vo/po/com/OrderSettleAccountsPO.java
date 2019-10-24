package com.kemean.vo.po.com;

import javax.validation.constraints.NotNull;

public class OrderSettleAccountsPO {

	private String code;

	@NotNull(message = "申请金额不能为空")
	private Double applyMoney;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

}
