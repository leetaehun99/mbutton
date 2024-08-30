package com.doo.mbutton.admin.controller;


import java.util.List;
import java.util.Map;

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

import com.doo.mbutton.common.helper.FileUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.model.ReNoticeVo;
import com.doo.mbutton.management.model.UserVo;
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
	public String createFaqForm(Model model,@ModelAttribute NoticeVo noticeVo){
		model.addAttribute("noticeVo", noticeVo);
		return "/management/faq/createFaq.default";
	}
	
	@RequestMapping
	public String createFaq(HttpServletRequest request,Model model,@ModelAttribute NoticeVo noticeVo) throws Exception{
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");

		FileVo fileVo = new FileVo();
		fileVo.setUploadPath("FILE");
		fileVo.setPathType("F");
		Map<String, Object> map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
		
		noticeVo.setRegisterId(sessionVo.getUserId());
		model.addAttribute("noticeVo", noticeVo); 
		int result = 0;
		if("Y".equals(map.get("RESULT"))){
			result = faqService.createFaq(noticeVo,map);
		}else{
			model.addAttribute("alertMsg", map.get("ERROR_MSG"));
			return "/management/faq/createFaq.default";
		}
		
		if(result == 1) {
			return "redirect:/faq/selectFaqList.doo?cd="+noticeVo.getCd();
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/faq/createFaq.default";
		}
		
	}
	
	@RequestMapping(value = "/updateFaq.json")
	public void updateFaq(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NoticeVo noticeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		noticeVo.setUpdaterId(sessionVo.getUserId());
		
		
		int resultValue = 0;
		try{
			FileVo fileVo = new FileVo();
			fileVo.setPathType("F");
			fileVo.setUploadPath("FILE");
			Map<String, Object> map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
			
			resultValue = faqService.updateFaq(noticeVo,map);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
		
		
	}
	
	@RequestMapping(value = "/createReFaq.json")
	public void createReFaq(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute ReNoticeVo reNoticeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		reNoticeVo.setRegisterId(sessionVo.getUserId());
		
		int resultValue = 0;
		try{
			resultValue = faqService.createReFaq(reNoticeVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
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
