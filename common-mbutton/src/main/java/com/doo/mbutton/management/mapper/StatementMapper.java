package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.ErrorCheckVo;
import com.doo.mbutton.management.model.KkNotifyVo;

public interface StatementMapper {
	public List<ErrorCheckVo> selectDiseaValidationCheck1(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDiseaValidationCheck2(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDrugMsgCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectMedicalMsgCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDiseaCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectNotifyExt(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectCbCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectCcCheck(ErrorCheckVo errorCheckVo);
	public ErrorCheckVo selectNotifyExtCheck(ErrorCheckVo errorCheckVo);
	public ErrorCheckVo selectDrugNotifyCastExtCheck(ErrorCheckVo errorCheckVo);
	public double selectCastSum(ErrorCheckVo errorCheckVo);
	public ErrorCheckVo selectCastVal(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectCastValidationCheck(ErrorCheckVo errorCheckVo);
	public List<KkNotifyVo> selectKKList(ErrorCheckVo errorCheckVo);
	public List<KkNotifyVo> selectKkSum(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo>  selectNotifyKkExtCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectOneDayExtCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectMedicalCheck(ErrorCheckVo errorCheckVo);
	
	
	
	
	
	
	
	
}
