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
				$("#medicalMappingForm").attr("action", "/disea/createMedicalMapping.json");
				$("#medicalMappingForm").ajaxSubmit(createMedicalMappingAjax);
			}
		}
	});
	
	//입력창 초기셋팅
	$("#initUpdateCustomer").click(function() {
		if($("#initUpdateCustomer").html() == '초기화') {
			$("#diseaCd",  "#medicalMappingForm").val("");
			$("#medicalInsurCd",  "#medicalMappingForm").val("");
			$("#initUpdateCustomer").html("수가코드 자동입력");
		}else {
			$("#medicalInsurCd",  "#medicalMappingForm").val($("#medicalInsurCd", "#diseaForm").val());
			$("#initUpdateCustomer").html("초기화");
		}
		
	});
	
	//인증상병 삭제
	$("#deleteMappingMedical").click(function() {
		if(!$("input[name=arrDel]").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
		var checkConfirm = confirm("정말로 삭제하시겠습니까?");
		if(checkConfirm) {
			$("#diseaForm").attr("action", "/disea/deleteMedicalMapping.json");
			$("#diseaForm").ajaxSubmit(deleteAjax);
			
		}
	});

});

//value검증
var validationCheck = function(){
	if(!blankCheck("#diseaCd",  "#medicalMappingForm", "상병코드는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#medicalInsurCd",  "#medicalMappingForm", "수가코드는 필수입력 사항입니다.")) { return false; }
	return true;
};

function diseaInfoPop(diseaCd){
	$("#diseaCd", "#diseaForm").val(diseaCd);
	pop('','disea','1000', '640');
	$("#diseaForm").attr("action", "/disea/selectDiseaPop.doo");
	$("#diseaForm").attr("target", "disea");
	$("#diseaForm").submit();
}

var createMedicalMappingAjax = {
	success : function(resultData) {
		if( resultData > 0) {
			alert(resultData + "건이 등록되었습니다.");
			pop('','medical','1000', '640');
			$("#diseaForm").attr("action", "/disea/selectMedicalInsurPop.doo");
			$("#diseaForm").attr("target", "medical");
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

//pop 검색 리스트
var popSearchList = function() {
	pop('','medical','1000', '840');
	$("#diseaForm").attr("target", "medical");
	$("#diseaForm").attr("action", "/disea/selectMedicalInsurPop.doo");
	$("#diseaForm").submit();
};
</script>

<!-- 인증상병 리스트 시작 -->
<form id="diseaForm" name="diseaForm" action="" method="post">
	<input type="hidden" id="diseaCd"  name="diseaCd" />
	<input type="hidden" id="medicalInsurCd"  name="medicalInsurCd" value="${medicalInsurVo.medicalInsurCd }" />
	<div style="background: #f8f8f8; height: 500px;" >
		<table>
			<colgroup>
				<col width="47px;" />
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
					<c:if test="${medicalMappingList != null}">
						<c:forEach var="disea" items="${medicalMappingList}" varStatus="status">
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
					<c:if test="${fn:length(medicalMappingList) == 0}">
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
<form id="medicalMappingForm" name="medicalMappingForm" action="" method="post">
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="50%">
			<col width="50%">
		</colgroup>
		<thead>
			<tr>
				<th>상병코드</th>
				<th>수가코드</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<textarea style="height:230px;" name="diseaCd" id="diseaCd"></textarea>
				</td>
				<td>
					<input type="text" id="medicalInsurCd" name="medicalInsurCd" value="${medicalInsurVo.medicalInsurCd }" maxlength="10"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<span id="btnMode4" ><span class="button medium icon"><span class="check"></span><a id="initUpdateCustomer">초기화</a></span></span>
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createCustomer">등록</a></span></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteMappingMedical">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
<!-- 인증상병 등록 끝 -->