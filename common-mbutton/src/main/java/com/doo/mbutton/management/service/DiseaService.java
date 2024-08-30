package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.DiseaMapper;
import com.doo.mbutton.management.mapper.DrugMapper;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MedicalInsurVo;

@Service
public class DiseaService {
	
	@Autowired
	private DiseaMapper diseaMapper;
	
	@Autowired
	private DrugMapper drugMapper;
	
	@Autowired
	HistoryLogMapper historyLogMapper;
	
	@Autowired
	CommonUtil commonUtil;

	public List<DiseaVo> selectDiseaList(DiseaVo diseaVo) {
		return diseaMapper.selectDiseaList(diseaVo);
	}
	
	public List<DrugVo> selectDiseaMappingList(DiseaVo diseaVo){
		return diseaMapper.selectDiseaMappingList(diseaVo);
	}

	public List<DiseaVo> managerDisea(DiseaVo diseaVo) {
		return diseaMapper.selectDiseaMappingListByDiseaCd(diseaVo);
	}
	
	public List<DiseaVo> selectCheckDiseaMappingList(DiseaVo diseaVo) {
		return diseaMapper.selectCheckDiseaMappingList(diseaVo);
	}
	
	public List<DiseaVo> selectDiseaCdList(DiseaVo diseaVo) {
		return diseaMapper.selectDiseaCdList(diseaVo);
	}
	
	public DiseaVo selectDiseaInfo(DiseaVo diseaVo) {
		return diseaMapper.selectDiseaInfo(diseaVo);
	}
	
	public int updateDisea(DiseaVo diseaVo) {
		return diseaMapper.updateDisea(diseaVo);
	}

	public int createDisea(DiseaVo diseaVo) {
		return diseaMapper.createDisea(diseaVo);
	}

	public int createDiseaMapping(DiseaVo diseaVo) {
		return diseaMapper.createDiseaMapping(diseaVo);
	}
	
	public List<DiseaVo> selectDiseaMappingListCnt(DiseaVo diseaVo) {
		return diseaMapper.selectDiseaMappingListCnt(diseaVo);
	}
	
	public List<DiseaVo> selectDiseaMappingListByDrug(DrugVo drugVo) {
		drugVo.setMainDrugCd(drugMapper.selectMainDrugCd(drugVo.getDrugCd()));
		return diseaMapper.selectDiseaMappingListByDrug(drugVo);
	}
	
	public List<DiseaVo> selectMedicalMappingListByMedicalInsur(MedicalInsurVo medicalInsurVo) {
		return diseaMapper.selectMedicalMappingListByMedicalInsur(medicalInsurVo);
	}
	
	public int createMedicalMapping(DiseaVo diseaVo) {
		return diseaMapper.createMedicalMapping(diseaVo);
	}
	
	public List<DiseaVo> selectMedicalMappingListCnt(DiseaVo diseaVo) {
		return diseaMapper.selectMedicalMappingListCnt(diseaVo);
	}
	
	
	public int deleteDiseaMapping(DiseaVo diseaVo, HistoryLogVo historyLogVo) throws Exception{
		
		int result = 0;
		if(diseaVo.getArrDel() != null) {
			Map <Integer, List<String>> resultMap = commonUtil.allDel(diseaVo.getArrDel());
			if(diseaVo.getIsAllDisea() == 'Y') {
				diseaVo.setAllDiseaCd(resultMap.get(0));
			}else {
				diseaVo.setAllDrugCd(resultMap.get(0));
			}
			result = diseaMapper.deleteDiseaMapping(diseaVo);
			if(result >= 1){
				for(int i=0; i<resultMap.get(0).size(); i++) {
					if(!resultMap.get(0).get(i).isEmpty()) {
						historyLogVo.setType('9');//인증상병(약가)
						String msg = "";
						if(diseaVo.getIsAllDisea() == 'Y') {
							msg = "diseaMapper.deleteDiseaMapping : 상병코드 - ".concat(resultMap.get(0).get(i)).concat(" / 약품코드 - ").concat(diseaVo.getDrugCd());
						}else {
							msg = "diseaMapper.deleteDiseaMapping : 상병코드 - ".concat(diseaVo.getDiseaCd()).concat(" / 약품코드 - ").concat(resultMap.get(0).get(i));
						}
						historyLogVo.setMsg(msg);
						historyLogMapper.createHistoryLog(historyLogVo);
					}
				}
			}
		}
		return result;
	}
	
	public List<DiseaVo> selectDiseaMappingByDrugCdListCnt(DrugVo drugVo) {
		return diseaMapper.selectDiseaMappingByDrugCdListCnt(drugVo);
	}
	
	public int createDiseaMappingByDrugCd(DrugVo drugVo) {
		return diseaMapper.createDiseaMappingByDrugCd(drugVo);
	}
	
	public int deleteMedicalMapping(DiseaVo diseaVo, HistoryLogVo historyLogVo) throws Exception{
		
		int result = 0;
		if(diseaVo.getArrDel() != null) {
			Map <Integer, List<String>> resultMap = commonUtil.allDel(diseaVo.getArrDel());
			diseaVo.setAllDiseaCd(resultMap.get(0));
			result = diseaMapper.deleteMedicalMapping(diseaVo);
			if(result >= 1){
				for(int i=0; i<resultMap.get(0).size(); i++) {
					if(!resultMap.get(0).get(i).isEmpty()) {
						historyLogVo.setType('8');//인증상병(수가)
						String msg = "diseaMapper.deleteMedicalMapping : 상병코드 - ".concat(resultMap.get(0).get(i)).concat(" / 수가코드 - ").concat(diseaVo.getMedicalInsurCd());
						historyLogVo.setMsg(msg);
						historyLogMapper.createHistoryLog(historyLogVo);
					}
				}
			}
		}
		return result;
	}
}
