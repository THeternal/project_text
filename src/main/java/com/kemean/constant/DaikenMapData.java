package com.kemean.constant;

import java.util.HashMap;
import java.util.Map;

import com.kemean.constant.DaikenMessageType.MessageTypeEnum;

public class DaikenMapData {

	public static Map<Integer, String> salesType = new HashMap<>(5);
	public static Map<Integer, String> userType = new HashMap<>(3);
	public static Map<Integer, String> investType = new HashMap<>(4);
	public static Map<Integer, String> investQuestionType = new HashMap<>(4);
	public static Map<Integer, String> salesLevel = new HashMap<>(5);
	public static Map<Integer, String> messageType = new HashMap<>(3);
	public static Map<Integer, String> financeType = new HashMap<>(22);
	public static Map<Integer, String> complaintType = new HashMap<>(4);
	public static Map<Integer, String> complaintStatus = new HashMap<>(3);
	public static Map<Integer, String> settledStatus = new HashMap<>(5);
	public static Map<Integer, String> recommendType = new HashMap<>(3);
	public static Map<Integer, String> recommendWay = new HashMap<>(2);
	public static Map<Integer, String> locatType = new HashMap<>(2);
	public static Map<String, String> expressCompany = new HashMap<>(11);
	public static Map<Integer, String> hotTreasure = new HashMap<>(7);
	public static Map<Integer, String> investOperationType = new HashMap<>(3);
	public static Map<Integer, String> hotTreasureState = new HashMap<>(3);
	public static Map<Integer, String> financeStatus = new HashMap<>(3);

	static {

		// 商品售卖类型
		salesType.put(DaikenGoodsType.SalesType.DISCOUNT_FAMOUS_BRAND.getType(), "名牌折扣");
		salesType.put(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType(), "限时折扣");
		salesType.put(DaikenGoodsType.SalesType.LOSS_MONEY.getType(), "亏本走量");
		salesType.put(DaikenGoodsType.SalesType.NINE_EXEMPTION_POSTAGE.getType(), "9.9包邮");
		salesType.put(DaikenGoodsType.SalesType.NORMAL_SALES.getType(), "正常售卖");

		// 用户类型
		userType.put(DaikenUserEnum.DaikenUserTypeEnum.CONSUMER.getType(), "消费者");
		userType.put(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType(), "商家");
		userType.put(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS_SON.getType(), "商家子账号");

		// 消息类型
		messageType.put(MessageTypeEnum.SYSTEM.getType(), "系统消息");
		messageType.put(MessageTypeEnum.SHOP.getType(), "商户消息");
		messageType.put(MessageTypeEnum.CONSUMER.getType(), "消费者");

		// 投诉类型
		complaintType.put(DaikenComplaint.ComplaintType.INVEST.getType(), "调研客户");
		complaintType.put(DaikenComplaint.ComplaintType.SHOP.getType(), "店铺");
		complaintType.put(DaikenComplaint.ComplaintType.SALE.getType(), "销售");
		complaintType.put(DaikenComplaint.ComplaintType.ADMIN.getType(), "平台运营");

		// 投诉状态
		complaintStatus.put(DaikenComplaint.ComplaintStatus.WAIT_DEAL.getStatus(), "待处理");
		complaintStatus.put(DaikenComplaint.ComplaintStatus.DEAL_IN.getStatus(), "已处理");
		complaintStatus.put(DaikenComplaint.ComplaintStatus.FINISH.getStatus(), "已完结");

		// 财务类型
		financeType.put(DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(), "点赞收入");
		financeType.put(DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(), "投票收入");
		financeType.put(DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType(), "问卷收入");
		financeType.put(DaikenFinanceTypeEnum.C_SELL_GOODS.getType(), "二手卖货收入");
		financeType.put(DaikenFinanceTypeEnum.C_BEFOR_RED_INCOME.getType(), "售前红包收入");
		financeType.put(DaikenFinanceTypeEnum.C_AFTER_RED_INCOME.getType(), "售后红包收入");
		financeType.put(DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType(), "帮卖收入");
		financeType.put(DaikenFinanceTypeEnum.C_LINE_PAY_BUY_GOODS.getType(), "购买商品支出");
		financeType.put(DaikenFinanceTypeEnum.C_LINE_PAY_INVESTIGATE.getType(), "发布调研支出");
		financeType.put(DaikenFinanceTypeEnum.C_CASH_USER.getType(), "提现支出");
		financeType.put(DaikenFinanceTypeEnum.B_SELL_GOODS.getType(), "卖货收入");
		financeType.put(DaikenFinanceTypeEnum.B_RECHARGE_MONEY.getType(), "充值收入");
		financeType.put(DaikenFinanceTypeEnum.B_LINE_PAY_INVESTIGATE.getType(), "发布调研支出");
		financeType.put(DaikenFinanceTypeEnum.B_LUNBO_ADVERTISING.getType(), "轮播图广告支出");
		financeType.put(DaikenFinanceTypeEnum.B_RECOMMEND_ADVERTISING.getType(), "推荐弹窗广告支出");
		financeType.put(DaikenFinanceTypeEnum.B_RECOMMEND_TREASURE.getType(), "推荐屏位宝贝支出");
		financeType.put(DaikenFinanceTypeEnum.B_SHOP_PUSH_GOODS.getType(), "精准投递费用支出");
		financeType.put(DaikenFinanceTypeEnum.B_AFTER_RED_INCOME.getType(), "售前红包支出");
		financeType.put(DaikenFinanceTypeEnum.B_LATER_RED_INCOME.getType(), "售后红包支出");
		financeType.put(DaikenFinanceTypeEnum.B_HELP_SALL_GOODS.getType(), "帮卖支出");
		financeType.put(DaikenFinanceTypeEnum.B_CASH_SHOP.getType(), "提现支出");
		financeType.put(DaikenFinanceTypeEnum.RECHARGE.getType(), "平台充值收入");

		// 销售人员等级
		salesLevel.put(DaikenSalesUserLevelEnum.SALE_COMPANY.getLevel(), "销售公司");
		salesLevel.put(DaikenSalesUserLevelEnum.SALE_DIRECTOR.getLevel(), "销售总监");
		salesLevel.put(DaikenSalesUserLevelEnum.SALE_MANAGER.getLevel(), "销售经理");
		salesLevel.put(DaikenSalesUserLevelEnum.SALE_SUPERVISOR.getLevel(), "销售主管");
		salesLevel.put(DaikenSalesUserLevelEnum.SELLER.getLevel(), "销售员");

		// 调研类型
		investType.put(DaikenInvestigate.DaikenInvestigateTypeEnum.ALL_INVESTIGATE.getType(), "全部");
		investType.put(DaikenInvestigate.DaikenInvestigateTypeEnum.POST_LIKE.getType(), "点赞");
		investType.put(DaikenInvestigate.DaikenInvestigateTypeEnum.POST_VOTE.getType(), "投票");
		investType.put(DaikenInvestigate.DaikenInvestigateTypeEnum.NEW_GOODS_LIKE.getType(), "一手商品(点赞)");
		investType.put(DaikenInvestigate.DaikenInvestigateTypeEnum.OLD_GOODS_LIKE.getType(), "二手商品(点赞)");
		investType.put(DaikenInvestigate.DaikenInvestigateTypeEnum.QUESTIONNAIRE.getType(), "问卷调查");

		// 调查问卷问题类型
		investQuestionType.put(DaikenInvestigate.DaikenInvestigateQuestionTypeEnum.ONE_SELECT.getType(), "单选");
		investQuestionType.put(DaikenInvestigate.DaikenInvestigateQuestionTypeEnum.MANY_SELECT.getType(), "多选");
		investQuestionType.put(DaikenInvestigate.DaikenInvestigateQuestionTypeEnum.SIMPLE.getType(), "简答题");

		// 审核类型
		settledStatus.put(KemeanSettledEnum.NOT_FILL.getStatus(), "没填写");
		settledStatus.put(KemeanSettledEnum.FILL_IN.getStatus(), "填写中");
		settledStatus.put(KemeanSettledEnum.UPDATE_WAIT_AUDIT.getStatus(), "更新待审核");
		settledStatus.put(KemeanSettledEnum.REVIEW_ING.getStatus(), "审核中");
		settledStatus.put(KemeanSettledEnum.AUDIT_FAILUE.getStatus(), "不通过/失败");
		settledStatus.put(KemeanSettledEnum.AUDIT_PASS.getStatus(), "审核通过");

		// 调研操作类型
		investOperationType.put(DaikenOperationTypeExplain.c_good, "赞");
		investOperationType.put(DaikenOperationTypeExplain.c_common, "一般");
		investOperationType.put(DaikenOperationTypeExplain.c_bad, "踩");

		// 推荐类型
		recommendType.put(DaikenRecommend.RecommendType.SHOW.getType(), "时间段显示");
		recommendType.put(DaikenRecommend.RecommendType.CLICK.getType(), "时间段点击");
		recommendType.put(DaikenRecommend.RecommendType.SUBSTITUTION.getType(), "替补广告");
		recommendType.put(0, "调研广告");

		// 推荐方式
		recommendWay.put(DaikenRecommend.RecommendWay.GOODS.getWay(), "商品");
		recommendWay.put(DaikenRecommend.RecommendWay.SHOP.getWay(), "商铺");
		recommendWay.put(0, "调研广告");

		// 展示位置
		locatType.put(DaikenLocatTypeEnum.HOME_PAGE.getType(), "首页");
		locatType.put(DaikenLocatTypeEnum.INVESTIGATE_PAGE.getType(), "调研");

		// 快递公司信息
		expressCompany.put(LogisticsCompanyEnum.SHUN_FENG.getType(), "顺丰");
		expressCompany.put(LogisticsCompanyEnum.EMS.getType(), "ems");
		expressCompany.put(LogisticsCompanyEnum.BAI_SHI_HUI_TOME.getType(), "百世汇通");
		expressCompany.put(LogisticsCompanyEnum.SHEN_TONE.getType(), "申通");
		expressCompany.put(LogisticsCompanyEnum.ZHONG_TONE.getType(), "中通");
		expressCompany.put(LogisticsCompanyEnum.YUAN_TONG.getType(), "圆通");
		expressCompany.put(LogisticsCompanyEnum.YUN_DA.getType(), "韵达");
		expressCompany.put(LogisticsCompanyEnum.TIAN_TIAN.getType(), "天天");
		expressCompany.put(LogisticsCompanyEnum.JD.getType(), "京东");
		expressCompany.put(LogisticsCompanyEnum.ZHAI_JI_SONG.getType(), "宅急送");
		expressCompany.put(LogisticsCompanyEnum.OTHER.getType(), "其它");

		// 屏位信息
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN.getType(), "一屏");
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType(), "一屏替补");
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN.getType(), "二屏");
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType(), "二屏替补");
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN.getType(), "三屏");
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType(), "三屏替补");
		hotTreasure.put(DaikenHotTreasure.HotTreasureScreenPosition.ORDINARY.getType(), "普通");

		// 推荐宝贝当前状态
		hotTreasureState.put(DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState(), "未开始");
		hotTreasureState.put(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState(), "进行中");
		hotTreasureState.put(DaikenHotTreasure.HotTreasureState.FINISHED.getState(), "已完成");
	}
}
