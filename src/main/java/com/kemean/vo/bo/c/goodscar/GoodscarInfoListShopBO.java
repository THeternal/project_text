package com.kemean.vo.bo.c.goodscar;

import java.util.List;

import com.kemean.vo.bo.KemeanIdBO;

public class GoodscarInfoListShopBO extends KemeanIdBO {

	private Integer userShopId;

	private String shopName;

	private String shopLogo;

	private List<GoodscarInfoListBO> goodsCarInfoList;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
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

	public List<GoodscarInfoListBO> getGoodsCarInfoList() {
		return goodsCarInfoList;
	}

	public void setGoodsCarInfoList(List<GoodscarInfoListBO> goodsCarInfoList) {
		this.goodsCarInfoList = goodsCarInfoList;
	}

}
