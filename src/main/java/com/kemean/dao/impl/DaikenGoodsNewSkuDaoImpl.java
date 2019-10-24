package com.kemean.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsNewSkuMapper;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewMinPriceBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsNewSkuDaoImpl extends DaoSupport<DaikenGoodsNewSku> implements DaikenGoodsNewSkuDao {

	@Autowired
	private DaikenGoodsNewSkuMapper mapper;

	@Override
	protected Mapper<DaikenGoodsNewSku> getMapper() {
		return mapper;
	}

	@Override
	public Integer countStock(Integer goodsId) {
		Integer countStock = 0;
		Example example = new Example(DaikenGoodsNewSku.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("goodsId", goodsId);
		List<DaikenGoodsNewSku> dbGoodsNewSkus = mapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(dbGoodsNewSkus)) {
			for (DaikenGoodsNewSku daikenGoodsNewSku : dbGoodsNewSkus) {
				countStock += daikenGoodsNewSku.getStock();
			}
		}
		return countStock;
	}

	@Override
	public List<AdminShopPromotionBO> selectGoodStockSum(Integer goodsId) {
		return mapper.selectGoodStockSum(goodsId);
	}

	@Override
	public Integer updateByHappyLock(Integer goodsSkuid, Integer dateVersion) {
		return mapper.updateByHappyLock(goodsSkuid, dateVersion);
	}

	@Override
	public AdminGoodsNewMinPriceBO selectGoodsNewMinPrice(Integer goodsId) {
		return mapper.selectGoodsNewMinPrice(goodsId);
	}

}
