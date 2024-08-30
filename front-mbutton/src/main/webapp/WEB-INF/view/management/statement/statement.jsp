<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="customTag"%>



<link type="text/css" href="/resources/css/tab.css" rel="stylesheet" />
<!-- value setting -->
<c:set var="medicalStatementSelectList"    	value="${statement.medicalStatementSelectList}" />
<c:set var="medicalStatement"    	 		value="${statement.medicalStatementVo}" />
<c:set var="diseaseStatementList"    		value="${statement.diseaseStatementList}" />
<c:set var="treatmentStatementList"  		value="${statement.treatmentStatementList}" />
<c:set var="prescriptnStatementList" 		value="${statement.prescriptnStatementList}" />
<c:set var="specificDetailList"      		value="${statement.specificDetailList}" />
<c:set var="errorCheckList"      			value="${statement.errorCheckList}" />
<c:set var="dayOfWeek"      				value="${statement.dayOfWeek}" />
<c:set var="yt" value="${fn:substring(statement.trtStartDt, 0, 4) }"/>
<c:set var="mt" value="${fn:substring(statement.trtStartDt, 4, 6) }"/>
<c:set var="dt" value="${fn:substring(statement.trtStartDt, 6, 8) }"/>
<fmt:formatNumber value="${dt}" type="number" var="dt"/>
<input type="hidden" id="trtStartDt" value="${statement.trtStartDt}"/>
<%@ include file="statementJs.jsp" %>
<div>
	<div class="left">
		<div class="leftBorder">
			<div class="topic"><img src="/resources/img/name.gif"></div>
			<div style="background-image:url(/resources/img/searchGra.jpg); background-repeat:repeat-x; border: 1px solid #b5b5b5; height:31px;">
				<img src="/resources/img/rcvrNm.jpg" align="absmiddle"/>
				<input type="text" id="searchText" value="" size="9" onKeypress="hitEnterKey(event);return;"/>
				<a href="#" id="searchMedicalSts"><img src="/resources/img/btn.jpg" align="absmiddle"/></a>
				<!-- 
				<a href="#" id="allMedicalSts"><img src="/resources/img/btn2.jpg" align="absmiddle"/></a>
				 -->
			</div>
			<table class="table7" summary="name">
				<colgroup><col width="45px;"><col width="100px;"><col width="*"></colgroup>
				<thead><tr><th>No.</th><th>수진자명</th><th>생년월일</th></tr></thead>
			</table>
			<div id="medicalStatementDiv" style="height:235px; overflow-y: scroll; overflow-x: hidden">
				<table class="table7" summary="name">
					<colgroup><col width="45px;"><col width="100px;"><col width="*"></colgroup>
					<tbody id="statementTbody">
					<c:if test="${medicalStatementSelectList != null}">
						<c:forEach var="statementSrlNum" items="${medicalStatementSelectList}" varStatus="idx">
						<tr onclick="javascript:selectStatement('${statementSrlNum.stsSrlNum}');" class="trFocus" <c:if test="${statementSrlNum.checkMsg != null}">style="background : #ffcccc;" id = "red"</c:if>>	
							<td>${statementSrlNum.stsSrlNum}</td>
							<td>${statementSrlNum.rcvrNm}</td>
							<td>${fn:substring(statementSrlNum.birthDy,0,4)}년${fn:substring(statementSrlNum.birthDy,4,6)}월${fn:substring(statementSrlNum.birthDy,6,8)}일</td>
						</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="backgroundSpaceL"></div>
		
		<div class="leftBorder">
			<ul class="tabs">
			    <li><a href="#tab1" id="medicalTab1">기본정보</a></li>
			    <li><a href="#tab2">세부정보</a></li>
			</ul>
			<div class="tab_container">
			    <div id="tab1" class="tab_content">
			       <table class="table7">
			       		<colgroup>
							<col width="85px;">
							<col width="*">
							<col width="100px;">
						</colgroup>
			       		<tbody id="medicalStatementTab1">
				       		<tr><th class="r">명일련</th><td colspan="2"></td></tr>
				       		<tr><th class="r">수진자</th><td></td><td></td></tr>
			       			<tr><th class="r">보험자</th><td></td><td></td></tr>
			       			<tr><th class="r">총진료비</th><td colspan="2" class="r"></td></tr>
			       			<tr><th class="r">본인부담금</th><td colspan="2" class="r"></td></tr>
			       			<tr><th class="r">청구액</th><td colspan="2" class="r"></td></tr>
			       			<tr><th class="r">장애인의료비</th><td colspan="2" class="r"></td></tr>
		       			</tbody>
			       </table>
			    </div>
			    <div id="tab2" class="tab_content">
			     	<table class="table7">
						<colgroup>
						<col width="120px;">
						<col width="*">
						</colgroup>
				     	<tbody id="medicalStatementTab2">
				       		<tr><th class="r">종별가산율</th><td></td></tr>
				       		<tr><th class="r">요양급여비용총액</th><td class="r"></td></tr>
				       		<tr><th class="r">본인일부부담금</th><td class="r"></td></tr>
				       		<tr><th class="r">상한액초과금</th><td class="r"></td></tr>
				       		<tr><th class="r">청구액</th><td class="r"></td></tr>
				       		<tr><th class="r">장애인의료비</th><td class="r"></td></tr>
				       		<tr><th class="r">대불금</th><td class="r"></td></tr>
			       		</tbody>
			       </table>
			    </div>
			</div>
		</div>
		
		<div class="backgroundSpaceL"></div>
		
		<div class="leftBorder">
			<div class="topic">
				<img src="/resources/img/speName.gif">
			</div>
			<div id="specificDetailDiv" style="height:128px; border:1px solid #bebebe; overflow-y:scroll; overflow-x:hidden">
			</div>
		</div>
	</div>

	<div class="blueLine"></div>
	
	<div class="right" >
		<div class="rightBorder">
		
			<div class="topic"><img src="/resources/img/name3.gif">
			</div>	
			<table style="border-collapse: collapse;">
			<tr>
				<td style="width:551px;  float:left;text-align: left; font-family: dotum; font-size:12px; color:#2c2c2c;  padding:0;">
					<table class="table7" summary="name">
					<colgroup><col width="34px;"><col width="70px;"><col width="300px;"><col width="*"></colgroup>
					<thead><tr><th>No.</th><th>분류</th><th>코드</th><th>진료과목</th></tr></thead>
					</table>
					
					<div id="diseaseStsDiv" style="height:150px; overflow-y:scroll; overflow-x:hidden">
						<table class="table7" summary="name">
						<colgroup><col width="34px;"><col width="70px;"><col width="300px;"><col width="*"></colgroup>
						<tbody id="diseaseStatementBody"></tbody>
						</table>
					</div>
				
				</td>
				<td style="width:182px; float:right;text-align: left; font-family: dotum; font-size:12px; color:#2c2c2c; font-weight:bold; padding:0; claer: left;">
					<table class="table0">
						<tr>
							<th colspan="7"><fmt:parseNumber value="${yt}" type="number"/>년<fmt:parseNumber value="${mt}" type="number"/>월</th>
						<tr>
						<tr>
							<th><font color="red">일</font></th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
						<tr>
						<tr>
							<c:forEach var="day" begin="1" items="${dayOfWeek}" varStatus="a">
								<c:if test="${(a.index -1)%7 == 0 }"></tr><tr></c:if>
								<c:if test="${day[0]=='SUN'}"><c:set var="fontColor" value="red"/></c:if>
								<c:if test="${day[0]!='SUN'}"><c:set var="fontColor" value="black"/></c:if>
								<td id="cal2_${day[1]}" class="calCss c" style="text-align:center;" ><font color="${fontColor}">${day[1]}</font></td>
							</c:forEach>
							</tr>
					</table>
				</td>
			</tr>
			</table>	
		</div>
		
		<div class="backgroundSpaceR"></div>
		
	 	<div class="rightBorder" >
			<div class="topic"><img src="/resources/img/name4.gif"></div>
			<table class="table7" summary="name">
				<colgroup><col width="25px"><col width="25px;"><col width="25px;"><col width="25px;"><col width="25px;"><col width="70px;"><col width="270px;"><col width="25px;"><col width="70px;"><col width="70px;"><col width="*"></colgroup>
				<thead><tr><th>No.</th> <th>항</th> <th>목</th> <th>1/2</th> <th>GB</th> <th>코드</th> <th>코드명칭</th> <th>예</th><th>금액(투여)</th> <th>일투(횟수)</th><th>총투(일수)</th> </tr></thead>
			</table>
			<div id="trtStsDiv" style="height:261px; overflow-y:scroll; overflow-x:hidden;">
			<table class="table7" summary="name">
				<colgroup><col width="25px"><col width="25px;"><col width="25px;"><col width="25px;"><col width="25px;"><col width="70px;"><col width="270px;"><col width="25px;"><col width="70px;"><col width="70px;"><col width="*"></colgroup>
		  		<tbody id="treatmentStatementBody"><tr><td colspan="12"></td></tr></tbody>
			</table>
			</div>
		</div> 
		
		<div class="backgroundSpaceR"></div>
		
		<div class="rightBorder">
			<div class="topic"><img src="/resources/img/name5.gif"></div>
			<table class="table7" summary="name">
				<colgroup><col width="34px;"><col width="100px;"><col width="100px;"><col width="*"></colgroup>
				<thead><tr><th>No.</th><th>구분</th><th>코드</th><th>점검내역</th></tr></thead>
			</table>
			<div style="height:111px; overflow-y:scroll; overflow-x:hidden">
				<table class="table7" summary="name">
					<colgroup><col width="34px;"><col width="100px;"><col width="100px;"><col width="*"></colgroup>
	 				<tbody id="errorCheckBody"></tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="popWrapDetail" class="popWrapDetail" style="cursor:default;">
	 <div class="popImg" style="cursor:pointer;">
		 <img src="/resources/img/pop1.png" /><img src="/resources/img/pop2.png" /><img src="/resources/img/pop3.png" onclick="javascript:$.unblockUI();" style="cursor:pointer;">
	 </div>
	 <div class="popTable1">
		 <table class="table7">
			<tr>
				<th style="text-align: left; background: #ebebeb; padding-left: 20px; padding-right:5px;" width="100px">코드</th>
				<td class="l" colspan="3" id="popTxt1"></td>
			</tr>
			<tr>
				<th style="text-align: left; background: #ebebeb; padding-left: 20px;">메세지</th>
				<td class="l" colspan="3" id="popTxt2"></td>
			</tr>		
	    </table>
		<ul class="tabs2">
		    <li><a href="#tab4" id="popTab1">관련근거</a></li>
		    <li><a href="#tab5" id="popTab2">효능효과</a></li>
		    <li><a href="#tab6" id="popTab3">용량요법</a></li>
		    <li><a href="#tab7" id="popTab4">금기사항</a></li>
		    <li><a href="#tab3" id="popTab5">인정상병</a></li>
		    <li><a href="#tab8" id="popTab6">심사사례</a></li>
		</ul>
		<div class="tab_container">	    
		    <div id="tab4" class="tab_content2">
		     	<table class="table7">
	       			<tr>
			       		<td class="l"><div id="tab4Text" class="tab2Text"></div></td>
	       			</tr>
		       </table>
		    </div>
		    <div id="tab5" class="tab_content2">
		     	<table class="table7">
	       			<tr>
			       		<td class="l"><div id="tab5Text" class="tab2Text"></div></td>
	       			</tr>
		       </table>
		    </div>
		    <div id="tab6" class="tab_content2">
		     	<table class="table7">
	       			<tr>
			       		<td class="l"><div id="tab6Text" class="tab2Text"></div></td>
	       			</tr>
		       </table>
		    </div>
		    <div id="tab7" class="tab_content2">
		     	<table class="table7">
	       			<tr>
			       		<td class="l"><div id="tab7Text" class="tab2Text"></div></td>
	       			</tr>
		       </table>
		    </div>
		    <div id="tab3" class="tab_content2">
				<table class="table7" style="height: 23px;">
					<colgroup><col width="34px;"><col width="80px;"><col width="*;"></colgroup>
					<tr><th>No</th><th>상병코드</th><th >상병명칭</th></tr>
				</table>
				<div style="height:247px; overflow-y:scroll; overflow-x:hidden; width: 507px;">
					<table class="table7">
						<colgroup><col width="34px;"><col width="80px;"><col width="*;"></colgroup>
						<tbody id="checkDrugTbody"></tbody>
					</table>
				</div>
		    </div>
		    <div id="tab8" class="tab_content2">
		     	<table class="table7">
	       			<tr>
			       		<td class="l"><div id="tab8Text" class="tab2Text"></div></td>
	       			</tr>
		       </table>
		    </div>
		</div>
	</div>
</div>

<div style="display:none;"><table id="tempStsList" ><tbody></tbody></table></div>

<div id="loadingDiv" style="display:none;width:295px;">
	<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
		<div class="xboxcontent">
		<img src='/resources/img/loading_001.gif'/>
		</div>
	<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>
	
<!-- 청구서 -->
<form id="statementForm" name="statementForm" action="/statement/selectStatement.doo" method="post">
	<input type="hidden" id="clmNum" name="clmNum" value="${statement.clmNum }" /> 
	<input type="hidden" id="hspId" name="hspId" value="${statement.hspId }" /> 
	<input type="hidden" id="recpCstClmSeq" name="recpCstClmSeq" value="${statement.recpCstClmSeq }" /> 
	<input type="hidden" id="stsSrlNum" name="stsSrlNum" value="${statement.stsSrlNum }" /> 
	<input type="hidden" id="msgLev" name="msgLev" />
	<input type="hidden" id="ymd" name="ymd" />
</form>

<form id="errorCheckDetailForm" name="errorCheckDetailForm" action="/statement/selectCheckContents.json" method="post">
	<input type="hidden" id="msgType" name="msgType" value="" /> 
	<input type="hidden" id="errCd" name="errCd" value="" /> 
	<input type="hidden" id="notify" name="notify" value="" /> 
	<input type="hidden" id="notifySub" name="notifySub" value="" /> 
</form>