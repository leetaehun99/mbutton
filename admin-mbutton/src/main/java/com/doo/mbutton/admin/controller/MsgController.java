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

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.DrugMsgVo;
import com.doo.mbutton.management.model.DrugNotifyVo;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MedicalMsgVo;
import com.doo.mbutton.management.model.MedicalNotifyVo;
import com.doo.mbutton.management.model.MsgVo;
import com.doo.mbutton.management.model.TrtMsgVo;
import com.doo.mbutton.management.model.TrtNotifyVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.DrugNotifyService;
import com.doo.mbutton.management.service.MedicalNotifyService;
import com.doo.mbutton.management.service.MsgService;
import com.doo.mbutton.management.service.TrtNotifyService;


@Controller
public class MsgController {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private DrugNotifyService drugNotifyService;

	@Autowired
	private MedicalNotifyService medicalNotifyService;
	
	@Autowired
	private TrtNotifyService trtNotifyService;
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	

	@Autowired
	private CommonUtil commonUtil;
	
	@RequestMapping
	public String selectMsgList(Model model,@ModelAttribute MsgVo msgVo){

		
		commonUtil.searchMode(msgVo, model);
		
		List<MsgVo> msgList = msgService.selectMsgList(msgVo);
		
		msgVo.setReadType("count");
		msgVo.setTotalCount(msgService.selectMsgList(msgVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(msgVo, "/msg/selectMsgList.doo");

		model.addAttribute("msgVo", msgVo);
		model.addAttribute("msgList", msgList);
		
		return "/management/msg/selectMsgList.default";
	}
	
	@RequestMapping
	public String selectSubMsgList(Model model,@ModelAttribute MsgVo msgVo){
		
		commonUtil.searchMode(msgVo, model);
		
		MsgVo resultVo = msgService.selectMsg(msgVo);
		msgVo.setMsg(resultVo.getMsg());
		msgVo.setType(resultVo.getType());
		List<MsgVo> msgList = msgService.selectSubMsgList(msgVo);
		msgVo.setReadType("count");
		msgVo.setTotalCount(msgService.selectSubMsgList(msgVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(msgVo, "/msg/selectSubMsgList.doo");
		model.addAttribute("msgVo", msgVo);
		model.addAttribute("msgList", msgList);
		
		return "/management/msg/selectSubMsgList.default";
	}
	
	@RequestMapping(value = "/selectMsg.json")
	public void selectMsg(HttpServletResponse response,Model model,@ModelAttribute MsgVo msgVo){
		MsgVo resultVo = msgService.selectMsg(msgVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/selectMsgList.json")
	public void selectMsgList(HttpServletResponse response,Model model,@ModelAttribute MsgVo msgVo){
		List<MsgVo> msgList = msgService.selectMsgJsonList(msgVo);
		model.addAttribute("msgList", msgList);
		mapper.send(response, msgList);
	}
	
	@RequestMapping(value = "/createMsg.json")
	public void createMsg(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		msgVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = msgService.createMsg(msgVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/createMsgItem.json")
	public void createMsgItem(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		msgVo.setRegisterId(sessionVo.getUserId());
		String msg = msgVo.getMsg();
		String[] msgTemp = msg.split("\n");
		
		MsgVo tempMsg = null;;
		
		DrugNotifyVo drugNotify = null;
		DrugMsgVo drugMsg = null;
		
		MedicalNotifyVo medicalNotify = null;
		MedicalMsgVo medicalMsg = null;
		
		TrtNotifyVo trtNotify = null;
		TrtMsgVo trtMsg = null;
		
		List<MsgVo> list = new ArrayList<MsgVo>();
		System.out.println(msgTemp.length);
		for (int i = 0; i < msgTemp.length; i++) {
			
			if (!"".equals(msgTemp[i].trim())) {
				tempMsg = new MsgVo();
				tempMsg.setCd(msgTemp[i].trim());
				
				if ("1".equals(msgVo.getType())){	// 수가
					medicalNotify = new MedicalNotifyVo();
					medicalNotify.setMedicalCd(msgTemp[i].trim());
					medicalNotify.setmSeq(msgVo.getSeq());
					medicalNotify = medicalNotifyService.selectMedicalNotifyItem(medicalNotify);
					
					if (medicalNotify != null ) {
						medicalMsg = new MedicalMsgVo();
						medicalMsg.setMedicalNotify(medicalNotify.getMedicalNotify());
						medicalMsg.setMedicalNotifySub(medicalNotify.getMedicalNotifySub());
						medicalMsg.setMedicalCd(msgTemp[i].trim());
						medicalMsg.setUpdaterId(msgVo.getRegisterId());
						medicalMsg.setmSeq(msgVo.getSeq());
						
						try{
							medicalNotifyService.createMedicalMsg(medicalMsg);
							tempMsg.setNotify(medicalNotify.getMedicalNotify());
							tempMsg.setNotifySub(medicalNotify.getMedicalNotifySub());
							tempMsg.setKorNm("등록이 성공하였습니다.");
						}catch(Exception e){
							tempMsg.setNotify(medicalNotify.getMedicalNotify());
							tempMsg.setNotifySub(medicalNotify.getMedicalNotifySub());
							tempMsg.setKorNm("이미 등록된 메시지입니다.");
						}
					} else {
						tempMsg.setKorNm("해당 고시가 없습니다.");
					}
					
				} else if ("3".equals(msgVo.getType())){	// 약가
					drugNotify = new DrugNotifyVo();
					drugNotify.setMainDrugCd(msgTemp[i].trim()); 
					drugNotify.setmSeq(msgVo.getSeq());
					drugNotify = drugNotifyService.selectDrugNotifyItem(drugNotify);
					
					if (drugNotify != null ) {
						tempMsg.setNotify(drugNotify.getDrugNotify());
						tempMsg.setNotifySub(drugNotify.getDrugNotifySub());
						tempMsg.setKorNm("등록이 성공하였습니다.");
						drugMsg = new DrugMsgVo();
						drugMsg.setDrugNotify(drugNotify.getDrugNotify());
						drugMsg.setDrugNotifySub(drugNotify.getDrugNotifySub());
						drugMsg.setMainDrugCd(msgTemp[i].trim());
						drugMsg.setUpdaterId(msgVo.getRegisterId());
						drugMsg.setmSeq(msgVo.getSeq());
						try{
							drugNotifyService.createDrugMsg(drugMsg);
						}catch(Exception e){
							tempMsg.setNotify(drugNotify.getDrugNotify());
							tempMsg.setNotifySub(drugNotify.getDrugNotifySub());
							tempMsg.setKorNm("이미 등록되어있습니다.");
						}
					} else {
						tempMsg.setKorNm("해당 고시가 없습니다.");
					}
				} else { // 치료대
					trtNotify = new TrtNotifyVo();
					trtNotify.setTrtCd(msgTemp[i].trim());
					trtNotify.setmSeq(msgVo.getSeq());
					trtNotify = trtNotifyService.selectTrtNotifyItem(trtNotify);
					
					if (trtNotify != null ) {
						tempMsg.setNotify(trtNotify.getTrtNotify());
						tempMsg.setNotifySub(trtNotify.getTrtNotifySub());
						tempMsg.setKorNm("등록이 성공하였습니다.");
						trtMsg = new TrtMsgVo();
						trtMsg.setTrtNotify(trtNotify.getTrtNotify());
						trtMsg.setTrtNotifySub(trtNotify.getTrtNotifySub());
						trtMsg.setTrtCd(msgTemp[i].trim());
						trtMsg.setUpdaterId(msgVo.getRegisterId());
						trtMsg.setmSeq(msgVo.getSeq());
						
						try{
							trtNotifyService.createTrtgMsg(trtMsg);
						}catch(Exception e){
							tempMsg.setNotify(trtNotify.getTrtNotify());
							tempMsg.setNotifySub(trtNotify.getTrtNotifySub());
							tempMsg.setKorNm("이미 등록된 메시지입니다.");
						}
					} else {
						tempMsg.setKorNm("해당 고시가 없습니다.");
					}
				}
				list.add(tempMsg);
			}
		}
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/deleteMsgItem.json")
	public void deleteMsgItem(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo) throws Exception {
		int resultVal = 0;
		if ("1".equals(msgVo.getType())){	// 수가
			/*MedicalMsgVo medicalMsg = new MedicalMsgVo();
			medicalMsg.setMedicalNotify(msgVo.getNotify());
			medicalMsg.setMedicalNotifySub(msgVo.getNotifySub());
			medicalMsg.setMedicalCd(msgVo.getCd());
			medicalMsg.setmSeq(msgVo.getmSeq());*/
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultVal = medicalNotifyService.deleteMedicalMsgAll(msgVo, historyLogVo);
		} else if ("3".equals(msgVo.getType())){	// 약가
			/*DrugMsgVo drugMsg = new DrugMsgVo();
			drugMsg.setDrugNotify(msgVo.getNotify());
			drugMsg.setDrugNotifySub(msgVo.getNotifySub());
			drugMsg.setMainDrugCd(msgVo.getCd());
			drugMsg.setmSeq(msgVo.getmSeq());*/
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultVal = drugNotifyService.deleteDrugMsgAll(msgVo, historyLogVo);
		} else if ("8".equals(msgVo.getType())){ // 치료대
			/*TrtMsgVo trtMsg = new TrtMsgVo();
			trtMsg.setTrtNotify(msgVo.getNotify());
			trtMsg.setTrtNotifySub(msgVo.getNotifySub());
			trtMsg.setTrtCd(msgVo.getCd());
			trtMsg.setmSeq(msgVo.getmSeq());*/
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultVal = trtNotifyService.deleteTrtMsgAll(msgVo, historyLogVo);
		}
		
		mapper.send(response, resultVal);
	}
	
	@RequestMapping(value = "/updateMsg.json")
	public void updateMsg(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		msgVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = msgService.updateMsg(msgVo);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectMsgCnt.json")
	public void selectMsgCnt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo){
		List<MsgVo> list = null;
		try{
			list = msgService.selectMsgCnt(msgVo);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/deleteMsg.json")
	public void deleteMsg(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo){
		int resultValue = 0;
		try{
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setIpUserid(historyLogVo, request);
			resultValue = msgService.deleteMsg(msgVo, historyLogVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectScreenMsgCnt.json")
	public void selectScreenMsgCnt(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MsgVo msgVo){
		int resultValue = 0;
		try{
			resultValue = msgService.selectScreenMsgCnt(msgVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		
		mapper.send(response, resultValue);
	}
}
