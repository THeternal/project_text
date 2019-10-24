package com.kemean.vo.bo.b.goods;

import com.kemean.vo.bo.KemeanIdBO;

public class GoodsListBO extends KemeanIdBO {

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 封面轮播图-json
	 */
	private String headImg;

	/**
	 * 上下架状态,针对全局SKU
	 */
	private Boolean goodsStatus;

	/**
	 * 销量
	 */
	private Integer salesNum;

	/**
	 * 最低售价
	 */
	private Double minPriceSales;

	/**
	 * 最低门市价
	 */
	private Double minPriceStore;

	/**
	 * 审核状态
	 */
	private Integer auditStatus;

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Double getMinPriceSales() {
		return minPriceSales;
	}

	public void setMinPriceSales(Double minPriceSales) {
		this.minPriceSales = minPriceSales;
	}

	public Double getMinPriceStore() {
		return minPriceStore;
	}

	public void setMinPriceStore(Double minPriceStore) {
		this.minPriceStore = minPriceStore;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

}
