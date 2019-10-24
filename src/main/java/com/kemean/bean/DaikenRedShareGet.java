package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_red_share_get")
public class DaikenRedShareGet extends KemeanAbstractBaseBean {

	/**
	 * 分享token
	 */
	@Column(name = "token")
	private String token;

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
	 * 红包金额
	 */
	@Column(name = "money")
	private Double money;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}