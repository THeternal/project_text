package com.kemean.vo.bo.admin.platform;

public class AdminIncomeBO {
	// 充值
	private Double recharge;

	// 佣金率
	private Double rate;

	// 提现
	private Double withdraw;

	// 赠送的token
	private Double tokenMoney;

	// 净利
	private Double netIncome;

	// 毛利
	private Double grossProfit;
	// 现金总账户
	private Double cashMoney;
	// 应付商家总额
	private Double getShopMoney;

	public Double getRecharge() {
		return recharge;
	}

	public void setRecharge(Double recharge) {
		this.recharge = recharge;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public Double getTokenMoney() {
		return tokenMoney;
	}

	public void setTokenMoney(Double tokenMoney) {
		this.tokenMoney = tokenMoney;
	}

	public Double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}

	public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public Double getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(Double cashMoney) {
		this.cashMoney = cashMoney;
	}

	public Double getGetShopMoney() {
		return getShopMoney;
	}

	public void setGetShopMoney(Double getShopMoney) {
		this.getShopMoney = getShopMoney;
	}

}
