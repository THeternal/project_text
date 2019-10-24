package com.kemean.vo.bo.admin.goods;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminGoodsPurchasingBO extends KemeanIdBO {

	// 帮买商铺名称
	private String userShopName;
	// 代卖佣金
	private Double purchasingPrice;
	// 销售量
	private Integer salesNum;
	// 销售额
	private Double salesPirce;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public String getUserShopName() {
		return userShopName;
	}

	public void setUserShopName(String userShopName) {
		this.userShopName = userShopName;
	}

	public Double getPurchasingPrice() {
		return purchasingPrice;
	}

	public void setPurchasingPrice(Double purchasingPrice) {
		this.purchasingPrice = purchasingPrice;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Double getSalesPirce() {
		return salesPirce;
	}

	public void setSalesPirce(Double salesPirce) {
		this.salesPirce = salesPirce;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
