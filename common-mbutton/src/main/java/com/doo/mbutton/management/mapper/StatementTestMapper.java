package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.ErrorCheckVo;

public interface StatementTestMapper {
	public List<ErrorCheckVo> selectKCheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectACheck(ErrorCheckVo errorCheckVo);
	public List<ErrorCheckVo> selectDrugMsgCheck(ErrorCheckVo errorCheckVo);
	
	public int selectDrugCheck(ErrorCheckVo errorCheckVo);
	public int selectDiseaCheck(ErrorCheckVo errorCheckVo);
}
