<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customTag" %>
<script type="text/javascript">

	//팝업리스트 enter key event
	function press(event) {
		if (event.keyCode==13) {
			$("#popSearchList").click();
		}
	}
	
	//건강보험차단 enter key event
	function mainPress(event) {
		if(event.keyCode==13) {
			searchList();
		}
	}
	
/************************ $(function(){}) 시작 ***********************************/		
	$(function() {
		
		//검색
		$("#sSearchList").click(function(){
			searchList();
		});
		
		//검색 초기화
		$("#initSearch").click(function(){
			initSearch();
		});
		
		//상병 등록
		$("#createCustomer").click(function() { 
			if(validationCheck()){
				if($("#isCreate").val() != "N") {
					document.customerForm.isCreate.value="Y";
				}
				$("#customerForm").attr("action", "/customer/createCustomer.json");
				$("#customerForm").ajaxSubmit(createCustomerAjax);	
			}
		});
		
		//상병 삭제
		$("#deleteCustomer").click(function() {
			$("#customerForm").attr("action", "/customer/deleteCustomer.json");
			$("#customerForm").ajaxSubmit(deleteCustomerAjax);
		});
		
		//팝업 조회
		$("#popSearchList").click(function(){				
			var check = $("#check").val();
			var strLength  = $("#searchText",  "#popCustomerForm").val();
			strLength = strLength.length;
			if(!blankCheck("#searchType",  "#popCustomerForm", "분류코드는 필수 선택 사항입니다.")) { return false; }
			if(check == 2) { 
				if(!blankCheck("#searchText",  "#popCustomerForm", "약품코드는 필수입력 사항입니다.")) { return false; }
				
				if($("#searchType", "#popCustomerForm").val().trim() == "DRUG_CD") {
					if(strLength<6) { alert("최소 6자리 이상 입력하셔야 합니다.");return false;}
				}else if($("#searchType", "#popCustomerForm").val().trim() == "DRUG_NM") {
					if(strLength<2) { alert("최소 2자리 이상 입력하셔야 합니다.");return false;}
				}
				$("#popCustomerForm").attr("action", "/customer/selectDrugCdList.json");	
			}else {
				if(!blankCheck("#searchText",  "#popCustomerForm", "질병분류코드는 필수입력 사항입니다.")) { return false; }
				if($("#searchType", "#popCustomerForm").val().trim() == 'DISEA_CD') {
					if(strLength<3) { alert("최소 3자리 이상 입력하셔야 합니다.");return false;}
				}else if($("#searchType", "#popCustomerForm").val().trim() == "DISEA_KOR_NM") {
					if(strLength<2) { alert("최소 2자리 이상 입력하셔야 합니다.");return false;}						
				}
				$("#popCustomerForm").attr("action", "/customer/selectDiseaCdList.json");
			}
			loadingPop();
			$("#popCustomerForm").ajaxSubmit(selectCheckDiseaMappingListAjax);
			
		}); 
		
		//팝업생성
		diseaListPopup = function(check){
			document.popCustomerForm.check.value = check;
			$("#tbDiseaMappingList").empty();
			$("#searchText","#popCustomerForm").val("");
			$.blockUI({message:$("#popCustomerFormContents"), css:{border: 'none',width:'514px;', left:'35%',top:'100px',backgroundColor:'none', cursor: 'default'}, overlayCSS : {opacity:0.5,border: 'none' }});
			if(check == 1 ) {
				//질병
				$("#popCheck").attr("src", "/resources/img/customer_disea_search.png");
				$('#selectSpan').html($('#s_00085').html()).show();
				$("#tdCd").html("질병분류코드");
				$("#tdNm").html("질 병 명");
			}else {
				//약품
				$("#popCheck").attr("src", "/resources/img/customer_drug_search.png");
				$('#selectSpan').html($('#s_00084').html()).show();
				$("#tdCd").html("약 품 코 드");
				$("#tdNm").html("약 품 명");
			}
			
		};
		
		//체크박스(전체체크)
		$("#allDel").on("click", function() {
			var chk = $(this).is(":checked");
			if(chk) {
				$("input[type=checkBox]").prop("checked", true);
			}else {
				$("input[type=checkBox]").prop("checked", false);
			}
		});
		
		//상세보기 초기화
		$("#btnMode4").click(function() {
			$("#diseaCd").attr("readonly", false);
			$("#drugCd").attr("readonly", false);
			document.customerForm.diseaCd.value = "";
			document.customerForm.drugCd.value = "";
			document.customerForm.mainDrugCd.value = "";
			document.customerForm.useYn.value = "";
			$("#diseaCd").css("background-color", "White");
			$("#drugCd").css("background-color", "White");
			$("#diseaCd").attr("onclick", "javascript:diseaListPopup(1);");
			$("#drugCd").attr("onclick", "javascript:diseaListPopup(2);");
			$("#createCustomer").html("등록");
			$("#btnMode4").css("display", "none");
		});
		
	});
/************************ $(function(){}) 끝 ***********************************/	
	
	//상병 수정
	function goUpdate(diseaCd, drugCd, mainDrugCd, useYn) {
		document.customerForm.diseaCd.value = diseaCd;
		document.customerForm.drugCd.value = drugCd;
		document.customerForm.mainDrugCd.value = mainDrugCd;
		document.customerForm.useYn.value = useYn;
		document.customerForm.isCreate.value = "N";
		$("#diseaCd").attr("readonly", true);
		$("#drugCd").attr("readonly", true);
		$("#diseaCd").css("background-color", "WhiteSmoke");
		$("#diseaCd").attr("onclick", "");
		$("#drugCd").css("background-color", "WhiteSmoke");
		$("#drugCd").attr("onclick", "");
		$("#createCustomer").html("수정");
		$("#btnMode4").css("display", "");
	}
		
	// 페이지 재검색
	function searchList() {
		$("#customerForm").attr("action", "/customer/selectManagerDiseaList.doo");
		$("#customerForm").submit();
	}
	
	//검색필드 초기화
	function initSearch(){
		$("#sSearchType").val("");
		$("#sSearchText").val("");
	}; 
	
	//value검증
	validationCheck= function(){
		if(!blankCheck("#diseaCd",  "#customerForm", "질병분류코드는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#mainDrugCd",  "#customerForm", "약품코드는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#useYn",  "#customerForm", "사용여부는 필수입력 사항입니다.")) { return false; }
		return true;
	};
	
	// 상병 등록Ajax
	var createCustomerAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				if($("#isCreate").val() == "Y") {
					alert("등록되었습니다.");
				}else if ($("#isCreate").val() == "N") {
					alert("수정되었습니다.");
				} 
				searchList();
			} else {
				alert("등록에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error :  function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);alert(xhr.responseText);
	        
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	// 상병 삭제Ajax
	var deleteCustomerAjax = {
		success : function(resultData) {
			if( resultData > 0 ) {
				alert("선택하신 " + resultData + "개의 데이터가 삭제되었습니다.");
				searchList();
			} else {
				alert("삭제에 실패하였습니다.");
			}
			$("#currentPage").val(1);
			searchList();
		},
		type : "post",
		dataType : "json",
		error :  function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);alert(xhr.responseText);
	        
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	// 상병 등록Ajax(팝업)
	var createCustomerAjaxPopup = {
		success : function(resultData) {
			if( resultData == 1 ) {
				window.open('','_self').close();
				alert("등록되었습니다.");
			} else {
				alert("등록에 실패하였습니다.");
			}
			
		},
		type : "post",
		dataType : "json",
		error : function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);alert(xhr.responseText);
		}
	};
	
	// 상병 리스트 Ajax
	var selectCheckDiseaMappingListAjax = {
		success : function(resultData) {
			if( resultData.length > 0 || resultData != null ) {
				$("#popDiseaContents").attr("style", "overflow:auto; height: 450px;");
				if($("#check").val() == 1) {
					checkDiseaMappingList(resultData);//상병정보
				} else {
					checkDrugMappingList(resultData);//약품정보
				}
			} else {
				alert("검색된 내용이 없습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	//스크롤이 생김으로 인한 테이블(width) 사이즈 재조정
	function scrollWidth(length) {
		var width = "";
		if(length > 12) {
			width = 107;	
		}else {
			width = 102;
		}
		return width;
	}
	
	//팝업 로딩중
	function loadingPop() {
		$("#popDiseaContents").attr("style", "");
		var sql = "";
		sql += "<tbody>";
		sql += "<tr>";
		sql += "<td style='width:405px;height:450px;text-align:center;'>";
		sql += "데이터를 읽어 들이고 있습니다. 잠시만 기다려주십시오.";
		sql += "</td>";
		sql += "</tr>";
		sql += "</tbody>";	
		$("#tbDiseaMappingList").html(sql);
	}
	
	//상병리스트 테이블 생성(팝업)
	function checkDiseaMappingList(resultData) {
		var width = scrollWidth(resultData.length);
		var sql = "";
		if(resultData.length > 0) {
			sql +=	"<tbody id='itemList'>";
			for(var i=0; i<resultData.length; i++) {
				resultText = resultData[i].diseaKorNm.substring(0,30).concat("...");
				sql +=	"<tr id='tr_search'>";
				sql +=	"<td style='width:"+ width +"px;text-align:center;padding-top:5px;padding-bottom:5px;cursor:pointer;' ";
				sql +=	"onclick='javascript:createCustomerPopup(\"" + resultData[i].diseaCd + " \");'";
				sql +=	"onmouseover='javascript:popupCheckColor(this.parentNode.rowIndex);'>";
				sql +=	resultData[i].diseaCd;
				sql +=	"</td>";
				sql +=	"<td style='width:290px; text-align:left; padding-left:10px;padding-top:5px;padding-bottom:5px;cursor:pointer;'  id='nm' name='nm' ";
				sql +=	"onclick='javascript:createCustomerPopup(\"" + resultData[i].diseaCd + " \");'";
				sql +=	"onmouseover='javascript:popupCheckColor(this.parentNode.rowIndex);'>";
				if(resultData[i].diseaKorNm.length > 30) {
					sql +=	resultText;
				}else {
					sql += resultData[i].diseaKorNm;
				} 
				sql +=	"</td>";
				sql +=	"</tr>";
			}
			sql +=	"</tbody>";
		}else {
			sql +=	"<tbody>";
			sql +=	"<tr>";
			sql +=	"<td style='text-align:center;'>";
			sql +=	"등록된 질병분류코드가 존재하지 않습니다";
			sql +=	"</td>";
			sql +=	"</tr>";
			sql +=	"</tbody>";
		}
		$("#tbDiseaMappingList").html(sql);
	}
	
	//약품리스트 테이블 생성(팝업)
	function checkDrugMappingList(resultData) {
		var width = scrollWidth(resultData.length);
		var sql = "";
		if(resultData.length > 0) {
			sql +=	"<tbody id='itemList' >";
			for(var i=0; i<resultData.length; i++) {
				resultText = resultData[i].drugNm.substring(0,30).concat("...");
				sql +=	"<tr id='list'>";
				sql +=	"<td style='width:"+ width +"px;text-align:center;padding-top:5px;padding-bottom:5px;' style='cursor:pointer;'";
				sql +=	"onclick='javascript:createCustomerPopup(null, \""  + resultData[i].drugCd + " \",\" " + resultData[i].mainDrugCd + "\");'";
				sql +=	"onmouseover='javascript:popupCheckColor(this.parentNode.rowIndex);'>";
				sql +=	resultData[i].drugCd;
				sql +=	"</td>";
				sql +=	"<td style='width:290px;text-align:left; padding-left:10px;padding-top:5px;padding-bottom:5px;' style='cursor:pointer;' id='nm' name='nm' ";
				sql +=	"onclick='javascript:createCustomerPopup(null, \""  + resultData[i].drugCd + " \",\" " + resultData[i].mainDrugCd + "\");'";
				sql +=	"onmouseover='javascript:popupCheckColor(this.parentNode.rowIndex);'>";
				if(resultData[i].drugNm.length > 30) {
					sql +=	resultText;
				}else {
					sql +=	resultData[i].drugNm;
				}
				sql +=	"</td>";
				sql +=	"</tr>";
			}
			sql +=	"</tbody>";
		}else {
			sql +=	"<tbody>";
			sql +=	"<tr>";
			sql +=	"<td style='text-align:center;'>";
			sql +=	"등록된 약품코드가 존재하지 않습니다";
			sql +=	"</td>";
			sql +=	"</tr>";
			sql +=	"</tbody>";
			
		}	
		$("#tbDiseaMappingList").html(sql);
	}

	//건강보험 차단 데이터 생성(팝업)
	function createCustomerPopup(diseaCd, drugCd, mainDrugCd) {
		document.customerForm.isCreate.value="Y";
		if($("#check").val() == 1) {
			document.customerForm.diseaCd.value = diseaCd;
		}else {
			document.customerForm.drugCd.value = drugCd;
			document.customerForm.mainDrugCd.value = mainDrugCd;
		}
		$.unblockUI();
	}

	//팝업 mouseover시 색상변환
	function popupCheckColor(rowId) {
		for(var i=0; i<$("#itemList tr").length; i++) {
			if(i==rowId) {
				$("#itemList tr").eq(i).css("background-color", "#99CCFF");
			}else {
				$("#itemList tr").eq(i).css("background-color", "");
			}
		}
	}
 	
</script>


<form id="customerForm" name="customerForm" action="" method="post" >

	<!-- 검색영역 시작 -->
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col width="35%" />
		<tbody>
			<tr>
				<th>검색어</th>
				<td>
					&nbsp;&nbsp;&nbsp;<ct:code name="sSearchType" type="select" groupCode="00083"  selectCode="${customerVo.sSearchType }" defaultCode="disea_cd"/>
					<input type="text" id="sSearchText" name="sSearchText" value="${customerVo.sSearchText }" onkeypress="javascript:mainPress(event);"/>
					<span class="button medium icon"><span class="check"></span><a id="sSearchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearch">초기화</a></span>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<!-- 검색영역 끝 -->

	<!-- 결과리스트 시작 -->
	<table class="table5">
		<caption><img src="/resources/img/customerList.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${customerVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.customerForm,'/customer/selectManagerDiseaList.doo');"/></span></caption>
		
		<colgroup>
			<col width="5%">
			<col width="18%">
			<col width="18%">
			<col width="18%">
			<col width="18%">
			<col width="13%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel" /></th>
				<th>질병분류코드</th>
				<th>약품코드</th>
				<th>주성분코드</th>
				<th>등록아이디</th>
				<th>사용여부</th>
				<th>상세보기</th>		
			</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(customerList) != null}">
				<c:forEach var="customerList" items="${customerList}">
					<tr>
						<td>
							<input type="checkBox" id="del" name="del" value="${customerList.diseaCd}_${customerList.drugCd}"/>
						</td>
						<td>${customerList.diseaCd}</td>
						<td>${customerList.drugCd}</td>
						<td>${customerList.mainDrugCd}</td>
						<td>${customerList.registerId}</td>
						<td>${customerList.useYn}</td>
						<td><span id="btnMode3"><span class="button medium icon"><span class="check"></span><a id="updateCustomer" onclick="javascript:goUpdate('${customerList.diseaCd}', '${customerList.drugCd}', '${customerList.mainDrugCd }', '${customerList.useYn }');">적용</a></span></span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(customerList) == 0}">
				<tr>
					<td colspan="7" align="center">등록된 주성분코드가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${customerVo.pagingHtml}</div>
	<!-- 결과리스트 끝 -->
	
	<!-- 등록 및 삭제 처리 시작 -->
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="33%">
			<col width="33%">
			<col width="33%">
		</colgroup>
		<thead>
			<tr>
				<th>질병분류코드</th>
				<th>약품코드</th>
				<th>사용여부</th>	
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<input type="text" id="diseaCd" name="diseaCd" value="ZZZ" maxlength="10"  style="cursor:pointer;"/>
				</td>
				<td>
					<input type="text" id="drugCd" name="drugCd" value="" maxlength="10" onclick="javascript:diseaListPopup(2);" style="cursor:pointer;"/>
					<input type="hidden" id="mainDrugCd" name="mainDrugCd" value="" maxlength="10"/>
				</td>
				<td><ct:code name="useYn" type="select" groupCode="00002"  defaultCode="Y" selectCode="Y" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createCustomer">등록</a></span></span>
					<input type="hidden" id="isCreate" name="isCreate" />
					<span id="btnMode4" style="display:none;"><span class="button medium icon"><span class="check"></span><a id="initUpdateCustomer">초기화</a></span></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="deleteCustomer">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 등록 및 삭제 처리 끝 -->
</form>

<!-- 레이어 팝업 시작 -->

<div id="popCustomerFormContents" style="display:none;cursor:default;" >
	<form id="popCustomerForm" name="popCustomerForm" action="" method="post"  onsubmit="return false;" >
		<input id="drugCd" name="drugCd" type ="hidden" value=""/>
		<input id="mainDrugCd" name="mainDrugCd" type ="hidden" value=""/>
		<input id="check" name="check" type="hidden" value="" />
		<div style="border: 0px; height: 37px;" >
			
			<img src="" id="popCheck" name="popCheck" /><img src="/resources/img/mid_bar.png" /><img src="/resources/img/close_bar.png" onclick="javascript:$.unblockUI();" style="cursor:pointer;">
		</div>
			<table class="table0" >
				<tr>
					<th> &nbsp;&nbsp;검색 : &nbsp;&nbsp;</th>
					<td>
						&nbsp;<span id="selectSpan">-</span>
						&nbsp;<input type="text" id="searchText" name=searchText value= "" onkeypress="javascript:press(event);return;"/>
						&nbsp;<span class="button medium icon"><span class="check"></span><a id="popSearchList">조회</a></span>
					</td> 
				</tr>
			</table>
		<!-- style="padding: 20px; -->
		<div style="background: #f8f8f8; height: 500px;" >
			<table class="table7 table7_main">
				<tbody>
					<tr>
						<td id="tdCd" style='width:100px; height:20px; text-align: center; background: #ecebeb; color: #2c2c2c; border: 1px solid #bebebe;'></td>
						<td id="tdNm" style='width:300px; height:20px; text-align: center; background: #ecebeb; color: #2c2c2c; border: 1px solid #bebebe;'></td>
					</tr>
				</tbody>
			</table>
			<div style="overflow:auto; height: 450px; " id="popDiseaContents">
				<table class="table7 table7_main" id="tbDiseaMappingList">
				</table>
			</div>
		</div>
	</form>
</div>
<!-- 레이어 팝업 끝 -->
 <!-- 약품 -->
<span id="s_00084" style="display:none;"><ct:code name="searchType" type="select" groupCode="00084"  selectCode="DRUG_CD"/></span>
  <!-- 질병 -->
<span id="s_00085" style="display:none;"><ct:code name="searchType" type="select" groupCode="00085"  selectCode="DISEA_CD"/></span>

