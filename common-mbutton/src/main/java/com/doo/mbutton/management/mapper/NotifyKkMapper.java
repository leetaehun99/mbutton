package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.NotifyExtVo;
import com.doo.mbutton.management.model.NotifyKkVo;




public interface NotifyKkMapper {
	
	public List<NotifyKkVo> selectNotifyKkItemList(NotifyKkVo notifyKkVo);
	public int updateNotifyKk(NotifyKkVo notifyKkVo);
	public int insertNotifyKk(NotifyKkVo notifyKkVo);
	public int deleteNotifyKk(NotifyKkVo notifyKkVo);
	public int selectNotifyKkCnt(NotifyExtVo notifyExtVo);
}
