package com.kemean.controller.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.business.BMessageService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.mysql.ImRecordRedis;

/**
 * 
 * 【商户端】消息控制器
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/b/message")
public class BMessageController extends DaikenBaseController {

	@Autowired
	private BMessageService bmessageService;

	/**
	 * IM记录
	 */
	@RequestMapping(value = "im_record", method = RequestMethod.POST)
	public KemeanResult<String> imRecord(@RequestBody @Valid ImRecordRedis imRecordPO) {
		bmessageService.imRecord(imRecordPO, getLoginBusiness());
		return new KemeanResult<>();
	}
}
