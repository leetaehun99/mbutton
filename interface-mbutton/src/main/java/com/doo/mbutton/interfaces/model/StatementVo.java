package com.doo.mbutton.interfaces.model;

import java.util.List;

public class StatementVo {
	
	private String recpCstClmSeq;
	private String clmNum;
	private String stsSrlNum;
	private String hspId;
	private String birthDy;
	private String trtStartDt;
	private String msgType;

	private  DrugVo drugVo; 
	private  DrugNotifyVo drugNotifyVo; 
	private  MedicalNotifyVo medicalNotifyVo;
	private  NotifyCcVo notifyCcVo; 
	private  NotifyCbVo notifyCbVo; 
	private  NotifyExtVo notifyExtVo; 
	private  List<DiseaVo> diseaList; 
	
	private  MedicalStatementVo medicalStatementVo; 
	private  List<DiseaseStatementVo> diseaseStatementList; 
	private  List<TreatmentStatementVo> treatmentStatementList; 
	private  List<PrescriptnStatementVo> prescriptnStatementList;
	private  List<ErrorCheckVo> errorCheckList;
	private  List<SpecificDetailVo> specificDetailList;
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public MedicalNotifyVo getMedicalNotifyVo() {
		return medicalNotifyVo;
	}
	public void setMedicalNotifyVo(MedicalNotifyVo medicalNotifyVo) {
		this.medicalNotifyVo = medicalNotifyVo;
	}
	public String getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(String recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public String getClmNum() {
		return clmNum;
	}
	public void setClmNum(String clmNum) {
		this.clmNum = clmNum;
	}
	public String getStsSrlNum() {
		return stsSrlNum;
	}
	public void setStsSrlNum(String stsSrlNum) {
		this.stsSrlNum = stsSrlNum;
	}
	public String getHspId() {
		return hspId;
	}
	public void setHspId(String hspId) {
		this.hspId = hspId;
	}
	public String getBirthDy() {
		return birthDy;
	}
	public void setBirthDy(String birthDy) {
		this.birthDy = birthDy;
	}
	public String getTrtStartDt() {
		return trtStartDt;
	}
	public void setTrtStartDt(String trtStartDt) {
		this.trtStartDt = trtStartDt;
	}
	public DrugVo getDrugVo() {
		return drugVo;
	}
	public void setDrugVo(DrugVo drugVo) {
		this.drugVo = drugVo;
	}
	public DrugNotifyVo getDrugNotifyVo() {
		return drugNotifyVo;
	}
	public void setDrugNotifyVo(DrugNotifyVo drugNotifyVo) {
		this.drugNotifyVo = drugNotifyVo;
	}
	public NotifyCcVo getNotifyCcVo() {
		return notifyCcVo;
	}
	public void setNotifyCcVo(NotifyCcVo notifyCcVo) {
		this.notifyCcVo = notifyCcVo;
	}
	public NotifyCbVo getNotifyCbVo() {
		return notifyCbVo;
	}
	public void setNotifyCbVo(NotifyCbVo notifyCbVo) {
		this.notifyCbVo = notifyCbVo;
	}
	public NotifyExtVo getNotifyExtVo() {
		return notifyExtVo;
	}
	public void setNotifyExtVo(NotifyExtVo notifyExtVo) {
		this.notifyExtVo = notifyExtVo;
	}
	public List<DiseaVo> getDiseaList() {
		return diseaList;
	}
	public void setDiseaList(List<DiseaVo> diseaList) {
		this.diseaList = diseaList;
	}
	public MedicalStatementVo getMedicalStatementVo() {
		return medicalStatementVo;
	}
	public void setMedicalStatementVo(MedicalStatementVo medicalStatementVo) {
		this.medicalStatementVo = medicalStatementVo;
	}
	public List<DiseaseStatementVo> getDiseaseStatementList() {
		return diseaseStatementList;
	}
	public void setDiseaseStatementList(List<DiseaseStatementVo> diseaseStatementList) {
		this.diseaseStatementList = diseaseStatementList;
	}
	public List<TreatmentStatementVo> getTreatmentStatementList() {
		return treatmentStatementList;
	}
	public void setTreatmentStatementList(List<TreatmentStatementVo> treatmentStatementList) {
		this.treatmentStatementList = treatmentStatementList;
	}
	public List<PrescriptnStatementVo> getPrescriptnStatementList() {
		return prescriptnStatementList;
	}
	public void setPrescriptnStatementList(List<PrescriptnStatementVo> prescriptnStatementList) {
		this.prescriptnStatementList = prescriptnStatementList;
	}
	public List<ErrorCheckVo> getErrorCheckList() {
		return errorCheckList;
	}
	public void setErrorCheckList(List<ErrorCheckVo> errorCheckList) {
		this.errorCheckList = errorCheckList;
	}
	public List<SpecificDetailVo> getSpecificDetailList() {
		return specificDetailList;
	}
	public void setSpecificDetailList(List<SpecificDetailVo> specificDetailList) {
		this.specificDetailList = specificDetailList;
	}
}
