package com.kemean.dao;

import com.kemean.bean.DaikenInvestigateOptions;
import com.kemean.dao.su.Idao;

public interface DaikenInvestigateOptionsDao extends Idao<DaikenInvestigateOptions> {

	/**
	 * 获取投票总数
	 * 
	 * @author huwei
	 * @date 2018年7月12日
	 */
	Integer countVotes(Integer investigateId);
}