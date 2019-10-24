package com.kemean.service.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.util.DaikenUtil;

/**
 * 定时任务
 *
 */
@Service
public class DaikenTaskService {

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	/**
	 * 每天凌晨执行一次 用户撸羊毛标志 到点则清除
	 * 
	 * @author huwei
	 * @date 2018年7月23日
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void task() {
		Date now = new Date();
		String nowStr = DaikenUtil.formatDate(now, KemeanDateFormatEnum.DATE);
		List<DaikenUser> dbUsers = daikenUserDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userType", "woolLabel" },
				new Object[] { false, DaikenUserEnum.DaikenUserTypeEnum.CONSUMER.getType(), true });
		if (CollectionUtils.isNotEmpty(dbUsers)) {
			for (DaikenUser dbUser : dbUsers) {
				String woolLabelDateStr = DaikenUtil.formatDate(dbUser.getWoolLabelDate(), KemeanDateFormatEnum.DATE);
				// 到时间了，给修改一下
				if (woolLabelDateStr.equals(nowStr)) {
					dbUser.setWoolLabel(false);
					dbUser.setUpdateTime(now);
					daikenUserDao.updateByPrimaryKeySelective(dbUser);
				}
			}
		}
	}

	/**
	 * 实时监听【限时折扣】商品，活动结束时间到了就下架商品
	 * 
	 * @author huwei
	 * @date 2018年7月23日
	 */
	// @Scheduled(cron = "* * * * * ?")
	public void taskGoods() {
		Date now = new Date();
		String nowStr = DaikenUtil.formatDate(now, KemeanDateFormatEnum.NORMAL);
		List<DaikenGoodsNew> dbGoods = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "salesType", "goodsStatus" },
				new Object[] { false, DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType(), true });

		if (CollectionUtils.isNotEmpty(dbGoods)) {
			for (DaikenGoodsNew daikenGoodsNew : dbGoods) {
				String discountDateStr = DaikenUtil.formatDate(daikenGoodsNew.getDiscountTimeEnd(),
						KemeanDateFormatEnum.NORMAL);
				// 到时间了，就下架商品
				if (discountDateStr.equals(nowStr)) {
					daikenGoodsNew.setGoodsStatus(false);
					daikenGoodsNew.setUpdateTime(now);
					daikenGoodsNewDao.updateByPrimaryKeySelective(daikenGoodsNew);
				}
			}
		}
	}

}
