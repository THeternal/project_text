package com.kemean.bean;

import java.util.Date;

import javax.persistence.*;

@Table(name = "daiken_investigate")
public class DaikenInvestigate extends KemeanAbstractBaseBean {

	/**
	 * 发布人ID
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 发布人UID
	 */
	@Column(name = "user_uid")
	private Integer userUId;
	/**
	 * 商品分享唯一标识
	 */
	@Column(name = "token")
	private String token;

	/**
	 * 跳转类型
	 */
	@Column(name = "jump_type")
	private Integer jumpType;

	/**
	 * 跳转类型id
	 */
	@Column(name = "jump_type_id")
	private Integer jumpTypeId;

	/**
	 * 发布类型
	 */
	@Column(name = "type")
	private Integer type;
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 奖励金额/人
	 */
	@Column(name = "reward_price")
	private Double rewardPrice;

	/**
	 * 最大参与人数,0-不限制
	 */
	@Column(name = "max_people_num")
	private Integer maxPeopleNum;

	/**
	 * 结束时间
	 */
	@Column(name = "end_time")
	private Date endTime;

	/**
	 * 发起人单位
	 */
	@Column(name = "initiator_unit")
	private String initiatorUnit;

	/**
	 * 广告说明
	 */
	@Column(name = "advertising_explain")
	private String advertisingExplain;

	/**
	 * 广告图片-json
	 */
	@Column(name = "advertising_img")
	private String advertisingImg;

	/**
	 * 调研说明
	 */
	@Column(name = "investigate_explain")
	private String investigateExplain;

	/**
	 * 调研说明图片-json
	 */
	@Column(name = "investigate_img")
	private String investigateImg;

	/**
	 * 领取人数
	 */
	@Column(name = "sales_num")
	private Integer salesNum;

	/**
	 * 赞(点赞)
	 */
	@Column(name = "num_good")
	private Integer numGood;

	/**
	 * 踩(点赞)
	 */
	@Column(name = "num_bad")
	private Integer numBad;

	/**
	 * 一般(点赞)
	 */
	@Column(name = "num_common")
	private Integer numCommon;

	/**
	 * 访问数
	 */
	@Column(name = "num_visit")
	private Integer numVisit;

	/**
	 * 数据版本
	 */
	@Column(name = "data_version")
	private Integer dataVersion;

	/**
	 * 调研上下架状态
	 */
	@Column(name = "investigate_status")
	private Boolean investigateStatus;

	/**
	 * 支付状态（true为支付了，false为未支付）
	 */
	@Column(name = "pay_status")
	private Boolean payStatus;

	/**
	 * 支付时间
	 */
	@Column(name = "pay_time")
	private Date payTime;

	/**
	 * 精准投放的配条件
	 */
	@Column(name = "match_condition")
	private String matchCondition;

	/**
	 * 领取人数
	 */
	@Column(name = "admin_user_id")
	private Integer adminUserId;

	/**
	 * true-平台发布，false用户发布
	 */
	@Column(name = "is_platform_publish")
	private Boolean isPlatformPublish;

	public Boolean getIsPlatformPublish() {
		return isPlatformPublish;
	}

	public void setIsPlatformPublish(Boolean isPlatformPublish) {
		this.isPlatformPublish = isPlatformPublish;
	}

	public Integer getUserUId() {
		return userUId;
	}

	public void setUserUId(Integer userUId) {
		this.userUId = userUId;
	}

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
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

	public String getAdvertisingImg() {
		return advertisingImg;
	}

	public void setAdvertisingImg(String advertisingImg) {
		this.advertisingImg = advertisingImg;
	}

	public String getInvestigateExplain() {
		return investigateExplain;
	}

	public void setInvestigateExplain(String investigateExplain) {
		this.investigateExplain = investigateExplain;
	}

	public String getInvestigateImg() {
		return investigateImg;
	}

	public void setInvestigateImg(String investigateImg) {
		this.investigateImg = investigateImg;
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

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}