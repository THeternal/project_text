package com.kemean.service.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.DaikenUserSales;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenSalesUserLevelEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserStatusEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenUserSalesDao;
import com.kemean.service.common.CommonService;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.sales.AdminSalesUserBO;
import com.kemean.vo.po.admin.sales.AdminSalesUserOperatePO;
import com.kemean.vo.po.admin.sales.AdminSalesUserPO;
import com.kemean.vo.po.admin.sales.AdminSalesUserPasswordPO;
import com.kemean.vo.po.admin.user.AdminUserStatusPO;

@Service
public class AdminSalesService {

	@Autowired
	private DaikenUserSalesDao daikenUserSalesDao;

	@Autowired
	private CommonService commonService;

	/**
	 * 销售管理员列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */

	public KemeanPageAdminBO<List<AdminSalesUserBO>> salesUserData(AdminSalesUserPO adminSalesUserPO,
			DaikenUserSales loginer) {
		List<Integer> levels = null;
		if (adminSalesUserPO.getLevel() != null) {
			levels = Arrays.asList(adminSalesUserPO.getLevel());
		}
		if (loginer != null) {
			if (DaikenSalesUserLevelEnum.SALE_DIRECTOR.getLevel().equals(loginer.getLevel())) {
				levels = Arrays.asList(DaikenSalesUserLevelEnum.SALE_MANAGER.getLevel(),
						DaikenSalesUserLevelEnum.SALE_SUPERVISOR.getLevel(),
						DaikenSalesUserLevelEnum.SELLER.getLevel());
			}
			if (DaikenSalesUserLevelEnum.SALE_SUPERVISOR.getLevel().equals(loginer.getLevel())) {
				levels = Arrays.asList(DaikenSalesUserLevelEnum.SALE_MANAGER.getLevel(),
						DaikenSalesUserLevelEnum.SELLER.getLevel());
			}
			if (DaikenSalesUserLevelEnum.SALE_MANAGER.getLevel().equals(loginer.getLevel())) {
				levels = Arrays.asList(DaikenSalesUserLevelEnum.SELLER.getLevel());
			}
			if (DaikenSalesUserLevelEnum.SELLER.getLevel().equals(loginer.getLevel())) {
				levels = Arrays.asList(00);
			}
		}

		List<DaikenUserSales> dbSalesUser = daikenUserSalesDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED, "uid", "phone" },
				new Object[] { false, adminSalesUserPO.getUid(), adminSalesUserPO.getPhone() }, "level", levels, "id",
				false, adminSalesUserPO.getPage(), adminSalesUserPO.getLimit());

		List<AdminSalesUserBO> result = Lists.transform(dbSalesUser, new Function<DaikenUserSales, AdminSalesUserBO>() {

			@Override
			public AdminSalesUserBO apply(DaikenUserSales input) {
				AdminSalesUserBO bo = new AdminSalesUserBO();
				BeanUtils.copyProperties(input, bo);
				bo.setLevelStr(DaikenMapData.salesLevel.get(input.getLevel()));
				bo.setSexManStr(BooleanUtils.isTrue(input.getSexMan()) ? "男" : "女");
				return bo;
			}

		});
		return new KemeanPageAdminBO<List<AdminSalesUserBO>>(new PageInfo<>(dbSalesUser).getTotal(), result);
	}

	/**
	 * 添加销售人员信息
	 * 
	 * @author huangyujian
	 * @date 2018年3月7日
	 */
	@Transactional
	public KemeanResult<String> salesUserAdd(AdminSalesUserOperatePO adminSalesUserOperatePO) {

		DaikenUserSales dbUser = daikenUserSalesDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "phone" },
				new Object[] { false, adminSalesUserOperatePO.getPhone() });
		if (dbUser != null) {
			return new KemeanResult<>(false, KemeanTips.Code.PHONE_REGISTERED);
		}
		DaikenUserSales newUser = new DaikenUserSales();
		Date now = new Date();
		BeanUtils.copyProperties(adminSalesUserOperatePO, newUser);
		newUser.setUserStatus(DaikenUserStatusEnum.NORMAL.getStatus());
		if (!adminSalesUserOperatePO.getUserStatus()) {
			newUser.setUserStatus(DaikenUserStatusEnum.DISABLED.getStatus());
		}
		newUser.setPassword(adminSalesUserOperatePO.getPassword().toLowerCase());
		newUser.setUid(commonService.getUid());
		newUser.setToken(DaikenUtil.getUUIDString());
		newUser.setReferralCode(adminSalesUserOperatePO.getPhone());
		newUser.setUpdateTime(now);
		newUser.setCreateTime(now);
		daikenUserSalesDao.saveSelective(newUser);
		return new KemeanResult<>(true, KemeanTips.Operate.ADD_SUCCESS);
	}

	/**
	 * 修改销售人员信息
	 * 
	 * @author huangyujian
	 * @date 2018年3月7日
	 */
	@Transactional
	public KemeanResult<String> salesUserUpdate(AdminSalesUserOperatePO adminSalesUserOperatePO) {

		DaikenUserSales dbUser = daikenUserSalesDao.selectById(adminSalesUserOperatePO.getObjId());
		dbUser.setPhone(adminSalesUserOperatePO.getPhone());
		dbUser.setLevel(adminSalesUserOperatePO.getLevel());
		dbUser.setSexMan(adminSalesUserOperatePO.getSexMan());
		dbUser.setReferralCode(adminSalesUserOperatePO.getPhone());
		dbUser.setNickName(adminSalesUserOperatePO.getNickName());
		dbUser.setUserStatus(DaikenUserStatusEnum.NORMAL.getStatus());
		if (!adminSalesUserOperatePO.getUserStatus()) {
			dbUser.setUserStatus(DaikenUserStatusEnum.DISABLED.getStatus());
		}
		if (StringUtils.isNoneBlank(adminSalesUserOperatePO.getPassword())) {
			dbUser.setPassword(adminSalesUserOperatePO.getPassword());
		}
		dbUser.setUpdateTime(new Date());
		daikenUserSalesDao.updateByPrimaryKeySelective(dbUser);
		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 操作用户 状态 正常 /禁用
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@Transactional
	public KemeanResult<String> userStatusOperate(AdminUserStatusPO adminUserStatusPO) {
		DaikenUserSales dbUser = daikenUserSalesDao.selectById(adminUserStatusPO.getObjId());
		dbUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.DISABLED.getStatus());
		if (adminUserStatusPO.getStatus()) {
			dbUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.NORMAL.getStatus());
		}
		dbUser.setUpdateTime(new Date());
		daikenUserSalesDao.updateByPrimaryKeySelective(dbUser);
		return new KemeanResult<>(true, KemeanTips.Operate.OPERATE_SUCCESS);
	}

	/**
	 * 修改密码
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@Transactional
	public KemeanResult<String> salesUserPasswordEdit(AdminSalesUserPasswordPO adminSalesUserPasswordPO) {
		DaikenUserSales dbUser = daikenUserSalesDao.selectById(adminSalesUserPasswordPO.getObjId());
		dbUser.setPassword(adminSalesUserPasswordPO.getPassword().toLowerCase());
		dbUser.setUpdateTime(new Date());
		daikenUserSalesDao.updateByPrimaryKeySelective(dbUser);
		return new KemeanResult<>(true, KemeanTips.Operate.UPDATE_SUCCESS);
	}

	/**
	 * 用户详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月13日
	 */
	public AdminSalesUserBO salesUserInfo(Integer objId) {
		AdminSalesUserBO bo = new AdminSalesUserBO();
		DaikenUserSales dbUser = daikenUserSalesDao.selectById(objId);
		BeanUtils.copyProperties(dbUser, bo);
		return bo;
	}
}
