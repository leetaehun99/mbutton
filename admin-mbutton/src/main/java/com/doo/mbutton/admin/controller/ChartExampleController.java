package com.doo.mbutton.admin.controller;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.doo.mbutton.common.helper.JsonMapper;

@Controller
public class ChartExampleController {
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;

	/*@RequestMapping(value="test")
	public void ChartExampleController(){
		
	}*/
	
	/*@RequestMapping
	public String selectCodeList(Model model, @ModelAttribute CodeVo codeVo,@RequestParam(value="cdLength") String cdLn){
		codeVo.setCdLn(cdLn);
		List<CodeVo> codelList = codeService.selectCodeList(codeVo);
		
		model.addAttribute("codeVo", codeVo);
		model.addAttribute("codeList", codelList);
		
		return "/common/code/code.default";
	}
	
	@RequestMapping(value = "/selectCodeList.json")
	public void selectCodeListJson(HttpServletRequest request,HttpServletResponse response,Model model, @ModelAttribute CodeVo codeVo){

		List<CodeVo> codelList = codeService.selectCodeList(codeVo);
		mapper.send(response, codelList);
	}*/
	
	//@RequestMapping("/aa.doo")
	//@RequestMapping(value="/test.doo")
	@RequestMapping
	public String chart(HttpServletRequest request,HttpServletResponse response, Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		return "/chart/example/example.default";
	}
	
	@RequestMapping(value = "/lineBasic.json")
	public void aLineBasicJson (HttpServletResponse response,Model model)throws Exception {
		ArrayList<Object> name = new ArrayList<Object>();
		name.add("Tokyo");
		name.add("New");
		name.add("Berlin");
		name.add("London");
		name.add("Seoul");
		ArrayList<Object> data1 = new ArrayList<Object>();
		data1.add(100);
		data1.add(10);
		data1.add(1000);
		data1.add(20);
		data1.add(200);
		data1.add(101);
		data1.add(3);
		data1.add(6);
		data1.add(9);
		ArrayList<Object> data2 = new ArrayList<Object>();
		data2.add(1);
		data2.add(2);
		data2.add(3);
		data2.add(4);
		data2.add(5);
		data2.add(6);
		data2.add(7);
		data2.add(8);
		data2.add(9000);
		ArrayList<Object> data3 = new ArrayList<Object>();
		data3.add(111);
		data3.add(222);
		data3.add(333);
		data3.add(444);
		data3.add(555);
		data3.add(666);
		data3.add(777);
		data3.add(888);
		data3.add(999);
		ArrayList<Object> data4 = new ArrayList<Object>();
		data4.add(11);
		data4.add(22);
		data4.add(33);
		data4.add(44);
		data4.add(55);
		data4.add(66);
		data4.add(77);
		data4.add(88);
		data4.add(99);
		
		ArrayList<Object> data5 = new ArrayList<Object>();
		data5.add(1111);
		data5.add(2222);
		data5.add(3333);
		data5.add(4444);
		data5.add(5555);
		data5.add(6666);
		data5.add(7777);
		data5.add(8888);
		data5.add(9999);

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(data1);
		data.add(data2);
		data.add(data3);
		data.add(data4);
		data.add(data5);
		
		Hashtable lineBasic = new Hashtable();
		lineBasic.put("name", name);
		lineBasic.put("data", data);

	    mapper.send(response, lineBasic);
	}
	
	@RequestMapping(value = "/barStacked.json")
	public void aBarStacked(HttpServletResponse response) throws Exception {
		mapper.send(response, barStacked());
	}
	
	public Map barStacked() throws Exception {
		//var json=eval([{name: 'John',data: [5, 3, 4, 7, 2]},{name: 'Jane',data: [2, 2, 3, 2, 1]},{name: 'Joe',data: [3, 4, 4, 2, 5]}]);
		String[] name = {"John", "Jane", "Joe"};
		int[][] data = {{5,3,4,7,2}, {2,2,3,2,1}, {3,4,4,2,5}};
		Map<String, Object> map = new Hashtable();
		map.put("name", name);
		map.put("data", data);
		return map;
	}
	
	@RequestMapping(value = "/pieBasic.json")
	public void apieBasic(HttpServletResponse response) throws Exception {
		mapper.send(response, pieBasic());
	}
	
	public Hashtable pieBasic() throws Exception {
		Hashtable ht = new Hashtable();
		ht.put("type", "pie");
		ht.put("name", "Browser share");
		String[] browserName = {"Firefox","IE","Safari","Opera","Other","Chrome"};
		double[] browserUse = {45.0, 26.8, 8.5, 6.2, 0.7, 12.8};
		ht.put("browserName", browserName);
		ht.put("browserUse", browserUse);
		return ht;
		
	}

	@RequestMapping(value = "/bubble.json")
	public void aBubble(HttpServletResponse response) throws Exception {
		mapper.send(response, bubble());
	}
	
	public Hashtable bubble() throws Exception {
		Hashtable ht = new Hashtable();
		
		int [][] data1 = {{97, 36, 79}, {94, 74, 60}, {68, 76, 58}, {64, 87, 56}, {68, 27, 73}, {74, 99, 42}, {7, 93, 87}, {51, 69, 40}, {38, 23, 33}, {57, 86, 31}};
		int [][] data2 = {{25, 10, 87}, {2, 75, 59}, {11, 54, 8}, {86, 55, 93}, {5, 3, 58}, {90, 63, 44}, {91, 33, 17}, {97, 3, 56}, {15, 67, 48}, {54, 25, 81}};
		int [][] data3 = {{47, 47, 21}, {20, 12, 4}, {6, 76, 91}, {38, 30, 60}, {57, 98, 64}, {61, 17, 80}, {83, 60, 13}, {67, 78, 75}, {64, 12, 10}, {30, 77, 82}};
		List list = new ArrayList();
		list.add(data1);
		list.add(data2);
		list.add(data3);
		ht.put("data", list);
		return ht;
		
	}

	@RequestMapping(value = "/combo.json")
	public void aCombo(HttpServletResponse response) throws Exception {
		mapper.send(response, combo());
	}
	
	public Hashtable combo() throws Exception {
		Hashtable ht = new Hashtable();
		
		int [][] data = {{3, 2, 1, 3, 4},{2, 3, 5, 7, 6},{4, 3, 3, 9, 0}};
		String [] name = {"Jane", "John", "Joe"};
		ht.put("name", name);
		ht.put("data", data);
		return ht;
		
	}
	
}
