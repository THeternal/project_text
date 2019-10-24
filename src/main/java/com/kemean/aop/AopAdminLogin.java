package com.kemean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kemean.service.ImRyService;
import com.kemean.vo.bo.KemeanResult;

/**
 * 管理员登录切面
 * 
 * @Date 2018年10月5日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Aspect
@Component
@Order(1)
public class AopAdminLogin {

	@Autowired
	protected ImRyService imRyService;

	/**
	 * 定义切入点
	 * 
	 * @author huangyujian
	 * @date 2018年10月4日
	 */
	@Pointcut("execution( * com.kemean.service.admin.KemeanAdminOpenService.loginCheck(..))")
	public void aopAdminLoginPointcut() {

	}

	@AfterReturning(returning = "object", pointcut = "aopAdminLoginPointcut()")
	public void doAfterReturning(JoinPoint joinPoint, Object object) {
		Object[] args = joinPoint.getArgs();
		System.out.println("请求参数***********" + args.toString() + "，结果：" + JSONObject.toJSONString(object));
		@SuppressWarnings("unchecked")
		KemeanResult<String> result = (KemeanResult<String>) object;
		if (result.getSuccess()) {

		}
	}

}
