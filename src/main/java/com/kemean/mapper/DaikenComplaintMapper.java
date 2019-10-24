package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenComplaint;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenComplaintMapper extends TkMapper<DaikenComplaint> {
	/**
	 * 客服处理客户信息统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectSerivceDealCount(@Param("complaintStatus") Integer complaintStatus,
			@Param("adminSerivceId") Integer adminSerivceId, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

}