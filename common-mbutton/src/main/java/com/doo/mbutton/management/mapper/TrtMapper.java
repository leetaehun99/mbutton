package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.TrtVo;

public interface TrtMapper {
	public List<TrtVo> selectTrtList(TrtVo trtVo);
	
	public TrtVo selectTrt(TrtVo trtVo);
	public int createTrt(TrtVo trtVo);
	public int updateTrt(TrtVo trtVo);
	
}
