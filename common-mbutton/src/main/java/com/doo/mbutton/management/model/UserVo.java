package com.doo.mbutton.management.model;

import java.io.Serializable;

import com.doo.mbutton.common.model.PagingVo;

/**
 * @author leetaehun
 *
 */
public class UserVo extends PagingVo implements Serializable {
	private String userId;
	private String hspNm;
	private String hspId;
	private String hspDtNm;
	private String userPwd;
	private String userNm;
	private String userLevCd;
	private String userStatCd;
	
	private String licenNum;
	private String zip;
	private String addr;
	private String phone;
	private String email;
	
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String resultCd;
	private String resultUrl;

	private String msgLev;
	//청구서 등록관련
	private int itotalVal;
	private int icurrentVal;
	//청구서 점검 관련
	private int ctotalVal;
	private int ccurrentVal;
	
	
	public int getItotalVal() {
		return itotalVal;
	}
	public void setItotalVal(int itotalVal) {
		this.itotalVal = itotalVal;
	}
	public int getIcurrentVal() {
		return icurrentVal;
	}
	public void setIcurrentVal(int icurrentVal) {
		this.icurrentVal = icurrentVal;
	}
	public int getCtotalVal() {
		return ctotalVal;
	}
	public void setCtotalVal(int ctotalVal) {
		this.ctotalVal = ctotalVal;
	}
	public int getCcurrentVal() {
		return ccurrentVal;
	}
	public void setCcurrentVal(int ccurrentVal) {
		this.ccurrentVal = ccurrentVal;
	}
	public String getMsgLev() {
		return msgLev;
	}
	public void setMsgLev(String msgLev) {
		this.msgLev = msgLev;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHspId() {
		return hspId;
	}
	public void setHspId(String hspId) {
		this.hspId = hspId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserLevCd() {
		return userLevCd;
	}
	public void setUserLevCd(String userLevCd) {
		this.userLevCd = userLevCd;
	}
	public String getUserStatCd() {
		return userStatCd;
	}
	public void setUserStatCd(String userStatCd) {
		this.userStatCd = userStatCd;
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
	public String getResultCd() {
		return resultCd;
	}
	public void setResultCd(String resultCd) {
		this.resultCd = resultCd;
	}
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}
	public String getHspNm() {
		return hspNm;
	}
	public void setHspNm(String hspNm) {
		this.hspNm = hspNm;
	}
	public String getHspDtNm() {
		return hspDtNm;
	}
	public void setHspDtNm(String hspDtNm) {
		this.hspDtNm = hspDtNm;
	}
	public String getLicenNum() {
		return licenNum;
	}
	public void setLicenNum(String licenNum) {
		this.licenNum = licenNum;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
