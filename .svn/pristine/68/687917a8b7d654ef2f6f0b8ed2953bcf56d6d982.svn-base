package com.kemean.controller.sales;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.service.sales.SOpenService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.s.LoginBO;
import com.kemean.vo.po.s.LoginPO;

/**
 * 【销售端】基本控制器
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/s/open")
public class SOpenController {

	@Autowired
	private SOpenService sopenService;

	/**
	 * 登陆
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public KemeanResult<LoginBO> login(@Valid @RequestBody LoginPO loginPO) {
		return sopenService.login(loginPO);
	}

}
