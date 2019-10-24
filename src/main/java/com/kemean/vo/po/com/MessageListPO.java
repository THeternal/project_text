package com.kemean.vo.po.com;

import com.kemean.vo.po.KemeanPageApiPO;

public class MessageListPO extends KemeanPageApiPO {
	private Boolean readed;

	private Integer messageType;

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}
}
