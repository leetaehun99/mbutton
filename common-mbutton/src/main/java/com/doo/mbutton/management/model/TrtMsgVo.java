package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
public class TrtMsgVo extends PagingVo{

	private String trtNotify;
	private String trtNotifySub;
	private String trtCd;
	private String msgLev;
	private String msg;
	private String msgSort;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;	
	private String specialCd;		// 특정내역 진료조제
	private String mSeq;
	
	private List<String> allTrtCd;
	private List<String> allTrtNotify;
	private List<String> allTrtNotifySub;
	
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String getTrtNotify() {
		return trtNotify;
	}
	public void setTrtNotify(String trtNotify) {
		this.trtNotify = trtNotify;
	}
	public String getTrtNotifySub() {
		return trtNotifySub;
	}
	public void setTrtNotifySub(String trtNotifySub) {
		this.trtNotifySub = trtNotifySub;
	}
	public String getTrtCd() {
		return trtCd;
	}
	public void setTrtCd(String trtCd) {
		this.trtCd = trtCd;
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
	public List<String> getAllTrtCd() {
		return allTrtCd;
	}
	public void setAllTrtCd(List<String> allTrtCd) {
		this.allTrtCd = allTrtCd;
	}
	public List<String> getAllTrtNotify() {
		return allTrtNotify;
	}
	public void setAllTrtNotify(List<String> allTrtNotify) {
		this.allTrtNotify = allTrtNotify;
	}
	public List<String> getAllTrtNotifySub() {
		return allTrtNotifySub;
	}
	public void setAllTrtNotifySub(List<String> allTrtNotifySub) {
		this.allTrtNotifySub = allTrtNotifySub;
	}
	
}
