package com.kemean.vo.po.admin.shop;

import javax.validation.constraints.NotNull;

public class AdminShopAuditStatusPO {
	@NotNull(message = "商铺Id不能为空")
	private Integer shopId;
	@NotNull(message = "状态不能为空")
	private Integer status;

	// 审核失败的原因
	private String auditCause;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditCause() {
		return auditCause;
	}

	public void setAuditCause(String auditCause) {
		this.auditCause = auditCause;
	}

}
