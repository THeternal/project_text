package com.kemean.vo.po.admin.sales;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class AdminSalesUserOperatePO {

	private Integer objId;
	/**
	 * 唯一标识(代研号)
	 */
	private Integer uid;

	/**
	 * 人员等级(4-销售公司，3-销售总监，2-销售主管，1-销售经理)
	 */

	@NotNull(message = "人员等级不能为空")
	private Integer level;

	/**
	 * 昵称
	 */
	@NotBlank(message = "昵称不能为空")
	private String nickName;

	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机格式不正确")
	private String phone;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * true-男 false-女
	 */
	@NotNull(message = "用户性别不能为空")
	private Boolean sexMan;

	/**
	 * 用户状态
	 */
	@NotNull(message = "用户状态不能为空")
	private Boolean userStatus;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

}
