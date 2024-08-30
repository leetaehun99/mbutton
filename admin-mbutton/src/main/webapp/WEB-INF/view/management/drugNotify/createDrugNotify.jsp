<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#createDrugNotify").click(function(){ //고시 등록 
			 oEditors1.getById["drugNotifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#drugNotifyForm").attr("action", "/drugNotify/createDrugNotify.doo");
			$("#drugNotifyForm").submit();
		});

		$("#selectDrugNotify").click(function(){ //고시 목록 이동
			$("#drugNotifyForm").attr("action", "/drugNotify/selectDrugNotifyList.doo");
			$("#drugNotifyForm").submit();
		});
		
	});
</script>


<form id="drugNotifyForm" name="drugNotifyForm" action="/drugNotify/selectDrugNotifyList.doo" method="post" >	
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${drugNotifyVo.searchText}"/>
	<input type="hidden" name="searchType" value="${drugNotifyVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${drugNotifyVo.currentPage}"/>
	<input type="hidden" name="rowPerPage" value="${drugNotifyVo.rowPerPage}"/>
	<!--//검색 조건  -->
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>고시번호</th>
				<td class="l"><input type="text" id="drugNotify" name="drugNotify" value="" size="10"  maxlength="10" /></td>
			</tr>
			<tr>
				<th>고시서브번호</th>
				<td class="l"><input type="text" id="drugNotifySub" name="drugNotifySub" value="" size="5"  maxlength="3" /></td>
			</tr>
			<tr>
				<th >고시명</th>
				<td class="l"><input type="text" id="drugNotifyNm" name="drugNotifyNm" value="" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >재제명</th>
				<td class="l"><input type="text" id="drugNotifyMainNm" name="drugNotifyMainNm" value="" size="100"  maxlength="250" /></td>
			</tr>
			<tr>
				<th >품명</th>
				<td class="l"><input type="text" id="drugNotifyItem" name="drugNotifyItem" value="" size="100"  maxlength="250" /></td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l"><input type="text" id="drugNotifyMsg" name="drugNotifyMsg" value="" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th rowspan	="2">고시</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="drugNotifyContents" name="drugNotifyContents" style="height:200px;display:none;"></textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="createDrugNotify">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrugNotify">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors1 = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors1,
    elPlaceHolder: "drugNotifyContents",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor21"
});
</script>
</form>	
