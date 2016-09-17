/**
 * 
 */
package com.sfteam.wxzysh.util;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

/**
 * @ClassName: Constants.java
 * @Description: 常量定义
 * @author: knight
 * @date: 2016年7月14日 下午3:09:12
 * @company: sfteam
 */
public final class Constants {

	public static String METHOD_POST = "post";

	public static String METHOD_GET = "get";

	/* 存放session */
	public static String PAGE_RESOURCE = "user";

	public static String SUCCESS = "0";

	public static String FAILURE = "1";

	public static String HAD_VERIFIED = "0";

	public static String NOT_VERIFIED = "1";

	public static String VERIFIED_NO = "2";

	/**
	 * 
	 * @Title: getUUID
	 * @Description: UUID
	 * @return: String
	 * @author: knight
	 * @date: 2016年7月9日 上午11:09:18
	 */
	public final static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek()
	{
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}
}
