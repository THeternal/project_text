package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsBaseType;
import com.kemean.dao.DaikenGoodsBaseTypeDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsBaseTypeMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenGoodsBaseTypeDaoImpl extends DaoSupport<DaikenGoodsBaseType> implements DaikenGoodsBaseTypeDao {

	@Autowired
	private DaikenGoodsBaseTypeMapper mapper;

	@Override
	protected Mapper<DaikenGoodsBaseType> getMapper() {
		return mapper;
	}

}
