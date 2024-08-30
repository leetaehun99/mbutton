<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	//검색
	$("#searchList").click(function(){
		$("#diseaForm").attr("action", "/disea/selectDiseaList.doo");
		$("#currentPage").val("1");
		$("#diseaForm").submit();
	});

	//insert
	$("#createDisea").click(function(){
		$("#diseaForm").attr("action", "/disea/createDiseaInfo.doo");
		$("#diseaForm").submit();
	});

});

var diseaInfoPop = function(diseaCd){
	$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
	
	$("#diseaCd","#diseaPopupForm").val(diseaCd);
	pop('','disea','1000', '840');
	$("#diseaPopupForm").attr("action", "/disea/selectDiseaPop.doo");
	$("#diseaPopupForm").attr("target", "disea");
	$("#diseaPopupForm").submit();
	
	$.unblockUI();
};

var diseaInfo = function(diseaCd){
		
	$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
	
	$("#diseaCd","#diseaForm").val(diseaCd);
	$("#diseaForm").attr("action", "/disea/selectDiseaInfo.doo");
	$("#diseaForm").submit();
	
	$.unblockUI();
};

</script>


<form id="diseaPopupForm" name="diseaPopupForm" action="/disea/selectDiseaList.doo"method="post">
	<input type="hidden" id="diseaCd"  name="diseaCd" />
</form>

<form id="diseaForm" name="diseaForm" action="/disea/selectDiseaList.doo"method="post">
	<input type="hidden" id="diseaCd"  name="diseaCd" />
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<colgroup>
		<col width="10%">
		<col width="*">
		<col width="10%">
		<col width="20%">
		<col width="20%">
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00061"  selectCode="${diseaVo.searchType }" defaultCode="DISEA_CD"/>
					<input type="text" id="searchText" name="searchText" value="${diseaVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${diseaVo.sSortType }" />
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	<table class="table5">
		<caption><!-- img src="/resources/img/Notify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${diseaVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.diseaForm,'/disea/selectDiseaList.doo');"/></span></caption>
		<colgroup>
			<col width="10%">
			<col width="40%;">
			<col width="40%">
			<col width="10%;">
		</colgroup>
		<thead>
			<tr>
				<th>상병코드</th>
				<th>상병명</th>
				<th>상병영문명</th>
				<th>상세보기</th>
				<th>약품보기</th>
			</tr>
		</thead>
		<tbody id="diseaListTbody">
			<c:if test="${diseaList != null}">
				<c:forEach var="disea" items="${diseaList}">
					<tr >
						<td>${disea.diseaCd}</td>
						<td>${disea.diseaKorNm}</td>
						<td>${disea.diseaEngNm}</td>
						<td><span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:diseaInfo('${disea.diseaCd}');">상세보기</a></span></td>
						<td><span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:diseaInfoPop('${disea.diseaCd}');">약품(${disea.drugCnt})개</a></span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(diseaList) == 0}">
				<tr>
					<td colspan="9" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="9"><span class="button medium icon"><span class="check"></span><a id="createDisea">등록</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${diseaVo.pagingHtml}</div>
</form>	
