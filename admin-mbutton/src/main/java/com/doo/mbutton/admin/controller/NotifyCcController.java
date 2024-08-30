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
import com.doo.mbutton.management.model.NotifyCcVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.MsgService;
import com.doo.mbutton.management.service.NotifyCcService;

@Controller
public class NotifyCcController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private NotifyCcService notifyCcService;

	@Autowired
	private MsgService msgService;
	
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping
	public String selectNotifyCcList(Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		
		notifyCcList(model,notifyCcVo);
		PagingManager.setPagingInfo(notifyCcVo, "/notifyCc/selectNotifyCcList.doo");
		return "/management/notifyCc/selectNotifyCcList.default";
	}
	private void notifyCcList(Model model,NotifyCcVo notifyCcVo){
		List<NotifyCcVo> ccList =  notifyCcService.selectNotifyCcList(notifyCcVo);
		
		notifyCcVo.setReadType("count");
		notifyCcVo.setTotalCount(notifyCcService.selectNotifyCcList(notifyCcVo).get(0).getTotalCount());
		model.addAttribute("notifyCcVo", notifyCcVo);
		model.addAttribute("ccList", ccList);
	}
	
	@RequestMapping
	public String selectNotifyCc(Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		NotifyCcVo notifyCc =  notifyCcService.selectNotifyCc(notifyCcVo);
		model.addAttribute("msgList", setMsgList());
		model.addAttribute("notifyCc", notifyCc);
		model.addAttribute("notifyCcVo", notifyCcVo);
		return "/management/notifyCc/selectNotifyCc.default";
	}
	
	@RequestMapping
	public String selectNotifyCcItemList(Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		notifyCcItemList(model,notifyCcVo);
		PagingManager.setPagingInfo(notifyCcVo, "/notifyCc/selectNotifyCcItemList.doo");
		return "/management/notifyCc/selectNotifyCcItemList.blank";
	}
	
	private void notifyCcItemList(Model model,NotifyCcVo notifyCcVo){
		List<NotifyCcVo> ccList =  notifyCcService.selectNotifyCcItemList(notifyCcVo);
		
		notifyCcVo.setReadType("count");
		notifyCcVo.setTotalCount(notifyCcService.selectNotifyCcItemList(notifyCcVo).get(0).getTotalCount());
		
		model.addAttribute("notifyCcVo", notifyCcVo);
		model.addAttribute("ccList", ccList);
	}
	

	@RequestMapping(value = "/updateNotifyCc.json")
	public void updateNotifyCc(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyCcVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = notifyCcService.updateNotifyCc(notifyCcVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping
	public String createNotifyCcForm(Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		model.addAttribute("msgList", setMsgList());
		model.addAttribute("notifyCcVo", notifyCcVo);
		return "/management/notifyCc/createNotifyCc.default";
	}

	@RequestMapping(value = "/createNotifyCc.json")
	public void createNotifyCc(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyCcVo.setRegisterId(sessionVo.getRegisterId());
		notifyCcVo.setCbNotify(notifyCcService.selectNotifyCcMax());
		int resultValue = 0;
		try{
			resultValue = notifyCcService.createNotifyCc(notifyCcVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping
	public String createNotifyCcItemForm(Model model,@ModelAttribute NotifyCcVo notifyCcVo){

		List<NotifyCcVo> ccList = notifyCcService.selectNotifyCcItemList(notifyCcVo);
		
		model.addAttribute("notifyCcVo", notifyCcVo);
		model.addAttribute("ccList", ccList);
		
	
		return "/management/notifyCc/createNotifyCcItem.blank";
	}
	
	@RequestMapping(value = "/createNotifyCcItem.json")
	public void createNotifyCcItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){

		String[] temp = notifyCcVo.getMainDrugCd().split("\n");
		
		List<String> list = new ArrayList<String>(); 
				
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i].trim());
			}
		}
		
		notifyCcVo.setMainDrugCdList(list);
		
		mapper.send(response, notifyCcService.createNotifyCcItem(notifyCcVo));
	}
	
	@RequestMapping(value = "/selectNotifyCcItemChk.json")
	public void selectNotifyCcItemChk(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){

		String[] temp = notifyCcVo.getMainDrugCd().split("\n");
		
		List<String> list = new ArrayList<String>(); 
				
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i].trim());
			}
		}
		
		notifyCcVo.setMainDrugCdList(list);
		
		mapper.send(response, notifyCcService.selectNotifyCcItemChk(notifyCcVo));
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
	
	@RequestMapping(value = "/deleteNotifyCcItem.json")
	public void deleteNotifyCcItem(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyCcService.deleteNotifyCcItem(notifyCcVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/deleteNotifyCc.json")
	public void deleteNotifyCc(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyCcService.deleteNotifyCc(notifyCcVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectNotifyCcCnt.json")
	public void selectNotifyCcCnt(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NotifyCcVo notifyCcVo){
		List list = null;
		try{
			list = notifyCcService.selectNotifyCcCnt(notifyCcVo);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		mapper.send(response, list);
	}
}
