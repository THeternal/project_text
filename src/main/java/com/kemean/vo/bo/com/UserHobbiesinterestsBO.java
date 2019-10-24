package com.kemean.vo.bo.com;

import com.kemean.vo.bo.KemeanIdBO;

public class UserHobbiesinterestsBO extends KemeanIdBO {

	private Integer pid;

	private String content;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
