package com.kemean.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenRedShare;
import com.kemean.constant.DaikenShareTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.DaikenRedShareDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenRedShareMapper;
import com.kemean.util.DaikenUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenRedShareDaoImpl extends DaoSupport<DaikenRedShare> implements DaikenRedShareDao {

	@Autowired
	private DaikenRedShareMapper mapper;

	@Override
	protected Mapper<DaikenRedShare> getMapper() {
		return mapper;
	}

	@Override
	public Boolean countGoodsOnecDay(Integer userId, Integer type, Integer typeId, String now, Boolean isNewGoods) {
		Example example = new Example(DaikenRedShare.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		// 一手
		if (isNewGoods) {
			criteria.andEqualTo("type", DaikenShareTypeEnum.NEW_GOODS.getType());
		}

		// 二手
		if (!isNewGoods) {
			criteria.andEqualTo("type", DaikenShareTypeEnum.OLD_GOODS.getType());
		}
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("type", type);
		criteria.andEqualTo("typeId", typeId);
		criteria.andEqualTo("relieveWool", true);
		List<DaikenRedShare> dbRedShares = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbRedShares)) {
			for (DaikenRedShare daikenRedShare : dbRedShares) {
				if (now.equals(DaikenUtil.formatDate(daikenRedShare.getCreateTime(), KemeanDateFormatEnum.DATE))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Boolean countGoodsThreeDay(Integer userId, Integer typeId, String now, Boolean isNewGoods) {
		Integer times = 0;
		Example example = new Example(DaikenRedShare.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		// 一手
		if (isNewGoods) {
			criteria.andEqualTo("type", DaikenShareTypeEnum.NEW_GOODS.getType());
		}

		// 二手
		if (!isNewGoods) {
			criteria.andEqualTo("type", DaikenShareTypeEnum.OLD_GOODS.getType());
		}
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("typeId", typeId);
		criteria.andEqualTo("relieveWool", true);
		List<DaikenRedShare> dbRedShares = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbRedShares))
			for (DaikenRedShare daikenRedShare : dbRedShares) {
				if (now.equals(DaikenUtil.formatDate(daikenRedShare.getCreateTime(), KemeanDateFormatEnum.DATE))) {
					times = times + 1;
					if (times > 3) {
						return true;
					}
				}
			}
		return false;
	}

}
