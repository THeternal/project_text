package com.kemean.vo.po.c.mine.old;

import javax.validation.constraints.NotNull;

public class UpdateStatusSecondhandPO {

	@NotNull(message = "操作对象id不能为空")
	private Integer objId;

	@NotNull(message = "操作类型不能为空")
	private Integer type;

	@NotNull(message = "操作状态不能为空")
	private Boolean status;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
