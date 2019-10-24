package com.kemean.service.business;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemean.bean.DaikenShop;
import com.kemean.bean.DaikenShopSettled;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.KemeanSettledEnum;
import com.kemean.dao.DaikenShopDao;
import com.kemean.dao.DaikenShopSettledDao;
import com.kemean.dao.KemeanMessageUserDao;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.b.shop.SettledInfoBO;
import com.kemean.vo.bo.b.shop.ShopInfoBO;
import com.kemean.vo.po.b.shop.SubmitSettledInfoPO;
import com.kemean.vo.po.b.shop.SubmitShopInfoPO;

/**
 * 【商户端】商铺业务层
 * 
 * @Date 2018年6月6日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class BShopService {

	@Autowired
	private DaikenShopSettledDao daikenShopSettledDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	@Autowired
	private KemeanMessageUserDao kemeanMessageUserDao;

	/**
	 * 获取商铺信息
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public ShopInfoBO shopInfo(DaikenUser loginBusiness) {
		ShopInfoBO result = new ShopInfoBO();
		DaikenShop dbShop = daikenShopDao.selectById(loginBusiness.getShopId());
		if (dbShop == null) {
			return result;
		}
		result.setUserType(loginBusiness.getUserType());
		BeanUtils.copyProperties(dbShop, result);
		result.setMessgeNoReadNum(kemeanMessageUserDao.countByProperties(new String[] { "userId", "readed" },
				new Object[] { loginBusiness.getId(), false }));
		return result;
	}

	/**
	 * 获取入驻信息
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public SettledInfoBO settledInfo(DaikenUser loginBusiness) {
		SettledInfoBO result = new SettledInfoBO();
		DaikenShopSettled dbShopSettled = daikenShopSettledDao.selectUniqueEntityByProperty("shopId",
				loginBusiness.getShopId());
		if (dbShopSettled == null) {
			return result;
		}
		BeanUtils.copyProperties(dbShopSettled, result);
		return result;
	}

	/**
	 * 提交商铺信息（含修改）
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> submitShopInfo(SubmitShopInfoPO submitShopInfoPO, DaikenUser loginBusiness) {
		DaikenShop dbShop = daikenShopDao.selectById(loginBusiness.getShopId());
		if (dbShop == null) {
			return new KemeanResult<String>(false, DaikenApiResultTips.Shop.USER_SHOP_NULL);
		}
		BeanUtils.copyProperties(submitShopInfoPO, dbShop);
		dbShop.setUpdateTime(new Date());
		daikenShopDao.updateByPrimaryKeySelective(dbShop);
		return new KemeanResult<String>();
	}

	/**
	 * 提交入驻信息（含修改）
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> submitSettledInfo(SubmitSettledInfoPO submitSettledInfoPO, DaikenUser loginBusiness) {
		DaikenShopSettled dbShopSettled = daikenShopSettledDao.selectUniqueEntityByProperty("shopId",
				loginBusiness.getShopId());
		DaikenShop dbShop = daikenShopDao.selectById(loginBusiness.getShopId());
		if (dbShopSettled == null) {
			return new KemeanResult<String>(false, DaikenApiResultTips.Settled.SETTLED_SHOP_NULL);
		}
		Date now = new Date();
		BeanUtils.copyProperties(submitSettledInfoPO, dbShopSettled);
		dbShopSettled.setUpdateTime(now);
		dbShopSettled.setAuditStatus(KemeanSettledEnum.REVIEW_ING.getStatus());
		daikenShopSettledDao.updateByPrimaryKeySelective(dbShopSettled);
		dbShop.setUpdateTime(now);
		BeanUtils.copyProperties(submitSettledInfoPO, dbShop);
		dbShop.setSettledType(submitSettledInfoPO.getSettledPersonal());
		dbShop.setBusinessLicenseLocation(submitSettledInfoPO.getBusinessLicenseLocation());
		dbShop.setAuditStatus(KemeanSettledEnum.REVIEW_ING.getStatus());
		daikenShopDao.updateByPrimaryKeySelective(dbShop);

		return new KemeanResult<>(true, DaikenApiResultTips.Settled.ADD_SETTLED_SHOP_SUCCESS);
	}

	/**
	 * 修改营业状态
	 * 
	 * @author huwei
	 * @date 2018年6月6日
	 */
	public KemeanResult<String> updateWorkStatus(Boolean workStatus, DaikenUser loginBusiness) {
		DaikenShop dbShop = daikenShopDao.selectById(loginBusiness.getShopId());
		if (dbShop == null) {
			return new KemeanResult<String>(false, DaikenApiResultTips.Shop.USER_SHOP_NULL);
		}
		dbShop.setWorkStatus(workStatus);
		dbShop.setUpdateTime(new Date());
		daikenShopDao.updateByPrimaryKeySelective(dbShop);
		return new KemeanResult<String>();
	}

}
