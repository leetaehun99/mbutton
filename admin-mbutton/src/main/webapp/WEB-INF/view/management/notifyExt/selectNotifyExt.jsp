<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#updateNotifyExt").click(function(){ //고시 수정 
			oEditors1.getById["notifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#notifyExtForm").attr("action", "/notifyExt/updateNotifyExt.json");
			$("#notifyExtForm").ajaxSubmit(updateNotifyExtAjax);
		});

		$("#selectNotifyExt").click(function(){ //고시 목록 이동
			$("#notifyExtForm").attr("action", "/notifyExt/selectNotifyExtList.doo");
			$("#notifyExtForm").submit();
		});
		
	});

	// 게시물 수정Ajax
	var updateNotifyExtAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("수정되었습니다.");
				//$("#selectDrugNotify").click();
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


<form id="notifyExtForm" name="notifyExtForm" action="/drugNotify/selectDrugNotifyList.doo" method="post" >
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${notifyExtVo.searchText}"/>
	<input type="hidden" name="searchType" value="${notifyExtVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${notifyExtVo.currentPage}"/>
	<input type="hidden" name="rowPerPage" value="${notifyExtVo.rowPerPage}"/>
	<!--//검색 조건  -->
	<input type="hidden" id="extNotify" name="extNotify" value="${notifyExtVo.extNotify}" />
	<input type="hidden" id="extNotifySub" name="extNotifySub" value="${notifyExtVo.extNotifySub}" />
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th >고시명</th>
				<td class="l"><input type="text" id="notifyNm" name="notifyNm" value="${notifyExt.notifyNm}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >재제명</th>
				<td class="l"><input type="text" id="notifyMainNm" name="notifyMainNm" value="${notifyExt.notifyMainNm}" size="100"  maxlength="250" /></td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l">
					<select id="mSeq" name="mSeq">
						<option value="" >선택해주세용..</option>
						<c:forEach items="${msgList}" var="msg">
							<option value="${msg.seq}" <c:if test="${msg.seq eq notifyExt.mSeq}"> selected </c:if>>${msg.msg}</option>	
						</c:forEach>
					</select>				
				</td>
			</tr>
			<tr>
				<th rowspan	="2">고시</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="notifyContents" name="notifyContents" style="height:200px;display:none;">${notifyExt.notifyContents}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="updateNotifyExt">수정</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectNotifyExt">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors1 = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors1,
    elPlaceHolder: "notifyContents",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor21"
});
</script>
</form>	
