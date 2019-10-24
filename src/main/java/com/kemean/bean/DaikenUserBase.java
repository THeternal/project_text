package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_user_base")
public class DaikenUserBase extends KemeanAbstractBaseBean {

	/**
	 * 父级id
	 */
	@Column(name = "pid")
	private Integer pid;

	/**
	 * 区分类型（兴趣爱好，职业）
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}