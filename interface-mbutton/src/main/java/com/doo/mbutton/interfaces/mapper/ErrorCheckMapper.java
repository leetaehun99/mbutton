package com.doo.mbutton.interfaces.mapper;

import java.util.List;
import java.util.Map;

import com.doo.mbutton.interfaces.model.DiseaVo;
import com.doo.mbutton.interfaces.model.DrugNotifyVo;
import com.doo.mbutton.interfaces.model.DrugVo;
import com.doo.mbutton.interfaces.model.MedicalNotifyVo;
import com.doo.mbutton.interfaces.model.NotifyCbVo;
import com.doo.mbutton.interfaces.model.NotifyCcVo;
import com.doo.mbutton.interfaces.model.NotifyExtVo;

public interface ErrorCheckMapper {
	
	public List<DiseaVo> selectDiseaMappingList(String drugCd);
	public NotifyExtVo selectNotifyExt(Map<String,Object> map);
	public NotifyCbVo selectNotifyCb(Map<String,Object> map);
	public NotifyCcVo selectNotifyCc(Map<String,Object> map);
	public DrugNotifyVo selectDrugNotify(String drugCd);
	public DrugVo selectCheckDrugInfo(String drugCd);
	public MedicalNotifyVo selectMedicalNotify(String medicalCd);
	
	
	
	
	
	
	
}

