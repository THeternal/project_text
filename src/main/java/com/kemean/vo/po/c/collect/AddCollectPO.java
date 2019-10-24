package com.kemean.vo.po.c.collect;

import java.util.List;

import javax.validation.constraints.NotNull;

public class AddCollectPO {

	@NotNull(message = "操作类型不能为空")
	private Integer type;

	@NotNull(message = "收藏类型id不能为空")
	private List<Integer> typeId;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Integer> getTypeId() {
		return typeId;
	}

	public void setTypeId(List<Integer> typeId) {
		this.typeId = typeId;
	}

}
