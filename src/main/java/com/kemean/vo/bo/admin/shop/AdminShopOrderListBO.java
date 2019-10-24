package com.kemean.vo.bo.admin.shop;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;
import com.kemean.vo.bo.ThirdLogisticsInfoDataBO;

public class AdminShopOrderListBO extends KemeanIdBO {

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 商品名称
	 */
	private String goodsTitles;

	/**
	 * 商品编号
	 */
	private String goodsUid;

	/**
	 * 商品SKU编号
	 */
	private String skuNo;
	/**
	 * 商品分类名称
	 */
	private String categoryName;

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 代卖商铺名称
	 */
	private String purchasingShopName;

	/**
	 * 订单状态
	 */
	private Integer statusShop;

	/**
	 * 订单状态
	 */
	private String statusShopStr;

	/**
	 * 商品购买量
	 */
	private Integer quantity;

	/**
	 * 红包成本：售前红包加售后红包
	 */
	private Double redBeforeAndAfter;

	/**
	 * 售前红包
	 */
	private Double redBefore;

	/**
	 * 售后红包
	 */
	private Double redAfter;

	/**
	 * 实际转账金额
	 */
	private Double realMoney;

	/**
	 * 售卖类型
	 */
	private String saleTypeStr;

	/**
	 * 代卖成本：代卖提成
	 */
	private Double pricePurchasing;

	/**
	 * 补偿金额
	 */
	private Double compensateMoney;

	/**
	 * 用户手机号
	 */
	private String userPhone;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 购买用户名称
	 */
	private String userName;

	/**
	 * 购买用户信息 (微信用户名、收货人、收货地址、电话）
	 */
	private String userRecord;
	/**
	 * 收货地址-json
	 */
	private String recordReceiving;

	/**
	 * 物流信息-json数组
	 */
	private List<ThirdLogisticsInfoDataBO> recordLogistics;

	/**
	 * 销售金额：商品销售价
	 */
	private Double salesPrice;

	/**
	 * 实际销售额：支付价格
	 */
	private Double pricePay;

	/**
	 * 订单时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getStatusShopStr() {
		return statusShopStr;
	}

	public void setStatusShopStr(String statusShopStr) {
		this.statusShopStr = statusShopStr;
	}

	public Double getCompensateMoney() {
		return compensateMoney;
	}

	public void setCompensateMoney(Double compensateMoney) {
		this.compensateMoney = compensateMoney;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUserRecord() {
		return userRecord;
	}

	public void setUserRecord(String userRecord) {
		this.userRecord = userRecord;
	}

	public String getSaleTypeStr() {
		return saleTypeStr;
	}

	public void setSaleTypeStr(String saleTypeStr) {
		this.saleTypeStr = saleTypeStr;
	}

	public String getGoodsTitles() {
		return goodsTitles;
	}

	public String getPurchasingShopName() {
		return purchasingShopName;
	}

	public void setPurchasingShopName(String purchasingShopName) {
		this.purchasingShopName = purchasingShopName;
	}

	public void setGoodsTitles(String goodsTitles) {
		this.goodsTitles = goodsTitles;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStatusShop() {
		return statusShop;
	}

	public void setStatusShop(Integer statusShop) {
		this.statusShop = statusShop;
	}

	public Double getRedBefore() {
		return redBefore;
	}

	public void setRedBefore(Double redBefore) {
		this.redBefore = redBefore;
	}

	public Double getRedAfter() {
		return redAfter;
	}

	public void setRedAfter(Double redAfter) {
		this.redAfter = redAfter;
	}

	public String getGoodsUid() {
		return goodsUid;
	}

	public void setGoodsUid(String goodsUid) {
		this.goodsUid = goodsUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public Double getRedBeforeAndAfter() {
		return redBeforeAndAfter;
	}

	public void setRedBeforeAndAfter(Double redBeforeAndAfter) {
		this.redBeforeAndAfter = redBeforeAndAfter;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Double getPricePay() {
		return pricePay;
	}

	public void setPricePay(Double pricePay) {
		this.pricePay = pricePay;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecordReceiving() {
		return recordReceiving;
	}

	public void setRecordReceiving(String recordReceiving) {
		this.recordReceiving = recordReceiving;
	}

	public List<ThirdLogisticsInfoDataBO> getRecordLogistics() {
		return recordLogistics;
	}

	public void setRecordLogistics(List<ThirdLogisticsInfoDataBO> recordLogistics) {
		this.recordLogistics = recordLogistics;
	}

}
