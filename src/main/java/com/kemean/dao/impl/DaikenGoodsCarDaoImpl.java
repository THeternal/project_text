package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsCar;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsCarDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsCarMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsCarDaoImpl extends DaoSupport<DaikenGoodsCar> implements DaikenGoodsCarDao {

	@Autowired
	private DaikenGoodsCarMapper mapper;

	@Override
	protected Mapper<DaikenGoodsCar> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenGoodsCar> helpSellGoods(Integer userId) {
		Example example = new Example(DaikenGoodsCar.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("userId", userId);
		criteria.andNotEqualTo("idPurchasing", 0);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsCar> helpSellGoodsInfo(Integer userId, Integer userShopId) {
		Example example = new Example(DaikenGoodsCar.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("idPurchasing", userShopId);
		return mapper.selectByExample(example);
	}

}
