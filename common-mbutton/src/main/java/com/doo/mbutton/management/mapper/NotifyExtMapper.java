package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.NotifyExtVo;

public interface NotifyExtMapper {
	public List<NotifyExtVo> selectSubNotifyExtList(NotifyExtVo notifyExtVo);
	public List<NotifyExtVo> selectNotifyExtList(NotifyExtVo notifyExtVo);
	public NotifyExtVo selectNotifyExt(NotifyExtVo notifyExtVo);
	public NotifyExtVo selectSubNotifyExt(NotifyExtVo notifyExtVo);
	public int createNotifyExt(NotifyExtVo notifyExtVo);
	public int createSubNotifyExt(NotifyExtVo notifyExtVo);
	public int updateNotifyExt(NotifyExtVo notifyExtVo);
	public int updateSubNotifyExt(NotifyExtVo notifyExtVo);
	public int deleteSubNotifyExt(NotifyExtVo notifyExtVo);
	public List<NotifyExtVo> selectNotifyExtCnt(NotifyExtVo notifyExtVo);
	public int deleteNotifyExt(NotifyExtVo notifyExtVo);
}