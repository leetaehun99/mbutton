<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//조회
		$("#searchList").click(function() {
			$("#currentPage","#dictionaryForm").val("1");
			$("#dictionaryForm").attr("action", "/dictionary/selectDictionary.doo");
			$("#dictionaryForm").submit();
		});
		

		// 한글명 엔터시 조회
		$("#searchText").keyup(function(e) {
			if(e.keyCode == 13) {
				if(!blankCheck("#searchType",  "#dictionaryForm", "검색어타입을 선택해주십시오.")) { return false; }
				if(!blankCheck("#searchText",  "#dictionaryForm", "검색어를 입력해주십시오.")) { return false; }
				$("#searchList").click();
			}
		});
		
		$("#searchText").focus();

	});
	
	selectItem = function(cd,nm){
		val = $("#searchDictionary").val();
		if(val==1){
			opener.diseaseStatementValueSetting(cd,nm);
		}
		self.close();
	};
	
	selectTrtItem = function(cd,sectNum1,sectNum2,cdDivd){
		opener.treatmentStatementValueSetting(cd,sectNum1,sectNum2,cdDivd,$("#untPrc").val());
		self.close();
	};
	
	selectTrtType = function(cd,mode,type,untPrc){
		/* 기존 소스
		
		$("#cd").val(cd);
		$("#mode").val(mode);
		$("#type").val(type);
		$("#untPrc").val(untPrc);
		$("#dictionaryForm").attr("action", "/statement/selectTrtType.json");
		$("#dictionaryForm").ajaxSubmit(selectTrtTypeAjax);	
		
		*/
		
		// 수정 소스 --시작
		$("#cd").val(cd);			// 코드
		$("#mode").val(mode);
		$("#type").val(type);
		$("#untPrc").val(untPrc);	// 단가

		opener.treatmentStatementValueSetting(cd,'','',mode,untPrc);
		
		//$("#dictionaryForm").attr("action", "/statement/selectTrtType.json");
		//$("#dictionaryForm").attr("action", "/statementTest/selectTrtType.json");
		//$("#dictionaryForm").ajaxSubmit(selectTrtTypeAjax);
		//selectTrtItem(resultData.cd,resultData.sectNum1,resultData.sectNum2,resultData.cdDivd);
		alert("입력하였습니다.");
		// 수정 소스 --끝
	};
	
	// 항목 구하기
	var selectTrtTypeAjax = {
		beforeSend : function(){
			//$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				if(resultData.result=="Y"){
					selectTrtItem(resultData.cd,resultData.sectNum1,resultData.sectNum2,resultData.cdDivd);
				}else{
					alert(resultData.result);
				}
			}else {
				alert("조회 실패");
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			
		}
	};
</script>
<form id="dictionaryForm" name="dictionaryForm" method="post" action="/dictionary/selectDictionary.doo">
	<input type="hidden" id="searchDictionary" name="searchDictionary" value="${dictionaryVo.searchDictionary }" />
	<input type="hidden" id="mode" name="type" value="" />
	<input type="hidden" id="type" name="mode" value="" />
	<input type="hidden" id="cd" name="cd" value="" />
	<input type="hidden" id="untPrc" name="untPrc" value="" />
<!--사전  -->
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="40%" />
		<col width="30%" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>검색어</th>
				<td>
					<c:if test="${dictionaryVo.searchDictionary=='1' }">
						<select id="searchType" name="searchType" >
							<option value="DISEA_KOR_NM" <c:if test="${dictionaryVo.searchType=='DISEA_KOR_NM' }">selected</c:if> >한글명</option>
							<option value="DISEA_ENG_NM" <c:if test="${dictionaryVo.searchType=='DISEA_ENG_NM' }">selected</c:if>>영문명</option>
							<option value="DISEA_CD" <c:if test="${dictionaryVo.searchType=='DISEA_CD' }">selected</c:if>>코드</option>
						</select>
					</c:if>
					
					<c:if test="${dictionaryVo.searchDictionary=='2' }">
						<select id="searchType" name="searchType" >
							<option value="CD" <c:if test="${dictionaryVo.searchType=='CD' }">selected</c:if>>코드</option>
							<option value="KORNM" <c:if test="${dictionaryVo.searchType=='KORNM' }">selected</c:if>>한글명</option>
						</select>
					</c:if>
					<input type="text" id="searchText" name="searchText" value="${dictionaryVo.searchText }"/>
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
				</td>
			</tr>
			
		</tbody>
		</table>
	</div>
	<br>
	
	<c:if test="${dictionaryVo.searchDictionary=='1' }">
		<table class="table5">
			<caption><img src="/resources/img/dictionary.gif"></caption>
			<colgroup>
				<col width="20%;">
				<col width="40%;">
				<col width="40%;">
			</colgroup>
			<thead>
				<tr>
					<th>코드</th>
					<th>한글명</th>
					<th>영문명</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${dictionaryList != null}">
					<c:forEach var="dictionary" items="${dictionaryList}">
						<tr class="trFocus" onclick="javascript:selectItem('${dictionary.cd}','${dictionary.korNm}');">
							<td>${dictionary.cd}</td>
							<td>${dictionary.korNm}</td>
							<td>${dictionary.engNm}</td>	
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${dictionaryList == null || fn:length(dictionaryList)  == 0}">
					<tr><td  colspan="3">검색된 내용이 없습니다.</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 페이지번호 -->
		<div class="paging">${dictionaryVo.pagingHtml}</div>
	</c:if>
	<c:if test="${dictionaryVo.searchDictionary=='2' }">
		<table class="table5">
			<caption><img src="/resources/img/dictionary.gif"></caption>
			<colgroup>
				<col width="5%;">
				<col width="10%;">
				<col width="10%;">
				<col width="*">
				<col width="*">
				<col width="10%;">
				<col width="10%;">
				<col width="10%;">
			</colgroup>
			<thead>
				<tr>
					<th>구분1</th>
					<th>구분2</th>
					<th>코드</th>
					<th>한글명</th>
					<th>영문영</th>
					<th>단가</th>
					<th>선택</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${dictionaryList != null}">
					<c:forEach var="dictionary" items="${dictionaryList}">
						<tr class="trFocus">
							<td>
								<c:if test="${dictionary.mode == 'A'}">수가</c:if>
								<c:if test="${dictionary.mode == 'B'}">약가</c:if>
								<c:if test="${dictionary.mode == 'C'}">재료대</c:if>
							</td>
							<td>
								<c:if test="${dictionary.mode == 'A'}"><ct:code name="A1" type="value" groupCode="00054" selectCode="${dictionary.type1}" /></c:if>
								<c:if test="${dictionary.mode == 'B'}"><ct:code name="A2" type="value" groupCode="00055" selectCode="${dictionary.type1}" /></c:if>
								<c:if test="${dictionary.mode == 'C'}"><ct:code name="A3" type="value" groupCode="00056" selectCode="${dictionary.type1}" /></c:if>
							</td>
							<td>${dictionary.cd}</td>
							<td class="l">${dictionary.korNm}</td>
							<td class="l">${dictionary.engNm}</td>
							<td class="r"><fmt:formatNumber  value="${dictionary.cost}" type="number"/></td>	
							<td><span class="button medium "><span class="check"></span><a onclick="javascript:selectTrtType('${dictionary.cd}','${dictionary.mode}','${dictionary.type1}','${dictionary.cost}');">선택하기</a></span></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${dictionaryList == null || fn:length(dictionaryList)  == 0}">
					<tr><td  colspan="7">검색된 내용이 없습니다.</td></tr>
				</c:if>
			</tbody>
		</table>
		<!-- 페이지번호 -->
		<div class="paging">${dictionaryVo.pagingHtml}</div>
	</c:if>
	
</form>


<!--//사전  -->