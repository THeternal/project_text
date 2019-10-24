package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsBaseCategoryMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsBaseCategoryDaoImpl extends DaoSupport<DaikenGoodsBaseCategory>
		implements DaikenGoodsBaseCategoryDao {

	@Autowired
	private DaikenGoodsBaseCategoryMapper mapper;

	@Override
	protected Mapper<DaikenGoodsBaseCategory> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenGoodsBaseCategory> getBaseCategory(List<Integer> levels) {
		Example example = new Example(DaikenGoodsBaseCategory.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("level", levels);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsBaseCategory> search(String searchInfo) {
		Example example = new Example(DaikenGoodsBaseCategory.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andLike("name", "%" + searchInfo + "%");
		return mapper.selectByExample(example);
	}
}
