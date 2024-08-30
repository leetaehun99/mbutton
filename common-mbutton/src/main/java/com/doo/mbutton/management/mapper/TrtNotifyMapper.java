package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.TrtNotifyVo;
import com.doo.mbutton.management.model.TrtVo;



public interface TrtNotifyMapper {
	public List<TrtNotifyVo> selectTrtNotifyList(TrtNotifyVo trtNotifyVo);
	public List<TrtNotifyVo> selectTrtNotifyGroupByList(TrtNotifyVo trtNotifyVo);
	
	public List<TrtNotifyVo> selectTrtNotifyItemList(TrtNotifyVo trtNotifyVo);
	public TrtNotifyVo selectTrtNotifyItem(TrtNotifyVo trtNotifyVo);
	public int createTrtNotifyItem(TrtNotifyVo trtNotifyVo);
	public int updateTrtNotifyItem(TrtNotifyVo trtNotifyVo);
	
	public TrtNotifyVo selectTrtNotify(TrtNotifyVo trtNotifyVo);
	public int createTrtNotify(TrtNotifyVo trtNotifyVo);
	public int updateTrtNotify(TrtNotifyVo trtNotifyVo);
	
	public int deleteTrtNotifyItem(TrtNotifyVo trtNotifyVo);
	public List<String> selectTrtNotifyCnt(TrtNotifyVo trtNotifyVo);
	public int deleteTrtNotify(TrtNotifyVo trtNotifyVo);
	
	public int deleteTMsg(TrtNotifyVo trtNotifyVo);
}
