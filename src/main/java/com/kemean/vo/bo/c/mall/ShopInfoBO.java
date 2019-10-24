package com.kemean.vo.bo.c.mall;

public class ShopInfoBO {

	/**
	 * 户主uid（方便融云通信）
	 */
	private Integer shopUserUid;

	/**
	 * 帮卖商铺id
	 */
	private Integer userShopId;

	/**
	 * 店铺id
	 */
	private Integer shopId;

	/**
	 * 店铺头像
	 */
	private String shopLogo;

	/**
	 * 店铺名称
	 */
	private String shopName;

	/**
	 * 销量
	 */
	private Integer salesVolume;

	/**
	 * 联系电话
	 */
	private String shopPhone;

	/**
	 * 收藏
	 */
	private Integer numCollect;

	/**
	 * 赞
	 */
	private Integer numPraise;

	/**
	 * 踩
	 */
	private Integer numTrample;

	/**
	 * 好评率（百分比形式）
	 */
	private Float degreeOfPraise;

	/**
	 * 公司名称
	 */
	private String businessLicenseName;

	/**
	 * 经营场所
	 */
	private String businessLicenseLocation;

	/**
	 * 是否收藏
	 */
	private Boolean isCollect;

	/**
	 * 店铺分享图片
	 */
	private String shopShareImg;

	public String getShopShareImg() {
		return shopShareImg;
	}

	public void setShopShareImg(String shopShareImg) {
		this.shopShareImg = shopShareImg;
	}

	public Integer getShopUserUid() {
		return shopUserUid;
	}

	public void setShopUserUid(Integer shopUserUid) {
		this.shopUserUid = shopUserUid;
	}

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Boolean getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public Integer getNumCollect() {
		return numCollect;
	}

	public void setNumCollect(Integer numCollect) {
		this.numCollect = numCollect;
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

	public Float getDegreeOfPraise() {
		return degreeOfPraise;
	}

	public void setDegreeOfPraise(Float degreeOfPraise) {
		this.degreeOfPraise = degreeOfPraise;
	}

	public String getBusinessLicenseName() {
		return businessLicenseName;
	}

	public void setBusinessLicenseName(String businessLicenseName) {
		this.businessLicenseName = businessLicenseName;
	}

	public String getBusinessLicenseLocation() {
		return businessLicenseLocation;
	}

	public void setBusinessLicenseLocation(String businessLicenseLocation) {
		this.businessLicenseLocation = businessLicenseLocation;
	}

}
