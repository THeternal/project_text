package com.kemean.vo.bo.admin.shop;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemean.vo.bo.KemeanIdBO;

public class AdminShopSettledBO extends KemeanIdBO {

	/**
	 * 商铺id
	 */
	private Integer shopId;

	private String shopLogo;

	/**
	 * 入驻类型(默认，待确定)
	 */
	private String settledPersonalStr;

	/**
	 * 入驻类型(默认，待确定)
	 */
	private Boolean settledPersonal;

	/**
	 * 营业状态
	 */
	private Boolean workStatus;

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 负责人正面身份证件照
	 */
	private String principalIdCardFrontImg;

	/**
	 * 负责人背面身份证件照
	 */
	private String principalIdCardReverseImg;

	/**
	 * 负责人姓名
	 */
	private String principalName;

	/**
	 * 负责人电话
	 */
	private String principalPhone;

	/**
	 * 身份证号
	 */
	private String principalIdCard;

	/**
	 * 身份证有效期（0为固定，1为长期）
	 */
	private String isIdCardValidityStr;

	/**
	 * 身份证有效期（0为固定，1为长期）
	 */
	private Boolean isIdCardValidity;

	/**
	 * 固定有效期结束时间
	 */
	private String periodOfValidityStr;

	/**
	 * 营业执照正面照
	 */
	private String businessLicenseImg;

	/**
	 * 营业执照名称
	 */
	private String businessLicenseName;

	/**
	 * 法人名称
	 */
	private String legalPersonName;

	/**
	 * 营业执照注册号码
	 */
	private String businessLicenseRegisterNo;

	/**
	 * 营业执照经营场所
	 */
	private String businessLicenseLocation;

	/**
	 * 审核状态(默认，初始化)
	 */
	private Integer auditStatus;

	/**
	 * 审核状态(默认，初始化)
	 */
	private String auditStatusStr;

	/**
	 * 审核失败的原因
	 */
	private String auditCause;
	/**
	 * 店铺介绍
	 */

	private String presentation;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getSettledPersonalStr() {
		return settledPersonalStr;
	}

	public void setSettledPersonalStr(String settledPersonalStr) {
		this.settledPersonalStr = settledPersonalStr;
	}

	public String getAuditCause() {
		return auditCause;
	}

	public void setAuditCause(String auditCause) {
		this.auditCause = auditCause;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Boolean getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Boolean workStatus) {
		this.workStatus = workStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public Boolean getSettledPersonal() {
		return settledPersonal;
	}

	public void setSettledPersonal(Boolean settledPersonal) {
		this.settledPersonal = settledPersonal;
	}

	public Boolean getIsIdCardValidity() {
		return isIdCardValidity;
	}

	public void setIsIdCardValidity(Boolean isIdCardValidity) {
		this.isIdCardValidity = isIdCardValidity;
	}

	public String getPrincipalIdCardFrontImg() {
		return principalIdCardFrontImg;
	}

	public void setPrincipalIdCardFrontImg(String principalIdCardFrontImg) {
		this.principalIdCardFrontImg = principalIdCardFrontImg;
	}

	public String getPrincipalIdCardReverseImg() {
		return principalIdCardReverseImg;
	}

	public void setPrincipalIdCardReverseImg(String principalIdCardReverseImg) {
		this.principalIdCardReverseImg = principalIdCardReverseImg;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getPrincipalIdCard() {
		return principalIdCard;
	}

	public void setPrincipalIdCard(String principalIdCard) {
		this.principalIdCard = principalIdCard;
	}

	public String getIsIdCardValidityStr() {
		return isIdCardValidityStr;
	}

	public void setIsIdCardValidityStr(String isIdCardValidityStr) {
		this.isIdCardValidityStr = isIdCardValidityStr;
	}

	public String getPeriodOfValidityStr() {
		return periodOfValidityStr;
	}

	public void setPeriodOfValidityStr(String periodOfValidityStr) {
		this.periodOfValidityStr = periodOfValidityStr;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public String getBusinessLicenseName() {
		return businessLicenseName;
	}

	public void setBusinessLicenseName(String businessLicenseName) {
		this.businessLicenseName = businessLicenseName;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getBusinessLicenseRegisterNo() {
		return businessLicenseRegisterNo;
	}

	public void setBusinessLicenseRegisterNo(String businessLicenseRegisterNo) {
		this.businessLicenseRegisterNo = businessLicenseRegisterNo;
	}

	public String getBusinessLicenseLocation() {
		return businessLicenseLocation;
	}

	public void setBusinessLicenseLocation(String businessLicenseLocation) {
		this.businessLicenseLocation = businessLicenseLocation;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditStatusStr() {
		return auditStatusStr;
	}

	public void setAuditStatusStr(String auditStatusStr) {
		this.auditStatusStr = auditStatusStr;
	}

}
