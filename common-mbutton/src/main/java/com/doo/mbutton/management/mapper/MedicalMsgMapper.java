package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.MedicalMsgVo;

public interface MedicalMsgMapper {
	public int createMedicalMsg(MedicalMsgVo medicalMsgVo);
	public int deleteMedicalMsg(MedicalMsgVo medicalMsgVo);
	public List<MedicalMsgVo> selectMedicalMsgList(MedicalMsgVo medicalMsgVo);

	public int deleteMedicalMsgAll(MedicalMsgVo medicalMsgVo);
}
