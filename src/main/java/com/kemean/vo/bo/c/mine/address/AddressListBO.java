package com.kemean.vo.bo.c.mine.address;

import com.kemean.vo.bo.KemeanIdBO;

public class AddressListBO extends KemeanIdBO {

	public AddressListBO() {
		super();
	}

	public AddressListBO(String receiveName, String address, Boolean sexMan, Boolean isDefault) {
		this.receiveName = receiveName;
		this.address = address;
		this.sexMan = sexMan;
		this.isDefault = isDefault;
	}

	/**
	 * 收货人姓名
	 */
	private String receiveName;

	/**
	 * 详细地址
	 */
	private String address;

	/**
	 * 性别true-男 false-女
	 */
	private Boolean sexMan;

	/**
	 * 默认地址
	 */
	private Boolean isDefault;

	public Boolean getSexMan() {
		return sexMan;
	}

	public void setSexMan(Boolean sexMan) {
		this.sexMan = sexMan;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
