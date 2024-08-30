<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#close").click(function(){
			self.close();
		});
		
		$("#createSamFile").click(function(){
			$("#statementForm").attr("action", "/statement/download.doo");
			$("#statementForm").submit();
		});
	});
</script>
<!-- 요양급여비용 명세서 헤더 -->
<!-- //요양급여비용 명세서 헤더 -->

<!-- 상병내역 명세서  -->
<table class="table5" style="width:710px;">
	<caption><img src="/resources/img/statement.gif"></caption>
	<colgroup>
		<col width="160px;">
		<col width="70px;">
		<col width="200px;">
		<col width="80px;">
		<col width="120px;">
		<col width="80px;">
	</colgroup>
	<tbody>
		<tr>
			<th class="r">청구번호</th>
			<td colspan="2">${recuperationCostAccount.clmNum}</td>
			<td colspan="3" class="r">
				<!--  
				<span class="button medium icon"><span class="check"></span><a id="createSamFile">청구서생성</a></span>
				-->
				<span class="button medium icon"><span class="check"></span><a id="close">닫기</a></span>
			</td>
		</tr>
		<tr>
			<th class="r">청구서 서식버전</th>
			<td>${recuperationCostAccount.clmDcmtVsn}</td>
			<th class="r">명세서 서식버전</th>
			<td>${recuperationCostAccount.stsDcmtVsn}</td>
			<th class="r">서식번호</th>
			<td>${recuperationCostAccount.dcmtNum}</td>
		</tr>
		<tr>
			<th class="r">요양(의료급여)기관기호</th>
			<td>${recuperationCostAccount.hspId}</td>
			<th class="r">수신기관</th>
			<td>${recuperationCostAccount.rcvInst}</td>
			<th class="r">보험자종별구분</th>
			<td>${recuperationCostAccount.insrDivd}</td>
		</tr>
		<tr>
			<th class="r">청구구분</th>
			<td>${recuperationCostAccount.clmDivd}</td>
			<th class="r">청구단위구분</th>
			<td>${recuperationCostAccount.clmUntDivd}</td>
			<th class="r">진료구분</th>
			<td>${recuperationCostAccount.trtDivd}</td>
		</tr>
		<tr>
			<th class="r">진료분야구분</th>
			<td>${recuperationCostAccount.trtFldDivd}</td>
			<th class="r">진료형태</th>
			<td>${recuperationCostAccount.trtTyp}</td>
			<th class="r">진료년월</th>
			<td>${recuperationCostAccount.trtYrMnth}</td>
		</tr>
		<tr>
			<th class="r">건수</th>
			<td>${recuperationCostAccount.trtCnt}</td>
			<th class="r">요양급여비용총액1</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.recpCst1}" /></td>
			<th class="r">본인일부부담금</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.cinsrCost}" /></td>
		</tr>
		<tr>
			<th class="r">본인부담상한액초과금</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.cinsrOverCst}" /></td>
			<th class="r">청구액</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.clmCst}" /></td>
			<th class="r">지원금</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.sprtCst}" /></td>
		</tr>
		<tr>
			<th class="r">장애인의료비</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.hadipCst}" /></td>
			<th class="r">요양급여비용총액2,진료비총액</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.recpCst2}" /></td>
			<th class="r">보훈청구액</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.helthCst}" /></td>
		</tr>
		<tr>
			<th class="r">100/100본인부담금총액</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.h100Cinsr}" /></td>
			<th class="r">보훈 보인일부부담금</th>
			<td class="r"><fmt:formatNumber type="number" value="${recuperationCostAccount.helthCinsrPrt}" /></td>
			<th class="r">청구일자</th>
			<td>${recuperationCostAccount.clmDt}</td>
		</tr>
		<tr>
			<th class="r">청구인</th>
			<td>${recuperationCostAccount.clmNm}</td>
			<th class="r">장성자성명</th>
			<td>${recuperationCostAccount.wrtNm}</td>
			<th class="r">작성자생년월일</th>
			<td>${fn:substring(recuperationCostAccount.wrtBrtDtEnc,0,6)}-*******</td>
		</tr>
		<tr>
			<th class="r">대행청구단체기호</th>
			<td colspan="5">${recuperationCostAccount.agnClmSym}</td>
		</tr>
		<tr>
			<th class="r">검사승인번호</th>
			<td colspan="5">${recuperationCostAccount.chkAprvNum}</td>
		</tr>
		<tr>
			<th class="r">참조란</th>
			<td colspan="5">${recuperationCostAccount.ref}</td>
		</tr>
	</tbody>
</table>

<table class="table5" style="width:710px;">
	<caption><img src="/resources/img/suga.gif"></caption>
	<colgroup>
		<col width="120px;">
		<col width="230px;">
		<col width="120px;">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th class="r">100분의100미만총액</th>
			<td>${recuperationCostAccount.t100Cst1}</td>
			<th class="r">100분의100미만본인일부부담금</th>
			<td>${recuperationCostAccount.t100Cst2}</td>
		</tr>
		<tr>
			<th class="r">100분의100미만청구액</th>
			<td>${recuperationCostAccount.t100Cst3}</td>
			<th class="r">100분의100미만보훈청구액</th>
			<td>${recuperationCostAccount.t100Cst4}</td>
		</tr>
	</tbody>
</table>


<form id="statementForm" name="statementForm" action="/statement/download2.doo" method="post">
	<input type="hidden" id="hspId" name="hspId" value="${recuperationCostAccount.hspId}" />
	<input type="hidden" id="clmNum" name="clmNum" value="${recuperationCostAccount.clmNum}" />
</form>