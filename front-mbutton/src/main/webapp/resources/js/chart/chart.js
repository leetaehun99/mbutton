//VO처럼 데이터를 setting 하는 함수

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

/* var xAxis = {
categories: [
    '01',
    '02',
    '03',
    '04',
    '05',
    '10',
    '11',
    '12',
    '13',
    '14',
    '15'
],
title : { text : '진료과목 코드'}
}; */
var subjectxVal;
var xAxis;
function setxVal(url, title) {
	$.ajax({
        url : url,
        type: "post",
        async:false,
        success : function(data){
        	subjectxVal = eval('(' + data + ')');
        	xAxis= {
        			categories : subjectxVal
        			,title : {text : title}
        	};
        }
	});
	
	
}

function getxVal() {
	return xAxis;
}
