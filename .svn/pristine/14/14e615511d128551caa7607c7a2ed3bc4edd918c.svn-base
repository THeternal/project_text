package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsRecommendCharge;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.KemeanLunbo;
import com.kemean.constant.DaikenAdminResultTips;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenLunboLocatTypeEnum;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRecommend;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsRecommendChargeDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.KemeanLunboDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.lunbo.AdminLunboBO;
import com.kemean.vo.bo.b.shop.RecommendChargeBO;
import com.kemean.vo.po.admin.lunbo.AddLunBoDataPO;
import com.kemean.vo.po.admin.lunbo.LunBoDataPO;

@Service
public class AdminLunBoService {

	@Autowired
	private KemeanLunboDao kemeanLunboDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenGoodsRecommendChargeDao daikenGoodsRecommendChargeDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	// 获取轮播图数据
	public KemeanPageAdminBO<List<AdminLunboBO>> getLunBoDate(LunBoDataPO lunBoDataPO) {
		List<KemeanLunbo> dbLunbos = kemeanLunboDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "locatType" },
				new Object[] { false, lunBoDataPO.getLocatType() }, lunBoDataPO.getPage(), lunBoDataPO.getLimit());
		List<AdminLunboBO> result = new ArrayList<AdminLunboBO>(dbLunbos.size());
		if (CollectionUtils.isEmpty(dbLunbos)) {
			return new KemeanPageAdminBO<List<AdminLunboBO>>(new PageInfo<KemeanLunbo>(dbLunbos).getTotal(), result);
		}
		for (KemeanLunbo kemeanLunbo : dbLunbos) {
			AdminLunboBO bo = new AdminLunboBO();
			BeanUtils.copyProperties(kemeanLunbo, bo);
			bo.setTypeStr("调研广告");
			if (kemeanLunbo.getRecommendWay().equals(DaikenRecommend.RecommendWay.GOODS.getWay())) {
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(kemeanLunbo.getTypeId());
				bo.setTypeStr("【商品】" + dbGoodsNew.getTitle());
			}
			if (kemeanLunbo.getRecommendWay().equals(DaikenRecommend.RecommendWay.SHOP.getWay())) {
				DaikenShop dbShop = daikenShopDao.selectById(kemeanLunbo.getTypeId());
				bo.setTypeStr("【商铺】" + dbShop.getShopName());
			}
			bo.setRecommendTypeStr(DaikenMapData.recommendType.get(kemeanLunbo.getRecommendType()));
			bo.setRecommendWayStr(DaikenMapData.recommendWay.get(kemeanLunbo.getRecommendWay()));
			bo.setLocatTypeStr(DaikenMapData.locatType.get(kemeanLunbo.getLocatType()));
			bo.setIsOverStr(kemeanLunbo.getIsOver() ? "时间到" : "未到时间");
			bo.setBuyTimeStr(DaikenUtil.formatDate(kemeanLunbo.getBuyTime(), KemeanDateFormatEnum.DATE));
			DaikenGoodsRecommendCharge dbRecommendCharge = daikenGoodsRecommendChargeDao
					.selectById(kemeanLunbo.getRecommendId());
			bo.setTimeBucket("永久有效");
			if (dbRecommendCharge != null) {
				bo.setTimeBucket(DaikenUtil.getHourStr(dbRecommendCharge.getBeginTime()) + "到"
						+ DaikenUtil.getHourStr(dbRecommendCharge.getEndTime()));

			}
			if (kemeanLunbo.getRecommendType().equals(DaikenRecommend.RecommendType.CLICK.getType())) {
				bo.setTimeBucket(kemeanLunbo.getBuyClickNum() - kemeanLunbo.getClickNum() + "次");
			}
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<AdminLunboBO>>(new PageInfo<KemeanLunbo>(dbLunbos).getTotal(), result);
	}

	// 删除轮播图数据
	public void delLunbo(Integer objId) {
		KemeanLunbo dbLunbo = kemeanLunboDao.selectById(objId);
		dbLunbo.setDataDeleted(true);
		dbLunbo.setUpdateTime(new Date());
		kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.LUNBO.getKey(), dbLunbo.getLocatType()));
	}

	// 新增轮播图数据
	@Transactional
	public KemeanResult<String> addLunbo(AddLunBoDataPO addLunBoDataPO) {
		Date now = new Date();
		KemeanLunbo newLunbo = new KemeanLunbo();
		BeanUtils.copyProperties(addLunBoDataPO, newLunbo);
		// 区分调研轮播图
		if (addLunBoDataPO.getLocatType().equals(DaikenLunboLocatTypeEnum.INVESTIGATE_PAGE.getLocatType())) {
			newLunbo.setRecommendId(0);
			newLunbo.setUserPhone("");
			newLunbo.setRecommendType(0);
			newLunbo.setRecommendWay(0);
			newLunbo.setBuyTime(now);
			newLunbo.setCreateTime(now);
			newLunbo.setUpdateTime(now);
			kemeanLunboDao.saveSelective(newLunbo);
			return new KemeanResult<>(true, "调研轮播图添加成功");
		}
		// 区分首页轮播图
		if (addLunBoDataPO.getLocatType().equals(DaikenLunboLocatTypeEnum.HOME_PAGE.getLocatType())) {
			// 时间段显示
			if (addLunBoDataPO.getRecommendType().equals(DaikenRecommend.RecommendType.SHOW.getType())) {
				RecommendChargeBO dbrecommendCharge = commonService
						.getClickShowCharge(addLunBoDataPO.getRecommendTypeSele());
				newLunbo.setRecommendId(dbrecommendCharge.getId());
				Double money = dbrecommendCharge.getShowCharge() * addLunBoDataPO.getBuyDays();
				DaikenUser dbUser = null;
				if (addLunBoDataPO.getRecommendWay().equals(DaikenRecommend.RecommendWay.GOODS.getWay())) {
					DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(addLunBoDataPO.getTypeId());
					dbUser = daikenUserDao.selectUniqueEntityByProperties(
							new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" }, new Object[] { false,
									dbGoodsNew.getShopId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
				}
				if (addLunBoDataPO.getRecommendWay().equals(DaikenRecommend.RecommendWay.SHOP.getWay())) {
					dbUser = daikenUserDao.selectUniqueEntityByProperties(
							new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" }, new Object[] { false,
									addLunBoDataPO.getTypeId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
				}
				if (dbUser == null) {
					return new KemeanResult<>(false, "没有找到该用户，请确认输入的序号是否正确");
				}
				Double blane = userService.getConsumerOrBusinessBlance(dbUser);
				if (blane < money) {
					return new KemeanResult<>(false, "改用户当前余额不足，请及时充值");
				}
				dbUser.setBalancePrice(dbUser.getBalancePrice() - money);
				dbUser.setUpdateTime(now);
				daikenUserDao.updateByPrimaryKeySelective(dbUser);
				kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), dbUser.getToken()));
				newLunbo.setUserPhone(dbUser.getPhone());
				// 计算购买显示时间 根据购买的查没有结束的，如果说没有，那直接生成今天的，如果有往后推
				List<KemeanLunbo> dbLunbos = kemeanLunboDao.selectByPropertiesAndIn(
						new String[] { KemeanConstant.DATA_DELETED, "locatType", "isOver", "recommendId" },
						new Object[] { false, DaikenLunboLocatTypeEnum.HOME_PAGE.getLocatType(), false,
								dbrecommendCharge.getId() },
						"recommendType", Arrays.asList(DaikenRecommend.RecommendType.CLICK.getType(),
								DaikenRecommend.RecommendType.SHOW.getType()),
						"buy_time", false, -1, -1);
				String buyTimeStr = "";
				if (CollectionUtils.isNotEmpty(dbLunbos)) {
					KemeanLunbo dbLunbo = dbLunbos.get(0);
					Date buyTime = KemeanUtilAid.getDateByCalendar(dbLunbo.getBuyTime(), KemeanCalendarFieldEnum.DAY,
							dbLunbo.getBuyDays());
					buyTimeStr = DaikenUtil.formatDate(buyTime, KemeanDateFormatEnum.DATE);
					newLunbo.setBuyTime(buyTime);
				}
				if (CollectionUtils.isEmpty(dbLunbos)) {
					// 如果购买的时间已过今天时间，将在明天显示，没有过的话，就在今天显示
					Integer currTimeInt = Integer.valueOf(DaikenUtil.formatDate(now, "HH"));
					Integer buyTimeInt = dbrecommendCharge.getEndTime();
					if (currTimeInt >= buyTimeInt) {
						// 将在明天显示
						Date buyTime = KemeanUtilAid.getDateByCalendar(now, KemeanCalendarFieldEnum.DAY, 1);
						buyTimeStr = DaikenUtil.formatDate(buyTime, KemeanDateFormatEnum.DATE);
						newLunbo.setBuyTime(buyTime);
					}
					if (currTimeInt < buyTimeInt) {
						// 将在今天显示
						buyTimeStr = DaikenUtil.formatDate(now, KemeanDateFormatEnum.DATE);
						newLunbo.setBuyTime(now);
					}
				}
				newLunbo.setCreateTime(now);
				newLunbo.setUpdateTime(now);
				kemeanLunboDao.saveSelective(newLunbo);
				DaikenShop dbShop = daikenShopDao.selectById(dbUser.getShopId());
				// 商家端 -- 添加轮播图流水
				commonService.createFinanceOrder(dbShop.getId() + "【" + dbShop.getShopName() + "】",
						DaikenFinanceTypeEnum.B_LUNBO_ADVERTISING.getType(), money, 0, dbUser.getShopId(),
						dbShop.getShopName(), "轮播图时长广告（支出）");
				return new KemeanResult<>(true, "时长轮播图添加成功，将在" + buyTimeStr + "时显示");
			}
			// 时间段点击
			if (addLunBoDataPO.getRecommendType().equals(DaikenRecommend.RecommendType.CLICK.getType())) {
				RecommendChargeBO dbrecommendCharge = commonService
						.getClickShowCharge(addLunBoDataPO.getRecommendTypeSele());
				newLunbo.setRecommendId(dbrecommendCharge.getId());
				Double money = dbrecommendCharge.getClickCharge() * addLunBoDataPO.getBuyClickNum();
				DaikenUser dbUser = null;
				if (addLunBoDataPO.getRecommendWay().equals(DaikenRecommend.RecommendWay.GOODS.getWay())) {
					DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(addLunBoDataPO.getTypeId());
					dbUser = daikenUserDao.selectUniqueEntityByProperties(
							new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" }, new Object[] { false,
									dbGoodsNew.getShopId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
				}
				if (addLunBoDataPO.getRecommendWay().equals(DaikenRecommend.RecommendWay.SHOP.getWay())) {
					dbUser = daikenUserDao.selectUniqueEntityByProperties(
							new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" }, new Object[] { false,
									addLunBoDataPO.getTypeId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS.getType() });
				}
				if (dbUser == null) {
					return new KemeanResult<>(false, "没有找到该用户，请确认输入的序号是否正确");
				}
				if (dbUser.getBalancePrice() < money) {
					return new KemeanResult<>(false, "改用户当前余额不足，请及时充值");
				}
				dbUser.setBalancePrice(dbUser.getBalancePrice() - money);
				dbUser.setUpdateTime(now);
				kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), dbUser.getToken()));
				daikenUserDao.updateByPrimaryKeySelective(dbUser);
				newLunbo.setUserPhone(dbUser.getPhone());
				newLunbo.setBuyTime(now);
				newLunbo.setCreateTime(now);
				newLunbo.setUpdateTime(now);
				kemeanLunboDao.saveSelective(newLunbo);
				DaikenShop dbShop = daikenShopDao.selectById(dbUser.getShopId());
				// 商家端 -- 添加轮播图流水
				commonService.createFinanceOrder(dbShop.getId() + "【" + dbShop.getShopName() + "】",
						DaikenFinanceTypeEnum.B_LUNBO_ADVERTISING.getType(), money, dbUser.getId(), dbUser.getShopId(),
						dbShop.getShopName(), "轮播图点击广告（支出）");
				return new KemeanResult<>(true, "点击轮播图添加成功");
			}
			// 替补广告
			if (addLunBoDataPO.getRecommendType().equals(DaikenRecommend.RecommendType.SUBSTITUTION.getType())) {
				newLunbo.setRecommendId(0);
				newLunbo.setUserPhone("");
				newLunbo.setBuyTime(now);
				newLunbo.setCreateTime(now);
				newLunbo.setUpdateTime(now);
				kemeanLunboDao.saveSelective(newLunbo);
				return new KemeanResult<>(true, "替补轮播图添加成功");
			}
		}
		return new KemeanResult<String>(false, DaikenAdminResultTips.Lunbo.LUNBO_ADD_ERROR);
	}

	/**
	 * 获取轮播信息
	 * 
	 * @author huwei
	 * @date 2018年7月6日
	 */
	public KemeanLunbo getLunboInfo(Integer objId) {
		return kemeanLunboDao.selectById(objId);
	}

	/**
	 * 修改生效状态
	 * 
	 * @author huwei
	 * @date 2018年8月31日
	 */
	public KemeanResult<String> isTakeEffect(Integer id) {
		KemeanLunbo dbLunbo = kemeanLunboDao.selectById(id);
		dbLunbo.setIsTakeEffect(!dbLunbo.getIsTakeEffect());
		dbLunbo.setUpdateTime(new Date());
		kemeanLunboDao.updateByPrimaryKeySelective(dbLunbo);
		return new KemeanResult<>();
	}
}
