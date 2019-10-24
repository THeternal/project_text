package com.kemean.vo.po.c.common;

import javax.validation.constraints.NotNull;

public class ShareRedPacketPO {

	@NotNull(message = "是否为一手商品")
	private Boolean isNewGoods;

	@NotNull(message = "业务id不能为空")
	private Integer typeId;

	public Boolean getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(Boolean isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
