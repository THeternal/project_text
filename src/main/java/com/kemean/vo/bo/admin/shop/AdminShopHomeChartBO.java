package com.kemean.vo.bo.admin.shop;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminShopHomeChartBO {
	// 余额
	private Double balancePrice;

	// 订单数量
	private List<AdminChartBO> orderNum;

	// 销售的产品数量
	private List<AdminChartBO> goodsNum;

	// 销售额
	private List<AdminChartBO> orderPrice;

	// 销售利润
	private List<AdminChartBO> orderProfit;

	public Double getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public List<AdminChartBO> getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(List<AdminChartBO> orderNum) {
		this.orderNum = orderNum;
	}

	public List<AdminChartBO> getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(List<AdminChartBO> goodsNum) {
		this.goodsNum = goodsNum;
	}

	public List<AdminChartBO> getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(List<AdminChartBO> orderPrice) {
		this.orderPrice = orderPrice;
	}

	public List<AdminChartBO> getOrderProfit() {
		return orderProfit;
	}

	public void setOrderProfit(List<AdminChartBO> orderProfit) {
		this.orderProfit = orderProfit;
	}

}
