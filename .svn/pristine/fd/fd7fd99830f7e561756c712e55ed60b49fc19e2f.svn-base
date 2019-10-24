package com.kemean.service.admin;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenComplaint;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenComplaint.ComplaintStatus;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenComplaintDao;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.support.AdminComplaintBO;
import com.kemean.vo.po.admin.support.AdminSupportComplaintAddPO;
import com.kemean.vo.po.admin.support.AdminSupportComplaintPO;

@Service
public class AdminComplaintService {
	@Autowired
	private DaikenComplaintDao daikenComplaintDao;

	/**
	 * 投诉信息输入
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	public KemeanResult<String> addComplaintData(AdminSupportComplaintAddPO adminSupportComplaintPO,
			KemeanAdminUser loginer) {
		Date now = new Date();
		DaikenComplaint newDaikenComplaint = new DaikenComplaint();
		BeanUtils.copyProperties(adminSupportComplaintPO, newDaikenComplaint);
		newDaikenComplaint.setUpdateTime(now);
		newDaikenComplaint.setCreateTime(now);
		newDaikenComplaint.setAdminSerivceId(loginer.getId());
		newDaikenComplaint.setAdminSerivceName(loginer.getName());
		newDaikenComplaint.setComplaintStatus(ComplaintStatus.WAIT_DEAL.getStatus());
		daikenComplaintDao.saveSelective(newDaikenComplaint);
		return new KemeanResult<>(true, KemeanTips.Operate.ADD_SUCCESS);
	}

	/**
	 * 投诉列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	public KemeanPageAdminBO<List<AdminComplaintBO>> complaintListData(AdminSupportComplaintPO adminSupportComplaintPO,
			KemeanAdminUser loginer) {
		List<DaikenComplaint> dbCompalint = daikenComplaintDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "adminSerivceId", "complaintStatus", "userPhone",
						"complaintType", "name" },
				new Object[] { false, loginer.getId(), adminSupportComplaintPO.getComplaintStatus(),
						adminSupportComplaintPO.getUserPhone(), adminSupportComplaintPO.getComplaintType(),
						adminSupportComplaintPO.getName() },
				"id", false, adminSupportComplaintPO.getPage(), adminSupportComplaintPO.getLimit());

		List<AdminComplaintBO> result = Lists.transform(dbCompalint, new Function<DaikenComplaint, AdminComplaintBO>() {
			@Override
			public AdminComplaintBO apply(DaikenComplaint input) {
				AdminComplaintBO bo = new AdminComplaintBO();
				bo.setComplaintStatusStr(DaikenMapData.complaintStatus.get(input.getComplaintStatus()));
				bo.setComplaintTypeStr(DaikenMapData.complaintType.get(input.getComplaintType()));
				bo.setUserTypeStr(DaikenMapData.userType.get(input.getUserType()));
				BeanUtils.copyProperties(input, bo);
				return bo;
			}

		});
		return new KemeanPageAdminBO<List<AdminComplaintBO>>(new PageInfo<>(dbCompalint).getTotal(), result);
	}

	/**
	 * 处理投诉内容
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@Transactional
	public KemeanResult<String> complaintDeal(Integer objId, String dealContent, KemeanAdminUser loginAdminUser) {
		DaikenComplaint dbComplaint = daikenComplaintDao.selectById(objId);
		dbComplaint.setDealContent(dealContent);
		dbComplaint.setUpdateTime(new Date());
		dbComplaint.setComplaintStatus(ComplaintStatus.FINISH.getStatus());
		daikenComplaintDao.updateByPrimaryKeySelective(dbComplaint);
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

}
