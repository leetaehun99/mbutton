package com.doo.mbutton.management.model;

import com.doo.mbutton.common.model.PagingVo;

public class RelativeValueVo extends PagingVo{
	private String seq;
	private String rv1;//병원,요양병원,종합병원 점수
	private String rv2;//의원 점수
	private String rv3;//치과의원 치과병원
	private String rv4;//한의원 한방병원
	private String rv5;//조산원
	private String rv6;//약국 한국희귀의약품센터
	private String rv7;//보건소,보건의료원
	private String year;
	
	private String registerId;		//
	private String registDthms;		//
	private String updaterId;		//
	private String updateDthms;		//
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRv1() {
		return rv1;
	}
	public void setRv1(String rv1) {
		this.rv1 = rv1;
	}
	public String getRv2() {
		return rv2;
	}
	public void setRv2(String rv2) {
		this.rv2 = rv2;
	}
	public String getRv3() {
		return rv3;
	}
	public void setRv3(String rv3) {
		this.rv3 = rv3;
	}
	public String getRv4() {
		return rv4;
	}
	public void setRv4(String rv4) {
		this.rv4 = rv4;
	}
	public String getRv5() {
		return rv5;
	}
	public void setRv5(String rv5) {
		this.rv5 = rv5;
	}
	public String getRv6() {
		return rv6;
	}
	public void setRv6(String rv6) {
		this.rv6 = rv6;
	}
	public String getRv7() {
		return rv7;
	}
	public void setRv7(String rv7) {
		this.rv7 = rv7;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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