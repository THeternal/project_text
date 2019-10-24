package com.kemean.vo.bo.admin.goods;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdminGoodsPrecisionPushBO {
	private String goodsUid;
	private String skuNo;
	private String goodsTitle;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startDate;
	// 当前点击人数
	private Integer nowClickUserNum;
	// 当前购买人数
	private Integer nowBuyUserNum;
	// 目标点击人数
	private Integer targetClickUserNum;

	// 转化率
	private Double conversionRate;

	public String getGoodsUid() {
		return goodsUid;
	}

	public void setGoodsUid(String goodsUid) {
		this.goodsUid = goodsUid;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getNowClickUserNum() {
		return nowClickUserNum;
	}

	public void setNowClickUserNum(Integer nowClickUserNum) {
		this.nowClickUserNum = nowClickUserNum;
	}

	public Integer getNowBuyUserNum() {
		return nowBuyUserNum;
	}

	public void setNowBuyUserNum(Integer nowBuyUserNum) {
		this.nowBuyUserNum = nowBuyUserNum;
	}

	public Integer getTargetClickUserNum() {
		return targetClickUserNum;
	}

	public void setTargetClickUserNum(Integer targetClickUserNum) {
		this.targetClickUserNum = targetClickUserNum;
	}

}
