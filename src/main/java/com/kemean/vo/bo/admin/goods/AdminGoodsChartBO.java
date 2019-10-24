package com.kemean.vo.bo.admin.goods;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminGoodsChartBO {

	/// ===========商品收入报表=======
	// 销售数量
	private List<AdminChartBO> saleNum;
	// 销售额
	private List<AdminChartBO> saleMoney;

	// ======== 商品红包报表=========
	// 售前红包
	private List<AdminChartBO> redBeforeMoney;
	// 售后红包
	private List<AdminChartBO> redAfterMoney;
	// =========商品总销售============
	// 销售数量、销售额
	// 代卖佣金、售前红包、售后红包金额
	private List<AdminChartBO> pricePurchasing;
	// ==========商品统计==============
	// 商品数量
	private List<AdminChartBO> goodsNum;
	// 下架数量
	private List<AdminChartBO> goodsStatusNum;
	// 在售数量= 销售数

	// ========商品成交报表=========
	// 成交数= 销售数
	// 退货数
	private List<AdminChartBO> goodsReturnNum;

	// ===========商品类型统计========

	public List<AdminChartBO> getGoodsReturnNum() {
		return goodsReturnNum;
	}

	public void setGoodsReturnNum(List<AdminChartBO> goodsReturnNum) {
		this.goodsReturnNum = goodsReturnNum;
	}

	public List<AdminChartBO> getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(List<AdminChartBO> pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public List<AdminChartBO> getSaleNum() {
		return saleNum;
	}

	public List<AdminChartBO> getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(List<AdminChartBO> goodsNum) {
		this.goodsNum = goodsNum;
	}

	public List<AdminChartBO> getGoodsStatusNum() {
		return goodsStatusNum;
	}

	public void setGoodsStatusNum(List<AdminChartBO> goodsStatusNum) {
		this.goodsStatusNum = goodsStatusNum;
	}

	public void setSaleNum(List<AdminChartBO> saleNum) {
		this.saleNum = saleNum;
	}

	public List<AdminChartBO> getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(List<AdminChartBO> saleMoney) {
		this.saleMoney = saleMoney;
	}

	public List<AdminChartBO> getRedBeforeMoney() {
		return redBeforeMoney;
	}

	public void setRedBeforeMoney(List<AdminChartBO> redBeforeMoney) {
		this.redBeforeMoney = redBeforeMoney;
	}

	public List<AdminChartBO> getRedAfterMoney() {
		return redAfterMoney;
	}

	public void setRedAfterMoney(List<AdminChartBO> redAfterMoney) {
		this.redAfterMoney = redAfterMoney;
	}

}
