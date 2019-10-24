package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenInvestigateOperation;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenInvestigateOperationMapper extends TkMapper<DaikenInvestigateOperation> {
	/**
	 * 用户token领取数量
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	Integer selectUserGetTokenNum(@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);

	/**
	 * 统计调研参与人数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectJoinUserNum(@Param("investId") List<Integer> investId,
			@Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 统计点赞、投票的参与人数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectPostAndLikeJoinUserNum(@Param("type") Integer type, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 统计问卷调查参与人数
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectQuestionJoinUserNum(@Param("type") Integer type, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 统计调研参与的用户ID
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<Integer> selectJoinUserId(@Param("type") Integer type, @Param("investigateId") List<Integer> investigateId,
			@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

}