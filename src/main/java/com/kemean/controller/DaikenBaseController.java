package com.kemean.controller;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserSales;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.KemeanConstant;

/**
 * 【公共】基础控制器
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class DaikenBaseController extends KemeanBaseController {

	/** 获取登录消费者 **/
	protected DaikenUser getLoginConsumer() {
		String userToken = request.getHeader(KemeanConstant.USER_TOKEN);
		String cachekey = String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), userToken);
		String cacheUser = kemeanRedisService.getString(cachekey);
		if (StringUtils.isBlank(cacheUser)) {
			return null;
		}
		return JSONObject.parseObject(cacheUser, DaikenUser.class);
	}

	/** 获取登录商户 **/
	protected DaikenUser getLoginBusiness() {
		String userToken = request.getHeader(KemeanConstant.USER_TOKEN);
		String cachekey = String.format(DaikenRedisKeyEnum.USER_BUSINESS.getKey(), userToken);
		String cacheUser = kemeanRedisService.getString(cachekey);
		if (StringUtils.isBlank(cacheUser)) {
			return null;
		}
		return JSONObject.parseObject(cacheUser, DaikenUser.class);
	}

	/** 【管理后台】获取登录的商家 **/
	protected DaikenUser getLoginAdminShop() {

		DaikenUser sessionShopUser = (DaikenUser) request.getSession().getAttribute("login_shop");
		if (sessionShopUser == null) {
			return null;
		}
		return (DaikenUser) sessionShopUser;
	}

	/** 【管理后台】获取登录的销售 **/
	protected DaikenUserSales getAdminLoginSale() {
		DaikenUserSales sessionSaleUser = (DaikenUserSales) request.getSession().getAttribute("login_sale");
		if (sessionSaleUser == null) {
			return null;
		}
		return (DaikenUserSales) sessionSaleUser;

	}

}
