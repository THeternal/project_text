package com.kemean.vo.bo.admin.shop;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminShopReturnBO extends KemeanIdBO {

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 店铺名称
	 */
	private String shopName;

	/**
	 * 商品名称
	 */
	private String goodsTitle;

	/**
	 * 商品id
	 */
	private Integer goodsId;

	/**
	 * 商品规格sku
	 */
	private String skuNo;

	/**
	 * 商品状态（true-收到货物，false-没有收到）
	 */
	private String goodsStatusStr;

	/**
	 * 退款件数
	 */
	private Integer goodsNum;

	/**
	 * 退款原因
	 */
	private String refundReason;

	/**
	 * 退款凭证图片
	 */
	private List<String> refundImg;

	/**
	 * 退款金额
	 */
	private Double refundMoney;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 退款状态
	 */
	private String refundStatus;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getGoodsStatusStr() {
		return goodsStatusStr;
	}

	public void setGoodsStatusStr(String goodsStatusStr) {
		this.goodsStatusStr = goodsStatusStr;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public List<String> getRefundImg() {
		return refundImg;
	}

	public void setRefundImg(List<String> refundImg) {
		this.refundImg = refundImg;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

}
