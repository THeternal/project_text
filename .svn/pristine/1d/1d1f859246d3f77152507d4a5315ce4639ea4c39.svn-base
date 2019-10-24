package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenShop;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.AdminChartBO;

public interface DaikenShopDao extends Idao<DaikenShop> {

	/**
	 * 店铺数量统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminChartBO> selectShopCount(Boolean settledType, String dateStart, String dateEnd, Integer limit);

	/**
	 * 管理后台【店铺列表】
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月6日
	 */
	List<DaikenShop> selectShopList(List<Integer> shopId, String shopPhone, String shopName, Integer shopType,
			Integer page, Integer limit);

}