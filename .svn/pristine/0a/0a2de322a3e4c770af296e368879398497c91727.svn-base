package com.kemean.service.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsHotTreasure;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsOld;
import com.kemean.bean.DaikenHistorySearch;
import com.kemean.bean.DaikenInvestigate;
import com.kemean.bean.DaikenRedShare;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenConstant;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenHotTreasure;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateJumpTypeEnum;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateTypeEnum;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenShareTypeEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.DaikenWoolLabelTypeEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsHotTreasureDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenGoodsOldDao;
import com.kemean.dao.DaikenHistorySearchDao;
import com.kemean.dao.DaikenInvestigateDao;
import com.kemean.dao.DaikenRedShareDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.c.common.HistorySearchBO;
import com.kemean.vo.bo.c.common.SearchInfoBO;
import com.kemean.vo.bo.c.mall.NewGoodsActivityListBO;
import com.kemean.vo.bo.com.ShareRedPacketBO;
import com.kemean.vo.po.KemeanPageApiPO;
import com.kemean.vo.po.c.common.GoodsActivityPO;
import com.kemean.vo.po.c.common.SearchGoodsPO;
import com.kemean.vo.po.c.common.ShareRedPacketPO;

/**
 * 【客户端】首页业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service("userCommonService")
public class HomePageService {

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenRedShareDao daikenRedShareDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsOldDao daikenGoodsOldDao;

	@Autowired
	private DaikenInvestigateDao daikenInvestigateDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenHistorySearchDao daikenHistorySearchDao;

	@Autowired
	private DaikenGoodsHotTreasureDao daikenGoodsHotTreasureDao;

	@Autowired
	private CMallService cMallService;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	/**
	 * 搜索(1101 一手所有，1201二手，1301 一手帮卖)
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	public KemeanPageApiBO<List<SearchInfoBO>> search(SearchGoodsPO searchGoodsPO, DaikenUser daikenUser) {
		// 一手商品 全部商品
		if (searchGoodsPO.getSearchType().equals(1101)) {
			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.search(searchGoodsPO.getSearchInfo(),
					searchGoodsPO.getPageNo(), searchGoodsPO.getPageSize(), false);
			List<SearchInfoBO> result = new ArrayList<SearchInfoBO>(dbGoodsNews.size());
			if (CollectionUtils.isEmpty(dbGoodsNews)) {
				return new KemeanPageApiBO<List<SearchInfoBO>>(0l, 1, result);
			}
			for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
				SearchInfoBO bo = new SearchInfoBO();
				BeanUtils.copyProperties(dbGoodsNew, bo);
				bo.setGoodsId(dbGoodsNew.getId());
				bo.setJump(1101);
				result.add(bo);
			}
			PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
			return new KemeanPageApiBO<List<SearchInfoBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
		}
		// 二手商品 全部商品
		if (searchGoodsPO.getSearchType().equals(1201)) {
			List<DaikenGoodsOld> dbGoodsOlds = daikenGoodsOldDao.search(searchGoodsPO.getSearchInfo(),
					searchGoodsPO.getPageNo(), searchGoodsPO.getPageSize());
			List<SearchInfoBO> result = new ArrayList<SearchInfoBO>(dbGoodsOlds.size());
			if (CollectionUtils.isEmpty(dbGoodsOlds)) {
				return new KemeanPageApiBO<List<SearchInfoBO>>(0l, 1, result);
			}
			for (DaikenGoodsOld dbGoodsOld : dbGoodsOlds) {
				SearchInfoBO bo = new SearchInfoBO();
				BeanUtils.copyProperties(dbGoodsOld, bo);
				bo.setGoodsId(dbGoodsOld.getId());
				bo.setJump(1201);
				result.add(bo);
			}
		}
		// 一手商品 只有能代卖商品
		if (searchGoodsPO.getSearchType().equals(1301)) {
			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.search(searchGoodsPO.getSearchInfo(),
					searchGoodsPO.getPageNo(), searchGoodsPO.getPageSize(), true);
			List<SearchInfoBO> result = new ArrayList<SearchInfoBO>(dbGoodsNews.size());
			if (CollectionUtils.isEmpty(dbGoodsNews)) {
				return new KemeanPageApiBO<List<SearchInfoBO>>(0l, 1, result);
			}
			for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
				SearchInfoBO bo = new SearchInfoBO();
				BeanUtils.copyProperties(dbGoodsNew, bo);
				bo.setGoodsId(dbGoodsNew.getId());
				bo.setJump(1101);
				result.add(bo);
			}
			PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
			return new KemeanPageApiBO<List<SearchInfoBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
		}
		return new KemeanPageApiBO<List<SearchInfoBO>>(0l, 1, new ArrayList<SearchInfoBO>());
	}

	/**
	 * 商品活动
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	public KemeanPageApiBO<List<NewGoodsActivityListBO>> goodsActivity(GoodsActivityPO goodsActivityPO) {
		String descStr = "";
		if (goodsActivityPO.getIsHot() != null) {
			if (goodsActivityPO.getIsHot()) {
				descStr += " sales_num, ";
			}
		}
		if (goodsActivityPO.getRedPacket() != null) {
			if (goodsActivityPO.getRedPacket()) {
				descStr += " red_after, ";
			}
		}
		if (goodsActivityPO.getDiscount() != null) {
			if (goodsActivityPO.getDiscount()) {
				descStr += " min_discount, ";
			}
		}
		if (StringUtils.isNotBlank(descStr)) {
			descStr = descStr.substring(0, descStr.length() - 2);
		}
		// 设置一个最大值
		Double maxPriceSales = 1000000000.0;
		if (goodsActivityPO.getMaxPriceSales() != null) {
			maxPriceSales = goodsActivityPO.getMaxPriceSales();
		}
		// 设置一个最小值
		Double minPriceSales = 0.0;
		if (goodsActivityPO.getMinPriceSales() != null) {
			minPriceSales = goodsActivityPO.getMinPriceSales();
		}
		List<Integer> categoryIds = new ArrayList<Integer>();
		if (goodsActivityPO.getCategoryId() == null) {
			// 加载全部的商品分类
			List<DaikenGoodsBaseCategory> dbGoodsBaseCategorys = daikenGoodsBaseCategoryDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "level" }, new Object[] { false, 3 });
			if (CollectionUtils.isNotEmpty(dbGoodsBaseCategorys)) {
				for (DaikenGoodsBaseCategory daikenGoodsBaseCategory : dbGoodsBaseCategorys) {
					categoryIds.add(daikenGoodsBaseCategory.getId());
				}
			}
		} else {
			categoryIds.add(goodsActivityPO.getCategoryId());
		}

		String keyWord = "";
		if (goodsActivityPO.getKeyWord() != null) {
			keyWord = goodsActivityPO.getKeyWord();
		}
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.goodsActivity(
				Arrays.asList(goodsActivityPO.getSalesType()), descStr, minPriceSales, maxPriceSales, categoryIds,
				keyWord, goodsActivityPO.getPageNo(), goodsActivityPO.getPageSize());
		List<NewGoodsActivityListBO> result = new ArrayList<NewGoodsActivityListBO>(dbGoodsNews.size());
		if (CollectionUtils.isEmpty(dbGoodsNews)) {
			return new KemeanPageApiBO<List<NewGoodsActivityListBO>>(0l, 1, result);
		}
		for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
			NewGoodsActivityListBO bo = new NewGoodsActivityListBO();
			BeanUtils.copyProperties(dbGoodsNew, bo);
			Integer countStock = daikenGoodsNewSkuDao.countStock(dbGoodsNew.getId());
			bo.setId(dbGoodsNew.getId());
			bo.setPriceSales(dbGoodsNew.getMinPriceSales());
			bo.setPriceStore(dbGoodsNew.getMinPriceStore());
			bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			bo.setSalesTypeStr(cMallService.parseSalesTypeStr(dbGoodsNew.getSalesType()));
			if (dbGoodsNew.getUserShopId() == null || dbGoodsNew.getUserShopId() == 0) {
				bo.setUserShopId(0);
			}
			if (dbGoodsNew.getGoodsId() != null && dbGoodsNew.getGoodsId() != 0) {
				bo.setId(dbGoodsNew.getGoodsId());
			}
			bo.setCountStock(countStock);
			bo.setTimeType(DaikenConstant.underway);
			if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
				bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
			}
			result.add(bo);
		}
		PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
		return new KemeanPageApiBO<List<NewGoodsActivityListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 分享红包
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	@Transactional
	public KemeanResult<ShareRedPacketBO> shareRedPacket(ShareRedPacketPO shareRedPacketPO, DaikenUser loginConsumer) {
		Date now = new Date();
		Integer typeId = 0;
		if (shareRedPacketPO.getIsNewGoods()) {
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(shareRedPacketPO.getTypeId());
			if (dbGoodsNew.getGoodsId() != null && dbGoodsNew.getGoodsId() != 0) {
				typeId = dbGoodsNew.getGoodsId();
			} else {
				typeId = dbGoodsNew.getId();
			}
		}
		if (!shareRedPacketPO.getIsNewGoods()) {
			DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(shareRedPacketPO.getTypeId());
			if (dbGoodsOld.getGoodsId() != null && dbGoodsOld.getGoodsId() != 0) {
				typeId = dbGoodsOld.getGoodsId();
			} else {
				typeId = dbGoodsOld.getId();
			}
		}
		ShareRedPacketBO bo = new ShareRedPacketBO();
		bo.setInvestigateId(0);
		if (loginConsumer.getWoolLabelDate() != null) {
			Integer dateDifference = DaikenUtil.dateDifference(now, loginConsumer.getWoolLabelDate());
			if (loginConsumer.getWoolLabel() && dateDifference > 0) {
				bo.setWoolLabelDateStr(DaikenUtil.formatDate(loginConsumer.getWoolLabelDate()));
				bo.setRelieveWoolType(DaikenWoolLabelTypeEnum.FORBIDDEN_ONE_WEEK.getType());
				if (loginConsumer.getWoolLabelNum() > 5) {
					bo.setRelieveWoolType(DaikenWoolLabelTypeEnum.FORBIDDEN_PERPETUAL.getType());
				}
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHARE_RED_PACKET_FORBIDDEN);
			}
		}
		// 一天只能分享三次 ，如果有购买能力，可以再发三次
		Boolean isThreeDay = daikenRedShareDao.countGoodsThreeDay(loginConsumer.getId(), typeId,
				DaikenUtil.formatDate(now, KemeanDateFormatEnum.DATE), shareRedPacketPO.getIsNewGoods());
		if (isThreeDay) {
			bo.setRelieveWoolType(DaikenWoolLabelTypeEnum.TODAY_REPETITION.getType());
			return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.TODAY_SHARE_TANTO);
		}
		// 判断用户是否已经超过三次
		Integer countrelieveWool = loginConsumer.getWoolLabelNum();
		if (countrelieveWool >= 3 && countrelieveWool <= 5) {
			loginConsumer.setWoolLabel(true);
			loginConsumer.setUpdateTime(now);
			loginConsumer.setWoolLabelDate(KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, 7));
			loginConsumer.setCause(DaikenApiResultTips.ShareRedPacket.SHARE_RED_PACKET_FORBIDDEN_TWO);
			daikenUserDao.updateByPrimaryKeySelective(loginConsumer);
			bo.setRelieveWoolType(DaikenWoolLabelTypeEnum.FORBIDDEN_ONE_WEEK.getType());
			bo.setWoolLabelDateStr(
					DaikenUtil.formatDate(KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, 7)));
			return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHARE_RED_PACKET_FORBIDDEN_TWO);
		}
		if (countrelieveWool > 5) {
			loginConsumer.setWoolLabel(true);
			loginConsumer.setUpdateTime(now);
			loginConsumer
					.setWoolLabelDate(KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, 3600));
			loginConsumer.setCause(DaikenApiResultTips.ShareRedPacket.SHARE_RED_PACKET_FORBIDDEN_THREE);
			daikenUserDao.updateByPrimaryKeySelective(loginConsumer);
			bo.setRelieveWoolType(DaikenWoolLabelTypeEnum.FORBIDDEN_PERPETUAL.getType());
			bo.setWoolLabelDateStr(DaikenUtil
					.formatDate(KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, 3600)));
			return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHARE_RED_PACKET_FORBIDDEN_THREE);
		}
		// 类型
		Integer type = 0;
		// 版本号
		Integer typeDateVersion = 0;
		// 调研标题
		String title = "";
		// 售前红包金额
		Double redBefore = 0.0;
		// 商铺名称
		String shopName = "二手商铺";
		// 调研说明 = 商品信息
		String imgsHead = "";
		String headStr = "";
		// 调研type
		Integer investigateType = 0;
		// 商铺id
		Integer shopId = 0;
		// 商品名称
		String goodsTitle = "";
		// 商品id
		Integer goodsId = 0;
		// 跳转类型
		Integer jumpType = 0;
		// 同个商品一天只能分享一次
		// 一手
		if (shareRedPacketPO.getIsNewGoods()) {
			Boolean isOnecDay = daikenRedShareDao.countGoodsOnecDay(loginConsumer.getId(),
					DaikenShareTypeEnum.NEW_GOODS.getType(), typeId,
					DaikenUtil.formatDate(now, KemeanDateFormatEnum.DATE), shareRedPacketPO.getIsNewGoods());
			if (isOnecDay) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.GOODS_ONE_DAY_ONCE);
			}
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(typeId);
			type = DaikenShareTypeEnum.NEW_GOODS.getType();
			investigateType = DaikenInvestigateTypeEnum.NEW_GOODS_LIKE.getType();
			if (dbGoodsNew.getNumShare() > 5) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHARE_NUM_BEYOND);
			}
			if (dbGoodsNew == null || !dbGoodsNew.getGoodsStatus()) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.GOODS_NO_OR_SOLD_OUT);
			}
			dbGoodsNew.setNumShare(dbGoodsNew.getNumShare() + 1);
			dbGoodsNew.setUpdateTime(now);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
			shopId = dbGoodsNew.getShopId();
			goodsTitle = dbGoodsNew.getTitle();
			typeDateVersion = dbGoodsNew.getDateVersion();
			title = dbGoodsNew.getTitle();
			redBefore = dbGoodsNew.getRedBefore();
			DaikenUser dbUser = daikenUserDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userType", "shopId" }, new Object[] { false,
							DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType(), dbGoodsNew.getShopId() });
			Double blance = userService.getConsumerOrBusinessBlance(dbUser);
			if (blance < redBefore) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHOP_MONEY_INSUFFICIENT);
			}
			DaikenShop dbShop = daikenShopDao.selectById(dbGoodsNew.getShopId());
			shopName = dbShop.getShopName();
			headStr = dbGoodsNew.getDescStr();
			imgsHead = dbGoodsNew.getImgsHead();
			goodsId = dbGoodsNew.getId();
			jumpType = DaikenInvestigateJumpTypeEnum.GOODS_INFO.getType();
		}

		// 同个商品一天只能分享一次
		// 二手
		if (!shareRedPacketPO.getIsNewGoods()) {
			Boolean isOnecDay = daikenRedShareDao.countGoodsOnecDay(loginConsumer.getId(),
					DaikenShareTypeEnum.OLD_GOODS.getType(), typeId,
					DaikenUtil.formatDate(now, KemeanDateFormatEnum.DATE), shareRedPacketPO.getIsNewGoods());
			if (isOnecDay) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.GOODS_ONE_DAY_ONCE);
			}
			DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(typeId);
			DaikenUser dbUser = daikenUserDao.selectById(dbGoodsOld.getUserId());
			if (dbUser.getBalancePrice() < redBefore) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHOP_MONEY_INSUFFICIENT);
			}

			type = DaikenShareTypeEnum.OLD_GOODS.getType();
			investigateType = DaikenInvestigateTypeEnum.OLD_GOODS_LIKE.getType();
			if (dbGoodsOld.getNumShare() > 5) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.SHARE_NUM_BEYOND);
			}
			if (dbGoodsOld == null || !dbGoodsOld.getGoodsStatus()) {
				return new KemeanResult<>(false, DaikenApiResultTips.ShareRedPacket.GOODS_NO_OR_SOLD_OUT);
			}
			dbGoodsOld.setNumShare(dbGoodsOld.getNumShare() + 1);
			dbGoodsOld.setUpdateTime(now);
			daikenGoodsOldDao.updateByPrimaryKeySelective(dbGoodsOld);
			typeDateVersion = dbGoodsOld.getDateVersion();
			title = dbGoodsOld.getTitle();
			redBefore = dbGoodsOld.getRedBefore();
			headStr = dbGoodsOld.getDescStr();
			imgsHead = dbGoodsOld.getImgsHead();
			goodsTitle = dbGoodsOld.getTitle();
			goodsId = dbGoodsOld.getGoodsId();
			jumpType = DaikenInvestigateJumpTypeEnum.OLD_GOODS_INFO.getType();
		}
		String token = DaikenUtil.getUUIDString();
		DaikenRedShare newRedShare = new DaikenRedShare();
		newRedShare.setUserId(loginConsumer.getId());
		newRedShare.setUserNickName(loginConsumer.getNickName());
		newRedShare.setToken(token);
		newRedShare.setGoodsTitle(goodsTitle);
		newRedShare.setType(type);
		newRedShare.setShopId(shopId);
		newRedShare.setTypeId(typeId);
		newRedShare.setTypeDateVersion(typeDateVersion);
		newRedShare.setCreateTime(now);
		newRedShare.setUpdateTime(now);
		daikenRedShareDao.saveSelective(newRedShare);
		// 调研添加
		DaikenInvestigate newInvestigate = new DaikenInvestigate();
		newInvestigate.setUserId(loginConsumer.getId());
		newInvestigate.setUserUId(loginConsumer.getUid());
		newInvestigate.setToken(token);
		newInvestigate.setTitle(title);
		// TODO 红包分享次数由平台设置，现在默认是五次
		newInvestigate.setRewardPrice(redBefore / 50);
		newInvestigate.setMaxPeopleNum(10);
		newInvestigate.setType(investigateType);
		// 三天后结束
		newInvestigate.setEndTime(KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, 3));
		newInvestigate.setInitiatorUnit(shopName);
		newInvestigate.setJumpType(jumpType);
		newInvestigate.setJumpTypeId(goodsId);
		// 调研说明
		newInvestigate.setInvestigateExplain(headStr);
		newInvestigate.setInvestigateImg(imgsHead);
		// 默认上架 和 已支付
		newInvestigate.setInvestigateStatus(true);
		newInvestigate.setPayStatus(true);
		newInvestigate.setCreateTime(now);
		newInvestigate.setUpdateTime(now);
		daikenInvestigateDao.saveSelective(newInvestigate);
		bo.setInvestigateId(newInvestigate.getId());
		bo.setRelieveWoolType(DaikenWoolLabelTypeEnum.ORDINARY.getType());
		loginConsumer.setWoolLabelNum(loginConsumer.getWoolLabelNum() + 1);
		newInvestigate.setPayTime(now);
		loginConsumer.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(loginConsumer);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), loginConsumer.getToken()));
		if (shareRedPacketPO.getIsNewGoods()) {
			// 一手商品生成流水
			commonService.createFinanceOrder(String.valueOf(goodsId),
					DaikenFinanceTypeEnum.B_AFTER_RED_INCOME.getType(), redBefore, 0, shopId, goodsTitle, "【售前红包（支出）】");
		}
		return new KemeanResult<>(true, DaikenApiResultTips.ShareRedPacket.RED_PACKET_SUCCESS);
	}

	/**
	 * 历史搜索
	 * 
	 * @author huwei
	 * @date 2018年8月9日
	 */
	public KemeanResult<List<HistorySearchBO>> historySearch(DaikenUser loginConsumer) {
		List<DaikenHistorySearch> dbSearchs = daikenHistorySearchDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, loginConsumer.getId() });
		List<HistorySearchBO> result = new ArrayList<HistorySearchBO>(dbSearchs.size());
		if (CollectionUtils.isEmpty(dbSearchs)) {
			return new KemeanResult<List<HistorySearchBO>>(result);
		}
		for (DaikenHistorySearch dbSearch : dbSearchs) {
			HistorySearchBO bo = new HistorySearchBO();
			BeanUtils.copyProperties(dbSearch, bo);
			result.add(bo);
		}
		return new KemeanResult<List<HistorySearchBO>>(result);
	}

	/**
	 * 删除历史搜索
	 * 
	 * @author huwei
	 * @date 2018年8月10日
	 */
	public KemeanResult<String> delSearch(DaikenUser daikenUser) {
		daikenHistorySearchDao.updateEntityByProperties("dataDeleted", true, new String[] { "userId" },
				new Object[] { daikenUser.getId() });
		return new KemeanResult<String>();
	}

	/**
	 * 热销宝贝（推荐宝贝）
	 * 
	 * @author huwei
	 * @date 2018年9月6日
	 */
	@Transactional
	public KemeanPageApiBO<List<NewGoodsActivityListBO>> recommendTreasure(KemeanPageApiPO kemeanPageApiPO) {
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsStatus", "recommend", "auditStatus" },
				new Object[] { false, true, true, KemeanSettledEnum.AUDIT_PASS.getStatus() }, "ad_order_sort", true,
				kemeanPageApiPO.getPageNo(), kemeanPageApiPO.getPageSize());
		List<NewGoodsActivityListBO> result = new ArrayList<NewGoodsActivityListBO>(dbGoodsNews.size());
		if (CollectionUtils.isEmpty(dbGoodsNews)) {
			List<DaikenGoodsHotTreasure> dbHotTreasures = daikenGoodsHotTreasureDao.selectByPropertiesAndIn(
					new String[] { KemeanConstant.DATA_DELETED, "status" }, new Object[] { false, true },
					"screenPosition",
					Arrays.asList(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType(),
							DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType(),
							DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType()),
					"screen_position", true, kemeanPageApiPO.getPageNo(), kemeanPageApiPO.getPageSize());
			if (CollectionUtils.isEmpty(dbHotTreasures)) {
				return new KemeanPageApiBO<List<NewGoodsActivityListBO>>(0L, 1, result);
			}
			// 根据屏位 选择替补的填充进去 如果有多个替补的话，顺序抽取4个
			for (DaikenGoodsHotTreasure dbHotTreasure : dbHotTreasures) {
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbHotTreasure.getGoodsId());
				List<DaikenGoodsNew> dbGoodsSubstitutionNews = daikenGoodsNewDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "adOrderSort", "recommend" },
						new Object[] { false, dbHotTreasure.getScreenPosition(), true });
				if (dbGoodsSubstitutionNews.size() == 4) {
					continue;
				}
				dbGoodsNew.setRecommend(true);
				dbGoodsNew.setAdOrderSort(dbHotTreasure.getScreenPosition());
				dbGoodsNew.setUpdateTime(new Date());
				daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
				dbHotTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState());
				dbHotTreasure.setUpdateTime(new Date());
				daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbHotTreasure);
			}
			dbGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsStatus", "recommend" },
					new Object[] { false, true, true }, "ad_order_sort", true, kemeanPageApiPO.getPageNo(),
					kemeanPageApiPO.getPageSize());
		}
		for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
			NewGoodsActivityListBO bo = new NewGoodsActivityListBO();
			BeanUtils.copyProperties(dbGoodsNew, bo);
			Integer countStock = daikenGoodsNewSkuDao.countStock(dbGoodsNew.getId());
			bo.setId(dbGoodsNew.getId());
			bo.setPriceSales(dbGoodsNew.getMinPriceSales());
			bo.setPriceStore(dbGoodsNew.getMinPriceStore());
			bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			bo.setSalesTypeStr(cMallService.parseSalesTypeStr(dbGoodsNew.getSalesType()));
			bo.setUserShopId(0);
			if (dbGoodsNew.getGoodsId() != null && dbGoodsNew.getGoodsId() != 0) {
				bo.setId(dbGoodsNew.getGoodsId());
			}
			bo.setCountStock(countStock);
			bo.setTimeType(DaikenConstant.underway);
			if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
				bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
			}
			result.add(bo);
		}
		PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
		return new KemeanPageApiBO<List<NewGoodsActivityListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}
}
