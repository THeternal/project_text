package com.kemean.vo.bo.admin.platform;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminPromotionBO extends KemeanIdBO {

	private Integer type;
	private String shopName;
	private String typeStr;
	private Integer salesNum;
	private String title;
	private Boolean goodsStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 折扣开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date discountTimeBegin;

	/**
	 * 折扣结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date discountTimeEnd;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public Date getDiscountTimeBegin() {
		return discountTimeBegin;
	}

	public void setDiscountTimeBegin(Date discountTimeBegin) {
		this.discountTimeBegin = discountTimeBegin;
	}

	public Date getDiscountTimeEnd() {
		return discountTimeEnd;
	}

	public void setDiscountTimeEnd(Date discountTimeEnd) {
		this.discountTimeEnd = discountTimeEnd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

}
