package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

public class DiseaVo extends PagingVo{
	private String mainDrugCd;		// 주성분코드
	private String diseaCd;			// 질병분류코드
	private String drugCd;			// 
	private String diseaDrugCd;	// 
	private String diseaDiv;		// 분류기준(C1:소,C2:세,C3:세세,C4:세세세,C5:없음)
	private String diseaHw;			// 표제어(A:1,B:2,C:없음)
	private String registYear;		// 등록년도
	private String diseaKorNm;		// 상병 한글명
	private String diseaEngNm;		// 상병 영문명
	private String lastYn;			//
	private String etc1;			// 검별 *,+
	private String etc2;			// 주석(A:주,B:포함,C:공백)
	private String registerId;		//
	private String registDthms;		//
	private String updaterId;		//
	private String updateDthms;		//
	private String drugCnt;			//
	private String useYn;
	
	private String searchDiseaCd;
	private String searchDrugCd;
	
	private String medicalInsurCd;		//수가코드
	private List<String> allDiseaCd;	//상병코드리스트
	private List<String> allDrugCd;		//약품코드리스트
	
	private String korNm;			//수가 한글명
	private char isAllDisea;		//삭제 체크(다수의 상병, 약품 삭제)
	
	public List<String> getAllDrugCd() {
		return allDrugCd;
	}
	public void setAllDrugCd(List<String> allDrugCd) {
		this.allDrugCd = allDrugCd;
	}
	public String getSearchDiseaCd() {
		return searchDiseaCd;
	}
	public void setSearchDiseaCd(String searchDiseaCd) {
		this.searchDiseaCd = searchDiseaCd;
	}
	public String getSearchDrugCd() {
		return searchDrugCd;
	}
	public void setSearchDrugCd(String searchDrugCd) {
		this.searchDrugCd = searchDrugCd;
	}
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	private String insertDisea;		// 등록할 상병코드 검색
	
	
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	public String getInsertDisea() {
		return insertDisea;
	}
	public void setInsertDisea(String insertDisea) {
		this.insertDisea = insertDisea;
	}
	public String getDiseaDrugCd() {
		return diseaDrugCd;
	}
	public void setDiseaDrugCd(String diseaDrugCd) {
		this.diseaDrugCd = diseaDrugCd;
	}
	public String getDrugCnt() {
		return drugCnt;
	}
	public void setDrugCnt(String drugCnt) {
		this.drugCnt = drugCnt;
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
	public String getDiseaDiv() {
		return diseaDiv;
	}
	public void setDiseaDiv(String diseaDiv) {
		this.diseaDiv = diseaDiv;
	}
	public String getDiseaHw() {
		return diseaHw;
	}
	public void setDiseaHw(String diseaHw) {
		this.diseaHw = diseaHw;
	}
	public String getRegistYear() {
		return registYear;
	}
	public void setRegistYear(String registYear) {
		this.registYear = registYear;
	}
	public String getDiseaKorNm() {
		return diseaKorNm;
	}
	public void setDiseaKorNm(String diseaKorNm) {
		this.diseaKorNm = diseaKorNm;
	}
	public String getDiseaEngNm() {
		return diseaEngNm;
	}
	public void setDiseaEngNm(String diseaEngNm) {
		this.diseaEngNm = diseaEngNm;
	}
	public String getLastYn() {
		return lastYn;
	}
	public void setLastYn(String lastYn) {
		this.lastYn = lastYn;
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
	public String getMedicalInsurCd() {
		return medicalInsurCd;
	}
	public void setMedicalInsurCd(String medicalInsurCd) {
		this.medicalInsurCd = medicalInsurCd;
	}
	public List<String> getAllDiseaCd() {
		return allDiseaCd;
	}
	public void setAllDiseaCd(List<String> allDiseaCd) {
		this.allDiseaCd = allDiseaCd;
	}
	public String getKorNm() {
		return korNm;
	}
	public void setKorNm(String korNm) {
		this.korNm = korNm;
	}
	public char getIsAllDisea() {
		return isAllDisea;
	}
	public void setIsAllDisea(char isAllDisea) {
		this.isAllDisea = isAllDisea;
	}
	
}