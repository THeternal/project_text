package com.kemean.vo.bo.admin.goods;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminGoodsCategoryBO extends KemeanIdBO {
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 父级id
	 */
	private Integer pid;

	/**
	 * 关联规格表id-json
	 */
	private List<Integer> recordTypeId;

	/**
	 * 级别
	 */
	private Integer level;

	private String levelStr;

	/**
	 * 图片
	 */
	private String img;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

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

	public List<Integer> getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(List<Integer> recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLevelStr() {
		return levelStr;
	}

	public void setLevelStr(String levelStr) {
		this.levelStr = levelStr;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
