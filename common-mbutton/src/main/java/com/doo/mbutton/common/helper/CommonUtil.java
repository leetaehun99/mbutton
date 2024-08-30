package com.doo.mbutton.common.helper;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.Model;

import com.doo.mbutton.common.model.GeneralVo;
import com.doo.mbutton.common.model.PagingVo;

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
	
	public void searchMode(Object object,Model model){
		PagingVo generalVo = new PagingVo();
		if("LIST".equals(((GeneralVo) object).getMode())){
			((PagingVo) object).setCurrentPage(((PagingVo) object).getS_currentPage());
			((PagingVo) object).setRowPerPage(((PagingVo) object).getS_rowPerPage());
			((PagingVo) object).setSearchType(((GeneralVo) object).getS_searchType());
			((PagingVo) object).setSearchText(((GeneralVo) object).getS_searchText());
			((PagingVo) object).setMode("");
			((PagingVo) object).setS_currentPage(1);
			((PagingVo) object).setS_rowPerPage(10);
			((PagingVo) object).setS_searchType("");
			((PagingVo) object).setS_searchText("");
		}else if("SUB".equals(((GeneralVo) object).getMode())){
			generalVo.setS_currentPage(((PagingVo) object).getCurrentPage());
			generalVo.setS_rowPerPage(((PagingVo) object).getRowPerPage());
			generalVo.setS_searchType(((GeneralVo) object).getSearchType());
			generalVo.setS_searchText(((GeneralVo) object).getSearchText());
			generalVo.setMode("");
			((PagingVo) object).setCurrentPage(1);
			((PagingVo) object).setRowPerPage(10);
			((PagingVo) object).setSearchType("");
			((PagingVo) object).setSearchText("");
			model.addAttribute("generalVo", generalVo);
			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' id='s_currentPage' name='s_currentPage' value='" + generalVo.getS_currentPage() + "'>");
			sb.append("<input type='hidden' id='s_rowPerPage' name='s_rowPerPage' value='" + generalVo.getS_rowPerPage() + "'>");
			sb.append("<input type='hidden' id='s_searchType' name='s_searchType' value='" + generalVo.getS_searchType() + "'>");
			sb.append("<input type='hidden' id='s_searchText' name='s_searchText' value='" + generalVo.getS_searchText() + "'>");
			sb.append("<input type='hidden' id='mode' name='mode' value=''>");
			model.addAttribute("subPagingParam", sb);
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' id='s_currentPage' name='s_currentPage' value='" + ((PagingVo) object).getS_currentPage() + "'>");
			sb.append("<input type='hidden' id='s_rowPerPage' name='s_rowPerPage' value='" + ((PagingVo) object).getS_rowPerPage() + "'>");
			sb.append("<input type='hidden' id='s_searchType' name='s_searchType' value='" + ((PagingVo) object).getS_searchType() + "'>");
			sb.append("<input type='hidden' id='s_searchText' name='s_searchText' value='" + ((PagingVo) object).getS_searchText() + "'>");
			sb.append("<input type='hidden' id='mode' name='mode' value=''>");
			model.addAttribute("subPagingParam", sb);
		}
	}
	/*public static void main(String[] args){
		CommonUtil c = new CommonUtil();
		String[] a = {"1_2_3","4_5_6","7_8_9"};
		Map<Integer, List<String>> resultMap = c.allDel(a);
		
		for(int i=0; i<3; i++){
			List<String> as = resultMap.get(i);
			for(int z=0; z<as.size(); z++){
				System.out.println("i = " + i + "  z = "+ z + "   " +as.get(z));
			}
		}
	}*/
	/**
	 * 체크박스 삭제 파라미터 세팅 
	 * @param String[]  {"1_2_3","4_5_6","7_8_9"} 구분자 "_"
	 * @return Map<Integer, List<String>>
	 */
	public Map<Integer, List<String>> allDel(String[] arrList) {
		
		List<String> r0 = new ArrayList<String>();
		List<String> r1 = new ArrayList<String>();
		List<String> r2 = new ArrayList<String>();
		List<String> r3 = new ArrayList<String>();
		List<String> r4 = new ArrayList<String>();
		
		Map<Integer, List<String>> resultMap = new HashMap<Integer,List<String>>();
		try {
			
			for(int x=0; x<arrList.length; x++){//배열 "1_2_3"
				String[] tempVal = arrList[x].split("_");
				if(!arrList[x].isEmpty()) {
					for(int y=0; y<tempVal.length; y++){
						if(y==0) r0.add(tempVal[y]); 
						else if(y==1) r1.add(tempVal[y]); 
						else if(y==2) r2.add(tempVal[y]); 
						else if(y==3) r3.add(tempVal[y]); 
						else if(y==4) r4.add(tempVal[y]);
					}
				}
			}
			
			resultMap.put(0, r0);
			resultMap.put(1, r1);
			resultMap.put(2, r2);
			resultMap.put(3, r3);
			resultMap.put(4, r4);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	public String getAgent(String param){

		String agent = param.toLowerCase();
		if (agent.indexOf("chrome") != -1) agent ="Chrome"; 
		if (agent.indexOf("opera") != -1) agent = "Opera"; 
		if (agent.indexOf("staroffice") != -1) agent = "Star Office"; 
		if (agent.indexOf("webtv") != -1) agent = "WebTV"; 
		if (agent.indexOf("beonex") != -1) agent = "Beonex"; 
		if (agent.indexOf("chimera") != -1) agent = "Chimera"; 
		if (agent.indexOf("netpositive") != -1) agent = "NetPositive"; 
		if (agent.indexOf("phoenix") != -1) agent = "Phoenix"; 
		if (agent.indexOf("firefox") != -1) agent = "Firefox"; 
		if (agent.indexOf("safari") != -1) agent = "Safari"; 
		if (agent.indexOf("skipstone") != -1) agent = "SkipStone"; 
		if (agent.indexOf("trident/7.0") != -1) agent = "Internet Explorer 11"; 
		if (agent.indexOf("trident/6.0") != -1) agent = "Internet Explorer 10"; 
		if (agent.indexOf("trident/5.0") != -1) agent = "Internet Explorer 9"; 
		if (agent.indexOf("trident/4.0") != -1) agent = "Internet Explorer 8"; 
		if (agent.indexOf("msie 7.0") != -1) agent = "Internet Explorer 7"; 
		if (agent.indexOf("msie 6.0") != -1) agent = "Internet Explorer 6"; 
		if (agent.indexOf("netscape") != -1) agent = "Netscape"; 
		
		return agent;
	}

	public String getClientIp(HttpServletRequest request) {
		String clientIp = request.getHeader("X-FORWARDED-FOR"); 
	    
	    if (clientIp == null || clientIp.length() == 0) {
	        clientIp = request.getHeader("Proxy-Client-IP");
	    }
	
	    if (clientIp == null || clientIp.length() == 0) {
	        clientIp = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
	    }
	
	    if (clientIp == null || clientIp.length() == 0) {
	        clientIp = request.getRemoteAddr() ;
	    }
	    return clientIp;
	}

	public List<String> arrayToList(String[] array){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<array.length; i++){
			list.add(array[i]);			
		}
		return list;
	}
}
