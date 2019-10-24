package com.kemean.bean;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "daiken_goods_base_category")
public class DaikenGoodsBaseCategory extends KemeanAbstractBaseBean {

	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 父级id
	 */
	@Column(name = "pid")
	private Integer pid;

	/**
	 * 关联规格表id-json
	 */
	@Column(name = "record_type_id")
	private String recordTypeId;

	/**
	 * 级别
	 */
	@Column(name = "level")
	private Integer level;

	/**
	 * 图片
	 */
	@Column(name = "img")
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

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

}