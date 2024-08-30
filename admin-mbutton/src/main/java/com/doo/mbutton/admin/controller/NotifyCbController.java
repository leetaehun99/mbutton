package com.doo.mbutton.admin.controller;


import java.util.ArrayList;
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
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MsgVo;
import com.doo.mbutton.management.model.NotifyCbVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.MsgService;
import com.doo.mbutton.management.service.NotifyCbService;

@Controller
public class NotifyCbController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private NotifyCbService notifyCbService;

	@Autowired
	private JsonMapper mapper;

	@Autowired
	private MsgService msgService;
	
	@RequestMapping
	public String selectNotifyCbList(Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		notifyCbList(model,notifyCbVo);
		PagingManager.setPagingInfo(notifyCbVo, "/notifyCb/selectNotifyCbList.doo");
		return "/management/notifyCb/selectNotifyCbList.default";
	}
	
	private void notifyCbList(Model model,NotifyCbVo notifyCbVo){
		List<NotifyCbVo> cbList =  notifyCbService.selectNotifyCbList(notifyCbVo);
		
		notifyCbVo.setReadType("count");
		notifyCbVo.setTotalCount(notifyCbService.selectNotifyCbList(notifyCbVo).get(0).getTotalCount());
		model.addAttribute("notifyCbVo", notifyCbVo);
		model.addAttribute("cbList", cbList);
	}
	
	@RequestMapping
	public String selectNotifyCb(Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		NotifyCbVo notifyCb =  notifyCbService.selectNotifyCb(notifyCbVo);
		model.addAttribute("msgList", setMsgList());
		model.addAttribute("notifyCb", notifyCb);
		model.addAttribute("notifyCbVo", notifyCbVo);
		return "/management/notifyCb/selectNotifyCb.default";
	}
	
	@RequestMapping
	public String createNotifyCbForm(Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		model.addAttribute("msgList", setMsgList());
		model.addAttribute("notifyCbVo", notifyCbVo);
		return "/management/notifyCb/createNotifyCb.default";
	}
	
	@RequestMapping
	public String selectNotifyCbItemList(Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		notifyCbItemList(model,notifyCbVo);
		PagingManager.setPagingInfo(notifyCbVo, "/notifyCb/selectNotifyCbItemList.doo");
		return "/management/notifyCb/selectNotifyCbItemList.blank";
	}
	
	@RequestMapping
	public String createNotifyCbItemForm(Model model,@ModelAttribute NotifyCbVo notifyCbVo){

		List<NotifyCbVo> cbList1 = notifyCbService.selectNotifyCbItemList1(notifyCbVo);
		List<NotifyCbVo> cbList2 = notifyCbService.selectNotifyCbItemList2(notifyCbVo);
		
		model.addAttribute("notifyCbVo", notifyCbVo);
		model.addAttribute("cbList1", cbList1);
		model.addAttribute("cbList2", cbList2);
		
		return "/management/notifyCb/createNotifyCbItem.blank";
	}
	
	@RequestMapping(value = "/selectNotifyCbItemChk.json")
	public void selectNotifyCbItemChk(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){

		String[] temp = notifyCbVo.getMainDrugCd1().split("\n");
		
		List<String> list = new ArrayList<String>(); 
				
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i].trim());
			}
		}
		
		notifyCbVo.setMainDrugCdList1(list);
		
		temp = notifyCbVo.getMainDrugCd2().split("\n");
		
		list = new ArrayList<String>(); 
				
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i].trim());
			}
		}
		
		notifyCbVo.setMainDrugCdList2(list);
		mapper.send(response, notifyCbService.selectNotifyCbItemChk(notifyCbVo));
	}
	
	@RequestMapping(value = "/createNotifyCbItem.json")
	public void createNotifyCbItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){

		String[] temp = notifyCbVo.getMainDrugCd1().split("\n");
		
		List<String> list = new ArrayList<String>(); 
				
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i].trim());
			}
		}
		
		notifyCbVo.setMainDrugCdList1(list);
		
		temp = notifyCbVo.getMainDrugCd2().split("\n");
		
		list = new ArrayList<String>(); 
				
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i].trim());
			}
		}
		
		notifyCbVo.setMainDrugCdList2(list);
		mapper.send(response, notifyCbService.createNotifyCbItem(notifyCbVo));
	}
	
	private void notifyCbItemList(Model model,NotifyCbVo notifyCbVo){
		List<NotifyCbVo> cbList =  notifyCbService.selectNotifyCbItemList(notifyCbVo);
		
		notifyCbVo.setReadType("count");
		notifyCbVo.setTotalCount(notifyCbService.selectNotifyCbItemList(notifyCbVo).get(0).getTotalCount());
		
		model.addAttribute("notifyCbVo", notifyCbVo);
		model.addAttribute("cbList", cbList);
	}

	@RequestMapping(value = "/updateNotifyCb.json")
	public void updateNotifyCb(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyCbVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = notifyCbService.updateNotifyCb(notifyCbVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}

	@RequestMapping(value = "/createNotifyCb.json")
	public void createNotifyCb(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyCbVo.setRegisterId(sessionVo.getRegisterId());
		notifyCbVo.setCbNotify(notifyCbService.selectNotifyCbMax());
		int resultValue = 0;
		try{
			resultValue = notifyCbService.createNotifyCb(notifyCbVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	private List<MsgVo> setMsgList(){
		MsgVo msgVo = new MsgVo();
		String[] searchTextList = {"0","Z","E","F","C","D"};
		msgVo.setSearchType("TYPE");
		msgVo.setSearchTextList(searchTextList);
		msgVo.setsSortOrder("MSG");
		msgVo.setsSortType("DESC");
		msgVo.setMode("ALL");
		List<MsgVo> msgList = msgService.selectMsgList(msgVo);
		return msgList;
	}
	
	@RequestMapping(value = "/deleteNotifyCbItem.json")
	public void deleteNotifyCbItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyCbService.deleteNotifyCbItem(notifyCbVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/deleteNotifyCb.json")
	public void deleteNotifyCb(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyCbService.deleteNotifyCb(notifyCbVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectNotifyCbCnt.json")
	public void selectNotifyCbCnt(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCbVo notifyCbVo){
		List<String> list = null;
		try{
			list = notifyCbService.selectNotifyCbCnt(notifyCbVo);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		mapper.send(response, list);
	}
}
