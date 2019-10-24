package com.kemean.vo.bo.admin.goods;

import com.kemean.vo.bo.KemeanIdBO;

public class AdminGoodsNewCategoryBO extends KemeanIdBO {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 父级id
	 */
	private Integer pid;

	/**
	 * 级别
	 */
	private Integer level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
