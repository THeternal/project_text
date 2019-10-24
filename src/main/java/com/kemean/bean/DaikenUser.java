package com.kemean.bean;

import java.util.Date;

import javax.persistence.*;

@Table(name = "daiken_user")
public class DaikenUser extends KemeanAbstractBaseBean {

	/**
	 * 唯一标识(代研号)
	 */
	@Column(name = "uid")
	private Integer uid;

	/**
	 * 商铺ID
	 */
	@Column(name = "shop_id")
	private Integer shopId;

	/**
	 * 用户类型
	 */
	@Column(name = "user_type")
	private Integer userType;

	/**
	 * 手机号
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * 始初手机号
	 */
	@Column(name = "first_phone")
	private String firstPhone;

	/**
	 * 融云token
	 */
	@Column(name = "token_ry")
	private String tokenRy;

	/**
	 * token
	 */
	@Column(name = "token")
	private String token;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 余额
	 */
	@Column(name = "balance_price")
	private Double balancePrice;

	/**
	 * token余额
	 */
	@Column(name = "balance_token")
	private Double balanceToken;

	/**
	 * 头像
	 */
	@Column(name = "head_img")
	private String headImg;

	/**
	 * 昵称
	 */
	@Column(name = "nick_name")
	private String nickName;

	/**
	 * 年龄
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * true-男 false-女
	 */
	@Column(name = "sex_man")
	private Boolean sexMan;

	/**
	 * 职业
	 */
	@Column(name = "profession")
	private Integer profession;

	/**
	 * 兴趣爱好-json
	 */
	@Column(name = "hobbies_interests")
	private String hobbiesInterests;

	/**
	 * 微信openId
	 */
	@Column(name = "wx_open_id")
	private String wxOpenId;

	/**
	 * 省id
	 */
	@Column(name = "province_id")
	private Integer provinceId;

	/**
	 * 省名
	 */
	@Column(name = "province_name")
	private String provinceName;

	/**
	 * 市id
	 */
	@Column(name = "city_id")
	private Integer cityId;

	/**
	 * 市名
	 */
	@Column(name = "city_name")
	private String cityName;

	/**
	 * 区id
	 */
	@Column(name = "area_id")
	private Integer areaId;

	/**
	 * 区名
	 */
	@Column(name = "area_name")
	private String areaName;

	/**
	 * 推荐码
	 */
	@Column(name = "referral_code")
	private String referralCode;

	/**
	 * 客服标记
	 */
	@Column(name = "service_mark")
	private Integer serviceMark;

	/**
	 * 分享次数
	 */
	@Column(name = "wool_label_num")
	private Integer woolLabelNum;

	@Column(name = "wool_label_date")
	private Date woolLabelDate;

	/**
	 * 撸羊毛标识,依赖daiken_red_share表信息
	 */
	@Column(name = "wool_label")
	private Boolean woolLabel;

	/**
	 * 用户状态
	 */
	@Column(name = "user_status")
	private Integer userStatus;

	/**
	 * 禁用原因
	 */
	@Column(name = "cause")
	private String cause;

	/**
	 * 是否第一次登陆
	 */
	@Column(name = "is_first_login")
	private Boolean isFirstLogin;

	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public String getTokenRy() {
		return tokenRy;
	}

	public void setTokenRy(String tokenRy) {
		this.tokenRy = tokenRy;
	}

	public Integer getWoolLabelNum() {
		return woolLabelNum;
	}

	public void setWoolLabelNum(Integer woolLabelNum) {
		this.woolLabelNum = woolLabelNum;
	}

	public Date getWoolLabelDate() {
		return woolLabelDate;
	}

	public void setWoolLabelDate(Date woolLabelDate) {
		this.woolLabelDate = woolLabelDate;
	}

	public Integer getProfession() {
		return profession;
	}

	public void setProfession(Integer profession) {
		this.profession = profession;
	}

	public String getHobbiesInterests() {
		return hobbiesInterests;
	}

	public void setHobbiesInterests(String hobbiesInterests) {
		this.hobbiesInterests = hobbiesInterests;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getUserType() {
		return userType;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getServiceMark() {
		return serviceMark;
	}

	public void setServiceMark(Integer serviceMark) {
		this.serviceMark = serviceMark;
	}

	public String getFirstPhone() {
		return firstPhone;
	}

	public void setFirstPhone(String firstPhone) {
		this.firstPhone = firstPhone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public Double getBalanceToken() {
		return balanceToken;
	}

	public void setBalanceToken(Double balanceToken) {
		this.balanceToken = balanceToken;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public Boolean getWoolLabel() {
		return woolLabel;
	}

	public void setWoolLabel(Boolean woolLabel) {
		this.woolLabel = woolLabel;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}