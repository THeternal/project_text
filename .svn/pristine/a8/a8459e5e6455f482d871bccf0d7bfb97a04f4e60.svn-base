package com.kemean.service.sales;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemean.bean.DaikenUserSales;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanResultEnum;
import com.kemean.dao.DaikenUserSalesDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.s.LoginBO;
import com.kemean.vo.po.s.LoginPO;

/**
 * 【销售端】基本业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class SOpenService {

	@Autowired
	private DaikenUserSalesDao daikenUserSalesDao;

	@Autowired
	private KemeanRedisService redisService;

	/**
	 * @author huwei
	 * @date 2018年7月17日
	 */
	public KemeanResult<LoginBO> login(LoginPO loginPO) {
		LoginBO bo = new LoginBO();
		KemeanResult<LoginBO> result = new KemeanResult<LoginBO>(bo);
		DaikenUserSales dbSalesUser = daikenUserSalesDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "phone", "password" },
				new Object[] { false, loginPO.getPhone(), loginPO.getPassword() });
		if (dbSalesUser == null) {
			result.setCode(KemeanResultEnum.FAILURE.getCode());
			result.setSuccess(false);
			result.setInfo(DaikenApiResultTips.Login.NO_REGISTERED_PHONE);
			return result;
		}
		Date now = new Date();
		redisService.del(String.format(DaikenRedisKeyEnum.USER_BUSINESS.getKey(), dbSalesUser.getToken()));
		String userToken = KemeanUtilAid.getUUIDString();
		dbSalesUser.setToken(userToken);
		dbSalesUser.setUpdateTime(now);
		daikenUserSalesDao.updateByPrimaryKeySelective(dbSalesUser);
		bo.setToken(userToken);
		return result;
	}

}
