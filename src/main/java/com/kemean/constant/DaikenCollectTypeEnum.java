package com.kemean.constant;

/**
 * 收藏类型
 * 
 * @Date 2018年6月25日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public enum DaikenCollectTypeEnum {

	/** 店铺收藏（一手） **/
	SHOP_COLLECT(1101),
	/** 帮卖店铺收藏 **/
	HELP_SELL_SHOP_COLLECT(1102),
	/** 一手商品收藏 **/
	NEW_GOODS_COLLECT(1201),
	/** 二手商品收藏 **/
	SECOND_GOODS_COLLECT(1301);
	private Integer type;

	private DaikenCollectTypeEnum(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
