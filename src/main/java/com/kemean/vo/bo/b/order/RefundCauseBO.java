package com.kemean.vo.bo.b.order;

import java.util.List;

public class RefundCauseBO {

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 用户手机号
	 */
	private String userPhone;

	/**
	 * 是否过了七天
	 */
	private Boolean isSevenDay;

	/**
	 * 退款商品信息
	 */
	private List<RefundCauseGoodsBO> refundCauseGoodsBO;

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

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public List<RefundCauseGoodsBO> getRefundCauseGoodsBO() {
		return refundCauseGoodsBO;
	}

	public void setRefundCauseGoodsBO(List<RefundCauseGoodsBO> refundCauseGoodsBO) {
		this.refundCauseGoodsBO = refundCauseGoodsBO;
	}

}
