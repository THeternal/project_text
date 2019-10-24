package com.kemean.constant;

public class DaikenHotTreasure {

	public enum HotTreasureScreenPosition {
		/** 一屏 **/
		ONE_SCREEN(1101),
		/** 一屏替补 **/
		ONE_SCREEN_SUBSTITUTION(1110),
		/** 二屏 **/
		TWO_SCREEN(1201),
		/** 二屏替补 **/
		TWO_SCREEN_SUBSTITUTION(1210),
		/** 三屏 **/
		THREE_SCREEN(1301),
		/** 三屏替补 **/
		THREE_SCREEN_SUBSTITUTION(1310),
		/** 普通 **/
		ORDINARY(100000);

		private Integer type;

		private HotTreasureScreenPosition(Integer type) {
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

	}

	public enum HotTreasureState {
		/** 未开始 **/
		NOT_STARTED(1101),
		/** 进行中 **/
		UNDERWAY(1201),
		/** 已结束 **/
		FINISHED(1301);

		private Integer state;

		private HotTreasureState(Integer state) {
			this.state = state;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

	}
}
