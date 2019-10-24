package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_goods_recommend_charge")
public class DaikenGoodsRecommendCharge extends KemeanAbstractBaseBean {

	/**
	 * 开始时间
	 */
	@Column(name = "begin_time")
	private Integer beginTime;

	/**
	 * 结束时间
	 */
	@Column(name = "end_time")
	private Integer endTime;

	/**
	 * 显示收取的费用
	 */
	@Column(name = "show_charge")
	private Double showCharge;

	/**
	 * 点击收取的费用
	 */
	@Column(name = "click_charge")
	private Double clickCharge;

	public Double getClickCharge() {
		return clickCharge;
	}

	public void setClickCharge(Double clickCharge) {
		this.clickCharge = clickCharge;
	}

	public Integer getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Double getShowCharge() {
		return showCharge;
	}

	public void setShowCharge(Double showCharge) {
		this.showCharge = showCharge;
	}

}