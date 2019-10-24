package com.kemean.vo.bo.c.mall;

import java.util.Date;
import java.util.List;

import com.kemean.vo.bo.KemeanIdBO;

public class NewGoodsInfoBO extends KemeanIdBO {

	/**
	 * 商品标题
	 */
	private String title;

	/**
	 * skn编号
	 */
	private List<SkuNoBO> skuNoBO;

	/**
	 * 商铺id
	 */
	private Integer shopId;

	/**
	 * 商铺logo
	 */
	private String shopLogo;

	/**
	 * 封面轮播图-json
	 */
	private List<String> imgsHead;

	/**
	 * 商品介绍
	 */
	private String descStr;

	/**
	 * 商品介绍图
	 */
	private List<String> imgsDesc;

	/**
	 * 商品规格 父
	 */
	private List<RecordTypeFBO> recordTypeFBO;

	/**
	 * 最低支付价格商品的门市价格
	 */
	private Double minPriceStore;

	/**
	 * 最低支付价格的最低支付价格
	 */
	private Double minPriceSales;

	/**
	 * 48小时发货
	 */
	private Boolean fortyEight;

	/**
	 * 假一赔十
	 */
	private Boolean fakeGoods;

	/**
	 * 邮费,0-免邮
	 */
	private Double postage;

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 商铺电话
	 */
	private String shopPhone;

	/**
	 * 店铺商品数量
	 */
	private Integer goodsNum;

	/**
	 * 店铺销售量
	 */
	private Integer salesVolume;

	/**
	 * 店铺剩余商品数
	 */
	private Integer goodsResidue;

	/**
	 * 店铺收藏量
	 */
	private Integer numCollect;

	/**
	 * 商品销售量
	 */
	private Integer salesNum;

	/**
	 * 商品评价数量
	 */
	private Integer appraisalNum;

	/**
	 * 代卖
	 */
	private Boolean purchasing;

	/**
	 * false-新品 true-二手
	 */
	private Boolean old;

	/*
	 * 是否收藏
	 */
	private Boolean isCollect;

	/**
	 * 售前红包
	 */
	private Double redBefore;

	/**
	 * 售后红包
	 */
	private Double redAfter;

	/**
	 * 代卖提成
	 */
	private Double pricePurchasing;

	/**
	 * 是否已代卖
	 */
	private Boolean isPurchasing;

	/**
	 * 撸羊毛标识
	 */
	private Boolean woolLabel;

	/**
	 * 是否可以分享
	 */
	private Boolean isShare;

	/**
	 * 帮卖商铺id
	 */
	private Integer userShopId;

	/**
	 * 商家uid
	 */
	private Integer shopUserUid;

	/**
	 * 折扣开始时间
	 */
	private Date discountTimeBegin;

	/**
	 * 折扣结束时间
	 */
	private Date discountTimeEnd;

	/**
	 * 售卖类型
	 */
	private Integer salesType;

	/**
	 * 限时折扣类型(未开始，进行中，已结束)
	 */
	private Integer timeType;

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public Integer getGoodsResidue() {
		return goodsResidue;
	}

	public void setGoodsResidue(Integer goodsResidue) {
		this.goodsResidue = goodsResidue;
	}

	public Date getDiscountTimeBegin() {
		return discountTimeBegin;
	}

	public void setDiscountTimeBegin(Date discountTimeBegin) {
		this.discountTimeBegin = discountTimeBegin;
	}

	public Date getDiscountTimeEnd() {
		return discountTimeEnd;
	}

	public void setDiscountTimeEnd(Date discountTimeEnd) {
		this.discountTimeEnd = discountTimeEnd;
	}

	public Integer getShopUserUid() {
		return shopUserUid;
	}

	public void setShopUserUid(Integer shopUserUid) {
		this.shopUserUid = shopUserUid;
	}

	public Integer getUserShopId() {
		return userShopId;
	}

	public void setUserShopId(Integer userShopId) {
		this.userShopId = userShopId;
	}

	public Boolean getIsShare() {
		return isShare;
	}

	public void setIsShare(Boolean isShare) {
		this.isShare = isShare;
	}

	public Boolean getWoolLabel() {
		return woolLabel;
	}

	public void setWoolLabel(Boolean woolLabel) {
		this.woolLabel = woolLabel;
	}

	public Boolean getIsPurchasing() {
		return isPurchasing;
	}

	public void setIsPurchasing(Boolean isPurchasing) {
		this.isPurchasing = isPurchasing;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getRedBefore() {
		return redBefore;
	}

	public void setRedBefore(Double redBefore) {
		this.redBefore = redBefore;
	}

	public Double getRedAfter() {
		return redAfter;
	}

	public void setRedAfter(Double redAfter) {
		this.redAfter = redAfter;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public List<SkuNoBO> getSkuNoBO() {
		return skuNoBO;
	}

	public void setSkuNoBO(List<SkuNoBO> skuNoBO) {
		this.skuNoBO = skuNoBO;
	}

	public List<RecordTypeFBO> getRecordTypeFBO() {
		return recordTypeFBO;
	}

	public void setRecordTypeFBO(List<RecordTypeFBO> recordTypeFBO) {
		this.recordTypeFBO = recordTypeFBO;
	}

	public Boolean getOld() {
		return old;
	}

	public void setOld(Boolean old) {
		this.old = old;
	}

	public Boolean getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public List<String> getImgsHead() {
		return imgsHead;
	}

	public void setImgsHead(List<String> imgsHead) {
		this.imgsHead = imgsHead;
	}

	public String getDescStr() {
		return descStr;
	}

	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}

	public List<String> getImgsDesc() {
		return imgsDesc;
	}

	public void setImgsDesc(List<String> imgsDesc) {
		this.imgsDesc = imgsDesc;
	}

	public Double getMinPriceStore() {
		return minPriceStore;
	}

	public void setMinPriceStore(Double minPriceStore) {
		this.minPriceStore = minPriceStore;
	}

	public Double getMinPriceSales() {
		return minPriceSales;
	}

	public void setMinPriceSales(Double minPriceSales) {
		this.minPriceSales = minPriceSales;
	}

	public Boolean getFortyEight() {
		return fortyEight;
	}

	public void setFortyEight(Boolean fortyEight) {
		this.fortyEight = fortyEight;
	}

	public Boolean getFakeGoods() {
		return fakeGoods;
	}

	public void setFakeGoods(Boolean fakeGoods) {
		this.fakeGoods = fakeGoods;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getNumCollect() {
		return numCollect;
	}

	public void setNumCollect(Integer numCollect) {
		this.numCollect = numCollect;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getAppraisalNum() {
		return appraisalNum;
	}

	public void setAppraisalNum(Integer appraisalNum) {
		this.appraisalNum = appraisalNum;
	}

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

}
