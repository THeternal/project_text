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
import com.kemean.service.admin.AdminGoodsRecommendService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.recommend.RecommendDataBO;
import com.kemean.vo.po.admin.recommend.RecommendDataPO;

@Controller
@RequestMapping(value = "admin/recommend")
public class AdminGoodsRecommendController extends DaikenBaseController {

	@Autowired
	private AdminGoodsRecommendService adminGoodsRecommendService;

	@Autowired
	private AdminGoodsHotTreasureService adminGoodsHotTreasureService;

	/**
	 * 首页推荐界面
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	@RequestMapping(value = "recommend_page", method = RequestMethod.GET)
	public String recommendPage() {
		return KemeanConstant.ADMIN_FOLDER + "recommendpage";
	}

	/**
	 * 新增首页推荐替补广告界面
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	@RequestMapping(value = "add_recommend_page", method = RequestMethod.GET)
	public String addRecommendPage() {
		request.setAttribute("shopData", adminGoodsHotTreasureService.getAllShop());
		return KemeanConstant.ADMIN_FOLDER + "substitutionrecommend";
	}

	/**
	 * 新增首页推荐替补广告
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	@ResponseBody
	@RequestMapping(value = "substitution_recommend", method = RequestMethod.GET)
	public KemeanResult<String> substitutionRecommend(@RequestParam Integer typeId, @RequestParam Integer recommendWay,
			@RequestParam String img) {
		return adminGoodsRecommendService.substitutionRecommend(typeId, recommendWay, img, getLoginAdminUser());
	}

	/**
	 * 首页推荐数据
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	@ResponseBody
	@RequestMapping(value = "recommend_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<RecommendDataBO>> recommendData(RecommendDataPO recommendDataPO) {
		return adminGoodsRecommendService.recommendData(recommendDataPO);
	}

	/**
	 * 修改生效状态
	 * 
	 * @author huwei
	 * @data 2018年9月10日
	 */
	@ResponseBody
	@RequestMapping(value = "is_take_effect", method = RequestMethod.GET)
	public KemeanResult<String> isTakeEffect(@RequestParam Integer objId) {
		return adminGoodsRecommendService.isTakeEffect(objId);
	}

}
