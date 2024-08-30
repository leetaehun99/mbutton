package com.doo.mbutton.management.model;

import java.util.List;

public class ScreenMsgVo {
	private String screenMsgSeq;
	private String clmNum;
	private long recpCstClmSeq;
	private String hspId;
	private String stsSrlNum;
	private String mSeq;
	private String msgLev;
	private String msgType;
	private String cd;
	private String birthDy;
	private String sex;
	private String msg;
	private String msgYn="N";
	
	private String notify;
	private String notifySub;
	private String cnt;
	
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	
	
	private List<ScreenMsgVo> screenMsgList;
	
	
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public List<ScreenMsgVo> getScreenMsgList() {
		return screenMsgList;
	}
	public void setScreenMsgList(List<ScreenMsgVo> screenMsgList) {
		this.screenMsgList = screenMsgList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgYn() {
		return msgYn;
	}
	public void setMsgYn(String msgYn) {
		this.msgYn = msgYn;
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
	public String getBirthDy() {
		return birthDy;
	}
	public void setBirthDy(String birthDy) {
		this.birthDy = birthDy;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getScreenMsgSeq() {
		return screenMsgSeq;
	}
	public void setScreenMsgSeq(String screenMsgSeq) {
		this.screenMsgSeq = screenMsgSeq;
	}
	public String getClmNum() {
		return clmNum;
	}
	public void setClmNum(String clmNum) {
		this.clmNum = clmNum;
	}
	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public String getHspId() {
		return hspId;
	}
	public void setHspId(String hspId) {
		this.hspId = hspId;
	}
	public String getStsSrlNum() {
		return stsSrlNum;
	}
	public void setStsSrlNum(String stsSrlNum) {
		this.stsSrlNum = stsSrlNum;
	}
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String getMsgLev() {
		return msgLev;
	}
	public void setMsgLev(String msgLev) {
		this.msgLev = msgLev;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
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
}
