package com.globot.hmi.attendance.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 时间戳转date
	 * @param timestamp
	 * @return
	 */
	public static Date getDateByTimeStamp(String timestamp){
		Date date;
		if(null!=timestamp){
			long time=new Long(timestamp);
			date=new Date(time);
		}else{
			date=new Date();
		}
		return date;
	}
	
	/**
	 * 根据日期得到当月工作日天数
	 * @param date
	 * @return
	 */
	public static int getTotalDays(Date date){
		int count = 0;
		String[] strDate = new SimpleDateFormat("yyyy-MM-dd").format(date).toString().split("-");
		int year = Integer.parseInt(strDate[0]);
		int month = Integer.parseInt(strDate[1]);
		int day = Integer.parseInt(strDate[2]);
		Calendar calendar = Calendar.getInstance();
		int trueMonth = calendar.get(Calendar.MONTH) + 1;
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
//		if(month == trueMonth){
//			while (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) < month && calendar.get(Calendar.DATE) <= day) {
//				int count_day = calendar.get(Calendar.DAY_OF_WEEK);
//				if (count_day != Calendar.SATURDAY && count_day != Calendar.SUNDAY) {
//					count++;
//				}
//				calendar.add(Calendar.DATE, 1);
//			}
//		}else{
//			while (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) < month) {
//				int count_day = calendar.get(Calendar.DAY_OF_WEEK);
//				if (count_day != Calendar.SATURDAY && count_day != Calendar.SUNDAY) {
//					count++;
//				}
//				calendar.add(Calendar.DATE, 1);
//			}
//		}
		while (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) < month) {
			int count_day = calendar.get(Calendar.DAY_OF_WEEK);
			if (count_day != Calendar.SATURDAY && count_day != Calendar.SUNDAY) {
				count++;
			}
			calendar.add(Calendar.DATE, 1);
		}
		return count;
	}
	
	public static int getInsMonthTotaldays(Date date) {
		int count = 0;
		String[] strDate = new SimpleDateFormat("yyyy-MM-dd").format(date).toString().split("-");
		int year = Integer.parseInt(strDate[0]);
		int month = Integer.parseInt(strDate[1]);
		int day = Integer.parseInt(strDate[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		while (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) < month && calendar.get(Calendar.DATE) <= day) {
			int count_day = calendar.get(Calendar.DAY_OF_WEEK);
			if (count_day != Calendar.SATURDAY && count_day != Calendar.SUNDAY) {
				count++;
			}
			calendar.add(Calendar.DATE, 1);
		}
		return count;
	}
}
