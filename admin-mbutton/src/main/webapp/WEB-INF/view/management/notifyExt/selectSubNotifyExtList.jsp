<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">

	$(function() {	
		$("#searchText").keypress(function(e) {
			if((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
				$("#searchList").click();
			}
		});
		
		//등록폼 초기화
		$("#initValue").click(function() { 
			initValue();
			$("#subNotifyExtNm").focus();
			btnDisplay(true);
		});
		
		//메시지 등록
		$("#createSubNotifyExt").click(function() { 
			if(validationCheck()){
				$("#subNotifyExtForm").attr("action", "/notifyExt/createSubNotifyExt.json");
				$("#subNotifyExtForm").ajaxSubmit(createSubNotifyExtAjax);
			}
		});
		
		$("#selectNotifyExtList").click(function() { 
			$("#mode","#notifyExtListForm").val("LIST");
			$("#notifyExtListForm").attr("action", "/notifyExt/selectNotifyExtList.doo");
			$("#notifyExtListForm").submit();
		});
		
		//메시지 수정
		$("#updateSubNotifyExt").click(function() { 
			if(validationCheck()){
				$("#subNotifyExtForm").attr("action", "/notifyExt/updateSubNotifyExt.json");
				$("#subNotifyExtForm").ajaxSubmit(createSubNotifyExtAjax);
			}
		});
		
		//삭제
		$("#deleteSubNotifyExt").click(function() {
			if(!$(".arrDel").is(":checked")) {
				alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
				return false;
			}
			
			//arrDel에 있는 약가코드를 사용하기 때문에 historyMsg가 필요없음
			
			//alert($(".trFocus").eq(0).find("td").eq(1).html());)
			var extNotify = $(".tdExtNotify").eq(0).html();
			if(extNotify == "0009" || extNotify == "0010" || extNotify == "0011") {
				alert("서비스 점검중입니다.");
			}else {
				var checkConfirm = confirm("정말로 삭제하시겠습니까?");
				if(checkConfirm) {
					$("#subNotifyExtForm").attr("action", "/notifyExt/deleteSubNotifyExt.json");
					$("#subNotifyExtForm").ajaxSubmit(deleteAjax);
				}
			}
		});
		
		//검색
		$("#searchList").click(function(){
			$("#subNotifyExtForm").attr("action", "/notifyExt/selectSubNotifyExtList.doo");
			$("#subNotifyExtForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
		btnDisplay(true);

		$("#delete").hide();
	});
	
	//메시지 상세 function
	var readSubNotifyExt = function(extNotify, extNotifySub, drugCd){
		$("#extNotify").val(extNotify);
		$("#extNotifySub").val(extNotifySub);
		$("#drugCd").val(drugCd);
		$("#subNotifyExtForm").attr("action", "/notifyExt/selectSubNotifyExt.json");
		$("#subNotifyExtForm").ajaxSubmit(selectSubNotifyExtAjax);
		btnDisplay(false);
	};
	
	// 메시지 등록Ajax
	var createSubNotifyExtAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				$("#searchList").click();
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
	var selectSubNotifyExtAjax = {
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
		//$("#drugCd").val(data.drugCd);
		$("#extDiv1").val(data.extDiv1);
		$("#extDiv2").val(data.extDiv2);
		$("#val").val(data.val);
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
		if(!blankCheck("#drugCd",  "#subNotifyExtForm", "약가코드는 필수입력 사항입니다.")) { return false; }
		//if(!onlyNumber("#val", "#subNotifyExtForm", "결과값은 숫자만 입력가능합니다.")) { return false; }
		return true;
	};
	
	//입력필드 초기화
	initValue = function(){
		$("#drugCd").val("");
		$("#extDiv1").val("");
		$("#extDiv2").val("");
		$("#val").val("");
	};
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#searchType").val("");
		$("#searchText").val("");
		$("#sortSeq").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
	};
	
</script>
<form id="notifyExtListForm" name="notifyExtListForm" action="/notifyExt/selectNotifyExtList.doo"method="post">
	<!--검색 조건  -->
	${subPagingParam}
</form>

<form id="subNotifyExtForm" name="subNotifyExtForm" action="/notifyExt/selectSubNotifyExtList.doo"method="post">
	<input type="hidden" id="extNotify" name="extNotify" value="${notifyExtVo.extNotify}"/>
	<input type="hidden" id="extNotifySub" name="extNotifySub" value="${notifyExtVo.extNotifySub}"/>
	
	${subPagingParam}
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
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<table class="table5">
		<caption><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyExtVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.subNotifyExtForm,'/notifyExt/selectSubNotifyExtList.doo');"/></span></caption>
		<colgroup>
			<col width="5%">
			<col width="8%">
			<col width="4%">
			<col width="11%">
			<col width="7%">
			<col width="7%">
			<col width="7%">
			<col width="7%">
			<col width="12%">
			<col width="7%">
			<col width="12%">
			<col width="17%">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>고시</th>
				<th>서브고시</th>
				<th>약가코드</th>
				<th>DIV1</th>
				<th>DIV2</th>
				<th>값</th>
				<th>등록자</th>
				<th>등록일</th>	
				<th>수정자</th>
				<th>수정일</th>	
				<th>상세</th>
			</tr>
		</thead>
		<tbody id="notifyExtListTbody">
			<c:if test="${notifyExtList != null}">
				<c:forEach var="subNotifyExt" items="${notifyExtList}">
					<tr class="trFocus">
						<td><input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${subNotifyExt.drugCd}_${subNotifyExt.extNotify}_${subNotifyExt.extNotifySub}" /></td>
						<td class="tdExtNotify">${notifyExtVo.extNotify}</td>
						<td>${notifyExtVo.extNotifySub}</td>
						<td>${subNotifyExt.drugCd}</td>
						<td>
							<c:if test="${subNotifyExt.extNotify == '0001'}">
								<ct:code name="lee1" type="value" groupCode="00072"  selectCode="${subNotifyExt.extDiv1 }" />
							</c:if>
							<c:if test="${subNotifyExt.extNotify != '0001'}">
								${subNotifyExt.extDiv1 }
							</c:if>
						</td>
						<td>
							<c:if test="${subNotifyExt.extNotify == '0001'}">
								<ct:code name="lee1" type="value" groupCode="00073"  selectCode="${subNotifyExt.extDiv2 }" />
							</c:if>
							<c:if test="${subNotifyExt.extNotify != '0001'}">
								${subNotifyExt.extDiv2 }
							</c:if>
						</td>
						<td>${subNotifyExt.val}</td>
						<td>${subNotifyExt.registerId}</td>
						<td>${subNotifyExt.registDthms}</td>
						<td>${subNotifyExt.updaterId}</td>
						<td>${subNotifyExt.updateDthms}</td>
						<td>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readSubNotifyExt('${subNotifyExt.extNotify}','${subNotifyExt.extNotifySub}','${subNotifyExt.drugCd}');">메시지상세</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(notifyExtList) == 0}">
				<tr>
					<td colspan="12" align="center">등록된 메시지가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="12">
					<span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteSubNotifyExt">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${notifyExtVo.pagingHtml}</div>
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="*">
			<col width="10%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>약가코드</th>
				<th>DIV1</th>
				<th>DIV2</th>
				<th>값</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="drugCd" name="drugCd" value="" size="15" /></td>
				<td><input type="text" id="extDiv1" name="extDiv1" value="" size="7" maxlength="1"/></td>
				<td><input type="text" id="extDiv2" name="extDiv2" value="" size="7" maxlength="1"/></td>
				<td><input type="text" id="val" name="val" value="" size="7" maxlength="10"/></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createSubNotifyExt">등록</a></span></span>
					<span class="button medium icon"><span class="check"></span><a id="selectNotifyExtList">목록</a></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateSubNotifyExt">수정</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>