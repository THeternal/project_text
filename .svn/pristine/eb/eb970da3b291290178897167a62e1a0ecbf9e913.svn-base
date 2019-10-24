package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.KemeanAdminAccountService;
import com.kemean.vo.bo.KemeanIdAndName;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewCategoryBO;
import com.kemean.vo.po.admin.AdminDatePO;

/**
 * 管理后台公共
 * 
 * @Date 2018年4月15日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/common")
public class AdminCommonController extends DaikenBaseController {

	@Autowired
	private KemeanAdminAccountService kemeanAdminAccountService;

	@Autowired
	private AdminCommonService adminCommonService;

	/**
	 * 管理后台主页
	 * 
	 * @author huangyujian
	 * @date 2018年4月16日
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		KemeanAdminUser loginAdminUser = getLoginAdminUser();
		request.setAttribute("loginUser", loginAdminUser);
		request.setAttribute("menuData",
				JSONObject.toJSONString(kemeanAdminAccountService.getAdminMenu(loginAdminUser.getSuperAdmin(),
						loginAdminUser.getMenus(), KemeanConstant.PLATFORM_ADMIN_MENU_TYPE, request)));
		return "home";
	}

	/**
	 * 管理后台消息
	 * 
	 * @author huangyujian
	 * @date 2018年4月16日
	 */
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public String message() {
		request.setAttribute("pageData", adminCommonService.messageCount());
		return "message";
	}

	/**
	 * 获取商品分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */

	@ResponseBody
	@RequestMapping(value = "goods_category", method = RequestMethod.GET)
	public KemeanResult<List<AdminGoodsNewCategoryBO>> goodsBeseCategory(@RequestParam Integer pid) {
		return new KemeanResult<List<AdminGoodsNewCategoryBO>>(adminCommonService.goodsBeseCategory(pid));
	}

	/**
	 * 获取商品规格
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	@ResponseBody
	@RequestMapping(value = "goods_baseType", method = RequestMethod.GET)
	public KemeanResult<List<KemeanIdAndName>> goodsBeseType(@RequestParam Integer objId) {
		return new KemeanResult<List<KemeanIdAndName>>(adminCommonService.goodsBeseType(objId));
	}

	/**
	 * 获取商品规格
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	@ResponseBody
	@RequestMapping(value = "user_base_data", method = RequestMethod.GET)
	public List<KemeanIdAndName> userBaseType(@RequestParam Integer type, @RequestParam Integer pid) {
		return adminCommonService.userBaseType(type, pid);
	}

	/**
	 * 平台管理统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月26日
	 */
	@RequestMapping(value = "chart", method = RequestMethod.GET)
	public String chart(@Valid AdminDatePO adminDatePO) {
		request.setAttribute("loginUser", getLoginAdminUser());
		request.setAttribute("pageData", adminCommonService.chart(adminDatePO));
		return "chart";
	}

	/**
	 * 客服上下线操作
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月7日
	 */
	@ResponseBody
	@RequestMapping(value = "online_operate", method = RequestMethod.GET)
	public KemeanResult<String> onlineOperate(@RequestParam Integer userUid, @RequestParam Boolean status) {
		return adminCommonService.onlineOperate(userUid, status);
	}

	/**
	 * 用户活动轨迹page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */
	@RequestMapping(value = "push_user_record_page", method = RequestMethod.GET)
	public String UserRecordPage(@RequestParam Integer userId) {
		request.setAttribute("pageData", adminCommonService.goodsPushUserRecord(userId));
		return KemeanConstant.ADMIN_FOLDER + "userRecord";
	}

	/**
	 * 管理运营人员，普通运营人员信息添加
	 * 
	 * @author huwei
	 * @date 2018年10月10日
	 */
	@ResponseBody
	@RequestMapping(value = "add_user_info", method = RequestMethod.GET)
	public KemeanResult<String> addUserInfo(@RequestParam String wxNum, @RequestParam String phone,
			@RequestParam String email) {
		return adminCommonService.addUserInfo(wxNum, phone, email, getLoginAdminUser());
	}

}
