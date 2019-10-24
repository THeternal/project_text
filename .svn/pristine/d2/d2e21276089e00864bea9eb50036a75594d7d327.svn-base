package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.AdminUserService;
import com.kemean.service.common.CommonService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.AdminChartBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.bo.admin.user.AdminUserFinanceOrderBO;
import com.kemean.vo.bo.admin.user.AdminUserTokenBO;
import com.kemean.vo.po.admin.AdminDatePO;
import com.kemean.vo.po.admin.investigate.AdminInvestChartPO;
import com.kemean.vo.po.admin.user.AdminRechargePO;
import com.kemean.vo.po.admin.user.AdminUserFinanceOrderPO;
import com.kemean.vo.po.admin.user.AdminUserListPO;
import com.kemean.vo.po.admin.user.AdminUserOrderListPO;
import com.kemean.vo.po.admin.user.AdminUserStatusPO;

/**
 * 平台用户管理
 * 
 * @Date 2018年6月28日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("admin/user")
public class AdminUserController extends DaikenBaseController {

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AdminCommonService adminCommonService;

	/**
	 * 用户信息列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userPage() {
		return KemeanConstant.ADMIN_FOLDER + "user";
	}

	/**
	 * 用户信息列表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@ResponseBody
	@RequestMapping(value = "page_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserBO>> userPageData(@Valid AdminUserListPO adminUserListPO) {
		return adminUserService.userPageData(adminUserListPO, null);
	}

	/**
	 * 操作用户状态 禁用/正常
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */

	@ResponseBody
	@RequestMapping(value = "status_operate", method = RequestMethod.GET)
	public KemeanResult<String> userStatusOperate(@Valid AdminUserStatusPO adminUserStatusPO) {
		return adminUserService.userStatusOperate(adminUserStatusPO);
	}

	/**
	 * 修改用户密码
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月16日
	 */
	@ResponseBody
	@RequestMapping(value = "user_reset_password", method = RequestMethod.GET)
	public KemeanResult<String> userResetPassword(@RequestParam Integer objId, @RequestParam String password) {
		adminUserService.userResetPassword(objId, password);
		return new KemeanResult<>();
	}

	/**
	 * 用户详情
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月17日
	 */
	@RequestMapping(value = "user_info_page", method = RequestMethod.GET)
	public String userInfoData(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminUserService.userInfoData(objId));
		request.setAttribute("provinceData", commonService.region(0));
		return KemeanConstant.ADMIN_FOLDER + "userInfo";
	}

	/**
	 * 用户类型统计（数量）
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */

	@RequestMapping(value = "chart_page", method = RequestMethod.GET)
	public String userCountChart() {
		request.setAttribute("pageData", adminUserService.userChart());
		return KemeanConstant.ADMIN_FOLDER + "userChart";
	}

	/**
	 * 用户消费统计
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@RequestMapping(value = "consum_chart_page", method = RequestMethod.GET)
	public String userConsumCountChartPage(@Valid AdminInvestChartPO adminChartsPO) {
		request.setAttribute("pageData", adminUserService.userConsumChart(adminChartsPO, 15));
		return KemeanConstant.ADMIN_FOLDER + "userConsumChart";
	}

	/**
	 * 用户消费统计(查询)
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@ResponseBody
	@RequestMapping(value = "consum_chart_search", method = RequestMethod.GET)
	public List<AdminChartBO> userConsumCountChartData(@Valid AdminInvestChartPO adminChartsPO) {
		return adminUserService.userConsumChart(adminChartsPO, 0);
	}

	/**
	 * 用户下单量分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@RequestMapping(value = "order_num_chart_page", method = RequestMethod.GET)
	public String userOrderNumChartPage(@Valid AdminDatePO adminDatePO) {
		request.setAttribute("pageData", adminCommonService.userOrderNumChart(adminDatePO, 15));
		return KemeanConstant.ADMIN_FOLDER + "userOrderNumChart";
	}

	/**
	 * 用户下单量data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@ResponseBody
	@RequestMapping(value = "order_num_chart_search", method = RequestMethod.GET)
	public String userOrderCountNumChartData(@Valid AdminDatePO adminChartsPO) {
		return adminCommonService.userOrderNumChart(adminChartsPO, 0);
	}

	/**
	 * 用户下单金额分析
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@RequestMapping(value = "order_money_chart_page", method = RequestMethod.GET)
	public String userOrderMoneyChartPage(@Valid AdminDatePO adminDatePO) {
		request.setAttribute("pageData", adminCommonService.userOrderMoneyChart(adminDatePO, 15));
		return KemeanConstant.ADMIN_FOLDER + "userOrderMoneyChart";
	}

	/**
	 * 用户下单金额data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月7日
	 */
	@ResponseBody
	@RequestMapping(value = "order_money_chart_serach", method = RequestMethod.GET)
	public String userOrderMoneyChartData(@Valid AdminDatePO adminDatePO) {
		return adminCommonService.userOrderMoneyChart(adminDatePO, 0);
	}

	/**
	 * 用户收入page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */

	@RequestMapping(value = "user_in_money_page", method = RequestMethod.GET)
	public String userFinanceOrderPage(@Valid AdminUserFinanceOrderPO adminUserFinanceOrderPO) {
		request.setAttribute("pageData", adminUserService.userInMoneyChart(adminUserFinanceOrderPO, 0));
		return KemeanConstant.ADMIN_FOLDER + "userInOrderChart";
	}

	/**
	 * 用户收入data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月16日
	 */
	@ResponseBody
	@RequestMapping(value = "user_in_money_data", method = RequestMethod.GET)
	public List<AdminChartBO> userFinanceOrderData(@Valid AdminUserFinanceOrderPO adminUserFinanceOrderPO) {
		return adminUserService.userInMoneyChart(adminUserFinanceOrderPO, 0);
	}

	/**
	 * 充值
	 * 
	 * @author huangyujian
	 * @date 2018年4月27日
	 */
	@ResponseBody
	@RequestMapping(value = "recharge", method = RequestMethod.POST)
	public KemeanResult<String> recharge(@Valid AdminRechargePO adminRechargePO) {
		return adminUserService.recharge(adminRechargePO, getLoginAdminUser());
	}

	/**
	 * 用户流水账page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月20日
	 */
	@RequestMapping(value = "user_order_page", method = RequestMethod.GET)
	public String userOrderListPage() {
		return KemeanConstant.ADMIN_FOLDER + "userOrderList";
	}

	/**
	 * 用户流水账data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月20日
	 */
	@ResponseBody
	@RequestMapping(value = "user_order_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserFinanceOrderBO>> userOrderListData(
			@Valid AdminUserOrderListPO adminUserOrderListPO) {
		return adminUserService.userOrderListData(adminUserOrderListPO);
	}

	/**
	 * 用户token统计page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@RequestMapping(value = "user_token_page", method = RequestMethod.GET)
	public String userTokenPage(@Valid AdminDatePO adminDatePO) {
		request.setAttribute("pageData", adminUserService.userTokenData(adminDatePO, 15));
		return KemeanConstant.ADMIN_FOLDER + "userToken";
	}

	/**
	 * 用户token统计data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月23日
	 */
	@ResponseBody
	@RequestMapping(value = "user_token_data", method = RequestMethod.GET)
	public AdminUserTokenBO userTokenData(@Valid AdminDatePO adminDatePO) {
		return adminUserService.userTokenData(adminDatePO, 0);
	}
}
