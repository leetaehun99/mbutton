package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

public class NotifyCcVo extends PagingVo{
	private String cbNotify;
	private String cbNotifySub;
	private String mSeq;
	private String msg;
	private String mainDrugCd;
	private String mainDrugNm;
	
	private String notifyNm;
	private String notifyContents;
	private String notifyMainNm;
	
	private String registerId;		//
	private String registDthms;		//
	private String updaterId;		//
	private String updateDthms;		//
	
	private List<String> allCbNotify;
	private List<String> allCbNotifySub;

	private List<String> mainDrugCdList;
	
	public List<String> getMainDrugCdList() {
		return mainDrugCdList;
	}
	public void setMainDrugCdList(List<String> mainDrugCdList) {
		this.mainDrugCdList = mainDrugCdList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCbNotify() {
		return cbNotify;
	}
	public void setCbNotify(String cbNotify) {
		this.cbNotify = cbNotify;
	}
	public String getCbNotifySub() {
		return cbNotifySub;
	}
	public void setCbNotifySub(String cbNotifySub) {
		this.cbNotifySub = cbNotifySub;
	}
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
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
	public String getNotifyNm() {
		return notifyNm;
	}
	public void setNotifyNm(String notifyNm) {
		this.notifyNm = notifyNm;
	}
	public String getNotifyContents() {
		return notifyContents;
	}
	public void setNotifyContents(String notifyContents) {
		this.notifyContents = notifyContents;
	}
	public String getNotifyMainNm() {
		return notifyMainNm;
	}
	public void setNotifyMainNm(String notifyMainNm) {
		this.notifyMainNm = notifyMainNm;
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
	public List<String> getAllCbNotify() {
		return allCbNotify;
	}
	public void setAllCbNotify(List<String> allCbNotify) {
		this.allCbNotify = allCbNotify;
	}
	public List<String> getAllCbNotifySub() {
		return allCbNotifySub;
	}
	public void setAllCbNotifySub(List<String> allCbNotifySub) {
		this.allCbNotifySub = allCbNotifySub;
	}
	
}