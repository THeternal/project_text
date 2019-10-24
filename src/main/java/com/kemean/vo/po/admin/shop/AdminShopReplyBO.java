package com.kemean.vo.po.admin.shop;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminShopReplyBO extends KemeanIdBO {
	/**
	 * 回复内容
	 */
	private String content;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
