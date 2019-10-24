package com.kemean.constant;

public class DaikenRecommend {

	public enum RecommendType {
		/** 时间段显示 **/
		SHOW(1101),
		/** 时间段点击 **/
		CLICK(1201),
		/** 替补广告 **/
		SUBSTITUTION(1301);
		private Integer type;

		private RecommendType(Integer type) {
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
	}

	public enum RecommendWay {
		/** 商铺 **/
		SHOP(1101),
		/** 商品 **/
		GOODS(1201);
		private Integer way;

		private RecommendWay(Integer way) {
			this.way = way;
		}

		public Integer getWay() {
			return way;
		}

		public void setWay(Integer way) {
			this.way = way;
		}

	}
}
