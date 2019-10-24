package com.kemean.controller.admin;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.UploadQnService;
import com.kemean.service.admin.AdminProtocolService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.po.admin.AdminProtocolPO;

@Controller
@RequestMapping("admin/protocol")
public class AdminProtocolController extends DaikenBaseController {

	@Autowired
	private UploadQnService uploadQnService;

	@Autowired
	private AdminProtocolService adminProtocolService;

	/**
	 * 免责条款界面/代卖规则/新手提示/关于代研
	 * 
	 * @author huwei
	 * @date 2018年8月14日
	 */
	@RequestMapping(value = "protocol_page", method = RequestMethod.GET)
	public String protocolPage(@RequestParam Integer type) {
		request.setAttribute("qnResult", uploadQnService.getQnUploadInfo(new Date()));
		request.setAttribute("pageData", adminProtocolService.protocolInfoData(type));
		return KemeanConstant.ADMIN_FOLDER + "protocol";
	}

	/**
	 * 修改 协议
	 * 
	 * @author huwei
	 * @date 2018年8月14日
	 */
	@ResponseBody
	@RequestMapping(value = "protocol_update", method = RequestMethod.POST)
	public KemeanResult<String> protocolUpdate(@Valid @RequestBody AdminProtocolPO adminProtocolPO) {
		adminProtocolService.protocolUpdate(adminProtocolPO);
		return new KemeanResult<>();
	}

}
