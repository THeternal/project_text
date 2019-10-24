package com.kemean.vo.po.com;

import com.kemean.vo.po.KemeanPageApiPO;

public class FinanceOrderListPO extends KemeanPageApiPO {

	/**
	 * 查看天数(默认显示7天)，1个月之内，半年，一年
	 */
	private Integer days;

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

}
