package com.kemean.service.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenGoodsOld;
import com.kemean.bean.DaikenInvestigate;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.DaikenUserShop;
import com.kemean.bean.KemeanCollect;
import com.kemean.bean.KemeanFinanceOrder;
import com.kemean.bean.KemeanUserAddress;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenCollectTypeEnum;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenOperationTypeExplain;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.DaikenSmsTypeEnum;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenGoodsOldDao;
import com.kemean.dao.DaikenInvestigateDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.DaikenUserShopDao;
import com.kemean.dao.KemeanCollectDao;
import com.kemean.dao.KemeanFinanceOrderDaikenDao;
import com.kemean.dao.KemeanRegionDao;
import com.kemean.dao.KemeanUserAddressDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.api.KemeanApiAidService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.api.UserAddressBO;
import com.kemean.vo.bo.c.collect.CollectListBO;
import com.kemean.vo.bo.c.investigate.InvestigateListBO;
import com.kemean.vo.bo.c.mall.OldGoodsListBO;
import com.kemean.vo.bo.c.mine.finance.WalletBO;
import com.kemean.vo.bo.c.mine.helpsell.HelpsellGoodsListBO;
import com.kemean.vo.bo.c.mine.helpsell.ShopInfoBO;
import com.kemean.vo.bo.c.mine.old.GoodsInfoBO;
import com.kemean.vo.mysql.CollectDB;
import com.kemean.vo.po.KemeanPageApiPO;
import com.kemean.vo.po.c.collect.AddCollectPO;
import com.kemean.vo.po.c.collect.CollectListPO;
import com.kemean.vo.po.c.investigate.UpdateStatusInvestigatePO;
import com.kemean.vo.po.c.mine.UpdateUserInfoPO;
import com.kemean.vo.po.c.mine.UpdateUserPhonePO;
import com.kemean.vo.po.c.mine.finance.WalletPO;
import com.kemean.vo.po.c.mine.helpsell.AddSellGoodsPO;
import com.kemean.vo.po.c.mine.helpsell.HelpsellGoodsListPO;
import com.kemean.vo.po.c.mine.helpsell.UpdateShopInfoPO;
import com.kemean.vo.po.c.mine.old.IssueSecondGoodsPO;
import com.kemean.vo.po.c.mine.old.UpdateStatusSecondhandPO;
import com.kemean.vo.po.com.FeedBackPO;

/**
 * 【客户端】 我的业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class CMineService {

	@Autowired
	private DaikenInvestigateDao daikenInvestigateDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenGoodsOldDao daikenGoodsOldDao;

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private KemeanCollectDao kemeanCollectDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenUserShopDao daikenUserShopDao;

	@Autowired
	private KemeanUserAddressDao kemeanUserAddressDao;

	@Autowired
	private KemeanFinanceOrderDaikenDao kemeanFinanceOrderDao;

	@Autowired
	private KemeanRegionDao kemeanRegionDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private KemeanApiAidService kemeanApiAidService;

	/**
	 * 添加收藏/取消收藏
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public KemeanResult<String> addCollect(AddCollectPO addCollectPO, DaikenUser loginConsumer) {
		Date now = new Date();
		// 收藏列表取消收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_cancelCollect)) {
			List<Integer> typeIds = addCollectPO.getTypeId();
			for (Integer typeId : typeIds) {
				KemeanCollect dbCollect = kemeanCollectDao.selectById(typeId);
				dbCollect.setDataDeleted(true);
				dbCollect.setUpdateTime(now);
				kemeanCollectDao.updateByPrimaryKeySelective(dbCollect);
			}
			return new KemeanResult<>();
		}

		// 店铺取消收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_shopCancelCollect)) {
			Integer typeId = addCollectPO.getTypeId().get(0);
			KemeanCollect dbCollect = kemeanCollectDao.selectUniqueEntityByProperties(
					new String[] { "userId", "type", "typeId" },
					new Object[] { loginConsumer.getId(), DaikenOperationTypeExplain.c_shopCollect, typeId });
			dbCollect.setDataDeleted(true);
			dbCollect.setUpdateTime(now);
			kemeanCollectDao.updateByPrimaryKeySelective(dbCollect);
			return new KemeanResult<>();
		}

		// 一手商品取消收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_newGoodsCancelCollect)) {
			Integer typeId = addCollectPO.getTypeId().get(0);
			KemeanCollect dbCollect = kemeanCollectDao.selectUniqueEntityByProperties(
					new String[] { "userId", "type", "typeId" },
					new Object[] { loginConsumer.getId(), DaikenOperationTypeExplain.c_newGoodsCollect, typeId });
			dbCollect.setDataDeleted(true);
			dbCollect.setUpdateTime(now);
			kemeanCollectDao.updateByPrimaryKeySelective(dbCollect);
			return new KemeanResult<>();
		}
		// 二手商品取消收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_oldGoodsCancelCollect)) {
			Integer typeId = addCollectPO.getTypeId().get(0);
			KemeanCollect dbCollect = kemeanCollectDao
					.selectUniqueEntityByProperties(new String[] { "userId", "type", "typeId" }, new Object[] {
							loginConsumer.getId(), DaikenOperationTypeExplain.c_secondHandGoodsCollect, typeId });
			dbCollect.setDataDeleted(true);
			dbCollect.setUpdateTime(now);
			kemeanCollectDao.updateByPrimaryKeySelective(dbCollect);
			return new KemeanResult<>();
		}
		// 收藏店铺 or 一手商品收藏 or 二手商品收藏

		// 判断是否收藏过
		KemeanCollect dbCollect = kemeanCollectDao.selectUniqueEntityByProperties(
				new String[] { "userId", "type", "typeId" },
				new Object[] { loginConsumer.getId(), addCollectPO.getType(), addCollectPO.getTypeId() });
		if (dbCollect != null) {
			// 证明收藏过
			dbCollect.setDataDeleted(!dbCollect.getDataDeleted());
			dbCollect.setUpdateTime(now);
			kemeanCollectDao.updateByPrimaryKeySelective(dbCollect);
			return new KemeanResult<>();
		}

		KemeanCollect newCollect = new KemeanCollect();
		newCollect.setUserId(loginConsumer.getId());
		newCollect.setType(addCollectPO.getType());
		Integer typeId = addCollectPO.getTypeId().get(0);
		newCollect.setTypeId(typeId);
		CollectDB collectDB = new CollectDB();
		// 店铺收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_shopCollect)) {
			// 店铺信息需要 店铺名称（name），店铺图片（headImg），销量（salesVolume），商铺id（objId）
			DaikenShop dbShop = daikenShopDao.selectById(typeId);
			collectDB.setSalesVolume(dbShop.getSalesVolume());
			collectDB.setName(dbShop.getShopName());
			collectDB.setHeadImg(dbShop.getShopLogo());
			collectDB.setTypeId(dbShop.getId());
		}

		// 帮卖店铺收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_helpSellShopCollect)) {
			// 店铺信息需要 店铺名称（name），店铺图片（headImg），销量（salesVolume），商铺id（objId）
			DaikenUserShop dbUserShop = daikenUserShopDao.selectById(typeId);
			collectDB.setSalesVolume(dbUserShop.getSalesVolume());
			collectDB.setName(dbUserShop.getShopName());
			collectDB.setHeadImg(dbUserShop.getImg());
			collectDB.setTypeId(dbUserShop.getId());
		}

		// 一手商品收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_newGoodsCollect)) {
			// 一手商品信息需要
			// 商品名称（name），商品图片（headImg），规格（recordType），门市价（priceStore），销售价（priceSales），商品id（objId）
			DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(typeId);
			List<DaikenGoodsNewSku> dbGoodsNewSkus = daikenGoodsNewSkuDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
					new Object[] { false, dbGoodsNew.getId() });
			collectDB.setTypeId(dbGoodsNew.getId());
			collectDB.setName(dbGoodsNew.getTitle());
			collectDB.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
			collectDB.setPriceSales(dbGoodsNew.getMinPriceSales());
			collectDB.setPriceStore(dbGoodsNew.getMinPriceStore());
			if (CollectionUtils.isEmpty(dbGoodsNewSkus)) {
				DaikenGoodsNewSku dbGoodsNewSku = daikenGoodsNewSkuDao
						.selectByProperties(new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
								new Object[] { false, dbGoodsNew.getGoodsId() })
						.get(0);
				collectDB.setRecordType(dbGoodsNewSku.getRecordType());
			}

			if (CollectionUtils.isNotEmpty(dbGoodsNewSkus)) {
				DaikenGoodsNewSku dbGoodsNewSku = dbGoodsNewSkus.get(0);
				collectDB.setRecordType(dbGoodsNewSku.getRecordType());
			}
		}

		// 二手商品收藏
		if (addCollectPO.getType().equals(DaikenOperationTypeExplain.c_secondHandGoodsCollect)) {
			// 二手商品信息需要 商品名称（name），商品图片（headImg），门市价（priceStore），销售价（priceSales），商品id（objId）
			DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(typeId);
			collectDB.setTypeId(dbGoodsOld.getId());
			collectDB.setName(dbGoodsOld.getTitle());
			collectDB.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class).get(0));
			collectDB.setPriceStore(dbGoodsOld.getPriceOriginal());
			collectDB.setPriceSales(dbGoodsOld.getPriceSales());
			if (dbGoodsOld.getGoodsId() != null && dbGoodsOld.getGoodsId() != 0) {
				DaikenGoodsOld dbHelpGoodsOld = daikenGoodsOldDao.selectById(dbGoodsOld.getGoodsId());
				collectDB.setTypeId(dbHelpGoodsOld.getId());
				collectDB.setName(dbHelpGoodsOld.getTitle());
				collectDB.setHeadImg(JSONArray.parseArray(dbHelpGoodsOld.getImgsHead(), String.class).get(0));
				collectDB.setPriceStore(dbHelpGoodsOld.getPriceOriginal());
				collectDB.setPriceSales(dbHelpGoodsOld.getPriceSales());
			}
		}
		newCollect.setRecord(JSONObject.toJSONString(collectDB));
		newCollect.setCreateTime(now);
		newCollect.setUpdateTime(now);
		kemeanCollectDao.saveSelective(newCollect);
		return new KemeanResult<String>();
	}

	/**
	 * 收藏列表
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public KemeanPageApiBO<List<CollectListBO>> collectList(CollectListPO collectListPO, DaikenUser loginConsumer) {
		// 默认查看一手和二手商品
		Integer type = collectListPO.getCollectType() == null ? DaikenCollectTypeEnum.NEW_GOODS_COLLECT.getType()
				: collectListPO.getCollectType();
		List<Integer> types = new ArrayList<Integer>();
		types.add(type);
		if (type.equals(DaikenCollectTypeEnum.SHOP_COLLECT.getType())) {
			types.add(DaikenCollectTypeEnum.HELP_SELL_SHOP_COLLECT.getType());
		}
		List<KemeanCollect> dbCollects = kemeanCollectDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, loginConsumer.getId() },
				"type", types, "id", false, collectListPO.getPageNo(), collectListPO.getPageSize());
		List<CollectListBO> result = new ArrayList<CollectListBO>(dbCollects.size());
		if (CollectionUtils.isEmpty(dbCollects)) {
			return new KemeanPageApiBO<List<CollectListBO>>(0l, 1, result);
		}
		for (KemeanCollect dbCollect : dbCollects) {
			CollectListBO bo = new CollectListBO();
			CollectDB collectDB = JSONObject.parseObject(dbCollect.getRecord(), CollectDB.class);
			bo.setId(dbCollect.getId());
			BeanUtils.copyProperties(collectDB, bo);
			bo.setTypeId(dbCollect.getTypeId());
			bo.setUserShopId(0);
			// 店铺收藏
			if (dbCollect.getType().equals(DaikenCollectTypeEnum.SHOP_COLLECT.getType())) {
				bo.setJumpType(DaikenCollectTypeEnum.SHOP_COLLECT.getType());
				bo.setRecordType("店铺收藏");
				bo.setPriceStore(0.0);
				bo.setPriceSales(0.0);
			}

			// 帮卖店铺
			if (dbCollect.getType().equals(DaikenCollectTypeEnum.HELP_SELL_SHOP_COLLECT.getType())) {
				bo.setUserShopId(dbCollect.getTypeId());
				bo.setJumpType(DaikenCollectTypeEnum.HELP_SELL_SHOP_COLLECT.getType());
				bo.setRecordType("帮买店铺收藏");
				bo.setPriceStore(0.0);
				bo.setPriceSales(0.0);
			}

			// 一手商品收藏
			if (dbCollect.getType().equals(DaikenCollectTypeEnum.NEW_GOODS_COLLECT.getType())) {
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbCollect.getTypeId());
				bo.setUserShopId(dbGoodsNew.getUserShopId());
				bo.setJumpType(DaikenCollectTypeEnum.NEW_GOODS_COLLECT.getType());
				bo.setRecordType(DaikenUtil.parseJsonArray(collectDB.getRecordType()));
				bo.setSalesVolume(0);
			}

			// 二手商品收藏
			if (dbCollect.getType().equals(DaikenCollectTypeEnum.SECOND_GOODS_COLLECT.getType())) {
				DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(dbCollect.getTypeId());
				bo.setUserShopId(dbGoodsOld.getUserShopId());
				bo.setJumpType(DaikenCollectTypeEnum.SECOND_GOODS_COLLECT.getType());
				bo.setRecordType("二手商品收藏");
				bo.setSalesVolume(0);
			}
			result.add(bo);
		}
		PageInfo<KemeanCollect> pageInfo = new PageInfo<KemeanCollect>(dbCollects);
		return new KemeanPageApiBO<List<CollectListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 我的调研列表（只有点赞和投票）
	 * 
	 * @author huwei
	 * @date 2018年6月11日
	 */
	public KemeanPageApiBO<List<InvestigateListBO>> investigateList(KemeanPageApiPO kemeanPageApiPO,
			DaikenUser loginConsumer) {
		List<DaikenInvestigate> dbInvestigates = daikenInvestigateDao.investigateList(loginConsumer.getId(),
				kemeanPageApiPO.getPageNo(), kemeanPageApiPO.getPageSize());
		List<InvestigateListBO> result = new ArrayList<InvestigateListBO>(dbInvestigates.size());
		if (CollectionUtils.isEmpty(dbInvestigates)) {
			return new KemeanPageApiBO<List<InvestigateListBO>>(0l, 1, result);
		}
		Date dateNow = new Date();
		for (DaikenInvestigate daikenInvestigate : dbInvestigates) {
			InvestigateListBO bo = new InvestigateListBO();
			BeanUtils.copyProperties(daikenInvestigate, bo);
			Integer dateDifference = DaikenUtil.dateDifference(dateNow, daikenInvestigate.getEndTime());
			bo.setIsOver(false);
			if (dateDifference <= 0) {
				bo.setIsOver(true);
			}
			result.add(bo);
		}
		PageInfo<DaikenInvestigate> pageInfo = new PageInfo<DaikenInvestigate>(dbInvestigates);
		return new KemeanPageApiBO<List<InvestigateListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 调研删除 type 1101 : 调研上下架 1201
	 * 
	 * @author huwei
	 * @date 2018年6月11日
	 */
	public KemeanResult<String> updateStatusInvestigate(UpdateStatusInvestigatePO updateStatusInvestigatePO) {
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(updateStatusInvestigatePO.getObjId());
		Date now = new Date();
		// 删除
		if (updateStatusInvestigatePO.getType().equals(DaikenOperationTypeExplain.c_delInvestigate)) {
			dbInvestigate.setDataDeleted(true);
			dbInvestigate.setUpdateTime(now);
			daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
		}
		// 上下架
		if (updateStatusInvestigatePO.getType().equals(DaikenOperationTypeExplain.c_soldOutInvestigate)) {
			// 上架
			if (updateStatusInvestigatePO.getInvestigateStatus()) {
				if (dbInvestigate.getPayStatus()) {
					// 支付才能修改
					dbInvestigate.setInvestigateStatus(updateStatusInvestigatePO.getInvestigateStatus());
					dbInvestigate.setUpdateTime(now);
					daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
				} else {
					return new KemeanResult<String>(false, DaikenApiResultTips.Investigate.INVESTIGATE_NO_PAY);
				}

			}
			// 下架
			if (!updateStatusInvestigatePO.getInvestigateStatus()) {
				dbInvestigate.setInvestigateStatus(updateStatusInvestigatePO.getInvestigateStatus());
				dbInvestigate.setUpdateTime(now);
				daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
			}
		}
		return new KemeanResult<String>();
	}

	/**
	 * 发布二手商品
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public KemeanResult<String> issueSecondGoods(IssueSecondGoodsPO issueSecondGoodsPO, DaikenUser daikenUser) {
		Date now = new Date();
		Boolean flag = false;
		if (issueSecondGoodsPO.getObjId() != null) {
			flag = true;
		}
		DaikenGoodsOld goodsOld = null;
		if (flag) {
			goodsOld = daikenGoodsOldDao.selectById(issueSecondGoodsPO.getObjId());
		} else {
			goodsOld = new DaikenGoodsOld();
			goodsOld.setUserId(daikenUser.getId());
			goodsOld.setUserUid(daikenUser.getUid());
			goodsOld.setNickName(daikenUser.getNickName());
			goodsOld.setUserHeadImg(daikenUser.getHeadImg());
		}
		BeanUtils.copyProperties(issueSecondGoodsPO, goodsOld);
		goodsOld.setImgsHead(DaikenUtil.parseJSONArrayByList(issueSecondGoodsPO.getImgsHead()).toString());
		DaikenGoodsBaseCategory dbGoodsBaseCategory = daikenGoodsBaseCategoryDao
				.selectById(issueSecondGoodsPO.getCategoryId());
		goodsOld.setCategoryName(dbGoodsBaseCategory.getName());
		goodsOld.setCreateTime(now);
		goodsOld.setUpdateTime(now);
		if (flag) {
			daikenGoodsOldDao.updateByPrimaryKeySelective(goodsOld);
		} else {
			goodsOld.setGoodsUid(DaikenUtil.getGoodsUid(now));
			daikenGoodsOldDao.saveSelective(goodsOld);
		}
		return new KemeanResult<String>();
	}

	/**
	 * 二手商品信息
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public GoodsInfoBO goodsInfo(Integer objId) {
		GoodsInfoBO bo = new GoodsInfoBO();
		DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(objId);
		BeanUtils.copyProperties(dbGoodsOld, bo);
		bo.setImgsHead(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class));
		return bo;
	}

	/**
	 * 我的二手商品列表
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public KemeanPageApiBO<List<OldGoodsListBO>> oldGoodsList(DaikenUser loginConsumer) {
		List<DaikenGoodsOld> dbGoodsOlds = daikenGoodsOldDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId" }, new Object[] { false, loginConsumer.getId() });
		List<OldGoodsListBO> result = new ArrayList<OldGoodsListBO>();
		if (CollectionUtils.isEmpty(dbGoodsOlds)) {
			return new KemeanPageApiBO<List<OldGoodsListBO>>(0l, 1, result);
		}
		for (DaikenGoodsOld daikenGoodsOld : dbGoodsOlds) {
			OldGoodsListBO bo = new OldGoodsListBO();
			BeanUtils.copyProperties(daikenGoodsOld, bo);
			bo.setNickName(loginConsumer.getNickName());
			bo.setUserHeadImg(loginConsumer.getHeadImg());
			bo.setHeadImg(JSONArray.parseArray(daikenGoodsOld.getImgsHead(), String.class));
			bo.setQualityStr(daikenGoodsOld.getQuality() + "成新");
			result.add(bo);
		}
		return new KemeanPageApiBO<List<OldGoodsListBO>>(10l, 1, result);
	}

	/**
	 * 二手商品上下架、删除 type = 1101 上下架 type = 1102 删除
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public KemeanResult<String> updateStatusSecondhand(UpdateStatusSecondhandPO updateStatusSecondhandPO) {
		Date now = new Date();
		DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(updateStatusSecondhandPO.getObjId());
		// 上下架
		if (updateStatusSecondhandPO.getType().equals(DaikenOperationTypeExplain.c_soldOutldGoods)) {
			dbGoodsOld.setGoodsStatus(updateStatusSecondhandPO.getStatus());
			dbGoodsOld.setUpdateTime(now);
			daikenGoodsOldDao.updateByPrimaryKeySelective(dbGoodsOld);
			return new KemeanResult<String>();
		}
		// 删除
		if (updateStatusSecondhandPO.getType().equals(DaikenOperationTypeExplain.c_delOldGoods)) {
			dbGoodsOld.setDataDeleted(true);
			dbGoodsOld.setUpdateTime(now);
			daikenGoodsOldDao.updateByPrimaryKeySelective(dbGoodsOld);
			return new KemeanResult<String>();
		}
		return new KemeanResult<String>();
	}

	/**
	 * 帮买店铺信息
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public ShopInfoBO shopInfo(DaikenUser loginConsumer) {
		DaikenUserShop dbUserShop = daikenUserShopDao.selectUniqueEntityByProperty("userId", loginConsumer.getId());
		ShopInfoBO bo = new ShopInfoBO();
		BeanUtils.copyProperties(dbUserShop, bo);
		bo.setDegreeOfPraise(dbUserShop.getDegreeOfPraise() * 100);
		return bo;
	}

	/**
	 * 我的帮卖商品列表
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public KemeanPageApiBO<List<HelpsellGoodsListBO>> helpsellGoodsList(DaikenUser loginConsumer,
			HelpsellGoodsListPO helpsellGoodsListPO) {
		Date now = new Date();
		Boolean old = false;
		if (helpsellGoodsListPO.getOld() != null) {
			old = helpsellGoodsListPO.getOld();
		}

		// 先找店铺
		DaikenUserShop dbUserShop = daikenUserShopDao.selectUniqueEntityByProperty("userId", loginConsumer.getId());
		Integer userShopId = 0;
		if (dbUserShop != null) {
			userShopId = dbUserShop.getId();
		} else {
			DaikenUserShop newUserShop = new DaikenUserShop();
			newUserShop.setUserId(loginConsumer.getId());
			newUserShop.setImg(loginConsumer.getHeadImg());
			newUserShop.setShopName(loginConsumer.getNickName() + "的店铺");
			newUserShop.setCreateTime(now);
			newUserShop.setUpdateTime(now);
			daikenUserShopDao.saveSelective(newUserShop);
			userShopId = newUserShop.getId();
		}

		// 一手
		if (!old) {
			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userShopId" }, new Object[] { false, userShopId },
					helpsellGoodsListPO.getPageNo(), helpsellGoodsListPO.getPageSize());
			List<HelpsellGoodsListBO> result = new ArrayList<HelpsellGoodsListBO>(dbGoodsNews.size());
			if (CollectionUtils.isEmpty(dbGoodsNews)) {
				return new KemeanPageApiBO<List<HelpsellGoodsListBO>>(0l, 1, result);
			}
			for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
				HelpsellGoodsListBO bo = new HelpsellGoodsListBO();
				BeanUtils.copyProperties(dbGoodsNew, bo);
				bo.setGoodsId(dbGoodsNew.getId());
				bo.setIsOld(false);
				bo.setHeadImg(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class).get(0));
				bo.setPriceSales(dbGoodsNew.getMinPriceSales());
				bo.setPriceStore(dbGoodsNew.getMinPriceStore());
				bo.setPricePurchasing(dbGoodsNew.getPricePurchasing());
				result.add(bo);
			}
			PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<DaikenGoodsNew>(dbGoodsNews);
			return new KemeanPageApiBO<List<HelpsellGoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
		}

		// 二手
		if (old) {
			List<DaikenGoodsOld> dbGoodsOlds = daikenGoodsOldDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userShopId" }, new Object[] { false, userShopId },
					helpsellGoodsListPO.getPageNo(), helpsellGoodsListPO.getPageSize());
			List<HelpsellGoodsListBO> result = new ArrayList<HelpsellGoodsListBO>(dbGoodsOlds.size());
			for (DaikenGoodsOld dbGoodsOld : dbGoodsOlds) {
				HelpsellGoodsListBO bo = new HelpsellGoodsListBO();
				BeanUtils.copyProperties(dbGoodsOld, bo);
				bo.setGoodsId(dbGoodsOld.getId());
				bo.setIsOld(true);
				bo.setHeadImg(JSONArray.parseArray(dbGoodsOld.getImgsHead(), String.class).get(0));
				bo.setPriceStore(dbGoodsOld.getPriceOriginal());
				result.add(bo);
			}
			PageInfo<DaikenGoodsOld> pageInfo = new PageInfo<DaikenGoodsOld>(dbGoodsOlds);
			return new KemeanPageApiBO<List<HelpsellGoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
		}
		return new KemeanPageApiBO<List<HelpsellGoodsListBO>>(0l, 1, Arrays.asList());
	}

	/**
	 * 添加帮卖商品
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	@Transactional
	public KemeanResult<String> operationSellGoods(AddSellGoodsPO addSellGoodsPO, DaikenUser loginConsumer) {
		// 添加帮卖商品
		if (addSellGoodsPO.getOperationType().equals(DaikenOperationTypeExplain.c_addSellGoods)) {
			Date now = new Date();
			DaikenUserShop dbUserShop = daikenUserShopDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "userId" },
					new Object[] { false, loginConsumer.getId() });
			Integer userShopId = 0;
			if (dbUserShop != null) {
				userShopId = dbUserShop.getId();
			}
			if (dbUserShop == null) {
				// 创建一个帮卖店铺
				DaikenUserShop newUserShop = new DaikenUserShop();
				newUserShop.setUserId(loginConsumer.getId());
				newUserShop.setImg(loginConsumer.getHeadImg());
				newUserShop.setShopName(loginConsumer.getNickName() + "的店铺");
				newUserShop.setCreateTime(now);
				newUserShop.setUpdateTime(now);
				daikenUserShopDao.saveSelective(newUserShop);
				userShopId = newUserShop.getId();
			}

			// 一手
			if (!addSellGoodsPO.getIsOld()) {
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(addSellGoodsPO.getObjId());
				if (dbGoodsNew.getGoodsId() != 0) {
					dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsNew.getGoodsId());
				}

				// 判断是否已经添加过
				List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "userShopId", "goodsId" },
						new Object[] { false, userShopId, dbGoodsNew.getId() });
				if (CollectionUtils.isNotEmpty(dbGoodsNews)) {
					return new KemeanResult<>(false, DaikenApiResultTips.GoodsPurchasing.ADD_GOODS_PURCHASING_ERROR);
				}
				DaikenGoodsNew newGoods = new DaikenGoodsNew();
				BeanUtils.copyProperties(dbGoodsNew, newGoods);
				newGoods.setId(null);
				newGoods.setShopId(0);
				newGoods.setUserShopId(userShopId);
				newGoods.setGoodsId(addSellGoodsPO.getObjId());
				// 不允许代卖了
				newGoods.setPurchasing(false);
				newGoods.setCreateTime(now);
				newGoods.setUpdateTime(now);
				daikenGoodsNewDao.saveSelective(newGoods);
				return new KemeanResult<>();
			}
			// 二手
			if (addSellGoodsPO.getIsOld()) {
				// 判断是否已经添加过
				List<DaikenGoodsOld> dbGoodsOlds = daikenGoodsOldDao.selectByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "userShopId", "goodsId" },
						new Object[] { false, userShopId, addSellGoodsPO.getObjId() });
				if (CollectionUtils.isNotEmpty(dbGoodsOlds)) {
					return new KemeanResult<>(false, DaikenApiResultTips.GoodsPurchasing.ADD_GOODS_PURCHASING_ERROR);
				}
				DaikenGoodsOld dbGoodsOld = daikenGoodsOldDao.selectById(addSellGoodsPO.getObjId());
				// 自己不能代卖自己的
				if (dbGoodsOld.getUserId().equals(loginConsumer.getId())) {
					return new KemeanResult<>(false, DaikenApiResultTips.GoodsPurchasing.SELF_GOODS_PURCHASING_ERROR);
				}
				DaikenGoodsOld newGoodsOld = new DaikenGoodsOld();
				BeanUtils.copyProperties(dbGoodsOld, newGoodsOld);
				newGoodsOld.setNickName(loginConsumer.getNickName());
				newGoodsOld.setUserHeadImg(loginConsumer.getHeadImg());
				newGoodsOld.setId(null);
				newGoodsOld.setUserShopId(userShopId);
				newGoodsOld.setGoodsId(addSellGoodsPO.getObjId());
				newGoodsOld.setPurchasing(false);
				newGoodsOld.setCreateTime(now);
				newGoodsOld.setUpdateTime(now);
				daikenGoodsOldDao.saveSelective(newGoodsOld);
				return new KemeanResult<>();
			}
		}
		if (addSellGoodsPO.getOperationType().equals(DaikenOperationTypeExplain.c_overSellGoods)) {
			// 一手商品删除
			if (!addSellGoodsPO.getIsOld()) {
				daikenGoodsNewDao.updateEntityByProperties("dataDeleted", true, "id", addSellGoodsPO.getObjId());
			}

			// 二手商品删除
			if (addSellGoodsPO.getIsOld()) {
				daikenGoodsOldDao.updateEntityByProperties("dataDeleted", true, "id", addSellGoodsPO.getObjId());
			}
			return new KemeanResult<>();
		}

		return new KemeanResult<String>();
	}

	/**
	 * 修改帮买店铺信息
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	public KemeanResult<String> updateShopInfo(UpdateShopInfoPO updateShopInfoPO, DaikenUser daikenUser) {
		DaikenUserShop dbUserShop = daikenUserShopDao.selectUniqueEntityByProperty("userId", daikenUser.getId());
		BeanUtils.copyProperties(updateShopInfoPO, dbUserShop);
		dbUserShop.setUpdateTime(new Date());
		daikenUserShopDao.updateByPrimaryKeySelective(dbUserShop);
		return new KemeanResult<String>();
	}

	/**
	 * 修改个人信息
	 * 
	 * @author huwei
	 * @date 2018年6月13日
	 */
	public KemeanResult<String> updateUserInfo(UpdateUserInfoPO updateUserInfoPO, DaikenUser loginConsumer) {
		if (loginConsumer.getAge() == 0 && loginConsumer.getProfession() == 0
				&& StringUtils.isBlank(loginConsumer.getHobbiesInterests())) {
			// TODO 第一次完整填写完之后给予奖励

		}
		BeanUtils.copyProperties(updateUserInfoPO, loginConsumer);
		loginConsumer.setHobbiesInterests(
				DaikenUtil.parseJSONArrayByList(updateUserInfoPO.getHobbiesInterests()).toString());
		loginConsumer.setProvinceName(kemeanRegionDao.selectById(updateUserInfoPO.getProvinceId()).getName());
		loginConsumer.setCityName(kemeanRegionDao.selectById(updateUserInfoPO.getCityId()).getName());
		loginConsumer.setAreaName(kemeanRegionDao.selectById(updateUserInfoPO.getAreaId()).getName());
		loginConsumer.setUpdateTime(new Date());
		daikenUserDao.updateByPrimaryKeySelective(loginConsumer);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), loginConsumer.getToken()));
		return new KemeanResult<>();
	}

	/**
	 * 修改手机号
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	public KemeanResult<String> updateUserPhone(UpdateUserPhonePO updateUserPhonePO, DaikenUser daikenUser) {
		if (!KemeanConstant.SUPER_CODE.equals(updateUserPhonePO.getCode())) {
			String cachaKey = String.format(DaikenRedisKeyEnum.CODE.getKey(),
					DaikenSmsTypeEnum.C_UPDATE_PHONE.getType(), updateUserPhonePO.getPhone());
			String cacheCode = kemeanRedisService.getString(cachaKey);
			if (StringUtils.isBlank(cacheCode)) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_NOT_EXIT);
			}
			if (!cacheCode.equals(updateUserPhonePO.getCode())) {
				return new KemeanResult<>(false, DaikenApiResultTips.CODE_CHECK.CODE_ERROR);
			}
			kemeanRedisService.del(cachaKey);
		}
		daikenUser.setPhone(updateUserPhonePO.getPhone());
		daikenUserDao.updateByPrimaryKeySelective(daikenUser);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
		return new KemeanResult<>();
	}

	/**
	 * 反馈意见
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	public KemeanResult<String> feedBack(FeedBackPO feedBackPO, DaikenUser loginUser) {
		kemeanApiAidService.feedbackAdd(feedBackPO.getFeedbackContent(), feedBackPO.getNeedContact(),
				loginUser.getHeadImg(), feedBackPO.getPhone(), loginUser.getNickName(), loginUser.getUserStatus());
		return new KemeanResult<>();
	}

	/**
	 * 获取默认地址
	 * 
	 * @author huwei
	 * @date 2018年7月9日
	 */
	public KemeanResult<UserAddressBO> defaultAddress(Integer userId) {
		KemeanUserAddress dbUserAddress = kemeanUserAddressDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "userId", "isDefault" },
				new Object[] { false, userId, true });
		UserAddressBO bo = new UserAddressBO();
		if (dbUserAddress != null) {
			BeanUtils.copyProperties(dbUserAddress, bo);
			return new KemeanResult<UserAddressBO>(bo);
		}
		return new KemeanResult<UserAddressBO>(bo);
	}

	/**
	 * 钱包信息
	 * 
	 * @author huwei
	 * @date 2018年7月11日
	 */
	public KemeanPageApiBO<List<WalletBO>> wallet(WalletPO walletPO, DaikenUser loginConsumer) {
		Integer day = 7;
		if (walletPO.getDays() != null) {
			day = walletPO.getDays();
		}
		Date plusDay = KemeanUtilAid.getDateByCalendar(new Date(), KemeanCalendarFieldEnum.DAY, -day);
		List<KemeanFinanceOrder> dbFinanceOrders = kemeanFinanceOrderDao.wallet(loginConsumer.getId(), plusDay,
				walletPO.getPageNo(), walletPO.getPageSize());
		List<WalletBO> result = new ArrayList<WalletBO>(dbFinanceOrders.size());
		if (CollectionUtils.isEmpty(dbFinanceOrders)) {
			return new KemeanPageApiBO<List<WalletBO>>(0l, 1, result);
		}
		for (KemeanFinanceOrder kemeanFinanceOrder : dbFinanceOrders) {
			WalletBO bo = new WalletBO();
			bo.setTypeStr(DaikenMapData.financeType.get(kemeanFinanceOrder.getFinanceType()));
			bo.setCreateTimeStr(DaikenUtil.formatDate(kemeanFinanceOrder.getCreateTime(), KemeanDateFormatEnum.DATE));
			bo.setMoney(kemeanFinanceOrder.getMoney());
			result.add(bo);
		}
		PageInfo<KemeanFinanceOrder> pageInfo = new PageInfo<KemeanFinanceOrder>(dbFinanceOrders);
		return new KemeanPageApiBO<List<WalletBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}
}
