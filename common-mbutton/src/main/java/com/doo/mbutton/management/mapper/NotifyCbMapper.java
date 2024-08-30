package com.doo.mbutton.management.mapper;

import java.util.List;
import com.doo.mbutton.management.model.NotifyCbVo;

public interface NotifyCbMapper {
	public List<NotifyCbVo> selectNotifyCbList(NotifyCbVo notifyCbVo);
	public List<NotifyCbVo> selectNotifyCbItemList(NotifyCbVo notifyCbVo);
	
	public List<NotifyCbVo> selectNotifyCbItemList1(NotifyCbVo notifyCbVo);
	public List<NotifyCbVo> selectNotifyCbItemList2(NotifyCbVo notifyCbVo);
	public List<NotifyCbVo> selectNotifyCbItemChk(NotifyCbVo notifyCbVo);
	
	public NotifyCbVo selectNotifyCb(NotifyCbVo notifyCbVo);
	public int updateNotifyCb(NotifyCbVo notifyCbVo);
	public int createNotifyCb(NotifyCbVo notifyCbVo);
	public int createNotifyCbItem(NotifyCbVo notifyCbVo);
	public int deleteNotifyCbItem(NotifyCbVo notifyCbVo);
	
	public String selectNotifyCbMax();
	public int deleteNotifyCb(NotifyCbVo notifyCbVo);
	public List<String> selectNotifyCbCnt(NotifyCbVo notifyCbVo);
	
}
