package com.doo.mbutton.common.model;

import java.io.Serializable;

public class GeneralVo implements Serializable{
	private String result;
	private String alertMsg;
	private String redirectUrl;
	private String searchType;
	private String searchText;
	private String[] searchTextList;
	private String sUseYn;
	private String sSortOrder="UPDATE_DTHMS";
	private String sSortType="DESC";
	private String encKey="kj32h498y49328h4324h329";
	private String mode;
	
	private String del;
	private String[] arrDel;
	private String s_searchType;
	private String s_searchText;
	private String s_sUseYn;
	private String s_sSortOrder="UPDATE_DTHMS";
	private String s_sSortType="DESC";
	private String[] historyMsg;
	
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String[] getArrDel() {
		return arrDel;
	}
	public void setArrDel(String[] arrDel) {
		this.arrDel = arrDel;
	}
	public String[] getSearchTextList() {
		return searchTextList;
	}
	public void setSearchTextList(String[] searchTextList) {
		this.searchTextList = searchTextList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getS_searchType() {
		return s_searchType;
	}
	public void setS_searchType(String s_searchType) {
		this.s_searchType = s_searchType;
	}
	public String getS_searchText() {
		return s_searchText;
	}
	public void setS_searchText(String s_searchText) {
		this.s_searchText = s_searchText;
	}
	public String getS_sUseYn() {
		return s_sUseYn;
	}
	public void setS_sUseYn(String s_sUseYn) {
		this.s_sUseYn = s_sUseYn;
	}
	public String getS_sSortOrder() {
		return s_sSortOrder;
	}
	public void setS_sSortOrder(String s_sSortOrder) {
		this.s_sSortOrder = s_sSortOrder;
	}
	public String getS_sSortType() {
		return s_sSortType;
	}
	public void setS_sSortType(String s_sSortType) {
		this.s_sSortType = s_sSortType;
	}
	public String getEncKey() {
		return encKey;
	}
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText != null ? searchText.trim() : searchText;
	}
	public String getsUseYn() {
		return sUseYn;
	}
	public void setsUseYn(String sUseYn) {
		this.sUseYn = sUseYn;
	}
	public String getsSortOrder() {
		return sSortOrder;
	}
	public void setsSortOrder(String sSortOrder) {
		this.sSortOrder = sSortOrder;
	}
	public String getsSortType() {
		return sSortType;
	}
	public void setsSortType(String sSortType) {
		this.sSortType = sSortType;
	}
	public String[] getHistoryMsg() {
		return historyMsg;
	}
	public void setHistoryMsg(String[] historyMsg) {
		this.historyMsg = historyMsg;
	}
}
