package com.kemean.vo.po.admin.shop;

import javax.validation.constraints.NotNull;

public class AdminOrderExportExcelPO {
	
	@NotNull(message = "文件名称不能为空")
	private String fileName;
	
	/**
	 * 订单编号
	 */
	private String orderNo;
	
	/**
	 * 商品编号
	 */
	private String goodsUid;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 开始日期
	 */
	private String startDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsUid() {
		return goodsUid;
	}

	public void setGoodsUid(String goodsUid) {
		this.goodsUid = goodsUid;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
