package com.kemean.vo.po.admin.shop;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AdminWithdrawDepositPO {

	/**
	 * 提现金额
	 */
	@NotNull(message = "提现金额不能为空")
	private Double money;

	/**
	 * 手机短信验证码
	 */
	@NotBlank(message = "验证码不能为空")
	private String code;

	/**
	 * 支付宝账号
	 */
	private String account;

	/**
	 * 支付宝实名名称
	 */
	private String name;

	/**
	 * 微信用户openid
	 */
	private String openid;

	/**
	 * 提现方式
	 */
	@NotNull(message = "提现方式不能为空")
	private Integer type;

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
