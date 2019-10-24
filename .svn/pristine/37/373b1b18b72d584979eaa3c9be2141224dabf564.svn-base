package com.kemean.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminGoodsHotTreasureService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.treasure.GoodsDataBO;
import com.kemean.vo.bo.admin.treasure.TreasureDataBO;
import com.kemean.vo.po.admin.treasure.HotTreasurePO;
import com.kemean.vo.po.admin.treasure.TreasureDataPO;

@Controller
@RequestMapping(value = "admin/treasure")
public class AdminGoodsHotTreasureController extends DaikenBaseController {

	@Autowired
	private AdminGoodsHotTreasureService adminGoodsHotTreasureService;

	/**
	 * 推荐宝贝页面
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@RequestMapping(value = "treasure_page", method = RequestMethod.GET)
	public String treasurePage() {
		return KemeanConstant.ADMIN_FOLDER + "treasurepage";
	}

	/**
	 * 推荐宝贝数据
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@ResponseBody
	@RequestMapping(value = "treasure_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<TreasureDataBO>> treasureData(TreasureDataPO treasureDataPO) {
		return adminGoodsHotTreasureService.treasureData(treasureDataPO);
	}

	/**
	 * 推荐宝贝上下架
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@ResponseBody
	@RequestMapping(value = "status_operate", method = RequestMethod.GET)
	public KemeanResult<String> statusOperate(@RequestParam Integer objId) {
		return adminGoodsHotTreasureService.statusOperate(objId);
	}

	/**
	 * 删除推荐宝贝
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@ResponseBody
	@RequestMapping(value = "delete_hot_treasure", method = RequestMethod.GET)
	public KemeanResult<String> deleteHotTreasure(@RequestParam Integer objId) {
		return adminGoodsHotTreasureService.deleteHotTreasure(objId);
	}

	/**
	 * 新增推荐宝贝
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@ResponseBody
	@RequestMapping(value = "add_hot_treasure", method = RequestMethod.GET)
	public KemeanResult<String> addHotTreasure(HotTreasurePO hotTreasurePO) {
		return adminGoodsHotTreasureService.addHotTreasure(hotTreasurePO, getLoginAdminShop());
	}

	/**
	 * 替补商品首页推荐宝贝页面
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	@RequestMapping(value = "substitution_goods_treasure_page", method = RequestMethod.GET)
	public String substitutionGoodsTreasurePage() {
		request.setAttribute("shopData", adminGoodsHotTreasureService.getAllShop());
		return "shop/substitutiontreasure";
	}

	/**
	 * 根据商铺id获取商品
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	@ResponseBody
	@RequestMapping(value = "goods_data", method = RequestMethod.GET)
	public List<GoodsDataBO> goodsData(@RequestParam Integer shopId) {
		return adminGoodsHotTreasureService.goodsData(shopId);
	}

	/**
	 * 新增替补推荐宝贝
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	@ResponseBody
	@RequestMapping(value = "add_substitution_hot_treasure", method = RequestMethod.GET)
	public KemeanResult<String> addSubstitutionHotTreasure(@RequestParam Integer goodsId,
			@RequestParam Integer screenPosition, @RequestParam String goodsTitle) {
		return adminGoodsHotTreasureService.addSubstitutionHotTreasure(goodsId, screenPosition, goodsTitle);
	}

}
