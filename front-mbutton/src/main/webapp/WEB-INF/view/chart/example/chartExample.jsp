<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customTag" %>
<script type="text/javascript" src="/resources/js/chart/chart.js?ver=1"></script>	
<script type="text/javascript">
$(function() {
	
	$("#searchList").click(function() {
		$("#chartForm").attr("action", "/chart/selectChartExample.doo");
		$("#chartForm").submit();
	});
	
	goAjax();
	goAjax2();
	goAjax3();
	goAjax4();
	goAjax5();
	goAjax6();
});			

function goAjax() {

	$.ajax({
        url : "/chart/selectDiseaStsSubjectColumnChart.json",
        type: "post",
        data : { "type" : "column" },
        success : function(data){
        	setxVal("/chart/selectxVal.json", "진료과목 코드");
			baseSetting($("#diseaChart"), "column", "가장 많이 등록된 상병의 진료과목", eval('(' + data + ')'), getxVal());
        }
	});
}

function goAjax2() {

	$.ajax({
        url : "/chart/selectDiseaMappingTypePieChart.json",
        type: "post",
        data : { "type" : "pie" },
        success : function(data){
		baseSetting($("#diseaChart2"), "pie", "인증상병 TYPE별 평균 통계", eval('(' + data + ')'));
        }
	});
}

function goAjax3() {
	
	$.ajax({
        url : "/chart/selectDiseaStsSubjectComboChart.json",
        type: "post",
        data : { "type" : "combo" },
        success : function(data){
        	setxVal("/chart/selectxVal.json", "진료과목 코드");
		baseSetting($("#diseaChart3"), "combo", "가장 많이 등록된 상병의 진료과목", eval('(' + data + ')'), getxVal());
        }
	});
}

function goAjax4() {
	
	$.ajax({
        url : "/chart/selectBillBubbleChart.json",
        type: "post",
        data : { "type" : "bubble" , "hspId" : "12304441"},
        success : function(data){
		baseSetting($("#diseaChart4"), "bubble", "날짜별 청구금액 통계", eval('(' + data + ')'));
        }
	});
}

function goAjax5() {

	$.ajax({
        url : "/chart/selectDiseaStsSubjectColumnChart.json",
        type: "post",
        data : { "type" : "" },
        success : function(data){
        	setxVal("/chart/selectxVal.json", "진료과목 코드");
			baseSetting($("#diseaChart5"), "", "가장 많이 등록된 상병의 진료과목", eval('(' + data + ')'), getxVal());
        }
	});
}

function goAjax6() {

	$.ajax({
        url : "/chart/selectDiseaStsSubjectColumnChart.json",
        type: "post",
        data : { "type" : "bar" },
        success : function(data){
        	setxVal("/chart/selectxVal.json", "진료과목 코드");
			baseSetting($("#diseaChart6"), "bar", "가장 많이 등록된 상병의 진료과목", eval('(' + data + ')'), getxVal());
        }
	});
}
</script>
<html>
<body>
<jsp:include page="/chart.html"/>
<form id="chartForm" name="chartForm" action="" method="post" >
	<div id="diseaChart" >
	</div>
	<div id="diseaChart2" >
	</div>
	<div id="diseaChart3" >
	</div>
	<div id="diseaChart4" >
	</div>
	<div id="diseaChart5" >
	</div>
	<div id="diseaChart6" >
	</div>
	<!-- 검색영역 시작 -->
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="20%" />
		<col width="80%" />
		<tbody>
			<tr>
				<th>상병분류구분</th>
				<td>
					&nbsp;&nbsp;&nbsp;<ct:code name="searchType" type="select" groupCode="00003"  selectCode="${chartVo.searchType }" defaultCode="1"/>
					<input type="text" id="searchText" name="searchText" value="${chartVo.searchText }"/>
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<!-- 검색영역 끝 -->

	<!-- 결과리스트 시작 -->
	<table class="table5">
		<caption><img src="/resources/img/groupCodeList.gif"><span class="divAr"></span></caption>
		
		<colgroup>
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="55%">
		</colgroup>
		<thead>
			<tr>
				<th>질병분류코드</th>
				<th>상병분류구분</th>
				<th>진료된질병수</th>
				<th>질병명</th>	
			</tr>
		</thead>
		<tbody id="tbodyList">
			<c:if test="${fn:length(chartList) != null}">
				<c:forEach var="list" items="${chartList}">
					<tr>
						<td id="tdDiseaCd">${list.diseaCd}</td>
						<td>${list.diseaDivdCd}</td>
						<td>${list.diseaStsCnt}</td>
						<td>${list.diseaKorNm}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(chartList) == 0}">
				<tr>
					<td colspan="4" align="center">진료된 질병내역이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<!-- 결과리스트 끝 -->
</form>
</body>
</html>
