package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;

public interface DrugMapper {
	public List<DrugVo> selectDrugList(DrugVo drugVo);
	public List<DrugVo> selectDrugCdList(DrugVo drugVo);
	public DrugVo selectDrugInfo(DrugVo drugVo);
	public int updateDrug(DrugVo drugVo);
	public int updateDrugInfo(DrugVo drugVo);
	public int updateCheckDrug(DrugVo drugVo);
	public int createDrug(DrugVo drugVo);
	public int createDrugInfo(DrugVo drugVo);
	
	public String selectMainDrugCd(String drugCd);
	//심사 Check
	public DrugVo selectCheckDrugInfo(DrugVo drugVo);
}
