package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

public class DrugVo extends PagingVo{	
	
	private String drugCd;			//약가코드
	private String diseaCd;			//상병코드
	private String applyDt;			//적용일자
	private String parDiv;			//급여구분    :     A:급여 B:급여정지 C:보훈급여 D:삭제 E:산정불가
	private String limitCost;		//상한가
	private String usePay;			//사용장려비
	private String injectPass;		//투여경로    :     A:기타 B:내복 C:외용 D:주사

	private String drugNm;			//약가명
	private String standard;		//규격
	private String unit;			//단위
	private String company;
	
	private String drugNotify;		//분류번호
	private String mainDrugCd;		//주성분코드
	private String speGen;			//전문/일반	:	A:전문의약품 B:일반의약품
	private String exitCd;			//퇴장방지구분\n(A:사용장려.B:원가+장려,C:원가보존)
	private String drugDivd;		//약품동등구분\n(A:생동(사후통보),B:,의약품동등C:필드값없음)
	private String drugReplaceYn;	//저가약대체여부(Y,N)
	
	private String specificDivd;	//예외의약구분
	private String medicineDivd;	//임의조제불가(A:마약,B:오남용 의약품 ,C:한외마약,D:향정신성의약품)
	private String notifyDt;		//고시일자
	private String respondCd;		//대응코드
	private String rareYn;			//희귀의약품구분(Y,N)
	private String sellDt;			//판매예정일
	private String drugLimitDiffExceptYn;	//약제상한차액지급제외(Y,N)
	private String ext1;			//기타
	private String registYear;		//등록한 년도
	private String registerId;		//등록한 id
	private String registDthms;		//등록날짜
	private String updaterId;		//마지막 수정자id
	private String updateDthms;		//마지막 수정날짜
	private String drugEnNm;		//약가영문명
	private String mainDrugNm;		//주성분영문명
	
	private String orgNm;
	private String val;
	private String mainDrugYn;		
	private String drugEfficacy;	//효능효과
	private String drugDosage;		//용법용량
	private String drugTaboo;		//금지
	private String drugNote;		//주의
	private String drugSideEffect;		//부작용
	private String drugInteraction;		//상호작용
	
	
	private String medical_insur_cd;
	private String medical_insur_seq;
	private String content;
	private String title;
	private String Surgery;
	private String designation;
	private String Salary;
	private String classification;
	private String etc1;
	private String drugScreening;
	private String drugCheck;
	private List<String> allDrugCd; 
	
	public String getDiseaCd() {
		return diseaCd;
	}
	public void setDiseaCd(String diseaCd) {
		this.diseaCd = diseaCd;
	}
	public String getDrugCheck() {
		return drugCheck;
	}
	public void setDrugCheck(String drugCheck) {
		this.drugCheck = drugCheck;
	}
	public String getDrugScreening() {
		return drugScreening;
	}
	public void setDrugScreening(String drugScreening) {
		this.drugScreening = drugScreening;
	}
	public String getMedical_insur_cd() {
		return medical_insur_cd;
	}
	public void setMedical_insur_cd(String medical_insur_cd) {
		this.medical_insur_cd = medical_insur_cd;
	}
	public String getMedical_insur_seq() {
		return medical_insur_seq;
	}
	public void setMedical_insur_seq(String medical_insur_seq) {
		this.medical_insur_seq = medical_insur_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSurgery() {
		return Surgery;
	}
	public void setSurgery(String surgery) {
		Surgery = surgery;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDrugTaboo() {
		return drugTaboo;
	}
	public void setDrugTaboo(String drugTaboo) {
		this.drugTaboo = drugTaboo;
	}
	public String getDrugNote() {
		return drugNote;
	}
	public void setDrugNote(String drugNote) {
		this.drugNote = drugNote;
	}
	public String getDrugSideEffect() {
		return drugSideEffect;
	}
	public void setDrugSideEffect(String drugSideEffect) {
		this.drugSideEffect = drugSideEffect;
	}
	public String getDrugInteraction() {
		return drugInteraction;
	}
	public void setDrugInteraction(String drugInteraction) {
		this.drugInteraction = drugInteraction;
	}
	private String diseaCnt;		//상병 수
	
	public String getDrugDosage() {
		return drugDosage;
	}
	public void setDrugDosage(String drugDosage) {
		this.drugDosage = drugDosage;
	}
	public String getDiseaCnt() {
		return diseaCnt;
	}
	public void setDiseaCnt(String diseaCnt) {
		this.diseaCnt = diseaCnt;
	}
	public String getDrugEfficacy() {
		return drugEfficacy;
	}
	public void setDrugEfficacy(String drugEfficacy) {
		this.drugEfficacy = drugEfficacy;
	}
	public String getMainDrugYn() {
		return mainDrugYn;
	}
	public void setMainDrugYn(String mainDrugYn) {
		this.mainDrugYn = mainDrugYn;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getDrugCd() {
		return drugCd;
	}
	public void setDrugCd(String drugCd) {
		this.drugCd = drugCd;
	}
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getParDiv() {
		return parDiv;
	}
	public void setParDiv(String parDiv) {
		this.parDiv = parDiv;
	}
	public String getLimitCost() {
		return limitCost;
	}
	public void setLimitCost(String limitCost) {
		this.limitCost = limitCost;
	}
	public String getUsePay() {
		return usePay;
	}
	public void setUsePay(String usePay) {
		this.usePay = usePay;
	}
	public String getInjectPass() {
		return injectPass;
	}
	public void setInjectPass(String injectPass) {
		this.injectPass = injectPass;
	}
	public String getDrugNm() {
		return drugNm;
	}
	public void setDrugNm(String drugNm) {
		this.drugNm = drugNm;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDrugNotify() {
		return drugNotify;
	}
	public void setDrugNotify(String drugNotify) {
		this.drugNotify = drugNotify;
	}
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	public String getSpeGen() {
		return speGen;
	}
	public void setSpeGen(String speGen) {
		this.speGen = speGen;
	}
	public String getExitCd() {
		return exitCd;
	}
	public void setExitCd(String exitCd) {
		this.exitCd = exitCd;
	}
	public String getDrugDivd() {
		return drugDivd;
	}
	public void setDrugDivd(String drugDivd) {
		this.drugDivd = drugDivd;
	}
	public String getDrugReplaceYn() {
		return drugReplaceYn;
	}
	public void setDrugReplaceYn(String drugReplaceYn) {
		this.drugReplaceYn = drugReplaceYn;
	}
	public String getSpecificDivd() {
		return specificDivd;
	}
	public void setSpecificDivd(String specificDivd) {
		this.specificDivd = specificDivd;
	}
	public String getMedicineDivd() {
		return medicineDivd;
	}
	public void setMedicineDivd(String medicineDivd) {
		this.medicineDivd = medicineDivd;
	}
	public String getNotifyDt() {
		return notifyDt;
	}
	public void setNotifyDt(String notifyDt) {
		this.notifyDt = notifyDt;
	}
	public String getRespondCd() {
		return respondCd;
	}
	public void setRespondCd(String respondCd) {
		this.respondCd = respondCd;
	}
	public String getRareYn() {
		return rareYn;
	}
	public void setRareYn(String rareYn) {
		this.rareYn = rareYn;
	}
	public String getSellDt() {
		return sellDt;
	}
	public void setSellDt(String sellDt) {
		this.sellDt = sellDt;
	}
	public String getDrugLimitDiffExceptYn() {
		return drugLimitDiffExceptYn;
	}
	public void setDrugLimitDiffExceptYn(String drugLimitDiffExceptYn) {
		this.drugLimitDiffExceptYn = drugLimitDiffExceptYn;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getRegistYear() {
		return registYear;
	}
	public void setRegistYear(String registYear) {
		this.registYear = registYear;
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
	public String getDrugEnNm() {
		return drugEnNm;
	}
	public void setDrugEnNm(String drugEnNm) {
		this.drugEnNm = drugEnNm;
	}
	public String getMainDrugNm() {
		return mainDrugNm;
	}
	public void setMainDrugNm(String mainDrugNm) {
		this.mainDrugNm = mainDrugNm;
	}
	public List<String> getAllDrugCd() {
		return allDrugCd;
	}
	public void setAllDrugCd(List<String> allDrugCd) {
		this.allDrugCd = allDrugCd;
	}
	
}