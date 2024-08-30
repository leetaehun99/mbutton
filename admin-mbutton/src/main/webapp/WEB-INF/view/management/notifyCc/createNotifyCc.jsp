<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<style>.ui-widget{ font-size: 13px; margin-bottom: 1px; padding: 2px; }</style>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#createDrugNotify").click(function(){ //고시 등록 
			if(valueCheck()) {
				oEditors1.getById["notifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
				$("#notifyForm").attr("action", "/notifyCc/createNotifyCc.json");
				$("#notifyForm").ajaxSubmit(createDrugCbNotifyAjax);
			}
		});

		$("#selectDrugNotify").click(function(){ //고시 목록 이동
			$("#notifyForm").attr("action", "/notifyCc/selectNotifyCcList.doo");
			$("#notifyForm").submit();
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

	// Ajax
	var createDrugCbNotifyAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				$("#selectDrugNotify","#notifyForm").click();
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

		if(!blankCheck("#notifyNm", "#notifyForm", "고시명을 입력해 주세요.")) { return false; }
		if(!blankCheck("#notifyMainNm", "#notifyForm", "품명를 입력해 주세요.")) { return false; }
		if(!blankCheck("#mSeq", "#notifyForm", "메세지를 입력해 주세요.")) { return false; }
		
		return true;
	};
</script>


<form id="notifyForm" name="notifyForm" action="/notifyCc/selectNotifyCcList.doo" method="post" >	
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${notifyCcVo.searchText}"/>
	<input type="hidden" name="searchType" value="${notifyCcVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${notifyCcVo.currentPage}"/>
	<input type="hidden" name="rowPerPage" value="${notifyCcVo.rowPerPage}"/>	
	<input type="hidden" name="sSortOrder" value="${notifyCcVo.sSortOrder}"/>
	<input type="hidden" name="sSortType" value="${notifyCcVo.sSortType}"/>
	
	<!--//검색 조건  -->
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th >고시명</th>
				<td class="l"><input type="text" id="notifyNm" name="notifyNm" value="" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >품명</th>
				<td class="l"><input type="text" id="notifyMainNm" name="notifyMainNm" value="" size="100"  maxlength="250" /></td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l">
					<select id="mSeq" name="mSeq">
						<option value="" >선택해주세용..</option>
						<c:forEach items="${msgList}" var="msg">
							<option value="${msg.seq}">${msg.msg}</option>	
						</c:forEach>
					</select>				
				</td>
			</tr>
			<!-- 
			<tr>
				<th>메세지</th>
				<td class="l"><input type="text" id="mSeqValue" autocomplete="off" name="mSeqValue" class="ui-widget" value="" size="100"  maxlength="300" /></td>
			</tr>
			 -->
			<tr>
				<th rowspan	="2">고시</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="notifyContents" name="notifyContents" style="height:200px;display:none;"></textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="createDrugNotify">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrugNotify">목록</a></span>
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
