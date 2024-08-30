package com.doo.mbutton.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.MenuVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.MenuService;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JsonMapper mapper;
	@Autowired
	private CommonUtil commonUtil;
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
    
	ArrayList<String> checkUrl = new ArrayList<String>();	// 세션이 없어도 접근가능 한 URL 목록
	
	//@Value("#{applicationProp['SESSIONCHECK']}")
	//private String sessionCheck;
	
	// 메뉴 Service
	@Autowired
	MenuService menuService;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception  {
		
		String requestUrl = request.getRequestURI();

		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) ip = request.getRemoteAddr();
		
//		MDC.put("RemoteAddress", ip);
		
		String agent = request.getHeader("User-Agent");
		agent = commonUtil.getAgent(agent);
		
//		MDC.put("Agent",agent );
/*
		UserVo userVo = (UserVo)SessionManager.get(request, "USER");
		setMenuInfo(request, null, requestUrl,userVo);
		logger.info(requestUrl);
		// postHandle 에서 session check 를 하면 로직을 수행후에 팅겨내기 때문에 preHandle 에서 세션체크를 하도록 변경
		if (userVo == null && isSessionCheck(requestUrl)) {
			if(requestUrl.indexOf(".json") > 1 ){
				logger.error("PRE 세션정보 말료 JSON  ");
				throw new Exception(); 
				//request.getRequestDispatcher(request.getContextPath() + "/index/main.doo").forward(request, response);
				//return false;
			}else{
				logger.error("PRE 세션정보 말료 DOO  ");
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("index");
				modelAndView.addObject("alertMsg", "로그인이 필요한 페이지입니다.");
				modelAndView.addObject("redirectUrl", "/main.doo");
			}
		}
		*/
		paramPrint(request);
		return super.preHandle(request, response, handler);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

		// 세션에서 회원정보 조회
		UserVo userVo = (UserVo)SessionManager.get(request, "USER");
		String selectedMenu = "";
		String img = "";
		String requestUrl = request.getRequestURI();
		/*
		if(modelAndView == null) {
			//if( isSessionCheck(requestUrl) ) {
			//	if( userVo == null) {
			//		if(requestUrl.indexOf(".json") > 1 ){
			//			logger.error("POST 세션정보 말료 JSON  ");
			//			request.getRequestDispatcher(request.getContextPath() + "/index/main.doo").forward(request, response);
			//		}
			//	}
			//}
			return;
		}else{
			setMenuInfo(request, modelAndView,requestUrl,userVo);
		}
		
		if( isSessionCheck(requestUrl) ) {
			if( userVo != null && userVo.getUserId() != null ) {
				if(modelAndView != null) {
					ModelMap modelMap = modelAndView.getModelMap();
					modelMap.addAttribute("selectedMenu", selectedMenu);
					modelMap.addAttribute("img", img);
				}
			} else {
				
				logger.error("POST 세션정보 말료 DOO  ");
				modelAndView.setViewName("index");
				modelAndView.addObject("alertMsg", "로그인이 필요한 페이지입니다.");
				modelAndView.addObject("redirectUrl", "/main.doo");
				return;
			}
			
			if(modelAndView != null) {
				modelAndView.addObject("hMenuList", (List<MenuVo>)SessionManager.get(request, "hMenuList"));
			}
		}
		*/
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 현재 접근한 URL이 세션 체크해야하는 URL인지 검사
	 * @param url
	 * @return boolean
	 */
	public boolean isSessionCheck(String url) {
		boolean result = true;
		
		int length = checkUrl.size();
		
		for(int i = 0 ; i < length ; i++) {
			if( url.equals(checkUrl.get(i)) ) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * 메뉴 정보 세팅
	 * @param request
	 * @param modelAndView
	 */
	@SuppressWarnings("unchecked")
	private void setMenuInfo(HttpServletRequest request, ModelAndView modelAndView,String requestUrl,UserVo userVo) {
		MenuVo menuVo = new MenuVo();
		List<MenuVo> allMenuList = (List<MenuVo>)SessionManager.get(request, "allMenuList");
		List<MenuVo> hMenuList = (List<MenuVo>)SessionManager.get(request, "hMenuList");
		if(allMenuList == null || allMenuList.size() == 0) {
			allMenuList = menuService.selectAllMenuList(menuVo);
			SessionManager.put(request, "allMenuList", allMenuList);
		}
		
		if((hMenuList == null || hMenuList.size() == 0 )&& userVo != null) {
			hMenuList = new ArrayList<MenuVo>();
			for(int i=0; i<allMenuList.size(); i++){
				menuVo = allMenuList.get(i);
				if( Integer.parseInt(menuVo.getUserLevCd()) >= Integer.parseInt(userVo.getUserLevCd()) ) {
					//메뉴여부 확인
					if("Y".equals(menuVo.getMenuYn()) && "Y".equals(menuVo.getUseYn())){
						hMenuList.add(menuVo);
					}
					
					//로그인 체크 여부 확인
					if("N".equals(menuVo.getLoginNeedYn())){
						checkUrl.add(menuVo.getLinkUrl()); 
					}
					
					//등록된 url 확인
					if(requestUrl.equals(menuVo.getLinkUrl())) {
						if (modelAndView != null) {
							modelAndView.addObject("title", menuVo.getMenuNm());
							modelAndView.addObject("navigation", menuVo.getNavigation());
						}
					}
				}else continue;
				
			}
			SessionManager.put(request, "hMenuList", hMenuList);
		} else {
			for(int i=0; i<allMenuList.size(); i++){
				
				menuVo = allMenuList.get(i);
				
				//로그인 체크 여부 확인
				if("N".equals(menuVo.getLoginNeedYn())){
					checkUrl.add(menuVo.getLinkUrl()); 
				}
				
				//등록된 url 확인
				if(requestUrl.equals(menuVo.getLinkUrl())) {
					if (modelAndView != null) {
						modelAndView.addObject("title", menuVo.getMenuNm());
						modelAndView.addObject("navigation", menuVo.getNavigation());
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void paramPrint(HttpServletRequest request){
		Enumeration<Object> paramEnum = request.getParameterNames();
		logger.info("PARAMTER : {");
	    while(paramEnum.hasMoreElements()) {
	        String name = (String)paramEnum.nextElement();
	        String value ="";
	        String[] values= (String[])request.getParameterValues(name);
	        if(values.length>1){
	        	for(int i=0; i<values.length; i++){
	        		if(i<values.length-1) value+=values[i]+",";
	        		else value+=values[i];
	        	}
        		logger.info("KEY : " +name+"["+value+"]");
	        }else{
	        	logger.info("KEY : " +name+"["+request.getParameter(name)+"]");
	        }
	    }
	    logger.info("}");
	}
}
