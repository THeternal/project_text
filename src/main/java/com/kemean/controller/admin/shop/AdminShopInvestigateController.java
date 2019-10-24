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

import com.kemean.constant.DaikenConstant;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateTypeEnum;
import com.kemean.constant.DaikenUserEnum.DaikenUserBaseEnum;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.AdminInvestigateService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.investigate.AdminInvestBO;
import com.kemean.vo.bo.admin.investigate.AdminInvestOptionsBO;
import com.kemean.vo.bo.admin.investigate.AdminInvestReportBO;
import com.kemean.vo.bo.admin.user.AdminUserBO;
import com.kemean.vo.bo.c.investigate.InvestigateOrderBO;
import com.kemean.vo.po.admin.investigate.AdminInvestOptionsPO;
import com.kemean.vo.po.admin.investigate.AdminInvestPO;
import com.kemean.vo.po.admin.investigate.AdminInvestReportPO;
import com.kemean.vo.po.admin.investigate.AdminInvestigateAddPO;
import com.kemean.vo.po.admin.investigate.AdminPrecisionConditionPO;
import com.kemean.vo.po.admin.investigate.AdminPrecisionUserPO;

/**
 * 调研管理
 * 
 * @Date 2018年6月28日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Controller
@RequestMapping("shop/invest")
public class AdminShopInvestigateController extends DaikenBaseController {

	@Autowired
	private AdminInvestigateService adminInvestigateService;

	@Autowired
	private AdminCommonService adminCommonService;

	/**
	 * 调研总列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String investPage() {
		return "shop/invest";
	}

	/**
	 * 调研投票列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "vote_page", method = RequestMethod.GET)
	public String investVotePage() {
		return "shop/investVote";
	}

	/**
	 * 调研点赞列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "like_page", method = RequestMethod.GET)
	public String investLikePage() {
		return "shop/investLike";
	}

	/**
	 * 调研问卷调查列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "question_page", method = RequestMethod.GET)
	public String investQuestionPage() {
		return "shop/investQuestion";
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
		return adminInvestigateService.investListData(adminInvestPO, getLoginAdminShop(), null);

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
		return "shop/investLikeInfo";
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
		return "shop/investVoteInfo";
	}

	/**
	 * 投票--选手列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@ResponseBody
	@RequestMapping(value = "vote_player_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminInvestOptionsBO>> invsetVotePlayerData(@RequestParam Integer objId,
			@RequestParam Integer page, @RequestParam Integer limit) {
		return adminInvestigateService.invsetVotePlayerData(objId, page, limit);
	}

	/**
	 * 修改选手信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@ResponseBody
	@RequestMapping(value = "vote_player_edit", method = RequestMethod.POST)
	public KemeanResult<String> invsetVotePlayerEdit(@Valid AdminInvestOptionsPO adminInvestOptionsPO) {
		return adminInvestigateService.invsetVotePlayerEdit(adminInvestOptionsPO);

	}

	/**
	 * 调研--问卷调查详情 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@RequestMapping(value = "question_info_page", method = RequestMethod.GET)
	public String investQuestionInfoPage(@RequestParam Integer objId) {
		request.setAttribute("pageData",
				adminInvestigateService.investInfo(objId, DaikenInvestigateTypeEnum.QUESTIONNAIRE.getType()));
		return "shop/investQuestionInfo";
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
		return adminInvestigateService.investStatusOperate(objId, status, null);

	}

	/**
	 * 删除调研
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月2日
	 */
	@ResponseBody
	@RequestMapping(value = "del", method = RequestMethod.GET)
	public KemeanResult<String> investDel(@RequestParam Integer objId) {
		return adminInvestigateService.investDel(objId);

	}

	/**
	 * 发布/修改点赞信息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@ResponseBody
	@RequestMapping(value = "like_operate", method = RequestMethod.POST)
	public KemeanResult<InvestigateOrderBO> investLikeOperate(
			@Valid @RequestBody AdminInvestigateAddPO adminInvestigateAddPO) {
		return adminInvestigateService.investLikeOperate(adminInvestigateAddPO, getLoginAdminShop(),
				DaikenConstant.shop_back_ground_issue);

	}

	/**
	 * 发布/修改问卷信息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@ResponseBody
	@RequestMapping(value = "question_operate", method = RequestMethod.POST)
	public KemeanResult<String> investQuestionOperate(@Valid @RequestBody AdminInvestigateAddPO adminInvestigateAddPO) {
		return adminInvestigateService.investQuestionOperate(adminInvestigateAddPO, getLoginAdminShop(),
				DaikenConstant.shop_back_ground_issue);

	}

	/**
	 * 发布/修改投票信息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */
	@ResponseBody
	@RequestMapping(value = "vote_operate", method = RequestMethod.POST)
	public KemeanResult<InvestigateOrderBO> investVoteOperate(
			@Valid @RequestBody AdminInvestigateAddPO adminInvestigateAddPO) {
		return adminInvestigateService.investVoteOperate(adminInvestigateAddPO, getLoginAdminShop(),
				DaikenConstant.shop_back_ground_issue);

	}

	/**
	 * 发布投票信息 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */

	@RequestMapping(value = "vote_add_page", method = RequestMethod.GET)
	public String investVoteAddPage() {
		return "shop/investVoteAdd";
	}

	/**
	 * 发布点赞信息 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月28日
	 */

	@RequestMapping(value = "like_add_page", method = RequestMethod.GET)
	public String investLikeAddPage() {
		return "shop/investLikeAdd";
	}

	/**
	 * 发布问卷调查信息 page
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	@RequestMapping(value = "question_add_page", method = RequestMethod.GET)
	public String investQuestionnaireAddPage() {
		return "shop/investQuestionAdd";
	}

	/**
	 * 报表(点赞)page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */

	@RequestMapping(value = "like_report", method = RequestMethod.GET)
	public String investLikeReport() {
		return "shop/investLikeReport";
	}

	/**
	 * 报表(问卷)page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */

	@RequestMapping(value = "question_report", method = RequestMethod.GET)
	public String investQuestionReport() {
		return "shop/investQuestionReport";
	}

	/**
	 * 报表(投票)page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */

	@RequestMapping(value = "vote_report", method = RequestMethod.GET)
	public String investVoteReport() {
		return "shop/investVoteReport";
	}

	/**
	 * （点赞、投票）报表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月3日
	 */
	@ResponseBody
	@RequestMapping(value = "report_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminInvestReportBO>> investReportData(
			@Valid AdminInvestReportPO adminInvestReportPO) {
		return adminInvestigateService.investReportData(adminInvestReportPO, getLoginAdminShop());
	}

	/**
	 * 调研参与人数列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "join_user_page", method = RequestMethod.GET)
	public String investJoinUserPage() {
		return "shop/investJoinUser";
	}

	/**
	 * 调研参与人数列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@ResponseBody
	@RequestMapping(value = "join_user_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserBO>> investJoinUserData(@Valid AdminInvestPO adminInvestPO) {
		return adminInvestigateService.investJoinUserData(adminInvestPO, getLoginAdminShop());
	}

	/**
	 * 点赞调研参与人数列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "like_join_user_page", method = RequestMethod.GET)
	public String investLikeJoinUserPage(@RequestParam Integer investId) {
		request.setAttribute("investData", adminInvestigateService.investIdAndName(investId));
		return "shop/investLikeJoinUser";
	}

	/**
	 * 投票调研参与人数列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "vote_join_user_page", method = RequestMethod.GET)
	public String investVoteJoinUserPage(@RequestParam Integer investId) {
		request.setAttribute("investData", adminInvestigateService.investIdAndName(investId));
		return "shop/investVoteJoinUser";
	}

	/**
	 * 问卷调查调研参与人数列表page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月24日
	 */
	@RequestMapping(value = "question_join_user_page", method = RequestMethod.GET)
	public String investQuestionJoinUserPage(@RequestParam Integer investId) {
		request.setAttribute("investData", adminInvestigateService.investIdAndName(investId));
		return "shop/investQuestionJoinUser";
	}

	/**
	 * 调研--问卷调查 用户答案
	 * 
	 * @author tanggengxiang
	 * @date 2018年8月27日
	 */

	@RequestMapping(value = "question_join_user_answer_page", method = RequestMethod.GET)
	public String investQuestionJoinUserAnswerPage(@RequestParam String questionStr) {
		request.setAttribute("pageData", adminInvestigateService.investQuestionJoinUserAnswerData(questionStr));
		return "shop/investQuestionJoinUserAnswer";
	}

	/**
	 * 精准投放page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@RequestMapping(value = "precision_put_page", method = RequestMethod.GET)
	public String precisionPutPage(@RequestParam Integer investId) {
		request.setAttribute("investId", investId);
		request.setAttribute("userInterestData",
				adminCommonService.userBaseType(DaikenUserBaseEnum.INTEREST.getType(), 0));
		request.setAttribute("userJobData", adminCommonService.userBaseType(DaikenUserBaseEnum.JOB.getType(), 0));
		return "shop/investPutUser";
	}

	/**
	 * 精准投放 条件匹配条件
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "precision_put_user_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminUserBO>> precisionPutUserData(
			@Valid AdminPrecisionConditionPO adminPrecisionConditionPO) {
		return adminInvestigateService.precisionPutUserData(adminPrecisionConditionPO);
	}

	/**
	 * 保存推送的用户
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月12日
	 */
	@ResponseBody
	@RequestMapping(value = "precision_operate", method = RequestMethod.POST)
	public KemeanResult<String> precisionOperate(@Valid @RequestBody AdminPrecisionUserPO adminPrecisionUserPO) {
		return adminInvestigateService.precisionOperate(adminPrecisionUserPO);
	}

}
