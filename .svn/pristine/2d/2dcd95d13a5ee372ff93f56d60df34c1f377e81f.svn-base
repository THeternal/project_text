package com.kemean.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenUser;
import com.kemean.tk.TkMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenUserMapper extends TkMapper<DaikenUser> {

	/**
	 * 用户token统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectUserToken(@Param("userType") Integer userType, @Param("dateStart") String dateStart,
			@Param("dateEnd") String dateEnd, @Param("limit") Integer limit);

	/**
	 * 获取最大UID
	 * 
	 * @author huwei
	 * @date 2018年7月23日
	 */
	Integer selectTheMaxId();
}