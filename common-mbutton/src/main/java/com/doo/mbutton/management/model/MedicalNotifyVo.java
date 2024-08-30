package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
public class MedicalNotifyVo extends PagingVo{

	private String medicalCd;
	private String medicalNm;
	private String medicalNotify;
	private String medicalNotifySub;
	private String notifyNm;
	private String notifyMainNm;
	private String notifyMsg;
	private String notifyContents;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	
	private String useYn;
	private String mSeq;

	private String cdDivd;
	private String[] msgLevArray;
	private String[] msgArray;
	private String[] msgSortArray;
	private String[] msgSpecialCd;		// 특정내역 진료조제(msg 에서 사용할)
	private List<MedicalMsgVo> msgList;
	private String screeningPractices;
	private List<String> allMedicalNotify;
	private List<String> allMedicalNotifySub;
	
	public String getScreeningPractices() {
		return screeningPractices;
	}
	public void setScreeningPractices(String screeningPractices) {
		this.screeningPractices = screeningPractices;
	}
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String getMedicalNm() {
		return medicalNm;
	}
	public void setMedicalNm(String medicalNm) {
		this.medicalNm = medicalNm;
	}
	public String getMedicalCd() {
		return medicalCd;
	}
	public void setMedicalCd(String medicalCd) {
		this.medicalCd = medicalCd;
	}
	public List<MedicalMsgVo> getMsgList() {
		return msgList;
	}
	public void setMsgList(List<MedicalMsgVo> msgList) {
		this.msgList = msgList;
	}
	public String getMedicalNotify() {
		return medicalNotify;
	}
	public void setMedicalNotify(String medicalNotify) {
		this.medicalNotify = medicalNotify;
	}
	public String getMedicalNotifySub() {
		return medicalNotifySub;
	}
	public void setMedicalNotifySub(String medicalNotifySub) {
		this.medicalNotifySub = medicalNotifySub;
	}
	public String getNotifyNm() {
		return notifyNm;
	}
	public void setNotifyNm(String notifyNm) {
		this.notifyNm = notifyNm;
	}
	public String getNotifyMainNm() {
		return notifyMainNm;
	}
	public void setNotifyMainNm(String notifyMainNm) {
		this.notifyMainNm = notifyMainNm;
	}
	public String getNotifyMsg() {
		return notifyMsg;
	}
	public void setNotifyMsg(String notifyMsg) {
		this.notifyMsg = notifyMsg;
	}
	public String getNotifyContents() {
		return notifyContents;
	}
	public void setNotifyContents(String notifyContents) {
		this.notifyContents = notifyContents;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String[] getMsgSpecialCd() {
		return msgSpecialCd;
	}
	public void setMsgSpecialCd(String[] msgSpecialCd) {
		this.msgSpecialCd = msgSpecialCd;
	}
	public List<String> getAllMedicalNotify() {
		return allMedicalNotify;
	}
	public void setAllMedicalNotify(List<String> allMedicalNotify) {
		this.allMedicalNotify = allMedicalNotify;
	}
	public List<String> getAllMedicalNotifySub() {
		return allMedicalNotifySub;
	}
	public void setAllMedicalNotifySub(List<String> allMedicalNotifySub) {
		this.allMedicalNotifySub = allMedicalNotifySub;
	}
	
}
