<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<link href="/resources/css/style.css?v=0.3" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function() {

	    $("#popWrap").draggable();
	    
		if("${recuperationCostAccountVo.result }" == "TRUE"){
			alert("등록성공");
		}else if("${recuperationCostAccountVo.result }" == "FALSE"){
			alert("등록실패");
		}else{}
		
		//청구서 등록 체크
		$("#createStatementCheck").click(function(){
			$("#stsTitle").val("");
			$.blockUI({message:$("#popWrap"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
			//$.blockUI({message:$("#popWrap"),css:{border: 'none',left:'25%',top:'100px',backgroundColor:''}, overlayCSS : {opacity:0.0,border: 'none'}});
		});
		//청구서 등록
		$("#createStatement").click(function(){ //상세코드 이동
			if($("#stsTitle").val() != ""){
				$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
				$("#statementForm").attr("action", "/statement/createStatement.json");
				$("#statementForm").ajaxSubmit(createStatementAjax);
			}else{
				alert("타이틀을 입력하세요.");
				$("#stsTitle").focus();
			}
		});
		
		//검색
		$("#searchList").click(function(){
			$("#statementForm").attr("action", "/statement/index.doo");
			$("#statementForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
		//전체 체크 해제
		$("#allCheck").change(function(){
			if(this.checked){
				$("input[name=recpCstClmSeqArray]").prop("checked",true);
			}else{
				$("input[name=recpCstClmSeqArray]").prop("checked",false);
			}
		});
		
		$("#deleteStatement").click(function(){
			checkFlag = false;
			$("input[name=recpCstClmSeqArray]:checked").each(function(){
				checkFlag = true;
			});
			if(checkFlag){
				if(checkFlag){
					$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
					$("#statementForm").attr("action", "/statement/deleteStatement.doo");
					$("#statementForm").submit();
				}
			}else{
				alert("삭제 하시려는 청구서를 CHECK 하십시오..");
			}
		});
	});
	
	function hitEnterKey(e){
		  if(e.keyCode == 13){
			  $("#searchList").click();
		  }
	}
	progressObj = "";
	// 청구서 등록Ajax
	var createStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
			$("#progressbar").width('0%');
			$("#percent").html("0%");
			$("#progressbox").show();
		},
		uploadProgress : function(event, position, total, percentComplete) {
			$("#progressbar").width(percentComplete+'%');
			$("#percent").html( percentComplete+'%');
			if (percentComplete == 100) {
				$("#progressbar2").width('0%');
				$("#percent2").html("0%");
				$("#progressbox2").show();
				progressObj = setInterval(function(){
				$.ajax({
						type:"POST",
						url:"/statement/uploadProgress.json",
						success : function(result){
							var json=eval('(' + result+')');
							if(parseInt(json.icurrentVal)>1){
								$("#progressbar2").width(parseInt( (parseInt(json.icurrentVal) / parseInt(json.itotalVal)* 100), 10)+'%');
								$("#percent2").html(parseInt( (parseInt(json.icurrentVal) / parseInt(json.itotalVal)* 100), 10)+'%');
							}else{
								$("#percent2").html("유효성 체크중입니다..");
							}
						}
					});
				},1000);
			}
		},
		success : function(resultData) {
			if( resultData.RESULT == "Y" ) {
				$("#progressbar2").width('100%');
				$("#percent2").html('100%');
				
				$("#progressbar3").width('100%');
				$("#percent3").html('100%');
				
				alert("등록되었습니다.");
				$("#searchList").click();
			}else {
				$("#progressbar").width('0%');
				$("#percent").html('0%');
				alert(resultData.ERROR_MSG);
				if(progressObj!="")	clearInterval(progressObj);  
			}
			$("#progressbar2").width('0%');
			$("#percent2").html('0%');
			if(progressObj!="")	clearInterval(progressObj);  
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			clearInterval(progressObj);  
		}
	};
	
	//메뉴 상세 function
	readStatement = function(recpCstClmSeq,clmNum,hspId){
		
		$("#recpCstClmSeq").val(recpCstClmSeq);
		$("#clmNum").val(clmNum);
		$("#hspId").val(hspId);
		if(checkBrowser()=="IE")	pop('','adminStatement','1010', '730');
		else if(checkBrowser()=="FF")	pop('','adminStatement','1010', '730');
		else pop('','adminStatement','1010', '730');
		$("#statementForm").attr("action", "/statement/selectStatement.doo");
		$("#statementForm").attr("target", "adminStatement");
		$("#statementForm").submit();
		$("#statementForm").attr("target", "_self");
	}
	

	//청구서조회
	selectRecuperationCostAccount = function(recpCstClmSeq,clmNum,hspId){
		$("#recpCstClmSeq").val(recpCstClmSeq);
		$("#clmNum").val(clmNum);
		$("#hspId").val(hspId);
		pop('','recuperationCostAccount','710', '620');
		$("#statementForm").attr("action", "/statement/selectRecuperationCostAccount.doo");
		$("#statementForm").attr("target", "recuperationCostAccount");
		$("#statementForm").submit();
		$("#statementForm").attr("target", "_self");
	}
	
	//점검결과 조회
	selectStatementResult = function(recpCstClmSeq,clmNum,hspId){
		$("#recpCstClmSeq").val(recpCstClmSeq);
		$("#clmNum").val(clmNum);
		$("#hspId").val(hspId);
		$("#statementForm").attr("action", "/statement/selectStatementResult.doo");
		$("#statementForm").attr("target", "_self");
		$("#statementForm").submit();
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
	
	//다운로드
	download = function(fileSeq){
		
		$("#fileSeq").val(fileSeq);
		$("#statementForm").attr("action", "/statement/download.doo");
		$("#statementForm").submit();
	};
</script>


<form id="statementForm" name="statementForm" action="" method="post" enctype="multipart/form-data">

	<input type="hidden" id="clmNum" name="clmNum" value=""/>	
	<input type="hidden" id="recpCstClmSeq" name="recpCstClmSeq" value="0"/>
	<input type="hidden" id="stsSrlNum" name="stsSrlNum" value="00001"/>
	<input type="hidden" id="hspId" name="hspId" value=""/>
	<input type="hidden" id="birthy" name="birthy" value=""/>
	<input type="hidden" id="type" name="type" value="INDEX"/>
	<input type="hidden" id="fileSeq" name="fileSeq" value=""/>
	<input type="text" style="display: none;" />  
	<div>
	
		
		<table class="table1">
			<caption><img src="/resources/img/ediFileReg.gif"></caption>
			<tbody>
				<tr>
					<th>EDI파일</th>
					<td >
						<input type="file" id="ediFile" name="ediFile" value="" style="width:400px;height:20px;"/>&nbsp;&nbsp;&nbsp;<span class="button medium icon"><span class="check"></span><a id="createStatementCheck">청구서등록</a></span>
						<div>
							<div style=" float:left;padding-right: 10px;">파일전송 :</div> 
							<div id="progressbox" style=" float:left;">
								<div id="progressbar"></div>
								<div id="percent"></div>
							</div>
							<div class="clear"></div>
							<div style=" float:left;padding-right: 10px;">파일등록 :</div> 
							<div id="progressbox2" style=" float:left;">
								<div id="progressbar2"></div>
								<div id="percent2"></div>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
	</div>
		
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
					<input type="text" id="searchText" name="searchText" value="${recuperationCostAccountVo.searchText }" onKeypress="hitEnterKey(event);return;"/>
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
		<caption><img src="/resources/img/statementList.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${recuperationCostAccountVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.statementForm,'/statement/index.doo');"/></span></caption>
		<colgroup>
			<col width="4%">
			<col width="*">
			<col width="9%">
			<col width="9%">
			<col width="9%">
			<col width="9%">
			<col width="9%">
			<col width="9%">
			<col width="20%">
			<col width="5%">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck" name="allCheck"/></th>
				<th>청구명</th>
				<th>요양기관</th>
				<th>청구번호</th>
				<th>청구인ID</th>
				<th>청구인</th>
				<th>작성자성명</th>
				<th>청구일자</th>
				<th>상세보기</th>	
				<th>파일</th>	
			</tr>
		</thead>
		<tbody>
			<c:if test="${recuperationCostAccountList != null}">
				<c:forEach var="recuperationCostAccount" items="${recuperationCostAccountList}">
					<tr>
						<td><input type="checkbox" name="recpCstClmSeqArray" value="${recuperationCostAccount.recpCstClmSeq}"/></td>
						<td>${recuperationCostAccount.stsTitle}</td>
						<td>${recuperationCostAccount.hspId}</td>
						<td>${recuperationCostAccount.clmNum}</td>
						<td>${recuperationCostAccount.registerId }</td>
						<td>${recuperationCostAccount.clmNm}</td>
						<td>${recuperationCostAccount.wrtNm}</td>
						<td>${recuperationCostAccount.clmDt}</td>
						<td>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readStatement('${recuperationCostAccount.recpCstClmSeq}','${recuperationCostAccount.clmNum}','${recuperationCostAccount.hspId}');">상세보기</a></span>
							<!-- 
							 <span class="button medium icon"><span class="check"></span><a onclick="javascript:selectStatementResult('${recuperationCostAccount.recpCstClmSeq}','${recuperationCostAccount.clmNum}','${recuperationCostAccount.hspId}');">점검결과 조회</a></span>
							-->
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:selectRecuperationCostAccount('${recuperationCostAccount.recpCstClmSeq}','${recuperationCostAccount.clmNum}','${recuperationCostAccount.hspId}');">청구서 조회</a></span>
						</td>
						<td><c:if test="${recuperationCostAccount.fileYn != null }"><a href="javascript:download('${recuperationCostAccount.fileYn}');" ><img src="/resources/img/file.gif" /></a></c:if></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(recuperationCostAccountList) == 0}">
				<tr>
					<td colspan="10" align="center">등록된 청구서가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${fn:length(recuperationCostAccountList) == 0}">
				<tr>
					<td colspan="10" align="center">등록된 청구서가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot><tr><td colspan="10" align="right"><span class="button medium icon"><span class="check"></span><a id="deleteStatement">삭제</a></span> </td></tr></tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${recuperationCostAccountVo.pagingHtml}</div>
	
	<div id="popWrap" class="popWrap">
		<div class="popImg">
			<img src="/resources/img/edi_bar.png" /><img src="/resources/img/edi_mid_bar.png" /><img src="/resources/img/edi_close_bar.png" onclick="javascript:$.unblockUI();" style="cursor:pointer;">
		</div>
		<div class="main">
			 <div class="text" id="popCntnts">
			 	<input type="text" id="stsTitle" name="stsTitle" value="" size="10" style="width:300px; height:40px;"/>
			 </div>
			 <div style="padding-left: 100px;">
				<a id="createStatement" style="cursor:pointer;"></a>
			 </div>
		</div>
	</div>
</form>	
