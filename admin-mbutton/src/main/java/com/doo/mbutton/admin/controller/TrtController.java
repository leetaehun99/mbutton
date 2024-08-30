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
import com.doo.mbutton.management.model.TrtVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.TrtService;


@Controller
public class TrtController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	@Autowired
	private JsonMapper mapper;

	@Autowired
	private TrtService trtService;

	@RequestMapping
	public String selectTrtList(Model model,@ModelAttribute TrtVo trtVo){
		trtList(model,trtVo);
		PagingManager.setPagingInfo(trtVo, "/trt/selectTrtList.doo");
		return "/management/trt/selectTrtList.default";
	}
	
	private void trtList(Model model,TrtVo trtVo){
		List<TrtVo> trtList =  trtService.selectTrtList(trtVo);
		trtVo.setReadType("count");
		trtVo.setTotalCount(trtService.selectTrtList(trtVo).get(0).getTotalCount());
		model.addAttribute("trtVo", trtVo);
		model.addAttribute("trtList", trtList);
	}
	
	@RequestMapping
	public String selectTrt(Model model,@ModelAttribute TrtVo trtVo){
		
		TrtVo trt =  trtService.selectTrt(trtVo);
		
		model.addAttribute("trt", trt);
		model.addAttribute("trtVo", trtVo);
		
		return "/management/trt/selectTrt.default";
	}
	
	@RequestMapping
	public String createTrtForm(Model model,@ModelAttribute TrtVo trtVo){
		
		model.addAttribute("trtVo", trtVo);
		return "/management/trt/createTrt.default";
	}
	
	@RequestMapping
	public String createTrt(HttpServletRequest request,Model model,@ModelAttribute TrtVo trtVo) throws Exception{
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtVo.setRegisterId(sessionVo.getRegisterId());
		model.addAttribute("trtVo", trtVo);
		
		int result = 0;
		try{
			 result = trtService.createTrt(trtVo);
		}catch(DataIntegrityViolationException e){
			logger.error(e.getMessage());
		}
		
		if(result == 1) {
			return "redirect:/trt/selectTrtList.doo";
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/trt/createTrt.default";
		}
		
	}

	@RequestMapping(value = "/updateTrt.json")
	public void updateTrt(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute TrtVo trtVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		trtVo.setUpdaterId(sessionVo.getUpdaterId());
		int resultValue = 0;
		try{
			resultValue = trtService.updateTrt(trtVo);
		}catch(Exception e){
			resultValue = 0;
			logger.error(e.getMessage());
		}
		mapper.send(response, resultValue);
	}
}
