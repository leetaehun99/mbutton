<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	//등록폼 초기화
	$("#initValue").click(function() { 
		initValue();
		btnDisplay(true);
	});
	
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#drugNotifyItemForm").attr("action", "/drugNotify/selectDrugNotifyItemList.doo");
		$("#drugNotifyItemForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
	
	//등록
	$("#createDrugNotifyItem").click(function(){
		
		if(!blankLenCheck("#mainDrugCd",  "#createDrugNotifyItemForm", "주성분코드는 필수입력 사항입니다.",9)) { return false; }
		if(!blankCheck("#useYn",  "#createDrugNotifyItemForm", "사용여부 필수선택 사항입니다.")) { return false; }
		$("#createDrugNotifyItemForm").attr("action", "/drugNotify/createDrugNotifyItem.json");
		$("#createDrugNotifyItemForm").ajaxSubmit(createDrugNotifyItemAjax);
		btnDisplay(true);
	});
	
	//수정
	$("#updateDrugNotifyItem").click(function(){
		if(!blankCheck("#mainDrugCd",  "#createDrugNotifyItemForm", "주성분코드는 필수입력 사항입니다.",9)) { return false; }
		if(!blankCheck("#useYn",  "#createDrugNotifyItemForm", "사용여부 필수선택 사항입니다.")) { return false; }
		$("#createDrugNotifyItemForm").attr("action", "/drugNotify/updateDrugNotifyItem.json");
		$("#createDrugNotifyItemForm").ajaxSubmit(updateDrugNotifyItemAjax);
		//btnDisplay(true);
	});
	

	//삭제
	$("#deleteDrugNotifyItem").click(function() {
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}

		var checkConfirm = confirm("정말로 삭제하시겠습니까?")
		if(checkConfirm) {	
			$("#drugNotifyItemForm").attr("action", "/drugNotify/deleteDrugNotifyItem.json");
			$("#drugNotifyItemForm").ajaxSubmit(deleteAjax);
		}
	});
	
	$("#delMsg").click(function(){
		
		$("input[name='msgBox']").each(function(){
			if($(this).is(":checked")) {
				if($(this).parent().parent().attr("id")!="msg0") $(this).parent().parent().remove();
			}
		});
	});

	btnDisplay(true);

	// 체크 박스 모두 체크
	$("#checkAll").click(function() {
		if ($("#checkValue").val() == 'N') {
			$("input[name=checkDrugNotify]:checkbox").each(function() {
				$(this).prop("checked", true);
				$("#M" + $(this).attr("id")).html("적용");
			});
			$("#checkValue").val("Y");
		} else {
			$("input[name=checkDrugNotify]:checkbox").each(function() {
				$(this).prop("checked", false);
				$("#M" + $(this).attr("id")).html("미적용");
			});	
			$("#checkValue").val("N");
		}
	});
	
	// 체크 박스 모두 체크
	$("#updateUseYn").click(function() {
		var cnt = 0;
		$("input[name=checkDrugNotify]:checkbox").each(function() {
			if ($(this).prop("checked") == true && $(this).val() == 'Y') {
				// 변경점없음 1
				// 체크가 되어있고 해당값이 Y이면 변경있음
			} else if ($(this).prop("checked") != 'checked' && $(this).val() == 'N') {
				// 체크가 안되어있고 해당값이 N이면 변경없음
				// 변경점없음 2
			} else {
				// 변경점있음
				var useYn = "N";
				
				if ($(this).prop("checked") == true) {
					 useYn = "Y";
					 $(this).val("Y");
					 $("#M" + $(this).attr("id")).html("적용");
				} else {
					 $(this).val("N");
					 $("#M" + $(this).attr("id")).html("미적용");
				}
				
				$("#mainDrugCd","#updateDrugNotifyuseYnItem").val($(this).attr("class"));
				$("#useYn","#updateDrugNotifyuseYnItem").val(useYn);
				$("#updateDrugNotifyuseYnItem").attr("action", "/drugNotify/updateDrugNotifyuseYnItem.json");
				$("#updateDrugNotifyuseYnItem").ajaxSubmit(updateDrugNotifyuseYnItem);
				cnt++;
			}
		});
		
		if (cnt > 0) {
			alert("변경되었습니다.");
		}
	});
});


//고시 약가 등록Ajax
var createDrugNotifyItemAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#searchList","#drugNotifyItemForm").click();
		} else if( resultData == 98){
			alert("존재하지 않는 주성분 코드입니다. 주성분 코드를 등록후 이용하십시오");
		} else if( resultData == 99){
			alert("약가 코드가 이미 존재 합니다.");
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

//고시 약가 수정Ajax
var updateDrugNotifyuseYnItem = {
	success : function(resultData) {
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
	    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
	}
};

//고시 약가 수정Ajax
var updateDrugNotifyItemAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			//$("#searchList","#drugNotifyItemForm").click();
		}  else if( resultData == 98){
			alert("존재하지 않는 주성분 코드입니다. 주성분 코드를 등록후 이용하십시오");
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

var readDrugNotifyItem = function(mainDrugCd){
	$("#mainDrugCd","#drugNotifyItemForm").val(mainDrugCd);
	
	$("#drugNotifyItemForm").attr("action", "/drugNotify/selectDrugNotifyItem.json");
	$("#drugNotifyItemForm").ajaxSubmit(selectDrugNotifyItemAjax);
	

	$("#drugNotifyItemTbody tr").each(function(){
		
		if(drugCd == $.trim($(this).find("td:eq(2)").text()))  $(this).css("background-color","#e5edfe");
		else  $(this).css("background-color","");
	});
	
	btnDisplay(false);
};

msgDel = function(mainDrugCd , mSeq){
	if(confirm("메세지를 삭제하기겠습니까?")==true){
		$("#mainDrugCd","#updateDrugNotifyuseYnItem").val(mainDrugCd);
		$("#mSeq","#updateDrugNotifyuseYnItem").val(mSeq);
		$("#updateDrugNotifyuseYnItem").attr("action", "/drugNotify/deleteDrugMsg.json");
		$("#updateDrugNotifyuseYnItem").ajaxSubmit(deleteDrugMsgAjax);
	}
}

//고시 약가 수정Ajax
var deleteDrugMsgAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("삭제 되었습니다.");
			readDrugNotifyItem($("#drugCd","#updateDrugNotifyuseYnItem").val());
		} else {
			alert("삭제에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

var selectDrugNotifyItemAjax = {
	success : function(resultData) {
		if( resultData != null ) {
			list = resultData.msgList;
			$("#mainDrugCd","#createDrugNotifyItemForm").val(resultData.mainDrugCd);
			$("#useYn","#createDrugNotifyItemForm").val(resultData.useYn);
		} else {
			$("#drugCd","#createDrugNotifyItemForm").val($("#drugCd","#drugNotifyItemForm").val());
			$("#useYn","#createDrugNotifyItemForm").val("N");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

//버튼 표현
btnDisplay = function(type){
	if(type){//등록
		$("#btnMode2").hide();
		$("#btnMode1").show();
	}else{//수정
		$("#btnMode1").hide();
		$("#btnMode2").show();
	}
}

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

//입력필드 초기화
initValue = function(){
	$("#mainDrugCd",  "#createDrugNotifyItemForm").val('');
};

document.title = '[${drugNotify.drugNotify} - ${drugNotify.drugNotifySub}] ${drugNotify.drugNotifyNm} - ${drugNotify.drugNotifyMainNm}(${drugNotify.drugNotifyItem})';

//적용 미적용 변경
changeId = function(id){
	if ($("#"+id).prop("checked") == true) {
		
		$("."+$("#"+id).attr("class")).each(function(){
			$(this).prop("checked",true);
		});
		
		$(".M"+$("#"+id).attr("class")).each(function(){
			$(this).html("적용");
		});
	} else {
		
		$("."+$("#"+id).attr("class")).each(function(){
			$(this).prop("checked",false);
		});
		
		$(".M"+$("#"+id).attr("class")).each(function(){
			$(this).html("미적용");
		});
	}
};

</script>



<form id="drugNotifyItemForm" name="drugNotifyItemForm" action="/drugNotify/selectDrugNotifyItemList.doo"method="post">
	<input type="hidden" id="drugNotify"  name="drugNotify" value="${drugNotifyVo.drugNotify}"/>
	<input type="hidden" id="drugNotifySub"  name="drugNotifySub" value="${drugNotifyVo.drugNotifySub}"/>
	
	<input type="hidden" id="mainDrugCd" name="mainDrugCd" value="" />
	<input type="hidden" id="checkValue" value="N" />
	
	<div >
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="35%" />
		<col width="*" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<select name="searchType" id="searchType" >
						<option value="" <c:if test="${drugNotifyVo.searchType==''}">selected</c:if>>선택해주십시오</option>
						<option value="MAIN_DRUG_CD" <c:if test="${drugNotifyVo.searchType=='MAIN_DRUG_CD'}">selected</c:if>>주성분코드</option>
					</select>
					<input type="text" id="searchText" name="searchText" value="${drugNotifyVo.searchText }"/>
				</td>
				<td class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	<div style="overflow-y:scroll;height:470px;">
	<table class="table5">
		<caption><!-- img src="/resources/img/DrugNotify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${drugNotifyVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.drugNotifyItemForm,'/drugNotify/selectDrugNotifyItemList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="90px;">
			<col width="70px;">
			<col width="100px;">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>적용여부<input type="checkbox" id="checkAll" style="vertical-align:-4px" /></th>	
				<th>상세</th>	
				<th>주성분코드</th>
				<th>주성분명</th>
			</tr>
		</thead>
		<tbody id="drugNotifyItemTbody">
			<c:if test="${drugNotifyItemList != null}">
				<c:forEach var="drugNotifyItem" items="${drugNotifyItemList}">
				<c:set var="mainDrugNms" value="${fn:split(drugNotifyItem.mainDrugNm,'∬')}"/>
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${drugNotifyItem.mainDrugCd}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${mainDrugNms[0]} <c:if test="${fn:length(mainDrugNms)!=1}">외 ${fn:length(mainDrugNms)-1} 개</c:if>" />
						</td>
						<td>
							<input type="checkbox" 
							style="width:13px;height:13px;vertical-align:text-top" 
							value="${drugNotifyItem.useYn}" id="${drugNotifyItem.mainDrugCd}" onclick="javascript:changeId('${drugNotifyItem.mainDrugCd}')" name="checkDrugNotify" <c:if test="${drugNotifyItem.useYn eq 'Y'}" > checked </c:if> />
							<span id="M${drugNotifyItem.mainDrugCd}"  style="vertical-align:-3px">
								<c:if test="${drugNotifyItem.useYn eq 'Y'}" >적용</c:if>
								<c:if test="${drugNotifyItem.useYn ne 'Y'}" >미적용</c:if>
							</span>
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readDrugNotifyItem('${drugNotifyItem.mainDrugCd}');">상세</a></span></td>
						<td> ${drugNotifyItem.mainDrugCd}</td>
						<td>${mainDrugNms[0]} <c:if test="${fn:length(mainDrugNms)!=1}">외 ${fn:length(mainDrugNms)-1} 개</c:if></td>
						
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(drugNotifyItemList) == 0}">
				<tr>
					<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
				<tr><td colspan="5">
					<span class="button medium icon"><span class="check"></span><a id="updateUseYn">적용</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteDrugNotifyItem">삭제</a></span></span>
				</td></tr>
		</tfoot>
	</table>
	</div>
	<!-- 페이지번호 -->
	<div class="paging">${drugNotifyVo.pagingHtml}</div>
</form>
<form id="createDrugNotifyItemForm" name="createDrugNotifyItemForm" action="/drugNotify/createDrugNotifyItem.json"method="post">
<input type="hidden" id="drugNotify" name="drugNotify" value="${drugNotifyVo.drugNotify}"/>
<input type="hidden" id="drugNotifySub" name="drugNotifySub" value="${drugNotifyVo.drugNotifySub}" />	

	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="30%">
			<col width="20%">
			<col width="30%">			
		</colgroup>
		<tbody>
			<tr>
				<th>주성분코드</th>	
				<td><input type="text" id="mainDrugCd" name="mainDrugCd" value="" maxlength="9"/></td>
				<th>적용여부</th>	
				<td><ct:code name="useYn" type="select" groupCode="00002"  defaultCode="Y" selectCode=""/></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" >
					<span id="btnMode1" ><span class="button medium icon"><span class="check"></span><a id="createDrugNotifyItem">등록</a></span></span>
					<span id="btnMode2" ><span class="button medium icon"><span class="check"></span><a id="updateDrugNotifyItem">수정</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>

<form id="updateDrugNotifyuseYnItem" name="updateDrugNotifyuseYnItem" action="/drugNotify/updateDrugNotifyuseYnItem.json"method="post">
	<input type="hidden" id="drugNotify"  name="drugNotify" value="${drugNotifyVo.drugNotify}"/>
	<input type="hidden" id="drugNotifySub"  name="drugNotifySub" value="${drugNotifyVo.drugNotifySub}"/>
	<input type="hidden" id="mainDrugCd" name="mainDrugCd" value="" />
	<input type="hidden" id="mSeq" name="mSeq" value="" />
	<input type="hidden" id="useYn" name="useYn" value="N" />
</form>
