package com.kemean.vo.bo.c.mall;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class OldGoodsListBO extends KemeanIdBO {

	/**
	 * 发布者头像
	 */
	private String userHeadImg;

	/**
	 * 发布者昵称
	 */
	private String nickName;

	/**
	 * 商品图片
	 */
	private List<String> headImg;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 成色
	 */
	private String qualityStr;

	/**
	 * 售前红包
	 */
	private Double redBefore;

	/**
	 * 代卖
	 */
	private Boolean purchasing;

	/**
	 * 代卖提成
	 */
	private Double pricePurchasing;

	/**
	 * 原价
	 */
	private Double priceOriginal;

	/**
	 * 支付价
	 */
	private Double priceSales;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 发布时间
	 */
	private String twoDayMinuteStr;

	/**
	 * 上下架状态
	 */
	private Boolean goodsStatus;

	/**
	 * 是否被卖出
	 */
	private Boolean isBuy;

	/**
	 * 帮代卖商铺id，如果是0表示不是帮代卖
	 */
	private Integer userShopId;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Boolean getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTwoDayMinuteStr() {
		return twoDayMinuteStr;
	}

	public void setTwoDayMinuteStr(String twoDayMinuteStr) {
		this.twoDayMinuteStr = twoDayMinuteStr;
	}

	public List<String> getHeadImg() {
		return headImg;
	}

	public void setHeadImg(List<String> headImg) {
		this.headImg = headImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQualityStr() {
		return qualityStr;
	}

	public void setQualityStr(String qualityStr) {
		this.qualityStr = qualityStr;
	}

	public Double getRedBefore() {
		return redBefore;
	}

	public void setRedBefore(Double redBefore) {
		this.redBefore = redBefore;
	}

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

	public Double getPriceOriginal() {
		return priceOriginal;
	}

	public void setPriceOriginal(Double priceOriginal) {
		this.priceOriginal = priceOriginal;
	}

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
