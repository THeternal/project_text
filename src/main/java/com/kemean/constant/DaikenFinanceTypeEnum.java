package com.kemean.constant;

public enum DaikenFinanceTypeEnum {

	/** 【小程序】调研点赞（收入） **/
	C_INVESTIGATE_LIKE(1101),
	/** 【小程序】调研投票（收入） **/
	C_INVESTIGATE_VOTE(1102),
	/** 【小程序】调研问卷（收入） **/
	C_INVESTIGATE_QUESTION(1103),
	/** 【小程序】二手卖货（收入） **/
	C_SELL_GOODS(1104),
	/** 【小程序】售前红包（收入） **/
	C_BEFOR_RED_INCOME(1105),
	/** 【小程序】售后红包（收入） **/
	C_AFTER_RED_INCOME(1106),
	/** 【小程序】帮卖（收入） **/
	C_HELLP_SELL_GOODS(1107),
	/** 【小程序】在线支付，购买商品（支出） **/
	C_LINE_PAY_BUY_GOODS(1208),
	/** 【小程序】在线支付，发布调研（支出） **/
	C_LINE_PAY_INVESTIGATE(1209),
	/** 【小程序】提现（支出） **/
	C_CASH_USER(1210),
	/** 【商家端】 卖货收入（收入） **/
	B_SELL_GOODS(2101),
	/** 【商家端】充值（收入） **/
	B_RECHARGE_MONEY(2102),
	/** 【商家端】发布调研（支出） **/
	B_LINE_PAY_INVESTIGATE(2203),
	/** 【商家端】轮播图广告（支出） **/
	B_LUNBO_ADVERTISING(2204),
	/** 【商家端】首页推荐弹窗广告（支出） **/
	B_RECOMMEND_ADVERTISING(2205),
	/** 【商家端】首页推荐屏位宝贝（支出） **/
	B_RECOMMEND_TREASURE(2206),
	/** 【商家端】 商户商品精准投递费用（支出） **/
	B_SHOP_PUSH_GOODS(2207),
	/** 【商家端】 售前红包（支出） **/
	B_AFTER_RED_INCOME(2208),
	/** 【商家端】 售后红包（支出） **/
	B_LATER_RED_INCOME(2209),
	/** 【商家端】帮卖（支出） **/
	B_HELP_SALL_GOODS(2210),
	/** 【商家端】提现（支出） **/
	B_CASH_SHOP(2211),
	/** 【平台】充值（收入） **/
	RECHARGE(3101),
	/** 【平台】 收费（发布调研，精准推送） **/
	CHARGE(3102);
	private Integer type;

	private DaikenFinanceTypeEnum(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
