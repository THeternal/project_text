package com.kemean.vo.po.admin.investigate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AdminInvestOptionsPO {

	@NotNull(message = "objId不能为空")
	private Integer objId;

	@NotBlank(message = "选手的姓名不能为空")
	private String name;
	/**
	 * 选手说明
	 * 
	 */
	@NotBlank(message = "选手说明不能为空")
	private String explainStr;

	/**
	 * 获得票数
	 */
	@NotNull(message = "选手获取的票数不能为空")
	private Integer votes;

	/**
	 * 人气
	 */
	@NotNull(message = "选手的人气数不能为空")
	private Integer popularity;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplainStr() {
		return explainStr;
	}

	public void setExplainStr(String explainStr) {
		this.explainStr = explainStr;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

}
