package com.kemean.dao;

import java.util.List;

import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.dao.su.Idao;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewMinPriceBO;
import com.kemean.vo.bo.admin.shop.AdminShopPromotionBO;

public interface DaikenGoodsNewSkuDao extends Idao<DaikenGoodsNewSku> {

	/**
	 * 总库存
	 * 
	 * @author huwei
	 * @date 2018年7月12日
	 */
	Integer countStock(Integer goodsId);

	/**
	 * 商品库存
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	List<AdminShopPromotionBO> selectGoodStockSum(Integer goodsId);

	/**
	 * 立即下单，一手商品 乐观锁处理
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	Integer updateByHappyLock(Integer goodsSkuid, Integer dateVersion);

	/**
	 * 获取商品的最低折扣、门市价、售价
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	AdminGoodsNewMinPriceBO selectGoodsNewMinPrice(Integer goodsId);
}