package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenOrderAppraisal;
import com.kemean.dao.su.Idao;

public interface DaikenOrderAppraisalDao extends Idao<DaikenOrderAppraisal> {

	/**
	 * 获取商品评论 type:1101商铺 1102商品
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	List<DaikenOrderAppraisal> goodsAppraisalList(List<Integer> appraisalTypes, Integer goodsId, Boolean isNewGoods,
			Integer pageNo, Integer pageSize);
}