package com.kemean.bean;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "daiken_history_search")
public class DaikenHistorySearch extends KemeanAbstractBaseBean {

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 搜索内容
	 */
	@Column(name = "content")
	private String content;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}