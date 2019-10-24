package com.kemean.vo.po.c.mall;

import javax.validation.constraints.NotNull;

public class ShopInfoPO {

	@NotNull(message = "是否为帮卖商铺不能为空")
	private Integer userShopId;

	@NotNull(message = "商铺id不能为空")
	private Integer shopId;

	private Integer recommendLocal;

	public Integer getRecommendLocal() {
		return recommendLocal;
	}

	public void setRecommendLocal(Integer recommendLocal) {
		this.recommendLocal = recommendLocal;
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

}
