package com.doo.mbutton.admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.common.service.IndexService;
import com.doo.mbutton.management.model.UserVo;

@Controller
public class IndexController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private IndexService indexService;
	
/*	@RequestMapping(value = {"/index.doo"}, method = RequestMethod.GET)
	public String index(){
		return "index";
	}*/
	
	@RequestMapping(value = {"/","/main.doo"})
	public String main(){
		return "/management/index/main.default";
	}
	
	@RequestMapping(value = "/login.json")
	public void login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserVo userVo){
		logger.info(request.getRequestURL());
		userVo.setMode("ADMIN");
		UserVo resultVo =  indexService.loginCheck(request, userVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping
	public String logout(Model model, HttpServletRequest request) {
		SessionManager.invalidate(request);
		model.addAttribute("alertMsg", "로그아웃 되었습니다.");
		return "/management/index/main.default";
	}
	
	@RequestMapping
	public String createUserForm(){
		return "/management/user/createUserPopup.blank";
	}
	
	@RequestMapping(value = "/createUser.json")
	public void createUser(Model model,@ModelAttribute UserVo userVo, HttpServletResponse response){
		int result = indexService.createUser(userVo);
		mapper.send(response, result);
	}
	
	
}
