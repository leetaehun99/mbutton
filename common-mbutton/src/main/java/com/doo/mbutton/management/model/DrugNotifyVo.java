package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
@SuppressWarnings("serial")
public class DrugNotifyVo extends PagingVo{
	
	private String drugNotify;
	private String drugNotifySub;
	private String drugNotifyNm;
	private String drugNotifyMainNm;
	private String drugNotifyItem;
	private String drugNotifyContents;
	private String drugDosage;
	private String drugEfficacy;
	private String drugNotifyMsg;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	
	private String drugCd;
	private String drugNm;
	private String mainDrugCd;
	private String mainDrugNm;
	private String useYn;

	private String mSeq;
	
	private String cdDivd;
	private String[] msgLevArray;
	private String[] msgArray;
	private String[] msgSortArray;
	private String[] msgSpecialCd;		// 특정내역 진료조제(msg 에서 사용할)
	
	private List<DrugMsgVo> msgList;
	
	private List<String> allDrugNotify;
	private List<String> allDrugNotifySub;
	
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	public String getMainDrugNm() {
		return mainDrugNm;
	}
	public void setMainDrugNm(String mainDrugNm) {
		this.mainDrugNm = mainDrugNm;
	}
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String[] getMsgSpecialCd() {
		return msgSpecialCd;
	}
	public void setMsgSpecialCd(String[] msgSpecialCd) {
		this.msgSpecialCd = msgSpecialCd;
	}
	public List<DrugMsgVo> getMsgList() {
		return msgList;
	}
	public void setMsgList(List<DrugMsgVo> msgList) {
		this.msgList = msgList;
	}
	public String getCdDivd() {
		return cdDivd;
	}
	public void setCdDivd(String cdDivd) {
		this.cdDivd = cdDivd;
	}
	public String[] getMsgLevArray() {
		return msgLevArray;
	}
	public void setMsgLevArray(String[] msgLevArray) {
		this.msgLevArray = msgLevArray;
	}
	public String[] getMsgArray() {
		return msgArray;
	}
	public void setMsgArray(String[] msgArray) {
		this.msgArray = msgArray;
	}
	public String[] getMsgSortArray() {
		return msgSortArray;
	}
	public void setMsgSortArray(String[] msgSortArray) {
		this.msgSortArray = msgSortArray;
	}
	public String getDrugDosage() {
		return drugDosage;
	}
	public void setDrugDosage(String drugDosage) {
		this.drugDosage = drugDosage;
	}
	public String getDrugEfficacy() {
		return drugEfficacy;
	}
	public void setDrugEfficacy(String drugEfficacy) {
		this.drugEfficacy = drugEfficacy;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDrugNm() {
		return drugNm;
	}
	public void setDrugNm(String drugNm) {
		this.drugNm = drugNm;
	}
	public String getDrugCd() {
		return drugCd;
	}
	public void setDrugCd(String drugCd) {
		this.drugCd = drugCd;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegistDthms() {
		return registDthms;
	}
	public void setRegistDthms(String registDthms) {
		this.registDthms = registDthms;
	}
	public String getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}
	public String getUpdateDthms() {
		return updateDthms;
	}
	public void setUpdateDthms(String updateDthms) {
		this.updateDthms = updateDthms;
	}
	public String getDrugNotify() {
		return drugNotify;
	}
	public void setDrugNotify(String drugNotify) {
		this.drugNotify = drugNotify;
	}
	public String getDrugNotifySub() {
		return drugNotifySub;
	}
	public void setDrugNotifySub(String drugNotifySub) {
		this.drugNotifySub = drugNotifySub;
	}
	public String getDrugNotifyNm() {
		return drugNotifyNm;
	}
	public void setDrugNotifyNm(String drugNotifyNm) {
		this.drugNotifyNm = drugNotifyNm;
	}
	public String getDrugNotifyMainNm() {
		return drugNotifyMainNm;
	}
	public void setDrugNotifyMainNm(String drugNotifyMainNm) {
		this.drugNotifyMainNm = drugNotifyMainNm;
	}
	public String getDrugNotifyItem() {
		return drugNotifyItem;
	}
	public void setDrugNotifyItem(String drugNotifyItem) {
		this.drugNotifyItem = drugNotifyItem;
	}
	public String getDrugNotifyContents() {
		return drugNotifyContents;
	}
	public void setDrugNotifyContents(String drugNotifyContents) {
		this.drugNotifyContents = drugNotifyContents;
	}
	public String getDrugNotifyMsg() {
		return drugNotifyMsg;
	}
	public void setDrugNotifyMsg(String drugNotifyMsg) {
		this.drugNotifyMsg = drugNotifyMsg;
	}
	public List<String> getAllDrugNotify() {
		return allDrugNotify;
	}
	public void setAllDrugNotify(List<String> allDrugNotify) {
		this.allDrugNotify = allDrugNotify;
	}
	public List<String> getAllDrugNotifySub() {
		return allDrugNotifySub;
	}
	public void setAllDrugNotifySub(List<String> allDrugNotifySub) {
		this.allDrugNotifySub = allDrugNotifySub;
	}
	
}
