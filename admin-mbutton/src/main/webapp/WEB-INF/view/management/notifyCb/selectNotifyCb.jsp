<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateNotifyCb").click(function(){ //고시 수정
		if(valueCheck()) {
			oEditors2.getById["notifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#notifyCbForm").attr("action", "/notifyCb/updateNotifyCb.json");
			$("#notifyCbForm").ajaxSubmit(updateNotifyCbAjax );
		}
	});
	
	$("#selectNotifyCb").click(function(){ //고시 리스트
		$("#notifyCbForm").attr("action", "/notifyCb/selectNotifyCbList.doo");
		$("#notifyCbForm").submit();
	});
	/*
	$("#mSeqValue").autocomplete({
        source: function( request, response ) {
        	 $.ajax({	url: '/msg/selectMsgList.json',
				data: { searchText : $("#mSeqValue").val() },
				type : "post",
				dataType : "json",
				success: function( data ) {
				    response( $.map( data, function( item ) {
				        return {
							value: item.seq,
							label: item.msg
						}
				    }));
				}
			});
		},
		select: function( event, ui ) {
	        $( "#mSeq" ).val( ui.item.value );
	        $( "#mSeqValue" ).val( ui.item.label );
	        $( "#mSeqTemp" ).val( ui.item.label );
	        return false;
		},
		matchContains: true, max: 100, mustMatch: false, scrollHeight: 70, width: 80 });
	*/
});

// 게시물 수정Ajax
var updateNotifyCbAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectNotifyCb").click();
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

//로그인 처리
valueCheck = function() {
	//if ($("#mSeqValue").val() != $("#mSeqTemp").val()) {
	//	alert("메시지를 선택 후 임의로 변경하셨습니다.");
	//	$("#mSeqValue").focus();
	//	return false;
	//}
		
	if(!blankCheck("#notifyNm", "#notifyCbForm", "고시명을 입력해 주세요.")) { return false; }
	if(!blankCheck("#mSeq", "#notifyCbForm", "메세지를 입력해 주세요.")) { return false; }
	
	return true;
};
</script>


<form id="notifyCbForm" name="notifyCbForm" action="/drugNorifyCb/updateNotifyCb.doo" method="post" >	
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${notifyCbVo.searchText}"/>
	<input type="hidden" name="searchType" value="${notifyCbVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${notifyCbVo.currentPage}"/>
	<input type="hidden" name="rowPerPage" value="${notifyCbVo.rowPerPage}"/>
	<input type="hidden" name="sSortOrder" value="${notifyCbVo.sSortOrder}"/>
	<input type="hidden" name="sSortType" value="${notifyCbVo.sSortType}"/>
	<!--//검색 조건  -->
	<input type="hidden" id="cbNotify"  name="cbNotify" value="${notifyCbVo.cbNotify }"/>
	<input type="hidden" id="cbNotifySub"  name="cbNotifySub" value="${notifyCbVo.cbNotifySub }"/>

	
	
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>고시번호</th>
				<td class="l">${notifyCb.cbNotify}</td>
			</tr>
			<tr>
				<th>서브고시번호</th>
				<td class="l">${notifyCb.cbNotifySub}</td>
			</tr>
			<tr>
				<th>고시명</th>
				<td class="l"><input type="text" id="notifyNm" name="notifyNm" value="${notifyCb.notifyNm}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>품명</th>
				<td class="l">${notifyCb.mainDrugNm1} / ${notifyCb.mainDrugNm2}</td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l">
					<select id="mSeq" name="mSeq">
						<option value="" >선택해주세용..</option>
						<c:forEach items="${msgList}" var="msg">
							<option value="${msg.seq}" <c:if test="${msg.seq eq notifyCb.mSeq}"> selected </c:if>>${msg.msg}</option>	
						</c:forEach>
					</select>				
				</td>
			</tr>
			<!-- 
			<tr>
				<th>메세지</th>
				<td class="l"><input type="text" id="mSeqValue" autocomplete="off" name="mSeqValue" class="ui-widget" value="${notifyCb.msg}" size="100"  maxlength="300" /></td>
			</tr>
			 -->
			<tr>
				<th rowspan	="2">관련근거</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="notifyContents" name="notifyContents" style="height:200px;display:none;">${notifyCb.notifyContents}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateNotifyCb">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectNotifyCb">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors2 = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors2,
    elPlaceHolder: "notifyContents",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor23"
});
</script>
</form>	


