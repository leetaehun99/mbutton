package com.doo.mbutton.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.DrugMapper;
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;

@Service
public class DrugService {
	@Autowired
	private DrugMapper drugMapper;

	public List<DrugVo> selectDrugList(DrugVo drugVo) {
		return drugMapper.selectDrugList(drugVo);
	}

	// public DrugVo selectDrugInfo(DrugVo drugVo){
	// return drugMapper.selectDrugInfo(drugVo);
	// }

	public List<DrugVo> selectDrugCdList(DrugVo drugVo) {
		return drugMapper.selectDrugCdList(drugVo);
	}
	
	public DrugVo selectDrugInfo(DrugVo drugVo) {
		return drugMapper.selectDrugInfo(drugVo);
	}

	public int updateDrug(DrugVo drugVo) {
		int result = drugMapper.updateDrug(drugVo);
		result = drugMapper.updateDrugInfo(drugVo);
		return result;
	}

	public int updateCheckDrug(DrugVo drugVo) {
		return drugMapper.updateCheckDrug(drugVo);
	}

	public int createDrug(DrugVo drugVo) {
		int result = drugMapper.createDrug(drugVo);
		result = drugMapper.createDrugInfo(drugVo);
		return result;
	}
}