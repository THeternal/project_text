package com.kemean.vo.po.b.goods;

import com.kemean.vo.po.KemeanPageApiPO;

public class GoodsListPO extends KemeanPageApiPO {

	private Integer salesType;

	private String title;

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
