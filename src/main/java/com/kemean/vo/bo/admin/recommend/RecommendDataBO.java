package com.kemean.vo.bo.admin.recommend;

import com.kemean.vo.bo.KemeanIdBO;

public class RecommendDataBO extends KemeanIdBO {

	/**
	 * 用户手机号
	 */
	private String userPhone;

	/**
	 * 名称
	 */
	private String typeStr;

	/**
	 * 推荐方式
	 */
	private String recommendWayStr;

	/**
	 * 推荐类型
	 */
	private String recommendTypeStr;

	/**
	 * 购买时间
	 */
	private String buyTimeStr;

	/**
	 * 是否结束
	 */
	private String isOverStr;

	/**
	 * 是否生效
	 */
	private Boolean isTakeEffect;

	/**
	 * 图片
	 */
	private String img;

	/**
	 * 购买时间段/剩余点击数
	 */
	private String timeBucket;

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getRecommendWayStr() {
		return recommendWayStr;
	}

	public void setRecommendWayStr(String recommendWayStr) {
		this.recommendWayStr = recommendWayStr;
	}

	public String getRecommendTypeStr() {
		return recommendTypeStr;
	}

	public void setRecommendTypeStr(String recommendTypeStr) {
		this.recommendTypeStr = recommendTypeStr;
	}

	public String getBuyTimeStr() {
		return buyTimeStr;
	}

	public void setBuyTimeStr(String buyTimeStr) {
		this.buyTimeStr = buyTimeStr;
	}

	public String getIsOverStr() {
		return isOverStr;
	}

	public void setIsOverStr(String isOverStr) {
		this.isOverStr = isOverStr;
	}

	public Boolean getIsTakeEffect() {
		return isTakeEffect;
	}

	public void setIsTakeEffect(Boolean isTakeEffect) {
		this.isTakeEffect = isTakeEffect;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTimeBucket() {
		return timeBucket;
	}

	public void setTimeBucket(String timeBucket) {
		this.timeBucket = timeBucket;
	}

}
