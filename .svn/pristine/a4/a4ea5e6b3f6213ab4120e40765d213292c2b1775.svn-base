package com.kemean.vo.po.admin.shop;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Administrator
 *
 */
public class AdminShopLoginPO {

	@NotBlank(message = "登录账号不能为空")
	private String loginNo;
	@NotBlank(message = "登录密码不能为空")
	@Length(min = 32, message = "密码格式不正确")
	private String loginPwd;
	public String getLoginNo() {
		return loginNo;
	}
	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	
}
