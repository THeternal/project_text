package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_user_shop")
public class DaikenUserShop extends KemeanAbstractBaseBean {

	/**
	 * 用户关联id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 商铺名称
	 */
	@Column(name = "shop_name")
	private String shopName;

	/**
	 * 商铺图片
	 */
	@Column(name = "img")
	private String img;

	/**
	 * 销量
	 */
	@Column(name = "sales_volume")
	private Integer salesVolume;

	/**
	 * 赞
	 */
	@Column(name = "num_praise")
	private Integer numPraise;

	/**
	 * 踩
	 */
	@Column(name = "num_trample")
	private Integer numTrample;

	/**
	 * 收藏
	 */
	@Column(name = "num_collect")
	private Integer numCollect;

	/**
	 * 好评率
	 */
	@Column(name = "degree_of_praise")
	private Float degreeOfPraise;

	public Integer getNumCollect() {
		return numCollect;
	}

	public void setNumCollect(Integer numCollect) {
		this.numCollect = numCollect;
	}

	public Float getDegreeOfPraise() {
		return degreeOfPraise;
	}

	public void setDegreeOfPraise(Float degreeOfPraise) {
		this.degreeOfPraise = degreeOfPraise;
	}

	public Integer getNumPraise() {
		return numPraise;
	}

	public void setNumPraise(Integer numPraise) {
		this.numPraise = numPraise;
	}

	public Integer getNumTrample() {
		return numTrample;
	}

	public void setNumTrample(Integer numTrample) {
		this.numTrample = numTrample;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

}