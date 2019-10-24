package com.kemean.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "kemean_finance_clear")
public class KemeanFinanceClear extends KemeanAbstractBaseBean {
	
	/**
	 * 流水单号
	 */
	@Column(name = "finance_no")
	private String financeNo;

	/**
	 * 财务类型
	 */
	@Column(name = "finance_type")
	private Integer financeType;

	/**
	 * 操作管理员id
	 */
	@Column(name = "admin_id")
	private Integer adminId;

	/**
	 * 用户id,商户id,0-平台
	 */
	@Column(name = "submit_aims_id")
	private Integer submitAimsId;

	/**
	 * 提交目标名称
	 */
	@Column(name = "submit_aims_name")
	private String submitAimsName;

	/**
	 * 操作管理员名称
	 */
	@Column(name = "admin_name")
	private String adminName;

	/**
	 * 提交金额
	 */
	@Column(name = "submit_money")
	private Double submitMoney;

	/**
	 * 佣金率
	 */
	@Column(name = "rate")
	private Double rate;

	/**
	 * 余额
	 */
	@Column(name = "balance")
	private Double balance;

	/**
	 * 成交金额
	 */
	@Column(name = "deal_money")
	private Double dealMoney;

	/**
	 * 交易方式
	 */
	@Column(name = "pay_method")
	private Integer payMethod;

	/**
	 * 交易账号
	 */
	@Column(name = "pay_account")
	private String payAccount;

	/**
	 * 第三方交易号
	 */
	@Column(name = "third_trade_no")
	private String thirdTradeNo;

	/**
	 * 第三方交易状态
	 */
	@Column(name = "third_trade_status")
	private String thirdTradeStatus;

	/**
	 * 财务状态
	 */
	@Column(name = "finance_status")
	private Integer financeStatus;

	/**
	 * 交易月份
	 */
	@Column(name = "finance_month")
	private String financeMonth;

	/**
	 * 交易日期
	 */
	@Column(name = "finance_data")
	private Date financeData;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getFinanceNo() {
		return financeNo;
	}

	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
	}

	public Integer getFinanceType() {
		return financeType;
	}

	public void setFinanceType(Integer financeType) {
		this.financeType = financeType;
	}

	public Integer getSubmitAimsId() {
		return submitAimsId;
	}

	public void setSubmitAimsId(Integer submitAimsId) {
		this.submitAimsId = submitAimsId;
	}

	public String getSubmitAimsName() {
		return submitAimsName;
	}

	public void setSubmitAimsName(String submitAimsName) {
		this.submitAimsName = submitAimsName;
	}

	public Double getSubmitMoney() {
		return submitMoney;
	}

	public void setSubmitMoney(Double submitMoney) {
		this.submitMoney = submitMoney;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getDealMoney() {
		return dealMoney;
	}

	public void setDealMoney(Double dealMoney) {
		this.dealMoney = dealMoney;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}

	public String getThirdTradeStatus() {
		return thirdTradeStatus;
	}

	public void setThirdTradeStatus(String thirdTradeStatus) {
		this.thirdTradeStatus = thirdTradeStatus;
	}

	public Integer getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(Integer financeStatus) {
		this.financeStatus = financeStatus;
	}

	public String getFinanceMonth() {
		return financeMonth;
	}

	public void setFinanceMonth(String financeMonth) {
		this.financeMonth = financeMonth;
	}

	public Date getFinanceData() {
		return financeData;
	}

	public void setFinanceData(Date financeData) {
		this.financeData = financeData;
	}
}
