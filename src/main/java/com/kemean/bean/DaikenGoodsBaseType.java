package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_goods_base_type")
public class DaikenGoodsBaseType extends KemeanAbstractBaseBean {

	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}