package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.DrugMsgVo;

public interface DrugMsgMapper {
	public int createDrugMsg(DrugMsgVo msgVo);
	public int deleteDrugMsg(DrugMsgVo msgVo);
	public List<DrugMsgVo> selectDrugMsgList(DrugMsgVo msgVo);
	
	public int deleteDrugMsgAll(DrugMsgVo msgVo);
}
