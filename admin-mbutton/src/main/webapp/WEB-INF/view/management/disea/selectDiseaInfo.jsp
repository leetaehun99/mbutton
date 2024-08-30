<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateDisea").click(function(){ // 약품 수정
		$("#drugForm").attr("action", "/disea/updateDiseaInfo.doo");
		$("#drugForm").submit();
	});
	
	$("#selectDisea").click(function(){ // 목록
		$("#drugForm").attr("action", "/disea/selectDiseaList.doo");
		$("#drugForm").submit();
	});
});

// 게시물 수정Ajax
var updateDiseaAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectDisea").click();
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


<form id="drugForm" name="drugForm" action="/disea/updateDisea.doo" method="post" >
	<!--검색 조건  -->
	<input type="hidden" value="${diseaVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${diseaVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${diseaVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${diseaVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${diseaVo.currentPage}" id="currentPage" name="currentPage"/>
	<!--//검색 조건  -->
	
	<input type="hidden" value="${disea.diseaCd}" id="diseaCd" name="diseaCd"/>
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>질병분류코드</th>
				<td class="l">${disea.diseaCd}</td>
			</tr>
			<tr>
				<th>분류기준</th>
				<td class="l">
				<select disabled name="diseaDiv" disabled>
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
					<select disabled name="diseaHw">
						<option value="" >선택</option>
						<option value="A" <c:if test="${disea.diseaHw eq 'A'}"> selected="selected" </c:if> >1</option>
						<option value="B" <c:if test="${disea.diseaHw eq 'B'}"> selected="selected" </c:if> >2</option>
						<option value="C" <c:if test="${disea.diseaHw eq 'C'}"> selected="selected" </c:if> >없음</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >질병한글명</th>
				<td class="l">${disea.diseaKorNm}</td>
			</tr>
			<tr>
				<th >질병영문명</th>
				<td class="l">${disea.diseaEngNm}</td>
			</tr>
			<tr>
				<th >검별 *,+</th>
				<td class="l">${disea.etc1}</td>
			</tr>
			<tr>
				<th>주석</th>
				<td class="l">
					<select disabled name="etc2">
						<option value="" >선택</option>
						<option value="A" <c:if test="${disea.etc2 eq 'A'}"> selected="selected" </c:if> >주</option>
						<option value="B" <c:if test="${disea.etc2 eq 'B'}"> selected="selected" </c:if> >포함</option>
						<option value="C" <c:if test="${disea.etc2 eq 'C'}"> selected="selected" </c:if> >공백</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >마지막 수정날짜</th>
				<td class="l">${disea.updateDthms}</td>
			</tr>
			<tr>
				<th >마지막 수정자</th>
				<td class="l">${disea.updaterId}</td>
			</tr>
			<% /* 
			<tr>
				<th >상병코드</th>
				<td class="l">
					<table class="table5">
						<caption><!-- img src="/resources/img/Notify2.gif" --></caption>
						<colgroup>
							<col width="10%;">
							<col width="40%">
							<col width="*">
						</colgroup>
						<thead>
							<tr>
								<th>약품코드</th>
								<th>약품명</th>
								<th>약품영문명</th>
							</tr>
						</thead>
						
						<tbody id="drugListTbody">
							<c:if test="${drugMappingList != null}">
								<c:forEach var="drug" items="${drugMappingList}">
									<tr >
										<td><a onclick="javascript:drugInfoPop('${drug.drugCd}');">${drug.drugCd}</a></td>
										<td>${drug.drugNm}</td>
										<td>${drug.drugEnNm}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(drugMappingList) == 0}">
								<tr>
									<td colspan="9" align="center">등록된 게시물이 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</td>
			</tr>
			 */ %>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateDisea">수정</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDisea">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
</form>	