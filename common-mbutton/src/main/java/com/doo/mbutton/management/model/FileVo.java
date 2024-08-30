package com.doo.mbutton.management.model;


/**
 * @author leetaehun
 *
 */
public class FileVo{
	private long fileSeq;
	private long noticeSeq;
	private String orgFileName;
	private String sysFileName;
	private String uploadPath;
	private String filePath;
	private Long recpCstClmSeq;
	private String pathType;
	
	public long getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(long fileSeq) {
		this.fileSeq = fileSeq;
	}
	public long getNoticeSeq() {
		return noticeSeq;
	}
	public void setNoticeSeq(long noticeSeq) {
		this.noticeSeq = noticeSeq;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getSysFileName() {
		return sysFileName;
	}
	public void setSysFileName(String sysFileName) {
		this.sysFileName = sysFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public Long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(Long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public String getPathType() {
		return pathType;
	}
	public void setPathType(String pathType) {
		this.pathType = pathType;
	}
	
}
