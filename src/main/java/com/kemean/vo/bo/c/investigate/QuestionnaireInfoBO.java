package com.kemean.vo.bo.c.investigate;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class QuestionnaireInfoBO extends KemeanIdBO {
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发布单位
	 */
	private String initiatorUnit;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 结束日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date endTime;

	/**
	 * 广告说明
	 */
	private String advertisingExplain;

	/**
	 * 广告说明图片
	 */
	private List<String> advertisingImg;

	/**
	 * 广告跳转类型
	 */
	private Integer jumpType;

	/**
	 * 跳转类型Id
	 */
	private Integer jumpTypeId;

	/**
	 * 奖励金额/人
	 */
	private Double rewardPrice;

	/**
	 * 最大参与人数
	 */
	private Integer maxPeopleNum;

	/**
	 * 领取人数
	 */
	private Integer salesNum;

	/**
	 * 访问数
	 */
	private Integer numVisit;

	/**
	 * 调研说明
	 */
	private String investigateExplain;

	/**
	 * 调研说明图片
	 */
	private List<String> investigateImg;

	/**
	 * 是否完成
	 */
	private Boolean isOver;

	/**
	 * 是否支付
	 */
	private Boolean payStatus;

	/**
	 * 调研上下架状态
	 */
	private Boolean investigateStatus;

	/**
	 * 问题信息
	 */
	private List<QuestionInfoBO> questionInfoBO;

	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}

	public Integer getJumpTypeId() {
		return jumpTypeId;
	}

	public void setJumpTypeId(Integer jumpTypeId) {
		this.jumpTypeId = jumpTypeId;
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

	public String getAdvertisingExplain() {
		return advertisingExplain;
	}

	public void setAdvertisingExplain(String advertisingExplain) {
		this.advertisingExplain = advertisingExplain;
	}

	public List<String> getAdvertisingImg() {
		return advertisingImg;
	}

	public void setAdvertisingImg(List<String> advertisingImg) {
		this.advertisingImg = advertisingImg;
	}

	public Double getRewardPrice() {
		return rewardPrice;
	}

	public void setRewardPrice(Double rewardPrice) {
		this.rewardPrice = rewardPrice;
	}

	public Integer getMaxPeopleNum() {
		return maxPeopleNum;
	}

	public void setMaxPeopleNum(Integer maxPeopleNum) {
		this.maxPeopleNum = maxPeopleNum;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getNumVisit() {
		return numVisit;
	}

	public void setNumVisit(Integer numVisit) {
		this.numVisit = numVisit;
	}

	public String getInvestigateExplain() {
		return investigateExplain;
	}

	public void setInvestigateExplain(String investigateExplain) {
		this.investigateExplain = investigateExplain;
	}

	public List<String> getInvestigateImg() {
		return investigateImg;
	}

	public void setInvestigateImg(List<String> investigateImg) {
		this.investigateImg = investigateImg;
	}

	public Boolean getIsOver() {
		return isOver;
	}

	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}

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

	public List<QuestionInfoBO> getQuestionInfoBO() {
		return questionInfoBO;
	}

	public void setQuestionInfoBO(List<QuestionInfoBO> questionInfoBO) {
		this.questionInfoBO = questionInfoBO;
	}

}
