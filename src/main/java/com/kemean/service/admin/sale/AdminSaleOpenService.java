package com.kemean.service.admin.sale;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemean.bean.DaikenUserSales;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenUserEnum.DaikenUserStatusEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanTips;
import com.kemean.dao.DaikenUserSalesDao;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.po.admin.AdminLoginCheckPO;

@Service
public class AdminSaleOpenService {

	@Autowired
	private DaikenUserSalesDao daikenSalesUserDao;

	/**
	 * 登录校验
	 * 
	 * @author huangyujian
	 * @date 2018年6月18日
	 */
	public KemeanResult<String> loginCheck(AdminLoginCheckPO adminLoginCheckPO, HttpServletRequest httpServletRequest) {
		DaikenUserSales dbUser = null;
		// 手机号码
		if (Pattern.matches("1\\d{10}", adminLoginCheckPO.getLoginNo())) {
			dbUser = daikenSalesUserDao.selectUniqueEntityByProperties(
					new String[] { "phone", KemeanConstant.DATA_DELETED },
					new Object[] { adminLoginCheckPO.getLoginNo(), false });
		}
		if (dbUser == null) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.NO_REGISTERED_PHONE);
		}
		if (DaikenUserStatusEnum.DISABLED.getStatus().equals(dbUser.getUserStatus())) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.ACCOUNT_DISABLED);
		}
		String md5Password = adminLoginCheckPO.getLoginPwd().toLowerCase();
		if (!md5Password.equalsIgnoreCase(dbUser.getPassword())) {
			return new KemeanResult<>(false, DaikenApiResultTips.Login.PASSWORD_ERROR);
		}
		httpServletRequest.getSession().setAttribute("login_sale", dbUser);
		return new KemeanResult<>(true, KemeanTips.Account.LOGIN_SUCCESS);
	}

}
