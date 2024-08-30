<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	$("#createMedicalInsur").click(function() { 
		$("#medicalInsurForm").attr("action", "/medicalInsur/createInsurScreeningForm.doo");
		$("#medicalInsurForm").submit();
	});
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#medicalInsurForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
});

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

var medicalInsurInfo = function(medicalInsurCd, medicalInsurSeq){
		
	$("#medicalInsurForm").attr("action", "/medicalInsur/selectInsurScreening.doo");
	$("#medicalInsurCd","#medicalInsurForm").val(medicalInsurCd);
	$("#medicalInsurSeq","#medicalInsurForm").val(medicalInsurSeq);
	
	$("#medicalInsurForm").submit();
};

</script>


<form id="medicalInsurForm" name="medicalInsurForm" action="/medicalInsur/selectInsurScreeningList.doo"method="post">
	<input type="hidden" id="medicalInsurCd"  name="medicalInsurCd" value=""/>
	<input type="hidden" id="medicalInsurSeq"  name="medicalInsurSeq" value=""/>
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="10%" />
		<col width="40%" />
		<col width="20%" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00066"  selectCode="${medicalInsurVo.searchType }" defaultCode="CLASSIFICATION"/>
					<input type="text" id="searchText" name="searchText" value="${medicalInsurVo.searchText }"/>
				</td>
				<td rowspan="2" class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>

		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- <img src="/resources/img/MedicalInsur2.gif"> --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${medicalInsurVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.medicalInsurForm,'/medicalInsur/selectInsurScreeningList.doo');"/></span></caption>
		<colgroup>
			<col width="10%">
			<col width="7%">
			<col width="7%">
			<col width="7%">
			<col width="20%">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th>상세</th>
				<th>수가코드</th>
				<th>시퀀스</th>
				<th>분류번호</th>
				<th>명칭</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody id="medicalInsurListTbody">
			<c:if test="${medicalInsurList != null}">
				<c:forEach var="medicalInsur" items="${medicalInsurList}">
					<tr >
						<td><span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:medicalInsurInfo('${medicalInsur.medicalInsurCd}','${medicalInsur.medicalInsurSeq}');">상세보기</a></span></td>
						<td>${medicalInsur.medicalInsurCd}</td>
						<td>${medicalInsur.medicalInsurSeq}</td>
						<td>${medicalInsur.classification}</td>
						<td>${medicalInsur.designation}</td>
						<td style="text-align: left; padding-left: 5px;">${medicalInsur.title}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(medicalInsurList) == 0}">
				<tr>
					<td colspan="9" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="9"><span class="button medium icon"><span class="check"></span><a id="createMedicalInsur">등록</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${medicalInsurVo.pagingHtml}</div>
</form>	
