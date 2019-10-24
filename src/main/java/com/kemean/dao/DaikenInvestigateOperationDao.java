package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenInvestigateOperation;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenInvestigateOperationDao extends Idao<DaikenInvestigateOperation> {

	/**
	 * 用户token领取数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	Integer selectUserGetTokenNum(String dateStart, String dateEnd);

	/**
	 * 统计调研参与人数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectJoinUserNum(List<Integer> investId, String dateStart, String dateEnd, Integer limit);

	/**
	 * 统计点赞、投票的参与人数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectPostAndLikeJoinUserNum(Integer type, String dateStart, String dateEnd, Integer limit);

	/**
	 * 统计问卷调查参与人数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectQuestionJoinUserNum(Integer type, String dateStart, String dateEnd, Integer limit);

	/**
	 * 统计调研参与的用户ID
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<Integer> selectJoinUserId(Integer type, List<Integer> investigateId, Integer pageNo, Integer pageSize);

	/**
	 * 参与用户列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	List<DaikenInvestigateOperation> selectInvestOperation(List<Integer> investigateId);

}