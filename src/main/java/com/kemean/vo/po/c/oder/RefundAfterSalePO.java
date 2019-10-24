package com.kemean.vo.po.c.oder;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RefundAfterSalePO {

	@NotBlank(message = "订单编号不能为空")
	private String orderNo;

	@NotNull(message = "商品id不能为空")
	private Integer goodsId;

	@NotBlank(message = "退款商品货号不能为空")
	private String skuNo;

	@NotNull(message = "商品状态不能为空")
	private Boolean goodsStatus;

	private String refundReason;

	private List<String> uploadDocumentsImg;

	@NotNull(message = "售后商品数量不能为空")
	private Integer goodsNum;

	@NotNull(message = "退款金额不能为空")
	private Double refundMoney;

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
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

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public List<String> getUploadDocumentsImg() {
		return uploadDocumentsImg;
	}

	public void setUploadDocumentsImg(List<String> uploadDocumentsImg) {
		this.uploadDocumentsImg = uploadDocumentsImg;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

}
