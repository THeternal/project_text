package com.kemean.vo.bo.admin.shop;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminShopFinanceClearBO extends KemeanIdBO {

	/**
	 * 流水单号
	 */
	private String financeNo;

	/**
	 * 财务类型
	 */
	private String financeTypeStr;

	/**
	 * 提交目标名称
	 */
	private String submitAimsName;

	private Integer submitAimsId;
	/**
	 * 提交金额
	 */
	private Double submitMoney;

	/**
	 * 佣金率
	 */
	private Double rate;

	/**
	 * 余额
	 */
	private Double balance;

	/**
	 * 成交金额
	 */
	private Double dealMoney;

	/**
	 * 交易方式
	 */
	private String payMethoStr;

	/**
	 * 财务状态
	 */
	private String financeStatusStr;

	/**
	 * 提现/充值 合计金额
	 */
	private Double totalMoney;

	/**
	 * 交易日期
	 */
	private Date financeData;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public Integer getSubmitAimsId() {
		return submitAimsId;
	}

	public void setSubmitAimsId(Integer submitAimsId) {
		this.submitAimsId = submitAimsId;
	}

	public String getFinanceNo() {
		return financeNo;
	}

	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
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

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getFinanceTypeStr() {
		return financeTypeStr;
	}

	public void setFinanceTypeStr(String financeTypeStr) {
		this.financeTypeStr = financeTypeStr;
	}

	public String getFinanceStatusStr() {
		return financeStatusStr;
	}

	public void setFinanceStatusStr(String financeStatusStr) {
		this.financeStatusStr = financeStatusStr;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getDealMoney() {
		return dealMoney;
	}

	public void setDealMoney(Double dealMoney) {
		this.dealMoney = dealMoney;
	}

	public String getPayMethoStr() {
		return payMethoStr;
	}

	public void setPayMethoStr(String payMethoStr) {
		this.payMethoStr = payMethoStr;
	}

	public Date getFinanceData() {
		return financeData;
	}

	public void setFinanceData(Date financeData) {
		this.financeData = financeData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
