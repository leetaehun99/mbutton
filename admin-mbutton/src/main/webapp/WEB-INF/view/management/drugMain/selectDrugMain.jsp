<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		$("#updateDrugMain").click(function(){ //게시물 수정 
			
			$("#drugMainForm").attr("action", "/drugMain/updateDrugMain.json");
			$("#drugMainForm").ajaxSubmit(updateDrugMainAjax);
		
		});
		
		
		$("#selectDrugMainList").click(function(){ //게시판 목록 이동
			$("#drugMainForm").attr("action", "/drugMain/selectDrugMainList.doo");
			$("#drugMainForm").submit();
		});
	});
	
	
	// 게시물 수정Ajax
	var updateDrugMainAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				$("#selectDrugMain").click();
			} else {
				alert("등록에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	
</script>


<form id="drugMainForm" name="drugMainForm" action="" method="post" >
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${drugMainVo.searchText}"/>
	<input type="hidden" name="searchType" value="${drugMainVo.searchType}"/>
	<input type="hidden" name="currentPage" value="${drugMainVo.currentPage}"/>
	<!--//검색 조건  -->
	<input type="hidden" name="mainDrugCd" value="${drugMain.mainDrugCd}"/>
	
	<table class="table5" >
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>주요성분코드</th>
				<td class="l">${drugMain.mainDrugCd}</td>
			</tr>
			<tr>
				<th >주요성분명</th>
				<td class="l">
					<div><font color="red">(구분자 ∬)</font>&nbsp;&nbsp;예)&nbsp;주성분명1 <font color="red">∬</font>주성분명2 <font color="red">∬</font></div>
					<textarea id="mainDrugNm" name="mainDrugNm" cols="100" rows="20"  >${drugMain.mainDrugNm}</textarea>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
				<span class="button medium icon"><span class="check"></span><a id="updateDrugMain">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrugMainList">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
	
</form>	
