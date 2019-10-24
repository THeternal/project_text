package com.kemean.vo.bo.c.order;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderInfoBO {

	/**
	 * 订单编号
	 */
	private String orderNo;

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
	private String address;

	/**
	 * 店铺id
	 */
	private Integer shopId;

	/**
	 * 帮卖商铺id
	 */
	private Integer userShopId;

	/**
	 * 店铺logo
	 */
	private String shopLogo;

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 商铺店主uid
	 */
	private String userShopUid;

	/**
	 * 购买商品
	 */
	private List<OrderListGoodsBO> orderListGoods;

	/**
	 * 订单状态
	 */
	private Integer statusUser;

	/**
	 * 订单状态字符
	 */
	private String statusUserStr;

	/**
	 * 邮费
	 */
	private Double postage;

	/**
	 * 支付价格
	 */
	private Double pricePay;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 付款时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date paymentTime;

	/**
	 * 发货时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date sendTime;

	/**
	 * 收货时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receiveGoodsTime;

	/**
	 * 订单购买物品数量
	 */
	private Integer quantity;

	/**
	 * 是否是一手订单 true-一手订单，false-二手订单
	 */
	private Boolean isNewGoods;

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public String getUserShopUid() {
		return userShopUid;
	}

	public void setUserShopUid(String userShopUid) {
		this.userShopUid = userShopUid;
	}

	public Boolean getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(Boolean isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReceiveGoodsTime() {
		return receiveGoodsTime;
	}

	public void setReceiveGoodsTime(Date receiveGoodsTime) {
		this.receiveGoodsTime = receiveGoodsTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<OrderListGoodsBO> getOrderListGoods() {
		return orderListGoods;
	}

	public void setOrderListGoods(List<OrderListGoodsBO> orderListGoods) {
		this.orderListGoods = orderListGoods;
	}

	public Integer getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(Integer statusUser) {
		this.statusUser = statusUser;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Double getPricePay() {
		return pricePay;
	}

	public void setPricePay(Double pricePay) {
		this.pricePay = pricePay;
	}

	public String getStatusUserStr() {
		return statusUserStr;
	}

	public void setStatusUserStr(String statusUserStr) {
		this.statusUserStr = statusUserStr;
	}

}
