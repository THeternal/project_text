package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsBaseType;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenGoodsRecommendUser;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserShop;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.constant.KemeanUserEnum;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsBaseTypeDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenGoodsRecommendUserDao;
import com.kemean.dao.DaikenOrderAfterSaleDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.business.BMineService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanIdAndName;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.common.AdminShopExpendChartBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsCategoryBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsChartBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsMarketingBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewInfoBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewMinPriceBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewSkuBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsPrecisionPushBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsPurchasingBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsSaleTotalBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsSkuRecordBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.AdminMonitorPO;
import com.kemean.vo.po.admin.goods.AdminGoodsAuditStatusPO;
import com.kemean.vo.po.admin.goods.AdminGoodsCategoryAddPO;
import com.kemean.vo.po.admin.goods.AdminGoodsCategoryPO;
import com.kemean.vo.po.admin.goods.AdminGoodsChartPO;
import com.kemean.vo.po.admin.goods.AdminGoodsListPO;
import com.kemean.vo.po.admin.goods.AdminGoodsNewSkuUpdatePO;
import com.kemean.vo.po.admin.goods.AdminGoodsNewUpdatePO;
import com.kemean.vo.po.admin.goods.AdminGoodsPrecisionPO;
import com.kemean.vo.po.admin.goods.AdminGoodsPrecisionPushPO;
import com.kemean.vo.po.admin.goods.AdminGoodsPrecisionUserPO;
import com.kemean.vo.po.admin.goods.AdminGoodsPurchasingPO;
import com.kemean.vo.po.admin.goods.AdminGoodsSkuPO;
import com.kemean.vo.po.admin.shop.AdminShopOrderListPO;
import com.kemean.vo.po.admin.shop.AdminShopPromotionPO;
import com.kemean.vo.po.admin.user.AdminUserListPO;
import com.kemean.vo.po.b.goods.GoodsPO;

@Service
public class AdminGoodsService {

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenGoodsBaseTypeDao daikenGoodsBaseTypeDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenUserShopDao daikenUserShopDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenOrderAfterSaleDao daikenOrderAfterSaleDao;

	@Autowired
	private DaikenGoodsRecommendUserDao daikenGoodsRecommendUserDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	@Autowired
	private BMineService bMineService;

	@Autowired
	private AdminCommonService adminCommonService;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	/**
	 * 一手商品 列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	public KemeanPageAdminBO<List<AdminGoodsNewBO>> goodsNewListData(AdminGoodsListPO adminGoodsListPO,
			DaikenUser loginer) {
		Integer shopId = adminGoodsListPO.getShopId();
		if (loginer != null) {
			shopId = loginer.getShopId();
		}
		List<Integer> auditStatus = new ArrayList<>();
		if (adminGoodsListPO.getAuditStatus() != null) {
			auditStatus.add(adminGoodsListPO.getAuditStatus());
		}
		if (KemeanSettledEnum.REVIEW_ING.getStatus().equals(adminGoodsListPO.getAuditStatus())) {
			auditStatus.add(KemeanSettledEnum.UPDATE_WAIT_AUDIT.getStatus());
			auditStatus.add(KemeanSettledEnum.AUDIT_FAILUE.getStatus());
		}

		List<DaikenGoodsNew> dbGoodsNew = daikenGoodsNewDao.goodsListData(shopId, adminGoodsListPO.getTitle(),
				adminGoodsListPO.getSalesType(), adminGoodsListPO.getGoodsNo(), adminGoodsListPO.getMarketing(),
				auditStatus, adminGoodsListPO.getPage(), adminGoodsListPO.getLimit());
		List<AdminGoodsNewBO> result = new ArrayList<>(dbGoodsNew.size());
		for (DaikenGoodsNew daikenGoodsNew : dbGoodsNew) {
			AdminGoodsNewBO bo = new AdminGoodsNewBO();
			BeanUtils.copyProperties(daikenGoodsNew, bo);
			DaikenShop dbShop = daikenShopDao.selectById(daikenGoodsNew.getShopId());
			if (dbShop == null) {
				continue;
			}
			if (adminGoodsListPO.getMarketing() != null) {
				AdminGoodsMarketingBO goodsMarketing = goodsMarketingData(daikenGoodsNew.getId(), loginer);
				bo.setGoodsMarketing(goodsMarketing);
			}
			bo.setShopId(dbShop.getId());
			bo.setShopName(dbShop.getShopName());
			bo.setSalesTypeStr(DaikenMapData.salesType.get(daikenGoodsNew.getSalesType()));
			bo.setAuditStatusStr(DaikenMapData.settledStatus.get(daikenGoodsNew.getAuditStatus()));
			result.add(bo);
		}

		return new KemeanPageAdminBO<List<AdminGoodsNewBO>>(new PageInfo<>(dbGoodsNew).getTotal(), result);
	}

	/**
	 * 监听商品
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@Transactional
	public KemeanResult<String> goodsNewStatusOperate(AdminMonitorPO adminMonitorPO, KemeanAdminUser loginer) {
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(adminMonitorPO.getObjId());
		Integer adminUserId = 0;
		if (loginer != null) {
			adminUserId = loginer.getId();
		}
		if (adminMonitorPO.getTjOperate()) {
			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "recommend" }, new Object[] { false, true });
			if (CollectionUtils.isNotEmpty(dbGoodsNews)) {
				for (DaikenGoodsNew daikenGoodsNew : dbGoodsNews) {
					daikenGoodsNew.setRecommend(false);
					daikenGoodsNew.setUpdateTime(new Date());
					daikenGoodsNewDao.updateByPrimaryKeySelective(daikenGoodsNew);
				}
			}
			dbGoodsNew.setRecommend(adminMonitorPO.getStatus());
		} else {
			dbGoodsNew.setGoodsStatus(adminMonitorPO.getStatus());
			dbGoodsNew.setAdminUserId(adminUserId);
		}
		dbGoodsNew.setUpdateTime(new Date());
		daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 获取商铺名称
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	public KemeanIdAndName shopName(Integer objId) {
		DaikenShop dbShop = daikenShopDao.selectById(objId);
		return new KemeanIdAndName(dbShop.getId(), dbShop.getShopName());
	}

	/**
	 * 商品详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	public AdminGoodsNewInfoBO goodsNewInfoData(Integer objId) {
		AdminGoodsNewInfoBO bo = new AdminGoodsNewInfoBO();
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(objId);

		if (KemeanSettledEnum.UPDATE_WAIT_AUDIT.getStatus().equals(dbGoodsNew.getAuditStatus())) {
			BeanUtils.copyProperties(JSONObject.parseObject(dbGoodsNew.getEditRecord(), GoodsPO.class), dbGoodsNew);
		}
		BeanUtils.copyProperties(dbGoodsNew, bo);
		bo.setGoodsId(dbGoodsNew.getId());
		bo.setDiscountTimeBegin(
				KemeanUtilAid.formatDate(dbGoodsNew.getDiscountTimeBegin(), KemeanDateFormatEnum.NORMAL));
		bo.setDiscountTimeEnd(KemeanUtilAid.formatDate(dbGoodsNew.getDiscountTimeEnd(), KemeanDateFormatEnum.NORMAL));

		if (StringUtils.isNoneBlank(dbGoodsNew.getImgsHead())) {
			bo.setImgsHead(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class));
		}

		if (StringUtils.isNoneBlank(dbGoodsNew.getImgsDesc())) {
			bo.setImgsDesc(JSONArray.parseArray(dbGoodsNew.getImgsDesc(), String.class));
		}
		bo.setSalesTypeStr(DaikenMapData.salesType.get(dbGoodsNew.getSalesType()));
		if (KemeanSettledEnum.UPDATE_WAIT_AUDIT.getStatus().equals(dbGoodsNew.getAuditStatus())) {
			bo.setSalesTypeStr(DaikenMapData.salesType.get(KemeanSettledEnum.REVIEW_ING.getStatus()));

		}
		bo.setGoodsUid(DaikenUtil.getGoodsUid(new Date()));
		// 商品规格
		List<DaikenGoodsNewSku> dbGoodsNewSku = daikenGoodsNewSkuDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" }, new Object[] { false, dbGoodsNew.getId() });
		List<AdminGoodsNewSkuBO> newSkuList = new ArrayList<>();
		if (!dbGoodsNewSku.isEmpty()) {
			for (DaikenGoodsNewSku daikenGoodsNewSku : dbGoodsNewSku) {
				AdminGoodsNewSkuBO newSkuBO = new AdminGoodsNewSkuBO();
				BeanUtils.copyProperties(daikenGoodsNewSku, newSkuBO);
				if (StringUtils.isNoneBlank(daikenGoodsNewSku.getRecordTypeBackstageUse())) {
					List<AdminGoodsSkuRecordBO> parseSku = JSONArray
							.parseArray(daikenGoodsNewSku.getRecordTypeBackstageUse(), AdminGoodsSkuRecordBO.class);
					newSkuBO.setGoodsSkuRecord(parseSku);
				}
				newSkuList.add(newSkuBO);
			}
		}
		bo.setGoodsNewSkuBO(newSkuList);
		bo.setAuditStatusStr(DaikenMapData.settledStatus.get(dbGoodsNew.getAuditStatus()));
		return bo;

	}

	/**
	 * 商品SKU
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	public KemeanPageAdminBO<List<AdminGoodsNewSkuBO>> goodsNewSkuData(AdminGoodsSkuPO adminGoodsSkuPO) {
		List<DaikenGoodsNewSku> dbNewSku = daikenGoodsNewSkuDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
				new Object[] { false, adminGoodsSkuPO.getGoodsId() }, "id", false, adminGoodsSkuPO.getPage(),
				adminGoodsSkuPO.getLimit());

		List<AdminGoodsNewSkuBO> result = new ArrayList<>(dbNewSku.size());

		for (DaikenGoodsNewSku daikenGoodsNewSku : dbNewSku) {
			AdminGoodsNewSkuBO bo = new AdminGoodsNewSkuBO();
			BeanUtils.copyProperties(daikenGoodsNewSku, bo);
			result.add(bo);
		}

		return new KemeanPageAdminBO<List<AdminGoodsNewSkuBO>>(new PageInfo<>(dbNewSku).getTotal(), result);
	}

	/**
	 * 商品信息修改
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月25日
	 */
	@Transactional
	public KemeanResult<String> goodsNewUpdate(AdminGoodsNewUpdatePO adminGoodsNewUpdatePO, Boolean platform) {

		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(adminGoodsNewUpdatePO.getObjId());
		BeanUtils.copyProperties(adminGoodsNewUpdatePO, dbGoodsNew);
		dbGoodsNew.setUpdateTime(new Date());
		dbGoodsNew.setImgsHead(JSONArray.toJSONString(adminGoodsNewUpdatePO.getImgsHead()));
		dbGoodsNew.setImgsDesc(JSONArray.toJSONString(adminGoodsNewUpdatePO.getImgsDesc()));
		if (DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType().equals(dbGoodsNew.getSalesType())) {
			dbGoodsNew.setDiscountTimeBegin(
					KemeanUtilAid.parseDate(adminGoodsNewUpdatePO.getDiscountTimeBegin(), KemeanDateFormatEnum.NORMAL));
			dbGoodsNew.setDiscountTimeEnd(
					KemeanUtilAid.parseDate(adminGoodsNewUpdatePO.getDiscountTimeEnd(), KemeanDateFormatEnum.NORMAL));
		}

		daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);

		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 修改商品 SKU信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */

	@Transactional
	public KemeanResult<String> goodsNewSkuUpdate(AdminGoodsNewSkuUpdatePO adminGoodsNewSkuUpdatePO) {
		DaikenGoodsNewSku dbNewSku = daikenGoodsNewSkuDao.selectById(adminGoodsNewSkuUpdatePO.getObjId());
		BeanUtils.copyProperties(adminGoodsNewSkuUpdatePO, dbNewSku);
		dbNewSku.setPriceSales(adminGoodsNewSkuUpdatePO.getPriceSales());
		dbNewSku.setUpdateTime(new Date());
		daikenGoodsNewSkuDao.updateByPrimaryKeySelective(dbNewSku);

		// 获取商品的最低售价，最低售价折扣,最低门市价
		AdminGoodsNewMinPriceBO goodsNewMinPrice = daikenGoodsNewSkuDao.selectGoodsNewMinPrice(dbNewSku.getGoodsId());

		DaikenGoodsNew goodsNew = daikenGoodsNewDao.selectById(dbNewSku.getGoodsId());
		goodsNew.setMinDiscount(goodsNewMinPrice.getMinDiscount());
		goodsNew.setMinPriceSales(goodsNewMinPrice.getMinPriceSales());
		goodsNew.setMinPriceStore(goodsNewMinPrice.getMinPriceStore());
		daikenGoodsNewDao.updateByPrimaryKeySelective(goodsNew);

		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 删除商品
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月27日
	 */
	public KemeanResult<String> goodsNewDel(Integer objId) {
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(objId);
		dbGoodsNew.setUpdateTime(new Date());
		dbGoodsNew.setDataDeleted(true);
		daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
		return new KemeanResult<String>(true, KemeanTips.Operate.DELETE_SUCCESS);

	}

	/**
	 * 商品代卖报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */

	public KemeanPageAdminBO<List<AdminGoodsPurchasingBO>> goodsPurchasingData(
			AdminGoodsPurchasingPO adminGoodsPurchasingPO) {

		List<DaikenGoodsNew> dbGoodsNew = daikenGoodsNewDao.selectGoodsPurchasing(adminGoodsPurchasingPO.getGoodsId(),
				adminGoodsPurchasingPO.getDateStart(), adminGoodsPurchasingPO.getDateEnd(),
				adminGoodsPurchasingPO.getPage(), adminGoodsPurchasingPO.getLimit());

		List<AdminGoodsPurchasingBO> result = Lists.transform(dbGoodsNew,
				new Function<DaikenGoodsNew, AdminGoodsPurchasingBO>() {

					@Override
					public AdminGoodsPurchasingBO apply(DaikenGoodsNew input) {
						AdminGoodsPurchasingBO bo = new AdminGoodsPurchasingBO();
						DaikenUserShop dbUserShop = daikenUserShopDao.selectById(input.getUserShopId());
						bo.setUserShopName(dbUserShop.getShopName());

						// 销量
						Integer salesNum = daikenOrderGoodsDao.countByProperties(
								new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
								new Object[] { false, adminGoodsPurchasingPO.getGoodsId() });
						bo.setSalesNum(salesNum);
						// 代卖佣金
						bo.setPurchasingPrice(input.getPricePurchasing());
						// 销量额
						Double salesPirce = daikenOrderGoodsDao.goodsSalePrice(adminGoodsPurchasingPO.getGoodsId());
						bo.setSalesPirce(salesPirce);
						bo.setCreateTime(input.getCreateTime());
						return bo;
					}

				});

		return new KemeanPageAdminBO<List<AdminGoodsPurchasingBO>>(new PageInfo<>(dbGoodsNew).getTotal(), result);
	}

	/**
	 * 总销售报表（销售数量、销售额、代卖佣金、售前红包、售后红包金额）
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	public AdminGoodsChartBO goodsChartData(AdminDatePO adminDatePO, int limit, DaikenUser loginer) {
		AdminGoodsChartBO bo = new AdminGoodsChartBO();
		// 销售数量
		List<AdminChartBO> dbchartData = daikenOrderDao.selectShopSalesNum(loginer.getShopId(),
				adminDatePO.getDateStart(), adminDatePO.getDateEnd(), limit);
		bo.setSaleNum(dbchartData);
		// 销售额
		dbchartData = daikenOrderDao.selectShopSalesMoney(loginer.getShopId(), adminDatePO.getDateStart(),
				adminDatePO.getDateEnd(), limit);
		bo.setSaleMoney(dbchartData);
		AdminShopExpendChartBO expengData = adminCommonService.expengData(adminDatePO.getDateStart(),
				adminDatePO.getDateEnd(), null, Arrays.asList(loginer.getShopId()), limit);
		bo.setRedAfterMoney(expengData.getRedAfterMoney());
		bo.setRedBeforeMoney(expengData.getRedBeforeMoney());
		bo.setPricePurchasing(expengData.getPurchasingMoney());
		return bo;
	}

	/**
	 * 商品收入报表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月6日
	 */
	public AdminGoodsChartBO goodsInChartData(AdminGoodsChartPO adminChartsPO, int index, DaikenUser loginer) {

		AdminGoodsChartBO bo = new AdminGoodsChartBO();

		// 销售数量
		List<AdminChartBO> dbGoodsInDate = daikenOrderGoodsDao.selectGoodInSaleNum(loginer.getShopId(),
				adminChartsPO.getGoodsId(), adminChartsPO.getDateStart(), adminChartsPO.getDateEnd(), null, index);
		bo.setSaleNum(dbGoodsInDate);

		// 销售额=销售额-售前售后红包
		dbGoodsInDate = daikenOrderGoodsDao.selectGoodInSalePrice(loginer.getShopId(), adminChartsPO.getGoodsId(),
				adminChartsPO.getDateStart(), adminChartsPO.getDateEnd(), null, index);

		AdminShopExpendChartBO expengData = adminCommonService.expengData(adminChartsPO.getDateStart(),
				adminChartsPO.getDateEnd(), adminChartsPO.getGoodsId(), Arrays.asList(loginer.getShopId()), index);

		List<AdminChartBO> goodsSaleMoney = new ArrayList<>();

		for (AdminChartBO goodsSale : dbGoodsInDate) {
			if (!expengData.getRedAfterMoney().isEmpty()) {
				for (AdminChartBO redAfter : expengData.getRedAfterMoney()) {
					if (goodsSale.getName().equals(redAfter.getName())) {
						goodsSaleMoney.add(new AdminChartBO(goodsSale.getY() - redAfter.getY(), goodsSale.getName()));
					} else {
						goodsSaleMoney.add(goodsSale);
					}
				}
			} else {
				goodsSaleMoney = dbGoodsInDate;
			}
		}
		List<AdminChartBO> goodsSaleData = new ArrayList<>();

		for (AdminChartBO saleMoney : goodsSaleMoney) {
			if (!expengData.getRedBeforeMoney().isEmpty()) {
				for (AdminChartBO redShareGet : expengData.getRedBeforeMoney()) {
					if (saleMoney.getName().equals(redShareGet.getName())) {
						goodsSaleData.add(new AdminChartBO(saleMoney.getY(), saleMoney.getName()));
					} else {
						goodsSaleData.add(saleMoney);
					}
				}
			} else {
				goodsSaleData = goodsSaleMoney;
			}
		}

		bo.setSaleMoney(goodsSaleData);

		return bo;
	}

	/**
	 * 促销活动报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */
	public KemeanPageAdminBO<List<AdminShopPromotionBO>> goodsPromotionData(AdminShopPromotionPO adminShopPromotionPO,
			DaikenUser loginer) {

		List<AdminShopPromotionBO> dbGoods = daikenOrderGoodsDao.selectGoodsDiscountPriceSum(loginer.getShopId(), null,
				adminShopPromotionPO.getName(), adminShopPromotionPO.getType(), adminShopPromotionPO.getDateStart(),
				adminShopPromotionPO.getDateEnd(), adminShopPromotionPO.getPage() - 1, adminShopPromotionPO.getLimit());

		List<AdminShopPromotionBO> result = new ArrayList<>(dbGoods.size());
		for (AdminShopPromotionBO adminShopPromotionBO : dbGoods) {
			adminShopPromotionBO.setSalesTypeStr(DaikenMapData.salesType.get(adminShopPromotionBO.getSalesType()));
			result.add(adminShopPromotionBO);
		}

		return new KemeanPageAdminBO<List<AdminShopPromotionBO>>(new PageInfo<>(dbGoods).getTotal(), result);

	}

	/**
	 * 商品红包报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */
	public AdminGoodsChartBO goodsNewRedShareData(AdminGoodsChartPO adminGoodsChartPO, int limit, DaikenUser loginer) {
		AdminGoodsChartBO bo = new AdminGoodsChartBO();

		AdminShopExpendChartBO expengData = adminCommonService.expengData(adminGoodsChartPO.getDateStart(),
				adminGoodsChartPO.getDateEnd(), adminGoodsChartPO.getGoodsId(), Arrays.asList(loginer.getShopId()),
				limit);
		bo.setRedAfterMoney(expengData.getRedAfterMoney());
		bo.setRedBeforeMoney(expengData.getRedBeforeMoney());
		return bo;
	}

	/**
	 * 单个店铺及总店铺的商品数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	public AdminGoodsChartBO goodsCount(AdminGoodsChartPO adminChartsPO, int index) {
		AdminGoodsChartBO bo = new AdminGoodsChartBO();
		// 商品数量
		List<AdminChartBO> dbGoods = daikenGoodsNewDao.selectGoodsNum(adminChartsPO.getShopId(),
				adminChartsPO.getDateStart(), adminChartsPO.getDateEnd(), index);
		bo.setGoodsNum(dbGoods);

		// 商品在售数量
		dbGoods = daikenGoodsNewDao.selectGoodsSaleNum(adminChartsPO.getShopId(), adminChartsPO.getDateStart(),
				adminChartsPO.getDateEnd(), index);
		bo.setSaleNum(dbGoods);

		// 商品下架数量
		dbGoods = daikenGoodsNewDao.selectGoodsStatusNum(adminChartsPO.getShopId(), adminChartsPO.getDateStart(),
				adminChartsPO.getDateEnd(), index);
		bo.setGoodsStatusNum(dbGoods);
		return bo;
	}

	/**
	 * 商品成交报表（成交数量、退货数量）
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	public AdminGoodsChartBO goodsCompleteCount(AdminGoodsChartPO adminChartsPO, int index) {
		AdminGoodsChartBO bo = new AdminGoodsChartBO();

		// 成交数量
		List<AdminChartBO> dbGoods = daikenOrderDao.selectGoodsFinishNum(adminChartsPO.getShopId(),
				adminChartsPO.getDateStart(), adminChartsPO.getDateEnd(), index);
		bo.setSaleNum(dbGoods);

		// 商品退货
		dbGoods = daikenOrderAfterSaleDao.selectGoodsReturnNum(adminChartsPO.getShopId(), adminChartsPO.getDateStart(),
				adminChartsPO.getDateEnd(), index);
		bo.setGoodsReturnNum(dbGoods);
		return bo;
	}

	/**
	 * 商品类型统计报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	public List<AdminChartBO> goodsCategoryCount(AdminGoodsChartPO adminGoodsChartPO) {
		return daikenGoodsNewDao.selectGoodsCategoryCount(adminGoodsChartPO.getShopId(),
				adminGoodsChartPO.getDateStart(), adminGoodsChartPO.getDateEnd());
	}

	/**
	 * 查询商品分类data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	public KemeanPageAdminBO<List<AdminGoodsCategoryBO>> goodsCategoryData(AdminGoodsCategoryPO adminGoodsCategoryPO) {

		List<DaikenGoodsBaseCategory> dbCategory = daikenGoodsBaseCategoryDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "name", "level", "pid" },
				new Object[] { false, adminGoodsCategoryPO.getName(), adminGoodsCategoryPO.getLevel(),
						adminGoodsCategoryPO.getPid() },
				new String[] { "name" }, "id", false, adminGoodsCategoryPO.getPage(), adminGoodsCategoryPO.getLimit());

		List<AdminGoodsCategoryBO> result = Lists.transform(dbCategory,
				new Function<DaikenGoodsBaseCategory, AdminGoodsCategoryBO>() {

					@Override
					public AdminGoodsCategoryBO apply(DaikenGoodsBaseCategory input) {
						AdminGoodsCategoryBO bo = new AdminGoodsCategoryBO();
						BeanUtils.copyProperties(input, bo);
						return bo;
					}

				});
		return new KemeanPageAdminBO<List<AdminGoodsCategoryBO>>(new PageInfo<>(dbCategory).getTotal(), result);
	}

	/**
	 * 商品分类 添加/修改
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */

	@Transactional
	public KemeanResult<String> goodsCategoryOperate(AdminGoodsCategoryAddPO adminGoodsCategoryAddPO) {
		Date now = new Date();
		String recordTypeId = "";
		if (StringUtils.isNoneBlank(adminGoodsCategoryAddPO.getRecordTypeId())) {
			String[] split = adminGoodsCategoryAddPO.getRecordTypeId().split(",");
			List<Integer> typeIdList = new ArrayList<>(split.length);
			for (String item : split) {
				typeIdList.add(Integer.valueOf(item));
			}
			recordTypeId = JSONObject.toJSONString(typeIdList);
		}

		if (adminGoodsCategoryAddPO.getObjId() != null) {
			DaikenGoodsBaseCategory dbBaseCategory = daikenGoodsBaseCategoryDao
					.selectById(adminGoodsCategoryAddPO.getObjId());
			BeanUtils.copyProperties(adminGoodsCategoryAddPO, dbBaseCategory);
			dbBaseCategory.setRecordTypeId(recordTypeId);
			dbBaseCategory.setUpdateTime(now);
			daikenGoodsBaseCategoryDao.updateByPrimaryKeySelective(dbBaseCategory);
		} else {
			DaikenGoodsBaseCategory newCategory = new DaikenGoodsBaseCategory();
			BeanUtils.copyProperties(adminGoodsCategoryAddPO, newCategory);
			newCategory.setRecordTypeId(recordTypeId);
			newCategory.setCreateTime(now);
			newCategory.setUpdateTime(now);
			daikenGoodsBaseCategoryDao.saveSelective(newCategory);
		}
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 商品分类删除
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	@Transactional
	public KemeanResult<String> goodsCategoryDel(Integer objId) {
		DaikenGoodsBaseCategory dbCategory = daikenGoodsBaseCategoryDao.selectById(objId);
		dbCategory.setDataDeleted(true);
		daikenGoodsBaseCategoryDao.updateByPrimaryKeySelective(dbCategory);
		return new KemeanResult<>(true, KemeanTips.Operate.DELETE_SUCCESS);
	}

	/**
	 * 商品规格详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	public AdminGoodsCategoryBO goodsCategoryInfo(Integer objId) {
		AdminGoodsCategoryBO bo = new AdminGoodsCategoryBO();
		DaikenGoodsBaseCategory dbCategory = daikenGoodsBaseCategoryDao.selectById(objId);
		BeanUtils.copyProperties(dbCategory, bo);
		bo.setRecordTypeId(JSONArray.parseArray(dbCategory.getRecordTypeId(), Integer.class));
		return bo;
	}

	/**
	 * 获取分类名称
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	public KemeanIdAndName getCategoryName(Integer objId) {
		DaikenGoodsBaseCategory dbCategory = daikenGoodsBaseCategoryDao.selectById(objId);
		return new KemeanIdAndName(dbCategory.getId(), dbCategory.getName());
	}

	/**
	 * 获取商品标题
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */

	public KemeanIdAndName getGoodsTitle(Integer objId) {
		DaikenGoodsNew dbGoods = daikenGoodsNewDao.selectById(objId);
		return new KemeanIdAndName(dbGoods.getId(), dbGoods.getTitle());

	}

	/**
	 * 获取商品规格/类型
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月11日
	 */
	public List<KemeanIdAndName> getGoodsType() {
		List<DaikenGoodsBaseType> dbBaseType = daikenGoodsBaseTypeDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false });
		return Lists.transform(dbBaseType, new Function<DaikenGoodsBaseType, KemeanIdAndName>() {
			@Override
			public KemeanIdAndName apply(DaikenGoodsBaseType input) {
				return new KemeanIdAndName(input.getId(), input.getName());
			}
		});
	}

	/**
	 * 商铺信息
	 * 
	 * @author huwei
	 * @date 2018年7月18日
	 */
	public List<DaikenShop> getShopInfos() {
		return daikenShopDao.selectByProperties(new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false });
	}

	/**
	 * 商品信息
	 * 
	 * @author huwei
	 * @date 2018年7月18日
	 */
	public List<DaikenGoodsNew> getShopGoodsInfo(Integer shopId) {
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId" }, new Object[] { false, shopId });

		return dbGoodsNews;
	}

	/**
	 * 商品精准投放匹配条件的用户
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月19日
	 */
	/*
	 * public KemeanPageAdminBO<List<AdminUserBO>>
	 * precisionPutUserData(AdminGoodsConditionPO adminGoodsConditionPO) {
	 * 
	 * List<Integer> city = new ArrayList<>(); if
	 * (StringUtils.isNoneBlank(adminGoodsConditionPO.getCityId())) { String[]
	 * splitCity = adminGoodsConditionPO.getCityId().split(","); for (String item :
	 * splitCity) { city.add(Integer.valueOf(item)); } } List<String> age = new
	 * ArrayList<>(); if
	 * (StringUtils.isNoneBlank(adminGoodsConditionPO.getUserAge())) { String[]
	 * splitAge = adminGoodsConditionPO.getUserAge().split("。"); for (String item :
	 * splitAge) { age.add(item); } }
	 * 
	 * List<DaikenUser> dbUser = daikenUserDao.selectGoodsPutUser(city, age,
	 * adminGoodsConditionPO.getUserSex(), adminGoodsConditionPO.getPage(),
	 * adminGoodsConditionPO.getLimit());
	 * 
	 * List<AdminUserBO> result = Lists.transform(dbUser, new Function<DaikenUser,
	 * AdminUserBO>() {
	 * 
	 * @Override public AdminUserBO apply(DaikenUser input) { AdminUserBO bo = new
	 * AdminUserBO();
	 * 
	 * BeanUtils.copyProperties(input, bo);
	 * bo.setUserTypeStr(DaikenMapData.userType.get(input.getUserType()));
	 * bo.setSexMan(BooleanUtils.isTrue(input.getSexMan()) ? "男" : "女"); return bo;
	 * } }); return new KemeanPageAdminBO<List<AdminUserBO>>(new
	 * PageInfo<>(dbUser).getTotal(), result); }
	 */

	/**
	 * 保存推送用户
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */

	@Transactional
	public KemeanResult<String> precisionOperate(AdminGoodsPrecisionUserPO adminGoodsPrecisionUserPO,
			DaikenUser loginer) {
		Date now = new Date();
		String record = commonService.getConfig(DaikenConfigEnum.PRECISE_DELIVERY).getRecord();
		// 商家支付金额 = 单价*上限金额
		Double payMoney = adminGoodsPrecisionUserPO.getMaxMoney() * Double.valueOf(record);
		DaikenUser dbUser = daikenUserDao.selectById(loginer.getId());
		DaikenShop dbShop = daikenShopDao.selectById(loginer.getShopId());
		Double balance = userService.getConsumerOrBusinessBlance(loginer);
		if (balance - payMoney < 0) {
			return new KemeanResult<>(false, "您的余额不足,请充值!");
		}
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(adminGoodsPrecisionUserPO.getGoodsId());
		// 匹配的最大人数
		int maxPeople = (int) Math.round(adminGoodsPrecisionUserPO.getMaxMoney() / Double.valueOf(record));
		if (maxPeople == 0) {
			return new KemeanResult<>(false, "匹配人数为0,请重新填写上限金额!");
		}
		dbGoodsNew.setMaxMoney(adminGoodsPrecisionUserPO.getMaxMoney());
		dbGoodsNew.setMaxPeople(maxPeople);
		dbGoodsNew.setMatchDate(now);
		dbGoodsNew.setMatchCondition(JSONObject.toJSONString(adminGoodsPrecisionUserPO.getMatchCondition()));
		dbGoodsNew.setUpdateTime(now);
		String cacheKeyHMatch = String.format(DaikenRedisKeyEnum.BIG_DATA_H.getKey(),
				adminGoodsPrecisionUserPO.getCategoryId());
		String cacheKeySMatch = String.format(DaikenRedisKeyEnum.BIG_DATA_S.getKey(),
				adminGoodsPrecisionUserPO.getCategoryId());
		int start = (int) (KemeanUtilAid.getDateByCalendar(KemeanCalendarFieldEnum.DAY, -14).getTime() / 100000.0);
		kemeanRedisService.zremrangeByScore(cacheKeySMatch, 0, start);
		Set<String> zrange = kemeanRedisService.zrevrange(cacheKeySMatch, maxPeople, -1);
		List<DaikenGoodsRecommendUser> userList = new ArrayList<>();
		// 【第一步】 精准匹配
		for (String item : zrange) {
			DaikenUser cacheUser = JSONObject.parseObject(kemeanRedisService.hget(cacheKeyHMatch, item),
					DaikenUser.class);
			// 精准推送的用户
			List<DaikenUser> precisionUser = daikenUserDao.selectGoodsPutUser(cacheUser.getId(),
					adminGoodsPrecisionUserPO.getMatchCondition().getCityId(),
					adminGoodsPrecisionUserPO.getMatchCondition().getAge(),
					adminGoodsPrecisionUserPO.getMatchCondition().getSexMan());
			for (DaikenUser daikenUser : precisionUser) {
				DaikenGoodsRecommendUser insertUser = insertUser(daikenUser, dbGoodsNew.getId(), true);
				if (userList.size() <= maxPeople) {
					userList.add(insertUser);
				}
			}

		}
		// 筛选的用户没有达到商家匹配的最大用户人数，从平台中随机匹配用户
		if (userList.size() < maxPeople) {
			// 还少多少个用户
			int userNum = maxPeople - userList.size();
			// 【第二步】忽略掉商品分类id条件，进行筛选用户
			List<DaikenUser> precisionUser = daikenUserDao.selectGoodsPutUser(null,
					adminGoodsPrecisionUserPO.getMatchCondition().getCityId(),
					adminGoodsPrecisionUserPO.getMatchCondition().getAge(),
					adminGoodsPrecisionUserPO.getMatchCondition().getSexMan());
			if (!precisionUser.isEmpty()) {
				for (DaikenUser daikenUser : precisionUser) {
					DaikenGoodsRecommendUser insertUser = insertUser(daikenUser, dbGoodsNew.getId(), false);
					if (userList.size() <= userNum) {
						userList.add(insertUser);
					}
				}
			}

			// 【第三步】 还未达到最大用户数量，就随机分配 消费者
			if (userList.size() < userNum) {
				List<DaikenUser> dbUserList = daikenUserDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "userStatus", "userType" }, new Object[] { false,
								KemeanUserEnum.UserStatus.NORMAL.getStatus(), DaikenUserTypeEnum.CONSUMER.getType() });

				for (int i = 0; i < userNum; i++) {
					int randomIndex = new Random().nextInt(dbUserList.size());
					DaikenGoodsRecommendUser insertUser = insertUser(dbUserList.get(randomIndex), dbGoodsNew.getId(),
							false);
					userList.add(insertUser);
				}
			}
		}
		dbUser.setBalancePrice(balance - payMoney);
		daikenGoodsRecommendUserDao.saveUserList(userList);
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
		// 商户端 生成一条收费记录
		commonService.createFinanceOrder(dbShop.getId() + "【" + dbShop.getShopName() + "】",
				DaikenFinanceTypeEnum.B_RECOMMEND_ADVERTISING.getType(), payMoney, 0, dbUser.getShopId(),
				dbShop.getShopName(), "精准推送（支出） ");
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 商品精准送达达人数据
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */
	public KemeanPageAdminBO<List<AdminUserBO>> goodsPushUserDataData(AdminGoodsPrecisionPO adminGoodsPrecisionPO,
			DaikenUser adminLoginShop) {

		List<DaikenGoodsNew> dbGoods = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId" },
				new Object[] { false, adminLoginShop.getShopId() });
		List<Integer> goodsId = new ArrayList<>(dbGoods.size());
		if (adminGoodsPrecisionPO.getGoodsId() == null) {
			for (DaikenGoodsNew item : dbGoods) {
				goodsId.add(item.getId());
			}
		} else {
			goodsId.add(adminGoodsPrecisionPO.getGoodsId());
		}

		List<DaikenGoodsRecommendUser> dbGoodsUser = daikenGoodsRecommendUserDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED, "isPrecision" },
				new Object[] { false, adminGoodsPrecisionPO.getIsPrecision() }, "goodsId", goodsId, "id", false,
				adminGoodsPrecisionPO.getPage(), adminGoodsPrecisionPO.getLimit());

		List<AdminUserBO> result = Lists.transform(dbGoodsUser, new Function<DaikenGoodsRecommendUser, AdminUserBO>() {

			@Override
			public AdminUserBO apply(DaikenGoodsRecommendUser input) {
				AdminUserBO bo = new AdminUserBO();
				BeanUtils.copyProperties(input, bo);
				bo.setSexMan(BooleanUtils.isTrue(input.getSexMan()) ? "男" : "女");
				bo.setIsPrecision(BooleanUtils.isTrue(input.getIsPrecision()) ? "是" : "否");
				bo.setId(input.getUserId());
				return bo;
			}
		});

		return new KemeanPageAdminBO<List<AdminUserBO>>(new PageInfo<>(dbGoodsUser).getTotal(), result);
	}

	private DaikenGoodsRecommendUser insertUser(DaikenUser cacheUser, Integer goodsId, boolean precision) {
		Date now = new Date();
		DaikenGoodsRecommendUser newUser = new DaikenGoodsRecommendUser();
		newUser.setUserId(cacheUser.getId());
		newUser.setNickName(cacheUser.getNickName());
		newUser.setPhone(cacheUser.getPhone());
		newUser.setSexMan(cacheUser.getSexMan());
		newUser.setGoodsId(goodsId);
		newUser.setDataDeleted(false);
		newUser.setIsPrecision(precision);
		newUser.setUpdateTime(now);
		newUser.setCreateTime(now);
		return newUser;
	}

	/**
	 * 商品审核
	 * 
	 * @author huwei
	 * @date 2018年8月20日
	 */
	@Transactional
	public KemeanResult<String> goodsStatusAudit(AdminGoodsAuditStatusPO adminGoodsAuditStatusPO) {
		Date now = new Date();
		DaikenGoodsNew dbGoods = daikenGoodsNewDao.selectById(adminGoodsAuditStatusPO.getGoodsId());
		dbGoods.setUpdateTime(now);
		dbGoods.setAuditStatus(adminGoodsAuditStatusPO.getStatus());
		daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoods);

		// 改变帮卖信息
		daikenGoodsNewDao.updateEntityByProperties("auditStatus", adminGoodsAuditStatusPO.getStatus(),
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
				new Object[] { false, adminGoodsAuditStatusPO.getGoodsId() });
		DaikenShop dbShop = daikenShopDao.selectById(dbGoods.getShopId());
		// 审核通过,设置商品为上架状态
		if (KemeanSettledEnum.AUDIT_PASS.getStatus().equals(adminGoodsAuditStatusPO.getStatus())) {
			dbGoods.setGoodsStatus(true);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoods);
			// 商品状态为： 更新待审核, 覆盖旧的商品信息,商品上架
			if (StringUtils.isNoneBlank(dbGoods.getEditRecord())) {
				GoodsPO parseObj = JSONObject.parseObject(dbGoods.getEditRecord(), GoodsPO.class);
				parseObj.setShopId(dbGoods.getShopId());
				bMineService.addGoods(parseObj, null, true);
			}

			String msg = String.format(commonService.getConfig(DaikenConfigEnum.GOODS_AUDIT_SUCCESS).getRecord(),
					dbGoods.getTitle());
			userService.daikenSendCode(dbShop.getShopPhone(), msg);
		}

		// 审核不通过
		if (KemeanSettledEnum.AUDIT_FAILUE.getStatus().equals(adminGoodsAuditStatusPO.getStatus())) {
			String msg = String.format(commonService.getConfig(DaikenConfigEnum.GOODS_AUDIT_ERROR).getRecord(),
					dbGoods.getTitle());
			userService.daikenSendCode(dbShop.getShopPhone(), msg);

		}

		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 店铺营销之后的销量、销售额、访问量、成交量
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月24日
	 */
	public AdminGoodsMarketingBO goodsMarketingData(Integer objId, DaikenUser loginer) {

		List<DaikenGoodsRecommendUser> dbRecommendUser = daikenGoodsRecommendUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" }, new Object[] { false, objId });

		List<Integer> userIds = new ArrayList<>();
		for (DaikenGoodsRecommendUser daikenGoodsRecommendUser : dbRecommendUser) {
			userIds.add(daikenGoodsRecommendUser.getUserId());
		}

		List<String> goodsMarketingByOrderNo = daikenOrderDao.selectGoodsMarketingByOrderNo(loginer.getShopId(),
				userIds, false);
		// 成交量
		Integer clinchNum = 0;
		// 访问量
		Integer accessNum = 0;
		// 成交人数
		Integer clinchUserNum = 0;
		// 销售量
		Integer saleNum = 0;
		// 销售额
		Double salePrice = 0.00;

		AdminGoodsMarketingBO goodsMarketing = new AdminGoodsMarketingBO();
		// 当前购买人数
		goodsMarketing.setBuyUserNum(goodsMarketingByOrderNo.size());
		if (!goodsMarketingByOrderNo.isEmpty()) {
			goodsMarketing = daikenOrderGoodsDao.selectGoodsMarkting(goodsMarketingByOrderNo);
			saleNum = goodsMarketing.getSaleNum();
			salePrice = goodsMarketing.getSalePrice();

		}
		goodsMarketing.setSalePrice(salePrice);
		goodsMarketing.setSaleNum(saleNum);

		// 成交量【商品】
		List<String> clinchOrderNo = daikenOrderDao.selectGoodsMarketingByOrderNo(loginer.getShopId(), userIds, true);
		clinchUserNum = clinchOrderNo.size();
		// 成交人数
		if (!clinchOrderNo.isEmpty()) {
			goodsMarketing = daikenOrderGoodsDao.selectGoodsMarkting(clinchOrderNo);
			if (goodsMarketing.getClinchNum() != null) {
				clinchNum = goodsMarketing.getClinchNum();
			}
		}
		goodsMarketing.setClinchUserNum(clinchUserNum);
		goodsMarketing.setClinchNum(clinchNum);
		// 访问量
		String cacheHashKey = String.format(DaikenRedisKeyEnum.BIG_DATA_GOODS_BROWSE_H.getKey(), objId);
		Boolean existsKey = kemeanRedisService.exists(cacheHashKey);
		if (existsKey) {
			Map<String, String> hGetAll = kemeanRedisService.hGetAll(cacheHashKey);
			accessNum = hGetAll.size();
		}
		goodsMarketing.setAccessNum(accessNum);
		return goodsMarketing;
	}

	/**
	 * 商品访问用户列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月24日
	 */
	public KemeanPageAdminBO<List<AdminUserBO>> goodsAccessPeopleData(AdminUserListPO adminUserListPO) {

		String cacheKeyS = String.format(DaikenRedisKeyEnum.BIG_DATA_GOODS_BROWSE_S.getKey(),
				adminUserListPO.getGoodsId());

		String cacheKeyH = String.format(DaikenRedisKeyEnum.BIG_DATA_GOODS_BROWSE_H.getKey(),
				adminUserListPO.getGoodsId());

		// 最后一千个以内
		Integer zcard = kemeanRedisService.zcard(cacheKeyS).intValue();
		Integer start = zcard;
		if (zcard > 1000) {
			start = zcard - 1000;
		}
		Set<String> zrevrange = kemeanRedisService.zrevrange(cacheKeyS, start - 1, -1);

		// 分页之后的结果
		List<String> cacheReslut = KemeanUtilAid.pageByJava(zrevrange, adminUserListPO.getPage(),
				adminUserListPO.getLimit());
		List<AdminUserBO> result = new ArrayList<>(cacheReslut.size());
		for (String cacheItem : cacheReslut) {
			AdminUserBO bo = new AdminUserBO();
			String hgetReslut = kemeanRedisService.hget(cacheKeyH, cacheItem);
			DaikenUser parseObj = JSONObject.parseObject(hgetReslut, DaikenUser.class);
			BeanUtils.copyProperties(parseObj, bo);
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<AdminUserBO>>(new PageInfo<>(cacheReslut).getTotal(), result);
	}

	/**
	 * 商品精准推送列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	public KemeanPageAdminBO<List<AdminGoodsPrecisionPushBO>> goodsPrecisionPushData(
			AdminGoodsPrecisionPushPO adminGoodsPrecisionPushPO, DaikenUser loginAdminShop) {

		List<DaikenGoodsNew> dbGoodsNew = daikenGoodsNewDao.goodsPrecisionPushData(loginAdminShop.getShopId(),
				adminGoodsPrecisionPushPO.getGoodsUid(), adminGoodsPrecisionPushPO.getPage(),
				adminGoodsPrecisionPushPO.getLimit());

		List<AdminGoodsPrecisionPushBO> result = Lists.transform(dbGoodsNew,
				new Function<DaikenGoodsNew, AdminGoodsPrecisionPushBO>() {
					@Override
					public AdminGoodsPrecisionPushBO apply(DaikenGoodsNew input) {
						AdminGoodsPrecisionPushBO bo = new AdminGoodsPrecisionPushBO();
						BeanUtils.copyProperties(input, bo);
						bo.setStartDate(input.getMatchDate());
						bo.setGoodsTitle(input.getTitle());
						AdminGoodsMarketingBO goodsMarketingData = goodsMarketingData(input.getId(), loginAdminShop);
						// 当前点击人数
						Integer newClickUserNum = goodsMarketingData.getAccessNum();
						bo.setNowClickUserNum(newClickUserNum);
						// 浏览商品的人数
						String cacheHashKey = String.format(DaikenRedisKeyEnum.BIG_DATA_GOODS_BROWSE_H.getKey(),
								input.getId());

						// 目标点击人数
						Integer targetClickUserNum = 0;
						Boolean existsKey = kemeanRedisService.exists(cacheHashKey);
						if (existsKey) {
							Map<String, String> hGetAll = kemeanRedisService.hGetAll(cacheHashKey);
							// 精准匹配的人数
							List<DaikenGoodsRecommendUser> dbRecommendUser = daikenGoodsRecommendUserDao
									.selectByProperties(
											new String[] { "goodsId", "isPrecision", KemeanConstant.DATA_DELETED },
											new Object[] { input.getId(), true, false });
							if (!dbRecommendUser.isEmpty()) {
								for (String item : hGetAll.keySet()) {
									for (DaikenGoodsRecommendUser userItem : dbRecommendUser) {
										if (userItem.getUserId().equals(Integer.valueOf(item))) {
											targetClickUserNum += 1;
										}
									}
								}
							}
						}
						bo.setTargetClickUserNum(targetClickUserNum);
						// 当前购买人数
						bo.setNowBuyUserNum(goodsMarketingData.getBuyUserNum());
						// 当前转化率= 目标点击人数/总的点击人数
						Integer totalClickNum = newClickUserNum + targetClickUserNum;
						Double conversionRate = 0.00;
						if (totalClickNum > 0 || targetClickUserNum > 0) {
							conversionRate = targetClickUserNum.doubleValue() / totalClickNum.doubleValue();

						}
						bo.setConversionRate(conversionRate);
						return bo;
					}
				});
		return new KemeanPageAdminBO<List<AdminGoodsPrecisionPushBO>>(new PageInfo<>(result).getTotal(), result);
	}

	/**
	 * 商品总销售收入报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月29日
	 */
	public KemeanPageAdminBO<List<AdminGoodsSaleTotalBO>> goodsSaleTotalData(AdminShopOrderListPO adminShopOrderListPO,
			DaikenUser loginAdminShop) {
		List<AdminGoodsSaleTotalBO> result = daikenOrderGoodsDao.selectGoodsSaleTotalData(loginAdminShop.getShopId(),
				adminShopOrderListPO.getStartDate(), adminShopOrderListPO.getEndDate(),
				adminShopOrderListPO.getPage() - 1, adminShopOrderListPO.getLimit());
		return new KemeanPageAdminBO<List<AdminGoodsSaleTotalBO>>(new PageInfo<>(result).getTotal(), result);
	}

	/**
	 * 商品推荐按钮
	 * 
	 * @author huwei
	 * @date 2018年9月6日
	 */
	public KemeanResult<String> recommendChange(Integer objId) {
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(objId);
		dbGoodsNew.setRecommend(!dbGoodsNew.getRecommend());
		dbGoodsNew.setUpdateTime(new Date());
		daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
		return new KemeanResult<>();
	}

}
