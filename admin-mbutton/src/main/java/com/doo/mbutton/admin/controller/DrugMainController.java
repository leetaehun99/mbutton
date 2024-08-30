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
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.DrugMainService;

@Controller
public class DrugMainController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");

	@Autowired
	private JsonMapper mapper;

	@Autowired
	private DrugMainService drugMainService;

	@RequestMapping
	public String selectDrugMainList(Model model, @ModelAttribute DrugVo drugVo){
		drugList(model, drugVo);
		PagingManager.setPagingInfo(drugVo, "/drugMain/selectDrugMainList.doo");
		return "/management/drugMain/selectDrugMainList.default";
	}
	
	private void drugList(Model model, DrugVo drugVo){
		List<DrugVo> drugMainList = drugMainService.selectDrugMainList(drugVo);
		
		drugVo.setReadType("count");
		drugVo.setTotalCount(drugMainService.selectDrugMainList(drugVo).get(0).getTotalCount());
		
		model.addAttribute("drugMainVo", drugVo);
		model.addAttribute("drugMainList", drugMainList);
	}
	@RequestMapping
	public String selectDrugMain(Model model,DrugVo drugVo){
		
		DrugVo drugMain = drugMainService.selectDrugMain(drugVo);
		model.addAttribute("drugMainVo", drugVo);
		model.addAttribute("drugMain", drugMain);
		return "/management/drugMain/selectDrugMain.default";
	}
	
	@RequestMapping
	public String createDrugMainForm(Model model,@ModelAttribute DrugVo drugVo){
		
		model.addAttribute("drugMainVo", drugVo);
		return "/management/drugMain/createDrugMain.default";
	}
	
	@RequestMapping
	public String createDrugMain(HttpServletRequest request,Model model,@ModelAttribute DrugVo drugVo) throws Exception{
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		drugVo.setRegisterId(sessionVo.getRegisterId());
		model.addAttribute("drugMainVo", drugVo);
		
		int result = 0;
		try{
			result = drugMainService.createDrugMain(drugVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/drugMain/selectDrugMainList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/drugMain/createDrugMain.default";
		}
		
		
	}
	
	@RequestMapping(value = "/updateDrugMain.json")
	public void updateDrugMain(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugVo drugVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		drugVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = drugMainService.updateDrugMain(drugVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
}
