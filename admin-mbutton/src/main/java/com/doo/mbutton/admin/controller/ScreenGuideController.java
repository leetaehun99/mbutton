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
	public String createScreenGuideForm(Model model,@ModelAttribute NoticeVo noticeVo){
		model.addAttribute("noticeVo", noticeVo);
		return "/management/screenGuide/createScreenGuide.default";
	}
	
	@RequestMapping
	public String createScreenGuide(HttpServletRequest request,Model model,@ModelAttribute NoticeVo noticeVo) throws Exception{
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");

		FileVo fileVo = new FileVo();
		fileVo.setUploadPath("FILE");
		fileVo.setPathType("F");
		Map<String, Object> map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
		
		noticeVo.setRegisterId(sessionVo.getUserId());
		model.addAttribute("noticeVo", noticeVo);
		
		int result = 0;
		if("Y".equals(map.get("RESULT"))){
			result = noticeService.createNotice(noticeVo,map);
		}else{
			model.addAttribute("alertMsg", map.get("ERROR_MSG"));
			return "/management/screenGuide/createScreenGuide.default";
		}
		
		if(result == 1) {
			return "redirect:/screenGuide/selectScreenGuideList.doo?cd="+noticeVo.getCd();
		}else{
			model.addAttribute("alertMsg", "등록에 실패하였습니다. 관리자에게 문의 하세요 XXX-XXXX");
			return "/management/notice/createNotice.default";
		}
		
	}
	
	@RequestMapping(value = "/updateScreenGuide.json")
	public void updateScreenGuide(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute NoticeVo noticeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		noticeVo.setUpdaterId(sessionVo.getUserId());
		
		
		int resultValue = 0;
		try{
			FileVo fileVo = new FileVo();
			fileVo.setUploadPath("FILE");
			fileVo.setPathType("F");
			Map<String, Object> map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
			
			resultValue = noticeService.updateNotice(noticeVo,map);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
		/*
		int resultValue = 0;
		try{
			FileVo fileVo = new FileVo();
			fileVo.setUploadPath(sessionVo.getHspId());
			fileVo.setPathType("F");
			Map<String, Object> map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
			
			//noticeVo = noticeService.updateNotice(noticeVo,map);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, noticeVo.getContents());
		*/
		
		
	}
	
	@RequestMapping(value = "/createReScreenGuide.json")
	public void createReScreenGuide(HttpServletRequest request,HttpServletResponse response,Model model,@ModelAttribute ReNoticeVo reNoticeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		reNoticeVo.setRegisterId(sessionVo.getUserId());
		
		int resultValue = 0;
		try{
			resultValue = noticeService.createReNotice(reNoticeVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	/*
	@RequestMapping
	public String updateReNotice(HttpServletRequest request,Model model,@ModelAttribute ReNoticeVo reNoticeVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		reNoticeVo.setUpdaterId(sessionVo.getUserId());
		noticeService.updateReNotice(reNoticeVo);
		return "redirect:/notice/selectNoticeList.doo?cd="+reNoticeVo.getCd();
	}
	*/
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
