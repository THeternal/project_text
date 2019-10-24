package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenUserBase;
import com.kemean.dao.DaikenUserBaseDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenUserBaseMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenUserBaseDaoImpl extends DaoSupport<DaikenUserBase> implements DaikenUserBaseDao {

	@Autowired
	private DaikenUserBaseMapper mapper;

	@Override
	protected Mapper<DaikenUserBase> getMapper() {
		return mapper;
	}

}
