package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.NotifyCbMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.NotifyCbVo;

@Service
public class NotifyCbService {
	@Autowired
	NotifyCbMapper notifyCbMapper;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	HistoryLogMapper historyLogMapper;
	
	public List<NotifyCbVo> selectNotifyCbList(NotifyCbVo notifyCbVo){
		return notifyCbMapper.selectNotifyCbList(notifyCbVo);
	}
	
	public List<NotifyCbVo> selectNotifyCbItemList(NotifyCbVo notifyCbVo){
		return notifyCbMapper.selectNotifyCbItemList(notifyCbVo);
	}
	
	public List<NotifyCbVo> selectNotifyCbItemList1(NotifyCbVo notifyCbVo){
		return notifyCbMapper.selectNotifyCbItemList1(notifyCbVo);
	}
	
	public List<NotifyCbVo> selectNotifyCbItemList2(NotifyCbVo notifyCbVo){
		return notifyCbMapper.selectNotifyCbItemList2(notifyCbVo);
	}
	
	public NotifyCbVo selectNotifyCb(NotifyCbVo notifyCbVo){
		return notifyCbMapper.selectNotifyCb(notifyCbVo);
	}
	
	public List<NotifyCbVo> selectNotifyCbItemChk(NotifyCbVo notifyCbVo){
		return notifyCbMapper.selectNotifyCbItemChk(notifyCbVo);
	}
	
	public int createNotifyCbItem(NotifyCbVo notifyCbVo){
		notifyCbMapper.deleteNotifyCbItem(notifyCbVo);
		int result = notifyCbMapper.createNotifyCbItem(notifyCbVo);
		return result;
	}
	
	public int deleteNotifyCbItem(NotifyCbVo notifyCbVo, HistoryLogVo historyLogVo){
		
		int result = 0;
		if(notifyCbVo.getArrDel() != null) {
			Map <Integer, List<String>> resultMap = commonUtil.allDel(notifyCbVo.getArrDel());
			notifyCbVo.setMainDrugCdList1(resultMap.get(0));
			notifyCbVo.setMainDrugCdList2(resultMap.get(1));
			result = notifyCbMapper.deleteNotifyCbItem(notifyCbVo);
			for(int i=0; i<resultMap.get(0).size(); i++) {
				if(!resultMap.get(0).get(i).isEmpty()) {
					historyLogVo.setType('4');
					String msg = "NotifyCbMapper.deleteNotifyCbItem : 주성분코드1 - ".concat(resultMap.get(0).get(i)).concat(" / 주성분코드2 - ").concat(resultMap.get(1).get(i));
					historyLogVo.setMsg(msg);
					historyLogMapper.createHistoryLog(historyLogVo);
				}
			}
		}
		return result;
	}
	
	public int updateNotifyCb(NotifyCbVo notifyCbVo){
		int result = notifyCbMapper.updateNotifyCb(notifyCbVo);
		return result;
	}
	
	public int createNotifyCb(NotifyCbVo notifyCbVo){
		int result = notifyCbMapper.createNotifyCb(notifyCbVo);
		return result;
	}
	
	public String selectNotifyCbMax(){
		return notifyCbMapper.selectNotifyCbMax();
	}
	
	public int deleteNotifyCb(NotifyCbVo notifyCbVo, HistoryLogVo historyLogVo) {
		if(notifyCbVo.getHistoryMsg() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyCbVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('4');
				String msg = "NotifyCbMapper.deleteNotifyCb : 주성분명1 - ".concat(resultMap.get(0).get(i)).concat(" / 주성분명2 - ").concat(resultMap.get(1).get(i));
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		
		if(notifyCbVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyCbVo.getArrDel());
			notifyCbVo.setAllCbNotify(resultMap.get(0));
			notifyCbVo.setAllCbNotifySub(resultMap.get(1));
		}
		int result = notifyCbMapper.deleteNotifyCb(notifyCbVo);
		return result;
	}
	
	public List<String> selectNotifyCbCnt(NotifyCbVo notifyCbVo) {
		Map<Integer, List<String>> resultMap = commonUtil.allDel(notifyCbVo.getArrDel());
		notifyCbVo.setAllCbNotify(resultMap.get(0));
		notifyCbVo.setAllCbNotifySub(resultMap.get(1));
		return notifyCbMapper.selectNotifyCbCnt(notifyCbVo);
	}
}