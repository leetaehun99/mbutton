package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.MedicalStatementVo;

public interface MedicalStatementMapper {
	public void createMedicalStatement(MedicalStatementVo medicalStatementVo) ;
	public List<MedicalStatementVo> selectMedicalStatementSelectList(MedicalStatementVo medicalStatementVo);
	public List<MedicalStatementVo> selectMedicalStatementList(MedicalStatementVo medicalStatementVo);
	public List<MedicalStatementVo> selectMedicalStatementSelectList2(MedicalStatementVo medicalStatementVo);
	public List<MedicalStatementVo> selectMedicalStatementSelectList3(MedicalStatementVo medicalStatementVo);
	
	public MedicalStatementVo selectMedicalStatement(MedicalStatementVo medicalStatementVo);
	
	public int updateMedicalStatement(MedicalStatementVo medicalStatementVo);
	public void deleteStatement(String recpCstClmSeq);
}
