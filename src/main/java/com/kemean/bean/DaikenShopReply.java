package com.kemean.bean;

import javax.persistence.*;

@Table(name = "daiken_shop_reply")
public class DaikenShopReply extends KemeanAbstractBaseBean {

	/**
	 * 与商铺关联id
	 */
	@Column(name = "shop_id")
	private Integer shopId;

	/**
	 * 回复内容
	 */
	@Column(name = "content")
	private String content;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}