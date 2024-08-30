package com.doo.mbutton.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.mapper.CodeMapper;
import com.doo.mbutton.common.model.CodeVo;
import com.googlecode.ehcache.annotations.Cacheable;

@Service
public class CodeService {
	@Autowired
	CodeMapper codeMapper;

	public List<CodeVo> selectCodeList(CodeVo codeVo){
		return codeMapper.selectCodeList(codeVo);
	}

	public CodeVo selectCode(CodeVo codeVo){
		return codeMapper.selectCode(codeVo);
	}

	@Cacheable(cacheName="basicCache")
	public List<CodeVo> selectTagCodeList(CodeVo codeVo){
		return codeMapper.selectTagCodeList(codeVo);
	}

	@Cacheable(cacheName="basicCache")
	public CodeVo selectTagCode(CodeVo codeVo){
		return codeMapper.selectTagCode(codeVo);
	}
	
	public int createCode(CodeVo codeVo){
		if("".equals(codeVo.getEctKey())) codeVo.setCd(codeMapper.getCd(codeVo));
		else codeVo.setCd(codeVo.getEctKey());
		return codeMapper.createCode(codeVo);
	}
	
	public int updateCode(CodeVo codeVo){
		return codeMapper.updateCode(codeVo);
	}
}
