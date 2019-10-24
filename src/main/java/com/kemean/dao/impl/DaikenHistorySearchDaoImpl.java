package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenHistorySearch;
import com.kemean.dao.DaikenHistorySearchDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenHistorySearchMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenHistorySearchDaoImpl extends DaoSupport<DaikenHistorySearch> implements DaikenHistorySearchDao {

	@Autowired
	private DaikenHistorySearchMapper mapper;

	@Override
	protected Mapper<DaikenHistorySearch> getMapper() {
		return mapper;
	}

}
