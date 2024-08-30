package com.doo.mbutton.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.UserService;

@Controller
public class UserController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping(value = "/userInfo.json")
	public void userInfo(HttpServletRequest request, HttpServletResponse response){
		UserVo userVo = (UserVo)SessionManager.get(request, "USER");
		UserVo resultVo = userService.selectUser(userVo);
		mapper.send(response, resultVo);
	}
	
	
	
	@RequestMapping(value = "/duplicatCheck.json")
	public void duplicatCheck(HttpServletRequest request, HttpServletResponse response,@ModelAttribute UserVo userVo){		
		UserVo resultVo = userService.selectUser(userVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/updateUser.json")
	public void updateUser(HttpServletRequest request, HttpServletResponse response,@ModelAttribute UserVo userVo){		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		userVo.setUpdaterId(sessionVo.getUserId());
		if (!sessionVo.getMsgLev().equals(userVo.getMsgLev())) {
			sessionVo.setMsgLev(userVo.getMsgLev());
			SessionManager.put(request, "USER", sessionVo);
		}
		
		int result = userService.updateUser(userVo);
		mapper.send(response, result);
	}
}
