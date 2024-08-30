package com.doo.mbutton.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.management.mapper.DrugMainMapper;
import com.doo.mbutton.management.mapper.DrugMsgMapper;
import com.doo.mbutton.management.mapper.DrugNotifyMapper;
import com.doo.mbutton.management.mapper.HistoryLogMapper;
import com.doo.mbutton.management.model.DrugMsgVo;
import com.doo.mbutton.management.model.DrugNotifyVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.HistoryLogVo;
import com.doo.mbutton.management.model.MsgVo;



@Service
public class DrugNotifyService {
	
	@Autowired
	private DrugNotifyMapper drugNotifyMapper;
	@Autowired
	private DrugMsgMapper drugMsgMapper;
	@Autowired
	private DrugMainMapper drugMainMapper;
	@Autowired
	private CommonUtil commonUtil;
	@Autowired
	private HistoryLogMapper historyLogMapper;
	
	public List<DrugNotifyVo> selectDrugNotifyList(DrugNotifyVo drugNotifyVo){
		return drugNotifyMapper.selectDrugNotifyList(drugNotifyVo);
	}

	public List<DrugNotifyVo> selectDrugNotifyGroupByList(DrugNotifyVo drugNotifyVo){
		return drugNotifyMapper.selectDrugNotifyGroupByList(drugNotifyVo);
	}

	public List<DrugNotifyVo> selectDrugNotifyItemList(DrugNotifyVo drugNotifyVo){
		return drugNotifyMapper.selectDrugNotifyItemList(drugNotifyVo);
	}
	
	public DrugNotifyVo selectDrugNotify(DrugNotifyVo drugNotifyVo){
		return drugNotifyMapper.selectDrugNotify(drugNotifyVo);
	}
	
	public DrugNotifyVo selectDrugNotifyItem(DrugNotifyVo drugNotifyVo){
		
		DrugMsgVo msgVo = new DrugMsgVo();
		msgVo.setDrugNotify(drugNotifyVo.getDrugNotify());
		msgVo.setDrugNotifySub(drugNotifyVo.getDrugNotifySub());
		msgVo.setMainDrugCd(drugNotifyVo.getMainDrugCd());
		
		drugNotifyVo = drugNotifyMapper.selectDrugNotifyItem(drugNotifyVo);
		
		
		return drugNotifyVo;
	}
	
	public int createDrugNotify(DrugNotifyVo drugNotifyVo){
		return drugNotifyMapper.createDrugNotify(drugNotifyVo);
	}
		
	public int updateDrugNotify(DrugNotifyVo drugNotifyVo){
		int result = drugNotifyMapper.updateDrugNotify(drugNotifyVo);
		return result;
	}
	
	public int createDrugMsg(DrugMsgVo drugMsg){
		return drugMsgMapper.createDrugMsg(drugMsg);
	}
	
	
	public int createDrugNotifyItem(DrugNotifyVo drugNotifyVo) throws Exception{
		int result = 0;
		DrugVo drugVo = new DrugVo();
		drugVo.setMainDrugCd(drugNotifyVo.getMainDrugCd());
		drugVo =  drugMainMapper.selectDrugMain(drugVo);
		if(drugVo != null){
			DrugNotifyVo noti  = drugNotifyMapper.selectDrugNotifyItem(drugNotifyVo);
			if(noti == null){
				result = drugNotifyMapper.createDrugNotifyItem(drugNotifyVo);
				/*if(result == 1){
					DrugMsgVo msgVo = new DrugMsgVo();
					
					String[] arrayMsg = drugNotifyVo.getMsgArray();
					String[] arrayMsgLev = drugNotifyVo.getMsgLevArray();
					String[] arrayMsgSort = drugNotifyVo.getMsgSortArray();

					msgVo.setDrugNotify(drugNotifyVo.getDrugNotify());
					msgVo.setDrugNotifySub(drugNotifyVo.getDrugNotifySub());
					msgVo.setDrugCd(drugNotifyVo.getDrugCd());
					msgVo.setUpdaterId(drugNotifyVo.getUpdaterId());
					if(arrayMsgLev!=null){
						for(int i=0; i<arrayMsgLev.length; i++){
							if(arrayMsgLev[i]!=""){
								msgVo.setMsg(arrayMsg[i]);
								msgVo.setMsgLev(arrayMsgLev[i]);
								msgVo.setMsgSort(arrayMsgSort[i]);
								drugMsgMapper.createDrugMsg(msgVo);
							}
						}
					}
					
				}*/
			}else{
				result = 99;
			}
		}else{
			result = 98;
		}
		
		return result;
	}
	
	public int updateDrugNotifyItem(DrugNotifyVo drugNotifyVo) throws Exception{
		int result = 0;
		DrugVo drugVo = new DrugVo();
		drugVo.setMainDrugCd(drugNotifyVo.getMainDrugCd());
		drugVo =  drugMainMapper.selectDrugMain(drugVo);
		if(drugVo != null){
			result = drugNotifyMapper.updateDrugNotifyItem(drugNotifyVo);
			/*if(result == 1){
				DrugMsgVo msgVo = new DrugMsgVo();
				
				String[] arrayMsg = drugNotifyVo.getMsgArray();
				String[] arrayMsgLev = drugNotifyVo.getMsgLevArray();
				String[] arrayMsgSort = drugNotifyVo.getMsgSortArray();
				String[] arrayMsgSpecialCd = drugNotifyVo.getMsgSpecialCd();
	
				msgVo.setDrugNotify(drugNotifyVo.getDrugNotify());
				msgVo.setDrugNotifySub(drugNotifyVo.getDrugNotifySub());
				msgVo.setDrugCd(drugNotifyVo.getDrugCd());
				msgVo.setUpdaterId(drugNotifyVo.getUpdaterId());
				drugMsgMapper.deleteDrugMsg(msgVo);
				if(arrayMsgLev!=null){
					for(int i=0; i<arrayMsgLev.length; i++){
						if(arrayMsgLev[i]!=""){
							msgVo.setMsg(arrayMsg[i]);
							msgVo.setMsgLev(arrayMsgLev[i]);
							msgVo.setMsgSort(arrayMsgSort[i]);
							msgVo.setSpecialCd(arrayMsgSpecialCd[i]);
							drugMsgMapper.createDrugMsg(msgVo);
						}
					}
				}
			}*/
		}else{
			result = 98;
		}
		
		return result;
	}
	
	public int updateDrugNotifyuseYnItem(DrugNotifyVo drugNotifyVo) throws Exception{
		return drugNotifyMapper.updateDrugNotifyItem(drugNotifyVo);
	}

	public int deleteDrugMsg(DrugMsgVo drugMsg) {
		return drugMsgMapper.deleteDrugMsg(drugMsg);
	}	
	
	public int deleteDrugMsgAll(MsgVo msgVo, HistoryLogVo historyLogVo) {
		DrugMsgVo drugMsgVo = new DrugMsgVo();
		if(msgVo.getArrDel() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getArrDel());
			drugMsgVo.setmSeq(msgVo.getmSeq());
			drugMsgVo.setAllDrugNotify(resultMap.get(0));
			drugMsgVo.setAllDrugNotifySub(resultMap.get(1));
			drugMsgVo.setAllMainDrugCd(resultMap.get(2));
		}
		
		if(msgVo.getHistoryMsg() != null) {
			Map<Integer, List<String>> resultMap = commonUtil.allDel(msgVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('1');
				String msg = "MsgMapper.deleteDrugMsg : 코드 - " + resultMap.get(0).get(i) + " / 명칭 - " + resultMap.get(1).get(i);
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		return drugMsgMapper.deleteDrugMsgAll(drugMsgVo);
	}
	
	public int deleteDrugNotifyItem(DrugNotifyVo drugNotifyVo, HistoryLogVo historyLogVo) {
		drugNotifyMapper.deleteDMsg(drugNotifyVo);
		int result = drugNotifyMapper.deleteDrugNotifyItem(drugNotifyVo);
		for(int i=0; i<drugNotifyVo.getHistoryMsg().length; i++) {
			if(!drugNotifyVo.getHistoryMsg()[i].isEmpty()) {
				historyLogVo.setType('1');
				String msg = "DrugNotifyMapper.deleteDrugNotifyItem : 주성분코드 - ".concat(drugNotifyVo.getArrDel()[i]).concat(" / 주성분명 - ").concat(drugNotifyVo.getHistoryMsg()[i]);
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		
		
		return result;
	}
	
	public List<String> selectDrugNotifyCnt(DrugNotifyVo drugNotifyVo) {
		Map <Integer, List<String>> resultMap = commonUtil.allDel(drugNotifyVo.getArrDel());
		drugNotifyVo.setAllDrugNotify(resultMap.get(0));
		drugNotifyVo.setAllDrugNotifySub(resultMap.get(1));
		return drugNotifyMapper.selectDrugNotifyCnt(drugNotifyVo);
	}
	
	public int deleteDrugNotify(DrugNotifyVo drugNotifyVo, HistoryLogVo historyLogVo) {
		if(drugNotifyVo.getHistoryMsg() != null) {
			Map <Integer, List<String>> resultMap = commonUtil.allDel(drugNotifyVo.getHistoryMsg());
			for(int i=0; i<resultMap.get(0).size(); i++) {
				historyLogVo.setType('1');
				String msg = "DrugNotifyMapper.deleteDrugNotify : 고시명 - ".concat(resultMap.get(0).get(i)).concat(" / 재재명 - ").concat(resultMap.get(0).get(i));
				historyLogVo.setMsg(msg);
				historyLogMapper.createHistoryLog(historyLogVo);
			}
		}
		
		if(drugNotifyVo.getArrDel() != null) {
			Map <Integer, List<String>> resultMap = commonUtil.allDel(drugNotifyVo.getArrDel());
			drugNotifyVo.setAllDrugNotify(resultMap.get(0));
			drugNotifyVo.setAllDrugNotifySub(resultMap.get(1));
		}
		return drugNotifyMapper.deleteDrugNotify(drugNotifyVo);
	}
}