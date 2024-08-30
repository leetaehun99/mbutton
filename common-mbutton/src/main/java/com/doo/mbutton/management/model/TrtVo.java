package com.doo.mbutton.management.model;


import java.util.List;

import com.doo.mbutton.common.model.PagingVo;

public class TrtVo extends PagingVo{
	private String trtCd;	// 코드
	private String trtNm;	// 품명
	private String trtStand;	// 규격
	private String trtUnit;		// 단위
	private String trtCompany;	// 제조회사
	private String trtMainCd;	// 재질
	private String trtSeller;	// 수입판매업소
	private String trtLimitCost;	// 상한가 (VAT 포함)
	private String trtDivd;		//A. 본인일부부담 B. 비급여 C 비급여(인체조직 D. 정액수가 E 100/100 정액수가
	private String etc1;		// 적용일자
	private String etc2;		// 비고
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;

	public String getTrtDivd() {
		return trtDivd;
	}

	public void setTrtDivd(String trtDivd) {
		this.trtDivd = trtDivd;
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

	private List<DrugMsgVo> msgList;

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

	public String getTrtStand() {
		return trtStand;
	}

	public void setTrtStand(String trtStand) {
		this.trtStand = trtStand;
	}

	public String getTrtUnit() {
		return trtUnit;
	}

	public void setTrtUnit(String trtUnit) {
		this.trtUnit = trtUnit;
	}

	public String getTrtCompany() {
		return trtCompany;
	}

	public void setTrtCompany(String trtCompany) {
		this.trtCompany = trtCompany;
	}

	public String getTrtMainCd() {
		return trtMainCd;
	}

	public void setTrtMainCd(String trtMainCd) {
		this.trtMainCd = trtMainCd;
	}

	public String getTrtSeller() {
		return trtSeller;
	}

	public void setTrtSeller(String trtSeller) {
		this.trtSeller = trtSeller;
	}

	public String getTrtLimitCost() {
		return trtLimitCost;
	}

	public void setTrtLimitCost(String trtLimitCost) {
		this.trtLimitCost = trtLimitCost;
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

	public List<DrugMsgVo> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<DrugMsgVo> msgList) {
		this.msgList = msgList;
	}
	
	
}
