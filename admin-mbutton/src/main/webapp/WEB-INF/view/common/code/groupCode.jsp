<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		//등록폼 초기화
		$("#initValue").click(function() { 
			initValue();
			$("#grpCdNm").focus();
			btnDisplay(true);
		});
		
		//메뉴 등록
		$("#createGroupCode").click(function() { 
			if(validationCheck()){
				$("#groupCodeForm").attr("action", "/groupCode/createGroupCode.json");
				$("#groupCodeForm").ajaxSubmit(createGroupCodeAjax);
			}
		});
		//코드 수정
		$("#updateGroupCode").click(function() { 
			if(validationCheck()){
				$("#groupCodeForm").attr("action", "/groupCode/updateGroupCode.json");
				$("#groupCodeForm").ajaxSubmit(createGroupCodeAjax);
			}
		});
		
		//검색
		$("#searchList").click(function(){
			$("#groupCodeForm").attr("action", "/groupCode/selectGroupCodeList.json");
			$("#groupCodeForm").submit();
		});
		
		// 한글명 엔터시 조회
		$("#searchText").keyup(function(e) {
			if(e.keyCode == 13) {
				if(!blankCheck("#searchType",  "#groupCodeForm", "검색어타입을 선택해주십시오.")) { return false; }
				if(!blankCheck("#searchText",  "#groupCodeForm", "검색어를 입력해주십시오.")) { return false; }
				$("#searchList").click();
			}
		});

		//검색필드 초기화
		$("#initSearchValue").click(function(e) {
			$("#searchType").val("");
			$("#searchText").val("");
			$("#sSortOrder").val("UPDATE_DTHMS");
			$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
			$("#sUseYn").val("");
		});
		
		btnDisplay(true);
	});
	
	//그룹코드 상세 function
	readGroupCode = function(grpCd){
		$("#grpCd").val(grpCd);
		$("#groupCodeForm").attr("action", "/groupCode/selectGroupCode.json");
		$("#groupCodeForm").ajaxSubmit(selectGroupCodeAjax);
		btnDisplay(false);
	}
	
	//코드 리스트 function
	readCodeList = function(grpCd,cdLength){
		$("#grpCd").val(grpCd);
		$("#cdLength").val(cdLength);
		$("#groupCodeForm").attr("action", "/code/selectCodeList.doo");
		$("#groupCodeForm").submit();
	}
	
	// 그룹코드 등록Ajax
	var createGroupCodeAjax = {
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
	
	// 그룹코드 상세Ajax 
	var selectGroupCodeAjax = {
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
		$("#grpCd").val(data.grpCd);
		$("#grpCdNm").val(data.grpCdNm);
		$("#useYn").val(data.useYn);
		$("#cdLn").val(data.cdLn);
		$("#cdLn").attr("readonly",true);
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
		if(!blankCheck("#grpCdNm",  "#groupCodeForm", "그룹코드명은 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#cdLn",  "#groupCodeForm", "코드길이는 필수입력 사항입니다.")) { return false; }
		if(!onlyNumber("#cdLn",  "#groupCodeForm", "코드길이는 숫자만 입력가능합니다.")) { return false; }
		if(!blankCheck("#useYn",  "#groupCodeForm", "사용여부는 필수입력 선택사항입니다.")) { return false; }
		return true;
	}
	
	//입력필드 초기화
	initValue = function(){
		$("#grpCd").val("");
		$("#grpCdNm").val("");
		$("#useYn").val("");
		$("#cdLn").val("");
		$("#cdLn").attr("readonly",false);
	}
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#searchType").val("");
		$("#searchText").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
		$("#sUseYn").val("");
	}
</script>


<form id="groupCodeForm" name="groupCodeForm" action="/groupCode/selectGroupCodeList.doo"method="post">
	<input type="hidden" id="grpCd" name="grpCd" value="" />
	<input type="hidden" id="cdLength" name="cdLength" value="" />
	<!-- 검색영역 -->
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
					<ct:code name="searchType" type="select" groupCode="00013"  selectCode="${groupCodeVo.searchType }" defaultCode="GRP_CD"/>
					<input type="text" id="searchText" name="searchText" value="${groupCodeVo.searchText }"/>
				</td>
				<th>사용여부</th>
				<td>
					<ct:code name="sUseYn" type="select" groupCode="00002"  selectCode=""/>
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
			<tr>
				<th>정렬</th>
				<td colspan="3">
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${groupCodeVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${groupCodeVo.sSortType }" />
				</td>
			</tr>
			<tr>
				
			</tr>
		</tbody>
		</table>
	</div>
	<!--// 검색영역 -->
	
	
	<table class="table5">
		<caption><img src="/resources/img/groupCodeList.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${groupCodeVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.groupCodeForm,'/groupCode/selectGroupCodeList.doo');"/></span></caption>
		<colgroup>
			<col width="7%">
			<col width="*">
			<col width="6%">
			<col width="15%">
			<col width="9%">
			<col width="20%">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>그룹코드</th>
				<th>그룹코드명</th>
				<th>코드길이</th>
				<th>사용여부</th>
				<th>수정자</th>
				<th>수정일</th>
				<th>상세</th>	
			</tr>
		</thead>
		<tbody>
			<c:if test="${groupCodeList != null}">
				<c:forEach var="groupCode" items="${groupCodeList}">
					<tr>
						<td>${groupCode.grpCd}</td>
						<td>${groupCode.grpCdNm}</td>
						<td>${groupCode.cdLn}</td>
						<td><ct:code name="cdUseYn" type="value" groupCode="00002"  selectCode="${groupCode.useYn}"/></td>						
						<td>${groupCode.updaterId}</td>
						<td>${groupCode.updateDthms}</td>
						<td>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readGroupCode('${groupCode.grpCd}');">그룹코드상세</a></span>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readCodeList('${groupCode.grpCd}','${groupCode.cdLn}');">코드상세</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(groupCodeList) == 0}">
				<tr>
					<td colspan="7" align="center">등록된 그룹코드가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="7"><span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${groupCodeVo.pagingHtml}</div>
	
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="40%">
			<col width="*">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>그룹코드명</th>
				<th>코드길이</th>
				<th>사용여부</th>	
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="grpCdNm" name="grpCdNm" value="" maxlength="10" /></td>
				<td><input type="text" id="cdLn" name="cdLn" value="" maxlength="2"/></td>
				<td><ct:code name="useYn" type="select" groupCode="00002"  defaultCode="N" selectCode=""/></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createGroupCode">등록</a></span></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateGroupCode">수정</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>	
