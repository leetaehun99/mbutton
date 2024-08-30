package com.doo.mbutton.front.controller;


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

import com.doo.mbutton.common.helper.FileUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.service.FaqService;

@Controller
public class FaqController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private FaqService faqService;
	
	// File Upload
	@Autowired
	private FileUtil fileUtil;
	

	@RequestMapping
	public String selectFaqList(Model model,@ModelAttribute NoticeVo noticeVo){
		noticeVo.setCd("02");
		faqList(model,noticeVo);
		PagingManager.setPagingInfo(noticeVo, "/faq/selectFaqList.doo");
		return "/management/faq/selectFaqList.default";
	}

	
	private void faqList(Model model,NoticeVo noticeVo){
		List<NoticeVo> faqList =  faqService.selectFaqList(noticeVo);
		
		noticeVo.setReadType("count");
		noticeVo.setTotalCount(faqService.selectFaqList(noticeVo).get(0).getTotalCount());
		
		
		model.addAttribute("noticeVo", noticeVo);
		model.addAttribute("faqList", faqList);
	}
	
	@RequestMapping
	public String selectFaq(Model model,@ModelAttribute NoticeVo noticeVo){
		
		NoticeVo notice =  faqService.selectFaq(noticeVo);
		
		model.addAttribute("notice", notice);
		model.addAttribute("noticeVo", noticeVo);
		
		return "/management/faq/selectFaq.default";
	}
	
	
	@RequestMapping
	public void download(HttpServletRequest request,HttpServletResponse response,@ModelAttribute FileVo fileVo){
		
		try {
			fileVo = faqService.selectFile(fileVo);
			fileUtil.download(request,response,fileVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
