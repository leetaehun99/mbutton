package com.doo.mbutton.common.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.mapper.UserMapper;
import com.doo.mbutton.management.model.UserVo;

@Service
public class IndexService {
	@Autowired
	UserMapper userMapper;
	
	public int createUser(UserVo userVo){
		userVo.setUserLevCd("00003");
		userVo.setUserStatCd("N");
		userVo.setRegisterId("SYSTEM");//최초등록
		return userMapper.createUser(userVo);
		//String userIdx = userVo.getUserId();
	}
	
	public UserVo loginCheck(HttpServletRequest request, UserVo userVo){
		UserVo resultVo  = userMapper.selectUser(userVo);
		String result = "00000";
		
		if(resultVo==null){
			result = "00001"; //존재하지 않는 ID
		}else{
			if(!resultVo.getUserPwd().equals(userVo.getUserPwd())){
				result = "00002"; //패스워드 불일치
			}
			
			if("N".equals(resultVo.getUserStatCd())){
				result = "00003"; //사용 중지 상태
			}
		}
		
		if(result == "00000"){
			resultVo.setResultCd(result);
			resultVo.setResultUrl("/index/main.doo");
			SessionManager.put(request, "USER", resultVo);
		}else{
			resultVo = new UserVo();
			resultVo.setResultCd(result);
			resultVo.setResultUrl("");
		}
		return resultVo;
		
	}
}
