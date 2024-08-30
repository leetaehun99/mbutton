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
import com.doo.mbutton.management.model.DrugMsgVo;
import com.doo.mbutton.management.model.DrugNotifyVo;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.DrugNotifyService;


@Controller
public class DrugNotifyController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");

	@Autowired
	private JsonMapper mapper;

	@Autowired
	private DrugNotifyService drugNotifyService;

	@RequestMapping
	public String selectDrugNotifyList(Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		drugNotifyList(model,drugNotifyVo);
		PagingManager.setPagingInfo(drugNotifyVo, "/drugNotify/selectDrugNotifyList.doo");
		return "/management/drugNotify/selectDrugNotifyList.default";
	}
	
	private void drugNotifyList(Model model,DrugNotifyVo drugNotifyVo){
		List<DrugNotifyVo> drugNotifyList =  drugNotifyService.selectDrugNotifyList(drugNotifyVo);
		List<DrugNotifyVo> drugNotifyGroupByList =  drugNotifyService.selectDrugNotifyGroupByList(drugNotifyVo);
		
		drugNotifyVo.setReadType("count");
		drugNotifyVo.setTotalCount(drugNotifyService.selectDrugNotifyList(drugNotifyVo).get(0).getTotalCount());
		model.addAttribute("drugNotifyVo", drugNotifyVo);
		model.addAttribute("drugNotifyList", drugNotifyList);
		model.addAttribute("drugNotifyGroupByList", drugNotifyGroupByList);

	}
	
	@RequestMapping
	public String selectDrugNotifyItemList(Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		drugNotifyItemList(model,drugNotifyVo);
		drugNotifyVo.setCdDivd("1");//약가
		PagingManager.setPagingInfo(drugNotifyVo, "/drugNotify/selectDrugNotifyItemList.doo");
		return "/management/drugNotify/selectDrugNotifyItemList.blank";
	}
	
	private void drugNotifyItemList(Model model,DrugNotifyVo drugNotifyVo){
		List<DrugNotifyVo> drugNotifyItemList =  drugNotifyService.selectDrugNotifyItemList(drugNotifyVo);
		
		drugNotifyVo.setReadType("count");
		drugNotifyVo.setTotalCount(drugNotifyService.selectDrugNotifyItemList(drugNotifyVo).get(0).getTotalCount());
		
		DrugNotifyVo drugNotify =  drugNotifyService.selectDrugNotify(drugNotifyVo);
		model.addAttribute("title","["+drugNotify.getDrugNotify()+"-"+drugNotify.getDrugNotifySub()+"]"+drugNotify.getDrugNotifyNm()+"-"+drugNotify.getDrugNotifyMainNm()+"("+drugNotify.getDrugNotifyItem()+")");
		model.addAttribute("drugNotifyVo", drugNotifyVo);
		model.addAttribute("drugNotify", drugNotify);
		model.addAttribute("drugNotifyItemList", drugNotifyItemList);
	}
	
	@RequestMapping
	public String selectDrugNotify(Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		DrugNotifyVo drugNotify =  drugNotifyService.selectDrugNotify(drugNotifyVo);
		
		model.addAttribute("drugNotify", drugNotify);
		model.addAttribute("drugNotifyVo", drugNotifyVo);
		return "/management/drugNotify/selectDrugNotify.default";
	}
	
	@RequestMapping
	public String selectDrugNotifyPop(Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		DrugNotifyVo drugNotify =  drugNotifyService.selectDrugNotify(drugNotifyVo);
		
		model.addAttribute("drugNotify", drugNotify);
		model.addAttribute("drugNotifyVo", drugNotifyVo);
		return "/management/drugNotify/selectDrugNotify.blank";
	}
	
	@RequestMapping
	public String createDrugNotifyForm(Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		model.addAttribute("drugNotifyVo", drugNotifyVo);
		return "/management/drugNotify/createDrugNotify.default";
	}
	
	@RequestMapping
	public String createDrugNotify(HttpServletRequest request,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo) throws Exception{
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");drugNotifyVo.setRegisterId(sessionVo.getRegisterId());
		
		model.addAttribute("drugNotifyVo", drugNotifyVo);
		
		int result = 0;
		try{
			result = drugNotifyService.createDrugNotify(drugNotifyVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/drugNotify/selectDrugNotifyList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/drugNotify/createDrugNotify.default";
		}
		
		
	}

	@RequestMapping(value = "/updateDrugNotify.json")
	public void updateDrugNotify(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		drugNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = drugNotifyService.updateDrugNotify(drugNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	@RequestMapping(value = "/createDrugNotifyItem.json")
	public void createDrugNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		drugNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = drugNotifyService.createDrugNotifyItem(drugNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateDrugNotifyItem.json")
	public void updateDrugNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		drugNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = drugNotifyService.updateDrugNotifyItem(drugNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateDrugNotifyuseYnItem.json")
	public void updateDrugNotifyuseYnItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		drugNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = drugNotifyService.updateDrugNotifyuseYnItem(drugNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectDrugNotifyItem.json")
	public void selectDrugNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		DrugNotifyVo drugNotifyItem =  drugNotifyService.selectDrugNotifyItem(drugNotifyVo);
		model.addAttribute("drugNotify", drugNotifyItem);
		mapper.send(response, drugNotifyItem);
	}
	
	@RequestMapping(value = "/deleteDrugMsg.json")
	public void deleteDrugMsg(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugMsgVo drugMsgVo){
		
		int resultValue = 0;
		try{
			resultValue = drugNotifyService.deleteDrugMsg(drugMsgVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/deleteDrugNotifyItem.json")
	public void deleteDrugNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = drugNotifyService.deleteDrugNotifyItem(drugNotifyVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectDrugNotifyCnt.json")
	public void selectDrugNotifyCnt(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		List<String> list = null;
		try{
			list = drugNotifyService.selectDrugNotifyCnt(drugNotifyVo);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/deleteDrugNotify.json")
	public void deleteDrugNotify(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute DrugNotifyVo drugNotifyVo){
		
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = drugNotifyService.deleteDrugNotify(drugNotifyVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
}
