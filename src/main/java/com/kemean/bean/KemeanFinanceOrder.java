package com.kemean.bean;

import java.util.Date;

import javax.persistence.*;

@Table(name = "kemean_finance_order")
public class KemeanFinanceOrder extends KemeanAbstractBaseBean {

	/**
	 * 订单编号
	 */
	@Column(name = "order_no")
	private String orderNo;

	/**
	 * 流水单号,财务落地时生成
	 */
	@Column(name = "finance_no")
	private String financeNo;

	/**
	 * 财务类型
	 */
	@Column(name = "finance_type")
	private Integer financeType;

	/**
	 * 金额
	 */
	@Column(name = "money")
	private Double money;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 商户id
	 */
	@Column(name = "business_id")
	private Integer businessId;

	/**
	 * 用户、商户名称
	 */
	@Column(name = "name")
	private String name;

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

	/**
	 * 拓展记录
	 */
	@Column(name = "record")
	private String record;

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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