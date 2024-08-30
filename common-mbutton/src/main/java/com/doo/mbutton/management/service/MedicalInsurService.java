package com.doo.mbutton.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.MedicalInsurMapper;
import com.doo.mbutton.management.model.MedicalInsurVo;

@Service
public class MedicalInsurService {
	
	@Autowired
	private MedicalInsurMapper medicalInsurMapper;
	
	public List<MedicalInsurVo> selectMedicalInsurList(MedicalInsurVo medicalInsurVo){
		return medicalInsurMapper.selectMedicalInsurList(medicalInsurVo);
	}

	public MedicalInsurVo selectMedicalInsur(MedicalInsurVo medicalInsurVo){
		return medicalInsurMapper.selectMedicalInsur(medicalInsurVo);
	}
	
	public int createMedicalInsur(MedicalInsurVo medicalInsurVo){
		return medicalInsurMapper.createMedicalInsur(medicalInsurVo);
	}
		
	public int updateMedicalInsur(MedicalInsurVo medicalInsurVo){
		int result = medicalInsurMapper.updateMedicalInsur(medicalInsurVo);
		return result;
	}
	
	public List<MedicalInsurVo> selectInsurScreeningList(MedicalInsurVo medicalInsurVo){
		return medicalInsurMapper.selectInsurScreeningList(medicalInsurVo);
	}

	public MedicalInsurVo selectInsurScreening(MedicalInsurVo medicalInsurVo){
		return medicalInsurMapper.selectInsurScreening(medicalInsurVo);
	}
	
	public int createInsurScreening(MedicalInsurVo medicalInsurVo){
		medicalInsurVo.setMedicalInsurSeq(medicalInsurMapper.getMedicalInsurSeq());
		return medicalInsurMapper.createInsurScreening(medicalInsurVo);
	}
		
	public int updateInsurScreening(MedicalInsurVo medicalInsurVo){
		int result = medicalInsurMapper.updateInsurScreening(medicalInsurVo);
		return result;
	}
}
