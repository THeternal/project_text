package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.kemean.bean.DaikenGoodsHotTreasure;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsHotTreasureDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsHotTreasureMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsHotTreasureDaoImpl extends DaoSupport<DaikenGoodsHotTreasure>
		implements DaikenGoodsHotTreasureDao {

	@Autowired
	private DaikenGoodsHotTreasureMapper mapper;

	@Override
	protected Mapper<DaikenGoodsHotTreasure> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenGoodsHotTreasure> treasureData(String userPhone, List<Integer> currentStates,
			List<Integer> screenPositions, Integer limit, Integer page) {
		Example example = new Example(DaikenGoodsHotTreasure.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("screenPosition", screenPositions);
		criteria.andIn("currentState", currentStates);
		if (StringUtil.isNotEmpty(userPhone)) {
			criteria.andLike("userPhone", "%" + userPhone + "%");
		}
		PageHelper.startPage(page, limit);
		return mapper.selectByExample(example);
	}

}
