package com.kemean.controller.admin.shop;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminShopService;
import com.kemean.service.business.BShopService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.shop.AdminShopFinanceClearBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.shop.AdminShopFinanceClearPO;
import com.kemean.vo.po.admin.shop.AdminShopReplyBO;
import com.kemean.vo.po.admin.shop.AdminShopReplyPO;
import com.kemean.vo.po.admin.shop.AdminShopUpdatePO;
import com.kemean.vo.po.b.shop.SubmitSettledInfoPO;

/**
 * 店铺管理
 * 
 * @Date 2018年6月21日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("shop/info")
public class AdminShopInfoController extends DaikenBaseController {

	@Autowired
	private AdminShopService adminShopService;

	@Autowired
	private BShopService bshopService;

	/**
	 * 主页
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {
		request.setAttribute("shopData", adminShopService.shopInfoData(null, getLoginAdminShop()));
		request.setAttribute("shopSettled", adminShopService.shopSettledInfoData(getLoginAdminShop().getShopId()));
		return "shop/home";
	}

	/**
	 * 商家首页统计图
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月16日
	 */
	@RequestMapping(value = "chart", method = RequestMethod.GET)
	public String chart() {
		request.setAttribute("pageData", adminShopService.chartData(getLoginAdminShop()));
		return "shop/chart";
	}

	/**
	 * 更新商铺入驻详情信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月2日
	 */
	@RequestMapping(value = "settled_info_page", method = RequestMethod.GET)
	public String shopSeettledPage() {
		request.setAttribute("shopSettled", adminShopService.shopSettledInfoData(getLoginAdminShop().getShopId()));
		return "shop/shopSettled";
	}

	/**
	 * 商家入驻审核结果页面
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月2日
	 */
	@RequestMapping(value = "settled_result", method = RequestMethod.GET)
	public String settledResultPage() {
		request.setAttribute("shopSettled", adminShopService.shopSettledInfoData(getLoginAdminShop().getShopId()));
		return "shop/settledResult";
	}

	/**
	 * 修改入驻信息
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@ResponseBody
	@RequestMapping(value = "submit_settled_info", method = RequestMethod.POST)
	public KemeanResult<String> submitSettledInfo(@Valid @RequestBody SubmitSettledInfoPO submitSettledInfoPO) {
		return bshopService.submitSettledInfo(submitSettledInfoPO, getLoginAdminShop());
	}

	/**
	 * 商铺详情 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String shopInfoPage() {
		request.setAttribute("pageData", adminShopService.shopInfoData(null, getLoginAdminShop()));
		return "shop/shopInfo";
	}

	/**
	 * 修改商铺信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月23日
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public KemeanResult<String> shopInfoUpdate(@Valid AdminShopUpdatePO adminShopUpdatePO) {
		return adminShopService.shopInfoUpdate(adminShopUpdatePO, getLoginAdminShop());
	}

	/**
	 * 店铺默认消息回复管理
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "reply_page", method = RequestMethod.GET)
	public String shopMsgReplyPage() {
		return "shop/shopReply";
	}

	/**
	 * 店铺默认消息回复data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月9日
	 */
	@ResponseBody
	@RequestMapping(value = "reply_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopReplyBO>> shopMsgReplyData(@Valid KemeanPageAdminPO kemeanPageAdminPO) {
		return adminShopService.shopMsgReplyData(kemeanPageAdminPO, getLoginAdminShop());
	}

	/**
	 * 删除消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	@ResponseBody
	@RequestMapping(value = "reply_data_del", method = RequestMethod.GET)
	public KemeanResult<String> shopMsgReplyDel(@RequestParam Integer objId) {
		adminShopService.shopMsgReplyDel(objId);
		return new KemeanResult<>();
	}

	/**
	 * 添加/修改消息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月10日
	 */
	@ResponseBody
	@RequestMapping(value = "reply_operate", method = RequestMethod.POST)
	public KemeanResult<String> shopMsgReplyOperate(@Valid AdminShopReplyPO adminShopReplyPO) {
		adminShopService.shopMsgReplyOperate(adminShopReplyPO, getLoginAdminShop());
		return new KemeanResult<>();
	}

	/**
	 * 充值记录page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	@RequestMapping(value = "recharge_page", method = RequestMethod.GET)
	public String rechargePage() {
		return "shop/recharge";

	}

	/**
	 * 提现记录page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	@RequestMapping(value = "withdraw_page", method = RequestMethod.GET)
	public String withdrawPage() {
		return "shop/withdraw";

	}

	/**
	 * 充值、提现记录data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	@ResponseBody
	@RequestMapping(value = "finance_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminShopFinanceClearBO>> shopFinanceClearData(
			@Valid AdminShopFinanceClearPO adminShopFinanceClearPO) {
		return adminShopService.shopFinanceClearData(adminShopFinanceClearPO, getLoginAdminShop(), "");

	}

	/**
	 * 店铺流水报表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月17日
	 */
	@RequestMapping(value = "financeWater_page", method = RequestMethod.GET)
	public String shopFinanceWaterPage() {
		request.setAttribute("pageData", adminShopService.shopFinanceWaterData(null, getLoginAdminShop(), 15));
		return "shop/financeWater";
	}

	/**
	 * 操作是否营业
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月18日
	 */
	@ResponseBody
	@RequestMapping(value = "workstatus_operate", method = RequestMethod.GET)
	public KemeanResult<String> workstatusOperate(@RequestParam Boolean workStatus) {
		return adminShopService.workstatusOperate(workStatus, getLoginAdminShop());

	}

}
