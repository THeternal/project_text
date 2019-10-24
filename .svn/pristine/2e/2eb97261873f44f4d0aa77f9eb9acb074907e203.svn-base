package com.kemean.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kemean.bean.KemeanConfig;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.KemeanConfigDao;
import com.kemean.util.DaikenUtil;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.goods.AdminConfigBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.config.AdminResetRecordPO;

@Service
public class AdminConfigService {

	@Autowired
	private KemeanConfigDao kemeanConfigDao;

	/**
	 * 配置数据
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	public KemeanPageAdminBO<List<AdminConfigBO>> configData(KemeanPageAdminPO kemeanPageAdminPO) {
		List<Integer> type = Arrays.asList(DaikenConfigEnum.INVESTIGATE_CHARGE.getType(),
				DaikenConfigEnum.TOKEN_AWARD.getType(), DaikenConfigEnum.ACTIVITY_PRICE.getType(),
				DaikenConfigEnum.LIKE_PRICE.getType(), DaikenConfigEnum.PRECISE_DELIVERY.getType(),
				DaikenConfigEnum.QUESTION_PRICE.getType(), DaikenConfigEnum.VOTE_PRICE.getType(),
				DaikenConfigEnum.TOKEN_NUMBER.getType(), DaikenConfigEnum.TOKEN_POOL.getType());
		List<KemeanConfig> dbKemeanConfigs = kemeanConfigDao.selectByPropertiesAndIn(
				new String[] { KemeanConstant.DATA_DELETED }, new Object[] { false }, "type", type, "id", false,
				kemeanPageAdminPO.getPage(), kemeanPageAdminPO.getLimit());
		List<AdminConfigBO> result = new ArrayList<AdminConfigBO>(dbKemeanConfigs.size());

		if (CollectionUtils.isEmpty(dbKemeanConfigs))

		{
			return new KemeanPageAdminBO<List<AdminConfigBO>>(0l, result);
		}
		for (KemeanConfig kemeanConfig : dbKemeanConfigs) {
			AdminConfigBO bo = new AdminConfigBO();
			BeanUtils.copyProperties(kemeanConfig, bo);
			bo.setCreateTimeStr(DaikenUtil.formatDate(kemeanConfig.getCreateTime()));
			result.add(bo);
		}
		return new KemeanPageAdminBO<List<AdminConfigBO>>(new PageInfo<KemeanConfig>(dbKemeanConfigs).getTotal(),
				result);
	}

	/**
	 * 修改记录
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	public KemeanResult<String> resetRecord(AdminResetRecordPO adminResetRecordPO) {
		KemeanConfig dbConfig = kemeanConfigDao.selectById(adminResetRecordPO.getObjId());
		dbConfig.setRecord(adminResetRecordPO.getCharge());
		dbConfig.setUpdateTime(new Date());
		kemeanConfigDao.updateByPrimaryKeySelective(dbConfig);
		return new KemeanResult<String>();
	}

}
