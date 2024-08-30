<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#createDisea").click(function(){ // 약품 등록 
		$("#drugForm").attr("action", "/disea/createDisea.doo");
		$("#drugForm").submit();
	});
	
	$("#selectDisea").click(function(){ // 목록 이동
		$("#drugForm").attr("action", "/disea/selectDiseaList.doo");
		$("#drugForm").submit();

	});
});

</script>


<form id="drugForm" name="drugForm" action="/disea/createDisea.doo" method="post" >	
	<!--검색 조건  -->
	<input type="hidden" value="${diseaVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${diseaVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${diseaVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${diseaVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${diseaVo.currentPage}" id="currentPage" name="currentPage"/>
	<!--//검색 조건  -->
	
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>질병분류코드</th>
				<td class="l"><input type="text" id="diseaCd" name="diseaCd" value="${disea.diseaCd}"/></td>
			</tr>
			<tr>
				<th>분류기준</th>
				<td class="l">
				<select name="diseaDiv">
					<option value="" >선택</option>
					<option value="C1" <c:if test="${disea.diseaDiv eq 'C1'}"> selected="selected" </c:if> >소</option>
					<option value="C2" <c:if test="${disea.diseaDiv eq 'C2'}"> selected="selected" </c:if> >세</option>
					<option value="C3" <c:if test="${disea.diseaDiv eq 'C3'}"> selected="selected" </c:if> >세세</option>
					<option value="C4" <c:if test="${disea.diseaDiv eq 'C4'}"> selected="selected" </c:if> >세세세</option>
					<option value="C5" <c:if test="${disea.diseaDiv eq 'C5'}"> selected="selected" </c:if> >없음</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>표제어</th>
				<td class="l">
					<select name="diseaHw">
						<option value="" >선택</option>
						<option value="A" <c:if test="${disea.diseaHw eq 'A'}"> selected="selected" </c:if> >1</option>
						<option value="B" <c:if test="${disea.diseaHw eq 'B'}"> selected="selected" </c:if> >2</option>
						<option value="C" <c:if test="${disea.diseaHw eq 'C'}"> selected="selected" </c:if> >없음</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >질병한글명</th>
				<td class="l"><input type="text" id="diseaKorNm" name="diseaKorNm" value="${disea.diseaKorNm}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >질병영문명</th>
				<td class="l"><input type="text" id="diseaEngNm" name="diseaEngNm" value="${disea.diseaEngNm}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >검별 *,+</th>
				<td class="l"><input type="text" id="etc1" name="etc1" value="${disea.etc1}" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>주석</th>
				<td class="l">
					<select name="etc2">
						<option value="" >선택</option>
						<option value="A" <c:if test="${disea.etc2 eq 'A'}"> selected="selected" </c:if> >주</option>
						<option value="B" <c:if test="${disea.etc2 eq 'B'}"> selected="selected" </c:if> >포함</option>
						<option value="C" <c:if test="${disea.etc2 eq 'C'}"> selected="selected" </c:if> >공백</option>
					</select>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="createDisea">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDisea">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
</form>	