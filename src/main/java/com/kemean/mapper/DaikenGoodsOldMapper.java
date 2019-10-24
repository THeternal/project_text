package com.kemean.mapper;

import org.apache.ibatis.annotations.Param;

import com.kemean.bean.DaikenGoodsOld;
import com.kemean.tk.TkMapper;

public interface DaikenGoodsOldMapper extends TkMapper<DaikenGoodsOld> {

	/**
	 * 立即下单，二手商品 乐观锁处理
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	Integer updateByHappyLock(@Param("goodsid") Integer goodsid, @Param("dateVersion") Integer dateVersion);

}