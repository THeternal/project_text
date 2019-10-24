package com.kemean.vo.po.c.investigate;

import javax.validation.constraints.NotNull;

public class LikePO {

	@NotNull(message = "调研id不能为空")
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
