package com.ninja.hrm.JavaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}

	public String getSystemDateYYYYDDMM() {
		Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
	}

	public String getRequiredDateYYYYDDMM(int days) {
		Date dateobj = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		simple.format(dateobj);
		Calendar cal = simple.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = simple.format(cal.getTime());
		return reqDate;

	}

}
