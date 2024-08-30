package com.doo.mbutton.management.model;

import java.util.Map;



public class SamVo {
	private String key;
	private String name;
	private String value;
	private int intLength;
	private int floatLength;
	private String align;
	private Map<String,SamVo> samMap; 
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIntLength() {
		return intLength;
	}
	public void setIntLength(int intLength) {
		this.intLength = intLength;
	}
	public int getFloatLength() {
		return floatLength;
	}
	public void setFloatLength(int floatLength) {
		this.floatLength = floatLength;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Map<String, SamVo> getSamMap() {
		return samMap;
	}
	public void setSamMap(Map<String, SamVo> samMap) {
		this.samMap = samMap;
	}
	
}
