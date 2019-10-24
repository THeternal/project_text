package com.kemean.vo.po.b.order;

import org.hibernate.validator.constraints.NotBlank;

import com.kemean.vo.po.KemeanPageApiPO;

public class SearchOrderPO extends KemeanPageApiPO {

	@NotBlank(message = "搜索信息不能为空")
	private String searchInfo;

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

}
