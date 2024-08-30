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

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MedicalInsurVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.DiseaService;

@Controller
public class DiseaController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private DiseaService diseaService;

	@Autowired
	private JsonMapper mapper;

	@Autowired
	private CommonUtil commonUtil;
	
	
	@RequestMapping
	public String selectDiseaList(Model model, @ModelAttribute DiseaVo diseaVo){
		diseaList(model, diseaVo);
		PagingManager.setPagingInfo(diseaVo, "/disea/selectDiseaList.doo");
		return "/management/disea/selectDiseaList.default";
	}
	
	private void diseaList(Model model, DiseaVo diseaVo){
		List<DiseaVo> diseaList = diseaService.selectDiseaList(diseaVo);
		diseaVo.setReadType("count");
		diseaVo.setTotalCount(diseaService.selectDiseaList(diseaVo).get(0).getTotalCount());
		model.addAttribute("diseaVo", diseaVo);
		model.addAttribute("diseaList", diseaList);
	}
	
	@RequestMapping
	public String selectDiseaPop(Model model, @ModelAttribute DiseaVo diseaVo){
		List<DrugVo> drugMappingList = diseaService.selectDiseaMappingList(diseaVo);
		model.addAttribute("diseaVo", diseaVo);
		model.addAttribute("drugMappingList", drugMappingList);
		return "/management/disea/selectDiseaPop.blank"; 
	}

	@RequestMapping
	public String selectDiseaInfo(Model model, @ModelAttribute DiseaVo diseaVo){
		List<DrugVo> drugMappingList = diseaService.selectDiseaMappingList(diseaVo);
		model.addAttribute("disea", diseaService.selectDiseaInfo(diseaVo));
		model.addAttribute("drugMappingList", drugMappingList);
		return "/management/disea/selectDiseaInfo.default";
	}

	@RequestMapping
	public String createDiseaInfo(Model model, @ModelAttribute DiseaVo diseaVo){
		return "/management/disea/createDisea.default";
	}

	@RequestMapping
	public String updateDiseaInfo(Model model, @ModelAttribute DiseaVo diseaVo){
		List<DrugVo> drugMappingList = diseaService.selectDiseaMappingList(diseaVo);
		model.addAttribute("disea", diseaService.selectDiseaInfo(diseaVo));
		model.addAttribute("drugMappingList", drugMappingList);
		return "/management/disea/updateDiseaInfo.default";
	}
	
	@RequestMapping
	public String createDisea(HttpServletRequest request,Model model,@ModelAttribute DiseaVo diseaVo) throws Exception{
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		
		diseaVo.setRegisterId(sessionVo.getUserId());
		int result = 0;
		try{
			 result = diseaService.createDisea(diseaVo);
			
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}

		if(result == 1) {
			return "redirect:/disea/selectDiseaList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/disea/createDisea.default";
		}
		
		 
		
	}
	
	@RequestMapping(value = "/createDiseaMapping.json")
	public void createDiseaMapping(HttpServletResponse response,Model model,@ModelAttribute DiseaVo diseaVo){
		List<DiseaVo> resultList = null;
		int resultValue = 0;
		try{
			List<String> list = commonUtil.arrayToList(diseaVo.getDiseaCd().split("\n"));
			diseaVo.setAllDiseaCd(list);
			resultList = diseaService.selectDiseaMappingListCnt(diseaVo);
			if(resultList.isEmpty()) {
			 resultValue = diseaService.createDiseaMapping(diseaVo);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		if(resultValue > 0) {
			mapper.send(response, resultValue);
		}else {
			mapper.send(response, resultList);
		}
	}
	
	@RequestMapping(value = "/createMedicalMapping.json")
	public void createMedicalMapping(HttpServletResponse response,Model model,@ModelAttribute DiseaVo diseaVo){
		List<DiseaVo> resultList = null;
		int resultValue = 0;
		try{
			 List<String> list = commonUtil.arrayToList(diseaVo.getDiseaCd().split("\n"));
			 diseaVo.setAllDiseaCd(list);
			 resultList = diseaService.selectMedicalMappingListCnt(diseaVo);
			 if(resultList.isEmpty()) {
				 resultValue = diseaService.createMedicalMapping(diseaVo);
			 }
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		if(resultValue > 0) {
			mapper.send(response, resultValue);
		}else {
			mapper.send(response, resultList);
		}
		
	}
	
	@RequestMapping(value = "/updateDisea.json")
	public void updateDisea(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DiseaVo diseaVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		diseaVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = diseaService.updateDisea(diseaVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}

	@RequestMapping(value = "/selectDiseaList.json")
	public void selectDiseaMappingList(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DiseaVo diseaVo){
		List<DiseaVo> diseaList = null;
		diseaVo.setDiseaCd(null);	// 쿼리 재사용성을 위하여 diseaCd id를 제거
		diseaVo.setSearchType("DISEA_CD");
		try{
			diseaVo.setReadType("count");
			int limit = diseaService.selectDiseaList(diseaVo).get(0).getTotalCount();
			if (limit > 100) {
				diseaList = null;	
			} else {
				diseaVo.setReadType("data");
				diseaVo.setRowPerPage(limit+1);
				diseaList = diseaService.selectDiseaList(diseaVo);
			}
			diseaList = diseaService.selectDiseaList(diseaVo);
		}catch(Exception e){
			diseaList = null;
			logger.error(e.getMessage());
		}
		mapper.send(response, diseaList);
	}
	
	@RequestMapping
	public String selectDrugPop(Model model, @ModelAttribute DrugVo drugVo) {
		List<DiseaVo> diseaMappingList = diseaService.selectDiseaMappingListByDrug(drugVo);

		model.addAttribute("drugVo", drugVo);
		model.addAttribute("diseaMappingList", diseaMappingList);
		return "/management/drug/selectDrugPop.blank";
	}
	
	@RequestMapping
	public String selectMedicalInsurPop(Model model, @ModelAttribute MedicalInsurVo medicalInsurVo) {
		List<DiseaVo> medicalMappingList = diseaService.selectMedicalMappingListByMedicalInsur(medicalInsurVo);

		model.addAttribute("medicalInsurVo", medicalInsurVo);
		model.addAttribute("medicalMappingList", medicalMappingList);
		return "/management/medicalInsur/selectMedicalInsurPop.blank";
	}
	
	@RequestMapping(value = "/deleteDiseaMapping.json")
	public void deleteDiseaMapping(Model model ,HttpServletRequest request,HttpServletResponse response,@ModelAttribute DiseaVo diseaVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = diseaService.deleteDiseaMapping(diseaVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/createDiseaMappingByDrugCd.json")
	public void createDiseaMappingByDrugCd(HttpServletResponse response,Model model,@ModelAttribute DrugVo drugVo){
		List<DiseaVo> resultList = null;
		int resultValue = 0;
		try{
			List<String> list = commonUtil.arrayToList(drugVo.getDrugCd().split("\n"));
			drugVo.setAllDrugCd(list);
			resultList = diseaService.selectDiseaMappingByDrugCdListCnt(drugVo);
			if(resultList.isEmpty()) {
			 resultValue = diseaService.createDiseaMappingByDrugCd(drugVo);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		if(resultValue > 0) {
			mapper.send(response, resultValue);
		}else {
			mapper.send(response, resultList);
		}
	}
	
	@RequestMapping(value = "/deleteMedicalMapping.json")
	public void deleteMedicalMapping(Model model ,HttpServletRequest request,HttpServletResponse response,@ModelAttribute DiseaVo diseaVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = diseaService.deleteMedicalMapping(diseaVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
}