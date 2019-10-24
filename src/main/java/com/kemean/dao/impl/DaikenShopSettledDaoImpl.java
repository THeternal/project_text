package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenShopSettled;
import com.kemean.dao.DaikenShopSettledDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenShopSettledMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenShopSettledDaoImpl extends DaoSupport<DaikenShopSettled> implements DaikenShopSettledDao {

	@Autowired
	private DaikenShopSettledMapper mapper;

	@Override
	protected Mapper<DaikenShopSettled> getMapper() {
		return mapper;
	}
}
