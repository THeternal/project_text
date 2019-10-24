package com.kemean.service.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanRedisKeyEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.constant.KemeanUserEnum;
import com.kemean.constant.KemeanUserEnum.AdminUserRole;
import com.kemean.dao.KemeanAdminUserDao;
import com.kemean.service.GeetestLib;
import com.kemean.service.ImRyService;
import com.kemean.service.KemeanRedisService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.util.KemeanUtilWeb;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.po.admin.AdminLoginCheckPO;

@Service
public class KemeanAdminOpenService {

	@Value("${geetest.id}")
	private String geetest_id;

	@Value("${geetest.key}")
	private String geetest_key;

	@Autowired
	private KemeanRedisService redisService;

	@Autowired
	private KemeanAdminUserDao kemeanAdminUserDao;

	@Autowired
	protected ImRyService imRyService;

	/**
	 * 极验服务注册
	 * 
	 * @author huangyujian
	 * @date 2018年9月22日
	 */
	public void geetestRegister(HttpServletRequest request, HttpServletResponse response) {
		GeetestLib geetestLib = new GeetestLib(geetest_id, geetest_key, true);

		// 自定义参数
		HashMap<String, String> param = new HashMap<String, String>(3);
		param.put("user_id", request.getSession().getId());
		param.put("ip_address", KemeanUtilWeb.getIpAddress(request));
		param.put("client_type", "web");

		// 进行验证预处理
		int gtServerStatus = geetestLib.preProcess(param);
		redisService.setString(String.format(KemeanRedisKeyEnum.GEETEST.getKey(), request.getSession().getId()),
				gtServerStatus + "");
		String responseStr = geetestLib.getResponseStr();

		try {
			response.getWriter().println(responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 管理员登录校验
	 * 
	 * @author huangyujian
	 * @date 2018年5月4日
	 */
	public KemeanResult<String> loginCheck(AdminLoginCheckPO adminLoginCheckPO, HttpServletRequest request) {
		KemeanAdminUser dbAdminUser = kemeanAdminUserDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "loginNo" },
				new Object[] { false, adminLoginCheckPO.getLoginNo() });
		if (dbAdminUser == null) {
			return new KemeanResult<>(false, KemeanTips.Account.ACCOUNT_NOT_EXIST);
		}
		if (!dbAdminUser.getLoginPwd().equalsIgnoreCase(adminLoginCheckPO.getLoginPwd())) {
			return new KemeanResult<>(false, KemeanTips.Account.PASSWORD_ERROR);
		}
		if (!KemeanUserEnum.UserStatus.NORMAL.getStatus().equals(dbAdminUser.getUserStatus())) {
			return new KemeanResult<>(false, KemeanTips.Account.FREEZE);
		}
		// 删除缓存
		// 依赖拦截器写入新缓存信息
		redisService.del(String.format(KemeanRedisKeyEnum.ADMIN_LOGIN_USER.getKey(), dbAdminUser.getToken()));
		if (AdminUserRole.CUSTOMER_SERVICE.getType().equals(dbAdminUser.getRole())) {
			// TODO 缺失用户头像
			String rmUserToken = imRyService.userToken(String.valueOf(dbAdminUser.getUid()), dbAdminUser.getName(),
					dbAdminUser.getHeadImg());
			dbAdminUser.setRecord(rmUserToken);
		}
		dbAdminUser.setToken(request.getSession().getId());
		dbAdminUser.setUpdateTime(new Date());
		kemeanAdminUserDao.updateByPrimaryKeySelective(dbAdminUser);
		return new KemeanResult<>(true, KemeanTips.Account.LOGIN_SUCCESS);
	}

	/**
	 * 退出登录
	 * 
	 * @author huangyujian
	 * @date 2018年3月24日
	 */
	public void signOut(HttpServletRequest request, KemeanAdminUser loginAdminUser) {
		loginAdminUser.setToken(KemeanUtilAid.getUUIDString());
		loginAdminUser.setUpdateTime(new Date());
		kemeanAdminUserDao.updateByPrimaryKeySelective(loginAdminUser);

		redisService.del(String.format(KemeanRedisKeyEnum.ADMIN_LOGIN_USER.getKey(), request.getSession().getId()));
	}

}
