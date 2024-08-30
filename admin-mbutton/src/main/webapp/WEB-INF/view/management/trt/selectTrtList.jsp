<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	$("#createTrt").click(function() { 
		$("#trtForm").attr("action", "/trt/createTrtForm.doo");
		$("#trtForm").submit();
	});
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#trtForm").submit();
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

var trtInfo = function(trtCd){
		
	$("#trtForm").attr("action", "/trt/selectTrt.doo");
	$("#trtCd","#trtForm").val(trtCd);
	$("#trtForm").submit();
};

</script>


<form id="trtForm" name="trtForm" action="/trt/selectTrtList.doo"method="post">
	<input type="hidden" id="trtCd"  name="trtCd" value=""/>
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="10%" />
		<col width="*" />
		<col width="20%" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00065"  selectCode="${trtVo.searchType }" defaultCode="TRT_NOTIFY"/>
					<input type="text" id="searchText" name="searchText" value="${trtVo.searchText }"/>
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
		<caption><!-- <img src="/resources/img/Trt2.gif"> --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${trtVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.trtForm,'/trt/selectTrtList.doo');"/></span></caption>
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="20%">
			<col width="7%">
			<col width="15%">
			<col width="*">
			<col width="15%">
		</colgroup>
		<thead>
			<tr>
				<th>상세</th>
				<th>치료대코드</th>
				<th>품명</th>
				<th>단위</th>
				<th>제조회사</th>
				<th>수입판매업소</th>
				<th>-</th>
			</tr>
		</thead>
		<tbody id="trtListTbody">
			<c:if test="${trtList != null}">
				<c:forEach var="trt" items="${trtList}">
					<tr >
						<td><span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:trtInfo('${trt.trtCd}');">상세보기</a></span></td>
						<td>${trt.trtCd}</td>
						<td>${trt.trtNm}</td>
						<td>${trt.trtUnit}</td>
						<td>${trt.trtCompany}</td>
						<td>${trt.trtSeller}</td>
						<td>
							<select disabled >
								<option value="" >선택</option>
								<option value="A" <c:if test="${trt.trtDivd eq 'A'}"> selected="selected" </c:if> >본인일부부담</option>
								<option value="B" <c:if test="${trt.trtDivd eq 'B'}"> selected="selected" </c:if> >비급여</option>
								<option value="C" <c:if test="${trt.trtDivd eq 'C'}"> selected="selected" </c:if> >비급여(인체조직)</option>
								<option value="D" <c:if test="${trt.trtDivd eq 'D'}"> selected="selected" </c:if> >정액수가</option>
								<option value="E" <c:if test="${trt.trtDivd eq 'E'}"> selected="selected" </c:if> >00/100 정액수가</option>
							</select>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(trtList) == 0}">
				<tr>
					<td colspan="8" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="8"><span class="button medium icon"><span class="check"></span><a id="createTrt">등록</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${trtVo.pagingHtml}</div>
</form>	
