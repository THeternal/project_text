package com.kemean.vo.bo.c.order;

import java.util.List;

public class RefundCauseBO {

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 商家手机号
	 */
	private Integer shopUid;

	/**
	 * 是否过了七天
	 */
	private Boolean isSevenDay;

	/**
	 * 退款商品信息
	 */
	private List<RefundCauseGoodsBO> refundCauseGoodsBO;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Boolean getIsSevenDay() {
		return isSevenDay;
	}

	public void setIsSevenDay(Boolean isSevenDay) {
		this.isSevenDay = isSevenDay;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getShopUid() {
		return shopUid;
	}

	public void setShopUid(Integer shopUid) {
		this.shopUid = shopUid;
	}

	public List<RefundCauseGoodsBO> getRefundCauseGoodsBO() {
		return refundCauseGoodsBO;
	}

	public void setRefundCauseGoodsBO(List<RefundCauseGoodsBO> refundCauseGoodsBO) {
		this.refundCauseGoodsBO = refundCauseGoodsBO;
	}

}
