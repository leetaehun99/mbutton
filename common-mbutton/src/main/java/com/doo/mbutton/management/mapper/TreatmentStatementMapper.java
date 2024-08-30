package com.doo.mbutton.management.mapper;


import java.util.List;
import java.util.Map;

import com.doo.mbutton.management.model.TreatmentStatementVo;

public interface TreatmentStatementMapper {
	public void createTreatmentStatement(TreatmentStatementVo treatmentStatementVo) ;
	public List<TreatmentStatementVo> selectTreatmentStatementList(TreatmentStatementVo treatmentStatementVo);
	public List<TreatmentStatementVo> selectTreatmentStatementListTotal(TreatmentStatementVo treatmentStatementVo);
	
	public TreatmentStatementVo selectTreatmentStatement(TreatmentStatementVo treatmentStatementVo);
	
	public void deleteTreatmentStatement(TreatmentStatementVo treatmentStatementVo);
	
	public int updateTreatmentStatement(TreatmentStatementVo treatmentStatementVo);
	
	public List<TreatmentStatementVo> selectTreatmentStatementLnNumDescList(TreatmentStatementVo treatmentStatementVo);
	public List<TreatmentStatementVo> selectTreatmentStatementLnNumAscList(TreatmentStatementVo treatmentStatementVo);
	
	public int updateTreatmentStatementLnNum(Map<String, String> map);
	public void deleteStatement(String recpCstClmSeq);
	
}
