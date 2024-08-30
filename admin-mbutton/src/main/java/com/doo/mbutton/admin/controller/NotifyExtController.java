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
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.NotifyExtVo;
import com.doo.mbutton.management.model.MsgVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.NotifyExtService;
import com.doo.mbutton.management.service.MsgService;
import com.doo.mbutton.management.service.NotifyKkService;

@Controller
public class NotifyExtController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private NotifyExtService notifyExtService;
	
	@Autowired
	private MsgService msgService;
	@Autowired
	private CommonUtil commonUtil;
	
	@RequestMapping
	public String createNotifyExtForm(Model model,@ModelAttribute NotifyExtVo notifyExtVo){
		
		model.addAttribute("msgList", setMsgList());
		model.addAttribute("notifyExtVo", notifyExtVo);
		return "/management/notifyExt/createNotifyExt.default";
	}
	
	@RequestMapping
	public String selectNotifyExtForm(HttpServletResponse response,Model model,@ModelAttribute NotifyExtVo notifyExtVo){
		NotifyExtVo resultVo = notifyExtService.selectNotifyExt(notifyExtVo);
		model.addAttribute("msgList", setMsgList());
		model.addAttribute("notifyExtVo", notifyExtVo);
		model.addAttribute("notifyExt", resultVo);
		return "/management/notifyExt/selectNotifyExt.default";
	}
	
	@RequestMapping
	public String createNotifyExt(HttpServletRequest request,Model model,@ModelAttribute NotifyExtVo notifyExtVo) throws Exception{
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyExtVo.setRegisterId(sessionVo.getRegisterId());
		model.addAttribute("notifyVo", notifyExtVo);
		int result = 0;
		try{
			result = notifyExtService.createNotifyExt(notifyExtVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}

		if(result == 1) {
			return "redirect:/notifyExt/selectNotifyExtList.doo";
		}else{
			model.addAttribute("msgList", setMsgList());
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/notifyExt/createNotifyExt.default";
		}
		
		
	}
	
	@RequestMapping
	public String selectNotifyExtList(Model model,@ModelAttribute NotifyExtVo notifyExtVo){
		commonUtil.searchMode(notifyExtVo, model);
		
		List<NotifyExtVo> notifyExtList = notifyExtService.selectNotifyExtList(notifyExtVo);
		
		notifyExtVo.setReadType("count");
		notifyExtVo.setTotalCount(notifyExtService.selectNotifyExtList(notifyExtVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(notifyExtVo, "/notifyExt/selectNotifyExtList.doo");
		
		model.addAttribute("notifyExtVo", notifyExtVo);
		model.addAttribute("notifyExtList", notifyExtList);
		return "/management/notifyExt/selectNotifyExtList.default";
	}
	
	@RequestMapping
	public String selectSubNotifyExtList(Model model,@ModelAttribute NotifyExtVo notifyExtVo){
		
		commonUtil.searchMode(notifyExtVo, model);
		
		List<NotifyExtVo> notifyExtList = notifyExtService.selectSubNotifyExtList(notifyExtVo);
		
		notifyExtVo.setReadType("count");
		notifyExtVo.setTotalCount(notifyExtService.selectSubNotifyExtList(notifyExtVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(notifyExtVo, "/notifyExt/selectSubNotifyExtList.doo");

		model.addAttribute("notifyExtVo", notifyExtVo);
		model.addAttribute("notifyExtList", notifyExtList);
		
		return "/management/notifyExt/selectSubNotifyExtList.default";
	}
	
	@RequestMapping(value = "/selectNotifyExt.json")
	public void selectNotifyExt(HttpServletResponse response,Model model,@ModelAttribute NotifyExtVo notifyExtVo){
		NotifyExtVo resultVo = notifyExtService.selectNotifyExt(notifyExtVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/selectSubNotifyExt.json")
	public void selectSubNotifyExt(HttpServletResponse response,Model model,@ModelAttribute NotifyExtVo notifyExtVo){
		NotifyExtVo resultVo = notifyExtService.selectSubNotifyExt(notifyExtVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/createNotifyExt.json")
	public void createNotifyExt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyExtVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = notifyExtService.createNotifyExt(notifyExtVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/createSubNotifyExt.json")
	public void createSubNotifyExt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyExtVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = notifyExtService.createSubNotifyExt(notifyExtVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateNotifyExt.json")
	public void updateNotifyExt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyExtVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = notifyExtService.updateNotifyExt(notifyExtVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateSubNotifyExt.json")
	public void updateSubNotifyExt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		notifyExtVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = notifyExtService.updateSubNotifyExt(notifyExtVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	private List<MsgVo> setMsgList(){
		MsgVo msgVo = new MsgVo();
		String[] searchTextList = {"0","Y","Z","E","F","C","D"};
		msgVo.setSearchType("TYPE");
		msgVo.setSearchTextList(searchTextList);
		msgVo.setsSortOrder("MSG");
		msgVo.setsSortType("DESC");
		msgVo.setMode("ALL");
		List<MsgVo> msgList = msgService.selectMsgList(msgVo);
		return msgList;
	}
	
	@RequestMapping(value = "/deleteSubNotifyExt.json")
	public void deleteSubNotifyExt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyExtService.deleteSubNotifyExt(notifyExtVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectNotifyExtCnt.json")
	public void selectNotifyExtCnt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		List<NotifyExtVo> list = null;
		try{
			list = notifyExtService.selectNotifyExtCnt(notifyExtVo);
		}catch(Exception e){
			e.printStackTrace();
		}
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/deleteNotifyExt.json")
	public void deleteNotifyExt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NotifyExtVo notifyExtVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = notifyExtService.deleteNotifyExt(notifyExtVo, historyLogVo);
		}catch(Exception e){
			e.printStackTrace();
		}
		mapper.send(response, resultValue);
	}
}
