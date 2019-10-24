package com.kemean.dao.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.mapper.DaikenOrderClearMapper;
import com.kemean.mapper.KemeanFinanceClearMapper;
import com.kemean.mapper.KemeanFinanceOrderMapper;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class KemeanFinanceOrderDaikenDaoImpl extends KemeanFinanceOrderDaoImpl implements KemeanFinanceOrderDaikenDao {

	@Autowired
	private DaikenOrderClearMapper daikenOrderClearMapper;

	@Autowired
	private KemeanFinanceClearMapper kemeanFinanceClearMapper;

	@Autowired
	private KemeanFinanceOrderMapper mapper;

	@Override
	protected Mapper<KemeanFinanceOrder> getMapper() {
		return mapper;
	}

	@Override
	public List<KemeanFinanceOrder> wallet(Integer userId, Date plusDay, Integer pageNo, Integer pageSize) {
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("userId", userId);
		criteria.andGreaterThanOrEqualTo("createTime", plusDay);
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<KemeanFinanceOrder> financeOrderList(Date plusDay, Integer shopId, Integer pageNo, Integer pageSize) {
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("businessId", shopId);
		criteria.andGreaterThanOrEqualTo("createTime", plusDay);
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public Double freezeMoney(Integer shopId, Date cycleDate) {
		Double freezeMoney = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIsNull("financeNo");
		criteria.andEqualTo("businessId", shopId);
		criteria.andGreaterThanOrEqualTo("createTime", cycleDate);
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				freezeMoney += dbFinanceOrder.getMoney();
			}
		}
		return freezeMoney;
	}

	@Override
	public Double getShopMoney(Integer shopId, Integer userId, Date cycleDate) {
		Double waitMoney = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		// 卖货有个结算周期，充值不算
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.B_SELL_GOODS.getType()));
		criteria.andEqualTo("businessId", shopId);
		criteria.andLessThan("financeData", DaikenUtil.formatDate(cycleDate, KemeanDateFormatEnum.DATE));
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				waitMoney += dbFinanceOrder.getMoney();
			}
		}
		// 充值金额
		Example financeClear = new Example(KemeanFinanceClear.class);
		Criteria financeCriteria = financeClear.createCriteria();
		financeCriteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		financeCriteria.andEqualTo("submitAimsId", userId);
		financeCriteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.B_RECHARGE_MONEY.getType(),
				DaikenFinanceTypeEnum.RECHARGE.getType()));
		List<KemeanFinanceClear> dbRechargeMoneys = kemeanFinanceClearMapper.selectByExample(financeClear);
		if (CollectionUtils.isNotEmpty(dbRechargeMoneys)) {
			for (KemeanFinanceClear dbRechargeMoney : dbRechargeMoneys) {
				waitMoney += dbRechargeMoney.getDealMoney();
			}
		}
		return waitMoney;
	}

	@Override
	public Double currMonthPrice(Integer userId, List<Integer> financeTypes, Boolean flag) {
		Double currMonthTaskPrice = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andIn("financeType", financeTypes);
		if (flag) {
			Date now = new Date();
			criteria.andGreaterThanOrEqualTo("createTime", DaikenUtil.currentMonthBegin(now));
			criteria.andLessThanOrEqualTo("createTime", DaikenUtil.currentMonthLast(now));
		}
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				currMonthTaskPrice += dbFinanceOrder.getMoney();
			}
		}
		return currMonthTaskPrice;
	}

	@Override
	public Double totalAssets(Integer shopId) {
		Double waitMoney = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("businessId", shopId);
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				waitMoney += dbFinanceOrder.getMoney();
			}
		}
		return waitMoney;
	}

	@Override
	public List<AdminChartBO> selectUserOrderCount(List<Integer> financeTypes, String dateStart, String dateEnd,
			Integer limit) {
		return daikenOrderClearMapper.selectUserOrderCount(financeTypes, dateStart, dateEnd, limit);
	}

	@Override
	public List<KemeanFinanceOrder> userFinanceOrderList(String orderNo, String userName, String dateStart,
			String dateEnd, Integer pageNo, Integer pageSize) {
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if (StringUtils.isNoneBlank(orderNo)) {
			criteria.andEqualTo("orderNo", orderNo);
		}
		if (StringUtils.isNoneBlank(userName)) {
			criteria.andEqualTo("name", userName);
		}

		criteria.andGreaterThan("userId", 0);

		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("financeData", dateStart);
		}

		if (StringUtils.isNoneBlank(dateEnd)) {
			criteria.andLessThanOrEqualTo("financeData", dateEnd);
		}

		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public Double getConsumerMoney(Integer userId, Boolean flag) {
		Double money = 0.0;
		Date now = new Date();
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("userId", userId);
		criteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(),
				DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(),
				DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType(), DaikenFinanceTypeEnum.C_SELL_GOODS.getType(),
				DaikenFinanceTypeEnum.C_BEFOR_RED_INCOME.getType(), DaikenFinanceTypeEnum.C_AFTER_RED_INCOME.getType(),
				DaikenFinanceTypeEnum.C_HELLP_SELL_GOODS.getType()));
		Date plusDay = KemeanUtilAid.getDateByCalendar(now, KemeanCalendarFieldEnum.DAY, -7);
		// 冻结
		if (flag) {
			criteria.andGreaterThanOrEqualTo("financeData", DaikenUtil.formatDate(plusDay, KemeanDateFormatEnum.DATE));
		}
		// 未冻结 + 充值金额
		if (!flag) {
			criteria.andLessThan("financeData", DaikenUtil.formatDate(plusDay, KemeanDateFormatEnum.DATE));
			Example financeClear = new Example(KemeanFinanceClear.class);
			Criteria financeClearCriteria = financeClear.createCriteria();
			financeClearCriteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
			financeClearCriteria.andEqualTo("submitAimsId", userId);
			financeClearCriteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.RECHARGE.getType()));
			List<KemeanFinanceClear> dbChargeMoenys = kemeanFinanceClearMapper.selectByExample(financeClear);
			if (CollectionUtils.isNotEmpty(dbChargeMoenys)) {
				for (KemeanFinanceClear dbChargeMoeny : dbChargeMoenys) {
					money += dbChargeMoeny.getDealMoney();
				}
			}
		}
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
			money += dbFinanceOrder.getMoney();
		}
		return money;
	}

	@Override
	public List<KemeanFinanceOrder> getPlatformChargeByUser(Integer userId) {
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("financeType", DaikenFinanceTypeEnum.CHARGE.getType());
		return mapper.selectByExample(example);
	}

	@Override
	public List<KemeanFinanceOrder> getBusinessAdvertisingCostsByShop(Integer shopId) {
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("businessId", shopId);
		criteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.B_LUNBO_ADVERTISING.getType(),
				DaikenFinanceTypeEnum.B_RECOMMEND_ADVERTISING.getType(),
				DaikenFinanceTypeEnum.B_RECOMMEND_TREASURE.getType(), DaikenFinanceTypeEnum.B_SHOP_PUSH_GOODS.getType(),
				DaikenFinanceTypeEnum.B_AFTER_RED_INCOME.getType()));
		return mapper.selectByExample(example);
	}

}
