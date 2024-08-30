<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		$("#createDrugMain").click(function(){ //게시판 등록 
			$("#drugMainForm").attr("action", "/drugMain/createDrugMain.doo");
			$("#drugMainForm").submit();
		});

		$("#selectDrugMainList").click(function(){ //게시판 목록 이동
			$("#drugMainForm").attr("action", "/drugMain/selectDrugMainList.doo");
			$("#drugMainForm").submit();
		});
	});
	
</script>


<form id="drugMainForm" name="drugMainForm" action="" method="post" >
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${drugMainVo.searchText}"/>
	<input type="hidden" name="searchType" value="${drugMainVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${drugMainVo.currentPage}"/>
	<!--//검색 조건  -->
		<input type="hidden" name="mainDrugYn" value="Y"/>
	<table class="table5" >
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>주요성분코드</th>
				<td class="l"><input type="text" id="mainDrugCd" name="mainDrugCd" value="" size="15"  maxlength="9" /></td>
			</tr>
			<tr>
				<th >주요성분명</th>
				<td class="l">
					<div><font color="red">(구분자 ∬)</font>&nbsp;&nbsp;예)주성분명1 <font color="red">∬</font>주성분명2 <font color="red">∬</font></div>
					<textarea id="mainDrugNm" name="mainDrugNm" cols="100" rows="20"  >
					</textarea>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="createDrugMain">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrugMainList">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>	
