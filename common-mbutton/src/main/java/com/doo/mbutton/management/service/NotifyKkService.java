package com.doo.mbutton.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.NotifyKkMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.NotifyExtVo;
import com.doo.mbutton.management.model.NotifyKkVo;

@Service
public class NotifyKkService{
	@Autowired
	NotifyKkMapper notifyKkMapper;
	@Autowired
	HistoryLogMapper historyLogMapper;

	public List<NotifyKkVo> selectNotifyKkItemList(NotifyKkVo notifyKkVo) {
		return notifyKkMapper.selectNotifyKkItemList(notifyKkVo);
	}

	public int updateNotifyKk(NotifyKkVo notifyKkVo) {
		int result = notifyKkMapper.updateNotifyKk(notifyKkVo);
		return result;
	}

	public int insertNotifyKk(NotifyKkVo notifyKkVo) {
		int result = notifyKkMapper.insertNotifyKk(notifyKkVo);
		return result;
	}

	public int deleteNotifyKk(NotifyKkVo notifyKkVo, HistoryLogVo historyLogVo) {
		int result = 0 ;
		if(notifyKkVo.getArrDel() != null) {
			result = notifyKkMapper.deleteNotifyKk(notifyKkVo);
			for(int i=0; i<notifyKkVo.getArrDel().length; i++) {
				historyLogVo.setType('0');
				String msg = "NotifyKkMapper.deleteNotifyKk : 주성분코드 - ".concat(notifyKkVo.getArrDel()[i]);
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		return result;
	}

	public int selectNotifyKkCnt(NotifyExtVo notifyExtVo) {
		int result = notifyKkMapper.selectNotifyKkCnt(notifyExtVo);
		return result;
	}
}