package com.doo.mbutton.management.model;

import java.io.Serializable;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
@SuppressWarnings("serial")
public class MenuVo extends PagingVo implements Serializable{
	private String menuId;
	private String grpId;
	private String menuLev;
	private String menuNm;
	private String linkUrl;
	private String imgPath;
	private String sortSeq;
	private String useYn;
	private String menuYn;
	private String loginNeedYn;
	private String menuDesc;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String navigation;		// 내비게이션 정보 
	private String userLevCd;		// 메뉴에 대한 유저의 접근 정보
	private String siteType;
	
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getUserLevCd() {
		return userLevCd;
	}
	public void setUserLevCd(String userLevCd) {
		this.userLevCd = userLevCd;
	}
	public String getNavigation() {
		return navigation;
	}
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
	
	/*
	 * 검색
	 */
	private String sLoginNeedYn;
	
	public String getsLoginNeedYn() {
		return sLoginNeedYn;
	}
	public void setsLoginNeedYn(String sLoginNeedYn) {
		this.sLoginNeedYn = sLoginNeedYn;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}	
	public String getMenuLev() {
		return menuLev;
	}
	public void setMenuLev(String menuLev) {
		this.menuLev = menuLev;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getSortSeq() {
		return sortSeq;
	}
	public void setSortSeq(String sortSeq) {
		this.sortSeq = sortSeq;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getLoginNeedYn() {
		return loginNeedYn;
	}
	public void setLoginNeedYn(String loginNeedYn) {
		this.loginNeedYn = loginNeedYn;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
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
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getMenuYn() {
		return menuYn;
	}
	public void setMenuYn(String menuYn) {
		this.menuYn = menuYn;
	}
	
	

}
