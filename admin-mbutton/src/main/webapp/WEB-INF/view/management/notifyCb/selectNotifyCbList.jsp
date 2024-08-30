<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var cnt = 0;
var delCheckArr = new Array();
$(function() {
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#notifyCbForm").attr("action", "/notifyCb/selectNotifyCbList.doo");
		$("#notifyCbForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});

	//등록폼으로 이동
	$("#createCb").click(function() { 
		$("#notifyCbForm").attr("action", "/notifyCb/createNotifyCbForm.doo");
		$("#notifyCbForm").submit();
	});
	
	//삭제
	$("#deleteNotifyCb").click(function() {
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
		$("#notifyCbForm").attr("action", "/notifyCb/selectNotifyCbCnt.json");
		$("#notifyCbForm").ajaxSubmit(selectNotifyCbCntAjax);
	});
});

readNotifyCb = function(cbNotify,cbNotifySub){
	$("#notifyCbForm").attr("action", "/notifyCb/selectNotifyCb.doo");
	$("#cbNotify","#notifyCbForm").val(cbNotify);
	$("#cbNotifySub","#notifyCbForm").val(cbNotifySub);
	$("#notifyCbForm").submit();
};

readNotifyCbItem  = function(cbNotify,cbNotifySub){
	pop('','notifyCbItem','1000', '730');

	$("#cbNotify","#notifyCbItemForm").val(cbNotify);
	$("#cbNotifySub","#notifyCbItemForm").val(cbNotifySub);
	
	$("#notifyCbItemForm").attr("action", "/notifyCb/selectNotifyCbItemList.doo");
	$("#notifyCbItemForm").attr("target", "notifyCbItem");
	$("#notifyCbItemForm").submit();	
};

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

searchVal = function(searchText){
	$("#searchType").val("DRUG_NOTIFY");
	$("#searchText").val(searchText);
	$("#searchList").click();
};

//하위 테이블 삭제존재유무 체크
var selectNotifyCbCntAjax = {
	success : function(resultData) {
		if( resultData.length > 0 ) {
			var msg = checkSelectNotifyCbCntAjax(resultData);
			alert(msg);
		} else {
			var checkConfirm = confirm("정말로 삭제하시겠습니까?");
			if(checkConfirm) {
				$("#notifyCbForm").attr("action", "/notifyCb/deleteNotifyCb.json");
				$("#notifyCbForm").ajaxSubmit(deleteAjax);
			}
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

function checkSelectNotifyCbCntAjax(resultData) {
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


<form id="notifyCbItemForm" name="notifyCbItemForm" action="/notifyCb/selectDrugNotifyItemList.doo"method="post">
	<input type="hidden" id="cbNotify"  name="cbNotify" value="0"/>
	<input type="hidden" id="cbNotifySub"  name="cbNotifySub" value="0"/>
</form>
<form id="notifyCbForm" name="notifyCbForm" action="/notifyCb/selectNotifyCbList.doo"method="post">
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
					<ct:code name="searchType" type="select" groupCode="00060"  selectCode="${notifyCbVo.searchType }" defaultCode="MAIN_DRUG_CD1"/>
					<input type="text" id="searchText" name="searchText" value="${notifyCbVo.searchText }"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortOrder" type="select" groupCode="00081"  selectCode="${notifyCbVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${notifyCbVo.sSortType }" />
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
		<caption><!-- img src="/resources/img/CombinedBan2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyCbVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyCbForm,'/notifyCb/selectNotifyCbList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="70px;">
			<col width="*">
			<col width="150px;">
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
				<th>주성분명1</th>
				<th>주성분명2</th>
			</tr>
		</thead>
		<tbody id="CombinedBanListTbody">
			<c:if test="${cbList != null}">
				<c:forEach var="cb" items="${cbList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" name="arrDel" class="arrDel" value="${cb.cbNotify }_${cb.cbNotifySub}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${cb.mainDrugNm1 }_${cb.mainDrugNm2}" />
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyCbItem('${cb.cbNotify}','${cb.cbNotifySub}');">병용금기</a></span></td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyCb('${cb.cbNotify}','${cb.cbNotifySub}');">상세</a></span></td>
						<td>${cb.cbNotify}</td>
						<td>${cb.cbNotifySub}</td>
						<td>${cb.notifyNm}</td>
						<td><span class="dot150">${cb.mainDrugNm1}</span></td>
						<td><span class="dot150">${cb.mainDrugNm2}</span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(cbList) == 0}">
				<tr>
					<td colspan="8" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<span class="button medium icon"><span class="check"></span><a id="createCb">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteNotifyCb">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${notifyCbVo.pagingHtml}</div>
</form>	
