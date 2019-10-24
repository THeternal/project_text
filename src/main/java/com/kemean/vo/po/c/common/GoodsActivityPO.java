package com.kemean.vo.po.c.common;

import javax.validation.constraints.NotNull;

import com.kemean.vo.po.KemeanPageApiPO;

public class GoodsActivityPO extends KemeanPageApiPO {

	@NotNull(message = "商品售卖类型不能为空")
	private Integer salesType;

	private String keyWord;

	private Integer categoryId;

	private Double minPriceSales;

	private Double maxPriceSales;

	private Boolean isHot;

	private Boolean redPacket;

	private Boolean discount;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getDiscount() {
		return discount;
	}

	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}

	public Double getMinPriceSales() {
		return minPriceSales;
	}

	public void setMinPriceSales(Double minPriceSales) {
		this.minPriceSales = minPriceSales;
	}

	public Double getMaxPriceSales() {
		return maxPriceSales;
	}

	public void setMaxPriceSales(Double maxPriceSales) {
		this.maxPriceSales = maxPriceSales;
	}

	public Boolean getRedPacket() {
		return redPacket;
	}

	public void setRedPacket(Boolean redPacket) {
		this.redPacket = redPacket;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

}
