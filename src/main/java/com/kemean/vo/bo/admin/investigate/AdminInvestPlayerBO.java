package com.kemean.vo.bo.admin.investigate;

import java.util.List;

public class AdminInvestPlayerBO {
	private String name;
	private String explainStr;
	private List<String> imgs;

	public AdminInvestPlayerBO() {
		super();
	}

	public AdminInvestPlayerBO(String name, String explainStr, List<String> imgs) {
		super();
		this.name = name;
		this.explainStr = explainStr;
		this.imgs = imgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplainStr() {
		return explainStr;
	}

	public void setExplainStr(String explainStr) {
		this.explainStr = explainStr;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

}
