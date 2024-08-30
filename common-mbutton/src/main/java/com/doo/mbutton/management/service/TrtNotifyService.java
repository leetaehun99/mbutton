package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.TrtMsgMapper;
import com.doo.mbutton.management.mapper.TrtNotifyMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MsgVo;
import com.doo.mbutton.management.model.TrtMsgVo;
import com.doo.mbutton.management.model.TrtNotifyVo;

@Service
public class TrtNotifyService {
	
	@Autowired
	private TrtNotifyMapper trtNotifyMapper;
	@Autowired
	private TrtMsgMapper trtMsgMapper;
	@Autowired
	private HistoryLogMapper historyLogMapper;
	@Autowired
	private CommonUtil commonUtil;

	public List<TrtNotifyVo> selectTrtNotifyList(TrtNotifyVo trtNotifyVo){
		return trtNotifyMapper.selectTrtNotifyList(trtNotifyVo);
	}
	public List<TrtNotifyVo> selectTrtNotifyGroupByList(TrtNotifyVo trtNotifyVo){
		return trtNotifyMapper.selectTrtNotifyGroupByList(trtNotifyVo);
	}

	public List<TrtNotifyVo> selectTrtNotifyItemList(TrtNotifyVo trtNotifyVo){
		return trtNotifyMapper.selectTrtNotifyItemList(trtNotifyVo);
	}
	
	public TrtNotifyVo selectTrtNotify(TrtNotifyVo trtNotifyVo){
		return trtNotifyMapper.selectTrtNotify(trtNotifyVo);
	}
	
	public TrtNotifyVo selectTrtNotifyItem(TrtNotifyVo trtNotifyVo){
		
		TrtMsgVo msgVo = new TrtMsgVo();
		msgVo.setTrtNotify(trtNotifyVo.getTrtNotify());
		msgVo.setTrtNotifySub(trtNotifyVo.getTrtNotifySub());
		msgVo.setTrtCd(trtNotifyVo.getTrtCd());
		
		trtNotifyVo = trtNotifyMapper.selectTrtNotifyItem(trtNotifyVo);
		
		return trtNotifyVo;
	}
	
	public int createTrtNotify(TrtNotifyVo trtNotifyVo){
		return trtNotifyMapper.createTrtNotify(trtNotifyVo);
	}
		
	public int updateTrtNotify(TrtNotifyVo trtNotifyVo){
		int result = trtNotifyMapper.updateTrtNotify(trtNotifyVo);
		return result;
	}
	
	public int createTrtNotifyItem(TrtNotifyVo trtNotifyVo) throws Exception{
		int result = 0;
		TrtNotifyVo noti  = trtNotifyMapper.selectTrtNotifyItem(trtNotifyVo);
		if(noti == null){
			result = trtNotifyMapper.createTrtNotifyItem(trtNotifyVo);
			/*if(result == 1){
				TrtMsgVo msgVo = new TrtMsgVo();
				
				String[] arrayMsg = trtNotifyVo.getMsgArray();
				String[] arrayMsgLev = trtNotifyVo.getMsgLevArray();
				String[] arrayMsgSort = trtNotifyVo.getMsgSortArray();

				msgVo.setTrtNotify(trtNotifyVo.getTrtNotify());
				msgVo.setTrtNotifySub(trtNotifyVo.getTrtNotifySub());
				msgVo.setTrtCd(trtNotifyVo.getTrtCd());
				msgVo.setUpdaterId(trtNotifyVo.getUpdaterId());
				if(arrayMsgLev!=null){
					for(int i=0; i<arrayMsgLev.length; i++){
						if(arrayMsgLev[i]!=""){
							msgVo.setMsg(arrayMsg[i]);
							msgVo.setMsgLev(arrayMsgLev[i]);
							msgVo.setMsgSort(arrayMsgSort[i]);
							trtMsgMapper.createTrtMsg(msgVo);
						}
					}
				}
				
			}*/
		}else{
			result = 99;
		}
		return result;
	}
	
	public int updateTrtNotifyItem(TrtNotifyVo trtNotifyVo) throws Exception{
		int result = 0;
		result = trtNotifyMapper.updateTrtNotifyItem(trtNotifyVo);
		if(result == 1){
			TrtMsgVo msgVo = new TrtMsgVo();
			
			String[] arrayMsg = trtNotifyVo.getMsgArray();
			String[] arrayMsgLev = trtNotifyVo.getMsgLevArray();
			String[] arrayMsgSort = trtNotifyVo.getMsgSortArray();
			String[] arrayMsgSpecialCd = trtNotifyVo.getMsgSpecialCd();

			msgVo.setTrtNotify(trtNotifyVo.getTrtNotify());
			msgVo.setTrtNotifySub(trtNotifyVo.getTrtNotifySub());
			msgVo.setTrtCd(trtNotifyVo.getTrtCd());
			msgVo.setUpdaterId(trtNotifyVo.getUpdaterId());
			trtMsgMapper.deleteTrtMsg(msgVo);
			if(arrayMsgLev!=null){
				for(int i=0; i<arrayMsgLev.length; i++){
					if(arrayMsgLev[i]!=""){
						msgVo.setMsg(arrayMsg[i]);
						msgVo.setMsgLev(arrayMsgLev[i]);
						msgVo.setMsgSort(arrayMsgSort[i]);
						msgVo.setSpecialCd(arrayMsgSpecialCd[i]);
						trtMsgMapper.createTrtMsg(msgVo);
					}
				}
			}
		}
		return result;
	}
	
	public int updateTrtNotifyuseYnItem(TrtNotifyVo trtNotifyVo) throws Exception{
		return trtNotifyMapper.updateTrtNotifyItem(trtNotifyVo);
	}
	
	public int createTrtgMsg(TrtMsgVo trtMsg) {
		return trtMsgMapper.createTrtMsg(trtMsg);
	}
	
	public int deleteTrtMsg(TrtMsgVo trtMsg) {
		return trtMsgMapper.deleteTrtMsg(trtMsg);
	}
	
	public int deleteTrtMsgAll(MsgVo msgVo, HistoryLogVo historyLogVo) {
		TrtMsgVo trtMsgVo = new TrtMsgVo();
		if(msgVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getArrDel());
			trtMsgVo.setmSeq(msgVo.getmSeq());
			trtMsgVo.setAllTrtNotify(resultMap.get(0));
			trtMsgVo.setAllTrtNotifySub(resultMap.get(1));
			trtMsgVo.setAllTrtCd(resultMap.get(2));
		}
		
		if(msgVo.getHistoryMsg() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('2');
				String msg = "MsgMapper.deleteMedicalMsg : 코드 - " + resultMap.get(0).get(i) + " / 명칭 - " + resultMap.get(1).get(i);
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		return trtMsgMapper.deleteTrtMsgAll(trtMsgVo);
	}
	
	public int deleteTrtNotifyItem(TrtNotifyVo trtNotifyVo, HistoryLogVo historyLogVo) {
		trtNotifyMapper.deleteTMsg(trtNotifyVo);
		int result = trtNotifyMapper.deleteTrtNotifyItem(trtNotifyVo);
		if(trtNotifyVo.getHistoryMsg() != null) {
			for(int i=0; i<trtNotifyVo.getHistoryMsg().length; i++) {
				historyLogVo.setType('3');
				String msg = "TrtNotifyMapper.deleteTrtNotifyItem : 치료대코드 - ".concat(trtNotifyVo.getArrDel()[i].concat(" / 치료대명 - ").concat(trtNotifyVo.getHistoryMsg()[i]));
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		return result;
	}
	
	public List<String> selectTrtNotifyCnt(TrtNotifyVo trtNotifyVo) {
		if(trtNotifyVo.getArrDel() != null ) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(trtNotifyVo.getArrDel());
			trtNotifyVo.setAllTrtNotify(resultMap.get(0));
			trtNotifyVo.setAllTrtNotifySub(resultMap.get(1));
		}
		return trtNotifyMapper.selectTrtNotifyCnt(trtNotifyVo);
	}
	
	public int deleteTrtNotify(TrtNotifyVo trtNotifyVo, HistoryLogVo historyLogVo) {
		if(trtNotifyVo.getHistoryMsg() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(trtNotifyVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('3');
				String msg = "TrtNotifyMapper.deleteTrtNotify : 치료대코드 - ".concat(resultMap.get(0).get(i)).concat(" / 치료대명 - ").concat(resultMap.get(1).get(i));
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		if(trtNotifyVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(trtNotifyVo.getArrDel());
			trtNotifyVo.setAllTrtNotify(resultMap.get(0));
			trtNotifyVo.setAllTrtNotifySub(resultMap.get(1));
		}
		return trtNotifyMapper.deleteTrtNotify(trtNotifyVo);
	}
}
