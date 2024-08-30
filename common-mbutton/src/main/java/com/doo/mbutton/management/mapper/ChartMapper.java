package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.ChartVo;

public interface ChartMapper {
	public List<ChartVo> selectDiseaStsChart(); 
	public List<ChartVo> selectDiseaStsSubjectChart();
	public List<ChartVo> selectDiseaMappingTypeChart();
	public List<Integer> selectDiseaStsSubjectAvgChart();
	public List<ChartVo> selectBillChart(String hspId);
}
