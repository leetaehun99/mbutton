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
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.DrugService;

@Controller
public class DrugController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private DrugService drugService;

	@Autowired
	private JsonMapper mapper;

	@RequestMapping
	public String selectDrugList(Model model, @ModelAttribute DrugVo drugVo) {
		drugList(model, drugVo);
		PagingManager.setPagingInfo(drugVo, "/drug/selectDrugList.doo");
		return "/management/drug/selectDrugList.default";
	}

	private void drugList(Model model, DrugVo drugVo) {
		List<DrugVo> drugList = drugService.selectDrugList(drugVo);

		drugVo.setReadType("count");
		drugVo.setTotalCount(drugService.selectDrugList(drugVo).get(0).getTotalCount());

		model.addAttribute("drugVo", drugVo);
		model.addAttribute("drugList", drugList);
	}

	@RequestMapping
	public String selectDrugInfo(Model model, @ModelAttribute DrugVo drugVo) {
		model.addAttribute("drug", drugService.selectDrugInfo(drugVo));
		return "/management/drug/selectDrugInfo.default";
	}

	@RequestMapping
	public String createDrugInfo(Model model, @ModelAttribute DrugVo drugVo) {
		return "/management/drug/createDrug.default";
	}

	@RequestMapping
	public String updateDrugInfo(Model model, @ModelAttribute DrugVo drugVo) {
		model.addAttribute("drug", drugService.selectDrugInfo(drugVo));
		return "/management/drug/updateDrugInfo.default";
	}

	@RequestMapping
	public String createDrug(HttpServletRequest request, Model model, @ModelAttribute DrugVo drugVo) throws Exception {
		UserVo sessionVo = (UserVo) SessionManager.get(request, "USER");
		drugVo.setRegisterId(sessionVo.getUserId());
		
		int result = 0;
		try{
			result = drugService.createDrug(drugVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/drug/selectDrugList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/drug/createDrug.default";
		}
		
	}

	@RequestMapping(value = "/updateDrug.json")
	public void updateDrug(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute DrugVo drugVo) {
		UserVo sessionVo = (UserVo) SessionManager.get(request, "USER");
		drugVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try {
			resultValue = drugService.updateDrug(drugVo);
		} catch (Exception e) {
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}

	@RequestMapping(value = "/updateCheckDrug.json")
	public void updateCheckDrug(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute DrugVo drugVo) {
		mapper.send(response, drugService.updateCheckDrug(drugVo));
	}	
}