package com.kemean.vo.bo.com;

import java.util.List;

import com.kemean.vo.bo.KemeanIdBO;

public class GetBaseCategoryBO extends KemeanIdBO {

	private String name;

	private Integer pid;

	private Integer level;

	private String img;

	private List<GetBaseCategoryBO> getBaseCategoryBO;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<GetBaseCategoryBO> getGetBaseCategoryBO() {
		return getBaseCategoryBO;
	}

	public void setGetBaseCategoryBO(List<GetBaseCategoryBO> getBaseCategoryBO) {
		this.getBaseCategoryBO = getBaseCategoryBO;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
