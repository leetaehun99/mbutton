package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.NotifyCcMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.NotifyCcVo;

@Service
public class NotifyCcService {
	@Autowired
	NotifyCcMapper notifyCcMapper;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	HistoryLogMapper historyLogMapper;
	
	public List<NotifyCcVo> selectNotifyCcList(NotifyCcVo notifyCcVo){
		return notifyCcMapper.selectNotifyCcList(notifyCcVo);
	}
	
	public List<NotifyCcVo> selectNotifyCcItemList(NotifyCcVo notifyCcVo){
		return notifyCcMapper.selectNotifyCcItemList(notifyCcVo);
	}
	
	public NotifyCcVo selectNotifyCc(NotifyCcVo notifyCcVo){
		return notifyCcMapper.selectNotifyCc(notifyCcVo);
	}
	
	public int updateNotifyCc(NotifyCcVo notifyCcVo){
		int result = notifyCcMapper.updateNotifyCc(notifyCcVo);
		return result;
	}
	
	public int createNotifyCc(NotifyCcVo notifyCcVo){
		int result = notifyCcMapper.createNotifyCc(notifyCcVo);
		return result;
	}
	
	public String selectNotifyCcMax(){
		return notifyCcMapper.selectNotifyCcMax();
	}
	
	public int createNotifyCcItem(NotifyCcVo notifyCcVo){
		notifyCcMapper.deleteNotifyCcItem(notifyCcVo);
		int result = notifyCcMapper.createNotifyCcItem(notifyCcVo);
		return result;
	}
	
	public int deleteNotifyCcItem(NotifyCcVo notifyCcVo, HistoryLogVo historyLogVo){
		int result = notifyCcMapper.deleteNotifyCcItem(notifyCcVo);
		
		for(int i=0; i<notifyCcVo.getArrDel().length; i++) {
			String msg = "NotifyCcMapper.deleteNotifyCcItem : 주성분코드 - ".concat(notifyCcVo.getArrDel()[i]);
			historyLogVo.setMsg(msg);
			historyLogVo.setType('5');
			historyLogMapper.createHistoryLog(historyLogVo);
		}
		return result;
	}
	
	public List<NotifyCcVo> selectNotifyCcItemChk(NotifyCcVo notifyCcVo){
		return notifyCcMapper.selectNotifyCcItemChk(notifyCcVo);
	}
	
	//delete삭제부터 다시
	public int deleteNotifyCc(NotifyCcVo notifyCcVo, HistoryLogVo historyLogVo) {
		for(int i=0; i<notifyCcVo.getHistoryMsg().length; i++) {
			if(!notifyCcVo.getHistoryMsg()[i].isEmpty()) {
				historyLogVo.setType('5');
				String msg = "NotifyCcMapper.deleteNotifyCc : 주성분명 - ".concat(notifyCcVo.getHistoryMsg()[i]);
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
				System.out.println(i + " : " + notifyCcVo.getHistoryMsg()[i]);
			}
		}
		if(notifyCcVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyCcVo.getArrDel());
			notifyCcVo.setAllCbNotify(resultMap.get(0));
			notifyCcVo.setAllCbNotifySub(resultMap.get(1));
		}
		int result = notifyCcMapper.deleteNotifyCc(notifyCcVo);
		return result;
	}
	
	public List<String> selectNotifyCcCnt(NotifyCcVo notifyCcVo) {
		Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyCcVo.getArrDel());
		notifyCcVo.setAllCbNotify(resultMap.get(0));
		notifyCcVo.setAllCbNotifySub(resultMap.get(1));
		return notifyCcMapper.selectNotifyCcCnt(notifyCcVo);
	}
}