package com.doo.mbutton.interfaces.model;

import java.util.List;

public class NotifyCcVo {
	private String cbNotify;
	private String cbNotifySub;
	private String mSeq;
	private String msg;
	private String mainDrugCd;
	private String mainDrugNm;
	
	private String notifyNm;
	private String notifyContents;
	private String notifyMainNm;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	
	
}