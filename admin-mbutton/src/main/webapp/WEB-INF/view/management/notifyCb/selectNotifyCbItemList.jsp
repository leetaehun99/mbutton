<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#notifyCbItemForm").attr("action", "/notifyCb/selectNotifyCbItemList.doo");
		$("#notifyCbItemForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});

	//등록폼 초기화
	$("#updateCb").click(function() { 
		$("#notifyCbItemForm").attr("action", "/notifyCb/createNotifyCbItemForm.doo");
		$("#notifyCbItemForm").submit();
	});
	
	//삭제
	$("#deletenotifyCbItem").click(function() {
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
		
		//arrDel에 있는 주성분코드를 사용하기 때문에 historyMsg가 필요없음
		
		var checkConfirm = confirm("정말로 삭제하시겠습니까?");
		if(checkConfirm) {
			$("#notifyCbItemForm").attr("action", "/notifyCb/deleteNotifyCbItem.json");
			$("#notifyCbItemForm").ajaxSubmit(deleteAjax);
		}
	});
});

</script>

<form id="notifyCbItemForm" name="notifyCbItemForm" action="/drugNotify/selectDrugNotifyItemList.doo"method="post">
	<input type="hidden" id="cbNotify" name="cbNotify" value="${notifyCbVo.cbNotify}"/>
	<input type="hidden" id="cbNotifySub" name="cbNotifySub" value="${notifyCbVo.cbNotifySub}" />	

	
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="35%" />
		<col width="*" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00075"  selectCode="${notifyCbVo.searchType }" defaultCode="MAIN_DRUG_CD1"/>
					<input type="text" id="searchText" name="searchText" value="${notifyCbVo.searchText }"/>
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
		<caption><!-- img src="/resources/img/DrugNotify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyCbVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyCbItemForm,'/notifyCb/selectNotifyCbItemList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="100px;">	
			<col width="*">
			<col width="100px;">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>주성분코드1</th>
				<th>주성분명1</th>
				<th>주성분코드2</th>
				<th>주성분명2</th>
			</tr>
		</thead>
		<tbody id="notifyCbItemTbody">
			<c:if test="${cbList != null}">
				<c:forEach var="cb" items="${cbList}">
					<tr class="trFocus">
						<!--
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readDrugNotifyItem('');">상세</a></span></td> 
						 -->
						<td><input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${cb.mainDrugCd1}_${cb.mainDrugCd2}" /></td>
						<td> ${cb.mainDrugCd1}</td>
						<td> ${cb.mainDrugNm1}</td>
						<td> ${cb.mainDrugCd2}</td>
						<td> ${cb.mainDrugNm2}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(cbList) == 0}">
				<tr>
					<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<span class="button medium icon"><span class="check"></span><a id="updateCb">수정</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deletenotifyCbItem">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${notifyCbVo.pagingHtml}</div>
</form>

