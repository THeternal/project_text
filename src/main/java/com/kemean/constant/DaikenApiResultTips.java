package com.kemean.constant;

public class DaikenApiResultTips {

	public class CODE_CHECK {
		public static final String CODE_NOT_EXIT = "验证码已过期";
		public static final String CODE_ERROR = "验证码错误";
	}

	public class SendCode {
		public static final String TYPE_INVALID = "验证码类型无效";
		public static final String PHONE_REGISTERED = "该手机号已注册";
		public static final String PHONE_NOT_REGISTERED = "该手机号尚未注册";
		public static final String GET_FAILURE = "获取失败";
		public static final String GET_SUCCESS = "获取成功";
		public static final String BINDING_SUCCESS = "openId绑定成功";
		public static final String BINDING_ERROR = "openId绑定失败,该账号已有openId";
	}

	public class Login {
		public static final String NO_REGISTERED_PHONE = "该手机号尚未注册";
		public static final String ACCOUNT_DISABLED = "该账号已禁用";
		public static final String PASSWORD_ERROR = "密码错误";
		public static final String LOGIN_TYPE_FAILURE = "登录类型校验失败";
		public static final int LOGIN_CHECK_PASS = 3000;// 校验正常
		public static final int LOGIN_CHECK_WX = 3001;// 微信尚未绑定
		public static final int LOGIN_CHECK_QQ = 3002;// QQ尚未绑定
	}

	public class GoodsCar {
		public static final String ADD_GOODS_CAR_SUCCESS = "添加物品到购物车成功";
		public static final String ADD_GOODS_CAR_ERROR = "添加物品到购物车失败";
		public static final String DEL_GOODS_CAR_SUCCESS = "删除在购物车中物品成功";
		public static final String DEL_GOODS_CAR_ERROR = "删除在购物车中物品失败";
		public static final String UPDATE_ADD_GOODS_CAR_SUCCESS = "修改购物车数量添加成功";
		public static final String UPDATE_DEDUCT_GOODS_CAR_SUCCESS = "修改购物车数量减少成功";
		public static final String UPDATE_GOODS_CAR_ERROR = "修改购物车数量失败";
		public static final String SUBMIT_CAR_ORDER_SUCCESS = "提交购物车订单成功";
		public static final String SUBMIT_CAR_ORDER_ERROR = "提交购物车订单失败";
		public static final String GOODS_CAR_QUANTITY_ERROR = "商品数量不能再减少了";
		public static final String SECOND_GOODS_DONT_ADD = "二手商品不能重复添加";
	}

	public class Order {
		public static final String SUBMIT_ORDER_SUCCESS = "订单提交成功";
		public static final String DEL_ORDER_SUCCESS = "订单删除成功";
		public static final String REFUND_AFTER_SALE_SUCCESS = "申请售后成功";
		public static final String ORDER_APPRAISAL_SUCCESS = "订单评价成功";
		public static final String REFUND_AFTER_SALE_ERROR = "申请售后失败";
		public static final String ORDER_FAILED = "下单失败，请重新下单";
	}

	public class Settled {
		public static final String ADD_SETTLED_SHOP_SUCCESS = "商铺入驻成功,请等待管理员审核";
		public static final String SETTLED_SHOP_NULL = "入驻商铺为空，请联系管理员";
	}

	public class Shop {
		public static final String UPDATE_SHOP_SUCCESS = "修改商铺信息成功";
		public static final String UPDATE_WORK_STATUS_SUCCESS = "修改店铺状态成功";
		public static final String USER_SHOP_NULL = "用户店铺为空，请联系管理员";
	}

	public class Goods {
		public static final String OPERATION_GOODS_STATUS_SUCCESS = "操作商品状态成功";
		public static final String ADD_GOODS_SUCCESS = "添加商品成功";
		public static final String UPDATE_GOODS_SUCCESS = "修改商品成功";
		public static final String GOODS_SPE_REPEAT = "商品规格重复";
	}

	public class Reply {
		public static final String DEL_REPLY_SUCCESS = "删除快捷回复成功";
		public static final String UPDATE_REPLY_SUCCESS = "修改快捷回复成功";
		public static final String ADD_REPLY_SUCCESS = "添加快捷回复成功";
	}

	public class Child {
		public static final String ADD_CHILD_ACCOUNT_SUCCESS = "添加子账户成功";
		public static final String DEL_CHILD_ACCOUNT_SUCCESS = "删除子账户成功";
	}

	public class Finance {
		public static final String ORDER_SETTLE_ACCOUNTS_SUCCESS = "提现成功";
	}

	public class Address {
		public static final String ADD_ADDRESS_SUCCESS = "添加收货地址成功";
		public static final String UPDATE_ADDRESS_SUCCESS = "修改收货地址成功";
		public static final String DEL_ADDRESS_SUCCESS = "删除收货地址成功";
		public static final String SET_DEFAULT_ADDRESS_SUCCESS = "设置默认地址成功";
	}

	public class Investigate {
		public static final String POST_VOTE_SUCCESS = "发布投票成功";
		public static final String POST_LIKE_SUCCESS = "发布点赞成功";
		public static final String INVESTIGATE_OPERATION_REPETITION = "操作重复，不允许操作";
		public static final String INVESTIGATE_NO_PAY = "调研订单没有支付，不允许上架";
		public static final String OPERATE_SUCCESS = "操作成功";
		public static final String USER_INFO_ERROR = "请先完善个人信息，奖励会发放到您的账号中!!!";
		public static final String MAX_PEOPLE_NUM = "该调研已经领取次数已经完成";
	}

	public class SonBusiness {
		public static final String SELECT_SON_ERROR = "找寻子账号失败";
	}

	public class GoodsPurchasing {
		public static final String ADD_GOODS_PURCHASING_ERROR = "该商品已经添加到本代购商铺中";
		public static final String SELF_GOODS_PURCHASING_ERROR = "发布者不能代卖属于自己商品";
	}

	public class ShareRedPacket {
		public static final String SHARE_RED_PACKET_FORBIDDEN = "该用户分享红包功能已禁用";
		public static final String SHARE_RED_PACKET_FORBIDDEN_TWO = "分享超过三次，没有产生购买行为。该功能禁用一周";
		public static final String SHARE_RED_PACKET_FORBIDDEN_THREE = "分享超过五次，没有产生购买行为。该功能永久禁用";
		public static final String TODAY_SHARE_TANTO = "今天已经分享超过三次了，不允许再分享了";
		public static final String GOODS_NO_OR_SOLD_OUT = "该商品已下架或不存在，不允许分享红包";
		public static final String GOODS_ONE_DAY_ONCE = "同一个物品一天只能分享一次";
		public static final String SHARE_NUM_BEYOND = "当前物品售前红包已超过分享次数";
		public static final String SHOP_MONEY_INSUFFICIENT = "商家发送红包余额不够";
		public static final String RED_PACKET_SUCCESS = "分享成功";
	}

}
