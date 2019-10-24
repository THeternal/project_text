package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminConfigService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.goods.AdminConfigBO;
import com.kemean.vo.po.KemeanPageAdminPO;
import com.kemean.vo.po.admin.config.AdminResetRecordPO;

/**
 * 管理后台配置(kemean_config)
 * 
 * @Date 2018年7月3日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/config")
public class AdminConfigController extends DaikenBaseController {

	@Autowired
	private AdminConfigService adminConfigService;

	/**
	 * 配置 page
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	@RequestMapping(value = "page", method = RequestMethod.GET)
	public String goodsNewPage() {
		return KemeanConstant.ADMIN_FOLDER + "config";
	}

	/**
	 * 配置数据
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	@ResponseBody
	@RequestMapping(value = "config_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminConfigBO>> configData(@Valid KemeanPageAdminPO kemeanPageAdminPO) {
		return adminConfigService.configData(kemeanPageAdminPO);
	}

	/**
	 * 修改记录
	 * 
	 * @author huwei
	 * @date 2018年7月3日
	 */
	@ResponseBody
	@RequestMapping(value = "reset_record", method = RequestMethod.GET)
	public KemeanResult<String> resetRecord(AdminResetRecordPO adminResetRecordPO) {
		return adminConfigService.resetRecord(adminResetRecordPO);
	}

}
