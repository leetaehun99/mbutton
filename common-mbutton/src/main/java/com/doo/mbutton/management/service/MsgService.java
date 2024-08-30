package com.doo.mbutton.management.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.MsgMapper;
import com.doo.mbutton.management.mapper.ScreenMsgMapper;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MsgVo;

@Service
public class MsgService {
	@Autowired
	MsgMapper msgMapper;
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	HistoryLogMapper historyLogMapper;
	
	@Autowired
	ScreenMsgMapper screenMsgMapper;
	
	public List<MsgVo> selectMsgList(MsgVo msgVo){
		return msgMapper.selectMsgList(msgVo);
	}
	
	public List<MsgVo> selectMsgJsonList(MsgVo msgVo){
		return msgMapper.selectMsgJsonList(msgVo);
	}

	public MsgVo selectMsg(MsgVo msgVo){
		return msgMapper.selectMsg(msgVo);
	}

	public int updateMsg(MsgVo msgVo) {
		return msgMapper.updateMsg(msgVo);
	}

	public int createMsg(MsgVo msgVo) {
		return msgMapper.createMsg(msgVo);
	}

	public List<MsgVo> selectSubMsgList(MsgVo msgVo) {
		return msgMapper.selectSubMsgList(msgVo);
	}
	
	public List<MsgVo> selectMsgCnt(MsgVo msgVo) {
		List<MsgVo> list = null;
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;
		boolean check5 = false;
		boolean check6 = false;
		List<MsgVo> resultList = new ArrayList<MsgVo>();
		Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getArrDel());
		msgVo.setAllSeq(resultMap.get(0));
		for(int i=0; i<resultMap.get(0).size(); i++) {
			//예외사항
			if("0".equals(resultMap.get(1).get(i)) || "E".equals(resultMap.get(1).get(i).toUpperCase()) 
			|| "Z".equals(resultMap.get(1).get(i).toUpperCase()) || "F".equals(resultMap.get(1).get(i).toUpperCase()) ) {
				list = msgMapper.selectMsgExceptionCnt(msgVo);
				if(check1 == false) {
					for(int j=0; j<list.size(); j++) {					
						MsgVo msg = list.get(j);
						resultList.add(msg);
					}
					check1 = true;
				}
			//단일금기
			}else if("D".equals(resultMap.get(1).get(i))) {
				list = msgMapper.selectMsgCcCnt(msgVo);
				if(check2 == false ) {
					for(int j=0; j<list.size(); j++) {
						MsgVo msg = list.get(j);
						resultList.add(msg);
					}
					check2 = true;
				}
			//병용금기
			}else if("C".equals(resultMap.get(1).get(i))) {
				list = msgMapper.selectMsgCbCnt(msgVo);
				if(check3 == false ) {
					for(int j=0; j<list.size(); j++) {
						MsgVo msg = list.get(j);
						resultList.add(msg);
					}
					check3 = true;
				}
			//수가고시
			}else if("1".equals(resultMap.get(1).get(i))) {
				list = msgMapper.selectMsgMedicalCnt(msgVo);
				if(check4 == false ) {
					for(int j=0; j<list.size(); j++) {
						MsgVo msg = list.get(j);
						resultList.add(msg);
					}
					check4 = true;
				}
			//약가고시	
			}else if("3".equals(resultMap.get(1).get(i))) {
				list = msgMapper.selectMsgDrugCnt(msgVo);
				if(check5 == false ) {
					for(int j=0; j<list.size(); j++) {
						MsgVo msg = list.get(j);
						resultList.add(msg);
					}
					check5 = true;
				}
			//치료대고시	
			}else if("8".equals(resultMap.get(1).get(i))) {
				list = msgMapper.selectMsgTrtCnt(msgVo);
				if(check6 == false ) {
					for(int j=0; j<list.size(); j++) {
						MsgVo msg = list.get(j);
						resultList.add(msg);
					}
					check6 = true;
				}
				
			}
			
		}
		
		return resultList;
	}
	
	public String changeKor(String str) {
		String result = "";
		if(str.equals("1")) {
			result = "수가";
		}else if(str.equals("2")) {
			result = "준용수가";
		}else if(str.equals("3")) {
			result = "보험등재약";
		}else if(str.equals("4")) {
			result = "원료약, 요양기관 자체 조제약";
		}else if(str.equals("C")) {
			result = "병용";
		}else if(str.equals("D")) {
			result = "단일";
		}else if(str.equals("E")) {
			result = "예외(약가)";
		}else if(str.equals("F")) {
			result = "예외(수가)";
		}else if(str.equals("Z")) {
			result = "약가(인증상병)";
		}else if(str.equals("8")) {
			result = "치료재료";
		}else if(str.equals("0")) {
			result = "불필요";
		}
		return result;
	}
	
	public int deleteMsg(MsgVo msgVo, HistoryLogVo historyLogVo) {

		int resultValue = 0;
		if(msgVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getArrDel());
			msgVo.setAllSeq(resultMap.get(0));
			resultValue = msgMapper.deleteMsg(msgVo);	
		}
		
		if(msgVo.getHistoryMsg() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++ ) {
				historyLogVo.setType('M');
				String korType = changeKor(resultMap.get(0).get(i));
				String msg = "MsgMapper.deleteMsg : 타입 - " + korType + " / 메시지 - " + resultMap.get(1).get(i);
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		
		return resultValue;
	}
	
	public int selectScreenMsgCnt(MsgVo msgVo) {
		Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getArrDel());
		msgVo.setAllSeq(resultMap.get(0));
		return screenMsgMapper.selectScreenMsgCnt(msgVo);
	}
	
}
