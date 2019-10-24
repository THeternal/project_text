package com.kemean.vo.bo.com;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CloseAccountListBO {

	public CloseAccountListBO() {
		super();
	}

	public CloseAccountListBO(String financeNo, Double payMoney, Double applyMoney, Double rate, Integer financeState,
			String financeStateStr, Date financeData) {
		super();
		this.financeNo = financeNo;
		this.payMoney = payMoney;
		this.applyMoney = applyMoney;
		this.rate = rate;
		this.financeState = financeState;
		this.financeStateStr = financeStateStr;
		this.financeData = financeData;
	}

	private String financeNo;

	/**
	 * 打款金额
	 */
	private Double payMoney;

	/**
	 * 金额
	 */
	private Double applyMoney;

	/**
	 * 佣金率
	 */
	private Double rate;

	/**
	 * 财务状态
	 */
	private Integer financeState;

	/**
	 * 财务状态
	 */
	private String financeStateStr;

	/**
	 * 交易日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date financeData;

	public String getFinanceNo() {
		return financeNo;
	}

	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Double getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getFinanceState() {
		return financeState;
	}

	public void setFinanceState(Integer financeState) {
		this.financeState = financeState;
	}

	public String getFinanceStateStr() {
		return financeStateStr;
	}

	public void setFinanceStateStr(String financeStateStr) {
		this.financeStateStr = financeStateStr;
	}

	public Date getFinanceData() {
		return financeData;
	}

	public void setFinanceData(Date financeData) {
		this.financeData = financeData;
	}

}
