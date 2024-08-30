<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {

	//인증상병 등록
	$("#createCustomer").click(function() {
		if(validationCheck()) {
			var checkConfirm = confirm("정말로 등록하시겠습니까?");
			if(checkConfirm) {
				$("#diseaMappingForm").attr("action", "/disea/createDiseaMapping.json");
				$("#diseaMappingForm").ajaxSubmit(createDiseaMappingAjax);
			}
		}
	});
	
	//입력창 초기셋팅
	$("#initUpdateCustomer").click(function() {
		if($("#initUpdateCustomer").html() == '초기화') {
			$("#diseaCd",  "#diseaMappingForm").val("");
			$("#mainDrugCd",  "#diseaMappingForm").val("");
			$("#initUpdateCustomer").html("주성분코드 자동입력");
		}else {
			$("#mainDrugCd",  "#diseaMappingForm").val($("#mainDrugCd", "#diseaForm").val());
			$("#initUpdateCustomer").html("초기화");
		}
	});

	//인증상병 삭제
	$("#deleteMappingDisea").click(function() {
		if(!$("input[name=arrDel]").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
		var checkConfirm = confirm("정말로 삭제하시겠습니까?");
		if(checkConfirm) {
			$("#diseaForm").attr("action", "/disea/deleteDiseaMapping.json");
			$("#diseaForm").ajaxSubmit(deleteAjax);
			
		}
	});
});

//value검증
var validationCheck = function(){
	if(!blankCheck("#diseaCd",  "#diseaMappingForm", "상병코드는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#mainDrugCd",  "#diseaMappingForm", "주성분코드는 필수입력 사항입니다.")) { return false; }
	return true;
};

function diseaInfoPop(diseaCd){
	$("#diseaCd", "#diseaForm").val(diseaCd);
	pop('','disea','1000', '840');
	$("#diseaForm").attr("action", "/disea/selectDiseaPop.doo");
	$("#diseaForm").attr("target", "disea");
	$("#diseaForm").submit();
}

var createDiseaMappingAjax = {
	success : function(resultData) {
		if( resultData > 0) {
			alert(resultData + "건이 등록되었습니다.");
			pop('','drug','1000', '840');
			$("#diseaForm").attr("action", "/disea/selectDrugPop.doo");
			$("#diseaForm").attr("target", "drug");
			$("#diseaForm").submit();
		} else {
			if(resultData.length > 0) {
				var diseaList = "";
				diseaList += "상병코드(";
				for(var i=0; i<resultData.length; i++) {
					diseaList += resultData[i].diseaCd;
					if(i < resultData.length -1 ) {
						diseaList += ", ";	
					}
				}
				diseaList += ")가 존재해서 추가 할 수 없습니다.";
				alert(diseaList);
			}else {
				alert("존재하지 않는 상병코드입니다.");	
			}
			
		}
	},
	type : "post",
	dataType : "json",
	error :  function (xhr, ajaxOptions, thrownError) {
        alert(xhr.status);alert(xhr.responseText);
        
		alert("서버와의 통신에 실패 하였습니다.");
	}

}; 

var popSearchList = function() {
	pop('','drug','1000', '840');
	$("#diseaForm").attr("target", "drug");
	$("#diseaForm").attr("action", "/disea/selectDrugPop.doo");
	$("#diseaForm").submit();
};
</script>

<!-- 인증상병 리스트 시작 -->
<form id="diseaForm" name="diseaForm" action="/drug/selectDrugList.doo"method="post">
	<input type="hidden" id="diseaCd"  name="diseaCd" />
	<input type="hidden" id="drugCd"  name="drugCd" value="${drugVo.drugCd }" />
	<input type="hidden" id="mainDrugCd"  name="mainDrugCd" value="${drugVo.mainDrugCd }" />
	<input type="hidden" id="listCnt" value="${fn:length(diseaMappingList)}" />
	<input type="hidden" name="isAllDisea" value="Y" />
	<div style="background: #f8f8f8; height: 500px;" >
		<table>
			<colgroup>
				<col width="45px;" />
				<col width="50px;" />
				<col width="100px;" />
				<col width="400px;" />
				<col width="400px;" />
			</colgroup>
			<thead>
				<tr>
					<th><input type="checkbox" id="allDel" name="allDel"/></th>
					<th>순번</th>
					<th>상병코드</th>
					<th>상병명</th>
					<th>상병영문명</th>
				</tr>
			</thead>
		</table>
		<div style="overflow:auto; height: 450px; " id="popDiseaContents">
			<table class="table5">
				<caption><!-- img src="/resources/img/Notify2.gif" --></caption>
				<colgroup>
					<col width="50px;" />
					<col width="50px;" />
					<col width="100px;" />
					<col width="400px;" />
					<col width="400px;" />
				</colgroup>
				<tbody id="drugListTbody">
					<c:if test="${diseaMappingList != null}">
						<c:forEach var="disea" items="${diseaMappingList}" varStatus="status">
							<tr >
								<td>
									<input type="checkBox" name="arrDel" class="arrDel" value="${disea.diseaCd}" />
								</td>
								<td>${status.count }</td>
								<td style="cursor:pointer;"><a onclick="javascript:diseaInfoPop('${disea.diseaCd}');">${disea.diseaCd}</a></td>
								<td>${disea.diseaKorNm}</td>
								<td>${disea.diseaEngNm}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${fn:length(diseaMappingList) == 0}">
						<tr>
							<td colspan="9" align="center">등록된 게시물이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</form>	
<!-- 인증상병 리스트 끝 -->

<!-- 인증상병 등록 시작 -->
<form id="diseaMappingForm" name="diseaMappingForm" action="/drug/selectDrugList.doo" method="post">
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="50%">
			<col width="50%">
		</colgroup>
		<thead>
			<tr>
				<th>상병코드</th>
				<th>주성분코드</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<textarea style="height:230px;" name="diseaCd" id="diseaCd"></textarea>
				</td>
				<td>
					<input type="text" id="mainDrugCd" name="mainDrugCd" value="${drugVo.mainDrugCd }" maxlength="10"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createCustomer">등록</a></span></span>
					<span id="btnMode4" ><span class="button medium icon"><span class="check"></span><a id="initUpdateCustomer">초기화</a></span></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteMappingDisea">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
<!-- 인증상병 등록 끝 -->