package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserBase;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenUserBaseDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.service.common.CommonService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.bo.admin.user.AdminUserChartBO;
import com.kemean.vo.bo.admin.user.AdminUserFinanceOrderBO;
import com.kemean.vo.bo.admin.user.AdminUserInfoBO;
import com.kemean.vo.bo.admin.user.AdminUserTokenBO;
import com.kemean.vo.db.DateAndDateBO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.investigate.AdminInvestChartPO;
import com.kemean.vo.po.admin.user.AdminRechargePO;
import com.kemean.vo.po.admin.user.AdminUserFinanceOrderPO;
import com.kemean.vo.po.admin.user.AdminUserListPO;
import com.kemean.vo.po.admin.user.AdminUserOrderListPO;
import com.kemean.vo.po.admin.user.AdminUserStatusPO;

@Service
public class AdminUserService {

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenUserBaseDao daikenUserBaseDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private KemeanFinanceOrderDaikenDao financeOrderDao;

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private CommonService commonService;

	/**
	 * 用户信息列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	public KemeanPageAdminBO<List<AdminUserBO>> userPageData(AdminUserListPO adminUserListPO, String referralCode) {
		List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "phone", "userType", "uid", "referralCode" },
				new Object[] { false, adminUserListPO.getPhone(), adminUserListPO.getUserType(),
						adminUserListPO.getUid(), referralCode },
				"id", false, adminUserListPO.getPage(), adminUserListPO.getLimit());

		List<AdminUserBO> result = Lists.transform(dbUser, new Function<DaikenUser, AdminUserBO>() {

			@Override
			public AdminUserBO apply(DaikenUser input) {
				AdminUserBO bo = new AdminUserBO();
				BeanUtils.copyProperties(input, bo);
				bo.setUserTypeStr(DaikenMapData.userType.get(input.getUserType()));
				return bo;
			}
		});
		return new KemeanPageAdminBO<List<AdminUserBO>>(new PageInfo<>(dbUser).getTotal(), result);
	}

	/**
	 * 操作用户 状态 正常 /禁用
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@Transactional
	public KemeanResult<String> userStatusOperate(AdminUserStatusPO adminUserStatusPO) {
		DaikenUser dbUser = daikenUserDao.selectById(adminUserStatusPO.getObjId());

		if (adminUserStatusPO.getStatus()) {
			dbUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.NORMAL.getStatus());
			dbUser.setCause("");
		} else {
			dbUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.DISABLED.getStatus());
			dbUser.setCause(adminUserStatusPO.getCause());
		}

		dbUser.setUpdateTime(new Date());
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 修改密码
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月16日
	 */
	// TODO 删除缓存用户
	public void userResetPassword(Integer objId, String password) {
		DaikenUser dbUser = daikenUserDao.selectById(objId);
		dbUser.setUpdateTime(new Date());
		dbUser.setPassword(password);
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
	}

	/**
	 * 用户详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	public AdminUserInfoBO userInfoData(Integer objId) {
		AdminUserInfoBO bo = new AdminUserInfoBO();
		DaikenUser dbUser = daikenUserDao.selectById(objId);
		BeanUtils.copyProperties(dbUser, bo);
		bo.setUserTypeStr(DaikenMapData.userType.get(dbUser.getUserType()));
		DaikenUserBase dbUserBase = daikenUserBaseDao.selectById(dbUser.getProfession());

		if (dbUserBase != null) {
			bo.setProfessionStr(dbUserBase.getContent());
		}

		if (StringUtils.isNoneBlank(dbUser.getHobbiesInterests())) {
			List<Integer> parseHobbies = JSONArray.parseArray(dbUser.getHobbiesInterests(), Integer.class);
			List<String> hobbiesList = new ArrayList<>(parseHobbies.size());
			for (Integer item : parseHobbies) {
				hobbiesList.add(daikenUserBaseDao.selectById(item).getContent());
			}
			bo.setHobbiesInterests(hobbiesList);
		}

		return bo;
	}

	/**
	 * 用户统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	public AdminUserChartBO userChart() {
		AdminUserChartBO bo = new AdminUserChartBO();
		// 消费者统计
		Integer consumerCount = daikenUserDao.countByProperties(
				new String[] { "userType", KemeanConstant.DATA_DELETED },
				new Object[] { DaikenUserTypeEnum.CONSUMER.getType(), false });
		// 商家统计
		Integer shopCount = daikenUserDao.countByProperties(new String[] { "userType", KemeanConstant.DATA_DELETED },
				new Object[] { DaikenUserTypeEnum.BUSINESS.getType(), false })
				+ daikenUserDao.countByProperties(new String[] { "userType", KemeanConstant.DATA_DELETED },
						new Object[] { DaikenUserTypeEnum.BUSINESS_SON.getType(), false });
		// 平台总用户
		bo.setPlatformSumUser(consumerCount + shopCount);
		// 性别男
		Integer userSexManCount = daikenUserDao.countByProperties(
				new String[] { "sexMan", KemeanConstant.DATA_DELETED, }, new Object[] { true, false });
		// 性别女
		Integer userSexGirlCount = daikenUserDao.countByProperties(
				new String[] { "sexMan", KemeanConstant.DATA_DELETED }, new Object[] { false, false });

		// 年龄(未满18岁)
		Integer notAdult = daikenUserDao.selectCountAge(0, 17);
		// 年龄(18岁~23岁)
		Integer eighteenBetweenTwentyThree = daikenUserDao.selectCountAge(18, 23);
		// 年龄(24岁~30岁)
		Integer twentyFourBetweenThirty = daikenUserDao.selectCountAge(24, 30);
		// 年龄(31岁~37岁)
		Integer thirtyOneBetweenThirtySeven = daikenUserDao.selectCountAge(31, 37);
		// 年龄(38岁~45岁)
		Integer thirtyEightBetweenFortyFive = daikenUserDao.selectCountAge(38, 45);
		bo.setNotAdult(notAdult);
		bo.setEighteenBetweenTwentyThree(eighteenBetweenTwentyThree);
		bo.setThirtyEightBetweenFortyFive(thirtyEightBetweenFortyFive);
		bo.setTwentyFourBetweenThirty(twentyFourBetweenThirty);
		bo.setThirtyOneBetweenThirtySeven(thirtyOneBetweenThirtySeven);
		bo.setUserGirlDate(userSexGirlCount);
		bo.setUserManDate(userSexManCount);
		bo.setConsumerNum(consumerCount);
		bo.setShopNumData(shopCount);
		return bo;
	}

	/**
	 * 用户消费统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	public List<AdminChartBO> userConsumChart(AdminInvestChartPO adminChartsPO, int limit) {
		return daikenOrderDao.selectUserSaleMoney(Arrays.asList(KemeanOrderEnum.OrderStatusUser.SHIP.getStatus(),
				KemeanOrderEnum.OrderStatusUser.WAIT_APPRAISAL.getStatus(),
				KemeanOrderEnum.OrderStatusUser.PAYED.getStatus(), KemeanOrderEnum.OrderStatusUser.FINISH.getStatus()),
				adminChartsPO.getDateStart(), adminChartsPO.getDateEnd(), limit);

	}

	/**
	 * 用户下单量分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	public String userOrderChart(AdminInvestChartPO adminChartsPO, int limit) {

		// 用户日下单量
		List<DateAndDateBO> dbOrderChart = daikenOrderDao.selectUserOrderNum(adminChartsPO.getDateStart(),
				adminChartsPO.getDateStart(), limit);
		List<Object[]> orderChartData = new ArrayList<>(dbOrderChart.size());
		for (DateAndDateBO item : dbOrderChart) {
			orderChartData.add(new Object[] {
					KemeanUtilAid.parseDate(item.getDbDate(), KemeanDateFormatEnum.DATE).getTime(), item.getDbData() });
		}

		return JSONObject.toJSONString(orderChartData);
	}

	/**
	 * 用户收入统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */
	public List<AdminChartBO> userInMoneyChart(AdminUserFinanceOrderPO adminUserFinanceOrderPO, int limit) {
		List<Integer> financeTypes = new ArrayList<>();
		if (adminUserFinanceOrderPO.getType() != null) {
			financeTypes.add(adminUserFinanceOrderPO.getType());
		} else {
			financeTypes = Arrays.asList(DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType(),
					DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(),
					DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType(),
					DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(),
					DaikenFinanceTypeEnum.C_AFTER_RED_INCOME.getType(), DaikenFinanceTypeEnum.C_SELL_GOODS.getType());
		}
		String dateStart = null;
		String dateEnd = null;
		if (adminUserFinanceOrderPO != null) {
			dateStart = adminUserFinanceOrderPO.getDateStart();
			dateEnd = adminUserFinanceOrderPO.getDateEnd();
		}
		return financeOrderDao.selectUserOrderCount(financeTypes, dateStart, dateEnd, limit);
	}

	/**
	 * 充值
	 * 
	 * @author huangyujian
	 * @date 2018年4月27日
	 */
	@Transactional
	public KemeanResult<String> recharge(AdminRechargePO adminRechargePO, KemeanAdminUser kemeanAdminUser) {
		String password = commonService.getConfig(DaikenConfigEnum.PASSWORD_FINANCE).getRecord();
		if (!password.equalsIgnoreCase(adminRechargePO.getPassword())) {
			return new KemeanResult<>(false, "财务密码错误");
		}
		Date now = new Date();
		DaikenUser dbUser = daikenUserDao.selectById(adminRechargePO.getObjId());
		dbUser.setUpdateTime(now);
		dbUser.setBalancePrice(dbUser.getBalancePrice() + adminRechargePO.getMoney());
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		commonService.deleteCacheUser(dbUser);
		KemeanFinanceClear newFinanceClear = new KemeanFinanceClear();
		newFinanceClear.setCreateTime(now);
		newFinanceClear.setFinanceData(now);
		newFinanceClear.setFinanceMonth(KemeanUtilAid.formatDate(now, KemeanDateFormatEnum.DATE));
		newFinanceClear.setUpdateTime(now);
		newFinanceClear.setFinanceNo(KemeanUtilAid.getOrderNo(now));
		newFinanceClear.setFinanceStatus(KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus());
		newFinanceClear.setDealMoney(adminRechargePO.getMoney());
		newFinanceClear.setSubmitAimsId(dbUser.getId());
		newFinanceClear.setSubmitAimsName(dbUser.getNickName());
		newFinanceClear.setSubmitMoney(adminRechargePO.getMoney());
		newFinanceClear.setFinanceType(DaikenFinanceTypeEnum.RECHARGE.getType());
		newFinanceClear.setAdminId(kemeanAdminUser.getId());
		newFinanceClear.setAdminName(kemeanAdminUser.getName());
		kemeanFinanceClearDao.saveSelective(newFinanceClear);

		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 用户流水账
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月20日
	 */
	public KemeanPageAdminBO<List<AdminUserFinanceOrderBO>> userOrderListData(
			AdminUserOrderListPO adminUserOrderListPO) {
		List<KemeanFinanceOrder> dbOrder = financeOrderDao.userFinanceOrderList(adminUserOrderListPO.getOrderNo(),
				adminUserOrderListPO.getName(), adminUserOrderListPO.getDateStart(), adminUserOrderListPO.getDateEnd(),
				adminUserOrderListPO.getPage(), adminUserOrderListPO.getLimit());

		List<AdminUserFinanceOrderBO> result = Lists.transform(dbOrder,
				new Function<KemeanFinanceOrder, AdminUserFinanceOrderBO>() {

					@Override
					public AdminUserFinanceOrderBO apply(KemeanFinanceOrder input) {
						AdminUserFinanceOrderBO bo = new AdminUserFinanceOrderBO();
						BeanUtils.copyProperties(input, bo);
						bo.setFinanceTypeStr(DaikenMapData.financeType.get(input.getFinanceType()));
						return bo;
					}

				});

		return new KemeanPageAdminBO<List<AdminUserFinanceOrderBO>>(new PageInfo<>(dbOrder).getTotal(), result);
	}

	/**
	 * 用户token统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	public AdminUserTokenBO userTokenData(AdminDatePO adminDatePO, int limit) {
		AdminUserTokenBO bo = new AdminUserTokenBO();
		// 消费者的token统计
		List<AdminChartBO> dbUserToken = daikenUserDao.selectUserToken(DaikenUserTypeEnum.CONSUMER.getType(),
				adminDatePO.getDateStart(), adminDatePO.getDateEnd(), limit);
		bo.setConsumerToken(dbUserToken);
		// 商家者的token统计
		dbUserToken = daikenUserDao.selectUserToken(DaikenUserTypeEnum.BUSINESS.getType(), adminDatePO.getDateStart(),
				adminDatePO.getDateEnd(), limit);
		bo.setBusinessToken(dbUserToken);
		return bo;
	}

}
