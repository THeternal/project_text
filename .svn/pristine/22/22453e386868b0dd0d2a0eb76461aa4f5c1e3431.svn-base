package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenUserSales;
import com.kemean.dao.DaikenUserSalesDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenUserSalesMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenUserSalesDaoImpl extends DaoSupport<DaikenUserSales> implements DaikenUserSalesDao {

	@Autowired
	DaikenUserSalesMapper mapper;

	@Override
	protected Mapper<DaikenUserSales> getMapper() {
		return mapper;
	}

}
