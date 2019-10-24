package com.kemean.dao.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsRecommend;
import com.kemean.constant.DaikenRecommend;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsRecommendMapper;
import com.kemean.util.DaikenUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsRecommendDaoImpl extends DaoSupport<DaikenGoodsRecommend>
		implements com.kemean.dao.DaikenGoodsRecommendDao {

	@Autowired
	private DaikenGoodsRecommendMapper mapper;

	@Override
	protected Mapper<DaikenGoodsRecommend> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenGoodsRecommend> recommendGoods(Integer recommendId, List<Integer> recommendTypes) {
		Example example = new Example(DaikenGoodsRecommend.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("recommendId", recommendId);
		criteria.andEqualTo("isOver", false);
		criteria.andEqualTo("isTakeEffect", true);
		criteria.andIn("recommendType", recommendTypes);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsRecommend> getLastBuyTime(Integer recommendId) {
		Example example = new Example(DaikenGoodsRecommend.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("recommendId", recommendId);
		criteria.andEqualTo("isOver", false);
		criteria.andIn("recommendType", Arrays.asList(DaikenRecommend.RecommendType.CLICK.getType(),
				DaikenRecommend.RecommendType.SHOW.getType()));
		example.setOrderByClause(" buy_time DESC");
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsRecommend> getShowRecommend() {
		Example example = new Example(DaikenGoodsRecommend.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("isOver", false);
		criteria.andEqualTo("recommendType", DaikenRecommend.RecommendType.SHOW.getType());
		criteria.andLessThanOrEqualTo("buyTime", DaikenUtil.formatDate(new Date(), KemeanDateFormatEnum.DATE));
		return mapper.selectByExample(example);
	}

}
