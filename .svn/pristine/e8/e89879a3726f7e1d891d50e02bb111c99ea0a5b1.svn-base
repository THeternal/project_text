package com.kemean.bean;

import java.util.Date;

import javax.persistence.*;

@Table(name = "daiken_order_after_sale")
public class DaikenOrderAfterSale extends KemeanAbstractBaseBean {

	/**
	 * 订单编号
	 */
	@Column(name = "order_no")
	private String orderNo;

	/**
	 * 退款商品id
	 */
	@Column(name = "goods_id")
	private Integer goodsId;

	/**
	 * 商品状态（true-收到货物，false-没有收到）
	 */
	@Column(name = "goods_status")
	private Boolean goodsStatus;

	/**
	 * 退款件数
	 */
	@Column(name = "goods_num")
	private Integer goodsNum;

	/**
	 * 退款原因
	 */
	@Column(name = "refund_reason")
	private String refundReason;
	
	/**
	 * 退款凭证图片
	 */
	@Column(name = "refund_img")
	private String refundImg;

	/**
	 * 退款金额
	 */
	@Column(name = "refund_money")
	private Double refundMoney;

	/**
	 * 退款状态
	 */
	@Column(name = "refund_status")
	private Integer refundStatus;
	
	/**
	 * 补偿金额
	 */
	@Column(name = "compensate_money")
	private Double compensateMoney;
	
	/**
	 * 补偿时间
	 */
	@Column(name = "compensate_time")
	private Date compensateTime;

	/**
	 * 退款商品货号
	 */
	@Column(name = "sku_no")
	private String skuNo;

	/**
	 * 退款快递公司信息
	 */
	@Column(name = "refund_record_logistics")
	private String refundRecordLogistics;

	public String getRefundRecordLogistics() {
		return refundRecordLogistics;
	}

	public void setRefundRecordLogistics(String refundRecordLogistics) {
		this.refundRecordLogistics = refundRecordLogistics;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
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

	public String getRefundImg() {
		return refundImg;
	}

	public void setRefundImg(String refundImg) {
		this.refundImg = refundImg;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Double getCompensateMoney() {
		return compensateMoney;
	}

	public void setCompensateMoney(Double compensateMoney) {
		this.compensateMoney = compensateMoney;
	}

	public Date getCompensateTime() {
		return compensateTime;
	}

	public void setCompensateTime(Date compensateTime) {
		this.compensateTime = compensateTime;
	}

}