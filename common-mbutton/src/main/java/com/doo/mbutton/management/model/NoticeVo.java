package com.doo.mbutton.management.model;

import java.util.List;

import com.doo.mbutton.common.model.PagingVo;


/**
 * @author leetaehun
 *
 */
public class NoticeVo extends PagingVo{
	private long noticeSeq=0;
	private String cd;
	private String subject;
	private String contents;
	private String delYn;
	private String userNm;
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	
	private String reCnt;
	private String fileYn;
	
	private String orgFileName;
	private String sysFIleName;
	private String filePath;
	private String fileSeq;
	private List<ReNoticeVo> reNoticeList;
	private List<FileVo> fileList;
	
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public long getNoticeSeq() {
		return noticeSeq;
	}
	public void setNoticeSeq(long noticeSeq) {
		this.noticeSeq = noticeSeq;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
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
	public List<ReNoticeVo> getReNoticeList() {
		return reNoticeList;
	}
	public void setReNoticeList(List<ReNoticeVo> reNoticeList) {
		this.reNoticeList = reNoticeList;
	}
	public List<FileVo> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVo> fileList) {
		this.fileList = fileList;
	}
	public String getReCnt() {
		return reCnt;
	}
	public void setReCnt(String reCnt) {
		this.reCnt = reCnt;
	}
	public String getFileYn() {
		return fileYn;
	}
	public void setFileYn(String fileYn) {
		this.fileYn = fileYn;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getSysFIleName() {
		return sysFIleName;
	}
	public void setSysFIleName(String sysFIleName) {
		this.sysFIleName = sysFIleName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}
	
}
