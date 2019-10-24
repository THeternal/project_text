package com.kemean.constant;

public enum DaikenRedisKeyEnum {

	/** 验证码 p1-验证码类型 p2-邮箱、手机号 **/
	CODE("code_%s_%s"),
	/** 消费者 p1-token **/
	USER_CONSUMER("daiken_consumer_%s"),
	/** 商户 p1-token **/
	USER_BUSINESS("daiken_business_%s"),
	/** 用户 p1-token **/
	USER("daiken_user_%s"),
	/** uid **/
	UID("daiken_uid"),
	/** 地区 p1-pid **/
	REGION("ul_region_%s"),
	/** 配置 p1-type **/
	CONFIG("daiken_config_%s"),
	/** 用户行为大数据分析 hash去重 **/
	BIG_DATA_H("daiken_big_data_hash_%s"),
	/** 用户行为大数据分析 有序时间 **/
	BIG_DATA_S("daiken_big_data_sort_%s"),
	
	/** 【用户】用户行为记录 hash 去重  p1-用户id **/
	BIG_DATA_USER_BEHAVIOR_H("daiken_big_data_user_behavior_hase_%s"),
	
	/** 【用户】用户行为记录  有序时间  p1-用户id **/
	BIG_DATA_USER_BEHAVIOR_S("daiken_big_data_user_behavior_set_%s"),
	
	/** 【商品】商品浏览用户 有序时间   p1-商品id **/
	BIG_DATA_GOODS_BROWSE_S("daiken_big_data_goods_browse_sort_%s"),
	
	/** 【商品】商品浏览用户  hash去重 p1-商品id **/
	BIG_DATA_GOODS_BROWSE_H("daiken_big_data_goods_browse_hash_%s"),
	
	/** 轮播图 p1-位置 **/
	LUNBO("daiken_lunbo_%s"),
	/** 商品类型 **/
	GOODS_CATEGORY("daiken_goods_category_%s"),
	/** 用户IM会话列表 **/
	IM_RECORD_USER_COMVERSATION("daiken_conversation_%s"),
	/** 用户IM会话未读消息数（单个会话） **/
	IM_RECORD_MESSAGE_NUMBER("daiken_message_num_%s_%s"),
	/** 客服列表hash去重 **/
	SERVICE_LIST_H("daiken_service_hash");

	private String key;

	private DaikenRedisKeyEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
