package com.kemean.vo.bo.c.common;

public class ComLunboBO {

	/**
	 * 推荐方式(商铺1101，商品1201)
	 */
	private Integer recommendWay;

	/**
	 * 推荐类型（时长1101，点击1201，替补1301）
	 */
	private Integer recommendType;

	/**
	 * 图片链接
	 */
	private String imgUrl;

	/**
	 * 商铺or商品id
	 */
	private Integer typeId;

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

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
