package com.kemean.vo.po.c.investigate;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostVotePO {

	private Integer objId;

	@NotBlank(message = "标题不能为空")
	private String title;

	@NotNull(message = "奖励金额/人不能为空")
	private Double rewardPrice;

	@NotNull(message = "最大参与人数不能为空")
	private Integer maxPeopleNum;

	@NotNull(message = "结束时间不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;

	@NotBlank(message = "发起人单位不能为空")
	private String initiatorUnit;

	private String advertisingExplain;

	private List<String> advertisingImg;

	private String investigateExplain;

	private Integer jumpType;
	private Integer jumpTypeId;

	private Boolean investigateStatus;

	private List<String> investigateImg;

	private List<PostVotePlyearPO> postVotePlyearLists;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Boolean getInvestigateStatus() {
		return investigateStatus;
	}

	public void setInvestigateStatus(Boolean investigateStatus) {
		this.investigateStatus = investigateStatus;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<PostVotePlyearPO> getPostVotePlyearLists() {
		return postVotePlyearLists;
	}

	public void setPostVotePlyearLists(List<PostVotePlyearPO> postVotePlyearLists) {
		this.postVotePlyearLists = postVotePlyearLists;
	}

}
