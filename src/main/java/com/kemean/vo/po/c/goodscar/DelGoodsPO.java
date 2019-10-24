package com.kemean.vo.po.c.goodscar;

import java.util.List;

import javax.validation.constraints.Size;

public class DelGoodsPO {

	@Size(min = 1, message = "删除购物车Id不能为空")
	private List<Integer> carIds;

	public List<Integer> getCarIds() {
		return carIds;
	}

	public void setCarIds(List<Integer> carIds) {
		this.carIds = carIds;
	}

}
