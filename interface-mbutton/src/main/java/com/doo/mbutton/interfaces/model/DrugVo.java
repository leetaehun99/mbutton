package com.doo.mbutton.interfaces.model;

public class DrugVo{	
	

	private String drugCd;			//약가코드
	private String parDiv;			//급여구분    :     A:급여 B:급여정지 C:보훈급여 D:삭제 E:산정불가
	private String limitCost;		//상한가
	private String injectPass;		//투여경로    :     A:기타 B:내복 C:외용 D:주사

	private String drugNm;			//약가명
	private String drugNotify;		//분류번호
	private String mainDrugCd;		//주성분코드
	private String mainDrugNm;		//주성분영문명
	
	private String drugEnNm;		//약가영문명		
	private String drugEfficacy;	//효능효과
	private String drugDosage;		//용법용량
	private String drugTaboo;		//금지
	public String getDrugCd() {
		return drugCd;
	}
	public void setDrugCd(String drugCd) {
		this.drugCd = drugCd;
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
	public String getMainDrugNm() {
		return mainDrugNm;
	}
	public void setMainDrugNm(String mainDrugNm) {
		this.mainDrugNm = mainDrugNm;
	}
	public String getDrugEnNm() {
		return drugEnNm;
	}
	public void setDrugEnNm(String drugEnNm) {
		this.drugEnNm = drugEnNm;
	}
	public String getDrugEfficacy() {
		return drugEfficacy;
	}
	public void setDrugEfficacy(String drugEfficacy) {
		this.drugEfficacy = drugEfficacy;
	}
	public String getDrugDosage() {
		return drugDosage;
	}
	public void setDrugDosage(String drugDosage) {
		this.drugDosage = drugDosage;
	}
	public String getDrugTaboo() {
		return drugTaboo;
	}
	public void setDrugTaboo(String drugTaboo) {
		this.drugTaboo = drugTaboo;
	}
	
}