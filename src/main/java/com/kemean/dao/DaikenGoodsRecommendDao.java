package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenGoodsRecommend;
import com.kemean.dao.su.Idao;

public interface DaikenGoodsRecommendDao extends Idao<DaikenGoodsRecommend> {

	/**
	 * @author huwei
	 * @date 2018年8月31日
	 */
	List<DaikenGoodsRecommend> recommendGoods(Integer recommendId, List<Integer> recommendTypes);

	/**
	 * @author huwei
	 * @date 2018年9月5日
	 */
	List<DaikenGoodsRecommend> getLastBuyTime(Integer recommendId);

	/**
	 * @author huwei
	 * @date 2018年9月6日
	 */
	List<DaikenGoodsRecommend> getShowRecommend();

}
