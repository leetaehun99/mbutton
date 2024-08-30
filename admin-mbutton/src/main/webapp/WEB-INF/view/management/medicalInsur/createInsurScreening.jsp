<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		
		$("#createMedicalInsur").click(function(){ //게시판 등록 
			oEditors2.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#medicalInsurForm").attr("action", "/medicalInsur/createInsurScreening.doo");
			$("#medicalInsurForm").submit();
		});

		$("#selectMedicalInsur").click(function(){ //게시판 목록 이동
			$("#medicalInsurForm").attr("action", "/medicalInsur/selectInsurScreeningList.doo");
			$("#medicalInsurForm").submit();
		});
		
	});
</script>


<form id="medicalInsurForm" name="medicalInsurForm" action="/medicalInsur/selectInsurScreeningList.doo" method="post" >	
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
				<th>명칭</th>
				<td class="l"><input type="text" id="designation" name="designation" value="${medicalInsur.designation}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th >제목</th>
				<td class="l"><input type="text" id="title" name="title" value="${medicalInsur.title}" size="100"   /></td>
			</tr>
			<tr>
				<th >내용</th>
				<td class="l">
				<textarea id="content" name="content" style="height:200px;display:none;">${medicalInsur.content}</textarea>
				</td>
			</tr>
			<tr>
				<th>수술여부</th>
				<td class="l"><input type="text" id="surgery" name="surgery" value="${medicalInsur.surgery}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>급여구분</th>
				<td class="l"><input type="text" id="salary" name="salary" value="${medicalInsur.salary}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>분류번호</th>
				<td class="l"><input type="text" id="classification" name="classification" value="${medicalInsur.classification}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>I/I 구분</th>
				<td class="l"><input type="text" id="etc1" name="etc1" value="${medicalInsur.etc1}" size="100"  maxlength="300" /></td>
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
<script type="text/javascript">
var oEditors2 = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors2,
    elPlaceHolder: "content",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor23"
});
</script>
</form>