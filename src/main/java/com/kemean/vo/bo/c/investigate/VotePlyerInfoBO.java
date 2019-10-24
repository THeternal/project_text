package com.kemean.vo.bo.c.investigate;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class VotePlyerInfoBO extends KemeanIdBO {

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
	 * 最大参与人数
	 */
	private Integer maxPeopleNum;

	/**
	 * 领取人数
	 */
	private Integer salesNum;

	/**
	 * 奖励金额/人
	 */
	private Double rewardPrice;

	/**
	 * 选手名称
	 */
	private String name;

	/**
	 * 获得票数
	 */
	private Integer votes;

	/**
	 * 人气
	 */
	private Integer popularity;

	/**
	 * 选手说明
	 */
	private String explainStr;

	/**
	 * 选手图片
	 */
	private List<String> img;

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

	public Double getRewardPrice() {
		return rewardPrice;
	}

	public void setRewardPrice(Double rewardPrice) {
		this.rewardPrice = rewardPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getExplainStr() {
		return explainStr;
	}

	public void setExplainStr(String explainStr) {
		this.explainStr = explainStr;
	}

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

}
