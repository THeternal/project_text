package com.kemean.vo.bo.c.collect;

import com.kemean.vo.bo.KemeanIdBO;

public class CollectListBO extends KemeanIdBO {

	/**
	 * 帮卖商铺id
	 */
	private Integer userShopId;

	/**
	 * 业务Id（商铺、商品）
	 */
	private Integer typeId;

	/**
	 * 跳转类型 1101一手商品 1102 二手商品 1103店铺
	 */
	private Integer jumpType;

	/**
	 * 照片
	 */
	private String headImg;

	/**
	 * 商品名称/商铺名称
	 */
	private String name;

	/**
	 * 一手商品的skuNo信息
	 */
	private String recordType;

	/**
	 * 门市价
	 */
	private Double priceStore;

	/**
	 * 销售价
	 */
	private Double priceSales;

	/**
	 * 商铺销量
	 */
	private Integer salesVolume;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Double getPriceStore() {
		return priceStore;
	}

	public void setPriceStore(Double priceStore) {
		this.priceStore = priceStore;
	}

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
