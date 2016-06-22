package com.landcoder.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

/**
 * 获取ID相关工具类
 * @author landcoder
 * @company oschina
 */
public class CreateIDUtils {
	public static final String dtLong = "yyyyMMddHHmmssSSS";
	
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 生成订单编号
	 * @return
	 */
	public static String newOrderId() {
		return getOrderNum() + RandomUtils.nextInt(100, 999);
	}
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
}
