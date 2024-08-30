package com.doo.mbutton.management.model;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
public class DictionaryVo extends PagingVo{
	private String cd;
	private String korNm;
	private String engNm;
	private String searchDictionary;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	
	
	private String mode;
	private String type;
	private String type1;
	private String type2;
	private String type3;
	private String type4;
	private String cost;
	private String limitCost;
	
	private String applyDt;
	
	
	
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public String getType4() {
		return type4;
	}
	public void setType4(String type4) {
		this.type4 = type4;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getLimitCost() {
		return limitCost;
	}
	public void setLimitCost(String limitCost) {
		this.limitCost = limitCost;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
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
	public String getSearchDictionary() {
		return searchDictionary;
	}
	public void setSearchDictionary(String searchDictionary) {
		this.searchDictionary = searchDictionary;
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
