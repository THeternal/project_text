package com.kemean.vo.bo.admin.goods;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminGoodsNewBO extends KemeanIdBO {
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 分类名称
	 */
	private String categoryName;

	private String goodsUid;

	/**
	 * 售卖类型
	 */
	private String salesTypeStr;

	/**
	 * 推荐
	 */
	private Boolean recommend;

	/**
	 * 销量
	 */
	private Integer salesNum;

	/**
	 * 上下架状态,针对全局SKU
	 */
	private Boolean goodsStatus;

	/**
	 * 帮代卖
	 */
	private Boolean purchasing;

	/**
	 * 帮买商品id
	 */
	private Integer goodsId;

	/**
	 * 审核状态
	 */
	private String auditStatusStr;

	/**
	 * 审核状态
	 */
	private Integer auditStatus;

	/**
	 * 精准匹配的条件
	 */
	private String matchCondition;

	/**
	 * 商铺名
	 */
	private String shopName;

	/**
	 * 商品店铺
	 */
	private Integer shopId;

	/**
	 * 商品参加营销活动的销售额，销量等
	 */

	private AdminGoodsMarketingBO goodsMarketing;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public String getAuditStatusStr() {
		return auditStatusStr;
	}

	public void setAuditStatusStr(String auditStatusStr) {
		this.auditStatusStr = auditStatusStr;
	}

	public AdminGoodsMarketingBO getGoodsMarketing() {
		return goodsMarketing;
	}

	public void setGoodsMarketing(AdminGoodsMarketingBO goodsMarketing) {
		this.goodsMarketing = goodsMarketing;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsUid() {
		return goodsUid;
	}

	public void setGoodsUid(String goodsUid) {
		this.goodsUid = goodsUid;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getMatchCondition() {
		return matchCondition;
	}

	public void setMatchCondition(String matchCondition) {
		this.matchCondition = matchCondition;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSalesTypeStr() {
		return salesTypeStr;
	}

	public void setSalesTypeStr(String salesTypeStr) {
		this.salesTypeStr = salesTypeStr;
	}

	public Boolean getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(Boolean purchasing) {
		this.purchasing = purchasing;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Boolean getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Boolean goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
