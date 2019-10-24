package com.kemean.service.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsBaseType;
import com.kemean.bean.DaikenGoodsHotTreasure;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsRecommend;
import com.kemean.bean.DaikenGoodsRecommendCharge;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenOrderGoods;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserBase;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.bean.KemeanConfig;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.bean.KemeanLunbo;
import com.kemean.bean.KemeanMessageUser;
import com.kemean.bean.KemeanRegion;
import com.kemean.bean.KemeanRichText;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenConstant;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenHotTreasure;
import com.kemean.constant.DaikenLunboLocatTypeEnum;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRecommend;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.constant.KemeanMapData;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsBaseTypeDao;
import com.kemean.dao.DaikenGoodsHotTreasureDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsRecommendChargeDao;
import com.kemean.dao.DaikenGoodsRecommendDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserBaseDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanAdminUserDao;
import com.kemean.dao.KemeanConfigDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.dao.KemeanLunboDao;
import com.kemean.dao.KemeanMessageUserDao;
import com.kemean.dao.KemeanRegionDao;
import com.kemean.dao.KemeanRichTextDao;
import com.kemean.exception.KemeanException;
import com.kemean.service.KemeanAidService;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.LogisticsService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.ThirdLogisticsInfoDataBO;
import com.kemean.vo.bo.b.shop.RecommendChargeBO;
import com.kemean.vo.bo.c.common.ComLunboBO;
import com.kemean.vo.bo.c.mine.finance.ClearOrderListBO;
import com.kemean.vo.bo.c.order.OrderListGoodsBO;
import com.kemean.vo.bo.com.ComRegionBO;
import com.kemean.vo.bo.com.ExpressInfoBO;
import com.kemean.vo.bo.com.GetBaseCategoryBBO;
import com.kemean.vo.bo.com.GetBaseCategoryBO;
import com.kemean.vo.bo.com.GetBaseTypeBO;
import com.kemean.vo.bo.com.HelpSellRuleBO;
import com.kemean.vo.bo.com.MessageBO;
import com.kemean.vo.bo.com.RecommendGoodsBO;
import com.kemean.vo.bo.com.TracesBO;
import com.kemean.vo.bo.com.UserHobbiesinterestsBO;
import com.kemean.vo.bo.com.UserInfoBO;
import com.kemean.vo.mysql.GoodsRecordDB;
import com.kemean.vo.mysql.ImRecordRedis;
import com.kemean.vo.mysql.LogisticsInfoDB;
import com.kemean.vo.po.KemeanPageApiPO;
import com.kemean.vo.po.c.mine.finance.ClearOrderListPO;
import com.kemean.vo.po.com.BaseCategoryPO;
import com.kemean.vo.po.com.MessageOperatePO;
import com.kemean.vo.po.com.OrderSettleAccountsPO;
import com.kemean.vo.po.com.UserInfoPO;

/**
 * 
 * 【公共端】公共业务层
 * 
 * @Date 2018年6月12日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class CommonService {

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenGoodsBaseTypeDao daikenGoodsBaseTypeDao;

	@Autowired
	private DaikenUserBaseDao daikenUserBaseDao;

	@Autowired
	private KemeanConfigDao kemeanConfigDao;

	@Autowired
	private KemeanRegionDao kemeanRegionDao;

	@Autowired
	private KemeanLunboDao kemeanLunboDao;

	@Autowired
	private KemeanMessageUserDao kemeanMessageUserDao;

	@Autowired
	private KemeanFinanceOrderDaikenDao kemeanFinanceOrderDao;

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private KemeanRichTextDao kemeanRichTextDao;

	@Autowired
	private DaikenGoodsRecommendDao daikenGoodsRecommendDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenGoodsRecommendChargeDao daikenGoodsRecommendChargeDao;

	@Autowired
	private KemeanAdminUserDao kemeanAdminUserDao;

	@Autowired
	private DaikenGoodsHotTreasureDao daikenGoodsHotTreasureDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	private KemeanAidService aidService;

	@Autowired
	private LogisticsService logisticsService;

	@Autowired
	private UserService userService;

	/**
	 * 提现
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	@Transactional
	public KemeanResult<String> orderSettleAccounts(OrderSettleAccountsPO orderSettleAccountsPO,
			DaikenUser daikenUser) {
		Date now = new Date();
		String financeNo = KemeanUtilAid.getOrderNo(now);
		// 判断是商家端还是小程序端
		if (daikenUser.getUserStatus().equals(DaikenUserEnum.DaikenUserTypeEnum.CONSUMER.getType())) {
			Double unFreezeMoney = DaikenUtil
					.formatDouble(kemeanFinanceOrderDao.getConsumerMoney(daikenUser.getId(), false), 2);
			// 已提现
			Double finishMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(daikenUser.getId(),
					KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus()), 2);
			// 提现中
			Double waitMoney = DaikenUtil.formatDouble(kemeanFinanceClearDao.consumerClearMoeny(daikenUser.getId(),
					KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus()), 2);
			Double blancePrice = unFreezeMoney - finishMoney - waitMoney;
			if (blancePrice < orderSettleAccountsPO.getApplyMoney()) {
				return new KemeanResult<>(false, "提现失败，余额不够");
			}
			// 小程序端
			KemeanFinanceClear newFinance = new KemeanFinanceClear();
			newFinance.setFinanceNo(financeNo);
			newFinance.setFinanceType(DaikenFinanceTypeEnum.C_CASH_USER.getType());
			newFinance.setSubmitAimsId(daikenUser.getId());
			newFinance.setSubmitAimsName(daikenUser.getNickName());
			newFinance.setSubmitMoney(orderSettleAccountsPO.getApplyMoney());
			newFinance.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
			newFinance.setFinanceMonth(DaikenUtil.formatDate(now, KemeanDateFormatEnum.YEAR_MONTH.getFormat()));
			newFinance.setFinanceData(now);
			// TODO 提现成功之后 余额等修改
			newFinance.setBalance(blancePrice);
			newFinance.setCreateTime(now);
			newFinance.setUpdateTime(now);
			kemeanFinanceClearDao.saveSelective(newFinance);
			daikenUser.setBalancePrice(daikenUser.getBalancePrice() - orderSettleAccountsPO.getApplyMoney());
			daikenUser.setUpdateTime(now);
			daikenUserDao.updateByPrimaryKeySelective(daikenUser);
			kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
			return new KemeanResult<String>();
		}

		if (daikenUser.getUserStatus().equals(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType())) {
			// 可提现资金 超过7天的订单资金
			DaikenShop dbShop = daikenShopDao.selectById(daikenUser.getShopId());
			Date cycleDate = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY,
					-dbShop.getCycle());
			Double waitMoney = kemeanFinanceOrderDao.getShopMoney(daikenUser.getShopId(), daikenUser.getId(),
					cycleDate);
			// 提现中
			Double finshMoneying = kemeanFinanceClearDao.shopClearMoney(daikenUser.getId(),
					KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
			// 已提现
			Double finshMoney = kemeanFinanceClearDao.shopClearMoney(daikenUser.getId(),
					KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus());

			Double blancePrice = waitMoney - finshMoneying - finshMoney;
			if (blancePrice < orderSettleAccountsPO.getApplyMoney()) {
				return new KemeanResult<>(false, "提现失败，余额不够");
			}
			KemeanFinanceClear newFinance = new KemeanFinanceClear();
			newFinance.setFinanceNo(financeNo);
			newFinance.setFinanceType(DaikenFinanceTypeEnum.B_CASH_SHOP.getType());
			newFinance.setSubmitAimsId(dbShop.getId());
			newFinance.setSubmitAimsName(dbShop.getShopName());
			newFinance.setSubmitMoney(waitMoney);
			newFinance.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus());
			newFinance.setFinanceMonth(DaikenUtil.formatDate(now, KemeanDateFormatEnum.YEAR_MONTH.getFormat()));
			newFinance.setFinanceData(now);
			newFinance.setCreateTime(now);
			newFinance.setUpdateTime(now);
			kemeanFinanceClearDao.saveSelective(newFinance);
			kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_BUSINESS.getKey(), daikenUser.getToken()));
			return new KemeanResult<String>();
		}
		return new KemeanResult<String>();
	}

	/** 获取UID **/
	public Integer getUid() {
		return aidService.getUid(DaikenRedisKeyEnum.UID.getKey(), daikenUserDao.selectTheMaxId(),
				Integer.valueOf(getConfig(DaikenConfigEnum.UID_PREFIX).getRecord()));
	}

	/**
	 * 获取商品分类
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	public List<GetBaseCategoryBO> getBaseCategory(BaseCategoryPO baseCategoryPO) {
		String cacheKey = String.format(DaikenRedisKeyEnum.GOODS_CATEGORY.getKey(), baseCategoryPO.getObjId());
		String cacheRegion = kemeanRedisService.getString(cacheKey);
		if (StringUtils.isNoneBlank(cacheRegion)) {
			return JSONObject.parseArray(cacheRegion, GetBaseCategoryBO.class);
		}
		List<Integer> levels = new ArrayList<Integer>();
		if (baseCategoryPO.getLevel() != null) {
			levels.add(baseCategoryPO.getLevel());
		} else {
			levels.add(1);
			levels.add(2);
			levels.add(3);
		}
		List<DaikenGoodsBaseCategory> dbBaseCategorys = daikenGoodsBaseCategoryDao.getBaseCategory(levels);
		List<GetBaseCategoryBO> result = new ArrayList<GetBaseCategoryBO>(dbBaseCategorys.size());
		for (DaikenGoodsBaseCategory daikenGoodsBaseCategory : dbBaseCategorys) {
			GetBaseCategoryBO bo = new GetBaseCategoryBO();
			BeanUtils.copyProperties(daikenGoodsBaseCategory, bo);
			if (baseCategoryPO.getLevel() != null && baseCategoryPO.getLevel().equals(2)) {
				List<DaikenGoodsBaseCategory> dbDaikenGoodsBaseCategory = daikenGoodsBaseCategoryDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "pid" },
						new Object[] { false, daikenGoodsBaseCategory.getId() });
				List<GetBaseCategoryBO> baseCategorys = new ArrayList<GetBaseCategoryBO>(
						dbDaikenGoodsBaseCategory.size());
				for (DaikenGoodsBaseCategory daikenGoodsBaseCategory2 : dbDaikenGoodsBaseCategory) {
					GetBaseCategoryBO bo1 = new GetBaseCategoryBO();
					BeanUtils.copyProperties(daikenGoodsBaseCategory2, bo1);
					baseCategorys.add(bo1);

				}
				bo.setGetBaseCategoryBO(baseCategorys);
			}
			if (baseCategoryPO.getObjId() != null) {
				if (baseCategoryPO.getObjId().equals(daikenGoodsBaseCategory.getPid())) {
					result.add(bo);
				}
			} else {
				result.add(bo);
			}
		}
		kemeanRedisService.setString(cacheKey, JSONObject.toJSONString(result));
		return result;
	}

	/**
	 * 获取规格分类
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	public List<GetBaseTypeBO> getBaseType(Integer objId) {
		DaikenGoodsBaseCategory dbBaseCategory = daikenGoodsBaseCategoryDao.selectById(objId);
		List<GetBaseTypeBO> result = new ArrayList<GetBaseTypeBO>();
		if (StringUtils.isEmpty(dbBaseCategory.getRecordTypeId())) {
			return result;
		}
		List<Integer> recordTypeIds = JSONArray.parseArray(dbBaseCategory.getRecordTypeId(), Integer.class);
		result = new ArrayList<GetBaseTypeBO>(recordTypeIds.size());
		if (CollectionUtils.isEmpty(recordTypeIds)) {
			return result;
		}
		for (Integer typeId : recordTypeIds) {
			DaikenGoodsBaseType dbBaseType = daikenGoodsBaseTypeDao.selectById(typeId);
			GetBaseTypeBO bo = new GetBaseTypeBO();
			BeanUtils.copyProperties(dbBaseType, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 获取商品分类(商户端)
	 * 
	 * @author huwei
	 * @date 2018年6月28日
	 */
	public List<GetBaseCategoryBBO> getBaseCategoryB(BaseCategoryPO baseCategoryPO) {
		Integer objId = 0;
		if (baseCategoryPO.getObjId() != null) {
			objId = baseCategoryPO.getObjId();
		}

		List<GetBaseCategoryBBO> result = new ArrayList<GetBaseCategoryBBO>();
		List<DaikenGoodsBaseCategory> dbBaseCategorys = daikenGoodsBaseCategoryDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "level", "pid" },
				new Object[] { false, baseCategoryPO.getLevel(), objId });
		for (DaikenGoodsBaseCategory daikenGoodsBaseCategory : dbBaseCategorys) {
			GetBaseCategoryBBO bo = new GetBaseCategoryBBO();
			BeanUtils.copyProperties(daikenGoodsBaseCategory, bo);
			result.add(bo);
		}
		// 存到redis里面去

		return result;
	}

	/**
	 * 获取用户的兴趣爱好/职业
	 * 
	 * @author huwei
	 * @date 2018年7月2日
	 */
	public List<UserHobbiesinterestsBO> getUserHobbiesinterests(Integer type) {
		List<DaikenUserBase> dbUserBases = daikenUserBaseDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED, "type" }, new Object[] { false, type });
		List<UserHobbiesinterestsBO> result = new ArrayList<UserHobbiesinterestsBO>(dbUserBases.size());
		for (DaikenUserBase daikenUserBase : dbUserBases) {
			UserHobbiesinterestsBO bo = new UserHobbiesinterestsBO();
			BeanUtils.copyProperties(daikenUserBase, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 获取数据库配置
	 * 
	 * @author huangyujian
	 * @date 2018年4月2日
	 */
	public KemeanConfig getConfig(DaikenConfigEnum daikenConfigEnum) {
		String cacheKey = String.format(DaikenRedisKeyEnum.CONFIG.getKey(), daikenConfigEnum.getType());
		String cache = kemeanRedisService.getString(cacheKey);
		if (StringUtils.isNoneBlank(cache)) {
			return JSONObject.parseObject(cache, KemeanConfig.class);
		}
		KemeanConfig dbConfig = kemeanConfigDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "type" },
				new Object[] { false, daikenConfigEnum.getType() });
		if (dbConfig == null) {
			throw new KemeanException("获取数据配置异常，类型：" + daikenConfigEnum.getType());
		}
		kemeanRedisService.setString(cacheKey, JSONObject.toJSONString(dbConfig));
		return dbConfig;
	}

	/**
	 * 全国地区联动
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	public List<ComRegionBO> region(Integer pid) {
		String cacheKey = String.format(DaikenRedisKeyEnum.REGION.getKey(), pid);
		String cacheRegion = kemeanRedisService.getString(cacheKey);
		if (StringUtils.isNoneBlank(cacheRegion)) {
			return JSONObject.parseArray(cacheRegion, ComRegionBO.class);
		}
		List<KemeanRegion> dbRegions = kemeanRegionDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "pid" }, new Object[] { false, pid }, "id", true);
		List<ComRegionBO> result = Lists.transform(dbRegions, new Function<KemeanRegion, ComRegionBO>() {

			@Override
			public ComRegionBO apply(KemeanRegion item) {
				ComRegionBO bo = new ComRegionBO();
				BeanUtils.copyProperties(item, bo);
				return bo;
			}

		});
		kemeanRedisService.setString(cacheKey, JSONObject.toJSONString(result));
		return result;
	}

	/**
	 * 首页轮播图
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	public List<ComLunboBO> lunbo(Integer locatType) {
		Date now = new Date();
		Integer hour = Integer.valueOf(KemeanUtilAid.formatDate(now, "HH"));
		RecommendChargeBO recommendChargeBO = getClickShowCharge(hour);
		if (recommendChargeBO == null) {
			return new ArrayList<>();
		}
		List<KemeanLunbo> dbLunbos = new ArrayList<KemeanLunbo>();
		// 首页
		if (locatType.equals(DaikenLunboLocatTypeEnum.HOME_PAGE.getLocatType())) {
			dbLunbos = kemeanLunboDao.selectByPropertiesAndIn(
					new String[] { KemeanConstant.DATA_DELETED, "locatType", "recommendId", "isOver" },
					new Object[] { false, locatType, recommendChargeBO.getId(), false }, "recommendType",
					Arrays.asList(DaikenRecommend.RecommendType.CLICK.getType(),
							DaikenRecommend.RecommendType.SHOW.getType()),
					"id", false, 0, 0);
		}
		// 调研
		if (locatType.equals(DaikenLunboLocatTypeEnum.INVESTIGATE_PAGE.getLocatType())) {
			dbLunbos = kemeanLunboDao.selectByProperties(new String[] { KemeanConstant.DATA_DELETED, "locatType" },
					new Object[] { false, locatType });
		}
		List<ComLunboBO> result = new ArrayList<ComLunboBO>(dbLunbos.size());
		for (KemeanLunbo dbLunbo : dbLunbos) {
			// 删除已经超时的轮播图（时长）
			String info = "亲爱的用户您好，欢迎使用代研首页轮播图推荐活动，由于您此次购买的"
					+ DaikenMapData.recommendWay.get(dbLunbo.getRecommendWay());
			Date plusDay = KemeanUtilAid.getDateByCalendar(dbLunbo.getBuyTime(), KemeanCalendarFieldEnum.DAY,
					dbLunbo.getBuyDays());
			Integer dateDifference = DaikenUtil.dateDifference(now, plusDay);
			if (dateDifference < 0) {
				// 到期
				dbLunbo.setIsOver(true);
				kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
				info += "时长首页轮播图推荐活动，时间已到，将暂停服务，如需续费，请联系平台管理员";
				userService.daikenSendCode(dbLunbo.getUserPhone(), info);
				continue;
			}
			// 有效的
			ComLunboBO bo = new ComLunboBO();
			BeanUtils.copyProperties(dbLunbo, bo);
			result.add(bo);
		}
		// 查找替补广告
		if (CollectionUtils.isEmpty(result)) {
			List<KemeanLunbo> dbSubstitutionLunbos = kemeanLunboDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "isOver", "isTakeEffect", "recommendType" },
					new Object[] { false, false, true, DaikenRecommend.RecommendType.SUBSTITUTION.getType() });
			for (KemeanLunbo dbSubstitutionLunbo : dbSubstitutionLunbos) {
				ComLunboBO bo = new ComLunboBO();
				BeanUtils.copyProperties(dbSubstitutionLunbo, bo);
				result.add(bo);
			}
			if (CollectionUtils.isEmpty(dbSubstitutionLunbos)) {
				return result;
			}
		}
		return result;
	}

	/**
	 * 获取用户职业
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	public List<UserHobbiesinterestsBO> getUserOccupation(Integer pid) {
		List<DaikenUserBase> dbUserBase = daikenUserBaseDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "type", "pid" }, new Object[] { false, 1101, pid });
		List<UserHobbiesinterestsBO> result = new ArrayList<UserHobbiesinterestsBO>(dbUserBase.size());
		for (DaikenUserBase daikenUserBase : dbUserBase) {
			UserHobbiesinterestsBO bo = new UserHobbiesinterestsBO();
			BeanUtils.copyProperties(daikenUserBase, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 * 
	 */
	public KemeanPageApiBO<List<MessageBO>> messageList(KemeanPageApiPO kemeanPageApiPO, DaikenUser loginUser) {
		List<KemeanMessageUser> dbMessages = kemeanMessageUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, loginUser.getId() }, "id",
				false, kemeanPageApiPO.getPageNo(), kemeanPageApiPO.getPageSize());
		List<MessageBO> result = Lists.transform(dbMessages, new Function<KemeanMessageUser, MessageBO>() {
			@Override
			public MessageBO apply(KemeanMessageUser input) {
				MessageBO bo = new MessageBO();
				BeanUtils.copyProperties(input, bo);
				return bo;
			}
		});
		PageInfo<KemeanMessageUser> pageInfo = new PageInfo<>(dbMessages);
		return new KemeanPageApiBO<List<MessageBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 消息-删除、已读
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	@Transactional
	public KemeanResult<String> messageDelRead(MessageOperatePO messageOperatePO, DaikenUser loginUser) {
		Date now = new Date();
		for (Integer item : messageOperatePO.getArrayInt()) {
			KemeanMessageUser dbMessage = kemeanMessageUserDao.selectById(item);
			if (!dbMessage.getUserId().equals(loginUser.getId())) {
				throw new KemeanException("消息id跟用户id不匹配");
			}
			if (messageOperatePO.getDel()) {
				dbMessage.setDataDeleted(true);
			} else {
				dbMessage.setReaded(true);
			}
			dbMessage.setUpdateTime(now);
			kemeanMessageUserDao.updateByPrimaryKeySelective(dbMessage);
		}
		return new KemeanResult<>();
	}

	/**
	 * 判断用户是否个人资料填写完成
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	public static Boolean isFillInfo(DaikenUser daikenUser) {
		Boolean flag = false;
		if (daikenUser.getAge() != null && daikenUser.getProfession() != null && daikenUser.getAge() != 0
				&& daikenUser.getProfession() != 0 && StringUtils.isNotBlank(daikenUser.getHobbiesInterests())) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 提示信息
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	public KemeanResult<HelpSellRuleBO> promptMessage(Integer type) {
		HelpSellRuleBO bo = new HelpSellRuleBO();
		KemeanRichText dbRichText = kemeanRichTextDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "type" }, new Object[] { false, type });
		bo.setTitle("");
		bo.setContentHtml("");
		bo.setAwardMoney(0.0);
		if (dbRichText != null) {
			BeanUtils.copyProperties(dbRichText, bo);
		}
		return new KemeanResult<HelpSellRuleBO>(bo);
	}

	/**
	 * 刪除缓存用户
	 * 
	 * @author huangyujian
	 * @date 2018年3月21日
	 */
	public void deleteCacheUser(DaikenUser needleUser) {
		threadPoolTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String cacheKey = String.format(DaikenRedisKeyEnum.USER.getKey(), needleUser.getToken());
				KemeanUtilAid.appLog.info("【删除用户缓存】key:" + cacheKey);
				kemeanRedisService.del(cacheKey);
			}

		});
	}

	/**
	 * 获取推荐商品信息（首页推荐）
	 * 
	 * @author huwei
	 * @date 2018年7月18日
	 */
	public KemeanResult<RecommendGoodsBO> recommendGoods() {
		Date now = new Date();
		Integer hour = Integer.valueOf(KemeanUtilAid.formatDate(now, "HH"));
		DaikenGoodsRecommendCharge dbRecommendCharge = daikenGoodsRecommendChargeDao.getRecommendShowCharge(hour);
		RecommendGoodsBO result = new RecommendGoodsBO();
		if (dbRecommendCharge == null) {
			return new KemeanResult<RecommendGoodsBO>(result);
		}
		// 通过时间段配置匹配对应的活动
		List<DaikenGoodsRecommend> dbGoodsRecommends = daikenGoodsRecommendDao.recommendGoods(dbRecommendCharge.getId(),
				Arrays.asList(DaikenRecommend.RecommendType.CLICK.getType(),
						DaikenRecommend.RecommendType.SHOW.getType()));
		String info = "亲爱的用户您好，欢迎使用代研首页弹窗推荐活动，由于您此次购买的";
		for (DaikenGoodsRecommend dbGoodsRecommend : dbGoodsRecommends) {
			info += DaikenMapData.recommendWay.get(dbGoodsRecommend.getRecommendWay());
			// 删除时长显示的
			if (dbGoodsRecommend.getRecommendType().equals(DaikenRecommend.RecommendType.SHOW.getType())) {
				Date plusDay = KemeanUtilAid.getDateByCalendar(dbGoodsRecommend.getBuyTime(),
						KemeanCalendarFieldEnum.DAY, dbGoodsRecommend.getBuyDays());
				Integer dateDifference = DaikenUtil.dateDifference(now, plusDay);
				if (dateDifference <= 0) {
					dbGoodsRecommend.setIsOver(true);
					dbGoodsRecommend.setUpdateTime(now);
					daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
					info += "时长首页弹窗推荐活动时间已到，将暂停服务，如要续费，请联系平台管理员。";
					userService.daikenSendCode(dbGoodsRecommend.getUserPhone(), info);
					continue;
				}
			}
			if (result.getId() == null) {
				BeanUtils.copyProperties(dbGoodsRecommend, result);
			}
		}

		// 数据库没有广告或者上面的广告都过期了
		// 位置不能换
		if (CollectionUtils.isEmpty(dbGoodsRecommends) || result.getId() == null) {
			dbGoodsRecommends = daikenGoodsRecommendDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "recommendType" },
					new Object[] { false, DaikenRecommend.RecommendType.SUBSTITUTION.getType() });
			if (dbGoodsRecommends.isEmpty()) {
				return new KemeanResult<>(result);
			}
			// 随机取一个
			int num = (int) (Math.random() * dbGoodsRecommends.size());
			BeanUtils.copyProperties(dbGoodsRecommends.get(num), result);
		}
		return new KemeanResult<>(result);
	}

	/**
	 * 获取推荐商品信息（首页推荐）
	 * 
	 * @author huwei
	 * @date 2018年7月18日
	 */
	public KemeanResult<RecommendGoodsBO> recommendGoods2() {
		Date now = new Date();
		Integer hour = Integer.valueOf(KemeanUtilAid.formatDate(now, "HH"));
		DaikenGoodsRecommendCharge dbRecommendCharge = daikenGoodsRecommendChargeDao.getRecommendShowCharge(hour);
		List<DaikenGoodsRecommend> dbGoodsRecommends = daikenGoodsRecommendDao.recommendGoods(dbRecommendCharge.getId(),
				Arrays.asList(DaikenRecommend.RecommendType.CLICK.getType(),
						DaikenRecommend.RecommendType.SHOW.getType()));
		// 删除已经过时的，并给他们发送短信
		for (DaikenGoodsRecommend dbGoodsRecommend : dbGoodsRecommends) {
			String info = "亲爱的用户您好，欢迎使用代研首页弹窗推荐活动，由于您此次购买的"
					+ DaikenMapData.recommendWay.get(dbGoodsRecommend.getRecommendWay());
			// 删除时长显示的
			if (dbGoodsRecommend.getRecommendType().equals(DaikenRecommend.RecommendType.SHOW.getType())) {
				Date plusDay = KemeanUtilAid.getDateByCalendar(dbGoodsRecommend.getBuyTime(),
						KemeanCalendarFieldEnum.DAY, dbGoodsRecommend.getBuyDays());
				Integer dateDifference = DaikenUtil.dateDifference(now, plusDay);
				if (dateDifference <= 0) {
					dbGoodsRecommend.setIsOver(true);
					dbGoodsRecommend.setUpdateTime(now);
					daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
					info += "时长首页弹窗推荐活动时间已到，将暂停服务，如要续费，请联系平台管理员。";
					userService.daikenSendCode(dbGoodsRecommend.getUserPhone(), info);
					continue;
				}
			}
			// 删除点击显示的
			if (dbGoodsRecommend.getRecommendType().equals(DaikenRecommend.RecommendType.CLICK.getType())) {
				if (dbGoodsRecommend.getClickNum() > dbGoodsRecommend.getBuyClickNum()) {
					dbGoodsRecommend.setIsOver(true);
					dbGoodsRecommend.setUpdateTime(now);
					daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
					userService.daikenSendCode(dbGoodsRecommend.getUserPhone(), info);
					info += "点击首页弹窗推荐活动时间已到，将暂停服务，如要续费，请联系平台管理员。";
					userService.daikenSendCode(dbGoodsRecommend.getUserPhone(), info);
					continue;
				}
			}
		}
		// 再查找一次
		dbGoodsRecommends = daikenGoodsRecommendDao.recommendGoods(dbRecommendCharge.getId(), Arrays
				.asList(DaikenRecommend.RecommendType.CLICK.getType(), DaikenRecommend.RecommendType.SHOW.getType()));
		RecommendGoodsBO bo = new RecommendGoodsBO();
		if (CollectionUtils.isEmpty(dbGoodsRecommends)) {
			dbGoodsRecommends = daikenGoodsRecommendDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "recommendType" },
					new Object[] { false, DaikenRecommend.RecommendType.SUBSTITUTION.getType() });
			// 随机取一个
			int num = (int) (Math.random() * dbGoodsRecommends.size());
			BeanUtils.copyProperties(dbGoodsRecommends.get(num), bo);
		}
		if (CollectionUtils.isNotEmpty(dbGoodsRecommends)) {
			BeanUtils.copyProperties(dbGoodsRecommends.get(0), bo);
		}
		return new KemeanResult<>(bo);
	}

	/**
	 * 商品类型存入到缓存中
	 * 
	 * @author huwei
	 * @date 2018年7月20
	 */
	public DaikenGoodsBaseCategory getGoodsCategory(Integer cateId) {
		String cacheKey = String.format(DaikenRedisKeyEnum.GOODS_CATEGORY.getKey(), cateId);
		String cacheCategory = kemeanRedisService.getString(cacheKey);
		if (StringUtils.isBlank(cacheCategory)) {
			DaikenGoodsBaseCategory dbBaseCategory = daikenGoodsBaseCategoryDao.selectById(cateId);
			kemeanRedisService.setString(cacheKey, JSONObject.toJSONString(dbBaseCategory));
			return dbBaseCategory;
		}
		return JSONObject.parseObject(cacheCategory, DaikenGoodsBaseCategory.class);
	}

	/**
	 * IM会话列表
	 * 
	 * @author huangyujian
	 * @date 2018年7月23日
	 */
	public KemeanResult<List<ImRecordRedis>> imConversation(DaikenUser loginConsumer) {
		Map<String, String> allConversation = kemeanRedisService.hGetAll(
				String.format(DaikenRedisKeyEnum.IM_RECORD_USER_COMVERSATION.getKey(), loginConsumer.getUid()));
		List<ImRecordRedis> result = new ArrayList<>(allConversation.size());
		// TODO 目前是死值，得修改
		// 通知
		ImRecordRedis informBO = new ImRecordRedis();
		informBO.setHeadImg("http://p68iq7sn2.bkt.clouddn.com/upload/201808/06/19526ac388ed44a08fef8243d2e1e2a5");
		informBO.setMessageType(1101);
		// 通知操作
		informBO.setOperationType(1101);
		informBO.setName("通知");
		informBO.setContent("暂无消息");
		kemeanMessageUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "readed", "messageType" },
				new Object[] { false, loginConsumer.getId(), false, 1001 }, "id", false);
		List<KemeanMessageUser> dbMessages = kemeanMessageUserDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED, "readed", "userId" },
				new Object[] { false, false, loginConsumer.getId() }, "messageType", Arrays.asList(1001, 1101), "id",
				false, -1, -1);

		if (CollectionUtils.isNotEmpty(dbMessages)) {
			KemeanMessageUser message = dbMessages.get(0);
			informBO.setContent(message.getMessageRecord());
		}
		informBO.setToUserId("");
		informBO.setFromUserId(loginConsumer.getUid() + "");
		informBO.setMessageNum(dbMessages.size());
		informBO.setDate(new Date());
		result.add(informBO);
		// TODO 未完成 客服
		ImRecordRedis serviceBO = new ImRecordRedis();
		serviceBO.setHeadImg("http://p68iq7sn2.bkt.clouddn.com/upload/201808/06/c6120a522e0941eb8449a337d4ff2d3b");
		informBO.setMessageType(1101);
		serviceBO.setName("暂无客服");
		serviceBO.setOperationType(1201);
		serviceBO.setContent("暂无消息");
		serviceBO.setToUserId("0");
		serviceBO.setFromUserId(loginConsumer.getUid() + "");
		if (loginConsumer.getServiceMark() != null && loginConsumer.getServiceMark() != 0) {
			KemeanAdminUser serviceUser = kemeanAdminUserDao.selectUniqueEntityByProperty("uid",
					loginConsumer.getServiceMark());
			serviceBO.setName("代研客服【 " + serviceUser.getName() + "】");
			serviceBO.setToUserId(loginConsumer.getServiceMark() + "");

		}

		String serviceMessageNum = kemeanRedisService
				.getString(String.format(DaikenRedisKeyEnum.IM_RECORD_MESSAGE_NUMBER.getKey(), loginConsumer.getUid(),
						loginConsumer.getServiceMark()));
		serviceBO.setMessageNum(0);
		if (StringUtils.isNotBlank(serviceMessageNum)) {
			serviceBO.setMessageNum(Integer.valueOf(serviceMessageNum));
		}

		serviceBO.setDate(new Date());
		result.add(serviceBO);

		if (allConversation.isEmpty()) {
			return new KemeanResult<List<ImRecordRedis>>(result);
		}
		for (String item : allConversation.keySet()) {
			String conversation = allConversation.get(item);
			ImRecordRedis imRecordPO = JSONObject.parseObject(conversation, ImRecordRedis.class);
			// 发送人
			String fromUserId = imRecordPO.getFromUserId();
			// 接收人
			String toUserId = imRecordPO.getToUserId();
			imRecordPO.setToUserId(toUserId);
			imRecordPO.setFromUserId(fromUserId);
			// 消息操作
			imRecordPO.setOperationType(1301);
			String messageNum = kemeanRedisService.getString(String
					.format(DaikenRedisKeyEnum.IM_RECORD_MESSAGE_NUMBER.getKey(), loginConsumer.getUid(), fromUserId));
			imRecordPO.setMessageNum(0);
			if (StringUtils.isNotBlank(messageNum)) {
				imRecordPO.setMessageNum(Integer.valueOf(messageNum));
			}
			result.add(imRecordPO);
		}
		return new KemeanResult<List<ImRecordRedis>>(result);
	}

	/**
	 * 消息已读
	 * 
	 * @author huangyujian
	 * @date 2018年7月23日
	 */
	public void imConversationOperate(String toUserId, String fromUserId, Boolean del) {
		String cacheKey = String.format(DaikenRedisKeyEnum.IM_RECORD_MESSAGE_NUMBER.getKey(), fromUserId, toUserId);
		if (BooleanUtils.isFalse(del)) {
			kemeanRedisService.setString(cacheKey, 0 + "");
		} else {
			kemeanRedisService.del(cacheKey);
			kemeanRedisService.hdel(String.format(DaikenRedisKeyEnum.IM_RECORD_USER_COMVERSATION.getKey(), fromUserId),
					toUserId);
		}
	}

	/**
	 * 根据UID获取头像昵称信息
	 * 
	 * @author huwei
	 * @date 2018年7月23日
	 */
	public KemeanResult<List<UserInfoBO>> getUserInfo(UserInfoPO userInfoPO) {
		List<UserInfoBO> result = new ArrayList<UserInfoBO>();
		List<Integer> uids = userInfoPO.getUids();
		if (CollectionUtils.isNotEmpty(uids)) {
			for (Integer uid : uids) {
				UserInfoBO bo = new UserInfoBO();
				DaikenUser dbUser = daikenUserDao.selectUniqueEntityByProperty("uid", uid);
				if (dbUser != null) {
					BeanUtils.copyProperties(dbUser, bo);
					if (dbUser.getUserType().equals(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType())) {
						DaikenShop dbShop = daikenShopDao.selectById(dbUser.getShopId());
						bo.setNickName(dbShop.getShopName());
						bo.setHeadImg(dbShop.getShopLogo());
					}
				}
				result.add(bo);
			}
		}
		return new KemeanResult<List<UserInfoBO>>(result);
	}

	/**
	 * 查询物流信息
	 * 
	 * @author huwei
	 * @date 2018年7月24日
	 */
	public KemeanResult<ExpressInfoBO> getExpressInfo(String orderNo) {
		ExpressInfoBO bo = new ExpressInfoBO();
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		bo.setCompanyName("");
		bo.setExpressWaybillNo("");
		List<ThirdLogisticsInfoDataBO> thirdLogisticsInfoByOther = new ArrayList<ThirdLogisticsInfoDataBO>();
		if (StringUtils.isNotBlank(dbOrder.getRecordLogistics())) {
			LogisticsInfoDB logisticsInfoDB = JSONObject.parseObject(dbOrder.getRecordLogistics(),
					LogisticsInfoDB.class);
			BeanUtils.copyProperties(logisticsInfoDB, bo);
			try {
				thirdLogisticsInfoByOther = logisticsService
						.getThirdLogisticsInfoByOther(logisticsInfoDB.getExpressWaybillNo());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<DaikenOrderGoods> dbOrderGoods = daikenOrderGoodsDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, orderNo });
		List<OrderListGoodsBO> orderListGoods = new ArrayList<OrderListGoodsBO>(dbOrderGoods.size());
		for (DaikenOrderGoods dbOrderGood : dbOrderGoods) {
			OrderListGoodsBO goodsBO = new OrderListGoodsBO();
			GoodsRecordDB orderGoodsRecordDB = JSONObject.parseObject(dbOrderGood.getGoodsRecord(),
					GoodsRecordDB.class);
			BeanUtils.copyProperties(orderGoodsRecordDB, goodsBO);
			goodsBO.setRecordType("");
			goodsBO.setSkuNo("");
			if (dbOrder.getIsNewGoods()) {
				goodsBO.setRecordType(DaikenUtil.parseJsonArray(orderGoodsRecordDB.getRecordType()));
				goodsBO.setSkuNo(orderGoodsRecordDB.getSkuNo());
			}
			goodsBO.setGoodsId(dbOrderGood.getGoodsId());
			goodsBO.setQuantity(dbOrder.getQuantity());
			orderListGoods.add(goodsBO);
		}
		bo.setOrderListGoods(orderListGoods);
		List<TracesBO> resultTraces = new ArrayList<TracesBO>();
		if (CollectionUtils.isNotEmpty(thirdLogisticsInfoByOther)) {
			for (ThirdLogisticsInfoDataBO thirdLogisticsInfoDataBO : thirdLogisticsInfoByOther) {
				TracesBO tracesBO = new TracesBO();
				tracesBO.setAcceptStation(thirdLogisticsInfoDataBO.getContext());
				tracesBO.setAcceptTime(thirdLogisticsInfoDataBO.getTime());
				tracesBO.setRemark("");
				resultTraces.add(tracesBO);
			}
		}
		bo.setTraces(resultTraces);
		return new KemeanResult<ExpressInfoBO>(bo);
	}

	/**
	 * 商品推荐 获取收取的费用
	 * 
	 * @author huwei
	 * @date 2018年8月30日
	 */
	public RecommendChargeBO getClickShowCharge(Integer hour) {
		hour = hour + 1;
		RecommendChargeBO bo = new RecommendChargeBO();
		bo.setId(0);
		bo.setClickCharge(0.0);
		bo.setShowCharge(0.0);
		DaikenGoodsRecommendCharge dbRecommendCharge = daikenGoodsRecommendChargeDao.getRecommendShowCharge(hour);
		if (dbRecommendCharge != null) {
			BeanUtils.copyProperties(dbRecommendCharge, bo);
		}
		return bo;
	}

	/**
	 * 计算新的广告推荐
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	public void findHotTreasure(Integer adOrderSort) {
		// 查找是否有进行中的相同屏位商品
		List<DaikenGoodsHotTreasure> dbGoodsHotTreasures = daikenGoodsHotTreasureDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "screenPosition", "currentState", "status" },
				new Object[] { false, adOrderSort, DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState(), true });
		// 不为空，代表有人出钱买了广告，只是没有顶上来
		if (CollectionUtils.isNotEmpty(dbGoodsHotTreasures)) {
			DaikenGoodsHotTreasure dbGoodsHotTreasure = dbGoodsHotTreasures.get(0);
			dbGoodsHotTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState());
			dbGoodsHotTreasure.setUpdateTime(new Date());
			daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbGoodsHotTreasure);
			DaikenGoodsNew dbHotGoodsNew = daikenGoodsNewDao.selectById(dbGoodsHotTreasure.getGoodsId());
			dbHotGoodsNew.setRecommend(true);
			dbHotGoodsNew.setAdOrderSort(adOrderSort);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbHotGoodsNew);

			// TODO 发送广告开始短信
			return;
		}
		// 为空，现在需要替补广告
		if (CollectionUtils.isEmpty(dbGoodsHotTreasures)) {
			Integer type = 0;
			// 随机选择一个一屏的替补广告
			if (adOrderSort.equals(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN.getType())) {
				type = DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType();
			}
			// 随机选择一个二屏的替补广告
			if (adOrderSort.equals(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN.getType())) {
				type = DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType();
			}
			// 随机选择一个三屏的替补广告
			if (adOrderSort.equals(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN.getType())) {
				type = DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType();
			}
			List<DaikenGoodsHotTreasure> dbSubstitutionHotTreasures = daikenGoodsHotTreasureDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "screenPosition", "status", "currentState" },
					new Object[] { false, type, true, DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState() });
			if (CollectionUtils.isNotEmpty(dbSubstitutionHotTreasures)) {
				// 随机选一个
				int num = (int) (Math.random() * dbSubstitutionHotTreasures.size());
				DaikenGoodsHotTreasure dbSubstitutionHotTreasure = dbSubstitutionHotTreasures.get(num);
				dbSubstitutionHotTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState());
				dbSubstitutionHotTreasure.setUpdateTime(new Date());
				daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbSubstitutionHotTreasure);
				DaikenGoodsNew dbSubstitutionGoodsNew = daikenGoodsNewDao
						.selectById(dbSubstitutionHotTreasure.getGoodsId());
				dbSubstitutionGoodsNew.setRecommend(true);
				dbSubstitutionGoodsNew.setAdOrderSort(type);
				daikenGoodsNewDao.updateByPrimaryKeySelective(dbSubstitutionGoodsNew);
			}
		}
	}

	public void createFinanceOrder(String orderNo, Integer financeType, Double money, Integer userId, Integer shopId,
			String name, String record) {
		Date now = new Date();
		KemeanFinanceOrder newFinanceOrder = new KemeanFinanceOrder();
		newFinanceOrder.setOrderNo(orderNo);
		newFinanceOrder.setFinanceType(financeType);
		newFinanceOrder.setMoney(money);
		newFinanceOrder.setUserId(userId);
		newFinanceOrder.setBusinessId(shopId);
		newFinanceOrder.setName(name);
		newFinanceOrder.setFinanceMonth(DaikenUtil.formatDate(now, KemeanDateFormatEnum.YEAR_MONTH));
		newFinanceOrder.setFinanceData(now);
		newFinanceOrder.setCreateTime(now);
		newFinanceOrder.setUpdateTime(now);
		kemeanFinanceOrderDao.saveSelective(newFinanceOrder);
	}

	/**
	 * 结算流水
	 * 
	 * @author huwei
	 * @date 2018年9月18日
	 */
	public KemeanPageApiBO<List<ClearOrderListBO>> clearOrderList(ClearOrderListPO clearOrderListPO,
			DaikenUser daikenUser) {
		Integer days = -7;
		if (clearOrderListPO.getDays() != null) {
			days = -clearOrderListPO.getDays();
		}
		Date plusDay = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, days);
		List<KemeanFinanceClear> dbFinanceClears = kemeanFinanceClearDao.clearOrderList(daikenUser.getId(), plusDay);
		List<ClearOrderListBO> result = new ArrayList<ClearOrderListBO>(dbFinanceClears.size());
		if (CollectionUtils.isEmpty(dbFinanceClears)) {
			return new KemeanPageApiBO<List<ClearOrderListBO>>(0l, 1, result);
		}
		for (KemeanFinanceClear dbFinanceClear : dbFinanceClears) {
			ClearOrderListBO bo = new ClearOrderListBO();
			BeanUtils.copyProperties(dbFinanceClear, bo);
			bo.setFinanceStateStr(KemeanMapData.financeStatus.get(dbFinanceClear.getFinanceStatus()));
			bo.setBalanceStr("¥" + DaikenUtil.formatDouble(dbFinanceClear.getBalance(), 2));
			bo.setDealMoneyStr("待处理");
			bo.setPlatformServiceMoneyStr("待处理");
			bo.setSubmitMoneyStr("¥" + DaikenUtil.formatDouble(dbFinanceClear.getSubmitMoney(), 2));
			if (!dbFinanceClear.getFinanceStatus().equals(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus())) {
				bo.setDealMoneyStr("¥" + DaikenUtil.formatDouble(dbFinanceClear.getDealMoney(), 2));
				Double platformServiceMoney = dbFinanceClear.getSubmitMoney() - dbFinanceClear.getDealMoney();
				bo.setPlatformServiceMoneyStr("¥" + DaikenUtil.formatDouble(platformServiceMoney, 2));
			}
			bo.setFinanceDataStr(DaikenUtil.formatDate(dbFinanceClear.getFinanceData(), KemeanDateFormatEnum.DATE));
			result.add(bo);
		}
		PageInfo<KemeanFinanceClear> pageInfo = new PageInfo<KemeanFinanceClear>(dbFinanceClears);
		return new KemeanPageApiBO<List<ClearOrderListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 塞入限时折扣TimeType
	 * 
	 * @author huwei
	 * @date 2018年9月21日
	 */
	public Integer setLimitedTime(DaikenGoodsNew daikenGoods) {
		Date now = new Date();
		Integer beginTime = DaikenUtil.dateDifferenceSecond(now, daikenGoods.getDiscountTimeBegin());
		if (beginTime < 0) {
			return DaikenConstant.not_begin;
		}
		Integer endTime = DaikenUtil.dateDifferenceSecond(daikenGoods.getDiscountTimeEnd(), now);
		if (endTime <= 0) {
			return DaikenConstant.over;
		}
		return DaikenConstant.underway;
	}

}
