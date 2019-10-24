package com.kemean.controller.consumer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.constant.DaikenImMessageTypeEnum;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.ImRyService;
import com.kemean.service.common.CommonService;
import com.kemean.service.consumer.CMessageService;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.mysql.ImRecordRedis;
import com.kemean.vo.po.c.message.ImDelNoReadPO;

/**
 * 【客户端】 消息控制器
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/c/message")
public class CMessageController extends DaikenBaseController {

	@Autowired
	private ImRyService imRyService;

	@Autowired
	private CMessageService cmessageService;

	@Autowired
	private CommonService commonService;

	/**
	 * IM记录
	 */
	@RequestMapping(value = "im_record", method = RequestMethod.POST)
	public KemeanResult<String> imRecord(@Valid ImRecordRedis imRecordPO) {
		// 图片
		if (imRecordPO.getMessageType().equals(DaikenImMessageTypeEnum.IMG.getType())) {
			imRyService.sendMsgByImg(imRecordPO.getToUserId(), imRecordPO.getFromUserId(), imRecordPO.getContent());
		}
		// 发送文字
		if (imRecordPO.getMessageType().equals(DaikenImMessageTypeEnum.TEXT.getType())) {
			imRyService.sendMsgByTxt(imRecordPO.getToUserId(), imRecordPO.getFromUserId(), imRecordPO.getContent());
		}
		cmessageService.imRecord(imRecordPO);
		return new KemeanResult<>();
	}

	/**
	 * 清除未读消息
	 * 
	 * @author huwei
	 * @date 2018年7月30日
	 */
	@RequestMapping(value = "im_del_no_read", method = RequestMethod.POST)
	public KemeanResult<String> imDelNoRead(@RequestBody @Valid ImDelNoReadPO imDelNoReadPO) {
		commonService.imConversationOperate(imDelNoReadPO.getToUserId() + "", imDelNoReadPO.getFromUserId() + "",
				imDelNoReadPO.getDel());
		return new KemeanResult<String>();
	}

}