package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsHotCharge;
import com.kemean.dao.DaikenGoodsHotChargeDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsHotChargeMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenGoodsHotChargeDaoImpl extends DaoSupport<DaikenGoodsHotCharge> implements DaikenGoodsHotChargeDao {

	@Autowired
	private DaikenGoodsHotChargeMapper mapper;

	@Override
	protected Mapper<DaikenGoodsHotCharge> getMapper() {
		return mapper;
	}

}
