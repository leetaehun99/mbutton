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
				$("#diseaMappingForm").attr("action", "/disea/createDiseaMappingByDrugCd.json");
				$("#diseaMappingForm").ajaxSubmit(createDiseaMappingAjax);
				
			}
		}
	});
	
	//입력창 초기셋팅
	$("#initUpdateCustomer").click(function() {
		if($("#initUpdateCustomer").html() == '초기화') {
			$("#diseaCd",  "#diseaMappingForm").val("");
			$("#drugCd",  "#diseaMappingForm").val("");
			$("#initUpdateCustomer").html("상병코드 자동입력");
		}else {
			$("#diseaCd",  "#diseaMappingForm").val($("#diseaCd", "#drugForm").val());
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
			$("#drugForm").attr("action", "/disea/search.json");
			
			$("#drugForm").attr("action", "/disea/deleteDiseaMapping.json");
			$("#drugForm").ajaxSubmit(deleteAjax);
			
		}
	});
	
	/* if($("#listCnt").val() == 0) {
		$("#tb_subject").hide();	
	} */
});

//value검증
var validationCheck = function(){
	if(!blankCheck("#drugCd",  "#diseaMappingForm", "약품코드는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#diseaCd",  "#diseaMappingForm", "상병코드는 필수입력 사항입니다.")) { return false; }
	return true;
};

var drugInfoPop = function(drugCd){
	$("#drugCd").val(drugCd);
	pop('','drug','1000', '840');
	$("#drugForm").attr("action", "/disea/selectDrugPop.doo");
	$("#drugForm").attr("target", "drug");
	$("#drugForm").submit();
};

var createDiseaMappingAjax = {
	success : function(resultData) {
		if( resultData > 0) {
			alert(resultData + "건이 등록되었습니다.");
			pop('','disea','1000', '840');
			$("#drugForm").attr("target", "disea");
			$("#drugForm").attr("action", "/disea/selectDiseaPop.doo");
			$("#drugForm").submit();
		} else {
			if(resultData.length > 0) {
				var diseaList = "";
				diseaList += "약품코드(";
				for(var i=0; i<resultData.length; i++) {
					diseaList += resultData[i].drugCd;
					if(i < resultData.length -1 ) {
						diseaList += ", ";	
					}
				}
				diseaList += ")가 존재해서 추가 할 수 없습니다.";
				alert(diseaList);
			}else {
				alert("존재하지 않는 약품코드입니다.");	
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

//pop 검색 리스트
var popSearchList = function() {
	pop('','disea','1000', '840');
	$("#drugForm").attr("target", "disea");
	$("#drugForm").attr("action", "/disea/selectDiseaPop.doo");
	$("#drugForm").submit();
};
</script>

<!-- 인증약품 리스트 시작 -->
<form id="drugForm" name="drugForm" action="/drug/selectDrugList.doo"method="post">
	<input type="hidden" id="drugCd"  name="drugCd" />
	<input type="hidden" id="diseaCd" name="diseaCd" value="${diseaVo.diseaCd}"/>
	<input type="hidden" name="isAllDisea" value="N" />
	<div style="background: #f8f8f8; height: 500px;" >
		<table id="tb_subject">
			<caption><!-- img src="/resources/img/Notify2.gif" --></caption>
			<colgroup>
				<col width="47px;" />
				<col width="140px;" />
				<col width="400px;" />
				<col width="400px;" />
			</colgroup>
			<thead>
				<tr>
					<th><input type="checkbox" id="allDel" name="allDel"/></th>
					<th>순번</th>
					<th>약품코드</th>
					<th>약품명</th>
				</tr>
			</thead>
		</table>
		<div style="overflow:auto; height: 450px; " id="popDiseaContents">
			<table class="table5">
				<colgroup>
					<col width="50px;" />
					<col width="150px;" />
					<col width="400px;" />
					<col width="400px;" />
				</colgroup>
				<tbody id="drugListTbody">
					<c:if test="${drugMappingList != null}">
						<c:forEach var="drug" items="${drugMappingList}" varStatus="status">
							<tr >
								<td>
									<input type="checkBox" name="arrDel" class="arrDel" value="${drug.drugCd}" />
								</td>
								<td>${status.count }</td>
								<td><a onclick="javascript:drugInfoPop('${drug.drugCd}');">${drug.drugCd}</a></td>
								<td>${drug.drugNm}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${fn:length(drugMappingList) == 0}">
						<tr>
							<td colspan="3" align="center">등록된 게시물이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</form>	
<!-- 인증약품 리스트 시작 -->

<!-- 인증약품 등록 시작 -->
<form id="diseaMappingForm" name="diseaMappingForm" action="/drug/selectDrugList.doo" method="post">
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="50%">
			<col width="50%">
		</colgroup>
		<thead>
			<tr>
				<th>약품코드</th>
				<th>상병코드</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<textarea style="height:230px;" name="drugCd" id="drugCd"></textarea>
				</td>
				<td>
					<input type="text" id="diseaCd" name="diseaCd" value="${diseaVo.diseaCd}" maxlength="10"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<span id="btnMode4" ><span class="button medium icon"><span class="check"></span><a id="initUpdateCustomer">초기화</a></span></span>
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createCustomer">등록</a></span></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteMappingDisea">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
<!-- 인증약품 등록 끝 -->
	

