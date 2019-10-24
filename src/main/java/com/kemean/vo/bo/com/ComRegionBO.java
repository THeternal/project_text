package com.kemean.vo.bo.com;

import com.kemean.vo.bo.KemeanIdBO;

public class ComRegionBO extends KemeanIdBO {
	private Integer pid;
	private String name;
	private Integer level;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
