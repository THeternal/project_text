package com.kemean.service.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.DaikenThirdAPIConstant;
import com.kemean.vo.wx.bo.AccessToken;
import com.kemean.vo.wx.bo.ResultTemplateDate;

@Service
@PropertySource(value = "classpath:kemean/kemean_third.properties", encoding = "UTF-8")
public class WxTemplateService {

	@Value("${wx.app.id}")
	private String appId;

	@Value("${wx.app.secret}")
	private String appSecret;

	/**
	 * 发送模板URL
	 */
	private String Send_Template_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

	/**
	 * 根据微信id，secret获取access_token
	 * 
	 * @author huwei
	 * @date 2018年7月26日
	 */
	public String getAccessToken() {
		String tmpUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
				+ "&secret=" + appSecret + "";
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(tmpUrl);
		try {
			HttpResponse httpResponse = httpCilent.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String entity = EntityUtils.toString(httpResponse.getEntity());
				AccessToken accessToken = JSONObject.parseObject(entity, AccessToken.class);
				return accessToken.getAccess_token();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				httpCilent.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 发送微信消息模板
	 * 
	 * @author huwei
	 * @date 2018年7月26日
	 */
	public String sendTemplate(String touser, String formId, String templateId, String clickurl, String[] fillData) {
		String tepUrl = Send_Template_URL + getAccessToken();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(tepUrl);
		// 装配post请求参数
		JSONObject json = new JSONObject();
		json.put("touser", touser);
		json.put("template_id", templateId);
		json.put("form_id", formId);
		json.put("emphasis_keyword", "keyword1.DATA");
		JSONObject dataJson = new JSONObject();
		for (int i = 0; i < fillData.length; i++) {
			JSONObject sonDateJson = new JSONObject();
			sonDateJson.put("value", fillData[i]);
			dataJson.put("keyword" + (i + 1), sonDateJson);
		}
		json.put("data", dataJson);
		String resultStr = "发送失败";
		try {
			StringEntity myEntity = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);
			// 设置post求情参数
			httpPost.setEntity(myEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 发送成功
				String resutlEntity = EntityUtils.toString(httpResponse.getEntity());
				ResultTemplateDate resultTemplateDate = JSONObject.parseObject(resutlEntity, ResultTemplateDate.class);
				if (resultTemplateDate.getErrcode().equals("40037")) {
					resultStr = "template_id不正确";
				}
				if (resultTemplateDate.getErrcode().equals("41028")) {
					resultStr = "form_id不正确，或者过期";
				}
				if (resultTemplateDate.getErrcode().equals("41029")) {
					resultStr = "form_id已被使用";
				}
				if (resultTemplateDate.getErrcode().equals("41030")) {
					resultStr = "page不正确";
				}
				if (resultTemplateDate.getErrcode().equals("45009")) {
					resultStr = "接口调用超过限额（目前默认每个帐号日调用限额为100万）";
				}
				resultStr = "ok";
				return resultStr;
			} else {
				// 发送失败
				return resultStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					// 释放资源
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultStr;
	}
	
	/***
	 * 获取商家店铺小程序二维码
	 * 
	 * @author linjinzhan
	 * @date 2018年8月23日
	 */
	// TODO 由于小程序尚未发布所以得发布之后才能测试
	public void smallQrCode(HttpServletResponse response, DaikenUser loginUser) {
		// 获取小程序二维码
		String url = DaikenThirdAPIConstant.WX.SMALL_QRCODE.replace("ACCESS_TOKEN", getAccessToken());
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		// 装配post请求参数
		JSONObject json = new JSONObject();
		String scene = "objId=" + loginUser.getId() + "&userShopId=0";
		json.put("scene", scene);
		//json.put("page", "pages/baby/goodsStore/goodsStore");
		json.put("page", "");
		json.put("width", 350);
		json.put("auto_color", false);
		json.put("is_hyaline", false);
		
		try {
			StringEntity myEntity = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);
			// 设置post请求参数
			httpPost.setEntity(myEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity resultEntity = httpResponse.getEntity();
			resultEntity.writeTo(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					// 释放资源
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
