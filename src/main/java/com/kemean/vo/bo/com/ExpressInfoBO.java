package com.kemean.vo.bo.com;

import java.util.List;

import com.kemean.vo.bo.c.order.OrderListGoodsBO;

public class ExpressInfoBO {

	/**
	 * 快递公司名称
	 */
	private String companyName;

	/**
	 * 快递单号
	 */
	private String expressWaybillNo;

	/**
	 * 物品信息
	 */
	private List<OrderListGoodsBO> orderListGoods;

	/**
	 * 物流信息
	 */
	private List<TracesBO> traces;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getExpressWaybillNo() {
		return expressWaybillNo;
	}

	public void setExpressWaybillNo(String expressWaybillNo) {
		this.expressWaybillNo = expressWaybillNo;
	}

	public List<OrderListGoodsBO> getOrderListGoods() {
		return orderListGoods;
	}

	public void setOrderListGoods(List<OrderListGoodsBO> orderListGoods) {
		this.orderListGoods = orderListGoods;
	}

	public List<TracesBO> getTraces() {
		return traces;
	}

	public void setTraces(List<TracesBO> traces) {
		this.traces = traces;
	}

}
