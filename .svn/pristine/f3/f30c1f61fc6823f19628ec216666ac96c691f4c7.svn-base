package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_red_share")
public class DaikenRedShare extends KemeanAbstractBaseBean {

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 用户昵称
	 */
	@Column(name = "user_nick_name")
	private String userNickName;

	/**
	 * 分享唯一标识
	 */
	@Column(name = "token")
	private String token;

	/**
	 * 类型
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 商铺id（二手为0）
	 */
	@Column(name = "shop_id")
	private Integer shopId;

	/**
	 * 类型id
	 */
	@Column(name = "type_id")
	private Integer typeId;

	/**
	 * 商品标题
	 */
	@Column(name = "goods_title")
	private String goodsTitle;

	/**
	 * 类型版本号
	 */
	@Column(name = "type_date_version")
	private Integer typeDateVersion;

	/**
	 * 是否接触撸羊毛标识，取决于领取红包用户下单
	 */
	@Column(name = "relieve_wool")
	private Boolean relieveWool;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeDateVersion() {
		return typeDateVersion;
	}

	public void setTypeDateVersion(Integer typeDateVersion) {
		this.typeDateVersion = typeDateVersion;
	}

	public Boolean getRelieveWool() {
		return relieveWool;
	}

	public void setRelieveWool(Boolean relieveWool) {
		this.relieveWool = relieveWool;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

}