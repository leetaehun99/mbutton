package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.MsgVo;

public interface MsgMapper {
	public List<MsgVo> selectMsgList(MsgVo msgVo);
	public List<MsgVo> selectMsgJsonList(MsgVo msgVo);
	public MsgVo selectMsg(MsgVo msgVo);
	public int createMsg(MsgVo msgVo);
	public int updateMsg(MsgVo msgVo);
	public List<MsgVo> selectSubMsgList(MsgVo msgVo);
	public List<MsgVo> selectMsgExceptionCnt(MsgVo msgVo);
	public List<MsgVo> selectMsgCcCnt(MsgVo msgVo);
	public List<MsgVo> selectMsgCbCnt(MsgVo msgVo);
	public List<MsgVo> selectMsgMedicalCnt(MsgVo msgVo);
	public List<MsgVo> selectMsgDrugCnt(MsgVo msgVo);
	public List<MsgVo> selectMsgTrtCnt(MsgVo msgVo);
	public int deleteMsg(MsgVo msgVo);
}