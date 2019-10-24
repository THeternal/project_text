package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.kemean.bean.DaikenGoodsHotCharge;
import com.kemean.bean.DaikenGoodsHotTreasure;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenHotTreasure;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.dao.DaikenGoodsHotChargeDao;
import com.kemean.dao.DaikenGoodsHotTreasureDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.treasure.GoodsDataBO;
import com.kemean.vo.bo.admin.treasure.ShopDataBO;
import com.kemean.vo.bo.admin.treasure.TreasureDataBO;
import com.kemean.vo.po.admin.treasure.HotTreasurePO;
import com.kemean.vo.po.admin.treasure.TreasureDataPO;

@Service
public class AdminGoodsHotTreasureService {

	@Autowired
	private DaikenGoodsHotTreasureDao daikenGoodsHotTreasureDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsHotChargeDao daikenGoodsHotChargeDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private UserService userService;

	/**
	 * @author huwei
	 * @date 2018年9月7日
	 */
	public KemeanPageAdminBO<List<TreasureDataBO>> treasureData(TreasureDataPO treasureDataPO) {
		String userPhone = "";
		if (StringUtil.isNotEmpty(treasureDataPO.getUserPhone())) {
			userPhone = treasureDataPO.getUserPhone();
		}

		List<Integer> currentStates = new ArrayList<Integer>();
		currentStates.add(DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState());
		currentStates.add(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState());
		currentStates.add(DaikenHotTreasure.HotTreasureState.FINISHED.getState());
		if (treasureDataPO.getCurrentState() != null) {
			currentStates.clear();
			currentStates.add(treasureDataPO.getCurrentState());
		}

		List<Integer> screenPositions = new ArrayList<Integer>();
		screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType());
		screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType());
		screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType());
		screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN.getType());
		screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN.getType());
		screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN.getType());
		if (treasureDataPO.getScreenPosition() != null) {
			// 查找所有替补的
			screenPositions.clear();
			screenPositions.add(treasureDataPO.getScreenPosition());
			if (treasureDataPO.getScreenPosition().equals(1100)) {
				screenPositions.clear();
				screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType());
				screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType());
				screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType());
			}
		}
		List<DaikenGoodsHotTreasure> dbHotTreasures = daikenGoodsHotTreasureDao.treasureData(userPhone, currentStates,
				screenPositions, treasureDataPO.getLimit(), treasureDataPO.getPage());
		List<TreasureDataBO> result = new ArrayList<TreasureDataBO>(dbHotTreasures.size());
		if (CollectionUtils.isEmpty(dbHotTreasures)) {
			return new KemeanPageAdminBO<List<TreasureDataBO>>(0l, result);
		}
		for (DaikenGoodsHotTreasure dbHotTreasure : dbHotTreasures) {
			TreasureDataBO bo = new TreasureDataBO();
			BeanUtils.copyProperties(dbHotTreasure, bo);
			bo.setBuyClickNumStr(dbHotTreasure.getBuyClickNum() + "次");
			if (dbHotTreasure.getBuyClickNum().equals(999999)) {
				bo.setBuyClickNumStr("不限次数");
			}
			bo.setScreenPositionStr(DaikenMapData.hotTreasure.get(dbHotTreasure.getScreenPosition()));
			bo.setCurrentStateStr(DaikenMapData.hotTreasureState.get(dbHotTreasure.getCurrentState()));
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<TreasureDataBO>>(
				new PageInfo<DaikenGoodsHotTreasure>(dbHotTreasures).getTotal(), result);
	}

	/**
	 * 推荐宝贝上下架
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	public KemeanResult<String> statusOperate(Integer objId) {
		DaikenGoodsHotTreasure dbHotTreasure = daikenGoodsHotTreasureDao.selectById(objId);
		Boolean flag = dbHotTreasure.getStatus();
		if (flag) {
			if (dbHotTreasure.getScreenPosition()
					.equals(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType())
					|| dbHotTreasure.getScreenPosition()
							.equals(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType())
					|| dbHotTreasure.getScreenPosition()
							.equals(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType())) {
				List<DaikenGoodsHotTreasure> dbHotTreasures = daikenGoodsHotTreasureDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "status", "screenPosition" },
						new Object[] { false, true, dbHotTreasure.getScreenPosition() });
				if (dbHotTreasures.size() <= 4) {
					return new KemeanResult<String>(false,
							"当前第" + DaikenMapData.hotTreasure.get(dbHotTreasure.getScreenPosition()) + "少于4个，禁止下架");
				}
			}
		}
		dbHotTreasure.setStatus(!flag);
		dbHotTreasure.setUpdateTime(new Date());
		daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbHotTreasure);
		return new KemeanResult<>();
	}

	/**
	 * 删除推荐宝贝 （最少12个广告，每屏不能少于4个）
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@Transactional
	public KemeanResult<String> deleteHotTreasure(Integer objId) {
		DaikenGoodsHotTreasure dbHotTreasure = daikenGoodsHotTreasureDao.selectById(objId);
		if (dbHotTreasure.getScreenPosition()
				.equals(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType())
				|| dbHotTreasure.getScreenPosition()
						.equals(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType())
				|| dbHotTreasure.getScreenPosition()
						.equals(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType())) {
			List<DaikenGoodsHotTreasure> dbHotTreasures = daikenGoodsHotTreasureDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "status", "screenPosition" },
					new Object[] { false, true, dbHotTreasure.getScreenPosition() });
			if (dbHotTreasures.size() <= 4) {
				return new KemeanResult<String>(false,
						"当前第" + DaikenMapData.hotTreasure.get(dbHotTreasure.getScreenPosition()) + "少于4个，禁止删除");
			}
		}

		dbHotTreasure.setDataDeleted(true);
		dbHotTreasure.setUpdateTime(new Date());
		daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbHotTreasure);
		// 删除了宝贝，如果是在进行中的话，就给他设置成普通的,且再给他顶上去一个
		if (dbHotTreasure.getCurrentState().equals(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState())) {
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbHotTreasure.getGoodsId());
			dbGoodsNew.setRecommend(false);
			dbGoodsNew.setAdOrderSort(DaikenHotTreasure.HotTreasureScreenPosition.ORDINARY.getType());
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
			commonService.findHotTreasure(dbHotTreasure.getScreenPosition());
		}
		return new KemeanResult<>();
	}

	/**
	 * 新增推荐宝贝(热销)
	 * 
	 * @author huwei
	 * @date 2018年9月7日
	 */
	@Transactional
	public synchronized KemeanResult<String> addHotTreasure(HotTreasurePO hotTreasurePO, DaikenUser loginAdminShop) {
		Date now = new Date();
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(hotTreasurePO.getGoodsId());
		DaikenGoodsHotCharge dbGoodsHotCharge = daikenGoodsHotChargeDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "screenPosition" },
				new Object[] { false, hotTreasurePO.getScreenPosition() });
		Double money = hotTreasurePO.getBuyClickNum() * dbGoodsHotCharge.getClickCharge();
		// 余额
		Double blance = userService.getConsumerOrBusinessBlance(loginAdminShop);
		if (blance < money) {
			return new KemeanResult<String>(false, "购买的推荐广告位置收费: " + money + "元，余额不够，请充值!");
		}
		loginAdminShop.setBalancePrice(loginAdminShop.getBalancePrice() - money);
		loginAdminShop.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(loginAdminShop);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), loginAdminShop.getToken()));
		// 商家端 -- 首页推荐屏位宝贝流水
		commonService.createFinanceOrder(dbGoodsNew.getId() + "【" + dbGoodsNew.getTitle() + "】",
				DaikenFinanceTypeEnum.B_RECOMMEND_TREASURE.getType(), money, 0, dbGoodsNew.getShopId(),
				dbGoodsNew.getTitle(), "首页推荐屏位宝贝（支出）");
		// 不包括替补广告
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "recommend", "adOrderSort" },
				new Object[] { false, true, hotTreasurePO.getScreenPosition() });
		// 如果改商品已经购买屏位且在进行中的话，就让他购买的等待
		DaikenGoodsHotTreasure dbHotGoods = daikenGoodsHotTreasureDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId", "currentState" },
				new Object[] { false, dbGoodsNew.getId(), DaikenHotTreasure.HotTreasureState.UNDERWAY.getState() });
		// 查询等到中的人数
		List<DaikenGoodsHotTreasure> dbHotTreasures = daikenGoodsHotTreasureDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "status", "screenPosition", "currentState" },
				new Object[] { false, true, hotTreasurePO.getScreenPosition(),
						DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState() });
		if (dbHotGoods != null) {
			DaikenGoodsHotTreasure newTreasure = new DaikenGoodsHotTreasure();
			BeanUtils.copyProperties(hotTreasurePO, newTreasure);
			newTreasure.setUserPhone(loginAdminShop.getPhone());
			newTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState());
			newTreasure.setShopId(loginAdminShop.getShopId());
			newTreasure.setCreateTime(now);
			newTreasure.setUpdateTime(now);
			daikenGoodsHotTreasureDao.saveSelective(newTreasure);
			return new KemeanResult<String>(true,
					"购买成功，您之前购买的推荐广告还未推荐完成，所以当前购买广告将进入排队状态。前面有" + dbHotTreasures.size() + "人排队。");
		}

		// 代表首页推荐宝贝有替补商品
		if (dbGoodsNews.size() < 4) {
			Integer type = 0;
			if (hotTreasurePO.getScreenPosition()
					.equals(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN.getType())) {
				type = DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType();
			}
			if (hotTreasurePO.getScreenPosition()
					.equals(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN.getType())) {
				type = DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType();
			}
			if (hotTreasurePO.getScreenPosition()
					.equals(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN.getType())) {
				type = DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType();
			}
			// 寻找替补广告
			List<DaikenGoodsNew> dbSubstitutionGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "recommend", "adOrderSort" },
					new Object[] { false, true, type });
			if (CollectionUtils.isNotEmpty(dbSubstitutionGoodsNews)) {
				// 随机取一个
				int num = (int) (Math.random() * dbSubstitutionGoodsNews.size());
				DaikenGoodsNew dbSubstitutionGoodsNew = dbSubstitutionGoodsNews.get(num);
				DaikenGoodsHotTreasure dbHotTreasure = daikenGoodsHotTreasureDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "status", "goodsId", "screenPosition" },
						new Object[] { false, true, dbSubstitutionGoodsNew.getId(), type });
				dbHotTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState());
				dbHotTreasure.setUpdateTime(new Date());
				daikenGoodsHotTreasureDao.updateByPrimaryKeySelective(dbHotTreasure);
				dbSubstitutionGoodsNew.setRecommend(false);
				dbSubstitutionGoodsNew.setAdOrderSort(DaikenHotTreasure.HotTreasureScreenPosition.ORDINARY.getType());
				dbSubstitutionGoodsNew.setUpdateTime(now);
				daikenGoodsNewDao.updateByPrimaryKeySelective(dbSubstitutionGoodsNew);
			}
			dbGoodsNew.setRecommend(true);
			dbGoodsNew.setAdOrderSort(hotTreasurePO.getScreenPosition());
			dbGoodsNew.setUpdateTime(now);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
			DaikenGoodsHotTreasure newTreasure = new DaikenGoodsHotTreasure();
			BeanUtils.copyProperties(hotTreasurePO, newTreasure);
			newTreasure.setUserPhone(loginAdminShop.getPhone());
			newTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState());
			newTreasure.setShopId(loginAdminShop.getShopId());
			newTreasure.setCreateTime(now);
			newTreasure.setUpdateTime(now);
			daikenGoodsHotTreasureDao.saveSelective(newTreasure);
			return new KemeanResult<String>(true, "购买成功,广告已显示,请到小程序端查看！");
		}
		// 代表首页推荐宝贝没有替补商品，则需要排队
		if (dbGoodsNews.size() > 4) {
			Integer numWatiPerson = dbHotTreasures.size() - 4;
			DaikenGoodsHotTreasure newTreasure = new DaikenGoodsHotTreasure();
			BeanUtils.copyProperties(hotTreasurePO, newTreasure);
			newTreasure.setUserPhone(loginAdminShop.getPhone());
			newTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.NOT_STARTED.getState());
			newTreasure.setShopId(loginAdminShop.getShopId());
			newTreasure.setCreateTime(now);
			newTreasure.setUpdateTime(now);
			daikenGoodsHotTreasureDao.saveSelective(newTreasure);
			return new KemeanResult<String>(true, "购买成功，" + "当前排队" + numWatiPerson + "人");
		}
		return new KemeanResult<>(false, "添加失败，请联系管理员");
	}

	/**
	 * 获取所有店铺
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	public List<ShopDataBO> getAllShop() {
		List<DaikenShop> dbShops = daikenShopDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "auditStatus" },
				new Object[] { false, KemeanSettledEnum.AUDIT_PASS.getStatus() });
		List<ShopDataBO> result = new ArrayList<ShopDataBO>(dbShops.size());
		for (DaikenShop dbShop : dbShops) {
			ShopDataBO bo = new ShopDataBO();
			BeanUtils.copyProperties(dbShop, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 根据商铺id获取商品
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	public List<GoodsDataBO> goodsData(Integer shopId) {
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId", "auditStatus" },
				new Object[] { false, shopId, KemeanSettledEnum.AUDIT_PASS.getStatus() });
		List<GoodsDataBO> result = new ArrayList<GoodsDataBO>(dbGoodsNews.size());
		for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
			GoodsDataBO bo = new GoodsDataBO();
			BeanUtils.copyProperties(dbGoodsNew, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 新增替补推荐宝贝
	 * 
	 * @author huwei
	 * @date 2018年9月8日
	 */
	@Transactional
	public KemeanResult<String> addSubstitutionHotTreasure(Integer goodsId, Integer screenPosition, String goodsTitle) {
		DaikenGoodsHotTreasure dbGoodsHotTreasure = daikenGoodsHotTreasureDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" }, new Object[] { false, goodsId });
		if (dbGoodsHotTreasure != null) {
			return new KemeanResult<>(false,
					"添加失败，该商品已是" + DaikenMapData.hotTreasure.get(dbGoodsHotTreasure.getScreenPosition()) + "商品");
		}
		Date now = new Date();
		DaikenGoodsHotTreasure newGoodsTreasure = new DaikenGoodsHotTreasure();
		newGoodsTreasure.setGoodsId(goodsId);
		newGoodsTreasure.setGoodsTitle(goodsTitle);
		newGoodsTreasure.setScreenPosition(screenPosition);
		newGoodsTreasure.setUserPhone("0");
		newGoodsTreasure.setBuyClickNum(999999);
		newGoodsTreasure.setStatus(true);
		newGoodsTreasure.setShopId(0);
		newGoodsTreasure.setCreateTime(now);
		newGoodsTreasure.setUpdateTime(now);
		List<Integer> screenPositions = new ArrayList<Integer>();
		String resultStr = "由于";
		// 查询小程序的广告屏位是否满足4个
		if (screenPosition.equals(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType())) {
			// 一屏替补
			screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN_SUBSTITUTION.getType());
			screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.ONE_SCREEN.getType());
			resultStr += "一屏";
		}

		if (screenPosition.equals(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType())) {
			// 二屏替补
			screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN_SUBSTITUTION.getType());
			screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.TWO_SCREEN.getType());
			resultStr += "二屏";
		}

		if (screenPosition.equals(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType())) {
			// 三屏替补
			screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN_SUBSTITUTION.getType());
			screenPositions.add(DaikenHotTreasure.HotTreasureScreenPosition.THREE_SCREEN.getType());
			resultStr += "三屏";
		}

		// 一屏替补
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED, "recommend" }, new Object[] { false, true }, "adOrderSort",
				screenPositions, "ad_order_sort", true, 0, 10);
		if (dbGoodsNews.size() < 4) {
			newGoodsTreasure.setCurrentState(DaikenHotTreasure.HotTreasureState.UNDERWAY.getState());
			daikenGoodsHotTreasureDao.saveSelective(newGoodsTreasure);
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(goodsId);
			dbGoodsNew.setRecommend(true);
			dbGoodsNew.setAdOrderSort(screenPosition);
			dbGoodsNew.setUpdateTime(now);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
			resultStr += "当前位置不够，所以替补进去";
			return new KemeanResult<>(true, resultStr);
		}
		daikenGoodsHotTreasureDao.saveSelective(newGoodsTreasure);
		return new KemeanResult<>(true, "添加成功");
	}
}
