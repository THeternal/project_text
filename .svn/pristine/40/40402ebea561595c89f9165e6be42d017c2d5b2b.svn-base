package com.kemean.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenInvestigateOptions;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenInvestigateOptionsDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenInvestigateOptionsMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenInvestigateOptionsDaoImpl extends DaoSupport<DaikenInvestigateOptions>
		implements DaikenInvestigateOptionsDao {

	@Autowired
	private DaikenInvestigateOptionsMapper mapper;

	@Override
	protected Mapper<DaikenInvestigateOptions> getMapper() {
		return mapper;
	}

	@Override
	public Integer countVotes(Integer investigateId) {
		Integer countVotes = 0;
		Example example = new Example(DaikenInvestigateOptions.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("investigateId", investigateId);
		List<DaikenInvestigateOptions> dbInvestigateOptions = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbInvestigateOptions)) {
			for (DaikenInvestigateOptions daikenInvestigateOptions : dbInvestigateOptions) {
				countVotes += daikenInvestigateOptions.getVotes();
			}
		}
		return countVotes;
	}
}
