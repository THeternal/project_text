package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenInvestigateQuestionUser;
import com.kemean.dao.DaikenInvestigateQuestionUserDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenInvestigateQuestionUserMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenInvestigateQuestionUserDaoImpl extends DaoSupport<DaikenInvestigateQuestionUser>
		implements DaikenInvestigateQuestionUserDao {

	@Autowired
	private DaikenInvestigateQuestionUserMapper mapper;

	@Override
	protected Mapper<DaikenInvestigateQuestionUser> getMapper() {
		return mapper;
	}

}
