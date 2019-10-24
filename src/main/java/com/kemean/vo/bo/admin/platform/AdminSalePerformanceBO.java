package com.kemean.vo.bo.admin.platform;

import com.kemean.vo.bo.KemeanIdBO;

public class AdminSalePerformanceBO extends KemeanIdBO {
	/**
	 * 唯一标识(代研号)
	 */
	private Integer uid;

	/**
	 */
	private Integer level;

	private String levelStr;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 对应店铺
	 */
	private Integer shopCount;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLevelStr() {
		return levelStr;
	}

	public void setLevelStr(String levelStr) {
		this.levelStr = levelStr;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getShopCount() {
		return shopCount;
	}

	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}

}
