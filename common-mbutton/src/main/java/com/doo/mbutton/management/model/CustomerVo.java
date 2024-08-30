package com.doo.mbutton.management.model;

import com.doo.mbutton.common.model.PagingVo;

public class CustomerVo extends PagingVo{
	private String drugCd;
	private String mainDrugCd;
	private String diseaCd;
	private String useYn;
	private String sSearchType;
	private String sSearchText;	
	private String registerId;		
	private String registDthms;		
	private String updaterId;		
	private String updateDthms;		
	
	
	
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
	public String getsSearchType() {
		return sSearchType;
	}
	public void setsSearchType(String sSearchType) {
		this.sSearchType = sSearchType;
	}
	public String getsSearchText() {
		return sSearchText;
	}
	public void setsSearchText(String sSearchText) {
		this.sSearchText = sSearchText;
	}
	public String getDrugCd() {
		return drugCd;
	}
	public void setDrugCd(String drugCd) {
		this.drugCd = drugCd;
	}
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	public String getDiseaCd() {
		return diseaCd;
	}
	public void setDiseaCd(String diseaCd) {
		this.diseaCd = diseaCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
