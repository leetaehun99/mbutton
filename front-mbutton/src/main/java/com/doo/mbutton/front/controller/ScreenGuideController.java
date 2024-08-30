package com.doo.mbutton.front.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.FileUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.service.NoticeService;

@Controller
public class ScreenGuideController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private NoticeService noticeService;
	
	// File Upload
	@Autowired
	private FileUtil fileUtil;
	

	
	@RequestMapping
	public String selectScreenGuideList(Model model,@ModelAttribute NoticeVo noticeVo){
		noticeVo.setCd("01");
		noticeList(model,noticeVo);
		PagingManager.setPagingInfo(noticeVo, "/screenGuide/selectScreenGuideList.doo");
		return "/management/screenGuide/selectScreenGuideList.default";
	}
	
	
	private void noticeList(Model model,NoticeVo noticeVo){
		List<NoticeVo> noticeList =  noticeService.selectNoticeList(noticeVo);
		
		noticeVo.setReadType("count");
		noticeVo.setTotalCount(noticeService.selectNoticeList(noticeVo).get(0).getTotalCount());
		
		
		model.addAttribute("noticeVo", noticeVo);
		model.addAttribute("noticeList", noticeList);
	}
	
	@RequestMapping
	public String selectScreenGuide(Model model,@ModelAttribute NoticeVo noticeVo){
		
		NoticeVo notice =  noticeService.selectNotice(noticeVo);
		
		model.addAttribute("notice", notice);
		model.addAttribute("noticeVo", noticeVo);
		
		return "/management/screenGuide/selectScreenGuide.default";
	}
	
	
	@RequestMapping
	public void download(HttpServletRequest request,HttpServletResponse response,@ModelAttribute FileVo fileVo){
		
		try {
			fileVo = noticeService.selectFile(fileVo);
			fileUtil.download(request,response,fileVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
