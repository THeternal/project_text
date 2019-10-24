package com.kemean.service.common;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenThirdAPIConstant;
import com.kemean.service.WxLoginService;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.WxLoginAppletsBO;
import com.kemean.vo.bo.admin.common.AdminAccessTokenWxBO;
import com.kemean.vo.po.WxLoginAppletsPO;
import com.kemean.vo.po.com.TransferQueryALiPO;

/**
 * 统一注入处理第三方服务（支付、登录等）
 * 
 * @Date 2018年6月25日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
@PropertySource(value = "classpath:daiken-pay.properties", encoding = "UTF-8")
public class ThirdService {

	private static final String CHART_SET = "utf-8";
	private static final String FORMAT = "json";
	private static final String SIGN_TYPE = "RSA2";
	private static final String GRANT_TYPE = "authorization_code";

	@Autowired
	private WxLoginService wxLoginService;

	@Value(value = "${wx.small.appid}")
	private String wxSmallAppid;

	@Value(value = "${wx.small.app-secret}")
	private String wxSmallAppSecret;

	@Value(value = "${wx.open.appid}")
	private String wxOpenAppid;

	@Value(value = "wx.open.app-secret")
	private String wxOpenAppSecret;

	@Autowired
	private WxTemplateService wxTemplateService;

	/**
	 * 小程序登录
	 * 
	 * @author huangyujian
	 * @date 2018年6月25日
	 */
	public WxLoginAppletsBO appletsLogin(String jsCode, String encryptedData, String iv) {
		return wxLoginService
				.appletsLogin(new WxLoginAppletsPO(wxSmallAppid, wxSmallAppSecret, jsCode, encryptedData, iv));
	}

	/**
	 * 获取我的店铺小程序二维码
	 * 
	 * @author linjinzhan
	 * @date 2018年8月23日
	 */
	public void myShopQrCode(HttpServletResponse response, DaikenUser loginUser) {
		wxTemplateService.smallQrCode(response, loginUser);
	}

	/**
	 * 查询支付宝单笔转账结果
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	public AlipayFundTransOrderQueryResponse transferResultQueryALi(TransferQueryALiPO transferQueryALiPO) {
		AlipayClient alipayClient = new DefaultAlipayClient(DaikenThirdAPIConstant.ALi.SERVER_URL,
				transferQueryALiPO.getAppId(), transferQueryALiPO.getAppPrivateKey(), FORMAT, CHART_SET,
				transferQueryALiPO.getAlipayPublicKey(), SIGN_TYPE);
		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
		// 查询参数集
		AlipayFundTransOrderQueryModel model = new AlipayFundTransOrderQueryModel();
		model.setOrderId(transferQueryALiPO.getOrderId());
		model.setOutBizNo(transferQueryALiPO.getOrderNo());
		request.setBizModel(model);
		try {
			AlipayFundTransOrderQueryResponse result = alipayClient.execute(request);
			// 判断是否查询成功
			if (!result.isSuccess()) {
				KemeanUtilAid.appLog.error("【支付宝单笔转账结果查询失败】参数：" + JSONObject.toJSONString(transferQueryALiPO)
						+ ", 支付宝结果：" + JSONObject.toJSONString(result));
			}
			return result;
		} catch (AlipayApiException e) {
			e.printStackTrace();
			KemeanUtilAid.appLog.error("【支付宝单笔转账结果查询失败】参数：" + JSONObject.toJSONString(transferQueryALiPO));
		}
		return null;
	}

	/**
	 * 微信第三方登录 <br/>
	 * 根据授权临时票据code获取该用户的openid
	 * 
	 * @author linjinzhan
	 * @date 2018年8月24日
	 */
	public AdminAccessTokenWxBO getWxAccessToken(String code) {
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod();
		try {
			getMethod.setURI(new URI(DaikenThirdAPIConstant.WX.ACCESS_TOKEN, false));
			getMethod.setQueryString(new NameValuePair[] { new NameValuePair("appid", wxOpenAppid),
					new NameValuePair("secret", wxOpenAppSecret), new NameValuePair("code", code),
					new NameValuePair("grant_type", GRANT_TYPE) });
			int resultCode = client.executeMethod(getMethod);
			if (resultCode != HttpStatus.SC_OK) {
				KemeanUtilAid.appLog.error("【微信绑定授权登录失败】" + getMethod.getResponseBodyAsString());
				return null;
			} else {
				AdminAccessTokenWxBO result = JSONObject.parseObject(getMethod.getResponseBodyAsString(),
						AdminAccessTokenWxBO.class);
				KemeanUtilAid.appLog.error("【微信绑定授权登录成功】" + getMethod.getResponseBodyAsString());
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
