package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kemean.bean.DaikenComplaint;
import com.kemean.dao.DaikenComplaintDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenComplaintMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;

@Repository
public class DaikenComplaintDaoImpl extends DaoSupport<DaikenComplaint> implements DaikenComplaintDao {

	@Autowired
	private DaikenComplaintMapper mapper;

	@Override
	protected Mapper<DaikenComplaint> getMapper() {
		return mapper;
	}

	@Override
	public List<AdminChartBO> selectSerivceDealCount(Integer complaintStatus, Integer adminSerivceId, String dateStart,
			String dateEnd, Integer limit) {
		return mapper.selectSerivceDealCount(complaintStatus, adminSerivceId, dateStart, dateEnd, limit);
	}

}
