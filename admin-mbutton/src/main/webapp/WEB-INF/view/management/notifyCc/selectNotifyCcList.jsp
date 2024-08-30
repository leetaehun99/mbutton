<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var delCheckArr = new Array();
var cnt = 0;
$(function() {
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#notifyCcForm").attr("action", "/notifyCc/selectNotifyCcList.doo");
		$("#notifyCcForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});

	//등록폼으로 이동
	$("#createCc").click(function() { 
		$("#notifyCcForm").attr("action", "/notifyCc/createNotifyCcForm.doo");
		$("#notifyCcForm").submit();
	});
	
	//삭제
	$("#deleteNotifyCc").click(function() {
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
		
		//삭제 체크추출
		$("input[name=arrDel]:checked").each(function() {
			var test = $(this).val();
			delCheckArr[cnt] = test;
			cnt++;
		});
		$("#notifyCcForm").attr("action", "/notifyCc/selectNotifyCcCnt.json");
		$("#notifyCcForm").ajaxSubmit(selectNotifyCcCntAjax);
	});
	
});

readNotifyCc = function(cbNotify,cbNotifySub){
	$("#notifyCcForm").attr("action", "/notifyCc/selectNotifyCc.doo");
	$("#cbNotify","#notifyCcForm").val(cbNotify);
	$("#cbNotifySub","#notifyCcForm").val(cbNotifySub);
	$("#notifyCcForm").submit();
}

readNotifyCcItem  = function(cbNotify,cbNotifySub){
	pop('','notifyCcItem','1000', '730');

	$("#cbNotify","#notifyCcItemForm").val(cbNotify);
	$("#cbNotifySub","#notifyCcItemForm").val(cbNotifySub);
	
	$("#notifyCcItemForm").attr("action", "/notifyCc/selectNotifyCcItemList.doo");
	$("#notifyCcItemForm").attr("target", "notifyCcItem");
	$("#notifyCcItemForm").submit();	
}

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

searchVal = function(searchText){
	$("#searchType").val("DRUG_NOTIFY");
	$("#searchText").val(searchText);
	$("#searchList").click();
}

//하위 테이블 삭제존재유무 체크
var selectNotifyCcCntAjax = {
	success : function(resultData) {
		if( resultData.length > 0 ) {
			var msg = checkSelectNotifyCcCntAjax(resultData);
			alert(msg);
		} else {
			var checkConfirm = confirm("정말로 삭제하시겠습니까?");
			if(checkConfirm) {
				$("#notifyCcForm").attr("action", "/notifyCc/deleteNotifyCc.json");
				$("#notifyCcForm").ajaxSubmit(deleteAjax);
			}
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

function checkSelectNotifyCcCntAjax(resultData) {
	var str = "";
	str += "[고시, 서브고시] : ";
	for(var i =0; i<resultData.length; i++) {
		for(var j=0; j<delCheckArr.length; j++) {
			var a = resultData[i].cbNotify + "_" + resultData[i].cbNotifySub;
			if(a != delCheckArr[j]) {
				continue;
			}
			str += "[" + resultData[i].cbNotify + ", " + resultData[i].cbNotifySub + "]";
			str += " ";
		}
		
	}
	str += "의 하위테이블에 데이터가 존재해서 삭제 할 수 없습니다.";
	return str;
}
</script>


<form id="notifyCcItemForm" name="notifyCcItemForm" action="/notifyCc/selectNotifyCcItemList.doo"method="post">
	<input type="hidden" id="cbNotify"  name="cbNotify" value="0"/>
	<input type="hidden" id="cbNotifySub"  name="cbNotifySub" value="0"/>
</form>
<form id="notifyCcForm" name="notifyCcForm" action="/notifyCc/selectNotifyCcList.doo"method="post">
	<input type="hidden" id="cbNotify"  name="cbNotify" value="0"/>
	<input type="hidden" id="cbNotifySub"  name="cbNotifySub" value="0"/>
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="13%" />
		<col width="29%" />
		<col width="13" />
		<col width="30%" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00080"  selectCode="${notifyCcVo.searchType }" defaultCode="MAIN_DRUG_CD"/>
					<input type="text" id="searchText" name="searchText" value="${notifyCcVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortOrder" type="select" groupCode="00082"  selectCode="${notifyCcVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${notifyCcVo.sSortType }" />
				</td>
				<td rowspan="2" class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- img src="/resources/img/CombinedBan2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyCcVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyCcForm,'/notifyCc/selectNotifyCcList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="*">
			<col width="150px;">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>인증약가</th>
				<th>상세</th>
				<th>고시번호</th>
				<th>서브</th>
				<th>고시명</th>
				<th>주성분명</th>
			</tr>
		</thead>
		<tbody id="CombinedBanListTbody">
			<c:if test="${ccList != null}">
				<c:forEach var="cc" items="${ccList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${cc.cbNotify }_${cc.cbNotifySub}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${cc.notifyMainNm}" />
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyCcItem('${cc.cbNotify}','${cc.cbNotifySub}');">단일금기</a></span></td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyCc('${cc.cbNotify}','${cc.cbNotifySub}');">상세</a></span></td>
						<td>${cc.cbNotify}</td>
						<td>${cc.cbNotifySub}</td>
						<td>${cc.notifyNm}</td>
						<td><span class="dot150">${cc.notifyMainNm}</span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(ccList) == 0}">
				<tr>
					<td colspan="7" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<span class="button medium icon"><span class="check"></span><a id="createCc">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteNotifyCc">삭제</a></span></span>
				</td>
			
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${notifyCcVo.pagingHtml}</div>
</form>	