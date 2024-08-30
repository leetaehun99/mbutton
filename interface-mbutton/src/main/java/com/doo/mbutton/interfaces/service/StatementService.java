package com.doo.mbutton.interfaces.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.StatementNotifyCheck;
import com.doo.mbutton.interfaces.mapper.ErrorCheckMapper;
import com.doo.mbutton.interfaces.mapper.StatementMapper;
import com.doo.mbutton.interfaces.model.DiseaVo;
import com.doo.mbutton.interfaces.model.DiseaseStatementVo;
import com.doo.mbutton.interfaces.model.DrugVo;
import com.doo.mbutton.interfaces.model.ErrorCheckVo;
import com.doo.mbutton.interfaces.model.MedicalStatementVo;
import com.doo.mbutton.interfaces.model.NotifyExtVo;
import com.doo.mbutton.interfaces.model.PrescriptnStatementVo;
import com.doo.mbutton.interfaces.model.SpecificDetailVo;
import com.doo.mbutton.interfaces.model.StatementVo;
import com.doo.mbutton.interfaces.model.TreatmentStatementVo;

@Service
public class StatementService {
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	

	@Autowired
	StatementMapper statementMapper;
	@Autowired
	StatementNotifyCheck statementNotifyCheck;
	@Autowired
	ErrorCheckMapper errorCheckMapper;
	
	
	
	public List<Map<String,Object>> selectRecuperationCostAccountList(Map<String, Object> map) {
		
		 return statementMapper.selectRecuperationCostAccountList(map);
	}
	
	public List<Map<String,Object>> selectMedicalStatementSelectList(Map<String, Object> map) {
		
		 return statementMapper.selectMedicalStatementSelectList(map);
	}
	
	/*
	 * 명세서
	 */
	public StatementVo selectStatement(Map<String, Object> map) {
		
		StatementVo statementVo = new StatementVo();
		ErrorCheckVo errorCheckVo = new ErrorCheckVo();

		statementVo.setRecpCstClmSeq((String) map.get("recpCstClmSeq"));
		statementVo.setClmNum((String)map.get("clmNum"));
		statementVo.setHspId((String)map.get("hspId"));
		statementVo.setStsSrlNum((String)map.get("stsSrlNum"));

		errorCheckVo.setRecpCstClmSeq((String) map.get("recpCstClmSeq"));
		errorCheckVo.setClmNum((String)map.get("clmNum"));
		errorCheckVo.setHspId((String)map.get("hspId"));
		errorCheckVo.setStsSrlNum((String)map.get("stsSrlNum"));
		
		
		Map<String, Object> relativeValue = null;
		MedicalStatementVo medicalStatement = null;
		List<DiseaseStatementVo>  diseaseStatementList = null;
		List<TreatmentStatementVo> treatmentStatementList = null;
		List<PrescriptnStatementVo> prescriptnStatementList = null;
		List<SpecificDetailVo> specificDetailList = null;
		
		medicalStatement = statementMapper.selectMedicalStatement(map);			// 명세서
		diseaseStatementList = statementMapper.selectDiseaseStatementList(map);	// 상병
		
		statementVo.setTrtStartDt(diseaseStatementList.get(0).getTrtStartDt());//진료 시작일
		String year =statementVo.getTrtStartDt().toString().substring(0, 4);// 년도
		
		relativeValue = statementMapper.selectRelativeValue(year);
		map.put("rv1", relativeValue.get("RV1"));
		map.put("rv2", relativeValue.get("RV2"));
		map.put("rv3", relativeValue.get("RV3"));
		map.put("rv4", relativeValue.get("RV4"));
		map.put("rv5", relativeValue.get("RV5"));
		map.put("rv6", relativeValue.get("RV6"));
		map.put("rv7", relativeValue.get("RV7"));
		

		treatmentStatementList = statementMapper.selectTreatmentStatementList(map);		// 진료
		prescriptnStatementList = statementMapper.selectPrescriptnStatementList(map);	// 처방
		specificDetailList = statementMapper.selectSpecificDetailList(map);						// 특정내역
		
		statementVo.setMedicalStatementVo(medicalStatement);
		statementVo.setDiseaseStatementList(diseaseStatementList);
		statementVo.setTreatmentStatementList(treatmentStatementList);
		statementVo.setPrescriptnStatementList(prescriptnStatementList);
		statementVo.setSpecificDetailList(specificDetailList);
		
		statementNotifyCheck.notifyCheck(statementVo,errorCheckVo);
		System.out.println("============="+statementVo.getErrorCheckList().size());
		
		return statementVo;
	}
	
	/*
	 * 고시 호출
	 */
	public StatementVo selectCheckContents(String msgType,String errCd,String notify,String notifySub){
		
		//0:불필요 1:수가 3:약가 Z:약가(인증상병) E:약품예외 F:수가예외 C:병용 D:단일
		StatementVo statementVo = new StatementVo();
		statementVo.setMsgType(msgType);
		Map<String,Object> map = null;
		if("3".equals(msgType) || "4".equals(msgType) || "Z".equals(msgType) || "E".equals(msgType) || "C".equals(msgType) || "D".equals(msgType)){//약가
			
			
			if("Z".equals(msgType)){
				List<DiseaVo> diseaList = errorCheckMapper.selectDiseaMappingList(errCd);
				statementVo.setDiseaList(diseaList);
				map = new HashMap<String,Object>();
				map.put("notify", notify);
				map.put("notifySub", notifySub);
				statementVo.setNotifyExtVo(errorCheckMapper.selectNotifyExt(map));
			}
			
			if("E".equals(msgType)){
				map = new HashMap<String,Object>();
				map.put("notify", notify);
				map.put("notifySub", notifySub);
				statementVo.setNotifyExtVo(errorCheckMapper.selectNotifyExt(map));
			}
			
			if("C".equals(msgType)){
				map = new HashMap<String,Object>();
				map.put("notify", notify);
				map.put("notifySub", notifySub);
				statementVo.setNotifyCbVo(errorCheckMapper.selectNotifyCb(map));
			}
			
			if("D".equals(msgType)){
				map = new HashMap<String,Object>();
				map.put("notify", notify);
				map.put("notifySub", notifySub);
				statementVo.setNotifyCcVo(errorCheckMapper.selectNotifyCc(map));
			}
			
			if("3".equals(msgType) || "4".equals(msgType)){
				statementVo.setDrugNotifyVo(errorCheckMapper.selectDrugNotify(errCd));
			}
			/*DrugVo drugVo = errorCheckMapper.selectCheckDrugInfo(errCd);
			drugVo.setDrugEfficacy(drugVo.getDrugEfficacy().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
			drugVo.setDrugDosage(drugVo.getDrugDosage().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
			drugVo.setDrugTaboo(drugVo.getDrugTaboo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
			statementVo.setDrugVo(drugVo);*/
			
			
			statementVo.setDrugVo(errorCheckMapper.selectCheckDrugInfo(errCd));
		}else if("1".equals(msgType) || "2".equals(msgType) || "F".equals(msgType)){ //수가
			if("1".equals(msgType) || "2".equals(msgType)){
				statementVo.setMedicalNotifyVo(errorCheckMapper.selectMedicalNotify(errCd));
			}
			if("F".equals(msgType)){
				map = new HashMap<String,Object>();
				map.put("notify", notify);
				map.put("notifySub", notifySub);
				statementVo.setNotifyExtVo(errorCheckMapper.selectNotifyExt(map));
			}
		}
		return statementVo;
	}
	
}
