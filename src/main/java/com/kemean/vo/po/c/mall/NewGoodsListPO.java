package com.kemean.vo.po.c.mall;

import com.kemean.vo.po.KemeanPageApiPO;

public class NewGoodsListPO extends KemeanPageApiPO {

	private String keyWord;

	private Integer categoryId;

	private Double minPriceSales;

	private Double maxPriceSales;

	private Boolean isHot;

	private Boolean redPacket;

	private Boolean discount;

	private Boolean purchasing;

	private Boolean isPricePurchasing;

	public Boolean getIsPricePurchasing() {
		return isPricePurchasing;
	}

	public void setIsPricePurchasing(Boolean isPricePurchasing) {
		this.isPricePurchasing = isPricePurchasing;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getRedPacket() {
		return redPacket;
	}

	public void setRedPacket(Boolean redPacket) {
		this.redPacket = redPacket;
	}

	public Boolean getDiscount() {
		return discount;
	}

	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}

}
