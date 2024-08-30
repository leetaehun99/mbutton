package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.DrugNotifyVo;

public interface DrugNotifyMapper {
	public List<DrugNotifyVo> selectDrugNotifyList(DrugNotifyVo drugNotifyVo);
	public List<DrugNotifyVo> selectDrugNotifyGroupByList(DrugNotifyVo drugNotifyVo);
	
	public List<DrugNotifyVo> selectDrugNotifyItemList(DrugNotifyVo drugNotifyVo);
	public DrugNotifyVo selectDrugNotifyItem(DrugNotifyVo drugNotifyVo);
	public int createDrugNotifyItem(DrugNotifyVo drugNotifyVo);
	public int updateDrugNotifyItem(DrugNotifyVo drugNotifyVo);
	
	public DrugNotifyVo selectDrugNotify(DrugNotifyVo drugNotifyVo);
	public DrugNotifyVo selectDrugNotify2(DrugNotifyVo drugNotifyVo);
	
	public int createDrugNotify(DrugNotifyVo drugNotifyVo);
	public int updateDrugNotify(DrugNotifyVo drugNotifyVo);
	
	public int deleteDrugNotifyItem(DrugNotifyVo drugNotifyVo);
	public List<String> selectDrugNotifyCnt(DrugNotifyVo drugNotifyVo);
	public int deleteDrugNotify(DrugNotifyVo drugNotifyVo);
	
	public int deleteDMsg(DrugNotifyVo drugNotifyVo);
	
}
