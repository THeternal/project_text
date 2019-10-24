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
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.mapper.DaikenOrderClearMapper;
import com.kemean.mapper.KemeanFinanceClearMapper;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class KemeanFinanceClearDaikenDaoImpl extends KemeanFinanceClearDaoImpl implements KemeanFinanceClearDaikenDao {

	@Autowired
	private DaikenOrderClearMapper daikenOrderClearMapper;

	@Autowired
	private KemeanFinanceClearMapper mapper;

	@Override
	protected Mapper<KemeanFinanceClear> getMapper() {
		return mapper;
	}

	@Override
	public List<KemeanFinanceClear> closeAccountList(Date plusDay, Integer shopId, Integer pageNo, Integer pageSize) {
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("submitAimsId", shopId);
		criteria.andGreaterThanOrEqualTo("createTime", plusDay);
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<KemeanFinanceClear> shopFinanceOrderList(String financeNo, Integer financeStatus, Integer type,
			String dateStart, String dateEnd, List<Integer> userId, Integer pageNo, Integer pageSize) {
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if (!userId.isEmpty()) {
			criteria.andIn("submitAimsId", userId);

		}
		if (StringUtils.isNoneBlank(financeNo)) {
			criteria.andLike("financeNo", "%" + financeNo + "%");
		}
		if (type != null) {
			// 充值
			if (1201 == type) {
				criteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.RECHARGE.getType(),
						DaikenFinanceTypeEnum.B_RECHARGE_MONEY.getType()));
			}

			if (1101 == type) {
				// 提现
				criteria.andIn("financeType", Arrays.asList(DaikenFinanceTypeEnum.B_CASH_SHOP.getType()));
			}
		}
		if (financeStatus != null) {
			criteria.andEqualTo("financeStatus", financeStatus);
		}

		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("financeData", dateStart);
		}
		if (StringUtils.isNoneBlank(dateEnd)) {
			criteria.andLessThanOrEqualTo("financeData", dateEnd);
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public Double shopMoneyCount(List<Integer> financeType, Integer status, Integer submitAimsId, String dateStart,
			String dateEnd) {
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("financeType", financeType);
		criteria.andEqualTo("submitAimsId", submitAimsId);
		criteria.andEqualTo("financeStatus", status);
		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("createTime", dateStart);
		}

		if (StringUtils.isNoneBlank(dateEnd)) {
			criteria.andLessThanOrEqualTo("createTime", dateEnd);
		}

		Double shopMoney = 0.0;
		List<KemeanFinanceClear> dbData = mapper.selectByExample(example);
		for (KemeanFinanceClear kemeanFinanceClear : dbData) {
			shopMoney += kemeanFinanceClear.getDealMoney();
		}

		return shopMoney;
	}

	@Override
	public List<AdminChartBO> selectShopFinanceSum(List<Integer> financeType, Integer financeStatus,
			List<Integer> submitAimsId, String dateStart, String dateEnd, Integer limit) {
		return daikenOrderClearMapper.selectShopFinanceSum(financeType, submitAimsId, financeStatus, dateStart, dateEnd,
				limit);
	}

	@Override
	public Double selectPlatformInMoney(Integer financeType, Integer financeStatus, String dateStart, String dateEnd) {
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("financeType", financeType);
		criteria.andEqualTo("financeStatus", financeStatus);

		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("createTime", dateStart);
		}
		if (StringUtils.isNoneBlank(dateEnd)) {
			criteria.andLessThanOrEqualTo("createTime", dateEnd);
		}
		List<KemeanFinanceClear> dbExample = mapper.selectByExample(example);
		Double inMoney = 0.0;

		for (KemeanFinanceClear kemeanFinanceClear : dbExample) {
			inMoney += kemeanFinanceClear.getDealMoney();

		}

		return inMoney;
	}

	@Override
	public Double selectPlatformOutMoney(List<Integer> financeType, Boolean rate, Integer financeStatus,
			String dateStart, String dateEnd) {
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("financeType", financeType);
		criteria.andEqualTo("financeStatus", financeStatus);

		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("createTime", dateStart);
		}
		if (StringUtils.isNoneBlank(dateEnd)) {
			criteria.andLessThanOrEqualTo("createTime", dateEnd);
		}
		List<KemeanFinanceClear> dbExample = mapper.selectByExample(example);
		Double outMoney = 0.0;

		for (KemeanFinanceClear kemeanFinanceClear : dbExample) {
			if (rate) {
				outMoney += kemeanFinanceClear.getSubmitMoney() - kemeanFinanceClear.getDealMoney();
			} else {
				outMoney += kemeanFinanceClear.getDealMoney();

			}
		}

		return outMoney;
	}

	@Override
	public Double getCunsumerFinishMoney(Integer userId) {
		Double finishMoney = 0.0;
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("financeType", DaikenFinanceTypeEnum.C_CASH_USER.getType());
		criteria.andEqualTo("submitAimsId", userId);
		criteria.andIn("financeStatus", Arrays.asList(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus(),
				KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus()));
		List<KemeanFinanceClear> dbFinanceClears = mapper.selectByExample(example);
		if (CollectionUtils.isEmpty(dbFinanceClears)) {
			return finishMoney;
		}
		for (KemeanFinanceClear dbFinanceClear : dbFinanceClears) {
			finishMoney += dbFinanceClear.getSubmitMoney();
		}
		return finishMoney;
	}

	@Override
	public List<KemeanFinanceClear> clearOrderList(Integer userId, Date plusDay) {
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("financeType", DaikenFinanceTypeEnum.C_CASH_USER.getType());
		criteria.andEqualTo("submitAimsId", userId);
		criteria.andGreaterThanOrEqualTo("financeData", DaikenUtil.formatDate(plusDay, KemeanDateFormatEnum.DATE));
		return mapper.selectByExample(example);
	}

	@Override
	public Double consumerClearMoeny(Integer userId, Integer financeStatus) {
		Double money = 0.0;
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("financeType", DaikenFinanceTypeEnum.C_CASH_USER.getType());
		criteria.andEqualTo("submitAimsId", userId);
		criteria.andEqualTo("financeStatus", financeStatus);
		List<KemeanFinanceClear> dbFinanceClears = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceClears)) {
			for (KemeanFinanceClear dbFinanceClear : dbFinanceClears) {
				money += dbFinanceClear.getSubmitMoney();
			}
		}
		return money;
	}

	@Override
	public Double shopClearMoney(Integer shopId, Integer financeStatus) {
		Double money = 0.0;
		Example example = new Example(KemeanFinanceClear.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("financeType", DaikenFinanceTypeEnum.B_CASH_SHOP.getType());
		criteria.andEqualTo("submitAimsId", shopId);
		criteria.andEqualTo("financeStatus", financeStatus);
		List<KemeanFinanceClear> dbFinanceClears = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbFinanceClears)) {
			for (KemeanFinanceClear dbFinanceClear : dbFinanceClears) {
				money += dbFinanceClear.getSubmitMoney();
			}
		}
		return money;
	}

}
