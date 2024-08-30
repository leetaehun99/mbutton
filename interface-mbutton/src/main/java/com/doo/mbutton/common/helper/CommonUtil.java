package com.doo.mbutton.common.helper;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class CommonUtil {
	
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	// cUtil
	private static CommonUtil cUtil = null;
	
	/**
	 * Constructor
	 */
	private CommonUtil() {
		
	}
	
	/**
	 * getInstance
	 * @return CommonUtil
	 */
	public static synchronized CommonUtil getInstance() {
		if( cUtil == null ) {
			cUtil = new CommonUtil();
		}
		return cUtil;
	}
	
	/**
	 * Object -> JSON String
	 * @param obj
	 * @return String
	 */
	public String convertObjectToJson(Object obj) {
		try {
			if(obj != null) {
				ObjectMapper om = new ObjectMapper();
				String convertMapToJson = om.defaultPrettyPrintingWriter().writeValueAsString(obj);
				return convertMapToJson;
			}
			
		} catch(JsonGenerationException jge) {
			logger.error(jge.getMessage(), jge);
			
		} catch(JsonMappingException jme) {
			logger.error(jme.getMessage(), jme);
			
		} catch(IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
			
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 현재 시간 기준으로 지정한 포맷에 맞게 리턴.
	 * @param format
	 * @return String
	 */
	public String getFormatDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		Date currDate = new Date();
		String formatDate = sdf.format(currDate);
		return formatDate;
	}
	
	/**
	 * 날짜 더하기
	 * 
	 * @param date 기준이 되는 날짜
	 * @param format SimpleDateFormat에 해당하는 날짜 형식
	 * 							date 파라미터와 형식이 일치 해야 함.
	 * 							Ex) yyyy-MM-dd HH:mm:ss
	 * @param type 더하는 날짜 Type
	 * 						(년도 : YEAR, 달 : MONTH, 일 : DAY)
	 * 						대소문자 구분 안함
	 * @param value 더할 값 + - int 값
	 * @return String
	 */
	public String calcDate(String date, String format, String type, int value) {

		String dateStr = "";
		Calendar cal = null;
		cal = getCalendar(date, format);

		String typeValue = type.toUpperCase();
		
		if (typeValue.equals("YEAR")) {
			dateStr = addYear(cal, format, value);
		} else if (typeValue.equals("MONTH")) {
			dateStr = addMonth(cal, format, value);
		} else if (typeValue.equals("DAY")) {
			dateStr = addDay(cal, format, value);
		} else if (typeValue.equals("HOUR")) {
			dateStr = addHour(cal, format, value);
		}

		return dateStr;

	}
	
	/**
	 * 해당 Format에 해당하는 Calendar 객체 가져오기
	 * @param date
	 * @param format
	 * @return Calendar
	 */
	public Calendar getCalendar(String date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = sdf.parse(date, new ParsePosition(0));
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		return cal;

	}
	
	/**
	 * 년도 더하기
	 * @param cal
	 * @param format
	 * @param year
	 * @return String
	 */
	public String addYear(Calendar cal, String format, int year) {

		cal.add(Calendar.YEAR, year);
		String returnString = setDateFormat(cal, format);

		return returnString;

	}
	
	/**
	 * 월 더하기
	 * @param cal
	 * @param format
	 * @param month
	 * @return String
	 */
	public String addMonth(Calendar cal, String format, int month) {

		cal.add(Calendar.MONTH, month);
		String returnString = setDateFormat(cal, format);

		return returnString;

	}
	
	/**
	 * 날짜 더하기
	 * @param cal
	 * @param format
	 * @param day
	 * @return String
	 */
	public String addDay(Calendar cal, String format, int day) {

		cal.add(Calendar.DATE, day);
		String returnString = setDateFormat(cal, format);

		return returnString;

	}
	
	/**
	 * 시간 더하기
	 * @param cal
	 * @param format
	 * @param hour
	 * @return String
	 */
	public String addHour(Calendar cal, String format, int hour) {

		cal.add(Calendar.HOUR, hour);
		String returnString = setDateFormat(cal, format);

		return returnString;

	}
	
	/**
	 * 해당하는 날짜 Format에 대한 날짜 가져오기
	 * @param cal
	 * @param format
	 * @return String
	 */
	public String setDateFormat(Calendar cal, String format) {

		String dateStr = "";
		SimpleDateFormat dFormat = null;
		Date d = null;

		try {
			d = cal.getTime();
			dFormat = new SimpleDateFormat(format);
			dateStr = dFormat.format(d);
		} catch (Exception e) {
			logger.error("setDateFormat Error :: " + e.getMessage(), e);
		}

		return dateStr;
	}
	/*
	 * 요일구하기
	 * 1:일요일,2:월요일,3:화요일,4:수요일,5:목요일,6:금요일,7:토요일
	 */
	public String[][] getDayOfWeek(String day,String format){
		int week = getCalendar(day,format).get(Calendar.DAY_OF_WEEK);
		int tempWeek = week;
		int lastDay =getCalendar(day,format).getActualMaximum(Calendar.DAY_OF_MONTH);
		
		String[][] days=new String[lastDay+week][2];
		for(int i=0; i<week; i++){
			days[i][1]="";
			if(i%7 == 1 ){
				days[i][0]="SUN";
			}else if(i%7 ==2 ){
				days[i][0]="MON";
			}else if(i%7 ==3 ){
				days[i][0]="TUE";
			}else if(i%7 ==4 ){
				days[i][0]="WEN";
			}else if(i%7 ==5 ){
				days[i][0]="THU";
			}else if(i%7 ==6 ){
				days[i][0]="FRI";
			}else if(i%7 ==0 ){
				days[i][0]="SAT";
			}else{
				days[i][0]="NON";
			}
		}
		for(int i=week; i<days.length; i++){
			days[i][1]=i-(tempWeek-1)+"";
			if(week%7 == 1 ){
				days[i][0]="SUN";
			}else if(week%7 ==2 ){
				days[i][0]="MON";
			}else if(week%7 ==3 ){
				days[i][0]="TUE";
			}else if(week%7 ==4 ){
				days[i][0]="WEN";
			}else if(week%7 ==5 ){
				days[i][0]="THU";
			}else if(week%7 ==6 ){
				days[i][0]="FRI";
			}else if(week%7 ==0 ){
				days[i][0]="SAT";
			}else{
				days[i][0]="NON";
			}
			week++;
		}
		return days;
	}
}
