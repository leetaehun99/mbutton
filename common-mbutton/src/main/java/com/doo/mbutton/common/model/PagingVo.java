package com.doo.mbutton.common.model;

public class PagingVo  extends GeneralVo{

	private static final long serialVersionUID = -4954356072365025121L;
	
	private String readType = "data";	// data, count
	private int currentPage = 1;		// 현재 페이지
	private int rowPerPage = 10;		// 페이지당 Row수
	private int startRow = 0;			// 시작 Row
	private int endRow;					// 끝 Row
	private int totalCount;				// 총 Row수
	private int currentBlock = 0;		// 현재 블럭
	private int pagePerBlock = 10;		// 블럭당 페이지수
	private String pagingHtml;			// 페이징 HTML
	

	private int s_currentPage = 1;		// 현재 페이지
	private int s_rowPerPage = 10;		// 페이지당 Row수
	
	
	public int getS_currentPage() {
		return s_currentPage;
	}
	public void setS_currentPage(int s_currentPage) {
		this.s_currentPage = s_currentPage;
	}
	public int getS_rowPerPage() {
		return s_rowPerPage;
	}
	public void setS_rowPerPage(int s_rowPerPage) {
		this.s_rowPerPage = s_rowPerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		// 시작 Row = (현재 페이지 - 1) * 페이지당 Row수
		setStartRow((currentPage - 1) * rowPerPage);
		// 끝 Row = 시작Row + 페이지당 Row수
		setEndRow(startRow + rowPerPage);
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
		setCurrentPage(this.currentPage);
	}
	public int getStartRow() {
		return startRow;
	}
	private void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	private void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentBlock() {
		return currentBlock;
	}
	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public String getPagingHtml() {
		return pagingHtml;
	}
	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}
	public String getReadType() {
		return readType;
	}
	public void setReadType(String readType) {
		this.readType = readType;
	}
	
}
