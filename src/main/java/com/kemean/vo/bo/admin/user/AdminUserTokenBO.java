package com.kemean.vo.bo.admin.user;

import java.util.List;

import com.kemean.vo.bo.admin.AdminChartBO;

public class AdminUserTokenBO {

	private List<AdminChartBO> consumerToken;

	private List<AdminChartBO> businessToken;

	public List<AdminChartBO> getConsumerToken() {
		return consumerToken;
	}

	public void setConsumerToken(List<AdminChartBO> consumerToken) {
		this.consumerToken = consumerToken;
	}

	public List<AdminChartBO> getBusinessToken() {
		return businessToken;
	}

	public void setBusinessToken(List<AdminChartBO> businessToken) {
		this.businessToken = businessToken;
	}

}
