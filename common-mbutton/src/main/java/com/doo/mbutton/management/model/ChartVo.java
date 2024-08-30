package com.doo.mbutton.management.model;

import com.doo.mbutton.common.model.PagingVo;

public class ChartVo extends PagingVo{
	
	private String name;
	private int data;
	private String xVal;
	private String xTitle;
	private int xData;
	private int yData;
	private int sizeData;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public String getxVal() {
		return xVal;
	}
	public void setxVal(String xVal) {
		this.xVal = xVal;
	}
	public String getxTitle() {
		return xTitle;
	}
	public void setxTitle(String xTitle) {
		this.xTitle = xTitle;
	}
	public int getxData() {
		return xData;
	}
	public void setxData(int xData) {
		this.xData = xData;
	}
	public int getyData() {
		return yData;
	}
	public void setyData(int yData) {
		this.yData = yData;
	}
	public int getSizeData() {
		return sizeData;
	}
	public void setSizeData(int sizeData) {
		this.sizeData = sizeData;
	}
	
}
