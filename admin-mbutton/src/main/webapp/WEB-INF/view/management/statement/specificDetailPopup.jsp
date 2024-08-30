<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#createForm").click(function(){
			
			if(!blankCheck("#speDetailDivd",  "#specificDetailCreateForm", "특정내역구분은 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#speDetail",  "#specificDetailCreateForm", "특정내역은 필수 입력사항입니다.")) { return false; }
			if($("#prscrptnDivd").val()=="2") if(!blankCheck("#lnNum",  "#specificDetailCreateForm", "줄번호는 필수 입력사항입니다.")) { return false; }
			
			//$("#specificDetailCreateForm").attr("action","/statement/createSpecificDetail.doo");
			$("#specificDetailCreateForm").attr("action","/statementTest/createSpecificDetail.doo");
			$("#specificDetailCreateForm").submit();
		});
		
		$("#newForm").click(function(){
			$("#modeType").val("Y");	
			$("#speDetailDivd").val("");
			$("#speDetail").val("");
			if($("#prscrptnDivd").val()=="2") $("#lnNum").val("");
		});
		
		$("#speDetailDivd").change(function(){
			if("1" == $("#prscrptnDivd").val()){
				if("MS001"==$(this).val()){
					$("#speDetailSpan").text("9(3)");
				}else if("MS002"==$(this).val()){
					$("#speDetailSpan").text("9(3)");
				}else if("MS003"==$(this).val()){
					$("#speDetailSpan").text("9(2)");
				}else if("MS004"==$(this).val()){
					$("#speDetailSpan").text("9(4)");
				}else if("MS005"==$(this).val()){
					$("#speDetailSpan").text("ccyymmddhhmm/ccyymmddhhmm");
				}else if("MS006"==$(this).val()){
					$("#speDetailSpan").text("ccyymmdd");
				}else if("MS007"==$(this).val()){
					$("#speDetailSpan").text("X(4)/X(2)");
				}else if("MS008"==$(this).val()){
					$("#speDetailSpan").text("X(4)/X(3)/X(2)/X(2)");
				}else if("MS009"==$(this).val()){
					$("#speDetailSpan").text("9(1)/9(2)/9(2)");
				}else if("MS010"==$(this).val()){
					$("#speDetailSpan").text("X(1)");
				}else if("MT001"==$(this).val()){
					$("#speDetailSpan").text("X(1)");
				}else if("MT002"==$(this).val()){
					$("#speDetailSpan").text("X(4)");
				}else if("MT003"==$(this).val()){
					$("#speDetailSpan").text("9(8)/9(1)");
				}else if("MT004"==$(this).val()){
					$("#speDetailSpan").text("X(1)");
				}else if("MT005"==$(this).val()){
					$("#speDetailSpan").text("9(13)");
				}else if("MT006"==$(this).val()){
					$("#speDetailSpan").text("9(2)");
				}else if("MT007"==$(this).val()){
					$("#speDetailSpan").text("dd/X/X(9)/9(10)/9(5)V9(2)/9(3)/9(10)/X(200)");
				}else if("MT008"==$(this).val()){
					$("#speDetailSpan").text("9(6)/9(2)");
				}else if("MT010"==$(this).val()){
					$("#speDetailSpan").text("X(1)/X(1)/X(1)/X(1)/X(1)/X(1)/ccyymmdd");
				}else if("MT011"==$(this).val()){
					$("#speDetailSpan").text("X(1)/X(1)/X(1)/X(1)/X(1)/ccyymmdd");
				}else if("MT014"==$(this).val()){
					$("#speDetailSpan").text("9(15)");
				}else if("MT015"==$(this).val()){
					$("#speDetailSpan").text("X(2)");
				}else if("MT016"==$(this).val()){
					$("#speDetailSpan").text("X(200)");
				}else if("MT018"==$(this).val()){
					$("#speDetailSpan").text("X(4)");
				}else if("MT019"==$(this).val()){
					$("#speDetailSpan").text("X(13)");
				}else if("MT020"==$(this).val()){
					$("#speDetailSpan").text("9(2)");
				}else if("MT021"==$(this).val()){
					$("#speDetailSpan").text("9(1)");
				}else if("MT022"==$(this).val()){
					$("#speDetailSpan").text("9(1)");
				}else if("MT023"==$(this).val()){
					$("#speDetailSpan").text("9(1)");
				}else if("MT025"==$(this).val()){
					$("#speDetailSpan").text("ccyymmdd/9(1)V9(1)");
				}else if("MT026"==$(this).val()){
					$("#speDetailSpan").text("9(5)");
				}else if("MT027"==$(this).val()){
					$("#speDetailSpan").text("9(4)");
				}else if("MT028"==$(this).val()){
					$("#speDetailSpan").text("X(5)/X(60)");
				}else if("MT998"==$(this).val()){
					$("#speDetailSpan").text("X/X(9)/9(5)V9(2)/9(3)/X(200)");
				}else if("MT999"==$(this).val()){
					$("#speDetailSpan").text("X/X(9)/9(5)V9(2)/9(3)/9(3)");
				}else if("MX999"==$(this).val()){
					$("#speDetailSpan").text("x(700)");
				}
			}else{
				if("JS001"==$(this).val()){ //마취과 전문의
					$("#speDetailSpan").text("9(13)/9(6)/X(20)");
				}else if("JS002"==$(this).val()){//의약분업예외구분코드
					$("#speDetailSpan").text("9(2)");
				}else if("JS003"==$(this).val()){//입원시각
					$("#speDetailSpan").text("ccyymmddhhmm");
				}else if("JS004"==$(this).val()){//퇴원시각
					$("#speDetailSpan").text("ccyymmddhhmm");
				}else if("JS005"==$(this).val()){
					$("#speDetailSpan").text("9(8)/ccyymmdd");
				}else if("JS006"==$(this).val()){
					$("#speDetailSpan").text("9(8)/ccyymmdd");
				}else if("JS007"==$(this).val()){
					$("#speDetailSpan").text("9(8)/ccyymmdd");
				}else if("JS008"==$(this).val()){
					$("#speDetailSpan").text("9(8)/ccyymmdd");
				}else if("JS009"==$(this).val()){
					$("#speDetailSpan").text("X(700)");
				}else if("JS010"==$(this).val()){
					$("#speDetailSpan").text("ccyymmddhhmm");
				}else if("JS011"==$(this).val()){
					$("#speDetailSpan").text("X(5)");
				}else if("JT001"==$(this).val()){
					$("#speDetailSpan").text("X(5)");
				}else if("JT002"==$(this).val()){
					$("#speDetailSpan").text("9(2)/ccyymmdd");
				}else if("JT003"==$(this).val()){
					$("#speDetailSpan").text("ccyymmdd/ccyymmdd");
				}else if("JT004"==$(this).val()){
					$("#speDetailSpan").text("9(2)/9(4)");
				}else if("JT005"==$(this).val()){
					$("#speDetailSpan").text("9(2)");
				}else if("JT006"==$(this).val()){
					$("#speDetailSpan").text("X(1)/HHMM/X(9)/X(30)/X(500)");
				}else if("JT007"==$(this).val()){
					$("#speDetailSpan").text("9(2)/ccyymmdd/9(1)V9(1)/ccyymmdd/9(1)/ccyymmdd");
				}else if("JT009"==$(this).val()){
					$("#speDetailSpan").text("X(1)/X(200)");
				}else if("JT010"==$(this).val()){
					$("#speDetailSpan").text("X(1)/X(200)");
				}else if("JT011"==$(this).val()){
					$("#speDetailSpan").text("X(400)");
				}else if("JT012"==$(this).val()){
					$("#speDetailSpan").text("X(1)/X(200)");
				}else if("JX999"==$(this).val()){
					$("#speDetailSpan").text("X(700)");
				}
			}
		});
		
		readSpecificDetail = function(seq){
			$("#speDetailSeq").val(seq);
			$("#modeType").val("N");
			$("#specificDetailCreateForm").attr("action","/statement/selectSpecificDetail.json");
			$("#specificDetailCreateForm").ajaxSubmit(readSpecificDetailAjax);
		};
		
		// 특전내역 조회Ajax
		var readSpecificDetailAjax = {
			beforeSend : function(){
				//$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
			},
			success : function(resultData) {
				if(resultData != null){
					$("#speDetailDivd").val(resultData.speDetailDivd);
					$("#speDetailDivd").change();
					$("#speDetail").val(resultData.speDetail);
					if($("#prscrptnDivd").val()=="2") $("#lnNum").val(resultData.lnNum);
				}else {
					alert("조회에 실패하였습니다.");
				}
				$.unblockUI();
			},
			type : "post",
			dataType : "json",
			error : function() {
				alert("서버와의 통신에 실패 하였습니다.");
				$.unblockUI();
			}
		};
		
		deleteSpecificDetail = function(seq){
			$("#speDetailSeq").val(seq);
			$("#specificDetailCreateForm").attr("action","/statement/deleteSpecificDetail.doo");
			$("#specificDetailCreateForm").submit();
		};
	});
</script>

<form id="specificDetailCreateForm" name="specificDetailCreateForm" method="post" action="">
<input type="hidden" id="prscrptnDivd" name="prscrptnDivd" value="${specificDetailVo.prscrptnDivd}" />
<input type="hidden"  name="stsSrlNum" value="${specificDetailVo.stsSrlNum}" />
<input type="hidden"  name="clmNum" value="${specificDetailVo.clmNum}" />
<input type="hidden"  name="hspId" value="${specificDetailVo.hspId}" />
<input type="hidden"  name="recpCstClmSeq" value="${specificDetailVo.recpCstClmSeq}" />

<input type="hidden"  id="speDetailSeq" name="speDetailSeq" value="" />
<input type="hidden"  id="modeType" name="modeType" value="Y" />

<div>
	<table class="table0" >
	<caption><img src="/resources/img/search.gif"></caption>
	<col width="100px;" />
	<col width="*" />
	<col width="180px;" />
	<tbody>
		<tr>
			<th>구분코드</th>
			<td>
			
			<c:if test="${tLnList!=null}">
			<select id="lnNum" name="lnNum">
				<c:forEach var="ln" items="${tLnList}">
					<option value="${ln.lnNum}">${ln.lnNum}</option>
				</c:forEach>
			</select>
			</c:if>
			
			<c:if test="${specificDetailVo.prscrptnDivd == '1'}">
				<ct:code name="speDetailDivd" type="select" groupCode="00045"  selectCode="" displayType="CD"/>
			</c:if>		
			<c:if test="${specificDetailVo.prscrptnDivd == '2'}">
				<ct:code name="speDetailDivd" type="select" groupCode="00046"  selectCode="" displayType="CD"/>	
			</c:if>	
				<input type="text" id="speDetail" name="speDetail" value=""/>
				<span id="speDetailSpan"></span>
			</td>
			<td class="center">
				<span class="button medium icon"><span class="check"></span><a id="newForm">신규</a></span>
				<span class="button medium icon"><span class="check"></span><a id="createForm">등록</a></span>
			</td>
		</tr>
	</tbody>
	</table>
</div>

<!-- 특정내역기제란 -->
	<table class="table5">
	<caption><img src="/resources/img/specificDetail.gif"></caption>
	<colgroup>
		<c:if test="${specificDetailVo.prscrptnDivd == '2'}"><col width="100px;"></c:if>
		<col width="200px;">
		<col width="*">
		<col width="180px;">
	</colgroup>
	<thead>
		<tr>
			<c:if test="${specificDetailVo.prscrptnDivd == '2'}"><th>줄</th></c:if>
			<th>특정내역구분</th>
			<th>특정내역</th>
			<th>상세</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${specificDetailList != null}">
			<c:forEach var="specificDetail" items="${specificDetailList}">
				<tr>
					<c:if test="${specificDetailVo.prscrptnDivd == '2'}"><td>${specificDetail.lnNum}</td></c:if>
					<td>${specificDetail.speDetailDivd}[${specificDetail.speCdNm}]</td>
					<td class='l'>${specificDetail.speDetail}</td>
					<td><span class='button medium icon'><span class='check'></span><a onclick="javascript:readSpecificDetail('${specificDetail.speDetailSeq}');">상세</a></span>&nbsp;&nbsp;<span class='button medium icon'><span class='check'></span><a onclick="javascript:deleteSpecificDetail('${specificDetail.speDetailSeq}');">삭제</a></span></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${specificDetailList == null || fn:length(specificDetailList)  == 0}">
			<tr><td  <c:if test="${specificDetailVo.prscrptnDivd == '2'}">colspan="4"</c:if> <c:if test="${specificDetailVo.prscrptnDivd == '1'}">colspan="3"</c:if>>등록된 특정내역이 없습니다.</td></tr>
		</c:if>
	</tbody>
</table>
	<!-- 페이지번호 -->
	<div class="paging">${specificDetailVo.pagingHtml}</div>
</form>


<!-- //특정내역기제란 -->