package com.doo.mbutton.common.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonMapper {
	
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	// Mapper
	private ObjectMapper mapper = null;
	
	// 공통 Util
	@Autowired
	private CommonUtil cUtil;
	
	/**
	 * Constructor
	 */
	public JsonMapper() {
		logger.info("JsonMapper new instance");
		mapper = new ObjectMapper();
	}
	
	/**
	 * Object를 Json으로 변환
	 * @param response
	 * @param obj
	 */
	public void send(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			mapper.writeValue(response.getWriter(), obj);
			
			
			//displayConsole(cUtil.convertObjectToJson(obj));
			
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Json 화면에 출력
	 * @param json
	 */
	public void displayConsole(String json) {
		//System.out.println("json"+json);
		logger.debug(json);
	}
	
	public JSONObject jsonObject(List<Map<String,String>> list,String name){
		JSONArray jsonArray = JSONArray.fromObject(list);
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put(name, jsonArray);
		 JSONObject jsonObject = JSONObject.fromObject(map);
		 return jsonObject;
	}
}
