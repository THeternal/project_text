package com.kemean.vo.po.admin.investigate;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.po.c.investigate.PostVotePlyearPO;

public class AdminInvestigateAddPO {

	/**
	 * 发布人ID
	 */
	private Integer userId;

	private Integer objId;

	@NotBlank(message = "标题不能为空")
	private String title;

	@NotNull(message = "奖励金额/人不能为空")
	private Double rewardPrice;

	@NotNull(message = "最大参与人数不能为空")
	private Integer maxPeopleNum;

	@NotNull(message = "结束时间不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;

	@NotBlank(message = "发起人单位不能为空")
	private String initiatorUnit;

	@NotBlank(message = "广告说明不能为空")
	private String advertisingExplain;
	// 广告图
	private List<String> advertisingImg;

	@NotBlank(message = "调研说明不能为空")
	private String investigateExplain;

	// 调研上下架状态
	private Boolean investigateStatus;
	// 调研图
	private List<String> investigateImg;

	// 跳转类型
	private Integer jumpType;
	// 跳转id
	private Integer jumpTypeId;

	// 选手信息，。适用于投票
	private List<PostVotePlyearPO> postVotePlyearLists;

	private List<AdminQuestionPO> adminQuestionPOs;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<String> getAdvertisingImg() {
		return advertisingImg;
	}

	public void setAdvertisingImg(List<String> advertisingImg) {
		this.advertisingImg = advertisingImg;
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

	public Boolean getInvestigateStatus() {
		return investigateStatus;
	}

	public void setInvestigateStatus(Boolean investigateStatus) {
		this.investigateStatus = investigateStatus;
	}

	public List<PostVotePlyearPO> getPostVotePlyearLists() {
		return postVotePlyearLists;
	}

	public void setPostVotePlyearLists(List<PostVotePlyearPO> postVotePlyearLists) {
		this.postVotePlyearLists = postVotePlyearLists;
	}

	public List<AdminQuestionPO> getAdminQuestionPOs() {
		return adminQuestionPOs;
	}

	public void setAdminQuestionPOs(List<AdminQuestionPO> adminQuestionPOs) {
		this.adminQuestionPOs = adminQuestionPOs;
	}

}
