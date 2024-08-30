<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateMedicalNotify").click(function(){ //고시 수정 
		oEditors1.getById["notifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
		oEditors2.getById["screeningPractices"].exec("UPDATE_CONTENTS_FIELD", []);
		$("#notifyForm").attr("action", "/medicalNotify/updateMedicalNotify.json");
		$("#notifyForm").ajaxSubmit(updateMedicalNotifyAjax);
	});
	
	$("#selectMedicalNotify").click(function(){ //고시 리스트
		$("#notifyForm").attr("action", "/medicalNotify/selectMedicalNotifyList.doo");
		$("#notifyForm").submit();
	});
});

// 게시물 수정Ajax
var updateMedicalNotifyAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectMedicalNotify").click();
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


<form id="notifyForm" name="notifyForm" action="/medicalNotify/updateMedicalNotify.doo" method="post" >
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${medicalNotifyVo.searchText}"/>
	<input type="hidden" name="searchType" value="${medicalNotifyVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${medicalNotifyVo.currentPage}"/>
	<input type="hidden" name="rowPerPage" value="${medicalNotifyVo.rowPerPage}"/>
	<!--//검색 조건  -->

	<input type="hidden" id="medicalNotify"  name="medicalNotify" value="${notify.medicalNotify }"/>
	<input type="hidden" id="medicalNotifySub"  name="medicalNotifySub" value="${notify.medicalNotifySub }"/>
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>분류번호</th>
				<td class="l">${notify.medicalNotify}</td>
			</tr>
			
			<tr>
				<th>분류서브번호</th>
				<td class="l">${notify.medicalNotifySub}</td>
			</tr>
			<tr>
				<th >수가명</th>
				<td class="l"><input type="text" id="notifyNm" name="notifyNm" value="${notify.notifyNm}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >수가코드</th>
				<td class="l"><input type="text" id="notifyMainNm" name="notifyMainNm" value="${notify.notifyMainNm}" size="100"  maxlength="250" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l"><input type="text" id="notifyMsg" name="notifyMsg" value="${notify.notifyMsg}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th rowspan	="2">관련근거</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="notifyContents" name="notifyContents" style="height:200px;display:none;">${notify.notifyContents}</textarea></td>
			</tr>
			<tr>
				<th rowspan	="2">심사사례</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="screeningPractices" name="screeningPractices" style="height:200px;display:none;">${notify.screeningPractices}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateMedicalNotify">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectMedicalNotify">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors1 = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors1,
    elPlaceHolder: "notifyContents",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor23"
});
var oEditors2 = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors2,
    elPlaceHolder: "screeningPractices",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor21"
});
</script>
</form>	


