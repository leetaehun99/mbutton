package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

public class MsgVo extends PagingVo{
	private static final long serialVersionUID = 1L;
	private String seq;
	private String mSeq;
	private String msg;
	private String msgLev;
	private String specialCd;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String type;
	private String cd;
	private String msgSort;
	private String notify;
	private String notifySub;
	private String korNm;
	private List<String> allSeq;
	private String infoMsg;
	int cnt;
	
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String getKorNm() {
		return korNm;
	}
	public void setKorNm(String korNm) {
		this.korNm = korNm;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getMsgSort() {
		return msgSort;
	}
	public void setMsgSort(String msgSort) {
		this.msgSort = msgSort;
	}
	public String getNotify() {
		return notify;
	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public String getNotifySub() {
		return notifySub;
	}
	public void setNotifySub(String notifySub) {
		this.notifySub = notifySub;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsgLev() {
		return msgLev;
	}
	public void setMsgLev(String msgLev) {
		this.msgLev = msgLev;
	}
	public String getSpecialCd() {
		return specialCd;
	}
	public void setSpecialCd(String specialCd) {
		this.specialCd = specialCd;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public List<String> getAllSeq() {
		return allSeq;
	}
	public void setAllSeq(List<String> allSeq) {
		this.allSeq = allSeq;
	}
	public String getInfoMsg() {
		return infoMsg;
	}
	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}