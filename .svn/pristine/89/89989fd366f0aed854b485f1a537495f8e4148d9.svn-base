package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_goods_recommend_user")
public class DaikenGoodsRecommendUser extends KemeanAbstractBaseBean {

	/**
	 * 商品id
	 */
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 手机号
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * true-男 false-女
	 */
	@Column(name = "sex_man")
	private Boolean sexMan;

	/**
	 * true--精准匹配 false--随机匹配
	 */
	@Column(name = "is_precision")
	private Boolean isPrecision;

	/**
	 * 昵称
	 */
	@Column(name = "nick_name")
	private String nickName;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Boolean getIsPrecision() {
		return isPrecision;
	}

	public void setIsPrecision(Boolean isPrecision) {
		this.isPrecision = isPrecision;
	}

}