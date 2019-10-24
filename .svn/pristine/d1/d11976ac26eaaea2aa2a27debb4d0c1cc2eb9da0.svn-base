package com.kemean.service.consumer;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanPayTypeEnum;
import com.kemean.constant.PayWxTradeTypeEnum;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.exception.KemeanException;
import com.kemean.service.PayWxService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.WxRefundBO;
import com.kemean.vo.po.PayRefundWxPO;
import com.kemean.vo.po.PaySignWxPO;
import com.kemean.vo.po.c.oder.GetPaidOrderPO;

@Service
@PropertySource(value = "classpath:daiken-pay.properties", encoding = "UTF-8")
public class PayService {

	@Value("${wx.p12.path}")
	private String wxP12Path;

	/** 回调地址 **/
	@Value("${wx.notify.url}")
	private String NOTIFY_URL;

	/** 应用ID **/
	@Value("${wx.open.appid}")
	private String APP_ID;

	/** 商户号 **/
	@Value("${wx.business.mchid}")
	private String MCH_ID;

	/** 商户平台设置的密钥key **/
	@Value("${wx.business.key}")
	private String KEY;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private PayWxService payWxService;

	/**
	 * 微信支付签名(小程序)
	 * 
	 * @author huangyujian
	 * @date 2018年4月4日
	 */
	@Transactional
	public KemeanResult<Map<String, String>> paySignWx(GetPaidOrderPO getPaidOrderPO, HttpServletRequest request,
			DaikenUser daikenUser) {
		Date now = new Date();
		PaySignWxPO wxPo = new PaySignWxPO();
		wxPo.setBody("代研订单");
		wxPo.setAppId(APP_ID);
		wxPo.setMchId(MCH_ID);
		wxPo.setKey(KEY);
		wxPo.setOpenid(daikenUser.getWxOpenId());
		wxPo.setNotifyUrl(NOTIFY_URL);
		wxPo.setTradeType(PayWxTradeTypeEnum.JSAPI);
		if (getPaidOrderPO.getOrderNos().size() == 1) {
			DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
					new String[] { KemeanConstant.DATA_DELETED, "orderNo" },
					new Object[] { false, getPaidOrderPO.getOrderNos().get(0) });
			if (!KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus().equals(dbOrder.getStatusUser())) {
				return new KemeanResult<>(false, "订单信息错误");
			}
			dbOrder.setPayType(KemeanPayTypeEnum.WX_PAY.getType());
			dbOrder.setPayReqTime(now);
			dbOrder.setUpdateTime(now);
			daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			wxPo.setOrderNo(getPaidOrderPO.getOrderNos().get(0));
			wxPo.setPayPrice(dbOrder.getPricePay());
		}
		if (getPaidOrderPO.getOrderNos().size() >= 2) {
			// 组合订单编号
			String orderNoGroup = "g" + KemeanUtilAid.getOrderNo(now);
			double payPrice = 0.0;
			for (String item : getPaidOrderPO.getOrderNos()) {
				DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
						new String[] { KemeanConstant.DATA_DELETED, "orderNo" }, new Object[] { false, item });
				if (!KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus().equals(dbOrder.getStatusUser())) {
					throw new KemeanException("订单信息错误");
				}
				dbOrder.setOrderNoGroup(orderNoGroup);
				payPrice += dbOrder.getPricePay();
				dbOrder.setPayType(KemeanPayTypeEnum.WX_PAY.getType());
				dbOrder.setPayReqTime(now);
				dbOrder.setUpdateTime(now);
				daikenOrderDao.updateByPrimaryKeySelective(dbOrder);
			}
			wxPo.setPayPrice(payPrice);
			wxPo.setOrderNo(orderNoGroup);
		}
		return new KemeanResult<Map<String, String>>(payWxService.unifiedorder(wxPo, true));
	}

	/**
	 * 微信退款
	 * 
	 * @author huwei
	 * @date 2018年7月27日
	 */
	public WxRefundBO refundWx(DaikenOrder daikenOrder, String orderNoRefund) {
		int refundMoney = (int) (daikenOrder.getPricePay() * 100);
		PayRefundWxPO payRefundWxPO = new PayRefundWxPO(APP_ID, MCH_ID, KEY, daikenOrder.getOrderNo(), orderNoRefund,
				refundMoney, refundMoney);
		WxRefundBO result = payWxService.refund(payRefundWxPO, wxP12Path, KEY);
		KemeanUtilAid.appLog
				.info("订单退款：" + JSONObject.toJSONString(daikenOrder) + "，【微信退款】结果：" + JSONObject.toJSONString(result));
		return result;
	}

}
