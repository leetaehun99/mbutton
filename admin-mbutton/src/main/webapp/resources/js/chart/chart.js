//VO처럼 데이터를 setting 하는 함수

 /* 		
  * 	{
  *         name: 'Tokyo',
  *         data: [100, 10, 1000, 20, 200, 200,3, 3, 3]
  *      }, {
  *         name: 'New York',
  *         data: [1, 2, 3,4,5,6,7,8,9000]
  *      }, {
  *         name: 'Berlin',
  *         data: [2, 4, 6,8,10,12,14,16,5]
  *      }, {
  *         name: 'London',
  *         data: [3, 6,9,12,15,18,21,24,27]
  *      }, {
  *         name: 'Seoul',
  *         data: [113, 116,119,1112,1115,1118,1121,1124,1127]
  *      }
 */
//series 기본틀(line, bar 사용)
var series = "";
function setSeries(result) {
	//alert("base : " + result);
series = "[";
	for(var i=0; i <result.name.length; i++){
		series += "{ name : '" + result.name[i] + "'";
		if(i == (result.name.length - 1)) {
			series += ", data : [" + result.data[i] + "] } ]";
		}else {
			series += ", data : [" + result.data[i] + "] }, ";
		}

		//alert(series);
	}
}

/*
 *		[{
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
 */
//series 기본틀(pie 사용)
function setSeriesToPie(result, point) {
	//alert("pie : " + result);
	series ="[{";
	series += "type : '" + result.type + "', ";
	series += "name : '" + result.name + "', ";
	
	series += "data : [";
	for(var i = 0; i<result.browserName.length; i++) {
		if(point != null && point !="" && point != "undefined") {			

			if(result.browserName[i] == point) {
				series += "{ name : '" + point + "',";
				series += "y : " + result.browserUse[i] + ",";
				series += "sliced : true,";
				series += "selected : true ";
				
				if(i == (result.browserName.length - 1)) {
					series += "} ]";
				} else {
					series += "},";
				}
				continue;
			}
		}
         
		series += "{ name : '" + result.browserName[i] + "', ";
		if(i == (result.browserName.length - 1)) {
			series += " y : " + result.browserUse[i] + "} ]";
		} else {
			series += " y : " + result.browserUse[i] + "}, ";
		}
		
	}
	series += "}]";

	/*obj = eval('(' + series + ')');
	obj[0].superpower = "김영철";
	document.write(obj[0]);*/
}

/*
 *		[{
 *      	data: [[97, 36, 79], [94, 74, 60], [68, 76, 58], [64, 87, 56], [68, 27, 73], [74, 99, 42], [7, 93, 87], [51, 69, 40], [38, 23, 33], [57, 86, 31]]
 *      }, {
 *          data: [[25, 10, 87], [2, 75, 59], [11, 54, 8], [86, 55, 93], [5, 3, 58], [90, 63, 44], [91, 33, 17], [97, 3, 56], [15, 67, 48], [54, 25, 81]]
 *      }, {
 *          data: [[47, 47, 21], [20, 12, 4], [6, 76, 91], [38, 30, 60], [57, 98, 64], [61, 17, 80], [83, 60, 13], [67, 78, 75], [64, 12, 10], [30, 77, 82]]
 *      }]
 */
//series 기본틀(bubble 사용)
function setSeriesToBubble(result) {
	//alert("bubble : " + result);
	series = "[";
	for(var i=0; i< result.data.length; i++) {
		series += "{ data : [";
		//document.write(result.data[i] + " = ");
		for(var j=0; j< result.data[i].length; j++) {
			if( j == (result.data[i].length - 1)) {
				series += "[" + result.data[i][j] + "]";
			}else {
				series += "[" + result.data[i][j] + "],";
			}
			//document.write(result.data[i][j] + " / ");
		}
		if(i == (result.data.length - 1)) {
			series += "]}";
		}else {
			series += "]},";
		}
	}
	series += "]";
	//document.write("<br><br><br>" + series);

	/*obj = eval('(' + series + ')');
	obj[0].superpower = "김영철";
	document.write(obj[0]);*/
}

/*[{
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
}]*/

function setSeriesToCombo(result) {
	//alert("combo : " + result);
	var sum = [];
	var avg = [];
	var colLength = 0;
	var colHap = 0;
	for(var i=0; i<result.data.length; i++) {
		var rowHap=0;
		for(var j=0; j<result.data[i].length; j++) {
			rowHap = rowHap + result.data[i][j];
		}
		if(i == (result.data.length - 1)) {
			colLength = result.data[i].length;
		}
		sum[i] = rowHap;
	}
	
	for(var i=0; i<colLength; i++) {
		for(var j=0; j<result.data.length; j++){
			colHap = colHap + result.data[j][i];
		}
		avg[i] = Math.round((colHap / result.data.length)*100)/100;
		colHap = 0;
	}
	
	series = "[{";
	for(var i=0; i<result.data.length; i++) {
		series += "type : 'column', ";
		series += "name : '" + result.name[i] + "',";
		series += "data : [" + result.data[i] + "]";
		series += "}, {";
	}
	
	series += "type : 'spline', ";
	series += "name : 'Average', ";
	series += "data : [" + avg + "],";
	series += "marker : {";
	series += "lineWidth : 2,";
	series += "fillColor : 'white'";
	series += "} }, {";
	
	series += "type: 'pie',";
	series += "name: 'Total consumption',";
	series += "data : [";
	for(var i=0; i<sum.length; i++) {
		series += "{";
		series += "name: '" + result.name[i] +"', ";
		series += "y : " + sum[i];
		if( i == (sum.length - 1)) {
			series += "}],";
		}else {
			series += "},";
		}
	}
	series += "center : [80, 60], ";
	series += "size : 80, ";
	series += "showInLegend : false, ";
	series += "dataLabels : { enabled : false }";
	series += "}]";
	//document.write(series);
	/*document.write("<br><br>");
	document.write(sum[0] + "-" + sum[1] + "-" + sum[2]);
	document.write("<br><br>");
	document.write(avg[0] + "-" + avg[1] + "-" + avg[2] + "-" + avg[3] + "-" + avg[4]);*/
	
}

function getSeries() {
	//alert(series);
	return series;
}

var chart;
function setChart(result) {
	chart = {
		type : result
	};
}
function getChart() {
	return chart;
}

var title;
function setTitle(result) {
	title = {
		text : result
	};
}
function getTitle() {
	return title;
}

/*
 *		{
 *      	categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
 *  	},
 */
var xAxis;
function setxAxis(result) {
	var newResult = "";
	newResult += "[";
	for(var i=0; i<result.length; i++) {
		if(i == (result.length -1)) {
			newResult += "'" + result[i] + "'";
		}else {
			newResult += "'" + result[i] + "',";
		}
	}
	newResult += "]";

	xAxis = {
		categories : eval('(' + newResult + ')')
	};
}
function getxAxis() {
	return xAxis;
}

var yAxis;
function setyAxis(result) {
	var newResult = "";
	newResult += "[";
	for(var i=0; i<result.length; i++) {
		if(i == (result.length -1)) {
			newResult += result[i] ;
		}else {
			newResult += result[i] + ",";
		}
	}
	newResult += "]";

	yAxis = {
		categories : eval('(' + newResult + ')')
	};
}
function getyAxis() {
	return yAxis;
}
