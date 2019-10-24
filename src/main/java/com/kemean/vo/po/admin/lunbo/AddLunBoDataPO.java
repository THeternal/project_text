package com.kemean.vo.po.admin.lunbo;

public class AddLunBoDataPO {

	/**
	 * 购买时间段
	 */
	private Integer recommendTypeSele;

	/**
	 * 展示位置
	 */
	private Integer locatType;

	/**
	 * 商铺 or 商铺id
	 */
	private Integer typeId;

	/**
	 * 推荐方式
	 */
	private Integer recommendWay;

	/**
	 * 推荐类型
	 */
	private Integer recommendType;

	/**
	 * 图片
	 */
	private String imgUrl;

	/**
	 * 购买天数
	 */
	private Integer buyDays;

	/**
	 * 购买点击次数
	 */
	private Integer buyClickNum;

	public Integer getBuyDays() {
		return buyDays;
	}

	public void setBuyDays(Integer buyDays) {
		this.buyDays = buyDays;
	}

	public Integer getBuyClickNum() {
		return buyClickNum;
	}

	public void setBuyClickNum(Integer buyClickNum) {
		this.buyClickNum = buyClickNum;
	}

	public Integer getRecommendTypeSele() {
		return recommendTypeSele;
	}

	public void setRecommendTypeSele(Integer recommendTypeSele) {
		this.recommendTypeSele = recommendTypeSele;
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

}
