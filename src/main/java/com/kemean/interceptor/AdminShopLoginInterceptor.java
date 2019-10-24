package com.kemean.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanRedisKeyEnum;
import com.kemean.service.KemeanRedisService;

/**
 * 管理后台 --商家登陆拦截
 * 
 * @Date 2018年7月10日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Component
public class AdminShopLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected KemeanRedisService redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		DaikenUser loginUser = (DaikenUser) request.getSession().getAttribute("login_shop");
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/shop/open/login");
			return false;
		}
		String cachekey = String.format(KemeanRedisKeyEnum.ADMIN_LOGIN_USER.getKey(), loginUser.getToken());
		String cacheAdmin = redisService.getString(cachekey);
		if (StringUtils.isNoneBlank(cacheAdmin)) {
			return true;
		}
		redisService.setString(cachekey, JSONObject.toJSONString(loginUser), KemeanConstant.DEFAULT_REDIS_EXPIRED);
		return true;
	}

}
