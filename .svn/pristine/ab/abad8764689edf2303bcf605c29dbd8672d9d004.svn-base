package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_goods_purchasing")
public class DaikenGoodsPurchasing extends KemeanAbstractBaseBean {

	/**
	 * 用户id
	 */
	@Column(name = "user_shop_id")
	private Integer userShopId;

	/**
	 * false-新品 true-二手
	 */
	@Column(name = "old")
	private Boolean old;

	/**
	 * 商品id
	 */
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 * 商品信息-json
	 */
	@Column(name = "record_goods")
	private String recordGoods;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Boolean getOld() {
		return old;
	}

	public void setOld(Boolean old) {
		this.old = old;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getRecordGoods() {
		return recordGoods;
	}

	public void setRecordGoods(String recordGoods) {
		this.recordGoods = recordGoods;
	}

}