package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsRecommendCharge;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.DaikenGoodsRecommendChargeDao;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.charge.ChargeDataBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.charge.ChargeResetPO;

@Service
public class AdminRecommendChargeService {

	@Autowired
	private DaikenGoodsRecommendChargeDao daikenGoodsRecommendChargeDao;

	/**
	 * 广告位收费设置数据
	 * 
	 * @author huwei
	 * @date 2018年9月4日
	 */
	public KemeanPageAdminBO<List<ChargeDataBO>> chargeData(KemeanPageAdminPO kemeanPageAdminPO) {
		List<DaikenGoodsRecommendCharge> dbRecommendCharges = daikenGoodsRecommendChargeDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false }, kemeanPageAdminPO.getPage(),
				kemeanPageAdminPO.getLimit());
		List<ChargeDataBO> result = new ArrayList<ChargeDataBO>(dbRecommendCharges.size());
		for (DaikenGoodsRecommendCharge dbRecommendCharge : dbRecommendCharges) {
			ChargeDataBO bo = new ChargeDataBO();
			BeanUtils.copyProperties(dbRecommendCharge, bo);
			bo.setCreateTimeStr(
					DaikenUtil.formatDate(dbRecommendCharge.getCreateTime(), KemeanDateFormatEnum.DATE_MINUTE));
			bo.setBeginTimeStr(DaikenUtil.getHourStr(dbRecommendCharge.getBeginTime()));
			bo.setEndTimeStr(DaikenUtil.getHourStr(dbRecommendCharge.getEndTime()));
			bo.setShowChargeStr(DaikenUtil.formatDouble(dbRecommendCharge.getShowCharge(), 2) + "元 / 段");
			bo.setClickChargeStr(DaikenUtil.formatDouble(dbRecommendCharge.getClickCharge(), 2) + "元 / 次");
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<ChargeDataBO>>(
				new PageInfo<DaikenGoodsRecommendCharge>(dbRecommendCharges).getTotal(), result);
	}

	/**
	 * 重置广告位收费费用
	 * 
	 * @author huwei
	 * @date 2018年9月4日
	 */
	public KemeanResult<String> chargeReset(ChargeResetPO chargeResetPO) {
		DaikenGoodsRecommendCharge dbRecommendCharge = daikenGoodsRecommendChargeDao
				.selectById(chargeResetPO.getObjId());
		dbRecommendCharge.setClickCharge(chargeResetPO.getClickCharge());
		dbRecommendCharge.setShowCharge(chargeResetPO.getShowCharge());
		dbRecommendCharge.setUpdateTime(new Date());
		daikenGoodsRecommendChargeDao.updateByPrimaryKeySelective(dbRecommendCharge);
		return new KemeanResult<>();
	}

}
