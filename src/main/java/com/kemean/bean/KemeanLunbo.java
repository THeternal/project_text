package com.kemean.bean;

import java.util.Date;

import javax.persistence.*;

@Table(name = "kemean_lunbo")
public class KemeanLunbo extends KemeanAbstractBaseBean {

	/**
	 * 与收费时间表关联
	 */
	@Column(name = "recommend_id")
	private Integer recommendId;

	/**
	 * 展示位置（1101首页，1201调研）
	 */
	@Column(name = "locat_type")
	private Integer locatType;

	/**
	 * 商铺or商品id
	 */
	@Column(name = "type_id")
	private Integer typeId;

	/**
	 * 用户联系手机
	 */
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 * 推荐方式(商铺1101，商品1201)
	 */
	@Column(name = "recommend_way")
	private Integer recommendWay;

	/**
	 * 推荐类型（时长1101，点击1201，替补1301）
	 */
	@Column(name = "recommend_type")
	private Integer recommendType;

	/**
	 * 图片链接
	 */
	@Column(name = "img_url")
	private String imgUrl;

	/**
	 * 购买点击次数
	 */
	@Column(name = "buy_click_num")
	private Integer buyClickNum;

	/**
	 * 点击次数
	 */
	@Column(name = "click_num")
	private Integer clickNum;

	/**
	 * 购买天数
	 */
	@Column(name = "buy_days")
	private Integer buyDays;

	/**
	 * 购买显示时间(年月日)
	 */
	@Column(name = "buy_time")
	private Date buyTime;

	/**
	 * 是否结束,true结束，false未结束
	 */
	@Column(name = "is_over")
	private Boolean isOver;

	/**
	 * 是否生效，true生效，false不生效
	 */
	@Column(name = "is_take_effect")
	private Boolean isTakeEffect;

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	public Integer getLocatType() {
		return locatType;
	}

	public void setLocatType(Integer locatType) {
		this.locatType = locatType;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getRecommendWay() {
		return recommendWay;
	}

	public void setRecommendWay(Integer recommendWay) {
		this.recommendWay = recommendWay;
	}

	public Integer getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(Integer recommendType) {
		this.recommendType = recommendType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getBuyClickNum() {
		return buyClickNum;
	}

	public void setBuyClickNum(Integer buyClickNum) {
		this.buyClickNum = buyClickNum;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getBuyDays() {
		return buyDays;
	}

	public void setBuyDays(Integer buyDays) {
		this.buyDays = buyDays;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Boolean getIsOver() {
		return isOver;
	}

	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}

	public Boolean getIsTakeEffect() {
		return isTakeEffect;
	}

	public void setIsTakeEffect(Boolean isTakeEffect) {
		this.isTakeEffect = isTakeEffect;
	}

}