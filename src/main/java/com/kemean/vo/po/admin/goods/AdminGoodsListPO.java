package com.kemean.vo.po.admin.goods;

import com.kemean.vo.po.KemeanPageAdminPO;

public class AdminGoodsListPO extends KemeanPageAdminPO {

	private String title;
	private Integer salesType;
	private String goodsNo;
	private String skuNo;
	private Integer shopId;
	private Integer auditStatus;
	// 商品是否营销
	private Boolean marketing;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Boolean getMarketing() {
		return marketing;
	}

	public void setMarketing(Boolean marketing) {
		this.marketing = marketing;
	}

}
