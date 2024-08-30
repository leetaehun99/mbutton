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
import com.doo.mbutton.management.model.MedicalInsurVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.MedicalInsurService;

@Controller
public class MedicalInsurController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");

	@Autowired
	private JsonMapper mapper;

	@Autowired
	private MedicalInsurService medicalInsurService;

	@RequestMapping
	public String selectMedicalInsurList(Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		medicalInsurList(model,medicalInsurVo);
		PagingManager.setPagingInfo(medicalInsurVo, "/medicalInsur/selectMedicalInsurList.doo");
		return "/management/medicalInsur/selectMedicalInsurList.default";
	}
	
	private void medicalInsurList(Model model,MedicalInsurVo medicalInsurVo){
		List<MedicalInsurVo> medicalInsurList =  medicalInsurService.selectMedicalInsurList(medicalInsurVo);
		medicalInsurVo.setReadType("count");
		medicalInsurVo.setTotalCount(medicalInsurService.selectMedicalInsurList(medicalInsurVo).get(0).getTotalCount());
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		model.addAttribute("medicalInsurList", medicalInsurList);
	}
	
	@RequestMapping
	public String selectMedicalInsur(Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		
		MedicalInsurVo medicalInsur =  medicalInsurService.selectMedicalInsur(medicalInsurVo);
		
		model.addAttribute("medicalInsur", medicalInsur);
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		
		return "/management/medicalInsur/selectMedicalInsur.default";
	}
	
	@RequestMapping
	public String createMedicalInsurForm(Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		return "/management/medicalInsur/createMedicalInsur.default";
	}
	
	@RequestMapping
	public String createMedicalInsur(HttpServletRequest request,Model model,@ModelAttribute MedicalInsurVo medicalInsurVo) throws Exception{
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalInsurVo.setRegisterId(sessionVo.getRegisterId());
		
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		
		int result = 0;
		try{
			result = medicalInsurService.createMedicalInsur(medicalInsurVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/medicalInsur/selectMedicalInsurList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/medicalInsur/createMedicalInsur.default";
		}
	}

	@RequestMapping(value = "/updateMedicalInsur.json")
	public void updateMedicalInsur(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalInsurVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = medicalInsurService.updateMedicalInsur(medicalInsurVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}

	@RequestMapping
	public String selectInsurScreeningList(Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		insurScreeningList(model,medicalInsurVo);
		PagingManager.setPagingInfo(medicalInsurVo, "/medicalInsur/selectInsurScreeningList.doo");
		return "/management/medicalInsur/selectInsurScreeningList.default";
	}
	
	private void insurScreeningList(Model model,MedicalInsurVo medicalInsurVo){
		List<MedicalInsurVo> medicalInsurList =  medicalInsurService.selectInsurScreeningList(medicalInsurVo);
		medicalInsurVo.setReadType("count");
		medicalInsurVo.setTotalCount(medicalInsurService.selectInsurScreeningList(medicalInsurVo).get(0).getTotalCount());
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		model.addAttribute("medicalInsurList", medicalInsurList);
	}
	
	@RequestMapping
	public String selectInsurScreening(Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		
		MedicalInsurVo medicalInsur =  medicalInsurService.selectInsurScreening(medicalInsurVo);
		
		model.addAttribute("medicalInsur", medicalInsur);
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		
		return "/management/medicalInsur/selectInsurScreening.default";
	}
	
	@RequestMapping
	public String createInsurScreeningForm(Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		return "/management/medicalInsur/createInsurScreening.default";
	}
	
	@RequestMapping
	public String createInsurScreening(HttpServletRequest request,Model model,@ModelAttribute MedicalInsurVo medicalInsurVo) throws Exception{
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalInsurVo.setRegisterId(sessionVo.getRegisterId());
		
		model.addAttribute("medicalInsurVo", medicalInsurVo);
		
		int result = 0;
		try{
			result = medicalInsurService.createInsurScreening(medicalInsurVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/medicalInsur/selectInsurScreeningList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/medicalInsur/createInsurScreening.default";
		}
		
	}

	@RequestMapping(value = "/updateInsurScreening.json")
	public void updateInsurScreening(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalInsurVo medicalInsurVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalInsurVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = medicalInsurService.updateInsurScreening(medicalInsurVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
}