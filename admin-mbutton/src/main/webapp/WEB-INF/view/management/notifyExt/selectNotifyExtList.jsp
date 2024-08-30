<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var delCheckArr = new Array();
var cnt = 0;

	$(function() {		
		//등록폼 초기화
		$("#initValue").click(function() { 
			initValue();
			$("#notifyExtNm").focus();
			btnDisplay(true);
		});
		
		//메시지 등록
		$("#createNotifyExt").click(function() { 
			if(validationCheck()){
				$("#notifyExtForm").attr("action", "/notifyExt/createNotifyExtForm.doo");
				$("#notifyExtForm").submit();
			}
		});
		
		//검색
		$("#searchList").click(function(){
			$("#notifyExtForm").attr("action", "/notifyExt/selectNotifyExtList.doo");
			$("#notifyExtForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
		//삭제
		$("#deleteNotifyExt").click(function() {
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
			$("#notifyExtForm").attr("action", "/notifyExt/selectNotifyExtCnt.json");
			$("#notifyExtForm").ajaxSubmit(selectNotifyExtCntAjax);
		});
		btnDisplay(true);
		
		$("#delete").hide();
	});
	
	//메시지 상세 function
	var readNotifyExt = function(extNotify, extNotifySub){
		$("#extNotify").val(extNotify);
		$("#extNotifySub").val(extNotifySub);
		$("#notifyExtForm").attr("action", "/notifyExt/selectNotifyExtForm.doo");
		$("#notifyExtForm").submit();
		btnDisplay(false);
	};

	var readSubNotifyExt = function(extNotify, extNotifySub){
		$("#mode","#notifyExtForm").val("SUB");//서브
		$("#extNotify").val(extNotify);
		$("#extNotifySub").val(extNotifySub);
		
		if (extNotify == "0012") {
			$("#notifyExtForm").attr("action", "/notifyKk/selectNotifyKkItemList.doo");
			$("#notifyExtForm").submit();
		} else {
			$("#notifyExtForm").attr("action", "/notifyExt/selectSubNotifyExtList.doo");
			$("#notifyExtForm").submit();
		}
	};
	
	//값 세팅
	valueSetting = function(data){
		$("#seq").val(data.seq);
		$("#notifyExt").val(data.notifyExt);
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
		//if(!onlyNumber("#notifyExtLev", "#notifyExtForm", "메시지레벨은 숫자만 입력가능합니다.")) { return false; }
		//if(!blankCheck("#notifyExt",  "#notifyExtForm", "메시지는 필수입력 사항입니다.")) { return false; }
		//if(!blankCheck("#notifyExtLev", "#notifyExtForm", "메시지레벨은 필수입력 사항입니다.")) { return false; }
		return true;
	};
	
	//입력필드 초기화
	initValue = function(){
		$("#extNotify").val("");
		$("#extNotifySub").val("");
		$("#notifyNm").val("");
		$("#notifyMainNm").val("");
		$("#notifyContents").val("");
		$("#msg").val("");
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
	var selectNotifyExtCntAjax = {
		success : function(resultData) {
			if( resultData.length > 0 ) {
				var msg = checkSelectNotifyExtCntAjax(resultData);
				alert(msg);
			} else {
				var checkConfirm = confirm("정말로 삭제하시겠습니까?");
				if(checkConfirm) {
					$("#notifyExtForm").attr("action", "/notifyExt/deleteNotifyExt.json");
					$("#notifyExtForm").ajaxSubmit(deleteAjax);
				}
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	function checkSelectNotifyExtCntAjax(resultData) {
		var str = "";
		str += "[고시, 서브고시] : ";
		for(var i =0; i<resultData.length; i++) {
			for(var j=0; j<delCheckArr.length; j++) {
				var a = resultData[i].extNotify + "_" + resultData[i].extNotifySub;
				if(a != delCheckArr[j]) {
					continue;
				}
				str += "[" + resultData[i].extNotify + ", " + resultData[i].extNotifySub + "]";
				str += " ";
			}
		}
		str += "의 하위테이블에 데이터가 존재해서 삭제 할 수 없습니다.";
		return str;
	}
</script>


<form id="notifyExtForm" name="notifyExtForm" action="/notifyExt/selectNotifyExtList.doo"method="post">
	<input type="hidden" id="extNotify" name="extNotify" value=""/>
	<input type="hidden" id="extNotifySub" name="extNotifySub" value=""/>
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
					<ct:code name="searchType" type="select" groupCode="00070" nonSelect="Y" selectCode="${notifyExtVo.searchType }" defaultCode="DRUG_CD"/>
					<input type="text" id="searchText" name="searchText" value="${notifyExtVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${notifyExtVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${notifyExtVo.sSortType }" />
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
		<caption><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyExtVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyExtForm,'/notifyExt/selectNotifyExtList.doo');"/></span></caption>
		<colgroup>
			<col width="5%">
			<col width="15%">
			<col width="4%">
			<col width="4%">
			<col width="15%">
			<col width="*">
			<col width="7%">
			<col width="12%">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>	
				<th>상세</th>
				<th>고시</th>
				<th>서브고시</th>
				<th>고시명</th>
				<th>메시지</th>
				<th>수정자</th>
				<th>수정일</th>
			</tr>
		</thead>
		<tbody id="notifyExtListTbody">
			<c:if test="${notifyExtList != null}">
				<c:forEach var="notifyExt" items="${notifyExtList}">
					<tr class="trFocus">
						<td class="tdFocus">
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${notifyExt.extNotify }_${notifyExt.extNotifySub}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${notifyExt.notifyNm}_${notifyExt.msg}" />
						</td>
						<td>
							<c:choose>
								<c:when test="${notifyExt.extNotify eq '0018' }">
									<span class="button medium icon"><span class="check"></span><a onclick="javascript:readSubNotifyExt('${notifyExt.extNotify}','${notifyExt.extNotifySub}');">수가</a></span>
								</c:when>
								<c:otherwise>
									<span class="button medium icon"><span class="check"></span><a onclick="javascript:readSubNotifyExt('${notifyExt.extNotify}','${notifyExt.extNotifySub}');">약가</a></span>
								</c:otherwise>
							</c:choose>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyExt('${notifyExt.extNotify}','${notifyExt.extNotifySub}');">상세</a></span>
						</td>
						<td>${notifyExt.extNotify}</td>
						<td style="text-align: left; padding-left: 10px;">${notifyExt.extNotifySub}</td>
						<td>${notifyExt.notifyNm}</td>
						<td>${notifyExt.msg}</td>
						<td>${notifyExt.updaterId}</td>
						<td>${notifyExt.updateDthms}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(notifyExtList) == 0}">
				<tr>
					<td colspan="8" align="center">등록된 메시지가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<span class="button medium icon"><span class="check"></span><a id="createNotifyExt">등록</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteNotifyExt">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${notifyExtVo.pagingHtml}</div>
</form>