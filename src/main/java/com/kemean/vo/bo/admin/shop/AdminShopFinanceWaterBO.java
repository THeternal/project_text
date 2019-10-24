package com.kemean.vo.bo.admin.shop;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminShopFinanceWaterBO {

	/**
	 * 余额
	 */
	private Double balance;

	/**
	 * 充值金额
	 */
	private List<AdminChartBO> rechargeMoney;

	/**
	 * 提现金额
	 * 
	 */
	private List<AdminChartBO> withdrawMoney;

	/**
	 * 开销
	 */
	private Double overheadMoney;

	/**
	 * 收入
	 */
	private Double incomeMoney;

	/**
	 * 到账
	 */
	private Double arrivalMoney;

	/**
	 * 未到账
	 */
	private Double notArrivalMoney;

	/**
	 * 到账
	 */
	private List<AdminChartBO> arrivalMoneys;
	/**
	 * 未到账
	 */
	private List<AdminChartBO> notArrivalMoneys;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

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

	public Double getOverheadMoney() {
		return overheadMoney;
	}

	public void setOverheadMoney(Double overheadMoney) {
		this.overheadMoney = overheadMoney;
	}

	public Double getIncomeMoney() {
		return incomeMoney;
	}

	public void setIncomeMoney(Double incomeMoney) {
		this.incomeMoney = incomeMoney;
	}

	public Double getArrivalMoney() {
		return arrivalMoney;
	}

	public void setArrivalMoney(Double arrivalMoney) {
		this.arrivalMoney = arrivalMoney;
	}

	public Double getNotArrivalMoney() {
		return notArrivalMoney;
	}

	public void setNotArrivalMoney(Double notArrivalMoney) {
		this.notArrivalMoney = notArrivalMoney;
	}

	public List<AdminChartBO> getArrivalMoneys() {
		return arrivalMoneys;
	}

	public void setArrivalMoneys(List<AdminChartBO> arrivalMoneys) {
		this.arrivalMoneys = arrivalMoneys;
	}

	public List<AdminChartBO> getNotArrivalMoneys() {
		return notArrivalMoneys;
	}

	public void setNotArrivalMoneys(List<AdminChartBO> notArrivalMoneys) {
		this.notArrivalMoneys = notArrivalMoneys;
	}

}
