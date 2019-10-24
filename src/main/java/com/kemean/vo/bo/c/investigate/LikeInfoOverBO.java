package com.kemean.vo.bo.c.investigate;

import com.kemean.vo.bo.KemeanIdBO;

public class LikeInfoOverBO extends KemeanIdBO {

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发布人单位
	 */
	private String initiatorUnit;

	/**
	 * 创建日期
	 */
	private String createTimeStr;

	/**
	 * 点赞总数
	 */
	private Integer maxPeopleNum;

	/**
	 * 赞
	 */
	private Integer numGood;

	/**
	 * 赞 - 百分比
	 */
	private Double numGoodPercentage;

	/**
	 * 一般
	 */
	private Integer numCommon;

	/**
	 * 一般 - 百分比
	 */
	private Double numCommonPercentage;

	/**
	 * 踩
	 */
	private Integer numBad;

	/**
	 * 踩 - 百分比
	 */
	private Double numBadPercentage;

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

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Integer getMaxPeopleNum() {
		return maxPeopleNum;
	}

	public void setMaxPeopleNum(Integer maxPeopleNum) {
		this.maxPeopleNum = maxPeopleNum;
	}

	public Integer getNumGood() {
		return numGood;
	}

	public void setNumGood(Integer numGood) {
		this.numGood = numGood;
	}

	public Integer getNumCommon() {
		return numCommon;
	}

	public void setNumCommon(Integer numCommon) {
		this.numCommon = numCommon;
	}

	public Integer getNumBad() {
		return numBad;
	}

	public void setNumBad(Integer numBad) {
		this.numBad = numBad;
	}

	public Double getNumGoodPercentage() {
		return numGoodPercentage;
	}

	public void setNumGoodPercentage(Double numGoodPercentage) {
		this.numGoodPercentage = numGoodPercentage;
	}

	public Double getNumCommonPercentage() {
		return numCommonPercentage;
	}

	public void setNumCommonPercentage(Double numCommonPercentage) {
		this.numCommonPercentage = numCommonPercentage;
	}

	public Double getNumBadPercentage() {
		return numBadPercentage;
	}

	public void setNumBadPercentage(Double numBadPercentage) {
		this.numBadPercentage = numBadPercentage;
	}

}
