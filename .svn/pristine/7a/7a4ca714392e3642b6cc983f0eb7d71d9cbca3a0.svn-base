package com.kemean.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminFinanceService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.admin.finance.AdminFinanceBO;
import com.kemean.vo.po.admin.finance.AdminFinancePO;

/**
 * 财务
 * 
 * @Date 2018年7月26日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class AdminFinanceController extends DaikenBaseController {

	@Autowired
	private AdminFinanceService adminFinanceService;

	/**
	 * 财务流水列表-支付宝转账
	 * 
	 * @author tanggengxiang
	 * @date 2018年3月29日
	 */
	@RequestMapping(value = "finance_list_ali", method = RequestMethod.GET)
	public String finaceListAli() {
		return KemeanConstant.ADMIN_FOLDER + "financeListAli";
	}

	/***
	 * 财务流水 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年3月29日
	 */
	@ResponseBody
	@RequestMapping(value = "finance_list_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminFinanceBO>> finaceListData(AdminFinancePO adminFinancePO) {
		return adminFinanceService.financeListData(adminFinancePO);
	}

}
