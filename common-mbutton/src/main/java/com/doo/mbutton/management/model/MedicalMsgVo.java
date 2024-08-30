package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
@SuppressWarnings("serial")
public class MedicalMsgVo extends PagingVo{

	private String medicalNotify;
	private String medicalNotifySub;
	private String medicalCd;
	private String msgLev;
	private String msg;
	private String msgSort;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String specialCd;		// 특정내역 진료조제
	private String mSeq;
	
	private List<String> allMedicalCd;
	private List<String> allMedicalNotify;
	private List<String> allMedicalNotifySub;
	
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
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
	public String getMedicalCd() {
		return medicalCd;
	}
	public void setMedicalCd(String medicalCd) {
		this.medicalCd = medicalCd;
	}
	public String getMsgLev() {
		return msgLev;
	}
	public void setMsgLev(String msgLev) {
		this.msgLev = msgLev;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgSort() {
		return msgSort;
	}
	public void setMsgSort(String msgSort) {
		this.msgSort = msgSort;
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
	public String getSpecialCd() {
		return specialCd;
	}
	public void setSpecialCd(String specialCd) {
		this.specialCd = specialCd;
	}
	public List<String> getAllMedicalCd() {
		return allMedicalCd;
	}
	public void setAllMedicalCd(List<String> allMedicalCd) {
		this.allMedicalCd = allMedicalCd;
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
