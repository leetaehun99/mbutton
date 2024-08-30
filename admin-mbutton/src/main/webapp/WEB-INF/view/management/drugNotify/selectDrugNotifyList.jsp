<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var delCheckArr = new Array();
var cnt = 0;
$(function() {
	$("#createDrugNotify").click(function() { 
		$("#drugNotifyForm").attr("action", "/drugNotify/createDrugNotifyForm.doo");
		$("#drugNotifyForm").submit();
	});
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#drugNotifyForm").attr("action", "/drugNotify/selectDrugNotifyList.doo");
		$("#drugNotifyForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
	
	//삭제
	$("#deleteDrugNotify").click(function() {
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
		$("#drugNotifyForm").attr("action", "/drugNotify/selectDrugNotifyCnt.json");
		$("#drugNotifyForm").ajaxSubmit(selectDrugNotifyCntAjax);
	});
});

readDrugNotify = function(drugNotify,drugNotifySub){
	$("#drugNotifyForm").attr("action", "/drugNotify/selectDrugNotify.doo");
	$("#drugNotify","#drugNotifyForm").val(drugNotify);
	$("#drugNotifySub","#drugNotifyForm").val(drugNotifySub);
	$("#drugNotifyForm").submit();
}

readDrugNotifyItem  = function(drugNotify,drugNotifySub){
	pop('','drugNotifyItem','1000', '800');

	$("#drugNotify","#drugNotifyItemForm").val(drugNotify);
	$("#drugNotifySub","#drugNotifyItemForm").val(drugNotifySub);
	$("#drugNotifyItemForm").attr("action", "/drugNotify/selectDrugNotifyItemList.doo");
	$("#drugNotifyItemForm").attr("target", "drugNotifyItem");
	$("#drugNotifyItemForm").submit();	
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
var selectDrugNotifyCntAjax = {
	success : function(resultData) {
		if( resultData.length > 0 ) {
			var msg = checkSelectDrugNotifyCntAjax(resultData);
			alert(msg);
		} else {
			var checkConfirm = confirm("정말로 삭제하시겠습니까?");
			if(checkConfirm) {
				$("#drugNotifyForm").attr("action", "/drugNotify/deleteDrugNotify.json");
				$("#drugNotifyForm").ajaxSubmit(deleteAjax);
			}
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

function checkSelectDrugNotifyCntAjax(resultData) {
	var str = "";
	str += "[고시, 서브고시] : ";
	for(var i =0; i<resultData.length; i++) {
		for(var j=0; j<delCheckArr.length; j++) {
			var a = resultData[i].drugNotify + "_" + resultData[i].drugNotifySub;
			if(a != delCheckArr[j]) {
				continue;
			}
			str += "[" + resultData[i].drugNotify + ", " + resultData[i].drugNotifySub + "]";
			str += " ";
		}
		
	}
	str += "의 하위테이블에 데이터가 존재해서 삭제 할 수 없습니다.";
	return str;
}
</script>


<form id="drugNotifyItemForm" name="drugNotifyItemForm" action="/drugNotify/selectDrugNotifyItemList.doo"method="post">
	<input type="hidden" id="drugNotify"  name="drugNotify" value="0"/>
	<input type="hidden" id="drugNotifySub"  name="drugNotifySub" value="0"/>
</form>
<form id="drugNotifyForm" name="drugNotifyForm" action="/drugNotify/selectDrugNotifyList.doo"method="post">
	<input type="hidden" id="drugNotify"  name="drugNotify" value="0"/>
	<input type="hidden" id="drugNotifySub"  name="drugNotifySub" value="0"/>
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
					<ct:code name="searchType" type="select" groupCode="00057"  selectCode="${drugNotifyVo.searchType }"  defaultCode="DRUG_CD"/>
					<input type="text" id="searchText" name="searchText" value="${drugNotifyVo.searchText }"/>
				</td>
				<td rowspan="2" class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<c:if test="${drugNotifyGroupByList != null}">
						<c:forEach var="drugNotifyGroupByItem" items="${drugNotifyGroupByList}">
						<span style="cursor:pointer;" onclick="javascript:searchVal('${drugNotifyGroupByItem.drugNotify}');">${drugNotifyGroupByItem.drugNotify}</span>,
						</c:forEach>
					</c:if>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- img src="/resources/img/DrugNotify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${drugNotifyVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.drugNotifyForm,'/drugNotify/selectDrugNotifyList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="70px;">
			<col width="70px;">
			<col width="100px;">
			<col width="200px;">
			<col width="*">
			<col width="100px;">
			<col width="100px;">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>인증약가</th>
				<th>상세</th>
				<th>고시명</th>
				<th>재재명</th>
				<th>품명명</th>
				<th>고시번호</th>
				<th>서브고시</th>
			</tr>
		</thead>
		<tbody id="drugNotifyListTbody">
			<c:if test="${drugNotifyList != null}">
				<c:forEach var="drugNotify" items="${drugNotifyList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${drugNotify.drugNotify }_${drugNotify.drugNotifySub}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${drugNotify.drugNotifyNm}_${drugNotify.drugNotifyMainNm}" />
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readDrugNotifyItem('${drugNotify.drugNotify}','${drugNotify.drugNotifySub}');">약가</a></span></td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readDrugNotify('${drugNotify.drugNotify}','${drugNotify.drugNotifySub}');">상세</a></span></td>
						
						<td>${drugNotify.drugNotifyNm}</td>
						<td><span class="dot400">${drugNotify.drugNotifyMainNm}</span></td>
						<td class="l">${drugNotify.drugNotifyItem}</td>
						
						<td>${drugNotify.drugNotify}</td>
						<td>${drugNotify.drugNotifySub}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(drugNotifyList) == 0}">
				<tr>
					<td colspan="8" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<span class="button medium icon"><span class="check"></span><a id="createDrugNotify">등록</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteDrugNotify">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${drugNotifyVo.pagingHtml}</div>
</form>	