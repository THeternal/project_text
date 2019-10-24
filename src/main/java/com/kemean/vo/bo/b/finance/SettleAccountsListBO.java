package com.kemean.vo.bo.b.finance;

public class SettleAccountsListBO {

	/**
	 * 冻结资金
	 */
	private Double freezeMoney;

	/**
	 * 可提现资金
	 */
	private Double waitMoney;

	/**
	 * 全部资金
	 */
	private Double totalAssets;

	public Double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Double getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(Double freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	public Double getWaitMoney() {
		return waitMoney;
	}

	public void setWaitMoney(Double waitMoney) {
		this.waitMoney = waitMoney;
	}

}
