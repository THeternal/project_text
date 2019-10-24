package com.kemean.vo.bo.b.shop;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SettledInfoBO {

	public SettledInfoBO() {
		super();
	}

	public SettledInfoBO(Boolean settledPersonal, String shopName, String principalIdCardFrontImg,
			String principalIdCardReverseImg, String principalName, String principalPhone, String principalIdCard,
			Boolean isIdCardValidity, Date periodOfValidity, String businessLicenseImg, String businessLicenseName,
			String legalPersonName, String businessLicenseRegisterNo, String businessLicenseLocation,
			Integer auditStatus) {
		super();
		this.settledPersonal = settledPersonal;
		this.shopName = shopName;
		this.principalIdCardFrontImg = principalIdCardFrontImg;
		this.principalIdCardReverseImg = principalIdCardReverseImg;
		this.principalName = principalName;
		this.principalPhone = principalPhone;
		this.principalIdCard = principalIdCard;
		this.isIdCardValidity = isIdCardValidity;
		this.periodOfValidity = periodOfValidity;
		this.businessLicenseImg = businessLicenseImg;
		this.businessLicenseName = businessLicenseName;
		this.legalPersonName = legalPersonName;
		this.businessLicenseRegisterNo = businessLicenseRegisterNo;
		this.businessLicenseLocation = businessLicenseLocation;
		this.auditStatus = auditStatus;
	}

	/**
	 * 入驻类型(默认，待确定)
	 */
	private Boolean settledPersonal;

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
	private Boolean isIdCardValidity;

	/**
	 * 固定有效期结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date periodOfValidity;

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

	public Boolean getSettledPersonal() {
		return settledPersonal;
	}

	public void setSettledPersonal(Boolean settledPersonal) {
		this.settledPersonal = settledPersonal;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public Boolean getIsIdCardValidity() {
		return isIdCardValidity;
	}

	public void setIsIdCardValidity(Boolean isIdCardValidity) {
		this.isIdCardValidity = isIdCardValidity;
	}

	public Date getPeriodOfValidity() {
		return periodOfValidity;
	}

	public void setPeriodOfValidity(Date periodOfValidity) {
		this.periodOfValidity = periodOfValidity;
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

}
