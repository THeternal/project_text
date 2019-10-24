package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.DaikenOrderAppraisal;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenOrderAppraisalMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenOrderAppraisalDao extends DaoSupport<DaikenOrderAppraisal>
		implements com.kemean.dao.DaikenOrderAppraisalDao {

	@Autowired
	private DaikenOrderAppraisalMapper mapper;

	@Override
	protected Mapper<DaikenOrderAppraisal> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenOrderAppraisal> goodsAppraisalList(List<Integer> appraisalTypes, Integer goodsId,
			Boolean isNewGoods, Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenOrderAppraisal.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("score", appraisalTypes);
		criteria.andEqualTo("idGoods", goodsId);
		criteria.andEqualTo("isNewGoods", isNewGoods);
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

}
