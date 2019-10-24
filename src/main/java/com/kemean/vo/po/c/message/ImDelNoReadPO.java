package com.kemean.vo.po.c.message;

import javax.validation.constraints.NotNull;

public class ImDelNoReadPO {

	@NotNull(message = "发送人uid")
	private Integer fromUserId;

	@NotNull(message = "接收人uid")
	private Integer toUserId;

	@NotNull(message = "是否删除不能为空")
	private Boolean del;

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

}
