<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#updateDrug").click(function(){ // 약품 수정
		$("#drugForm").attr("action", "/drug/updateDrugInfo.doo");
		$("#drugForm").submit();
	});
	
	$("#selectDrug").click(function(){ // 목록
		$("#drugForm").attr("action", "/drug/selectDrugList.doo");
		$("#drugForm").submit();
	});
});

// 게시물 수정Ajax
var updateDrugAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#selectDrug").click();
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


<form id="drugForm" name="drugForm" action="/drug/updateDrug.doo" method="post" >

	<input type="hidden" value="${drugVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${drugVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${drugVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${drugVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${drugVo.currentPage}" id="currentPage" name="currentPage"/>
	
	
	<input type="hidden" value="${drug.drugCd}" id="drugCd" name="drugCd"/>
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>약품코드</th>
				<td class="l">${drug.drugCd}</td>
			</tr>
			<tr>
				<th>적용일자</th>
				<td class="l">${drug.applyDt}</td>
			</tr>
			
			<tr>
				<th>급여구분</th>
				<td class="l">
				<select disabled name="parDiv" disabled>
					<option value="" >선택</option>
					<option value="A" <c:if test="${drug.parDiv eq 'A'}"> selected="selected" </c:if> >급여</option>
					<option value="B" <c:if test="${drug.parDiv eq 'B'}"> selected="selected" </c:if> >급여정지</option>
					<option value="C" <c:if test="${drug.parDiv eq 'C'}"> selected="selected" </c:if> >보훈급여</option>
					<option value="D" <c:if test="${drug.parDiv eq 'D'}"> selected="selected" </c:if> >삭제</option>
					<option value="E" <c:if test="${drug.parDiv eq 'E'}"> selected="selected" </c:if> >산정불가</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>사용장려비</th>
				<td class="l">${drug.usePay}</td>
			</tr>
			<tr>
				<th>투여경로</th>
				<td class="l">
					<select disabled name="injectPass">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.injectPass eq 'A'}"> selected="selected" </c:if> >기타</option>
						<option value="B" <c:if test="${drug.injectPass eq 'B'}"> selected="selected" </c:if> >내복</option>
						<option value="C" <c:if test="${drug.injectPass eq 'C'}"> selected="selected" </c:if> >외용</option>
						<option value="D" <c:if test="${drug.injectPass eq 'D'}"> selected="selected" </c:if> >주사</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>품명</th>
				<td class="l">${drug.drugNm}</td>
			</tr>
			<tr>
				<th>규격</th>
				<td class="l">${drug.standard}</td>
			</tr>
			<tr>
				<th >단위</th>
				<td class="l">${drug.unit}</td>
			</tr>
			<tr>
				<th>상한가</th>
				<td class="l">${drug.limitCost}</td>
			</tr>
			<tr>
				<th>회사</th>
				<td class="l">${drug.company}</td>
			</tr>
			<tr>
				<th>분류번호</th>
				<td class="l">${drug.drugNotify}</td>
			</tr>
			<tr>
				<th>주성분코드</th>
				<td class="l">${drug.mainDrugCd}</td>
			</tr>
			<tr>
				<th >전문/일반</th>
				<td class="l">
					<select disabled name="speGen">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.speGen eq 'A'}"> selected="selected" </c:if> >전문의약품</option>
						<option value="B" <c:if test="${drug.speGen eq 'B'}"> selected="selected" </c:if> >일반의약품</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >퇴장방지구분</th>
				<td class="l">
					<select disabled name="exitCd">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.exitCd eq 'A'}"> selected="selected" </c:if> >사용장려</option>
						<option value="B" <c:if test="${drug.exitCd eq 'B'}"> selected="selected" </c:if> >원가+장려</option>
						<option value="C" <c:if test="${drug.exitCd eq 'C'}"> selected="selected" </c:if> >원가보존</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >약품동등구분</th>
				<td class="l">
					<select disabled name="drugDivd">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.drugDivd eq 'A'}"> selected="selected" </c:if> >생동(사후통보)</option>
						<option value="B" <c:if test="${drug.drugDivd eq 'B'}"> selected="selected" </c:if> >의약품동등</option>
						<option value="C" <c:if test="${drug.drugDivd eq 'C'}"> selected="selected" </c:if> >필드값없음</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >저가약대체여부</th>
				<td class="l">
					<select disabled name="drugReplaceYn">
						<option value="" >선택</option>
						<option value="Y" <c:if test="${drug.drugReplaceYn eq 'A'}"> selected="selected" </c:if> >예</option>
						<option value="N" <c:if test="${drug.drugReplaceYn eq 'B'}"> selected="selected" </c:if> >아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >예외의약구분</th>
				<td class="l">${drug.specificDivd}</td>
			</tr>
			<tr>
				<th >임의조제불가</th>
				<td class="l">
					<select disabled name="medicineDivd">
						<option value="" >선택</option>
						<option value="A" <c:if test="${drug.medicineDivd eq 'A'}"> selected="selected" </c:if> >마약</option>
						<option value="B" <c:if test="${drug.medicineDivd eq 'B'}"> selected="selected" </c:if> >오남용 의약품</option>
						<option value="C" <c:if test="${drug.medicineDivd eq 'C'}"> selected="selected" </c:if> >한외마약</option>
						<option value="D" <c:if test="${drug.medicineDivd eq 'C'}"> selected="selected" </c:if> >향정신성의약품</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >고시일자</th>
				<td class="l">${drug.notifyDt}</td>
			</tr>
			<tr>
				<th >대응코드</th>
				<td class="l">${drug.respondCd}</td>
			</tr>
			<tr>
				<th >희귀의약품구분</th>
				<td class="l">
					<select disabled name="rareYn">
						<option value="" >선택</option>
						<option value="Y" <c:if test="${drug.rareYn eq 'A'}"> selected="selected" </c:if> >예</option>
						<option value="N" <c:if test="${drug.rareYn eq 'B'}"> selected="selected" </c:if> >아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >판매예정일</th>
				<td class="l">${drug.sellDt}</td>
			</tr>
			<tr>
				<th >약제상한차액지급제외</th>
				<td class="l">
					<select disabled name="drugLimitDiffExceptYn">
						<option value="" >선택</option>
						<option value="Y" <c:if test="${drug.drugLimitDiffExceptYn eq 'A'}"> selected="selected" </c:if> >예</option>
						<option value="N" <c:if test="${drug.drugLimitDiffExceptYn eq 'B'}"> selected="selected" </c:if> >아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >약품영문명</th>
				<td class="l">${drug.drugEnNm}</td>
			</tr>
			<tr>
				<c:set var="mainDrugNms" value="${fn:replace(fn:trim(drug.mainDrugNm), '∬', '</BR>')}" />
				<th >주성분영문명</th>
				<td class="l"> ${mainDrugNms}</td>
			</tr>
			<tr>
				<th >기타</th>
				<td class="l">${drug.ext1}</td>
			</tr>
			<tr>
				<th >마지막 수정날짜</th>
				<td class="l">${drug.updateDthms}</td>
			</tr>
			<tr>
				<th >마지막 수정자</th>
				<td class="l">${drug.updaterId}</td>
			</tr>
			<tr>
				<th rowspan	="2">효능효과</th>
			</tr>
			<tr>
				<td class="l">${drug.drugEfficacy}</td>
			</tr>
			<tr>
				<th rowspan	="2">용법용량</th>
			</tr>
			<tr>
				<td class="l">${drug.drugDosage}</td>
			</tr>
			<tr>
				<th rowspan	="2">금기</th>
			</tr>
			<tr>
				<td class="l">${drug.drugTaboo}</td>
			</tr>
			<tr>
				<th rowspan	="2">주의</th>
			</tr>
			<tr>
				<td class="l">${drug.drugNote}</td>
			</tr>
			<tr>
				<th rowspan	="2">부작용</th>
			</tr>
			<tr>
				<td class="l">${drug.drugSideEffect}</td>
			</tr>
			<tr>
				<th rowspan	="2">상호작용</th>
			</tr>
			<tr>
				<td class="l">${drug.drugInteraction}</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="updateDrug">수정</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrug">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
</form>	