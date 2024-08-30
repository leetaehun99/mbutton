package com.doo.mbutton.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doo.mbutton.common.helper.FileUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.ErrorCheckVo;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.MedicalStatementVo;
import com.doo.mbutton.management.model.RecuperationCostAccountVo;
import com.doo.mbutton.management.model.ScreenMsgVo;
import com.doo.mbutton.management.model.StatementVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.StatementService;


@Controller
public class StatementController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	/*
	 * SAM 파일패스
	 */
	@Value("#{applicationProp['system.upload.path']}")
	private String filePath;
	/*
	 * SAM 파일 확장자
	 */
	@Value("#{applicationProp['edi.file.extension']}")
	private String extension;

	@Autowired
	private StatementService statementService;
	
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private PagingManager pagingManager;
	
	/*
	 * 청구서 인덱스
	 */
	@RequestMapping
	public String index(Model model,HttpServletRequest request,@ModelAttribute RecuperationCostAccountVo recuperationCostAccountVo) {
		
		List<String> fileList = statementService.getFileList(filePath,extension);
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		if(sessionVo!=null){
			recuperationCostAccountVo.setRegisterId(sessionVo.getUserId());
			if("00001".equals(sessionVo.getUserLevCd())){//슈퍼 관리자
				recuperationCostAccountVo.setSearchCd("00001");
			}else if("00002".equals(sessionVo.getUserLevCd())){// 관리자
				recuperationCostAccountVo.setSearchCd("00002");
			}else if("00003".equals(sessionVo.getUserLevCd())){//사용자
				recuperationCostAccountVo.setSearchCd("00003");
				recuperationCostAccountVo.setHspId(sessionVo.getHspId());
			}
		}
		
		List<RecuperationCostAccountVo> recuperationCostAccountList = statementService.selectRecuperationCostAccountList(recuperationCostAccountVo);
		
		recuperationCostAccountVo.setReadType("count");
		recuperationCostAccountVo.setTotalCount(statementService.selectRecuperationCostAccountList(recuperationCostAccountVo).get(0).getTotalCount());		
		PagingManager.setPagingInfo(recuperationCostAccountVo, "/statement/index.doo");
		
		model.addAttribute("fileList", fileList);
		model.addAttribute("recuperationCostAccountVo", recuperationCostAccountVo);
		model.addAttribute("recuperationCostAccountList", recuperationCostAccountList);
		
		return "/management/statement/index.default";
	}
	
	/*
	 * 청구서 생성
	 */
	@RequestMapping(value = "/createStatement.json")
	public void createStatement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("RESULT", "N");
		try{
			String stsTitle = request.getParameter("stsTitle");
			FileVo fileVo = new FileVo();
			fileVo.setUploadPath(sessionVo.getHspId());
			fileVo.setPathType("R");
			map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
			if("Y".equals(map.get("RESULT"))){
				map = statementService.createStatement("ADMIN",map ,sessionVo,stsTitle,request);
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		mapper.send(response, map);
	}
	
	/*
	 * 명세서 조회
	 */
	@RequestMapping(value = "/selectStatement.json")
	public void selectStatement(HttpServletRequest request, HttpServletResponse response, MedicalStatementVo medicalStatementVo) {
		
		
		StatementVo statement = null;
		try{
			if(medicalStatementVo.getMsgLev() == "" || medicalStatementVo.getMsgLev() == null) {
				medicalStatementVo.setMsgLev(((UserVo)SessionManager.get(request, "USER")).getMsgLev());
				medicalStatementVo.setRegisterId(((UserVo)SessionManager.get(request, "USER")).getUserId());
			}
			medicalStatementVo.setDivCd("A");
			statement = statementService.selectStatement(medicalStatementVo,"JSON");
		}catch(Exception e){
			 logger.error(e.getMessage());
			 statement = null;
		}
		mapper.send(response, statement);
		
	}
	
	/*
	 * 명세서 조회 init
	 */
	@RequestMapping
	public String selectStatement(HttpServletRequest request, Model model, MedicalStatementVo medicalStatementVo) throws Exception{
		try{
			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			medicalStatementVo.setMsgLev(sessionVo.getMsgLev());
			
			StatementVo statement = statementService.selectStatement(medicalStatementVo,"ADMIN");
			if(!"INDEX".equals(medicalStatementVo.getType())) statement.setStsSrlNum(medicalStatementVo.getStsSrlNum());
			model.addAttribute("statement", statement);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return "/management/statement/statement.blank";
	}
	
	/*
	 * 점검내역 조회
	 */
	@RequestMapping(value = "/selectCheckContents.json")
	public void selectCheckContents(HttpServletResponse response,  @RequestParam(value="msgType") String msgType,@RequestParam(value="errCd") String errCd,@RequestParam(value="notify") String notify,@RequestParam(value="notifySub") String notifySub) { // ,@RequestParam(value="grpCd1") String grpCd1,@RequestParam(value="grpCd2") String grpCd2,@RequestParam(value="grpCd3") String grpCd3
		
		StatementVo statement = null;
		try{
			statement = statementService.selectCheckContents(msgType,errCd,notify,notifySub);
		}catch(Exception e){
			logger.error(e.getMessage());
			 statement = null;
		}
		mapper.send(response, statement);
	}
	
	/*
	 * 청구서 팝업
	 */
	@RequestMapping
	public String selectRecuperationCostAccount(Model model, @ModelAttribute RecuperationCostAccountVo recuperationCostAccountVo) {
		RecuperationCostAccountVo recuperationCostAccount = statementService.selectRecuperationCostAccount(recuperationCostAccountVo);
		model.addAttribute("recuperationCostAccount", recuperationCostAccount);
		return "/management/statement/receperationCostAccountPopup.blank";
	}
	
	/*
	 * 점검 결과 조회
	 */
	@RequestMapping
	public String selectStatementResult(HttpServletRequest request,Model model, @ModelAttribute ScreenMsgVo screenMsgVo) {
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		screenMsgVo.setMsgLev(sessionVo.getMsgLev());
		List<ScreenMsgVo> screenMsgList = statementService.selectStatementResult(screenMsgVo);
		model.addAttribute("screenMsgList", screenMsgList);
		return "/management/statement/selectStatementResult.default";
	}
	/*
	 * 청구서 메세지 명세서 리스트
	 */
	@RequestMapping(value = "/medicalStatementList.json")
	public void medicalStatementList(HttpServletRequest request,@ModelAttribute MedicalStatementVo medicalStatementVo,HttpServletResponse response) { 
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		medicalStatementVo.setMsgLev(sessionVo.getMsgLev());
		List<MedicalStatementVo> medicalStatementList = statementService.medicalStatementList(medicalStatementVo);
		
		mapper.send(response, medicalStatementList);
	}
	
	
	/*
	 * 점검내용 진행 바
	 */
	@RequestMapping(value = "/uploadProgress.json")
	public void uploadProgress(HttpServletRequest request,HttpServletResponse response) { 
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		mapper.send(response, sessionVo);
	}
	
	/*
	 * 청구서 삭제
	 */
	@RequestMapping
	public String deleteStatement(Model model, @RequestParam(value="recpCstClmSeqArray") String[] recpCstClmSeqArray) {
		statementService.deleteStatement(recpCstClmSeqArray);
		return "redirect:/statement/index.doo";
	}
	
	@RequestMapping
	public void download(HttpServletRequest request,HttpServletResponse response,@ModelAttribute FileVo fileVo){
		
		try {
			fileVo = statementService.selectFile(fileVo);
			fileUtil.download(request,response,fileVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}