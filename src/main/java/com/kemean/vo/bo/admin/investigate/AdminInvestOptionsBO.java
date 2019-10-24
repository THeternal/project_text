package com.kemean.vo.bo.admin.investigate;

import com.kemean.vo.bo.KemeanIdBO;

public class AdminInvestOptionsBO extends KemeanIdBO {

	/**
	 * 调研表id
	 */
	private Integer investigateId;

	/**
	 * 选手名称
	 */
	private String name;

	/**
	 * 选手图片
	 */
	private String img;

	/**
	 * 选手说明
	 */
	private String explainStr;

	/**
	 * 获得票数
	 */
	private Integer votes;

	/**
	 * 人气
	 */
	private Integer popularity;

	public Integer getInvestigateId() {
		return investigateId;
	}

	public void setInvestigateId(Integer investigateId) {
		this.investigateId = investigateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
