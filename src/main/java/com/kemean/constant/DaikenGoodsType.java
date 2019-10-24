package com.kemean.constant;

/**
 * 商品类型
 * 
 * @Date 2018年5月9日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class DaikenGoodsType {

	/**
	 * 商品售卖类型
	 * 
	 * @Date 2018年5月9日
	 *
	 * @company 深圳科名网络有限公司 {@link www.kemean.com}
	 */
	public enum SalesType {
		/** 正常售卖 **/
		NORMAL_SALES(1101),
		/** 限时折扣 **/
		DISCOUNT_LIMITED_TIME(1102),
		/** 亏本走量 **/
		LOSS_MONEY(1103),
		/** 9.9包邮 **/
		NINE_EXEMPTION_POSTAGE(1104),
		/** 名牌折扣 **/
		DISCOUNT_FAMOUS_BRAND(1105);
		private Integer type;

		private SalesType(Integer type) {
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
	}
}
