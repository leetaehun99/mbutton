<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateTrt").click(function(){ //치료대 수정 
		$("#trtForm").attr("action", "/trt/updateTrt.json");
		$("#trtForm").ajaxSubmit(updateTrtAjax);
	});
	
	$("#selectTrt").click(function(){ //치료대 리스트
		$("#trtForm").attr("action", "/trt/selectTrtList.doo");
		$("#trtForm").submit();
	});
});

// 게시물 수정Ajax
var updateTrtAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectTrt").click();
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


<form id="trtForm" name="trtForm" action="/trt/updateTrt.doo" method="post" >
	<!--검색 조건  -->
	<input type="hidden" value="${trtVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${trtVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${trtVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${trtVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${trtVo.currentPage}" id="currentPage" name="currentPage"/>
	<!--//검색 조건  -->

	<input type="hidden" id="trtCd"  name="trtCd" value="${trt.trtCd }"/>
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>치료대코드</th>
				<td class="l">${trt.trtCd}</td>
			</tr>
			<tr>
				<th >품명</th>
				<td class="l"><input type="text" id="trtNm" name="trtNm" value="${trt.trtNm}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >규격</th>
				<td class="l"><input type="text" id="trtStand" name="trtStand" value="${trt.trtStand}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/></td>
			</tr>
			<tr>
				<th >단위</th>
				<td class="l"><input type="text" id="trtUnit" name="trtUnit" value="${trt.trtUnit}" size="100"   /></td>
			</tr>
			<tr>
				<th>제조회사</th>
				<td class="l"><input type="text" id="trtCompany" name="trtCompany" value="${trt.trtCompany}" size="100"  maxlength="300" /></td>
			</tr>	
			<tr>
				<th>재질</th>
				<td class="l"><input type="text" id="trtMainCd" name="trtMainCd" value="${trt.trtMainCd}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>수입판매업소</th>
				<td class="l"><input type="text" id="trtSeller" name="trtSeller" value="${trt.trtSeller}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>상한가(VAT 포함)</th>
				<td class="l"><input type="text" id="trtLimitCost" name="trtLimitCost" value="${trt.trtLimitCost}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>-</th>
				<td class="l"><select name="trtDivd" >
								<option value="" >선택</option>
								<option value="A" <c:if test="${trt.trtDivd eq 'A'}"> selected="selected" </c:if> >본인일부부담</option>
								<option value="B" <c:if test="${trt.trtDivd eq 'B'}"> selected="selected" </c:if> >비급여</option>
								<option value="C" <c:if test="${trt.trtDivd eq 'C'}"> selected="selected" </c:if> >비급여(인체조직)</option>
								<option value="D" <c:if test="${trt.trtDivd eq 'D'}"> selected="selected" </c:if> >정액수가</option>
								<option value="E" <c:if test="${trt.trtDivd eq 'E'}"> selected="selected" </c:if> >00/100 정액수가</option>
							</select></td>
			</tr>
			<tr>
				<th>적용일자</th>
				<td class="l"><input type="text" id="etc1" name="etc1" value="${trt.etc1}" size="100"  maxlength="300" /></td>
			</tr>
			<tr>
				<th>비고</th>
				<td class="l"><input type="text" id="etc2" name="etc2" value="${trt.etc2}" size="100"  maxlength="300" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateTrt">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectTrt">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
</form>