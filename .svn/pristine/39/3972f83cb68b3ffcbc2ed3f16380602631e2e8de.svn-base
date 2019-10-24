package com.kemean.vo.po.admin.goods;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.kemean.vo.po.b.goods.AddGoodsSkuPO;

public class AdminGoodsNewUpdatePO {

	private Integer objId;

	/**
	 * 标题
	 */
	@NotBlank(message = "商品名称不能为空")
	private String title;

	/**
	 * 分类id
	 */
	private Integer categoryId;

	/**
	 * 封面轮播图-json
	 */
	private List<String> imgsHead;

	// 商铺Id
	private Integer shopId;

	/**
	 * 描述
	 */
	@NotBlank(message = "描述不能为空")
	private String descStr;

	/**
	 * 描述图-json
	 */
	private List<String> imgsDesc;

	/**
	 * 售卖类型
	 */
	private Integer salesType;

	/**
	 * 折扣时间-json(开始时间-结束时间)
	 */
	private String discountTimeBegin;
	private String discountTimeEnd;

	private List<AddGoodsSkuPO> goodsSkuPO;

	/**
	 * 48小时发货
	 */
	@NotNull(message = "48小时发货不能为空")
	private Boolean fortyEight;

	/**
	 * 假一赔十
	 */
	@NotNull(message = "假一赔十不能为空")
	private Boolean fakeGoods;

	/**
	 * 邮费,0-免邮
	 */
	private Double postage;

	/**
	 * 售前红包
	 */
	@NotNull(message = "售前红包不能为空，最少输入0")
	private Double redBefore;

	/**
	 * 售后红包
	 */
	@NotNull(message = "售后红包不能为空，最少输入0")
	private Double redAfter;

	/**
	 * 帮代卖
	 */
	@NotNull(message = "帮代卖不能为空")
	private Boolean purchasing;

	/**
	 * 代卖提成
	 */
	private Double pricePurchasing;

	/**
	 * 上下架状态
	 */
	private Boolean goodsStatus;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<String> getImgsHead() {
		return imgsHead;
	}

	public void setImgsHead(List<String> imgsHead) {
		this.imgsHead = imgsHead;
	}

	public List<String> getImgsDesc() {
		return imgsDesc;
	}

	public void setImgsDesc(List<String> imgsDesc) {
		this.imgsDesc = imgsDesc;
	}

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public List<AddGoodsSkuPO> getGoodsSkuPO() {
		return goodsSkuPO;
	}

	public void setGoodsSkuPO(List<AddGoodsSkuPO> goodsSkuPO) {
		this.goodsSkuPO = goodsSkuPO;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescStr() {
		return descStr;
	}

	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}

	public String getDiscountTimeBegin() {
		return discountTimeBegin;
	}

	public void setDiscountTimeBegin(String discountTimeBegin) {
		this.discountTimeBegin = discountTimeBegin;
	}

	public String getDiscountTimeEnd() {
		return discountTimeEnd;
	}

	public void setDiscountTimeEnd(String discountTimeEnd) {
		this.discountTimeEnd = discountTimeEnd;
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

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

	public Double getPricePurchasing() {
		return pricePurchasing;
	}

	public void setPricePurchasing(Double pricePurchasing) {
		this.pricePurchasing = pricePurchasing;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
