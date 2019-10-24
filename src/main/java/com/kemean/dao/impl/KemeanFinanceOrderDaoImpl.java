package com.kemean.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.KemeanFinanceOrderDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.KemeanFinanceOrderMapper;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class KemeanFinanceOrderDaoImpl extends DaoSupport<KemeanFinanceOrder> implements KemeanFinanceOrderDao {
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
		criteria.andEqualTo("isNewGoods", true);
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
		criteria.andEqualTo("isNewGoods", true);
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
	public Double waitMoney(Integer shopId, Date cycleDate) {
		Double waitMoney = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIsNull("financeNo");
		criteria.andEqualTo("businessId", shopId);
		criteria.andEqualTo("isNewGoods", true);
		criteria.andLessThan("createTime", cycleDate);
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				waitMoney += dbFinanceOrder.getMoney();
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
	public Double hellpGoodsSumMoneyPrice(Integer userId) {
		Double hellpGoodsSumMoney = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("financeType", DaikenFinanceTypeEnum.HELLP_SELL_GOODS.getType());
		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				hellpGoodsSumMoney += dbFinanceOrder.getMoney();
			}
		}
		return hellpGoodsSumMoney;
	}

	@Override
	public Double totalAssets(Integer shopId) {
		Double waitMoney = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if(shopId==null) {
			criteria.andEqualTo("businessId", shopId);
		}
		criteria.andEqualTo("isNewGoods", true);
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
		return mapper.selectUserOrderCount(financeTypes, dateStart, dateEnd, limit);
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
	public Double consumerCountIncome(Integer userId, List<Integer> financeTypes, boolean flag) {
		Double money = 0.0;
		Example example = new Example(KemeanFinanceOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("financeType", financeTypes);
		if (!flag) {
			// 余额
			criteria.andIsNull("financeNo");
		}

		if (flag) {
			// 已提现
			criteria.andIsNotNull("financeNo");
		}

		List<KemeanFinanceOrder> dbFinanceOrders = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceOrders)) {
			for (KemeanFinanceOrder dbFinanceOrder : dbFinanceOrders) {
				money += dbFinanceOrder.getMoney();
			}
		}
		return money;
	}

}
