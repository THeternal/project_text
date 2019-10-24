package com.kemean.dao;

import java.util.Date;
import java.util.List;

import com.kemean.bean.DaikenInvestigate;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenInvestigateDao extends Idao<DaikenInvestigate> {

	/**
	 * 调研列表
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	List<DaikenInvestigate> investigateList(Integer investigateStatus, String descStr, Boolean isOver, Integer pageNo,
			Integer pageSize, Boolean rewardPrice);

	/**
	 * 【管理后台】调研列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月22日
	 */
	List<DaikenInvestigate> adminInvestigateList(String title, List<Integer> type, String dateTimeS, String dateTimeE,
			List<Integer> userId, Integer pageNo, Integer pageSize);

	/**
	 * 点赞、投票报表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */
	List<DaikenInvestigate> investigateReport(Integer type, Integer userId, Boolean isOver, String title,
			Integer pageNo, Integer pageSize);

	/**
	 * 我的调研列表
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	List<DaikenInvestigate> investigateList(Integer userId, Integer pageNo, Integer pageSize);

	/**
	 * 调研结果统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */
	Integer selectInvestResultCount(Integer type, Integer userId, Date data, Boolean isOver);

	/**
	 * 调研点赞/投票，乐观锁处理
	 * 
	 * @author huwei
	 * @date 2018年7月5日
	 */
	Integer updateByHappyLock(Integer investigateId, Integer dataVersion);

	/**
	 * 调研日数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectDayQuantity(Integer type, Integer userUid, String dateStart, String dateEnd,
			Integer limit);

	/**
	 * 调研日参与人数统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectDayUserNum(Integer type, Integer userUid, String dateStart, String dateEnd, Integer limit);

	/**
	 * 调研用户佣金统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	Double selectDayUserMoney(Integer type, List<Integer> userId, String dateStart, String dateEnd);

	/**
	 * 平台发布调研 统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	Integer selectPlatformPublishCount(Integer type, List<Integer> userId, String dateStart, String dateEnd);

	/**
	 * 调研客户统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	Integer selectUserNum(Integer type, String dateStart, String dateEnd);

	/**
	 * 调研类型数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	Integer selectInvestTypeNum(Integer userId, Integer type, String dateStart, String dateEnd);

	/**
	 * 平台管理代客户上下架调研项目统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月5日
	 */
	List<AdminChartBO> selectAdminOperateInvestCount(Integer userId, String dateStart, String dateEnd, Integer limit);

}