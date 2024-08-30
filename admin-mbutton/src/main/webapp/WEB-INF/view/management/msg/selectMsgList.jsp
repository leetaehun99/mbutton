<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var delCheckArr = new Array();
var cnt = 0;
var checkScreenMsg = false;
	$(function() {	
		
		$("#searchText").keypress(function(e) {
			if((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
				$("#searchList").click();
			}
		});
		
		//등록폼 초기화
		$("#initValue").click(function() { 
			initValue();
			$("#msgNm").focus();
			btnDisplay(true);
		});
		
		//메시지 등록
		$("#createMsg").click(function() { 
			if(validationCheck()){
				$("#msgForm").attr("action", "/msg/createMsg.json");
				$("#msgForm").ajaxSubmit(createMsgAjax);
			}
		});
		//메시지 수정
		$("#updateMsg").click(function() { 
			if(validationCheck()){
				$("#msgForm").attr("action", "/msg/updateMsg.json");
				$("#msgForm").ajaxSubmit(createMsgAjax);
			}
		});
		
		//삭제
		$("#deleteMsg").click(function() {
			$("#msgForm").attr("action", "/msg/selectScreenMsgCnt.json");
			$("#msgForm").ajaxSubmit(selectScreenMsgCntAjax);
		});
		
		//검색
		$("#searchList").click(function(){
			$("#msgForm").attr("action", "/msg/selectMsgList.doo");
			$("#currentPage").val(1);
			$("#msgForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
		btnDisplay(true);
	});
	
	function displayList(){
		$("#msgForm").attr("action", "/msg/selectMsgList.doo");
		$("#msgForm").submit();
	}
	//메시지 상세 function
	var readMsg = function(seq){
		$("#seq").val(seq);
		$("#msgForm").attr("action", "/msg/selectMsg.json");
		$("#msgForm").ajaxSubmit(selectMsgAjax);
		btnDisplay(false);
		/*
		$("#msgListTbody tr").each(function(){
			if(seq == $(this).find("td:eq(0)").text()) $(this).css("background-color","#e5edfe");
			else  $(this).css("background-color","");
		});
		*/
	};

	var readSubMsg = function(seq, type){
		$("#seq").val(seq);
		if (type == "0" || type == "C" || type == "E" || type == "F" || type == "Z" ) {
			alert("불필요 코드입니다.");
			return;
		}
		$("#mode","#msgForm").val("SUB");//서브
		$("#msgForm").attr("action", "/msg/selectSubMsgList.doo");
		$("#msgForm").submit();
	};
	
	// 메시지 등록Ajax
	var createMsgAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				displayList();
			} else {
				alert("등록에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	// 메시지 상세Ajax 
	var selectMsgAjax = {
		success : function(resultData) {
			if( resultData != null ) {
				valueSetting(resultData);
			} else {
				alert("조회된 내용이 없습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	//값 세팅
	valueSetting = function(data){
		$("#seq").val(data.seq);
		$("#msg").val(data.msg);
		$("#msgLev").val(data.msgLev);
		$("#type").val(data.type);
		$("#specialCd").val(data.specialCd);
	};
	
	//버튼 표현
	btnDisplay = function(type){
		if(type){//등록
			$("#btnMode2").hide();
			$("#btnMode1").show();
		}else{//수정
			$("#btnMode1").hide();
			$("#btnMode2").show();
		}
	};
	
	//value검증
	validationCheck = function(){
		if(!blankCheck("#msg",  "#msgForm", "메시지는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#msgLev", "#msgForm", "메시지레벨은 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#type", "#msgForm", "메시지타입은 필수선택 사항입니다.")) { return false; }
		if(!onlyNumber("#msgLev", "#msgForm", "메시지레벨은 숫자만 입력가능합니다.")) { return false; }
		return true;
	};
	
	//입력필드 초기화
	initValue = function(){
		$("#seq").val("");
		$("#msg").val("");
		$("#msgLev").val("");
		$("#type").val("");
		$("#specialCd").val("");
	};
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#searchType").val("");
		$("#searchText").val("");
		$("#sortSeq").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
	};
	
	// 하위 테이블 삭제존재유무 체크
	var selectMsgCntAjax = {
		success : function(resultData) {
			if( resultData.length > 0 ) {
				var msg = checkSelectMsgCntAjax(resultData);
				alert(msg);
			} else {
				var checkConfirm = confirm("정말로 삭제하시겠습니까?");
				if(checkConfirm) {
					$("#msgForm").attr("action", "/msg/deleteMsg.json");
					$("#msgForm").ajaxSubmit(deleteAjax);
				}
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	function checkSelectMsgCntAjax(resultData) {
		var str = "";
		var infoMsgCompare = "";
		for(var i=0; i<resultData.length; i++) {
			if(resultData[i].notify != null && resultData[i].notify != "") {
				if(infoMsgCompare != resultData[i].infoMsg) {
					str += resultData[i].infoMsg; 
					str += " : [고시, 서브고시] : ";
					infoMsgCompare = resultData[i].infoMsg;
				}
				str += "[" + resultData[i].notify + ", " + resultData[i].notifySub + "]";
				if(i<(resultData.length-1)) {
					str += " / ";
				}
			}
		}
		str += "의 데이터가 존재해서 데이터를 삭제 할 수 없습니다.";
		return str;
	}
	
	// 청구서 메시지 체크
	var selectScreenMsgCntAjax = {
		success : function(resultData) {
			if( resultData > 0 ) {
				alert("이 메시지는 청구서와 관련해서 사용중이니, 삭제가 불가능한 메시지입니다.");
				return false;
			}else {
				goDelete();
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	//삭제 프로세스
	function goDelete() {
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
	
		//삭제 체크추출
		$("input[name=arrDel]:checked").each(function() {
			var test = $(this).val();
			delCheckArr[cnt] = test;
			cnt++;
		});
		$("#msgForm").attr("action", "/msg/selectMsgCnt.json");
		$("#msgForm").ajaxSubmit(selectMsgCntAjax);
	}
</script>


<form id="msgForm" name="msgForm" action="/msg/selectMsgList.doo"method="post">
	<input type="hidden" id="seq" name="seq" value=""/>
	<input type="hidden" id="mode" name="mode" value=""/>
	
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="13%" />
		<col width="29%" />
		<col width="13" />
		<col width="30%" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>검색어</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00076" nonSelect="Y" selectCode="${msgVo.searchType }" defaultCode="MSG"/>
					<input type="text" id="searchText" name="searchText" value="${msgVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${msgVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${msgVo.sSortType }" />
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<table class="table5">
		<caption><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${msgVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.msgForm,'/msg/selectMsgList.doo');"/></span></caption>
		<colgroup>
			<col width="4%">
			<col width="4%">
			<col width="10%">
			<col width="*">
			<col width="4%">
			<col width="5%">
			<col width="7%">
			<col width="12%">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>No.</th>
				<th>타입</th>
				<th>메시지</th>
				<th>레벨</th>
				<th>특이상병</th>
				<th>수정자</th>
				<th>수정일</th>	
				<th>상세</th>	
			</tr>
		</thead>
		<tbody id="msgListTbody">
			<c:if test="${msgList != null}">
				<c:forEach var="msg" items="${msgList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" class="arrDel" value="${msg.seq}_${msg.type}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${msg.type}_${msg.msg}" />
						</td>
						<td>${msg.seq}</td>
						<td>
							<ct:code name="val" type="value" groupCode="00077" selectCode="${msg.type}"/>
						</td>
						<td style="text-align: left; padding-left: 10px;">${msg.msg}</td>
						<td>${msg.msgLev}</td>
						<td>${msg.specialCd}</td>
						<td>${msg.updaterId}</td>
						<td>${msg.updateDthms}</td>
						<td>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readMsg('${msg.seq}');">메시지상세</a></span>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readSubMsg('${msg.seq}','${msg.type}');">고시별</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(msgList) == 0}">
				<tr>
					<td colspan="9" align="center">등록된 메시지가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="9">
					<span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteMsg">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${msgVo.pagingHtml}</div>
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="*">
			<col width="15%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>메시지</th>
				<th>레벨</th>
				<th>특이상병</th>
				<th>타입</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="msg" name="msg" value="" size="95" /></td>
				<td><ct:code name="msgLev" type="select" groupCode="00068" nonSelect="Y" selectCode=""/></td>
				<td><ct:code name="specialCd" type="select" groupCode="00046" displayType="onlyCD" selectCode=""/></td>
				<td><ct:code name="type" type="select" groupCode="00077" displayType="CD" selectCode=""/></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createMsg">등록</a></span></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateMsg">수정</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>