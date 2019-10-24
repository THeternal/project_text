package com.kemean.vo.po.c.mine;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserInfoPO {

	@NotNull(message = "性别不能为空")
	private Boolean sexMan;

	@NotNull(message = "年龄不能为空")
	private Integer age;

	@NotNull(message = "职业不能为空")
	private Integer profession;

	@Size(min = 1, message = "兴趣爱好最少选一种")
	private List<Integer> hobbiesInterests;

	/**
	 * 省id
	 */
	@NotNull(message = "省id不能为空")
	private Integer provinceId;

	/**
	 * 市id
	 */
	@NotNull(message = "市id不能为空")
	private Integer cityId;

	/**
	 * 区id
	 */
	@NotNull(message = "区id不能为空")
	private Integer areaId;

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getProfession() {
		return profession;
	}

	public void setProfession(Integer profession) {
		this.profession = profession;
	}

	public List<Integer> getHobbiesInterests() {
		return hobbiesInterests;
	}

	public void setHobbiesInterests(List<Integer> hobbiesInterests) {
		this.hobbiesInterests = hobbiesInterests;
	}

}
