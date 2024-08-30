package com.doo.mbutton.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.NotifyExtVo;
import com.doo.mbutton.management.model.NotifyKkVo;
import com.doo.mbutton.management.service.NotifyKkService;

@Controller
public class NotifyKkController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private NotifyKkService notifyKkService;

	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping
	public String selectNotifyKkItemList(Model model,@ModelAttribute NotifyKkVo notifyKkVo){
		notifyKkItemList(model,notifyKkVo);
		PagingManager.setPagingInfo(notifyKkVo, "/notifyKk/selectNotifyKkItemList.doo");
		return "/management/notifyKk/selectNotifyKkItemList.default";
	}
	
	private void notifyKkItemList(Model model,NotifyKkVo notifyKkVo){
		List<NotifyKkVo> kkList = notifyKkService.selectNotifyKkItemList(notifyKkVo);
		
		notifyKkVo.setReadType("count");
		notifyKkVo.setTotalCount(notifyKkService.selectNotifyKkItemList(notifyKkVo).get(0).getTotalCount());
		
		model.addAttribute("notifyKkVo", notifyKkVo);
		model.addAttribute("kkList", kkList);
	}

	@RequestMapping(value = "/deleteNotifyKk.json")
	public void deleteNotifyKk(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyKkVo notifyKkVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyKkService.deleteNotifyKk(notifyKkVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateNotifyKk.json")
	public void updateNotifyKk(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyKkVo notifyKkVo){
		int resultValue = 0;
		try{
			resultValue = notifyKkService.updateNotifyKk(notifyKkVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/insertNotifyKk.json")
	public void insertNotifyKk(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyKkVo notifyKkVo){
		int resultValue = 0;
		try{
			resultValue = notifyKkService.insertNotifyKk(notifyKkVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectNotifyKkCnt.json")
	public void selectNotifyKkCnt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		int resultValue = 0;
		try{
			resultValue = notifyKkService.selectNotifyKkCnt(notifyExtVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
}
