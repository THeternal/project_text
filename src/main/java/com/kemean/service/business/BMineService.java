package com.kemean.service.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsBaseCategory;
import com.kemean.bean.DaikenGoodsBaseType;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsNewSku;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenShopReply;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenGoodsType;
import com.kemean.constant.DaikenOperationTypeExplain;
import com.kemean.constant.DaikenUserEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.dao.DaikenGoodsBaseCategoryDao;
import com.kemean.dao.DaikenGoodsBaseTypeDao;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsNewSkuDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenShopReplyDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.api.KemeanApiAidService;
import com.kemean.service.common.CommonService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.b.child.AccountListBO;
import com.kemean.vo.bo.b.goods.GoodsInfoBO;
import com.kemean.vo.bo.b.goods.GoodsListBO;
import com.kemean.vo.bo.b.goods.GoodsSkusBO;
import com.kemean.vo.bo.b.goods.RecordTypeBO;
import com.kemean.vo.bo.b.reply.FastReplyListBO;
import com.kemean.vo.po.b.goods.AddGoodsSkuPO;
import com.kemean.vo.po.b.goods.GoodsListPO;
import com.kemean.vo.po.b.goods.GoodsPO;
import com.kemean.vo.po.b.goods.OperationGoodsPO;
import com.kemean.vo.po.b.goods.RecordTypePO;
import com.kemean.vo.po.b.reply.AddAccountPO;
import com.kemean.vo.po.b.reply.FastReplyPO;
import com.kemean.vo.po.com.FeedBackPO;

/**
 * 
 * 【商户端】我的业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */

@Service
public class BMineService {

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenGoodsNewSkuDao daikenGoodsNewSkuDao;

	@Autowired
	private DaikenGoodsBaseCategoryDao daikenGoodsBaseCategoryDao;

	@Autowired
	private DaikenGoodsBaseTypeDao daikenGoodsBaseTypeDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private DaikenShopReplyDao daikenShopReplyDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private KemeanApiAidService kemeanApiAidService;

	@Autowired
	private CommonService commonService;

	/**
	 * 商品列表
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public KemeanPageApiBO<List<GoodsListBO>> goodsList(GoodsListPO goodsListPO, DaikenUser loginBusiness) {
		// 默认显示全部
		List<Integer> salesTypes = new ArrayList<Integer>();
		if (goodsListPO.getSalesType() != null) {
			salesTypes.add(goodsListPO.getSalesType());
		} else {
			salesTypes.add(DaikenGoodsType.SalesType.NORMAL_SALES.getType());
			salesTypes.add(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType());
			salesTypes.add(DaikenGoodsType.SalesType.LOSS_MONEY.getType());
			salesTypes.add(DaikenGoodsType.SalesType.NINE_EXEMPTION_POSTAGE.getType());
			salesTypes.add(DaikenGoodsType.SalesType.DISCOUNT_FAMOUS_BRAND.getType());
		}

		String title = "";
		if (goodsListPO.getTitle() != null) {
			title = goodsListPO.getTitle();
		}

		List<DaikenGoodsNew> dbDaikenGoodsNews = daikenGoodsNewDao.goodsList(salesTypes, title,
				loginBusiness.getShopId(), goodsListPO.getPageNo(), goodsListPO.getPageSize());
		List<GoodsListBO> result = new ArrayList<GoodsListBO>(dbDaikenGoodsNews.size());
		if (CollectionUtils.isEmpty(dbDaikenGoodsNews)) {
			return new KemeanPageApiBO<List<GoodsListBO>>(0L, 1, result);
		}
		List<GoodsListBO> data = new ArrayList<GoodsListBO>();
		for (DaikenGoodsNew daikenGoodsNew : dbDaikenGoodsNews) {
			GoodsListBO bo = new GoodsListBO();
			BeanUtils.copyProperties(daikenGoodsNew, bo);
			bo.setHeadImg(JSONArray.parseArray(daikenGoodsNew.getImgsHead(), String.class).get(0));
			data.add(bo);
		}
		PageInfo<DaikenGoodsNew> pageInfo = new PageInfo<>(dbDaikenGoodsNews);
		return new KemeanPageApiBO<List<GoodsListBO>>(pageInfo.getTotal(), pageInfo.getPages(), data);
	}

	/**
	 * 单个商品信息
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public GoodsInfoBO goodsInfo(Integer objId) {
		GoodsInfoBO bo = new GoodsInfoBO();
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(objId);
		BeanUtils.copyProperties(dbGoodsNew, bo);
		bo.setImgsHead(JSONArray.parseArray(dbGoodsNew.getImgsHead(), String.class));
		bo.setImgsDesc(JSONArray.parseArray(dbGoodsNew.getImgsDesc(), String.class));
		bo.setRecordTypeStr("");
		bo.setDiscountTimeBegin("");
		bo.setDiscountTimeEnd("");
		if (dbGoodsNew.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
			bo.setDiscountTimeBegin(DaikenUtil.formatDate(dbGoodsNew.getDiscountTimeBegin()));
			bo.setDiscountTimeEnd(DaikenUtil.formatDate(dbGoodsNew.getDiscountTimeEnd()));
		}
		List<DaikenGoodsNewSku> dbGoodsNewSkus = daikenGoodsNewSkuDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" }, new Object[] { false, objId });
		if (CollectionUtils.isNotEmpty(dbGoodsNewSkus)) {
			List<GoodsSkusBO> goodsSkus = new ArrayList<GoodsSkusBO>(dbGoodsNewSkus.size());
			for (DaikenGoodsNewSku daikenGoodsNewSku : dbGoodsNewSkus) {
				GoodsSkusBO skuBo = new GoodsSkusBO();
				BeanUtils.copyProperties(daikenGoodsNewSku, skuBo);
				List<RecordTypeBO> recordTypeBO = new ArrayList<RecordTypeBO>();
				JSONArray jsonArra = JSONObject.parseArray(daikenGoodsNewSku.getRecordType());
				for (int i = 0; i < jsonArra.size(); i++) {
					JSONObject obj = (JSONObject) JSONObject.toJSON(jsonArra.get(i));
					Iterator<Entry<String, Object>> it = obj.entrySet().iterator();
					while (it.hasNext()) {
						RecordTypeBO recordTypeCBO = new RecordTypeBO();
						Entry<String, Object> entry = it.next();
						List<DaikenGoodsBaseType> dbGoodsBaseTypes = daikenGoodsBaseTypeDao.selectByProperties(
								new String[] { KemeanConstant.DATA_DELETED, "name" },
								new Object[] { false, entry.getKey() });
						DaikenGoodsBaseType dbGoodsBaseType = dbGoodsBaseTypes.get(0);
						recordTypeCBO.setTypeId(dbGoodsBaseType.getId());
						recordTypeCBO.setTypeName(dbGoodsBaseType.getName());
						recordTypeCBO.setTypeValue(entry.getValue().toString());
						recordTypeBO.add(recordTypeCBO);
					}
				}
				skuBo.setRecordTypeBO(recordTypeBO);
				goodsSkus.add(skuBo);
			}
			bo.setGoodsSkus(goodsSkus);
		}
		return bo;
	}

	/**
	 * 删除、上下架商品(帮卖店铺随着主店铺商品信息变动)
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	@Transactional
	public KemeanResult<String> operationGoods(OperationGoodsPO operationGoodsPO) {
		DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(operationGoodsPO.getObjId());
		List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
				new Object[] { false, operationGoodsPO.getObjId() });
		Date now = new Date();
		// 删除商品
		if (operationGoodsPO.getType().equals(DaikenOperationTypeExplain.b_delGoods)) {
			// 删除(skuNo)子商品
			List<DaikenGoodsNewSku> dbGoodsNewSkus = daikenGoodsNewSkuDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsId" },
					new Object[] { false, dbGoodsNew.getId() });
			for (DaikenGoodsNewSku daikenGoodsNewSku : dbGoodsNewSkus) {
				daikenGoodsNewSku.setUpdateTime(now);
				daikenGoodsNewSku.setDataDeleted(true);
				daikenGoodsNewSkuDao.updateByPrimaryKeySelective(daikenGoodsNewSku);
			}
			dbGoodsNew.setUpdateTime(now);
			dbGoodsNew.setDataDeleted(true);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);

			if (CollectionUtils.isNotEmpty(dbGoodsNews)) {
				for (DaikenGoodsNew daikenGoodsNew : dbGoodsNews) {
					daikenGoodsNew.setDataDeleted(true);
					daikenGoodsNew.setUpdateTime(now);
					daikenGoodsNewDao.updateByPrimaryKeySelective(daikenGoodsNew);
				}
			}
			return new KemeanResult<String>();
		}
		// 上，下架商品
		if (operationGoodsPO.getType().equals(DaikenOperationTypeExplain.b_soldOutGoods)) {
			Boolean goodsStatus = dbGoodsNew.getGoodsStatus();
			dbGoodsNew.setUpdateTime(now);
			dbGoodsNew.setGoodsStatus(!goodsStatus);
			daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
			if (CollectionUtils.isNotEmpty(dbGoodsNews)) {
				for (DaikenGoodsNew daikenGoodsNew : dbGoodsNews) {
					daikenGoodsNew.setGoodsStatus(!goodsStatus);
					daikenGoodsNew.setUpdateTime(now);
					daikenGoodsNewDao.updateByPrimaryKeySelective(daikenGoodsNew);
				}
			}
			return new KemeanResult<String>();
		}
		return new KemeanResult<String>();
	}

	/**
	 * 添加、修改 sknNo = 商品id + 规格1id + 规格1内容 + 规格2id + 规格2内容
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	@Transactional
	public KemeanResult<String> addGoods(GoodsPO addGoodsPO, DaikenUser daikenUser, Boolean platform) {
		Integer shopId = addGoodsPO.getShopId();
		if (daikenUser != null) {
			shopId = daikenUser.getShopId();
		}

		Date now = new Date();
		DaikenGoodsBaseCategory dbBaseCatrgory = daikenGoodsBaseCategoryDao.selectById(addGoodsPO.getCategoryId());
		DaikenGoodsNew goodsNew = null;
		if (addGoodsPO.getObjId() == null) {
			goodsNew = new DaikenGoodsNew();
		}
		if (addGoodsPO.getObjId() != null) {
			goodsNew = daikenGoodsNewDao.selectById(addGoodsPO.getObjId());
			Boolean isAudit = false;
			// 如果更新的是商品标题、图片及商品说明三个字段，则需要重新进入审核流程，未审核完成则上架的依然是旧的信息
			String imgsHead = DaikenUtil.parseJSONArrayByList(addGoodsPO.getImgsHead()).toString();
			String imgsDesc = DaikenUtil.parseJSONArrayByList(addGoodsPO.getImgsDesc()).toString();
			// 用于处理平台审核通过，覆盖旧的信息。
			if (!platform) {
				if (!goodsNew.getTitle().equals(addGoodsPO.getTitle())
						|| !goodsNew.getDescStr().equals(addGoodsPO.getDescStr())
						|| !goodsNew.getImgsHead().equals(imgsHead) || !goodsNew.getImgsDesc().equals(imgsDesc)) {
					isAudit = true;
					if (isAudit) {
						goodsNew.setEditRecord(JSONObject.toJSONString(addGoodsPO));
						goodsNew.setGoodsStatus(false);
						goodsNew.setAuditStatus(KemeanSettledEnum.UPDATE_WAIT_AUDIT.getStatus());
						daikenGoodsNewDao.updateByPrimaryKeySelective(goodsNew);
						return new KemeanResult<String>();
					}
				}
			}

			List<DaikenGoodsNew> dbGoodsNews = daikenGoodsNewDao.selectByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "goodsId" }, new Object[] { false, goodsNew.getId() });
			if (CollectionUtils.isNotEmpty(dbGoodsNews)) {
				// 修改帮卖商品信息
				for (DaikenGoodsNew dbGoodsNew : dbGoodsNews) {
					dbGoodsNew.setCategoryName(dbBaseCatrgory.getName());
					dbGoodsNew.setImgsHead(imgsHead);
					dbGoodsNew.setImgsDesc(imgsDesc);
					AddGoodsSkuPO goodsSku = addGoodsPO.getGoodsSkuPO().get(0);
					List<RecordTypePO> recordTypes = goodsSku.getRecordTypePO();
					JSONArray array = new JSONArray();
					for (RecordTypePO recordType : recordTypes) {
						JSONObject recordJson = new JSONObject();
						DaikenGoodsBaseType dbBaseType = daikenGoodsBaseTypeDao.selectById(recordType.getTypeId());
						recordJson.put("typeId", dbBaseType.getId());
						recordJson.put("typeValue", dbBaseType.getName());
						List<AddGoodsSkuPO> goodsSkus = addGoodsPO.getGoodsSkuPO();
						HashSet<String> set = new HashSet<String>();
						for (AddGoodsSkuPO goodsSkuC : goodsSkus) {
							List<RecordTypePO> recordTypeCs = goodsSkuC.getRecordTypePO();
							for (RecordTypePO recordTypePO : recordTypeCs) {
								if (recordTypePO.getTypeId().equals(dbBaseType.getId())) {
									set.add(recordTypePO.getTypeValue());
								}
							}
						}
						recordJson.put("recordTypeZBO", set.toArray());
						array.add(recordJson);
					}
					dbGoodsNew.setRecordType(array.toJSONString());
					dbGoodsNew.setUpdateTime(now);
					if (isAudit) {
						// 未审核完成则上架的依然是旧的信息,将更新的信息先保存,待审核通过覆盖
						dbGoodsNew.setEditRecord(JSONObject.toJSONString(addGoodsPO));
						dbGoodsNew.setAuditStatus(KemeanSettledEnum.UPDATE_WAIT_AUDIT.getStatus());
					}
					daikenGoodsNewDao.updateByPrimaryKeySelective(dbGoodsNew);
				}
			}

		}

		// 平台发布商品，不需要审核,默认商品上架
		if (platform) {
			goodsNew.setAuditStatus(KemeanSettledEnum.AUDIT_PASS.getStatus());
		}

		BeanUtils.copyProperties(addGoodsPO, goodsNew);

		// 折扣时间
		if (addGoodsPO.getSalesType().equals(DaikenGoodsType.SalesType.DISCOUNT_LIMITED_TIME.getType())) {
			goodsNew.setDiscountTimeBegin(addGoodsPO.getDiscountTimeBegin());
			goodsNew.setDiscountTimeEnd(addGoodsPO.getDiscountTimeEnd());
		}
		goodsNew.setShopId(shopId);
		goodsNew.setCategoryName(dbBaseCatrgory.getName());

		goodsNew.setImgsHead(DaikenUtil.parseJSONArrayByList(addGoodsPO.getImgsHead()).toString());
		goodsNew.setImgsDesc(DaikenUtil.parseJSONArrayByList(addGoodsPO.getImgsDesc()).toString());
		AddGoodsSkuPO goodsSku = addGoodsPO.getGoodsSkuPO().get(0);
		List<RecordTypePO> recordTypes = goodsSku.getRecordTypePO();
		JSONArray array = new JSONArray();
		for (RecordTypePO recordType : recordTypes) {
			JSONObject recordJson = new JSONObject();
			DaikenGoodsBaseType dbBaseType = daikenGoodsBaseTypeDao.selectById(recordType.getTypeId());
			recordJson.put("typeId", dbBaseType.getId());
			recordJson.put("typeValue", dbBaseType.getName());
			List<AddGoodsSkuPO> goodsSkus = addGoodsPO.getGoodsSkuPO();
			HashSet<String> set = new HashSet<String>();
			for (AddGoodsSkuPO goodsSkuC : goodsSkus) {
				List<RecordTypePO> recordTypeCs = goodsSkuC.getRecordTypePO();
				for (RecordTypePO recordTypePO : recordTypeCs) {
					if (recordTypePO.getTypeId().equals(dbBaseType.getId())) {
						set.add(recordTypePO.getTypeValue());
					}
				}
			}
			recordJson.put("recordTypeZBO", set.toArray());
			array.add(recordJson);
		}
		goodsNew.setRecordType(array.toJSONString());

		goodsNew.setUpdateTime(now);
		// 如果商铺没有审核通过的话，商品为下架状态
		DaikenShop dbShop = daikenShopDao.selectById(shopId);

		if (!KemeanSettledEnum.AUDIT_PASS.getStatus().equals(dbShop.getAuditStatus())) {
			goodsNew.setGoodsStatus(false);
		}
		if (addGoodsPO.getObjId() == null) {
			// 添加uid
			goodsNew.setGoodsUid(DaikenUtil.getGoodsUid(now));
			goodsNew.setCreateTime(now);
			daikenGoodsNewDao.saveSelective(goodsNew);
		}
		if (addGoodsPO.getObjId() != null) {
			daikenGoodsNewDao.updateByPrimaryKeySelective(goodsNew);
		}
		Double minPriceSales = 100000000.0;
		Float minDiscount = 0f;
		Double minPriceStore = 0.0;
		// 删除本商品所有的规格，然后再进行添加
		daikenGoodsNewSkuDao.updateEntityByProperties("dataDeleted", true, "goodsId", goodsNew.getId());
		List<AddGoodsSkuPO> goodsSkuPO = addGoodsPO.getGoodsSkuPO();
		for (int i = 0; i <= goodsSkuPO.size() - 1; i++) {
			AddGoodsSkuPO addGoodsSkuPO = goodsSkuPO.get(i);
			DaikenGoodsNewSku goodsNewSku = new DaikenGoodsNewSku();
			String skuNo = goodsNew.getId() + "";
			List<RecordTypePO> recordTypePO = addGoodsSkuPO.getRecordTypePO();
			if (CollectionUtils.isNotEmpty(recordTypePO)) {
				JSONArray recordTypeArray = new JSONArray();
				JSONArray recordTypeBackstageUseArray = new JSONArray();
				for (RecordTypePO recordTypeChild : recordTypePO) {
					skuNo = skuNo + recordTypeChild.getTypeId();
					skuNo = skuNo + recordTypeChild.getTypeValue();
					JSONObject recordTypeJson = new JSONObject();
					DaikenGoodsBaseType dbBaseType = daikenGoodsBaseTypeDao.selectById(recordTypeChild.getTypeId());
					recordTypeJson.put(dbBaseType.getName(), recordTypeChild.getTypeValue());
					recordTypeArray.add(recordTypeJson);
					JSONObject recordTypeUseJson = new JSONObject();
					recordTypeUseJson.put("typeId", dbBaseType.getId());
					recordTypeUseJson.put("typeValue", dbBaseType.getName());
					recordTypeUseJson.put("typeContent", recordTypeChild.getTypeValue());
					recordTypeBackstageUseArray.add(recordTypeUseJson);
				}
				BeanUtils.copyProperties(addGoodsSkuPO, goodsNewSku);
				goodsNewSku.setRecordType(recordTypeArray.toString());
				goodsNewSku.setRecordTypeBackstageUse(recordTypeBackstageUseArray.toString());
				goodsNewSku.setSkuNo(KemeanUtilAid.md5(skuNo));
				goodsNewSku.setGoodsId(goodsNew.getId());
				Double priceSales = addGoodsSkuPO.getPriceSales();
				goodsNewSku.setPriceSales(priceSales);
				goodsNewSku.setUpdateTime(now);
				goodsNewSku.setCreateTime(now);
				daikenGoodsNewSkuDao.saveSelective(goodsNewSku);
				if (minPriceSales > priceSales) {
					minPriceSales = priceSales;
					minDiscount = addGoodsSkuPO.getDiscount();
					minPriceStore = addGoodsSkuPO.getPriceStore();
				}
			}
		}
		// 塞入最低售价，最低售价折扣,最低门市价
		DaikenGoodsNew goodsNewMin = daikenGoodsNewDao.selectById(goodsNew.getId());
		goodsNewMin.setMinDiscount(minDiscount);
		goodsNewMin.setMinPriceSales(minPriceSales);
		goodsNewMin.setMinPriceStore(minPriceStore);
		if (KemeanSettledEnum.AUDIT_PASS.getStatus().equals(goodsNewMin.getAuditStatus())) {
			goodsNewMin.setGoodsStatus(true);
		}
		daikenGoodsNewDao.updateByPrimaryKeySelective(goodsNewMin);
		return new KemeanResult<String>();
	}

	/**
	 * 获取单个快捷回复信息
	 * 
	 * @author huwei
	 * @date 2018年6月29日
	 */
	public KemeanResult<FastReplyListBO> fastReplyInfo(Integer objId) {
		DaikenShopReply dbShopReply = daikenShopReplyDao.selectById(objId);
		FastReplyListBO bo = new FastReplyListBO();
		BeanUtils.copyProperties(dbShopReply, bo);
		return new KemeanResult<FastReplyListBO>(bo);
	}

	/**
	 * 获取快捷回复信息
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public List<FastReplyListBO> fastReplyList(DaikenUser loginBusiness) {
		List<DaikenShopReply> dbShopReply = daikenShopReplyDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "shopId" },
				new Object[] { false, loginBusiness.getShopId() });
		List<FastReplyListBO> result = new ArrayList<FastReplyListBO>(dbShopReply.size());
		if (CollectionUtils.isEmpty(dbShopReply)) {
			return result;
		}
		for (DaikenShopReply daikenShopReply : dbShopReply) {
			FastReplyListBO bo = new FastReplyListBO();
			BeanUtils.copyProperties(daikenShopReply, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 删除快捷回复信息
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public KemeanResult<String> delFastReply(Integer objId) {
		DaikenShopReply dbShopReply = daikenShopReplyDao.selectById(objId);
		dbShopReply.setDataDeleted(true);
		dbShopReply.setUpdateTime(new Date());
		daikenShopReplyDao.updateByPrimaryKeySelective(dbShopReply);
		return new KemeanResult<String>();
	}

	/**
	 * 添加快捷信息回复
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public KemeanResult<String> addFastReply(FastReplyPO fastReplyPO, DaikenUser daikenUser) {
		Date now = new Date();
		DaikenShopReply shopReply = null;
		// 修改
		Boolean flag = false;
		if (fastReplyPO.getObjId() == null) {
			// 新增
			flag = true;
		}
		if (flag) {
			shopReply = new DaikenShopReply();
			shopReply.setShopId(daikenUser.getShopId());
			shopReply.setCreateTime(now);
		} else {
			shopReply = daikenShopReplyDao.selectById(fastReplyPO.getObjId());
		}
		BeanUtils.copyProperties(fastReplyPO, shopReply);
		shopReply.setUpdateTime(now);
		if (flag) {
			daikenShopReplyDao.saveSelective(shopReply);
		} else {
			daikenShopReplyDao.updateByPrimaryKeySelective(shopReply);
		}
		return new KemeanResult<String>();
	}

	/**
	 * 获取子账号
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public List<AccountListBO> accountList(DaikenUser loginBusiness) {
		List<DaikenUser> dbUsers = daikenUserDao
				.selectByProperties(new String[] { KemeanConstant.DATA_DELETED, "shopId", "userType" }, new Object[] {
						false, loginBusiness.getShopId(), DaikenUserEnum.DaikenUserTypeEnum.BUSINESS_SON.getType() });
		List<AccountListBO> result = new ArrayList<AccountListBO>(dbUsers.size());
		if (CollectionUtils.isEmpty(dbUsers)) {
			return result;
		}
		for (DaikenUser daikenUser : dbUsers) {
			AccountListBO bo = new AccountListBO();
			BeanUtils.copyProperties(daikenUser, bo);
			result.add(bo);
		}
		return result;
	}

	/**
	 * 添加子账号
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public KemeanResult<String> addAccount(AddAccountPO addAccountPO, DaikenUser daikenUser) {
		DaikenUser dbSonUser = daikenUserDao.selectByPhone(addAccountPO.getPhone(),
				Arrays.asList(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS_SON.getType()));
		if (dbSonUser != null) {
			return new KemeanResult<String>(false, "手机号已注册，拒绝重复添加");
		}
		Date now = new Date();
		DaikenUser newUser = new DaikenUser();
		newUser.setUid(commonService.getUid());
		newUser.setPhone(addAccountPO.getPhone());
		newUser.setFirstPhone(addAccountPO.getPhone());
		newUser.setShopId(daikenUser.getShopId());
		newUser.setUserType(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS_SON.getType());
		newUser.setUserStatus(DaikenUserEnum.DaikenUserStatusEnum.NORMAL.getStatus());
		newUser.setNickName(addAccountPO.getNickName());
		newUser.setPassword(addAccountPO.getPassword());
		newUser.setToken(DaikenUtil.getUUIDString());
		newUser.setCreateTime(now);
		newUser.setUpdateTime(now);
		daikenUserDao.saveSelective(newUser);
		return new KemeanResult<String>();
	}

	/**
	 * 删除子账号
	 * 
	 * @author huwei
	 * @date 2018年6月7日
	 */
	public KemeanResult<String> delAccount(String phone) {
		DaikenUser dbUser = daikenUserDao.selectByPhone(phone,
				Arrays.asList(DaikenUserEnum.DaikenUserTypeEnum.BUSINESS_SON.getType()));
		if (dbUser == null) {
			return new KemeanResult<String>(false, DaikenApiResultTips.SonBusiness.SELECT_SON_ERROR);
		}
		dbUser.setDataDeleted(true);
		dbUser.setUpdateTime(new Date());
		daikenUserDao.updateByPrimaryKeySelective(dbUser);
		return new KemeanResult<String>();
	}

	/**
	 * 反馈意见
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	public KemeanResult<String> feedBack(FeedBackPO feedBackPO, DaikenUser loginBusiness) {
		kemeanApiAidService.feedbackAdd(feedBackPO.getFeedbackContent(), feedBackPO.getNeedContact(),
				loginBusiness.getHeadImg(), feedBackPO.getPhone(), loginBusiness.getNickName(),
				loginBusiness.getUserStatus());
		return new KemeanResult<String>();
	}

}
