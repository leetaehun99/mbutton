package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.TrtMsgVo;
import com.doo.mbutton.management.model.TrtVo;

public interface TrtMsgMapper {
	public int createTrtMsg(TrtMsgVo trtMsgVo);
	public int deleteTrtMsg(TrtMsgVo trtMsgVo);
	public List<TrtMsgVo> selectTrtMsgList(TrtMsgVo trtMsgVo);
	
	public int deleteTrtMsgAll(TrtMsgVo trtMsgVo);
}
