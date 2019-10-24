package com.kemean.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.constant.KemeanConstant;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminMessageService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.message.AdminMessageBO;
import com.kemean.vo.po.admin.message.AdminMessagePO;

@Controller
@RequestMapping("admin/msg")
public class AdminMessageController extends DaikenBaseController {

	@Autowired
	private AdminMessageService adminMessageService;

	/**
	 * 消息列表
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String messageListPage() {
		return KemeanConstant.ADMIN_FOLDER + "messagePage";
	}

	/**
	 * 消息列表 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */
	@ResponseBody
	@RequestMapping(value = "list_data", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminMessageBO>> messageListData(@Valid AdminMessagePO adminMessagePO) {
		return adminMessageService.messageListData(adminMessagePO);

	}

	/**
	 * 修改消息列表 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */
	@RequestMapping(value = "edit_page", method = RequestMethod.GET)
	public String messageEditPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminMessageService.messageInfo(objId));
		return KemeanConstant.ADMIN_FOLDER + "messageEditPage";
	}

	/**
	 * 添加消息 page
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */

	@RequestMapping(value = "add_page", method = RequestMethod.GET)
	public String messageAddPage() {
		return KemeanConstant.ADMIN_FOLDER + "messageAddPage";
	}

	/**
	 * 添加消息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */
	@ResponseBody
	@RequestMapping(value = "add_data", method = RequestMethod.POST)
	public KemeanResult<String> messageAddData(AdminMessagePO adminMessagePO) {
		return adminMessageService.messageAdd(adminMessagePO);
	}

	/**
	 * 删除消息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */
	@ResponseBody
	@RequestMapping(value = "del_data", method = RequestMethod.GET)
	public KemeanResult<String> messageDel(@RequestParam Integer objId) {
		return adminMessageService.messageDel(objId);
	}

	/**
	 * 修改消息 data
	 * 
	 * @author tanggengxiang
	 * @date 2018年4月8日
	 */
	@ResponseBody
	@RequestMapping(value = "update_data", method = RequestMethod.POST)
	public KemeanResult<String> messageUpdate(AdminMessagePO adminMessagePO) {
		return adminMessageService.messageUpdateData(adminMessagePO);
	}

}
