package com.kemean.vo.po.c.mall;

import javax.validation.constraints.NotNull;

import com.kemean.vo.po.KemeanPageApiPO;

public class GoodsInfoPO extends KemeanPageApiPO {

	@NotNull(message = "是否是代卖商铺不能为空")
	private Integer userShopId;

	@NotNull(message = "商铺id不能为空")
	private Integer shopId;

	private Integer categoryId;

	private String title;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
