package com.doo.mbutton.management.mapper;


import java.util.List;
import java.util.Map;

import com.doo.mbutton.management.model.SpecificDetailVo;

public interface SpecificDetailMapper {
	public int createSpecificDetail(SpecificDetailVo specificDetailVo) ;	
	public List<SpecificDetailVo> selectSpecificDetailList(SpecificDetailVo specificDetailVo);
	public List<SpecificDetailVo> selectSpecificDetailListTotal(SpecificDetailVo specificDetailVo);
	
	public SpecificDetailVo selectSpecificDetail(SpecificDetailVo specificDetailVo);
	
	
	public List<String> selectSpecificDetailLnNumList(SpecificDetailVo specificDetailVo);
	
	public int updateSpecificDetailLnNum(Map<String, String> map);
	public int deleteSpecificDetailLnNum(SpecificDetailVo specificDetailVo);
	
	public int updateSpecificDetail(SpecificDetailVo specificDetailVo);
	public int deleteSpecificDetail(SpecificDetailVo specificDetailVo);
	public void deleteStatement(String recpCstClmSeq);
	
}
