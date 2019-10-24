package com.kemean.vo.bo.admin.shop;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;
import com.kemean.vo.bo.b.order.OrderListGoodsBO;

public class AdminShopOrderBO extends KemeanIdBO {
	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 购买用户id
	 */
	private Integer idUser;

	/**
	 * 用户uid
	 */
	private Integer userUid;
	/**
	 * 商铺id
	 */
	private Integer idShop;
	/**
	 * 订单类型
	 */
	private String typeStr;

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 购买商品名称
	 */
	private String goodsTitles;

	/**
	 * 订单状态-商家
	 */
	private String statusShopStr;

	/**
	 * 订单状态-商家
	 */
	private Integer statusShop;

	/**
	 * 订单状态-用户
	 */
	private String statusUserStr;

	/**
	 * 订单状态-用户
	 */
	private Integer statusUser;

	/**
	 * 用户手机号
	 */
	private String userPhone;

	/**
	 * 商品购买数量
	 */
	private Integer quantity;

	/**
	 * 购买商品
	 */
	private List<OrderListGoodsBO> goods;

	/**
	 * 支付价格
	 */
	private Double pricePay;

	/**
	 * 优惠价格
	 */
	private Double priceDiscount;

	/**
	 * 订单总价
	 */
	private Double priceTotal;

	/**
	 * 邮费
	 */
	private Double postage;

	/**
	 * 支付类型
	 */
	private String payTypeStr;

	/**
	 * 收货地址
	 */
	private String recordReceiving;

	/**
	 * 快递公司编号
	 */
	private String companyNo;

	/**
	 * 快递公司名称
	 */
	private String companyName;

	/**
	 * 快递单号
	 */
	private String expressWaybillNo;

	/**
	 * 发货时间
	 */
	private Date sendTime;

	/**
	 * 收货时间
	 */
	private Date receiveGoodsTime;

	/**
	 * 备注
	 */
	private String remark;

	// 售后信息
	private AdminShopReturnBO goodsReturn;

	/**
	 * 商品规格sku
	 */
	private String skuNo;

	private Integer goodsId;

	/**
	 * 商品状态（true-收到货物，false-没有收到）
	 */
	private String goodsStatusStr;

	/**
	 * 退款件数
	 */
	private Integer goodsNum;

	/**
	 * 退款金额
	 */
	private Double refundMoney;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date submitTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Integer getStatusShop() {
		return statusShop;
	}

	public void setStatusShop(Integer statusShop) {
		this.statusShop = statusShop;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getIdShop() {
		return idShop;
	}

	public void setIdShop(Integer idShop) {
		this.idShop = idShop;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getGoodsStatusStr() {
		return goodsStatusStr;
	}

	public void setGoodsStatusStr(String goodsStatusStr) {
		this.goodsStatusStr = goodsStatusStr;
	}

	public AdminShopReturnBO getGoodsReturn() {
		return goodsReturn;
	}

	public void setGoodsReturn(AdminShopReturnBO goodsReturn) {
		this.goodsReturn = goodsReturn;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<OrderListGoodsBO> getGoods() {
		return goods;
	}

	public void setGoods(List<OrderListGoodsBO> goods) {
		this.goods = goods;
	}

	public String getRecordReceiving() {
		return recordReceiving;
	}

	public void setRecordReceiving(String recordReceiving) {
		this.recordReceiving = recordReceiving;
	}

	public String getGoodsTitles() {
		return goodsTitles;
	}

	public void setGoodsTitles(String goodsTitles) {
		this.goodsTitles = goodsTitles;
	}

	public Integer getUserUid() {
		return userUid;
	}

	public void setUserUid(Integer userUid) {
		this.userUid = userUid;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getStatusShopStr() {
		return statusShopStr;
	}

	public void setStatusShopStr(String statusShopStr) {
		this.statusShopStr = statusShopStr;
	}

	public String getStatusUserStr() {
		return statusUserStr;
	}

	public void setStatusUserStr(String statusUserStr) {
		this.statusUserStr = statusUserStr;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPricePay() {
		return pricePay;
	}

	public void setPricePay(Double pricePay) {
		this.pricePay = pricePay;
	}

	public Double getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(Double priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public String getPayTypeStr() {
		return payTypeStr;
	}

	public void setPayTypeStr(String payTypeStr) {
		this.payTypeStr = payTypeStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(Integer statusUser) {
		this.statusUser = statusUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
