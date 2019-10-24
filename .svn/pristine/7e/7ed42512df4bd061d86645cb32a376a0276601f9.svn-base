package com.kemean.vo.po.c.mall;

import javax.validation.constraints.NotNull;

import com.kemean.vo.po.KemeanPageApiPO;

public class GoodsAppraisalListPO extends KemeanPageApiPO {

	/**
	 * 区分是一手还是二手商品
	 */
	@NotNull(message = "获取类型不能为空")
	private Boolean isNewGoods;

	/**
	 * 如果是商品就传商品id
	 */
	@NotNull(message = "商品id不能为空")
	private Integer objId;

	private Integer appraisalType;

	public Integer getAppraisalType() {
		return appraisalType;
	}

	public void setAppraisalType(Integer appraisalType) {
		this.appraisalType = appraisalType;
	}

	public Boolean getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(Boolean isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

}
