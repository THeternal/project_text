package com.kemean.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kemean.constant.KemeanCalendarFieldEnum;
import com.kemean.constant.KemeanDateFormatEnum;

/**
 * 代研项目工具类
 * 
 * @Date 2018年6月1日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
public class DaikenUtil extends KemeanUtilWeb {

	// 开始时间2018-09-21 15:00:00 
	// 结束时间2018-09-21 16:00:00
	
	public static void main(String[] args) {
		Date now = new Date();
		Date dateByCalendar = KemeanUtilAid.getDateByCalendar(now, KemeanCalendarFieldEnum.MINUTE, 5);
		System.out.println("dateByCalendar : " + DaikenUtil.formatDate(dateByCalendar, KemeanDateFormatEnum.NORMAL));
		System.out.println("now : " + DaikenUtil.formatDate(now, KemeanDateFormatEnum.NORMAL));
		Integer dateDifference = dateDifferenceSecond(dateByCalendar, now);
		System.out.println(dateDifference);
	}

	public static String getHourStr(Integer hours) {
		String[] hoursArr = { "零点", "一点", "两点", "三点", "四点", "五点", "六点", "七点", "八点", "九点", "十点", "十一点", "十二点", "十三点",
				"十四点", "十五点", "十六点", "十七点", "十八点", "十九点", "二十点", "二十一点", "二十二点", "二十三点", "二十四点" };
		return hoursArr[hours];
	}

	/**
	 * 生成商品的uid
	 * 
	 * @author huwei
	 * @date 2018年8月30日
	 */
	public static String getGoodsUid(Date now) {
		String curTime = formatDate(now, KemeanDateFormatEnum.TIME_NUM);
		int i = (int) (Math.random() * 900) + 100;
		return curTime + i;
	}

	/**
	 * 格式化时间
	 * 
	 * @author huwei
	 * @date 2018年8月30日
	 */
	public static String foematInteger(String num) {
		char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String m = val[i] + "";
			int n = Integer.valueOf(m);
			boolean isZero = n == 0;
			if (isZero) {
				if ('0' == val[i - 1]) {
					continue;
				} else {
					sb.append(numArray[n]);
				}
			} else {
				sb.append(numArray[n]);
			}
		}
		return sb.toString();
	}

	/**
	 * emoji表情替换
	 *
	 * @param source
	 *            原字符串
	 * @param slipStr
	 *            emoji表情替换成的字符串
	 * @return 过滤后的字符串
	 */
	public static String filterEmoji(String source, String slipStr) {
		if (StringUtils.isNotBlank(source)) {
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
		} else {
			return source;
		}
	}

	/**
	 * 本月月末
	 */
	public static Date currentMonthLast(Date now) {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		return ca.getTime();
	}

	/**
	 * 本月月初
	 */
	public static Date currentMonthBegin(Date now) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH, 0);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		return ca.getTime();
	}

	/**
	 * 规格1-内容1 规格2-内容2
	 * 
	 * @author huwei
	 * @date 2018年6月29日
	 */
	public static String parseJsonArray(String jsonArray) {
		String recordType = "";
		JSONArray jsonArra = JSONObject.parseArray(jsonArray);
		for (int i = 0; i < jsonArra.size(); i++) {
			JSONObject obj = (JSONObject) JSONObject.toJSON(jsonArra.get(i));
			Iterator<Entry<String, Object>> it = obj.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				recordType += entry.getKey() + "-" + entry.getValue() + "   ";
			}
		}
		return recordType;
	}

	/**
	 * String 转 JSONObject
	 * 
	 * @author huwei
	 * @date 2018年6月29日
	 */
	public static JSONObject parseJsonObject(String jsonArray) {
		JSONObject result = new JSONObject();
		JSONArray jsonArra = JSONObject.parseArray(jsonArray);
		for (int i = 0; i < jsonArra.size(); i++) {
			JSONObject obj = (JSONObject) JSONObject.toJSON(jsonArra.get(i));
			Iterator<Entry<String, Object>> it = obj.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}

	/**
	 * 判断是否是订单编号
	 */
	public static Boolean isOrderNo(String keyWord) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) keyWord);
		return matcher.matches();
	}

	/**
	 * 当前时间加年份
	 */
	public static Date addYear(Date date, Integer year) {
		Calendar c = Calendar.getInstance();
		date = c.getTime();
		c.add(Calendar.YEAR, year);
		return c.getTime();
	}

	/**
	 * list转换成JsonArray
	 */
	public static JSONArray parseJSONArrayByList(List<?> list) {
		JSONArray jsonArray = new JSONArray();
		if (list == null || list.isEmpty()) {
			return jsonArray;
		}
		for (Object object : list) {
			jsonArray.add(object);
		}
		return jsonArray;
	}

	/**
	 * @param 日期相差天数
	 *            yyyy-MM-dd
	 */
	public static Integer dateDifference(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 日期相差天数精准到秒
	 */
	public static Integer dateDifferenceSecond(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time1 - time2) / 1000;
		return Integer.parseInt(String.valueOf(between_days));
	}

	/** 获取两个时间差 **/
	public static String getTwoDayMinuteStr(Date frontDay, Date backDay) {
		int twoDayMinute = getTwoDayMinute(frontDay, backDay);
		if (twoDayMinute < 60) {
			return twoDayMinute + "分钟前";
		}
		if (twoDayMinute >= 60 && 1440 >= twoDayMinute) {
			return twoDayMinute / 60 + "小时前";
		}

		if (twoDayMinute > 1440) {
			return twoDayMinute / 1440 + "天前";
		}
		return "";
	}
}
