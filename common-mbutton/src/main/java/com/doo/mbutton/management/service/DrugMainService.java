package com.doo.mbutton.management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.DrugMainMapper;
import com.doo.mbutton.management.model.DrugVo;


@Service
public class DrugMainService {
	@Autowired
	private DrugMainMapper drugMainMapper;
	
	public List<DrugVo> selectDrugMainList(DrugVo drugVo){
		return drugMainMapper.selectDrugMainList(drugVo);
	}
	
	public DrugVo selectDrugMain(DrugVo drugVo){
		return drugMainMapper.selectDrugMain(drugVo);
	}
	
	public int createDrugMain(DrugVo drugVo){
		return drugMainMapper.createDrugMain(drugVo);
	}
		
	public int updateDrugMain(DrugVo drugVo){
		int result = drugMainMapper.updateDrugMain(drugVo);
		return result;
	}
}
