package com.kemean.vo.bo.admin.investigate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminInvestReportBO extends KemeanIdBO {

	// 类型
	private String typeStr;
	// 标题
	private String title;
	// 结果
	private String result;
	// 用户佣金
	private Double rewardPrice;
	// 所费金额
	private Double payPrice;
	// 平台佣金
	private Double platformPrice;
	// 参与人数
	private Integer maxPeopleNum;
	// 结束时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd", timezone = "GMT+8")
	private Date endTime;
	// 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd", timezone = "GMT+8")
	private Date createTime;

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Double getRewardPrice() {
		return rewardPrice;
	}

	public void setRewardPrice(Double rewardPrice) {
		this.rewardPrice = rewardPrice;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public Double getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Double platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Integer getMaxPeopleNum() {
		return maxPeopleNum;
	}

	public void setMaxPeopleNum(Integer maxPeopleNum) {
		this.maxPeopleNum = maxPeopleNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
