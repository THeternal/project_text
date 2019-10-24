package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenGoodsRecommendUser;
import com.kemean.dao.su.Idao;

public interface DaikenGoodsRecommendUserDao extends Idao<DaikenGoodsRecommendUser> {

	/**
	 * 保存user 集合
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月20日
	 */
	Integer saveUserList(List<DaikenGoodsRecommendUser> userList);

}
