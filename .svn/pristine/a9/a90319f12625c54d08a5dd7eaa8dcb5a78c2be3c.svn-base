package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsRecommendUser;
import com.kemean.dao.DaikenGoodsRecommendUserDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsRecommendUserMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenGoodsRecommendUserDaoImpl extends DaoSupport<DaikenGoodsRecommendUser>
		implements DaikenGoodsRecommendUserDao {

	@Autowired
	DaikenGoodsRecommendUserMapper mapper;

	@Override
	protected Mapper<DaikenGoodsRecommendUser> getMapper() {
		return mapper;
	}

	@Override
	public Integer saveUserList(List<DaikenGoodsRecommendUser> userList) {
		return mapper.insertList(userList);
	}

}
