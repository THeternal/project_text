package com.kemean.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenInvestigateOperation;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenInvestigateOperationDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenInvestigateOperationMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenInvestigateOperationDaoImpl extends DaoSupport<DaikenInvestigateOperation>
		implements DaikenInvestigateOperationDao {

	@Autowired
	private DaikenInvestigateOperationMapper mapper;

	@Override
	protected Mapper<DaikenInvestigateOperation> getMapper() {
		return mapper;
	}

	@Override
	public Integer selectUserGetTokenNum(String dateStart, String dateEnd) {
		return mapper.selectUserGetTokenNum(dateStart, dateEnd);
	}

	@Override
	public List<AdminChartBO> selectJoinUserNum(List<Integer> investId, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectJoinUserNum(investId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectPostAndLikeJoinUserNum(Integer type, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectPostAndLikeJoinUserNum(type, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectQuestionJoinUserNum(Integer type, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectQuestionJoinUserNum(type, dateStart, dateEnd, limit);
	}

	@Override
	public List<Integer> selectJoinUserId(Integer type, List<Integer> investigateId, Integer pageNo, Integer pageSize) {
		return mapper.selectJoinUserId(type, investigateId, pageNo, pageSize);
	}

	@Override
	public List<DaikenInvestigateOperation> selectInvestOperation(List<Integer> investigateId) {
		Example example = new Example(DaikenInvestigateOperation.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if (!investigateId.isEmpty()) {
			createCriteria.andIn("investigateId", investigateId);
		}
		createCriteria.andIn("type", Arrays.asList(DaikenInvestigateTypeEnum.POST_LIKE.getType(),
				DaikenInvestigateTypeEnum.POST_VOTE.getType(), DaikenInvestigateTypeEnum.QUESTIONNAIRE.getType()));

		return mapper.selectByExample(example);
	}
}
