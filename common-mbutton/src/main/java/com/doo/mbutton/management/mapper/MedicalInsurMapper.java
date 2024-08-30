package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.MedicalInsurVo;



public interface MedicalInsurMapper {
	public List<MedicalInsurVo> selectMedicalInsurList(MedicalInsurVo medicalInsurVo);
	
	public MedicalInsurVo selectMedicalInsur(MedicalInsurVo medicalInsurVo);
	public int createMedicalInsur(MedicalInsurVo medicalInsurVo);
	public int updateMedicalInsur(MedicalInsurVo medicalInsurVo);
	
	public List<MedicalInsurVo> selectInsurScreeningList(MedicalInsurVo medicalInsurVo);
	
	public MedicalInsurVo selectInsurScreening(MedicalInsurVo medicalInsurVo);
	public int updateInsurScreening(MedicalInsurVo medicalInsurVo);
	public int createInsurScreening(MedicalInsurVo medicalInsurVo);
	
	public String getMedicalInsurSeq();
}
