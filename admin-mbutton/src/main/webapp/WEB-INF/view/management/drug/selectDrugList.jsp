<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	//검색
	$("#searchList").click(function(){
		$("#drugForm").attr("action", "/drug/selectDrugList.doo");
		$("#currentPage").val(1);
		$("#drugForm").submit();
	});

	//insert
	$("#createDrug").click(function(){
		$("#drugForm").attr("action", "/drug/createDrugInfo.doo");
		$("#drugForm").submit();
	});

	// 체크 박스 모두 체크
	$("#checkClearDrug").click(function() {
		if ($("#checkValue").val() == 'N') {
			$("input[name=checkDrug]:checkbox").each(function() {
				$(this).prop("checked", true);
				updateCheckDrug($(this).val());
			});
			$("#checkValue").val("Y");
		} else {
			$("input[name=checkDrug]:checkbox").each(function() {
				$(this).prop("checked", false);
				updateCheckDrug($(this).val());
			});	
			$("#checkValue").val("N");
		}
	});
});

//게시물 수정Ajax
var updateCheckDrugAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			//alert("고생하셨습니다.");
		} else {
			alert("개발팀에 문의바랍니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

var drugInfoPop = function(drugCd, mainDrugCd){
	$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
	
	$("#drugCd","#drugPopupForm").val(drugCd);
	$("#mainDrugCd","#drugPopupForm").val(mainDrugCd);
	pop('','drug','1000', '840');
	$("#drugPopupForm").attr("action", "/disea/selectDrugPop.doo");
	$("#drugPopupForm").attr("target", "drug");
	$("#drugPopupForm").submit();
	
	$.unblockUI();
};

var drugInfo = function(drugCd){
		
	$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
	
	$("#drugCd","#drugForm").val(drugCd);
	$("#drugForm").attr("action", "/drug/selectDrugInfo.doo");
	$("#drugForm").submit();
	
	$.unblockUI();
};

var updateCheckDrug = function(drugCd) {
	$("#drugCd","#drugForm").val(drugCd);
	
	if ($("#" + drugCd,"#drugForm").prop("checked") == true) {
		$("#drugCheck","#drugForm").val("Y");
	} else {
		$("#drugCheck","#drugForm").val("N");
	}
	
	$("#drugForm").attr("action", "/drug/updateCheckDrug.json");
	$("#drugForm").ajaxSubmit(updateCheckDrugAjax);
}

</script>


<form id="drugPopupForm" name="drugPopupForm" action="/drug/selectDrugList.doo"method="post">
	<input type="hidden" id="drugCd"  name="drugCd" />
	<input type="hidden" id="mainDrugCd"  name="mainDrugCd" />
</form>

<form id="drugForm" name="drugForm" action="/drug/selectDrugList.doo"method="post">
	<input type="hidden" id="drugCd"  name="drugCd" />
	<input type="hidden" id="drugCheck"  name="drugCheck" />
	
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<colgroup>
		<col width="10%">
		<col width="35%">
		<col width="10%">
		<col width="*">
		<col width="10%">
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td>
					&nbsp;<ct:code name="searchType" type="select" groupCode="00059"  selectCode="${drugVo.searchType }" defaultCode="DRUG_CD" />
					&nbsp;<input type="text" id="searchText" name="searchText" value="${drugVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td>
					&nbsp;<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${drugVo.sSortOrder }" />
					&nbsp;<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${drugVo.sSortType }" />
				</td>
				<td align="center" class="c">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- img src="/resources/img/Notify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${drugVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.drugForm,'/drug/selectDrugList.doo');"/></span></caption>
		<colgroup>
			<col width="15%">
			<col width="6%">
			<col width="6%;">
			<col width="10%;">
			<col width="*;">
			<col width="20%;">
			<col width="10%;">
			<col width="10%;">
		</colgroup>
		<thead>
			<tr>
				<th>약품코드 // 작업완료</th>
				<th>약품코드</th>
				<th>약품명</th>
				<th>주성분코드</th>
				<th>주성분명</th>
				<th>회사명</th>
				<th>적용일자</th>
				<th>상병팝업</th>
			</tr>
		</thead>
		<tbody id="drugListTbody">
			<c:if test="${drugList != null}">
			
				<input type="hidden" id="checkValue"  name="checkValue" value= '${drugList[0].drugCheck}'/>
				<c:forEach var="drug" items="${drugList}">
				<c:set var="mainDrugNms" value="${fn:split(drug.mainDrugNm,'∬')}"/>
					<tr >
						<td>
						<span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:drugInfo('${drug.drugCd}');">상세보기</a></span>
							<input type="checkbox" value="${drug.drugCd}" id="${drug.drugCd}" onclick="javascipt:updateCheckDrug('${drug.drugCd}')" name="checkDrug" <c:if test="${drug.drugCheck eq 'Y'}" > checked </c:if> />
						</td>
						<td>${drug.drugCd}</td>
						<td>${drug.drugNm}</td>
						<td>${drug.mainDrugCd}</td>
						<td>${mainDrugNms[0]} <c:if test="${fn:length(mainDrugNms)!=1}">외 ${fn:length(mainDrugNms)-1} 개</c:if></td>
						<td>${drug.company}</td>
						<td>${drug.applyDt}</td>
						<td><span class="button medium icon"><span class="check"></span>
						<a onclick="javascript:drugInfoPop('${drug.drugCd}', '${drug.mainDrugCd}');">상병(${drug.diseaCnt})개</a></span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(drugList) == 0}">
				<tr>
					<td colspan="9" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="9">
			<span class="button medium icon"><span class="check"></span><a id="createDrug">등록</a></span>
			<span class="button medium icon"><span class="check"></span><a id="checkClearDrug">전부 check</a></span>
			</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${drugVo.pagingHtml}</div>
</form>	
