package com.doo.mbutton.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.common.model.CodeVo;
import com.doo.mbutton.common.service.CodeService;
import com.doo.mbutton.management.model.UserVo;

@Controller
public class CodeController {
	
	
	@Autowired
	private CodeService codeService;
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping
	public String selectCodeList(Model model, @ModelAttribute CodeVo codeVo,@RequestParam(value="cdLength") String cdLn){
		codeVo.setCdLn(cdLn);
		List<CodeVo> codelList = codeService.selectCodeList(codeVo);
		
		model.addAttribute("codeVo", codeVo);
		model.addAttribute("codeList", codelList);
		
		return "/common/code/code.default";
	}
	
	@RequestMapping(value = "/selectCodeList.json")
	public void selectCodeListJson(HttpServletRequest request,HttpServletResponse response,Model model, @ModelAttribute CodeVo codeVo){

		List<CodeVo> codelList = codeService.selectCodeList(codeVo);
		mapper.send(response, codelList);
	}
	
	@RequestMapping(value = "/selectCode.json")
	public void selectCode(HttpServletResponse response,Model model,@ModelAttribute CodeVo codeVo){
		CodeVo resultVo = codeService.selectCode(codeVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/createCode.json")
	public void createCode(HttpServletRequest request,HttpServletResponse response,Model model, @ModelAttribute CodeVo codeVo ){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		codeVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = codeService.createCode(codeVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateCode.json")
	public void updateCode(HttpServletRequest request, HttpServletResponse response,@ModelAttribute CodeVo codeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		codeVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = codeService.updateCode(codeVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
}
