package com.skyrate.clib.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;



public class DateTimeUtil {

	public static String getTodayString(){

		//Date date = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String today = formatter.format(new Date());
		
		return  today;
	}
	public static Date stringToDate(String argDate) {
		Date t = null;
		try {
			//TimeZone tz = TimeZone.getDefault();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			sdf.setLenient(false);
			//sdf.setTimeZone(tz);
			t = new Date(sdf.parse(argDate).getTime());

		} catch (java.text.ParseException pe) {
			//logger_.debug("ParseException encountered in stringToTimestamp. String = " + argDate + ", Msg = " + pe.getMessage());
		}
		return t;
	}
	public static String convertToStringFormat(String dateComing) throws ParseException{
		String dateString = null;
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
				 Date myDate = dateFormat.parse(dateComing);
				 Calendar cal = Calendar.getInstance();
			        cal.setTime(myDate);
			        cal.add(Calendar.DATE, 1); //minus number would decrement the days
			        cal.getTime();
				 myDate =cal.getTime();
			    DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy",
			        Locale.ENGLISH);
			    outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			   
			    dateString = outputFormat.format(myDate);
			    System.out.println(" sdfv  sdfsd "+dateString);
			    return dateString;
	}
	public  static String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
	   /*you can also use DateFormat reference instead of SimpleDateFormat 
	    * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	    */
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
	public static Date parse(String value, DateFormat... formatters) {
	    Date date = null;
	    for (DateFormat formatter : formatters) {
	      try {
	        date = formatter.parse(value);
	        break;
	      } catch (ParseException e) {
	      }
	    }
	    return date;
	  }
	public static String getDateAndTime(){
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd HH:mm:s");
	      return ft.format(dNow);
	}
	public static Date getTodayAsDate(){
	 return new Date();
	}
	public static String changeStringFormat(String dateFormat) throws ParseException{
		 SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		    Date date = format1.parse(dateFormat);
		    System.out.println(format2.format(date));
		    return format2.format(date);
	}
	public static String changeDatetoString(Date date){
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd");
	      return ft.format(date);
	}
	public static String changeStringToDate(String date) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date d1 = df.parse(date);
		return df.format(d1);
	}
	public static void main(String s[]) throws ParseException{
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  System.out.println(timestamp.getTime());
		  String loginToken = UUID.randomUUID().toString().replace("-", "");
			String shortToken = loginToken.substring(0, 15);
		System.out.println("adate "+shortToken);

	}
}
