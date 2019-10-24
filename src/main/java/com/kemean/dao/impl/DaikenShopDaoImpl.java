package com.kemean.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.DaikenShop;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenShopMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenShopDaoImpl extends DaoSupport<DaikenShop> implements DaikenShopDao {

	@Autowired
	private DaikenShopMapper mapper;

	@Override
	protected Mapper<DaikenShop> getMapper() {
		return mapper;
	}

	@Override
	public List<AdminChartBO> selectShopCount(Boolean settledType, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectShopCount(settledType, dateStart, dateEnd, limit);
	}

	@Override
	public List<DaikenShop> selectShopList(List<Integer> shopId, String shopPhone, String shopName, Integer shopType,
			Integer page, Integer limit) {

		Example example = new Example(DaikenShop.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		createCriteria.andEqualTo("auditStatus", KemeanSettledEnum.AUDIT_PASS.getStatus());
		if (StringUtils.isNoneBlank(shopPhone)) {
			createCriteria.andEqualTo("shopPhone", shopPhone);
		}
		if (StringUtils.isNoneBlank(shopName)) {
			createCriteria.andLike("shopName", "%" + shopName + "%");
		}
		if (!shopId.isEmpty()) {
			createCriteria.andIn("id", shopId);
		}
		if (shopType != null) {
			createCriteria.andEqualTo("settledType", shopType);
		}
		example.setOrderByClause("id desc");
		PageHelper.startPage(page, limit);
		return mapper.selectByExample(example);
	}
}
