<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateDrugNotify").click(function(){ //고시 수정 
		 oEditors2.getById["drugNotifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
		$("#drugNotifyForm").attr("action", "/drugNotify/updateDrugNotify.json");
		$("#drugNotifyForm").ajaxSubmit(updateDrugNotifyAjax);
	});
	
	$("#selectDrugNotify").click(function(){ //고시 리스트
		$("#drugNotifyForm").attr("action", "/drugNotify/selectDrugNotifyList.doo");
		$("#drugNotifyForm").submit();
	});
});

// 게시물 수정Ajax
var updateDrugNotifyAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectDrugNotify").click();
		} else {
			alert("등록에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};
</script>


<form id="drugNotifyForm" name="drugNotifyForm" action="/drugNotify/updateDrugNotify.doo" method="post" >
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${drugNotifyVo.searchText}"/>
	<input type="hidden" name="searchType" value="${drugNotifyVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${drugNotifyVo.currentPage}"/>
	<input type="hidden" name="rowPerPage" value="${drugNotifyVo.rowPerPage}"/>
	<!--//검색 조건  -->

	<input type="hidden" id="drugNotify"  name="drugNotify" value="${drugNotify.drugNotify }"/>
	<input type="hidden" id="drugNotifySub"  name="drugNotifySub" value="${drugNotify.drugNotifySub }"/>
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>고시번호</th>
				<td class="l">${drugNotify.drugNotify}</td>
			</tr>
			
			<tr>
				<th>고시서브번호</th>
				<td class="l">${drugNotify.drugNotifySub}</td>
			</tr>
			<tr>
				<th >고시명</th>
				<td class="l"><input type="text" id="drugNotifyNm" name="drugNotifyNm" value="${drugNotify.drugNotifyNm}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >재제명</th>
				<td class="l"><input type="text" id="drugNotifyMainNm" name="drugNotifyMainNm" value="${drugNotify.drugNotifyMainNm}" size="100"  maxlength="250" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >품명</th>
				<td class="l"><input type="text" id="drugNotifyItem" name="drugNotifyItem" value="${drugNotify.drugNotifyItem}" size="100"    maxlength="250"/></td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l"><input type="text" id="drugNotifyMsg" name="drugNotifyMsg" value="${drugNotify.drugNotifyMsg}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th rowspan	="2">고시</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="drugNotifyContents" name="drugNotifyContents" style="height:200px;display:none;">${drugNotify.drugNotifyContents}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateDrugNotify">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrugNotify">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors2 = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors2,
    elPlaceHolder: "drugNotifyContents",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor23"
});
</script>
</form>	


