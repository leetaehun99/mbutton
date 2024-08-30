<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		//등록폼 초기화
		$("#initValue").click(function() { 
			initValue();
			$("#cdNm").focus();
			btnDisplay(true);
		});
		
		//메뉴 등록
		$("#createCode").click(function() { 
			if(validationCheck()){
				$("#codeForm").attr("action", "/code/createCode.json");
				$("#codeForm").ajaxSubmit(createCodeAjax);
			}
		});
		//코드 수정
		$("#updateCode").click(function() { 
			if(validationCheck()){
				$("#codeForm").attr("action", "/code/updateCode.json");
				$("#codeForm").ajaxSubmit(createCodeAjax);
			}
		});
		
		//그룹코드 목록
		$("#selectGroupCodeList").click(function(){
			$("#codeForm").attr("action", "/groupCode/selectGroupCodeList.doo");
			$("#codeForm").submit();
		});
		
		btnDisplay(true);
	});
	
	//코드 상세 function
	readCode = function(cd){
		$("#cd").val(cd);
		$("#codeForm").attr("action", "/code/selectCode.json");
		$("#codeForm").ajaxSubmit(selectCodeAjax);
		
		$("#codeListTbody tr").each(function(){
			if(cd == $(this).find("td:eq(2)").text()) $(this).css("background-color","#e5edfe");
			else  $(this).css("background-color","");
		});
		
		btnDisplay(false);
	}
	
	// 코드 등록Ajax
	var createCodeAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				searchList();
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
	
	// 코드 상세Ajax 
	var selectCodeAjax = {
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
	
	//값 셋팅
	valueSetting = function(data){
		$("#cd").val(data.cd);
		$("#cdNm").val(data.cdNm);
		$("#useYn").val(data.useYn);
		$("#sort").val(data.sort);
		$("#ectKey").val("");
		$("#ectKey").attr("readonly",true);
	}
	
	//버튼 표현
	btnDisplay = function(type){
		if(type){//등록
			$("#btnMode2").hide();
			$("#btnMode1").show();
		}else{//수정
			$("#btnMode1").hide();
			$("#btnMode2").show();
		}
	}
	
	//value검증
	validationCheck = function(){
		if(!blankCheck("#cdNm",  "#codeForm", "코드명은 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#useYn",  "#codeForm", "사용여부는 필수입력 선택사항입니다.")) { return false; }
		if(!onlyNumber("#sort",  "#codeForm", "정렬은 숫자만 입력가능합니다.")) { return false; }
		return true;
	}
	
	//입력필드 초기화
	initValue = function(){
		$("#cd").val("");
		$("#cdNm").val("");
		$("#useYn").val("");
		$("#sort").val("");
		$("#ectKey").val("");
		$("#ectKey").attr("readonly",false);
	}
	
	//갱신
	searchList = function(){
		$("#codeForm").attr("action", "/code/selectCodeList.doo");
		$("#codeForm").submit();
	}
</script>


<form id="codeForm" name="codeForm" action="/code/selectCodeList.doo"method="post">
	<!-- 검색 필드 필수 -->
	<input type="hidden" id="rowPerPage" 	name="rowPerPage" 	value="${codeVo.rowPerPage}" />
	<input type="hidden" id="currentPage" 	name="currentPage" 	value="${codeVo.currentPage}" />
	<input type="hidden" id="sUseYn" 		name="sUseYn" 		value="${codeVo.sUseYn}" />
	<input type="hidden" id="sSortOrder" 	name="sSortOrder" 	value="${codeVo.sSortOrder}" />
	<input type="hidden" id="sSortType" 	name="sSortType" 	value="${codeVo.sSortType}" />
	<input type="hidden" id="searchText" 	name="searchText" 	value="${codeVo.searchText}" />
	<input type="hidden" id="searchType" 	name="searchType" 	value="${codeVo.searchType}" />
	<!-- //검색 필드 필수 -->
	
	<input type="hidden" id="grpCd" 		name="grpCd" 		value="${codeVo.grpCd}" />
	<input type="hidden" id="cdLn" 			name="cdLn" 		value="${codeVo.cdLn}" />
	<input type="hidden" id="cdLength" 		name="cdLength" 	value="${codeVo.cdLn}" />
	<input type="hidden" id="cd" 			name="cd" 			value="" />
	
	
	
	<table class="table5">
		<caption><img src="/resources/img/codeList.gif"></caption>
		<colgroup>
			<col width="10%">
			<col width="*">
			<col width="6%">
			<col width="*">
			<col width="15%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>그룹코드</th>
				<th>그룹코드명</th>
				<th>코드</th>
				<th>코드명</th>
				<th>사용여부</th>
				<th>수정자</th>
				<th>수정일</th>		
			</tr>
		</thead>
		<tbody id="codeListTbody">
			<c:if test="${codeList != null}">
				<c:forEach var="code" items="${codeList}">
					<tr class="trFocus" onclick="javascript:readCode('${code.cd}');">
						<td>${code.grpCd}</td>
						<td>${code.grpCdNm}</td>
						<td>${code.cd}</td>
						<td>${code.cdNm}</td>
						<td><ct:code name="cdUseYn" type="value" groupCode="00002" selectCode="${code.useYn}" /></td>
						<td>${code.updaterId}</td>
						<td>${code.updateDthms}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(codeList) == 0}">
				<tr>
					<td colspan="7" align="center">등록된 코드가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="7"><span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span></td></tr>
		</tfoot>
	</table>
	
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="40%">
			<col width="*">	
			<col width="20%">		
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>코드명</th>
				<th>특수키</th>
				<th>정렬</th>
				<th>사용여부</th>	
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="cdNm" name="cdNm" value="" /></td>
				<td><input type="text" id="ectKey" name="ectKey" value="" /></td>
				<td><input type="text" id="sort" name="sort" value="" size="3" maxlength="3"/></td>			
				<td><ct:code name="useYn" type="select" groupCode="00002"  selectCode="Y" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createCode">등록</a></span></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateCode">수정</a></span></span>
					<span class="button medium icon"><span class="check"></span><a id="selectGroupCodeList">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>	
