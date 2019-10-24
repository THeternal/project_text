package com.kemean.vo.bo.admin.sales;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminSalesUserBO extends KemeanIdBO {

	/**
	 * 唯一标识(代研号)
	 */
	private Integer uid;

	/**
	 * 人员等级(4-销售公司，3-销售总监，2-销售主管，1-销售经理)
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
	 * true-男 false-女
	 */
	private Boolean sexMan;

	private String sexManStr;

	private String password;

	/**
	 * 用户状态
	 */
	private Integer userStatus;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
	}

	public String getSexManStr() {
		return sexManStr;
	}

	public void setSexManStr(String sexManStr) {
		this.sexManStr = sexManStr;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

}
