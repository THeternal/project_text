package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenShopReply;
import com.kemean.bean.DaikenShopSettled;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserSales;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.constant.KemeanMapData;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.DaikenRedShareGetDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenShopReplyDao;
import com.kemean.dao.DaikenShopSettledDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.common.AdminShopExpendChartBO;
import com.kemean.vo.bo.admin.shop.AdminShopBO;
import com.kemean.vo.bo.admin.shop.AdminShopChartBO;
import com.kemean.vo.bo.admin.shop.AdminShopFinanceClearBO;
import com.kemean.vo.bo.admin.shop.AdminShopFinanceWaterBO;
import com.kemean.vo.bo.admin.shop.AdminShopHomeChartBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;
import com.kemean.vo.bo.admin.shop.AdminShopSettledBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.AdminMonitorPO;
import com.kemean.vo.po.admin.AdminUpdateInfoPO;
import com.kemean.vo.po.admin.shop.AdminShopAuditStatusPO;
import com.kemean.vo.po.admin.shop.AdminShopChartPO;
import com.kemean.vo.po.admin.shop.AdminShopFinanceClearPO;
import com.kemean.vo.po.admin.shop.AdminShopFinanceOrderPO;
import com.kemean.vo.po.admin.shop.AdminShopPO;
import com.kemean.vo.po.admin.shop.AdminShopPromotionPO;
import com.kemean.vo.po.admin.shop.AdminShopReplyBO;
import com.kemean.vo.po.admin.shop.AdminShopReplyPO;
import com.kemean.vo.po.admin.shop.AdminShopSettledPO;
import com.kemean.vo.po.admin.shop.AdminShopUpdatePO;

@Service
public class AdminShopService {

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenShopReplyDao daikenShopReplyDao;

	@Autowired
	private DaikenShopSettledDao daikenShopSettledDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private AdminCommonService adminCommonService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private DaikenRedShareGetDao daikenRedShareGetDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private KemeanFinanceOrderDaikenDao financeOrderDao;

	/**
	 * 店铺入驻
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	public KemeanPageAdminBO<List<AdminShopSettledBO>> shopSettledData(AdminShopSettledPO adminShopSettledPO) {

		List<DaikenShopSettled> dbShopSettled = daikenShopSettledDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "principalPhone", "shopName", "auditStatus",
						"settledPersonal" },
				new Object[] { false, adminShopSettledPO.getPhone(), adminShopSettledPO.getShopName(),
						adminShopSettledPO.getAuditStatus(), adminShopSettledPO.getSettledPersonal() },
				new String[] { "shopName" }, "id", false, adminShopSettledPO.getPage(), adminShopSettledPO.getLimit());

		List<AdminShopSettledBO> result = Lists.transform(dbShopSettled,
				new Function<DaikenShopSettled, AdminShopSettledBO>() {

					@Override
					public AdminShopSettledBO apply(DaikenShopSettled input) {
						AdminShopSettledBO bo = new AdminShopSettledBO();
						BeanUtils.copyProperties(input, bo);
						bo.setSettledPersonalStr(BooleanUtils.isTrue(input.getSettledPersonal()) ? "个人" : "企业");
						bo.setAuditStatusStr(KemeanMapData.settleStatus.get(input.getAuditStatus()));

						return bo;
					}
				});

		return new KemeanPageAdminBO<List<AdminShopSettledBO>>(new PageInfo<>(dbShopSettled).getTotal(), result);
	}

	/**
	 * 店铺入驻详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */
	public AdminShopSettledBO shopSettledInfoData(Integer shopId) {
		AdminShopSettledBO bo = new AdminShopSettledBO();
		DaikenShopSettled dbShopSettled = daikenShopSettledDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId" }, new Object[] { false, shopId });
		BeanUtils.copyProperties(dbShopSettled, bo);
		bo.setSettledPersonalStr(BooleanUtils.isTrue(dbShopSettled.getSettledPersonal()) ? "个人" : "企业");
		bo.setAuditStatusStr(KemeanMapData.settleStatus.get(dbShopSettled.getAuditStatus()));
		bo.setIsIdCardValidityStr("长期");
		if (!dbShopSettled.getIsIdCardValidity()) {
			bo.setIsIdCardValidityStr(
					KemeanUtilAid.formatDate(dbShopSettled.getPeriodOfValidity(), KemeanDateFormatEnum.DATE));
		}

		DaikenShop dbShop = daikenShopDao.selectById(shopId);
		bo.setPresentation(dbShop.getPresentation());
		bo.setPeriodOfValidityStr(
				KemeanUtilAid.formatDate(dbShopSettled.getPeriodOfValidity(), KemeanDateFormatEnum.DATE));
		return bo;
	}

	/**
	 * 店铺审核
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月22日
	 */
	public KemeanResult<String> shopStatusAudit(AdminShopAuditStatusPO shopAuditStatusPO) {
		Date now = new Date();

		DaikenShop dbShop = daikenShopDao.selectUniqueEntityByProperties(
				new String[] { "id", KemeanConstant.DATA_DELETED },
				new Object[] { shopAuditStatusPO.getShopId(), false });
		dbShop.setUpdateTime(now);
		dbShop.setAuditStatus(shopAuditStatusPO.getStatus());
		daikenShopDao.updateByPrimaryKeySelective(dbShop);

		daikenShopSettledDao.updateEntityByProperties(new String[] { "auditStatus", "updateTime", "auditCause" },
				new Object[] { shopAuditStatusPO.getStatus(), now, shopAuditStatusPO.getAuditCause() },
				new String[] { "shopId", KemeanConstant.DATA_DELETED },
				new Object[] { shopAuditStatusPO.getShopId(), false });

		// 审核通过，把该店铺所有商品设置为上架状态
		if (KemeanSettledEnum.AUDIT_PASS.getStatus().equals(shopAuditStatusPO.getStatus())) {
			String msg = String.format(commonService.getConfig(DaikenConfigEnum.SHOP_SETTLED_CHECK).getRecord(),
					dbShop.getShopName());
			userService.daikenSendCode(dbShop.getShopPhone(), msg);
		}

		// 审核不通过
		if (KemeanSettledEnum.AUDIT_FAILUE.getStatus().equals(shopAuditStatusPO.getStatus())) {
			String msg = String.format(commonService.getConfig(DaikenConfigEnum.SHOP_SETTLED_NO_CHECK).getRecord(),
					dbShop.getShopName(), shopAuditStatusPO.getAuditCause());
			userService.daikenSendCode(dbShop.getShopPhone(), msg);
		}

		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 店铺列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月21日
	 */

	public KemeanPageAdminBO<List<AdminShopBO>> shopData(AdminShopPO adminShopPO, DaikenUserSales loginer) {
		List<Integer> shopId = new ArrayList<>();
		if (loginer != null) {
			// TODO 商家推荐码为销售员的手机号码
			List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "referralCode", "userType" },
					new Object[] { false, loginer.getReferralCode(), DaikenUserTypeEnum.BUSINESS.getType() });
			if (!dbUser.isEmpty()) {
				for (DaikenUser daikenUser : dbUser) {
					shopId.add(daikenUser.getShopId());
				}
			} else {
				return new KemeanPageAdminBO<List<AdminShopBO>>(0L, new ArrayList<AdminShopBO>());
			}
		}

		List<DaikenShop> dbShop = daikenShopDao.selectShopList(shopId, adminShopPO.getPhone(),
				adminShopPO.getShopName(), adminShopPO.getShopType(), adminShopPO.getPage(), adminShopPO.getLimit());

		List<AdminShopBO> result = Lists.transform(dbShop, new Function<DaikenShop, AdminShopBO>() {
			@Override
			public AdminShopBO apply(DaikenShop input) {
				AdminShopBO bo = new AdminShopBO();
				BeanUtils.copyProperties(input, bo);
				bo.setSettledTypeStr(BooleanUtils.isTrue(input.getSettledType()) ? "个人" : "企业");
				bo.setAuditStatusStr(KemeanMapData.settleStatus.get(input.getAuditStatus()));
				bo.setWorkStatusStr(BooleanUtils.isTrue(input.getWorkStatus()) ? "营业中" : "打烊");
				return bo;
			}
		});

		return new KemeanPageAdminBO<List<AdminShopBO>>(new PageInfo<>(dbShop).getTotal(), result);
	}

	/**
	 * 监听商铺 上下架/平台推荐状态
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	public KemeanResult<String> shopStatusOperate(AdminMonitorPO adminMonitorPO) {

		DaikenShop dbShop = daikenShopDao.selectById(adminMonitorPO.getObjId());
		// 推荐
		if (adminMonitorPO.getTjOperate()) {
			dbShop.setPlatformRecommend(adminMonitorPO.getStatus());
		} else {
			dbShop.setShopStatus(adminMonitorPO.getStatus());
		}
		dbShop.setUpdateTime(new Date());
		daikenShopDao.updateByPrimaryKeySelective(dbShop);
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 商铺明细
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	public AdminShopBO shopInfoData(Integer objId, DaikenUser loginer) {
		AdminShopBO bo = new AdminShopBO();
		if (objId == null) {
			objId = loginer.getShopId();
		}

		bo.setHeadImg(KemeanConstant.ADMIN_HEAD_IMG);
		if (loginer != null) {
			bo.setNickName(loginer.getNickName());
			if (StringUtils.isNoneBlank(loginer.getHeadImg())) {
				bo.setHeadImg(loginer.getHeadImg());
			}
		}
		DaikenShop dbShop = daikenShopDao.selectById(objId);

		BeanUtils.copyProperties(dbShop, bo);
		bo.setSettledTypeStr(BooleanUtils.isTrue(dbShop.getSettledType()) ? "个人" : "企业");
		bo.setAuditStatusStr(KemeanMapData.settleStatus.get(dbShop.getAuditStatus()));
		return bo;
	}

	/**
	 * 修改商铺信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@Transactional
	public KemeanResult<String> shopInfoUpdate(AdminShopUpdatePO adminShopUpdatePO, DaikenUser loginer) {
		Date now = new Date();
		Integer shopId = adminShopUpdatePO.getShopId();
		if (loginer != null) {
			shopId = loginer.getShopId();
			if (StringUtils.isNoneBlank(adminShopUpdatePO.getPassword())) {
				loginer.setPassword(adminShopUpdatePO.getPassword().toLowerCase());
			}
			daikenUserDao.updateByPrimaryKeySelective(loginer);
		}

		DaikenShop dbShop = daikenShopDao.selectById(shopId);
		BeanUtils.copyProperties(adminShopUpdatePO, dbShop);
		dbShop.setUpdateTime(now);
		daikenShopDao.updateByPrimaryKeySelective(dbShop);

		DaikenShopSettled dbShopSettled = daikenShopSettledDao.selectUniqueEntityByProperty("shopId", shopId);
		dbShopSettled.setShopName(adminShopUpdatePO.getShopName());
		dbShopSettled.setUpdateTime(now);
		daikenShopSettledDao.updateByPrimaryKeySelective(dbShopSettled);

		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 商铺数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	public AdminShopChartBO shopChart(AdminDatePO adminChartPO, int index) {
		AdminShopChartBO bo = new AdminShopChartBO();
		// 个人商铺
		List<AdminChartBO> dbChart = daikenShopDao.selectShopCount(true, adminChartPO.getDateStart(),
				adminChartPO.getDateEnd(), index);

		bo.setUserShopNum(dbChart);
		// 企业商铺
		dbChart = daikenShopDao.selectShopCount(false, adminChartPO.getDateStart(), adminChartPO.getDateEnd(), index);
		bo.setCompanyShopNum(dbChart);
		return bo;
	}

	/**
	 * 商铺默认消息管理
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	public KemeanPageAdminBO<List<AdminShopReplyBO>> shopMsgReplyData(KemeanPageAdminPO kemeanPageAdminPO,
			DaikenUser loginer) {
		List<DaikenShopReply> dbShopReply = daikenShopReplyDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId" }, new Object[] { false, loginer.getShopId() },
				"id", false, kemeanPageAdminPO.getPage(), kemeanPageAdminPO.getLimit());

		List<AdminShopReplyBO> result = Lists.transform(dbShopReply, new Function<DaikenShopReply, AdminShopReplyBO>() {
			@Override
			public AdminShopReplyBO apply(DaikenShopReply input) {
				AdminShopReplyBO bo = new AdminShopReplyBO();
				BeanUtils.copyProperties(input, bo);
				return bo;
			}
		});
		return new KemeanPageAdminBO<List<AdminShopReplyBO>>(new PageInfo<>(dbShopReply).getTotal(), result);
	}

	/**
	 * 删除商铺默认消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	@Transactional
	public void shopMsgReplyDel(Integer objId) {
		DaikenShopReply dbShopReply = daikenShopReplyDao.selectById(objId);
		dbShopReply.setDataDeleted(true);
		dbShopReply.setUpdateTime(new Date());
		daikenShopReplyDao.updateByPrimaryKeySelective(dbShopReply);
	}

	/**
	 * 添加/修改消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	@Transactional
	public void shopMsgReplyOperate(AdminShopReplyPO adminShopReplyPO, DaikenUser loginer) {
		Date now = new Date();
		if (adminShopReplyPO.getObjId() != null) {
			DaikenShopReply dbShopReply = daikenShopReplyDao.selectById(adminShopReplyPO.getObjId());
			dbShopReply.setContent(adminShopReplyPO.getContent());
			dbShopReply.setUpdateTime(now);
			daikenShopReplyDao.updateByPrimaryKeySelective(dbShopReply);
		} else {
			DaikenShopReply newDaikenShopReply = new DaikenShopReply();
			newDaikenShopReply.setContent(adminShopReplyPO.getContent());
			newDaikenShopReply.setShopId(loginer.getShopId());
			newDaikenShopReply.setUpdateTime(now);
			newDaikenShopReply.setCreateTime(now);
			daikenShopReplyDao.saveSelective(newDaikenShopReply);
		}

	}

	/**
	 * 店铺促销活动报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */

	public KemeanPageAdminBO<List<AdminShopPromotionBO>> shopPromotionData(AdminShopPromotionPO adminShopPromotionPO) {
		List<AdminShopPromotionBO> dbGoods = daikenOrderGoodsDao.selectGoodsDiscountPriceSum(null,
				adminShopPromotionPO.getName(), null, adminShopPromotionPO.getType(),
				adminShopPromotionPO.getDateStart(), adminShopPromotionPO.getDateEnd(),
				adminShopPromotionPO.getPage() - 1, adminShopPromotionPO.getLimit());

		List<AdminShopPromotionBO> result = new ArrayList<>(dbGoods.size());
		for (AdminShopPromotionBO adminShopPromotionBO : dbGoods) {

			if (DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType().equals(adminShopPromotionBO.getSalesType())) {
				adminShopPromotionBO.setDiscountTimeBeginStr(KemeanUtilAid
						.formatDate(adminShopPromotionBO.getDiscountTimeBegin(), KemeanDateFormatEnum.NORMAL));

				adminShopPromotionBO.setDiscountTimeEndStr(KemeanUtilAid
						.formatDate(adminShopPromotionBO.getDiscountTimeEnd(), KemeanDateFormatEnum.NORMAL));

			} else {
				// 名牌折扣，9.9包邮，亏本走量，库存为0时，活动结束。
				adminShopPromotionBO.setDiscountTimeBeginStr(
						KemeanUtilAid.formatDate(adminShopPromotionBO.getCreateTime(), KemeanDateFormatEnum.NORMAL));
				List<AdminShopPromotionBO> goodStockSum = daikenGoodsNewSkuDao
						.selectGoodStockSum(adminShopPromotionBO.getGoodId());
				Integer stockCount = 0;
				for (AdminShopPromotionBO goodItem : goodStockSum) {
					stockCount += goodItem.getStock();
				}

				Integer stock = stockCount - adminShopPromotionBO.getQuantity();

				if (stock == 0) {
					adminShopPromotionBO.setDiscountTimeEndStr(KemeanUtilAid
							.formatDate(goodStockSum.get(0).getDiscountTimeEnd(), KemeanDateFormatEnum.NORMAL));
				} else {
					adminShopPromotionBO.setDiscountTimeEndStr("活动进行中");
				}

			}

			adminShopPromotionBO.setSalesTypeStr(DaikenMapData.salesType.get(adminShopPromotionBO.getSalesType()));
			result.add(adminShopPromotionBO);
		}

		return new KemeanPageAdminBO<List<AdminShopPromotionBO>>(new PageInfo<>(dbGoods).getTotal(), dbGoods);
	}

	/**
	 * 店铺支出报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */
	public AdminShopChartBO shopExpendData(AdminShopChartPO adminShopChartPO, int limit, String referralCode) {
		AdminShopChartBO bo = new AdminShopChartBO();

		List<Integer> shopId = new ArrayList<>();
		if (StringUtils.isNoneBlank(referralCode)) {
			List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "referralCode" }, new Object[] { false, referralCode });
			for (DaikenUser daikenUser : dbUser) {
				shopId.add(daikenUser.getShopId());
			}
		}

		if (adminShopChartPO.getShopId() != null) {
			shopId.add(adminShopChartPO.getShopId());
		}

		AdminShopExpendChartBO expengData = adminCommonService.expengData(adminShopChartPO.getDateStart(),
				adminShopChartPO.getDateEnd(), null, shopId, limit);
		bo.setRedAfterMoney(expengData.getRedAfterMoney());
		bo.setRedBeforeMoney(expengData.getRedBeforeMoney());
		bo.setPurchasingMoney(expengData.getPurchasingMoney());
		return bo;
	}

	/**
	 * 店铺收益报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */
	public List<AdminChartBO> shopIncomeData(AdminShopChartPO adminShopChartPO, int limit) {

		// 店铺收益=总销售额-售前红包-售前红包
		// 总销售额
		List<AdminChartBO> selectShopSalesMoney = daikenOrderDao.selectShopSalesMoney(adminShopChartPO.getShopId(),
				adminShopChartPO.getDateStart(), adminShopChartPO.getDateEnd(), limit);

		AdminShopChartBO shopExpendData = shopExpendData(adminShopChartPO, limit, "");
		List<AdminChartBO> redAfterCompareData = new ArrayList<>(selectShopSalesMoney.size());
		for (AdminChartBO adminChartBO : selectShopSalesMoney) {
			if (!shopExpendData.getRedAfterMoney().isEmpty()) {
				for (AdminChartBO redAfterData : shopExpendData.getRedAfterMoney()) {
					if (adminChartBO.getName().equals(redAfterData.getName())) {
						redAfterCompareData.add(
								new AdminChartBO(adminChartBO.getY() - redAfterData.getY(), adminChartBO.getName()));
					} else {
						redAfterCompareData.add(new AdminChartBO(adminChartBO.getY(), adminChartBO.getName()));
					}
				}
			} else {
				redAfterCompareData = selectShopSalesMoney;
			}
		}

		List<AdminChartBO> result = new ArrayList<>(selectShopSalesMoney.size());
		for (AdminChartBO adminChartBO : redAfterCompareData) {
			if (!shopExpendData.getRedBeforeMoney().isEmpty()) {
				for (AdminChartBO redBeforeData : shopExpendData.getRedBeforeMoney()) {
					if (adminChartBO.getName().equals(redBeforeData.getName())) {
						result.add(
								new AdminChartBO(adminChartBO.getY() - redBeforeData.getY(), adminChartBO.getName()));
					} else {
						result.add(new AdminChartBO(adminChartBO.getY(), adminChartBO.getName()));
					}
				}
			} else {
				result = redAfterCompareData;
			}
		}

		return result;

	}

	/**
	 * 提现、充值流水
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	public KemeanPageAdminBO<List<AdminShopFinanceClearBO>> shopFinanceClearData(
			AdminShopFinanceClearPO adminShopFinanceClearPO, DaikenUser loginer, String referralCode) {
		List<Integer> userId = new ArrayList<>();
		String dateStart = adminShopFinanceClearPO.getDateStart();
		String dateEnd = adminShopFinanceClearPO.getDateEnd();
		if (StringUtils.isNoneBlank(referralCode)) {
			List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "referralCode" }, new Object[] { false, referralCode });
			for (DaikenUser daikenUser : dbUser) {
				userId.add(daikenUser.getId());
			}
		}
		if (StringUtils.isBlank(dateStart)) {
			// 商家查询最近一个月的提现和充值流水记录
			dateStart = KemeanUtilAid.formatDate(KemeanUtilAid.getDateByCalendar(KemeanCalendarFieldEnum.DAY, -30),
					KemeanDateFormatEnum.DATE);
		}
		if (StringUtils.isBlank(dateEnd)) {
			dateEnd = KemeanUtilAid.formatDate(new Date(), KemeanDateFormatEnum.DATE);
		}

		if (loginer != null) {
			userId.add(loginer.getId());
		}

		if (adminShopFinanceClearPO.getUserId() != null) {
			userId.add(adminShopFinanceClearPO.getUserId());
		}
		List<KemeanFinanceClear> dbFinaceClear = kemeanFinanceClearDao.shopFinanceOrderList(
				adminShopFinanceClearPO.getFinanceNo(), adminShopFinanceClearPO.getFinanceStatus(),
				adminShopFinanceClearPO.getType(), dateStart, dateEnd, userId, adminShopFinanceClearPO.getPage(),
				adminShopFinanceClearPO.getLimit());

		Double totalMoney = 0.00;
		List<AdminShopFinanceClearBO> result = new ArrayList<>(dbFinaceClear.size());
		for (KemeanFinanceClear item : dbFinaceClear) {
			AdminShopFinanceClearBO bo = new AdminShopFinanceClearBO();
			totalMoney += item.getSubmitMoney();
			BeanUtils.copyProperties(item, bo);
			bo.setTotalMoney(totalMoney);
			bo.setFinanceStatusStr(KemeanMapData.financeStatus.get(item.getFinanceStatus()));
			bo.setFinanceTypeStr(DaikenMapData.financeType.get(item.getFinanceType()));
			bo.setPayMethoStr(KemeanMapData.payType.get(item.getPayMethod()));
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<AdminShopFinanceClearBO>>(new PageInfo<>(dbFinaceClear).getTotal(), result);

	}

	/**
	 * 店铺流水统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月19日
	 */
	public AdminShopFinanceWaterBO shopFinanceWaterData(AdminShopFinanceOrderPO adminShopFinanceOrderPO,
			DaikenUser loginer, int limit) {
		AdminShopFinanceWaterBO bo = new AdminShopFinanceWaterBO();
		String dateStart = "";
		String dateEnd = "";
		List<Integer> userIds = new ArrayList<>();
		Integer userId = null;
		if (adminShopFinanceOrderPO != null) {
			dateStart = adminShopFinanceOrderPO.getDateStart();
			dateEnd = adminShopFinanceOrderPO.getDateEnd();
			if (adminShopFinanceOrderPO.getShopId() != null) {
				DaikenUser dbUser = daikenUserDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "shopId" },
						new Object[] { false, adminShopFinanceOrderPO.getShopId() });
				if (dbUser != null) {
					userIds.add(dbUser.getId());
					userId = dbUser.getId();
				}
			}
		}

		if (loginer != null) {
			userId = loginer.getId();
			userIds.add(loginer.getId());
			// 店铺支出
			bo = shopSpending(userId, dateStart, dateEnd, limit);
		}
		// 到帐
		Double arrivalMoney = kemeanFinanceClearDao.shopMoneyCount(
				Arrays.asList(DaikenFinanceTypeClearEnum.CASH_SHOP.getType(),
						DaikenFinanceTypeClearEnum.RECHARGE.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), shopId);

		bo.setArrivalMoney(arrivalMoney);
		// 未到帐
		Double notArrivalMoney = kemeanFinanceClearDao.shopMoneyCount(
				Arrays.asList(DaikenFinanceTypeClearEnum.CASH_SHOP.getType(),
						DaikenFinanceTypeClearEnum.RECHARGE.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus(), shopId);
		bo.setNotArrivalMoney(notArrivalMoney);
		// 充值金额
		List<AdminChartBO> shopMoney = kemeanFinanceClearDao.selectShopFinanceSum(
<<<<<<< .mine
				Arrays.asList(DaikenFinanceTypeClearEnum.RECHARGE.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), shopId, dateStart, dateEnd, limit);
=======
				Arrays.asList(DaikenFinanceTypeEnum.RECHARGE.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), userIds, dateStart, dateEnd, limit);
>>>>>>> .r7266
		bo.setRechargeMoney(shopMoney);

		// 提现金额
		shopMoney = kemeanFinanceClearDao.selectShopFinanceSum(
<<<<<<< .mine
				Arrays.asList(DaikenFinanceTypeClearEnum.CASH_SHOP.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), shopId, dateStart, dateEnd, limit);
=======
				Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), userIds, dateStart, dateEnd, limit);
>>>>>>> .r7266
		bo.setWithdrawMoney(shopMoney);

<<<<<<< .mine
=======
		// 到帐
		Double arrivalMoney = kemeanFinanceClearDao.shopMoneyCount(
				Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType(), DaikenFinanceTypeEnum.RECHARGE.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), userId, dateStart, dateEnd);

		bo.setArrivalMoney(arrivalMoney);
		// 未到帐
		Double notArrivalMoney = kemeanFinanceClearDao.shopMoneyCount(
				Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType(), DaikenFinanceTypeEnum.RECHARGE.getType()),
				KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus(), userId, dateStart, dateEnd);
		bo.setNotArrivalMoney(notArrivalMoney);

>>>>>>> .r7266
		return bo;
	}

	/**
	 * 店铺开支
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月17日
	 */

	private AdminShopFinanceWaterBO shopSpending(Integer userId, String dateStart, String dateEnd, int limit) {

		AdminShopFinanceWaterBO bo = new AdminShopFinanceWaterBO();

		DaikenUser dbUser = daikenUserDao.selectById(userId);
		bo.setBalance(dbUser.getBalancePrice());

		// 开销(售前红包、售后红包、代卖佣金)
		// 代卖佣金
		List<DaikenGoodsNew> dbGoodsNew = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId", "purchasing" },
				new Object[] { false, dbUser.getShopId(), true });

		List<Integer> goodsId = new ArrayList<>(dbGoodsNew.size());
		for (DaikenGoodsNew daikenGoodsNew : dbGoodsNew) {
			List<DaikenGoodsNew> dbGoods = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
					new Object[] { false, daikenGoodsNew.getId() });
			for (DaikenGoodsNew item : dbGoods) {
				goodsId.add(item.getId());
			}
		}
		Double overhead = 0.0;
		if (!goodsId.isEmpty()) {
			List<AdminChartBO> purchasingData = daikenGoodsNewDao.selectShopPurchasingPriceSum(goodsId, dateStart,
					dateEnd, limit);
			for (AdminChartBO adminChartBO : purchasingData) {
				overhead += adminChartBO.getY();
			}
		}

		// 售前红包
		List<AdminChartBO> redBeforeData = daikenRedShareGetDao.selectRedShareGetRecord(null,
				Arrays.asList(dbUser.getShopId()), dateStart, dateEnd, limit);
		for (AdminChartBO adminChartBO : redBeforeData) {
			overhead += adminChartBO.getY();
		}

		// 售后红包
		List<AdminChartBO> redAfterData = daikenGoodsNewDao.selectRedAfterGetRecord(
				KemeanOrderEnum.OrderStatusUser.FINISH.getStatus(), Arrays.asList(dbUser.getShopId()), null, dateStart,
				dateEnd, limit);

		for (AdminChartBO adminChartBO : redAfterData) {
			overhead += adminChartBO.getY();
		}
		bo.setOverheadMoney(overhead);
		// 收入
		Double incomeMoney = financeOrderDao.totalAssets(dbUser.getShopId());
		bo.setIncomeMoney(incomeMoney);

		return bo;
	}

	/**
	 * 店铺首页统计图表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月17日
	 */
	public AdminShopHomeChartBO chartData(DaikenUser loginAdminShop) {

		AdminShopHomeChartBO result = new AdminShopHomeChartBO();
		// 商家余额
		result.setBalancePrice(userService.getConsumerOrBusinessBlance(loginAdminShop));
		// 订单数量
		List<AdminChartBO> shopSalesNum = daikenOrderDao.selectShopSalesNum(loginAdminShop.getShopId(), "", "", 30);
		result.setOrderNum(shopSalesNum);
		// 订单产品数量
		List<AdminChartBO> goodsNum = daikenOrderDao.selectGoodsFinishNum(loginAdminShop.getShopId(), "", "", 30);
		result.setGoodsNum(goodsNum);
		// 订单销售额
		List<AdminChartBO> shopSalesMoney = daikenOrderDao.selectShopSalesMoney(loginAdminShop.getShopId(), "", "", 30);
		result.setOrderPrice(shopSalesMoney);
		// 销售利润
		List<AdminChartBO> shopOrderProfit = daikenOrderDao.selectShopOrderProfit(loginAdminShop.getShopId(), 30);
		result.setOrderProfit(shopOrderProfit);
		return result;
	}

	/**
	 * 修改个人信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月17日
	 */
	public KemeanResult<String> updateInfoData(AdminUpdateInfoPO adminUpdateInfoPO, DaikenUser loginAdminShop) {
		loginAdminShop.setNickName(adminUpdateInfoPO.getName());
		if (StringUtils.isNoneBlank(adminUpdateInfoPO.getPassword())) {
			loginAdminShop.setPassword(adminUpdateInfoPO.getPassword().toLowerCase());
		}
		loginAdminShop.setUpdateTime(new Date());
		loginAdminShop.setHeadImg(adminUpdateInfoPO.getHeadImg());
		daikenUserDao.updateByPrimaryKeySelective(loginAdminShop);
		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 商家操作是否营业
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月23日
	 */
	@Transactional
	public KemeanResult<String> workstatusOperate(Boolean workStatus, DaikenUser loginAdminShop) {
		DaikenShop dbShop = daikenShopDao.selectById(loginAdminShop.getShopId());
		dbShop.setWorkStatus(workStatus);
		dbShop.setUpdateTime(new Date());
		daikenShopDao.updateByPrimaryKeySelective(dbShop);
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

}
