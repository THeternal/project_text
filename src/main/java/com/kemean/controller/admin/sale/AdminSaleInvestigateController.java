package com.kemean.controller.admin.sale;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.DaikenInvestigate.DaikenInvestigateTypeEnum;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminInvestigateService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.investigate.AdminInvestBO;
import com.kemean.vo.bo.admin.investigate.AdminInvestExpendBO;
import com.kemean.vo.po.admin.investigate.AdminInvestExpendPO;
import com.kemean.vo.po.admin.investigate.AdminInvestPO;

/**
 * 调研管理
 * 
 * @Date 2018年6月28日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("sale/invest")
public class AdminSaleInvestigateController extends DaikenBaseController {

	@Autowired
	private AdminInvestigateService adminInvestigateService;

	/**
	 * 调研列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String investListPage() {
		return "sale/invest";
	}

	/**
	 * 调研列表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */

	@ResponseBody
	@RequestMapping(value = "page_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminInvestBO>> investListData(@Valid AdminInvestPO adminInvestPO) {
		return adminInvestigateService.investListData(adminInvestPO, null, getAdminLoginSale());

	}

	/**
	 * 调研--点赞详情 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "like_info_page", method = RequestMethod.GET)
	public String investLikeInfoPage(@RequestParam Integer objId) {
		request.setAttribute("pageData",
				adminInvestigateService.investInfo(objId, DaikenInvestigateTypeEnum.POST_LIKE.getType()));
		return "sale/investLikeInfo";
	}

	/**
	 * 调研--投票详情 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "vote_info_page", method = RequestMethod.GET)
	public String investVoteInfoPage(@RequestParam Integer objId) {
		request.setAttribute("pageData",
				adminInvestigateService.investInfo(objId, DaikenInvestigateTypeEnum.POST_VOTE.getType()));
		return "sale/investVoteInfo";
	}

	/**
	 * 操作调研上下架状态
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@ResponseBody
	@RequestMapping(value = "status_operate", method = RequestMethod.GET)
	public KemeanResult<String> investStatusOperate(@RequestParam Integer objId, @RequestParam Boolean status) {
		return adminInvestigateService.investStatusOperate(objId, status, getLoginAdminUser());

	}

	/**
	 * 调研客户消费page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */
	@RequestMapping(value = "user_expend_page", method = RequestMethod.GET)
	public String investExpendPage(@Valid AdminInvestExpendPO adminInvestExpendPO) {
		request.setAttribute("pageData", adminInvestigateService.investExpendData(adminInvestExpendPO, 15,
				getAdminLoginSale().getReferralCode()));
		return "sale/investUserExpendChart";
	}

	/**
	 * 调研客户消费data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */
	@ResponseBody
	@RequestMapping(value = "user_expend_data", method = RequestMethod.GET)
	public AdminInvestExpendBO investExpendData(AdminInvestExpendPO adminInvestExpendPO) {
		return adminInvestigateService.investExpendData(adminInvestExpendPO, 0, getAdminLoginSale().getReferralCode());
	}

}
