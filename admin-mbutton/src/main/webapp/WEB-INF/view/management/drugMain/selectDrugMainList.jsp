<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		$("#createDrugMain").click(function() { 
			$("#drugMainForm").attr("action", "/drugMain/createDrugMainForm.doo");
			$("#drugMainForm").submit();
		});
		//검색
		$("#searchList").click(function(){
			$("#drugMainForm").attr("action", "/drugMain/selectDrugMainList.doo");
			$("#currentPage").val(1);
			$("#drugMainForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
	});

	var readDrugMain = function(mainDrugCd){
		$("#mainDrugCd","#drugMainForm").val(mainDrugCd);
		$("#drugMainForm").attr("action", "/drugMain/selectDrugMain.doo");
		$("#drugMainForm").submit();
	}
	var callDrugList = function(mainDrugCd){
		$("#sSortType","#drugForm").val("DESC");
		$("#searchType","#drugForm").val("MAIN_DRUG_CD");
		$("#searchText","#drugForm").val(mainDrugCd);
		$("#drugForm").attr("action", "/drug/selectDrugList.doo");
		$("#drugForm").submit();
	}

	//검색필드 초기화
	initSearchValue = function(){
		$("#searchType").val("");
		$("#searchText").val("");
	};
</script>

<form id="drugForm" name="drugForm" action="/drug/selectDrugList.doo"method="post">
<input type="hidden" id="sSortType" name="sSortType" value=""/>
<input type="hidden" id="searchType" name="searchType" value=""/>
<input type="hidden" id="searchText" name="searchText" value=""/>
</form>
<form id="drugMainForm" name="drugMainForm" action="/drugMain/selectDrugMainList.doo"method="post">
<input type="hidden" id="mainDrugCd" name="mainDrugCd" value=""/>
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="70%" />
		<col width="*" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00058"  selectCode="${drugMainVo.searchType }" defaultCode="MAIN_DRUG_CD" />
					<input type="text" id="searchText" name="searchText" value="${drugMainVo.searchText }"/>
				</td>
				<td class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${drugMainVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.drugMainForm,'/drugMain/selectDrugMainList.doo');"/></span></caption>
		<colgroup>
			<col width="100px;">
			<col width="*">
			<col width="130px;">
			<col width="90px;">
		</colgroup>
		<thead>
			<tr>
				<th>주요성분코드</th>
				<th>주요성분명</th>
				<th>주요성분 약품</th>
				<th>상세보기</th>
			</tr>
		</thead>
		<tbody id="drugMainListTbody">
			<c:if test="${drugMainList != null}">
				<c:forEach var="drugMain" items="${drugMainList}">
				<c:set var="mainDrugNms" value="${fn:split(fn:trim(drugMain.mainDrugNm),'∬')}"/>
					<tr >
						<td>${drugMain.mainDrugCd}</td>
						<td>${mainDrugNms[0]} <c:if test="${fn:length(mainDrugNms)!=1}">외 ${fn:length(mainDrugNms)-1} 개</c:if></td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:callDrugList('${drugMain.mainDrugCd}');">주요성분 약품</a></span></td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readDrugMain('${drugMain.mainDrugCd}');">상세보기</a></span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(drugMainList) == 0}">
				<tr>
					<td colspan="4" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="4"><span class="button medium icon"><span class="check"></span><a id="createDrugMain">등록</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${drugMainVo.pagingHtml}</div>
</form>	
