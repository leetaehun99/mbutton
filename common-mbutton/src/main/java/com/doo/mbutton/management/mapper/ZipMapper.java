package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.ZipVo;


public interface ZipMapper {
	public List<ZipVo> selectZipList(ZipVo zipVo);
}
