package com.kemean.vo.bo.com;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class MessageBO extends KemeanIdBO {

	/**
	 * 消息类型
	 */
	private Integer messageType;

	/**
	 * 消息标题
	 */
	private String messageTitle;

	/**
	 * 消息记录,根据type不同而不同
	 */
	private String messageRecord;

	/**
	 * 是否已读
	 */
	private Boolean readed;

	@JsonFormat(pattern = "MM-dd", timezone = "GMT+8")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageRecord() {
		return messageRecord;
	}

	public void setMessageRecord(String messageRecord) {
		this.messageRecord = messageRecord;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

}
