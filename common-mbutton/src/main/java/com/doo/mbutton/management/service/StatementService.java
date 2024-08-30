package com.doo.mbutton.management.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.common.helper.StatementNotifyCheck;
import com.doo.mbutton.management.mapper.DiseaMapper;
import com.doo.mbutton.management.mapper.DiseaseStatementMapper;
import com.doo.mbutton.management.mapper.DrugMapper;
import com.doo.mbutton.management.mapper.DrugNotifyMapper;
import com.doo.mbutton.management.mapper.MedicalNotifyMapper;
import com.doo.mbutton.management.mapper.MedicalStatementMapper;
import com.doo.mbutton.management.mapper.NotifyCbMapper;
import com.doo.mbutton.management.mapper.NotifyCcMapper;
import com.doo.mbutton.management.mapper.NotifyExtMapper;
import com.doo.mbutton.management.mapper.PrescriptnStatementMapper;
import com.doo.mbutton.management.mapper.RecuperationCostAccountMapper;
import com.doo.mbutton.management.mapper.RelativeValueMapper;
import com.doo.mbutton.management.mapper.ScreenMsgMapper;
import com.doo.mbutton.management.mapper.SpecificDetailMapper;
import com.doo.mbutton.management.mapper.StatementMapper;
import com.doo.mbutton.management.mapper.TreatmentStatementMapper;
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DiseaseStatementVo;
import com.doo.mbutton.management.model.DrugNotifyVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.ErrorCheckVo;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.MedicalInsurVo;
import com.doo.mbutton.management.model.MedicalNotifyVo;
import com.doo.mbutton.management.model.MedicalStatementVo;
import com.doo.mbutton.management.model.NotifyCbVo;
import com.doo.mbutton.management.model.NotifyCcVo;
import com.doo.mbutton.management.model.NotifyExtVo;
import com.doo.mbutton.management.model.PrescriptnStatementVo;
import com.doo.mbutton.management.model.RecuperationCostAccountVo;
import com.doo.mbutton.management.model.RelativeValueVo;
import com.doo.mbutton.management.model.SamArray;
import com.doo.mbutton.management.model.SamArray086;
import com.doo.mbutton.management.model.SamVo;
import com.doo.mbutton.management.model.ScreenMsgVo;
import com.doo.mbutton.management.model.SpecificDetailVo;
import com.doo.mbutton.management.model.StatementVo;
import com.doo.mbutton.management.model.TreatmentStatementVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.model.SamArray087;

@Service
public class StatementService {

	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");


	@Autowired
	RecuperationCostAccountMapper recuperationCostAccountMapper;

	@Autowired
	MedicalStatementMapper medicalStatementMapper;

	@Autowired
	DiseaseStatementMapper diseaseStatementMapper;

	@Autowired
	TreatmentStatementMapper treatmentStatementMapper;

	@Autowired
	PrescriptnStatementMapper prescriptnStatementMapper;

	@Autowired
	SpecificDetailMapper specificDetailMapper;
	
	@Autowired
	DrugMapper drugMapper;
	
	@Autowired
	DiseaMapper diseaMapper;
	
	@Autowired
	DrugNotifyMapper drugNotifyMapper;
	
	@Autowired
	MedicalNotifyMapper medicalNotifyMapper;
	
	@Autowired
	RelativeValueMapper relativeValueMapper;

	@Autowired
	NotifyExtMapper notifyExtMapper;

	@Autowired
	NotifyCbMapper notifyCbMapper;
	

	@Autowired
	NotifyCcMapper notifyCcMapper;
	

	@Autowired
	StatementMapper statementMapper;
	
	@Autowired
	ScreenMsgMapper screenMsgMapper;
	
	@Autowired
	private StatementNotifyCheck statementNotifyCheck;
	
	/*	
	 * SAM 파일패스
	 */
	@Value("#{applicationProp['system.upload.path']}")
	private String uploadPath;

	@Value("#{applicationProp['enc.key']}")
	private String encKey;

	/*
	 * 청구서 생성
	 */
	public Map<String, Object> createStatement(String type,Map<String, Object> map, UserVo sessionVo,String stsTitle,HttpServletRequest request) throws Exception {
		
		sessionVo = (UserVo)SessionManager.get(request, "USER");
		sessionVo.setCcurrentVal(0);
		sessionVo.setCtotalVal(0);
		SessionManager.put(request, "USER", sessionVo);
		String filePath = "";
		if ("Y".equals(map.get("RESULT"))) {
			ArrayList<Map<String, String>> uploadFileList = null;
			uploadFileList = (ArrayList<Map<String, String>>) map.get("uploadFileList");
			HashMap<String, String> fileInfo = (HashMap<String, String>) uploadFileList.get(0);
			filePath = uploadPath + File.separator + fileInfo.get("uploadPath") + File.separator + fileInfo.get("fileName");
		} else {
			map.put("ERROR_MSG", "파일 등록 실패");
			logger.info(map.get("ERROR_MSG"));
			return map;
		}
		
		List<Map<String, SamVo>> recuperationCostAccountList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> medicalStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> diseaseStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> treatmentStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> prescriptnStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> specificDetailList = new ArrayList<Map<String, SamVo>>();
		String result = readSamFile(filePath, sessionVo.getHspId(), sessionVo.getUserLevCd(), recuperationCostAccountList, medicalStatementList, diseaseStatementList, treatmentStatementList, prescriptnStatementList, specificDetailList);
		
		if ("Y".equals(result)) {
			long recpCstClmSeq = createStatement("", sessionVo.getUserId(), recuperationCostAccountList, medicalStatementList, diseaseStatementList, treatmentStatementList, prescriptnStatementList, specificDetailList, encKey,stsTitle,request);// 수정모드
			ArrayList<Map<String, String>> uploadFileList = null;
			uploadFileList = (ArrayList<Map<String, String>>) map.get("uploadFileList");
			HashMap<String, String> fileInfo = null;
			FileVo fileVo = null;
			if(uploadFileList!=null){
				for(int i=0; i<uploadFileList.size(); i++){
					fileInfo =  (HashMap<String, String>) uploadFileList.get(i);
					fileVo = new FileVo();
					fileVo.setRecpCstClmSeq(recpCstClmSeq);
					fileVo.setOrgFileName(fileInfo.get("orgFileName"));
					fileVo.setSysFileName(fileInfo.get("fileName"));
					fileVo.setFilePath(fileInfo.get("filePath"));
					recuperationCostAccountMapper.createFile(fileVo);
				}
			}  
			
			if("FRONT".equals(type)){
				autoCheck(recpCstClmSeq,request);
			}
			return map;
		}  else {
			map.put("RESULT", "N");
			map.put("ERROR_MSG", result);
			return map;
		}
	}

	/*
	 * 청구서 파일 리스트
	 */
	public List<String> getFileList(String filePath, String extension) {

		List<String> fileList = new ArrayList<String>();
		File file = new File(filePath);
		if (file.exists()) {
			File[] list = file.listFiles();
			for (int i = 0; i < list.length; i++) {
				if (list[i].getName().indexOf("." + extension) != -1) {
					fileList.add(list[i].getName());
				}
			}
		}
		return fileList;
	}

	/*
	 * 청구서 리스트
	 */
	public List<RecuperationCostAccountVo> selectRecuperationCostAccountList(RecuperationCostAccountVo recuperationCostAccountVo) {
		return recuperationCostAccountMapper.selectRecuperationCostAccountList(recuperationCostAccountVo);
	}

	/*
	 * 명세서
	 */
	public StatementVo selectStatement(MedicalStatementVo medicalStatementVo,String mode) {
		String year = ""; //진료년도
		StatementVo statementVo = new StatementVo();
		long recpCstClmSeq = medicalStatementVo.getRecpCstClmSeq();
		String clmNum =medicalStatementVo.getClmNum();
		String hspId=medicalStatementVo.getHspId();
		String stsSrlNum =medicalStatementVo.getStsSrlNum();
		String msgLev =medicalStatementVo.getMsgLev();
		String birthy=medicalStatementVo.getBirthDy();
		String divCd=medicalStatementVo.getDivCd();
		String type=medicalStatementVo.getType();
		String registerId=medicalStatementVo.getRegisterId();
		
		statementVo.setRegisterId(registerId);
		statementVo.setRecpCstClmSeq(recpCstClmSeq);
		statementVo.setClmNum(clmNum);
		statementVo.setHspId(hspId);
		statementVo.setStsSrlNum(stsSrlNum);
		
		MedicalStatementVo medicalStatement = null;
		List<DiseaseStatementVo> diseaseStatementList = null;
		List<TreatmentStatementVo> treatmentStatementList = null;
		List<PrescriptnStatementVo> prescriptnStatementList = null;
		List<SpecificDetailVo> specificDetailList = null;

		DiseaseStatementVo diseaseStatementVo = new DiseaseStatementVo();
		
		diseaseStatementVo.setRecpCstClmSeq(recpCstClmSeq);
		diseaseStatementVo.setClmNum(clmNum);
		diseaseStatementVo.setHspId(hspId);
		diseaseStatementVo.setStsSrlNum(stsSrlNum);

		
		medicalStatement = medicalStatementMapper.selectMedicalStatement(medicalStatementVo);			// 선택된 명세서
		diseaseStatementList = diseaseStatementMapper.selectDiseaseStatementList(diseaseStatementVo);	// 상병내역
		
		statementVo.setMedicalStatementVo(medicalStatement);
		statementVo.setDiseaseStatementList(diseaseStatementList);
		
		if(diseaseStatementList!=null && diseaseStatementList.size()>0){
			statementVo.setTrtStartDt(diseaseStatementList.get(0).getTrtStartDt());//진료 시작일
			
			year = diseaseStatementList.get(0).getTrtStartDt().substring(0, 4);// 년도
			RelativeValueVo relativeValueVo = new RelativeValueVo();
			relativeValueVo = relativeValueMapper.selectRelativeValueList(year);
			
			statementVo.setDayOfWeek(CommonUtil.getInstance().getDayOfWeek(diseaseStatementList.get(0).getTrtStartDt().substring(0, 6), "yyyyMM"));// 달력
			
			if ("JSON".equals(mode)) {
				TreatmentStatementVo treatmentStatementVo = new TreatmentStatementVo();
				PrescriptnStatementVo prescriptnStatementVo = new PrescriptnStatementVo();
				SpecificDetailVo specificDetailVo = new SpecificDetailVo();
				ErrorCheckVo errorCheckVo = new ErrorCheckVo();
				if(birthy != "" && birthy != null)	errorCheckVo.setBirthy(birthy);
				treatmentStatementVo.setApplyDt(statementVo.getTrtStartDt());
				treatmentStatementVo.setRecpCstClmSeq(recpCstClmSeq);
				treatmentStatementVo.setClmNum(clmNum);
				treatmentStatementVo.setHspId(hspId);
				treatmentStatementVo.setStsSrlNum(stsSrlNum);
				
				treatmentStatementVo.setRv1(relativeValueVo.getRv1());
				treatmentStatementVo.setRv2(relativeValueVo.getRv2());
				treatmentStatementVo.setRv3(relativeValueVo.getRv3());
				treatmentStatementVo.setRv4(relativeValueVo.getRv4());
				treatmentStatementVo.setRv5(relativeValueVo.getRv5());
				treatmentStatementVo.setRv6(relativeValueVo.getRv6());
				treatmentStatementVo.setRv7(relativeValueVo.getRv7());
				
				prescriptnStatementVo.setApplyDt(statementVo.getTrtStartDt());
				prescriptnStatementVo.setRecpCstClmSeq(recpCstClmSeq);
				prescriptnStatementVo.setClmNum(clmNum);
				prescriptnStatementVo.setHspId(hspId);
				prescriptnStatementVo.setStsSrlNum(stsSrlNum);

				specificDetailVo.setRecpCstClmSeq(recpCstClmSeq);
				specificDetailVo.setClmNum(clmNum);
				specificDetailVo.setHspId(hspId);
				specificDetailVo.setStsSrlNum(stsSrlNum);

				errorCheckVo.setRecpCstClmSeq(recpCstClmSeq);
				errorCheckVo.setClmNum(clmNum);
				errorCheckVo.setHspId(hspId);
				errorCheckVo.setStsSrlNum(stsSrlNum);
				treatmentStatementList = treatmentStatementMapper.selectTreatmentStatementList(treatmentStatementVo);		// 진료
				prescriptnStatementList = prescriptnStatementMapper.selectPrescriptnStatementList(prescriptnStatementVo);	// 처방
				specificDetailList = specificDetailMapper.selectSpecificDetailList(specificDetailVo);						// 특정내역
				
				statementVo.setTreatmentStatementList(treatmentStatementList);
				statementVo.setPrescriptnStatementList(prescriptnStatementList);
				statementVo.setSpecificDetailList(specificDetailList);
				long start = System.currentTimeMillis(); 
				if("A".equals(divCd)){
					statementNotifyCheck.notifyCheck(statementVo,errorCheckVo);
				}else{
					errorCheckVo.setMsgLev(msgLev);
					List<ErrorCheckVo> errorList =  screenMsgMapper.selectErrorCheckList(errorCheckVo);
					if(errorList!=null) statementVo.setErrorCheckList(errorList);
				}
				long end = System.currentTimeMillis();
				logger.info("실행 시간 : " + ( end - start )/1000.0 );
			}else if("ADMIN".equals(mode)){
				List<MedicalStatementVo> medicalStatementSelectList = null;
				if("SCREEN".equals(type)){
					medicalStatementSelectList = medicalStatementMapper.selectMedicalStatementSelectList3(medicalStatementVo);	// 명세서
				}else{
					medicalStatementSelectList = medicalStatementMapper.selectMedicalStatementSelectList(medicalStatementVo);	// 명세서
				}
				statementVo.setMedicalStatementSelectList(medicalStatementSelectList);
				statementVo.setStsSrlNum(medicalStatementSelectList.get(0).getStsSrlNum());
			}else{
				List<MedicalStatementVo> medicalStatementSelectList = null;
				if("SCREEN".equals(type)){
					medicalStatementSelectList = medicalStatementMapper.selectMedicalStatementSelectList3(medicalStatementVo);	// 명세서
				}else{
					medicalStatementSelectList = medicalStatementMapper.selectMedicalStatementSelectList2(medicalStatementVo);	// 명세서
				}
				statementVo.setMedicalStatementSelectList(medicalStatementSelectList);
				statementVo.setStsSrlNum(medicalStatementSelectList.get(0).getStsSrlNum());
			}
		}else{
			List<ErrorCheckVo> errorCheckList = new ArrayList<ErrorCheckVo>();
			ErrorCheckVo errorCheckVo = new ErrorCheckVo();
			errorCheckVo.setNotify("0000");
			errorCheckVo.setNotifySub("1");
			errorCheckVo.setParam1(stsSrlNum);
			errorCheckList.add(statementMapper.selectNotifyExtCheck(errorCheckVo));
			statementVo.setErrorCheckList(errorCheckList);
		}

		return statementVo;
	}

	/*
	 * 청구서 상세
	 */
	public RecuperationCostAccountVo selectRecuperationCostAccount(RecuperationCostAccountVo recuperationCostAccountVo) {
		return recuperationCostAccountMapper.selectRecuperationCostAccount(recuperationCostAccountVo);
	}
	
	/*
	 * 청구서 점검 결과
	 */
	public List<ScreenMsgVo> selectStatementResult(ScreenMsgVo screenMsgVo) {
		return screenMsgMapper.selectStatementResult(screenMsgVo);
	}
	
	/*
	 * 청구서 메세지 명세서 리스트
	 */
	public List<MedicalStatementVo> medicalStatementList(MedicalStatementVo medicalStatementVo) {
		return medicalStatementMapper.selectMedicalStatementSelectList3(medicalStatementVo);
	}
	
	/*
	 * 청구서 삭제
	 */
	public void deleteStatement(String[] recpCstClmSeqArray) {
		for(int i=0; i<recpCstClmSeqArray.length; i++){
			specificDetailMapper.deleteStatement(recpCstClmSeqArray[i]);//특정내역
			prescriptnStatementMapper.deleteStatement(recpCstClmSeqArray[i]);//원외
			treatmentStatementMapper.deleteStatement(recpCstClmSeqArray[i]);//원내
			diseaseStatementMapper.deleteStatement(recpCstClmSeqArray[i]);//상병
			screenMsgMapper.deleteStatement(recpCstClmSeqArray[i]);//점검내역
			medicalStatementMapper.deleteStatement(recpCstClmSeqArray[i]);//명세서
			recuperationCostAccountMapper.deleteFile(recpCstClmSeqArray[i]);//첨부파일
			recuperationCostAccountMapper.deleteStatement(recpCstClmSeqArray[i]);//청구서
		}
		
	}
	
	
	/*
	 * 고시 호출
	 */
	public StatementVo selectCheckContents(String msgType,String errCd,String notify,String notifySub){
		
		//0:불필요 1:수가 3:약가 Z:약가(인증상병) Y:수가(인증상병) E:약품예외 F:수가예외 C:병용 D:단일
		StatementVo statementVo = new StatementVo();
		if("3".equals(msgType) || "4".equals(msgType) || "Z".equals(msgType) || "E".equals(msgType) || "C".equals(msgType) || "D".equals(msgType)){//약가
			DrugVo drugVo = new DrugVo();
			drugVo.setDrugCd(errCd);
			if("Z".equals(msgType)){
				drugVo.setMainDrugCd(drugMapper.selectMainDrugCd(drugVo.getDrugCd()));
				List<DiseaVo> diseaList = diseaMapper.selectDiseaMappingListByDrug(drugVo);
				statementVo.setDiseaList(diseaList);
				NotifyExtVo notifyExtVo = new NotifyExtVo();
				notifyExtVo.setExtNotify(notify);
				notifyExtVo.setExtNotifySub(notifySub);
				notifyExtVo = notifyExtMapper.selectNotifyExt(notifyExtVo);
				statementVo.setNotifyExtVo(notifyExtVo);
			}
			
			if("E".equals(msgType)){
				NotifyExtVo notifyExtVo = new NotifyExtVo();
				notifyExtVo.setExtNotify(notify);
				notifyExtVo.setExtNotifySub(notifySub);
				notifyExtVo = notifyExtMapper.selectNotifyExt(notifyExtVo);
				statementVo.setNotifyExtVo(notifyExtVo);
			}
			
			if("C".equals(msgType)){
				NotifyCbVo notifyCbVo = new NotifyCbVo();
				notifyCbVo.setCbNotify(notify);
				notifyCbVo.setCbNotifySub(notifySub);
				notifyCbVo = notifyCbMapper.selectNotifyCb(notifyCbVo);
				statementVo.setNotifyCbVo(notifyCbVo);
			}
			
			if("D".equals(msgType)){
				NotifyCcVo notifyCcVo = new NotifyCcVo();
				notifyCcVo.setCbNotify(notify);
				notifyCcVo.setCbNotifySub(notifySub);
				notifyCcVo = notifyCcMapper.selectNotifyCc(notifyCcVo);
				statementVo.setNotifyCcVo(notifyCcVo);
			}
			
			if("3".equals(msgType) || "4".equals(msgType)){
				DrugNotifyVo drugNotifyVo = new DrugNotifyVo();
				drugNotifyVo.setDrugCd(errCd);
				drugNotifyVo = drugNotifyMapper.selectDrugNotify(drugNotifyVo);
				statementVo.setDrugNotifyVo(drugNotifyVo);
			}

			drugVo = drugMapper.selectCheckDrugInfo(drugVo);
			statementVo.setDrugVo(drugVo);
		}else if("1".equals(msgType) || "2".equals(msgType) || "F".equals(msgType) || "Y".equals(msgType)){ //수가
			if("1".equals(msgType) || "2".equals(msgType)){
				MedicalNotifyVo medicalNotifyVo = new MedicalNotifyVo();
				medicalNotifyVo.setMedicalCd(errCd);
				
				medicalNotifyVo = medicalNotifyMapper.selectMedicalNotify2(medicalNotifyVo);
				statementVo.setMedicalVo(medicalNotifyVo);
			}
			
			if("F".equals(msgType)){
				NotifyExtVo notifyExtVo = new NotifyExtVo();
				notifyExtVo.setExtNotify(notify);
				notifyExtVo.setExtNotifySub(notifySub);
				notifyExtVo = notifyExtMapper.selectNotifyExt(notifyExtVo);
				statementVo.setNotifyExtVo(notifyExtVo);
			}
			
			if("Y".equals(msgType)) {
				MedicalInsurVo medicalInsurVo = new MedicalInsurVo();
				medicalInsurVo.setMedicalInsurCd(errCd);
				List<DiseaVo> medicalInsurList = diseaMapper.selectMedicalMappingList(medicalInsurVo);
				statementVo.setDiseaList(medicalInsurList);
				NotifyExtVo notifyExtVo = new NotifyExtVo();
				notifyExtVo.setExtNotify(notify);
				notifyExtVo.setExtNotifySub(notifySub);
				notifyExtVo = notifyExtMapper.selectNotifyExt(notifyExtVo);
				statementVo.setNotifyExtVo(notifyExtVo);
			}
		}
		
		
		
		return statementVo;
	}
	
	private long createStatement(String table, String registerId, List<Map<String, SamVo>> recuperationCostAccountList, List<Map<String, SamVo>> tempMedicalStatementList,
			List<Map<String, SamVo>> tempDiseaseStatementList, List<Map<String, SamVo>> tempTreatmentStatementList, List<Map<String, SamVo>> tempPrescriptnStatementList,
			List<Map<String, SamVo>> tempSpecificDetailList, String encKey,String stsTitle,HttpServletRequest request) throws Exception {
		long recpCstClmSeq =0;
		RecuperationCostAccountVo recuperationCostAccountVo = null;
		MedicalStatementVo medicalStatementVo = null;
		DiseaseStatementVo diseaseStatementVo = null;
		TreatmentStatementVo treatmentStatementVo = null;
		PrescriptnStatementVo prescriptnStatementVo = null;
		SpecificDetailVo specificDetailVo = null;
		String hspId = "";
		
		int totalSize = recuperationCostAccountList.size() + tempMedicalStatementList.size()+tempDiseaseStatementList.size()
				+tempTreatmentStatementList.size()+tempPrescriptnStatementList.size()+tempSpecificDetailList.size();
		int currentSize = 0;
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		sessionVo.setItotalVal(totalSize);
		for (int i = 0; i < recuperationCostAccountList.size(); i++) {
			recuperationCostAccountVo = new RecuperationCostAccountVo();
			recuperationCostAccountVo.setRegisterId(registerId);
			recuperationCostAccountVo.setEncKey(encKey);
			Map<String, SamVo> recuperationCostAccountMap = recuperationCostAccountList.get(i);
			recuperationCostAccountVo.setRecuperationCostAccountMap(recuperationCostAccountMap, table);
			recuperationCostAccountVo.setStsTitle(stsTitle);
			recpCstClmSeq =  recuperationCostAccountMapper.createRecuperationCostAccount(recuperationCostAccountVo);
			
			if(recpCstClmSeq == 1) recpCstClmSeq = recuperationCostAccountVo.getRecpCstClmSeq();
			currentSize += 1;
		}

		List<MedicalStatementVo> medicalStatementList = new ArrayList<MedicalStatementVo>();

		for (int i = 0; i < tempMedicalStatementList.size(); i++) {
			medicalStatementVo = new MedicalStatementVo();
			medicalStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			medicalStatementVo.setRegisterId(registerId);
			medicalStatementVo.setEncKey(encKey);
			Map<String, SamVo> medicalStatementMap = tempMedicalStatementList.get(i);
			medicalStatementVo.setMedicalStatementMap(medicalStatementMap, table);
			hspId = medicalStatementVo.getHspId();
			medicalStatementList.add(medicalStatementVo);
			medicalStatementMapper.createMedicalStatement(medicalStatementVo);
			currentSize += 1;
			if(i%10 == 0){
				sessionVo.setIcurrentVal(currentSize);
				SessionManager.put(request, "USER", sessionVo);
			}
		}
		
		for (int i = 0; i < tempDiseaseStatementList.size(); i++) {
			diseaseStatementVo = new DiseaseStatementVo();
			diseaseStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			diseaseStatementVo.setRegisterId(registerId);
			Map<String, SamVo> diseaseStatementMap = tempDiseaseStatementList.get(i);
			diseaseStatementVo.setDiseaseStatementMap(diseaseStatementMap, hspId, table);
			diseaseStatementMapper.createDiseaseStatement(diseaseStatementVo);
			currentSize += 1;
			if(i%10 == 0){
				sessionVo.setIcurrentVal(currentSize);
				SessionManager.put(request, "USER", sessionVo);
			}
		}

		for (int i = 0; i < tempTreatmentStatementList.size(); i++) {
			treatmentStatementVo = new TreatmentStatementVo();
			treatmentStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			treatmentStatementVo.setRegisterId(registerId);
			Map<String, SamVo> treatmentStatementMap = tempTreatmentStatementList.get(i);
			treatmentStatementVo.setTreatmentStatementMap(treatmentStatementMap, hspId, table);
			treatmentStatementMapper.createTreatmentStatement(treatmentStatementVo);
			currentSize += 1;
			if(i%10 == 0){
				sessionVo.setIcurrentVal(currentSize);
				SessionManager.put(request, "USER", sessionVo);
			}
		}

		for (int i = 0; i < tempPrescriptnStatementList.size(); i++) {
			prescriptnStatementVo = new PrescriptnStatementVo();
			prescriptnStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			prescriptnStatementVo.setRegisterId(registerId);
			Map<String, SamVo> prescriptnStatementMap = tempPrescriptnStatementList.get(i);
			prescriptnStatementVo.setPrescriptnStatementMap(prescriptnStatementMap, hspId, table);
			prescriptnStatementMapper.createPrescriptnStatement(prescriptnStatementVo);
			currentSize += 1;
			if(i%10 == 0){
				sessionVo.setIcurrentVal(currentSize);
				SessionManager.put(request, "USER", sessionVo);
			}
		}

		for (int i = 0; i < tempSpecificDetailList.size(); i++) {
			specificDetailVo = new SpecificDetailVo();
			specificDetailVo.setRecpCstClmSeq(recpCstClmSeq);
			specificDetailVo.setRegisterId(registerId);
			Map<String, SamVo> specificDetailMap = tempSpecificDetailList.get(i);
			specificDetailVo.setSpecificDetailMap(specificDetailMap, hspId, table);
			specificDetailMapper.createSpecificDetail(specificDetailVo);
			currentSize += 1;
			if(i%10 == 0){
				sessionVo.setIcurrentVal(currentSize);
				SessionManager.put(request, "USER", sessionVo);
			}
		}
		
		logger.info("등록자 : " + registerId + "	TABLE : " + table + "	recuperationCostAccountList SIZE : " + recuperationCostAccountList.size());
		logger.info("등록자 : " + registerId + "	TABLE : " + table + "	medicalStatementList SIZE 		 : " + medicalStatementList.size());
		logger.info("등록자 : " + registerId + "	TABLE : " + table + "	diseaseStatementList SIZE 		 : " + tempDiseaseStatementList.size());
		logger.info("등록자 : " + registerId + "	TABLE : " + table + "	treatmentStatementList SIZE 	 : " + tempTreatmentStatementList.size());
		logger.info("등록자 : " + registerId + "	TABLE : " + table + "	prescriptnStatementList SIZE 	 : " + tempPrescriptnStatementList.size());
		logger.info("등록자 : " + registerId + "	TABLE : " + table + "	specificDetailList SIZE 		 : " + tempSpecificDetailList.size());
		return recpCstClmSeq;
	}	
	private String readSamFile(String filePath, String hspId, String userLevCd, List<Map<String, SamVo>> recuperationCostAccountList, List<Map<String, SamVo>> medicalStatementList,
			List<Map<String, SamVo>> diseaseStatementList, List<Map<String, SamVo>> treatmentStatementList, List<Map<String, SamVo>> prescriptnStatementList,
			List<Map<String, SamVo>> specificDetailList) {
		String result = "N";
		try {
			String clmDcmtVsn = "";
			String inputLine;
			boolean firstCheck = true;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "euc-kr"));
			Map<String, SamVo> samMap = null;
			while ((inputLine = br.readLine()) != null) {
				byte[] inputByte = inputLine.getBytes("euc-kr");
				if (firstCheck) {
					clmDcmtVsn = new String(inputByte, 0, 3, "euc-kr");
					
					if (hspId.equals(new String(inputByte, 20, 8, "euc-kr")) || "00001".equals(userLevCd)) {
						if("086".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray086.recuperationCostAccount, samMap, recuperationCostAccountList);// 요양급여비용청구서
						}else if("087".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray087.recuperationCostAccount, samMap, recuperationCostAccountList);// 요양급여비용청구서
						}else if("089".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray.recuperationCostAccount, samMap, recuperationCostAccountList);// 요양급여비용청구서
						}else{
							result = "서식 불일치 ("+clmDcmtVsn+")";
							return result;
						}
					} else {
						result = "요양기관 코드 불일치";
						return result;
					}
					firstCheck = false;
				} else {
					if ("A".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						if(clmDcmtVsn.equals("086")){
							getRecuperationCost(inputByte, SamArray086.medicalStatement, samMap, medicalStatementList);// 명세서(의치과)
						}else if("087".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray087.medicalStatement, samMap, medicalStatementList);// 명세서(의치과)
						}else{
							getRecuperationCost(inputByte, SamArray.medicalStatement, samMap, medicalStatementList);// 명세서(의치과)
						}																						
					} else if ("B".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						
						if(clmDcmtVsn.equals("086")){
							getRecuperationCost(inputByte, SamArray086.diseaseStatement, samMap, diseaseStatementList);// 명세서(상병)
						}else if("087".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray087.diseaseStatement, samMap, diseaseStatementList);// 명세서(상병)
						}else{
							getRecuperationCost(inputByte, SamArray.diseaseStatement, samMap, diseaseStatementList);// 명세서(상병)
						}
					} else if ("C".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						if(clmDcmtVsn.equals("086")){
							getRecuperationCost(inputByte, SamArray086.treatmentStatement, samMap, treatmentStatementList);// 명세서(진료)
						}else if("087".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray087.treatmentStatement, samMap, treatmentStatementList);// 명세서(진료)
						}else{
							getRecuperationCost(inputByte, SamArray.treatmentStatement, samMap, treatmentStatementList);// 명세서(진료)
						}
					} else if ("D".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						if(clmDcmtVsn.equals("086")){
							getRecuperationCost(inputByte, SamArray086.prescriptnStatement, samMap, prescriptnStatementList);// 명세서(처방)
						}else if("087".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray087.prescriptnStatement, samMap, prescriptnStatementList);// 명세서(처방)
						}else{
							getRecuperationCost(inputByte, SamArray.prescriptnStatement, samMap, prescriptnStatementList);// 명세서(처방)
						}
					} else if ("E".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						if(clmDcmtVsn.equals("086")){
							getRecuperationCost(inputByte, SamArray086.specificDetail, samMap, specificDetailList);// 명세서(특정내역)
						}else if("087".equals(clmDcmtVsn)){
							getRecuperationCost(inputByte, SamArray087.specificDetail, samMap, specificDetailList);// 명세서(특정내역)
						}else{
							getRecuperationCost(inputByte, SamArray.specificDetail, samMap, specificDetailList);// 명세서(특정내역)
						}
					}
				}
			}
			result = "Y";
			br.close();
			logger.info("StatementService  > readSamFile Version: " + clmDcmtVsn);
			
		} catch (Exception e) {
			result = "파일 형식 오류";
			logger.error("StatementService  > readSamFile : " + e.getMessage());
		}
		
		return result;
	}

	private void getRecuperationCost(byte[] inputByte, String[][] recuperationCost, Map<String, SamVo> map, List<Map<String, SamVo>> list) {

		try {
			int byteLength = inputByte.length; // 총사이즈
			int startPoint = 0; // 시작위치
			int endPoint = 0;
			int intLength = 0;
			int floatLength = 0;
			String key = "";
			String name = "";
			String value = "";
			String align = "";
			SamVo samVo;
			map = new HashMap<String, SamVo>();
			for (int i = 0; i < recuperationCost.length; i++) {
				samVo = new SamVo();
				intLength = Integer.parseInt(recuperationCost[i][2]);
				floatLength = Integer.parseInt(recuperationCost[i][3]);
				
				endPoint = intLength + floatLength;
				if (startPoint + endPoint > byteLength) { // 사이즈 예외처리
					endPoint = byteLength - (startPoint + endPoint);
					if (endPoint <= 0)
						endPoint = byteLength - startPoint;
				}
				key = recuperationCost[i][0];
				name = recuperationCost[i][1];

				value = new String(inputByte, startPoint, endPoint, "euc-kr");
				align = recuperationCost[i][4];
				if("1".equals(align)){
					value = value.replaceAll(" ", "0");
				}
				
				samVo.setKey(key);
				samVo.setName(name);
				samVo.setValue(value.trim());
				samVo.setAlign(align);
				samVo.setFloatLength(floatLength);
				samVo.setIntLength(intLength);
				map.put(key, samVo);
				startPoint += endPoint;
			}
			list.add(map);

		} catch (Exception e) {
			logger.error("StatementService  > getRecuperationCost : " + e.getMessage());
		}
	}
	private void autoCheck(long recpCstClmSeq,HttpServletRequest request){
		
		if(recpCstClmSeq!=0){// 등록
			List<ScreenMsgVo> list = screenMsgMapper.selectScreenStsList(recpCstClmSeq);
			if(list != null){
				if(list.size()>0){
					Map<String, String> screenMsg = new HashMap<String, String>();

					screenMsg.put("RECP_CST_CLM_SEQ", recpCstClmSeq+"");
					UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
					
					sessionVo.setCtotalVal(list.size());
					for(int i=0; i<list.size(); i++){
						sessionVo.setCcurrentVal(i+1);
						int sR = 0;
						ScreenMsgVo screenMsgVo = (ScreenMsgVo)list.get(i);
						ErrorCheckVo errorCheckVo = new ErrorCheckVo();
						StatementVo statementVo = new StatementVo();
						
						errorCheckVo.setRecpCstClmSeq(screenMsgVo.getRecpCstClmSeq());
						errorCheckVo.setClmNum(screenMsgVo.getClmNum());
						errorCheckVo.setHspId(screenMsgVo.getHspId());
						errorCheckVo.setStsSrlNum(screenMsgVo.getStsSrlNum());
						
						List<DiseaseStatementVo> dList = screenMsgMapper.selectDiseaseStatementList(screenMsgVo);				// 상병리스트
						try{
							if(dList==null || dList.size()==0){
								errorCheckVo = new ErrorCheckVo();
								errorCheckVo.setNotify("0000");
								errorCheckVo.setNotifySub("1");
								errorCheckVo.setParam1(screenMsgVo.getStsSrlNum());
								errorCheckVo.setStsSrlNum(screenMsgVo.getStsSrlNum());
								errorCheckVo = statementMapper.selectNotifyExtCheck(errorCheckVo);
								screenMsgVo.setNotify(errorCheckVo.getNotify());
								screenMsgVo.setNotifySub(errorCheckVo.getNotifySub());
								screenMsgVo.setCd(errorCheckVo.getCd());
								screenMsgVo.setmSeq(errorCheckVo.getmSeq());
								screenMsgVo.setMsg(errorCheckVo.getErrMsg());
								screenMsgVo.setMsgType(errorCheckVo.getMsgType());
								screenMsgVo.setMsgLev(errorCheckVo.getMsgLev());
								sR = screenMsgMapper.createScreenMsg(screenMsgVo);
								continue;
							}
							List<TreatmentStatementVo> tList = screenMsgMapper.selectTreatmentStatementList(screenMsgVo);		// 원내처방리스트
							List<PrescriptnStatementVo> pList = screenMsgMapper.selectPrescriptnStatementList(screenMsgVo);	// 원외처방리스트
							List<SpecificDetailVo> sList = screenMsgMapper.selectSpecificDetailList(screenMsgVo);	// 원외처방리스트
							
							statementVo.setTrtStartDt(dList.get(0).getTrtStartDt());//진료 시작일
							
							statementVo.setDiseaseStatementList(dList);
							statementVo.setTreatmentStatementList(tList);
							statementVo.setPrescriptnStatementList(pList);
							statementVo.setSpecificDetailList(sList);
							statementVo.setRegisterId(sessionVo.getRegisterId());
							errorCheckVo.setBirthy(screenMsgVo.getBirthDy());
						
						
							statementNotifyCheck.notifyCheck(statementVo,errorCheckVo);
							List<ErrorCheckVo> errorList =  statementVo.getErrorCheckList();
							logger.info("\t ERROR LIST:"+errorList.size());
							if(errorList.size()>0){
								for(int x=0; x<errorList.size(); x++){
									errorCheckVo = errorList.get(x);
									screenMsgVo.setNotify(errorCheckVo.getNotify());
									screenMsgVo.setNotifySub(errorCheckVo.getNotifySub());
									screenMsgVo.setCd(errorCheckVo.getCd());
									screenMsgVo.setmSeq(errorCheckVo.getmSeq());
									screenMsgVo.setMsg(errorCheckVo.getErrMsg());
									screenMsgVo.setMsgType(errorCheckVo.getMsgType());
									screenMsgVo.setMsgLev(errorCheckVo.getMsgLev());
									sR = screenMsgMapper.createScreenMsg(screenMsgVo);
								}
							}
							if(sR==1){
								logger.info("\tRECP_CST_CLM_SEQ:"+screenMsgVo.getRecpCstClmSeq()+"STS_SRL_NUM:" + screenMsgVo.getStsSrlNum() + "\tCD:"+errorCheckVo.getCd() +"\tM_SEQ:"+errorCheckVo.getmSeq()  );
							}
						}catch(Exception e){
							logger.error("\tRECP_CST_CLM_SEQ:"+screenMsgVo.getRecpCstClmSeq()+"STS_SRL_NUM:" + screenMsgVo.getStsSrlNum() + "\tCD:"+errorCheckVo.getCd() +"\tM_SEQ:"+errorCheckVo.getmSeq()  );						
						}
						SessionManager.put(request, "USER", sessionVo);
					}
				}
			}
		}
	}
	
	public FileVo selectFile(FileVo fileVo){
		return recuperationCostAccountMapper.selectFile(fileVo);
	}
}
