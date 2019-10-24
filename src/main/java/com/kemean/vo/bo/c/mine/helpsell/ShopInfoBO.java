package com.kemean.vo.bo.c.mine.helpsell;

import com.kemean.vo.bo.KemeanIdBO;

public class ShopInfoBO extends KemeanIdBO {

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 商铺图片
	 */
	private String img;

	/**
	 * 销量
	 */
	private Integer salesVolume;

	/**
	 * 赞
	 */
	private Integer numPraise;

	/**
	 * 踩
	 */
	private Integer numTrample;

	/**
	 * 好评率
	 */
	private Float degreeOfPraise;

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

	public Float getDegreeOfPraise() {
		return degreeOfPraise;
	}

	public void setDegreeOfPraise(Float degreeOfPraise) {
		this.degreeOfPraise = degreeOfPraise;
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
