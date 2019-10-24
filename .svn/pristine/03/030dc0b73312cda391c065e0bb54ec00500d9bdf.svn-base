package com.kemean.service.admin;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kemean.bean.KemeanFinanceClear;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanMapData;
import com.kemean.dao.KemeanFinanceClearDaikenDao;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.admin.finance.AdminFinanceBO;
import com.kemean.vo.po.admin.finance.AdminFinancePO;

@Service
public class AdminFinanceService {
	@Autowired
	private KemeanFinanceClearDaikenDao kemeanFinanceClearDao;

	/**
	 * 财务流水列表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年3月29日
	 */
	public KemeanPageAdminBO<List<AdminFinanceBO>> financeListData(AdminFinancePO adminFinancePO) {
		List<KemeanFinanceClear> dbFinance = kemeanFinanceClearDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "financeNo", "submitAimsName", "financeState" },
				new Object[] { false, adminFinancePO.getFinanceNo(), adminFinancePO.getShopName(),
						adminFinancePO.getFinanceStatus() },
				new String[] { "tellerName" }, "id", false, adminFinancePO.getPage(), adminFinancePO.getLimit());
		List<AdminFinanceBO> result = Lists.transform(dbFinance, new Function<KemeanFinanceClear, AdminFinanceBO>() {
			@Override
			public AdminFinanceBO apply(KemeanFinanceClear item) {
				AdminFinanceBO bo = new AdminFinanceBO();
				BeanUtils.copyProperties(item, bo);
				bo.setCreateTimeStr(KemeanUtilAid.formatDate(item.getCreateTime()));
				bo.setFinanceTypeStr(DaikenMapData.financeType.get(item.getFinanceType()));
				bo.setFinanceStateStr(KemeanMapData.financeStatus.get(item.getFinanceStatus()));
				bo.setPayMethodStr(KemeanMapData.payType.get(item.getPayMethod()));
				bo.setPayAccountLast("");
				bo.setPayNameLast("");
				return bo;
			}

		});
		return new KemeanPageAdminBO<List<AdminFinanceBO>>(new PageInfo<>(dbFinance).getTotal(), result);
	}

}
