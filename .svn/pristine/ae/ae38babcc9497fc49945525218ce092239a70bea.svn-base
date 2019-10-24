package com.kemean.interceptor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanRedisKeyEnum;
import com.kemean.constant.KemeanTips;
import com.kemean.constant.KemeanUserEnum;
import com.kemean.constant.KemeanUserEnum.AdminUserRole;
import com.kemean.dao.KemeanAdminUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanResult;

/**
 * 管理员登录拦截
 * 
 * @Date 2018年1月1日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Component
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private KemeanAdminUserDao kemeanAdminUserDao;

	@Autowired
	protected KemeanRedisService redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String sessionId = request.getSession().getId();
		String cachekey = String.format(KemeanRedisKeyEnum.ADMIN_LOGIN_USER.getKey(), sessionId);
		String cacheAdmin = redisService.getString(cachekey);
		if (StringUtils.isNoneBlank(cacheAdmin)) {
			return true;
		}
		KemeanAdminUser dbAdmin = kemeanAdminUserDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "token" }, new Object[] { false, sessionId });
		if (dbAdmin == null) {
			response.sendRedirect(request.getContextPath() + KemeanConstant.KEMEAN_URL_ADMIN_LOGIN);
			return false;
		}
		if (KemeanUserEnum.UserStatus.DISABLED.getStatus().equals(dbAdmin.getUserStatus())) {
			String info = JSONObject.toJSONString(new KemeanResult<>(false, KemeanTips.Account.FREEZE));
			responseResult(response, info);
			return false;
		}
		dbAdmin.setHeadImg(dbAdmin.getHeadImg());
		if (StringUtils.isBlank(dbAdmin.getHeadImg())) {
			dbAdmin.setHeadImg(KemeanConstant.ADMIN_HEAD_IMG);
		}
		if (AdminUserRole.CUSTOMER_SERVICE.getType().equals(dbAdmin.getRole())) {
			// hash
			redisService.hset(DaikenRedisKeyEnum.SERVICE_LIST_H.getKey(), dbAdmin.getUid() + "",
					KemeanUtilAid.formatDate(new Date(), KemeanDateFormatEnum.DATE_NUM));
		}
		redisService.setString(cachekey, JSONObject.toJSONString(dbAdmin), KemeanConstant.DEFAULT_REDIS_EXPIRED);
		return true;
	}

	private void responseResult(HttpServletResponse response, String info) throws IOException {
		response.setStatus(200);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(info);
	}
}
