package com.doo.mbutton.management.model;

import java.io.Serializable;
import java.util.List;


public class ErrorCheckVo implements Serializable {
	//청구서 시컨스
	private long recpCstClmSeq;
	//명세서 일련번호
	private String stsSrlNum;
	//청구 번호
	private String clmNum;
	//병원 아이디
	private String hspId;
	private String registerId;
	
	//0:불필요 1:수가 3:약가 Z:약가(인증상병) E:약품예외 C:병용
	private String notify;
	private String notifySub;
	private String cd;
	private String mainDrugCd;
	private String val;
	private String msgType;
	private String msgLev;
	private String mSeq;
	private String errMsg;
	
	private String extDiv1;
	private String extDiv2;
	private String specialCd;
	
	
	private String diseaCd;
	private String drugCd;
	private String mode;
	private String param1;
	private String param2;
	private String param3;
	
	private List<String> diseaCdList;
	private List<String> medicalCdList;
	private List<String> drugCdList;
	private List<String> drugAllCdList;
	private List<String> trtCdList;
	private List<String> mainDrugAllCdList;
	private List<String> mainDrugCdList1;
	private List<String> mainDrugCdList2;
	private List<String> allMedicalInsurCd;
	private String medicalInsurCd;
	
	//임시
	private String birthy;
	

	

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getBirthy() {
		return birthy;
	}

	public void setBirthy(String birthy) {
		this.birthy = birthy;
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

	public String getSpecialCd() {
		return specialCd;
	}

	public void setSpecialCd(String specialCd) {
		this.specialCd = specialCd;
	}

	public List<String> getMedicalCdList() {
		return medicalCdList;
	}

	public void setMedicalCdList(List<String> medicalCdList) {
		this.medicalCdList = medicalCdList;
	}

	public List<String> getDrugAllCdList() {
		return drugAllCdList;
	}

	public void setDrugAllCdList(List<String> drugAllCdList) {
		this.drugAllCdList = drugAllCdList;
	}

	public String getMainDrugCd() {
		return mainDrugCd;
	}

	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}

	public List<String> getMainDrugAllCdList() {
		return mainDrugAllCdList;
	}

	public void setMainDrugAllCdList(List<String> mainDrugAllCdList) {
		this.mainDrugAllCdList = mainDrugAllCdList;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getmSeq() {
		return mSeq;
	}

	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<String> getTrtCdList() {
		return trtCdList;
	}

	public void setTrtCdList(List<String> trtCdList) {
		this.trtCdList = trtCdList;
	}

	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}

	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}

	public String getStsSrlNum() {
		return stsSrlNum;
	}

	public void setStsSrlNum(String stsSrlNum) {
		this.stsSrlNum = stsSrlNum;
	}

	public String getClmNum() {
		return clmNum;
	}

	public void setClmNum(String clmNum) {
		this.clmNum = clmNum;
	}

	public String getHspId() {
		return hspId;
	}

	public void setHspId(String hspId) {
		this.hspId = hspId;
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

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgLev() {
		return msgLev;
	}

	public void setMsgLev(String msgLev) {
		this.msgLev = msgLev;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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

	public String getDiseaCd() {
		return diseaCd;
	}

	public void setDiseaCd(String diseaCd) {
		this.diseaCd = diseaCd;
	}

	public String getDrugCd() {
		return drugCd;
	}

	public void setDrugCd(String drugCd) {
		this.drugCd = drugCd;
	}

	public List<String> getDiseaCdList() {
		return diseaCdList;
	}

	public void setDiseaCdList(List<String> diseaCdList) {
		this.diseaCdList = diseaCdList;
	}

	public List<String> getDrugCdList() {
		return drugCdList;
	}

	public void setDrugCdList(List<String> drugCdList) {
		this.drugCdList = drugCdList;
	}

	public List<String> getAllMedicalInsurCd() {
		return allMedicalInsurCd;
	}

	public void setAllMedicalInsurCd(List<String> allMedicalInsurCd) {
		this.allMedicalInsurCd = allMedicalInsurCd;
	}

	public String getMedicalInsurCd() {
		return medicalInsurCd;
	}

	public void setMedicalInsurCd(String medicalInsurCd) {
		this.medicalInsurCd = medicalInsurCd;
	}
	
}
