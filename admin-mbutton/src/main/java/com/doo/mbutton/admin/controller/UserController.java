package com.doo.mbutton.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.UserService;

@Controller
public class UserController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private UserService userService;
	
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping
	public String selectUserList(Model model,@ModelAttribute UserVo userVo, HttpServletRequest request){
		List<UserVo> userList = userService.selectUserList(userVo);

		userVo.setReadType("count");
		userVo.setTotalCount(userService.selectUserList(userVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(userVo, "/user/selectUserList.doo");

		model.addAttribute("userVo", userVo);
		model.addAttribute("userList", userList);
		return "/management/user/selectUserList.default";
	}
	
	@RequestMapping
	public String selectUser(Model model,@ModelAttribute UserVo userVo, HttpServletRequest request){
		model.addAttribute("user", userService.selectUser(userVo));
		model.addAttribute("userVo", userVo);
		return "/management/user/selectUser.default";
	}
	
	@RequestMapping(value = "/userInfo.json")
	public void userInfo(HttpServletRequest request, HttpServletResponse response){
		UserVo userVo = (UserVo)SessionManager.get(request, "USER");
		UserVo resultVo = userService.selectUser(userVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping
	public String userInfo(Model model,@ModelAttribute UserVo userVo){
		model.addAttribute("user", userService.selectUser(userVo));
		model.addAttribute("userVo", userVo);
		return "/management/user/userInfo.default";
	}
	
	@RequestMapping
	public String createUserForm(){
		return "/management/user/createUser.default";
	}
	
	@RequestMapping(value = "/duplicatCheck.json")
	public void duplicatCheck(HttpServletRequest request, HttpServletResponse response,@ModelAttribute UserVo userVo){		
		UserVo resultVo = userService.selectUser(userVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping
	public String createUser(HttpServletRequest request,Model model,@ModelAttribute UserVo userVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		userVo.setRegisterId(sessionVo.getUserId());
		int result = 0;
		try{
			result = userService.createUser(userVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result >=1) return "redirect:/user/selectUserList.doo";
		else return "redirect:/user/createUserForm.doo";
	}
	
	@RequestMapping
	public String updateUser(HttpServletRequest request,Model model,@ModelAttribute UserVo userVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		userVo.setUpdaterId(sessionVo.getUserId());
		int result = userService.updateUser(userVo);
		if(result >=1) return "redirect:/user/selectUserList.doo";
		else return "redirect:/user/selectUser.doo";
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
	
	@RequestMapping(value = "/deleteUser.json")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response,@ModelAttribute UserVo userVo){		
		int resultValue = 0;
		try {
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = userService.deleteUser(userVo, historyLogVo);
		}catch(Exception e) {
			resultValue = 0;
			logger.error(e.getMessage());
		}

		mapper.send(response, resultValue);
	}
}
