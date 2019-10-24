package com.kemean.vo.po.admin.goods;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminGoodsCategoryPO extends KemeanPageAdminPO {
	private String name;

	private Integer level;

	private Integer pid;

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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
