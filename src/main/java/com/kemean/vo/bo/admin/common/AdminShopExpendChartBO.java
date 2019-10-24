package com.kemean.vo.bo.admin.common;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminShopExpendChartBO {

	// 售前红包
	private List<AdminChartBO> redBeforeMoney;

	// 售后红包
	private List<AdminChartBO> redAfterMoney;

	// 代卖佣金
	private List<AdminChartBO> purchasingMoney;

	public List<AdminChartBO> getPurchasingMoney() {
		return purchasingMoney;
	}

	public void setPurchasingMoney(List<AdminChartBO> purchasingMoney) {
		this.purchasingMoney = purchasingMoney;
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
