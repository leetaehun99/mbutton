package com.doo.mbutton.admin.controller;

import java.io.File;
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
import com.doo.mbutton.management.model.DictionaryVo;
import com.doo.mbutton.management.model.DiseaseStatementVo;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.PrescriptnStatementVo;
import com.doo.mbutton.management.model.RecuperationCostAccountVo;
import com.doo.mbutton.management.model.SpecificDetailVo;
import com.doo.mbutton.management.model.StatementVo;
import com.doo.mbutton.management.model.TreatmentStatementVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.StatementTestService;


@Controller
public class StatementTestController {

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
	private StatementTestService statementTestService;
	
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	/*
	 * 청구서 인덱스
	 */
	@RequestMapping
	public String index(Model model,HttpServletRequest request,@ModelAttribute RecuperationCostAccountVo recuperationCostAccountVo) {
		
		List<String> fileList = statementTestService.getFileList(filePath,extension);
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		if(sessionVo!=null){
			recuperationCostAccountVo.setRegisterId(sessionVo.getRegisterId());
			if("00001".equals(sessionVo.getUserLevCd())){//슈퍼 관리자
				recuperationCostAccountVo.setSearchCd("00001");
			}else if("00002".equals(sessionVo.getUserLevCd())){// 관리자
				recuperationCostAccountVo.setSearchCd("00002");
				recuperationCostAccountVo.setRegisterId(sessionVo.getUserId());
			}else if("00003".equals(sessionVo.getUserLevCd())){//사용자
				recuperationCostAccountVo.setSearchCd("00003");
				recuperationCostAccountVo.setHspId(sessionVo.getHspId());
			}
		}
		
		List<RecuperationCostAccountVo> recuperationCostAccountList = statementTestService.selectRecuperationCostAccountList(recuperationCostAccountVo);
		
		recuperationCostAccountVo.setReadType("count");
		recuperationCostAccountVo.setTotalCount(statementTestService.selectRecuperationCostAccountList(recuperationCostAccountVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(recuperationCostAccountVo, "/statementTest/index.doo");
		
		model.addAttribute("fileList", fileList);
		model.addAttribute("recuperationCostAccountVo", recuperationCostAccountVo);
		model.addAttribute("recuperationCostAccountList", recuperationCostAccountList);
		
		return "/management/statement/indexTest.default";
	}
	
	/*
	 * 명세서 조회
	 */
	@RequestMapping(value = "/selectStatement.json")
	public void selectStatement(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="recpCstClmSeq") long recpCstClmSeq, @RequestParam(value="clmNum") String clmNum, @RequestParam(value="hspId") String hspId, @RequestParam(value="stsSrlNum") String stsSrlNum, @RequestParam(value="msgLev") String msgLev) {
		if(stsSrlNum == "" || stsSrlNum == null) stsSrlNum="00001";
		
		StatementVo statement = null;
		try{
			if(msgLev == "" || msgLev == null) {
				msgLev = ((UserVo)SessionManager.get(request, "USER")).getMsgLev();
			}
			statement = statementTestService.selectStatement(recpCstClmSeq,clmNum,hspId,stsSrlNum,"JSON", msgLev);
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
	public String selectStatement(HttpServletRequest request, Model model, @RequestParam(value="recpCstClmSeq") long recpCstClmSeq,@RequestParam(value="clmNum") String clmNum, 
			@RequestParam(value="hspId") String hspId, @RequestParam(value="stsSrlNum") String stsSrlNum) {
		try{
			stsSrlNum="00001";
			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			StatementVo statement = statementTestService.selectStatement(recpCstClmSeq,clmNum,hspId,stsSrlNum,"DOO", sessionVo.getMsgLev());
			statement.setJsonObject(mapper.jsonObject(statement.getSectNum2List(),"sectNum2"));
			model.addAttribute("statement", statement);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return "/management/statement/statementTest.blank";
	}
	
	
	@RequestMapping(value = "/selectCheckContents.json")
	public void selectCheckContents(HttpServletResponse response,  @RequestParam(value="errCodeType") String errCodeType,@RequestParam(value="errCode") String errCode ) {
		
		StatementVo statement = null;
		try{
			statement = statementTestService.selectCheckContents(errCodeType,errCode);
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
		RecuperationCostAccountVo recuperationCostAccount = statementTestService.selectRecuperationCostAccount(recuperationCostAccountVo);
		model.addAttribute("recuperationCostAccount", recuperationCostAccount);
		return "/management/statement/receperationCostAccountPopup.blank";
	}
	
	@RequestMapping
	public void download(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="hspId") String hspId, @RequestParam(value="clmNum") String clmNum){		
		try {
			String fileName = clmNum+"."+extension;			
			String path = fileUtil.makeDownloadDir(hspId);
			
			FileVo fileVo = new FileVo();
			fileVo.setOrgFileName(fileName);
			fileVo.setFilePath(path+File.separator+fileName);

			statementTestService.createSamFile(clmNum, hspId,path,fileName);
			
			fileUtil.download2(request,response, fileVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/*
	 * 일반 특정내역팝업
	 */
	@RequestMapping
	public String selectSpecificDetailList(Model model, @ModelAttribute SpecificDetailVo specificDetailVo,HttpServletRequest request, HttpServletResponse response) {
		
		List<SpecificDetailVo> specificDetailList = statementTestService.selectSpecificDetailList(specificDetailVo);
		
		specificDetailVo.setReadType("count");
		specificDetailVo.setTotalCount(statementTestService.selectSpecificDetailList(specificDetailVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(specificDetailVo, "/statementTest/selectSpecificDetailList.doo");
		
		if("2".equals(specificDetailVo.getPrscrptnDivd())){
			TreatmentStatementVo treatmentStatementVo = new TreatmentStatementVo();
			treatmentStatementVo.setHspId(specificDetailVo.getHspId());
			treatmentStatementVo.setClmNum(specificDetailVo.getClmNum());
			treatmentStatementVo.setStsSrlNum(specificDetailVo.getStsSrlNum());
			treatmentStatementVo.setRecpCstClmSeq(specificDetailVo.getRecpCstClmSeq());
			List<TreatmentStatementVo> tLnList = statementTestService.selectTreatmentStatementLnNumAscList(treatmentStatementVo);
			model.addAttribute("tLnList", tLnList);
		}
		model.addAttribute("specificDetailVo", specificDetailVo);
		model.addAttribute("specificDetailList", specificDetailList);
		return "/management/statement/specificDetailPopup.blank";
	}
	
	/*
	 * 상병수정팝업
	 */
	@RequestMapping
	public String selectDiseaseStatementList(Model model, @ModelAttribute DiseaseStatementVo diseaseStatementVo) {
		List<DiseaseStatementVo> diseaseStatementList = statementTestService.selectDiseaseStatementList(diseaseStatementVo);
		
		diseaseStatementVo.setTrtStartDt(diseaseStatementList.get(0).getTrtStartDt());
		diseaseStatementVo.setLicenCateg(diseaseStatementList.get(0).getLicenCateg());
		diseaseStatementVo.setLicenNum(diseaseStatementList.get(0).getLicenNum());
		diseaseStatementVo.setTrtSubct(diseaseStatementList.get(0).getTrtSubct());
		diseaseStatementVo.setTrtDetailSubct(diseaseStatementList.get(0).getTrtDetailSubct());
		
		model.addAttribute("diseaseStatementVo", diseaseStatementVo);
		model.addAttribute("diseaseStatementList", diseaseStatementList);
		return "/management/statement/diseaseStatementPopup.blank";
	}
	
	/*
	 * 상병조회
	 */
	@RequestMapping(value = "/selectDiseaseStatement.json")
	public void selectDiseaseStatement(@ModelAttribute DiseaseStatementVo diseaseStatementVo,HttpServletRequest request, HttpServletResponse response) {
		DiseaseStatementVo diseaseStatement = statementTestService.selectDiseaseStatement(diseaseStatementVo);
		mapper.send(response, diseaseStatement);
	}
	
	/*
	 * 상병수정
	 */
	@RequestMapping(value = "/updateDiseaseStatement.json")
	public void updateDiseaseStatement(Model model, @ModelAttribute DiseaseStatementVo diseaseStatementVo,HttpServletRequest request, HttpServletResponse response) {
		List<DiseaseStatementVo> list = null;
		try{
			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			list= statementTestService.updateDiseaseStatement(diseaseStatementVo,sessionVo.getUserId());
		}catch(Exception e){
			logger.error(e.getMessage());
			list = null;
		}
		mapper.send(response, list);
	}
	
	/*
	 * 상병삭제
	 */
	@RequestMapping(value = "/deleteDiseaseStatement.json")
	public void deleteDiseaseStatement(Model model, @ModelAttribute DiseaseStatementVo diseaseStatementVo,HttpServletRequest request, HttpServletResponse response) {
		List<DiseaseStatementVo> list = null;
		try{
			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			list = statementTestService.deleteDiseaseStatement(diseaseStatementVo,sessionVo.getUserId());
		}catch(Exception e){
			logger.error(e.getMessage());
			list = null;
		}
		mapper.send(response, list);
	}
	
	/*
	 * 진료내역리스트
	 */
	@RequestMapping
	public String selectTreatmentStatementList(Model model, @ModelAttribute TreatmentStatementVo treatmentStatementVo,HttpServletRequest request, HttpServletResponse response) {
		
		List<TreatmentStatementVo> treatmentStatementList = statementTestService.selectTreatmentStatementList(treatmentStatementVo);
		model.addAttribute("treatmentStatementList", treatmentStatementList);
		return "/management/statement/treatmentStatementPopup.blank";
	}

	
	/*
	 * 진료내역조회
	 */
	@RequestMapping(value = "/selectTreatmentStatement.json")
	public void selectTreatmentStatement(@ModelAttribute TreatmentStatementVo treatmentStatementVo,HttpServletRequest request, HttpServletResponse response) {
		TreatmentStatementVo treatmentStatement = statementTestService.selectTreatmentStatement(treatmentStatementVo);
		mapper.send(response, treatmentStatement);
	}
	
	/*
	 * 진료내역수정
	 */
	@RequestMapping(value = "/updateTreatmentStatement.json")
	public void updateTreatmentStatement(Model model, @ModelAttribute TreatmentStatementVo treatmentStatementVo,HttpServletRequest request, HttpServletResponse response) {
		TreatmentStatementVo tVo = null;
		try{
			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			tVo= statementTestService.updateTreatmentStatement(treatmentStatementVo,sessionVo.getUserId());
		}catch(Exception e){
			logger.error(e.getMessage());
			tVo = null;
		}
		mapper.send(response, tVo);
	}
	
	/*
	 * 진료내역삭제
	 */
	@RequestMapping(value = "/deleteTreatmentStatement.json")
	public void deleteTreatmentStatement(Model model, @ModelAttribute TreatmentStatementVo treatmentStatementVo,HttpServletRequest request, HttpServletResponse response) {
		TreatmentStatementVo tVo = null;
		try{
			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			tVo = statementTestService.deleteTreatmentStatement(treatmentStatementVo,sessionVo.getUserId());
		}catch(Exception e){
			logger.error(e.getMessage());
			tVo = null;
		}
		mapper.send(response, tVo);
	}
	
	@RequestMapping(value = "/selectTrtType.json")
	public void selectTrtType(Model model, @ModelAttribute DictionaryVo dictionaryVo,HttpServletRequest request, HttpServletResponse response){
		Map<String,String> map= null;
		map = statementTestService.selectTrtType(dictionaryVo.getMode(),dictionaryVo.getType(),dictionaryVo.getCd());
		mapper.send(response, map);
	}
	
	/*
	 * 특전내역 등록/수정
	 */
	@RequestMapping
	public String createSpecificDetail(HttpServletRequest request,Model model, @ModelAttribute SpecificDetailVo specificDetailVo) {
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		specificDetailVo.setRegisterId(sessionVo.getRegisterId());
		specificDetailVo.setUpdaterId(sessionVo.getUpdaterId());
		statementTestService.createSpecificDetail(specificDetailVo);
		String param = "recpCstClmSeq="+specificDetailVo.getRecpCstClmSeq()+"&stsSrlNum="+specificDetailVo.getStsSrlNum()+"&clmNum="+specificDetailVo.getClmNum()+"&hspId="+specificDetailVo.getHspId()+"&prscrptnDivd="+specificDetailVo.getPrscrptnDivd();
		return "redirect:/statementTest/selectSpecificDetailList.doo?"+param;
	}
	
	
	/*
	 * 특전내역 삭제
	 */
	@RequestMapping
	public String deleteSpecificDetail(Model model, @ModelAttribute SpecificDetailVo specificDetailVo,HttpServletRequest request) {
		
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		specificDetailVo.setRegisterId(sessionVo.getRegisterId());
		specificDetailVo.setUpdaterId(sessionVo.getUpdaterId());
		statementTestService.deleteSpecificDetail(specificDetailVo);
		
		String param = "recpCstClmSeq="+specificDetailVo.getRecpCstClmSeq()+"&stsSrlNum="+specificDetailVo.getStsSrlNum()+"&clmNum="+specificDetailVo.getClmNum()+"&hspId="+specificDetailVo.getHspId()+"&prscrptnDivd="+specificDetailVo.getPrscrptnDivd();
		return "redirect:/statementTest/selectSpecificDetailList.doo?"+param;
			
	}
	
	/*
	 * 특전내역 삭제
	 */
	@RequestMapping
	public void deletePrescriptnStatement(Model model, @ModelAttribute PrescriptnStatementVo prescriptnStatementVo, HttpServletResponse response) {
		
		int result = 0;
		PrescriptnStatementVo prStsVo = new PrescriptnStatementVo();
		try{
			result = statementTestService.deletePrescriptnStatement(prescriptnStatementVo);
			if(result==1) prStsVo.setStsSrlNum(prescriptnStatementVo.getStsSrlNum());
	
		}catch(Exception e){
			logger.error(e.getMessage());
			result = 0;
		}
		mapper.send(response, prStsVo);
			
	}
	
	
	@RequestMapping(value = "/selectSpecificDetail.json")
	public void selectSpecificDetail(Model model, @ModelAttribute SpecificDetailVo specificDetailVo,HttpServletRequest request,HttpServletResponse response) {		
	
			mapper.send(response, statementTestService.selectSpecificDetail(specificDetailVo));
	}
}