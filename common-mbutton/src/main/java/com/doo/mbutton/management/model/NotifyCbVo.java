package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

@SuppressWarnings("serial")
public class NotifyCbVo extends PagingVo{
	private String cbNotify;
	private String cbNotifySub;
	private String mSeq;
	private String msg;
	private String mainDrugCd1;
	private String mainDrugCd2;
	private String mainDrugNm1;
	private String mainDrugNm2;
	private String etc1;
	private String etc2;
	
	private List<String> mainDrugCdList1;
	private List<String> mainDrugCdList2;
	
	private String notifyNm;
	private String notifyContents;
	private String notifyMainNm;
	
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private List<String> allCbNotify;
	private List<String> allCbNotifySub;
	
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	public String getEtc2() {
		return etc2;
	}
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	public List<String> getMainDrugCdList1() {
		return mainDrugCdList1;
	}
	public void setMainDrugCdList1(List<String> mainDrugCdList1) {
		this.mainDrugCdList1 = mainDrugCdList1;
	}
	public List<String> getMainDrugCdList2() {
		return mainDrugCdList2;
	}
	public void setMainDrugCdList2(List<String> mainDrugCdList2) {
		this.mainDrugCdList2 = mainDrugCdList2;
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
	public String getMainDrugCd1() {
		return mainDrugCd1;
	}
	public void setMainDrugCd1(String mainDrugCd1) {
		this.mainDrugCd1 = mainDrugCd1;
	}
	public String getMainDrugCd2() {
		return mainDrugCd2;
	}
	public void setMainDrugCd2(String mainDrugCd2) {
		this.mainDrugCd2 = mainDrugCd2;
	}
	public String getMainDrugNm1() {
		return mainDrugNm1;
	}
	public void setMainDrugNm1(String mainDrugNm1) {
		this.mainDrugNm1 = mainDrugNm1;
	}
	public String getMainDrugNm2() {
		return mainDrugNm2;
	}
	public void setMainDrugNm2(String mainDrugNm2) {
		this.mainDrugNm2 = mainDrugNm2;
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