package com.kemean.controller.consumer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.consumer.CGoodscarService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.c.goodscar.GoodscarInfoListShopBO;
import com.kemean.vo.bo.c.goodscar.SubmitOrderInCarBO;
import com.kemean.vo.po.c.goodscar.AddGoodsCarPO;
import com.kemean.vo.po.c.goodscar.DelGoodsPO;
import com.kemean.vo.po.c.goodscar.SubmitOrderInCarPO;
import com.kemean.vo.po.c.goodscar.UpdateGoodsInfoPO;

/**
 * 
 * 【客户端】购物车控制器
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/c/goodscar")
public class CGoodscarController extends DaikenBaseController {

	@Autowired
	private CGoodscarService goodscarService;

	/**
	 * 购物车信息
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@RequestMapping(value = "info_list", method = RequestMethod.GET)
	public KemeanResult<List<GoodscarInfoListShopBO>> infoList() {
		return new KemeanResult<List<GoodscarInfoListShopBO>>(goodscarService.infoList(getLoginConsumer()));
	}

	/**
	 * 添加物品到购物车
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@RequestMapping(value = "add_goods_car", method = RequestMethod.POST)
	public KemeanResult<String> addGoodsCar(@Valid @RequestBody AddGoodsCarPO addGoodsCarPO) {
		return goodscarService.addGoodsCar(addGoodsCarPO, getLoginConsumer());
	}

	/**
	 * 删除购物车商品
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@RequestMapping(value = "del_goods", method = RequestMethod.POST)
	public KemeanResult<String> delGoods(@Valid @RequestBody DelGoodsPO delGoodsPO) {
		return goodscarService.delGoods(delGoodsPO);
	}

	/**
	 * 修改购物车商品数量
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@RequestMapping(value = "update_goods_info", method = RequestMethod.POST)
	public KemeanResult<String> updateGoodsInfo(@Valid @RequestBody UpdateGoodsInfoPO updateGoodsInfoPO) {
		return goodscarService.updateGoodsInfo(updateGoodsInfoPO);
	}

	/**
	 * 结算购物车订单
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@RequestMapping(value = "submit_order_in_car", method = RequestMethod.POST)
	public KemeanResult<List<SubmitOrderInCarBO>> submitOrderInCar(
			@Valid @RequestBody SubmitOrderInCarPO submitOrderInCarPO) {
		return new KemeanResult<List<SubmitOrderInCarBO>>(
				goodscarService.submitOrderInCar(submitOrderInCarPO, getLoginConsumer()));
	}

}
