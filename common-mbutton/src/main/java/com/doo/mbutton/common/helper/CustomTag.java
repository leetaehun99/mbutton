package com.doo.mbutton.common.helper;

import java.io.IOException;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.doo.mbutton.common.model.CodeVo;
import com.doo.mbutton.common.service.CodeService;

public class CustomTag extends RequestContextAwareTag {
	private Log logger = LogFactory.getLog("MBUTTON.INFO");

	private static final long serialVersionUID = 8647034483927147905L;
	
	private WebApplicationContext webApplicationContext;
	
	private String type;
	private String name;
	private String groupCode;
	private String code;
	private String className;
	private String defaultCode;
	private String selectCode;
	private String ignoreValue;
	private String displayType;
	private String nonSelect; //셀렉트 제외
	private String eventNm; //이벤트리스너
	private String eventFn; //이벤트 펑션
	

	
	public String getEventNm() {
		return eventNm;
	}

	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}

	public String getEventFn() {
		return eventFn;
	}

	public void setEventFn(String eventFn) {
		this.eventFn = eventFn;
	}

	public String getNonSelect() {
		return nonSelect;
	}

	public void setNonSelect(String nonSelect) {
		this.nonSelect = nonSelect;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDefaultCode() {
		return defaultCode;
	}

	public void setDefaultCode(String defaultCode) {
		this.defaultCode = defaultCode;
	}

	public String getSelectCode() {
		return selectCode;
	}

	public void setSelectCode(String selectCode) {
		this.selectCode = selectCode;
	}
	
	public String getIgnoreValue() {
		return ignoreValue;
	}

	public void setIgnoreValue(String ignoreValue) {
		this.ignoreValue = ignoreValue;
	}
	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public int doStartTagInternal() {
		
		webApplicationContext = getRequestContext().getWebApplicationContext();
		CodeService codeService = (CodeService)webApplicationContext.getBean("codeService");
		
		try {

			pageContext.getOut().print(makeTag(codeService));
			
		} catch(IOException e) {
			logger.error(e.getMessage());
		}
		
		return SKIP_BODY;
	}
	
	/**
	 * Tag 생성
	 * @param codeService
	 * @return
	 */
	private String makeTag(CodeService codeService) {
		
		StringBuffer sb = new StringBuffer();
		String style="cursor:pointer";
		// 값
		if(type.equals("value")) {
			CodeVo codeVo = new CodeVo();
			codeVo.setGrpCd(this.groupCode);
			codeVo.setCd(this.selectCode);
			codeVo = codeService.selectTagCode(codeVo);
			sb.append(codeVo.getCdNm());
			
		// Select 박스
		} else if(type.equals("select")) {
			CodeVo codeVo = new CodeVo();
			codeVo.setGrpCd(this.groupCode);
			List<CodeVo> codeList = codeService.selectTagCodeList(codeVo);
			boolean codeCheck =	false;
			sb.append("\r\n<select id=\"");
			sb.append(this.name);
			sb.append("\" name=\"");
			sb.append(this.name);
			if(this.className != null) {
				sb.append("\" class=\"");
				sb.append(this.className);
			}
			sb.append("\" style=\"");
			sb.append(style);
			if(this.eventNm != null){
				sb.append("\" "+eventNm+"=\"");
				sb.append(eventFn);
			}
			sb.append("\">\r\n");
			
			if (!"Y".equals(this.nonSelect)) sb.append("\t<option value=\"\">선택해주세요.</option>\r\n");
			
			for(int i=0; i<codeList.size(); i++) {
				// 무시해야할 값이 있으면 Select option에 추가하지 않기 위함
				if( !codeList.get(i).getCd().equals(ignoreValue) ) {
					sb.append("\t<option value=\"");
					sb.append(codeList.get(i).getCd());
					sb.append("\"");
					if(selectCode != null || selectCode != "") {
						if(selectCode.equals(codeList.get(i).getCd())) sb.append(" selected ");
						codeCheck=true;
					}
					if(!codeCheck)	if(defaultCode != null) if(defaultCode.equals(codeList.get(i).getCd())) sb.append(" selected ");
					
					sb.append("/>");
					if("CD".equals(displayType)) sb.append(codeList.get(i).getCd()+"["+codeList.get(i).getCdNm()+"]");
					else if ("onlyCD".equals(displayType)) sb.append(codeList.get(i).getCd());
					else sb.append(codeList.get(i).getCdNm());
					sb.append("</option>\r\n");
				}
			}
			sb.append("</select>\r\n");
			
		// Text 박스
		} else if(type.equals("text")) {
			CodeVo codeVo = new CodeVo();
			codeVo.setCd(this.code);
			codeVo = codeService.selectCode(codeVo);
			sb.append("\r\n<input type=\"text\" id=\"");
			sb.append(this.name);
			sb.append("\" name=\"");
			sb.append(this.name);
			if(this.className != null) {
				sb.append("\" class=\"");
				sb.append(this.className);
			}
			sb.append("\" value=\"");
			sb.append(codeVo.getCdNm());
			sb.append("\" />\r\n");
			
		// Radio 박스
		} else if(type.equals("radio")) {
			CodeVo codeVo = new CodeVo();
			codeVo.setGrpCd(this.groupCode);
			List<CodeVo> codeList = codeService.selectTagCodeList(codeVo);
			
			sb.append("\r\n");
			for(int i=0; i<codeList.size(); i++) {			
				sb.append("<input type=\"radio\" name=\"");
				sb.append(this.name);
				sb.append("\" value=\"");
				sb.append(codeList.get(i).getCd());
				if(this.className != null) {
					sb.append("\" class=\"");
					sb.append(this.className);
				}
				sb.append("\" style=\"");
				sb.append(style+"\"");
				if(selectCode!=null) if(selectCode.equals(codeList.get(i).getCd())) sb.append(" checked ");
				sb.append("/> ");
				
				sb.append(codeList.get(i).getCdNm());
				sb.append("\r\n");
			}
			
		// Check 박스
		} else if(type.equals("checkbox")) {
			CodeVo codeVo = new CodeVo();
			codeVo.setGrpCd(this.groupCode);
			List<CodeVo> codeList = codeService.selectTagCodeList(codeVo);
			
			sb.append("\r\n");
			for(int i=0; i<codeList.size(); i++) {			
				sb.append("<input type=\"checkbox\" name=\"");
				sb.append(this.name);
				sb.append("\" value=\"");
				sb.append(codeList.get(i).getCd());
				if(this.className != null) {
					sb.append("\" class=\"");
					sb.append(this.className);
				}
				sb.append("\" style=\"");
				sb.append(style+"\"");
				if(selectCode!=null){//선택값이 있고
					if(selectCode.indexOf(",")!=-1){ //1개 이상이라면
						String selectCodes[]=selectCode.split(",");
						for(int y=0; y<selectCodes.length; y++){
							if(selectCodes[y].equals(codeList.get(i).getCd())) sb.append(" checked ");
						}
					}
				}
				sb.append("/> ");
				sb.append(codeList.get(i).getCdNm());
				sb.append("\r\n");
			}
		}
		
		return sb.toString();
	}
	
}
