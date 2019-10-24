package com.kemean.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminGoodsHotChargeService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.hot.HotDataBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.charge.ChargeResetPO;

@Controller
@RequestMapping(value = "admin/hot")
public class AdminGoodsHotChargeController extends DaikenBaseController {

	@Autowired
	private AdminGoodsHotChargeService adminGoodsHotChargeService;

	/**
	 * 推荐宝贝收费设置页面
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@RequestMapping(value = "hot_page", method = RequestMethod.GET)
	public String hotPage() {
		return KemeanConstant.ADMIN_FOLDER + "hotpage";
	}

	/**
	 * 推荐宝贝收费设置数据
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@ResponseBody
	@RequestMapping(value = "hot_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<HotDataBO>> hotData(KemeanPageAdminPO kemeanPageAdminPO) {
		return adminGoodsHotChargeService.hotData(kemeanPageAdminPO);
	}

	/**
	 * 重置收取费用
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@ResponseBody
	@RequestMapping(value = "charge_reset", method = RequestMethod.POST)
	public KemeanResult<String> chargeReset(ChargeResetPO chargeResetPO) {
		return adminGoodsHotChargeService.chargeReset(chargeResetPO);
	}

}
