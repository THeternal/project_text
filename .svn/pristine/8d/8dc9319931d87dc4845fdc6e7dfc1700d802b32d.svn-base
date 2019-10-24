package com.kemean.service.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanFinanceEnum;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.b.finance.BOrderFinanceInfoBO;
import com.kemean.vo.bo.b.finance.SettleAccountsListBO;
import com.kemean.vo.bo.com.CloseAccountListBO;
import com.kemean.vo.bo.com.FinanceOrderListBO;
import com.kemean.vo.po.com.FinanceOrderListPO;

@Service
public class BFinanceService {

	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	@Autowired
	private KemeanFinanceOrderDaikenDao kemeanFinanceOrderDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private UserService userService;

	/**
	 * (冻结资金，可提资金，全部资金)
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	public KemeanResult<SettleAccountsListBO> freezehWaitMoney(DaikenUser loginBusiness) {
		SettleAccountsListBO bo = new SettleAccountsListBO();
		// 可提现资金 超过7天的订单资金
		DaikenShop dbShop = daikenShopDao.selectById(loginBusiness.getShopId());
		Date cycleDate = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, -dbShop.getCycle());
		// 可提资金 = 未冻结资金 - 已提现 - 提现中
		bo.setWaitMoney(userService.getConsumerOrBusinessBlance(loginBusiness));
		// 冻结资金 没超过7天的订单资金
		Double freezeMoney = kemeanFinanceOrderDao.freezeMoney(loginBusiness.getId(), cycleDate);
		bo.setFreezeMoney(DaikenUtil.formatDouble(freezeMoney, 2));
		// 总资金
		Double totalAssets = kemeanFinanceOrderDao.totalAssets(loginBusiness.getId());
		bo.setTotalAssets(DaikenUtil.formatDouble(totalAssets, 2));
		return new KemeanResult<SettleAccountsListBO>(bo);
	}

	/**
	 * 提现流水
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	public KemeanPageApiBO<List<FinanceOrderListBO>> financeOrderList(FinanceOrderListPO financeOrderListPO,
			DaikenUser loginBusiness) {
		Integer day = 7;
		if (financeOrderListPO.getDays() != null) {
			day = financeOrderListPO.getDays();
		}
		Date plusDay = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, -day);
		List<KemeanFinanceOrder> dbFinanceOrders = kemeanFinanceOrderDao.financeOrderList(plusDay,
				loginBusiness.getShopId(), financeOrderListPO.getPageNo(), financeOrderListPO.getPageSize());
		List<FinanceOrderListBO> result = new ArrayList<FinanceOrderListBO>(dbFinanceOrders.size());
		if (CollectionUtils.isEmpty(dbFinanceOrders)) {
			return new KemeanPageApiBO<List<FinanceOrderListBO>>(0l, 1, result);
		}
		for (KemeanFinanceOrder kemeanFinanceOrder : dbFinanceOrders) {
			FinanceOrderListBO bo = new FinanceOrderListBO();
			bo.setOrderNo(kemeanFinanceOrder.getOrderNo());
			bo.setOrderPrice(kemeanFinanceOrder.getMoney());
			bo.setOrderFinishTime(
					DaikenUtil.formatDate(kemeanFinanceOrder.getFinanceData(), KemeanDateFormatEnum.DATE));
			result.add(bo);
		}
		PageInfo<KemeanFinanceOrder> pageInfo = new PageInfo<KemeanFinanceOrder>(dbFinanceOrders);
		return new KemeanPageApiBO<List<FinanceOrderListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 商家端 -- 订单流水
	 * 
	 * @author huwei
	 * @date 2018年7月16日
	 */
	public KemeanPageApiBO<List<BOrderFinanceInfoBO>> orderPriceFlowList(FinanceOrderListPO financeOrderListPO,
			DaikenUser loginBusiness) {
		Integer day = 7;
		if (financeOrderListPO.getDays() != null) {
			day = financeOrderListPO.getDays();
		}
		Date plusDay = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, -day);
		List<DaikenOrder> dbOrders = daikenOrderDao.orderPriceFlowList(plusDay, loginBusiness.getShopId(),
				financeOrderListPO.getPageNo(), financeOrderListPO.getPageSize());
		List<BOrderFinanceInfoBO> result = new ArrayList<BOrderFinanceInfoBO>(dbOrders.size());
		if (CollectionUtils.isEmpty(dbOrders)) {
			return new KemeanPageApiBO<List<BOrderFinanceInfoBO>>(0l, 1, result);
		}
		for (DaikenOrder daikenOrder : dbOrders) {
			BOrderFinanceInfoBO bo = new BOrderFinanceInfoBO();
			bo.setOrderFinishTime(DaikenUtil.formatDate(daikenOrder.getReceiveGoodsTime(), KemeanDateFormatEnum.DATE));
			bo.setOrderNo(daikenOrder.getOrderNo());
			bo.setOrderPrice(daikenOrder.getPricePay());
			result.add(bo);
		}
		PageInfo<DaikenOrder> pageInfo = new PageInfo<DaikenOrder>(dbOrders);
		return new KemeanPageApiBO<List<BOrderFinanceInfoBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 结算流水
	 * 
	 * @author huwei
	 * @date 2018年7月14日
	 */
	public KemeanPageApiBO<List<CloseAccountListBO>> closeAccountList(FinanceOrderListPO financeOrderListPO,
			DaikenUser loginBusiness) {
		Integer day = 7;
		if (financeOrderListPO.getDays() != null) {
			day = financeOrderListPO.getDays();
		}
		Date plusDay = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, -day);
		List<KemeanFinanceClear> dbFinanceClears = kemeanFinanceClearDao.closeAccountList(plusDay,
				loginBusiness.getShopId(), financeOrderListPO.getPageNo(), financeOrderListPO.getPageSize());
		List<CloseAccountListBO> result = new ArrayList<CloseAccountListBO>(dbFinanceClears.size());
		if (CollectionUtils.isEmpty(dbFinanceClears)) {
			return new KemeanPageApiBO<List<CloseAccountListBO>>(0l, 1, result);
		}
		for (KemeanFinanceClear dbFinanceClear : dbFinanceClears) {
			CloseAccountListBO bo = new CloseAccountListBO();
			BeanUtils.copyProperties(dbFinanceClear, bo);
			bo.setFinanceState(dbFinanceClear.getFinanceStatus());
			bo.setPayMoney(dbFinanceClear.getSubmitMoney());
			bo.setApplyMoney(dbFinanceClear.getDealMoney());
			if (dbFinanceClear.getFinanceStatus().equals(KemeanFinanceEnum.FinanceStatusEnum.NEW.getStatus())) {
				bo.setFinanceStateStr("待处理");
			}
			if (dbFinanceClear.getFinanceStatus().equals(KemeanFinanceEnum.FinanceStatusEnum.FAILURE.getStatus())) {
				bo.setFinanceStateStr("交易失败");
			}
			if (dbFinanceClear.getFinanceStatus().equals(KemeanFinanceEnum.FinanceStatusEnum.SUCCESS.getStatus())) {
				bo.setFinanceStateStr("交易成功");
			}
			result.add(bo);
		}
		PageInfo<KemeanFinanceClear> pageInfo = new PageInfo<KemeanFinanceClear>(dbFinanceClears);
		return new KemeanPageApiBO<List<CloseAccountListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}
}
