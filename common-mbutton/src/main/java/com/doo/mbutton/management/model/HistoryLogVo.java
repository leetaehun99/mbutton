package com.doo.mbutton.management.model;

import javax.servlet.http.HttpServletRequest;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.common.helper.SessionManager;

public class HistoryLogVo{
	private char type;
	private String msg;
	private String remoteIp;
	private String registerId;
	private String registDthms;
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
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
	
	public HistoryLogVo setIpUserid(HistoryLogVo historyLogVo, HttpServletRequest request) throws Exception{
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		historyLogVo.setRegisterId(sessionVo.getUserId());
		String clientIp = CommonUtil.getInstance().getClientIp(request);
		historyLogVo.setRemoteIp(clientIp);
		return historyLogVo;
	}
}
