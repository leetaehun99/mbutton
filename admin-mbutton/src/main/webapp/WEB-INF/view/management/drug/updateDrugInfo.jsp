<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
var insertDisea;
$(function() {
	
	//calendar("#standard");
	//calendar("#mainDrugNm");
	
	insertDisea = new Array();
	
	$("#updateDrug").click(function(){ // 약품 수정
		 oEditors1.getById["drugEfficacy"].exec("UPDATE_CONTENTS_FIELD", []);
		 oEditors2.getById["drugDosage"].exec("UPDATE_CONTENTS_FIELD", []);
		 oEditors3.getById["drugTaboo"].exec("UPDATE_CONTENTS_FIELD", []);
		 oEditors4.getById["drugNote"].exec("UPDATE_CONTENTS_FIELD", []);
		 oEditors5.getById["drugSideEffect"].exec("UPDATE_CONTENTS_FIELD", []);
		 oEditors6.getById["drugInteraction"].exec("UPDATE_CONTENTS_FIELD", []);
		 
		$("#drugForm").attr("action", "/drug/updateDrug.json");
		$("#drugForm").ajaxSubmit(updateDrugAjax);
	});
	
	$("#selectDrug").click(function(){ // 목록
		$("#drugForm").attr("action", "/drug/selectDrugList.doo");
		$("#drugForm").submit();
	});
	
	$("#insertSearch").click(function(){
		var insertDisea = $("#insertDisea").val();
		
		alert(insertDisea)
		if (insertDisea.length >= 2) {
			
			oEditors1.getById["drugEfficacy"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors2.getById["drugDosage"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors3.getById["drugTaboo"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors4.getById["drugNote"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors5.getById["drugSideEffect"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors6.getById["drugInteraction"].exec("UPDATE_CONTENTS_FIELD", []);
			
			$("#searchText").val($("#insertDisea").val());

			$("#drugForm").attr("action", "/disea/selectDiseaList.json");
			$("#drugForm").ajaxSubmit(updateDrugAjax);
		} else {
			alert("두글자 이상을 검색해주시기 바랍니다.");
		}
			
	});
	
	// 체크 박스 모두 체크
	$("#checkAllL").click(function() {
		if ($("#checkLeft").val() == 'N') {
			$("input[name=deleteDisea]:checkbox").each(function() {
				$(this).prop("checked", true);
			});
			$("#checkLeft").val("Y");
		} else {
			$("input[name=deleteDisea]:checkbox").each(function() {
				$(this).prop("checked", false);
			});	
			$("#checkLeft").val("N");
		}
		
	});
});

// 게시물 수정Ajax
var updateDrugAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#drugForm").attr("action", "/drug/selectDrugInfo.doo");
			$("#drugForm").submit();
		} else {
			alert("등록에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

//게시물 수정Ajax
var selectDiseaAjax = {
	success : function(resultData) {
		var html = "";
		$("#resultListTbody").html(resultData);
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

var insertDisea = function(){
	alert("등록");
};

var deleteDisea = function(){
	$("input[name=deleteDisea]:checked").each(function() {
		var disea = $(this).val();
		if ($("#deleteDiseaList").val() == '') {
			$("#deleteDiseaList").val(disea);			
		} else {
			$("#deleteDiseaList").val($("#deleteDiseaList").val()+","+disea);	
		}
		$(this).parent().parent().hide();
	});
};

</script>


<form id="drugForm" name="drugForm" action="/drug/updateDrug.doo" method="post" >
	<input type="hidden" value="${drug.drugCd}" id="drugCd" name="drugCd"/>

	<input type="hidden" value="${drugVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${drugVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${drugVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${drugVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${drugVo.currentPage}" id="currentPage" name="currentPage"/>
	
	<input type="hidden" value="${drug.applyDt}" id="applyDt" name="applyDt"/>
	
	<input type="hidden" id="insertDiseaList" name="insertDiseaList"/>
	<input type="hidden" id="deleteDiseaList" name="deleteDiseaList"/>
	
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<th>약품코드</th>
				<td class="l" colspan="3">${drug.drugCd}</td>
			</tr>
			<tr>
				<th>적용일자</th>
				<td class="l" colspan="3">${drug.applyDt}</td>
			</tr>
			
			<tr>
				<th>급여구분</th>
				<td class="l" colspan="3">
				<select name="parDiv">
					<option value="" >선택</option>
					<option value="A" <c:if test="${drug.parDiv eq 'A'}"> selected="selected" </c:if> >급여</option>
					<option value="B" <c:if test="${drug.parDiv eq 'B'}"> selected="selected" </c:if> >급여정지</option>
					<option value="C" <c:if test="${drug.parDiv eq 'C'}"> selected="selected" </c:if> >보훈급여</option>
					<option value="D" <c:if test="${drug.parDiv eq 'D'}"> selected="selected" </c:if> >삭제</option>
					<option value="E" <c:if test="${drug.parDiv eq 'E'}"> selected="selected" </c:if> >산정불가</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>사용장려비</th>
				<td class="l" colspan="3"><input type="text" id="usePay" name="usePay" value="${drug.usePay}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>투여경로</th>
				<td class="l" colspan="3">
					<select name="injectPass">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.injectPass eq 'A'}"> selected="selected" </c:if> >기타</option>
						<option value="B" <c:if test="${drug.injectPass eq 'B'}"> selected="selected" </c:if> >내복</option>
						<option value="C" <c:if test="${drug.injectPass eq 'C'}"> selected="selected" </c:if> >외용</option>
						<option value="D" <c:if test="${drug.injectPass eq 'D'}"> selected="selected" </c:if> >주사</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>품명</th>
				<td class="l" colspan="3"><input type="text" id="drugNm" name="drugNm" value="${drug.drugNm}" size="100"  maxlength="250" /></td>
			</tr>
			<tr>
				<th>규격</th>
				<td class="l" colspan="3"><input type="text" id="standard" name="standard" value="${drug.standard}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >단위</th>
				<td class="l" colspan="3"><input type="text" id="unit" name="unit" value="${drug.unit}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>상한가</th>
				<td class="l" colspan="3"><input type="text" id="limitCost" name="limitCost" value="${drug.limitCost}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>회사</th>
				<td class="l" colspan="3"><input type="text" id="company" name="company" value="${drug.company}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>분류번호</th>
				<td class="l" colspan="3"><input type="text" id="drugNotify" name="drugNotify" value="${drug.drugNotify}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>주성분코드</th>
				<td class="l" colspan="3"><input type="text" id="mainDrugCd" name="mainDrugCd" value="${drug.mainDrugCd}" size="100"  maxlength="9" /></td>
			</tr>
			<tr>
				<th >전문/일반</th>
				<td class="l" colspan="3">
					<select name="speGen">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.speGen eq 'A'}"> selected="selected" </c:if> >전문의약품</option>
						<option value="B" <c:if test="${drug.speGen eq 'B'}"> selected="selected" </c:if> >일반의약품</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >퇴장방지구분</th>
				<td class="l" colspan="3">
					<select name="exitCd">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.exitCd eq 'A'}"> selected="selected" </c:if> >사용장려</option>
						<option value="B" <c:if test="${drug.exitCd eq 'B'}"> selected="selected" </c:if> >원가+장려</option>
						<option value="C" <c:if test="${drug.exitCd eq 'C'}"> selected="selected" </c:if> >원가보존</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >약품동등구분</th>
				<td class="l" colspan="3">
					<select name="drugDivd">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.drugDivd eq 'A'}"> selected="selected" </c:if> >생동(사후통보)</option>
						<option value="B" <c:if test="${drug.drugDivd eq 'B'}"> selected="selected" </c:if> >의약품동등</option>
						<option value="C" <c:if test="${drug.drugDivd eq 'C'}"> selected="selected" </c:if> >필드값없음</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >저가약대체여부</th>
				<td class="l" colspan="3">
					<select name="drugReplaceYn">
						<option value="" >선택</option>
						<option value="Y" <c:if test="${drug.drugReplaceYn eq 'A'}"> selected="selected" </c:if> >예</option>
						<option value="N" <c:if test="${drug.drugReplaceYn eq 'B'}"> selected="selected" </c:if> >아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >예외의약구분</th>
				<td class="l" colspan="3"><input type="text" id="specificDivd" name="specificDivd" value="${drug.specificDivd}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >임의조제불가</th>
				<td class="l" colspan="3">
					<select name="medicineDivd">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.medicineDivd eq 'A'}"> selected="selected" </c:if> >마약</option>
						<option value="B" <c:if test="${drug.medicineDivd eq 'B'}"> selected="selected" </c:if> >오남용 의약품</option>
						<option value="C" <c:if test="${drug.medicineDivd eq 'C'}"> selected="selected" </c:if> >한외마약</option>
						<option value="D" <c:if test="${drug.medicineDivd eq 'C'}"> selected="selected" </c:if> >향정신성의약품</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >고시일자</th>
				<td class="l" colspan="3"><input type="text" id="notifyDt" name="notifyDt" value="${drug.notifyDt}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >대응코드</th>
				<td class="l" colspan="3"><input type="text" id="respondCd" name="respondCd" value="${drug.respondCd}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >희귀의약품구분</th>
				<td class="l" colspan="3">
					<select name="rareYn">
						<option value="" >선택</option>
						<option value="Y" <c:if test="${drug.rareYn eq 'A'}"> selected="selected" </c:if> >예</option>
						<option value="N" <c:if test="${drug.rareYn eq 'B'}"> selected="selected" </c:if> >아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >판매예정일</th>
				<td class="l" colspan="3"><input type="text" id="sellDt" name="sellDt" value="${drug.sellDt}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<c:set var="mainDrugNms" value="${fn:replace(fn:trim(drug.mainDrugNm), '∬', '</BR>')}" />
				<th >주성분영문명</th>
				<td class="l" colspan="3"> ${mainDrugNms}</td>
			</tr>
			<tr>
				<th >기타</th>
				<td class="l" colspan="3"><input type="text" id="ext1" name="ext1" value="${drug.ext1}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th colspan	="2">효능효과</th>
				<th colspan	="2">용법용량</th>
			</tr>
			<tr>
				<td colspan	="2" class="l"> <textarea id="drugEfficacy" name="drugEfficacy" style="height:200px;display:none;">${drug.drugEfficacy}</textarea></td>
				<td colspan	="2" class="l"><textarea id="drugDosage" name="drugDosage" style="height:200px;display:none;">${drug.drugDosage}</textarea></td>
			</tr>
			<tr>
				<th colspan	="2">금기</th>
				<th colspan	="2">주의</th>
			</tr>
			<tr>
				<td colspan	="2" class="l"><textarea id="drugTaboo" name="drugTaboo" style="height:200px;display:none;">${drug.drugTaboo}</textarea></td>
				<td colspan	="2" class="l"><textarea id="drugNote" name="drugNote" style="height:200px;display:none;">${drug.drugNote}</textarea></td>
			</tr>
			<tr>
				<th colspan	="2">부작용</th>
				<th colspan	="2">상호작용</th>
			</tr>
			<tr>
				<td class="l" colspan ="2" ><textarea id="drugSideEffect" name="drugSideEffect" style="height:200px;display:none;">${drug.drugSideEffect}</textarea></td>
				<td class="l" colspan ="2" ><textarea id="drugInteraction" name="drugInteraction" style="height:200px;display:none;">${drug.drugInteraction}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateDrug">수정</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrug">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
	<script type="text/javascript">
	var oEditors1 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors1,
	    elPlaceHolder: "drugEfficacy",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor21"
	});
	
	var oEditors2 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors2,
	    elPlaceHolder: "drugDosage",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors3 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors3,
	    elPlaceHolder: "drugTaboo",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors4 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors4,
	    elPlaceHolder: "drugNote",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors5 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors5,
	    elPlaceHolder: "drugSideEffect",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors6 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors6,
	    elPlaceHolder: "drugInteraction",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
</script>
</form>	