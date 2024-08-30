<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#createMedicalInsur").click(function(){ //게시판 등록 
			$("#medicalInsurForm").attr("action", "/medicalInsur/createMedicalInsur.doo");
			$("#medicalInsurForm").submit();
		});

		$("#selectMedicalInsur").click(function(){ //게시판 목록 이동
			$("#medicalInsurForm").attr("action", "/medicalInsur/selectMedicalInsurList.doo");
			$("#medicalInsurForm").submit();
		});
		
	});
</script>


<form id="medicalInsurForm" name="medicalInsurForm" action="/medicalInsur/selectMedicalInsurList.doo" method="post" >	
	<!--검색 조건  -->
	<input type="hidden" value="${medicalInsurVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${medicalInsurVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${medicalInsurVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${medicalInsurVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${medicalInsurVo.currentPage}" id="currentPage" name="currentPage"/>
	<!--//검색 조건  -->
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>수가코드</th>
				<td class="l"><input type="text" id="medicalInsurCd" name="medicalInsurCd" value="${medicalInsur.medicalInsurCd}" size="100"   /></td>
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
						<option value="1" <c:if test="${medicalInsur.divCd eq '1'}"> selected="selected" </c:if> >1</option>
						<option value="2" <c:if test="${medicalInsur.divCd eq '2'}"> selected="selected" </c:if> >2</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>수술여부</th>
				<td class="l">
					<select name="operYn" >
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
								<option value="B" <c:if test="${medicalInsur.typeCd eq 'B'}"> selected="selected" </c:if> >의과100-100</option>
								<option value="C" <c:if test="${medicalInsur.typeCd eq 'C'}"> selected="selected" </c:if> >의과(비급여)</option>
								<option value="C" <c:if test="${medicalInsur.typeCd eq 'D'}"> selected="selected" </c:if> >산재</option>
								<option value="C" <c:if test="${medicalInsur.typeCd eq 'E'}"> selected="selected" </c:if> >자동차보험</option>
							</select></td>
			</tr>
			<tr>
				<th rowspan	="2">심사기준</th>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="createMedicalInsur">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectMedicalInsur">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>