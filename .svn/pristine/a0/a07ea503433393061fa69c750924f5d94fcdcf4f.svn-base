package com.kemean.service.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsHotTreasure;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenGoodsOld;
import com.kemean.bean.DaikenGoodsPurchasing;
import com.kemean.bean.DaikenGoodsRecommend;
import com.kemean.bean.DaikenGoodsRecommendUser;
import com.kemean.bean.DaikenHistorySearch;
import com.kemean.bean.DaikenOrderAppraisal;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserShop;
import com.kemean.bean.KemeanCollect;
import com.kemean.bean.KemeanLunbo;
import com.kemean.constant.DaikenCollectTypeEnum;
import com.kemean.constant.DaikenConstant;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenHotTreasure;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRecommend;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenShareTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsHotTreasureDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenGoodsOldDao;
import com.kemean.dao.DaikenGoodsPurchasingDao;
import com.kemean.dao.DaikenGoodsRecommendDao;
import com.kemean.dao.DaikenGoodsRecommendUserDao;
import com.kemean.dao.DaikenHistorySearchDao;
import com.kemean.dao.DaikenOrderAppraisalDao;
import com.kemean.dao.DaikenRedShareDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.dao.KemeanCollectDao;
import com.kemean.dao.KemeanLunboDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.c.mall.GoodsAppraisalListBO;
import com.kemean.vo.bo.c.mall.GoodsAppraisalTopListBO;
import com.kemean.vo.bo.c.mall.NewGoodsInfoBO;
import com.kemean.vo.bo.c.mall.NewGoodsListBO;
import com.kemean.vo.bo.c.mall.OldGoodsInfoBO;
import com.kemean.vo.bo.c.mall.OldGoodsListBO;
import com.kemean.vo.bo.c.mall.RecordTypeFBO;
import com.kemean.vo.bo.c.mall.ShopInfoBO;
import com.kemean.vo.bo.c.mall.SkuNoBO;
import com.kemean.vo.mysql.UserBehavior;
import com.kemean.vo.po.KemeanPageApiPO;
import com.kemean.vo.po.c.mall.GoodsAppraisalListPO;
import com.kemean.vo.po.c.mall.GoodsAppraisalTopListPO;
import com.kemean.vo.po.c.mall.GoodsInfoPO;
import com.kemean.vo.po.c.mall.NewGoodsInfoPO;
import com.kemean.vo.po.c.mall.NewGoodsListPO;
import com.kemean.vo.po.c.mall.OldGoodsListPO;
import com.kemean.vo.po.c.mall.ShopInfoPO;

/**
 * 【客户端】 商城业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class CMallService {

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenOrderAppraisalDao daikenOrderAppraisalDao;

	@Autowired
	private KemeanCollectDao kemeanCollectDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenGoodsOldDao daikenGoodsOldDao;

	@Autowired
	private DaikenGoodsPurchasingDao daikenGoodsPurchasingDao;

	@Autowired
	private DaikenUserShopDao daikenUserShopDao;

	@Autowired
	private DaikenRedShareDao daikenRedShareDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsRecommendUserDao daikenGoodsRecommendUserDao;

	@Autowired
	private DaikenGoodsRecommendDao daikenGoodsRecommendDao;

	@Autowired
	private DaikenHistorySearchDao daikenHistorySearchDao;

	@Autowired
	private KemeanLunboDao kemeanLunboDao;

	@Autowired
	private DaikenGoodsHotTreasureDao daikenGoodsHotTreasureDao;

	@Autowired
	private UserService userService;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private CommonService commonService;

	/**
	 * 一手商品列表（分类商品、热销商品、店铺商品、帮买商品）
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	public KemeanPageApiBO<List<NewGoodsListBO>> newGoodsList(NewGoodsListPO newGoodsListPO, DaikenUser daikenUser) {
		if (daikenUser.getIsFirstLogin()) {
			// 第一次登陆,修改登陆状态
			daikenUser.setIsFirstLogin(false);
			daikenUserDao.updateByPrimaryKeySelective(daikenUser);
			// 清缓存
			kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
		}
		String keyWord = "";
		if (newGoodsListPO.getKeyWord() != null) {
			keyWord = newGoodsListPO.getKeyWord();
			List<DaikenHistorySearch> dbHistorySearch = daikenHistorySearchDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userId", "content" },
					new Object[] { false, daikenUser.getId(), keyWord });
			if (CollectionUtils.isEmpty(dbHistorySearch)) {
				DaikenHistorySearch newSearch = new DaikenHistorySearch();
				newSearch.setUserId(daikenUser.getId());
				newSearch.setContent(keyWord);
				newSearch.setCreateTime(new Date());
				newSearch.setUpdateTime(new Date());
				daikenHistorySearchDao.saveSelective(newSearch);
			}
		}

		String descStr = "";
		if (newGoodsListPO.getIsHot() != null) {
			if (newGoodsListPO.getIsHot()) {
				descStr += " sales_num, ";
			}
		}

		if (newGoodsListPO.getIsPricePurchasing() != null) {
			if (newGoodsListPO.getIsPricePurchasing()) {
				descStr += " price_purchasing, ";
			}
		}

		if (newGoodsListPO.getRedPacket() != null) {
			if (newGoodsListPO.getRedPacket()) {
				descStr += " red_after, ";
			}
		}
		if (newGoodsListPO.getDiscount() != null) {
			if (newGoodsListPO.getDiscount()) {
				descStr += " min_discount, ";
			}
		}
		if (StringUtils.isNotBlank(descStr)) {
			descStr = descStr.substring(0, descStr.length() - 2);
		}
		// 设置一个最大值
		Double maxPriceSales = 1000000000.0;
		if (newGoodsListPO.getMaxPriceSales() != null) {
			maxPriceSales = newGoodsListPO.getMaxPriceSales();
		}
		// 设置一个最小值
		Double minPriceSales = 0.0;
		if (newGoodsListPO.getMinPriceSales() != null) {
			minPriceSales = newGoodsListPO.getMinPriceSales();
		}
		// 是否帮代卖
		Boolean purchasing = false;
		if (newGoodsListPO.getPurchasing() != null) {
			purchasing = newGoodsListPO.getPurchasing();
		}
		List<Integer> categoryIds = new ArrayList<Integer>();
		if (newGoodsListPO.getCategoryId() == null) {
			// 加载全部的商品分类
			List<DaikenGoodsBaseCategory> dbGoodsBaseCategorys = daikenGoodsBaseCategoryDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "level" }, new Object[] { false, 3 });
			if (CollectionUtils.isNotEmpty(dbGoodsBaseCategorys)) {
				for (DaikenGoodsBaseCategory daikenGoodsBaseCategory : dbGoodsBaseCategorys) {
					categoryIds.add(daikenGoodsBaseCategory.getId());
				}
			}
		} else {
			categoryIds.add(newGoodsListPO.getCategoryId());
		}
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.newGoodsList(descStr, minPriceSales, maxPriceSales,
				categoryIds, purchasing, keyWord, newGoodsListPO.getPageNo(), newGoodsListPO.getPageSize());
		List<NewGoodsListBO> result = new ArrayList<NewGoodsListBO>(dbGoodsNews.size());
		if (CollectionUtils.isEmpty(dbGoodsNews)) {
			return new KemeanPageApiBO<List<NewGoodsListBO>>(0l, 1, result);
		}
		for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
			NewGoodsListBO bo = new NewGoodsListBO();
			BeanUtils.copyProperties(dbGoodsNew, bo);
			bo.setPriceSales(dbGoodsNew.getMinPriceSales());
			bo.setPriceStore(dbGoodsNew.getMinPriceStore());
			bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			bo.setSalesTypeStr(parseSalesTypeStr(dbGoodsNew.getSalesType()));
			if (dbGoodsNew.getUserShopId() == null || dbGoodsNew.getUserShopId() == 0) {
				bo.setUserShopId(0);
			}
			bo.setTimeType(DaikenConstant.underway);
			if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
				bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
			}
			result.add(bo);
		}
		PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
		return new KemeanPageApiBO<List<NewGoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 二手商品列表
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	public KemeanPageApiBO<List<OldGoodsListBO>> oldGoodsList(OldGoodsListPO oldGoodsListPO, DaikenUser daikenUser) {
		String keyWord = "";
		if (oldGoodsListPO.getKeyWord() != null) {
			keyWord = oldGoodsListPO.getKeyWord();
			List<DaikenHistorySearch> dbHistorySearch = daikenHistorySearchDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userId", "content" },
					new Object[] { false, daikenUser.getId(), keyWord });
			if (CollectionUtils.isEmpty(dbHistorySearch)) {
				DaikenHistorySearch newSearch = new DaikenHistorySearch();
				newSearch.setUserId(daikenUser.getId());
				newSearch.setContent(keyWord);
				newSearch.setCreateTime(new Date());
				newSearch.setUpdateTime(new Date());
				daikenHistorySearchDao.saveSelective(newSearch);
			}
		}
		List<Integer> categoryIds = new ArrayList<Integer>();
		if (oldGoodsListPO.getCategoryId() == null) {
			// 加载全部的商品分类
			List<DaikenGoodsBaseCategory> dbGoodsBaseCategorys = daikenGoodsBaseCategoryDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "level" }, new Object[] { false, 3 });
			if (CollectionUtils.isNotEmpty(dbGoodsBaseCategorys)) {
				for (DaikenGoodsBaseCategory daikenGoodsBaseCategory : dbGoodsBaseCategorys) {
					categoryIds.add(daikenGoodsBaseCategory.getId());
				}
			}
		} else {
			categoryIds.add(oldGoodsListPO.getCategoryId());
		}
		String descStr = "";
		if (oldGoodsListPO.getIsQuality() != null) {
			if (oldGoodsListPO.getIsQuality()) {
				descStr += " quality, ";
			}
		}
		if (oldGoodsListPO.getRedPacket() != null) {
			if (oldGoodsListPO.getRedPacket()) {
				descStr += " red_after, ";
			}
		}

		descStr += " create_time, ";

		if (StringUtils.isNotBlank(descStr)) {
			descStr = descStr.substring(0, descStr.length() - 2);
		}
		// 设置一个最大值
		Double maxPriceSales = 1000000000.0;
		if (oldGoodsListPO.getMaxPriceSales() != null) {
			maxPriceSales = oldGoodsListPO.getMaxPriceSales();
		}
		// 设置一个最小值
		Double minPriceSales = 0.0;
		if (oldGoodsListPO.getMinPriceSales() != null) {
			minPriceSales = oldGoodsListPO.getMinPriceSales();
		}
		// 是否帮卖
		Boolean isPurchasing = false;
		if (oldGoodsListPO.getIsPurchasing() != null) {
			isPurchasing = oldGoodsListPO.getIsPurchasing();
		}
		PageHelper.startPage(oldGoodsListPO.getPageNo(), oldGoodsListPO.getPageSize());
		List<DaikenGoodsOld> dbGoodsOlds = daikenGoodsOldDao.oldGoodsList(categoryIds, descStr, maxPriceSales,
				minPriceSales, isPurchasing, keyWord);
		List<OldGoodsListBO> result = new ArrayList<OldGoodsListBO>(dbGoodsOlds.size());
		if (CollectionUtils.isEmpty(dbGoodsOlds)) {
			return new KemeanPageApiBO<List<OldGoodsListBO>>(0l, 1, result);
		}
		for (DaikenGoodsOld daikenGoodsOld : dbGoodsOlds) {
			OldGoodsListBO bo = new OldGoodsListBO();
			BeanUtils.copyProperties(daikenGoodsOld, bo);
			List<String> headImg = JSONArray.parseArray(daikenGoodsOld.getImgsHead(), String.class);
			if (headImg.size() >= 3) {
				headImg = headImg.subList(0, 3);
			}
			bo.setHeadImg(headImg);
			bo.setQualityStr(
					DaikenUtil.foematInteger(String.valueOf(daikenGoodsOld.getQuality()).substring(0, 1)) + "成新");
			bo.setTwoDayMinuteStr(DaikenUtil.getTwoDayMinuteStr(daikenGoodsOld.getCreateTime(), new Date()));
			if (daikenGoodsOld.getUserShopId() == null || daikenGoodsOld.getUserShopId() == 0) {
				bo.setUserShopId(0);
			}
			result.add(bo);
		}
		PageInfo<DaikenGoodsOld> pageInfo = new PageInfo<DaikenGoodsOld>(dbGoodsOlds);
		return new KemeanPageApiBO<List<OldGoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 获取一手商品信息 skuNo组成（商品id + 规格id + 规格内容--父（MD5加密） + 规格内容--子（MD5加密））
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	@Transactional
	public NewGoodsInfoBO newGoodsInfo(NewGoodsInfoPO newGoodsInfoPO, DaikenUser daikenUser) {
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(newGoodsInfoPO.getObjId());
		// 从首页弹窗进
		if (newGoodsInfoPO.getRecommendLocal() != null
				&& newGoodsInfoPO.getRecommendLocal().equals(DaikenConstant.home_page_recommend)) {
			List<DaikenGoodsRecommend> dbGoodsRecommends = daikenGoodsRecommendDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "typeId", "recommendType", "recommendWay", "isOver" },
					new Object[] { false, newGoodsInfoPO.getObjId(), DaikenRecommend.RecommendType.CLICK.getType(),
							DaikenRecommend.RecommendWay.GOODS.getWay(), false });
			if (CollectionUtils.isNotEmpty(dbGoodsRecommends)) {
				DaikenGoodsRecommend dbGoodsRecommend = dbGoodsRecommends.get(0);
				dbGoodsRecommend.setClickNum(dbGoodsRecommend.getClickNum() + 1);
				dbGoodsRecommend.setUpdateTime(new Date());
				daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
				// 首页推荐到期（点击）
				if (dbGoodsRecommend.getClickNum() >= dbGoodsRecommend.getBuyClickNum()) {
					goodsRecommendIsOver(dbGoodsRecommend);
				}
			}
		}

		// 从轮播图进
		if (newGoodsInfoPO.getRecommendLocal() != null
				&& newGoodsInfoPO.getRecommendLocal().equals(DaikenConstant.lunbo_recommend)) {
			if (newGoodsInfoPO.getAdOrderSort().equals(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN.getType())
					|| newGoodsInfoPO.getAdOrderSort()
							.equals(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN.getType())
					|| newGoodsInfoPO.getAdOrderSort()
							.equals(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN.getType())) {
				List<KemeanLunbo> dbLunbos = kemeanLunboDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "typeId", "recommendType", "recommendWay",
								"isOver" },
						new Object[] { false, newGoodsInfoPO.getObjId(), DaikenRecommend.RecommendType.CLICK.getType(),
								DaikenRecommend.RecommendWay.GOODS.getWay(), false });
				if (CollectionUtils.isNotEmpty(dbLunbos)) {
					KemeanLunbo dbLunbo = dbLunbos.get(0);
					dbLunbo.setClickNum(dbLunbo.getClickNum() + 1);
					dbLunbo.setUpdateTime(new Date());
					kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
					// 如果到期了，设置结束
					if (dbLunbo.getClickNum() >= dbLunbo.getBuyClickNum()) {
						// 轮播图到期（点击）
						lunboIsOver(dbLunbo);
					}
				}
			}
		}

		// 从推荐宝贝进（只算计费的）
		if (newGoodsInfoPO.getRecommendLocal() != null
				&& newGoodsInfoPO.getRecommendLocal().equals(DaikenConstant.hot_treasure)) {
			DaikenGoodsHotTreasure dbHotTreasure = daikenGoodsHotTreasureDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "status", "currentState", "goodsId", "screenPosition" },
					new Object[] { false, true, DaikenHotTreasure.HotTreasureState.UNDERWAY.getState(),
							newGoodsInfoPO.getObjId(), newGoodsInfoPO.getAdOrderSort() });
			if (dbHotTreasure != null) {
				Integer clickNum = dbHotTreasure.getClickNum() + 1;
				dbHotTreasure.setClickNum(clickNum);
				Boolean flag = false;
				if (clickNum >= dbHotTreasure.getBuyClickNum()) {
					flag = true;
					dbHotTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.FINISHED.getState());
					DaikenGoodsNew dbHotGoodsNew = daikenGoodsNewDao.selectById(newGoodsInfoPO.getObjId());
					dbHotGoodsNew.setRecommend(false);
					dbHotGoodsNew.setAdOrderSort(DaikenHotTreasure.HotTreasureScreenPosition.ORDINARY.getType());
					dbHotGoodsNew.setUpdateTime(new Date());
					daikenGoodsNewDao.updateByPrimaryKeySelective(dbHotGoodsNew);
					userService.daikenSendCode(dbHotTreasure.getUserPhone(),
							"亲爱的用户您好，欢迎使用代研首页推荐宝贝活动，由于您此次购买的 "
									+ DaikenMapData.hotTreasure.get(dbHotTreasure.getScreenPosition())
									+ "点击商品推荐活动，已经结束，将暂停服务，如需续费，请登陆商家平台进行续费。");
				}
				dbHotTreasure.setUpdateTime(new Date());
				daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbHotTreasure);

				if (flag) {
					// 进行推荐
					commonService.findHotTreasure(newGoodsInfoPO.getAdOrderSort());
				}
			}
		}

		if (dbGoodsNew.getGoodsId() != null && dbGoodsNew.getGoodsId() != 0) {
			dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsNew.getGoodsId());
		}

		String cacheKeyThirdH = String.format(DaikenRedisKeyEnum.BIG_DATA_H.getKey(), dbGoodsNew.getCategoryId());
		String cacheKeyThirdZ = String.format(DaikenRedisKeyEnum.BIG_DATA_S.getKey(), dbGoodsNew.getCategoryId());
		// 用户浏览商品记录
		UserBehavior newUserBehavior = new UserBehavior();
		BeanUtils.copyProperties(dbGoodsNew, newUserBehavior);
		newUserBehavior.setCreateTime(new Date());

		// 记录商品的浏览用户sort
		kemeanRedisService.zadd(String.format(DaikenRedisKeyEnum.BIG_DATA_GOODS_BROWSE_S.getKey(), dbGoodsNew.getId()),
				System.currentTimeMillis() / 100000.0, daikenUser.getId() + "", -1);

		// 记录商品的浏览用户hash
		kemeanRedisService.hset(String.format(DaikenRedisKeyEnum.BIG_DATA_GOODS_BROWSE_H.getKey(), dbGoodsNew.getId()),
				daikenUser.getId() + "", JSONObject.toJSONString(daikenUser));

		// 记录用户行为记录sort
		kemeanRedisService.zadd(String.format(DaikenRedisKeyEnum.BIG_DATA_USER_BEHAVIOR_S.getKey(), daikenUser.getId()),
				System.currentTimeMillis() / 100000.0, dbGoodsNew.getId() + "", -1);

		// 记录用户行为记录hash
		kemeanRedisService.hset(String.format(DaikenRedisKeyEnum.BIG_DATA_USER_BEHAVIOR_H.getKey(), daikenUser.getId()),
				dbGoodsNew.getId() + "", JSONObject.toJSONString(newUserBehavior));

		// 记录三级
		// 有序集合控制数据的有效性 hash控制同一用户只有一个记录
		Long thirdResult = kemeanRedisService.hset(cacheKeyThirdH, daikenUser.getId() + "",
				JSONObject.toJSONString(daikenUser));
		if (thirdResult.equals(1L)) {
			// 记录二级
			DaikenGoodsBaseCategory cacheCategory = commonService.getGoodsCategory(dbGoodsNew.getCategoryId());
			kemeanRedisService.zadd(cacheKeyThirdZ, System.currentTimeMillis() / 100000.0, daikenUser.getId() + "", -1);
			if (!cacheCategory.getPid().equals(0)) {
				String cacheKeySecondH = String.format(DaikenRedisKeyEnum.BIG_DATA_H.getKey(), cacheCategory.getPid());
				String cacheKeySecondS = String.format(DaikenRedisKeyEnum.BIG_DATA_S.getKey(), cacheCategory.getPid());
				Long secondResult = kemeanRedisService.hset(cacheKeySecondH, daikenUser.getId() + "",
						JSONObject.toJSONString(daikenUser));
				if (secondResult.equals(1L)) {
					kemeanRedisService.zadd(cacheKeySecondS, System.currentTimeMillis() / 100000.0,
							daikenUser.getId() + "", -1);
				}
			}
		}
		DaikenShop dbShop = daikenShopDao.selectById(dbGoodsNew.getShopId());

		NewGoodsInfoBO bo = new NewGoodsInfoBO();
		BeanUtils.copyProperties(dbGoodsNew, bo);
		if (dbShop != null) {
			BeanUtils.copyProperties(dbShop, bo);
			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "shopId", "goodsStatus" },
					new Object[] { false, dbShop.getId(), true });
			List<DaikenOrderAppraisal> dbOrderAppraisals = daikenOrderAppraisalDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "idShop", "idGoods", "isNewGoods" },
					new Object[] { false, dbShop.getId(), dbGoodsNew.getId(), true });
			bo.setGoodsNum(dbGoodsNews.size());
			bo.setAppraisalNum(dbOrderAppraisals.size());
		}
		bo.setId(newGoodsInfoPO.getObjId());
		bo.setImgsHead(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class));
		bo.setImgsDesc(JSONArray.parseArray(dbGoodsNew.getImgsDesc(), String.class));
		bo.setOld(false);
		bo.setIsCollect(false);
		KemeanCollect dbCollect = kemeanCollectDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "type", "typeId" },
				new Object[] { false, daikenUser.getId(), DaikenCollectTypeEnum.NEW_GOODS_COLLECT.getType(),
						newGoodsInfoPO.getObjId() });
		if (dbCollect != null) {
			bo.setIsCollect(true);
		}

		List<DaikenGoodsNewSku> dbGoodsNewSkus = daikenGoodsNewSkuDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" }, new Object[] { false, dbGoodsNew.getId() });

		if (dbGoodsNew.getGoodsId() != null && dbGoodsNew.getGoodsId() != 0) {
			dbGoodsNewSkus = daikenGoodsNewSkuDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
					new Object[] { false, dbGoodsNew.getGoodsId() });
		}

		List<SkuNoBO> skuNoBOs = new ArrayList<SkuNoBO>(dbGoodsNewSkus.size());
		// 总库存
		Integer sumSockt = 0;
		if (CollectionUtils.isNotEmpty(dbGoodsNewSkus)) {
			for (DaikenGoodsNewSku daikenGoodsNewSku : dbGoodsNewSkus) {
				SkuNoBO skuBO = new SkuNoBO();
				BeanUtils.copyProperties(daikenGoodsNewSku, skuBO);
				Integer stock = daikenGoodsNewSku.getStock() - daikenGoodsNewSku.getSalesNum();
				sumSockt += daikenGoodsNewSku.getStock();
				skuBO.setStock(stock);
				skuBO.setRecordType(DaikenUtil.parseJsonObject(daikenGoodsNewSku.getRecordType()));
				if (stock >= 1) {
					skuNoBOs.add(skuBO);
				}
			}
		}
		bo.setGoodsResidue(sumSockt - dbGoodsNew.getSalesNum());
		bo.setSkuNoBO(skuNoBOs);
		bo.setRecordTypeFBO(JSONArray.parseArray(dbGoodsNew.getRecordType(), RecordTypeFBO.class));
		bo.setNumCollect(
				kemeanCollectDao.countByProperties(new String[] { KemeanConstant.DATA_DELETED, "type", "typeId" },
						new Object[] { false, DaikenCollectTypeEnum.SHOP_COLLECT.getType(), dbGoodsNew.getShopId() }));
		bo.setIsPurchasing(false);
		DaikenUserShop dbUserShop = daikenUserShopDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, daikenUser.getId() });
		if (dbUserShop != null) {
			DaikenGoodsPurchasing dbGoodsPurchasing = daikenGoodsPurchasingDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userShopId", "old", "goodsId" },
					new Object[] { false, dbUserShop.getId(), false, dbGoodsNew.getId() });
			if (dbGoodsPurchasing != null) {
				bo.setIsPurchasing(true);
			}
		}
		// 撸羊毛
		bo.setWoolLabel(daikenUser.getWoolLabel());
		// 剩余已分享次数
		Integer numShare = daikenRedShareDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "type", "shopId", "typeId", "relieveWool" },
				new Object[] { false, DaikenShareTypeEnum.NEW_GOODS.getType(), dbGoodsNew.getShopId(),
						dbGoodsNew.getId(), true });
		Integer userShopId = 0;
		if (newGoodsInfoPO.getUserShopId() != null && newGoodsInfoPO.getUserShopId() != 0) {
			userShopId = newGoodsInfoPO.getUserShopId();
			DaikenUserShop dbHelpUserShop = daikenUserShopDao.selectById(newGoodsInfoPO.getUserShopId());
			bo.setShopName(dbHelpUserShop.getShopName());
			bo.setShopLogo(dbHelpUserShop.getImg());
			bo.setSalesVolume(dbHelpUserShop.getSalesVolume());
			// 商品总数
			bo.setGoodsNum(daikenGoodsNewDao.countByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userShopId", "goodsStatus" },
					new Object[] { false, userShopId, true }));
			// 收藏总数
			bo.setNumCollect(kemeanCollectDao.countByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "type", "typeId" },
					new Object[] { false, DaikenCollectTypeEnum.HELP_SELL_SHOP_COLLECT.getType(), userShopId }));

			// 评论数
			List<DaikenOrderAppraisal> dbOrderAppraisals = daikenOrderAppraisalDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "idShop", "idGoods", "isNewGoods" },
					new Object[] { false, dbShop.getId(), newGoodsInfoPO.getObjId(), true });
			bo.setAppraisalNum(dbOrderAppraisals.size());
		}

		if (dbGoodsNew.getUserShopId() != null && dbGoodsNew.getUserShopId() != 0) {
			userShopId = dbGoodsNew.getUserShopId();
			DaikenUserShop dbHelpUserShop = daikenUserShopDao.selectById(dbGoodsNew.getUserShopId());
			bo.setShopName(dbHelpUserShop.getShopName());
			bo.setShopLogo(dbHelpUserShop.getImg());
			bo.setSalesVolume(dbHelpUserShop.getSalesVolume());
			// 商品总数
			bo.setGoodsNum(daikenGoodsNewDao.countByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userShopId", "goodsStatus" },
					new Object[] { false, userShopId, true }));
			// 收藏总数
			bo.setNumCollect(kemeanCollectDao.countByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "type", "typeId" },
					new Object[] { false, DaikenCollectTypeEnum.HELP_SELL_SHOP_COLLECT.getType(), userShopId }));
			// 评论数
			List<DaikenOrderAppraisal> dbOrderAppraisals = daikenOrderAppraisalDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "idShop", "idGoods", "isNewGoods" },
					new Object[] { false, dbShop.getId(), newGoodsInfoPO.getObjId(), true });
			bo.setAppraisalNum(dbOrderAppraisals.size());
		}
		bo.setUserShopId(userShopId);
		// TODO 需要获取平台准许分享次数 目前设置为5
		bo.setIsShare(true);
		if (5 - numShare <= dbGoodsNew.getNumShare()) {
			bo.setIsShare(false);
		}

		if (daikenUser.getWoolLabel()) {
			bo.setIsShare(false);
		}
		bo.setTimeType(DaikenConstant.underway);
		if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
			bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
		}
		return bo;
	}

	/**
	 * 获取二手商品信息
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	public OldGoodsInfoBO oldGoodsInfo(Integer objId, DaikenUser daikenUser) {
		OldGoodsInfoBO bo = new OldGoodsInfoBO();
		DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(objId);
		BeanUtils.copyProperties(dbGoodsOld, bo);
		bo.setId(objId);
		bo.setQualityStr(DaikenUtil.foematInteger(String.valueOf(dbGoodsOld.getQuality()).substring(0, 1)) + "成新");
		bo.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class));
		// 判断是否收藏
		bo.setIsCollect(false);
		KemeanCollect dbCollects = kemeanCollectDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "type", "typeId" }, new Object[] { false,
						daikenUser.getId(), DaikenCollectTypeEnum.SECOND_GOODS_COLLECT.getType(), objId });
		if (dbCollects != null) {
			bo.setIsCollect(true);
		}

		bo.setIsPurchasing(false);
		DaikenUserShop dbUserShop = daikenUserShopDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, daikenUser.getId() });
		if (dbUserShop != null) {
			DaikenGoodsPurchasing dbGoodsPurchasing = daikenGoodsPurchasingDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userShopId", "old", "goodsId" },
					new Object[] { false, dbUserShop.getId(), true, dbGoodsOld.getId() });
			if (dbGoodsPurchasing != null) {
				bo.setIsPurchasing(true);
			}
		}
		// 撸羊毛
		bo.setWoolLabel(daikenUser.getWoolLabel());
		// TODO 需要获取平台准许分享次数 目前设置为5
		bo.setIsShare(true);
		if (5 - dbGoodsOld.getNumShare() <= 0) {
			bo.setIsShare(false);
		}

		if (daikenUser.getWoolLabel()) {
			bo.setIsShare(false);
		}

		return bo;
	}

	/**
	 * 获取商店信息
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	public ShopInfoBO shopInfo(ShopInfoPO shopInfoPO, DaikenUser daikenUser) {
		// 从首页弹窗进
		if (shopInfoPO.getRecommendLocal() != null && shopInfoPO.getRecommendLocal().equals(1101)) {
			List<DaikenGoodsRecommend> dbGoodsRecommends = daikenGoodsRecommendDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "typeId", "recommendWay", "recommendType", "isOver" },
					new Object[] { false, shopInfoPO.getShopId(), DaikenRecommend.RecommendWay.SHOP.getWay(),
							DaikenRecommend.RecommendType.CLICK.getType(), false });
			if (CollectionUtils.isNotEmpty(dbGoodsRecommends)) {
				DaikenGoodsRecommend dbGoodsRecommend = dbGoodsRecommends.get(0);
				dbGoodsRecommend.setClickNum(dbGoodsRecommend.getClickNum() + 1);
				dbGoodsRecommend.setUpdateTime(new Date());
				daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
				if (dbGoodsRecommend.getClickNum() >= dbGoodsRecommend.getBuyClickNum()) {
					goodsRecommendIsOver(dbGoodsRecommend);
				}
			}
		}

		// 从轮播图进
		if (shopInfoPO.getRecommendLocal() != null && shopInfoPO.getRecommendLocal().equals(1201)) {
			List<KemeanLunbo> dbLunbos = kemeanLunboDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "recommendWay", "recommendType", "typeId", "isOver" },
					new Object[] { false, DaikenRecommend.RecommendWay.SHOP.getWay(),
							DaikenRecommend.RecommendType.CLICK.getType(), shopInfoPO.getShopId(), false });
			if (CollectionUtils.isNotEmpty(dbLunbos)) {
				KemeanLunbo dbLunbo = dbLunbos.get(0);
				dbLunbo.setClickNum(dbLunbo.getClickNum() + 1);
				dbLunbo.setUpdateTime(new Date());
				kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
				// 如果到期了，设置结束
				if (dbLunbo.getClickNum() >= dbLunbo.getBuyClickNum()) {
					dbLunbo.setIsOver(true);
					dbLunbo.setUpdateTime(new Date());
					kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
					// 轮播图结束
					lunboIsOver(dbLunbo);
				}
			}
		}

		ShopInfoBO bo = new ShopInfoBO();
		bo.setBusinessLicenseName("");
		bo.setBusinessLicenseLocation("");
		bo.setShopUserUid(0);
		// 帮卖商铺
		if (!shopInfoPO.getUserShopId().equals(0)) {
			DaikenUserShop dbUserShop = daikenUserShopDao.selectById(shopInfoPO.getUserShopId());
			BeanUtils.copyProperties(dbUserShop, bo);
			bo.setShopLogo(dbUserShop.getImg());
			bo.setDegreeOfPraise(dbUserShop.getDegreeOfPraise() * 100);
			bo.setIsCollect(false);
			KemeanCollect dbCollect = kemeanCollectDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userId", "type", "typeId" },
					new Object[] { false, daikenUser.getId(), DaikenCollectTypeEnum.HELP_SELL_SHOP_COLLECT.getType(),
							shopInfoPO.getUserShopId() });
			if (dbCollect != null) {
				bo.setIsCollect(true);
			}
			bo.setShopPhone("");
			bo.setUserShopId(dbUserShop.getId());
			bo.setShopId(0);
			return bo;
		}

		// 自卖商铺
		if (shopInfoPO.getUserShopId().equals(0)) {
			DaikenShop dbShop = daikenShopDao.selectById(shopInfoPO.getShopId());
			BeanUtils.copyProperties(dbShop, bo);
			bo.setIsCollect(false);
			KemeanCollect dbCollect = kemeanCollectDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userId", "type", "typeId" }, new Object[] { false,
							daikenUser.getId(), DaikenCollectTypeEnum.SHOP_COLLECT.getType(), shopInfoPO.getShopId() });
			if (dbCollect != null) {
				bo.setIsCollect(true);
			}
			bo.setShopId(dbShop.getId());
			bo.setUserShopId(0);
			bo.setDegreeOfPraise(dbShop.getDegreeOfPraise() * 100);
			return bo;
		}
		// TODO 未完成分享图片
		bo.setShopShareImg(
				"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2516563086,3831878519&fm=111&gp=0.jpg");
		return bo;
	}

	/**
	 * 首页推荐到期（点击）
	 * 
	 * @author huwei
	 * @date 2018年9月13日
	 */
	private void goodsRecommendIsOver(DaikenGoodsRecommend dbGoodsRecommend) {
		dbGoodsRecommend.setIsOver(true);
		dbGoodsRecommend.setUpdateTime(new Date());
		daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
		String info = "亲爱的用户您好，欢迎使用代研首页弹窗推荐活动，由于您此次购买的"
				+ DaikenMapData.recommendWay.get(dbGoodsRecommend.getRecommendWay())
				+ "点击首页弹窗推荐活动时间已到，将暂停服务，如要续费，请联系平台管理员。";
		userService.daikenSendCode(dbGoodsRecommend.getUserPhone(), info);

	}

	/**
	 * 轮播图到期（点击）
	 * 
	 * @author huwei
	 * @date 2018年9月13日
	 */
	private void lunboIsOver(KemeanLunbo dbLunbo) {
		dbLunbo.setIsOver(true);
		dbLunbo.setUpdateTime(new Date());
		kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
		String info = "亲爱的用户您好，欢迎使用代研首页轮播图推荐活动，由于您此次购买的" + DaikenMapData.recommendWay.get(dbLunbo.getRecommendWay())
				+ "点击首页轮播图推荐活动，时间已到，将暂停服务，如需续费，请联系平台管理员";
		userService.daikenSendCode(dbLunbo.getUserPhone(), info);
	}

	/**
	 * 获取商店全部商品信息
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	public KemeanPageApiBO<List<NewGoodsListBO>> goodsInfo(GoodsInfoPO goodsInfoPO) {
		List<Integer> categoryIds = new ArrayList<Integer>();
		if (goodsInfoPO.getCategoryId() == null) {
			// 加载全部的商品分类
			List<DaikenGoodsBaseCategory> dbGoodsBaseCategorys = daikenGoodsBaseCategoryDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "level" }, new Object[] { false, 3 });
			if (CollectionUtils.isNotEmpty(dbGoodsBaseCategorys)) {
				for (DaikenGoodsBaseCategory daikenGoodsBaseCategory : dbGoodsBaseCategorys) {
					categoryIds.add(daikenGoodsBaseCategory.getId());
				}
			}
		} else {
			categoryIds.add(goodsInfoPO.getCategoryId());
		}

		String title = "";
		if (goodsInfoPO.getTitle() != null) {
			title = goodsInfoPO.getTitle();
		}
		List<DaikenGoodsNew> dbGoodsNews = null;
		// 帮代卖商铺
		if (!goodsInfoPO.getUserShopId().equals(0)) {
			dbGoodsNews = daikenGoodsNewDao.helpSellGoodsInfo(categoryIds, title, goodsInfoPO.getUserShopId(),
					goodsInfoPO.getPageNo(), goodsInfoPO.getPageSize());
		}

		// 自卖商铺
		if (goodsInfoPO.getUserShopId().equals(0)) {
			dbGoodsNews = daikenGoodsNewDao.goodsInfo(categoryIds, title, goodsInfoPO.getShopId(),
					goodsInfoPO.getPageNo(), goodsInfoPO.getPageSize());
		}
		List<NewGoodsListBO> result = new ArrayList<NewGoodsListBO>(dbGoodsNews.size());
		if (CollectionUtils.isEmpty(dbGoodsNews)) {
			return new KemeanPageApiBO<List<NewGoodsListBO>>(0l, 1, result);
		}
		Date now = new Date();
		for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
			NewGoodsListBO bo = new NewGoodsListBO();
			BeanUtils.copyProperties(dbGoodsNew, bo);
			bo.setPriceSales(dbGoodsNew.getMinPriceSales());
			bo.setPriceStore(dbGoodsNew.getMinPriceStore());
			bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			bo.setSalesTypeStr(parseSalesTypeStr(dbGoodsNew.getSalesType()));
			if (!goodsInfoPO.getUserShopId().equals(0)) {
				bo.setUserShopId(goodsInfoPO.getShopId());
			}
			if (goodsInfoPO.getUserShopId().equals(0)) {
				bo.setUserShopId(0);
			}
			bo.setDiscountTimeBegin(now);
			bo.setDiscountTimeEnd(now);
			bo.setTimeType(DaikenConstant.underway);
			if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
				bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
			}
			result.add(bo);
		}
		PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
		return new KemeanPageApiBO<List<NewGoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 获取商品评论 type:1101商铺 1102商品
	 * 
	 * @author huwei
	 * @date 2018年6月8日
	 */
	public KemeanPageApiBO<List<GoodsAppraisalListBO>> goodsAppraisalList(GoodsAppraisalListPO goodsAppraisalListPO) {
		List<Integer> appraisalTypes = new ArrayList<Integer>();
		if (goodsAppraisalListPO.getAppraisalType() != null) {
			appraisalTypes.add(goodsAppraisalListPO.getAppraisalType());
		} else {
			appraisalTypes.add(1);
			appraisalTypes.add(2);
			appraisalTypes.add(3);
		}
		List<DaikenOrderAppraisal> dbOrderAppraisals = daikenOrderAppraisalDao.goodsAppraisalList(appraisalTypes,
				goodsAppraisalListPO.getObjId(), goodsAppraisalListPO.getIsNewGoods(), goodsAppraisalListPO.getPageNo(),
				goodsAppraisalListPO.getPageSize());

		List<GoodsAppraisalListBO> result = new ArrayList<GoodsAppraisalListBO>(dbOrderAppraisals.size());
		if (dbOrderAppraisals.isEmpty()) {
			return new KemeanPageApiBO<List<GoodsAppraisalListBO>>(0l, 1, result);
		}
		for (DaikenOrderAppraisal daikenOrderAppraisal : dbOrderAppraisals) {
			GoodsAppraisalListBO bo = new GoodsAppraisalListBO();
			BeanUtils.copyProperties(daikenOrderAppraisal, bo);
			if (StringUtils.isNotBlank(daikenOrderAppraisal.getContentImg())) {
				bo.setContentImg(JSONArray.parseArray(daikenOrderAppraisal.getContentImg(), String.class));
			} else {
				bo.setContentImg(Arrays.asList());
			}
			if (StringUtils.isNotBlank(daikenOrderAppraisal.getContent())
					|| StringUtils.isNotBlank(daikenOrderAppraisal.getContentImg())) {
				result.add(bo);
			}
		}
		PageInfo<DaikenOrderAppraisal> pageInfo = new PageInfo<DaikenOrderAppraisal>(dbOrderAppraisals);
		return new KemeanPageApiBO<List<GoodsAppraisalListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 推荐商品（个人中心、商品内页）
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	public KemeanPageApiBO<List<NewGoodsListBO>> recommendGoods(KemeanPageApiPO kemeanPageApiPO,
			DaikenUser loginConsumer) {
		Date now = new Date();
		List<DaikenGoodsRecommendUser> dbGoodsRecommendUsers = daikenGoodsRecommendUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, loginConsumer.getId() },
				kemeanPageApiPO.getPageNo(), kemeanPageApiPO.getPageSize());
		List<NewGoodsListBO> result = new ArrayList<NewGoodsListBO>(dbGoodsRecommendUsers.size());
		if (CollectionUtils.isEmpty(dbGoodsRecommendUsers)) {
			return new KemeanPageApiBO<List<NewGoodsListBO>>(0l, 1, result);
		}

		for (DaikenGoodsRecommendUser daikenGoodsRecommendUser : dbGoodsRecommendUsers) {
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(daikenGoodsRecommendUser.getGoodsId());
			NewGoodsListBO bo = new NewGoodsListBO();
			BeanUtils.copyProperties(dbGoodsNew, bo);
			bo.setPriceSales(dbGoodsNew.getMinPriceSales());
			bo.setPriceStore(dbGoodsNew.getMinPriceStore());
			bo.setHeadImg("");
			if (StringUtils.isNotBlank(dbGoodsNew.getImgsHead())) {
				bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			}
			bo.setSalesTypeStr(parseSalesTypeStr(dbGoodsNew.getSalesType()));
			if (dbGoodsNew.getUserShopId() == null || dbGoodsNew.getUserShopId() == 0) {
				bo.setUserShopId(0);
			}
			if (dbGoodsNew.getUserShopId() != 0) {
				bo.setId(dbGoodsNew.getGoodsId());
			}
			bo.setTimeType(DaikenConstant.underway);
			if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
				bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
			}
			result.add(bo);
		}

		// 内容不够10个，销量来凑
		if (result.size() < 10 && kemeanPageApiPO.getPageNo() == 1) {
			int addNum = 10 - result.size();
			// 凑几个
			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false }, "sales_num", false,
					kemeanPageApiPO.getPageNo(), kemeanPageApiPO.getPageSize());
			for (int i = 0; i < addNum; i++) {
				DaikenGoodsNew dbGoodsNew = dbGoodsNews.get(i);
				NewGoodsListBO bo = new NewGoodsListBO();
				BeanUtils.copyProperties(dbGoodsNew, bo);
				bo.setPriceSales(dbGoodsNew.getMinPriceSales());
				bo.setPriceStore(dbGoodsNew.getMinPriceStore());
				bo.setHeadImg("");
				if (StringUtils.isNotBlank(dbGoodsNew.getImgsHead())) {
					bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
				}
				bo.setSalesTypeStr(parseSalesTypeStr(dbGoodsNew.getSalesType()));
				if (dbGoodsNew.getUserShopId() == null || dbGoodsNew.getUserShopId() == 0) {
					bo.setUserShopId(0);
				}
				if (dbGoodsNew.getUserShopId() != 0) {
					bo.setId(dbGoodsNew.getGoodsId());
				}
				bo.setDiscountTimeBegin(now);
				bo.setDiscountTimeEnd(now);
				if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
					bo.setTimeType(commonService.setLimitedTime(dbGoodsNew));
				}
				result.add(bo);
			}
			return new KemeanPageApiBO<List<NewGoodsListBO>>(10l, 1, result);
		}
		PageInfo<DaikenGoodsRecommendUser> pageInfo = new PageInfo<DaikenGoodsRecommendUser>(dbGoodsRecommendUsers);
		return new KemeanPageApiBO<List<NewGoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 商品类型转换
	 * 
	 * @author huwei
	 * @date 2018年6月29日
	 */
	public String parseSalesTypeStr(Integer salesType) {
		String salesTypeStr = "正常售卖";
		if (salesType.equals(DaikenGoodsType.SalesType.DISCOUNT_FAMOUS_BRAND.getType())) {
			salesTypeStr = "名牌折扣";
		}
		if (salesType.equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
			salesTypeStr = "限时折扣";
		}
		if (salesType.equals(DaikenGoodsType.SalesType.NINE_EXEMPTION_POSTAGE.getType())) {
			salesTypeStr = "9.9包邮";
		}
		if (salesType.equals(DaikenGoodsType.SalesType.LOSS_MONEY.getType())) {
			salesTypeStr = "亏本走量";
		}
		return salesTypeStr;
	}

	/**
	 * 获取商品评论（赞，一般，踩）
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	public GoodsAppraisalTopListBO goodsAppraisalNum(GoodsAppraisalTopListPO goodsAppraisalTopListPO) {
		GoodsAppraisalTopListBO bo = new GoodsAppraisalTopListBO();
		// 踩
		bo.setBadNum(daikenOrderAppraisalDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "isNewGoods", "idGoods", "score" }, new Object[] { false,
						goodsAppraisalTopListPO.getIsNewGoods(), goodsAppraisalTopListPO.getObjId(), 1 }));
		// 一般
		bo.setCommonNum(daikenOrderAppraisalDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "isNewGoods", "idGoods", "score" }, new Object[] { false,
						goodsAppraisalTopListPO.getIsNewGoods(), goodsAppraisalTopListPO.getObjId(), 2 }));
		// 赞
		bo.setGoodNum(daikenOrderAppraisalDao.countByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "isNewGoods", "idGoods", "score" }, new Object[] { false,
						goodsAppraisalTopListPO.getIsNewGoods(), goodsAppraisalTopListPO.getObjId(), 3 }));
		return bo;
	}
}
