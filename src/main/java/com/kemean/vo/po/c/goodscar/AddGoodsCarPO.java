package com.kemean.vo.po.c.goodscar;

import javax.validation.constraints.NotNull;

public class AddGoodsCarPO {

	/**
	 * 商品id
	 */
	@NotNull(message = "商品id不能为空")
	private Integer goodsId;

	private String skuNo;

	@NotNull(message = "收藏数量不能为空")
	private Integer addQuantity;

	@NotNull(message = "区分一手、二手")
	private Boolean isNewGoods;

	private Integer userShopId;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Boolean getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(Boolean isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Integer getAddQuantity() {
		return addQuantity;
	}

	public void setAddQuantity(Integer addQuantity) {
		this.addQuantity = addQuantity;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

}
