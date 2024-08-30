<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	$("#createMedicalInsur").click(function() { 
		$("#medicalInsurForm").attr("action", "/medicalInsur/createMedicalInsurForm.doo");
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

var medicalInsurInfo = function(medicalInsurCd){
		
	$("#medicalInsurForm").attr("action", "/medicalInsur/selectMedicalInsur.doo");
	$("#medicalInsurCd","#medicalInsurForm").val(medicalInsurCd);
	$("#medicalInsurForm").submit();
};

var medicalInsurInfoPop = function(medicalInsurCd){
	$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
	
	$("#medicalInsurCd").val(medicalInsurCd);
	pop('','medical','1000', '840');
	$("#medicalInsurForm").attr("action", "/disea/selectMedicalInsurPop.doo");
	$("#medicalInsurForm").attr("target", "medical");
	$("#medicalInsurForm").submit();
	
	$.unblockUI();
};
</script>


<form id="medicalInsurForm" name="medicalInsurForm" action="/medicalInsur/selectMedicalInsurList.doo"method="post">
	<input type="hidden" id="medicalInsurCd"  name="medicalInsurCd" value=""/>
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
					<ct:code name="searchType" type="select" groupCode="00064"  selectCode="${medicalInsurVo.searchType }" defaultCode="MEDICAL_INSUR_CD"/>
					<input type="text" id="searchText" name="searchText" value="${medicalInsurVo.searchText }"/>
				</td>
				<td rowspan="2" class="c">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>

		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- <img src="/resources/img/MedicalInsur2.gif"> --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${medicalInsurVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.medicalInsurForm,'/medicalInsur/selectMedicalInsurList.doo');"/></span></caption>
		<colgroup>
			<col width="10%">
			<col width="7%">
			<col width="7%">
			<col width="22%">
			<col width="10%">
			<col width="*">
			<col width="10%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>상세</th>
				<th>수가코드</th>
				<th>분류번호</th>
				<th>한글명</th>
				<th>산정</th>
				<th>영문명</th>
				<th>상대가치점수</th>
				<th>행위구분</th>
				<th>상병팝업</th>
			</tr>
		</thead>
		<tbody id="medicalInsurListTbody">
			<c:if test="${medicalInsurList != null}">
				<c:forEach var="medicalInsur" items="${medicalInsurList}">
					<tr >
						<td><span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:medicalInsurInfo('${medicalInsur.medicalInsurCd}');">상세보기</a></span></td>
						<td>${medicalInsur.medicalInsurCd}</td>
						<td>${medicalInsur.cdDivd}</td>
						<td>${medicalInsur.korNm}</td>
						<td>${medicalInsur.calculationCd}</td>
						<td>${medicalInsur.engNm}</td>
						<td>${medicalInsur.relativeVal}</td>
						<td>
							<select name="typeCd" disabled >
								<option value="" >선택</option>
								<option value="A" <c:if test="${medicalInsur.typeCd eq 'A'}"> selected="selected" </c:if> >의과급여</option>
								<option value="C" <c:if test="${medicalInsur.typeCd eq 'B'}"> selected="selected" </c:if> >의과(비급여)</option>								
							</select>
						</td>
						<td>
							<span class="button medium icon"><span class="check"></span>
								<a onclick="javascript:medicalInsurInfoPop('${medicalInsur.medicalInsurCd}');">상병</a>
							</span>
						</td>
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
