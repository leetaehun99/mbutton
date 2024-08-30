package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.DiseaseStatementVo;
import com.doo.mbutton.management.model.ErrorCheckVo;
import com.doo.mbutton.management.model.MsgVo;
import com.doo.mbutton.management.model.PrescriptnStatementVo;
import com.doo.mbutton.management.model.ScreenMsgVo;
import com.doo.mbutton.management.model.SpecificDetailVo;
import com.doo.mbutton.management.model.TreatmentStatementVo;


public interface ScreenMsgMapper {
	public List<ScreenMsgVo> selectScreenStsList(long recpCstClmSeq);
	
	public List<DiseaseStatementVo> selectDiseaseStatementList(ScreenMsgVo screenMsgVo);	
	public List<TreatmentStatementVo> selectTreatmentStatementList(ScreenMsgVo screenMsgVo);
	public List<PrescriptnStatementVo> selectPrescriptnStatementList(ScreenMsgVo screenMsgVo);	
	public List<SpecificDetailVo> selectSpecificDetailList(ScreenMsgVo screenMsgVo);	
	public int createScreenMsg(ScreenMsgVo screenMsgVo);	
	
	public List<ErrorCheckVo> selectErrorCheckList(ErrorCheckVo errorCheckVo);
	public void deleteStatement(String recpCstClmSeq);
	public List<ScreenMsgVo> selectStatementResult(ScreenMsgVo screenMsgVo);
	public int selectScreenMsgCnt(MsgVo msgVo);
}