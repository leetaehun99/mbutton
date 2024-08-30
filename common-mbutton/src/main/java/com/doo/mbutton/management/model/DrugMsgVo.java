package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
public class DrugMsgVo extends PagingVo{
	
	private static final long serialVersionUID = -6731156213105090168L;
	private String drugNotify;
	private String drugNotifySub;
	private String mainDrugCd;
	private String msgLev;
	private String msg;
	private String msgSort;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String specialCd;		// 특정내역 진료조제
	private String seq;
	private String value;
	private String label;
	private String mSeq;
	
	private List<String> allDrugNotify;
	private List<String> allDrugNotifySub;
	private List<String> allMainDrugCd;
	
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSpecialCd() {
		return specialCd;
	}
	public void setSpecialCd(String specialCd) {
		this.specialCd = specialCd;
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

	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
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
	public List<String> getAllMainDrugCd() {
		return allMainDrugCd;
	}
	public void setAllMainDrugCd(List<String> allMainDrugCd) {
		this.allMainDrugCd = allMainDrugCd;
	}
	
	
	
}
