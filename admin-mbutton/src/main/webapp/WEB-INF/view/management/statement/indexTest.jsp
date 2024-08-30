<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		if("${recuperationCostAccountVo.result }" == "TRUE"){
			alert("등록성공");
		}else if("${recuperationCostAccountVo.result }" == "FALSE"){
			alert("등록실패");
		}else{}
		
		//검색
		$("#searchList").click(function(){
			$("#statementForm").attr("action", "/statementTest/index.doo");
			$("#statementForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
	});
	
	// 청구서 등록Ajax
	var createStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
		//	alert(resultData);
			if( resultData == "TRUE" ) {
				alert("등록되었습니다.");
				$("#searchList").click();
			} else if( resultData == "FALSE1" ) {
				alert("요양기관 ID가 일치하지 않는 명세서 입니다.");
			} else {
				alert("등록에 실패하였습니다.");
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
	
	//메뉴 상세 function
	readStatement = function(recpCstClmSeq,clmNum,hspId){

		
		$("#recpCstClmSeq").val(recpCstClmSeq);
		$("#clmNum").val(clmNum);
		$("#hspId").val(hspId);
		if(checkBrowser()=="IE")	pop('','adminStatementTest','1010', '730');
		else if(checkBrowser()=="FF")	pop('','adminStatementTest','1010', '730');
		else pop('','adminStatementTest','1110', '800');
		$("#statementForm").attr("action", "/statementTest/selectStatement.doo");
		$("#statementForm").attr("target", "adminStatementTest");
		$("#statementForm").submit();
		$("#statementForm").attr("target", "_self");
	}
	

	//청구서조회
	selectRecuperationCostAccount = function(recpCstClmSeq,clmNum,hspId){
		$("#recpCstClmSeq").val(recpCstClmSeq);
		$("#clmNum").val(clmNum);
		$("#hspId").val(hspId);
		pop('','recuperationCostAccount','800', '700');
		$("#statementForm").attr("action", "/statementTest/selectRecuperationCostAccount.doo");
		$("#statementForm").attr("target", "recuperationCostAccount");
		$("#statementForm").submit();
		$("#statementForm").attr("target", "_self");
	}
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#recpCstClmSeq").val("0");
		$("#searchType").val("");
		$("#searchText").val("");
		$("#sortSeq").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
	}
</script>


<form id="statementForm" name="statementForm" action="" method="post" enctype="multipart/form-data">

	<input type="hidden" id="clmNum" name="clmNum" value=""/>	
	<input type="hidden" id="recpCstClmSeq" name="recpCstClmSeq" value="0"/>
	<input type="hidden" id="stsSrlNum" name="stsSrlNum" value=""/>
	<input type="hidden" id="hspId" name="hspId" value=""/>
	<input type="text" style="display: none;" />  
	<div>
		<table class="table1" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="10%" />
		<col width="30%" />
		<col width="15%" />
		<col width="30%" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>검색어</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00016"  selectCode="${recuperationCostAccountVo.searchType }" defaultCode="0001"/>
					<input type="text" id="searchText" name="searchText" value="${recuperationCostAccountVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td >
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${recuperationCostAccountVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${recuperationCostAccountVo.sSortType }" />
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	<table class="table9">
		<caption><img src="/resources/img/statementList.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${recuperationCostAccountVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.statementForm,'/statementTest/index.doo');"/></span></caption>
		<colgroup>
			<col width="20%">
			<col width="10%">
			<col width="*">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>청구명</th>
				<th>요양기관</th>
				<th>청구번호</th>
				<th>청구인ID</th>
				<th>청구인</th>
				<th>작성자성명</th>
				<th>청구일자</th>
				<th>상세보기</th>		
			</tr>
		</thead>
		<tbody>
			<c:if test="${recuperationCostAccountList != null}">
				<c:forEach var="recuperationCostAccount" items="${recuperationCostAccountList}">
					<tr>
						<td>${recuperationCostAccount.stsTitle}</td>
						<td>${recuperationCostAccount.hspId}</td>
						<td>${recuperationCostAccount.clmNum}</td>
						<td>${recuperationCostAccount.registerId}</td>
						<td>${recuperationCostAccount.clmNm}</td>
						<td>${recuperationCostAccount.wrtNm}</td>
						<td>${recuperationCostAccount.clmDt}</td>
						<td>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readStatement('${recuperationCostAccount.recpCstClmSeq}','${recuperationCostAccount.clmNum}','${recuperationCostAccount.hspId}');">상세보기</a></span>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:selectRecuperationCostAccount('${recuperationCostAccount.recpCstClmSeq}','${recuperationCostAccount.clmNum}','${recuperationCostAccount.hspId}');">청구서 조회</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(recuperationCostAccountList) == 0}">
				<tr>
					<td colspan="8" align="center">등록된 청구서가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</form>	
