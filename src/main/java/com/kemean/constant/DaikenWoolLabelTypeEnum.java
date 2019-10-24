package com.kemean.constant;

/**
 * 撸羊毛类型
 * 
 * @Date 2018年7月12日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public enum DaikenWoolLabelTypeEnum {

	/** 正常状态 **/
	ORDINARY(1101),
	/** 今天不允许发商品红包 **/
	TODAY_REPETITION(1102),
	/** 禁用一周 **/
	FORBIDDEN_ONE_WEEK(1103),
	/** 永久禁用 **/
	FORBIDDEN_PERPETUAL(1104);
	private Integer type;

	private DaikenWoolLabelTypeEnum(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
