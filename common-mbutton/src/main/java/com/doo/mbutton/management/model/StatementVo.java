package com.doo.mbutton.management.model;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


public class StatementVo {
	private long recpCstClmSeq;
	private String clmNum;
	private String stsSrlNum;
	private String hspId;
	private String trtStartDt;
	private String trtSubct;
	private String trtDetailSubct;
	private String licenCateg;
	private String licenNum;
	private String registerId;
	
	private int sum1;
	private int sum2;
	private int round;
	private int selfPrice;
	private int claimPrice;
	private int totalPrice;
	private int age;
	
	
	private  RecuperationCostAccountVo recuperationCostAccountVo; 
	private  List<RecuperationCostAccountVo> recuperationCostAccountList; 
	private  MedicalStatementVo medicalStatementVo; 
	private  List<MedicalStatementVo> medicalStatementSelectList; 
	private  List<DiseaseStatementVo> diseaseStatementList; 
	private  List<TreatmentStatementVo> treatmentStatementList; 
	private  List<PrescriptnStatementVo> prescriptnStatementList; 
	private  List<SpecificDetailVo> specificDetailList;
	private  List<ErrorCheckVo> errorCheckList;
	private  String[][] dayOfWeek;
	private  List<Map<String,String>> sectNum2List;
	private  JSONObject jsonObject = null;

	
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//에러 Check
	List<DiseaVo> diseaList;
	DrugVo drugVo;
	DrugNotifyVo drugNotifyVo;
	NotifyExtVo notifyExtVo;
	NotifyCbVo notifyCbVo;
	NotifyCcVo notifyCcVo;
	MedicalNotifyVo medicalVo;
	
	
	public NotifyCbVo getNotifyCbVo() {
		return notifyCbVo;
	}
	public NotifyCcVo getNotifyCcVo() {
		return notifyCcVo;
	}
	public void setNotifyCcVo(NotifyCcVo notifyCcVo) {
		this.notifyCcVo = notifyCcVo;
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
	public DrugNotifyVo getDrugNotifyVo() {
		return drugNotifyVo;
	}
	public void setDrugNotifyVo(DrugNotifyVo drugNotifyVo) {
		this.drugNotifyVo = drugNotifyVo;
	}
	public MedicalNotifyVo getMedicalVo() {
		return medicalVo;
	}
	public void setMedicalVo(MedicalNotifyVo medicalVo) {
		this.medicalVo = medicalVo;
	}
	public DrugVo getDrugVo() {
		return drugVo;
	}
	public void setDrugVo(DrugVo drugVo) {
		this.drugVo = drugVo;
	}
	public List<DiseaVo> getDiseaList() {
		return diseaList;
	}
	public void setDiseaList(List<DiseaVo> diseaList) {
		this.diseaList = diseaList;
	}
	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public List<Map<String, String>> getSectNum2List() {
		return sectNum2List;
	}
	public void setSectNum2List(List<Map<String, String>> sectNum2List) {
		this.sectNum2List = sectNum2List;
	}
	public String[][] getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String[][] dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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
	public String getTrtStartDt() {
		return trtStartDt;
	}
	public void setTrtStartDt(String trtStartDt) {
		this.trtStartDt = trtStartDt;
	}
	public int getSum1() {
		return sum1;
	}
	public void setSum1(int sum1) {
		this.sum1 = sum1;
	}
	public int getSum2() {
		return sum2;
	}
	public void setSum2(int sum2) {
		this.sum2 = sum2;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getSelfPrice() {
		return selfPrice;
	}
	public void setSelfPrice(int selfPrice) {
		this.selfPrice = selfPrice;
	}
	public int getClaimPrice() {
		return claimPrice;
	}
	public void setClaimPrice(int claimPrice) {
		this.claimPrice = claimPrice;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTrtSubct() {
		return trtSubct;
	}
	public void setTrtSubct(String trtSubct) {
		this.trtSubct = trtSubct;
	}
	public String getTrtDetailSubct() {
		return trtDetailSubct;
	}
	public void setTrtDetailSubct(String trtDetailSubct) {
		this.trtDetailSubct = trtDetailSubct;
	}
	public String getLicenCateg() {
		return licenCateg;
	}
	public void setLicenCateg(String licenCateg) {
		this.licenCateg = licenCateg;
	}
	public String getLicenNum() {
		return licenNum;
	}
	public void setLicenNum(String licenNum) {
		this.licenNum = licenNum;
	}
	public RecuperationCostAccountVo getRecuperationCostAccountVo() {
		return recuperationCostAccountVo;
	}
	public void setRecuperationCostAccountVo(
			RecuperationCostAccountVo recuperationCostAccountVo) {
		this.recuperationCostAccountVo = recuperationCostAccountVo;
	}
	public List<RecuperationCostAccountVo> getRecuperationCostAccountList() {
		return recuperationCostAccountList;
	}
	public void setRecuperationCostAccountList(
			List<RecuperationCostAccountVo> recuperationCostAccountList) {
		this.recuperationCostAccountList = recuperationCostAccountList;
	}
	public MedicalStatementVo getMedicalStatementVo() {
		return medicalStatementVo;
	}
	public void setMedicalStatementVo(MedicalStatementVo medicalStatementVo) {
		this.medicalStatementVo = medicalStatementVo;
	}
	public List<MedicalStatementVo> getMedicalStatementSelectList() {
		return medicalStatementSelectList;
	}
	public void setMedicalStatementSelectList(
			List<MedicalStatementVo> medicalStatementSelectList) {
		this.medicalStatementSelectList = medicalStatementSelectList;
	}
	public List<DiseaseStatementVo> getDiseaseStatementList() {
		return diseaseStatementList;
	}
	public void setDiseaseStatementList(
			List<DiseaseStatementVo> diseaseStatementList) {
		this.diseaseStatementList = diseaseStatementList;
	}
	public List<TreatmentStatementVo> getTreatmentStatementList() {
		return treatmentStatementList;
	}
	public void setTreatmentStatementList(
			List<TreatmentStatementVo> treatmentStatementList) {
		this.treatmentStatementList = treatmentStatementList;
	}
	public List<PrescriptnStatementVo> getPrescriptnStatementList() {
		return prescriptnStatementList;
	}
	public void setPrescriptnStatementList(
			List<PrescriptnStatementVo> prescriptnStatementList) {
		this.prescriptnStatementList = prescriptnStatementList;
	}
	public List<SpecificDetailVo> getSpecificDetailList() {
		return specificDetailList;
	}
	public void setSpecificDetailList(List<SpecificDetailVo> specificDetailList) {
		this.specificDetailList = specificDetailList;
	}
	public List<ErrorCheckVo> getErrorCheckList() {
		return errorCheckList;
	}
	public void setErrorCheckList(List<ErrorCheckVo> errorCheckList) {
		this.errorCheckList = errorCheckList;
	}
	
	
}
