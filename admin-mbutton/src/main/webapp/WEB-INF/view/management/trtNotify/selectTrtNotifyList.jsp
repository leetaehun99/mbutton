<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var delCheckArr = new Array();
var cnt = 0;
$(function() {
	$("#createTrtNotify").click(function() { 
		$("#notifyForm").attr("action", "/trtNotify/createTrtNotifyForm.doo");
		$("#notifyForm").submit();
	});
	//검색
	$("#searchList").click(function(){
		$("#notifyForm").attr("action", "/trtNotify/selectTrtNotifyList.doo");
		$("#currentPage").val("1");
		$("#notifyForm").submit();
	});
	
	
	//삭제
	$("#deleteTrtNotify").click(function() {
		
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
		$("#notifyForm").attr("action", "/trtNotify/selectTrtNotifyCnt.json");
		$("#notifyForm").ajaxSubmit(selectTrtNotifyCntAjax);
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
});

readNotify = function(trtNotify,trtNotifySub){
	$("#notifyForm").attr("action", "/trtNotify/selectTrtNotify.doo");
	$("#trtNotify","#notifyForm").val(trtNotify);
	$("#trtNotifySub","#notifyForm").val(trtNotifySub);
	$("#notifyForm").submit();
}

readNotifyItem  = function(trtNotify,trtNotifySub){
	pop('','trtNotifyItem','950', '730');

	$("#trtNotify","#notifyItemForm").val(trtNotify);
	$("#trtNotifySub","#notifyItemForm").val(trtNotifySub);
	$("#notifyItemForm").attr("action", "/trtNotify/selectTrtNotifyItemList.doo");
	$("#notifyItemForm").attr("target", "trtNotifyItem");
	$("#notifyItemForm").submit();	
}

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

searchVal = function(searchText){
	$("#searchType").val("TRT_NOTIFY");
	$("#searchText").val(searchText);
	$("#searchList").click();
}

//하위 테이블 삭제존재유무 체크
var selectTrtNotifyCntAjax = {
	success : function(resultData) {
		if( resultData.length > 0 ) {
			var msg = checkSelectTrtNotifyCntAjax(resultData);
			alert(msg);
		} else {
			var checkConfirm = confirm("정말로 삭제하시겠습니까?");
			if(checkConfirm) {
				$("#notifyForm").attr("action", "/trtNotify/deleteTrtNotify.json");
				$("#notifyForm").ajaxSubmit(deleteAjax);
			}
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

function checkSelectTrtNotifyCntAjax(resultData) {
	var str = "";
	str += "[분류번호, 분류서브번호] : ";
	for(var i =0; i<resultData.length; i++) {
		for(var j=0; j<delCheckArr.length; j++) {
			var a = resultData[i].trtNotify + "_" + resultData[i].trtNotifySub;
			if(a != delCheckArr[j]) {
				continue;
			}
			str += "[" + resultData[i].trtNotify + ", " + resultData[i].trtNotifySub + "]";
			str += " ";
		}
		
	}
	str += "의 하위테이블에 데이터가 존재해서 삭제 할 수 없습니다.";
	return str;
}
</script>


<form id="notifyItemForm" name="notifyItemForm" action="/trtNotify/selectTrtNotifyItemList.doo"method="post">
	<input type="hidden" id="trtNotify"  name="trtNotify" value="0"/>
	<input type="hidden" id="trtNotifySub"  name="trtNotifySub" value="0"/>
</form>
<form id="notifyForm" name="notifyForm" action="/trtNotify/selectTrtNotifyList.doo"method="post">
	<input type="hidden" id="trtNotify"  name="trtNotify" value="0"/>
	<input type="hidden" id="trtNotifySub"  name="trtNotifySub" value="0"/>
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="10%" />
		<col width="*" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00074"  selectCode="${trtNotifyVo.searchType}" defaultCode="MAIN_DRUG_CD1"/>
					<input type="text" id="searchText" name="searchText" value="${trtNotifyVo.searchText }"/>
				</td>
				<td rowspan="2" class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${notifyGroupByList != null}">
						<c:forEach var="notifyGroupByItem" items="${notifyGroupByList}">
						<span style="cursor:pointer;" onclick="javascript:searchVal('${notifyGroupByItem.trtNotify}');">${notifyGroupByItem.trtNotify}</span>,
						</c:forEach>
					</c:if>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- img src="/resources/img/Notify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${trtNotifyVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyForm,'/trtNotify/selectTrtNotifyList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="70px;">
			<col width="70px;">
			<col width="100px;">
			<col width="100px;">
			<col width="200px;">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>인증치료대</th>
				<th>상세</th>
				<th>분류번호</th>
				<th>분류서브번호</th>
				<th>치료대코드</th>
				<th>치료대명</th>
			</tr>
		</thead>
		<tbody id="notifyListTbody">
			<c:if test="${notifyList != null}">
				<c:forEach var="notify" items="${notifyList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${notify.trtNotify}_${notify.trtNotifySub}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${notify.notifyMainNm}_${notify.notifyNm}" />
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyItem('${notify.trtNotify}','${notify.trtNotifySub}');">치료대</a></span></td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotify('${notify.trtNotify}','${notify.trtNotifySub}');">상세</a></span></td>
						<td>${notify.trtNotify}</td>
						<td>${notify.trtNotifySub}</td>
						<td>${notify.notifyMainNm}</td>
						<td><span class="dot400">${notify.notifyNm}</span></td>
						
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(notifyList) == 0}">
				<tr>
					<td colspan="7" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<span class="button medium icon"><span class="check"></span><a id="createTrtNotify">등록</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteTrtNotify">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${trtNotifyVo.pagingHtml}</div>
</form>	
