package com.kemean.vo.bo.com;

public class HelpSellRuleBO {

	/**
	 * 新手奖励token
	 */
	private Double awardToken;

	/**
	 * 新手奖励余额
	 */
	private Double awardMoney;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * Html
	 */
	private String contentHtml;

	public Double getAwardToken() {
		return awardToken;
	}

	public void setAwardToken(Double awardToken) {
		this.awardToken = awardToken;
	}

	public Double getAwardMoney() {
		return awardMoney;
	}

	public void setAwardMoney(Double awardMoney) {
		this.awardMoney = awardMoney;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

}
