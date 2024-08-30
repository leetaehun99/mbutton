package com.doo.mbutton.common.mapper;

import java.util.List;

import com.doo.mbutton.common.model.GroupCodeVo;

public interface GroupCodeMapper {
	public List<GroupCodeVo> selectGroupCodeList(GroupCodeVo groupCodeVo);
	public GroupCodeVo selectGroupCode(GroupCodeVo groupCodeVo);
	public int createGroupCode(GroupCodeVo groupCodeVo);
	public int updateGroupCode(GroupCodeVo groupCodeVo);
	public String getGroupCd();
}
