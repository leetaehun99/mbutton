package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.DictionaryVo;




public interface DictionaryMapper {
	public List<DictionaryVo> selectDisea(DictionaryVo dictionaryVo);
	public List<DictionaryVo> selectDictionary(DictionaryVo dictionaryVo);
}
