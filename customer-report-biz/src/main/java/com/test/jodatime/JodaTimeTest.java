package com.test.jodatime;

import java.util.Calendar;

import org.joda.time.DateTime;

public class JodaTimeTest {

	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance();
		DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		
		System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
		calendar.setTime(dateTime.toDate());
	}
}
