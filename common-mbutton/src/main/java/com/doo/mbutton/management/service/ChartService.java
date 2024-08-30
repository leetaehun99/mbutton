package com.doo.mbutton.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.ChartMapper;
import com.doo.mbutton.management.model.ChartVo;

@Service
public class ChartService{

	@Autowired
	private ChartMapper chartMapper;

	public List<ChartVo> selectDiseaStsChart() {
		return chartMapper.selectDiseaStsChart();
	}
	
	public List<String> selectxVal() {
		List<ChartVo> dbData = chartMapper.selectDiseaStsSubjectChart();
		List<String> resultList = uniquexVal(dbData);
		return resultList;
	}
	
	public List<Map<String, Object>> selectDiseaStsSubjectColumnChart(String type) {
		List<ChartVo> list = chartMapper.selectDiseaStsSubjectChart();
		List<Map<String, Object>> resultList = setChart(type, list, "");
		
		return resultList;
	}
	
	public List<Map<String, Object>> selectDiseaMappingTypePieChart(String type) {
		List<ChartVo> list = chartMapper.selectDiseaMappingTypeChart();
		List<Map<String, Object>> resultList = setChart(type, list, "type별 평균");
		return resultList;
	}
	
	public List<Map<String, Object>> selectDiseaStsSubjectComboChart(String type) {
		List<ChartVo> list = chartMapper.selectDiseaStsSubjectChart();
		List<Map<String, Object>> resultList = setChart(type, list, "");
		return resultList;
	}
	
	public List<Map<String, Object>> selectBillBubbleChart(String hspId, String type) {
		List<ChartVo> list = chartMapper.selectBillChart(hspId);
		List<Map<String, Object>> resultList = setChart(type, list, "");
		return resultList;
	}
	
	//차트 컨트롤러
	public List<Map<String, Object>> setChart(String type, List<ChartVo> dbData, String text) {
		List<Map<String, Object>> resultList = null;
		if("bar".equals(type) || "column".equals(type)) {
			resultList = setBase(dbData);
		}else if("pie".equals(type)) {
			resultList = setPie(dbData, text);
		}else if("bubble".equals(type)) {
			resultList = setBubble(dbData);
		}else if("combo".equals(type)) {
			resultList = setCombo(dbData);
		}else {
			resultList = setBase(dbData);
		}
		return resultList;
	}
	
	//가상테이블(행이 없는 경우 0의 값을 입력해주는 부분)
	public List<Map<String, Object>> virtualTable(List<ChartVo> dbData) {
		
		List<String> nameList = uniqueName(dbData);
		List<String> xValList = uniquexVal(dbData);
		
		List<Map<String, String>> preList = new ArrayList<Map<String,String>>();
		//xVal을 사용하지 않는 name이 있을 경우 가상으로 행을 만들어 주는 리스트  
		for(int i=0; i<nameList.size(); i++) {
			for(int j=0; j<xValList.size(); j++) {
				Map<String, String> preMap = new HashMap<String, String>();
				preMap.put("name", nameList.get(i));
				preMap.put("xVal", xValList.get(j));
				preList.add(preMap);
			}
		}
		
		//가상 리스트 확인 소스
		/*for(int i=0; i<preList.size(); i++) {
			Map<String, String> resultMap = preList.get(i);
			System.out.println("name" + i + " 확인 : " + resultMap.get("name"));
			System.out.println("xVal" + i + " 확인 : " + resultMap.get("xVal"));
		}*/
		
		//xVal을 사용하지 않는 name이 있을 경우 0의 값을 입력 해주는 리스트
		int j = 0;
		List<Map<String, Object>> settingList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<preList.size(); i++) {
			Map<String, String> baseMap = preList.get(i);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String baseName = baseMap.get("name");
			String basexVal = baseMap.get("xVal");
			int data = dbData.get(j).getData();
			String xVal = dbData.get(j).getxVal();
			if(basexVal.equals(xVal)) {
				resultMap.put("name", baseName);
				resultMap.put("data", data);
				resultMap.put("xVal", basexVal);
			}else {
				resultMap.put("name", baseName);
				resultMap.put("data", 0);
				resultMap.put("xVal", basexVal);
				j = j - 1;
			}
			settingList.add(resultMap);
			j++;
		}
		
		//0의 값이 제대로 입력되었는지 확인하는 리스트
		/*for(int i=0; i<settingList.size(); i++) {
			Map<String, Object> resultMap = settingList.get(i);
			System.out.println("resultname" + i + "확인 : " + resultMap.get("name"));
			System.out.println("resultxVal" + i + "확인 : " + resultMap.get("xVal"));
			System.out.println("resultdata" + i + "확인 : " + resultMap.get("data"));
		}*/
		return settingList;
	}
	/**
	*	 [{
	*         name: 'Tokyo',
	*         data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
	*
	*     }, {
	*         name: 'New York',
	*         data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
	*
	*     }, {
	*         name: 'London',
	*         data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]
	*
	*     }, {
	*         name: 'Berlin',
	*         data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]
	*
    *	 }]
     **/
	public List<Map<String, Object>> setBase(List<ChartVo> dbData) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(); //최종 차트 출력 리스트
		List<Integer> dataList = new ArrayList<Integer>(); // 최종 차트 data리스트
		List<Map<String, Object>> settingList = virtualTable(dbData); //없는 행의 경우 0을 입력해주는 테이블
		
		//highchart의 구조로 바꿔주는 리스트
		for(int i=0; i<settingList.size(); i++) {
			Map<String, Object> preMap = settingList.get(i);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String name = (String)preMap.get("name");
			int data = (Integer)preMap.get("data");
			if((i+1) == settingList.size()) {
				dataList.add(data);
				resultMap.put("name", name);
				resultMap.put("data", dataList);
				resultList.add(resultMap);
			}else if(settingList.get(i).get("name").equals(settingList.get(i+1).get("name"))) {
				dataList.add(data);
			}else {
				dataList.add(data);
				resultMap.put("name", name);
				resultMap.put("data", dataList);
				resultList.add(resultMap);
				dataList = new ArrayList<Integer>();
			}
			
		}
		
		//highchart 구조 확인하는 리스트
		/*for(int i=0; i<resultList.size(); i++) {
			System.out.println(i + "name : " + resultList.get(i).get("name"));
			System.out.println(i + "data : " + resultList.get(i).get("data"));
		}*/
		
		return resultList;
	}

		/**	[{
		 *      	type: 'pie',
		 *          name: 'Browser share',
		 *          data: 	[
		 *					['Firefox',   45.0],
		 *               	['IE',       26.8],
		 *               	{
		 *                   name: 'Chrome',
		 *                   y: 12.8,
		 *                   sliced: true,
		 *                   selected: true
		 *               	},
		 *               	['Safari',    8.5],
		 *               	['Opera',     6.2],
		 *               	['Others',   0.7]
		 *           		]
		 *       }]
		 **/
	public  List<Map<String, Object>> setPie(List<ChartVo> dbData, String text) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Object[][] dataArr = new Object[dbData.size()][2];
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("type", "pie");
		resultMap.put("name", text);
		System.out.println("size 확인 : " + dbData.size());
		for(int i=0; i<dbData.size(); i++) {
			for(int j=0; j<2; j++) {
				String name = dbData.get(i).getName();
				int data = dbData.get(i).getData();
				if(j==0 ) {
					dataArr[i][j] = name;
				}else {
					dataArr[i][j] = data;
				}
			}
		}
		resultMap.put("data", dataArr);
		resultList.add(resultMap);
		/*for(int i=0; i<resultList.size(); i++) {
			Map<String, Object> map = resultList.get(i);
			System.out.println("type 확인 : " + map.get("type"));
			System.out.println("name 확인 : " + map.get("name"));
			Object[][] resultArr = (Object[][]) map.get("data");
			for(int j=0; j<resultArr.length; j++) {
				for(int k=0; k<resultArr[j].length; k++) {
					System.out.println("data 확인 : " + resultArr[j][k]);
				}
			}
		}*/
		return resultList;
	}

	/**
	 *		[{
	 *      	data: [[97, 36, 79], [94, 74, 60], [68, 76, 58], [64, 87, 56], [68, 27, 73], [74, 99, 42], [7, 93, 87], [51, 69, 40], [38, 23, 33], [57, 86, 31]]
	 *      }, {
	 *          data: [[25, 10, 87], [2, 75, 59], [11, 54, 8], [86, 55, 93], [5, 3, 58], [90, 63, 44], [91, 33, 17], [97, 3, 56], [15, 67, 48], [54, 25, 81]]
	 *      }, {
	 *          data: [[47, 47, 21], [20, 12, 4], [6, 76, 91], [38, 30, 60], [57, 98, 64], [61, 17, 80], [83, 60, 13], [67, 78, 75], [64, 12, 10], [30, 77, 82]]
	 *      }]
	 **/
	public  List<Map<String, Object>> setBubble(List<ChartVo> dbData) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		List<List<Integer>> groupList = new ArrayList<List<Integer>>();
		
		for(int i=0; i<dbData.size(); i++) {
			List<Integer> subList = new ArrayList<Integer>();
			ChartVo chartVo = dbData.get(i);
			int xData = chartVo.getxData();
			int yData = chartVo.getyData();
			int sizeData = chartVo.getSizeData();
			subList.add(xData);
			subList.add(yData);
			subList.add(sizeData);
			groupList.add(subList);
		}
		resultMap.put("data", groupList);
		resultList.add(resultMap);
		
		//highchart구조 확인하는 리스트
		/*for(int i=0; i<resultList.size(); i++) {
			Map<String, Object> outMap = resultList.get(i);
			List<List<Integer>> gl = (List<List<Integer>>) outMap.get("data");
			for(int j=0; j<gl.size(); j++) {
				List<Integer> sl = gl.get(j);
				for(int k=0; k<sl.size(); k++) {
					int data = sl.get(k);
					System.out.println("bubble 확인 : " + data);
				}
			}
		}*/
		return resultList;
	}
	
	/**
	* 	[{
	*		type: 'column',
	*		name: 'Jane',
	*		data: [3, 2, 1, 3, 4]
	*		}, {
	*		type: 'column',
	*		name: 'John',
	*		data: [2, 3, 5, 7, 6]
	*		}, {
	*		type: 'column',
	*		name: 'Joe',
	*		data: [4, 3, 3, 9, 0]
	*		}, 
	*		1Map추가
	*		{
	*		type: 'spline',
	*		name: 'Average',
	*		data: [3, 2.67, 3, 6.33, 3.33],
	*		marker: {
	*    		lineWidth: 2,
	*    		lineColor: Highcharts.getOptions().colors[3],
	*    		fillColor: 'white'
	*		}
	*		}, 
	*		2Map추가
	*		{
	*		type: 'pie',
	*		name: 'Total consumption',
	*		data: [{
	*    		name: 'Jane',
	*    		y: 13,
	*    		color: Highcharts.getOptions().colors[0] // Jane's color
	*		}, {
	*    		name: 'John',
	*    		y: 23,
	*    		color: Highcharts.getOptions().colors[1] // John's color
	*		}, {
	*    		name: 'Joe',
	*    		y: 19,
	*    		color: Highcharts.getOptions().colors[2] // Joe's color
	*		}],
	*		center: [100, 80],
	*		size: 100,
	*		showInLegend: false,
	*		dataLabels: {
	*    		enabled: false
	*		}	
	*	}
	*	3Map 추가
	*]
	**/
	public  List<Map<String, Object>> setCombo(List<ChartVo> dbData) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Integer> dataList = new ArrayList<Integer>();
		List<Map<String, Object>> settingList = virtualTable(dbData);
		//highchart의 구조로 바꿔주는 리스트
		for(int i=0; i<settingList.size(); i++) {
			Map<String, Object> preMap = settingList.get(i);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String name = (String)preMap.get("name");
			int data = (Integer)preMap.get("data");
			if((i+1) == settingList.size()) {
				dataList.add(data);
				resultMap.put("name", name);
				resultMap.put("type", "column");
				resultMap.put("data", dataList);
				resultList.add(resultMap);
			}else if(settingList.get(i).get("name").equals(settingList.get(i+1).get("name"))) {
				dataList.add(data);
			}else {
				dataList.add(data);
				resultMap.put("name", name);
				resultMap.put("type", "column");
				resultMap.put("data", dataList);
				resultList.add(resultMap);
				dataList = new ArrayList<Integer>();
			}
			
		}
		
		Map<String, Object> categories3 = new HashMap<String, Object>();
		categories3.put("type", "pie");
		categories3.put("name", "Total consumption");
		
		List<Map<String, Object>> sumList = new ArrayList<Map<String, Object>>();
		for(int i=0; i<resultList.size(); i++) {
			Map<String, Object> resultMap = resultList.get(i);
			Map<String, Object> settingMap = new HashMap<String, Object>();
			@SuppressWarnings("unchecked")
			List<Integer> dataList2 = (List<Integer>) resultMap.get("data");
			int sum = 0;
			for(int j=0; j<dataList2.size(); j++) {
				sum += dataList2.get(j);
			}
			settingMap.put("name", resultMap.get("name"));
			settingMap.put("y", sum);
			sumList.add(settingMap);
		}
		categories3.put("data", sumList);
		int[] center = {800,80};
		categories3.put("center", center);
		categories3.put("size", 100);
		categories3.put("showInLegend", false);
		resultList.add(categories3);
	
		
		List<Integer> avgList = chartMapper.selectDiseaStsSubjectAvgChart();
		System.out.println(avgList);
		List<Float> resultAvgList = new ArrayList<Float>();
		int operand = uniqueName(dbData).size();
		for(int i=0; i<avgList.size(); i++) {
			float avg = avgList.get(i) / operand;
			resultAvgList.add(avg);
		}
		
		Map<String, Object> categories2 = new HashMap<String, Object>();
		categories2.put("type", "spline");
		categories2.put("name", "Average");
		categories2.put("data", resultAvgList);
		resultList.add(categories2);
		
		
		return resultList;
	}

	/*public static void main(String[] args){
		List<String> dbData  = new ArrayList<String>();
		dbData.add("1");
		dbData.add("2");
		dbData.add("3");
		dbData.add("4");
		Set<String> nameSet = new TreeSet<String>();
		for(int i =0; i<dbData.size(); i++) {
			nameSet.add(dbData.get(i));
			//System.out.println(dbData.get(i));
		}
		Iterator<String> it = nameSet.iterator();
		//Iterator<String> it1 = nameSet.iterator();
		List<String> resultList = new ArrayList<String>();
		while(it.hasNext()) {
			System.out.println("IT = " +it.next());
			Iterator<String> it1 = nameSet.iterator();
			while(it1.hasNext()) {
				System.out.println("SUB_IT"+it1.next());
			}
			
		}
	}*/
	//name 중복 제거
	private List<String> uniqueName(List<ChartVo> dbData ) {
		Set<String> nameSet = new TreeSet<String>();
		for(int i =0; i<dbData.size(); i++) {
			nameSet.add(dbData.get(i).getName());
		}
		Iterator<String> it = nameSet.iterator();
		List<String> resultList = new ArrayList<String>();
		while(it.hasNext()) {
				resultList.add(it.next());
		}
		return resultList;
	}
	
	//x축 중복 제거
	private List<String> uniquexVal(List<ChartVo> dbData ) {
		Set<String> xValSet = new TreeSet<String>();
		for(int i =0; i<dbData.size(); i++) {
			xValSet.add(dbData.get(i).getxVal());
		}
		Iterator<String> it = xValSet.iterator();
		List<String> resultList = new ArrayList<String>();
		while(it.hasNext()) {
			resultList.add(it.next());
		}
		return resultList;
	}
	
}
