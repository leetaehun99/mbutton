package com.doo.mbutton.common.mapper;

import java.util.List;

import com.doo.mbutton.common.model.CodeVo;

public interface CodeMapper {
	public List<CodeVo> selectCodeList(CodeVo codeVo);
	public CodeVo selectCode(CodeVo codeVo);
	public List<CodeVo> selectTagCodeList(CodeVo codeVo);
	public CodeVo selectTagCode(CodeVo codeVo);
	public int createCode(CodeVo codeVo);
	public int updateCode(CodeVo CodeVo);
	public String getCd(CodeVo codeVo);
}
