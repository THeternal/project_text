package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenShopReply;
import com.kemean.dao.DaikenShopReplyDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenShopReplyMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenShopReplyDaoImpl extends DaoSupport<DaikenShopReply> implements DaikenShopReplyDao {

	@Autowired
	private DaikenShopReplyMapper mapper;

	@Override
	protected Mapper<DaikenShopReply> getMapper() {
		return mapper;
	}
}
