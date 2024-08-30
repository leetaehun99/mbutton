package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
public class TrtNotifyVo extends PagingVo{

	private String trtCd;
	private String trtNm;
	private String trtNotify;
	private String trtNotifySub;
	private String notifyNm;
	private String notifyMainNm;
	private String notifyMsg;
	private String notifyContents;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;

	private String mSeq;
	private String useYn;

	private String cdDivd;
	private String[] msgLevArray;
	private String[] msgArray;
	private String[] msgSortArray;
	private String[] msgSpecialCd;		// 특정내역 진료조제(msg 에서 사용할)
	private List<TrtMsgVo> msgList;
	
	private List<String> allTrtNotify;
	private List<String> allTrtNotifySub;
	
	public String getmSeq() {
		return mSeq;
	}
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}
	public String getTrtCd() {
		return trtCd;
	}
	public void setTrtCd(String trtCd) {
		this.trtCd = trtCd;
	}
	public String getTrtNm() {
		return trtNm;
	}
	public void setTrtNm(String trtNm) {
		this.trtNm = trtNm;
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
	public List<TrtMsgVo> getMsgList() {
		return msgList;
	}
	public void setMsgList(List<TrtMsgVo> msgList) {
		this.msgList = msgList;
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
