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
import com.doo.mbutton.management.model.MedicalNotifyVo;
import com.doo.mbutton.management.model.TrtNotifyVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.TrtNotifyService;


@Controller
public class TrtNotifyController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private JsonMapper mapper;

	@Autowired
	private TrtNotifyService trtNotifyService;

	@RequestMapping
	public String selectTrtNotifyList(Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		notifyList(model,trtNotifyVo);
		PagingManager.setPagingInfo(trtNotifyVo, "/trtNotify/selectTrtNotifyList.doo");
		return "/management/trtNotify/selectTrtNotifyList.default";
	}
	
	private void notifyList(Model model,TrtNotifyVo trtNotifyVo){
		List<TrtNotifyVo> notifyList =  trtNotifyService.selectTrtNotifyList(trtNotifyVo);
		List<TrtNotifyVo> notifyGroupByList =  trtNotifyService.selectTrtNotifyGroupByList(trtNotifyVo);
		
		trtNotifyVo.setReadType("count");
		trtNotifyVo.setTotalCount(trtNotifyService.selectTrtNotifyList(trtNotifyVo).get(0).getTotalCount());
		model.addAttribute("trtNotifyVo", trtNotifyVo);
		model.addAttribute("notifyList", notifyList);
		model.addAttribute("notifyGroupByList", notifyGroupByList);
	}
	
	@RequestMapping
	public String selectTrtNotifyItemList(Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		notifyItemList(model,trtNotifyVo);
		PagingManager.setPagingInfo(trtNotifyVo, "/trtNotify/selectTrtNotifyItemList.doo");
		return "/management/trtNotify/selectTrtNotifyItemList.blank";
	}
	
	private void notifyItemList(Model model,TrtNotifyVo trtNotifyVo){
		List<TrtNotifyVo> notifyItemList =  trtNotifyService.selectTrtNotifyItemList(trtNotifyVo);
		
		trtNotifyVo.setReadType("count");
		trtNotifyVo.setTotalCount(trtNotifyService.selectTrtNotifyItemList(trtNotifyVo).get(0).getTotalCount());
		
		TrtNotifyVo notify =  trtNotifyService.selectTrtNotify(trtNotifyVo);
		model.addAttribute("title","["+notify.getTrtNotify()+"-"+notify.getTrtNotifySub()+"]"+notify.getNotifyNm()+"-"+notify.getNotifyMainNm());
		model.addAttribute("trtNotifyVo", trtNotifyVo);
		model.addAttribute("notify", notify);
		model.addAttribute("notifyItemList", notifyItemList);
	}
	
	@RequestMapping
	public String selectTrtNotify(Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		TrtNotifyVo notify =  trtNotifyService.selectTrtNotify(trtNotifyVo);
		
		model.addAttribute("notify", notify);
		model.addAttribute("trtNotifyVo", trtNotifyVo);
		return "/management/trtNotify/selectTrtNotify.default";
	}
	
	@RequestMapping
	public String selectTrtNotifyPop(Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		TrtNotifyVo notify =  trtNotifyService.selectTrtNotify(trtNotifyVo);
		
		model.addAttribute("notify", notify);
		model.addAttribute("trtNotifyVo", trtNotifyVo);
		return "/management/trtNotify/selectTrtNotify.blank";
	}
	
	@RequestMapping
	public String createTrtNotifyForm(Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		model.addAttribute("trtNotifyVo", trtNotifyVo);
		return "/management/trtNotify/createTrtNotify.default";
	}
	
	@RequestMapping
	public String createTrtNotify(HttpServletRequest request,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtNotifyVo.setRegisterId(sessionVo.getRegisterId());;
		
		model.addAttribute("notifyVo", trtNotifyVo);
		int result = 0;
		try{
			result = trtNotifyService.createTrtNotify(trtNotifyVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/trtNotify/selectTrtNotifyList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/trtNotify/createTrtNotify.default";
		}
		
		
	}

	@RequestMapping(value = "/updateTrtNotify.json")
	public void updateTrtNotify(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = trtNotifyService.updateTrtNotify(trtNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	@RequestMapping(value = "/createTrtNotifyItem.json")
	public void createTrtNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = trtNotifyService.createTrtNotifyItem(trtNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateTrtNotifyItem.json")
	public void updateTrtNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = trtNotifyService.updateTrtNotifyItem(trtNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateTrtNotifyuseYnItem.json")
	public void updateTrtNotifyuseYnItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtNotifyVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = trtNotifyService.updateTrtNotifyuseYnItem(trtNotifyVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectTrtNotifyItem.json")
	public void selectTrtNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		TrtNotifyVo notifyItem =  trtNotifyService.selectTrtNotifyItem(trtNotifyVo);
		model.addAttribute("notify", notifyItem);
		mapper.send(response, notifyItem);
	}
	
	@RequestMapping(value = "/deleteTrtNotifyItem.json")
	public void deleteTrtNotifyItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = trtNotifyService.deleteTrtNotifyItem(trtNotifyVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectTrtNotifyCnt.json")
	public void selectTrtNotifyCnt(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		List<String> list = null;
		try{
			list = trtNotifyService.selectTrtNotifyCnt(trtNotifyVo);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/deleteTrtNotify.json")
	public void deleteTrtNotify(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtNotifyVo trtNotifyVo){
		
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = trtNotifyService.deleteTrtNotify(trtNotifyVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
}
