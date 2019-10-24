package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenGoodsNew;
import com.kemean.bean.DaikenGoodsRecommend;
import com.kemean.bean.DaikenGoodsRecommendCharge;
import com.kemean.bean.DaikenShop;
import com.kemean.bean.KemeanAdminUser;
import com.kemean.constant.DaikenMapData;
import com.kemean.constant.DaikenRecommend;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.dao.DaikenGoodsNewDao;
import com.kemean.dao.DaikenGoodsRecommendChargeDao;
import com.kemean.dao.DaikenGoodsRecommendDao;
import com.kemean.dao.DaikenShopDao;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.recommend.RecommendDataBO;
import com.kemean.vo.po.admin.recommend.RecommendDataPO;

@Service
public class AdminGoodsRecommendService {

	@Autowired
	private DaikenGoodsRecommendDao daikenGoodsRecommendDao;

	@Autowired
	private DaikenGoodsRecommendChargeDao daikenGoodsRecommendChargeDao;

	@Autowired
	private DaikenGoodsNewDao daikenGoodsNewDao;

	@Autowired
	private DaikenShopDao daikenShopDao;

	/**
	 * 首页推荐数据
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	public KemeanPageAdminBO<List<RecommendDataBO>> recommendData(RecommendDataPO recommendDataPO) {
		List<DaikenGoodsRecommend> dbGoodsRecommends = daikenGoodsRecommendDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "recommendWay", "recommendType", "userPhone" },
				new Object[] { false, recommendDataPO.getRecommendWay(), recommendDataPO.getRecommendType(),
						recommendDataPO.getUserPhone() },
				new String[] { "userPhone" }, "id", true, recommendDataPO.getPage(), recommendDataPO.getLimit());

		List<RecommendDataBO> result = new ArrayList<RecommendDataBO>(dbGoodsRecommends.size());
		if (CollectionUtils.isEmpty(dbGoodsRecommends)) {
			return new KemeanPageAdminBO<List<RecommendDataBO>>(0L, result);
		}
		for (DaikenGoodsRecommend dbGoodsRecommend : dbGoodsRecommends) {
			RecommendDataBO bo = new RecommendDataBO();
			BeanUtils.copyProperties(dbGoodsRecommend, bo);
			bo.setRecommendTypeStr(DaikenMapData.recommendType.get(dbGoodsRecommend.getRecommendType()));
			bo.setRecommendWayStr(DaikenMapData.recommendType.get(dbGoodsRecommend.getRecommendWay()));
			bo.setIsOverStr(dbGoodsRecommend.getIsOver() ? "时间到" : "未到时间");
			bo.setBuyTimeStr(DaikenUtil.formatDate(dbGoodsRecommend.getBuyTime(), KemeanDateFormatEnum.DATE));
			if (dbGoodsRecommend.getRecommendWay().equals(DaikenRecommend.RecommendWay.GOODS.getWay())) {
				DaikenGoodsNew dbGoodsNew = daikenGoodsNewDao.selectById(dbGoodsRecommend.getTypeId());
				bo.setTypeStr("【商品】" + dbGoodsNew.getTitle());
			}
			if (dbGoodsRecommend.getRecommendWay().equals(DaikenRecommend.RecommendWay.SHOP.getWay())) {
				DaikenShop dbShop = daikenShopDao.selectById(dbGoodsRecommend.getTypeId());
				bo.setTypeStr("【商铺】" + dbShop.getShopName());
			}
			DaikenGoodsRecommendCharge dbRecommendCharge = daikenGoodsRecommendChargeDao
					.selectById(dbGoodsRecommend.getRecommendId());
			bo.setTimeBucket("永久有效");
			if (dbRecommendCharge != null) {
				bo.setTimeBucket(DaikenUtil.getHourStr(dbRecommendCharge.getBeginTime()) + "到"
						+ DaikenUtil.getHourStr(dbRecommendCharge.getEndTime()));
			}
			if (dbGoodsRecommend.getRecommendType().equals(DaikenRecommend.RecommendType.CLICK.getType())) {
				bo.setTimeBucket(dbGoodsRecommend.getBuyClickNum() - dbGoodsRecommend.getClickNum() + "次");
			}
			result.add(bo);
		}

		return new KemeanPageAdminBO<List<RecommendDataBO>>(
				new PageInfo<DaikenGoodsRecommend>(dbGoodsRecommends).getTotal(), result);
	}

	/**
	 * 修改生效状态
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	public KemeanResult<String> isTakeEffect(Integer objId) {
		DaikenGoodsRecommend dbGoodsRecommend = daikenGoodsRecommendDao.selectById(objId);
		if (dbGoodsRecommend == null) {
			return new KemeanResult<>(false, "没有找到该推荐广告，错误");
		}
		dbGoodsRecommend.setIsTakeEffect(!dbGoodsRecommend.getIsTakeEffect());
		dbGoodsRecommend.setUpdateTime(new Date());
		daikenGoodsRecommendDao.updateByPrimaryKeySelective(dbGoodsRecommend);
		return new KemeanResult<>();
	}

	/**
	 * 新增首页推荐替补广告
	 * 
	 * @author huwei
	 * @date 2018年9月10日
	 */
	public KemeanResult<String> substitutionRecommend(Integer typeId, Integer recommendWay, String img,
			KemeanAdminUser kemeanAdminUser) {
		Date now = new Date();
		DaikenGoodsRecommend newRecommend = new DaikenGoodsRecommend();
		newRecommend.setRecommendId(0);
		newRecommend.setBuyTime(now);
		newRecommend.setUserUid(Integer.valueOf(kemeanAdminUser.getUid()));
		newRecommend.setUserPhone("替补广告");
		newRecommend.setTypeId(typeId);
		newRecommend.setRecommendWay(recommendWay);
		newRecommend.setRecommendType(DaikenRecommend.RecommendType.SUBSTITUTION.getType());
		newRecommend.setImg(img);
		newRecommend.setCreateTime(now);
		newRecommend.setUpdateTime(now);
		daikenGoodsRecommendDao.saveSelective(newRecommend);
		return new KemeanResult<>(true, "添加首页推荐替补广告成功");
	}

}
