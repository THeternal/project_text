package com.kemean.constant;

/**
 * 代研第三方接口调用API
 * 
 * @Date 2018年8月23日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class DaikenThirdAPIConstant {

	/**
	 * 微信
	 * 
	 * @Date 2018年8月23日
	 *
	 * @company 深圳科名网络有限公司 {@link www.kemean.com}
	 */
	public class WX {
		
		// 获取小程序二维码
		public static final String SMALL_QRCODE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
		
		// 第三方登录获取accessToken
		public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	}
	
	/**
	 * 阿里支付宝
	 * 
	 * @Date 2018年8月24日
	 *
	 * @company 深圳科名网络有限公司 {@link www.kemean.com}
	 */
	public class ALi {
		
		// 支付宝网关
		public static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";
		
	}

}
