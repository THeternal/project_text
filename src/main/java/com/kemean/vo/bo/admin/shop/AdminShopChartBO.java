package com.kemean.vo.bo.admin.shop;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminShopChartBO {

	// 个人商铺数量
	private List<AdminChartBO> userShopNum;
	// 企业商铺数量
	private List<AdminChartBO> companyShopNum;

	// 售前红包
	private List<AdminChartBO> redBeforeMoney;

	// 售后红包
	private List<AdminChartBO> redAfterMoney;

	// 代卖佣金
	private List<AdminChartBO> purchasingMoney;

	public List<AdminChartBO> getUserShopNum() {
		return userShopNum;
	}

	public void setUserShopNum(List<AdminChartBO> userShopNum) {
		this.userShopNum = userShopNum;
	}

	public List<AdminChartBO> getCompanyShopNum() {
		return companyShopNum;
	}

	public void setCompanyShopNum(List<AdminChartBO> companyShopNum) {
		this.companyShopNum = companyShopNum;
	}

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
