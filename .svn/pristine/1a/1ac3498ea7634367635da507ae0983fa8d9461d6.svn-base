package com.kemean.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenOrderGoods;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenOrderGoodsMapper;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsMarketingBO;
import com.kemean.vo.bo.admin.goods.AdminGoodsSaleTotalBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenOrderGoodsDaoImpl extends DaoSupport<DaikenOrderGoods> implements DaikenOrderGoodsDao {

	@Autowired
	private DaikenOrderGoodsMapper mapper;

	@Override
	protected Mapper<DaikenOrderGoods> getMapper() {
		return mapper;
	}

	@Override
	public List<AdminChartBO> selectGoodInSaleNum(Integer shopId, Integer goodsId, String dateStart, String dateEnd,
			Boolean purchasing, Integer limit) {
		return mapper.selectGoodInSaleNum(shopId, goodsId, dateStart, dateEnd, purchasing, limit);
	}

	@Override
	public List<AdminChartBO> selectGoodInSalePrice(Integer shopId, Integer goodsId, String dateStart, String dateEnd,
			Boolean purchasing, Integer limit) {
		return mapper.selectGoodInSalePrice(shopId, goodsId, dateStart, dateEnd, purchasing, limit);
	}

	@Override
	public List<AdminShopPromotionBO> selectGoodsDiscountPriceSum(Integer shopId, String shopName, String goodsTitle,
			Integer salesType, String dateStart, String dateEnd, Integer pageNo, Integer limit) {
		return mapper.selectGoodsDiscountPriceSum(shopId, shopName, goodsTitle, salesType, dateStart, dateEnd, pageNo,
				limit);
	}

	@Override
	public Double goodsSalePrice(Integer goodsId) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("goodsId", goodsId);
		List<DaikenOrderGoods> dbGoodsData = mapper.selectByExample(example);
		Double salePrice = 0.0;
		for (DaikenOrderGoods daikenOrderGoods : dbGoodsData) {
			salePrice += daikenOrderGoods.getSalesPrice();
		}

		return salePrice;
	}

	@Override
	public List<DaikenOrderGoods> shopOrderGoods(String orderNo, String goodsUid, String skuNo) {
		Example example = new Example(DaikenOrderGoods.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("orderNo", orderNo);
		if (StringUtils.isNotBlank(goodsUid)) {
			criteria.andLike("goodsUid", "%" + goodsUid + "%");
		}
		if (StringUtils.isNoneBlank(skuNo)) {
			criteria.andLike("skuNo", "%" + skuNo + "%");

		}
		return mapper.selectByExample(example);
	}

	@Override
	public AdminGoodsMarketingBO selectGoodsMarkting(List<String> orderNo) {
		AdminGoodsMarketingBO result = new AdminGoodsMarketingBO();
		Example example = new Example(DaikenOrderGoods.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if (!orderNo.isEmpty()) {
			criteria.andIn("orderNo", orderNo);
		}
		Double salePrice = 0.00;
		Integer saleNum = 0;
		List<DaikenOrderGoods> selectByExample = mapper.selectByExample(example);
		for (DaikenOrderGoods daikenOrder : selectByExample) {
			salePrice += daikenOrder.getSalesPrice();
			saleNum += daikenOrder.getQuantity();
		}
		result.setSalePrice(salePrice);
		result.setSaleNum(saleNum);
		return result;
	}

	@Override
	public List<AdminGoodsSaleTotalBO> selectGoodsSaleTotalData(Integer shopId, String dateStart, String dateEnd,
			Integer pageNo, Integer limit) {
		return mapper.selectGoodsSaleTotalData(shopId, dateStart, dateEnd, pageNo, limit);
	}
}
