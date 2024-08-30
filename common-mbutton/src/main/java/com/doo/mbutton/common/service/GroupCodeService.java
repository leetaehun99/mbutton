package com.doo.mbutton.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.mapper.GroupCodeMapper;
import com.doo.mbutton.common.model.GroupCodeVo;

@Service
public class GroupCodeService {
	
	@Autowired
	GroupCodeMapper groupCodeMapper;
	
	public List<GroupCodeVo> selectGroupCodeList(GroupCodeVo groupCodeVo){
		return groupCodeMapper.selectGroupCodeList(groupCodeVo);
	}
	
	public GroupCodeVo selectGroupCode(GroupCodeVo groupCodeVo){
		return groupCodeMapper.selectGroupCode(groupCodeVo);
	}
	
	public int createGroupCode(GroupCodeVo groupCodeVo){
		groupCodeVo.setGrpCd(groupCodeMapper.getGroupCd());
		return groupCodeMapper.createGroupCode(groupCodeVo);
	}
	
	public int updateGroupCode(GroupCodeVo groupCodeVo){
		return groupCodeMapper.updateGroupCode(groupCodeVo);
	}
}
