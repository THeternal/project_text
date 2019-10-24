package com.kemean.vo.po.com;

/**
 * 支付宝查询转账结果
 * 
 * @Date 2018年8月24日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class TransferQueryALiPO {
	/** APPID **/
	private String appId;
	/** APP应用私钥 **/
	private String appPrivateKey;
	/** 支付宝公钥 **/
	private String alipayPublicKey;
	/** 支付宝流水订单编号 **/
	private String orderId;
	/** 转账订单编号 **/
	private String orderNo;

	public TransferQueryALiPO(String appId, String appPrivateKey, String alipayPublicKey, String orderNo,
			String orderId) {
		super();
		this.appId = appId;
		this.appPrivateKey = appPrivateKey;
		this.alipayPublicKey = alipayPublicKey;
		this.orderNo = orderNo;
		this.orderId = orderId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppPrivateKey() {
		return appPrivateKey;
	}

	public void setAppPrivateKey(String appPrivateKey) {
		this.appPrivateKey = appPrivateKey;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
