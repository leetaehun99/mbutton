package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.MedicalInsurVo;

public interface DiseaMapper {
	public List<DiseaVo> selectDiseaList(DiseaVo diseaVo);
	public List<DrugVo> selectDiseaMappingList(DiseaVo diseaVo);
	public List<DiseaVo> selectDiseaMappingListByDiseaCd(DiseaVo diseaVo);
	public List<DiseaVo> selectCheckDiseaMappingList(DiseaVo diseaVo);
	public List<DiseaVo> selectDiseaCdList(DiseaVo diseaVo);
	public DiseaVo selectDiseaInfo(DiseaVo diseaVo);
	public int updateDisea(DiseaVo diseaVo);
	public int createDisea(DiseaVo diseaVo);
	public int createDiseaMapping(DiseaVo diseaVo);
	public List<DiseaVo> selectDiseaMappingListCnt(DiseaVo diseaVo);
	public List<DiseaVo> selectDiseaMappingListByDrug(DrugVo drugVo);
	public List<DiseaVo> selectMedicalMappingListByMedicalInsur(MedicalInsurVo medicalInsurVo);
	public int createMedicalMapping(DiseaVo diseaVo);
	public List<DiseaVo> selectMedicalMappingListCnt(DiseaVo diseaVo);
	public List<DiseaVo> selectMedicalMappingList(MedicalInsurVo medicalInsurVo);
	public int deleteDiseaMapping(DiseaVo diseaVo);
	public int createDiseaMappingByDrugCd(DrugVo drugVo);
	public List<DiseaVo> selectDiseaMappingByDrugCdListCnt(DrugVo drugVo);
	public int deleteMedicalMapping(DiseaVo diseaVo);
}
