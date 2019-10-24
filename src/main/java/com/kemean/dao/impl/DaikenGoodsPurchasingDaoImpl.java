package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsPurchasing;
import com.kemean.dao.DaikenGoodsPurchasingDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsPurchasingMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenGoodsPurchasingDaoImpl extends DaoSupport<DaikenGoodsPurchasing>
		implements DaikenGoodsPurchasingDao {

	@Autowired
	private DaikenGoodsPurchasingMapper mapper;

	@Override
	protected Mapper<DaikenGoodsPurchasing> getMapper() {
		return mapper;
	}
}
