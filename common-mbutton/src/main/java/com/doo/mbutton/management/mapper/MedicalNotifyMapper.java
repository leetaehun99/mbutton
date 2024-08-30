package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.MedicalMsgVo;
import com.doo.mbutton.management.model.MedicalNotifyVo;



public interface MedicalNotifyMapper {
	public List<MedicalNotifyVo> selectMedicalNotifyList(MedicalNotifyVo medicalNotifyVo);
	public List<MedicalNotifyVo> selectMedicalNotifyGroupByList(MedicalNotifyVo medicalNotifyVo);
	
	public List<MedicalNotifyVo> selectMedicalNotifyItemList(MedicalNotifyVo medicalNotifyVo);
	public MedicalNotifyVo selectMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo);
	public int createMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo);
	public int updateMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo);
	
	public MedicalNotifyVo selectMedicalNotify(MedicalNotifyVo medicalNotifyVo);
	public MedicalNotifyVo selectMedicalNotify2(MedicalNotifyVo medicalNotifyVo);
	
	public int createMedicalNotify(MedicalNotifyVo medicalNotifyVo);
	public int updateMedicalNotify(MedicalNotifyVo medicalNotifyVo);
	
	public int deleteMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo);
	
	public List<String> selectMedicalNotifyCnt(MedicalNotifyVo medicalNotifyVo);
	public int deleteMedicalNotify(MedicalNotifyVo medicalNotifyVo);
	
	public int deleteMMsg(MedicalNotifyVo medicalNotifyVo);
}
