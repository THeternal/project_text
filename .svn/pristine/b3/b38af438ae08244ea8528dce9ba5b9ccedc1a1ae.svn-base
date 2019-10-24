package com.kemean.vo.bo.admin.investigate;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminInvestBO extends KemeanIdBO {

	/**
	 * 发起人Id
	 */
	private Integer userId;

	/**
	 * 发起人昵称
	 */
	private String nickName;

	/**
	 * 发布类型
	 */
	private Integer type;

	/**
	 * 发布类型
	 */
	private String typeStr;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 奖励金额/人
	 */
	private Double rewardPrice;

	/**
	 * 所需费用
	 */
	private Double totalPrice;

	/**
	 * 最大参与人数,0-不限制
	 */
	private Integer maxPeopleNum;

	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;

	private String endTimeStr;

	/**
	 * 发起人单位
	 */
	private String initiatorUnit;

	/**
	 * 广告说明
	 */
	private String advertisingExplain;

	/**
	 * 广告图片-json
	 */
	private List<String> advertisingImg;

	/**
	 * 调研说明
	 */
	private String investigateExplain;

	/**
	 * 调研说明图片-json
	 */
	private List<String> investigateImg;

	/**
	 * 领取人数
	 */
	private Integer salesNum;

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * 赞(点赞)
	 */
	private Integer numGood;

	/**
	 * 踩(点赞)
	 */
	private Integer numBad;

	/**
	 * 一般(点赞)
	 */
	private Integer numCommon;

	/**
	 * 访问数
	 */
	private Integer numVisit;

	/**
	 * 调研上下架状态
	 */
	private Boolean investigateStatus;

	/**
	 * 支付状态（true为支付了，false为未支付）
	 */
	private Boolean payStatus;

	/**
	 * 支付状态（true为支付了，false为未支付）
	 */
	private String payStatusStr;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 针对问卷，问题列表
	 */
	private List<AdminInvestQuestionBO> investQuestionBO;

	/**
	 * 匹配条件
	 */
	private String matchCondition;

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

	public String getPayStatusStr() {
		return payStatusStr;
	}

	public void setPayStatusStr(String payStatusStr) {
		this.payStatusStr = payStatusStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getMatchCondition() {
		return matchCondition;
	}

	public void setMatchCondition(String matchCondition) {
		this.matchCondition = matchCondition;
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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInitiatorUnit() {
		return initiatorUnit;
	}

	public void setInitiatorUnit(String initiatorUnit) {
		this.initiatorUnit = initiatorUnit;
	}

	public String getAdvertisingExplain() {
		return advertisingExplain;
	}

	public void setAdvertisingExplain(String advertisingExplain) {
		this.advertisingExplain = advertisingExplain;
	}

	public String getInvestigateExplain() {
		return investigateExplain;
	}

	public void setInvestigateExplain(String investigateExplain) {
		this.investigateExplain = investigateExplain;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public List<String> getAdvertisingImg() {
		return advertisingImg;
	}

	public void setAdvertisingImg(List<String> advertisingImg) {
		this.advertisingImg = advertisingImg;
	}

	public List<String> getInvestigateImg() {
		return investigateImg;
	}

	public void setInvestigateImg(List<String> investigateImg) {
		this.investigateImg = investigateImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<AdminInvestQuestionBO> getInvestQuestionBO() {
		return investQuestionBO;
	}

	public void setInvestQuestionBO(List<AdminInvestQuestionBO> investQuestionBO) {
		this.investQuestionBO = investQuestionBO;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getNumGood() {
		return numGood;
	}

	public void setNumGood(Integer numGood) {
		this.numGood = numGood;
	}

	public Integer getNumBad() {
		return numBad;
	}

	public void setNumBad(Integer numBad) {
		this.numBad = numBad;
	}

	public Integer getNumCommon() {
		return numCommon;
	}

	public void setNumCommon(Integer numCommon) {
		this.numCommon = numCommon;
	}

	public Integer getNumVisit() {
		return numVisit;
	}

	public void setNumVisit(Integer numVisit) {
		this.numVisit = numVisit;
	}

	public Boolean getInvestigateStatus() {
		return investigateStatus;
	}

	public void setInvestigateStatus(Boolean investigateStatus) {
		this.investigateStatus = investigateStatus;
	}

	public Boolean getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Boolean payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
