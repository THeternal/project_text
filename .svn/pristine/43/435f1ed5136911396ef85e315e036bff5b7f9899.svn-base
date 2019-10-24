package com.kemean.controller.common;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.UploadQnService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.b.shop.RecommendChargeBO;
import com.kemean.vo.bo.c.common.ComLunboBO;
import com.kemean.vo.bo.c.mine.finance.EarningsDetailBO;
import com.kemean.vo.bo.com.CBaseUserInfoBO;
import com.kemean.vo.bo.com.ComRegionBO;
import com.kemean.vo.bo.com.ExpressInfoBO;
import com.kemean.vo.bo.com.GetBaseCategoryBBO;
import com.kemean.vo.bo.com.GetBaseCategoryBO;
import com.kemean.vo.bo.com.GetBaseTypeBO;
import com.kemean.vo.bo.com.HelpSellRuleBO;
import com.kemean.vo.bo.com.RecommendGoodsBO;
import com.kemean.vo.bo.com.UserHobbiesinterestsBO;
import com.kemean.vo.bo.com.UserInfoBO;
import com.kemean.vo.po.api.WxAppletsLoginPO;
import com.kemean.vo.po.com.BaseCategoryPO;
import com.kemean.vo.po.com.SendCodePO;
import com.kemean.vo.po.com.UserInfoPO;

/**
 * 
 * 【公共端】公共控制器
 * 
 * @Date 2018年6月12日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/common")
public class CommonController extends DaikenBaseController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	@Autowired
	private UploadQnService uploadQnService;

	/**
	 * 小程序登录
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	@RequestMapping(value = "mini_apps_login", method = RequestMethod.POST)
	public KemeanResult<CBaseUserInfoBO> miniAppsLogin(@Valid @RequestBody WxAppletsLoginPO wxAppletsLoginPO) {
		return userService.miniAppsLogin(wxAppletsLoginPO);
	}

	/**
	 * 发送验证码
	 * 
	 * @author huwei
	 * @date 2018年6月13日
	 */
	@RequestMapping(value = "send_code", method = RequestMethod.POST)
	public KemeanResult<String> sendCode(@Valid @RequestBody SendCodePO sendCodePO) {
		return userService.sendCode(sendCodePO);
	}

	/**
	 * 获取商品分类(小程序)
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	@RequestMapping(value = "get_base_category", method = RequestMethod.POST)
	public KemeanResult<List<GetBaseCategoryBO>> getBaseCategory(@Valid @RequestBody BaseCategoryPO baseCategoryPO) {
		return new KemeanResult<List<GetBaseCategoryBO>>(commonService.getBaseCategory(baseCategoryPO));
	}

	/**
	 * 获取商品分类(商户端)
	 * 
	 * @author huwei
	 * @date 2018年6月28
	 */
	@RequestMapping(value = "get_base_category_b", method = RequestMethod.POST)
	public KemeanResult<List<GetBaseCategoryBBO>> getBaseCategoryB(@Valid @RequestBody BaseCategoryPO baseCategoryPO) {
		return new KemeanResult<List<GetBaseCategoryBBO>>(commonService.getBaseCategoryB(baseCategoryPO));
	}

	/**
	 * 获取规格分类
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	@RequestMapping(value = "get_base_type", method = RequestMethod.GET)
	public KemeanResult<List<GetBaseTypeBO>> getBaseType(@RequestParam Integer objId) {
		return new KemeanResult<List<GetBaseTypeBO>>(commonService.getBaseType(objId));
	}

	/**
	 * 获取用户的兴趣爱好
	 * 
	 * @author huwei
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "get_user_base", method = RequestMethod.GET)
	public KemeanResult<List<UserHobbiesinterestsBO>> getUserHobbiesinterests(@RequestParam Integer type) {
		return new KemeanResult<List<UserHobbiesinterestsBO>>(commonService.getUserHobbiesinterests(type));
	}

	/**
	 * 获取用户职业
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "get_user_occupation", method = RequestMethod.GET)
	public KemeanResult<List<UserHobbiesinterestsBO>> getUserOccupation(@RequestParam Integer pid) {
		return new KemeanResult<List<UserHobbiesinterestsBO>>(commonService.getUserOccupation(pid));
	}

	/**
	 * 全国地区联动
	 * 
	 * @author huangyujian
	 * @date 2018年3月8日
	 */
	@ResponseBody
	@RequestMapping(value = "region", method = RequestMethod.GET)
	public KemeanResult<List<ComRegionBO>> region(@RequestParam Integer pid) {
		return new KemeanResult<List<ComRegionBO>>(commonService.region(pid));
	}

	/**
	 * 轮播图
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	@RequestMapping(value = "lunbo", method = RequestMethod.GET)
	public KemeanResult<List<ComLunboBO>> lunbo(@RequestParam Integer locatType) {
		return new KemeanResult<List<ComLunboBO>>(commonService.lunbo(locatType));
	}

	/**
	 * 收益明细
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	@RequestMapping(value = "finance/earnings_detail", method = RequestMethod.GET)
	public KemeanResult<EarningsDetailBO> earningsDetail(@RequestParam String wxOpenId) {
		return userService.earningsDetail(wxOpenId);
	}

	/**
	 * 提示信息
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	@RequestMapping(value = "prompt_message", method = RequestMethod.GET)
	public KemeanResult<HelpSellRuleBO> promptMessage(@RequestParam Integer type) {
		return commonService.promptMessage(type);
	}

	/**
	 * 获取推荐商品、店铺信息（首页推荐）
	 * 
	 * @author huwei
	 * @date 2018年7月17日
	 */
	@RequestMapping(value = "recommend_goods", method = RequestMethod.GET)
	public KemeanResult<RecommendGoodsBO> recommendGoods() {
		return commonService.recommendGoods();
	}

	/**
	 * 根据UID获取头像昵称信息
	 * 
	 * @author huwei
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "get_user_info", method = RequestMethod.POST)
	public KemeanResult<List<UserInfoBO>> getUserInfo(@Valid @RequestBody UserInfoPO userInfoPO) {
		return commonService.getUserInfo(userInfoPO);
	}

	/**
	 * 查询物流信息
	 * 
	 * @author huwei
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "get_express_info", method = RequestMethod.GET)
	public KemeanResult<ExpressInfoBO> getExpressInfo(@RequestParam String orderNo) {
		return commonService.getExpressInfo(orderNo);
	}

	/**
	 * 七牛上传图片
	 * 
	 * @author huwei
	 * @date 2018年7月27日
	 */
	@RequestMapping(value = "qiniu_batch_upload", method = RequestMethod.POST)
	public KemeanResult<String> qiniuBatchUpload(MultipartFile file) {
		Date now = new Date();
		String uploadOne = uploadQnService.uploadOne(file, now);
		return new KemeanResult<String>(uploadOne);
	}

	/**
	 * 商品推荐 获取收取的费用
	 * 
	 * @author huwei
	 * @date 2018年9月4日
	 */
	@RequestMapping(value = "get_click_show_charge", method = RequestMethod.GET)
	public KemeanResult<RecommendChargeBO> getClickShowCharge(@RequestParam Integer hours) {
		return new KemeanResult<>(commonService.getClickShowCharge(hours));
	}
}
