package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

public class NotifyExtVo extends PagingVo{

	private static final long serialVersionUID = 1L;
	private String extNotify;
	private String extNotifySub;
	private String mSeq;
	private String notifyNm;
	private String notifyMainNm;
	private String notifyContents;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String drugCd;
	private String extDiv1;
	private String extDiv2;
	private String val;
	private String msg;
	private List<String> drugAllCdList; 
	private List<String> extNotifyAllList;
	private List<String> extNotifySubAllList;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDrugCd() {
		return drugCd;
	}
	public void setDrugCd(String drugCd) {
		this.drugCd = drugCd;
	}
	public String getExtDiv1() {
		return extDiv1;
	}
	public void setExtDiv1(String extDiv1) {
		this.extDiv1 = extDiv1;
	}
	public String getExtDiv2() {
		return extDiv2;
	}
	public void setExtDiv2(String extDiv2) {
		this.extDiv2 = extDiv2;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getExtNotify() {
		return extNotify;
	}
	public void setExtNotify(String extNotify) {
		this.extNotify = extNotify;
	}
	public String getExtNotifySub() {
		return extNotifySub;
	}
	public void setExtNotifySub(String extNotifySub) {
		this.extNotifySub = extNotifySub;
	}
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
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
	public List<String> getDrugAllCdList() {
		return drugAllCdList;
	}
	public void setDrugAllCdList(List<String> drugAllCdList) {
		this.drugAllCdList = drugAllCdList;
	}
	public List<String> getExtNotifyAllList() {
		return extNotifyAllList;
	}
	public void setExtNotifyAllList(List<String> extNotifyAllList) {
		this.extNotifyAllList = extNotifyAllList;
	}
	public List<String> getExtNotifySubAllList() {
		return extNotifySubAllList;
	}
	public void setExtNotifySubAllList(List<String> extNotifySubAllList) {
		this.extNotifySubAllList = extNotifySubAllList;
	}
	
}