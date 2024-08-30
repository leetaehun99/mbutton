package com.doo.mbutton.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.doo.mbutton.common.helper.JsonMapper;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JsonMapper mapper;
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception  {
		
		paramPrint(request);
		return super.preHandle(request, response, handler);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
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
