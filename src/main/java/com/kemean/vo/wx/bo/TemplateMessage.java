package com.kemean.vo.wx.bo;

import java.util.Map;
import java.util.TreeMap;

/**
 * 注册成功，通知模板消息实体类
 */
public class TemplateMessage {

	/**
	 * 模板id
	 */
	private String templateId;

	/**
	 * 接收者
	 */
	private String touser;

	/**
	 * 模板跳转链接
	 */
	private String url;

	/**
	 * data数据
	 */
	private String topcolor;

	private Map<String, TreeMap<String, TemplateData>> data;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Map<String, TreeMap<String, TemplateData>> getData() {
		return data;
	}

	public void setData(Map<String, TreeMap<String, TemplateData>> data) {
		this.data = data;
	}

}
