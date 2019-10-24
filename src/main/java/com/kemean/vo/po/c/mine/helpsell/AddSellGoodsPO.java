package com.kemean.vo.po.c.mine.helpsell;

import javax.validation.constraints.NotNull;

public class AddSellGoodsPO {

	/**
	 * 1101添加 1201 结束
	 */
	@NotNull(message = "操作类型不能为空")
	private Integer operationType;

	@NotNull(message = "商品id不能为空")
	private Integer objId;

	@NotNull(message = "是否为二手不能为空")
	private Boolean isOld;

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}

}
