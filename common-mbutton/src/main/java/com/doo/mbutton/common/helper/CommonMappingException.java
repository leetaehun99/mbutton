package com.doo.mbutton.common.helper;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CommonMappingException extends SimpleMappingExceptionResolver{
	
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	
	public CommonMappingException(){
		super();
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
		logger.error(ex.getMessage(),ex);
		return super.resolveException(request, response, handler, ex);
	}
}
