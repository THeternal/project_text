package com.kemean.vo.po.c.common;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.kemean.vo.po.KemeanPageApiPO;

public class SearchGoodsPO extends KemeanPageApiPO {

	/**
	 * 1101 一手所有，1201二手，1301 一手帮卖
	 */
	@NotNull(message = "搜索类型不能为空")
	private Integer searchType;

	@NotBlank(message = "搜索内容不能为空")
	private String searchInfo;

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

}
