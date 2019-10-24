package com.kemean.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 管理后台 --销售登陆拦截
 * 
 * @Date 2018年7月10日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Component
public class AdminSaleLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("login_sale") == null) {
			response.sendRedirect(request.getContextPath() + "/sale/open/login");
			return false;
		}
		return true;
	}

}
