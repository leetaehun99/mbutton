package com.doo.mbutton.management.model;

import com.doo.mbutton.common.model.PagingVo;

public class MedicalInsurVo extends PagingVo{
	private String medicalInsurCd;		// 수가코드
	private String applyDt;				// 적용일자
	private String cdDivd;					// 분류번호
	private String korNm;					// 한글명
	private String engNm;					// 영문명
	private String divCd;					// 1_2구분
	private String operYn;					// 수술여부(0,9)
	private String cost1;					// 의원단가
	private String cost2;					// 병원급이상단가
	private String cost3;					// 치과병의원단가
	private String cost4;					// 보건기관단가
	private String cost5;					// 조산원단가
	private String cost6;					// 한방병원단가
	private String relativeVal;			// 상대가치점수
	private String calculationCd;			// 산정코드
	private String etc;						// 장구분
	private String typeCd;					// 행위구분(A:의과급여,B:의과 비급여)
	private String self50Yn;					// 본인부담 50
	private String self80Yn;					// 본인부담 80
	private String duplicateYn;					// 중복허용
	private String registerId;				
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private int cnt;
	
	// 심사지침 임시 테이블 변수 시작
	private String medicalInsurSeq;
	private String content;
	private String title;
	private String surgery;
	private String designation;
	private String salary;
	private String classification;
	private String etc1;
	private String etc2;
	// 심사지침 관련 임시 테이블 변수 끝
	
	public String getMedicalInsurSeq() {
		return medicalInsurSeq;
	}
	public void setMedicalInsurSeq(String medicalInsurSeq) {
		this.medicalInsurSeq = medicalInsurSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
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
	public String getEtc2() {
		return etc2;
	}
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	public String getMedicalInsurCd() {
		return medicalInsurCd;
	}
	public void setMedicalInsurCd(String medicalInsurCd) {
		this.medicalInsurCd = medicalInsurCd;
	}
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getCdDivd() {
		return cdDivd;
	}
	public void setCdDivd(String cdDivd) {
		this.cdDivd = cdDivd;
	}
	public String getKorNm() {
		return korNm;
	}
	public void setKorNm(String korNm) {
		this.korNm = korNm;
	}
	public String getEngNm() {
		return engNm;
	}
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}
	public String getOperYn() {
		return operYn;
	}
	public void setOperYn(String operYn) {
		this.operYn = operYn;
	}
	public String getCost1() {
		return cost1;
	}
	public void setCost1(String cost1) {
		this.cost1 = cost1;
	}
	public String getCost2() {
		return cost2;
	}
	public void setCost2(String cost2) {
		this.cost2 = cost2;
	}
	public String getCost3() {
		return cost3;
	}
	public void setCost3(String cost3) {
		this.cost3 = cost3;
	}
	public String getCost4() {
		return cost4;
	}
	public void setCost4(String cost4) {
		this.cost4 = cost4;
	}
	public String getCost5() {
		return cost5;
	}
	public void setCost5(String cost5) {
		this.cost5 = cost5;
	}
	public String getCost6() {
		return cost6;
	}
	public void setCost6(String cost6) {
		this.cost6 = cost6;
	}
	public String getRelativeVal() {
		return relativeVal;
	}
	public void setRelativeVal(String relativeVal) {
		this.relativeVal = relativeVal;
	}
	public String getCalculationCd() {
		return calculationCd;
	}
	public void setCalculationCd(String calculationCd) {
		this.calculationCd = calculationCd;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public String getSelf50Yn() {
		return self50Yn;
	}
	public void setSelf50Yn(String self50Yn) {
		this.self50Yn = self50Yn;
	}
	public String getSelf80Yn() {
		return self80Yn;
	}
	public void setSelf80Yn(String self80Yn) {
		this.self80Yn = self80Yn;
	}
	public String getDuplicateYn() {
		return duplicateYn;
	}
	public void setDuplicateYn(String duplicateYn) {
		this.duplicateYn = duplicateYn;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
