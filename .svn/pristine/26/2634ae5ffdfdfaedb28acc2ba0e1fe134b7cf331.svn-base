package com.kemean.service.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenGoodsCar;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenGoodsOld;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenOrderGoods;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserShop;
import com.kemean.bean.KemeanConfig;
import com.kemean.bean.KemeanUserAddress;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanPayTypeEnum;
import com.kemean.dao.DaikenGoodsCarDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenGoodsOldDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenOrderGoodsDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.dao.KemeanUserAddressDao;
import com.kemean.service.common.CommonService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.c.goodscar.GoodscarInfoListBO;
import com.kemean.vo.bo.c.goodscar.GoodscarInfoListShopBO;
import com.kemean.vo.bo.c.goodscar.SubmitOrderInCarBO;
import com.kemean.vo.mysql.GoodsRecordDB;
import com.kemean.vo.mysql.RecordGoodsInfoDB;
import com.kemean.vo.mysql.UserAddressDB;
import com.kemean.vo.po.c.goodscar.AddGoodsCarPO;
import com.kemean.vo.po.c.goodscar.DelGoodsPO;
import com.kemean.vo.po.c.goodscar.SubmitOrderInCarPO;
import com.kemean.vo.po.c.goodscar.SubmitOrderInShopPO;
import com.kemean.vo.po.c.goodscar.UpdateGoodsInfoPO;

/**
 * 
 * 【客户端】购物车业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class CGoodscarService {

	@Autowired
	private DaikenGoodsCarDao daikenGoodsCarDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsOldDao daikenGoodsOldDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private KemeanUserAddressDao kemeanUserAddressDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenOrderGoodsDao daikenOrderGoodsDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenUserShopDao daikenUserShopDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private CommonService commonService;

	/**
	 * 购物车信息
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public List<GoodscarInfoListShopBO> infoList(DaikenUser daikenUser) {
		List<GoodscarInfoListShopBO> result = new ArrayList<GoodscarInfoListShopBO>();
		// 查找一手自卖商品 和 二手自卖商品
		List<DaikenGoodsCar> dbGoodsCarSelfBuys = daikenGoodsCarDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "idPurchasing" },
				new Object[] { false, daikenUser.getId(), 0 });

		if (CollectionUtils.isNotEmpty(dbGoodsCarSelfBuys)) {
			Set<Integer> shopIds = new HashSet<Integer>();
			for (DaikenGoodsCar dbGoodsCarSelfBuy : dbGoodsCarSelfBuys) {
				shopIds.add(dbGoodsCarSelfBuy.getShopId());
			}

			// 自卖商铺信息
			for (Integer shopId : shopIds) {
				GoodscarInfoListShopBO bo = new GoodscarInfoListShopBO();
				bo.setUserShopId(0);
				bo.setId(shopId);
				// 二手
				if (shopId.equals(0)) {
					bo.setShopLogo("http://paxduygw4.bkt.clouddn.com//file/upload/20186/12/1531359764839yyqofzng");
					bo.setShopName("二手专区");
				}
				// 一手
				if (!shopId.equals(0)) {
					DaikenShop dbShop = daikenShopDao.selectById(shopId);
					BeanUtils.copyProperties(dbShop, bo);
				}

				List<DaikenGoodsCar> dbGoodsCars = daikenGoodsCarDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "shopId", "userId", "idPurchasing" },
						new Object[] { false, shopId, daikenUser.getId(), 0 });

				List<GoodscarInfoListBO> goodsCarInfoList = getCarGoodsInfo(dbGoodsCars);
				bo.setGoodsCarInfoList(goodsCarInfoList);
				result.add(bo);
			}
		}

		// 查找一手帮卖商品 和 二手帮卖商品
		List<DaikenGoodsCar> dbGoodsCatHelpBuys = daikenGoodsCarDao.helpSellGoods(daikenUser.getId());
		if (CollectionUtils.isNotEmpty(dbGoodsCatHelpBuys)) {
			Set<Integer> userShopIds = new HashSet<Integer>();
			for (DaikenGoodsCar dbGoodsCatHelpBuy : dbGoodsCatHelpBuys) {
				userShopIds.add(dbGoodsCatHelpBuy.getIdPurchasing());
			}

			// 帮卖商铺信息
			for (Integer userShopId : userShopIds) {
				GoodscarInfoListShopBO bo = new GoodscarInfoListShopBO();
				bo.setUserShopId(userShopId);
				bo.setId(0);
				// 二手
				if (userShopId.equals(0)) {
					bo.setShopLogo("http://paxduygw4.bkt.clouddn.com//file/upload/20186/12/1531359764839yyqofzng");
					bo.setShopName("帮卖二手专区");
				}

				// 一手
				if (!userShopId.equals(0)) {
					DaikenUserShop dbUserShop = daikenUserShopDao.selectById(userShopId);
					bo.setShopLogo(dbUserShop.getImg());
					bo.setShopName(dbUserShop.getShopName());
				}

				List<DaikenGoodsCar> helpSellGoodsInfo = daikenGoodsCarDao.helpSellGoodsInfo(daikenUser.getId(),
						userShopId);
				List<GoodscarInfoListBO> goodsCarInfoList = getCarGoodsInfo(helpSellGoodsInfo);
				bo.setGoodsCarInfoList(goodsCarInfoList);
				result.add(bo);
			}
		}

		return result;
	}

	/**
	 * 添加物品到购物车
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> addGoodsCar(AddGoodsCarPO addGoodsCarPO, DaikenUser daikenUser) {
		// 如果购物车中有的话数量直接加一
		Date now = new Date();
		DaikenGoodsCar dbGoodsCar = daikenGoodsCarDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "skuNo", "idPurchasing", "isNewGoods" },
				new Object[] { false, daikenUser.getId(), addGoodsCarPO.getSkuNo(), addGoodsCarPO.getUserShopId(),
						addGoodsCarPO.getIsNewGoods() });
		if (dbGoodsCar != null) {
			if (!dbGoodsCar.getIsNewGoods()) {
				return new KemeanResult<String>(false, DaikenApiResultTips.GoodsCar.SECOND_GOODS_DONT_ADD);
			}

			dbGoodsCar.setAddQuantity(dbGoodsCar.getAddQuantity() + 1);
			dbGoodsCar.setUpdateTime(now);
			daikenGoodsCarDao.updateByPrimaryKeySelective(dbGoodsCar);
			return new KemeanResult<String>(true, DaikenApiResultTips.GoodsCar.ADD_GOODS_CAR_SUCCESS);
		}
		DaikenGoodsCar newGoodsCar = new DaikenGoodsCar();
		newGoodsCar.setUserId(daikenUser.getId());
		newGoodsCar.setAddQuantity(addGoodsCarPO.getAddQuantity());
		// 一手商品
		if (addGoodsCarPO.getIsNewGoods()) {
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(addGoodsCarPO.getGoodsId());

			if (!dbGoodsNew.getGoodsId().equals(0)) {
				dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsNew.getGoodsId());
			}

			List<DaikenGoodsNewSku> dbGoodsNewSkus = daikenGoodsNewSkuDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "skuNo" },
					new Object[] { false, addGoodsCarPO.getSkuNo() });
			DaikenGoodsNewSku dbGoodsNewSku = dbGoodsNewSkus.get(0);
			newGoodsCar.setShopId(dbGoodsNew.getShopId());
			newGoodsCar.setIsNewGoods(true);
			newGoodsCar.setGoodsId(addGoodsCarPO.getGoodsId());
			newGoodsCar.setSkuNo(addGoodsCarPO.getSkuNo());
			RecordGoodsInfoDB goodsInfoDB = new RecordGoodsInfoDB();
			BeanUtils.copyProperties(dbGoodsNew, goodsInfoDB);
			BeanUtils.copyProperties(dbGoodsNewSku, goodsInfoDB);
			goodsInfoDB.setShopId(dbGoodsNew.getShopId());
			goodsInfoDB.setIsPurchasing(false);
			if (addGoodsCarPO.getUserShopId() != null) {
				newGoodsCar.setIdPurchasing(addGoodsCarPO.getUserShopId());
				// 帮代卖
				goodsInfoDB.setIsPurchasing(true);
			}
			goodsInfoDB.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			newGoodsCar.setRecordGoodsInfo(JSONObject.toJSONString(goodsInfoDB));
		}

		// 二手商品
		if (!addGoodsCarPO.getIsNewGoods()) {
			DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(addGoodsCarPO.getGoodsId());
			newGoodsCar.setShopId(0);
			newGoodsCar.setGoodsId(dbGoodsOld.getId());
			newGoodsCar.setIsNewGoods(false);
			newGoodsCar.setSkuNo("二手商品");
			RecordGoodsInfoDB goodsInfoDB = new RecordGoodsInfoDB();
			goodsInfoDB.setShopId(0);
			goodsInfoDB.setIsPurchasing(false);
			BeanUtils.copyProperties(dbGoodsOld, goodsInfoDB);
			goodsInfoDB.setDiscount(10f);
			goodsInfoDB.setPriceStore(dbGoodsOld.getPriceOriginal());
			if (addGoodsCarPO.getUserShopId() != null) {
				newGoodsCar.setIdPurchasing(addGoodsCarPO.getUserShopId());
				// 帮代卖
				goodsInfoDB.setIsPurchasing(true);
			}
			goodsInfoDB.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class).get(0));
			newGoodsCar.setRecordGoodsInfo(JSONObject.toJSONString(goodsInfoDB));
		}
		newGoodsCar.setCreateTime(now);
		newGoodsCar.setUpdateTime(now);
		daikenGoodsCarDao.saveSelective(newGoodsCar);
		return new KemeanResult<String>();
	}

	/**
	 * 删除购物车商品
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> delGoods(DelGoodsPO delGoodsPO) {
		List<Integer> carIds = delGoodsPO.getCarIds();
		for (Integer carId : carIds) {
			DaikenGoodsCar dbGoodsCar = daikenGoodsCarDao.selectById(carId);
			dbGoodsCar.setDataDeleted(true);
			dbGoodsCar.setUpdateTime(new Date());
			daikenGoodsCarDao.updateByPrimaryKeySelective(dbGoodsCar);
		}
		return new KemeanResult<String>();
	}

	/**
	 * 修改购物车商品数量
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> updateGoodsInfo(UpdateGoodsInfoPO updateGoodsInfoPO) {
		DaikenGoodsCar dbGoodsCar = daikenGoodsCarDao.selectById(updateGoodsInfoPO.getObjId());
		dbGoodsCar.setAddQuantity(updateGoodsInfoPO.getQuantity());
		if (updateGoodsInfoPO.getQuantity().equals(0)) {
			dbGoodsCar.setDataDeleted(true);
		}
		dbGoodsCar.setUpdateTime(new Date());
		daikenGoodsCarDao.updateByPrimaryKeySelective(dbGoodsCar);
		return new KemeanResult<String>();
	}

	/**
	 * 提交购物车订单
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	@Transactional
	public List<SubmitOrderInCarBO> submitOrderInCar(SubmitOrderInCarPO submitOrderInCarPO, DaikenUser daikenUser) {
		Date now = new Date();
		KemeanConfig config = commonService.getConfig(DaikenConfigEnum.PROJECT_VERSION);
		List<SubmitOrderInCarBO> result = new ArrayList<SubmitOrderInCarBO>();
		// 收货地址
		KemeanUserAddress dbUserAddress = kemeanUserAddressDao.selectById(submitOrderInCarPO.getAddressId());
		UserAddressDB userAddressDB = new UserAddressDB();
		BeanUtils.copyProperties(dbUserAddress, userAddressDB);
		List<SubmitOrderInShopPO> shopInfos = submitOrderInCarPO.getShopIds();
		for (SubmitOrderInShopPO goodsCarSubmitInfo : shopInfos) {
			List<Integer> carIds = goodsCarSubmitInfo.getCarIds();
			for (Integer carId : carIds) {
				SubmitOrderInCarBO bo = new SubmitOrderInCarBO();
				DaikenGoodsCar dbGoodsCar = daikenGoodsCarDao.selectById(carId);
				DaikenOrder newOrder = new DaikenOrder();
				String orderNo = KemeanUtilAid.getOrderNo(now);
				newOrder.setOrderNo(orderNo);
				newOrder.setIdInvestigate(0);
				newOrder.setUserName(daikenUser.getNickName());
				newOrder.setIdUser(daikenUser.getId());
				newOrder.setUidUser(daikenUser.getUid());
				newOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus());
				newOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus());
				newOrder.setUserPhone(dbUserAddress.getPhone());
				newOrder.setQuantity(dbGoodsCar.getAddQuantity());
				newOrder.setPayType(KemeanPayTypeEnum.NO_SET.getType());
				// TODO 无优惠价格
				newOrder.setPriceDiscount(0.0);
				newOrder.setRecordReceiving(JSONObject.toJSONString(userAddressDB));
				newOrder.setRemark(goodsCarSubmitInfo.getRemark());
				newOrder.setIsNewGoods(dbGoodsCar.getIsNewGoods());
				newOrder.setIdPurchasing(dbGoodsCar.getIdPurchasing());
				newOrder.setIdPurchasingUser(0);
				if (dbGoodsCar.getIdPurchasing() != 0) {
					DaikenUserShop dbUserShop = daikenUserShopDao.selectById(dbGoodsCar.getIdPurchasing());
					newOrder.setIdPurchasingUser(dbUserShop.getUserId());
				}
				Double priceStore = 0.0;
				Double priceSales = 0.0;
				// 售前红包
				Double redBefore = 0.0;
				// 售后红包
				Double redAfter = 0.0;
				// 帮卖
				Double pricePurchasing = 0.0;
				// 商品uid
				String goodsUid = "";

				GoodsRecordDB goodsRecordDB = new GoodsRecordDB();
				// 一手
				if (dbGoodsCar.getIsNewGoods()) {
					DaikenShop dbShop = daikenShopDao.selectById(dbGoodsCar.getShopId());
					newOrder.setIdShop(dbShop.getId());
					newOrder.setShopName(dbShop.getShopName());
					DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsCar.getGoodsId());
					goodsUid = dbGoodsNew.getGoodsUid();
					redBefore = dbGoodsNew.getRedBefore();
					redAfter = dbGoodsNew.getRedAfter();
					pricePurchasing = dbGoodsNew.getPricePurchasing();
					DaikenGoodsNewSku dbGoodsNewSkuNo = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
							new String[] { KemeanConstant.DATA_DELETED, "skuNo" },
							new Object[] { false, dbGoodsCar.getSkuNo() });
					priceStore = dbGoodsNewSkuNo.getPriceStore();
					priceSales = dbGoodsNewSkuNo.getPriceSales();
					newOrder.setGoodsTitles(dbGoodsNew.getTitle());
					Double pricePay = (dbGoodsNewSkuNo.getPriceSales() + dbGoodsNew.getPostage())
							* dbGoodsCar.getAddQuantity();
					newOrder.setPricePay(pricePay);
					newOrder.setPriceTotal(pricePay);
					newOrder.setPostage(dbGoodsNew.getPostage());
					if (config.getRecord().equals("测试")) {
						newOrder.setPricePay(0.01);
						newOrder.setPriceTotal(0.01);
						newOrder.setPostage(0.01);
					}
					bo.setPrice(pricePay);
					goodsRecordDB.setTitle(dbGoodsNew.getTitle());
					goodsRecordDB.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
					goodsRecordDB.setPriceSales(dbGoodsNewSkuNo.getPriceSales());
					goodsRecordDB.setRecordType(dbGoodsNewSkuNo.getRecordType());
					goodsRecordDB.setPriceStore(dbGoodsNewSkuNo.getPriceStore());
					goodsRecordDB.setQuantity(dbGoodsCar.getAddQuantity());
					goodsRecordDB.setSkuNo(dbGoodsNewSkuNo.getSkuNo());
				}

				// 二手
				if (!dbGoodsCar.getIsNewGoods()) {
					DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(dbGoodsCar.getGoodsId());
					redBefore = dbGoodsOld.getRedBefore();
					redAfter = dbGoodsOld.getRedAfter();
					pricePurchasing = dbGoodsOld.getPricePurchasing();
					goodsUid = dbGoodsOld.getGoodsUid();
					DaikenUser dbUser = daikenUserDao.selectById(dbGoodsOld.getUserId());
					newOrder.setIdShop(0);
					newOrder.setShopName(dbUser.getNickName() + "的二手商品订单");
					priceStore = dbGoodsOld.getPriceOriginal();
					priceSales = dbGoodsOld.getPriceSales();
					newOrder.setGoodsTitles(dbGoodsOld.getTitle());
					newOrder.setPricePay(dbGoodsOld.getPriceSales());
					newOrder.setPriceTotal(dbGoodsOld.getPriceSales());
					// TODO 如果是测试版的话 支付0.01
					if (config.getRecord().equals("测试")) {
						newOrder.setPricePay(0.01);
						newOrder.setPriceTotal(0.01);
					}
					bo.setPrice(dbGoodsOld.getPriceSales());
					bo.setPrice(dbGoodsOld.getPriceOriginal());
					newOrder.setPostage(0.0);
					goodsRecordDB.setTitle(dbGoodsOld.getTitle());
					goodsRecordDB.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class).get(0));
					goodsRecordDB.setPriceSales(dbGoodsOld.getPriceSales());
					goodsRecordDB.setRecordType("二手商品 【" + dbGoodsOld.getTitle() + "】");
					goodsRecordDB.setPriceStore(dbGoodsOld.getPriceOriginal());
					goodsRecordDB.setQuantity(dbGoodsCar.getAddQuantity());
					goodsRecordDB.setSkuNo("");
				}
				newOrder.setCreateYearMonth(DaikenUtil.formatDate(now, KemeanDateFormatEnum.YEAR_MONTH));
				newOrder.setCreateTime(now);
				newOrder.setUpdateTime(now);
				daikenOrderDao.saveSelective(newOrder);
				DaikenOrderGoods newOrderGoods = new DaikenOrderGoods();
				newOrderGoods.setOrderNo(orderNo);
				newOrderGoods.setGoodsId(dbGoodsCar.getGoodsId());
				newOrderGoods.setGoodsUid(goodsUid);
				newOrderGoods.setRedBefore(redBefore);
				newOrderGoods.setRedAfter(redAfter);
				newOrderGoods.setPricePurchasing(pricePurchasing);
				newOrderGoods.setSkuNo(dbGoodsCar.getSkuNo());
				newOrderGoods.setQuantity(dbGoodsCar.getAddQuantity());
				newOrderGoods.setPriceStore(priceStore);
				newOrderGoods.setSalesPrice(priceSales);
				newOrderGoods.setGoodsRecord(JSONObject.toJSONString(goodsRecordDB));
				newOrderGoods.setCreateTime(now);
				newOrderGoods.setUpdateTime(now);
				daikenOrderGoodsDao.saveSelective(newOrderGoods);
				dbGoodsCar.setDataDeleted(true);
				dbGoodsCar.setUpdateTime(now);
				daikenGoodsCarDao.updateByPrimaryKeySelective(dbGoodsCar);
				bo.setOrderNo(orderNo);
				bo.setOrderInfo(DaikenApiResultTips.GoodsCar.SUBMIT_CAR_ORDER_SUCCESS);
				result.add(bo);
			}
		}
		return result;
	}

	public List<GoodscarInfoListBO> getCarGoodsInfo(List<DaikenGoodsCar> dbGoodsCars) {
		List<GoodscarInfoListBO> goodsCarInfoList = new ArrayList<GoodscarInfoListBO>();
		if (CollectionUtils.isNotEmpty(dbGoodsCars)) {
			for (DaikenGoodsCar dbGoodsCar : dbGoodsCars) {
				GoodscarInfoListBO infoBo = new GoodscarInfoListBO();
				BeanUtils.copyProperties(dbGoodsCar, infoBo);
				// 一手
				if (dbGoodsCar.getIsNewGoods()) {
					DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsCar.getGoodsId());
					DaikenGoodsNewSku dbGoodsSkuNo = daikenGoodsNewSkuDao.selectUniqueEntityByProperties(
							new String[] { KemeanConstant.DATA_DELETED, "skuNo" },
							new Object[] { false, dbGoodsCar.getSkuNo() });
					infoBo.setTitle(dbGoodsNew.getTitle());
					infoBo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
					infoBo.setPriceStore(dbGoodsSkuNo.getPriceStore());
					infoBo.setPriceSales(dbGoodsSkuNo.getPriceSales());
					infoBo.setDiscount(dbGoodsSkuNo.getDiscount());
					infoBo.setPostage(dbGoodsNew.getPostage());
					Integer stock = dbGoodsSkuNo.getStock() - dbGoodsSkuNo.getSalesNum();
					infoBo.setStock(stock);
					infoBo.setRecordType(DaikenUtil.parseJsonArray(dbGoodsSkuNo.getRecordType()));
					infoBo.setGoodsStatus(true);
					if (dbGoodsNew.getDataDeleted() || !dbGoodsNew.getGoodsStatus() || stock <= 0) {
						infoBo.setGoodsStatus(false);
					}
				}
				// 二手
				if (!dbGoodsCar.getIsNewGoods()) {
					DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(dbGoodsCar.getGoodsId());
					infoBo.setTitle(dbGoodsOld.getTitle());
					infoBo.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class).get(0));
					infoBo.setPriceStore(dbGoodsOld.getPriceOriginal());
					infoBo.setPriceSales(dbGoodsOld.getPriceSales());
					infoBo.setDiscount(10f);
					infoBo.setPostage(0.0);
					infoBo.setRecordType("");
					infoBo.setStock(1);
					infoBo.setGoodsStatus(true);
					if (!dbGoodsOld.getDataDeleted() || dbGoodsOld.getIsBuy()) {
						infoBo.setGoodsStatus(false);
					}
				}
				goodsCarInfoList.add(infoBo);
			}
		}
		return goodsCarInfoList;
	}

}
