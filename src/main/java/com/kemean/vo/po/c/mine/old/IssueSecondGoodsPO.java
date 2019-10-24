package com.kemean.vo.po.c.mine.old;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class IssueSecondGoodsPO {

	private Integer objId;

	@NotBlank(message = "标题不能为空")
	private String title;

	private String descStr;

	@Size(min = 1, max = 7, message = "必须上传一张封面图，不能大于六张")
	private List<String> imgsHead;

	@NotNull(message = "原价不能为空")
	private Double priceOriginal;

	@NotNull(message = "售价不能为空")
	private Double priceSales;

	private Double redAfter;

	private Double redBefore;

	@NotNull(message = "成色不能为空")
	private Float quality;

	@NotNull(message = "分类id不能为空")
	private Integer categoryId;

	@NotNull(message = "帮代卖不能为空")
	private Boolean purchasing;

	private Double pricePurchasing;

	@NotBlank(message = "商品地址不能为空")
	private String goodsAddress;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescStr() {
		return descStr;
	}

	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}

	public List<String> getImgsHead() {
		return imgsHead;
	}

	public void setImgsHead(List<String> imgsHead) {
		this.imgsHead = imgsHead;
	}

	public Double getPriceOriginal() {
		return priceOriginal;
	}

	public void setPriceOriginal(Double priceOriginal) {
		this.priceOriginal = priceOriginal;
	}

	public Double getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(Double priceSales) {
		this.priceSales = priceSales;
	}

	public Double getRedAfter() {
		return redAfter;
	}

	public void setRedAfter(Double redAfter) {
		this.redAfter = redAfter;
	}

	public Double getRedBefore() {
		return redBefore;
	}

	public void setRedBefore(Double redBefore) {
		this.redBefore = redBefore;
	}

	public Float getQuality() {
		return quality;
	}

	public void setQuality(Float quality) {
		this.quality = quality;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public String getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

}
