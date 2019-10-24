package com.kemean.vo.po.b.reply;

import org.hibernate.validator.constraints.NotBlank;

public class FastReplyPO {

	private Integer objId;

	@NotBlank(message = "回复内容不能为空")
	private String content;

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
