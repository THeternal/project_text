package com.kemean.vo.po.c.goodscar;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubmitOrderInCarPO {

	@NotNull(message = "收货地址不能为空")
	private Integer addressId;

	@Size(min = 1, message = "最少得购买一个物品")
	private List<SubmitOrderInShopPO> shopIds;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public List<SubmitOrderInShopPO> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<SubmitOrderInShopPO> shopIds) {
		this.shopIds = shopIds;
	}

}
