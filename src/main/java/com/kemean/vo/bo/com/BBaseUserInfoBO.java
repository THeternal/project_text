package com.kemean.vo.bo.com;

public class BBaseUserInfoBO {

	private String uid;

	/**
	 * token
	 */
	private String token;

	/**
	 * 融云token
	 */
	private String tokenRy;

	/**
	 * 用户头像
	 */
	private String headImg;

	/**
	 * token积分（钱币）
	 */
	private Double balanceToken;

	/**
	 * 余额
	 */
	private Double balancePrice;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 登陆校验
	 */
	private Integer check;

	/**
	 * 审核状态
	 */
	private Integer auditStatus;

	/**
	 * 客服uid
	 */
	private Integer serviceUserUid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTokenRy() {
		return tokenRy;
	}

	public void setTokenRy(String tokenRy) {
		this.tokenRy = tokenRy;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getServiceUserUid() {
		return serviceUserUid;
	}

	public void setServiceUserUid(Integer serviceUserUid) {
		this.serviceUserUid = serviceUserUid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Double getBalanceToken() {
		return balanceToken;
	}

	public void setBalanceToken(Double balanceToken) {
		this.balanceToken = balanceToken;
	}

	public Double getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}