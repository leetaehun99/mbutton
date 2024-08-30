<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<%@ page session="false" %>

<!--Controller처럼 setting/getting 하는 부분 -->
<html>
<head>
	<title>Home</title>
	<!-- <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js?ver=2"></script> -->
	<script type="text/javascript" src="/resources/js/chart/chart.js?ver=1"></script>	
	<script type="text/javascript">
	
	$( document ).ready(function() {
		//상병 삭제(미사용)
		goAjax();
		goAjax2();
		goAjax3();
		goAjax4();
		goAjax5();
	});

	function goAjax() {
		$.ajax({
            url : "/chartExample/bubble.json",
            type: "get",
            //data : { "id" : $("#id").val() },
            success : function(data){
			/* setChart("bubble");
			chart.zoomType = 'xy';
			setTitle("Highcharts Bubbles");
			setSeriesToBubble(data); */
			baseSetting($("#container1"), "bubble", "Highcharts Bubbles", eval('(' + data + ')'));
            }
		});
	}
	
	function goAjax2() {
		var xAxis = ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas'];
		var legend = {
            reversed: true
        };
        var plotOptions = {
            series: {
                stacking: 'normal'
            }
        };
        var yAxis = {
            min: 0,
            title: {
                text: 'Total fruit consumption'
            }
        };
        
		$.ajax({
            url : "/chartExample/barStacked.json",
            type: "get",
            //data : { "id" : $("#id").val() },
            success : function(data){
			/* setChart("bar");
			setTitle("Stacked bar chart");
        	setSeries(data);
        	setxAxis(xAxis); */
        	baseSetting($("#container2"), "bar", "Stacked bar chart", data, xAxis, yAxis, "", plotOptions, legend, "");
            }
			,dataType : "json"
		});
	}
	
	function goAjax3() {
		var xAxis = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
           'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  		var tooltip = {
              valueSuffix: '°C'
          };
  		var legend = {
              layout: 'vertical',
              align: 'right',
              verticalAlign: 'middle',
              borderWidth: 0
          };
  		
  		var yAxis = {
              title: {
                  text: 'Temperature (°C)'
              },
              plotLines: [{
                  value: 0,
                  width: 1,
                  color: '#808080'
              }]
              ,categories: [0, 5000, 10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 55000]
              
          };
  		var navigation = {
          	menuItemStyle: {
                  borderLeft: '10px solid #E0E0E0'
         		},
  	        menuStyle: {
  	        	background: '#E0E0E0'
  	        }
          };
  		$.ajax({
              url : "/chartExample/lineBasic.json",
              type: "get",
              //data : { "id" : $("#id").val() },
              success : function(data){
               /*  setTitle("Monthly Average Temperature");
              	setSeries(data);
              	setxAxis(xAxis); */
              	baseSetting($("#container3"), "", "Monthly Average Temperature", eval('(' + data + ')'), xAxis, yAxis, tooltip, null, legend, navigation);
              }
  		});
	}
	
	function goAjax4() {
		var tooltip = {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        };
        var plotOptions = {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        };
		$.ajax({
            url : "/chartExample/pieBasic.json",
            type: "get",
            //data : { "id" : $("#id").val() },
            success : function(data){
            	/* setSeriesToPie(data, "Chrome");
                setTitle("Browser market shares at a specific website, 2014"); */
            	baseSetting($("#container4"), "pie", "Browser market shares at a specific website, 2014", eval('(' + data + ')'), null, null, tooltip, plotOptions);
            }
		});
	}
	
	function goAjax5() {
		var xAxis = ['Apples', 'Oranges', 'Pears', 'Bananas', 'Plums'];
		
		var labels = {
	            items: [{
	                html: 'Total fruit consumption',
	                style: {
	                    left: '50px',
	                    top: '18px',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
	                }
	            }]
	        };
		
		$.ajax({
            url : "/chartExample/combo.json",
            type: "get",
            //data : { "id" : $("#id").val() },
            success : function(data){
                baseSetting($("#container5"), "combo", "Combination chart", eval('(' + data + ')'), xAxis, null, null, null, null, null, labels );
            }
		});
	}
	</script>	
</head>
<body>

<h1>
	Hello world!  
</h1>
<jsp:include page="/chart.html"/>
<div id="container1" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
<div id="container2" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
<div id="container3" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
<div id="container4" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
<div id="container5" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
<!-- <input type ="button" id="" name="" value="print" onClick="javascript:goPrint();"/> -->
</body>
</html>
