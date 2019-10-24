package com.kemean.vo.po.admin.goods;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminGoodsPrecisionPO extends KemeanPageAdminPO {

	private Boolean isPrecision;
	private Integer goodsId;

	public Boolean getIsPrecision() {
		return isPrecision;
	}

	public void setIsPrecision(Boolean isPrecision) {
		this.isPrecision = isPrecision;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

}
