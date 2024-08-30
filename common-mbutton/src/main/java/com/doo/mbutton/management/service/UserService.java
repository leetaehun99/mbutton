package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.UserMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.UserVo;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	HistoryLogMapper historyLogMapper;
	@Autowired
	CommonUtil commonUtil;
	
	public List<UserVo> selectUserList(UserVo userVo){
		return userMapper.selectUserList(userVo);
	}
	
	public UserVo selectUser(UserVo userVo){
		return userMapper.selectUser(userVo);
	}
	
	public int createUser(UserVo userVo){
		return userMapper.createUser(userVo);
	}
	
	public int updateUser(UserVo userVo){
		return userMapper.updateUser(userVo);
	}
	
	public int deleteUser(UserVo userVo, HistoryLogVo historyLogVo) {
		int resultValue = userMapper.deleteUser(userVo);
		
		Map<Integer, List<String>> resultMap = commonUtil.allDel(userVo.getHistoryMsg());
		for(int i=0; i<resultMap.get(0).size(); i++) {
			historyLogVo.setType('U');
			String msg = "UserMapper.deleteUser : 요양기관코드 - " + resultMap.get(0).get(i) + " / 병원명 - " + resultMap.get(0).get(i);
			historyLogVo.setMsg(msg);
			historyLogMapper.createHistoryLog(historyLogVo);
		}
		return resultValue;
	}
}
