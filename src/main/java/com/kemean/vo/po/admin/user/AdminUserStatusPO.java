package com.kemean.vo.po.admin.user;

import javax.validation.constraints.NotNull;

public class AdminUserStatusPO {
	@NotNull(message = "objId不能为空")
	private Integer objId;

	private String cause;

	@NotNull(message = "状态不能为空")
	private Boolean status;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
