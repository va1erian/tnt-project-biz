package com.app5.tnt.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	static public Calendar getCalendar(Date date){
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}
}
