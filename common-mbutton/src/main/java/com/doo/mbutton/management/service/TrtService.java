package com.doo.mbutton.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.TrtMapper;
import com.doo.mbutton.management.model.TrtVo;

@Service
public class TrtService {
	
	@Autowired
	private TrtMapper trtMapper;

	public List<TrtVo> selectTrtList(TrtVo trtVo){
		return trtMapper.selectTrtList(trtVo);
	}
	
	public TrtVo selectTrt(TrtVo trtVo){
		return trtMapper.selectTrt(trtVo);
	}
	
	public int createTrt(TrtVo trtVo){
		return trtMapper.createTrt(trtVo);
	}
		
	public int updateTrt(TrtVo trtVo){
		int result = trtMapper.updateTrt(trtVo);
		return result;
	}
}
