package com.doo.mbutton.common.model;

public class CodeVo extends PagingVo{
	private String cd;
	private String cdLn;
	private String ectKey;
	private String grpCd;
	private String grpCdNm;
	private String cdNm;
	private String useYn;
	private String sort;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}	
	public String getCdLn() {
		return cdLn;
	}
	public void setCdLn(String cdLn) {
		this.cdLn = cdLn;
	}
	public String getEctKey() {
		return ectKey;
	}
	public void setEctKey(String ectKey) {
		this.ectKey = ectKey;
	}
	public String getGrpCd() {
		return grpCd;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}	
	public String getGrpCdNm() {
		return grpCdNm;
	}
	public void setGrpCdNm(String grpCdNm) {
		this.grpCdNm = grpCdNm;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
}
