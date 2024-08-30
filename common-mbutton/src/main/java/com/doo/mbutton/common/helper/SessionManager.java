package com.doo.mbutton.common.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

	/**
	 * Session 조회
	 * @param request
	 * @param key
	 * @return Object
	 */
	public static Object get(HttpServletRequest request, String key) {
		
		Object obj = null;
		HttpSession session = null;
		
		session = request.getSession();
			
		if(session == null) {
			return null;
		}
			
		obj = (Object)session.getAttribute(key);
		return obj;
	}
	
	/**
	 * Session 등록
	 * @param request
	 * @param key
	 * @param obj
	 */
	public static void put(HttpServletRequest request, String key, Object obj) {

		HttpSession session = null;
		session = request.getSession();
		session.setAttribute(key, obj);
	}
	
	/**
	 * Session 초기화
	 * @param request
	 */
	public static void invalidate(HttpServletRequest request) {

		HttpSession session = null;
		
		session = request.getSession();
			
		if(session != null) {
			session.invalidate();
		}
	
	}	
	
}
