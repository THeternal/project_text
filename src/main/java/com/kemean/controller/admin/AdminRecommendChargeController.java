package com.kemean.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminRecommendChargeService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.charge.ChargeDataBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.charge.ChargeResetPO;

@Controller
@RequestMapping("admin/charge")
public class AdminRecommendChargeController extends DaikenBaseController {

	@Autowired
	private AdminRecommendChargeService adminRecommendChargeService;

	/**
	 * 广告位收费设置页面
	 * 
	 * @author huwei
	 * @date 2018年9月4日
	 */
	@RequestMapping(value = "charge_page", method = RequestMethod.GET)
	public String chargePage() {
		return KemeanConstant.ADMIN_FOLDER + "chargepage";
	}

	/**
	 * 广告位收费设置数据
	 * 
	 * @author huwei
	 * @date 2018年9月4日
	 */
	@ResponseBody
	@RequestMapping(value = "charge_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<ChargeDataBO>> chargeData(KemeanPageAdminPO kemeanPageAdminPO) {
		return adminRecommendChargeService.chargeData(kemeanPageAdminPO);
	}

	/**
	 * 重置广告位收费费用
	 * 
	 * @author huwei
	 * @date 2018年9月4日
	 */
	@ResponseBody
	@RequestMapping(value = "charge_reset", method = RequestMethod.POST)
	public KemeanResult<String> chargeReset(ChargeResetPO chargeResetPO) {
		return adminRecommendChargeService.chargeReset(chargeResetPO);
	}

}
