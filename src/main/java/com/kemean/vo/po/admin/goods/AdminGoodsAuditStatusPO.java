package com.kemean.vo.po.admin.goods;

import javax.validation.constraints.NotNull;

public class AdminGoodsAuditStatusPO {

	@NotNull(message = "商品Id不能为空")
	private Integer goodsId;

	@NotNull(message = "状态不能为空")
	private Integer status;

	// 审核失败的原因
	private String auditCause;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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
