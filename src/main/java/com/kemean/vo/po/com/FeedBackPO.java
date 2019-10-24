package com.kemean.vo.po.com;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class FeedBackPO {

	@NotNull(message = "是否需要联系不能为空")
	private Boolean needContact;

	private String phone;

	@NotBlank(message = "反馈内容不能为空")
	private String feedbackContent;

	public Boolean getNeedContact() {
		return needContact;
	}

	public void setNeedContact(Boolean needContact) {
		this.needContact = needContact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

}
