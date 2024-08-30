<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	$("#createDrug").click(function(){ // 약품 등록 
		oEditors1.getById["drugEfficacy"].exec("UPDATE_CONTENTS_FIELD", []);
		oEditors2.getById["drugDosage"].exec("UPDATE_CONTENTS_FIELD", []);
		oEditors3.getById["drugTaboo"].exec("UPDATE_CONTENTS_FIELD", []);
		oEditors4.getById["drugNote"].exec("UPDATE_CONTENTS_FIELD", []);
		oEditors5.getById["drugSideEffect"].exec("UPDATE_CONTENTS_FIELD", []);
		oEditors6.getById["drugInteraction"].exec("UPDATE_CONTENTS_FIELD", []);
		$("#drugForm").attr("action", "/drug/createDrug.doo");
		$("#drugForm").submit();
	});
	
	$("#selectDrug").click(function(){ // 목록 이동
		$("#drugForm").attr("action", "/drug/selectDrugList.doo");
		$("#drugForm").submit();

	});
});

</script>
<form id="drugForm" name="drugForm" action="/drug/createDrug.doo" method="post" >

	<input type="hidden" value="${drugVo.searchType}" id="searchType" name="searchType"/>
	<input type="hidden" value="${drugVo.searchText}" id="searchText" name="searchText"/>
	<input type="hidden" value="${drugVo.sSortType}" id="sSortType" name="sSortType"/>
	<input type="hidden" value="${drugVo.rowPerPage}" id="rowPerPage" name="rowPerPage"/>
	<input type="hidden" value="${drugVo.currentPage}" id="currentPage" name="currentPage"/>
	
	<table class="table5">
		<caption><img src="/resources/img/noticeList.gif"></caption>
		<colgroup>
			<col width="25%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<th>약품코드</th>
				<td class="l" colspan="3"><input type="text" id="drugCd" name="drugCd"  size="100"  maxlength="9" /></td>
			</tr>
			<tr>
				<th>적용일자</th>
				<td class="l" colspan="3"><input type="text" id="applyDt" name="applyDt" size="100"  maxlength="45" /></td>
			</tr>
			
			<tr>
				<th>급여구분</th>
				<td class="l" colspan="3">
				<select name="parDiv">
					<option value="" >선택</option>
					<option value="A">급여</option>
					<option value="B">급여정지</option>
					<option value="C">보훈급여</option>
					<option value="D">삭제</option>
					<option value="E">산정불가</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>사용장려비</th>
				<td class="l" colspan="3"><input type="text" id="usePay" name="usePay" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>투여경로</th>
				<td class="l" colspan="3">
					<select name="injectPass">
						<option value="" >선택</option>
						<option value="A">기타</option>
						<option value="B">내복</option>
						<option value="C">외용</option>
						<option value="D">주사</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>품명</th>
				<td class="l" colspan="3"><input type="text" id="drugNm" name="drugNm" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>규격</th>
				<td class="l" colspan="3"><input type="text" id="standard" name="standard" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >단위</th>
				<td class="l" colspan="3"><input type="text" id="unit" name="unit" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>상한가</th>
				<td class="l" colspan="3"><input type="text" id="limitCost" name="limitCost" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>회사</th>
				<td class="l" colspan="3"><input type="text" id="company" name="company" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>분류번호</th>
				<td class="l" colspan="3"><input type="text" id="drugNotify" name="drugNotify" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th>주성분코드</th>
				<td class="l" colspan="3"><input type="text" id="mainDrugCd" name="mainDrugCd" size="100"  maxlength="9" /></td>
			</tr>
			<tr>
				<th >전문/일반</th>
				<td class="l" colspan="3">
					<select name="speGen">
						<option value="" >선택</option>
						<option value="A">전문의약품</option>
						<option value="B">일반의약품</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >퇴장방지구분</th>
				<td class="l" colspan="3">
					<select name="exitCd">
						<option value="" >선택</option>
						<option value="A">사용장려</option>
						<option value="B">원가+장려</option>
						<option value="C">원가보존</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >약품동등구분</th>
				<td class="l" colspan="3">
					<select name="drugDivd">
						<option value="" >선택</option>
						<option value="A">생동(사후통보)</option>
						<option value="B">의약품동등</option>
						<option value="C">필드값없음</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >저가약대체여부</th>
				<td class="l" colspan="3">
					<select name="drugReplaceYn">
						<option value="" >선택</option>
						<option value="Y">예</option>
						<option value="N">아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >예외의약구분</th>
				<td class="l" colspan="3"><input type="text" id="specificDivd" name="specificDivd" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >임의조제불가</th>
				<td class="l" colspan="3">
					<select name="medicineDivd">
						<option value="" >선택</option>
						<option value="A">마약</option>
						<option value="B">오남용 의약품</option>
						<option value="C">한외마약</option>
						<option value="D">향정신성의약품</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >고시일자</th>
				<td class="l" colspan="3"><input type="text" id="notifyDt" name="notifyDt" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >대응코드</th>
				<td class="l" colspan="3"><input type="text" id="respondCd" name="respondCd" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th >희귀의약품구분</th>
				<td class="l" colspan="3">
					<select name="rareYn">
						<option value="" >선택</option>
						<option value="Y">예</option>
						<option value="N">아니요</option>
					</select>
				</td>
			</tr>
			<tr>
				<th >판매예정일</th>                                        
				<td class="l" colspan="3"><input type="text" id="sellDt" name="sellDt" size="100"  maxlength="45" /></td>
			</tr>  
			<tr>
				<th >기타</th>
				<td class="l" colspan="3"><input type="text" id="ext1" name="ext1" size="100"  maxlength="45" /></td>
			</tr>
			<tr>
				<th colspan	="2">효능효과</th>
				<th colspan	="2">용법용량</th>
			</tr>
			<tr>
				<td colspan	="2" class="l"><textarea id="drugEfficacy" name="drugEfficacy" style="height:200px;display:none;">${drug.drugEfficacy}</textarea></td>
				<td colspan	="2" class="l"><textarea id="drugDosage" name="drugDosage" style="height:200px;display:none;">${drug.drugDosage}</textarea></td>
			</tr>
			<tr>
				<th colspan	="2">금기</th>
				<th colspan	="2">주의</th>
			</tr>
			<tr>
				<td colspan	="2" class="l"><textarea id="drugTaboo" name="drugTaboo" style="height:200px;display:none;">${drug.drugTaboo}</textarea></td>
				<td colspan	="2" class="l"><textarea id="drugNote" name="drugNote" style="height:200px;display:none;">${drug.drugNote}</textarea></td>
			</tr>
			<tr>
				<th colspan	="2">부작용</th>
				<th colspan	="2">상호작용</th>
			</tr>
			<tr>
				<td class="l" colspan ="2" ><textarea id="drugSideEffect" name="drugSideEffect" style="height:200px;display:none;">${drug.drugSideEffect}</textarea></td>
				<td class="l" colspan ="2" ><textarea id="drugInteraction" name="drugInteraction" style="height:200px;display:none;">${drug.drugInteraction}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<span class="button medium icon"><span class="check"></span><a id="createDrug">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectDrug">목록</a></span>
				</td>
				<!--<td colspan="7"></td>  -->
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
	var oEditors1 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors1,
	    elPlaceHolder: "drugEfficacy",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor21"
	});
	
	var oEditors2 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors2,
	    elPlaceHolder: "drugDosage",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors3 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors3,
	    elPlaceHolder: "drugTaboo",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors4 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors4,
	    elPlaceHolder: "drugNote",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors5 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors5,
	    elPlaceHolder: "drugSideEffect",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
	
	var oEditors6 = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors6,
	    elPlaceHolder: "drugInteraction",
	    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor23"
	});
</script>
</form>	