package com.doo.mbutton.interfaces.mapper;

import java.util.List;
import java.util.Map;

import com.doo.mbutton.interfaces.model.DiseaseStatementVo;
import com.doo.mbutton.interfaces.model.ErrorCheckVo;
import com.doo.mbutton.interfaces.model.KkNotifyVo;
import com.doo.mbutton.interfaces.model.MedicalStatementVo;
import com.doo.mbutton.interfaces.model.PrescriptnStatementVo;
import com.doo.mbutton.interfaces.model.SpecificDetailVo;
import com.doo.mbutton.interfaces.model.TreatmentStatementVo;

public interface StatementMapper {
	
	public List<Map<String,Object>> selectRecuperationCostAccountList(Map<String, Object> map);
	public List<Map<String,Object>> selectMedicalStatementSelectList(Map<String, Object> map);
	
	public Map<String,Object> selectRelativeValue(String year);
	public MedicalStatementVo selectMedicalStatement(Map<String, Object> map);
	public List<DiseaseStatementVo> selectDiseaseStatementList(Map<String, Object> map);
	public List<TreatmentStatementVo> selectTreatmentStatementList(Map<String, Object> map);
	public List<PrescriptnStatementVo> selectPrescriptnStatementList(Map<String, Object> map);
	public List<SpecificDetailVo> selectSpecificDetailList(Map<String, Object> map);
	
	
	
	public List<ErrorCheckVo> selectDiseaValidationCheck1(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDiseaValidationCheck2(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDrugMsgCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectMedicalMsgCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDiseaCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectNotifyExt(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectCbCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectCcCheck(ErrorCheckVo errorCheckVo);
	public ErrorCheckVo selectNotifyExtCheck(ErrorCheckVo errorCheckVo);
	public ErrorCheckVo selectDrugNotifyCastExtCheck(ErrorCheckVo errorCheckVo);
	public double selectCastSum(ErrorCheckVo errorCheckVo);
	public ErrorCheckVo selectCastVal(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectCastValidationCheck(ErrorCheckVo errorCheckVo);
	public List<KkNotifyVo> selectKKList(ErrorCheckVo errorCheckVo);
	public List<KkNotifyVo> selectKkSum(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo>  selectNotifyKkExtCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectOneDayExtCheck(ErrorCheckVo errorCheckVo);
	
	
}
