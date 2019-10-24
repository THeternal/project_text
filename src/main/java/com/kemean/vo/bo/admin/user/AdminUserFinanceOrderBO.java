package com.kemean.vo.bo.admin.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminUserFinanceOrderBO extends KemeanIdBO {
	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 流水单号,财务落地时生成
	 */
	private String financeNo;

	/**
	 * 财务类型
	 */
	private Integer financeType;

	/**
	 * 财务类型
	 */
	private String financeTypeStr;

	/**
	 * 金额
	 */
	private Double money;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 商户id
	 */
	private Integer businessId;

	/**
	 * 用户、商户名称
	 */
	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date financeData;

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

	public String getFinanceTypeStr() {
		return financeTypeStr;
	}

	public void setFinanceTypeStr(String financeTypeStr) {
		this.financeTypeStr = financeTypeStr;
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

	public Date getFinanceData() {
		return financeData;
	}

	public void setFinanceData(Date financeData) {
		this.financeData = financeData;
	}

}
