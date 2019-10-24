package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenComplaint;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenComplaintDao extends Idao<DaikenComplaint> {
	/**
	 * 客服处理客户信息统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectSerivceDealCount(Integer complaintStatus, Integer adminSerivceId, String dateStart,
			String dateEnd, Integer limit);

}
