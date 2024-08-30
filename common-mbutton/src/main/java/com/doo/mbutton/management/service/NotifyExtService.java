package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.NotifyExtMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.NotifyExtVo;

@Service
public class NotifyExtService {

	@Autowired
	NotifyExtMapper notifyExtMapper;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	HistoryLogMapper historyLogMapper;
	
	public List<NotifyExtVo> selectNotifyExtList(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.selectNotifyExtList(notifyExtVo);
	}

	public List<NotifyExtVo> selectSubNotifyExtList(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.selectSubNotifyExtList(notifyExtVo);
	}

	public NotifyExtVo selectNotifyExt(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.selectNotifyExt(notifyExtVo);
	}

	public NotifyExtVo selectSubNotifyExt(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.selectSubNotifyExt(notifyExtVo);
	}

	public int createNotifyExt(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.createNotifyExt(notifyExtVo);
	}

	public int createSubNotifyExt(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.createSubNotifyExt(notifyExtVo);
	}

	public int updateSubNotifyExt(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.updateSubNotifyExt(notifyExtVo);
	}

	public int updateNotifyExt(NotifyExtVo notifyExtVo) {
		return notifyExtMapper.updateNotifyExt(notifyExtVo);
	}
	
	public int deleteSubNotifyExt(NotifyExtVo notifyExtVo, HistoryLogVo historyLogVo) {
		int result = 0;
		if(notifyExtVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyExtVo.getArrDel());
			notifyExtVo.setDrugAllCdList(resultMap.get(0));
			notifyExtVo.setExtNotifyAllList(resultMap.get(1));
			notifyExtVo.setExtNotifySubAllList(resultMap.get(2));
			result = notifyExtMapper.deleteSubNotifyExt(notifyExtVo);
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('0');
				String msg = "NotifyExtMapper.deleteSubNotifyExt : 약가코드 - ".concat(resultMap.get(0).get(i));
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		
		return result;
		
	}
	
	public List<NotifyExtVo> selectNotifyExtCnt(NotifyExtVo notifyExtVo) {
		Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyExtVo.getArrDel());
		notifyExtVo.setExtNotifyAllList(resultMap.get(0));
		return notifyExtMapper.selectNotifyExtCnt(notifyExtVo);
	}

	public int deleteNotifyExt(NotifyExtVo notifyExtVo, HistoryLogVo historyLogVo) {
		if(notifyExtVo.getHistoryMsg() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyExtVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('0');
				String msg = "NotifyExtMapper.deleteNotifyExt : 고시명 - ".concat(resultMap.get(0).get(i)).concat(" / 메시지 - ").concat(resultMap.get(1).get(i));
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		if(notifyExtVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyExtVo.getArrDel());
			notifyExtVo.setExtNotifyAllList(resultMap.get(0));
			notifyExtVo.setExtNotifySubAllList(resultMap.get(1));
		}
		
		return notifyExtMapper.deleteNotifyExt(notifyExtVo);
	}

}
