package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_goods_hot_treasure")
public class DaikenGoodsHotTreasure extends KemeanAbstractBaseBean {

	/**
	 * 商品id
	 */
	@Column(name = "goods_id")
	private Integer goodsId;

	@Column(name = "goods_title")
	private String goodsTitle;

	/**
	 * 屏幕记录
	 */
	@Column(name = "screen_position")
	private Integer screenPosition;

	/**
	 * 购买用户联系电话
	 */
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 * 购买点击次数
	 */
	@Column(name = "buy_click_num")
	private Integer buyClickNum;

	/**
	 * 点击次数
	 */
	@Column(name = "click_num")
	private Integer clickNum;

	/**
	 * 结束状态(1101未开始，1201进行中，1301已结束)
	 */
	@Column(name = "current_state")
	private Integer currentState;

	/**
	 * 上下架状态,true上架，false下架
	 */
	@Column(name = "status")
	private Boolean status;

	/**
	 * 商铺id 0-平台设置、补充广告
	 */
	@Column(name = "shop_id")
	private Integer shopId;

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getScreenPosition() {
		return screenPosition;
	}

	public void setScreenPosition(Integer screenPosition) {
		this.screenPosition = screenPosition;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getBuyClickNum() {
		return buyClickNum;
	}

	public void setBuyClickNum(Integer buyClickNum) {
		this.buyClickNum = buyClickNum;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}