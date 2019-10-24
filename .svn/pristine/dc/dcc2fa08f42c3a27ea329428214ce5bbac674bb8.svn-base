package com.kemean.constant;

/**
 * 消息类型
 * 
 * @Date 2018年6月25日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class DaikenMessageType {

	/**
	 * 消息类型
	 * 
	 * @Date 2018年8月1日
	 *
	 * @company 深圳科名网络有限公司 {@link www.kemean.com}
	 */
	public enum MessageTypeEnum {
		/** 系统消息 **/
		SYSTEM(1001),
		/** 消费者消息 **/
		CONSUMER(1101),
		/** 商家消息 **/
		SHOP(1201),
		/** 客服发送消息 **/
		SERVICE(1301),
		/** 平台发送消息给客服 **/
		PLATFORM_SERVICE(1302);

		private Integer type;

		private MessageTypeEnum(Integer type) {
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
	}

	/**
	 * 
	 * websocket 消息推送
	 * 
	 * @Date 2018年8月1日
	 *
	 * @company 深圳科名网络有限公司 {@link www.kemean.com}
	 */
	public enum MessageWebsocketCodeEnum {
		/** 企业入驻 **/
		SHOP_SETTLED("1101"),
		/** 个人入驻 **/
		SHOP_PERSONAL_SETTLED("1102");

		private String code;

		private MessageWebsocketCodeEnum(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

}
