<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateMedicalInsur").click(function(){ //치료대 수정 
		$("#medicalInsurForm").attr("action", "/medicalInsur/updateMedicalInsur.json");
		$("#medicalInsurForm").ajaxSubmit(updateMedicalInsurAjax);
	});
	
	$("#selectMedicalInsur").click(function(){ //치료대 리스트
		$("#medicalInsurForm").attr("action", "/medicalInsur/selectMedicalInsurList.doo");
		$("#medicalInsurForm").submit();
	});
});

// 게시물 수정Ajax
var updateMedicalInsurAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectMedicalInsur").click();
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
</script>


<form id="medicalInsurForm" name="medicalInsurForm" action="/medicalInsur/updateMedicalInsur.doo" method="post" >
	<!--검색 조건  -->
	<input type="hidden" value="${medicalInsurVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${medicalInsurVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${medicalInsurVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${medicalInsurVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${medicalInsurVo.currentPage}" id="currentPage" name="currentPage"/>
	<!--//검색 조건  -->

	<input type="hidden" id="medicalInsurCd"  name="medicalInsurCd" value="${medicalInsur.medicalInsurCd }"/>
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>수가코드</th>
				<td class="l">${medicalInsur.medicalInsurCd}</td>
			</tr>
			<tr>
				<th >적용일자</th>
				<td class="l"><input type="text" id="applyDt" name="applyDt" value="${medicalInsur.applyDt}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >분류번호</th>
				<td class="l"><input type="text" id="cdDivd" name="cdDivd" value="${medicalInsur.cdDivd}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >한글명</th>
				<td class="l"><input type="text" id="korNm" name="korNm" value="${medicalInsur.korNm}" size="100"   /></td>
			</tr>
			<tr>
				<th>영문명</th>
				<td class="l"><input type="text" id="engNm" name="engNm" value="${medicalInsur.engNm}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>구분</th>
				<td class="l">
					<select name="divCd" >
						<option value="" >선택</option>
						<option value="1" <c:if test="${medicalInsur.divCd eq '1'}"> selected="selected" </c:if> >1</option>
						<option value="2" <c:if test="${medicalInsur.divCd eq '2'}"> selected="selected" </c:if> >2</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>수술여부</th>
				<td class="l">
					<select name="operYn" >
						<option value="" >선택</option>
						<option value="0" <c:if test="${medicalInsur.operYn eq '0'}"> selected="selected" </c:if> >수술</option>
						<option value="9" <c:if test="${medicalInsur.operYn eq '9'}"> selected="selected" </c:if> >비수술</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>상대가치점수</th>
				<td class="l"><input type="text" id="relativeVal" name="relativeVal" value="${medicalInsur.relativeVal}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>본인부담률50/100</th>
				<td class="l">
					<select name="actDiv" >
						<option value="Y" <c:if test="${medicalInsur.self50Yn eq 'Y'}"> selected="selected" </c:if> >Y</option>
						<option value="N" <c:if test="${medicalInsur.self50Yn eq 'N'}"> selected="selected" </c:if> >N</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>본인부담률80/100</th>
				<td class="l">
					<select name="actDiv" >
						<option value="Y" <c:if test="${medicalInsur.self80Yn eq 'Y'}"> selected="selected" </c:if> >Y</option>
						<option value="N" <c:if test="${medicalInsur.self80Yn eq 'N'}"> selected="selected" </c:if> >N</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>중복인정여부</th>
				<td class="l">
					<select name="actDiv" >
						<option value="Y" <c:if test="${medicalInsur.duplicateYn eq 'Y'}"> selected="selected" </c:if> >Y</option>
						<option value="N" <c:if test="${medicalInsur.duplicateYn eq 'N'}"> selected="selected" </c:if> >N</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>산정코드</th>
				<td class="l"><input type="text" id="calculationCd" name="calculationCd" value="${medicalInsur.calculationCd}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>장구분</th>
				<td class="l"><input type="text" id="etc" name="etc" value="${medicalInsur.etc}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>행위구분</th>
				<td class="l"><select name="typeCd" >
								<option value="" >선택</option>
								<option value="A" <c:if test="${medicalInsur.typeCd eq 'A'}"> selected="selected" </c:if> >의과급여</option>
								<option value="B" <c:if test="${medicalInsur.typeCd eq 'B'}"> selected="selected" </c:if> >의과(비급여)</option>
							</select></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateMedicalInsur">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectMedicalInsur">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
</form>