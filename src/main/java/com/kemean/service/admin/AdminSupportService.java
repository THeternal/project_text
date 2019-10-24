package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserSales;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenComplaint.ComplaintStatus;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenUserEnum.DaikenUserTypeEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenComplaintDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserSalesDao;
import com.kemean.dao.KemeanAdminUserDao;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.po.admin.platform.AdminSupportDealPO;
import com.kemean.vo.po.admin.support.AdminSupportUserPO;

@Service
public class AdminSupportService {
	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenUserSalesDao daikenUserSalesDao;

	@Autowired
	private KemeanAdminUserDao kemeanAdminUserDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenComplaintDao daikenComplaintDao;

	/**
	 * 查看平台所有用户
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	public KemeanPageAdminBO<List<AdminUserBO>> userPageData(AdminSupportUserPO adminSupportUserPO) {
		List<AdminUserBO> result = new ArrayList<>();
		// 商家
		if ("1101".equals(adminSupportUserPO.getUserType()) || StringUtils.isBlank(adminSupportUserPO.getUserType())) {
			List<DaikenShop> dbShops = daikenShopDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "shopName" },
					new Object[] { false, adminSupportUserPO.getName() });
			List<Integer> shopIds = new ArrayList<>(dbShops.size());
			for (DaikenShop item : dbShops) {
				shopIds.add(item.getId());
			}
			List<DaikenUser> dbUser = daikenUserDao.selectByPropertiesAndIn(
					new String[] { KemeanConstant.DATA_DELETED, "userType" },
					new Object[] { false, DaikenUserTypeEnum.BUSINESS.getType() }, "shopId", shopIds, "id", false,
					adminSupportUserPO.getPage(), adminSupportUserPO.getLimit());

			for (DaikenUser daikenUser : dbUser) {
				AdminUserBO bo = new AdminUserBO();
				BeanUtils.copyProperties(daikenUser, bo);
				DaikenShop dbShop = daikenShopDao.selectById(daikenUser.getShopId());
				bo.setUserTypeStr(DaikenMapData.userType.get(daikenUser.getUserType()));
				bo.setNickName(dbShop.getShopName());
				result.add(bo);
			}
		}
		// 调研
		if ("1201".equals(adminSupportUserPO.getUserType())) {
			List<DaikenUser> dbUser = daikenUserDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "nickName" },
					new Object[] { false, adminSupportUserPO.getName() }, "id", false, adminSupportUserPO.getPage(),
					adminSupportUserPO.getLimit());
			for (DaikenUser daikenUser : dbUser) {
				AdminUserBO bo = new AdminUserBO();
				BeanUtils.copyProperties(daikenUser, bo);
				bo.setUserTypeStr("调研");
				bo.setNickName(daikenUser.getNickName());
				result.add(bo);
			}

		}
		// 销售
		if ("1301".equals(adminSupportUserPO.getUserType())) {
			List<DaikenUserSales> dbUser = daikenUserSalesDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "nickName" },
					new Object[] { false, adminSupportUserPO.getName() }, "id", false, adminSupportUserPO.getPage(),
					adminSupportUserPO.getLimit());
			for (DaikenUserSales daikenUserSales : dbUser) {
				AdminUserBO bo = new AdminUserBO();
				BeanUtils.copyProperties(daikenUserSales, bo);
				bo.setUserTypeStr("销售");
				bo.setNickName(daikenUserSales.getNickName());
				result.add(bo);
			}
		}
		// 平台运营
		if ("1401".equals(adminSupportUserPO.getUserType())) {
			List<KemeanAdminUser> dbUser = kemeanAdminUserDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "name" },
					new Object[] { false, adminSupportUserPO.getName() }, "id", false, adminSupportUserPO.getPage(),
					adminSupportUserPO.getLimit());
			for (KemeanAdminUser adminUser : dbUser) {
				AdminUserBO bo = new AdminUserBO();
				BeanUtils.copyProperties(adminUser, bo);
				bo.setUserTypeStr("平台运营");
				bo.setNickName(adminUser.getName());
				result.add(bo);
			}
		}

		return new KemeanPageAdminBO<List<AdminUserBO>>(new PageInfo<>(result).getTotal(), result);
	}

	/**
	 * 客服处理客户信息统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	public List<AdminChartBO> complaintDealCount(AdminSupportDealPO adminDatePO, KemeanAdminUser loginAdminUser,
			int limit) {
		if (loginAdminUser != null) {
			return daikenComplaintDao.selectSerivceDealCount(ComplaintStatus.FINISH.getStatus(), loginAdminUser.getId(),
					adminDatePO.getDateStart(), adminDatePO.getDateEnd(), limit);
		}
		List<KemeanAdminUser> dbAdminUser = kemeanAdminUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "name" }, new Object[] { false, adminDatePO.getName() });
		Integer adminUserId = null;
		if (dbAdminUser != null) {
			adminUserId = dbAdminUser.get(0).getId();
		}
		return daikenComplaintDao.selectSerivceDealCount(ComplaintStatus.FINISH.getStatus(), adminUserId,
				adminDatePO.getDateStart(), adminDatePO.getDateEnd(), limit);
	}

}
