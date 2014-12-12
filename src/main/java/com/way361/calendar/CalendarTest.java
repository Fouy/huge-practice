package com.way361.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {

	public static void main(String[] args) {
//		Calendar now = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
//		now.set(1970, 0, 1, 0, 0, 0);
//		now.setTimeZone(TimeZone.getTimeZone("GMT"));
//		System.out.println(now.getTimeZone());
//		Date week = now.getTime();
//		System.out.println(week);
//		System.out.println(now.getTimeZone().getRawOffset());
//		System.out.println(new Date());
		
//		long time = now.getTimeInMillis();
//		System.out.println(time);
		
//		String[] timeZones = TimeZone.getAvailableIDs();
//		for(String timeZone : timeZones){
//			System.out.println(timeZone);
//		}
//		System.out.println(timeZones.length);
		
		new CalendarTest().printFloatBinary(9f);
		
	}
	
	
	
	
	public void printFloatBinary(float data){
		int a=-6;
		for(int i = 0; i < 32; i++){
			int t=(a & 0x80000000>>>i)>>>(31-i);
			System.out.print(t);
		}

	}
	
	
	
	

}
