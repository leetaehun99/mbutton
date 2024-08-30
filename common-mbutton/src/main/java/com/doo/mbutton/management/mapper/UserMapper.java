package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.UserVo;


public interface UserMapper {
	public List<UserVo> selectUserList(UserVo userVo);
	public UserVo selectUser(UserVo userVo);
	public int createUser(UserVo userVo);
	public int updateUser(UserVo userVo);
	public String getUserId(UserVo userVo);
	public int deleteUser(UserVo userVo);
}
