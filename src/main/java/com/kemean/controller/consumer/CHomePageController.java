package com.kemean.controller.consumer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kemean.controller.DaikenBaseController;
import com.kemean.service.common.UserService;
import com.kemean.service.consumer.HomePageService;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.c.common.HistorySearchBO;
import com.kemean.vo.bo.c.common.SearchInfoBO;
import com.kemean.vo.bo.c.mall.NewGoodsActivityListBO;
import com.kemean.vo.bo.com.CBaseUserInfoBO;
import com.kemean.vo.bo.com.ShareRedPacketBO;
import com.kemean.vo.po.KemeanPageApiPO;
import com.kemean.vo.po.c.common.GoodsActivityPO;
import com.kemean.vo.po.c.common.SearchGoodsPO;
import com.kemean.vo.po.c.common.ShareRedPacketPO;

/**
 * 【客户端】首页控制器
 * 
 * @Date 2018年7月11日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@RestController
@RequestMapping("api/c/common")
public class CHomePageController extends DaikenBaseController {

	@Autowired
	private HomePageService homePageService;

	@Autowired
	private UserService userService;

	/**
	 * 搜索
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public KemeanResult<KemeanPageApiBO<List<SearchInfoBO>>> search(@Valid @RequestBody SearchGoodsPO searchGoodsPO) {
		return new KemeanResult<KemeanPageApiBO<List<SearchInfoBO>>>(
				homePageService.search(searchGoodsPO, getLoginConsumer()));
	}

	/**
	 * 历史搜索
	 * 
	 * @author huwei
	 * @date 2018年8月9日
	 */
	@RequestMapping(value = "history_search", method = RequestMethod.GET)
	public KemeanResult<List<HistorySearchBO>> historySearch() {
		return homePageService.historySearch(getLoginConsumer());
	}

	/**
	 * 删除历史搜索
	 * 
	 * @author huwei
	 * @date 2018年8月10日
	 */
	@RequestMapping(value = "del_search", method = RequestMethod.GET)
	public KemeanResult<String> delSearch() {
		return homePageService.delSearch(getLoginConsumer());
	}

	/**
	 * 商品活动
	 * 
	 * @author huwei
	 * @date 2018年6月15日
	 */
	@RequestMapping(value = "goods_activity", method = RequestMethod.POST)
	public KemeanResult<KemeanPageApiBO<List<NewGoodsActivityListBO>>> goodsActivity(
			@Valid @RequestBody GoodsActivityPO goodsActivityPO) {
		return new KemeanResult<KemeanPageApiBO<List<NewGoodsActivityListBO>>>(
				homePageService.goodsActivity(goodsActivityPO));
	}

	/**
	 * 基础信息（跟登录接口返回一致）
	 * 
	 * @author huwei
	 * @date 2018年6月12日
	 */
	@RequestMapping(value = "base_info", method = RequestMethod.GET)
	public KemeanResult<CBaseUserInfoBO> baseInfo() {
		return userService.getUserInfo(getLoginConsumer(), false);
	}

	/**
	 * 分享发红包
	 * 
	 * @author huwei
	 * @date 2018年7月4日
	 */
	@RequestMapping(value = "share_red_packet", method = RequestMethod.POST)
	public KemeanResult<ShareRedPacketBO> shareRedPacket(@Valid @RequestBody ShareRedPacketPO shareRedPacketPO) {
		return homePageService.shareRedPacket(shareRedPacketPO, getLoginConsumer());
	}

	/**
	 * 热销宝贝（推荐宝贝）
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@RequestMapping(value = "recommend_treasure", method = RequestMethod.POST)
	public KemeanResult<KemeanPageApiBO<List<NewGoodsActivityListBO>>> recommendTreasure(
			@Valid @RequestBody KemeanPageApiPO kemeanPageApiPO) {
		return new KemeanResult<>(homePageService.recommendTreasure(kemeanPageApiPO));
	}

}
