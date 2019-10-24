package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenInvestigateQuestionUser;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserSales;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenInvestigateDao;
import com.kemean.dao.DaikenInvestigateOperationDao;
import com.kemean.dao.DaikenInvestigateQuestionUserDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserSalesDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.service.common.CommonService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.platform.AdminIncomeBO;
import com.kemean.vo.bo.admin.platform.AdminPlatformWorkBO;
import com.kemean.vo.bo.admin.platform.AdminPromotionBO;
import com.kemean.vo.bo.admin.platform.AdminQuestionUserBO;
import com.kemean.vo.bo.admin.platform.AdminSalePerformanceBO;
import com.kemean.vo.db.DateAndDateBO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.platform.AdminInvestQuestionUserPO;
import com.kemean.vo.po.admin.platform.AdminPromotionPO;
import com.kemean.vo.po.admin.platform.AdminSalePerformancePO;

@Service
public class AdminPlatformService {

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenInvestigateDao daikenInvestigateDao;

	@Autowired
	private DaikenInvestigateOperationDao investigateOperationDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenUserSalesDao daikenUserSalesDao;

	@Autowired
	private DaikenInvestigateQuestionUserDao daikenInvestigateQuestionUserDao;

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private CommonService commonService;

	/**
	 * 平台总销售额
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	public String platformSaleData(AdminDatePO adminChartPO, Integer limit) {
		List<DateAndDateBO> platformSaleMoneyCount = daikenOrderDao.selectPlatformSaleMoneyCount(
				Arrays.asList(KemeanOrderEnum.OrderStatusUser.FINISH.getStatus(),
						KemeanOrderEnum.OrderStatusUser.PAYED.getStatus(),
						KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus()),
				adminChartPO.getDateStart(), adminChartPO.getDateEnd(), limit);
		List<Object[]> result = new ArrayList<>(platformSaleMoneyCount.size());

		for (DateAndDateBO item : platformSaleMoneyCount) {
			result.add(new Object[] { KemeanUtilAid.parseDate(item.getDbDate(), KemeanDateFormatEnum.DATE).getTime(),
					item.getDbData() });
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 平台四个促销推广报表data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	public KemeanPageAdminBO<List<AdminPromotionBO>> platformPromotionData(AdminPromotionPO adminPromotionPO) {
		List<DaikenGoodsNew> dbGoodsNew = daikenGoodsNewDao.selectGoodsPromotionData(adminPromotionPO.getType(), null,
				adminPromotionPO.getGoodsTitle(), adminPromotionPO.getDateStart(), adminPromotionPO.getDateEnd(),
				adminPromotionPO.getPage(), adminPromotionPO.getLimit());

		List<AdminPromotionBO> result = Lists.transform(dbGoodsNew, new Function<DaikenGoodsNew, AdminPromotionBO>() {

			@Override
			public AdminPromotionBO apply(DaikenGoodsNew input) {
				AdminPromotionBO bo = new AdminPromotionBO();
				BeanUtils.copyProperties(input, bo);
				DaikenShop dbShop = daikenShopDao.selectById(input.getShopId());
				if (dbShop != null) {
					bo.setShopName(dbShop.getShopName());
				}
				bo.setTypeStr(DaikenMapData.salesType.get(input.getSalesType()));
				return bo;
			}
		});
		return new KemeanPageAdminBO<List<AdminPromotionBO>>(new PageInfo<>(dbGoodsNew).getTotal(), result);
	}

	/**
	 * 平台精准投放（问卷调查）用户列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */

	public KemeanPageAdminBO<List<AdminQuestionUserBO>> platformQuestionUserData(
			AdminInvestQuestionUserPO adminInvestQuestionUserPO) {
		List<DaikenInvestigateQuestionUser> dbQuestionUser = daikenInvestigateQuestionUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "isFinished" },
				new Object[] { false, adminInvestQuestionUserPO.getResult() }, "id", false,
				adminInvestQuestionUserPO.getPage(), adminInvestQuestionUserPO.getLimit());

		List<AdminQuestionUserBO> result = Lists.transform(dbQuestionUser,
				new Function<DaikenInvestigateQuestionUser, AdminQuestionUserBO>() {
					@Override
					public AdminQuestionUserBO apply(DaikenInvestigateQuestionUser input) {
						AdminQuestionUserBO bo = new AdminQuestionUserBO();
						BeanUtils.copyProperties(input, bo);
						bo.setIsFinishedStr(BooleanUtils.isTrue(input.getIsFinished()) ? "已完成" : "未完成");
						DaikenUser dbUser = daikenUserDao.selectById(input.getUserId());
						BeanUtils.copyProperties(dbUser, bo);
						return bo;
					}
				});
		return new KemeanPageAdminBO<List<AdminQuestionUserBO>>(new PageInfo<>(dbQuestionUser).getTotal(), result);
	}

	/**
	 * 统计平台管理人员待客户上下架商品、调研数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	public AdminPlatformWorkBO platformWorkCount(AdminDatePO adminChartPO, KemeanAdminUser loginAdminUser, int limit) {
		AdminPlatformWorkBO bo = new AdminPlatformWorkBO();
		// 调研数
		List<AdminChartBO> operateNum = daikenInvestigateDao.selectAdminOperateInvestCount(loginAdminUser.getId(),
				adminChartPO.getDateStart(), adminChartPO.getDateEnd(), limit);
		bo.setInvestNum(operateNum);

		// 商品数
		operateNum = daikenGoodsNewDao.selectAdminOperateGoodsCount(loginAdminUser.getId(), adminChartPO.getDateStart(),
				adminChartPO.getDateEnd(), limit);
		bo.setGoodsNum(operateNum);
		return bo;
	}

	/**
	 * 统计销售对应的客户、店铺
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月14日
	 */
	public KemeanPageAdminBO<List<AdminSalePerformanceBO>> platformSalesPerformanceData(
			AdminSalePerformancePO adminSalePerformancePO) {

		List<DaikenUserSales> dbSalesUser = daikenUserSalesDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "phone" },
				new Object[] { false, adminSalePerformancePO.getPhone() }, "id", false,
				adminSalePerformancePO.getPage(), adminSalePerformancePO.getLimit());

		List<AdminSalePerformanceBO> result = Lists.transform(dbSalesUser,
				new Function<DaikenUserSales, AdminSalePerformanceBO>() {

					@Override
					public AdminSalePerformanceBO apply(DaikenUserSales input) {
						AdminSalePerformanceBO bo = new AdminSalePerformanceBO();
						BeanUtils.copyProperties(input, bo);
						bo.setLevelStr(DaikenMapData.salesLevel.get(input.getLevel()));
						Integer shopCount = daikenUserDao.countByProperties(
								new String[] { KemeanConstant.DATA_DELETED, "referralCode" },
								new Object[] { false, input.getReferralCode() });
						bo.setShopCount(shopCount);
						return bo;
					}

				});

		return new KemeanPageAdminBO<List<AdminSalePerformanceBO>>(new PageInfo<>(dbSalesUser).getTotal(), result);
	}

	/**
	 * 平台收入总额报表（平台入帐减支出报表）
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	public AdminIncomeBO platformIncomeData(AdminDatePO adminChartPO) {

		AdminIncomeBO result = new AdminIncomeBO();
		// 新手token奖励
		Double newGuyToken = Double.valueOf(commonService.getConfig(DaikenConfigEnum.NEW_GUY_TOKEN).getRecord());
		// 参与调研任务token奖励
		Double tokenAward = Double.valueOf(commonService.getConfig(DaikenConfigEnum.TOKEN_AWARD).getRecord());
		// 充值金额
		Double recharge = kemeanFinanceClearDao.selectPlatformInMoney(DaikenFinanceTypeEnum.RECHARGE.getType(),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), adminChartPO.getDateStart(),
				adminChartPO.getDateEnd());
		result.setRecharge(recharge);
		// 佣金
		Double rate = kemeanFinanceClearDao.selectPlatformOutMoney(
				Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType(), DaikenFinanceTypeEnum.C_CASH_USER.getType()),
				true, KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), adminChartPO.getDateStart(),
				adminChartPO.getDateEnd());
		result.setRate(rate);
		// 支出（提现）
		Double shopCash = kemeanFinanceClearDao.selectPlatformOutMoney(
				Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType(), DaikenFinanceTypeEnum.C_CASH_USER.getType()),
				false, KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus(), adminChartPO.getDateStart(),
				adminChartPO.getDateEnd());
		result.setWithdraw(shopCash);
		Integer tokenNumber = investigateOperationDao.selectUserGetTokenNum(adminChartPO.getDateStart(),
				adminChartPO.getDateEnd());

		Integer userNum = daikenUserDao.countByProperties(new String[] { "userType", KemeanConstant.DATA_DELETED },
				new Object[] { DaikenUserTypeEnum.CONSUMER.getType(), false });

		// 赠送的token
		Double tokenMoney = tokenAward * tokenNumber + newGuyToken * userNum;

		result.setTokenMoney(tokenMoney);
		// 毛利（计算充值）
		result.setGrossProfit(recharge);
		// 净利 = 充值- 提现等（赠送token等）
		Double netIncome = recharge - (tokenMoney + shopCash);
		result.setNetIncome(netIncome);

		// 现金总额(收入-支出)
		Double cashMoney = (recharge + rate) - (shopCash + tokenMoney);
		result.setCashMoney(cashMoney);
		// 应付商家（提现待处理的）
		Double getShopMoney = kemeanFinanceClearDao.selectPlatformOutMoney(
				Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType()), true,
				KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus(), null, null);
		result.setGetShopMoney(getShopMoney);

		return result;
	}

}
