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
import com.kemean.service.admin.AdminLunBoService;
import com.kemean.vo.bo.KemeanPageAdminBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.lunbo.AdminLunboBO;
import com.kemean.vo.po.admin.lunbo.AddLunBoDataPO;
import com.kemean.vo.po.admin.lunbo.LunBoDataPO;

/**
 * 
 * @Date 2018年7月5日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("admin/lunbo")
public class AdminLunboController extends DaikenBaseController {

	@Autowired
	private AdminLunBoService adminLunBoService;

	/**
	 * 轮播列表
	 * 
	 * @author huwei
	 * @date 2018年7月5日
	 */
	@RequestMapping(value = "lunbo_page", method = RequestMethod.GET)
	public String lunboPage() {
		return KemeanConstant.ADMIN_FOLDER + "lunbo";
	}

	/**
	 * 获取轮播图数据
	 * 
	 * @author huwei
	 * @date 2018年3月27日
	 */
	@ResponseBody
	@RequestMapping(value = "lunbo_date", method = RequestMethod.GET)
	public KemeanPageAdminBO<List<AdminLunboBO>> lunboDate(@Valid LunBoDataPO lunBoDataPO) {
		return adminLunBoService.getLunBoDate(lunBoDataPO);
	}

	/**
	 * 删除轮播图数据
	 * 
	 * @author huwei
	 * @date 2018年3月27日
	 */
	@ResponseBody
	@RequestMapping(value = "del_lunbo", method = RequestMethod.GET)
	public KemeanResult<String> delLunbo(@RequestParam Integer objId) {
		adminLunBoService.delLunbo(objId);
		return new KemeanResult<>();
	}

	/**
	 * 跳到新增轮播图页面
	 * 
	 * @author huwei
	 * @date 2018年3月27日
	 */
	@RequestMapping(value = "add_lunbo_page", method = RequestMethod.GET)
	public String addLunboPage() {
		return KemeanConstant.ADMIN_FOLDER + "lunboAdd";
	}

	/**
	 * 新增轮播图数据
	 * 
	 * @author huwei
	 * @date 2018年3月27日
	 */
	@ResponseBody
	@RequestMapping(value = "add_lunbo", method = RequestMethod.POST)
	public KemeanResult<String> addLunbo(@Valid AddLunBoDataPO addLunBoDataPO) {
		return adminLunBoService.addLunbo(addLunBoDataPO);
	}

	/**
	 * 修改生效状态
	 * 
	 * @author huwei
	 * @date 2018年8月31日
	 */
	@ResponseBody
	@RequestMapping(value = "is_take_effect", method = RequestMethod.GET)
	public KemeanResult<String> isTakeEffect(@Valid Integer objId) {
		return adminLunBoService.isTakeEffect(objId);
	}
}
