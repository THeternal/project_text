package com.kemean.vo.bo.c.order;

import java.util.List;

public class GetPaidOrderBO {

	/**
	 * 用户头像
	 */
	private String headImg;

	/**
	 * 用户昵称
	 */
	private String nickName;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 支付价格
	 */
	private Double pricePay;

	/**
	 * 商品信息
	 */
	private List<GetPaidOrderGoodsBO> getPaidOrderGoodsBO;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public List<GetPaidOrderGoodsBO> getGetPaidOrderGoodsBO() {
		return getPaidOrderGoodsBO;
	}

	public void setGetPaidOrderGoodsBO(List<GetPaidOrderGoodsBO> getPaidOrderGoodsBO) {
		this.getPaidOrderGoodsBO = getPaidOrderGoodsBO;
	}

}
