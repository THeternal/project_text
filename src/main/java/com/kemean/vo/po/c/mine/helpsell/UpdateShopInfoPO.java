package com.kemean.vo.po.c.mine.helpsell;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateShopInfoPO {

	@NotBlank(message = "代卖商铺名称不能为空")
	private String shopName;

	@NotBlank(message = "代卖商铺图片不能为空")
	private String img;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
