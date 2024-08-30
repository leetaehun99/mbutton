package com.doo.mbutton.front.controller;


import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.management.model.ChartVo;
import com.doo.mbutton.management.service.ChartService;

@Controller
public class ChartController {
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;

	@Autowired
	private ChartService chartService;
	
	@RequestMapping
	public String selectChartExample(HttpServletRequest request,HttpServletResponse response, @ModelAttribute ChartVo chartVo, Model model) {
		List<ChartVo> list = chartService.selectDiseaStsChart();
		model.addAttribute("chartList", list);
		return "/chart/example/chartExample.default";
	}
		
	@RequestMapping(value = "/selectDiseaStsSubjectColumnChart.json")
	public void selectDiseaStsSubjectColumnChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		List<Map<String, Object>> list = chartService.selectDiseaStsSubjectColumnChart(type);
		
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/selectDiseaMappingTypePieChart.json")
	public void selectDiseaMappingTypePieChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		List<Map<String, Object>> list = chartService.selectDiseaMappingTypePieChart(type);
		
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/selectDiseaStsSubjectComboChart.json")
	public void selectDiseaStsSubjectComboChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		List<Map<String, Object>> list = chartService.selectDiseaStsSubjectComboChart(type);
		
		mapper.send(response, list);
	}
	@RequestMapping(value = "/selectBillBubbleChart.json")
	public void selectBillBubbleChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String hspId = request.getParameter("hspId");
		List<Map<String, Object>> list = chartService.selectBillBubbleChart(hspId, type);
		
		mapper.send(response, list);
	}
	
	@RequestMapping(value = "/selectxVal.json")
	public void selectxVal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> list = chartService.selectxVal();		
		mapper.send(response, list);
	}
	
}
