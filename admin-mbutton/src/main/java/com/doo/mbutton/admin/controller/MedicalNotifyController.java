package com.doo.mbutton.admin.controller;

import java.net.InetAddress;
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
import com.doo.mbutton.management.model.MedicalMsgVo;
import com.doo.mbutton.management.model.MedicalNotifyVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.MedicalNotifyService;


@Controller
public class MedicalNotifyController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private JsonMapper mapper;

	@Autowired
	private MedicalNotifyService medicalNotifyService;
	
	@RequestMapping
	public String selectMedicalNotifyList(Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		notifyList(model,medicalNotifyVo);
		PagingManager.setPagingInfo(medicalNotifyVo, "/medicalNotify/selectMedicalNotifyList.doo");
		return "/management/medicalNotify/selectMedicalNotifyList.default";
	}
	
	private void notifyList(Model model,MedicalNotifyVo medicalNotifyVo){
		List<MedicalNotifyVo> notifyList =  medicalNotifyService.selectMedicalNotifyList(medicalNotifyVo);
		//List<MedicalNotifyVo> notifyGroupByList =  medicalNotifyService.selectMedicalNotifyGroupByList(medicalNotifyVo);
		
		medicalNotifyVo.setReadType("count");
		medicalNotifyVo.setTotalCount(medicalNotifyService.selectMedicalNotifyList(medicalNotifyVo).get(0).getTotalCount());
		model.addAttribute("medicalNotifyVo", medicalNotifyVo);
		model.addAttribute("notifyList", notifyList);
		//model.addAttribute("notifyGroupByList", notifyGroupByList);
	}
	
	@RequestMapping
	public String selectMedicalNotifyItemList(Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		notifyItemList(model,medicalNotifyVo);
		PagingManager.setPagingInfo(medicalNotifyVo, "/medicalNotify/selectMedicalNotifyItemList.doo");
		return "/management/medicalNotify/selectMedicalNotifyItemList.blank";
	}
	
	private void notifyItemList(Model model,MedicalNotifyVo medicalNotifyVo){
		List<MedicalNotifyVo> notifyItemList =  medicalNotifyService.selectMedicalNotifyItemList(medicalNotifyVo);
		
		medicalNotifyVo.setReadType("count");
		medicalNotifyVo.setTotalCount(medicalNotifyService.selectMedicalNotifyItemList(medicalNotifyVo).get(0).getTotalCount());
		
		MedicalNotifyVo notify =  medicalNotifyService.selectMedicalNotify(medicalNotifyVo);
		model.addAttribute("title","["+notify.getMedicalNotify()+"-"+notify.getMedicalNotifySub()+"]"+notify.getNotifyNm()+"-"+notify.getNotifyMainNm());
		model.addAttribute("medicalNotifyVo", medicalNotifyVo);
		model.addAttribute("notify", notify);
		model.addAttribute("notifyItemList", notifyItemList);
	}
	
	@RequestMapping
	public String selectMedicalNotify(Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		MedicalNotifyVo notify =  medicalNotifyService.selectMedicalNotify(medicalNotifyVo);
		
		model.addAttribute("notify", notify);
		model.addAttribute("medicalNotifyVo", medicalNotifyVo);
		return "/management/medicalNotify/selectMedicalNotify.default";
	}
	
	@RequestMapping
	public String selectMedicalNotifyPop(Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		MedicalNotifyVo notify =  medicalNotifyService.selectMedicalNotify(medicalNotifyVo);
		
		model.addAttribute("notify", notify);
		model.addAttribute("medicalNotifyVo", medicalNotifyVo);
		return "/management/medicalNotify/selectMedicalNotify.blank";
	}
	
	@RequestMapping
	public String createMedicalNotifyForm(Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		model.addAttribute("medicalNotifyVo", medicalNotifyVo);
		return "/management/medicalNotify/createMedicalNotify.default";
	}
	
	@RequestMapping
	public String createMedicalNotify(HttpServletRequest request,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo) throws Exception{
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalNotifyVo.setRegisterId(sessionVo.getRegisterId());
 
		model.addAttribute("notifyVo", medicalNotifyVo);
		int result = 0;
		try{
			result = medicalNotifyService.createMedicalNotify(medicalNotifyVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/medicalNotify/selectMedicalNotifyList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/medicalNotify/createMedicalNotify.default";
		}
		
	}

	@RequestMapping(value = "/updateMedicalNotify.json")
	public void updateMedicalNotify(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = medicalNotifyService.updateMedicalNotify(medicalNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	@RequestMapping(value = "/createMedicalNotifyItem.json")
	public void createMedicalNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = medicalNotifyService.createMedicalNotifyItem(medicalNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateMedicalNotifyItem.json")
	public void updateMedicalNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = medicalNotifyService.updateMedicalNotifyItem(medicalNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateMedicalNotifyuseYnItem.json")
	public void updateMedicalNotifyuseYnItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = medicalNotifyService.updateMedicalNotifyuseYnItem(medicalNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectMedicalNotifyItem.json")
	public void selectMedicalNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		MedicalNotifyVo notifyItem =  medicalNotifyService.selectMedicalNotifyItem(medicalNotifyVo);
		model.addAttribute("notify", notifyItem);
		mapper.send(response, notifyItem);
	}
	
	@RequestMapping(value = "/deleteMedicalMsg.json")
	public void deleteMedicalMsg(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalMsgVo medicalMsgVo){
		
		int resultValue = 0;
		try{
			resultValue = medicalNotifyService.deleteMedicalMsg(medicalMsgVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/deleteMedicalNotifyItem.json")
	public void deleteMedicalNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = medicalNotifyService.deleteMedicalNotifyItem(medicalNotifyVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectMedicalNotifyCnt.json")
	public void selectMedicalNotifyCnt(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		List<String> list = null;
		try{
			list = medicalNotifyService.selectMedicalNotifyCnt(medicalNotifyVo);
		}catch(Exception e){

			logger.error(e.getMessage());
		}
		
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/deleteMedicalNotify.json")
	public void deleteMedicalNotify(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute MedicalNotifyVo medicalNotifyVo){
		
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = medicalNotifyService.deleteMedicalNotify(medicalNotifyVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
}
