package com.kemean.service.admin;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kemean.bean.KemeanRichText;
import com.kemean.dao.KemeanRichTextDao;
import com.kemean.vo.po.admin.AdminProtocolPO;

@Service
public class AdminProtocolService {

	@Autowired
	private KemeanRichTextDao kemeanRichTextDao;

	/**
	 * 
	 * @author huwei
	 * @date 2018年8月14日
	 */
	public KemeanRichText protocolInfoData(Integer type) {
		return kemeanRichTextDao.selectUniqueEntityByProperty("type", type);
	}

	/**
	 * 修改协议
	 * 
	 * @author huwei
	 * @date 2018年4月11日
	 */
	@Transactional
	public void protocolUpdate(AdminProtocolPO adminProtocolPO) {
		KemeanRichText dbRichText = kemeanRichTextDao.selectById(adminProtocolPO.getId());
		dbRichText.setUpdateTime(new Date());
		BeanUtils.copyProperties(adminProtocolPO, dbRichText);
		kemeanRichTextDao.updateByPrimaryKeySelective(dbRichText);
	}

}
