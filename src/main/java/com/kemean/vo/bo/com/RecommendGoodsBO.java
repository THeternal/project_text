package com.kemean.vo.bo.com;

import com.kemean.vo.bo.KemeanIdBO;

public class RecommendGoodsBO extends KemeanIdBO {

	/**
	 * 业务id（商铺，商品）
	 */
	private Integer typeId;

	/**
	 * 推荐方式(商铺1101，商品1201)
	 */
	private Integer recommendWay;

	/**
	 * 显示图片
	 */
	private String img;

	public Integer getRecommendWay() {
		return recommendWay;
	}

	public void setRecommendWay(Integer recommendWay) {
		this.recommendWay = recommendWay;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
