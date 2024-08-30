<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#createTrtNotify").click(function(){ //치료대 등록 
			 oEditors1.getById["notifyContents"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#notifyForm").attr("action", "/trtNotify/createTrtNotify.doo");
			$("#notifyForm").submit();
		});

		$("#selectTrtNotify").click(function(){ //치료대 목록 이동
			$("#notifyForm").attr("action", "/trtNotify/selectTrtNotifyList.doo");
			$("#notifyForm").submit();
		});
		
	});
</script>


<form id="notifyForm" name="notifyForm" action="/trtNotify/selectTrtNotifyList.doo" method="post" >	
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${trtNotifyVo.searchText}"/>
	<input type="hidden" name="searchType" value="${trtNotifyVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${trtNotifyVo.currentPage}"/>
	<!--//검색 조건  -->
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>분류번호</th>
				<td class="l"><input type="text" id="trtNotify" name="trtNotify" value="" size="10"  maxlength="10" /></td>
			</tr>
			<tr>
				<th>분류서브번호</th>
				<td class="l"><input type="text" id="trtNotifySub" name="trtNotifySub" value="" size="5"  maxlength="3" /></td>
			</tr>
			<tr>
				<th >치료대명</th>
				<td class="l"><input type="text" id="notifyNm" name="notifyNm" value="" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >치료대코드</th>
				<td class="l"><input type="text" id="notifyMainNm" name="notifyMainNm" value="" size="100"  maxlength="250" /></td>
			</tr>
			<tr>
				<th>메세지</th>
				<td class="l"><input type="text" id="notifyMsg" name="notifyMsg" value="" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th rowspan	="2">관련근거</th>
			</tr>
			<tr>
				<td class="l"> <textarea id="notifyContents" name="notifyContents" style="height:200px;display:none;"></textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="createTrtNotify">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectTrtNotify">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors1 = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors1,
    elPlaceHolder: "notifyContents",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor21"
});
</script>
</form>	
