package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsBaseType;
import com.kemean.bean.DaikenGoodsHotCharge;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsRecommend;
import com.kemean.bean.DaikenGoodsRecommendCharge;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserBase;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRecommend;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanRedisKeyEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsBaseTypeDao;
import com.kemean.dao.DaikenGoodsHotChargeDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsRecommendChargeDao;
import com.kemean.dao.DaikenGoodsRecommendDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenRedShareGetDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenShopSettledDao;
import com.kemean.dao.DaikenUserBaseDao;
import com.kemean.dao.KemeanAdminUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanIdAndName;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.common.AdminHomeChartBO;
import com.kemean.vo.bo.admin.common.AdminMessageCountBO;
import com.kemean.vo.bo.admin.common.AdminShopExpendChartBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewCategoryBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsPushUserRecordBO;
import com.kemean.vo.bo.admin.recommend.ChargeMoneyBO;
import com.kemean.vo.bo.admin.recommend.RecommendBO;
import com.kemean.vo.bo.admin.recommend.RecommendMoneyBO;
import com.kemean.vo.bo.admin.recommend.TreasureBO;
import com.kemean.vo.bo.admin.user.AdminUserChartBO;
import com.kemean.vo.bo.admin.user.KemeanAdminUserRecord;
import com.kemean.vo.bo.b.shop.RecommendChargeBO;
import com.kemean.vo.db.DateAndDateBO;
import com.kemean.vo.mysql.UserBehavior;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.recommend.ShopRecommendPO;

@Service
public class AdminCommonService {

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenGoodsBaseTypeDao daikenGoodsBaseTypeDao;

	@Autowired
	private DaikenUserBaseDao daikenUserBaseDao;

	@Autowired
	private DaikenShopSettledDao daikenShopSettledDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenRedShareGetDao daikenRedShareGetDao;

	@Autowired
	private DaikenGoodsRecommendDao daikenGoodsRecommendDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenGoodsHotChargeDao daikenGoodsHotChargeDao;

	@Autowired
	private DaikenGoodsRecommendChargeDao daikenGoodsRecommendChargeDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private KemeanAdminUserDao kemeanAdminUserDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	/**
	 * 获取商品的分类(根据pid)
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	public List<AdminGoodsNewCategoryBO> goodsBeseCategory(Integer pid) {
		List<DaikenGoodsBaseCategory> dbBaseCategory = daikenGoodsBaseCategoryDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED, "pid" }, new Object[] { false, pid });
		return Lists.transform(dbBaseCategory, new Function<DaikenGoodsBaseCategory, AdminGoodsNewCategoryBO>() {

			@Override
			public AdminGoodsNewCategoryBO apply(DaikenGoodsBaseCategory input) {
				AdminGoodsNewCategoryBO bo = new AdminGoodsNewCategoryBO();
				BeanUtils.copyProperties(input, bo);
				return bo;
			}
		});

	}

	/**
	 * 获取商品规格
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	public List<KemeanIdAndName> goodsBeseType(Integer objId) {
		DaikenGoodsBaseCategory dbBaseCategory = daikenGoodsBaseCategoryDao.selectById(objId);
		List<KemeanIdAndName> baseTypes = new ArrayList<>();
		if (StringUtils.isNoneBlank(dbBaseCategory.getRecordTypeId())) {
			List<Integer> baseType = JSONArray.parseArray(dbBaseCategory.getRecordTypeId(), Integer.class);
			baseTypes = new ArrayList<>(baseType.size());
			for (Integer item : baseType) {
				DaikenGoodsBaseType dbBaseType = daikenGoodsBaseTypeDao.selectById(item);
				baseTypes.add(new KemeanIdAndName(dbBaseType.getId(), dbBaseType.getName()));
			}
		}
		return baseTypes;

	}

	/**
	 * 用户基础信息（兴趣、职业）
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	public List<KemeanIdAndName> userBaseType(Integer type, Integer pid) {
		List<DaikenUserBase> dbUserBase = daikenUserBaseDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "type", "pid" }, new Object[] { false, type, pid });

		return Lists.transform(dbUserBase, new Function<DaikenUserBase, KemeanIdAndName>() {

			@Override
			public KemeanIdAndName apply(DaikenUserBase input) {
				return new KemeanIdAndName(input.getId(), input.getContent());
			}
		});
	}

	/**
	 * 平台消息统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月30日
	 */
	public AdminMessageCountBO messageCount() {
		AdminMessageCountBO bo = new AdminMessageCountBO();
		// 商铺入驻
		Integer dbShopSettled = daikenShopSettledDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "settledPersonal" }, new Object[] { false, false });

		Integer shopPersonalSettled = daikenShopSettledDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "settledPersonal" }, new Object[] { false, true });
		bo.setShopSettled(dbShopSettled);
		bo.setShopPersonalSettled(shopPersonalSettled);
		return bo;
	}

	/**
	 * 商品精准推送达人活动轨迹
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */

	public List<AdminGoodsPushUserRecordBO> goodsPushUserRecord(Integer userId) {
		// key
		// TODO 待修改（获取用户最近一个月的浏览记录）

		String cacheKeyH = String.format(DaikenRedisKeyEnum.BIG_DATA_USER_BEHAVIOR_H.getKey(), userId);
		String cacheKeyS = String.format(DaikenRedisKeyEnum.BIG_DATA_USER_BEHAVIOR_S.getKey(), userId);

		int end = (int) (KemeanUtilAid.getDateByCalendar(KemeanCalendarFieldEnum.DAY, -30).getTime() / 100000.0);

		Set<String> zrevrange = kemeanRedisService.zrevrange(cacheKeyS, 0, end);

		List<AdminGoodsPushUserRecordBO> result = new ArrayList<>(zrevrange.size());
		for (String sortKeyItem : zrevrange) {
			AdminGoodsPushUserRecordBO bo = new AdminGoodsPushUserRecordBO();
			// 用户行为
			String hashKeyValue = kemeanRedisService.hget(cacheKeyH, sortKeyItem);
			UserBehavior parseBehavior = JSONObject.parseObject(hashKeyValue, UserBehavior.class);
			bo.setCategoryName(parseBehavior.getCategoryName());
			bo.setGoodsTitle(parseBehavior.getTitle());
			bo.setCreateTimeStr(KemeanUtilAid.formatDate(parseBehavior.getCreateTime(), KemeanDateFormatEnum.NORMAL));
			result.add(bo);
		}
		return result;

	}

	/**
	 * 店铺支出、单个商品支出
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */

	public AdminShopExpendChartBO expengData(String dateStart, String dateEnd, Integer goodsId, List<Integer> shopId,
			int limit) {
		AdminShopExpendChartBO bo = new AdminShopExpendChartBO();
		// 售后红包领取金额
		List<AdminChartBO> dbChart = daikenGoodsNewDao.selectRedAfterGetRecord(
				KemeanOrderEnum.OrderStatusUser.FINISH.getStatus(), shopId, goodsId, dateStart, dateEnd, limit);
		bo.setRedAfterMoney(dbChart);

		// 售前红包领取金额
		dbChart = daikenRedShareGetDao.selectRedShareGetRecord(goodsId, shopId, dateStart, dateEnd, limit);
		bo.setRedBeforeMoney(dbChart);

		// 代卖佣金
		if (shopId.isEmpty()) {
			shopId = null;
		}
		List<DaikenGoodsNew> dbGoodsNew = daikenGoodsNewDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED, "purchasing" }, new Object[] { false, true }, "shopId",
				shopId, "id", false, 0, limit);

		List<Integer> goodIdList = new ArrayList<>(dbGoodsNew.size());
		for (DaikenGoodsNew daikenGoodsNew : dbGoodsNew) {
			List<DaikenGoodsNew> dbGoods = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
					new Object[] { false, daikenGoodsNew.getId() });
			for (DaikenGoodsNew item : dbGoods) {
				goodIdList.add(item.getId());
			}
		}
		dbChart = daikenGoodsNewDao.selectShopPurchasingPriceSum(goodIdList, dateStart, dateEnd, limit);

		return bo;
	}

	/**
	 * 统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	public AdminHomeChartBO chart(AdminDatePO adminDatePO) {
		AdminHomeChartBO bo = new AdminHomeChartBO();
		// 用户日下单数量
		String orderDateCountData = userOrderNumChart(adminDatePO, 0);
		// 用户日下单金额
		String orderDateData = userOrderMoneyChart(adminDatePO, 0);
		AdminUserChartBO userChart = adminUserService.userChart();
		BeanUtils.copyProperties(userChart, bo);
		bo.setOrderDateCountData(orderDateCountData);
		bo.setOrderChartData(orderDateData);

		return bo;
	}

	/**
	 * 用户日下单金额分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	public String userOrderMoneyChart(AdminDatePO adminDatePO, int limit) {
		// 用户日下单金额
		List<DateAndDateBO> dbOrderChart = daikenOrderDao.selectOrderChart(adminDatePO.getDateStart(),
				adminDatePO.getDateEnd(), limit);
		List<Object[]> orderChartData = new ArrayList<>(dbOrderChart.size());
		for (DateAndDateBO item : dbOrderChart) {
			orderChartData.add(new Object[] {
					KemeanUtilAid.parseDate(item.getDbDate(), KemeanDateFormatEnum.DATE).getTime(), item.getDbData() });
		}
		return JSONObject.toJSONString(orderChartData);

	}

	/**
	 * 用户日下单量分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	public String userOrderNumChart(AdminDatePO adminDatePO, int limit) {
		// 用户日下单量
		List<DateAndDateBO> dbOrderChart = daikenOrderDao.selectUserOrderNum(adminDatePO.getDateStart(),
				adminDatePO.getDateEnd(), limit);
		List<Object[]> orderChartCountData = new ArrayList<>(dbOrderChart.size());
		for (DateAndDateBO item : dbOrderChart) {
			orderChartCountData.add(new Object[] {
					KemeanUtilAid.parseDate(item.getDbDate(), KemeanDateFormatEnum.DATE).getTime(), item.getDbData() });
		}

		return JSONObject.toJSONString(orderChartCountData);
	}

	/**
	 * 客服上下线操作
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月7日
	 */
	public KemeanResult<String> onlineOperate(Integer userUid, Boolean status) {
		// 上线
		if (status) {
			kemeanRedisService.hset(DaikenRedisKeyEnum.SERVICE_LIST_H.getKey(), userUid + "",
					KemeanUtilAid.formatDate(new Date(), KemeanDateFormatEnum.DATE_NUM));
		} else {
			kemeanRedisService.hdel(DaikenRedisKeyEnum.SERVICE_LIST_H.getKey(), userUid + "");

		}
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 店铺推荐页面
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	public RecommendBO recommendShop(DaikenUser loginAdminShop) {
		RecommendBO resultBo = new RecommendBO();
		resultBo.setTypeId(loginAdminShop.getShopId());
		resultBo.setRecommendWay(1101);
		List<DaikenGoodsRecommendCharge> dbRecommendCharges = daikenGoodsRecommendChargeDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false });
		List<RecommendMoneyBO> result = new ArrayList<RecommendMoneyBO>(dbRecommendCharges.size());
		resultBo.setCharges(result);
		if (CollectionUtils.isEmpty(dbRecommendCharges)) {
			return resultBo;
		}
		for (DaikenGoodsRecommendCharge dbRecommendCharge : dbRecommendCharges) {
			RecommendMoneyBO bo = new RecommendMoneyBO();
			bo.setChargeValue(dbRecommendCharge.getBeginTime());
			String info = "" + dbRecommendCharge.getBeginTime() + "点-" + dbRecommendCharge.getEndTime() + "点(点击"
					+ dbRecommendCharge.getClickCharge() + "元/次，时长" + dbRecommendCharge.getShowCharge() + "元/一段" + ")";
			bo.setChargeText(info);
			result.add(bo);
		}
		return resultBo;
	}

	/**
	 * 添加首页推荐信息
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@Transactional
	public synchronized KemeanResult<String> shopRecommendAdd(ShopRecommendPO shopRecommendPO, DaikenUser daikenUser) {
		Double money = 0.0;
		RecommendChargeBO recommendCharge = commonService.getClickShowCharge(shopRecommendPO.getRecommendTypeSele());
		// 时长
		if (shopRecommendPO.getRecommendType().equals(DaikenRecommend.RecommendType.SHOW.getType())) {
			money = shopRecommendPO.getBuyDays() * recommendCharge.getShowCharge();
		}
		// 点击
		if (shopRecommendPO.getRecommendType().equals(DaikenRecommend.RecommendType.CLICK.getType())) {
			money = shopRecommendPO.getBuyClickNum() * recommendCharge.getClickCharge();
		}

		if (money.equals(0.0)) {
			return new KemeanResult<>(false, "输入的时间段有问题，请联系管理员");
		}
		Double blance = userService.getConsumerOrBusinessBlance(daikenUser);

		if (blance - money < 0) {
			return new KemeanResult<>(false, "余额不够，请充值后才能进行推荐活动");
		}
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
		List<DaikenGoodsRecommend> dbGoodsRecommends = daikenGoodsRecommendDao.getLastBuyTime(recommendCharge.getId());
		Date now = new Date();
		DaikenGoodsRecommend newRecommend = new DaikenGoodsRecommend();
		String buyTimeStr = "";
		if (CollectionUtils.isNotEmpty(dbGoodsRecommends)) {
			DaikenGoodsRecommend dbGoodsRecommend = dbGoodsRecommends.get(0);
			Date buyTime = KemeanUtilAid.getDateByCalendar(dbGoodsRecommend.getBuyTime(), KemeanCalendarFieldEnum.DAY,
					dbGoodsRecommend.getBuyDays());
			buyTimeStr = DaikenUtil.formatDate(buyTime, KemeanDateFormatEnum.DATE);
			newRecommend.setBuyTime(buyTime);
		}
		if (CollectionUtils.isEmpty(dbGoodsRecommends)) {
			// 如果购买的时间已过今天时间，将在明天显示，没有过的话，就在今天显示
			Integer currTimeInt = Integer.valueOf(DaikenUtil.formatDate(now, "HH"));
			Integer buyTimeInt = recommendCharge.getEndTime();
			if (currTimeInt >= buyTimeInt) {
				// 将在明天显示
				Date buyTime = KemeanUtilAid.getDateByCalendar(now, KemeanCalendarFieldEnum.DAY, 1);
				buyTimeStr = DaikenUtil.formatDate(buyTime, KemeanDateFormatEnum.DATE);
				newRecommend.setBuyTime(buyTime);
			}
			if (currTimeInt < buyTimeInt) {
				// 将在今天显示
				buyTimeStr = DaikenUtil.formatDate(now, KemeanDateFormatEnum.DATE);
				newRecommend.setBuyTime(now);
			}
		}
		BeanUtils.copyProperties(shopRecommendPO, newRecommend);
		newRecommend.setUserUid(daikenUser.getUid());
		newRecommend.setUserPhone(daikenUser.getPhone());
		newRecommend.setRecommendId(recommendCharge.getId());
		newRecommend.setCreateTime(now);
		newRecommend.setUpdateTime(now);
		daikenGoodsRecommendDao.saveSelective(newRecommend);
		DaikenShop dbShop = daikenShopDao.selectById(daikenUser.getShopId());
		// 商户端
		commonService.createFinanceOrder(dbShop.getId() + "【" + dbShop.getShopName() + "】",
				DaikenFinanceTypeEnum.B_RECOMMEND_ADVERTISING.getType(), money, 0, daikenUser.getShopId(),
				dbShop.getShopName(), "首页推荐弹窗广告（支出） ");
		return new KemeanResult<>(true, "首页推荐添加成功，将在" + buyTimeStr + "时显示。");
	}

	/**
	 * 商品首页推荐宝贝页面
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	public TreasureBO goodsTreasurePage(Integer goodsId) {
		TreasureBO result = new TreasureBO();
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(goodsId);
		result.setGoodsId(dbGoodsNew.getId());
		result.setGoodsTitle(dbGoodsNew.getTitle());
		List<DaikenGoodsHotCharge> dbHotCharges = daikenGoodsHotChargeDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false });
		List<ChargeMoneyBO> chargeMoney = new ArrayList<ChargeMoneyBO>(dbHotCharges.size());
		if (CollectionUtils.isNotEmpty(dbHotCharges)) {
			for (DaikenGoodsHotCharge dbHotCharge : dbHotCharges) {
				ChargeMoneyBO bo = new ChargeMoneyBO();
				bo.setScreenPosition(dbHotCharge.getScreenPosition());
				bo.setScreenPositionStr(DaikenMapData.hotTreasure.get(dbHotCharge.getScreenPosition()) + "("
						+ dbHotCharge.getClickCharge() + "元 / 次)");
				chargeMoney.add(bo);
			}
		}
		result.setChargeMoney(chargeMoney);
		return result;
	}

	/**
	 * @author huwei
	 * @date 2018年9月10日
	 */
	public RecommendBO goodsRecommendPage(Integer objId) {
		RecommendBO resultBo = new RecommendBO();
		resultBo.setTypeId(objId);
		resultBo.setRecommendWay(1201);
		List<DaikenGoodsRecommendCharge> dbRecommendCharges = daikenGoodsRecommendChargeDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false });
		List<RecommendMoneyBO> result = new ArrayList<RecommendMoneyBO>(dbRecommendCharges.size());
		resultBo.setCharges(result);
		if (CollectionUtils.isEmpty(dbRecommendCharges)) {
			return resultBo;
		}
		for (DaikenGoodsRecommendCharge dbRecommendCharge : dbRecommendCharges) {
			RecommendMoneyBO bo = new RecommendMoneyBO();
			bo.setChargeValue(dbRecommendCharge.getBeginTime());
			String info = "" + dbRecommendCharge.getBeginTime() + "点-" + dbRecommendCharge.getEndTime() + "点(点击"
					+ dbRecommendCharge.getClickCharge() + "元/次，时长" + dbRecommendCharge.getShowCharge() + "元/ 一段" + ")";
			bo.setChargeText(info);
			result.add(bo);
		}
		return resultBo;
	}

	/**
	 * 管理运营人员，普通运营人员信息添加
	 * 
	 * @author huwei
	 * @date 2018年10月10日
	 */
	public KemeanResult<String> addUserInfo(String wxNum, String phone, String email, KemeanAdminUser loginAdminUser) {
		KemeanAdminUserRecord record = new KemeanAdminUserRecord(wxNum, phone, email);
		loginAdminUser.setRecord(JSONObject.toJSONString(record));
		kemeanAdminUserDao.updateByPrimaryKeySelective(loginAdminUser);
		kemeanRedisService.del(String.format(KemeanRedisKeyEnum.ADMIN_LOGIN_USER.getKey(), loginAdminUser.getToken()));
		return new KemeanResult<>(true, "信息添加成功");
	}

}
