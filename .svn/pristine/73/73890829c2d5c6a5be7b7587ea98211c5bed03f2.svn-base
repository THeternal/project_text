package com.kemean.vo.po.admin.goods;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AdminGoodsCategoryAddPO {

	private Integer objId;
	/**
	 * 名称
	 */

	@NotBlank(message = "名称不能为空")
	private String name;

	/**
	 * 父级id
	 */

	@NotNull(message = "父级不能为空")
	private Integer pid;

	/**
	 * 关联规格表id-json
	 */

	private String recordTypeId;

	/**
	 * 级别
	 */
	@NotNull(message = "级别不能为空")
	private Integer level;

	/**
	 * 图片
	 */
	private String img;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
