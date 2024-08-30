package com.doo.mbutton.management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doo.mbutton.management.mapper.DictionaryMapper;
import com.doo.mbutton.management.model.DictionaryVo;

@Service
public class DictionaryService {
	@Autowired
	DictionaryMapper dictionaryMapper;
	
	public List<DictionaryVo> selectDisea(DictionaryVo dictionaryVo){
		return dictionaryMapper.selectDisea(dictionaryVo);
	}
	
	public List<DictionaryVo> selectDictionary(DictionaryVo dictionaryVo){
		return dictionaryMapper.selectDictionary(dictionaryVo);
	}
}
