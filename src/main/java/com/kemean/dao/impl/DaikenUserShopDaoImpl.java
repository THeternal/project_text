package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenUserShop;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenUserShopMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenUserShopDaoImpl extends DaoSupport<DaikenUserShop> implements DaikenUserShopDao {

	@Autowired
	private DaikenUserShopMapper mapper;

	@Override
	protected Mapper<DaikenUserShop> getMapper() {
		return mapper;
	}

}
