package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenGoodsCar;
import com.kemean.dao.su.Idao;

public interface DaikenGoodsCarDao extends Idao<DaikenGoodsCar> {

	/**
	 * @author huwei
	 * @date 2018年8月13日
	 */
	List<DaikenGoodsCar> helpSellGoods(Integer userId);

	/**
	 * @author huwei
	 * @date 2018年8月13日
	 */
	List<DaikenGoodsCar> helpSellGoodsInfo(Integer userId, Integer userShopId);
}