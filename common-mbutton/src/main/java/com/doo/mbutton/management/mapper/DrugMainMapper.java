package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.DrugVo;


public interface DrugMainMapper {
	public List<DrugVo> selectDrugMainList(DrugVo drugVo);
	public DrugVo selectDrugMain(DrugVo drugVo);
	public int createDrugMain(DrugVo drugVo);
	public int updateDrugMain(DrugVo drugVo);
}
