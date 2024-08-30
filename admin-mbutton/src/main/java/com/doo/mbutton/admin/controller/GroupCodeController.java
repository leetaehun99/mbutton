package com.doo.mbutton.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.common.model.GroupCodeVo;
import com.doo.mbutton.common.service.GroupCodeService;
import com.doo.mbutton.management.model.UserVo;

@Controller
public class GroupCodeController {
	
	@Autowired
	private GroupCodeService groupCodeService;
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping
	public String selectGroupCodeList(Model model, @ModelAttribute GroupCodeVo groupCodeVo){
		
		List<GroupCodeVo> groupCodeList = groupCodeService.selectGroupCodeList(groupCodeVo);
		
		groupCodeVo.setReadType("count");
		groupCodeVo.setTotalCount(groupCodeService.selectGroupCodeList(groupCodeVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(groupCodeVo, "/groupCode/selectGroupCodeList.doo");

		model.addAttribute("groupCodeVo", groupCodeVo);
		model.addAttribute("groupCodeList", groupCodeList);
		
		return "/common/code/groupCode.default";
	}
	
	@RequestMapping(value = "/selectGroupCode.json")
	public void selectGroupCode(HttpServletResponse response,Model model,@ModelAttribute GroupCodeVo groupCodeVo){
		GroupCodeVo resultVo = groupCodeService.selectGroupCode(groupCodeVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/createGroupCode.json")
	public void createGroupCode(HttpServletRequest request,HttpServletResponse response,Model model, @ModelAttribute GroupCodeVo groupCodeVo ){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		groupCodeVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = groupCodeService.createGroupCode(groupCodeVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateGroupCode.json")
	public void updateGroupCode(HttpServletRequest request, HttpServletResponse response,@ModelAttribute GroupCodeVo groupCodeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		groupCodeVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = groupCodeService.updateGroupCode(groupCodeVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
}
