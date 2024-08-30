package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.NotifyCcVo;

public interface NotifyCcMapper {
	public List<NotifyCcVo> selectNotifyCcList(NotifyCcVo notifyCcVo);
	public List<NotifyCcVo> selectNotifyCcItemList(NotifyCcVo notifyCcVo);
	
	public NotifyCcVo selectNotifyCc(NotifyCcVo notifyCcVo);
	public int updateNotifyCc(NotifyCcVo notifyCcVo);
	
	public int createNotifyCc(NotifyCcVo notifyCcVo);
	public int createNotifyCcItem(NotifyCcVo notifyCcVo);
	public int deleteNotifyCcItem(NotifyCcVo notifyCcVo);
	public List<NotifyCcVo> selectNotifyCcItemChk(NotifyCcVo notifyCcVo);
	
	public String selectNotifyCcMax();
	
	public int deleteNotifyCc(NotifyCcVo notifyCcVo);
	public List<String> selectNotifyCcCnt(NotifyCcVo notifyCcVo);
}