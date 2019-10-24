package com.kemean.vo.bo.b.order;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderListBO {

	/**
	 * 下单用户
	 */
	private String userName;

	/**
	 * 用户uid
	 */
	private Integer uidUser;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 订单日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 收货人姓名
	 */
	private String receiveName;

	/**
	 * 收货人电话
	 */
	private String phone;

	/**
	 * 收货地址
	 */
	private String receiveAddress;

	/**
	 * 购买商品
	 */
	private List<OrderListGoodsBO> goods;

	/**
	 * 邮费
	 */
	private Double postage;

	/**
	 * 支付价格
	 */
	private Double pricePay;

	/**
	 * 订单状态
	 */
	private Integer statusShop;

	/**
	 * 订单状态
	 */
	private String statusShopStr;

	/**
	 * 快递公司编号
	 */
	private String companyNo;

	/**
	 * 快递单号
	 */
	private String expressWaybillNo;

	/**
	 * 留言
	 */
	private String remark;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getExpressWaybillNo() {
		return expressWaybillNo;
	}

	public void setExpressWaybillNo(String expressWaybillNo) {
		this.expressWaybillNo = expressWaybillNo;
	}

	public Integer getUidUser() {
		return uidUser;
	}

	public void setUidUser(Integer uidUser) {
		this.uidUser = uidUser;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public String getStatusShopStr() {
		return statusShopStr;
	}

	public void setStatusShopStr(String statusShopStr) {
		this.statusShopStr = statusShopStr;
	}

	public Integer getStatusShop() {
		return statusShop;
	}

	public void setStatusShop(Integer statusShop) {
		this.statusShop = statusShop;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public List<OrderListGoodsBO> getGoods() {
		return goods;
	}

	public void setGoods(List<OrderListGoodsBO> goods) {
		this.goods = goods;
	}

	public Double getPricePay() {
		return pricePay;
	}

	public void setPricePay(Double pricePay) {
		this.pricePay = pricePay;
	}

}
