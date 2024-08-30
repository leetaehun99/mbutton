package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.DiseaseStatementVo;

public interface DiseaseStatementMapper {
	public void createDiseaseStatement(DiseaseStatementVo diseaseStatementVo) ;
	
	public List<DiseaseStatementVo> selectDiseaseStatementList(DiseaseStatementVo diseaseStatementVo);
	
	public List<DiseaseStatementVo> selectDiseaseStatementListTotal(DiseaseStatementVo diseaseStatementVo);
	
	public DiseaseStatementVo selectDiseaseStatement(DiseaseStatementVo diseaseStatementVo);
	
	public int updateDiseaseStatement(DiseaseStatementVo diseaseStatementVo);
	/*
	 * 주상병 부상병 변환
	 */
	public int updateDiseaDivdCd(DiseaseStatementVo diseaseStatementVo);
	
	public void deleteDiseaseStatement(DiseaseStatementVo diseaseStatementVo);
	
	public void deleteStatement(String recpCstClmSeq);
}
