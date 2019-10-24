package com.kemean.vo.bo.c.investigate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class InvestigateListBO extends KemeanIdBO {

	
	
	/**
	 * 发布类型
	 */
	private Integer type;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发起人单位
	 */
	private String initiatorUnit;

	/**
	 * 奖励金额
	 */
	private Double rewardPrice;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date endTime;

	/**
	 * 最大参与人数
	 */
	private Integer maxPeopleNum;

	/**
	 * 访问数
	 */
	private Integer numVisit;

	/**
	 * 领取人数
	 */
	private Integer salesNum;

	/**
	 * 调研上下架状态
	 */
	private Boolean investigateStatus;

	/**
	 * 支付状态
	 */
	private Boolean payStatus;

	/**
	 * 是否结束
	 */
	private Boolean isOver;

	public Boolean getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Boolean payStatus) {
		this.payStatus = payStatus;
	}

	public Boolean getInvestigateStatus() {
		return investigateStatus;
	}

	public void setInvestigateStatus(Boolean investigateStatus) {
		this.investigateStatus = investigateStatus;
	}

	public Boolean getIsOver() {
		return isOver;
	}

	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInitiatorUnit() {
		return initiatorUnit;
	}

	public void setInitiatorUnit(String initiatorUnit) {
		this.initiatorUnit = initiatorUnit;
	}

	public Double getRewardPrice() {
		return rewardPrice;
	}

	public void setRewardPrice(Double rewardPrice) {
		this.rewardPrice = rewardPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getMaxPeopleNum() {
		return maxPeopleNum;
	}

	public void setMaxPeopleNum(Integer maxPeopleNum) {
		this.maxPeopleNum = maxPeopleNum;
	}

	public Integer getNumVisit() {
		return numVisit;
	}

	public void setNumVisit(Integer numVisit) {
		this.numVisit = numVisit;
	}

}
