package com.kemean.vo.bo.admin.goods;

public class AdminGoodsMarketingBO {
	// 销量
	private Integer saleNum;
	// 销售额
	private Double salePrice;
	// 访问量
	private Integer accessNum;
	// 成交量【商品】
	private Integer clinchNum;

	// 成交人数
	private Integer clinchUserNum;

	// 当前购买人数
	private Integer buyUserNum;

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getAccessNum() {
		return accessNum;
	}

	public Integer getBuyUserNum() {
		return buyUserNum;
	}

	public void setBuyUserNum(Integer buyUserNum) {
		this.buyUserNum = buyUserNum;
	}

	public void setAccessNum(Integer accessNum) {
		this.accessNum = accessNum;
	}

	public Integer getClinchNum() {
		return clinchNum;
	}

	public void setClinchNum(Integer clinchNum) {
		this.clinchNum = clinchNum;
	}

	public Integer getClinchUserNum() {
		return clinchUserNum;
	}

	public void setClinchUserNum(Integer clinchUserNum) {
		this.clinchUserNum = clinchUserNum;
	}

}
