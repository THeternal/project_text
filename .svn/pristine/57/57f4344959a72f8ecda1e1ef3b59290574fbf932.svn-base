package com.kemean.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenInvestigateQuestion;
import com.kemean.dao.DaikenInvestigateQuestionDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenInvestigateQuestionMapper;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenInvestigateQuestionDaoImpl extends DaoSupport<DaikenInvestigateQuestion>
		implements DaikenInvestigateQuestionDao {

	@Autowired
	private DaikenInvestigateQuestionMapper mapper;

	@Override
	protected Mapper<DaikenInvestigateQuestion> getMapper() {
		return mapper;
	}

}
