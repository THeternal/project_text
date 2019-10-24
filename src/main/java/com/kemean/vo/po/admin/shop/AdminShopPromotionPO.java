package com.kemean.vo.po.admin.shop;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminShopPromotionPO extends KemeanPageAdminPO {

	private String name;
	private String dateStart;
	private String dateEnd;
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
