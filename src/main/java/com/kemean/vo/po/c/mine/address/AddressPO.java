package com.kemean.vo.po.c.mine.address;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AddressPO {

	private Integer objId;

	@NotBlank(message = "收货人姓名不能为空")
	private String receiveName;

	@NotNull(message = "性别不能为空")
	private Boolean sexMan;

	@NotBlank(message = "收货人手机号不能为空")
	private String phone;

	@NotNull(message = "省id不能为空")
	private Integer provinceId;

	@NotBlank(message = "省名称不能为空")
	private String provinceName;

	@NotNull(message = "市id不能为空")
	private Integer cityId;

	@NotBlank(message = "市名称不能为空")
	private String cityName;

	@NotNull(message = "区id不能为空")
	private Integer areaId;

	@NotBlank(message = "区名称不能为空")
	private String areaName;

	@NotBlank(message = "详细地址不能为空")
	private String address;

	@NotBlank(message = "位置点不能为空")
	private String position;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
	}

	public Integer getObjId() {
		return objId;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
