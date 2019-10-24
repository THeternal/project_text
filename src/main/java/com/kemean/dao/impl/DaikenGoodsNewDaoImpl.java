package com.kemean.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenGoodsNewMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenGoodsNewDaoImpl extends DaoSupport<DaikenGoodsNew> implements DaikenGoodsNewDao {

	@Autowired
	private DaikenGoodsNewMapper mapper;

	@Override
	protected Mapper<DaikenGoodsNew> getMapper() {
		return mapper;
	}

	@Override
	public List<DaikenGoodsNew> newGoodsList(String descStr, Double minPriceSales, Double maxPriceSales,
			List<Integer> categoryIds, Boolean purchasing, String keyWord, Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andGreaterThanOrEqualTo("minPriceSales", minPriceSales);
		criteria.andLessThanOrEqualTo("minPriceSales", maxPriceSales);
		criteria.andIn("categoryId", categoryIds);
		// 审核状态为通过
		criteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		criteria.andEqualTo("goodsStatus", true);
		if (purchasing) {
			criteria.andEqualTo("purchasing", true);
		}
		if (StringUtils.isNotBlank(keyWord)) {
			criteria.andLike("title", "%" + keyWord + "%");
		}
		if (StringUtils.isNotBlank(descStr)) {
			example.setOrderByClause(descStr + " DESC");
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> goodsActivity(List<Integer> salesTypes, String descStr, Double minPriceSales,
			Double maxPriceSales, List<Integer> categoryIds, String keyWord, Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("salesType", salesTypes);
		criteria.andGreaterThanOrEqualTo("minPriceSales", minPriceSales);
		criteria.andLessThanOrEqualTo("minPriceSales", maxPriceSales);
		criteria.andIn("categoryId", categoryIds);
		// 审核状态为通过
		criteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		criteria.andEqualTo("goodsStatus", true);
		if (StringUtils.isNotBlank(keyWord)) {
			criteria.andLike("title", "%" + keyWord + "%");
		}
		if (StringUtils.isNotBlank(descStr)) {
			example.setOrderByClause(descStr + " DESC");
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> recommendGoods(Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		// 审核状态为通过
		criteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		criteria.andEqualTo("goodsStatus", true);
		example.setOrderByClause(" sales_num DESC");
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> goodsList(List<Integer> salesTypes, String title, Integer shopId, Integer pageNo,
			Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("salesType", salesTypes);
		criteria.andEqualTo("shopId", shopId);
		if (StringUtils.isNotBlank(title)) {
			criteria.andLike("title", "%" + title + "%");
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> goodsInfo(List<Integer> categoryIds, String title, Integer shopId, Integer pageNo,
			Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("categoryId", categoryIds);
		criteria.andEqualTo("shopId", shopId);
		criteria.andEqualTo("goodsStatus", true);
		// 审核状态为通过
		criteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		if (StringUtils.isNotBlank(title)) {
			criteria.andLike("title", "%" + title + "%");
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> search(String searchInfo) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("goodsStatus", true);
		criteria.andLike("title", "%" + searchInfo + "%");
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectGoodsNum(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectGoodsNum(shopId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectGoodsStatusNum(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectGoodsStatusNum(shopId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectGoodsSaleNum(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectGoodsSaleNum(shopId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectGoodsCategoryCount(Integer shopId, String dateStart, String dateEnd) {
		return mapper.selectGoodsCategoryCount(shopId, dateStart, dateEnd);
	}

	/**
	 * 帮卖商铺商品信息
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	@Override
	public List<DaikenGoodsNew> helpSellGoodsInfo(List<Integer> categoryIds, String title, Integer userShopId,
			Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("categoryId", categoryIds);
		criteria.andEqualTo("userShopId", userShopId);
		// 审核状态为通过
		criteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		criteria.andEqualTo("goodsStatus", true);
		if (StringUtils.isNotBlank(title)) {
			criteria.andLike("title", "%" + title + "%");
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> selectGoodsPromotionData(Integer type, Integer shopId, String goodsTitle,
			String dateStart, String dateEnd, Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("salesType", type);
		if (StringUtils.isNoneBlank(goodsTitle)) {
			criteria.andLike("title", "%" + goodsTitle + "%");
		}
		if (shopId != null) {
			criteria.andEqualTo("shopId", shopId);
		}

		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("createTime", dateStart);
		}
		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andLessThanOrEqualTo("createTime", dateEnd);
		}

		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectAdminOperateGoodsCount(Integer userId, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectAdminOperateGoodsCount(userId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectRedAfterGetRecord(Integer statusUser, List<Integer> shopId, Integer goodsId,
			String dateStart, String dateEnd, Integer limit) {
		return mapper.selectRedAfterGetRecord(statusUser, shopId, goodsId, dateStart, dateEnd, limit);
	}

	@Override
	public List<DaikenGoodsNew> selectGoodsPurchasing(Integer goodsId, String dateStart, String dateEnd, Integer pageNo,
			Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);

		criteria.andEqualTo("goodsId", goodsId);

		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andGreaterThanOrEqualTo("createTime", dateStart);
		}
		if (StringUtils.isNoneBlank(dateStart)) {
			criteria.andLessThanOrEqualTo("createTime", dateEnd);
		}

		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectShopPurchasingPriceSum(List<Integer> goodsId, String dateStart, String dateEnd,
			Integer limit) {
		return mapper.selectShopPurchasingPriceSum(goodsId, dateStart, dateEnd, limit);
	}

	@Override
	public List<AdminChartBO> selectGoodsFinishNum(Integer shopId, String dateStart, String dateEnd, Integer limit) {
		return null;
	}

	@Override
	public List<DaikenGoodsNew> search(String searchInfo, Integer pageNo, Integer pageSize, Boolean flag) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andEqualTo("goodsStatus", true);
		// 审核状态为通过
		criteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		criteria.andLike("title", "%" + searchInfo + "%");
		if (flag) {
			criteria.andEqualTo("purchasing", flag);
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> goodsListData(Integer shopId, String title, Integer salesType, String goodsUid,
			Boolean marketing, List<Integer> auditStatus, Integer pageNo, Integer pageSize) {

		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if (shopId != null) {
			criteria.andEqualTo("shopId", shopId);
		}
		if (StringUtils.isNoneBlank(title)) {
			criteria.andLike("title", "%" + title + "%");
		}
		if (salesType != null) {
			criteria.andEqualTo("salesType", salesType);
		}
		if (StringUtils.isNoneBlank(goodsUid)) {
			criteria.andEqualTo("goodsUid", goodsUid);
		}
		if (marketing != null) {
			criteria.andNotEqualTo("salesType", DaikenGoodsType.SalesType.NORMAL_SALES.getType());
		}
		if (!auditStatus.isEmpty()) {
			criteria.andIn("auditStatus", auditStatus);
		}
		PageHelper.startPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public List<DaikenGoodsNew> goodsPrecisionPushData(Integer shopId, String goodsUid, Integer pageNo,
			Integer pageSize) {
		Example example = new Example(DaikenGoodsNew.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andNotEqualTo("matchCondition", "");
		if (shopId != null) {
			criteria.andEqualTo("shopId", shopId);
		}
		if (StringUtils.isNoneBlank(goodsUid)) {
			criteria.andEqualTo("goodsUid", goodsUid);
		}
		PageHelper.startPage(pageNo, pageSize);
		example.setOrderByClause("id desc");
		return mapper.selectByExample(example);
	}

}
