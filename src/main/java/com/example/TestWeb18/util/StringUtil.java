/**
 * @author zhouzhen@date 2017-2-20
 */
package com.example.TestWeb18.util;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouzhen
 * @date 2017-2-20
 */
public class StringUtil
{

    /**
     * 检查 参数String 是否为有意义的字符串<br>
     * 不为null,且不为空
     *
     * @param string
     * @return
     */
    public static boolean isValid(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * 检查 参数StringBuffer 是否为有意义的字符串<br>
     * 不为null,且不为空
     *
     * @param string
     * @return
     */
    public static boolean isValid(StringBuffer sb) {
        if (sb == null || sb.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查 参数StringBuilder 是否为有意义的字符串<br>
     * 不为null,且不为空
     *
     * @param string
     * @return
     */
    public static boolean isValid(StringBuilder sb) {
        if (sb == null || sb.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查 参数Object 是否为有意义的对象<br>
     * 不为null,且不为空
     *
     * @param object
     * @return
     */
    public static boolean isValid(Object object) {
        if (object == null || object.toString().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    

    /**
     * 中文：/[\u4e00-\u9fa5]/
     * 日文：/[\u0800-\u4e00]/
     * 韩文：/[\uac00-\ud7ff]/
     *
     * @param str
     * @return
     */
    public static boolean isJapanesChar(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u0800-\u4e00]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

    public static String getString(String str) {
        if (str == null || "".equals(str)) {
            return "";
        } else {
            return str;
        }
    }
    
    public static String getStringOrNULL(Object str) {
        if (str == null) {
            return null;
        } else {
            return str.toString();
        }
    }

    public static String getFormatDate(String oridata) {
    	
		SimpleDateFormat sdfOmd = new SimpleDateFormat("MMMM d yyyy h:mma", Locale.ENGLISH);
		sdfOmd.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		SimpleDateFormat sdfPlat = null;
		String publisTime = "";
		try {
			//时间为2018-07-26T03:34:54 Z
			if (oridata.trim().matches("^2[0-9]{3}-[01][0-9]-[0123][0-9]T[012][0-9]:[0-5][0-9]:[0-5][0-9]Z")) {
				TimeZone utc= TimeZone.getTimeZone("UTC");
				sdfPlat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				sdfPlat.setTimeZone(utc);
				Date utcdate = sdfPlat.parse(oridata);
				publisTime = sdfOmd.format(utcdate);
			}
			else if(oridata.trim().matches("^2[0-9]{3}-[01][0-9]-[0123][0-9]T[012][0-9]:[0-5][0-9]:[0-5][0-9]\\.[0-9]{3}Z")){
				//时间为2018-07-26T03:34:54.679Z
				TimeZone utc= TimeZone.getTimeZone("UTC");
				sdfPlat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				sdfPlat.setTimeZone(utc);
				Date utcdate = sdfPlat.parse(oridata);
				publisTime = sdfOmd.format(utcdate);
			}
			else{
				publisTime = new DateTime(oridata,
						DateTimeZone.forID("America/Los_Angeles")).toString("MMMM d yyyy h:mma", Locale.ENGLISH);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publisTime;
        
    }

    /**
     * 获取美国时间（字符串）
     *
     * @param date 传入日期
     */
    public static String getFormatDate(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
            return sdf.format(date);
        } else {
            return null;
        }
    }
    
    public static String getStringValue(Object obj){
		if(obj!=null && !"".equals(String.valueOf(obj))){
			return String.valueOf(obj);
		}else{
			return "";
		}
	}
    
    public static Date walFormatDate(String utcStr) throws ParseException {
    	if(!isValid(utcStr)){
    		return null;
    	}
        TimeZone utc= TimeZone.getTimeZone("UTC");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(utc);
		Date utcdate = sdf.parse(utcStr);
		SimpleDateFormat losFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone tz= TimeZone.getTimeZone("America/Los_Angeles");
		losFormater.setTimeZone(tz);
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String losDateStr = losFormater.format(utcdate);
		return sdf2.parse(losDateStr);
    }

    public static Date getDateByStrAndSdfFomart(String utcStr, String format){
    	if(StringUtil.isValid(utcStr) && StringUtil.isValid(format)){
    		String publisTime = new DateTime(utcStr,
    				DateTimeZone.forID("America/Los_Angeles")).toString(format, Locale.ENGLISH);
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		try {
    			Date date = sdf.parse(publisTime);
    			return date;
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    	}
    	return null;
    }
    
    public static String getDateStrByUtcStrAndSdfFormat(String utcStr, String format){
    	if(StringUtil.isValid(utcStr) && StringUtil.isValid(format)){
    		String publisTime = new DateTime(utcStr,
    				DateTimeZone.forID("America/Los_Angeles")).toString(format, Locale.ENGLISH);
    		return publisTime;
    	}
    	return null;
    }

    public static String getAmazonSHZOrderDate(String orderDate) throws ParseException {
        if (orderDate == null || "".equals(orderDate)) {
            return null;
        }
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        sdf.setTimeZone(utc);
        Date utcDate = sdf.parse(orderDate);
        SimpleDateFormat losFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
        losFormat.setTimeZone(tz);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String losDateStr = losFormat.format(utcDate);
        Date date = sdf2.parse(losDateStr);
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mma", Locale.ENGLISH);
        return format.format(date);
    }
    
    /**
     * 将'yyyy-MM-dd HH:mm:ss.SSS'格式的时间转换为洛杉矶时间
     * @param orderDate
     * @return
     * @throws ParseException
     */
    public static String getLosAngelesDate(String orderDate) throws ParseException {
        if (orderDate == null || "".equals(orderDate)) {
            return null;
        }
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(utc);
        Date utcDate = sdf.parse(orderDate);
        SimpleDateFormat losFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
        losFormat.setTimeZone(tz);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String losDateStr = losFormat.format(utcDate);
        Date date = sdf2.parse(losDateStr);
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mma", Locale.ENGLISH);
        return format.format(date);
    }
    
    /**
     * 检查 参数Object 是否为有意义的对象<br>
     * 不为null,且不为空
     *
     * @param object
     * @return
     */
    public static boolean isInvalid(Object object) {
        if (object == null || object.toString().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String subStrWithLength(String str , int length){
        if(str.length()<length){
            return str;
        }else{
            return str.substring(0,length);
        }
    }

    /**
     * add by chenyafei
     * @param dateStr 要转换的时间字符串
     * @param fromFormat  当前时间字符串的格式
     * @param toFormat 要转换成的时间字符串格式
     * @param timeZone 时区设置（不传使用，America/Los_Angeles）
     * @param locale 地区（不传默认使用，Locale.ENGLISH）
     * @return
     */
    public static Date getFormatDate(String dateStr, String fromFormat, String toFormat, TimeZone timeZone, Locale locale) throws ParseException {

        if(dateStr == null || "".equals(dateStr)){
            return null;
        }
        SimpleDateFormat sourcesdf = null;

        SimpleDateFormat targetDateSdf = null;

        if(null != locale){
            sourcesdf = new SimpleDateFormat(fromFormat, locale);
            targetDateSdf = new SimpleDateFormat(toFormat, locale);

        }else{// 默认Locale.ENGLISH
            sourcesdf = new SimpleDateFormat(fromFormat, Locale.ENGLISH);
            targetDateSdf = new SimpleDateFormat(toFormat, Locale.ENGLISH);

        }



        TimeZone defaultZone =null;
        if(null != timeZone){
            defaultZone = timeZone;
        }else {
            defaultZone = TimeZone.getTimeZone("America/Los_Angeles");
        }



        targetDateSdf.setTimeZone(defaultZone);


        Date sourceDate = sourcesdf.parse(dateStr);
        String targetDateStr = targetDateSdf.format(sourceDate);
        Date targetDate = targetDateSdf.parse(targetDateStr);

        return targetDate;
    }
}
