package com.kemean.vo.bo.admin.investigate;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminInvestUserFinanceBO {
	/**
	 * 充值金额
	 */
	private List<AdminChartBO> rechargeMoney;

	/**
	 * 提现金额
	 * 
	 */
	private List<AdminChartBO> withdrawMoney;

	public List<AdminChartBO> getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(List<AdminChartBO> rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public List<AdminChartBO> getWithdrawMoney() {
		return withdrawMoney;
	}

	public void setWithdrawMoney(List<AdminChartBO> withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}

}
