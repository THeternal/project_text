package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsHotCharge;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenGoodsHotChargeDao;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.hot.HotDataBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.charge.ChargeResetPO;

@Service
public class AdminGoodsHotChargeService {

	@Autowired
	private DaikenGoodsHotChargeDao daikenGoodsHotChargeDao;

	/**
	 * 推荐宝贝收费设置数据
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	public KemeanPageAdminBO<List<HotDataBO>> hotData(KemeanPageAdminPO kemeanPageAdminPO) {
		List<DaikenGoodsHotCharge> dbGoodsHots = daikenGoodsHotChargeDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false }, kemeanPageAdminPO.getPage(),
				kemeanPageAdminPO.getLimit());
		List<HotDataBO> result = new ArrayList<HotDataBO>(dbGoodsHots.size());
		for (DaikenGoodsHotCharge dbGoodsHot : dbGoodsHots) {
			HotDataBO bo = new HotDataBO();
			BeanUtils.copyProperties(dbGoodsHot, bo);
			if (dbGoodsHot.getScreenPosition().equals(1101)) {
				bo.setScreenPositionStr("一屏位置");
			}
			if (dbGoodsHot.getScreenPosition().equals(1201)) {
				bo.setScreenPositionStr("二屏位置");
			}
			if (dbGoodsHot.getScreenPosition().equals(1301)) {
				bo.setScreenPositionStr("三屏位置");
			}
			bo.setClickChargeStr(DaikenUtil.formatDouble(dbGoodsHot.getClickCharge(), 2) + "元 / 次");
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<HotDataBO>>(new PageInfo<DaikenGoodsHotCharge>(dbGoodsHots).getTotal(),
				result);
	}

	/**
	 * 重置收取费用
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	public KemeanResult<String> chargeReset(ChargeResetPO chargeResetPO) {
		DaikenGoodsHotCharge dbGoodsHotCharge = daikenGoodsHotChargeDao.selectById(chargeResetPO.getObjId());
		if (dbGoodsHotCharge == null) {
			return new KemeanResult<>(false, "设置失败，该数据丢失");
		}
		dbGoodsHotCharge.setClickCharge(chargeResetPO.getClickCharge());
		dbGoodsHotCharge.setUpdateTime(new Date());
		daikenGoodsHotChargeDao.updateByPrimaryKeySelective(dbGoodsHotCharge);
		return new KemeanResult<>();
	}

}
