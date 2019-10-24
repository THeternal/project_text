package com.kemean.vo.po.c.investigate;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class PostVotePlyearPO {

	/**
	 * 调研id
	 */
	private Integer investigateId;

	private Integer objId;

	/**
	 * 选手名称
	 */
	@NotBlank(message = "选手名称不能为空")
	private String name;

	/**
	 * 选手图片
	 */
	@Size(min = 1, max = 6, message = "最少上传一张选手图片,不能大于六张")
	private List<String> img;

	/**
	 * 选手说明
	 */
	@NotBlank(message = "选手说明不能为空")
	private String explainStr;

	public Integer getInvestigateId() {
		return investigateId;
	}

	public void setInvestigateId(Integer investigateId) {
		this.investigateId = investigateId;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

	public String getExplainStr() {
		return explainStr;
	}

	public void setExplainStr(String explainStr) {
		this.explainStr = explainStr;
	}

}
