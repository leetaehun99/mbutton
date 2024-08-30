package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.mapper.MedicalMsgMapper;
import com.doo.mbutton.management.mapper.MedicalNotifyMapper;
import com.doo.mbutton.management.model.DrugMsgVo;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MedicalMsgVo;
import com.doo.mbutton.management.model.MedicalNotifyVo;
import com.doo.mbutton.management.model.MsgVo;



@Service
public class MedicalNotifyService {
	
	@Autowired
	private MedicalNotifyMapper medicalNotifyMapper;
	@Autowired
	private MedicalMsgMapper medicalMsgMapper;
	@Autowired
	private CommonUtil commonUtil;
	@Autowired
	private HistoryLogMapper historyLogMapper;

	public List<MedicalNotifyVo> selectMedicalNotifyList(MedicalNotifyVo medicalNotifyVo){
		return medicalNotifyMapper.selectMedicalNotifyList(medicalNotifyVo);
	}
	public List<MedicalNotifyVo> selectMedicalNotifyGroupByList(MedicalNotifyVo medicalNotifyVo){
		return medicalNotifyMapper.selectMedicalNotifyGroupByList(medicalNotifyVo);
	}

	public List<MedicalNotifyVo> selectMedicalNotifyItemList(MedicalNotifyVo medicalNotifyVo){
		return medicalNotifyMapper.selectMedicalNotifyItemList(medicalNotifyVo);
	}
	
	public MedicalNotifyVo selectMedicalNotify(MedicalNotifyVo medicalNotifyVo){
		return medicalNotifyMapper.selectMedicalNotify(medicalNotifyVo);
	}
	
	public MedicalNotifyVo selectMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo){
		
		MedicalMsgVo msgVo = new MedicalMsgVo();
		msgVo.setMedicalNotify(medicalNotifyVo.getMedicalNotify());
		msgVo.setMedicalNotifySub(medicalNotifyVo.getMedicalNotifySub());
		msgVo.setMedicalCd(medicalNotifyVo.getMedicalCd());
		
		medicalNotifyVo = medicalNotifyMapper.selectMedicalNotifyItem(medicalNotifyVo);
		
		
		return medicalNotifyVo;
	}
	
	public int createMedicalNotify(MedicalNotifyVo medicalNotifyVo){
		return medicalNotifyMapper.createMedicalNotify(medicalNotifyVo);
	}
		
	public int updateMedicalNotify(MedicalNotifyVo medicalNotifyVo){
		int result = medicalNotifyMapper.updateMedicalNotify(medicalNotifyVo);
		return result;
	}
	
	public int createMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo) throws Exception{
		int result = 0;
		MedicalNotifyVo noti  = medicalNotifyMapper.selectMedicalNotifyItem(medicalNotifyVo);
		if(noti == null){
			result = medicalNotifyMapper.createMedicalNotifyItem(medicalNotifyVo);
			/*if(result == 1){
				MedicalMsgVo msgVo = new MedicalMsgVo();
				
				String[] arrayMsg = medicalNotifyVo.getMsgArray();
				String[] arrayMsgLev = medicalNotifyVo.getMsgLevArray();
				String[] arrayMsgSort = medicalNotifyVo.getMsgSortArray();

				msgVo.setMedicalNotify(medicalNotifyVo.getMedicalNotify());
				msgVo.setMedicalNotifySub(medicalNotifyVo.getMedicalNotifySub());
				msgVo.setMedicalCd(medicalNotifyVo.getMedicalCd());
				msgVo.setUpdaterId(medicalNotifyVo.getUpdaterId());
				if(arrayMsgLev!=null){
					for(int i=0; i<arrayMsgLev.length; i++){
						if(arrayMsgLev[i]!=""){
							msgVo.setMsg(arrayMsg[i]);
							msgVo.setMsgLev(arrayMsgLev[i]);
							msgVo.setMsgSort(arrayMsgSort[i]);
							medicalMsgMapper.createMedicalMsg(msgVo);
						}
					}
				}
				
			}*/
		}else{
			result = 99;
		}
		return result;
	}
	

	public int createMedicalMsg(MedicalMsgVo medicalMsgVo) {
		return medicalMsgMapper.createMedicalMsg(medicalMsgVo);
	}
	
	public int updateMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo) throws Exception{
		int result = 0;
		result = medicalNotifyMapper.updateMedicalNotifyItem(medicalNotifyVo);
		/*if(result == 1){
			MedicalMsgVo msgVo = new MedicalMsgVo();
			
			String[] arrayMsg = medicalNotifyVo.getMsgArray();
			String[] arrayMsgLev = medicalNotifyVo.getMsgLevArray();
			String[] arrayMsgSort = medicalNotifyVo.getMsgSortArray();
			String[] arrayMsgSpecialCd = medicalNotifyVo.getMsgSpecialCd();

			msgVo.setMedicalNotify(medicalNotifyVo.getMedicalNotify());
			msgVo.setMedicalNotifySub(medicalNotifyVo.getMedicalNotifySub());
			msgVo.setMedicalCd(medicalNotifyVo.getMedicalCd());
			msgVo.setUpdaterId(medicalNotifyVo.getUpdaterId());
			medicalMsgMapper.deleteMedicalMsg(msgVo);
			if(arrayMsgLev!=null){
				for(int i=0; i<arrayMsgLev.length; i++){
					if(arrayMsgLev[i]!=""){
						msgVo.setMsg(arrayMsg[i]);
						msgVo.setMsgLev(arrayMsgLev[i]);
						msgVo.setMsgSort(arrayMsgSort[i]);
						msgVo.setSpecialCd(arrayMsgSpecialCd[i]);
						medicalMsgMapper.createMedicalMsg(msgVo);
					}
				}
			}
		}*/
		return result;
	}
	
	public int updateMedicalNotifyuseYnItem(MedicalNotifyVo medicalNotifyVo) throws Exception{
		return medicalNotifyMapper.updateMedicalNotifyItem(medicalNotifyVo);
	}
	
	public int deleteMedicalMsg(MedicalMsgVo medicalMsg) {
		return medicalMsgMapper.deleteMedicalMsg(medicalMsg);
	}
	
	public int deleteMedicalMsgAll(MsgVo msgVo, HistoryLogVo historyLogVo) {
		MedicalMsgVo medicalMsgVo = new MedicalMsgVo();
		if(msgVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getArrDel());
			medicalMsgVo.setmSeq(msgVo.getmSeq());
			medicalMsgVo.setAllMedicalNotify(resultMap.get(0));
			medicalMsgVo.setAllMedicalNotifySub(resultMap.get(1));
			medicalMsgVo.setAllMedicalCd(resultMap.get(2));
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
		return medicalMsgMapper.deleteMedicalMsgAll(medicalMsgVo);
	}
	
	public int deleteMedicalNotifyItem(MedicalNotifyVo medicalNotifyVo, HistoryLogVo historyLogVo) {
		medicalNotifyMapper.deleteMMsg(medicalNotifyVo);
		int result = medicalNotifyMapper.deleteMedicalNotifyItem(medicalNotifyVo);
		
		for(int i=0; i<medicalNotifyVo.getArrDel().length; i++) {
			String msg = "MedicalNotifyMapper.deleteMedicalNotifyItem : ".concat(medicalNotifyVo.getArrDel()[i]);
			historyLogVo.setMsg(msg);
			historyLogVo.setType('2');
			historyLogMapper.createHistoryLog(historyLogVo);
		}
		
		return result;
	}
	
	public List<String> selectMedicalNotifyCnt(MedicalNotifyVo medicalNotifyVo) {
		Map<Integer, List<String>> resultMap = commonUtil.allDel(medicalNotifyVo.getArrDel());
		medicalNotifyVo.setAllMedicalNotify(resultMap.get(0));
		medicalNotifyVo.setAllMedicalNotifySub(resultMap.get(1));
		return medicalNotifyMapper.selectMedicalNotifyCnt(medicalNotifyVo);
	}
	
	public int deleteMedicalNotify(MedicalNotifyVo medicalNotifyVo, HistoryLogVo historyLogVo) {
		Map<Integer, List<String>> resultMap1 = commonUtil.allDel(medicalNotifyVo.getHistoryMsg());
		for(int i=0; i<resultMap1.get(0).size(); i++) {
			historyLogVo.setType('2');
			String msg = "MedicalNotifyMapper.deleteMedicalNotify : 수가코드 - ".concat(resultMap1.get(0).get(i) + " / 수가명 - ".concat(resultMap1.get(1).get(i)));
			historyLogVo.setMsg(msg);
			historyLogMapper.createHistoryLog(historyLogVo);
		}
		Map<Integer, List<String>> resultMap2 = commonUtil.allDel(medicalNotifyVo.getArrDel());
		medicalNotifyVo.setAllMedicalNotify(resultMap2.get(0));
		medicalNotifyVo.setAllMedicalNotifySub(resultMap2.get(1));
		return medicalNotifyMapper.deleteMedicalNotify(medicalNotifyVo);
	}
}
