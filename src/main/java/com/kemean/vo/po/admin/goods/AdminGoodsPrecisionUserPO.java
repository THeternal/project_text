package com.kemean.vo.po.admin.goods;

import javax.validation.constraints.NotNull;

import com.kemean.vo.po.admin.investigate.AdminMatchConditionPO;

public class AdminGoodsPrecisionUserPO {

	@NotNull(message = "商品Id不能为空")
	private Integer goodsId;

	@NotNull(message = "上限金额不能为空")
	private Double maxMoney;

	/**
	 * 匹配条件
	 */
	@NotNull(message = "匹配条件不能为 空")
	private AdminMatchConditionPO matchCondition;

	/**
	 * 商品分类id
	 */
	@NotNull(message = "商品分类id不能为空")
	private Integer categoryId;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Double maxMoney) {
		this.maxMoney = maxMoney;
	}

	public AdminMatchConditionPO getMatchCondition() {
		return matchCondition;
	}

	public void setMatchCondition(AdminMatchConditionPO matchCondition) {
		this.matchCondition = matchCondition;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
