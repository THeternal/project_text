package com.kemean.controller.business;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.business.BFinanceService;
import com.kemean.service.common.CommonService;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.b.finance.BOrderFinanceInfoBO;
import com.kemean.vo.bo.b.finance.SettleAccountsListBO;
import com.kemean.vo.bo.com.CloseAccountListBO;
import com.kemean.vo.bo.com.FinanceOrderListBO;
import com.kemean.vo.po.com.FinanceOrderListPO;
import com.kemean.vo.po.com.OrderSettleAccountsPO;

/**
 * 
 * 【商户端】财务、钱包控制器
 * 
 * @Date 2018年7月9日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/b/finance")
public class BFinanceController extends DaikenBaseController {

	@Autowired
	private BFinanceService bfinanceService;

	@Autowired
	private CommonService commonService;

	/**
	 * (冻结资金，可提资金，全部资金)
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	@RequestMapping(value = "freeze_wait_money", method = RequestMethod.GET)
	public KemeanResult<SettleAccountsListBO> freezeWaitMoney() {
		return bfinanceService.freezehWaitMoney(getLoginBusiness());
	}

	/**
	 * 商家端 -- 订单流水
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "order_price_flow_list", method = RequestMethod.POST)
	public KemeanResult<KemeanPageApiBO<List<BOrderFinanceInfoBO>>> orderPriceFlowList(
			@RequestBody @Valid FinanceOrderListPO financeOrderListPO) {
		return new KemeanResult<KemeanPageApiBO<List<BOrderFinanceInfoBO>>>(
				bfinanceService.orderPriceFlowList(financeOrderListPO, getLoginBusiness()));
	}

	/**
	 * 商家端 -- 提现流水
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "finance_order_list", method = RequestMethod.POST)
	public KemeanResult<KemeanPageApiBO<List<FinanceOrderListBO>>> financeOrderList(
			@RequestBody @Valid FinanceOrderListPO financeOrderListPO) {
		return new KemeanResult<KemeanPageApiBO<List<FinanceOrderListBO>>>(
				bfinanceService.financeOrderList(financeOrderListPO, getLoginBusiness()));
	}

	/**
	 * 商家端 -- 结算流水
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	@RequestMapping(value = "close_account_list", method = RequestMethod.POST)
	public KemeanResult<KemeanPageApiBO<List<CloseAccountListBO>>> closeAccountList(
			@Valid @RequestBody FinanceOrderListPO financeOrderListPO) {
		return new KemeanResult<KemeanPageApiBO<List<CloseAccountListBO>>>(
				bfinanceService.closeAccountList(financeOrderListPO, getLoginBusiness()));
	}

	/**
	 * 商家端 -- 提现
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	@RequestMapping(value = "order_settle_accounts", method = RequestMethod.POST)
	public KemeanResult<String> orderSettleAccounts(@Valid @RequestBody OrderSettleAccountsPO orderSettleAccountsPO) {
		return commonService.orderSettleAccounts(orderSettleAccountsPO, getLoginBusiness());
	}
}
