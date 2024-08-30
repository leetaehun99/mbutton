package com.doo.mbutton.interfaces.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doo.mbutton.common.helper.FileUtil;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.interfaces.model.FileVo;
import com.doo.mbutton.interfaces.model.StatementVo;
import com.doo.mbutton.interfaces.service.StatementService;




@Controller
public class StatementController {

	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private StatementService statementService;
	
	/*
	 * 청구서리스트 조회
	 */
	@RequestMapping(value = "/selectRecuperationCostAccountList.json")
	public void selectRecuperationCostAccountList(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="userId") String userId, 
			@RequestParam(value="hspId") String hspId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("hspId", hspId);
		List<Map<String,Object>> resultList = statementService.selectRecuperationCostAccountList(map);
		mapper.send(response, resultList);
		
	}
	
	/*
	 * 청구서 생성
	 */
	@RequestMapping(value = "/createStatement.json")
	public void createStatement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String result = "FALSE";
		try{
			String stsTitle = request.getParameter("stsTitle");
			FileVo fileVo = new FileVo();
			fileVo.setUploadPath("leetaehun");
			Map<String, Object> map = (Map<String, Object>)fileUtil.fileUpload(request, fileVo);
			//result = statementService.createStatement(map ,sessionVo.getUserId(),sessionVo.getHspId(),stsTitle, sessionVo.getUserLevCd());
			
		}catch(Exception e){
			logger.error(e.getMessage());
			result = "FALSE";
		}
		mapper.send(response, result);
	}
	
	/*
	 * 명세서리스트 조회
	 */
	@RequestMapping(value = "/selectMedicalStatementSelectList.json")
	public void selectMedicalStatementSelectList(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="recpCstClmSeq") String recpCstClmSeq, 
			@RequestParam(value="clmNum") String clmNum, 
			@RequestParam(value="hspId") String hspId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recpCstClmSeq", recpCstClmSeq);
		map.put("clmNum", clmNum);
		map.put("hspId", hspId);
		List<Map<String,Object>> resultList = statementService.selectMedicalStatementSelectList(map);
		mapper.send(response, resultList);
	}
	
	/*
	 * 명세서 조회
	 */
	@RequestMapping(value = "/selectStatement.json")
	public void selectStatement(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="recpCstClmSeq") String recpCstClmSeq, 
			@RequestParam(value="clmNum") String clmNum, 
			@RequestParam(value="hspId") String hspId, 
			@RequestParam(value="stsSrlNum") String stsSrlNum) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recpCstClmSeq", recpCstClmSeq);
		map.put("clmNum", clmNum);
		map.put("hspId", hspId);
		map.put("stsSrlNum", stsSrlNum);
		
		StatementVo resultMap = statementService.selectStatement(map);
		mapper.send(response, resultMap);
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
}
