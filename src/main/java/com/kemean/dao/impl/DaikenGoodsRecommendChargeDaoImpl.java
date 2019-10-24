package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsRecommendCharge;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsRecommendChargeDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsRecommendChargeMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsRecommendChargeDaoImpl extends DaoSupport<DaikenGoodsRecommendCharge>
		implements DaikenGoodsRecommendChargeDao {

	@Autowired
	private DaikenGoodsRecommendChargeMapper mapper;

	@Override
	protected Mapper<DaikenGoodsRecommendCharge> getMapper() {
		return mapper;
	}

	@Override
	public DaikenGoodsRecommendCharge getRecommendShowCharge(Integer hour) {
		Example example = new Example(DaikenGoodsRecommendCharge.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andLessThan("beginTime", hour);
		criteria.andGreaterThanOrEqualTo("endTime", hour);
		List<DaikenGoodsRecommendCharge> result = mapper.selectByExample(example);
		return result.isEmpty() ? null : result.get(0);
	}
}
