package com.kemean.vo.po.b.goods;

import javax.validation.constraints.NotNull;

public class OperationGoodsPO {

	@NotNull(message = "商品id")
	private Integer objId;

	@NotNull(message = "操作类型不能为空")
	private Integer type;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
