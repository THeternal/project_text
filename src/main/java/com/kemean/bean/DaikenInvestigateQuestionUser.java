package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_investigate_question_user")
public class DaikenInvestigateQuestionUser extends KemeanAbstractBaseBean {

	/**
	 * 与调研表关联
	 */
	@Column(name = "investigate_id")
	private Integer investigateId;

	/**
	 * 与用户表关联
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
	 * 昵称
	 */
	@Column(name = "nick_name")
	private String nickName;

	/**
	 * 是否完成,false-未完成，true完成
	 */
	@Column(name = "is_finished")
	private Boolean isFinished;

	public Integer getInvestigateId() {
		return investigateId;
	}

	public void setInvestigateId(Integer investigateId) {
		this.investigateId = investigateId;
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

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

}