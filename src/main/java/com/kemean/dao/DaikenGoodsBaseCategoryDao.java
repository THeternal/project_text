package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.dao.su.Idao;

public interface DaikenGoodsBaseCategoryDao extends Idao<DaikenGoodsBaseCategory> {

	/**
	 * 获取商品分类
	 * 
	 * @author huwei
	 * @date 2018年6月25日
	 */
	List<DaikenGoodsBaseCategory> getBaseCategory(List<Integer> levels);

	/**
	 * 搜索
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	List<DaikenGoodsBaseCategory> search(String searchInfo);
}