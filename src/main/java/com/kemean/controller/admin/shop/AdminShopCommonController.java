package com.kemean.controller.admin.shop;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kemean.bean.KemeanRichText;
import com.kemean.controller.DaikenBaseController;
import com.kemean.service.admin.AdminCommonService;
import com.kemean.service.admin.AdminPayService;
import com.kemean.service.common.ThirdService;
import com.kemean.vo.bo.KemeanIdAndName;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.admin.goods.AdminGoodsNewCategoryBO;
import com.kemean.vo.po.admin.recommend.ShopRecommendPO;
import com.kemean.vo.po.admin.shop.AdminWithdrawDepositPO;

/**
 * 管理后台公共
 * 
 * @Date 2018年4月15日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Controller
@RequestMapping("shop/common")
public class AdminShopCommonController extends DaikenBaseController {

	@Autowired
	private AdminCommonService adminCommonService;

	@Autowired
	private AdminPayService adminPayService;

	@Autowired
	private ThirdService thirdService;

	/**
	 * 获取商品分类
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */

	@ResponseBody
	@RequestMapping(value = "goods_category", method = RequestMethod.GET)
	public KemeanResult<List<AdminGoodsNewCategoryBO>> goodsBeseCategory(@RequestParam Integer pid) {
		return new KemeanResult<List<AdminGoodsNewCategoryBO>>(adminCommonService.goodsBeseCategory(pid));
	}

	/**
	 * 获取商品规格
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	@ResponseBody
	@RequestMapping(value = "goods_baseType", method = RequestMethod.GET)
	public KemeanResult<List<KemeanIdAndName>> goodsBeseType(@RequestParam Integer objId) {
		return new KemeanResult<List<KemeanIdAndName>>(adminCommonService.goodsBeseType(objId));
	}

	/**
	 * 获取用户基础信息
	 * 
	 * @author tanggengxiang
	 * @date 2018年6月26日
	 */
	@ResponseBody
	@RequestMapping(value = "user_base_data", method = RequestMethod.GET)
	public List<KemeanIdAndName> userBaseType(@RequestParam Integer type, @RequestParam Integer pid) {
		return adminCommonService.userBaseType(type, pid);
	}

	/**
	 * 商家充值页面
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	@RequestMapping(value = "my_recharge", method = RequestMethod.GET)
	public String myRecharge() {
		return "shop/myRecharge";
	}

	/**
	 * 商家充值 -- 微信支付
	 * 
	 * @author linjinzhan
	 * @date 2018年8月20日
	 */
	@RequestMapping(value = "my_recharge_wx_pay", method = RequestMethod.GET)
	public void myRechargeWxPay(@RequestParam Double payPrice, HttpServletResponse response) {
		adminPayService.paySignWx(request, payPrice, response, getLoginAdminShop());
	}

	/**
	 * 商家充值 -- 支付宝支付
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "my_recharge_ali_pay", method = RequestMethod.GET)
	public void myRechargeAliPay(@RequestParam Double payPrice, HttpServletResponse response) {
		adminPayService.paySignAli(request, response, payPrice, getLoginAdminShop());
	}

	/**
	 * 发起支付宝支付请求页面
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "ali_pay_page", method = RequestMethod.GET)
	public String aliPayPage(@RequestParam Double payPrice) {
		request.setAttribute("payPrice", payPrice);
		return "shop/aliPay";
	}

	/**
	 * 支付宝成功返回提示页面
	 * 
	 * @author linjinzhan
	 * @date 2018年8月21日
	 */
	@RequestMapping(value = "pay_return_success_page", method = RequestMethod.GET)
	public String aliPaySuccess() {
		return "shop/aliPaySuccess";
	}

	/**
	 * 商家充值条款
	 * 
	 * @author linjinzhan
	 * @date 2018年8月22日
	 */
	@ResponseBody
	@RequestMapping(value = "pay_item", method = RequestMethod.GET)
	public KemeanResult<KemeanRichText> payItem() {
		return adminPayService.payItem();
	}

	/**
	 * 商家提现页面
	 * 
	 * @author linjinzhan
	 * @date 2018年8月23日
	 */
	@RequestMapping(value = "my_withdraw_deposit", method = RequestMethod.GET)
	public String myWithdrawDeposit() {
		request.setAttribute("pageData", adminPayService.shopBalanceInfo(getLoginAdminShop()));
		return "shop/myWithdrawDeposit";
	}

	/**
	 * 获取我的店铺小程序二维码
	 * 
	 * @author linjinzhan
	 * @date 2018年8月23日
	 */
	@RequestMapping(value = "my_shop_qr_code", method = RequestMethod.GET)
	public void myShopQrCode(HttpServletResponse response) {
		thirdService.myShopQrCode(response, getLoginAdminShop());
	}

	/**
	 * 商家提交提现 -- 微信、支付宝
	 * 
	 * @author linjinzhan
	 * @date 2018年8月23日
	 */
	@ResponseBody
	@RequestMapping(value = "withdraw_deposit", method = RequestMethod.POST)
	public KemeanResult<String> withdrawDeposit(@Valid AdminWithdrawDepositPO adminWithdrawDepositPO) {
		return adminPayService.withdrawDeposit(adminWithdrawDepositPO, getLoginAdminShop());
	}

	/**
	 * 商家提现发送验证码
	 * 
	 * @author linjinzhan
	 * @date 2018年8月27日
	 */
	@ResponseBody
	@RequestMapping(value = "withdraw_deposit_send_code", method = RequestMethod.GET)
	public KemeanResult<String> withdrawDepositSendCode(@RequestParam Integer type) {
		return adminPayService.withdrawDepositSendCode(type, getLoginAdminShop(), 0.0D);
	}

	/**
	 * 用户活动轨迹page
	 * 
	 * @author tanggengxiang
	 * @date 2018年7月25日
	 */
	@RequestMapping(value = "push_user_record_page", method = RequestMethod.GET)
	public String UserRecordPage(@RequestParam Integer userId) {
		request.setAttribute("pageData", adminCommonService.goodsPushUserRecord(userId));
		return "shop/userRecord";
	}

	/**
	 * 首页推荐店铺页面
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@RequestMapping(value = "shop_recommend_page", method = RequestMethod.GET)
	public String shopRecommendPage() {
		request.setAttribute("pageData", adminCommonService.recommendShop(getLoginAdminShop()));
		return "shop/recommend";
	}

	/**
	 * 首页推荐商品页面
	 * 
	 * @author Woody Hu
	 * @date 2018年9月6日
	 */
	@RequestMapping(value = "goods_recommend_page", method = RequestMethod.GET)
	public String goodsRecommendPage(@RequestParam Integer objId) {
		request.setAttribute("pageData", adminCommonService.goodsRecommendPage(objId));
		return "shop/recommend";
	}

	/**
	 * 商品首页推荐宝贝页面
	 * 
	 * @author WuDiHu
	 * @date 2018年9月7日
	 */
	@RequestMapping(value = "goods_treasure_page", method = RequestMethod.GET)
	public String goodsTreasurePage(@RequestParam Integer goodsId) {
		request.setAttribute("pageData", adminCommonService.goodsTreasurePage(goodsId));
		return "shop/treasure";
	}

	/**
	 * 添加首页推荐信息
	 * 
	 * @author huwei
	 * @date 2018年9月5日
	 */
	@ResponseBody
	@RequestMapping(value = "shop_recommend_add", method = RequestMethod.POST)
	public KemeanResult<String> shopRecommendAdd(ShopRecommendPO shopRecommendPO) {
		return adminCommonService.shopRecommendAdd(shopRecommendPO, getLoginAdminShop());
	}
}
