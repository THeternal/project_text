package com.kemean.constant;

public class DaikenAdminResultTips {

	public class Lunbo {
		public static final String LUNBO_ADD_SUCCESS = "轮播图添加成功";
		public static final String LUNBO_UPDATE_SUCCESS = "轮播图修改成功";
		public static final String LUNBO_ADD_ERROR = "轮播图添加失败";
	}

	public class Goods {
		public static final String GOODS_RECOMMEND_SUCCESS = "推荐商品成功";
	}

	public class forgetPassword {
		public static final String UPDATE_PASSWORD_SUCCESS = "密码重置成功";
	}

	public class Order {
		public static final String ORDER_NOT_FOUND = "查询不到对应的订单信息";
		public static final String ORDER_LOGISTICS_NOT_FOUND = "查询不到对应的物流信息";
		public static final String ORDER_AFTER_SALE_LOGISTICS_SUCCESS = "保存成功";
		public static final String ORDER_AFTER_SALE_NOT_FOUND = "根据订单编号查询不到对应订单售后信息";
		public static final String ORDER_COMPENSATE_SAVE_SUCCESS = "补偿成功";
	}

	public class Shop {
		public static final String WITHDRAW_DEPOSIT_ERROR = "提现失败，请仔细核对您的信息是否正确";
		public static final String WITHDRAW_DEPOSIT_SUCCESS = "提现成功，请在7个工作日内关注自己的账户是否到账，如果未到账请联系平台客服人员处理";
		public static final String WITHDRAW_DEPOSIT_ACCOUNT_NULL = "提现的支付宝账号不能为空";
		public static final String WITHDRAW_DEPOSIT_MONEY_ERROR = "提现的金额不能大于账户余额";
		public static final String WITHDRAW_DEPOSIT_OPENID_NULL = "微信用户的openid不能为空";

	}

	public class Third {
		public static final String WX_LOGIN_FAIL = "微信授权登录失败";
		public static final String WX_LOGIN_SUCCESS = "微信授权登录成功";
	}

	public class Sms {
		public static final String SEND_CODE_ERROR = "手机验证码错误";
	}
}
