package com.landcoder.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期服务类
 * @author landcoder
 * @company oschina
 */
public class DateUtils {

	/**
	 * 日期加时间： yyyy-MM-dd HH:mm:ss
	 */
	public static final String dt = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期： yyyy-MM-dd
	 */
	public static final String d = "yyyy-MM-dd";
	/**
	 * 时间： HH:mm:ss
	 */
	public static final String t = "HH:mm:ss";
	/**
	 * long型时间日期格式： yyyyMMddHHmmssS
	 */
	public static final String dtLong = "yyyyMMddHHmmssS";

	/**
	 * 日期转字符串
	 * @param d date
	 * @param p 格式
	 * @return
	 */
	public static String dTS(Date d,String p){
		if(d == null){
			return null;
		}
		DateFormat df = new SimpleDateFormat(p);
		return df.format(d);
	}

	/**
	 * 字符串转日期
	 * @param d date
	 * @param p 格式
	 * @return
	 * @throws ParseException 
	 */
	public static Date sTD(String d,String p) throws ParseException{
		if(d == null || d.isEmpty()){
			return null;
		}
		DateFormat df = new SimpleDateFormat(p);
		return df.parse(d);
	}

	/**
     * 返回系统当前时间(精确到毫秒)
     * @return
     *      以yyyyMMddHHmmssS为格式的当前系统时间
	 * @throws ParseException 
     */
	public static String getDSL(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取基于传入时间的时间变化 例如获取下一天的时间
	 * @param date 传入时间
	 * @param field 需要推迟的类型
	 * @param amount 需要推迟的数量
	 * @param year 可以设置年
	 * @param month 可以设置月
	 * @param day 可以设置日
	 * @param hour 可以设置小时
	 * @param minute 可以设置分钟
	 * @param second 可以设置秒钟
	 * @return
	 */
	public static Date getNextDate(Date date, Integer field, Integer amount, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, amount);
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		if (day == null) {
			day = calendar.get(Calendar.DAY_OF_MONTH);
		}
		if (hour == null) {
			hour = calendar.get(Calendar.HOUR_OF_DAY);
		}
		if (minute == null) {
			minute = calendar.get(Calendar.MINUTE);
		}
		if (second == null) {
			second = calendar.get(Calendar.SECOND);
		}
		calendar.set(year, month, day, hour, minute, second);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取unix时间戳
	 * @param date 时间
	 * @return
	 */
	public static Long getUnixTime(Date date) {
		Long seconds = date.getTime() / 1000;
		return seconds;
	}
}
